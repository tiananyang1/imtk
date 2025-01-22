package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.service.bs */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bs.class */
class C0840bs extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2619a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0840bs(XMPushService xMPushService, int i) {
        super(i);
        this.f2619a = xMPushService;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "disconnect for service destroy.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        if (this.f2619a.f2393a != null) {
            this.f2619a.f2393a.mo1398b(15, (Exception) null);
            this.f2619a.f2393a = null;
        }
    }
}
