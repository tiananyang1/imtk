package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* renamed from: com.xiaomi.push.v */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/v.class */
public class C0880v {

    /* renamed from: a */
    private static Context f2732a;

    /* renamed from: a */
    public static int m2848a() {
        try {
            Class<?> cls = Class.forName("miui.os.Build");
            if (cls.getField("IS_STABLE_VERSION").getBoolean(null)) {
                return 3;
            }
            return cls.getField("IS_DEVELOPMENT_VERSION").getBoolean(null) ? 2 : 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /* renamed from: a */
    public static Context m2849a() {
        return f2732a;
    }

    /* renamed from: a */
    public static void m2850a(Context context) {
        f2732a = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    /* renamed from: a */
    public static boolean m2851a() {
        return TextUtils.equals((String) C0504at.m497a("android.os.SystemProperties", "get", "sys.boot_completed"), "1");
    }

    /* renamed from: a */
    public static boolean m2852a(Context context) {
        boolean z = false;
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m2853b() {
        try {
            return Class.forName("miui.os.Build").getField("IS_GLOBAL_BUILD").getBoolean(false);
        } catch (ClassNotFoundException e) {
            AbstractC0407b.m75d("miui.os.Build ClassNotFound");
            return false;
        } catch (Exception e2) {
            AbstractC0407b.m72a(e2);
            return false;
        }
    }
}
