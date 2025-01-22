package com.subgraph.orchid.crypto;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/Curve25519.class */
public class Curve25519 {
    static byte[] basev = {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[] minusp = {19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 128};
    final int CRYPTO_BYTES = 32;
    final int CRYPTO_SCALARBYTES = 32;

    static void add(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < 31; i5++) {
            int i6 = i4 + iArr2[i2 + i5] + iArr3[i3 + i5];
            iArr[i + i5] = i6 & 255;
            i4 = i6 >>> 8;
        }
        iArr[i + 31] = i4 + iArr2[i2 + 31] + iArr3[i3 + 31];
    }

    public static int crypto_scalarmult(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[96];
        byte[] bArr4 = new byte[32];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 32) {
                break;
            }
            bArr4[i2] = bArr2[i2];
            i = i2 + 1;
        }
        bArr4[0] = (byte) (bArr4[0] & 248);
        bArr4[31] = (byte) (bArr4[31] & Byte.MAX_VALUE);
        bArr4[31] = (byte) (bArr4[31] | 64);
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= 32) {
                break;
            }
            iArr[i4] = bArr3[i4] & 255;
            i3 = i4 + 1;
        }
        mainloop(iArr, bArr4);
        recip(iArr, 32, iArr, 32);
        mult(iArr, 64, iArr, 0, iArr, 32);
        freeze(iArr, 64);
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= 32) {
                return 0;
            }
            bArr[i6] = (byte) iArr[i6 + 64];
            i5 = i6 + 1;
        }
    }

    public static int crypto_scalarmult_base(byte[] bArr, byte[] bArr2) {
        return crypto_scalarmult(bArr, bArr2, basev);
    }

    static void freeze(int[] iArr, int i) {
        int[] iArr2 = new int[32];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 32) {
                break;
            }
            iArr2[i3] = iArr[i + i3];
            i2 = i3 + 1;
        }
        add(iArr, 0, iArr, 0, minusp, 0);
        int i4 = -((iArr[i + 31] >>> 7) & 1);
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= 32) {
                return;
            }
            int i7 = i + i6;
            iArr[i7] = iArr[i7] ^ ((iArr2[i6] ^ iArr[i7]) & i4);
            i5 = i6 + 1;
        }
    }

    static void mainloop(int[] iArr, byte[] bArr) {
        int[] iArr2 = new int[64];
        int[] iArr3 = new int[64];
        int[] iArr4 = new int[64];
        int[] iArr5 = new int[64];
        int[] iArr6 = new int[64];
        int[] iArr7 = new int[64];
        int[] iArr8 = new int[64];
        int[] iArr9 = new int[64];
        int[] iArr10 = new int[64];
        int[] iArr11 = new int[64];
        int[] iArr12 = new int[64];
        int[] iArr13 = new int[32];
        int[] iArr14 = new int[32];
        int[] iArr15 = new int[32];
        int[] iArr16 = new int[32];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 32) {
                break;
            }
            iArr2[i2] = iArr[i2];
            i = i2 + 1;
        }
        iArr2[32] = 1;
        int i3 = 33;
        while (true) {
            int i4 = i3;
            if (i4 >= 64) {
                break;
            }
            iArr2[i4] = 0;
            i3 = i4 + 1;
        }
        iArr3[0] = 1;
        int i5 = 1;
        while (true) {
            int i6 = i5;
            if (i6 >= 64) {
                break;
            }
            iArr3[i6] = 0;
            i5 = i6 + 1;
        }
        int i7 = 254;
        while (true) {
            int i8 = i7;
            if (i8 < 0) {
                break;
            }
            int i9 = ((bArr[i8 / 8] & 255) >>> (i8 & 7)) & 1;
            select(iArr4, iArr5, iArr3, iArr2, i9);
            add(iArr8, 0, iArr4, 0, iArr4, 32);
            sub(iArr8, 32, iArr4, 0, iArr4, 32);
            add(iArr9, 0, iArr5, 0, iArr5, 32);
            sub(iArr9, 32, iArr5, 0, iArr5, 32);
            square(iArr10, 0, iArr8, 0);
            square(iArr10, 32, iArr8, 32);
            mult(iArr11, 0, iArr9, 0, iArr8, 32);
            mult(iArr11, 32, iArr9, 32, iArr8, 0);
            add(iArr12, 0, iArr11, 0, iArr11, 32);
            sub(iArr12, 32, iArr11, 0, iArr11, 32);
            square(iArr13, 0, iArr12, 32);
            sub(iArr14, 0, iArr10, 0, iArr10, 32);
            mult121665(iArr15, iArr14);
            add(iArr16, 0, iArr15, 0, iArr10, 0);
            mult(iArr6, 0, iArr10, 0, iArr10, 32);
            mult(iArr6, 32, iArr14, 0, iArr16, 0);
            square(iArr7, 0, iArr12, 0);
            mult(iArr7, 32, iArr13, 0, iArr, 0);
            select(iArr3, iArr2, iArr6, iArr7, i9);
            i7 = i8 - 1;
        }
        int i10 = 0;
        while (true) {
            int i11 = i10;
            if (i11 >= 64) {
                return;
            }
            iArr[i11] = iArr3[i11];
            i10 = i11 + 1;
        }
    }

    static void mult(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= 32) {
                squeeze(iArr, i);
                return;
            }
            int i6 = 0;
            for (int i7 = 0; i7 <= i5; i7++) {
                i6 += iArr2[i2 + i7] * iArr3[(i3 + i5) - i7];
            }
            int i8 = i5 + 1;
            int i9 = i6;
            int i10 = i8;
            while (true) {
                int i11 = i10;
                if (i11 < 32) {
                    i9 += iArr2[i2 + i11] * 38 * iArr3[((i3 + i5) + 32) - i11];
                    i10 = i11 + 1;
                }
            }
            iArr[i5 + i] = i9;
            i4 = i8;
        }
    }

    static void mult121665(int[] iArr, int[] iArr2) {
        int i = 0;
        for (int i2 = 0; i2 < 31; i2++) {
            int i3 = i + (iArr2[i2] * 121665);
            iArr[i2] = i3 & 255;
            i = i3 >>> 8;
        }
        int i4 = i + (iArr2[31] * 121665);
        iArr[31] = i4 & 127;
        int i5 = (i4 >>> 7) * 19;
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i7 >= 31) {
                iArr[i7] = i5 + iArr[i7];
                return;
            }
            int i8 = i5 + iArr[i7];
            iArr[i7] = i8 & 255;
            i5 = i8 >>> 8;
            i6 = i7 + 1;
        }
    }

    static void recip(int[] iArr, int i, int[] iArr2, int i2) {
        int[] iArr3 = new int[32];
        int[] iArr4 = new int[32];
        int[] iArr5 = new int[32];
        int[] iArr6 = new int[32];
        int[] iArr7 = new int[32];
        int[] iArr8 = new int[32];
        int[] iArr9 = new int[32];
        int[] iArr10 = new int[32];
        int[] iArr11 = new int[32];
        int[] iArr12 = new int[32];
        square(iArr3, 0, iArr2, i2);
        square(iArr12, 0, iArr3, 0);
        square(iArr11, 0, iArr12, 0);
        mult(iArr4, 0, iArr11, 0, iArr2, i2);
        mult(iArr5, 0, iArr4, 0, iArr3, 0);
        square(iArr11, 0, iArr5, 0);
        mult(iArr6, 0, iArr11, 0, iArr4, 0);
        square(iArr11, 0, iArr6, 0);
        square(iArr12, 0, iArr11, 0);
        square(iArr11, 0, iArr12, 0);
        square(iArr12, 0, iArr11, 0);
        square(iArr11, 0, iArr12, 0);
        mult(iArr7, 0, iArr11, 0, iArr6, 0);
        square(iArr11, 0, iArr7, 0);
        square(iArr12, 0, iArr11, 0);
        int i3 = 2;
        while (true) {
            int i4 = i3;
            if (i4 >= 10) {
                break;
            }
            square(iArr11, 0, iArr12, 0);
            square(iArr12, 0, iArr11, 0);
            i3 = i4 + 2;
        }
        mult(iArr8, 0, iArr12, 0, iArr7, 0);
        square(iArr11, 0, iArr8, 0);
        square(iArr12, 0, iArr11, 0);
        int i5 = 2;
        while (true) {
            int i6 = i5;
            if (i6 >= 20) {
                break;
            }
            square(iArr11, 0, iArr12, 0);
            square(iArr12, 0, iArr11, 0);
            i5 = i6 + 2;
        }
        mult(iArr11, 0, iArr12, 0, iArr8, 0);
        square(iArr12, 0, iArr11, 0);
        square(iArr11, 0, iArr12, 0);
        int i7 = 2;
        while (true) {
            int i8 = i7;
            if (i8 >= 10) {
                break;
            }
            square(iArr12, 0, iArr11, 0);
            square(iArr11, 0, iArr12, 0);
            i7 = i8 + 2;
        }
        mult(iArr9, 0, iArr11, 0, iArr7, 0);
        square(iArr11, 0, iArr9, 0);
        square(iArr12, 0, iArr11, 0);
        int i9 = 2;
        while (true) {
            int i10 = i9;
            if (i10 >= 50) {
                break;
            }
            square(iArr11, 0, iArr12, 0);
            square(iArr12, 0, iArr11, 0);
            i9 = i10 + 2;
        }
        mult(iArr10, 0, iArr12, 0, iArr9, 0);
        square(iArr12, 0, iArr10, 0);
        square(iArr11, 0, iArr12, 0);
        int i11 = 2;
        while (true) {
            int i12 = i11;
            if (i12 >= 100) {
                break;
            }
            square(iArr12, 0, iArr11, 0);
            square(iArr11, 0, iArr12, 0);
            i11 = i12 + 2;
        }
        mult(iArr12, 0, iArr11, 0, iArr10, 0);
        square(iArr11, 0, iArr12, 0);
        square(iArr12, 0, iArr11, 0);
        int i13 = 2;
        while (true) {
            int i14 = i13;
            if (i14 >= 50) {
                mult(iArr11, 0, iArr12, 0, iArr9, 0);
                square(iArr12, 0, iArr11, 0);
                square(iArr11, 0, iArr12, 0);
                square(iArr12, 0, iArr11, 0);
                square(iArr11, 0, iArr12, 0);
                square(iArr12, 0, iArr11, 0);
                mult(iArr, i, iArr12, 0, iArr5, 0);
                return;
            }
            square(iArr11, 0, iArr12, 0);
            square(iArr12, 0, iArr11, 0);
            i13 = i14 + 2;
        }
    }

    static void select(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 64) {
                return;
            }
            int i4 = (iArr3[i3] ^ iArr4[i3]) & (i - 1);
            iArr[i3] = iArr4[i3] ^ i4;
            iArr2[i3] = i4 ^ iArr3[i3];
            i2 = i3 + 1;
        }
    }

    static void square(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= 32) {
                squeeze(iArr, i);
                return;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < i4 - i6; i6++) {
                i5 += iArr2[i2 + i6] * iArr2[(i2 + i4) - i6];
            }
            int i7 = i4 + 1;
            int i8 = i5;
            int i9 = i7;
            while (true) {
                int i10 = i9;
                if (i10 >= (i4 + 32) - i10) {
                    break;
                }
                i8 += iArr2[i2 + i10] * 38 * iArr2[((i2 + i4) + 32) - i10];
                i9 = i10 + 1;
            }
            int i11 = i8 * 2;
            int i12 = i11;
            if ((i4 & 1) == 0) {
                int i13 = (i4 / 2) + i2;
                int i14 = iArr2[i13];
                int i15 = iArr2[i13];
                int i16 = i13 + 16;
                i12 = i11 + (i14 * i15) + (iArr2[i16] * 38 * iArr2[i16]);
            }
            iArr[i4 + i] = i12;
            i3 = i7;
        }
    }

    static void squeeze(int[] iArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 31; i3++) {
            int i4 = i + i3;
            int i5 = i2 + iArr[i4];
            iArr[i4] = i5 & 255;
            i2 = i5 >>> 8;
        }
        int i6 = i + 31;
        int i7 = i2 + iArr[i6];
        iArr[i6] = i7 & 127;
        int i8 = (i7 >>> 7) * 19;
        int i9 = 0;
        while (true) {
            int i10 = i9;
            if (i10 >= 31) {
                iArr[i6] = i8 + iArr[i6];
                return;
            }
            int i11 = i + i10;
            int i12 = i8 + iArr[i11];
            iArr[i11] = i12 & 255;
            i8 = i12 >>> 8;
            i9 = i10 + 1;
        }
    }

    static void sub(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        int i4 = 218;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= 31) {
                iArr[i + 31] = i4 + (iArr2[i2 + 31] - iArr3[i3 + 31]);
                return;
            }
            int i7 = i4 + ((iArr2[i2 + i6] + 65280) - iArr3[i3 + i6]);
            iArr[i + i6] = i7 & 255;
            i4 = i7 >>> 8;
            i5 = i6 + 1;
        }
    }
}
