package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.Connection;
import com.subgraph.orchid.ConnectionIOException;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.Threading;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.cells.CellImpl;
import com.subgraph.orchid.circuits.cells.RelayCellImpl;
import com.subgraph.orchid.dashboard.DashboardRenderable;
import com.subgraph.orchid.dashboard.DashboardRenderer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitIO.class */
public class CircuitIO implements DashboardRenderable {
    private static final long CIRCUIT_BUILD_TIMEOUT_MS = 30000;
    private static final long CIRCUIT_RELAY_RESPONSE_TIMEOUT = 20000;
    private static final Logger logger = Logger.getLogger(CircuitIO.class.getName());
    private final CircuitImpl circuit;
    private final int circuitId;
    private final Connection connection;
    private boolean isClosed;
    private boolean isMarkedForClose;
    private final ReentrantLock streamLock = Threading.lock("stream");
    private final ReentrantLock relaySendLock = Threading.lock("relaySend");
    private final BlockingQueue<RelayCell> relayCellResponseQueue = new LinkedBlockingQueue();
    private final BlockingQueue<Cell> controlCellResponseQueue = new LinkedBlockingQueue();
    private final Map<Integer, StreamImpl> streamMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitIO(CircuitImpl circuitImpl, Connection connection, int i) {
        this.circuit = circuitImpl;
        this.connection = connection;
        this.circuitId = i;
    }

    private void closeCircuit() {
        logger.fine("Closing circuit " + this.circuit);
        sendDestroyCell(0);
        this.connection.removeCircuit(this.circuit);
        this.circuit.setStateDestroyed();
        this.isClosed = true;
    }

    private RelayCell decryptRelayCell(Cell cell) {
        for (CircuitNode circuitNode : this.circuit.getNodeList()) {
            if (circuitNode.decryptBackwardCell(cell)) {
                return RelayCellImpl.createFromCell(circuitNode, cell);
            }
        }
        destroyCircuit();
        throw new TorException("Could not decrypt relay cell");
    }

    private Level getLogLevelForCell(RelayCell relayCell) {
        int relayCommand = relayCell.getRelayCommand();
        return (relayCommand == 2 || relayCommand == 5) ? Level.FINEST : Level.FINER;
    }

    private long getReceiveTimeout() {
        return this.circuit.getStatus().isBuilding() ? remainingBuildTime() : CIRCUIT_RELAY_RESPONSE_TIMEOUT;
    }

    private void logRelayCell(String str, RelayCell relayCell) {
        Level logLevelForCell = getLogLevelForCell(relayCell);
        if (logger.isLoggable(logLevelForCell)) {
            logger.log(logLevelForCell, str + relayCell);
        }
    }

    private void processCircuitSendme(RelayCell relayCell) {
        relayCell.getCircuitNode().incrementSendWindow();
    }

    private void processDestroyCell(int i) {
        logger.fine("DESTROY cell received (" + CellImpl.errorToDescription(i) + ") on " + this.circuit);
        destroyCircuit();
    }

    private void processRelayDataCell(RelayCell relayCell) {
        if (relayCell.getRelayCommand() == 2) {
            relayCell.getCircuitNode().decrementDeliverWindow();
            if (relayCell.getCircuitNode().considerSendingSendme()) {
                RelayCell createRelayCell = createRelayCell(5, 0, relayCell.getCircuitNode());
                sendRelayCellTo(createRelayCell, createRelayCell.getCircuitNode());
            }
        }
        this.streamLock.lock();
        try {
            StreamImpl streamImpl = this.streamMap.get(Integer.valueOf(relayCell.getStreamId()));
            if (streamImpl != null) {
                streamImpl.addInputCell(relayCell);
            }
        } finally {
            this.streamLock.unlock();
        }
    }

