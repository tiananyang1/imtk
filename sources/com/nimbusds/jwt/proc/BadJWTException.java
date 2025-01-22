package com.nimbusds.jwt.proc;

import com.nimbusds.jose.proc.BadJOSEException;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jwt/proc/BadJWTException.class */
public class BadJWTException extends BadJOSEException {
    public BadJWTException(String str) {
        super(str);
    }

    public BadJWTException(String str, Throwable th) {
        super(str, th);
    }
}
