package com.xiaomi.push;

import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.AbstractC0818ax;
import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.XMPushService;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.xiaomi.push.gc */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gc.class */
public abstract class AbstractC0649gc {

    /* renamed from: a */
    private static final AtomicInteger f1055a = new AtomicInteger(0);

    /* renamed from: a */
    public static boolean f1056a;

    /* renamed from: a */
    protected C0650gd f1059a;

    /* renamed from: a */
    protected XMPushService f1061a;

    /* renamed from: a */
    protected int f1057a = 0;

    /* renamed from: a */
    protected long f1058a = -1;

    /* renamed from: b */
    protected volatile long f1067b = 0;

    /* renamed from: c */
    protected volatile long f1071c = 0;

    /* renamed from: a */
    private LinkedList<Pair<Integer, Long>> f1064a = new LinkedList<>();

    /* renamed from: a */
    private final Collection<InterfaceC0652gf> f1063a = new CopyOnWriteArrayList();

    /* renamed from: a */
    protected final Map<InterfaceC0654gh, a> f1065a = new ConcurrentHashMap();

    /* renamed from: b */
    protected final Map<InterfaceC0654gh, a> f1069b = new ConcurrentHashMap();

    /* renamed from: a */
    protected InterfaceC0661go f1060a = null;

    /* renamed from: a */
    protected String f1062a = "";

    /* renamed from: b */
    protected String f1068b = "";

    /* renamed from: c */
    private int f1070c = 2;

    /* renamed from: b */
    protected final int f1066b = f1055a.getAndIncrement();

    /* renamed from: e */
    private long f1073e = 0;

    /* renamed from: d */
    protected long f1072d = 0;

    /* renamed from: com.xiaomi.push.gc$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gc$a.class */
    public static class a {

        /* renamed from: a */
        private InterfaceC0654gh f1074a;

        /* renamed from: a */
        private InterfaceC0662gp f1075a;

        public a(InterfaceC0654gh interfaceC0654gh, InterfaceC0662gp interfaceC0662gp) {
            this.f1074a = interfaceC0654gh;
            this.f1075a = interfaceC0662gp;
        }

        /* renamed from: a */
        public void m1409a(C0641fv c0641fv) {
            this.f1074a.mo583a(c0641fv);
        }

