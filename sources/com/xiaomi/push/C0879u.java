package com.xiaomi.push;

/* renamed from: com.xiaomi.push.u */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/u.class */
public class C0879u {
    /* renamed from: a */
    public static String m2847a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }
}
