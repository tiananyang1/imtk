package im.imkey.imkeylibrary.core.foundation.crypto;

import im.imkey.imkeylibrary.common.Constants;
import java.math.BigInteger;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/EccUtil.class */
public class EccUtil {
    public static DeterministicKey deriveChildKeyFromPublic(DeterministicKey deterministicKey, String str) {
        String[] split = str.replace('/', ' ').split(" ");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= split.length) {
                return deterministicKey;
            }
            deterministicKey = HDKeyDerivation.deriveChildKey(deterministicKey, new ChildNumber(Integer.parseInt(split[i2]), false));
            i = i2 + 1;
        }
    }

    public static ECKey getCompressECKey(byte[] bArr) {
        byte[] bArr2 = new byte[33];
        System.arraycopy(bArr, 33, bArr2, 1, 32);
        if (bArr2[32] % 2 != 0) {
            bArr2[0] = 3;
        } else {
            bArr2[0] = 2;
        }
        System.arraycopy(bArr, 1, bArr2, 1, 32);
        return ECKey.fromPublicOnly(bArr2);
    }

    public static ECKey getECKeyFromPublicOnly(byte[] bArr) {
        return bArr.length == 33 ? ECKey.fromPublicOnly(bArr) : getCompressECKey(bArr);
    }

    public static BigInteger getLowS(BigInteger bigInteger) {
        BigInteger bigInteger2 = bigInteger;
        if (bigInteger.compareTo(Constants.HALF_CURVE_ORDER) > 0) {
            bigInteger2 = Constants.CURVE_N.subtract(bigInteger);
        }
        return bigInteger2;
    }
}
