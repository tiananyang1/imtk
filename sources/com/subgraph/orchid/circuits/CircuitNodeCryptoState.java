package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.crypto.TorMessageDigest;
import com.subgraph.orchid.crypto.TorStreamCipher;
import com.subgraph.orchid.data.HexDigest;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitNodeCryptoState.class */
public class CircuitNodeCryptoState {
    public static final int KEY_MATERIAL_SIZE = 72;
    private final TorStreamCipher backwardCipher;
    private final TorMessageDigest backwardDigest;
    private final HexDigest checksumDigest;
    private final TorStreamCipher forwardCipher;
    private final TorMessageDigest forwardDigest = new TorMessageDigest();

    private CircuitNodeCryptoState(byte[] bArr, byte[] bArr2) {
        this.checksumDigest = HexDigest.createFromDigestBytes(bArr2);
        this.forwardDigest.update(extractDigestBytes(bArr, 0));
        this.backwardDigest = new TorMessageDigest();
        this.backwardDigest.update(extractDigestBytes(bArr, 20));
        this.forwardCipher = TorStreamCipher.createFromKeyBytes(extractCipherKey(bArr, 40));
        this.backwardCipher = TorStreamCipher.createFromKeyBytes(extractCipherKey(bArr, 56));
    }

    public static CircuitNodeCryptoState createFromKeyMaterial(byte[] bArr, byte[] bArr2) {
        return new CircuitNodeCryptoState(bArr, bArr2);
    }

    private static byte[] extractCipherKey(byte[] bArr, int i) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, i, bArr2, 0, 16);
        return bArr2;
    }

    private static byte[] extractDigestBytes(byte[] bArr, int i) {
        byte[] bArr2 = new byte[20];
        System.arraycopy(bArr, i, bArr2, 0, 20);
        return bArr2;
    }

    private byte[] extractRelayDigest(Cell cell) {
        byte[] bArr = new byte[4];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 4) {
                return bArr;
            }
            int i3 = i2 + 8;
            bArr[i2] = (byte) cell.getByteAt(i3);
            cell.putByteAt(i3, 0);
            i = i2 + 1;
        }
    }

    private boolean isRecognizedCell(Cell cell) {
        if (cell.getShortAt(4) != 0) {
            return false;
        }
        byte[] extractRelayDigest = extractRelayDigest(cell);
        byte[] peekDigest = this.backwardDigest.peekDigest(cell.getCellBytes(), 3, Cell.CELL_PAYLOAD_LEN);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 4) {
                this.backwardDigest.update(cell.getCellBytes(), 3, Cell.CELL_PAYLOAD_LEN);
                replaceRelayDigest(cell, extractRelayDigest);
                return true;
            }
            if (extractRelayDigest[i2] != peekDigest[i2]) {
                replaceRelayDigest(cell, extractRelayDigest);
                return false;
            }
            i = i2 + 1;
        }
    }

    private void replaceRelayDigest(Cell cell, byte[] bArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 4) {
                return;
            }
            cell.putByteAt(i2 + 8, bArr[i2] & 255);
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean decryptBackwardCell(Cell cell) {
        this.backwardCipher.encrypt(cell.getCellBytes(), 3, Cell.CELL_PAYLOAD_LEN);
        return isRecognizedCell(cell);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void encryptForwardCell(Cell cell) {
        this.forwardCipher.encrypt(cell.getCellBytes(), 3, Cell.CELL_PAYLOAD_LEN);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getForwardDigestBytes() {
        return this.forwardDigest.getDigestBytes();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateForwardDigest(Cell cell) {
        this.forwardDigest.update(cell.getCellBytes(), 3, Cell.CELL_PAYLOAD_LEN);
    }

    boolean verifyPacketDigest(HexDigest hexDigest) {
        return this.checksumDigest.equals(hexDigest);
    }
}
