package com.xiaomi.push.service.receivers;

import android.content.Context;

/* renamed from: com.xiaomi.push.service.receivers.a */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/receivers/a.class */
class RunnableC0869a implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f2705a;

    /* renamed from: a */
    final /* synthetic */ NetworkStatusReceiver f2706a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0869a(NetworkStatusReceiver networkStatusReceiver, Context context) {
        this.f2706a = networkStatusReceiver;
        this.f2705a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f2706a.m2787a(this.f2705a);
    }
}
