package com.subgraph.orchid.circuits;

import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.crypto.TorTapKeyAgreement;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/TapCircuitExtender.class */
public class TapCircuitExtender {
    private static final Logger logger = Logger.getLogger(TapCircuitExtender.class.getName());
    private final CircuitExtender extender;
    private final TorTapKeyAgreement kex;
    private final Router router;

    public TapCircuitExtender(CircuitExtender circuitExtender, Router router) {
        this.extender = circuitExtender;
        this.router = router;
        this.kex = new TorTapKeyAgreement(router.getOnionKey());
    }

    private RelayCell createRelayExtendCell() {
        RelayCell createRelayCell = this.extender.createRelayCell(6);
        createRelayCell.putByteArray(this.router.getAddress().getAddressDataBytes());
        createRelayCell.putShort(this.router.getOnionPort());
        createRelayCell.putByteArray(this.kex.createOnionSkin());
        createRelayCell.putByteArray(this.router.getIdentityHash().getRawBytes());
        return createRelayCell;
    }

    private CircuitNode processExtendResponse(RelayCell relayCell) {
        byte[] bArr = new byte[148];
        relayCell.getByteArray(bArr);
        byte[] bArr2 = new byte[72];
        byte[] bArr3 = new byte[20];
        if (this.kex.deriveKeysFromHandshakeResponse(bArr, bArr2, bArr3)) {
            return this.extender.createNewNode(this.router, bArr2, bArr3);
        }
        return null;
    }

    public CircuitNode extendTo() {
        logger.fine("Extending to " + this.router.getNickname() + " with TAP");
        this.extender.sendRelayCell(createRelayExtendCell());
        RelayCell receiveRelayResponse = this.extender.receiveRelayResponse(7, this.router);
        if (receiveRelayResponse == null) {
            return null;
        }
        return processExtendResponse(receiveRelayResponse);
    }
}
