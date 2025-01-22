package com.xiaomi.mipush.sdk;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.af */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/af.class */
public final class RunnableC0429af implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f280a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0429af(Context context) {
        this.f280a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        MessageHandleService.m127c(this.f280a);
    }
}
