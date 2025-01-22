package com.xiaomi.push.service;

import android.app.NotificationManager;
import com.xiaomi.push.C0493ai;

/* renamed from: com.xiaomi.push.service.aj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/aj.class */
final class C0804aj extends C0493ai.a {

    /* renamed from: a */
    final /* synthetic */ int f2466a;

    /* renamed from: a */
    final /* synthetic */ NotificationManager f2467a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0804aj(int i, NotificationManager notificationManager) {
        this.f2466a = i;
        this.f2467a = notificationManager;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return this.f2466a;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f2467a.cancel(this.f2466a);
    }
}
