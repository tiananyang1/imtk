package com.nimbusds.jose.proc;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSProvider;
import com.nimbusds.jose.JWSVerifier;
import java.security.Key;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/proc/JWSVerifierFactory.class */
public interface JWSVerifierFactory extends JWSProvider {
    JWSVerifier createJWSVerifier(JWSHeader jWSHeader, Key key) throws JOSEException;
}
