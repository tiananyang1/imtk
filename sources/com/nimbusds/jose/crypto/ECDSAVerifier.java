package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.utils.ECChecks;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPublicKey;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ECDSAVerifier.class */
public class ECDSAVerifier extends ECDSAProvider implements JWSVerifier, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;
    private final ECPublicKey publicKey;

    public ECDSAVerifier(ECKey eCKey) throws JOSEException {
        this(eCKey.toECPublicKey());
    }

    public ECDSAVerifier(ECPublicKey eCPublicKey) throws JOSEException {
        this(eCPublicKey, null);
    }

    public ECDSAVerifier(ECPublicKey eCPublicKey, Set<String> set) throws JOSEException {
        super(ECDSA.resolveAlgorithm(eCPublicKey));
        this.critPolicy = new CriticalHeaderParamsDeferral();
        this.publicKey = eCPublicKey;
        if (!ECChecks.isPointOnCurve(eCPublicKey, Curve.forJWSAlgorithm(supportedECDSAAlgorithm()).iterator().next().toECParameterSpec())) {
            throw new JOSEException("Curve / public key parameters mismatch");
        }
        this.critPolicy.setDeferredCriticalHeaderParams(set);
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    public ECPublicKey getPublicKey() {
        return this.publicKey;
    }

    @Override // com.nimbusds.jose.crypto.ECDSAProvider
    public /* bridge */ /* synthetic */ JWSAlgorithm supportedECDSAAlgorithm() {
        return super.supportedECDSAAlgorithm();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.JWSProvider
    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }

    @Override // com.nimbusds.jose.JWSVerifier
    public boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) throws JOSEException {
        JWSAlgorithm algorithm = jWSHeader.getAlgorithm();
        if (!supportedJWSAlgorithms().contains(algorithm)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(algorithm, supportedJWSAlgorithms()));
        }
        if (!this.critPolicy.headerPasses(jWSHeader)) {
            return false;
        }
        try {
            byte[] transcodeSignatureToDER = ECDSA.transcodeSignatureToDER(base64URL.decode());
            Signature signerAndVerifier = ECDSA.getSignerAndVerifier(algorithm, getJCAContext().getProvider());
            try {
                signerAndVerifier.initVerify(this.publicKey);
                signerAndVerifier.update(bArr);
                return signerAndVerifier.verify(transcodeSignatureToDER);
            } catch (InvalidKeyException e) {
                throw new JOSEException("Invalid EC public key: " + e.getMessage(), e);
            } catch (SignatureException e2) {
                return false;
            }
        } catch (JOSEException e3) {
            return false;
        }
    }
}
