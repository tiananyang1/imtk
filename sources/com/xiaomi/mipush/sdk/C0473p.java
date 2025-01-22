package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.stub.StubApp;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.service.C0812ar;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.p */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/p.class */
public final class C0473p extends C0493ai.a {

    /* renamed from: a */
    final /* synthetic */ Context f376a;

    /* renamed from: a */
    final /* synthetic */ C0732je f377a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0473p(C0732je c0732je, Context context) {
        this.f377a = c0732je;
        this.f376a = context;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 22;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0732je c0732je = this.f377a;
        if (c0732je != null) {
            c0732je.m2058a(C0812ar.m2571a());
            C0449az.m224a(StubApp.getOrigApplicationContext(this.f376a.getApplicationContext())).m255a((C0449az) this.f377a, EnumC0696hw.Notification, true, (C0717iq) null, true);
        }
    }
}
