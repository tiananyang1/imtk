package com.nimbusds.jose;

import com.subgraph.orchid.Cell;
import com.sun.jna.Function;
import java.util.Collection;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/EncryptionMethod.class */
public final class EncryptionMethod extends Algorithm {
    private static final long serialVersionUID = 1;
    private final int cekBitLength;
    public static final EncryptionMethod A128CBC_HS256 = new EncryptionMethod("A128CBC-HS256", Requirement.REQUIRED, Function.MAX_NARGS);
    public static final EncryptionMethod A192CBC_HS384 = new EncryptionMethod("A192CBC-HS384", Requirement.OPTIONAL, Function.USE_VARARGS);
    public static final EncryptionMethod A256CBC_HS512 = new EncryptionMethod("A256CBC-HS512", Requirement.REQUIRED, Cell.CELL_LEN);
    public static final EncryptionMethod A128CBC_HS256_DEPRECATED = new EncryptionMethod("A128CBC+HS256", Requirement.OPTIONAL, Function.MAX_NARGS);
    public static final EncryptionMethod A256CBC_HS512_DEPRECATED = new EncryptionMethod("A256CBC+HS512", Requirement.OPTIONAL, Cell.CELL_LEN);
    public static final EncryptionMethod A128GCM = new EncryptionMethod("A128GCM", Requirement.RECOMMENDED, 128);
    public static final EncryptionMethod A192GCM = new EncryptionMethod("A192GCM", Requirement.OPTIONAL, 192);
    public static final EncryptionMethod A256GCM = new EncryptionMethod("A256GCM", Requirement.RECOMMENDED, Function.MAX_NARGS);

    /* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/EncryptionMethod$Family.class */
    public static final class Family extends AlgorithmFamily<EncryptionMethod> {
        public static final Family AES_CBC_HMAC_SHA = new Family(EncryptionMethod.A128CBC_HS256, EncryptionMethod.A192CBC_HS384, EncryptionMethod.A256CBC_HS512);
        public static final Family AES_GCM = new Family(EncryptionMethod.A128GCM, EncryptionMethod.A192GCM, EncryptionMethod.A256GCM);
        private static final long serialVersionUID = 1;

        public Family(EncryptionMethod... encryptionMethodArr) {
            super(encryptionMethodArr);
        }

        @Override // com.nimbusds.jose.AlgorithmFamily
        public /* bridge */ /* synthetic */ boolean add(EncryptionMethod encryptionMethod) {
            return super.add((Family) encryptionMethod);
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

    public EncryptionMethod(String str) {
        this(str, null, 0);
    }

    public EncryptionMethod(String str, Requirement requirement) {
        this(str, requirement, 0);
    }

    public EncryptionMethod(String str, Requirement requirement, int i) {
        super(str, requirement);
        this.cekBitLength = i;
    }

    public static EncryptionMethod parse(String str) {
        return str.equals(A128CBC_HS256.getName()) ? A128CBC_HS256 : str.equals(A192CBC_HS384.getName()) ? A192CBC_HS384 : str.equals(A256CBC_HS512.getName()) ? A256CBC_HS512 : str.equals(A128GCM.getName()) ? A128GCM : str.equals(A192GCM.getName()) ? A192GCM : str.equals(A256GCM.getName()) ? A256GCM : str.equals(A128CBC_HS256_DEPRECATED.getName()) ? A128CBC_HS256_DEPRECATED : str.equals(A256CBC_HS512_DEPRECATED.getName()) ? A256CBC_HS512_DEPRECATED : new EncryptionMethod(str);
    }

    public int cekBitLength() {
        return this.cekBitLength;
    }
}
