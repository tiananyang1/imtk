package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.clientreport.processor.InterfaceC0415c;

/* renamed from: com.xiaomi.push.bc */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bc.class */
public class RunnableC0514bc implements Runnable {

    /* renamed from: a */
    private Context f476a;

    /* renamed from: a */
    private InterfaceC0415c f477a;

    /* renamed from: a */
    public void m562a(Context context) {
        this.f476a = context;
    }

    /* renamed from: a */
    public void m563a(InterfaceC0415c interfaceC0415c) {
        this.f477a = interfaceC0415c;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0519bh m575a;
        String str;
        long currentTimeMillis;
        try {
            if (this.f477a != null) {
                this.f477a.mo96a();
            }
            AbstractC0407b.m74c("begin read and send perf / event");
            if (this.f477a instanceof IEventProcessor) {
                m575a = C0519bh.m575a(this.f476a);
                str = "event_last_upload_time";
                currentTimeMillis = System.currentTimeMillis();
            } else {
                if (!(this.f477a instanceof IPerfProcessor)) {
                    return;
                }
                m575a = C0519bh.m575a(this.f476a);
                str = "perf_last_upload_time";
                currentTimeMillis = System.currentTimeMillis();
            }
            m575a.m578a("sp_client_report_status", str, currentTimeMillis);
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }
}
