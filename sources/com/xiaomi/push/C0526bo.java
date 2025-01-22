package com.xiaomi.push;

/* renamed from: com.xiaomi.push.bo */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bo.class */
public class C0526bo {
    /* renamed from: a */
    private static Class m623a() {
        return Class.forName("android.os.SystemProperties");
    }

    /* renamed from: a */
    public static String m624a(String str, String str2) {
        try {
            return (String) m623a().getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
