package com.xiaomi.push;

import android.content.Context;
import android.preference.PreferenceManager;
import java.util.Map;

/* renamed from: com.xiaomi.push.p */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/p.class */
public abstract class AbstractC0772p {
    /* renamed from: a */
    public static void m2410a(Context context) {
    }

    /* renamed from: a */
    public static void m2411a(Context context, String str, boolean z) {
        m2410a(context);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(str, z).commit();
    }

    /* renamed from: a */
    public static void m2412a(Map<String, String> map, String str, String str2) {
        if (map == null || str == null || str2 == null) {
            return;
        }
        map.put(str, str2);
    }

    /* renamed from: a */
    public static boolean m2413a(Context context, String str, boolean z) {
        m2410a(context);
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, z);
    }
}
