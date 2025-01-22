package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.ae */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ae.class */
public final class C0799ae extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0729jb f2447a;

    /* renamed from: a */
    final /* synthetic */ XMPushService f2448a;

    /* renamed from: a */
    final /* synthetic */ boolean f2449a;

    /* renamed from: b */
    final /* synthetic */ boolean f2450b;

    /* renamed from: c */
    final /* synthetic */ boolean f2451c;

    /* renamed from: d */
    final /* synthetic */ boolean f2452d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0799ae(int i, XMPushService xMPushService, C0729jb c0729jb, boolean z, boolean z2, boolean z3, boolean z4) {
        super(i);
        this.f2448a = xMPushService;
        this.f2447a = c0729jb;
        this.f2449a = z;
        this.f2450b = z2;
        this.f2451c = z3;
        this.f2452d = z4;
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
            C0729jb m2821a = C0875x.m2821a((Context) this.f2448a, this.f2447a, this.f2449a, this.f2450b, this.f2451c);
            if (this.f2452d) {
                m2821a.m2022a().m1828a("permission_to_location", AbstractC0823bb.f2572b);
            }
            C0800af.m2499a(this.f2448a, m2821a);
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            this.f2448a.m2466a(10, e);
        }
    }
}
