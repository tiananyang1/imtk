package com.nimbusds.jose.jwk;

import com.nimbusds.jose.JOSEException;
import java.security.Key;
import java.security.KeyPair;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/KeyConverter.class */
public class KeyConverter {
    public static List<Key> toJavaKeys(List<JWK> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        LinkedList linkedList = new LinkedList();
        for (Object obj : list) {
            try {
                if (obj instanceof AssymetricJWK) {
                    KeyPair keyPair = ((AssymetricJWK) obj).toKeyPair();
                    linkedList.add(keyPair.getPublic());
                    if (keyPair.getPrivate() != null) {
                        linkedList.add(keyPair.getPrivate());
                    }
                } else if (obj instanceof SecretJWK) {
                    linkedList.add(((SecretJWK) obj).toSecretKey());
                }
            } catch (JOSEException e) {
            }
        }
        return linkedList;
    }
}
