package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.manager.C0410a;
import com.xiaomi.push.C0493ai;

/* renamed from: com.xiaomi.push.ba */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ba.class */
public class C0512ba extends C0493ai.a {

    /* renamed from: a */
    private Context f474a;

    public C0512ba(Context context) {
        this.f474a = context;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 100886;
    }

    /* renamed from: a */
    public boolean m560a() {
        boolean z = false;
        if (!C0410a.m77a(this.f474a).m84a().isEventUploadSwitchOpen()) {
            return false;
        }
        if (((int) ((System.currentTimeMillis() - C0519bh.m575a(this.f474a).m576a("sp_client_report_status", "event_last_upload_time", 0L)) / 1000)) >= ((int) C0410a.m77a(this.f474a).m84a().getEventUploadFrequency()) - 5) {
            z = true;
        }
        AbstractC0407b.m74c(this.f474a.getPackageName() + " event upload result " + z);
        return z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (m560a()) {
                AbstractC0407b.m74c(this.f474a.getPackageName() + " begin upload event");
                C0410a.m77a(this.f474a).m90b();
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }
}
