package com.sensorsdata.analytics.android.sdk.util;

import com.sensorsdata.analytics.android.sdk.SALog;
import java.io.UnsupportedEncodingException;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/util/Base64Coder.class */
public class Base64Coder {
    public static final String CHARSET_UTF8 = "UTF-8";
    private static char[] map1 = new char[64];
    private static byte[] map2 = new byte[128];

    static {
        int i;
        int i2;
        char c = 'A';
        int i3 = 0;
        while (true) {
            i = i3;
            if (c > 'Z') {
                break;
            }
            map1[i] = c;
            c = (char) (c + 1);
            i3 = i + 1;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            map1[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            map1[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = map1;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        int i4 = 0;
        while (true) {
            int i5 = i4;
            byte[] bArr = map2;
            if (i5 >= bArr.length) {
                break;
            }
            bArr[i5] = -1;
            i4 = i5 + 1;
        }
        for (i2 = 0; i2 < 64; i2++) {
            map2[map1[i2]] = (byte) i2;
        }
    }

    public static byte[] decode(String str) {
        return decode(str.toCharArray());
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0128, code lost:            throw new java.lang.IllegalArgumentException("Illegal character in Base64 encoded data.");     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] decode(char[] r6) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.util.Base64Coder.decode(char[]):byte[]");
    }

    public static String decodeString(String str) {
        return new String(decode(str));
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, bArr.length);
    }

    public static char[] encode(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4 = ((i * 4) + 2) / 3;
        char[] cArr = new char[((i + 2) / 3) * 4];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i5 >= i) {
                return cArr;
            }
            int i8 = i5 + 1;
            int i9 = bArr[i5] & 255;
            if (i8 < i) {
                i5 = i8 + 1;
                i2 = bArr[i8] & 255;
            } else {
                i5 = i8;
                i2 = 0;
            }
            if (i5 < i) {
                i3 = bArr[i5] & 255;
                i5++;
            } else {
                i3 = 0;
            }
            int i10 = i7 + 1;
            char[] cArr2 = map1;
            cArr[i7] = cArr2[i9 >>> 2];
            int i11 = i10 + 1;
            cArr[i10] = cArr2[((i9 & 3) << 4) | (i2 >>> 4)];
            cArr[i11] = i11 < i4 ? cArr2[((i2 & 15) << 2) | (i3 >>> 6)] : '=';
            int i12 = i11 + 1;
            char c = '=';
            if (i12 < i4) {
                c = map1[i3 & 63];
            }
            cArr[i12] = c;
            i6 = i12 + 1;
        }
    }

    public static String encodeString(String str) {
        try {
            return new String(encode(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            SALog.printStackTrace(e);
            return "";
        }
    }
}
