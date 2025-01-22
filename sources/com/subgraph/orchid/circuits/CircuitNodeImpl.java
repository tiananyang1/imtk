package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitNodeImpl.class */
public class CircuitNodeImpl implements CircuitNode {
    private static final int CIRCWINDOW_INCREMENT = 100;
    private static final int CIRCWINDOW_START = 1000;
    private final CircuitNodeCryptoState cryptoState;
    private final CircuitNode previousNode;
    private final Router router;
    private final Object windowLock = new Object();
    private int packageWindow = 1000;
    private int deliverWindow = 1000;

    private CircuitNodeImpl(Router router, CircuitNode circuitNode, CircuitNodeCryptoState circuitNodeCryptoState) {
        this.previousNode = circuitNode;
        this.router = router;
        this.cryptoState = circuitNodeCryptoState;
    }

    public static CircuitNode createAnonymous(CircuitNode circuitNode, byte[] bArr, byte[] bArr2) {
        return createNode(null, circuitNode, bArr, bArr2);
    }

    public static CircuitNode createFirstHop(Router router, byte[] bArr, byte[] bArr2) {
        return createNode(router, null, bArr, bArr2);
    }

    public static CircuitNode createNode(Router router, CircuitNode circuitNode, byte[] bArr, byte[] bArr2) {
        return new CircuitNodeImpl(router, circuitNode, CircuitNodeCryptoState.createFromKeyMaterial(bArr, bArr2));
    }

    private void waitForSendWindow(boolean z) {
        synchronized (this.windowLock) {
            while (this.packageWindow == 0) {
                try {
                    this.windowLock.wait();
                } catch (InterruptedException e) {
                    throw new TorException("Thread interrupted while waiting for circuit send window");
                }
            }
            if (z) {
                this.packageWindow--;
            }
        }
    }

    @Override // com.subgraph.orchid.CircuitNode
    public boolean considerSendingSendme() {
        synchronized (this.windowLock) {
            if (this.deliverWindow > 900) {
                return false;
            }
            this.deliverWindow += 100;
            return true;
        }
    }

    @Override // com.subgraph.orchid.CircuitNode
    public void decrementDeliverWindow() {
        synchronized (this.windowLock) {
            this.deliverWindow--;
        }
    }

    @Override // com.subgraph.orchid.CircuitNode
    public boolean decryptBackwardCell(Cell cell) {
        return this.cryptoState.decryptBackwardCell(cell);
    }

    @Override // com.subgraph.orchid.CircuitNode
    public void encryptForwardCell(RelayCell relayCell) {
        this.cryptoState.encryptForwardCell(relayCell);
    }

    @Override // com.subgraph.orchid.CircuitNode
    public byte[] getForwardDigestBytes() {
        return this.cryptoState.getForwardDigestBytes();
    }

    @Override // com.subgraph.orchid.CircuitNode
    public CircuitNode getPreviousNode() {
        return this.previousNode;
    }

    @Override // com.subgraph.orchid.CircuitNode
    public Router getRouter() {
        return this.router;
    }

    @Override // com.subgraph.orchid.CircuitNode
    public void incrementSendWindow() {
        synchronized (this.windowLock) {
            this.packageWindow += 100;
            this.windowLock.notifyAll();
        }
    }

    public String toString() {
        if (this.router == null) {
            return "|()|";
        }
        return "|" + this.router.getNickname() + "|";
    }

    @Override // com.subgraph.orchid.CircuitNode
    public void updateForwardDigest(RelayCell relayCell) {
        this.cryptoState.updateForwardDigest(relayCell);
    }

    @Override // com.subgraph.orchid.CircuitNode
    public void waitForSendWindow() {
        waitForSendWindow(false);
    }

    @Override // com.subgraph.orchid.CircuitNode
    public void waitForSendWindowAndDecrement() {
        waitForSendWindow(true);
    }
}
