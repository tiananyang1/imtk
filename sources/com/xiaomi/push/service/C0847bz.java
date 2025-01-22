package com.xiaomi.push.service;

import com.xiaomi.push.C0503as;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.bz */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bz.class */
public class C0847bz extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2626a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0847bz(XMPushService xMPushService, int i) {
        super(i);
        this.f2626a = xMPushService;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "prepare the mi push account.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        C0800af.m2498a(this.f2626a);
        if (C0503as.m484b(this.f2626a)) {
            this.f2626a.m2474a(true);
        }
    }
}
