package com.xiaomi.push.service;

import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.service.aw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/aw.class */
class C0817aw extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0814at.b.c f2526a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0817aw(C0814at.b.c cVar, int i) {
        super(i);
        this.f2526a = cVar;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "check peer job";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        if (C0814at.m2578a().m2581a(this.f2526a.f2518a.f2509g, this.f2526a.f2518a.f2503b).f2493a == null) {
            C0814at.b.this.f2495a.m2472a(this.f2526a.f2518a.f2509g, this.f2526a.f2518a.f2503b, 2, null, null);
        }
    }
}
