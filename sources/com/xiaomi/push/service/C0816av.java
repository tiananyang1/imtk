package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.service.av */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/av.class */
class C0816av extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0814at.b.c f2525a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0816av(C0814at.b.c cVar, int i) {
        super(i);
        this.f2525a = cVar;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "clear peer job";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        if (this.f2525a.f2517a == this.f2525a.f2518a.f2493a) {
            AbstractC0407b.m73b("clean peer, chid = " + this.f2525a.f2518a.f2509g);
            this.f2525a.f2518a.f2493a = null;
        }
    }
}
