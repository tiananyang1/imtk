package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.AlgorithmParameters;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/RSA_OAEP_256.class */
class RSA_OAEP_256 {
    private RSA_OAEP_256() {
    }

    public static SecretKey decryptCEK(PrivateKey privateKey, byte[] bArr, Provider provider) throws JOSEException {
        try {
            AlgorithmParameters algorithmParametersHelper = AlgorithmParametersHelper.getInstance("OAEP", provider);
            algorithmParametersHelper.init(new OAEPParameterSpec(CommonUtils.SHA256_INSTANCE, "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
            Cipher cipherHelper = CipherHelper.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", provider);
            cipherHelper.init(2, privateKey, algorithmParametersHelper);
            return new SecretKeySpec(cipherHelper.doFinal(bArr), "AES");
        } catch (Exception e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    public static byte[] encryptCEK(RSAPublicKey rSAPublicKey, SecretKey secretKey, Provider provider) throws JOSEException {
        try {
            AlgorithmParameters algorithmParametersHelper = AlgorithmParametersHelper.getInstance("OAEP", provider);
            algorithmParametersHelper.init(new OAEPParameterSpec(CommonUtils.SHA256_INSTANCE, "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
            Cipher cipherHelper = CipherHelper.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", provider);
            cipherHelper.init(1, rSAPublicKey, algorithmParametersHelper);
            return cipherHelper.doFinal(secretKey.getEncoded());
        } catch (IllegalBlockSizeException e) {
            throw new JOSEException("RSA block size exception: The RSA key is too short, try a longer one", e);
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }
}
