package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0504at;
import com.xiaomi.push.C0632fm;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMJobService.class */
public class XMJobService extends Service {

    /* renamed from: a */
    static Service f2381a;

    /* renamed from: a */
    private IBinder f2382a = null;

    @TargetApi(21)
    /* renamed from: com.xiaomi.push.service.XMJobService$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMJobService$a.class */
    static class JobServiceC0777a extends JobService {

        /* renamed from: a */
        Binder f2383a;

        /* renamed from: a */
        private Handler f2384a;

        /* renamed from: com.xiaomi.push.service.XMJobService$a$a */
        /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/XMJobService$a$a.class */
        private static class a extends Handler {

            /* renamed from: a */
            JobService f2385a;

            a(JobService jobService) {
                super(jobService.getMainLooper());
                this.f2385a = jobService;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                JobParameters jobParameters = (JobParameters) message.obj;
                AbstractC0407b.m70a("Job finished " + jobParameters.getJobId());
                this.f2385a.jobFinished(jobParameters, false);
                if (jobParameters.getJobId() == 1) {
                    C0632fm.m1271a(false);
                }
            }
        }

        JobServiceC0777a(Service service) {
            this.f2383a = null;
            this.f2383a = (Binder) C0504at.m495a(this, "onBind", new Intent());
            C0504at.m495a(this, "attachBaseContext", service);
        }

        @Override // android.app.job.JobService
        public boolean onStartJob(JobParameters jobParameters) {
            AbstractC0407b.m70a("Job started " + jobParameters.getJobId());
            Intent intent = new Intent(this, (Class<?>) XMPushService.class);
            intent.setAction("com.xiaomi.push.timer");
            intent.setPackage(getPackageName());
            startService(intent);
            if (this.f2384a == null) {
                this.f2384a = new a(this);
            }
            Handler handler = this.f2384a;
            handler.sendMessage(Message.obtain(handler, 1, jobParameters));
            return true;
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters jobParameters) {
            AbstractC0407b.m70a("Job stop " + jobParameters.getJobId());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static Service m2421a() {
        return f2381a;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        IBinder iBinder = this.f2382a;
        return iBinder != null ? iBinder : new Binder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2382a = new JobServiceC0777a(this).f2383a;
        }
        f2381a = this;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        f2381a = null;
    }
}
