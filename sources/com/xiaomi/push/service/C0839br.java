package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.br */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/br.class */
public class C0839br extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2616a;

    /* renamed from: a */
    final /* synthetic */ String f2617a;

    /* renamed from: a */
    final /* synthetic */ byte[] f2618a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0839br(XMPushService xMPushService, int i, String str, byte[] bArr) {
        super(i);
        this.f2616a = xMPushService;
        this.f2617a = str;
        this.f2618a = bArr;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "send mi push message";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        try {
            C0800af.m2501a(this.f2616a, this.f2617a, this.f2618a);
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            this.f2616a.m2466a(10, e);
        }
    }
}
