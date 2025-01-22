package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.crypto.HybridEncryption;
import com.subgraph.orchid.crypto.TorPublicKey;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/IntroductionProcessor.class */
public class IntroductionProcessor {
    private static final int INTRODUCTION_PROTOCOL_VERSION = 3;
    private static final Logger logger = Logger.getLogger(IntroductionProcessor.class.getName());
    private final HiddenService hiddenService;
    private final Circuit introductionCircuit;
    private final IntroductionPoint introductionPoint;

    /* JADX INFO: Access modifiers changed from: protected */
    public IntroductionProcessor(HiddenService hiddenService, Circuit circuit, IntroductionPoint introductionPoint) {
        this.hiddenService = hiddenService;
        this.introductionCircuit = circuit;
        this.introductionPoint = introductionPoint;
    }

    private void addAuthentication(ByteBuffer byteBuffer) {
        HSDescriptorCookie authenticationCookie = this.hiddenService.getAuthenticationCookie();
        if (authenticationCookie == null) {
            byteBuffer.put((byte) 0);
            return;
        }
        byteBuffer.put(authenticationCookie.getAuthTypeByte());
        byteBuffer.putShort((short) authenticationCookie.getValue().length);
        byteBuffer.put(authenticationCookie.getValue());
    }

    private ByteBuffer createIntroductionBuffer(int i, Router router, byte[] bArr, byte[] bArr2) {
        ByteBuffer allocate = ByteBuffer.allocate(Cell.CELL_LEN);
        byte[] addressDataBytes = router.getAddress().getAddressDataBytes();
        short onionPort = (short) router.getOnionPort();
        byte[] rawBytes = router.getIdentityHash().getRawBytes();
        byte[] rawBytes2 = router.getOnionKey().getRawBytes();
        allocate.put((byte) 3);
        addAuthentication(allocate);
        allocate.putInt(i);
        allocate.put(addressDataBytes);
        allocate.putShort(onionPort);
        allocate.put(rawBytes);
        allocate.putShort((short) rawBytes2.length);
        allocate.put(rawBytes2);
        allocate.put(bArr);
        allocate.put(bArr2);
        return allocate;
    }

    private byte[] createIntroductionPayload(Router router, byte[] bArr, byte[] bArr2, TorPublicKey torPublicKey) {
        return encryptIntroductionBuffer(createIntroductionBuffer((int) (System.currentTimeMillis() / 1000), router, bArr2, bArr), torPublicKey);
    }

    private byte[] encryptIntroductionBuffer(ByteBuffer byteBuffer, TorPublicKey torPublicKey) {
        byte[] bArr = new byte[byteBuffer.position()];
        byteBuffer.flip();
        byteBuffer.get(bArr);
        return new HybridEncryption().encrypt(bArr, torPublicKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TorPublicKey getServiceKey() {
        return this.introductionPoint.getServiceKey();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void markCircuitForClose() {
        this.introductionCircuit.markForClose();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean sendIntroduce(TorPublicKey torPublicKey, byte[] bArr, byte[] bArr2, Router router) {
        Circuit circuit = this.introductionCircuit;
        RelayCell createRelayCell = circuit.createRelayCell(34, 0, circuit.getFinalCircuitNode());
        byte[] createIntroductionPayload = createIntroductionPayload(router, bArr, bArr2, torPublicKey);
        createRelayCell.putByteArray(this.introductionPoint.getServiceKey().getFingerprint().getRawBytes());
        createRelayCell.putByteArray(createIntroductionPayload);
        this.introductionCircuit.sendRelayCell(createRelayCell);
        RelayCell receiveRelayCell = this.introductionCircuit.receiveRelayCell();
        if (receiveRelayCell == null) {
            logger.fine("Timeout waiting for response to INTRODUCE1 cell");
            return false;
        }
        if (receiveRelayCell.getRelayCommand() != 40) {
            logger.info("Unexpected relay cell type received waiting for response to INTRODUCE1 cell: " + receiveRelayCell.getRelayCommand());
            return false;
        }
        if (receiveRelayCell.cellBytesRemaining() == 0) {
            return true;
        }
        logger.info("INTRODUCE_ACK indicates that introduction was not forwarded: " + receiveRelayCell.getByte());
        return false;
    }
}
