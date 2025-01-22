package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0504at;

/* renamed from: com.xiaomi.mipush.sdk.n */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/n.class */
public class C0471n {

    /* renamed from: a */
    private static int f375a = -1;

    /* renamed from: a */
    public static EnumC0440aq m362a(Context context) {
        try {
            return (context.getPackageManager().getServiceInfo(new ComponentName("com.huawei.hwid", "com.huawei.hms.core.service.HMSCoreService"), 128) == null || !m363a()) ? EnumC0440aq.OTHER : EnumC0440aq.HUAWEI;
        } catch (Exception e) {
            return EnumC0440aq.OTHER;
        }
    }

    /* renamed from: a */
    private static boolean m363a() {
        try {
            String str = (String) C0504at.m497a("android.os.SystemProperties", "get", "ro.build.hw_emui_api_level", "");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return Integer.parseInt(str) >= 9;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m364a(Context context) {
        Object m495a = C0504at.m495a(C0504at.m497a("com.google.android.gms.common.GoogleApiAvailability", "getInstance", new Object[0]), "isGooglePlayServicesAvailable", context);
        Object m496a = C0504at.m496a("com.google.android.gms.common.ConnectionResult", "SUCCESS");
        if (m496a == null || !(m496a instanceof Integer)) {
            AbstractC0407b.m74c("google service is not avaliable");
            f375a = 0;
            return false;
        }
        int intValue = ((Integer) Integer.class.cast(m496a)).intValue();
        if (m495a != null) {
            if (m495a instanceof Integer) {
                f375a = ((Integer) Integer.class.cast(m495a)).intValue() == intValue ? 1 : 0;
            } else {
                f375a = 0;
                AbstractC0407b.m74c("google service is not avaliable");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("is google service can be used");
        sb.append(f375a > 0);
        AbstractC0407b.m74c(sb.toString());
        boolean z = false;
        if (f375a > 0) {
            z = true;
        }
        return z;
    }

    /* renamed from: b */
    public static boolean m365b(Context context) {
        Object m497a = C0504at.m497a("com.xiaomi.assemble.control.COSPushManager", "isSupportPush", context);
        boolean z = false;
        if (m497a != null) {
            z = false;
            if (m497a instanceof Boolean) {
                z = ((Boolean) Boolean.class.cast(m497a)).booleanValue();
            }
        }
        AbstractC0407b.m74c("color os push  is avaliable ? :" + z);
        return z;
    }
}
