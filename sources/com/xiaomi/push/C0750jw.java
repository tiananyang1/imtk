package com.xiaomi.push;

import com.xiaomi.push.C0751jx;
import java.io.ByteArrayOutputStream;

/* renamed from: com.xiaomi.push.jw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jw.class */
public class C0750jw {

    /* renamed from: a */
    private AbstractC0756kb f2305a;

    /* renamed from: a */
    private final C0763ki f2306a;

    /* renamed from: a */
    private final ByteArrayOutputStream f2307a;

    public C0750jw() {
        this(new C0751jx.a());
    }

    public C0750jw(InterfaceC0758kd interfaceC0758kd) {
        this.f2307a = new ByteArrayOutputStream();
        this.f2306a = new C0763ki(this.f2307a);
        this.f2305a = interfaceC0758kd.mo2378a(this.f2306a);
    }

    /* renamed from: a */
    public byte[] m2336a(InterfaceC0744jq interfaceC0744jq) {
        this.f2307a.reset();
        interfaceC0744jq.mo1293b(this.f2305a);
        return this.f2307a.toByteArray();
    }
}
