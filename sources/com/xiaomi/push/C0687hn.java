package com.xiaomi.push;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.hn */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hn.class */
public class C0687hn implements InterfaceC0652gf {

    /* renamed from: a */
    private int f1386a;

    /* renamed from: a */
    AbstractC0649gc f1388a;

    /* renamed from: a */
    XMPushService f1389a;

    /* renamed from: a */
    private Exception f1390a;

    /* renamed from: e */
    private long f1395e;

    /* renamed from: f */
    private long f1396f;

    /* renamed from: a */
    private long f1387a = 0;

    /* renamed from: b */
    private long f1392b = 0;

    /* renamed from: c */
    private long f1393c = 0;

    /* renamed from: d */
    private long f1394d = 0;

    /* renamed from: a */
    private String f1391a = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0687hn(XMPushService xMPushService) {
        this.f1395e = 0L;
        this.f1396f = 0L;
        this.f1389a = xMPushService;
        m1561b();
        int myUid = Process.myUid();
        this.f1396f = TrafficStats.getUidRxBytes(myUid);
        this.f1395e = TrafficStats.getUidTxBytes(myUid);
    }

    /* renamed from: b */
    private void m1561b() {
        this.f1392b = 0L;
        this.f1394d = 0L;
        this.f1387a = 0L;
        this.f1393c = 0L;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (C0503as.m484b(this.f1389a)) {
            this.f1387a = elapsedRealtime;
        }
        if (this.f1389a.m2483c()) {
            this.f1393c = elapsedRealtime;
        }
    }

    /* renamed from: c */
    private void m1562c() {
        synchronized (this) {
            AbstractC0407b.m74c("stat connpt = " + this.f1391a + " netDuration = " + this.f1392b + " ChannelDuration = " + this.f1394d + " channelConnectedTime = " + this.f1393c);
            C0638fs c0638fs = new C0638fs();
            c0638fs.f1003a = (byte) 0;
            c0638fs.m1284a(EnumC0637fr.CHANNEL_ONLINE_RATE.m1281a());
            c0638fs.m1285a(this.f1391a);
            c0638fs.m1300d((int) (System.currentTimeMillis() / 1000));
            c0638fs.m1291b((int) (this.f1392b / 1000));
            c0638fs.m1296c((int) (this.f1394d / 1000));
            C0688ho.m1568a().m1573a(c0638fs);
            m1561b();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Exception m1563a() {
        return this.f1390a;
    }

    /* renamed from: a */
    public void m1564a() {
        synchronized (this) {
            if (this.f1389a == null) {
                return;
            }
            String m475a = C0503as.m475a((Context) this.f1389a);
            boolean m484b = C0503as.m484b(this.f1389a);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.f1387a > 0) {
                this.f1392b += elapsedRealtime - this.f1387a;
                this.f1387a = 0L;
            }
            if (this.f1393c != 0) {
                this.f1394d += elapsedRealtime - this.f1393c;
                this.f1393c = 0L;
            }
            if (m484b) {
                if ((!TextUtils.equals(this.f1391a, m475a) && this.f1392b > 30000) || this.f1392b > 5400000) {
                    m1562c();
                }
                this.f1391a = m475a;
                if (this.f1387a == 0) {
                    this.f1387a = elapsedRealtime;
                }
                if (this.f1389a.m2483c()) {
                    this.f1393c = elapsedRealtime;
                }
            }
        }
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo586a(AbstractC0649gc abstractC0649gc) {
        m1564a();
        this.f1393c = SystemClock.elapsedRealtime();
        C0690hq.m1582a(0, EnumC0637fr.CONN_SUCCESS.m1281a(), abstractC0649gc.mo1389a(), abstractC0649gc.m1386a());
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo587a(AbstractC0649gc abstractC0649gc, int i, Exception exc) {
        if (this.f1386a == 0 && this.f1390a == null) {
            this.f1386a = i;
            this.f1390a = exc;
            C0690hq.m1588b(abstractC0649gc.mo1389a(), exc);
        }
        if (i == 22 && this.f1393c != 0) {
            long m1387a = abstractC0649gc.m1387a() - this.f1393c;
            long j = m1387a;
            if (m1387a < 0) {
                j = 0;
            }
            this.f1394d += j + (C0655gi.m1428b() / 2);
            this.f1393c = 0L;
        }
        m1564a();
        int myUid = Process.myUid();
        long uidRxBytes = TrafficStats.getUidRxBytes(myUid);
        long uidTxBytes = TrafficStats.getUidTxBytes(myUid);
        AbstractC0407b.m74c("Stats rx=" + (uidRxBytes - this.f1396f) + ", tx=" + (uidTxBytes - this.f1395e));
        this.f1396f = uidRxBytes;
        this.f1395e = uidTxBytes;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: a */
    public void mo588a(AbstractC0649gc abstractC0649gc, Exception exc) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    @Override // com.xiaomi.push.InterfaceC0652gf
    /* renamed from: b */
    public void mo589b(AbstractC0649gc abstractC0649gc) {
        this.f1386a = 0;
        this.f1390a = null;
        this.f1388a = abstractC0649gc;
        this.f1391a = C0503as.m475a((Context) this.f1389a);
        C0690hq.m1580a(0, EnumC0637fr.CONN_SUCCESS.m1281a());
    }
}
