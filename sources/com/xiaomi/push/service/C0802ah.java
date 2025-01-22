package com.xiaomi.push.service;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.service.C0814at;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.ah */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ah.class */
public final class C0802ah implements C0814at.b.a {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2455a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0802ah(XMPushService xMPushService) {
        this.f2455a = xMPushService;
    }

    @Override // com.xiaomi.push.service.C0814at.b.a
    /* renamed from: a */
    public void mo1555a(C0814at.c cVar, C0814at.c cVar2, int i) {
        if (cVar2 == C0814at.c.binded) {
            C0874w.m2815a(this.f2455a);
            C0874w.m2817b(this.f2455a);
        } else if (cVar2 == C0814at.c.unbind) {
            C0874w.m2813a(this.f2455a, ErrorCode.ERROR_SERVICE_UNAVAILABLE, " the push is not connected.");
        }
    }
}
