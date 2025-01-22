package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.ECDH;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/ECDHEncrypter.class */
public class ECDHEncrypter extends ECDHCryptoProvider implements JWEEncrypter {
    private final ECPublicKey publicKey;

    public ECDHEncrypter(ECKey eCKey) throws JOSEException {
        super(eCKey.getCurve());
        this.publicKey = eCKey.toECPublicKey();
    }

    public ECDHEncrypter(ECPublicKey eCPublicKey) throws JOSEException {
        super(Curve.forECParameterSpec(eCPublicKey.getParams()));
        this.publicKey = eCPublicKey;
    }

    private KeyPair generateEphemeralKeyPair(ECParameterSpec eCParameterSpec) throws JOSEException {
        Provider keyEncryptionProvider = getJCAContext().getKeyEncryptionProvider();
        try {
            KeyPairGenerator keyPairGenerator = keyEncryptionProvider != null ? KeyPairGenerator.getInstance("EC", keyEncryptionProvider) : KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(eCParameterSpec);
            return keyPairGenerator.generateKeyPair();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            throw new JOSEException("Couldn't generate ephemeral EC key pair: " + e.getMessage(), e);
        }
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        Base64URL m3704encode;
        ECDH.AlgorithmMode resolveAlgorithmMode = ECDH.resolveAlgorithmMode(jWEHeader.getAlgorithm());
        EncryptionMethod encryptionMethod = jWEHeader.getEncryptionMethod();
        KeyPair generateEphemeralKeyPair = generateEphemeralKeyPair(this.publicKey.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) generateEphemeralKeyPair.getPublic();
        SecretKey deriveSharedSecret = ECDH.deriveSharedSecret(this.publicKey, (ECPrivateKey) generateEphemeralKeyPair.getPrivate(), getJCAContext().getKeyEncryptionProvider());
        getConcatKDF().getJCAContext().setProvider(getJCAContext().getMACProvider());
        SecretKey deriveSharedKey = ECDH.deriveSharedKey(jWEHeader, deriveSharedSecret, getConcatKDF());
        if (resolveAlgorithmMode.equals(ECDH.AlgorithmMode.DIRECT)) {
            m3704encode = null;
        } else {
            if (!resolveAlgorithmMode.equals(ECDH.AlgorithmMode.KW)) {
                throw new JOSEException("Unexpected JWE ECDH algorithm mode: " + resolveAlgorithmMode);
            }
            SecretKey generateCEK = ContentCryptoProvider.generateCEK(encryptionMethod, getJCAContext().getSecureRandom());
            m3704encode = Base64URL.m3704encode(AESKW.wrapCEK(generateCEK, deriveSharedKey, getJCAContext().getKeyEncryptionProvider()));
            deriveSharedKey = generateCEK;
        }
        return ContentCryptoProvider.encrypt(new JWEHeader.Builder(jWEHeader).ephemeralPublicKey(new ECKey.Builder(getCurve(), eCPublicKey).build()).build(), bArr, deriveSharedKey, m3704encode, getJCAContext());
    }

    @Override // com.nimbusds.jose.crypto.ECDHCryptoProvider
    public /* bridge */ /* synthetic */ Curve getCurve() {
        return super.getCurve();
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    public ECPublicKey getPublicKey() {
        return this.publicKey;
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
