package com.nimbusds.jose.crypto.utils;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/utils/ConstantTimeUtils.class */
public class ConstantTimeUtils {
    private ConstantTimeUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean areEqual(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        byte b = false;
        for (int i = 0; i < bArr.length; i++) {
            b = ((b == true ? 1 : 0) | (bArr[i] ^ bArr2[i])) == true ? 1 : 0;
        }
        return b == false;
    }
}
