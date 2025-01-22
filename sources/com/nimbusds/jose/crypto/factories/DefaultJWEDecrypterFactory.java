package com.nimbusds.jose.crypto.factories;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.KeyTypeException;
import com.nimbusds.jose.crypto.AESDecrypter;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.ECDHDecrypter;
import com.nimbusds.jose.crypto.PasswordBasedDecrypter;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.proc.JWEDecrypterFactory;
import java.security.Key;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/factories/DefaultJWEDecrypterFactory.class */
public class DefaultJWEDecrypterFactory implements JWEDecrypterFactory {
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS;
    private final JWEJCAContext jcaContext = new JWEJCAContext();

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(RSADecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(ECDHDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(DirectDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(AESDecrypter.SUPPORTED_ALGORITHMS);
        linkedHashSet.addAll(PasswordBasedDecrypter.SUPPORTED_ALGORITHMS);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        linkedHashSet2.addAll(RSADecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(ECDHDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(DirectDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(AESDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        linkedHashSet2.addAll(PasswordBasedDecrypter.SUPPORTED_ENCRYPTION_METHODS);
        SUPPORTED_ENCRYPTION_METHODS = Collections.unmodifiableSet(linkedHashSet2);
    }

    @Override // com.nimbusds.jose.proc.JWEDecrypterFactory
    public JWEDecrypter createJWEDecrypter(JWEHeader jWEHeader, Key key) throws JOSEException {
        JWEDecrypter passwordBasedDecrypter;
        if (RSADecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && RSADecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
            if (!(key instanceof RSAPrivateKey)) {
                throw new KeyTypeException(RSAPrivateKey.class);
            }
            passwordBasedDecrypter = new RSADecrypter((RSAPrivateKey) key);
        } else if (ECDHDecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && ECDHDecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
            if (!(key instanceof ECPrivateKey)) {
                throw new KeyTypeException(ECPrivateKey.class);
            }
            passwordBasedDecrypter = new ECDHDecrypter((ECPrivateKey) key);
        } else if (DirectDecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && DirectDecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
            if (!(key instanceof SecretKey)) {
                throw new KeyTypeException(SecretKey.class);
            }
            JWEDecrypter directDecrypter = new DirectDecrypter((SecretKey) key);
            if (!directDecrypter.supportedEncryptionMethods().contains(jWEHeader.getEncryptionMethod())) {
                throw new KeyLengthException(jWEHeader.getEncryptionMethod().cekBitLength(), jWEHeader.getEncryptionMethod());
            }
            passwordBasedDecrypter = directDecrypter;
        } else if (AESDecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) && AESDecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
            if (!(key instanceof SecretKey)) {
                throw new KeyTypeException(SecretKey.class);
            }
            JWEDecrypter aESDecrypter = new AESDecrypter((SecretKey) key);
            if (!aESDecrypter.supportedJWEAlgorithms().contains(jWEHeader.getAlgorithm())) {
                throw new KeyLengthException(jWEHeader.getAlgorithm());
            }
            passwordBasedDecrypter = aESDecrypter;
        } else {
            if (!PasswordBasedDecrypter.SUPPORTED_ALGORITHMS.contains(jWEHeader.getAlgorithm()) || !PasswordBasedDecrypter.SUPPORTED_ENCRYPTION_METHODS.contains(jWEHeader.getEncryptionMethod())) {
                throw new JOSEException("Unsupported JWE algorithm or encryption method");
            }
            if (!(key instanceof SecretKey)) {
                throw new KeyTypeException(SecretKey.class);
            }
            passwordBasedDecrypter = new PasswordBasedDecrypter(key.getEncoded());
        }
        passwordBasedDecrypter.getJCAContext().setSecureRandom(this.jcaContext.getSecureRandom());
        passwordBasedDecrypter.getJCAContext().setProvider(this.jcaContext.getProvider());
        passwordBasedDecrypter.getJCAContext().setKeyEncryptionProvider(this.jcaContext.getKeyEncryptionProvider());
        passwordBasedDecrypter.getJCAContext().setMACProvider(this.jcaContext.getMACProvider());
        passwordBasedDecrypter.getJCAContext().setContentEncryptionProvider(this.jcaContext.getContentEncryptionProvider());
        return passwordBasedDecrypter;
    }

    @Override // com.nimbusds.jose.jca.JCAAware
    public JWEJCAContext getJCAContext() {
        return this.jcaContext;
    }

    @Override // com.nimbusds.jose.JWEProvider
    public Set<EncryptionMethod> supportedEncryptionMethods() {
        return SUPPORTED_ENCRYPTION_METHODS;
    }

    @Override // com.nimbusds.jose.JWEProvider
    public Set<JWEAlgorithm> supportedJWEAlgorithms() {
        return SUPPORTED_ALGORITHMS;
    }
}
