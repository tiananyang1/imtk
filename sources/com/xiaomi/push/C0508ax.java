package com.xiaomi.push;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.xiaomi.push.ax */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ax.class */
public class C0508ax {
    /* renamed from: a */
    private static String m517a(byte b) {
        int i = (b & Byte.MAX_VALUE) + (b < 0 ? 128 : 0);
        StringBuilder sb = new StringBuilder();
        sb.append(i < 16 ? "0" : "");
        sb.append(Integer.toHexString(i).toLowerCase());
        return sb.toString();
    }

    /* renamed from: a */
    public static String m518a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            StringBuffer stringBuffer = new StringBuffer();
            messageDigest.update(str.getBytes(), 0, str.length());
            for (byte b : messageDigest.digest()) {
                stringBuffer.append(m517a(b));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m519b(String str) {
        return m518a(str).subSequence(8, 24).toString();
    }
}
