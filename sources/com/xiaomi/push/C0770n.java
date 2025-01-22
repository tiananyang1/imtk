package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.xiaomi.push.n */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/n.class */
public class C0770n {

    /* renamed from: a */
    private static int f2357a = 0;

    /* renamed from: a */
    private static Map<String, EnumC0774q> f2358a;

    /* renamed from: b */
    private static int f2359b = -1;

    /* renamed from: c */
    private static int f2360c = -1;

    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0077  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int m2398a() {
        /*
            java.lang.Class<com.xiaomi.push.n> r0 = com.xiaomi.push.C0770n.class
            monitor-enter(r0)
            int r0 = com.xiaomi.push.C0770n.f2357a     // Catch: java.lang.Throwable -> L61
            r3 = r0
            r0 = r3
            if (r0 != 0) goto L58
            java.lang.String r0 = "ro.miui.ui.version.code"
            java.lang.String r0 = m2401a(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L61
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L61
            r5 = r0
            r0 = 1
            r4 = r0
            r0 = r5
            if (r0 == 0) goto L6c
            java.lang.String r0 = "ro.miui.ui.version.name"
            java.lang.String r0 = m2401a(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L61
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L61
            if (r0 != 0) goto L67
            goto L6c
        L28:
            r0 = r3
            com.xiaomi.push.C0770n.f2357a = r0     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L61
            goto L3a
        L2f:
            r6 = move-exception
            java.lang.String r0 = "get isMIUI failed"
            r1 = r6
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m71a(r0, r1)     // Catch: java.lang.Throwable -> L61
            r0 = 0
            com.xiaomi.push.C0770n.f2357a = r0     // Catch: java.lang.Throwable -> L61
        L3a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L61
            r1 = r0
            r1.<init>()     // Catch: java.lang.Throwable -> L61
            r6 = r0
            r0 = r6
            java.lang.String r1 = "isMIUI's value is: "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L61
            r0 = r6
            int r1 = com.xiaomi.push.C0770n.f2357a     // Catch: java.lang.Throwable -> L61
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L61
            r0 = r6
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L61
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m73b(r0)     // Catch: java.lang.Throwable -> L61
        L58:
            int r0 = com.xiaomi.push.C0770n.f2357a     // Catch: java.lang.Throwable -> L61 java.lang.Throwable -> L61
            r3 = r0
            java.lang.Class<com.xiaomi.push.n> r0 = com.xiaomi.push.C0770n.class
            monitor-exit(r0)
            r0 = r3
            return r0
        L61:
            r6 = move-exception
            java.lang.Class<com.xiaomi.push.n> r0 = com.xiaomi.push.C0770n.class
            monitor-exit(r0)
            r0 = r6
            throw r0
        L67:
            r0 = 0
            r3 = r0
            goto L6e
        L6c:
            r0 = 1
            r3 = r0
        L6e:
            r0 = r3
            if (r0 == 0) goto L77
            r0 = r4
            r3 = r0
            goto L28
        L77:
            r0 = 2
            r3 = r0
            goto L28
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0770n.m2398a():int");
    }

    /* renamed from: a */
    public static EnumC0774q m2399a(String str) {
        EnumC0774q m2404b = m2404b(str);
        EnumC0774q enumC0774q = m2404b;
        if (m2404b == null) {
            enumC0774q = EnumC0774q.Global;
        }
        return enumC0774q;
    }

    /* renamed from: a */
    public static String m2400a() {
        synchronized (C0770n.class) {
            try {
                int m2848a = C0880v.m2848a();
                return (!m2403a() || m2848a <= 0) ? "" : m2848a < 2 ? "alpha" : m2848a < 3 ? "development" : "stable";
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static String m2401a(String str) {
        try {
            try {
                return (String) C0504at.m497a("android.os.SystemProperties", "get", str, "");
            } catch (Exception e) {
                AbstractC0407b.m72a(e);
                return null;
            }
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m2402a() {
        if (f2358a != null) {
            return;
        }
        f2358a = new HashMap();
        f2358a.put("CN", EnumC0774q.China);
        f2358a.put("FI", EnumC0774q.Europe);
        f2358a.put("SE", EnumC0774q.Europe);
        f2358a.put("NO", EnumC0774q.Europe);
        f2358a.put("FO", EnumC0774q.Europe);
        f2358a.put("EE", EnumC0774q.Europe);
        f2358a.put("LV", EnumC0774q.Europe);
        f2358a.put("LT", EnumC0774q.Europe);
        f2358a.put("BY", EnumC0774q.Europe);
        f2358a.put("MD", EnumC0774q.Europe);
        f2358a.put("UA", EnumC0774q.Europe);
        f2358a.put("PL", EnumC0774q.Europe);
        f2358a.put("CZ", EnumC0774q.Europe);
        f2358a.put("SK", EnumC0774q.Europe);
        f2358a.put("HU", EnumC0774q.Europe);
        f2358a.put("DE", EnumC0774q.Europe);
        f2358a.put("AT", EnumC0774q.Europe);
        f2358a.put("CH", EnumC0774q.Europe);
        f2358a.put("LI", EnumC0774q.Europe);
        f2358a.put("GB", EnumC0774q.Europe);
        f2358a.put("IE", EnumC0774q.Europe);
        f2358a.put("NL", EnumC0774q.Europe);
        f2358a.put("BE", EnumC0774q.Europe);
        f2358a.put("LU", EnumC0774q.Europe);
        f2358a.put("FR", EnumC0774q.Europe);
        f2358a.put("RO", EnumC0774q.Europe);
        f2358a.put("BG", EnumC0774q.Europe);
        f2358a.put("RS", EnumC0774q.Europe);
        f2358a.put("MK", EnumC0774q.Europe);
        f2358a.put("AL", EnumC0774q.Europe);
        f2358a.put("GR", EnumC0774q.Europe);
        f2358a.put("SI", EnumC0774q.Europe);
        f2358a.put("HR", EnumC0774q.Europe);
        f2358a.put("IT", EnumC0774q.Europe);
        f2358a.put("SM", EnumC0774q.Europe);
        f2358a.put("MT", EnumC0774q.Europe);
        f2358a.put("ES", EnumC0774q.Europe);
        f2358a.put("PT", EnumC0774q.Europe);
        f2358a.put("AD", EnumC0774q.Europe);
        f2358a.put("CY", EnumC0774q.Europe);
        f2358a.put("DK", EnumC0774q.Europe);
        f2358a.put("RU", EnumC0774q.Russia);
        f2358a.put("IN", EnumC0774q.India);
    }

    /* renamed from: a */
    public static boolean m2403a() {
        boolean z;
        synchronized (C0770n.class) {
            try {
                z = true;
                if (m2398a() != 1) {
                    z = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    /* renamed from: b */
    private static EnumC0774q m2404b(String str) {
        m2402a();
        return f2358a.get(str.toUpperCase());
    }

    /* renamed from: b */
    public static String m2405b() {
        String m2847a = C0879u.m2847a("ro.miui.region", "");
        String str = m2847a;
        if (TextUtils.isEmpty(m2847a)) {
            str = C0879u.m2847a("ro.product.locale.region", "");
        }
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            str2 = C0879u.m2847a("persist.sys.country", "");
        }
        String str3 = str2;
        if (TextUtils.isEmpty(str2)) {
            str3 = Locale.getDefault().getCountry();
        }
        return str3;
    }

    /* renamed from: b */
    public static boolean m2406b() {
        boolean z;
        synchronized (C0770n.class) {
            try {
                z = m2398a() == 2;
            } finally {
            }
        }
        return z;
    }

    /* renamed from: c */
    public static boolean m2407c() {
        if (f2359b < 0) {
            Object m497a = C0504at.m497a("miui.external.SdkHelper", "isMiuiSystem", new Object[0]);
            f2359b = 0;
            if (m497a != null && (m497a instanceof Boolean) && !((Boolean) Boolean.class.cast(m497a)).booleanValue()) {
                f2359b = 1;
            }
        }
        return f2359b > 0;
    }

    /* renamed from: d */
    public static boolean m2408d() {
        if (f2360c < 0) {
            if (EnumC0774q.Europe.name().equalsIgnoreCase(m2399a(m2405b()).name()) && m2403a()) {
                f2360c = 1;
            } else {
                f2360c = 0;
            }
        }
        return f2360c > 0;
    }
}
