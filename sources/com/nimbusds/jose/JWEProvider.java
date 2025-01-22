package com.nimbusds.jose;

import com.nimbusds.jose.jca.JCAAware;
import com.nimbusds.jose.jca.JWEJCAContext;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWEProvider.class */
public interface JWEProvider extends JOSEProvider, JCAAware<JWEJCAContext> {
    Set<EncryptionMethod> supportedEncryptionMethods();

    Set<JWEAlgorithm> supportedJWEAlgorithms();
}
