package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.hk */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hk.class */
class C0684hk extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0683hj f1382a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0684hk(C0683hj c0683hj, int i) {
        super(i);
        this.f1382a = c0683hj;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "Handling bind stats";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        this.f1382a.m1553c();
    }
}
