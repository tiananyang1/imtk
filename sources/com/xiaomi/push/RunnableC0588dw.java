package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;

/* renamed from: com.xiaomi.push.dw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dw.class */
class RunnableC0588dw implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f721a;

    /* renamed from: a */
    final /* synthetic */ Intent f722a;

    /* renamed from: a */
    final /* synthetic */ C0587dv f723a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0588dw(C0587dv c0587dv, Context context, Intent intent) {
        this.f723a = c0587dv;
        this.f721a = context;
        this.f722a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f723a.m954b(this.f721a, this.f722a);
    }
}
