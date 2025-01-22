package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.util.ByteUtils;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/DirectCryptoProvider.class */
public abstract class DirectCryptoProvider extends BaseJWEProvider {
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS = ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS;
    private final SecretKey cek;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWEAlgorithm.DIR);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DirectCryptoProvider(SecretKey secretKey) throws KeyLengthException {
        super(SUPPORTED_ALGORITHMS, getCompatibleEncryptionMethods(ByteUtils.bitLength(secretKey.getEncoded())));
        this.cek = secretKey;
    }

    private static Set<EncryptionMethod> getCompatibleEncryptionMethods(int i) throws KeyLengthException {
        Set<EncryptionMethod> set = ContentCryptoProvider.COMPATIBLE_ENCRYPTION_METHODS.get(Integer.valueOf(i));
        if (set != null) {
            return set;
        }
        throw new KeyLengthException("The Content Encryption Key length must be 128 bits (16 bytes), 192 bits (24 bytes), 256 bits (32 bytes), 384 bits (48 bytes) or 512 bites (64 bytes)");
    }

    public SecretKey getKey() {
        return this.cek;
    }
}
