package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.AbstractC0772p;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.C0812ar;
import com.xiaomi.push.service.C0865o;
import java.util.HashMap;

/* renamed from: com.xiaomi.mipush.sdk.bi */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/bi.class */
public class C0459bi extends C0493ai.a {

    /* renamed from: a */
    private Context f337a;

    public C0459bi(Context context) {
        this.f337a = context;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 10;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0732je c0732je = new C0732je(C0812ar.m2571a(), false);
        C0461d m289a = C0461d.m289a(this.f337a);
        c0732je.m2075c(EnumC0714in.SyncMIID.f1752a);
        c0732je.m2071b(m289a.m293a());
        c0732je.m2079d(this.f337a.getPackageName());
        HashMap hashMap = new HashMap();
        AbstractC0772p.m2412a(hashMap, Constants.EXTRA_KEY_MIID, C0865o.m2771a(this.f337a).m2777a());
        c0732je.f2033a = hashMap;
        C0449az.m224a(this.f337a).m254a((C0449az) c0732je, EnumC0696hw.Notification, true, (C0717iq) null);
    }
}
