package com.xiaomi.push.service;

import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.service.bx */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bx.class */
class C0845bx implements C0814at.a {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2624a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0845bx(XMPushService xMPushService) {
        this.f2624a = xMPushService;
    }

    @Override // com.xiaomi.push.service.C0814at.a
    /* renamed from: a */
    public void mo2593a() {
        this.f2624a.m2453e();
        if (C0814at.m2578a().m2580a() <= 0) {
            XMPushService xMPushService = this.f2624a;
            xMPushService.m2468a(new XMPushService.C0783f(12, null));
        }
    }
}
