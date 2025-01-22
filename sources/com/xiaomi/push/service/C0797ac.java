package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.ac */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ac.class */
public final class C0797ac extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0729jb f2440a;

    /* renamed from: a */
    final /* synthetic */ XMPushService f2441a;

    /* renamed from: a */
    final /* synthetic */ String f2442a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0797ac(int i, XMPushService xMPushService, C0729jb c0729jb, String str) {
        super(i);
        this.f2441a = xMPushService;
        this.f2440a = c0729jb;
        this.f2442a = str;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "send app absent ack message for message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        try {
            C0729jb m2820a = C0875x.m2820a((Context) this.f2441a, this.f2440a);
            m2820a.m2022a().m1828a("absent_target_package", this.f2442a);
            C0800af.m2499a(this.f2441a, m2820a);
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            this.f2441a.m2466a(10, e);
        }
    }
}
