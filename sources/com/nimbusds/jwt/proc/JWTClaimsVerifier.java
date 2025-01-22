package com.nimbusds.jwt.proc;

import com.nimbusds.jwt.JWTClaimsSet;

@Deprecated
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jwt/proc/JWTClaimsVerifier.class */
public interface JWTClaimsVerifier {
    @Deprecated
    void verify(JWTClaimsSet jWTClaimsSet) throws BadJWTException;
}
