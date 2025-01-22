package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.jca.JWEJCAContext;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.StandardCharset;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/PasswordBasedEncrypter.class */
public class PasswordBasedEncrypter extends PasswordBasedCryptoProvider implements JWEEncrypter {
    public static final int MIN_RECOMMENDED_ITERATION_COUNT = 1000;
    public static final int MIN_SALT_LENGTH = 8;
    private final int iterationCount;
    private final int saltLength;

    public PasswordBasedEncrypter(String str, int i, int i2) {
        this(str.getBytes(StandardCharset.UTF_8), i, i2);
    }

    public PasswordBasedEncrypter(byte[] bArr, int i, int i2) {
        super(bArr);
        if (i < 8) {
            throw new IllegalArgumentException("The minimum salt length (p2s) is 8 bytes");
        }
        this.saltLength = i;
        if (i2 < 1000) {
            throw new IllegalArgumentException("The minimum recommended iteration count (p2c) is 1000");
        }
        this.iterationCount = i2;
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        JWEAlgorithm algorithm = jWEHeader.getAlgorithm();
        EncryptionMethod encryptionMethod = jWEHeader.getEncryptionMethod();
        byte[] bArr2 = new byte[this.saltLength];
        getJCAContext().getSecureRandom().nextBytes(bArr2);
        SecretKey deriveKey = PBKDF2.deriveKey(getPassword(), PBKDF2.formatSalt(algorithm, bArr2), this.iterationCount, PRFParams.resolve(algorithm, getJCAContext().getMACProvider()));
        JWEHeader build = new JWEHeader.Builder(jWEHeader).pbes2Salt(Base64URL.m3704encode(bArr2)).pbes2Count(this.iterationCount).build();
        SecretKey generateCEK = ContentCryptoProvider.generateCEK(encryptionMethod, getJCAContext().getSecureRandom());
        return ContentCryptoProvider.encrypt(build, bArr, generateCEK, Base64URL.m3704encode(AESKW.wrapCEK(generateCEK, deriveKey, getJCAContext().getKeyEncryptionProvider())), getJCAContext());
    }

    public int getIterationCount() {
        return this.iterationCount;
    }

    @Override // com.nimbusds.jose.crypto.BaseJWEProvider, com.nimbusds.jose.jca.JCAAware
    public /* bridge */ /* synthetic */ JWEJCAContext getJCAContext() {
        return super.getJCAContext();
    }

    @Override // com.nimbusds.jose.crypto.PasswordBasedCryptoProvider
    public /* bridge */ /* synthetic */ byte[] getPassword() {
        return super.getPassword();
    }

    @Override // com.nimbusds.jose.crypto.PasswordBasedCryptoProvider
    public /* bridge */ /* synthetic */ String getPasswordString() {
        return super.getPasswordString();
    }

    public int getSaltLength() {
        return this.saltLength;
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
