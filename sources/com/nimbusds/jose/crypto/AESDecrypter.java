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
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AESDecrypter.class */
public class AESDecrypter extends AESCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;

    public AESDecrypter(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toSecretKey("AES"));
    }

    public AESDecrypter(SecretKey secretKey) throws KeyLengthException {
        this(secretKey, null);
    }

    public AESDecrypter(SecretKey secretKey, Set<String> set) throws KeyLengthException {
        super(secretKey);
        this.critPolicy = new CriticalHeaderParamsDeferral();
        this.critPolicy.setDeferredCriticalHeaderParams(set);
    }

    public AESDecrypter(byte[] bArr) throws KeyLengthException {
        this(new SecretKeySpec(bArr, "AES"));
    }

    @Override // com.nimbusds.jose.JWEDecrypter
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws JOSEException {
        SecretKey unwrapCEK;
        if (base64URL == null) {
            throw new JOSEException("Missing JWE encrypted key");
        }
        if (base64URL2 == null) {
            throw new JOSEException("Missing JWE initialization vector (IV)");
        }
        if (base64URL4 == null) {
            throw new JOSEException("Missing JWE authentication tag");
        }
        this.critPolicy.ensureHeaderPasses(jWEHeader);
        JWEAlgorithm algorithm = jWEHeader.getAlgorithm();
        int cekBitLength = jWEHeader.getEncryptionMethod().cekBitLength();
        if (algorithm.equals(JWEAlgorithm.A128KW) || algorithm.equals(JWEAlgorithm.A192KW) || algorithm.equals(JWEAlgorithm.A256KW)) {
            unwrapCEK = AESKW.unwrapCEK(getKey(), base64URL.decode(), getJCAContext().getKeyEncryptionProvider());
        } else {
            if (!algorithm.equals(JWEAlgorithm.A128GCMKW) && !algorithm.equals(JWEAlgorithm.A192GCMKW) && !algorithm.equals(JWEAlgorithm.A256GCMKW)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithm, SUPPORTED_ALGORITHMS));
            }
            if (jWEHeader.getIV() == null) {
                throw new JOSEException("Missing JWE \"iv\" header parameter");
            }
            byte[] decode = jWEHeader.getIV().decode();
            if (jWEHeader.getAuthTag() == null) {
                throw new JOSEException("Missing JWE \"tag\" header parameter");
            }
            unwrapCEK = AESGCMKW.decryptCEK(getKey(), decode, new AuthenticatedCipherText(base64URL.decode(), jWEHeader.getAuthTag().decode()), cekBitLength, getJCAContext().getKeyEncryptionProvider());
        }
        return ContentCryptoProvider.decrypt(jWEHeader, base64URL, base64URL2, base64URL3, base64URL4, unwrapCEK, getJCAContext());
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    @Override // com.nimbusds.jose.crypto.AESCryptoProvider
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
