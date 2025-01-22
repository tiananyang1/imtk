package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.Curve;
import java.util.Collection;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AlgorithmSupportMessage.class */
class AlgorithmSupportMessage {
    private AlgorithmSupportMessage() {
    }

    private static String itemize(Collection collection) {
        StringBuilder sb = new StringBuilder();
        Object[] array = collection.toArray();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= array.length) {
                return sb.toString();
            }
            if (i2 != 0) {
                if (i2 < array.length - 1) {
                    sb.append(", ");
                } else if (i2 == array.length - 1) {
                    sb.append(" or ");
                }
            }
            sb.append(array[i2].toString());
            i = i2 + 1;
        }
    }

    public static String unsupportedEllipticCurve(Curve curve, Collection<Curve> collection) {
        return "Unsupported elliptic curve " + curve + ", must be " + itemize(collection);
    }

    public static String unsupportedEncryptionMethod(EncryptionMethod encryptionMethod, Collection<EncryptionMethod> collection) {
        return "Unsupported JWE encryption method " + encryptionMethod + ", must be " + itemize(collection);
    }

    public static String unsupportedJWEAlgorithm(JWEAlgorithm jWEAlgorithm, Collection<JWEAlgorithm> collection) {
        return "Unsupported JWE algorithm " + jWEAlgorithm + ", must be " + itemize(collection);
    }

    public static String unsupportedJWSAlgorithm(JWSAlgorithm jWSAlgorithm, Collection<JWSAlgorithm> collection) {
        return "Unsupported JWS algorithm " + jWSAlgorithm + ", must be " + itemize(collection);
    }
}
