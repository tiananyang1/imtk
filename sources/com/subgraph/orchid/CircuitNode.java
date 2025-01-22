package com.subgraph.orchid;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/CircuitNode.class */
public interface CircuitNode {
    boolean considerSendingSendme();

    void decrementDeliverWindow();

    boolean decryptBackwardCell(Cell cell);

    void encryptForwardCell(RelayCell relayCell);

    byte[] getForwardDigestBytes();

    CircuitNode getPreviousNode();

    Router getRouter();

    void incrementSendWindow();

    void updateForwardDigest(RelayCell relayCell);

    void waitForSendWindow();

    void waitForSendWindowAndDecrement();
}
