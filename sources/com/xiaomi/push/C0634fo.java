package com.xiaomi.push;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0632fm;
import com.xiaomi.push.service.XMJobService;

@TargetApi(21)
/* renamed from: com.xiaomi.push.fo */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fo.class */
public class C0634fo implements C0632fm.a {

    /* renamed from: a */
    JobScheduler f919a;

    /* renamed from: a */
    Context f920a;

    /* renamed from: a */
    private boolean f921a = false;

    C0634fo(Context context) {
        this.f920a = context;
        this.f919a = (JobScheduler) context.getSystemService("jobscheduler");
    }

    @Override // com.xiaomi.push.C0632fm.a
    /* renamed from: a */
    public void mo1273a() {
        this.f921a = false;
        this.f919a.cancel(1);
    }

    /* renamed from: a */
    void m1278a(long j) {
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this.f920a.getPackageName(), XMJobService.class.getName()));
        builder.setMinimumLatency(j);
        builder.setOverrideDeadline(j);
        builder.setRequiredNetworkType(1);
        builder.setPersisted(false);
        AbstractC0407b.m74c("schedule Job = " + builder.build().getId() + " in " + j);
        this.f919a.schedule(builder.build());
    }

    @Override // com.xiaomi.push.C0632fm.a
    /* renamed from: a */
    public void mo1274a(boolean z) {
        if (z || this.f921a) {
            long m1428b = C0655gi.m1428b();
            long j = m1428b;
            if (z) {
                mo1273a();
                j = m1428b - (SystemClock.elapsedRealtime() % m1428b);
            }
            this.f921a = true;
            m1278a(j);
        }
    }

    @Override // com.xiaomi.push.C0632fm.a
    /* renamed from: a */
    public boolean mo1275a() {
        return this.f921a;
    }
}
