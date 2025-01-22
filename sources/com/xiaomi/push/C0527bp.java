package com.xiaomi.push;

import android.content.Context;
import android.telephony.TelephonyManager;

/* renamed from: com.xiaomi.push.bp */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bp.class */
public class C0527bp {

    /* renamed from: a */
    private static Context f515a;

    /* renamed from: a */
    private static TelephonyManager f516a;

    /* renamed from: a */
    public static String m625a() {
        TelephonyManager telephonyManager = f516a;
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperator();
        }
        return null;
    }

    /* renamed from: a */
    public static void m626a(Context context) {
        f515a = context;
        f516a = (TelephonyManager) context.getSystemService("phone");
    }

    /* renamed from: b */
    public static String m627b() {
        String str = null;
        try {
            if (f515a != null) {
                str = null;
                if (f515a.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", f515a.getPackageName()) == 0) {
                    str = null;
                    if (f516a != null) {
                        str = f516a.getDeviceId();
                    }
                }
            }
        } catch (Exception e) {
            str = null;
        }
        return str != null ? str : "UNKNOWN";
    }
}
