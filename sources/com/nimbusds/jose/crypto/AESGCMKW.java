package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.Container;
import java.security.Provider;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AESGCMKW.class */
class AESGCMKW {
    private AESGCMKW() {
    }

    public static SecretKey decryptCEK(SecretKey secretKey, byte[] bArr, AuthenticatedCipherText authenticatedCipherText, int i, Provider provider) throws JOSEException {
        byte[] decrypt = AESGCM.decrypt(secretKey, bArr, authenticatedCipherText.getCipherText(), new byte[0], authenticatedCipherText.getAuthenticationTag(), provider);
        if (ByteUtils.safeBitLength(decrypt) == i) {
            return new SecretKeySpec(decrypt, "AES");
        }
        throw new KeyLengthException("CEK key length mismatch: " + ByteUtils.safeBitLength(decrypt) + " != " + i);
    }

    public static AuthenticatedCipherText encryptCEK(SecretKey secretKey, Container<byte[]> container, SecretKey secretKey2, Provider provider) throws JOSEException {
        return AESGCM.encrypt(secretKey2, container, secretKey.getEncoded(), new byte[0], provider);
    }
}
