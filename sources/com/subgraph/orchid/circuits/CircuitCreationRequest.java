package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.CircuitBuildHandler;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.Connection;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.circuits.path.CircuitPathChooser;
import com.subgraph.orchid.circuits.path.PathSelectionFailedException;
import java.util.Collections;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitCreationRequest.class */
public class CircuitCreationRequest implements CircuitBuildHandler {
    private final CircuitBuildHandler buildHandler;
    private final CircuitImpl circuit;
    private final boolean isDirectoryCircuit;
    private List<Router> path = Collections.emptyList();
    private final CircuitPathChooser pathChooser;

    public CircuitCreationRequest(CircuitPathChooser circuitPathChooser, Circuit circuit, CircuitBuildHandler circuitBuildHandler, boolean z) {
        this.pathChooser = circuitPathChooser;
        this.circuit = (CircuitImpl) circuit;
        this.buildHandler = circuitBuildHandler;
        this.isDirectoryCircuit = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void choosePath() throws InterruptedException, PathSelectionFailedException {
        CircuitImpl circuitImpl = this.circuit;
        if (!(circuitImpl instanceof CircuitImpl)) {
            throw new IllegalArgumentException();
        }
        this.path = circuitImpl.choosePath(this.pathChooser);
    }

    @Override // com.subgraph.orchid.CircuitBuildHandler
    public void circuitBuildCompleted(Circuit circuit) {
        CircuitBuildHandler circuitBuildHandler = this.buildHandler;
        if (circuitBuildHandler != null) {
            circuitBuildHandler.circuitBuildCompleted(circuit);
        }
    }

    @Override // com.subgraph.orchid.CircuitBuildHandler
    public void circuitBuildFailed(String str) {
        CircuitBuildHandler circuitBuildHandler = this.buildHandler;
        if (circuitBuildHandler != null) {
            circuitBuildHandler.circuitBuildFailed(str);
        }
    }

    @Override // com.subgraph.orchid.CircuitBuildHandler
    public void connectionCompleted(Connection connection) {
        CircuitBuildHandler circuitBuildHandler = this.buildHandler;
        if (circuitBuildHandler != null) {
            circuitBuildHandler.connectionCompleted(connection);
        }
    }

    @Override // com.subgraph.orchid.CircuitBuildHandler
    public void connectionFailed(String str) {
        CircuitBuildHandler circuitBuildHandler = this.buildHandler;
        if (circuitBuildHandler != null) {
            circuitBuildHandler.connectionFailed(str);
        }
    }

    CircuitBuildHandler getBuildHandler() {
        return this.buildHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitImpl getCircuit() {
        return this.circuit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Router> getPath() {
        return this.path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Router getPathElement(int i) {
        return this.path.get(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPathLength() {
        return this.path.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDirectoryCircuit() {
        return this.isDirectoryCircuit;
    }

    @Override // com.subgraph.orchid.CircuitBuildHandler
    public void nodeAdded(CircuitNode circuitNode) {
        CircuitBuildHandler circuitBuildHandler = this.buildHandler;
        if (circuitBuildHandler != null) {
            circuitBuildHandler.nodeAdded(circuitNode);
        }
    }
}
