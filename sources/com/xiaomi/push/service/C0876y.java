package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.y */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/y.class */
public final class C0876y extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0729jb f2728a;

    /* renamed from: a */
    final /* synthetic */ XMPushService f2729a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0876y(int i, XMPushService xMPushService, C0729jb c0729jb) {
        super(i);
        this.f2729a = xMPushService;
        this.f2728a = c0729jb;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "send app absent message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        try {
            C0800af.m2499a(this.f2729a, C0800af.m2494a(this.f2728a.m2037b(), this.f2728a.m2029a()));
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            this.f2729a.m2466a(10, e);
        }
    }
}
