package com.lambdaworks.codec;

import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:com/lambdaworks/codec/Base64.class */
public class Base64 {
    private static final char pad = '=';
    private static final char[] encode = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final int[] decode = new int[128];

    static {
        Arrays.fill(decode, -1);
        int i = 0;
        while (true) {
            int i2 = i;
            char[] cArr = encode;
            if (i2 >= cArr.length) {
                decode[61] = 0;
                return;
            } else {
                decode[cArr[i2]] = i2;
                i = i2 + 1;
            }
        }
    }

    public static byte[] decode(char[] cArr) {
        return decode(cArr, decode, '=');
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x013a A[LOOP:1: B:28:0x0134->B:30:0x013a, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] decode(char[] r6, int[] r7, char r8) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lambdaworks.codec.Base64.decode(char[], int[], char):byte[]");
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, encode, '=');
    }

    public static char[] encode(byte[] bArr, boolean z) {
        return encode(bArr, encode, z ? '=' : (char) 0);
    }

    public static char[] encode(byte[] bArr, char[] cArr, char c) {
        int length = bArr.length;
        int i = 0;
        if (length == 0) {
            return new char[0];
        }
        int i2 = (length / 3) * 3;
        int i3 = (((length - 1) / 3) + 1) << 2;
        int i4 = length - i2;
        int i5 = i3;
        if (c == 0) {
            i5 = i3;
            if (i4 > 0) {
                i5 = i3 - (3 - i4);
            }
        }
        char[] cArr2 = new char[i5];
        int i6 = 0;
        while (i < i2) {
            int i7 = i + 1;
            int i8 = i7 + 1;
            int i9 = ((bArr[i] & 255) << 16) | ((bArr[i7] & 255) << 8) | (bArr[i8] & 255);
            int i10 = i6 + 1;
            cArr2[i6] = cArr[(i9 >>> 18) & 63];
            int i11 = i10 + 1;
            cArr2[i10] = cArr[(i9 >>> 12) & 63];
            int i12 = i11 + 1;
            cArr2[i11] = cArr[(i9 >>> 6) & 63];
            i6 = i12 + 1;
            cArr2[i12] = cArr[i9 & 63];
            i = i8 + 1;
        }
        if (i4 > 0) {
            int i13 = (bArr[i] & 255) << 10;
            int i14 = i13;
            if (i4 == 2) {
                i14 = i13 | ((bArr[i + 1] & 255) << 2);
            }
            int i15 = i6 + 1;
            cArr2[i6] = cArr[(i14 >>> 12) & 63];
            int i16 = i15 + 1;
            cArr2[i15] = cArr[(i14 >>> 6) & 63];
            if (i4 == 2) {
                cArr2[i16] = cArr[i14 & 63];
                i16++;
            }
            if (c != 0) {
                int i17 = i16;
                if (i4 == 1) {
                    cArr2[i16] = c;
                    i17 = i16 + 1;
                }
                cArr2[i17] = c;
            }
        }
        return cArr2;
    }
}
