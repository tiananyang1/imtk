package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MIPushNotificationHelper4Hybrid;
import com.xiaomi.push.C0729jb;
import java.util.Map;

/* renamed from: com.xiaomi.push.service.bo */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bo.class */
public class C0836bo {

    /* renamed from: a */
    public static Runnable f2613a;

    /* renamed from: a */
    private static String m2670a(Context context, String str) {
        return context.getSharedPreferences("typed_shield_pref", 4).getString(str + "_title", str);
    }

    /* renamed from: a */
    public static String m2671a(C0729jb c0729jb) {
        Map<String, String> m1826a = c0729jb.m2022a().m1826a();
        if (m1826a == null) {
            return null;
        }
        return m1826a.get("__typed_shield_type");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(19)
    /* renamed from: a */
    public static void m2672a(Context context, C0729jb c0729jb, Notification notification) {
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        String m2671a = m2671a(c0729jb);
        if (TextUtils.isEmpty(m2671a) || !"com.xiaomi.xmsf".equals(C0803ai.m2521a(c0729jb))) {
            return;
        }
        Bundle bundle = notification.extras;
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString(MIPushNotificationHelper4Hybrid.KEY_CATEGORY, m2671a);
        bundle2.putString(MIPushNotificationHelper4Hybrid.KEY_SUBST_NAME, m2670a(context, m2671a));
        notification.extras = bundle2;
    }

    /* renamed from: a */
    public static boolean m2673a(Context context, C0729jb c0729jb) {
        Runnable runnable;
        if (!"com.xiaomi.xmsf".equals(C0803ai.m2521a(c0729jb))) {
            return false;
        }
        String m2671a = m2671a(c0729jb);
        if (TextUtils.isEmpty(m2671a)) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("typed_shield_pref", 4);
        if (!sharedPreferences.contains(m2671a + "_shield") && (runnable = f2613a) != null) {
            runnable.run();
        }
        return sharedPreferences.getBoolean(m2671a + "_shield", true);
    }
}
