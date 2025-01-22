package im.imkey.imkeylibrary.core.foundation.crypto;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/RsaCrypto.class */
public class RsaCrypto {
    public static byte[] encryptByPublicKeyWithPadding(byte[] bArr, byte[] bArr2) throws InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException, NoSuchAlgorithmException, NoSuchPaddingException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bArr2);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", SecurityProvider.getProvierName());
        PublicKey generatePublic = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm() + "/None/PKCS1Padding", SecurityProvider.getProvierName());
        cipher.init(1, generatePublic);
        return cipher.doFinal(bArr);
    }
}
