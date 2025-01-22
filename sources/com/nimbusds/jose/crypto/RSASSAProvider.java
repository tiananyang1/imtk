package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JWSAlgorithm;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/RSASSAProvider.class */
public abstract class RSASSAProvider extends BaseJWSProvider {
    public static final Set<JWSAlgorithm> SUPPORTED_ALGORITHMS;

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWSAlgorithm.RS256);
        linkedHashSet.add(JWSAlgorithm.RS384);
        linkedHashSet.add(JWSAlgorithm.RS512);
        linkedHashSet.add(JWSAlgorithm.PS256);
        linkedHashSet.add(JWSAlgorithm.PS384);
        linkedHashSet.add(JWSAlgorithm.PS512);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RSASSAProvider() {
        super(SUPPORTED_ALGORITHMS);
    }
}
