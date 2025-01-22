package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0558ct;
import com.xiaomi.push.C0709ii;
import com.xiaomi.push.C0878t;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.j */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/j.class */
public class C0859j {
    /* renamed from: a */
    public static void m2715a(Context context, String str) {
        ArrayList<C0709ii> m2701a = C0856g.m2692a(context).m2701a(str);
        if (m2701a == null || m2701a.size() < 1) {
            return;
        }
        if (C0856g.m2692a(context).m2702b(str) == 0) {
            AbstractC0407b.m70a("appIsUninstalled. failed to delete geofencing with package name. name:" + str);
        }
        Iterator<C0709ii> it = m2701a.iterator();
        while (it.hasNext()) {
            C0709ii next = it.next();
            if (next == null) {
                AbstractC0407b.m70a("appIsUninstalled. failed to find geofence with package name. name:" + str);
                return;
            }
            m2717a(next.m1712a(), context);
            if (C0858i.m2709a(context).m2714b(next.m1712a()) == 0) {
                AbstractC0407b.m70a("appIsUninstalled. failed to delete geoMessage with package name. name:" + str + ", geoId:" + next.m1712a());
            }
        }
    }

    /* renamed from: a */
    public static void m2716a(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putBoolean("geo_switch", z);
        C0878t.m2846a(edit);
    }

    /* renamed from: a */
    public static void m2717a(String str, Context context) {
        new C0558ct(context).m814a(context, "com.xiaomi.xmsf", str);
    }

    /* renamed from: a */
    public static boolean m2718a(Context context) {
        return m2719a(context, "com.xiaomi.metoknlp", 6);
    }

    /* renamed from: a */
    public static boolean m2719a(Context context, String str, int i) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null && packageInfo.versionCode >= i;
    }

    /* renamed from: b */
    public static boolean m2720b(Context context) {
        boolean m2719a = m2719a(context, "com.xiaomi.xmsf", 106);
        boolean m2719a2 = m2719a(context, "com.xiaomi.metok", 20);
        boolean m2719a3 = m2719a(context, "com.xiaomi.metoknlp", 6);
        if (m2719a) {
            return m2719a2 || m2719a3;
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m2721c(Context context) {
        return TextUtils.equals(context.getPackageName(), "com.xiaomi.xmsf");
    }

    /* renamed from: d */
    public static boolean m2722d(Context context) {
        return m2718a(context);
    }

    /* renamed from: e */
    public static boolean m2723e(Context context) {
        return context.getSharedPreferences("mipush_extra", 4).getBoolean("geo_switch", false);
    }
}
