package com.lambdaworks.crypto;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:com/lambdaworks/crypto/PBKDF.class */
public class PBKDF {
    public static void pbkdf2(Mac mac, byte[] bArr, int i, byte[] bArr2, int i2) throws GeneralSecurityException {
        int macLength = mac.getMacLength();
        double d = i2;
        double d2 = macLength;
        if (d > (Math.pow(2.0d, 32.0d) - 1.0d) * d2) {
            throw new GeneralSecurityException("Requested key length too long");
        }
        byte[] bArr3 = new byte[macLength];
        byte[] bArr4 = new byte[macLength];
        byte[] bArr5 = new byte[bArr.length + 4];
        int ceil = (int) Math.ceil(d / d2);
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        int i3 = 1;
        while (true) {
            int i4 = i3;
            if (i4 > ceil) {
                return;
            }
            bArr5[bArr.length + 0] = (byte) ((i4 >> 24) & 255);
            bArr5[bArr.length + 1] = (byte) ((i4 >> 16) & 255);
            bArr5[bArr.length + 2] = (byte) ((i4 >> 8) & 255);
            bArr5[bArr.length + 3] = (byte) ((i4 >> 0) & 255);
            mac.update(bArr5);
            mac.doFinal(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr4, 0, macLength);
            int i5 = 1;
            while (true) {
                int i6 = i5;
                if (i6 >= i) {
                    break;
                }
                mac.update(bArr3);
                mac.doFinal(bArr3, 0);
                int i7 = 0;
                while (true) {
                    int i8 = i7;
                    if (i8 < macLength) {
                        bArr4[i8] = (byte) (bArr4[i8] ^ bArr3[i8]);
                        i7 = i8 + 1;
                    }
                }
                i5 = i6 + 1;
            }
            System.arraycopy(bArr4, 0, bArr2, (i4 - 1) * macLength, i4 == ceil ? i2 - ((ceil - 1) * macLength) : macLength);
            i3 = i4 + 1;
        }
    }

    public static byte[] pbkdf2(String str, byte[] bArr, byte[] bArr2, int i, int i2) throws GeneralSecurityException {
        Mac mac = Mac.getInstance(str);
        mac.init(new SecretKeySpec(bArr, str));
        byte[] bArr3 = new byte[i2];
        pbkdf2(mac, bArr2, i, bArr3, i2);
        return bArr3;
    }
}
