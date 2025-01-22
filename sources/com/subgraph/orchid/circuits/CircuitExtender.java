package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.cells.CellImpl;
import com.subgraph.orchid.circuits.cells.RelayCellImpl;
import com.subgraph.orchid.crypto.TorCreateFastKeyAgreement;
import com.subgraph.orchid.crypto.TorKeyAgreement;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitExtender.class */
public class CircuitExtender {
    private static final int CIPHER_KEY_LEN = 16;
    private static final int DH_BYTES = 128;
    private static final int PKCS1_OAEP_PADDING_OVERHEAD = 42;
    static final int TAP_ONIONSKIN_LEN = 186;
    static final int TAP_ONIONSKIN_REPLY_LEN = 148;
    private static final Logger logger = Logger.getLogger(CircuitExtender.class.getName());
    private final CircuitImpl circuit;
    private final boolean ntorEnabled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitExtender(CircuitImpl circuitImpl, boolean z) {
        this.circuit = circuitImpl;
        this.ntorEnabled = z;
    }

    private void logProtocolViolation(String str, Router router) {
        String version = router == null ? "(none)" : router.getVersion();
        String nickname = router == null ? "(none)" : router.getNickname();
        logger.warning("Protocol error extending circuit from (" + str + ") to (" + nickname + ") [version: " + version + "]");
    }

    private String nodeToName(CircuitNode circuitNode) {
        return (circuitNode == null || circuitNode.getRouter() == null) ? "(null)" : circuitNode.getRouter().getNickname();
    }

    private CircuitNode processCreatedFastCell(Router router, Cell cell, TorKeyAgreement torKeyAgreement) {
        byte[] bArr = new byte[40];
        byte[] bArr2 = new byte[72];
        byte[] bArr3 = new byte[20];
        cell.getByteArray(bArr);
        if (!torKeyAgreement.deriveKeysFromHandshakeResponse(bArr, bArr2, bArr3)) {
            return null;
        }
        CircuitNode createFirstHop = CircuitNodeImpl.createFirstHop(router, bArr2, bArr3);
        this.circuit.appendNode(createFirstHop);
        return createFirstHop;
    }

    private CircuitNode receiveAndProcessCreateFastResponse(Router router, TorKeyAgreement torKeyAgreement) {
        Cell receiveControlCellResponse = this.circuit.receiveControlCellResponse();
        if (receiveControlCellResponse != null) {
            return processCreatedFastCell(router, receiveControlCellResponse, torKeyAgreement);
        }
        throw new TorException("Timeout building circuit waiting for CREATE_FAST response from " + router);
    }

    private void sendCreateFastCell(TorCreateFastKeyAgreement torCreateFastKeyAgreement) {
        CellImpl createCell = CellImpl.createCell(this.circuit.getCircuitId(), 5);
        createCell.putByteArray(torCreateFastKeyAgreement.createOnionSkin());
        this.circuit.sendCell(createCell);
    }

    private boolean useNtor(Router router) {
        return this.ntorEnabled && router.getNTorOnionKey() != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitNode createFastTo(Router router) {
        logger.fine("Creating 'fast' to " + router);
        TorCreateFastKeyAgreement torCreateFastKeyAgreement = new TorCreateFastKeyAgreement();
        sendCreateFastCell(torCreateFastKeyAgreement);
        return receiveAndProcessCreateFastResponse(router, torCreateFastKeyAgreement);
    }

    public CircuitNode createNewNode(Router router, byte[] bArr, byte[] bArr2) {
        CircuitNode createNode = CircuitNodeImpl.createNode(router, this.circuit.getFinalCircuitNode(), bArr, bArr2);
        logger.fine("Adding new circuit node for " + router.getNickname());
        this.circuit.appendNode(createNode);
        return createNode;
    }

    public RelayCell createRelayCell(int i) {
        return new RelayCellImpl(this.circuit.getFinalCircuitNode(), this.circuit.getCircuitId(), 0, i, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitNode extendTo(Router router) {
        if (this.circuit.getCircuitLength() != 0) {
            return useNtor(router) ? new NTorCircuitExtender(this, router).extendTo() : new TapCircuitExtender(this, router).extendTo();
        }
        throw new TorException("Cannot EXTEND an empty circuit");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Router getFinalRouter() {
        CircuitNode finalCircuitNode = this.circuit.getFinalCircuitNode();
        if (finalCircuitNode != null) {
            return finalCircuitNode.getRouter();
        }
        return null;
    }

    public RelayCell receiveRelayResponse(int i, Router router) {
        RelayCell receiveRelayCell = this.circuit.receiveRelayCell();
        if (receiveRelayCell == null) {
            throw new TorException("Timeout building circuit");
        }
        int relayCommand = receiveRelayCell.getRelayCommand();
        if (relayCommand != 9) {
            if (relayCommand == i) {
                return receiveRelayCell;
            }
            throw new TorException("Received incorrect extend response, expecting " + RelayCellImpl.commandToDescription(i) + " but received " + RelayCellImpl.commandToDescription(relayCommand));
        }
        int i2 = receiveRelayCell.getByte() & 255;
        String errorToDescription = CellImpl.errorToDescription(i2);
        String nodeToName = nodeToName(receiveRelayCell.getCircuitNode());
        if (i2 == 1) {
            logProtocolViolation(nodeToName, router);
        }
        throw new TorException("Error from (" + nodeToName + ") while extending to (" + router.getNickname() + "): " + errorToDescription);
    }

    public void sendRelayCell(RelayCell relayCell) {
        this.circuit.sendRelayCell(relayCell);
    }
}
