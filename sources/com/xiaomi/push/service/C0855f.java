package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.service.module.C0863a;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.f */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/f.class */
public class C0855f extends C0493ai.a {

    /* renamed from: a */
    private XMPushService f2641a;

    public C0855f(XMPushService xMPushService) {
        this.f2641a = xMPushService;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 15;
    }

    @Override // java.lang.Runnable
    public void run() {
        Iterator<C0863a> it = C0858i.m2709a(this.f2641a).m2711a().iterator();
        while (it.hasNext()) {
            C0863a next = it.next();
            if (next.m2761a() < System.currentTimeMillis()) {
                if (C0858i.m2709a(this.f2641a).m2710a(next.m2762a()) == 0) {
                    AbstractC0407b.m70a("GeofenceDbCleaner delete a geofence message failed message_id:" + next.m2762a());
                }
                C0875x.m2826a(this.f2641a, C0875x.m2822a(next.m2767a()), false, false, true);
            }
        }
    }
}
