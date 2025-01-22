package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/RSASSA.class */
class RSASSA {
    private RSASSA() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Signature getSignerAndVerifier(JWSAlgorithm jWSAlgorithm, Provider provider) throws JOSEException {
        String str;
        PSSParameterSpec pSSParameterSpec = null;
        if (jWSAlgorithm.equals(JWSAlgorithm.RS256)) {
            str = "SHA256withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.RS384)) {
            str = "SHA384withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.RS512)) {
            str = "SHA512withRSA";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.PS256)) {
            pSSParameterSpec = new PSSParameterSpec("SHA256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1);
            str = "SHA256withRSAandMGF1";
        } else if (jWSAlgorithm.equals(JWSAlgorithm.PS384)) {
            pSSParameterSpec = new PSSParameterSpec("SHA384", "MGF1", MGF1ParameterSpec.SHA384, 48, 1);
            str = "SHA384withRSAandMGF1";
        } else {
            if (!jWSAlgorithm.equals(JWSAlgorithm.PS512)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, RSASSAProvider.SUPPORTED_ALGORITHMS));
            }
            pSSParameterSpec = new PSSParameterSpec("SHA512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1);
            str = "SHA512withRSAandMGF1";
        }
        try {
            Signature signature = provider != null ? Signature.getInstance(str, provider) : Signature.getInstance(str);
            if (pSSParameterSpec == null) {
                return signature;
            }
            try {
                signature.setParameter(pSSParameterSpec);
                return signature;
            } catch (InvalidAlgorithmParameterException e) {
                throw new JOSEException("Invalid RSASSA-PSS salt length parameter: " + e.getMessage(), e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new JOSEException("Unsupported RSASSA algorithm: " + e2.getMessage(), e2);
        }
    }
}
