package com.nimbusds.jose.jwk;

import java.security.cert.X509Certificate;
import java.text.ParseException;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/KeyUse.class */
public enum KeyUse {
    SIGNATURE("sig"),
    ENCRYPTION("enc");

    private final String identifier;

    KeyUse(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The key use identifier must not be null");
        }
        this.identifier = str;
    }

    public static KeyUse from(X509Certificate x509Certificate) {
        if (x509Certificate.getKeyUsage() == null) {
            return null;
        }
        if (x509Certificate.getKeyUsage()[1]) {
            return SIGNATURE;
        }
        if (x509Certificate.getKeyUsage()[0] && x509Certificate.getKeyUsage()[2]) {
            return ENCRYPTION;
        }
        if (x509Certificate.getKeyUsage()[0] && x509Certificate.getKeyUsage()[4]) {
            return ENCRYPTION;
        }
        if (x509Certificate.getKeyUsage()[2] || x509Certificate.getKeyUsage()[3] || x509Certificate.getKeyUsage()[4]) {
            return ENCRYPTION;
        }
        if (x509Certificate.getKeyUsage()[5] || x509Certificate.getKeyUsage()[6]) {
            return SIGNATURE;
        }
        return null;
    }

    public static KeyUse parse(String str) throws ParseException {
        if (str == null) {
            return null;
        }
        KeyUse[] valuesCustom = valuesCustom();
        int length = valuesCustom.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                throw new ParseException("Invalid JWK use: " + str, 0);
            }
            KeyUse keyUse = valuesCustom[i2];
            if (str.equals(keyUse.identifier)) {
                return keyUse;
            }
            i = i2 + 1;
        }
    }

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static KeyUse[] valuesCustom() {
        KeyUse[] valuesCustom = values();
        int length = valuesCustom.length;
        KeyUse[] keyUseArr = new KeyUse[length];
        System.arraycopy(valuesCustom, 0, keyUseArr, 0, length);
        return keyUseArr;
    }

    public String identifier() {
        return this.identifier;
    }

    @Override // java.lang.Enum
    public String toString() {
        return identifier();
    }
}
