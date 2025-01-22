package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.Connection;
import com.subgraph.orchid.DirectoryCircuit;
import com.subgraph.orchid.ExitCircuit;
import com.subgraph.orchid.InternalCircuit;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.path.CircuitPathChooser;
import com.subgraph.orchid.circuits.path.PathSelectionFailedException;
import com.subgraph.orchid.dashboard.DashboardRenderable;
import com.subgraph.orchid.dashboard.DashboardRenderer;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitImpl.class */
public abstract class CircuitImpl implements Circuit, DashboardRenderable {
    protected static final Logger logger = Logger.getLogger(CircuitImpl.class.getName());
    private final CircuitManagerImpl circuitManager;

    /* renamed from: io */
    private CircuitIO f185io;
    private final List<CircuitNode> nodeList;
    protected final List<Router> prechosenPath;
    private final CircuitStatus status;

    /* JADX INFO: Access modifiers changed from: protected */
    public CircuitImpl(CircuitManagerImpl circuitManagerImpl) {
        this(circuitManagerImpl, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CircuitImpl(CircuitManagerImpl circuitManagerImpl, List<Router> list) {
        this.nodeList = new ArrayList();
        this.circuitManager = circuitManagerImpl;
        this.prechosenPath = list;
        this.status = new CircuitStatus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DirectoryCircuit createDirectoryCircuit(CircuitManagerImpl circuitManagerImpl) {
        return new DirectoryCircuitImpl(circuitManagerImpl, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DirectoryCircuit createDirectoryCircuitTo(CircuitManagerImpl circuitManagerImpl, List<Router> list) {
        return new DirectoryCircuitImpl(circuitManagerImpl, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExitCircuit createExitCircuit(CircuitManagerImpl circuitManagerImpl, Router router) {
        return new ExitCircuitImpl(circuitManagerImpl, router);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExitCircuit createExitCircuitTo(CircuitManagerImpl circuitManagerImpl, List<Router> list) {
        return new ExitCircuitImpl(circuitManagerImpl, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InternalCircuit createInternalCircuitTo(CircuitManagerImpl circuitManagerImpl, List<Router> list) {
        return new InternalCircuitImpl(circuitManagerImpl, list);
    }

    @Override // com.subgraph.orchid.Circuit
    public void appendNode(CircuitNode circuitNode) {
        this.nodeList.add(circuitNode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bindToConnection(Connection connection) {
        if (this.f185io != null) {
            throw new IllegalStateException("Circuit already bound to a connection");
        }
        this.f185io = new CircuitIO(this, connection, connection.bindCircuit(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Router> choosePath(CircuitPathChooser circuitPathChooser) throws InterruptedException, PathSelectionFailedException {
        List<Router> list = this.prechosenPath;
        return list != null ? new ArrayList(list) : choosePathForCircuit(circuitPathChooser);
    }

    protected abstract List<Router> choosePathForCircuit(CircuitPathChooser circuitPathChooser) throws InterruptedException, PathSelectionFailedException;

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamImpl createNewStream() {
        return createNewStream(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamImpl createNewStream(boolean z) {
        return this.f185io.createNewStream(z);
    }

    @Override // com.subgraph.orchid.Circuit
    public RelayCell createRelayCell(int i, int i2, CircuitNode circuitNode) {
        return this.f185io.createRelayCell(i, i2, circuitNode);
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderable
    public void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) throws IOException {
        if (this.f185io != null) {
            printWriter.println(toString());
            dashboardRenderer.renderComponent(printWriter, i, this.f185io);
        }
    }

    @Override // com.subgraph.orchid.Circuit
    public void deliverControlCell(Cell cell) {
        this.f185io.deliverControlCell(cell);
    }

    @Override // com.subgraph.orchid.Circuit
    public void deliverRelayCell(Cell cell) {
        this.f185io.deliverRelayCell(cell);
    }

    @Override // com.subgraph.orchid.Circuit
    public void destroyCircuit() {
        CircuitIO circuitIO = this.f185io;
        if (circuitIO != null) {
            circuitIO.destroyCircuit();
        }
        this.circuitManager.removeActiveCircuit(this);
    }

    @Override // com.subgraph.orchid.Circuit
    public List<Stream> getActiveStreams() {
        CircuitIO circuitIO = this.f185io;
        return circuitIO == null ? Collections.emptyList() : circuitIO.getActiveStreams();
    }

    @Override // com.subgraph.orchid.Circuit
    public int getCircuitId() {
        CircuitIO circuitIO = this.f185io;
        if (circuitIO == null) {
            return 0;
        }
        return circuitIO.getCircuitId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCircuitLength() {
        return this.nodeList.size();
    }

    protected abstract String getCircuitTypeLabel();

    @Override // com.subgraph.orchid.Circuit
    public Connection getConnection() {
        if (isConnected()) {
            return this.f185io.getConnection();
        }
        throw new TorException("Circuit is not connected.");
    }

    @Override // com.subgraph.orchid.Circuit
    public CircuitNode getFinalCircuitNode() {
        if (this.nodeList.isEmpty()) {
            throw new TorException("getFinalCircuitNode() called on empty circuit");
        }
        return this.nodeList.get(getCircuitLength() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<CircuitNode> getNodeList() {
        return this.nodeList;
    }

    @Override // com.subgraph.orchid.Circuit
    public int getSecondsDirty() {
        return (int) (this.status.getMillisecondsDirty() / 1000);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitStatus getStatus() {
        return this.status;
    }

    @Override // com.subgraph.orchid.Circuit
    public boolean isClean() {
        return !this.status.isDirty();
    }

    @Override // com.subgraph.orchid.Circuit
    public boolean isConnected() {
        return this.status.isConnected();
    }

    @Override // com.subgraph.orchid.Circuit
    public boolean isMarkedForClose() {
        CircuitIO circuitIO = this.f185io;
        if (circuitIO == null) {
            return false;
        }
        return circuitIO.isMarkedForClose();
    }

    @Override // com.subgraph.orchid.Circuit
    public boolean isPending() {
        return this.status.isBuilding();
    }

    @Override // com.subgraph.orchid.Circuit
    public void markForClose() {
        CircuitIO circuitIO = this.f185io;
        if (circuitIO != null) {
            circuitIO.markForClose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyCircuitBuildCompleted() {
        this.status.setStateOpen();
        this.status.updateCreatedTimestamp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyCircuitBuildFailed() {
        this.status.setStateFailed();
        this.circuitManager.removeActiveCircuit(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyCircuitBuildStart() {
        if (!this.status.isUnconnected()) {
            throw new IllegalStateException("Can only connect UNCONNECTED circuits");
        }
        this.status.updateCreatedTimestamp();
        this.status.setStateBuilding();
        this.circuitManager.addActiveCircuit(this);
    }

    protected String pathToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (CircuitNode circuitNode : this.nodeList) {
            if (sb.length() > 1) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            sb.append(circuitNode.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Stream processStreamOpenException(Exception exc) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        if (exc instanceof InterruptedException) {
            throw ((InterruptedException) exc);
        }
        if (exc instanceof TimeoutException) {
            throw ((TimeoutException) exc);
        }
        if (exc instanceof StreamConnectFailedException) {
            throw ((StreamConnectFailedException) exc);
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cell receiveControlCellResponse() {
        return this.f185io.receiveControlCellResponse();
    }

    @Override // com.subgraph.orchid.Circuit
    public RelayCell receiveRelayCell() {
        return this.f185io.dequeueRelayResponseCell();
    }

    public void removeStream(StreamImpl streamImpl) {
        this.f185io.removeStream(streamImpl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendCell(Cell cell) {
        this.f185io.sendCell(cell);
    }

    @Override // com.subgraph.orchid.Circuit
    public void sendRelayCell(RelayCell relayCell) {
        this.f185io.sendRelayCellTo(relayCell, relayCell.getCircuitNode());
    }

    public void sendRelayCellToFinalNode(RelayCell relayCell) {
        this.f185io.sendRelayCellTo(relayCell, getFinalCircuitNode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStateDestroyed() {
        this.status.setStateDestroyed();
        this.circuitManager.removeActiveCircuit(this);
    }

    public String toString() {
        return "  Circuit (" + getCircuitTypeLabel() + ") id=" + getCircuitId() + " state=" + this.status.getStateAsString() + " " + pathToString();
    }
}
