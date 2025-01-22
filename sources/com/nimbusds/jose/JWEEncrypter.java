package com.nimbusds.jose;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/JWEEncrypter.class */
public interface JWEEncrypter extends JWEProvider {
    JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException;
}
