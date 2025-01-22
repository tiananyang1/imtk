package com.xiaomi.push;

/* renamed from: com.xiaomi.push.dt */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dt.class */
public class C0585dt {

    /* renamed from: a */
    private static volatile C0585dt f719a;

    /* renamed from: a */
    private InterfaceC0584ds f720a;

    /* renamed from: a */
    public static C0585dt m946a() {
        if (f719a == null) {
            synchronized (C0585dt.class) {
                try {
                    if (f719a == null) {
                        f719a = new C0585dt();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f719a;
    }

    /* renamed from: a */
    public InterfaceC0584ds m947a() {
        return this.f720a;
    }

    /* renamed from: a */
    public void m948a(InterfaceC0584ds interfaceC0584ds) {
        this.f720a = interfaceC0584ds;
    }
}
