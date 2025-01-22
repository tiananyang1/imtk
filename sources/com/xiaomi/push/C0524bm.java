package com.xiaomi.push;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;

/* renamed from: com.xiaomi.push.bm */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bm.class */
public class C0524bm {

    /* renamed from: a */
    private static Class f506a;

    /* renamed from: a */
    private static String f507a = "NLPBuild";

    /* renamed from: a */
    private static Field f508a;

    /* renamed from: a */
    private static boolean f509a;

    /* renamed from: b */
    private static Field f511b;

    /* renamed from: c */
    private static Field f513c;

    /* renamed from: d */
    private static Field f514d;

    /* renamed from: b */
    private static String f510b = Build.BRAND;

    /* renamed from: c */
    private static String f512c = Build.TYPE;

    static {
        boolean z = true;
        try {
            f506a = Class.forName("miui.os.Build");
            f508a = f506a.getField("IS_CTA_BUILD");
            f511b = f506a.getField("IS_ALPHA_BUILD");
            f513c = f506a.getField("IS_DEVELOPMENT_VERSION");
            f514d = f506a.getField("IS_STABLE_VERSION");
            z = false;
        } catch (ClassNotFoundException | NoSuchFieldException | Exception e) {
        }
        if (z) {
            f506a = null;
            f508a = null;
            f511b = null;
            f513c = null;
            f514d = null;
        }
    }

    /* renamed from: a */
    public static String m617a() {
        return "3rdROM-" + f512c;
    }

    /* renamed from: a */
    public static boolean m618a() {
        if (f509a) {
            Log.d(f507a, "brand=" + f510b);
        }
        String str = f510b;
        return str != null && str.equalsIgnoreCase("xiaomi");
    }

    /* renamed from: b */
    public static boolean m619b() {
        Class cls;
        Field field;
        if (!m618a() || (cls = f506a) == null || (field = f511b) == null) {
            return false;
        }
        try {
            boolean z = field.getBoolean(cls);
            if (f509a) {
                Log.d(f507a, "is alpha version=" + z);
            }
            return z;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m620c() {
        Class cls;
        Field field;
        if (!m618a() || (cls = f506a) == null || (field = f513c) == null) {
            return false;
        }
        try {
            boolean z = field.getBoolean(cls);
            if (f509a) {
                Log.d(f507a, "is dev version=" + z);
            }
            return z;
        } catch (IllegalAccessException e) {
            return false;
        }
    }

    /* renamed from: d */
    public static boolean m621d() {
        Class cls;
        Field field;
        if (!m618a() || (cls = f506a) == null || (field = f514d) == null) {
            return false;
        }
        try {
            boolean z = field.getBoolean(cls);
            if (f509a) {
                Log.d(f507a, "is stable version=" + z);
            }
            return z;
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}
