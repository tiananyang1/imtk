package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.push.al */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/al.class */
public class C0496al {

    /* renamed from: a */
    private int f427a;

    /* renamed from: a */
    private Handler f428a;

    /* renamed from: a */
    private a f429a;

    /* renamed from: a */
    private volatile b f430a;

    /* renamed from: a */
    private volatile boolean f431a;

    /* renamed from: b */
    private final boolean f432b;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.xiaomi.push.al$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/al$a.class */
    public class a extends Thread {

        /* renamed from: a */
        private final LinkedBlockingQueue<b> f434a;

        public a() {
            super("PackageProcessor");
            this.f434a = new LinkedBlockingQueue<>();
        }

        /* renamed from: a */
        private void m459a(int i, b bVar) {
            C0496al.this.f428a.sendMessage(C0496al.this.f428a.obtainMessage(i, bVar));
        }

        /* renamed from: a */
        public void m460a(b bVar) {
            try {
                this.f434a.add(bVar);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            long j = C0496al.this.f427a > 0 ? C0496al.this.f427a : Long.MAX_VALUE;
            while (!C0496al.this.f431a) {
                try {
                    b poll = this.f434a.poll(j, TimeUnit.SECONDS);
                    C0496al.this.f430a = poll;
                    if (poll != null) {
                        m459a(0, poll);
                        poll.mo462b();
                        m459a(1, poll);
                    } else if (C0496al.this.f427a > 0) {
                        C0496al.this.m454a();
                    }
                } catch (InterruptedException e) {
                    AbstractC0407b.m72a(e);
                }
            }
        }
    }

    /* renamed from: com.xiaomi.push.al$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/al$b.class */
    public static abstract class b {
        /* renamed from: a */
        public void m461a() {
        }

        /* renamed from: b */
        public abstract void mo462b();

        /* renamed from: c */
        public void mo463c() {
        }
    }

    public C0496al() {
        this(false);
    }

    public C0496al(boolean z) {
        this(z, 0);
    }

    public C0496al(boolean z, int i) {
        this.f428a = null;
        this.f431a = false;
        this.f427a = 0;
        this.f428a = new HandlerC0497am(this, Looper.getMainLooper());
        this.f432b = z;
        this.f427a = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m454a() {
        synchronized (this) {
            this.f429a = null;
            this.f431a = true;
        }
    }

    /* renamed from: a */
    public void m457a(b bVar) {
        synchronized (this) {
            if (this.f429a == null) {
                this.f429a = new a();
                this.f429a.setDaemon(this.f432b);
                this.f431a = false;
                this.f429a.start();
            }
            this.f429a.m460a(bVar);
        }
    }

    /* renamed from: a */
    public void m458a(b bVar, long j) {
        this.f428a.postDelayed(new RunnableC0498an(this, bVar), j);
    }
}
