package com.nimbusds.jose;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/Requirement.class */
public enum Requirement {
    REQUIRED,
    RECOMMENDED,
    OPTIONAL;

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static Requirement[] valuesCustom() {
        Requirement[] valuesCustom = values();
        int length = valuesCustom.length;
        Requirement[] requirementArr = new Requirement[length];
        System.arraycopy(valuesCustom, 0, requirementArr, 0, length);
        return requirementArr;
    }
}
