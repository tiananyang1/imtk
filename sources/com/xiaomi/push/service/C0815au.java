package com.xiaomi.push.service;

import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.au */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/au.class */
public class C0815au implements C0814at.b.a {

    /* renamed from: a */
    final /* synthetic */ C0814at.b f2524a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0815au(C0814at.b bVar) {
        this.f2524a = bVar;
    }

    @Override // com.xiaomi.push.service.C0814at.b.a
    /* renamed from: a */
    public void mo1555a(C0814at.c cVar, C0814at.c cVar2, int i) {
        XMPushService.C0779b c0779b;
        XMPushService.C0779b c0779b2;
        if (cVar2 == C0814at.c.binding) {
            XMPushService xMPushService = this.f2524a.f2495a;
            c0779b2 = this.f2524a.f2494a;
            xMPushService.m2469a(c0779b2, 60000L);
        } else {
            XMPushService xMPushService2 = this.f2524a.f2495a;
            c0779b = this.f2524a.f2494a;
            xMPushService2.m2481b(c0779b);
        }
    }
}
