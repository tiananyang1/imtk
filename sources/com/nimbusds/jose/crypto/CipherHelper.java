package com.nimbusds.jose.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/CipherHelper.class */
class CipherHelper {
    CipherHelper() {
    }

    public static Cipher getInstance(String str, Provider provider) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return provider == null ? Cipher.getInstance(str) : Cipher.getInstance(str, provider);
    }
}
