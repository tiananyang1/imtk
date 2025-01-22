package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.ad */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ad.class */
public final class C0798ad extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0729jb f2443a;

    /* renamed from: a */
    final /* synthetic */ XMPushService f2444a;

    /* renamed from: a */
    final /* synthetic */ String f2445a;

    /* renamed from: b */
    final /* synthetic */ String f2446b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0798ad(int i, XMPushService xMPushService, C0729jb c0729jb, String str, String str2) {
        super(i);
        this.f2444a = xMPushService;
        this.f2443a = c0729jb;
        this.f2445a = str;
        this.f2446b = str2;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "send wrong message ack for message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        try {
            C0729jb m2820a = C0875x.m2820a((Context) this.f2444a, this.f2443a);
            m2820a.f1998a.m1828a("error", this.f2445a);
            m2820a.f1998a.m1828a("reason", this.f2446b);
            C0800af.m2499a(this.f2444a, m2820a);
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            this.f2444a.m2466a(10, e);
        }
    }
}