        /* renamed from: a */
        public void m1410a(AbstractC0666gt abstractC0666gt) {
            InterfaceC0662gp interfaceC0662gp = this.f1075a;
            if (interfaceC0662gp == null || interfaceC0662gp.mo585a(abstractC0666gt)) {
                this.f1074a.mo584a(abstractC0666gt);
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:7:0x001c
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    static {
        /*
            java.util.concurrent.atomic.AtomicInteger r0 = new java.util.concurrent.atomic.AtomicInteger
            r1 = r0
            r2 = 0
            r1.<init>(r2)
            com.xiaomi.push.AbstractC0649gc.f1055a = r0
            r0 = 0
            com.xiaomi.push.AbstractC0649gc.f1056a = r0
            java.lang.String r0 = "smack.debugEnabled"
            boolean r0 = java.lang.Boolean.getBoolean(r0)     // Catch: java.lang.Exception -> L1c
            com.xiaomi.push.AbstractC0649gc.f1056a = r0     // Catch: java.lang.Exception -> L1c
        L17:
            java.lang.String r0 = com.xiaomi.push.C0655gi.m1425a()     // Catch: java.lang.Exception -> L1c
            return
        L1c:
            r4 = move-exception
            goto L17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.AbstractC0649gc.m3990clinit():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC0649gc(XMPushService xMPushService, C0650gd c0650gd) {
        this.f1059a = c0650gd;
        this.f1061a = xMPushService;
        m1403c();
    }

    /* renamed from: a */
    private String m1384a(int i) {
        return i == 1 ? "connected" : i == 0 ? "connecting" : i == 2 ? "disconnected" : "unknown";
    }

    /* renamed from: a */
    private void m1385a(int i) {
        synchronized (this.f1064a) {
            if (i == 1) {
                this.f1064a.clear();
            } else {
                this.f1064a.add(new Pair<>(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())));
                if (this.f1064a.size() > 6) {
                    this.f1064a.remove(0);
                }
            }
        }
    }

    /* renamed from: a */
    public int m1386a() {
        return this.f1057a;
    }

    /* renamed from: a */
    public long m1387a() {
        return this.f1071c;
    }

    /* renamed from: a */
    public C0650gd m1388a() {
        return this.f1059a;
    }

    /* renamed from: a */
    public String mo1389a() {
        return this.f1059a.m1420c();
    }

    /* renamed from: a */
    public void m1390a(int i, int i2, Exception exc) {
        int i3 = this.f1070c;
        if (i != i3) {
            AbstractC0407b.m70a(String.format("update the connection status. %1$s -> %2$s : %3$s ", m1384a(i3), m1384a(i), AbstractC0818ax.m2610a(i2)));
        }
        if (C0503as.m484b(this.f1061a)) {
            m1385a(i);
        }
        if (i == 1) {
            this.f1061a.m2465a(10);
            if (this.f1070c != 0) {
                AbstractC0407b.m70a("try set connected while not connecting.");
            }
            this.f1070c = i;
            Iterator<InterfaceC0652gf> it = this.f1063a.iterator();
            while (it.hasNext()) {
                it.next().mo586a(this);
            }
            return;
        }
        if (i == 0) {
            if (this.f1070c != 2) {
                AbstractC0407b.m70a("try set connecting while not disconnected.");
            }
            this.f1070c = i;
            Iterator<InterfaceC0652gf> it2 = this.f1063a.iterator();
            while (it2.hasNext()) {
                it2.next().mo589b(this);
            }
            return;
        }
        if (i == 2) {
            this.f1061a.m2465a(10);
            int i4 = this.f1070c;
            if (i4 == 0) {
                Iterator<InterfaceC0652gf> it3 = this.f1063a.iterator();
                while (it3.hasNext()) {
                    it3.next().mo588a(this, exc == null ? new CancellationException("disconnect while connecting") : exc);
                }
            } else if (i4 == 1) {
                Iterator<InterfaceC0652gf> it4 = this.f1063a.iterator();
                while (it4.hasNext()) {
                    it4.next().mo587a(this, i2, exc);
                }
            }
            this.f1070c = i;
        }
    }

    /* renamed from: a */
    public void m1391a(InterfaceC0652gf interfaceC0652gf) {
        if (interfaceC0652gf == null || this.f1063a.contains(interfaceC0652gf)) {
            return;
        }
        this.f1063a.add(interfaceC0652gf);
    }

    /* renamed from: a */
    public void m1392a(InterfaceC0654gh interfaceC0654gh, InterfaceC0662gp interfaceC0662gp) {
        if (interfaceC0654gh == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f1065a.put(interfaceC0654gh, new a(interfaceC0654gh, interfaceC0662gp));
    }

    /* renamed from: a */
    public abstract void mo1375a(AbstractC0666gt abstractC0666gt);

    /* renamed from: a */
    public abstract void mo1376a(C0814at.b bVar);

    /* renamed from: a */
    public void m1393a(String str) {
        synchronized (this) {
            if (this.f1070c == 0) {
                AbstractC0407b.m70a("setChallenge hash = " + C0508ax.m518a(str).substring(0, 8));
                this.f1062a = str;
                m1390a(1, 0, null);
            } else {
                AbstractC0407b.m70a("ignore setChallenge because connection was disconnected");
            }
        }
    }

    /* renamed from: a */
    public abstract void mo1377a(String str, String str2);

    /* renamed from: a */
    public abstract void mo1379a(C0641fv[] c0641fvArr);

    /* renamed from: a */
    public boolean mo1380a() {
        return false;
    }

    /* renamed from: a */
    public boolean m1394a(long j) {
        boolean z;
        synchronized (this) {
            z = this.f1073e >= j;
        }
        return z;
    }

    /* renamed from: b */
    public int m1395b() {
        return this.f1070c;
    }

    /* renamed from: b */
    public String m1396b() {
        return this.f1059a.m1418b();
    }

    /* renamed from: b */
    public void m1397b() {
        mo1398b(0, (Exception) null);
    }

    /* renamed from: b */
    public abstract void mo1398b(int i, Exception exc);

    /* renamed from: b */
    public abstract void mo1382b(C0641fv c0641fv);

    /* renamed from: b */
    public void m1399b(InterfaceC0652gf interfaceC0652gf) {
        this.f1063a.remove(interfaceC0652gf);
    }

    /* renamed from: b */
    public void m1400b(InterfaceC0654gh interfaceC0654gh, InterfaceC0662gp interfaceC0662gp) {
        if (interfaceC0654gh == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f1069b.put(interfaceC0654gh, new a(interfaceC0654gh, interfaceC0662gp));
    }

    /* renamed from: b */
    public abstract void mo1401b(boolean z);

    /* renamed from: b */
    public boolean m1402b() {
        return this.f1070c == 0;
    }

    /* renamed from: c */
    protected void m1403c() {
        String str;
        if (this.f1059a.m1416a() && this.f1060a == null) {
            try {
                str = System.getProperty("smack.debuggerClass");
            } catch (Throwable th) {
                str = null;
            }
            Class<?> cls = null;
            if (str != null) {
                try {
                    cls = Class.forName(str);
                } catch (Exception e) {
                    e.printStackTrace();
                    cls = null;
                }
            }
            if (cls == null) {
                this.f1060a = new C0520bi(this);
                return;
            }
            try {
                this.f1060a = (InterfaceC0661go) cls.getConstructor(AbstractC0649gc.class, Writer.class, Reader.class).newInstance(this);
            } catch (Exception e2) {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", e2);
            }
        }
    }

    /* renamed from: c */
    public boolean m1404c() {
        return this.f1070c == 1;
    }

    /* renamed from: d */
    public void m1405d() {
        synchronized (this) {
            this.f1073e = System.currentTimeMillis();
        }
    }

    /* renamed from: d */
    public boolean m1406d() {
        boolean z;
        synchronized (this) {
            z = System.currentTimeMillis() - this.f1073e < ((long) C0655gi.m1423a());
        }
        return z;
    }

    /* renamed from: e */
    public void m1407e() {
        synchronized (this.f1064a) {
            this.f1064a.clear();
        }
    }

    /* renamed from: e */
    public boolean m1408e() {
        boolean z;
        synchronized (this) {
            z = true;
            if (System.currentTimeMillis() - this.f1072d >= (C0655gi.m1423a() << 1)) {
                z = false;
            }
        }
        return z;
    }
}
