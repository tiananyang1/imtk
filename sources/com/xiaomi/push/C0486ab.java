package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ab */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ab.class */
public class C0486ab {

    /* renamed from: a */
    private static int f403a;

    /* renamed from: a */
    public static final String f404a;

    /* renamed from: a */
    public static final boolean f405a;

    /* renamed from: b */
    public static final boolean f406b;

    /* renamed from: c */
    public static final boolean f407c;

    /* renamed from: d */
    public static final boolean f408d;

    /* renamed from: e */
    public static boolean f409e;

    /* renamed from: f */
    public static final boolean f410f;

    /* renamed from: g */
    public static final boolean f411g;

    static {
        int i;
        f404a = C0489ae.f413a ? "ONEBOX" : "@SHIP.TO.2A2FE0D7@";
        f405a = f404a.contains("2A2FE0D7");
        f406b = f405a || "DEBUG".equalsIgnoreCase(f404a);
        f407c = "LOGABLE".equalsIgnoreCase(f404a);
        f408d = f404a.contains("YY");
        f409e = f404a.equalsIgnoreCase("TEST");
        f410f = "BETA".equalsIgnoreCase(f404a);
        String str = f404a;
        boolean z = false;
        if (str != null) {
            z = false;
            if (str.startsWith("RC")) {
                z = true;
            }
        }
        f411g = z;
        f403a = 1;
        if (f404a.equalsIgnoreCase("SANDBOX")) {
            i = 2;
        } else {
            if (!f404a.equalsIgnoreCase("ONEBOX")) {
                f403a = 1;
                return;
            }
            i = 3;
        }
        f403a = i;
    }

    /* renamed from: a */
    public static int m424a() {
        return f403a;
    }

    /* renamed from: a */
    public static void m425a(int i) {
        f403a = i;
    }

    /* renamed from: a */
    public static boolean m426a() {
        return f403a == 2;
    }

    /* renamed from: b */
    public static boolean m427b() {
        return f403a == 3;
    }
}
