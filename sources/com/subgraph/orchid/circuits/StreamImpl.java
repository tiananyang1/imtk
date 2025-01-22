package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.cells.RelayCellImpl;
import com.subgraph.orchid.dashboard.DashboardRenderable;
import com.subgraph.orchid.dashboard.DashboardRenderer;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/StreamImpl.class */
public class StreamImpl implements Stream, DashboardRenderable {
    private static final int STREAMWINDOW_INCREMENT = 50;
    private static final int STREAMWINDOW_MAX_UNFLUSHED = 10;
    private static final int STREAMWINDOW_START = 500;
    private static final Logger logger = Logger.getLogger(StreamImpl.class.getName());
    private final boolean autoclose;
    private final CircuitImpl circuit;
    private boolean isClosed;
    private boolean relayConnectedReceived;
    private int relayEndReason;
    private boolean relayEndReceived;
    private final int streamId;
    private final CircuitNode targetNode;
    private final Object waitConnectLock = new Object();
    private final Object windowLock = new Object();
    private String streamTarget = "";
    private final TorInputStream inputStream = new TorInputStream(this);
    private final TorOutputStream outputStream = new TorOutputStream(this);
    private int packageWindow = STREAMWINDOW_START;
    private int deliverWindow = STREAMWINDOW_START;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamImpl(CircuitImpl circuitImpl, CircuitNode circuitNode, int i, boolean z) {
        this.circuit = circuitImpl;
        this.targetNode = circuitNode;
        this.streamId = i;
        this.autoclose = z;
    }

    private void considerSendingSendme() {
        synchronized (this.windowLock) {
            if (this.deliverWindow > 450) {
                return;
            }
            if (this.inputStream.unflushedCellCount() >= 10) {
                return;
            }
            this.circuit.sendRelayCell(this.circuit.createRelayCell(5, this.streamId, this.targetNode));
            this.deliverWindow += 50;
        }
    }

    private void waitForRelayConnected(long j) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.waitConnectLock) {
            long j2 = 0;
            while (!this.relayConnectedReceived) {
                if (this.relayEndReceived) {
                    throw new StreamConnectFailedException(this.relayEndReason);
                }
                if (j2 >= j) {
                    throw new TimeoutException();
                }
                this.waitConnectLock.wait(j - j2);
                j2 = System.currentTimeMillis() - currentTimeMillis;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addInputCell(RelayCell relayCell) {
        if (this.isClosed) {
            return;
        }
        if (relayCell.getRelayCommand() == 3) {
            synchronized (this.waitConnectLock) {
                this.relayEndReason = relayCell.getByte();
                this.relayEndReceived = true;
                this.inputStream.addEndCell(relayCell);
                this.waitConnectLock.notifyAll();
            }
            return;
        }
        if (relayCell.getRelayCommand() == 4) {
            synchronized (this.waitConnectLock) {
                this.relayConnectedReceived = true;
                this.waitConnectLock.notifyAll();
            }
            return;
        }
        if (relayCell.getRelayCommand() == 5) {
            synchronized (this.windowLock) {
                this.packageWindow += 50;
                this.windowLock.notifyAll();
            }
            return;
        }
        this.inputStream.addInputCell(relayCell);
        synchronized (this.windowLock) {
            this.deliverWindow--;
            if (this.deliverWindow < 0) {
                throw new TorException("Stream has negative delivery window");
            }
        }
        considerSendingSendme();
    }

    @Override // com.subgraph.orchid.Stream
    public void close() {
        if (this.isClosed) {
            return;
        }
        logger.fine("Closing stream " + this);
        this.isClosed = true;
        this.inputStream.close();
        this.outputStream.close();
        this.circuit.removeStream(this);
        if (this.autoclose) {
            this.circuit.markForClose();
        }
        if (this.relayEndReceived) {
            return;
        }
        RelayCellImpl relayCellImpl = new RelayCellImpl(this.circuit.getFinalCircuitNode(), this.circuit.getCircuitId(), this.streamId, 3);
        relayCellImpl.putByte(6);
        this.circuit.sendRelayCellToFinalNode(relayCellImpl);
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderable
    public void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) throws IOException {
        printWriter.print("     ");
        printWriter.print("[Stream stream_id=" + this.streamId + " cid=" + this.circuit.getCircuitId());
        if (this.relayConnectedReceived) {
            printWriter.print(" sent=" + this.outputStream.getBytesSent() + " recv=" + this.inputStream.getBytesReceived());
        } else {
            printWriter.print(" (waiting connect)");
        }
        printWriter.print(" target=" + this.streamTarget);
        printWriter.println("]");
    }

    @Override // com.subgraph.orchid.Stream
    public Circuit getCircuit() {
        return this.circuit;
    }

    @Override // com.subgraph.orchid.Stream
    public InputStream getInputStream() {
        return this.inputStream;
    }

    @Override // com.subgraph.orchid.Stream
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override // com.subgraph.orchid.Stream
    public int getStreamId() {
        return this.streamId;
    }

    @Override // com.subgraph.orchid.Stream
    public CircuitNode getTargetNode() {
        return this.targetNode;
    }

    public void openDirectory(long j) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        this.streamTarget = "[Directory]";
        this.circuit.sendRelayCellToFinalNode(new RelayCellImpl(this.circuit.getFinalCircuitNode(), this.circuit.getCircuitId(), this.streamId, 13));
        waitForRelayConnected(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void openExit(String str, int i, long j) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        this.streamTarget = str + Constants.COLON_SEPARATOR + i;
        RelayCellImpl relayCellImpl = new RelayCellImpl(this.circuit.getFinalCircuitNode(), this.circuit.getCircuitId(), this.streamId, 1);
        relayCellImpl.putString(str + Constants.COLON_SEPARATOR + i);
        this.circuit.sendRelayCellToFinalNode(relayCellImpl);
        waitForRelayConnected(j);
    }

    public String toString() {
        return "[Stream stream_id=" + this.streamId + " circuit=" + this.circuit + " target=" + this.streamTarget + "]";
    }

    @Override // com.subgraph.orchid.Stream
    public void waitForSendWindow() {
        waitForSendWindow(false);
    }

    public void waitForSendWindow(boolean z) {
        synchronized (this.windowLock) {
            while (this.packageWindow == 0) {
                try {
                    this.windowLock.wait();
                } catch (InterruptedException e) {
                    throw new TorException("Thread interrupted while waiting for stream package window");
                }
            }
            if (z) {
                this.packageWindow--;
            }
        }
        this.targetNode.waitForSendWindow();
    }

    public void waitForSendWindowAndDecrement() {
        waitForSendWindow(true);
    }
}
