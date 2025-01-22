package com.xiaomi.push.service;

import android.os.SystemClock;
import com.sun.jna.Function;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: com.xiaomi.push.service.k */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/k.class */
public class C0860k {

    /* renamed from: a */
    private static long f2654a;

    /* renamed from: b */
    private static long f2655b;

    /* renamed from: c */
    private static long f2656c;

    /* renamed from: a */
    private final a f2657a;

    /* renamed from: a */
    private final c f2658a;

    /* renamed from: com.xiaomi.push.service.k$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/k$a.class */
    private static final class a {

        /* renamed from: a */
        private final c f2659a;

        a(c cVar) {
            this.f2659a = cVar;
        }

        protected void finalize() {
            try {
                synchronized (this.f2659a) {
                    this.f2659a.f2666c = true;
                    this.f2659a.notify();
                }
            } finally {
                super.finalize();
            }
        }
    }

    /* renamed from: com.xiaomi.push.service.k$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/k$b.class */
    public static abstract class b implements Runnable {

        /* renamed from: a */
        protected int f2660a;

        public b(int i) {
            this.f2660a = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.xiaomi.push.service.k$c */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/k$c.class */
    public static final class c extends Thread {

        /* renamed from: b */
        private boolean f2665b;

        /* renamed from: c */
        private boolean f2666c;

        /* renamed from: a */
        private volatile long f2661a = 0;

        /* renamed from: a */
        private volatile boolean f2663a = false;

        /* renamed from: b */
        private long f2664b = 50;

        /* renamed from: a */
        private a f2662a = new a();

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.xiaomi.push.service.k$c$a */
        /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/k$c$a.class */
        public static final class a {

            /* renamed from: a */
            private int f2667a;

            /* renamed from: a */
            private d[] f2668a;

            /* renamed from: b */
            private int f2669b;

            /* renamed from: c */
            private int f2670c;

            private a() {
                this.f2667a = Function.MAX_NARGS;
                this.f2668a = new d[this.f2667a];
                this.f2669b = 0;
                this.f2670c = 0;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: a */
            public int m2743a(d dVar) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    d[] dVarArr = this.f2668a;
                    if (i2 >= dVarArr.length) {
                        return -1;
                    }
                    if (dVarArr[i2] == dVar) {
                        return i2;
                    }
                    i = i2 + 1;
                }
            }

            /* renamed from: c */
            private void m2744c() {
                int i = this.f2669b - 1;
                int i2 = (i - 1) / 2;
                while (true) {
                    int i3 = i2;
                    if (this.f2668a[i].f2672a >= this.f2668a[i3].f2672a) {
                        return;
                    }
                    d[] dVarArr = this.f2668a;
                    d dVar = dVarArr[i];
                    dVarArr[i] = dVarArr[i3];
                    dVarArr[i3] = dVar;
                    i = i3;
                    i2 = (i3 - 1) / 2;
                }
            }

            /* renamed from: c */
            private void m2745c(int i) {
                int i2 = i;
                int i3 = (i * 2) + 1;
                while (true) {
                    int i4 = this.f2669b;
                    if (i3 >= i4 || i4 <= 0) {
                        return;
                    }
                    int i5 = i3 + 1;
                    int i6 = i3;
                    if (i5 < i4) {
                        i6 = i3;
                        if (this.f2668a[i5].f2672a < this.f2668a[i3].f2672a) {
                            i6 = i5;
                        }
                    }
                    if (this.f2668a[i2].f2672a < this.f2668a[i6].f2672a) {
                        return;
                    }
                    d[] dVarArr = this.f2668a;
                    d dVar = dVarArr[i2];
                    dVarArr[i2] = dVarArr[i6];
                    dVarArr[i6] = dVar;
                    i3 = (i6 * 2) + 1;
                    i2 = i6;
                }
            }

            /* renamed from: a */
            public d m2746a() {
                return this.f2668a[0];
            }

            /* renamed from: a */
            public void m2747a() {
                this.f2668a = new d[this.f2667a];
                this.f2669b = 0;
            }

            /* renamed from: a */
            public void m2748a(int i) {
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= this.f2669b) {
                        m2753b();
                        return;
                    } else {
                        if (this.f2668a[i3].f2671a == i) {
                            this.f2668a[i3].m2756a();
                        }
                        i2 = i3 + 1;
                    }
                }
            }

