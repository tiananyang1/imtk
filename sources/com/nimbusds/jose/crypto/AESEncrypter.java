package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.Container;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AESEncrypter.class */
public class AESEncrypter extends AESCryptoProvider implements JWEEncrypter {

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AESEncrypter$AlgFamily.class */
    private enum AlgFamily {
        AESKW,
        AESGCMKW;

        /* renamed from: values, reason: to resolve conflict with enum method */
        public static AlgFamily[] valuesCustom() {
            AlgFamily[] valuesCustom = values();
            int length = valuesCustom.length;
            AlgFamily[] algFamilyArr = new AlgFamily[length];
            System.arraycopy(valuesCustom, 0, algFamilyArr, 0, length);
            return algFamilyArr;
        }
    }

    public AESEncrypter(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toSecretKey("AES"));
    }

    public AESEncrypter(SecretKey secretKey) throws KeyLengthException {
        super(secretKey);
    }

    public AESEncrypter(byte[] bArr) throws KeyLengthException {
        this(new SecretKeySpec(bArr, "AES"));
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        AlgFamily algFamily;
        JWEHeader build;
        Base64URL base64URL;
        JWEAlgorithm algorithm = jWEHeader.getAlgorithm();
        if (algorithm.equals(JWEAlgorithm.A128KW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 128) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 128 bits for A128KW encryption");
            }
            algFamily = AlgFamily.AESKW;
        } else if (algorithm.equals(JWEAlgorithm.A192KW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 192) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 192 bits for A192KW encryption");
            }
            algFamily = AlgFamily.AESKW;
        } else if (algorithm.equals(JWEAlgorithm.A256KW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 256) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 256 bits for A256KW encryption");
            }
            algFamily = AlgFamily.AESKW;
        } else if (algorithm.equals(JWEAlgorithm.A128GCMKW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 128) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 128 bits for A128GCMKW encryption");
            }
            algFamily = AlgFamily.AESGCMKW;
        } else if (algorithm.equals(JWEAlgorithm.A192GCMKW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 192) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 192 bits for A192GCMKW encryption");
            }
            algFamily = AlgFamily.AESGCMKW;
        } else {
            if (!algorithm.equals(JWEAlgorithm.A256GCMKW)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithm, SUPPORTED_ALGORITHMS));
            }
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 256) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 256 bits for A256GCMKW encryption");
            }
            algFamily = AlgFamily.AESGCMKW;
        }
        SecretKey generateCEK = ContentCryptoProvider.generateCEK(jWEHeader.getEncryptionMethod(), getJCAContext().getSecureRandom());
        if (AlgFamily.AESKW.equals(algFamily)) {
            base64URL = Base64URL.m3704encode(AESKW.wrapCEK(generateCEK, getKey(), getJCAContext().getKeyEncryptionProvider()));
            build = jWEHeader;
        } else {
            if (!AlgFamily.AESGCMKW.equals(algFamily)) {
                throw new JOSEException("Unexpected JWE algorithm: " + algorithm);
            }
            Container container = new Container(AESGCM.generateIV(getJCAContext().getSecureRandom()));
            AuthenticatedCipherText encryptCEK = AESGCMKW.encryptCEK(generateCEK, container, getKey(), getJCAContext().getKeyEncryptionProvider());
            Base64URL m3704encode = Base64URL.m3704encode(encryptCEK.getCipherText());
            build = new JWEHeader.Builder(jWEHeader).m10iv(Base64URL.m3704encode((byte[]) container.get())).authTag(Base64URL.m3704encode(encryptCEK.getAuthenticationTag())).build();
            base64URL = m3704encode;
        }
        return ContentCryptoProvider.encrypt(build, bArr, generateCEK, base64URL, getJCAContext());
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    @Override // com.nimbusds.jose.crypto.AESCryptoProvider
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
