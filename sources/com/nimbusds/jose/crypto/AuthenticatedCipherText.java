package com.nimbusds.jose.crypto;

import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/AuthenticatedCipherText.class */
final class AuthenticatedCipherText {
    private final byte[] authenticationTag;
    private final byte[] cipherText;

    public AuthenticatedCipherText(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new IllegalArgumentException("The cipher text must not be null");
        }
        this.cipherText = bArr;
        if (bArr2 == null) {
            throw new IllegalArgumentException("The authentication tag must not be null");
        }
        this.authenticationTag = bArr2;
    }

    public byte[] getAuthenticationTag() {
        return this.authenticationTag;
    }

    public byte[] getCipherText() {
        return this.cipherText;
    }
}