            /* renamed from: a */
            public void m2749a(int i, b bVar) {
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= this.f2669b) {
                        m2753b();
                        return;
                    } else {
                        if (this.f2668a[i3].f2673a == bVar) {
                            this.f2668a[i3].m2756a();
                        }
                        i2 = i3 + 1;
                    }
                }
            }

            /* renamed from: a */
            public void m2750a(d dVar) {
                d[] dVarArr = this.f2668a;
                int length = dVarArr.length;
                int i = this.f2669b;
                if (length == i) {
                    d[] dVarArr2 = new d[i * 2];
                    System.arraycopy(dVarArr, 0, dVarArr2, 0, i);
                    this.f2668a = dVarArr2;
                }
                d[] dVarArr3 = this.f2668a;
                int i2 = this.f2669b;
                this.f2669b = i2 + 1;
                dVarArr3[i2] = dVar;
                m2744c();
            }

            /* renamed from: a */
            public boolean m2751a() {
                return this.f2669b == 0;
            }

            /* renamed from: a */
            public boolean m2752a(int i) {
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= this.f2669b) {
                        return false;
                    }
                    if (this.f2668a[i3].f2671a == i) {
                        return true;
                    }
                    i2 = i3 + 1;
                }
            }

            /* renamed from: b */
            public void m2753b() {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.f2669b) {
                        return;
                    }
                    int i3 = i2;
                    if (this.f2668a[i2].f2675a) {
                        this.f2670c++;
                        m2754b(i2);
                        i3 = i2 - 1;
                    }
                    i = i3 + 1;
                }
            }

            /* renamed from: b */
            public void m2754b(int i) {
                int i2;
                if (i < 0 || i >= (i2 = this.f2669b)) {
                    return;
                }
                d[] dVarArr = this.f2668a;
                int i3 = i2 - 1;
                this.f2669b = i3;
                dVarArr[i] = dVarArr[i3];
                dVarArr[this.f2669b] = null;
                m2745c(i);
            }
        }

        c(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m2737a(d dVar) {
            this.f2662a.m2750a(dVar);
            notify();
        }

        /* renamed from: a */
        public void m2740a() {
            synchronized (this) {
                this.f2665b = true;
                this.f2662a.m2747a();
                notify();
            }
        }

        /* renamed from: a */
        public boolean m2741a() {
            return this.f2663a && SystemClock.uptimeMillis() - this.f2661a > 600000;
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x010e, code lost:            r6.f2661a = android.os.SystemClock.uptimeMillis();        r6.f2663a = true;        r0.f2673a.run();        r6.f2663a = false;     */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0129, code lost:            r12 = move-exception;     */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x012c, code lost:            monitor-enter(r6);     */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x012d, code lost:            r6.f2665b = true;     */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0136, code lost:            throw r12;     */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 351
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0860k.c.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.k$d */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/k$d.class */
    public static class d {

        /* renamed from: a */
        int f2671a;

        /* renamed from: a */
        long f2672a;

        /* renamed from: a */
        b f2673a;

        /* renamed from: a */
        final Object f2674a = new Object();

        /* renamed from: a */
        boolean f2675a;

        /* renamed from: b */
        private long f2676b;

        d() {
        }

        /* renamed from: a */
        void m2755a(long j) {
            synchronized (this.f2674a) {
                this.f2676b = j;
            }
        }

        /* renamed from: a */
        public boolean m2756a() {
            boolean z;
            synchronized (this.f2674a) {
                z = !this.f2675a && this.f2672a > 0;
                this.f2675a = true;
            }
            return z;
        }
    }

    static {
        long j = 0;
        if (SystemClock.elapsedRealtime() > 0) {
            j = SystemClock.elapsedRealtime();
        }
        f2654a = j;
        f2655b = f2654a;
    }

    public C0860k() {
        this(false);
    }

    public C0860k(String str) {
        this(str, false);
    }

    public C0860k(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        this.f2658a = new c(str, z);
        this.f2657a = new a(this.f2658a);
    }

    public C0860k(boolean z) {
        this("Timer-" + m2725b(), z);
    }

    /* renamed from: a */
    static long m2724a() {
        long j;
        synchronized (C0860k.class) {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (elapsedRealtime > f2655b) {
                    f2654a += elapsedRealtime - f2655b;
                }
                f2655b = elapsedRealtime;
                j = f2654a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    /* renamed from: b */
    private static long m2725b() {
        long j;
        synchronized (C0860k.class) {
            try {
                j = f2656c;
                f2656c = 1 + j;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    /* renamed from: b */
    private void m2726b(b bVar, long j) {
        synchronized (this.f2658a) {
            if (this.f2658a.f2665b) {
                throw new IllegalStateException("Timer was canceled");
            }
            long m2724a = j + m2724a();
            if (m2724a < 0) {
                throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + m2724a);
            }
            d dVar = new d();
            dVar.f2671a = bVar.f2660a;
            dVar.f2673a = bVar;
            dVar.f2672a = m2724a;
            this.f2658a.m2737a(dVar);
        }
    }

    /* renamed from: a */
    public void m2727a() {
        this.f2658a.m2740a();
    }

    /* renamed from: a */
    public void m2728a(int i) {
        synchronized (this.f2658a) {
            this.f2658a.f2662a.m2748a(i);
        }
    }

    /* renamed from: a */
    public void m2729a(int i, b bVar) {
        synchronized (this.f2658a) {
            this.f2658a.f2662a.m2749a(i, bVar);
        }
    }

    /* renamed from: a */
    public void m2730a(b bVar) {
        if (AbstractC0407b.m62a() >= 1 || Thread.currentThread() == this.f2658a) {
            bVar.run();
        } else {
            AbstractC0407b.m75d("run job outside job job thread");
            throw new RejectedExecutionException("Run job outside job thread");
        }
    }

    /* renamed from: a */
    public void m2731a(b bVar, long j) {
        if (j >= 0) {
            m2726b(bVar, j);
            return;
        }
        throw new IllegalArgumentException("delay < 0: " + j);
    }

    /* renamed from: a */
    public boolean m2732a() {
        return this.f2658a.m2741a();
    }

    /* renamed from: a */
    public boolean m2733a(int i) {
        boolean m2752a;
        synchronized (this.f2658a) {
            m2752a = this.f2658a.f2662a.m2752a(i);
        }
        return m2752a;
    }

    /* renamed from: b */
    public void m2734b() {
        synchronized (this.f2658a) {
            this.f2658a.f2662a.m2747a();
        }
    }
}
