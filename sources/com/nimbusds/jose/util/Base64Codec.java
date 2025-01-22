package com.nimbusds.jose.util;

import com.sun.jna.Function;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/Base64Codec.class */
final class Base64Codec {

    /* renamed from: CA */
    private static final char[] f120CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final char[] CA_URL_SAFE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();

    /* renamed from: IA */
    private static final int[] f121IA = new int[Function.MAX_NARGS];
    private static final int[] IA_URL_SAFE = new int[Function.MAX_NARGS];

    static {
        Arrays.fill(f121IA, -1);
        int length = f120CA.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                break;
            }
            f121IA[f120CA[i2]] = i2;
            i = i2 + 1;
        }
        f121IA[61] = 0;
        Arrays.fill(IA_URL_SAFE, -1);
        int length2 = CA_URL_SAFE.length;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= length2) {
                IA_URL_SAFE[61] = 0;
                return;
            } else {
                IA_URL_SAFE[CA_URL_SAFE[i4]] = i4;
                i3 = i4 + 1;
            }
        }
    }

    Base64Codec() {
    }

    public static int computeEncodedLength(int i, boolean z) {
        if (i == 0) {
            return 0;
        }
        if (!z) {
            return (((i - 1) / 3) + 1) << 2;
        }
        int i2 = (i / 3) << 2;
        int i3 = i % 3;
        return i3 == 0 ? i2 : i2 + i3 + 1;
    }

    public static int countIllegalChars(String str) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i >= str.length()) {
                return i3;
            }
            char charAt = str.charAt(i);
            int i4 = i3;
            if (f121IA[charAt] == -1) {
                i4 = i3;
                if (IA_URL_SAFE[charAt] == -1) {
                    i4 = i3 + 1;
                }
            }
            i++;
            i2 = i4;
        }
    }

    public static byte[] decode(String str) {
        if (str == null || str.isEmpty()) {
            return new byte[0];
        }
        String normalizeEncodedString = normalizeEncodedString(str);
        int length = normalizeEncodedString.length();
        int countIllegalChars = length - countIllegalChars(normalizeEncodedString);
        if (countIllegalChars % 4 != 0) {
            return new byte[0];
        }
        int i = 0;
        while (length > 1) {
            int i2 = length - 1;
            if (f121IA[normalizeEncodedString.charAt(i2)] > 0) {
                break;
            }
            length = i2;
            if (normalizeEncodedString.charAt(i2) == '=') {
                i++;
                length = i2;
            }
        }
        int i3 = ((countIllegalChars * 6) >> 3) - i;
        byte[] bArr = new byte[i3];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            int i6 = 0;
            int i7 = 0;
            while (i6 < 4) {
                int i8 = f121IA[normalizeEncodedString.charAt(i5)];
                if (i8 >= 0) {
                    i7 = (i8 << (18 - (i6 * 6))) | i7;
                } else {
                    i6--;
                }
                i6++;
                i5++;
            }
            int i9 = i4 + 1;
            bArr[i4] = (byte) (i7 >> 16);
            int i10 = i9;
            if (i9 < i3) {
                int i11 = i9 + 1;
                bArr[i9] = (byte) (i7 >> 8);
                i10 = i11;
                if (i11 < i3) {
                    i10 = i11 + 1;
                    bArr[i11] = (byte) i7;
                }
            }
            i4 = i10;
        }
        return bArr;
    }

    public static char[] encodeToChar(byte[] bArr, boolean z) {
        int length = bArr != null ? bArr.length : 0;
        if (length == 0) {
            return new char[0];
        }
        int i = (length / 3) * 3;
        int computeEncodedLength = computeEncodedLength(length, z);
        char[] cArr = new char[computeEncodedLength];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            int i6 = ((bArr[i2] & 255) << 16) | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
            if (z) {
                int i7 = i3 + 1;
                char[] cArr2 = CA_URL_SAFE;
                cArr[i3] = cArr2[(i6 >>> 18) & 63];
                int i8 = i7 + 1;
                cArr[i7] = cArr2[(i6 >>> 12) & 63];
                int i9 = i8 + 1;
                cArr[i8] = cArr2[(i6 >>> 6) & 63];
                i3 = i9 + 1;
                cArr[i9] = cArr2[i6 & 63];
            } else {
                int i10 = i3 + 1;
                char[] cArr3 = f120CA;
                cArr[i3] = cArr3[(i6 >>> 18) & 63];
                int i11 = i10 + 1;
                cArr[i10] = cArr3[(i6 >>> 12) & 63];
                int i12 = i11 + 1;
                cArr[i11] = cArr3[(i6 >>> 6) & 63];
                i3 = i12 + 1;
                cArr[i12] = cArr3[i6 & 63];
            }
            i2 = i5 + 1;
        }
        int i13 = length - i;
        if (i13 > 0) {
            byte b = bArr[i];
            int i14 = 0;
            if (i13 == 2) {
                i14 = (bArr[length - 1] & 255) << 2;
            }
            int i15 = ((b & 255) << 10) | i14;
            if (z) {
                if (i13 != 2) {
                    char[] cArr4 = CA_URL_SAFE;
                    cArr[computeEncodedLength - 2] = cArr4[i15 >> 12];
                    cArr[computeEncodedLength - 1] = cArr4[(i15 >>> 6) & 63];
                    return cArr;
                }
                char[] cArr5 = CA_URL_SAFE;
                cArr[computeEncodedLength - 3] = cArr5[i15 >> 12];
                cArr[computeEncodedLength - 2] = cArr5[(i15 >>> 6) & 63];
                cArr[computeEncodedLength - 1] = cArr5[i15 & 63];
                return cArr;
            }
            char[] cArr6 = f120CA;
            cArr[computeEncodedLength - 4] = cArr6[i15 >> 12];
            cArr[computeEncodedLength - 3] = cArr6[(i15 >>> 6) & 63];
            cArr[computeEncodedLength - 2] = i13 == 2 ? cArr6[i15 & 63] : '=';
            cArr[computeEncodedLength - 1] = '=';
        }
        return cArr;
    }

    public static String encodeToString(byte[] bArr, boolean z) {
        return new String(encodeToChar(bArr, z));
    }

    public static String normalizeEncodedString(String str) {
        int length = str.length();
        int countIllegalChars = (length - countIllegalChars(str)) % 4;
        int i = countIllegalChars == 0 ? 0 : 4 - countIllegalChars;
        char[] cArr = new char[length + i];
        str.getChars(0, length, cArr, 0);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                break;
            }
            cArr[length + i3] = '=';
            i2 = i3 + 1;
        }
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= length) {
                return new String(cArr);
            }
            if (cArr[i5] == '_') {
                cArr[i5] = '/';
            } else if (cArr[i5] == '-') {
                cArr[i5] = '+';
            }
            i4 = i5 + 1;
        }
    }
}
