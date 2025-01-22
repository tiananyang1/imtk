package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ac */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ac.class */
public class C0487ac {
    /* renamed from: a */
    public static int m428a(byte[] bArr) {
        if (bArr.length != 4) {
            throw new IllegalArgumentException("the length of bytes must be 4");
        }
        return (bArr[3] & 255) | 0 | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    /* renamed from: a */
    public static byte[] m429a(int i) {
        return new byte[]{(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i};
    }
}
