package com.xiaomi.clientreport.manager;

import android.content.Context;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.push.RunnableC0515bd;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.clientreport.manager.b */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/manager/b.class */
public class RunnableC0411b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0410a f233a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0411b(C0410a c0410a) {
        this.f233a = c0410a;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        IEventProcessor iEventProcessor;
        ExecutorService executorService;
        context = this.f233a.f226a;
        iEventProcessor = this.f233a.f228a;
        RunnableC0515bd runnableC0515bd = new RunnableC0515bd(context, iEventProcessor);
        executorService = this.f233a.f231a;
        executorService.execute(runnableC0515bd);
    }
}
