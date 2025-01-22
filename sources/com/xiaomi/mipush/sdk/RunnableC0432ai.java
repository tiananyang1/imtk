package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0770n;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.service.C0812ar;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.ai */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ai.class */
public final class RunnableC0432ai implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        Context context3;
        Context context4;
        Context context5;
        if (C0770n.m2408d()) {
            return;
        }
        context = MiPushClient.sContext;
        if (C0727j.m1993f(context) != null) {
            C0732je c0732je = new C0732je();
            context2 = MiPushClient.sContext;
            c0732je.m2071b(C0461d.m289a(context2).m293a());
            c0732je.m2075c("client_info_update");
            c0732je.m2058a(C0812ar.m2571a());
            c0732je.m2060a(new HashMap());
            String str = "";
            context3 = MiPushClient.sContext;
            String m1993f = C0727j.m1993f(context3);
            if (!TextUtils.isEmpty(m1993f)) {
                str = "" + C0509ay.m521a(m1993f);
            }
            context4 = MiPushClient.sContext;
            String m1995h = C0727j.m1995h(context4);
            String str2 = str;
            if (!TextUtils.isEmpty(str)) {
                str2 = str;
                if (!TextUtils.isEmpty(m1995h)) {
                    str2 = str + Constants.ACCEPT_TIME_SEPARATOR_SP + m1995h;
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                c0732je.m2064a().put(Constants.EXTRA_KEY_IMEI_MD5, str2);
            }
            int m1979a = C0727j.m1979a();
            if (m1979a >= 0) {
                c0732je.m2064a().put("space_id", Integer.toString(m1979a));
            }
            context5 = MiPushClient.sContext;
            C0449az.m224a(context5).m254a((C0449az) c0732je, EnumC0696hw.Notification, false, (C0717iq) null);
        }
    }
}
