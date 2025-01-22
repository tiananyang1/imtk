package com.subgraph.orchid;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Circuit.class */
public interface Circuit {
    void appendNode(CircuitNode circuitNode);

    RelayCell createRelayCell(int i, int i2, CircuitNode circuitNode);

    void deliverControlCell(Cell cell);

    void deliverRelayCell(Cell cell);

    void destroyCircuit();

    List<Stream> getActiveStreams();

    int getCircuitId();

    Connection getConnection();

    CircuitNode getFinalCircuitNode();

    int getSecondsDirty();

    boolean isClean();

    boolean isConnected();

    boolean isMarkedForClose();

    boolean isPending();

    void markForClose();

    RelayCell receiveRelayCell();

    void sendRelayCell(RelayCell relayCell);
}
