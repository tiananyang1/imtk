package com.xiaomi.mipush.sdk;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.ag */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ag.class */
public final class RunnableC0430ag implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f281a;

    /* renamed from: b */
    final /* synthetic */ String f282b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0430ag(String str, String str2) {
        this.f281a = str;
        this.f282b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        context = MiPushClient.sContext;
        MiPushClient.initialize(context, this.f281a, this.f282b, null);
    }
}
