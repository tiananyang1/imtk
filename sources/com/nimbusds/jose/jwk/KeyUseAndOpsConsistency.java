package com.nimbusds.jose.jwk;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/KeyUseAndOpsConsistency.class */
class KeyUseAndOpsConsistency {
    static Map<KeyUse, Set<KeyOperation>> MAP;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(KeyUse.SIGNATURE, new HashSet(Arrays.asList(KeyOperation.SIGN, KeyOperation.VERIFY)));
        hashMap.put(KeyUse.ENCRYPTION, new HashSet(Arrays.asList(KeyOperation.ENCRYPT, KeyOperation.DECRYPT, KeyOperation.WRAP_KEY, KeyOperation.UNWRAP_KEY)));
        MAP = Collections.unmodifiableMap(hashMap);
    }

    KeyUseAndOpsConsistency() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean areConsistent(KeyUse keyUse, Set<KeyOperation> set) {
        if (keyUse == null || set == null) {
            return true;
        }
        return MAP.get(keyUse).containsAll(set);
    }
}
