package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.manager.C0410a;
import com.xiaomi.push.C0493ai;

/* renamed from: com.xiaomi.push.bb */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bb.class */
public class C0513bb extends C0493ai.a {

    /* renamed from: a */
    private Context f475a;

    public C0513bb(Context context) {
        this.f475a = context;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 100887;
    }

    /* renamed from: a */
    public boolean m561a() {
        boolean z = false;
        if (!C0410a.m77a(this.f475a).m84a().isPerfUploadSwitchOpen()) {
            return false;
        }
        if (((int) ((System.currentTimeMillis() - C0519bh.m575a(this.f475a).m576a("sp_client_report_status", "perf_last_upload_time", 0L)) / 1000)) >= ((int) C0410a.m77a(this.f475a).m84a().getPerfUploadFrequency()) - 5) {
            z = true;
        }
        AbstractC0407b.m74c(this.f475a.getPackageName() + "perf upload result " + z);
        return z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (m561a()) {
                C0410a.m77a(this.f475a).m91c();
                AbstractC0407b.m74c(this.f475a.getPackageName() + "perf  begin upload");
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }
}
