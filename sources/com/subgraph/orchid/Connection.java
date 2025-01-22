package com.subgraph.orchid;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Connection.class */
public interface Connection {
    int bindCircuit(Circuit circuit);

    Router getRouter();

    boolean isClosed();

    void removeCircuit(Circuit circuit);

    void sendCell(Cell cell) throws ConnectionIOException;
}
