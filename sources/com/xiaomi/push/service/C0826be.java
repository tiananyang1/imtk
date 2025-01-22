package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0641fv;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.be */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/be.class */
public class C0826be extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    private C0641fv f2583a;

    /* renamed from: a */
    private XMPushService f2584a;

    public C0826be(XMPushService xMPushService, C0641fv c0641fv) {
        super(4);
        this.f2584a = null;
        this.f2584a = xMPushService;
        this.f2583a = c0641fv;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "send a message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        try {
            if (this.f2583a != null) {
                this.f2584a.m2467a(this.f2583a);
            }
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            this.f2584a.m2466a(10, e);
        }
    }
}
