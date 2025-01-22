package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/DirectDecrypter.class */
public class DirectDecrypter extends DirectCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;

    public DirectDecrypter(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toSecretKey("AES"));
    }

    public DirectDecrypter(SecretKey secretKey) throws KeyLengthException {
        super(secretKey);
        this.critPolicy = new CriticalHeaderParamsDeferral();
    }

    public DirectDecrypter(SecretKey secretKey, Set<String> set) throws KeyLengthException {
        super(secretKey);
        this.critPolicy = new CriticalHeaderParamsDeferral();
        this.critPolicy.setDeferredCriticalHeaderParams(set);
    }

    public DirectDecrypter(byte[] bArr) throws KeyLengthException {
        this(new SecretKeySpec(bArr, "AES"));
    }

    @Override // com.nimbusds.jose.JWEDecrypter
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws JOSEException {
        if (base64URL != null) {
            throw new JOSEException("Unexpected present JWE encrypted key");
        }
        if (base64URL2 == null) {
            throw new JOSEException("Unexpected present JWE initialization vector (IV)");
        }
        if (base64URL4 == null) {
            throw new JOSEException("Missing JWE authentication tag");
        }
        JWEAlgorithm algorithm = jWEHeader.getAlgorithm();
        if (!algorithm.equals(JWEAlgorithm.DIR)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithm, SUPPORTED_ALGORITHMS));
        }
        this.critPolicy.ensureHeaderPasses(jWEHeader);
        return ContentCryptoProvider.decrypt(jWEHeader, null, base64URL2, base64URL3, base64URL4, getKey(), getJCAContext());
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    @Override // com.nimbusds.jose.crypto.DirectCryptoProvider
    public /* bridge */ /* synthetic */ SecretKey getKey() {
        return super.getKey();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.JWEProvider
    public /* bridge */ /* synthetic */ Set supportedEncryptionMethods() {
        return super.supportedEncryptionMethods();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.JWEProvider
    public /* bridge */ /* synthetic */ Set supportedJWEAlgorithms() {
        return super.supportedJWEAlgorithms();
    }
}
