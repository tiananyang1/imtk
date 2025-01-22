package com.xiaomi.push;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

/* renamed from: com.xiaomi.push.by */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/by.class */
public class C0536by {

    /* renamed from: a */
    private static C0536by f528a;

    /* renamed from: a */
    private Context f529a;

    /* renamed from: a */
    private Handler f530a;

    /* renamed from: a */
    private HandlerThread f531a;

    /* renamed from: a */
    private InterfaceC0556cr f532a;

    /* renamed from: a */
    private boolean f533a;

    /* renamed from: b */
    private boolean f534b;

    /* renamed from: a */
    public static C0536by m650a() {
        if (f528a == null) {
            f528a = new C0536by();
        }
        return f528a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m653c() {
        C0552cn.m788a().m798a(this.f529a);
        this.f534b = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m654d() {
        C0552cn.m788a().m797a();
        this.f534b = false;
    }

    /* renamed from: a */
    public void m655a() {
        C0552cn.m788a().m800c();
    }

    /* renamed from: a */
    public void m656a(Context context) {
        this.f529a = context;
        C0523bl.m604a(this.f529a);
        if (this.f533a) {
            return;
        }
        this.f533a = true;
        this.f531a = new HandlerThread("metoknlp_cl");
        this.f531a.start();
        this.f530a = new Handler(this.f531a.getLooper());
        this.f532a = new C0555cq(this, null);
        C0523bl.m603a().m609a(this.f532a);
        if (C0522bk.m590a().m601a()) {
            m657b();
        }
    }

    /* renamed from: b */
    public void m657b() {
        Handler handler = this.f530a;
        if (handler == null) {
            return;
        }
        handler.post(new RunnableC0537bz(this));
    }
}
