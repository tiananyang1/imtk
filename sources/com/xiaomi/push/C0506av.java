package com.xiaomi.push;

/* renamed from: com.xiaomi.push.av */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/av.class */
public class C0506av {

    /* renamed from: a */
    private static byte[] f458a;

    /* renamed from: a */
    private static final String f457a = System.getProperty("line.separator");

    /* renamed from: a */
    private static char[] f459a = new char[64];

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
            f459a[i] = c;
            c = (char) (c + 1);
            i3 = i + 1;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            f459a[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            f459a[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = f459a;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        f458a = new byte[128];
        int i4 = 0;
        while (true) {
            int i5 = i4;
            byte[] bArr = f458a;
            if (i5 >= bArr.length) {
                break;
            }
            bArr[i5] = -1;
            i4 = i5 + 1;
        }
        for (i2 = 0; i2 < 64; i2++) {
            f458a[f459a[i2]] = (byte) i2;
        }
    }

    /* renamed from: a */
    public static byte[] m510a(String str) {
        return m511a(str.toCharArray());
    }

    /* renamed from: a */
    public static byte[] m511a(char[] cArr) {
        return m512a(cArr, 0, cArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x012f, code lost:            throw new java.lang.IllegalArgumentException("Illegal character in Base64 encoded data.");     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] m512a(char[] r6, int r7, int r8) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0506av.m512a(char[], int, int):byte[]");
    }

    /* renamed from: a */
    public static char[] m513a(byte[] bArr) {
        return m514a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static char[] m514a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5 = ((i2 * 4) + 2) / 3;
        char[] cArr = new char[((i2 + 2) / 3) * 4];
        int i6 = i2 + i;
        int i7 = 0;
        while (true) {
            int i8 = i7;
            if (i >= i6) {
                return cArr;
            }
            int i9 = i + 1;
            int i10 = bArr[i] & 255;
            if (i9 < i6) {
                i = i9 + 1;
                i3 = bArr[i9] & 255;
            } else {
                i = i9;
                i3 = 0;
            }
            if (i < i6) {
                i4 = bArr[i] & 255;
                i++;
            } else {
                i4 = 0;
            }
            int i11 = i8 + 1;
            char[] cArr2 = f459a;
            cArr[i8] = cArr2[i10 >>> 2];
            int i12 = i11 + 1;
            cArr[i11] = cArr2[((i10 & 3) << 4) | (i3 >>> 4)];
            cArr[i12] = i12 < i5 ? cArr2[((i3 & 15) << 2) | (i4 >>> 6)] : '=';
            int i13 = i12 + 1;
            char c = '=';
            if (i13 < i5) {
                c = f459a[i4 & 63];
            }
            cArr[i13] = c;
            i7 = i13 + 1;
        }
    }
}
