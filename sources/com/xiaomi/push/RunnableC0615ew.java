package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.ew */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ew.class */
public final class RunnableC0615ew implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f879a;

    /* renamed from: a */
    final /* synthetic */ Context f880a;

    /* renamed from: a */
    final /* synthetic */ String f881a;

    /* renamed from: b */
    final /* synthetic */ String f882b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0615ew(Context context, String str, int i, String str2) {
        this.f880a = context;
        this.f881a = str;
        this.f879a = i;
        this.f882b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0614ev.m1199c(this.f880a, this.f881a, this.f879a, this.f882b);
    }
}
