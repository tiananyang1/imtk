package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0641fv;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.c */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/c.class */
public class C0848c extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    private XMPushService f2627a;

    /* renamed from: a */
    private C0641fv[] f2628a;

    public C0848c(XMPushService xMPushService, C0641fv[] c0641fvArr) {
        super(4);
        this.f2627a = null;
        this.f2627a = xMPushService;
        this.f2628a = c0641fvArr;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "batch send message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        try {
            if (this.f2628a != null) {
                this.f2627a.m2476a(this.f2628a);
            }
        } catch (C0660gn e) {
            AbstractC0407b.m72a(e);
            this.f2627a.m2466a(10, e);
        }
    }
}