    private long remainingBuildTime() {
        long millisecondsElapsedSinceCreated = this.circuit.getStatus().getMillisecondsElapsedSinceCreated();
        if (millisecondsElapsedSinceCreated == 0 || millisecondsElapsedSinceCreated >= CIRCUIT_BUILD_TIMEOUT_MS) {
            return 0L;
        }
        return CIRCUIT_BUILD_TIMEOUT_MS - millisecondsElapsedSinceCreated;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamImpl createNewStream(boolean z) {
        this.streamLock.lock();
        try {
            int nextStreamId = this.circuit.getStatus().nextStreamId();
            StreamImpl streamImpl = new StreamImpl(this.circuit, this.circuit.getFinalCircuitNode(), nextStreamId, z);
            this.streamMap.put(Integer.valueOf(nextStreamId), streamImpl);
            return streamImpl;
        } finally {
            this.streamLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RelayCell createRelayCell(int i, int i2, CircuitNode circuitNode) {
        return new RelayCellImpl(circuitNode, this.circuitId, i2, i);
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderable
    public void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) throws IOException {
        if ((i & 16) == 0) {
            return;
        }
        Iterator<Stream> it = getActiveStreams().iterator();
        while (it.hasNext()) {
            dashboardRenderer.renderComponent(printWriter, i, it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deliverControlCell(Cell cell) {
        if (cell.getCommand() == 4) {
            processDestroyCell(cell.getByte());
        } else {
            this.controlCellResponseQueue.add(cell);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0038. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void deliverRelayCell(com.subgraph.orchid.Cell r5) {
        /*
            r4 = this;
            r0 = r4
            com.subgraph.orchid.circuits.CircuitImpl r0 = r0.circuit
            com.subgraph.orchid.circuits.CircuitStatus r0 = r0.getStatus()
            r0.updateDirtyTimestamp()
            r0 = r4
            r1 = r5
            com.subgraph.orchid.RelayCell r0 = r0.decryptRelayCell(r1)
            r5 = r0
            r0 = r4
            java.lang.String r1 = "Dispatching: "
            r2 = r5
            r0.logRelayCell(r1, r2)
            r0 = r5
            int r0 = r0.getRelayCommand()
            r6 = r0
            r0 = r6
            r1 = 15
            if (r0 == r1) goto Lcf
            r0 = r6
            r1 = 37
            if (r0 == r1) goto Lcf
            r0 = r6
            r1 = 39
            if (r0 == r1) goto Lcf
            r0 = r6
            r1 = 40
            if (r0 == r1) goto Lcf
            r0 = r6
            switch(r0) {
                case 1: goto La4;
                case 2: goto L9e;
                case 3: goto L9e;
                case 4: goto L9e;
                case 5: goto L89;
                case 6: goto La4;
                case 7: goto Lcf;
                case 8: goto La4;
                case 9: goto Lcf;
                default: goto L6c;
            }
        L6c:
            r0 = r6
            switch(r0) {
                case 11: goto La4;
                case 12: goto Lcf;
                case 13: goto La4;
                default: goto L88;
            }
        L88:
            return
        L89:
            r0 = r5
            int r0 = r0.getStreamId()
            if (r0 == 0) goto L98
            r0 = r4
            r1 = r5
            r0.processRelayDataCell(r1)
            return
        L98:
            r0 = r4
            r1 = r5
            r0.processCircuitSendme(r1)
            return
        L9e:
            r0 = r4
            r1 = r5
            r0.processRelayDataCell(r1)
            return
        La4:
            r0 = r4
            r0.destroyCircuit()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r7 = r0
            r0 = r7
            java.lang.String r1 = "Unexpected 'forward' direction relay cell type: "
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r7
            r1 = r5
            int r1 = r1.getRelayCommand()
            java.lang.StringBuilder r0 = r0.append(r1)
            com.subgraph.orchid.TorException r0 = new com.subgraph.orchid.TorException
            r1 = r0
            r2 = r7
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        Lcf:
            r0 = r4
            java.util.concurrent.BlockingQueue<com.subgraph.orchid.RelayCell> r0 = r0.relayCellResponseQueue
            r1 = r5
            boolean r0 = r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.CircuitIO.deliverRelayCell(com.subgraph.orchid.Cell):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RelayCell dequeueRelayResponseCell() {
        try {
            return this.relayCellResponseQueue.poll(getReceiveTimeout(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroyCircuit() {
        this.streamLock.lock();
        try {
            if (this.isClosed) {
                return;
            }
            this.circuit.setStateDestroyed();
            this.connection.removeCircuit(this.circuit);
            Iterator it = new ArrayList(this.streamMap.values()).iterator();
            while (it.hasNext()) {
                ((StreamImpl) it.next()).close();
            }
            this.isClosed = true;
        } finally {
            this.streamLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Stream> getActiveStreams() {
        this.streamLock.lock();
        try {
            return new ArrayList(this.streamMap.values());
        } finally {
            this.streamLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCircuitId() {
        return this.circuitId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Connection getConnection() {
        return this.connection;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isMarkedForClose() {
        this.streamLock.lock();
        try {
            return this.isMarkedForClose;
        } finally {
            this.streamLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markForClose() {
        this.streamLock.lock();
        try {
            if (this.isMarkedForClose) {
                return;
            }
            this.isMarkedForClose = true;
            if (this.streamMap.isEmpty()) {
                closeCircuit();
            }
        } finally {
            this.streamLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cell receiveControlCellResponse() {
        try {
            return this.controlCellResponseQueue.poll(getReceiveTimeout(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void removeStream(com.subgraph.orchid.circuits.StreamImpl r4) {
        /*
            r3 = this;
            r0 = r3
            java.util.concurrent.locks.ReentrantLock r0 = r0.streamLock
            r0.lock()
            r0 = r3
            java.util.Map<java.lang.Integer, com.subgraph.orchid.circuits.StreamImpl> r0 = r0.streamMap     // Catch: java.lang.Throwable -> L44
            r1 = r4
            int r1 = r1.getStreamId()     // Catch: java.lang.Throwable -> L44
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L44
            java.lang.Object r0 = r0.remove(r1)     // Catch: java.lang.Throwable -> L44
            r0 = r3
            java.util.Map<java.lang.Integer, com.subgraph.orchid.circuits.StreamImpl> r0 = r0.streamMap     // Catch: java.lang.Throwable -> L44
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L44
            if (r0 == 0) goto L32
            r0 = r3
            boolean r0 = r0.isMarkedForClose     // Catch: java.lang.Throwable -> L44
            r6 = r0
            r0 = r6
            if (r0 == 0) goto L32
            r0 = 1
            r5 = r0
            goto L34
        L32:
            r0 = 0
            r5 = r0
        L34:
            r0 = r3
            java.util.concurrent.locks.ReentrantLock r0 = r0.streamLock
            r0.unlock()
            r0 = r5
            if (r0 == 0) goto L43
            r0 = r3
            r0.closeCircuit()
        L43:
            return
        L44:
            r4 = move-exception
            r0 = r3
            java.util.concurrent.locks.ReentrantLock r0 = r0.streamLock
            r0.unlock()
            r0 = r4
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.CircuitIO.removeStream(com.subgraph.orchid.circuits.StreamImpl):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendCell(Cell cell) {
        CircuitStatus status = this.circuit.getStatus();
        if (status.isConnected() || status.isBuilding()) {
            try {
                status.updateDirtyTimestamp();
                this.connection.sendCell(cell);
            } catch (ConnectionIOException e) {
                destroyCircuit();
            }
        }
    }

    void sendDestroyCell(int i) {
        CellImpl createCell = CellImpl.createCell(this.circuitId, 4);
        createCell.putByte(i);
        try {
            this.connection.sendCell(createCell);
        } catch (ConnectionIOException e) {
            logger.warning("Connection IO error sending DESTROY cell: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendRelayCellTo(RelayCell relayCell, CircuitNode circuitNode) {
        this.relaySendLock.lock();
        try {
            logRelayCell("Sending:     ", relayCell);
            relayCell.setLength();
            circuitNode.updateForwardDigest(relayCell);
            relayCell.setDigest(circuitNode.getForwardDigestBytes());
            for (CircuitNode circuitNode2 = circuitNode; circuitNode2 != null; circuitNode2 = circuitNode2.getPreviousNode()) {
                circuitNode2.encryptForwardCell(relayCell);
            }
            if (relayCell.getRelayCommand() == 2) {
                circuitNode.waitForSendWindowAndDecrement();
            }
            sendCell(relayCell);
        } finally {
            this.relaySendLock.unlock();
        }
    }
}
