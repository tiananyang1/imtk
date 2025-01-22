package com.subgraph.orchid.crypto;

import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorCreateFastKeyAgreement.class */
public class TorCreateFastKeyAgreement implements TorKeyAgreement {
    private final byte[] xValue = new TorRandom().getBytes(20);
    private byte[] yValue;

    @Override // com.subgraph.orchid.crypto.TorKeyAgreement
    public byte[] createOnionSkin() {
        return getPublicValue();
    }

    @Override // com.subgraph.orchid.crypto.TorKeyAgreement
    public boolean deriveKeysFromHandshakeResponse(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        byte[] bArr4 = new byte[20];
        byte[] bArr5 = new byte[20];
        wrap.get(bArr4);
        wrap.get(bArr5);
        setOtherValue(bArr4);
        new TorKeyDerivation(getDerivedValue()).deriveKeys(bArr2, bArr3);
        return Arrays.equals(bArr3, bArr5);
    }

    public byte[] getDerivedValue() {
        if (this.yValue == null) {
            throw new IllegalStateException("Must call setOtherValue() first");
        }
        byte[] bArr = new byte[40];
        System.arraycopy(this.xValue, 0, bArr, 0, 20);
        System.arraycopy(this.yValue, 0, bArr, 20, 20);
        return bArr;
    }

    public byte[] getPublicValue() {
        byte[] bArr = this.xValue;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public void setOtherValue(byte[] bArr) {
        if (bArr == null || bArr.length != 20) {
            throw new IllegalArgumentException();
        }
        this.yValue = Arrays.copyOf(bArr, bArr.length);
    }
}
