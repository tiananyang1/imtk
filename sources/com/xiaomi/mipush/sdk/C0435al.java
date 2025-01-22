package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0770n;
import java.util.HashMap;

/* renamed from: com.xiaomi.mipush.sdk.al */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/al.class */
class C0435al {
    /* renamed from: a */
    public static HashMap<String, String> m184a(Context context, String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            hashMap.put("appToken", C0461d.m289a(context).m303b());
            hashMap.put("regId", MiPushClient.getRegId(context));
            hashMap.put("appId", C0461d.m289a(context).m293a());
            hashMap.put("regResource", C0461d.m289a(context).m312e());
            if (!C0770n.m2408d()) {
                String m1994g = C0727j.m1994g(context);
                if (!TextUtils.isEmpty(m1994g)) {
                    hashMap.put("imeiMd5", C0509ay.m521a(m1994g));
                }
            }
            hashMap.put("isMIUI", String.valueOf(C0770n.m2403a()));
            hashMap.put("miuiVersion", C0770n.m2400a());
            hashMap.put("devId", C0727j.m1982a(context, true));
            hashMap.put("model", Build.MODEL);
            hashMap.put("pkgName", context.getPackageName());
            hashMap.put("sdkVersion", "3_6_12");
            hashMap.put("androidVersion", String.valueOf(Build.VERSION.SDK_INT));
            hashMap.put("os", Build.VERSION.RELEASE + Constants.ACCEPT_TIME_SEPARATOR_SERVER + Build.VERSION.INCREMENTAL);
            hashMap.put("andId", C0727j.m1992e(context));
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("clientInterfaceId", str);
            }
            return hashMap;
        } catch (Throwable th) {
            return hashMap;
        }
    }
}
