package com.nimbusds.jose.jwk;

import com.nimbusds.jose.JWSAlgorithm;
import java.io.Serializable;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/Curve.class */
public final class Curve implements Serializable {
    private static final long serialVersionUID = 1;
    private final String name;
    private final String oid;
    private final String stdName;
    public static final Curve P_256 = new Curve("P-256", "secp256r1", "1.2.840.10045.3.1.7");
    public static final Curve P_384 = new Curve("P-384", "secp384r1", "1.3.132.0.34");
    public static final Curve P_521 = new Curve("P-521", "secp521r1", "1.3.132.0.35");
    public static final Curve Ed25519 = new Curve("Ed25519", "Ed25519", null);
    public static final Curve Ed448 = new Curve("Ed448", "Ed448", null);
    public static final Curve X25519 = new Curve("X25519", "X25519", null);
    public static final Curve X448 = new Curve("X448", "X448", null);

    public Curve(String str) {
        this(str, null, null);
    }

    public Curve(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException("The JOSE cryptographic curve name must not be null");
        }
        this.name = str;
        this.stdName = str2;
        this.oid = str3;
    }

    public static Curve forECParameterSpec(ECParameterSpec eCParameterSpec) {
        return ECParameterTable.get(eCParameterSpec);
    }

    public static Set<Curve> forJWSAlgorithm(JWSAlgorithm jWSAlgorithm) {
        if (JWSAlgorithm.ES256.equals(jWSAlgorithm)) {
            return Collections.singleton(P_256);
        }
        if (JWSAlgorithm.ES384.equals(jWSAlgorithm)) {
            return Collections.singleton(P_384);
        }
        if (JWSAlgorithm.ES512.equals(jWSAlgorithm)) {
            return Collections.singleton(P_521);
        }
        if (JWSAlgorithm.EdDSA.equals(jWSAlgorithm)) {
            return Collections.unmodifiableSet(new HashSet(Arrays.asList(Ed25519, Ed448)));
        }
        return null;
    }

    public static Curve forOID(String str) {
        if (P_256.getOID().equals(str)) {
            return P_256;
        }
        if (P_384.getOID().equals(str)) {
            return P_384;
        }
        if (P_521.getOID().equals(str)) {
            return P_521;
        }
        return null;
    }

    public static Curve forStdName(String str) {
        if ("secp256r1".equals(str) || "prime256v1".equals(str)) {
            return P_256;
        }
        if ("secp384r1".equals(str)) {
            return P_384;
        }
        if ("secp521r1".equals(str)) {
            return P_521;
        }
        if (Ed25519.getStdName().equals(str)) {
            return Ed25519;
        }
        if (Ed448.getStdName().equals(str)) {
            return Ed448;
        }
        if (X25519.getStdName().equals(str)) {
            return X25519;
        }
        if (X448.getStdName().equals(str)) {
            return X448;
        }
        return null;
    }

    public static Curve parse(String str) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("The cryptographic curve string must not be null or empty");
        }
        return str.equals(P_256.getName()) ? P_256 : str.equals(P_384.getName()) ? P_384 : str.equals(P_521.getName()) ? P_521 : str.equals(Ed25519.getName()) ? Ed25519 : str.equals(Ed448.getName()) ? Ed448 : str.equals(X25519.getName()) ? X25519 : str.equals(X448.getName()) ? X448 : new Curve(str);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Curve) && toString().equals(obj.toString());
    }

    public String getName() {
        return this.name;
    }

    public String getOID() {
        return this.oid;
    }

    public String getStdName() {
        return this.stdName;
    }

    public ECParameterSpec toECParameterSpec() {
        return ECParameterTable.get(this);
    }

    public String toString() {
        return getName();
    }
}
