package com.nimbusds.jose.jwk;

import com.nimbusds.jose.JOSEException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/jwk/AssymetricJWK.class */
public interface AssymetricJWK {
    KeyPair toKeyPair() throws JOSEException;

    PrivateKey toPrivateKey() throws JOSEException;

    PublicKey toPublicKey() throws JOSEException;
}
