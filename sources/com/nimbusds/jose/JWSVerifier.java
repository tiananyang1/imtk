package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWSVerifier.class */
public interface JWSVerifier extends JWSProvider {
    boolean verify(JWSHeader jWSHeader, byte[] bArr, Base64URL base64URL) throws JOSEException;
}
