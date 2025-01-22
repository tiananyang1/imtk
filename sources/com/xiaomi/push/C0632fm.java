package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.XMJobService;

/* renamed from: com.xiaomi.push.fm */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fm.class */
public final class C0632fm {

    /* renamed from: a */
    private static a f914a;

    /* renamed from: a */
    private static final String f915a = XMJobService.class.getCanonicalName();

    /* renamed from: a */
    private static int f913a = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.fm$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fm$a.class */
    public interface a {
        /* renamed from: a */
        void mo1273a();

        /* renamed from: a */
        void mo1274a(boolean z);

        /* renamed from: a */
        boolean mo1275a();
    }

    /* renamed from: a */
    public static void m1268a() {
        synchronized (C0632fm.class) {
            try {
                if (f914a == null) {
                    return;
                }
                f914a.mo1273a();
            } finally {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a0, code lost:            if (com.xiaomi.push.C0632fm.f915a.equals(java.lang.Class.forName(r0.name).getSuperclass().getCanonicalName()) != false) goto L21;     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m1269a(android.content.Context r4) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0632fm.m1269a(android.content.Context):void");
    }

    /* renamed from: a */
    public static void m1270a(Context context, int i) {
        synchronized (C0632fm.class) {
            try {
                int i2 = f913a;
                if (!"com.xiaomi.xmsf".equals(context.getPackageName())) {
                    if (i == 2) {
                        f913a = 2;
                    } else {
                        f913a = 0;
                    }
                }
                if (i2 != f913a && f913a == 2) {
                    m1268a();
                    f914a = new C0635fp(context);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static void m1271a(boolean z) {
        synchronized (C0632fm.class) {
            try {
                if (f914a == null) {
                    AbstractC0407b.m70a("timer is not initialized");
                } else {
                    f914a.mo1274a(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static boolean m1272a() {
        synchronized (C0632fm.class) {
            try {
                if (f914a == null) {
                    return false;
                }
                return f914a.mo1275a();
            } finally {
            }
        }
    }
}
