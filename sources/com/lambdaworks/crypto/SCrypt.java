package com.lambdaworks.crypto;

import com.lambdaworks.jni.LibraryLoaders;
import com.sun.jna.Function;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes08-dex2jar.jar:com/lambdaworks/crypto/SCrypt.class */
public class SCrypt {
    private static final boolean native_library_loaded = LibraryLoaders.loader().load("scrypt", true);

    /* renamed from: R */
    public static int m8R(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static void blockmix_salsa8(byte[] bArr, int i, int i2, int i3) {
        int i4;
        byte[] bArr2 = new byte[64];
        int i5 = i3 * 2;
        System.arraycopy(bArr, ((i5 - 1) * 64) + i, bArr2, 0, 64);
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i7 >= i5) {
                break;
            }
            int i8 = i7 * 64;
            blockxor(bArr, i8, bArr2, 0, 64);
            salsa20_8(bArr2);
            System.arraycopy(bArr2, 0, bArr, i8 + i2, 64);
            i6 = i7 + 1;
        }
        int i9 = 0;
        while (true) {
            int i10 = i9;
            if (i10 >= i3) {
                break;
            }
            System.arraycopy(bArr, (i10 * 2 * 64) + i2, bArr, (i10 * 64) + i, 64);
            i9 = i10 + 1;
        }
        for (i4 = 0; i4 < i3; i4++) {
            System.arraycopy(bArr, (((i4 * 2) + 1) * 64) + i2, bArr, ((i4 + i3) * 64) + i, 64);
        }
    }

    public static void blockxor(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= i3) {
                return;
            }
            int i6 = i2 + i5;
            bArr2[i6] = (byte) (bArr2[i6] ^ bArr[i + i5]);
            i4 = i5 + 1;
        }
    }

    public static int integerify(byte[] bArr, int i, int i2) {
        int i3 = i + (((i2 * 2) - 1) * 64);
        return ((bArr[i3 + 3] & 255) << 24) | ((bArr[i3 + 0] & 255) << 0) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16);
    }

    public static void salsa20_8(byte[] bArr) {
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 16) {
                break;
            }
            int i3 = i2 * 4;
            iArr[i2] = (bArr[i3 + 0] & 255) << 0;
            iArr[i2] = ((bArr[i3 + 1] & 255) << 8) | iArr[i2];
            iArr[i2] = iArr[i2] | ((bArr[i3 + 2] & 255) << 16);
            iArr[i2] = ((bArr[i3 + 3] & 255) << 24) | iArr[i2];
            i = i2 + 1;
        }
        System.arraycopy(iArr, 0, iArr2, 0, 16);
        int i4 = 8;
        while (true) {
            int i5 = i4;
            if (i5 <= 0) {
                break;
            }
            iArr2[4] = iArr2[4] ^ m8R(iArr2[0] + iArr2[12], 7);
            iArr2[8] = iArr2[8] ^ m8R(iArr2[4] + iArr2[0], 9);
            iArr2[12] = iArr2[12] ^ m8R(iArr2[8] + iArr2[4], 13);
            iArr2[0] = iArr2[0] ^ m8R(iArr2[12] + iArr2[8], 18);
            iArr2[9] = iArr2[9] ^ m8R(iArr2[5] + iArr2[1], 7);
            iArr2[13] = iArr2[13] ^ m8R(iArr2[9] + iArr2[5], 9);
            iArr2[1] = iArr2[1] ^ m8R(iArr2[13] + iArr2[9], 13);
            iArr2[5] = iArr2[5] ^ m8R(iArr2[1] + iArr2[13], 18);
            iArr2[14] = m8R(iArr2[10] + iArr2[6], 7) ^ iArr2[14];
            iArr2[2] = m8R(iArr2[14] + iArr2[10], 9) ^ iArr2[2];
            iArr2[6] = m8R(iArr2[2] + iArr2[14], 13) ^ iArr2[6];
            iArr2[10] = iArr2[10] ^ m8R(iArr2[6] + iArr2[2], 18);
            iArr2[3] = iArr2[3] ^ m8R(iArr2[15] + iArr2[11], 7);
            iArr2[7] = iArr2[7] ^ m8R(iArr2[3] + iArr2[15], 9);
            iArr2[11] = iArr2[11] ^ m8R(iArr2[7] + iArr2[3], 13);
            iArr2[15] = iArr2[15] ^ m8R(iArr2[11] + iArr2[7], 18);
            iArr2[1] = iArr2[1] ^ m8R(iArr2[0] + iArr2[3], 7);
            iArr2[2] = iArr2[2] ^ m8R(iArr2[1] + iArr2[0], 9);
            iArr2[3] = iArr2[3] ^ m8R(iArr2[2] + iArr2[1], 13);
            iArr2[0] = m8R(iArr2[3] + iArr2[2], 18) ^ iArr2[0];
            iArr2[6] = iArr2[6] ^ m8R(iArr2[5] + iArr2[4], 7);
            iArr2[7] = iArr2[7] ^ m8R(iArr2[6] + iArr2[5], 9);
            iArr2[4] = iArr2[4] ^ m8R(iArr2[7] + iArr2[6], 13);
            iArr2[5] = iArr2[5] ^ m8R(iArr2[4] + iArr2[7], 18);
            iArr2[11] = iArr2[11] ^ m8R(iArr2[10] + iArr2[9], 7);
            iArr2[8] = iArr2[8] ^ m8R(iArr2[11] + iArr2[10], 9);
            iArr2[9] = iArr2[9] ^ m8R(iArr2[8] + iArr2[11], 13);
            iArr2[10] = iArr2[10] ^ m8R(iArr2[9] + iArr2[8], 18);
            iArr2[12] = iArr2[12] ^ m8R(iArr2[15] + iArr2[14], 7);
            iArr2[13] = iArr2[13] ^ m8R(iArr2[12] + iArr2[15], 9);
            iArr2[14] = m8R(iArr2[13] + iArr2[12], 13) ^ iArr2[14];
            iArr2[15] = m8R(iArr2[14] + iArr2[13], 18) ^ iArr2[15];
            i4 = i5 - 2;
        }
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i7 >= 16) {
                break;
            }
            iArr[i7] = iArr2[i7] + iArr[i7];
            i6 = i7 + 1;
        }
        int i8 = 0;
        while (true) {
            int i9 = i8;
            if (i9 >= 16) {
                return;
            }
            int i10 = i9 * 4;
            bArr[i10 + 0] = (byte) ((iArr[i9] >> 0) & 255);
            bArr[i10 + 1] = (byte) ((iArr[i9] >> 8) & 255);
            bArr[i10 + 2] = (byte) ((iArr[i9] >> 16) & 255);
            bArr[i10 + 3] = (byte) ((iArr[i9] >> 24) & 255);
            i8 = i9 + 1;
        }
    }

    public static byte[] scrypt(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) throws GeneralSecurityException {
        return native_library_loaded ? scryptN(bArr, bArr2, i, i2, i3, i4) : scryptJ(bArr, bArr2, i, i2, i3, i4);
    }

    public static byte[] scryptJ(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) throws GeneralSecurityException {
        if (i < 2 || ((i - 1) & i) != 0) {
            throw new IllegalArgumentException("N must be a power of 2 greater than 1");
        }
        if (i > 16777215 / i2) {
            throw new IllegalArgumentException("Parameter N is too large");
        }
        if (i2 > 16777215 / i3) {
            throw new IllegalArgumentException("Parameter r is too large");
        }
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
        byte[] bArr3 = new byte[i4];
        int i5 = i2 * 128;
        byte[] bArr4 = new byte[i5 * i3];
        byte[] bArr5 = new byte[i2 * Function.MAX_NARGS];
        byte[] bArr6 = new byte[i5 * i];
        PBKDF.pbkdf2(mac, bArr2, 1, bArr4, i3 * 128 * i2);
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i7 >= i3) {
                PBKDF.pbkdf2(mac, bArr4, 1, bArr3, i4);
                return bArr3;
            }
            smix(bArr4, i7 * 128 * i2, i2, i, bArr6, bArr5);
            i6 = i7 + 1;
        }
    }

    public static native byte[] scryptN(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4);

    public static void smix(byte[] bArr, int i, int i2, int i3, byte[] bArr2, byte[] bArr3) {
        int i4 = i2 * 128;
        System.arraycopy(bArr, i, bArr3, 0, i4);
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= i3) {
                break;
            }
            System.arraycopy(bArr3, 0, bArr2, i6 * i4, i4);
            blockmix_salsa8(bArr3, 0, i4, i2);
            i5 = i6 + 1;
        }
        int i7 = 0;
        while (true) {
            int i8 = i7;
            if (i8 >= i3) {
                System.arraycopy(bArr3, 0, bArr, i, i4);
                return;
            } else {
                blockxor(bArr2, (integerify(bArr3, 0, i2) & (i3 - 1)) * i4, bArr3, 0, i4);
                blockmix_salsa8(bArr3, 0, i4, i2);
                i7 = i8 + 1;
            }
        }
    }
}
