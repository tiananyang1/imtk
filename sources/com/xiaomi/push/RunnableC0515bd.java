package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.processor.InterfaceC0416d;

/* renamed from: com.xiaomi.push.bd */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bd.class */
public class RunnableC0515bd implements Runnable {

    /* renamed from: a */
    private Context f478a;

    /* renamed from: a */
    private InterfaceC0416d f479a;

    public RunnableC0515bd(Context context, InterfaceC0416d interfaceC0416d) {
        this.f478a = context;
        this.f479a = interfaceC0416d;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f479a != null) {
                this.f479a.mo101b();
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }
}
