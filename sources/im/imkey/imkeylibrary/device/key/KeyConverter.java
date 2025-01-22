package im.imkey.imkeylibrary.device.key;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.ECPoint;
import java.security.spec.InvalidKeySpecException;
import org.spongycastle.jce.ECNamedCurveTable;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECPrivateKeySpec;
import org.spongycastle.jce.spec.ECPublicKeySpec;
import org.spongycastle.math.ec.custom.sec.SecP256K1Curve;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/key/KeyConverter.class */
public class KeyConverter {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static ECPoint convertECPoint(org.spongycastle.math.ec.ECPoint eCPoint) {
        return new ECPoint(eCPoint.getXCoord().toBigInteger(), eCPoint.getYCoord().toBigInteger());
    }

    public static PrivateKey getPrivKey(byte[] bArr) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("EC", "SC").generatePrivate(new ECPrivateKeySpec(new BigInteger(1, bArr), ECNamedCurveTable.getParameterSpec("secp256k1")));
    }

    public static PublicKey getPubKey(byte[] bArr) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bArr2 = new byte[32];
        byte[] bArr3 = new byte[32];
        System.arraycopy(bArr, 1, bArr2, 0, 32);
        System.arraycopy(bArr, 33, bArr3, 0, 32);
        BigInteger bigInteger = new BigInteger(1, bArr2);
        BigInteger bigInteger2 = new BigInteger(1, bArr3);
        return KeyFactory.getInstance("EC", "SC").generatePublic(new ECPublicKeySpec(new SecP256K1Curve().createPoint(bigInteger, bigInteger2), ECNamedCurveTable.getParameterSpec("secp256k1")));
    }
}
