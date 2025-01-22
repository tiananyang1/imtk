package com.xiaomi.push;

import com.xiaomi.push.C0751jx;

/* renamed from: com.xiaomi.push.ju */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ju.class */
public class C0748ju {

    /* renamed from: a */
    private final AbstractC0756kb f2303a;

    /* renamed from: a */
    private final C0765kk f2304a;

    public C0748ju() {
        this(new C0751jx.a());
    }

    public C0748ju(InterfaceC0758kd interfaceC0758kd) {
        this.f2304a = new C0765kk();
        this.f2303a = interfaceC0758kd.mo2378a(this.f2304a);
    }

    /* renamed from: a */
    public void m2335a(InterfaceC0744jq interfaceC0744jq, byte[] bArr) {
        try {
            this.f2304a.m2391a(bArr);
            interfaceC0744jq.mo1287a(this.f2303a);
        } finally {
            this.f2303a.m2383l();
        }
    }
}
