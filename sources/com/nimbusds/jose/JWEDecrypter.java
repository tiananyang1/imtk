package com.nimbusds.jose;

import com.nimbusds.jose.util.Base64URL;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWEDecrypter.class */
public interface JWEDecrypter extends JWEProvider {
    byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws JOSEException;
}
