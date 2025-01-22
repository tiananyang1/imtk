package com.xiaomi.push;

import android.os.Handler;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.ch */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ch.class */
public class C0546ch implements InterfaceC0554cp {

    /* renamed from: a */
    private long f584a;

    /* renamed from: a */
    final /* synthetic */ C0552cn f585a;

    /* renamed from: a */
    private String f586a;

    /* renamed from: b */
    private long f587b;

    private C0546ch(C0552cn c0552cn) {
        this.f585a = c0552cn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ C0546ch(C0552cn c0552cn, HandlerC0540cb handlerC0540cb) {
        this(c0552cn);
    }

    /* renamed from: a */
    public long m763a() {
        return this.f584a;
    }

    @Override // com.xiaomi.push.InterfaceC0554cp
    /* renamed from: a */
    public void mo764a(String str, long j, long j2) {
        Handler handler;
        this.f586a = str;
        this.f584a = j;
        this.f587b = j2;
        handler = this.f585a.f598a;
        handler.obtainMessage(1).sendToTarget();
    }

    /* renamed from: b */
    public long m765b() {
        return this.f587b;
    }
}
