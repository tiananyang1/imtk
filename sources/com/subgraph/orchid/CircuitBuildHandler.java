package com.subgraph.orchid;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/CircuitBuildHandler.class */
public interface CircuitBuildHandler {
    void circuitBuildCompleted(Circuit circuit);

    void circuitBuildFailed(String str);

    void connectionCompleted(Connection connection);

    void connectionFailed(String str);

    void nodeAdded(CircuitNode circuitNode);
}
