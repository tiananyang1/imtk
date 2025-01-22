package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ECDSASigner.class */
public class ECDSASigner extends ECDSAProvider implements JWSSigner {
    private final PrivateKey privateKey;

    public ECDSASigner(ECKey eCKey) throws JOSEException {
        super(ECDSA.resolveAlgorithm(eCKey.getCurve()));
        if (!eCKey.isPrivate()) {
            throw new JOSEException("The EC JWK doesn't contain a private part");
        }
        this.privateKey = eCKey.toPrivateKey();
    }

    public ECDSASigner(PrivateKey privateKey, Curve curve) throws JOSEException {
        super(ECDSA.resolveAlgorithm(curve));
        if (!"EC".equalsIgnoreCase(privateKey.getAlgorithm())) {
            throw new IllegalArgumentException("The private key algorithm must be EC");
        }
        this.privateKey = privateKey;
    }

    public ECDSASigner(ECPrivateKey eCPrivateKey) throws JOSEException {
        super(ECDSA.resolveAlgorithm(eCPrivateKey));
        this.privateKey = eCPrivateKey;
    }

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    @Override // com.nimbusds.jose.JWSSigner
    public Base64URL sign(JWSHeader jWSHeader, byte[] bArr) throws JOSEException {
        JWSAlgorithm algorithm = jWSHeader.getAlgorithm();
        if (!supportedJWSAlgorithms().contains(algorithm)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(algorithm, supportedJWSAlgorithms()));
        }
        try {
            Signature signerAndVerifier = ECDSA.getSignerAndVerifier(algorithm, getJCAContext().getProvider());
            signerAndVerifier.initSign(this.privateKey, getJCAContext().getSecureRandom());
            signerAndVerifier.update(bArr);
            return Base64URL.m3704encode(ECDSA.transcodeSignatureToConcat(signerAndVerifier.sign(), ECDSA.getSignatureByteArrayLength(jWSHeader.getAlgorithm())));
        } catch (InvalidKeyException | SignatureException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    @Override // com.nimbusds.jose.crypto.ECDSAProvider
    public /* bridge */ /* synthetic */ JWSAlgorithm supportedECDSAAlgorithm() {
        return super.supportedECDSAAlgorithm();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.JWSProvider
    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }
}
