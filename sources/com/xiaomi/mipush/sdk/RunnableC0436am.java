package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiTinyDataClient;
import com.xiaomi.push.C0702ib;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.am */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/am.class */
public class RunnableC0436am implements Runnable {

    /* renamed from: a */
    final /* synthetic */ MiTinyDataClient.C0421a.a f285a;

    /* renamed from: a */
    final /* synthetic */ C0702ib f286a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0436am(MiTinyDataClient.C0421a.a aVar, C0702ib c0702ib) {
        this.f285a = aVar;
        this.f286a = c0702ib;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f285a.f264a.add(this.f286a);
        this.f285a.m149a();
    }
}
