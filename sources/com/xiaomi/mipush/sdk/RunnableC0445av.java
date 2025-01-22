package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.av */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/av.class */
public final class RunnableC0445av implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f299a;

    /* renamed from: a */
    final /* synthetic */ Intent f300a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0445av(Context context, Intent intent) {
        this.f299a = context;
        this.f300a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        PushMessageHandler.m165b(this.f299a, this.f300a);
    }
}
