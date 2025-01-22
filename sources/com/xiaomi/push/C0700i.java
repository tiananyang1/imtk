package com.xiaomi.push;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.xiaomi.push.i */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/i.class */
public class C0700i {

    /* renamed from: a */
    private static final byte[] f1491a = {100, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, 52, 84, 102, 18, 32};

    /* renamed from: a */
    private static Cipher m1627a(byte[] bArr, int i) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(f1491a);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(i, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    /* renamed from: a */
    public static byte[] m1628a(byte[] bArr, byte[] bArr2) {
        return m1627a(bArr, 2).doFinal(bArr2);
    }

    /* renamed from: b */
    public static byte[] m1629b(byte[] bArr, byte[] bArr2) {
        return m1627a(bArr, 1).doFinal(bArr2);
    }
}
