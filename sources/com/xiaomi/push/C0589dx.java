package com.xiaomi.push;

/* renamed from: com.xiaomi.push.dx */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dx.class */
public class C0589dx {
    /* renamed from: a */
    private static void m956a(byte[] bArr) {
        if (bArr.length >= 2) {
            bArr[0] = 99;
            bArr[1] = 100;
        }
    }

    /* renamed from: a */
    public static byte[] m957a(String str, byte[] bArr) {
        byte[] m510a = C0506av.m510a(str);
        try {
            m956a(m510a);
            return C0700i.m1628a(m510a, bArr);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m958b(String str, byte[] bArr) {
        byte[] m510a = C0506av.m510a(str);
        try {
            m956a(m510a);
            return C0700i.m1629b(m510a, bArr);
        } catch (Exception e) {
            return null;
        }
    }
}
