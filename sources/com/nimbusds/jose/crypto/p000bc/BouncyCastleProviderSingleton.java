package com.nimbusds.jose.crypto.p000bc;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/bc/BouncyCastleProviderSingleton.class */
public final class BouncyCastleProviderSingleton {
    private static BouncyCastleProvider bouncyCastleProvider;

    private BouncyCastleProviderSingleton() {
    }

    public static BouncyCastleProvider getInstance() {
        BouncyCastleProvider bouncyCastleProvider2 = bouncyCastleProvider;
        if (bouncyCastleProvider2 != null) {
            return bouncyCastleProvider2;
        }
        bouncyCastleProvider = new BouncyCastleProvider();
        return bouncyCastleProvider;
    }
}
