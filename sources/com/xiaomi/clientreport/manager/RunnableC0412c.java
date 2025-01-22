package com.xiaomi.clientreport.manager;

import android.content.Context;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.push.RunnableC0515bd;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.clientreport.manager.c */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/manager/c.class */
public class RunnableC0412c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0410a f234a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0412c(C0410a c0410a) {
        this.f234a = c0410a;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        IPerfProcessor iPerfProcessor;
        ExecutorService executorService;
        context = this.f234a.f226a;
        iPerfProcessor = this.f234a.f229a;
        RunnableC0515bd runnableC0515bd = new RunnableC0515bd(context, iPerfProcessor);
        executorService = this.f234a.f231a;
        executorService.execute(runnableC0515bd);
    }
}
