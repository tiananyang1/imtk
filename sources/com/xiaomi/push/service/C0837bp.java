package com.xiaomi.push.service;

import com.xiaomi.push.AbstractC0666gt;
import com.xiaomi.push.C0641fv;
import com.xiaomi.push.InterfaceC0654gh;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.service.bp */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bp.class */
class C0837bp implements InterfaceC0654gh {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2614a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0837bp(XMPushService xMPushService) {
        this.f2614a = xMPushService;
    }

    @Override // com.xiaomi.push.InterfaceC0654gh
    /* renamed from: a */
    public void mo583a(C0641fv c0641fv) {
        XMPushService xMPushService = this.f2614a;
        xMPushService.m2468a(new XMPushService.C0780c(c0641fv));
    }

    @Override // com.xiaomi.push.InterfaceC0654gh
    /* renamed from: a */
    public void mo584a(AbstractC0666gt abstractC0666gt) {
        XMPushService xMPushService = this.f2614a;
        xMPushService.m2468a(new XMPushService.C0788k(abstractC0666gt));
    }
}
