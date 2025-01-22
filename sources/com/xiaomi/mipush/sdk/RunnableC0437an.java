package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiTinyDataClient;
import java.util.concurrent.ScheduledFuture;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.an */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/an.class */
public class RunnableC0437an implements Runnable {

    /* renamed from: a */
    final /* synthetic */ MiTinyDataClient.C0421a.a f287a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0437an(MiTinyDataClient.C0421a.a aVar) {
        this.f287a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ScheduledFuture scheduledFuture;
        ScheduledFuture scheduledFuture2;
        if (this.f287a.f264a.size() != 0) {
            this.f287a.m151b();
            return;
        }
        scheduledFuture = this.f287a.f265a;
        if (scheduledFuture != null) {
            scheduledFuture2 = this.f287a.f265a;
            scheduledFuture2.cancel(false);
            this.f287a.f265a = null;
        }
    }
}
