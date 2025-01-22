package com.nimbusds.jose.jca;

import com.nimbusds.jose.jca.JCAContext;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jca/JCAAware.class */
public interface JCAAware<T extends JCAContext> {
    T getJCAContext();
}
