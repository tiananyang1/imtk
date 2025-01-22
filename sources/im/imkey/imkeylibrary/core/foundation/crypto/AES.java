package im.imkey.imkeylibrary.core.foundation.crypto;

import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.exception.ImkeyException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/AES.class */
public class AES {

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/AES$AESType.class */
    public enum AESType {
        CTR,
        CBC
    }

    public static byte[] decryptByCBC(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return doAES(bArr, bArr2, bArr3, 2, AESType.CBC, "PKCS5Padding");
    }

    private static byte[] doAES(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, AESType aESType, String str) {
        String str2 = aESType == AESType.CBC ? "CBC" : "CTR";
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance(String.format("AES/%s/%s", str2, str));
            cipher.init(i, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            throw new ImkeyException(Messages.IMKEY_AES_EXCEPTION);
        }
    }

    public static byte[] encryptByCBC(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return doAES(bArr, bArr2, bArr3, 1, AESType.CBC, "PKCS5Padding");
    }
}
