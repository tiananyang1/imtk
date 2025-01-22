package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.C0812ar;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MiPushClient4VR.class */
public class MiPushClient4VR {
    public static void uploadData(Context context, String str) {
        C0732je c0732je = new C0732je();
        c0732je.m2075c(EnumC0714in.VRUpload.f1752a);
        c0732je.m2071b(C0461d.m289a(context).m293a());
        c0732je.m2079d(context.getPackageName());
        c0732je.m2066a("data", str);
        c0732je.m2058a(C0812ar.m2571a());
        C0449az.m224a(context).m252a((C0449az) c0732je, EnumC0696hw.Notification, (C0717iq) null);
    }
}
