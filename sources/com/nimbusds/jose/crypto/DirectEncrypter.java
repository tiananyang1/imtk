package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.ByteUtils;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/DirectEncrypter.class */
public class DirectEncrypter extends DirectCryptoProvider implements JWEEncrypter {
    public DirectEncrypter(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toSecretKey("AES"));
    }

    public DirectEncrypter(SecretKey secretKey) throws KeyLengthException {
        super(secretKey);
    }

    public DirectEncrypter(byte[] bArr) throws KeyLengthException {
        this(new SecretKeySpec(bArr, "AES"));
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        JWEAlgorithm algorithm = jWEHeader.getAlgorithm();
        if (!algorithm.equals(JWEAlgorithm.DIR)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithm, SUPPORTED_ALGORITHMS));
        }
        EncryptionMethod encryptionMethod = jWEHeader.getEncryptionMethod();
        if (encryptionMethod.cekBitLength() == ByteUtils.safeBitLength(getKey().getEncoded())) {
            return ContentCryptoProvider.encrypt(jWEHeader, bArr, getKey(), null, getJCAContext());
        }
        throw new KeyLengthException(encryptionMethod.cekBitLength(), encryptionMethod);
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    @Override // com.nimbusds.jose.crypto.DirectCryptoProvider
    public /* bridge */ /* synthetic */ SecretKey getKey() {
        return super.getKey();
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
