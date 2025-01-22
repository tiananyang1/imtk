package com.subgraph.orchid.circuits;

import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.crypto.TorNTorKeyAgreement;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/NTorCircuitExtender.class */
public class NTorCircuitExtender {
    private static final Logger logger = Logger.getLogger(NTorCircuitExtender.class.getName());
    private final CircuitExtender extender;
    private final TorNTorKeyAgreement kex;
    private final Router router;

    public NTorCircuitExtender(CircuitExtender circuitExtender, Router router) {
        this.extender = circuitExtender;
        this.router = router;
        this.kex = new TorNTorKeyAgreement(router.getIdentityHash(), router.getNTorOnionKey());
    }

    private RelayCell createExtend2Cell(byte[] bArr) {
        RelayCell createRelayCell = this.extender.createRelayCell(14);
        createRelayCell.putByte(2);
        createRelayCell.putByte(0);
        createRelayCell.putByte(6);
        createRelayCell.putByteArray(this.router.getAddress().getAddressDataBytes());
        createRelayCell.putShort(this.router.getOnionPort());
        createRelayCell.putByte(2);
        createRelayCell.putByte(20);
        createRelayCell.putByteArray(this.router.getIdentityHash().getRawBytes());
        createRelayCell.putShort(2);
        createRelayCell.putShort(bArr.length);
        createRelayCell.putByteArray(bArr);
        return createRelayCell;
    }

    private RelayCell createExtendCell(byte[] bArr, byte[] bArr2) {
        RelayCell createRelayCell = this.extender.createRelayCell(6);
        createRelayCell.putByteArray(this.router.getAddress().getAddressDataBytes());
        createRelayCell.putShort(this.router.getOnionPort());
        byte[] bArr3 = new byte[186 - (bArr.length + bArr2.length)];
        createRelayCell.putByteArray(bArr2);
        createRelayCell.putByteArray(bArr);
        createRelayCell.putByteArray(bArr3);
        createRelayCell.putByteArray(this.router.getIdentityHash().getRawBytes());
        return createRelayCell;
    }

    private CircuitNode extendWithExtend2(byte[] bArr) {
        this.extender.sendRelayCell(createExtend2Cell(bArr));
        return processExtended2(this.extender.receiveRelayResponse(15, this.router));
    }

    private CircuitNode extendWithTunneledExtend(byte[] bArr) {
        this.extender.sendRelayCell(createExtendCell(bArr, this.kex.getNtorCreateMagic()));
        return processExtended(this.extender.receiveRelayResponse(7, this.router));
    }

    private boolean finalRouterSupportsExtend2() {
        return this.extender.getFinalRouter().getNTorOnionKey() != null;
    }

    private CircuitNode processExtended(RelayCell relayCell) {
        byte[] bArr = new byte[148];
        relayCell.getByteArray(bArr);
        return processPayload(bArr);
    }

    private CircuitNode processExtended2(RelayCell relayCell) {
        int i = relayCell.getShort();
        if (i > relayCell.cellBytesRemaining()) {
            throw new TorException("Incorrect payload length value in RELAY_EXTENED2 cell");
        }
        byte[] bArr = new byte[i];
        relayCell.getByteArray(bArr);
        return processPayload(bArr);
    }

    private CircuitNode processPayload(byte[] bArr) {
        byte[] bArr2 = new byte[72];
        byte[] bArr3 = new byte[20];
        if (this.kex.deriveKeysFromHandshakeResponse(bArr, bArr2, bArr3)) {
            return this.extender.createNewNode(this.router, bArr2, bArr3);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitNode extendTo() {
        byte[] createOnionSkin = this.kex.createOnionSkin();
        if (finalRouterSupportsExtend2()) {
            logger.fine("Extending circuit to " + this.router.getNickname() + " with NTor inside RELAY_EXTEND2");
            return extendWithExtend2(createOnionSkin);
        }
        logger.fine("Extending circuit to " + this.router.getNickname() + " with NTor inside RELAY_EXTEND");
        return extendWithTunneledExtend(createOnionSkin);
    }
}
