package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0724ix;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0704id;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.C0810ap;

/* renamed from: com.xiaomi.mipush.sdk.ao */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ao.class */
public class C0438ao extends C0493ai.a {

    /* renamed from: a */
    private Context f288a;

    public C0438ao(Context context) {
        this.f288a = context;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 2;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0809ao m2557a = C0809ao.m2557a(this.f288a);
        C0724ix c0724ix = new C0724ix();
        c0724ix.m1942a(C0810ap.m2565a(m2557a, EnumC0704id.MISC_CONFIG));
        c0724ix.m1947b(C0810ap.m2565a(m2557a, EnumC0704id.PLUGIN_CONFIG));
        C0732je c0732je = new C0732je("-1", false);
        c0732je.m2075c(EnumC0714in.DailyCheckClientConfig.f1752a);
        c0732je.m2062a(C0743jp.m2314a(c0724ix));
        C0449az.m224a(this.f288a).m252a((C0449az) c0732je, EnumC0696hw.Notification, (C0717iq) null);
    }
}
