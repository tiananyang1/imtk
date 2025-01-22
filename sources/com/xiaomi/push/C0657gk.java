package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.gk */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gk.class */
class C0657gk extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ long f1100a;

    /* renamed from: a */
    final /* synthetic */ AbstractC0656gj f1101a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0657gk(AbstractC0656gj abstractC0656gj, int i, long j) {
        super(i);
        this.f1101a = abstractC0656gj;
        this.f1100a = j;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "check the ping-pong." + this.f1100a;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        Thread.yield();
        if (!this.f1101a.m1435c() || this.f1101a.m1394a(this.f1100a)) {
            return;
        }
        this.f1101a.f1093b.m2466a(22, (Exception) null);
        this.f1101a.f1093b.m2474a(true);
    }
}
