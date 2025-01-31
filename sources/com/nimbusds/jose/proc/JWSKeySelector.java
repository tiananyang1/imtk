package com.nimbusds.jose.proc;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.proc.SecurityContext;
import java.security.Key;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/proc/JWSKeySelector.class */
public interface JWSKeySelector<C extends SecurityContext> {
    List<? extends Key> selectJWSKeys(JWSHeader jWSHeader, C c) throws KeySourceException;
}
