package com.nimbusds.jose.proc;

import com.nimbusds.jose.proc.SecurityContext;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/proc/JOSEProcessorConfiguration.class */
public interface JOSEProcessorConfiguration<C extends SecurityContext> {
    JWEDecrypterFactory getJWEDecrypterFactory();

    JWEKeySelector<C> getJWEKeySelector();

    JWSKeySelector<C> getJWSKeySelector();

    JWSVerifierFactory getJWSVerifierFactory();

    void setJWEDecrypterFactory(JWEDecrypterFactory jWEDecrypterFactory);

    void setJWEKeySelector(JWEKeySelector<C> jWEKeySelector);

    void setJWSKeySelector(JWSKeySelector<C> jWSKeySelector);

    void setJWSVerifierFactory(JWSVerifierFactory jWSVerifierFactory);
}
