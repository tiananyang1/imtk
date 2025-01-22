package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/RSA_OAEP.class */
class RSA_OAEP {
    private RSA_OAEP() {
    }

    public static SecretKey decryptCEK(PrivateKey privateKey, byte[] bArr, Provider provider) throws JOSEException {
        try {
            Cipher cipherHelper = CipherHelper.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding", provider);
            cipherHelper.init(2, privateKey);
            return new SecretKeySpec(cipherHelper.doFinal(bArr), "AES");
        } catch (Exception e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    public static byte[] encryptCEK(RSAPublicKey rSAPublicKey, SecretKey secretKey, Provider provider) throws JOSEException {
        try {
            Cipher cipherHelper = CipherHelper.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", provider);
            cipherHelper.init(1, rSAPublicKey, new SecureRandom());
            return cipherHelper.doFinal(secretKey.getEncoded());
        } catch (IllegalBlockSizeException e) {
            throw new JOSEException("RSA block size exception: The RSA key is too short, try a longer one", e);
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }
}
