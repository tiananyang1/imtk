package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/RSAEncrypter.class */
public class RSAEncrypter extends RSACryptoProvider implements JWEEncrypter {
    private final RSAPublicKey publicKey;

    public RSAEncrypter(RSAKey rSAKey) throws JOSEException {
        this(rSAKey.toRSAPublicKey());
    }

    public RSAEncrypter(RSAPublicKey rSAPublicKey) {
        if (rSAPublicKey == null) {
            throw new IllegalArgumentException("The public RSA key must not be null");
        }
        this.publicKey = rSAPublicKey;
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        Base64URL m3704encode;
        JWEAlgorithm algorithm = jWEHeader.getAlgorithm();
        SecretKey generateCEK = ContentCryptoProvider.generateCEK(jWEHeader.getEncryptionMethod(), getJCAContext().getSecureRandom());
        if (algorithm.equals(JWEAlgorithm.RSA1_5)) {
            m3704encode = Base64URL.m3704encode(RSA1_5.encryptCEK(this.publicKey, generateCEK, getJCAContext().getKeyEncryptionProvider()));
        } else if (algorithm.equals(JWEAlgorithm.RSA_OAEP)) {
            m3704encode = Base64URL.m3704encode(RSA_OAEP.encryptCEK(this.publicKey, generateCEK, getJCAContext().getKeyEncryptionProvider()));
        } else {
            if (!algorithm.equals(JWEAlgorithm.RSA_OAEP_256)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithm, SUPPORTED_ALGORITHMS));
            }
            m3704encode = Base64URL.m3704encode(RSA_OAEP_256.encryptCEK(this.publicKey, generateCEK, getJCAContext().getKeyEncryptionProvider()));
        }
        return ContentCryptoProvider.encrypt(jWEHeader, bArr, generateCEK, m3704encode, getJCAContext());
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public RSAPublicKey getPublicKey() {
        return this.publicKey;
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
