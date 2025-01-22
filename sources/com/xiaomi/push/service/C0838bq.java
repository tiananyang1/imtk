package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.bq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bq.class */
public class C0838bq extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2615a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0838bq(XMPushService xMPushService, int i) {
        super(i);
        this.f2615a = xMPushService;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "disconnect for disable push";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        if (this.f2615a.f2393a != null) {
            this.f2615a.f2393a.m1397b();
            this.f2615a.f2393a = null;
        }
    }
}
