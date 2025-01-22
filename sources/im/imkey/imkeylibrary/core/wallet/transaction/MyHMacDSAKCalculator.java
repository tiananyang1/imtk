package im.imkey.imkeylibrary.core.wallet.transaction;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.DSAKCalculator;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/MyHMacDSAKCalculator.class */
public class MyHMacDSAKCalculator implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: K */
    private final byte[] f2745K;

    /* renamed from: V */
    private final byte[] f2746V;
    private final HMac hMac;

    /* renamed from: n */
    private BigInteger f2747n;
    private boolean needTry;

    public MyHMacDSAKCalculator(Digest digest) {
        this.hMac = new HMac(digest);
        this.f2746V = new byte[this.hMac.getMacSize()];
        this.f2745K = new byte[this.hMac.getMacSize()];
    }

    private BigInteger bitsToInt(byte[] bArr) {
        BigInteger bigInteger = new BigInteger(1, bArr);
        BigInteger bigInteger2 = bigInteger;
        if (bArr.length * 8 > this.f2747n.bitLength()) {
            bigInteger2 = bigInteger.shiftRight((bArr.length * 8) - this.f2747n.bitLength());
        }
        return bigInteger2;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f2747n = bigInteger;
        this.needTry = false;
        Arrays.fill(this.f2746V, (byte) 1);
        Arrays.fill(this.f2745K, (byte) 0);
        byte[] bArr2 = new byte[(bigInteger.bitLength() + 7) / 8];
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger2);
        System.arraycopy(asUnsignedByteArray, 0, bArr2, bArr2.length - asUnsignedByteArray.length, asUnsignedByteArray.length);
        byte[] bArr3 = new byte[(bigInteger.bitLength() + 7) / 8];
        BigInteger bitsToInt = bitsToInt(bArr);
        BigInteger bigInteger3 = bitsToInt;
        if (bitsToInt.compareTo(bigInteger) > 0) {
            bigInteger3 = bitsToInt.subtract(bigInteger);
        }
        byte[] asUnsignedByteArray2 = BigIntegers.asUnsignedByteArray(bigInteger3);
        System.arraycopy(asUnsignedByteArray2, 0, bArr3, bArr3.length - asUnsignedByteArray2.length, asUnsignedByteArray2.length);
        this.hMac.init(new KeyParameter(this.f2745K));
        HMac hMac = this.hMac;
        byte[] bArr4 = this.f2746V;
        hMac.update(bArr4, 0, bArr4.length);
        this.hMac.update((byte) 0);
        this.hMac.update(bArr2, 0, bArr2.length);
        this.hMac.update(bArr3, 0, bArr3.length);
        this.hMac.doFinal(this.f2745K, 0);
        this.hMac.init(new KeyParameter(this.f2745K));
        HMac hMac2 = this.hMac;
        byte[] bArr5 = this.f2746V;
        hMac2.update(bArr5, 0, bArr5.length);
        this.hMac.doFinal(this.f2746V, 0);
        HMac hMac3 = this.hMac;
        byte[] bArr6 = this.f2746V;
        hMac3.update(bArr6, 0, bArr6.length);
        this.hMac.update((byte) 1);
        this.hMac.update(bArr2, 0, bArr2.length);
        this.hMac.update(bArr3, 0, bArr3.length);
        this.hMac.doFinal(this.f2745K, 0);
        this.hMac.init(new KeyParameter(this.f2745K));
        HMac hMac4 = this.hMac;
        byte[] bArr7 = this.f2746V;
        hMac4.update(bArr7, 0, bArr7.length);
        this.hMac.doFinal(this.f2746V, 0);
    }

    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        throw new IllegalStateException("Operation not supported");
    }

    public boolean isDeterministic() {
        return true;
    }

    public BigInteger nextK() {
        byte[] bArr = new byte[(this.f2747n.bitLength() + 7) / 8];
        if (this.needTry) {
            this.hMac.init(new KeyParameter(this.f2745K));
            HMac hMac = this.hMac;
            byte[] bArr2 = this.f2746V;
            hMac.update(bArr2, 0, bArr2.length);
            this.hMac.update((byte) 0);
            this.hMac.doFinal(this.f2745K, 0);
            this.hMac.init(new KeyParameter(this.f2745K));
            HMac hMac2 = this.hMac;
            byte[] bArr3 = this.f2746V;
            hMac2.update(bArr3, 0, bArr3.length);
            this.hMac.doFinal(this.f2746V, 0);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bArr.length) {
                BigInteger bitsToInt = bitsToInt(bArr);
                this.needTry = true;
                return bitsToInt;
            }
            this.hMac.init(new KeyParameter(this.f2745K));
            HMac hMac3 = this.hMac;
            byte[] bArr4 = this.f2746V;
            hMac3.update(bArr4, 0, bArr4.length);
            this.hMac.doFinal(this.f2746V, 0);
            int min = Math.min(bArr.length - i2, this.f2746V.length);
            System.arraycopy(this.f2746V, 0, bArr, i2, min);
            i = i2 + min;
        }
    }
}
