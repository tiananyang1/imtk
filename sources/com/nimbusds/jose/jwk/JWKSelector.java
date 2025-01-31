package com.nimbusds.jose.jwk;

import java.util.LinkedList;
import java.util.List;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/JWKSelector.class */
public final class JWKSelector {
    private final JWKMatcher matcher;

    public JWKSelector(JWKMatcher jWKMatcher) {
        if (jWKMatcher == null) {
            throw new IllegalArgumentException("The JWK matcher must not be null");
        }
        this.matcher = jWKMatcher;
    }

    public JWKMatcher getMatcher() {
        return this.matcher;
    }

    public List<JWK> select(JWKSet jWKSet) {
        LinkedList linkedList = new LinkedList();
        if (jWKSet == null) {
            return linkedList;
        }
        for (JWK jwk : jWKSet.getKeys()) {
            if (this.matcher.matches(jwk)) {
                linkedList.add(jwk);
            }
        }
        return linkedList;
    }
}
