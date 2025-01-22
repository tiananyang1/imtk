package com.nimbusds.jose;

import com.nimbusds.jose.util.ArrayUtils;
import java.util.Collection;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWSAlgorithm.class */
public final class JWSAlgorithm extends Algorithm {
    private static final long serialVersionUID = 1;
    public static final JWSAlgorithm HS256 = new JWSAlgorithm("HS256", Requirement.REQUIRED);
    public static final JWSAlgorithm HS384 = new JWSAlgorithm("HS384", Requirement.OPTIONAL);
    public static final JWSAlgorithm HS512 = new JWSAlgorithm("HS512", Requirement.OPTIONAL);
    public static final JWSAlgorithm RS256 = new JWSAlgorithm("RS256", Requirement.RECOMMENDED);
    public static final JWSAlgorithm RS384 = new JWSAlgorithm("RS384", Requirement.OPTIONAL);
    public static final JWSAlgorithm RS512 = new JWSAlgorithm("RS512", Requirement.OPTIONAL);
    public static final JWSAlgorithm ES256 = new JWSAlgorithm("ES256", Requirement.RECOMMENDED);
    public static final JWSAlgorithm ES384 = new JWSAlgorithm("ES384", Requirement.OPTIONAL);
    public static final JWSAlgorithm ES512 = new JWSAlgorithm("ES512", Requirement.OPTIONAL);
    public static final JWSAlgorithm PS256 = new JWSAlgorithm("PS256", Requirement.OPTIONAL);
    public static final JWSAlgorithm PS384 = new JWSAlgorithm("PS384", Requirement.OPTIONAL);
    public static final JWSAlgorithm PS512 = new JWSAlgorithm("PS512", Requirement.OPTIONAL);
    public static final JWSAlgorithm EdDSA = new JWSAlgorithm("EdDSA", Requirement.OPTIONAL);

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWSAlgorithm$Family.class */
    public static final class Family extends AlgorithmFamily<JWSAlgorithm> {
        private static final long serialVersionUID = 1;
        public static final Family HMAC_SHA = new Family(JWSAlgorithm.HS256, JWSAlgorithm.HS384, JWSAlgorithm.HS512);
        public static final Family RSA = new Family(JWSAlgorithm.RS256, JWSAlgorithm.RS384, JWSAlgorithm.RS512, JWSAlgorithm.PS256, JWSAlgorithm.PS384, JWSAlgorithm.PS512);

        /* renamed from: EC */
        public static final Family f81EC = new Family(JWSAlgorithm.ES256, JWSAlgorithm.ES384, JWSAlgorithm.ES512);

        /* renamed from: ED */
        public static final Family f82ED = new Family(JWSAlgorithm.EdDSA);
        public static final Family SIGNATURE = new Family((JWSAlgorithm[]) ArrayUtils.concat((JWSAlgorithm[]) RSA.toArray(new JWSAlgorithm[0]), new JWSAlgorithm[]{(JWSAlgorithm[]) f81EC.toArray(new JWSAlgorithm[0]), (JWSAlgorithm[]) f82ED.toArray(new JWSAlgorithm[0])}));

        public Family(JWSAlgorithm... jWSAlgorithmArr) {
            super(jWSAlgorithmArr);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily
        public /* bridge */ /* synthetic */ boolean add(JWSAlgorithm jWSAlgorithm) {
            return super.add((Family) jWSAlgorithm);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
            return super.addAll(collection);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily, java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean remove(Object obj) {
            return super.remove(obj);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
            return super.removeAll(collection);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
            return super.retainAll(collection);
        }
    }

    public JWSAlgorithm(String str) {
        super(str, null);
    }

    public JWSAlgorithm(String str, Requirement requirement) {
        super(str, requirement);
    }

    public static JWSAlgorithm parse(String str) {
        return str.equals(HS256.getName()) ? HS256 : str.equals(HS384.getName()) ? HS384 : str.equals(HS512.getName()) ? HS512 : str.equals(RS256.getName()) ? RS256 : str.equals(RS384.getName()) ? RS384 : str.equals(RS512.getName()) ? RS512 : str.equals(ES256.getName()) ? ES256 : str.equals(ES384.getName()) ? ES384 : str.equals(ES512.getName()) ? ES512 : str.equals(PS256.getName()) ? PS256 : str.equals(PS384.getName()) ? PS384 : str.equals(PS512.getName()) ? PS512 : str.equals(EdDSA.getName()) ? EdDSA : new JWSAlgorithm(str);
    }
}
