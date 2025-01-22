package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.jca.JCAContext;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/RSASSASigner.class */
public class RSASSASigner extends RSASSAProvider implements JWSSigner {
    private final PrivateKey privateKey;

    public RSASSASigner(RSAKey rSAKey) throws JOSEException {
        if (!rSAKey.isPrivate()) {
            throw new JOSEException("The RSA JWK doesn't contain a private part");
        }
        this.privateKey = rSAKey.toPrivateKey();
    }

    public RSASSASigner(PrivateKey privateKey) {
        if (!"RSA".equalsIgnoreCase(privateKey.getAlgorithm())) {
            throw new IllegalArgumentException("The private key algorithm must be RSA");
        }
        this.privateKey = privateKey;
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
        Signature signerAndVerifier = RSASSA.getSignerAndVerifier(jWSHeader.getAlgorithm(), getJCAContext().getProvider());
        try {
            signerAndVerifier.initSign(this.privateKey);
            signerAndVerifier.update(bArr);
            return Base64URL.m3704encode(signerAndVerifier.sign());
        } catch (InvalidKeyException e) {
            throw new JOSEException("Invalid private RSA key: " + e.getMessage(), e);
        } catch (SignatureException e2) {
            throw new JOSEException("RSA signature exception: " + e2.getMessage(), e2);
        }
    }

    @Override // com.nimbusds.jose.crypto.BaseJWSProvider, com.nimbusds.jose.JWSProvider
    public /* bridge */ /* synthetic */ Set supportedJWSAlgorithms() {
        return super.supportedJWSAlgorithms();
    }
}
