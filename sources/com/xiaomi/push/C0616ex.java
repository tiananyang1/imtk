package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.List;

/* renamed from: com.xiaomi.push.ex */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ex.class */
public class C0616ex {
    /* renamed from: a */
    public static boolean m1201a(Context context, String str) {
        try {
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(str, 4).services;
            if (serviceInfoArr == null) {
                return false;
            }
            int length = serviceInfoArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return false;
                }
                ServiceInfo serviceInfo = serviceInfoArr[i2];
                if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                    return true;
                }
                i = i2 + 1;
            }
        } catch (PackageManager.NameNotFoundException e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1202a(Context context, String str, String str2) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str2);
            intent.setPackage(str);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 32);
            if (queryIntentServices != null) {
                return !queryIntentServices.isEmpty();
            }
            return false;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m1203b(Context context, String str) {
        boolean z;
        try {
            PackageManager packageManager = context.getPackageManager();
            z = true;
            if (Build.VERSION.SDK_INT >= 19) {
                List<ProviderInfo> queryContentProviders = packageManager.queryContentProviders((String) null, 0, 8);
                if (queryContentProviders == null || queryContentProviders.isEmpty()) {
                    return false;
                }
                z = false;
                for (ProviderInfo providerInfo : queryContentProviders) {
                    try {
                        if (providerInfo.enabled && providerInfo.exported && providerInfo.authority.equals(str)) {
                            z = true;
                        }
                    } catch (Exception e) {
                        e = e;
                        AbstractC0407b.m72a(e);
                        return z;
                    }
                }
                return z;
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
        return z;
    }

    /* renamed from: b */
    public static boolean m1204b(Context context, String str, String str2) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str2);
            intent.setPackage(str);
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 32);
            if (queryIntentActivities != null) {
                return !queryIntentActivities.isEmpty();
            }
            return false;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }
}
