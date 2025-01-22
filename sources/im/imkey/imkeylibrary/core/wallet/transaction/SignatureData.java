package im.imkey.imkeylibrary.core.wallet.transaction;

import im.imkey.imkeylibrary.utils.NumericUtil;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/SignatureData.class */
public class SignatureData {

    /* renamed from: r */
    private final byte[] f2748r;

    /* renamed from: s */
    private final byte[] f2749s;

    /* renamed from: v */
    private final int f2750v;

    public SignatureData(int i, byte[] bArr, byte[] bArr2) {
        this.f2750v = i;
        this.f2748r = bArr;
        this.f2749s = bArr2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SignatureData signatureData = (SignatureData) obj;
        if (this.f2750v == signatureData.f2750v && Arrays.equals(this.f2748r, signatureData.f2748r)) {
            return Arrays.equals(this.f2749s, signatureData.f2749s);
        }
        return false;
    }

    public byte[] getR() {
        return this.f2748r;
    }

    public byte[] getS() {
        return this.f2749s;
    }

    public int getV() {
        return this.f2750v;
    }

    public int hashCode() {
        return (((this.f2750v * 31) + Arrays.hashCode(this.f2748r)) * 31) + Arrays.hashCode(this.f2749s);
    }

    public String toString() {
        return String.format("%s%s%02x", NumericUtil.bytesToHex(getR()), NumericUtil.bytesToHex(getS()), Integer.valueOf(getV()));
    }
}
