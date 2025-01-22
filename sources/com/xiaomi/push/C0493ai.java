package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.push.ai */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ai.class */
public class C0493ai {

    /* renamed from: a */
    private static volatile C0493ai f418a;

    /* renamed from: a */
    private SharedPreferences f419a;

    /* renamed from: a */
    private ScheduledThreadPoolExecutor f422a = new ScheduledThreadPoolExecutor(1);

    /* renamed from: a */
    private SparseArray<ScheduledFuture> f420a = new SparseArray<>();

    /* renamed from: a */
    private Object f421a = new Object();

    /* renamed from: com.xiaomi.push.ai$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ai$a.class */
    public static abstract class a implements Runnable {
        /* renamed from: a */
        public abstract int mo185a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.xiaomi.push.ai$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ai$b.class */
    public static class b implements Runnable {

        /* renamed from: a */
        a f423a;

        public b(a aVar) {
            this.f423a = aVar;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo449a() {
        }

        /* renamed from: b */
        void mo450b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            mo449a();
            this.f423a.run();
            mo450b();
        }
    }

    private C0493ai(Context context) {
        this.f419a = context.getSharedPreferences("mipush_extra", 0);
    }

    /* renamed from: a */
    public static C0493ai m439a(Context context) {
        if (f418a == null) {
            synchronized (C0493ai.class) {
                try {
                    if (f418a == null) {
                        f418a = new C0493ai(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f418a;
    }

    /* renamed from: a */
    private static String m441a(int i) {
        return "last_job_time" + i;
    }

    /* renamed from: a */
    private ScheduledFuture m442a(a aVar) {
        ScheduledFuture scheduledFuture;
        synchronized (this.f421a) {
            scheduledFuture = this.f420a.get(aVar.mo185a());
        }
        return scheduledFuture;
    }

    /* renamed from: a */
    public void m443a(Runnable runnable) {
        m444a(runnable, 0);
    }

    /* renamed from: a */
    public void m444a(Runnable runnable, int i) {
        this.f422a.schedule(runnable, i, TimeUnit.SECONDS);
    }

    /* renamed from: a */
    public boolean m445a(int i) {
        synchronized (this.f421a) {
            ScheduledFuture scheduledFuture = this.f420a.get(i);
            if (scheduledFuture == null) {
                return false;
            }
            this.f420a.remove(i);
            return scheduledFuture.cancel(false);
        }
    }

    /* renamed from: a */
    public boolean m446a(a aVar, int i) {
        return m447a(aVar, i, 0);
    }

    /* renamed from: a */
    public boolean m447a(a aVar, int i, int i2) {
        if (aVar == null || m442a(aVar) != null) {
            return false;
        }
        String m441a = m441a(aVar.mo185a());
        C0494aj c0494aj = new C0494aj(this, aVar, m441a);
        long abs = Math.abs(System.currentTimeMillis() - this.f419a.getLong(m441a, 0L)) / 1000;
        int i3 = i2;
        if (abs < i - i2) {
            i3 = (int) (i - abs);
        }
        ScheduledFuture<?> scheduleAtFixedRate = this.f422a.scheduleAtFixedRate(c0494aj, i3, i, TimeUnit.SECONDS);
        synchronized (this.f421a) {
            this.f420a.put(aVar.mo185a(), scheduleAtFixedRate);
        }
        return true;
    }

    /* renamed from: b */
    public boolean m448b(a aVar, int i) {
        if (aVar == null || m442a(aVar) != null) {
            return false;
        }
        ScheduledFuture<?> schedule = this.f422a.schedule(new C0495ak(this, aVar), i, TimeUnit.SECONDS);
        synchronized (this.f421a) {
            this.f420a.put(aVar.mo185a(), schedule);
        }
        return true;
    }
}
