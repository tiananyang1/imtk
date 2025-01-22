package com.nimbusds.jose.jca;

import java.security.Provider;
import java.security.SecureRandom;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jca/JCAContext.class */
public class JCAContext {
    private Provider provider;
    private SecureRandom randomGen;

    public JCAContext() {
        this(null, null);
    }

    public JCAContext(Provider provider, SecureRandom secureRandom) {
        this.provider = provider;
        this.randomGen = secureRandom;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public SecureRandom getSecureRandom() {
        SecureRandom secureRandom = this.randomGen;
        return secureRandom != null ? secureRandom : new SecureRandom();
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setSecureRandom(SecureRandom secureRandom) {
        this.randomGen = secureRandom;
    }
}
