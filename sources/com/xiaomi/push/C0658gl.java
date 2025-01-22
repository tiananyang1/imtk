package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.gl */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gl.class */
class C0658gl extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ AbstractC0656gj f1102a;

    /* renamed from: a */
    final /* synthetic */ Exception f1103a;

    /* renamed from: b */
    final /* synthetic */ int f1104b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0658gl(AbstractC0656gj abstractC0656gj, int i, int i2, Exception exc) {
        super(i);
        this.f1102a = abstractC0656gj;
        this.f1104b = i2;
        this.f1103a = exc;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "shutdown the connection. " + this.f1104b + ", " + this.f1103a;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        this.f1102a.f1093b.m2466a(this.f1104b, this.f1103a);
    }
}
