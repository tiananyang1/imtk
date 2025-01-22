package com.nimbusds.jwt;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jwt/JWTClaimsSetTransformer.class */
public interface JWTClaimsSetTransformer<T> {
    T transform(JWTClaimsSet jWTClaimsSet);
}
