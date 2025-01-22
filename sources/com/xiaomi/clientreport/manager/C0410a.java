package com.xiaomi.clientreport.manager;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.data.C0408a;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0512ba;
import com.xiaomi.push.C0513bb;
import com.xiaomi.push.C0516be;
import com.xiaomi.push.RunnableC0510az;
import com.xiaomi.push.RunnableC0514bc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.xiaomi.clientreport.manager.a */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/manager/a.class */
public class C0410a {

    /* renamed from: a */
    private static volatile C0410a f225a;

    /* renamed from: a */
    private Context f226a;

    /* renamed from: a */
    private Config f227a;

    /* renamed from: a */
    private IEventProcessor f228a;

    /* renamed from: a */
    private IPerfProcessor f229a;

    /* renamed from: a */
    private ExecutorService f231a = Executors.newSingleThreadExecutor();

    /* renamed from: a */
    private HashMap<String, HashMap<String, C0408a>> f230a = new HashMap<>();

    /* renamed from: b */
    private HashMap<String, ArrayList<C0408a>> f232b = new HashMap<>();

    private C0410a(Context context) {
        this.f226a = context;
    }

    /* renamed from: a */
    public static C0410a m77a(Context context) {
        if (f225a == null) {
            synchronized (C0410a.class) {
                try {
                    if (f225a == null) {
                        f225a = new C0410a(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f225a;
    }

    /* renamed from: a */
    private void m81a(Runnable runnable, int i) {
        C0493ai.m439a(this.f226a).m444a(runnable, i);
    }

    /* renamed from: d */
    private void m82d() {
        int m574b = C0516be.m574b(this.f226a);
        int eventUploadFrequency = (int) m84a().getEventUploadFrequency();
        if (m574b >= 0) {
            synchronized (C0410a.class) {
                try {
                    if (!C0493ai.m439a(this.f226a).m447a(new C0512ba(this.f226a), eventUploadFrequency, m574b)) {
                        C0493ai.m439a(this.f226a).m445a(100886);
                        C0493ai.m439a(this.f226a).m447a(new C0512ba(this.f226a), eventUploadFrequency, m574b);
                    }
                } finally {
                }
            }
        }
    }

    /* renamed from: e */
    private void m83e() {
        int m564a = C0516be.m564a(this.f226a);
        int perfUploadFrequency = (int) m84a().getPerfUploadFrequency();
        if (m564a >= 0) {
            synchronized (C0410a.class) {
                try {
                    if (!C0493ai.m439a(this.f226a).m447a(new C0513bb(this.f226a), perfUploadFrequency, m564a)) {
                        C0493ai.m439a(this.f226a).m445a(100887);
                        C0493ai.m439a(this.f226a).m447a(new C0513bb(this.f226a), perfUploadFrequency, m564a);
                    }
                } finally {
                }
            }
        }
    }

    /* renamed from: a */
    public Config m84a() {
        Config config;
        synchronized (this) {
            if (this.f227a == null) {
                this.f227a = Config.defaultConfig(this.f226a);
            }
            config = this.f227a;
        }
        return config;
    }

    /* renamed from: a */
    public void m85a() {
        m77a(this.f226a).m82d();
        m77a(this.f226a).m83e();
    }

    /* renamed from: a */
    public void m86a(Config config, IEventProcessor iEventProcessor, IPerfProcessor iPerfProcessor) {
        this.f227a = config;
        this.f228a = iEventProcessor;
        this.f229a = iPerfProcessor;
        this.f228a.setEventMap(this.f232b);
        this.f229a.setPerfMap(this.f230a);
    }

    /* renamed from: a */
    public void m87a(EventClientReport eventClientReport) {
        if (m84a().isEventUploadSwitchOpen()) {
            this.f231a.execute(new RunnableC0510az(this.f226a, eventClientReport, this.f228a));
            m81a(new RunnableC0411b(this), 30);
        }
    }

    /* renamed from: a */
    public void m88a(PerfClientReport perfClientReport) {
        if (m84a().isPerfUploadSwitchOpen()) {
            this.f231a.execute(new RunnableC0510az(this.f226a, perfClientReport, this.f229a));
            m81a(new RunnableC0412c(this), 30);
        }
    }

    /* renamed from: a */
    public void m89a(boolean z, boolean z2, long j, long j2) {
        Config config = this.f227a;
        if (config != null) {
            if (z == config.isEventUploadSwitchOpen() && z2 == this.f227a.isPerfUploadSwitchOpen() && j == this.f227a.getEventUploadFrequency() && j2 == this.f227a.getPerfUploadFrequency()) {
                return;
            }
            long eventUploadFrequency = this.f227a.getEventUploadFrequency();
            long perfUploadFrequency = this.f227a.getPerfUploadFrequency();
            Config build = Config.getBuilder().setAESKey(C0516be.m566a(this.f226a)).setEventEncrypted(this.f227a.isEventEncrypted()).setEventUploadSwitchOpen(z).setEventUploadFrequency(j).setPerfUploadSwitchOpen(z2).setPerfUploadFrequency(j2).build(this.f226a);
            this.f227a = build;
            if (!this.f227a.isEventUploadSwitchOpen()) {
                C0493ai.m439a(this.f226a).m445a(100886);
            } else if (eventUploadFrequency != build.getEventUploadFrequency()) {
                AbstractC0407b.m74c(this.f226a.getPackageName() + "reset event job " + build.getEventUploadFrequency());
                m82d();
            }
            if (!this.f227a.isPerfUploadSwitchOpen()) {
                C0493ai.m439a(this.f226a).m445a(100887);
                return;
            }
            if (perfUploadFrequency != build.getPerfUploadFrequency()) {
                AbstractC0407b.m74c(this.f226a.getPackageName() + "reset perf job " + build.getPerfUploadFrequency());
                m83e();
            }
        }
    }

    /* renamed from: b */
    public void m90b() {
        if (m84a().isEventUploadSwitchOpen()) {
            RunnableC0514bc runnableC0514bc = new RunnableC0514bc();
            runnableC0514bc.m562a(this.f226a);
            runnableC0514bc.m563a(this.f228a);
            this.f231a.execute(runnableC0514bc);
        }
    }

    /* renamed from: c */
    public void m91c() {
        if (m84a().isPerfUploadSwitchOpen()) {
            RunnableC0514bc runnableC0514bc = new RunnableC0514bc();
            runnableC0514bc.m563a(this.f229a);
            runnableC0514bc.m562a(this.f226a);
            this.f231a.execute(runnableC0514bc);
        }
    }
}
