package com.nimbusds.jose.jwk;

import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/KeyOperation.class */
public enum KeyOperation {
    SIGN("sign"),
    VERIFY("verify"),
    ENCRYPT("encrypt"),
    DECRYPT("decrypt"),
    WRAP_KEY("wrapKey"),
    UNWRAP_KEY("unwrapKey"),
    DERIVE_KEY("deriveKey"),
    DERIVE_BITS("deriveBits");

    private final String identifier;

    KeyOperation(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The key operation identifier must not be null");
        }
        this.identifier = str;
    }

    public static Set<KeyOperation> parse(List<String> list) throws ParseException {
        KeyOperation keyOperation;
        if (list == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str : list) {
            if (str != null) {
                KeyOperation[] valuesCustom = valuesCustom();
                int length = valuesCustom.length;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= length) {
                        keyOperation = null;
                        break;
                    }
                    keyOperation = valuesCustom[i2];
                    if (str.equals(keyOperation.identifier())) {
                        break;
                    }
                    i = i2 + 1;
                }
                if (keyOperation == null) {
                    throw new ParseException("Invalid JWK operation: " + str, 0);
                }
                linkedHashSet.add(keyOperation);
            }
        }
        return linkedHashSet;
    }

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static KeyOperation[] valuesCustom() {
        KeyOperation[] valuesCustom = values();
        int length = valuesCustom.length;
        KeyOperation[] keyOperationArr = new KeyOperation[length];
        System.arraycopy(valuesCustom, 0, keyOperationArr, 0, length);
        return keyOperationArr;
    }

    public String identifier() {
        return this.identifier;
    }

    @Override // java.lang.Enum
    public String toString() {
        return identifier();
    }
}
