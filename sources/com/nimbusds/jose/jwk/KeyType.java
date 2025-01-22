package com.nimbusds.jose.jwk;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.Requirement;
import java.io.Serializable;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/KeyType.class */
public final class KeyType implements JSONAware, Serializable {
    private static final long serialVersionUID = 1;
    private final Requirement requirement;
    private final String value;

    /* renamed from: EC */
    public static final KeyType f91EC = new KeyType("EC", Requirement.RECOMMENDED);
    public static final KeyType RSA = new KeyType("RSA", Requirement.REQUIRED);
    public static final KeyType OCT = new KeyType("oct", Requirement.OPTIONAL);
    public static final KeyType OKP = new KeyType("OKP", Requirement.OPTIONAL);

    public KeyType(String str, Requirement requirement) {
        if (str == null) {
            throw new IllegalArgumentException("The key type value must not be null");
        }
        this.value = str;
        this.requirement = requirement;
    }

    public static KeyType forAlgorithm(Algorithm algorithm) {
        if (algorithm == null) {
            return null;
        }
        if (JWSAlgorithm.Family.RSA.contains(algorithm)) {
            return RSA;
        }
        if (JWSAlgorithm.Family.f81EC.contains(algorithm)) {
            return f91EC;
        }
        if (JWSAlgorithm.Family.HMAC_SHA.contains(algorithm)) {
            return OCT;
        }
        if (JWEAlgorithm.Family.RSA.contains(algorithm)) {
            return RSA;
        }
        if (JWEAlgorithm.Family.ECDH_ES.contains(algorithm)) {
            return f91EC;
        }
        if (!JWEAlgorithm.DIR.equals(algorithm) && !JWEAlgorithm.Family.AES_GCM_KW.contains(algorithm) && !JWEAlgorithm.Family.AES_KW.contains(algorithm) && !JWEAlgorithm.Family.PBES2.contains(algorithm)) {
            if (JWSAlgorithm.Family.f82ED.contains(algorithm)) {
                return OKP;
            }
            return null;
        }
        return OCT;
    }

    public static KeyType parse(String str) {
        return str.equals(f91EC.getValue()) ? f91EC : str.equals(RSA.getValue()) ? RSA : str.equals(OCT.getValue()) ? OCT : str.equals(OKP.getValue()) ? OKP : new KeyType(str, null);
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof KeyType) && toString().equals(obj.toString());
    }

    public Requirement getRequirement() {
        return this.requirement;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toJSONString() {
        return "\"" + JSONObject.escape(this.value) + '\"';
    }

    public String toString() {
        return this.value;
    }
}
