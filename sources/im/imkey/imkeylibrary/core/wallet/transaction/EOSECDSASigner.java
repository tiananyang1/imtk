package im.imkey.imkeylibrary.core.wallet.transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bitcoinj.core.ECKey;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.DSAKCalculator;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointCombMultiplier;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/wallet/transaction/EOSECDSASigner.class */
public class EOSECDSASigner {
    private final DSAKCalculator kCalculator;
    private ECKeyParameters key;
    private SecureRandom random;

    public EOSECDSASigner(DSAKCalculator dSAKCalculator) {
        this.kCalculator = dSAKCalculator;
    }

    protected BigInteger calculateE(BigInteger bigInteger, byte[] bArr) {
        int bitLength = bigInteger.bitLength();
        int length = bArr.length * 8;
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        BigInteger bigInteger3 = bigInteger2;
        if (bitLength < length) {
            bigInteger3 = bigInteger2.shiftRight(length - bitLength);
        }
        return bigInteger3;
    }

    protected ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    public BigInteger[] generateSignature(byte[] bArr) {
        BigInteger nextK;
        BigInteger mod;
        BigInteger mod2;
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n = parameters.getN();
        BigInteger calculateE = calculateE(n, bArr);
        BigInteger d = this.key.getD();
        int i = 1;
        while (true) {
            int i2 = i;
            this.kCalculator.init(n, d, bArr);
            ECMultiplier createBasePointMultiplier = createBasePointMultiplier();
            do {
                BigInteger bigInteger = BigInteger.ZERO;
                do {
                    nextK = this.kCalculator.nextK();
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        if (i4 >= i2) {
                            break;
                        }
                        nextK = this.kCalculator.nextK();
                        i3 = i4 + 1;
                    }
                    mod = createBasePointMultiplier.multiply(parameters.getG(), nextK).normalize().getAffineXCoord().toBigInteger().mod(n);
                } while (mod.equals(BigDecimal.ZERO));
                mod2 = nextK.modInverse(n).multiply(calculateE.add(d.multiply(mod))).mod(n);
            } while (mod2.equals(BigDecimal.ZERO));
            byte[] encodeToDER = new ECKey.ECDSASignature(mod, mod2).toCanonicalised().encodeToDER();
            byte b = encodeToDER[3];
            byte b2 = encodeToDER[b + 5];
            if (b == 32 && b2 == 32) {
                return new BigInteger[]{mod, mod2};
            }
            i = i2 + 1;
        }
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (!z) {
            this.key = (ECPublicKeyParameters) cipherParameters;
        } else {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.key = parametersWithRandom.getParameters();
                secureRandom = parametersWithRandom.getRandom();
                this.random = initSecureRandom((z || this.kCalculator.isDeterministic()) ? false : true, secureRandom);
            }
            this.key = (ECPrivateKeyParameters) cipherParameters;
        }
        secureRandom = null;
        this.random = initSecureRandom((z || this.kCalculator.isDeterministic()) ? false : true, secureRandom);
    }

    protected SecureRandom initSecureRandom(boolean z, SecureRandom secureRandom) {
        if (z) {
            return secureRandom != null ? secureRandom : new SecureRandom();
        }
        return null;
    }

    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n = parameters.getN();
        BigInteger calculateE = calculateE(n, bArr);
        if (bigInteger.compareTo(BigInteger.ONE) < 0 || bigInteger.compareTo(n) >= 0 || bigInteger2.compareTo(BigInteger.ONE) < 0 || bigInteger2.compareTo(n) >= 0) {
            return false;
        }
        BigInteger modInverse = bigInteger2.modInverse(n);
        ECPoint normalize = ECAlgorithms.sumOfTwoMultiplies(parameters.getG(), calculateE.multiply(modInverse).mod(n), this.key.getQ(), bigInteger.multiply(modInverse).mod(n)).normalize();
        if (normalize.isInfinity()) {
            return false;
        }
        return normalize.getAffineXCoord().toBigInteger().mod(n).equals(bigInteger);
    }
}
