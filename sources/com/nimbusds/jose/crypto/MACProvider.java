package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.util.StandardCharset;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/MACProvider.class */
public abstract class MACProvider extends BaseJWSProvider {
    public static final Set<JWSAlgorithm> SUPPORTED_ALGORITHMS;
    private final byte[] secret;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWSAlgorithm.HS256);
        linkedHashSet.add(JWSAlgorithm.HS384);
        linkedHashSet.add(JWSAlgorithm.HS512);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MACProvider(byte[] bArr, Set<JWSAlgorithm> set) throws KeyLengthException {
        super(set);
        if (bArr.length < 32) {
            throw new KeyLengthException("The secret length must be at least 256 bits");
        }
        this.secret = bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getJCAAlgorithmName(JWSAlgorithm jWSAlgorithm) throws JOSEException {
        if (jWSAlgorithm.equals(JWSAlgorithm.HS256)) {
            return "HMACSHA256";
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.HS384)) {
            return "HMACSHA384";
        }
        if (jWSAlgorithm.equals(JWSAlgorithm.HS512)) {
            return "HMACSHA512";
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWSAlgorithm(jWSAlgorithm, SUPPORTED_ALGORITHMS));
    }

    public byte[] getSecret() {
        return this.secret;
    }

    public SecretKey getSecretKey() {
        return new SecretKeySpec(this.secret, "MAC");
    }

    public String getSecretString() {
        return new String(this.secret, StandardCharset.UTF_8);
    }
}
