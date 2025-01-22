package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.z */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/z.class */
public final class C0877z extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0729jb f2730a;

    /* renamed from: a */
    final /* synthetic */ XMPushService f2731a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0877z(int i, XMPushService xMPushService, C0729jb c0729jb) {
        super(i);
        this.f2731a = xMPushService;
        this.f2730a = c0729jb;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "send ack message for message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        try {
            C0800af.m2499a(this.f2731a, C0875x.m2820a((Context) this.f2731a, this.f2730a));
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            this.f2731a.m2466a(10, e);
        }
    }
}
