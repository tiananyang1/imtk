package com.xiaomi.push;

import android.content.Context;

/* renamed from: com.xiaomi.push.af */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/af.class */
public class C0490af {

    /* renamed from: a */
    static final char[] f414a = "0123456789ABCDEF".toCharArray();

    /* renamed from: a */
    public static String m431a(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(i2 * 2);
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                return sb.toString();
            }
            int i5 = bArr[i + i4] & 255;
            sb.append(f414a[i5 >> 4]);
            sb.append(f414a[i5 & 15]);
            i3 = i4 + 1;
        }
    }

    /* renamed from: a */
    public static boolean m432a(Context context) {
        return C0489ae.f413a;
    }
}
