package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/HMAC.class */
class HMAC {
    HMAC() {
    }

    public static byte[] compute(String str, byte[] bArr, byte[] bArr2, Provider provider) throws JOSEException {
        return compute(new SecretKeySpec(bArr, str), bArr2, provider);
    }

    public static byte[] compute(SecretKey secretKey, byte[] bArr, Provider provider) throws JOSEException {
        Mac initMac = getInitMac(secretKey, provider);
        initMac.update(bArr);
        return initMac.doFinal();
    }

    public static Mac getInitMac(SecretKey secretKey, Provider provider) throws JOSEException {
        try {
            Mac mac = provider != null ? Mac.getInstance(secretKey.getAlgorithm(), provider) : Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return mac;
        } catch (InvalidKeyException e) {
            throw new JOSEException("Invalid HMAC key: " + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new JOSEException("Unsupported HMAC algorithm: " + e2.getMessage(), e2);
        }
    }
}
