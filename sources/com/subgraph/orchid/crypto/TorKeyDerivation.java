package com.subgraph.orchid.crypto;

import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorKeyDerivation.class */
public class TorKeyDerivation {
    private final byte[] kdfBuffer;
    private int round;

    public TorKeyDerivation(byte[] bArr) {
        this.kdfBuffer = new byte[bArr.length + 1];
        System.arraycopy(bArr, 0, this.kdfBuffer, 0, bArr.length);
    }

    private byte[] calculateRoundData() {
        TorMessageDigest torMessageDigest = new TorMessageDigest();
        byte[] bArr = this.kdfBuffer;
        int length = bArr.length;
        int i = this.round;
        bArr[length - 1] = (byte) i;
        this.round = i + 1;
        torMessageDigest.update(bArr);
        return torMessageDigest.getDigestBytes();
    }

    public ByteBuffer deriveKeys(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        this.round = 0;
        while (allocate.hasRemaining()) {
            byte[] calculateRoundData = calculateRoundData();
            allocate.put(calculateRoundData, 0, Math.min(allocate.remaining(), calculateRoundData.length));
        }
        allocate.flip();
        return allocate;
    }

    public void deriveKeys(byte[] bArr, byte[] bArr2) {
        ByteBuffer deriveKeys = deriveKeys(bArr.length + bArr2.length);
        deriveKeys.get(bArr2);
        deriveKeys.get(bArr);
    }
}
