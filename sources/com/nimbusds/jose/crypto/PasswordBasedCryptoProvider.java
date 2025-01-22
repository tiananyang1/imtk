package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.util.StandardCharset;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/PasswordBasedCryptoProvider.class */
public abstract class PasswordBasedCryptoProvider extends BaseJWEProvider {
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS = ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS;
    private final byte[] password;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWEAlgorithm.PBES2_HS256_A128KW);
        linkedHashSet.add(JWEAlgorithm.PBES2_HS384_A192KW);
        linkedHashSet.add(JWEAlgorithm.PBES2_HS512_A256KW);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PasswordBasedCryptoProvider(byte[] bArr) {
        super(SUPPORTED_ALGORITHMS, ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS);
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("The password must not be null or empty");
        }
        this.password = bArr;
    }

    public byte[] getPassword() {
        return this.password;
    }

    public String getPasswordString() {
        return new String(this.password, StandardCharset.UTF_8);
    }
}
