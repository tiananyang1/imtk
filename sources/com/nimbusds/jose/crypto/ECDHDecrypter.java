package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.ECDH;
import com.nimbusds.jose.crypto.utils.ECChecks;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Set;
import javax.crypto.SecretKey;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ECDHDecrypter.class */
public class ECDHDecrypter extends ECDHCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;
    private final ECPrivateKey privateKey;

    public ECDHDecrypter(ECKey eCKey) throws JOSEException {
        super(eCKey.getCurve());
        this.critPolicy = new CriticalHeaderParamsDeferral();
        if (!eCKey.isPrivate()) {
            throw new JOSEException("The EC JWK doesn't contain a private part");
        }
        this.privateKey = eCKey.toECPrivateKey();
    }

    public ECDHDecrypter(ECPrivateKey eCPrivateKey) throws JOSEException {
        this(eCPrivateKey, null);
    }

    public ECDHDecrypter(ECPrivateKey eCPrivateKey, Set<String> set) throws JOSEException {
        super(Curve.forECParameterSpec(eCPrivateKey.getParams()));
        this.critPolicy = new CriticalHeaderParamsDeferral();
        this.critPolicy.setDeferredCriticalHeaderParams(set);
        this.privateKey = eCPrivateKey;
    }

    @Override // com.nimbusds.jose.JWEDecrypter
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws JOSEException {
        ECDH.AlgorithmMode resolveAlgorithmMode = ECDH.resolveAlgorithmMode(jWEHeader.getAlgorithm());
        this.critPolicy.ensureHeaderPasses(jWEHeader);
        ECKey ephemeralPublicKey = jWEHeader.getEphemeralPublicKey();
        if (ephemeralPublicKey == null) {
            throw new JOSEException("Missing ephemeral public EC key \"epk\" JWE header parameter");
        }
        ECPublicKey eCPublicKey = ephemeralPublicKey.toECPublicKey();
        if (!ECChecks.isPointOnCurve(eCPublicKey, getPrivateKey())) {
            throw new JOSEException("Invalid ephemeral public EC key: Point(s) not on the expected curve");
        }
        SecretKey deriveSharedSecret = ECDH.deriveSharedSecret(eCPublicKey, this.privateKey, getJCAContext().getKeyEncryptionProvider());
        getConcatKDF().getJCAContext().setProvider(getJCAContext().getMACProvider());
        SecretKey deriveSharedKey = ECDH.deriveSharedKey(jWEHeader, deriveSharedSecret, getConcatKDF());
        if (!resolveAlgorithmMode.equals(ECDH.AlgorithmMode.DIRECT)) {
            if (!resolveAlgorithmMode.equals(ECDH.AlgorithmMode.KW)) {
                throw new JOSEException("Unexpected JWE ECDH algorithm mode: " + resolveAlgorithmMode);
            }
            if (base64URL == null) {
                throw new JOSEException("Missing JWE encrypted key");
            }
            deriveSharedKey = AESKW.unwrapCEK(deriveSharedKey, base64URL.decode(), getJCAContext().getKeyEncryptionProvider());
        }
        return ContentCryptoProvider.decrypt(jWEHeader, base64URL, base64URL2, base64URL3, base64URL4, deriveSharedKey, getJCAContext());
    }

    @Override // com.nimbusds.jose.crypto.ECDHCryptoProvider
    public /* bridge */ /* synthetic */ Curve getCurve() {
        return super.getCurve();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public ECPrivateKey getPrivateKey() {
        return this.privateKey;
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.ECDHCryptoProvider
    public /* bridge */ /* synthetic */ Set supportedEllipticCurves() {
        return super.supportedEllipticCurves();
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
