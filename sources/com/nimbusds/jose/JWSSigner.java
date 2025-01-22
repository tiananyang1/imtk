package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWSSigner.class */
public interface JWSSigner extends JWSProvider {
    Base64URL sign(JWSHeader jWSHeader, byte[] bArr) throws JOSEException;
}
