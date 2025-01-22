package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AESKW.class */
class AESKW {
    private AESKW() {
    }

    public static SecretKey unwrapCEK(SecretKey secretKey, byte[] bArr, Provider provider) throws JOSEException {
        try {
            Cipher cipher = provider != null ? Cipher.getInstance("AESWrap", provider) : Cipher.getInstance("AESWrap");
            cipher.init(4, secretKey);
            return (SecretKey) cipher.unwrap(bArr, "AES", 3);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new JOSEException("Couldn't unwrap AES key: " + e.getMessage(), e);
        }
    }

    public static byte[] wrapCEK(SecretKey secretKey, SecretKey secretKey2, Provider provider) throws JOSEException {
        try {
            Cipher cipher = provider != null ? Cipher.getInstance("AESWrap", provider) : Cipher.getInstance("AESWrap");
            cipher.init(3, secretKey2);
            return cipher.wrap(secretKey);
        } catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new JOSEException("Couldn't wrap AES key: " + e.getMessage(), e);
        }
    }
}
