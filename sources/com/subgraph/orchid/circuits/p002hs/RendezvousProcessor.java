package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.HiddenServiceCircuit;
import com.subgraph.orchid.InternalCircuit;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.circuits.CircuitNodeImpl;
import com.subgraph.orchid.crypto.TorRandom;
import com.subgraph.orchid.crypto.TorTapKeyAgreement;
import com.subgraph.orchid.data.HexDigest;
import java.math.BigInteger;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/RendezvousProcessor.class */
public class RendezvousProcessor {
    private static final int RENDEZVOUS_COOKIE_LEN = 20;
    private static final Logger logger = Logger.getLogger(RendezvousProcessor.class.getName());
    private static final TorRandom random = new TorRandom();
    private final InternalCircuit circuit;
    private final byte[] cookie = random.getBytes(20);

    /* JADX INFO: Access modifiers changed from: protected */
    public RendezvousProcessor(InternalCircuit internalCircuit) {
        this.circuit = internalCircuit;
    }

    private BigInteger readPeerPublic(Cell cell) {
        byte[] bArr = new byte[128];
        cell.getByteArray(bArr);
        BigInteger bigInteger = new BigInteger(1, bArr);
        BigInteger bigInteger2 = bigInteger;
        if (!TorTapKeyAgreement.isValidPublicValue(bigInteger)) {
            logger.warning("Illegal DH public value received: " + bigInteger);
            bigInteger2 = null;
        }
        return bigInteger2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean establishRendezvous() {
        InternalCircuit internalCircuit = this.circuit;
        RelayCell createRelayCell = internalCircuit.createRelayCell(33, 0, internalCircuit.getFinalCircuitNode());
        createRelayCell.putByteArray(this.cookie);
        this.circuit.sendRelayCell(createRelayCell);
        RelayCell receiveRelayCell = this.circuit.receiveRelayCell();
        if (receiveRelayCell == null) {
            logger.info("Timeout waiting for Rendezvous establish response");
            return false;
        }
        if (receiveRelayCell.getRelayCommand() == 39) {
            return true;
        }
        logger.info("Response received from Rendezvous establish was not expected acknowledgement, Relay Command: " + receiveRelayCell.getRelayCommand());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getCookie() {
        return this.cookie;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Router getRendezvousRouter() {
        return this.circuit.getFinalCircuitNode().getRouter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HiddenServiceCircuit processRendezvous2(TorTapKeyAgreement torTapKeyAgreement) {
        RelayCell receiveRelayCell = this.circuit.receiveRelayCell();
        if (receiveRelayCell == null) {
            logger.info("Timeout waiting for RENDEZVOUS2");
            return null;
        }
        if (receiveRelayCell.getRelayCommand() != 37) {
            logger.info("Unexpected Relay cell type received while waiting for RENDEZVOUS2: " + receiveRelayCell.getRelayCommand());
            return null;
        }
        BigInteger readPeerPublic = readPeerPublic(receiveRelayCell);
        HexDigest readHandshakeDigest = readHandshakeDigest(receiveRelayCell);
        if (readPeerPublic == null || readHandshakeDigest == null) {
            return null;
        }
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[72];
        if (torTapKeyAgreement.deriveKeysFromDHPublicAndHash(readPeerPublic, readHandshakeDigest.getRawBytes(), bArr2, bArr)) {
            InternalCircuit internalCircuit = this.circuit;
            return internalCircuit.connectHiddenService(CircuitNodeImpl.createAnonymous(internalCircuit.getFinalCircuitNode(), bArr2, bArr));
        }
        logger.info("Error deriving session keys while extending to hidden service");
        return null;
    }

    HexDigest readHandshakeDigest(Cell cell) {
        byte[] bArr = new byte[20];
        cell.getByteArray(bArr);
        return HexDigest.createFromDigestBytes(bArr);
    }
}
