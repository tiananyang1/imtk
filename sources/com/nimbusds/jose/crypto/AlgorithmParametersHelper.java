package com.nimbusds.jose.crypto;

import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AlgorithmParametersHelper.class */
class AlgorithmParametersHelper {
    AlgorithmParametersHelper() {
    }

    public static AlgorithmParameters getInstance(String str, Provider provider) throws NoSuchAlgorithmException {
        return provider == null ? AlgorithmParameters.getInstance(str) : AlgorithmParameters.getInstance(str, provider);
    }
}
