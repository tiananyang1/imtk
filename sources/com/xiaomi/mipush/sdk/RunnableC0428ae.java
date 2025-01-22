package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* renamed from: com.xiaomi.mipush.sdk.ae */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ae.class */
final class RunnableC0428ae implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f278a;

    /* renamed from: a */
    final /* synthetic */ Intent f279a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0428ae(Context context, Intent intent) {
        this.f278a = context;
        this.f279a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f278a.startService(this.f279a);
        } catch (Exception e) {
            AbstractC0407b.m70a(e.getMessage());
        }
    }
}
