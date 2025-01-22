package com.nimbusds.jose.jwk.source;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.proc.SecurityContext;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/source/JWKSource.class */
public interface JWKSource<C extends SecurityContext> {
    List<JWK> get(JWKSelector jWKSelector, C c) throws KeySourceException;
}
