package com.nimbusds.jose;

import com.nimbusds.jose.util.ArrayUtils;
import java.util.Collection;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWEAlgorithm.class */
public final class JWEAlgorithm extends Algorithm {
    private static final long serialVersionUID = 1;

    @Deprecated
    public static final JWEAlgorithm RSA1_5 = new JWEAlgorithm("RSA1_5", Requirement.REQUIRED);

    @Deprecated
    public static final JWEAlgorithm RSA_OAEP = new JWEAlgorithm("RSA-OAEP", Requirement.OPTIONAL);
    public static final JWEAlgorithm RSA_OAEP_256 = new JWEAlgorithm("RSA-OAEP-256", Requirement.OPTIONAL);
    public static final JWEAlgorithm A128KW = new JWEAlgorithm("A128KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm A192KW = new JWEAlgorithm("A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A256KW = new JWEAlgorithm("A256KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm DIR = new JWEAlgorithm("dir", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES = new JWEAlgorithm("ECDH-ES", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES_A128KW = new JWEAlgorithm("ECDH-ES+A128KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm ECDH_ES_A192KW = new JWEAlgorithm("ECDH-ES+A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm ECDH_ES_A256KW = new JWEAlgorithm("ECDH-ES+A256KW", Requirement.RECOMMENDED);
    public static final JWEAlgorithm A128GCMKW = new JWEAlgorithm("A128GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A192GCMKW = new JWEAlgorithm("A192GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm A256GCMKW = new JWEAlgorithm("A256GCMKW", Requirement.OPTIONAL);
    public static final JWEAlgorithm PBES2_HS256_A128KW = new JWEAlgorithm("PBES2-HS256+A128KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm PBES2_HS384_A192KW = new JWEAlgorithm("PBES2-HS384+A192KW", Requirement.OPTIONAL);
    public static final JWEAlgorithm PBES2_HS512_A256KW = new JWEAlgorithm("PBES2-HS512+A256KW", Requirement.OPTIONAL);

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWEAlgorithm$Family.class */
    public static final class Family extends AlgorithmFamily<JWEAlgorithm> {
        private static final long serialVersionUID = 1;
        public static final Family RSA = new Family(JWEAlgorithm.RSA1_5, JWEAlgorithm.RSA_OAEP, JWEAlgorithm.RSA_OAEP_256);
        public static final Family AES_KW = new Family(JWEAlgorithm.A128KW, JWEAlgorithm.A192KW, JWEAlgorithm.A256KW);
        public static final Family ECDH_ES = new Family(JWEAlgorithm.ECDH_ES, JWEAlgorithm.ECDH_ES_A128KW, JWEAlgorithm.ECDH_ES_A192KW, JWEAlgorithm.ECDH_ES_A256KW);
        public static final Family AES_GCM_KW = new Family(JWEAlgorithm.A128GCMKW, JWEAlgorithm.A192GCMKW, JWEAlgorithm.A256GCMKW);
        public static final Family PBES2 = new Family(JWEAlgorithm.PBES2_HS256_A128KW, JWEAlgorithm.PBES2_HS384_A192KW, JWEAlgorithm.PBES2_HS512_A256KW);
        public static final Family ASYMMETRIC = new Family((JWEAlgorithm[]) ArrayUtils.concat((JWEAlgorithm[]) RSA.toArray(new JWEAlgorithm[0]), new JWEAlgorithm[]{(JWEAlgorithm[]) ECDH_ES.toArray(new JWEAlgorithm[0])}));
        public static final Family SYMMETRIC = new Family((JWEAlgorithm[]) ArrayUtils.concat((JWEAlgorithm[]) AES_KW.toArray(new JWEAlgorithm[0]), new JWEAlgorithm[]{(JWEAlgorithm[]) AES_GCM_KW.toArray(new JWEAlgorithm[0]), new JWEAlgorithm[]{JWEAlgorithm.DIR}}));

        public Family(JWEAlgorithm... jWEAlgorithmArr) {
            super(jWEAlgorithmArr);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily
        public /* bridge */ /* synthetic */ boolean add(JWEAlgorithm jWEAlgorithm) {
            return super.add((Family) jWEAlgorithm);
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

    public JWEAlgorithm(String str) {
        super(str, null);
    }

    public JWEAlgorithm(String str, Requirement requirement) {
        super(str, requirement);
    }

    public static JWEAlgorithm parse(String str) {
        return str.equals(RSA1_5.getName()) ? RSA1_5 : str.equals(RSA_OAEP.getName()) ? RSA_OAEP : str.equals(RSA_OAEP_256.getName()) ? RSA_OAEP_256 : str.equals(A128KW.getName()) ? A128KW : str.equals(A192KW.getName()) ? A192KW : str.equals(A256KW.getName()) ? A256KW : str.equals(DIR.getName()) ? DIR : str.equals(ECDH_ES.getName()) ? ECDH_ES : str.equals(ECDH_ES_A128KW.getName()) ? ECDH_ES_A128KW : str.equals(ECDH_ES_A192KW.getName()) ? ECDH_ES_A192KW : str.equals(ECDH_ES_A256KW.getName()) ? ECDH_ES_A256KW : str.equals(A128GCMKW.getName()) ? A128GCMKW : str.equals(A192GCMKW.getName()) ? A192GCMKW : str.equals(A256GCMKW.getName()) ? A256GCMKW : str.equals(PBES2_HS256_A128KW.getName()) ? PBES2_HS256_A128KW : str.equals(PBES2_HS384_A192KW.getName()) ? PBES2_HS384_A192KW : str.equals(PBES2_HS512_A256KW.getName()) ? PBES2_HS512_A256KW : new JWEAlgorithm(str);
    }
}
