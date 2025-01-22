package com.lambdaworks.crypto;

import com.lambdaworks.codec.Base64;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

/* loaded from: classes08-dex2jar.jar:com/lambdaworks/crypto/SCryptUtil.class */
public class SCryptUtil {
    /* JADX WARN: Multi-variable type inference failed */
    public static boolean check(String str, String str2) {
        try {
            try {
                String[] split = str2.split("\\$");
                if (split.length != 5 || !split[1].equals("s0")) {
                    throw new IllegalArgumentException("Invalid hashed value");
                }
                long parseLong = Long.parseLong(split[2], 16);
                byte[] decode = Base64.decode(split[3].toCharArray());
                byte[] decode2 = Base64.decode(split[4].toCharArray());
                int pow = (int) Math.pow(2.0d, (parseLong >> 16) & 65535);
                int i = (int) parseLong;
                byte[] scrypt = SCrypt.scrypt(str.getBytes("UTF-8"), decode, pow, (i >> 8) & 255, i & 255, 32);
                if (decode2.length != scrypt.length) {
                    return false;
                }
                byte b = false;
                for (int i2 = 0; i2 < decode2.length; i2++) {
                    b = ((b == true ? 1 : 0) | (decode2[i2] ^ scrypt[i2])) == true ? 1 : 0;
                }
                return b == false;
            } catch (GeneralSecurityException e) {
                throw new IllegalStateException("JVM doesn't support SHA1PRNG or HMAC_SHA256?");
            }
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalStateException("JVM doesn't support UTF-8?");
        }
    }

    private static int log2(int i) {
        int i2;
        int i3;
        if (((-65536) & i) != 0) {
            i3 = i >>> 16;
            i2 = 16;
        } else {
            i2 = 0;
            i3 = i;
        }
        int i4 = i2;
        int i5 = i3;
        if (i3 >= 256) {
            i5 = i3 >>> 8;
            i4 = i2 + 8;
        }
        int i6 = i4;
        int i7 = i5;
        if (i5 >= 16) {
            i7 = i5 >>> 4;
            i6 = i4 + 4;
        }
        int i8 = i6;
        int i9 = i7;
        if (i7 >= 4) {
            i9 = i7 >>> 2;
            i8 = i6 + 2;
        }
        return i8 + (i9 >>> 1);
    }

    public static String scrypt(String str, int i, int i2, int i3) {
        try {
            byte[] bArr = new byte[16];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(bArr);
            byte[] scrypt = SCrypt.scrypt(str.getBytes("UTF-8"), bArr, i, i2, i3, 32);
            String l = Long.toString((log2(i) << 16) | (i2 << 8) | i3, 16);
            StringBuilder sb = new StringBuilder((bArr.length + scrypt.length) * 2);
            sb.append("$s0$");
            sb.append(l);
            sb.append('$');
            sb.append(Base64.encode(bArr));
            sb.append('$');
            sb.append(Base64.encode(scrypt));
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("JVM doesn't support UTF-8?");
        } catch (GeneralSecurityException e2) {
            throw new IllegalStateException("JVM doesn't support SHA1PRNG or HMAC_SHA256?");
        }
    }
}
