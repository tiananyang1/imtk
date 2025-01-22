package com.xiaomi.push;

import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.XMPushService;
import java.io.IOException;
import java.net.Socket;

/* renamed from: com.xiaomi.push.gj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gj.class */
public abstract class AbstractC0656gj extends AbstractC0649gc {

    /* renamed from: a */
    protected Exception f1091a;

    /* renamed from: a */
    protected Socket f1092a;

    /* renamed from: b */
    protected XMPushService f1093b;

    /* renamed from: c */
    private int f1094c;

    /* renamed from: c */
    String f1095c;

    /* renamed from: d */
    private String f1096d;

    /* renamed from: e */
    protected volatile long f1097e;

    /* renamed from: f */
    protected volatile long f1098f;

    /* renamed from: g */
    protected volatile long f1099g;

    public AbstractC0656gj(XMPushService xMPushService, C0650gd c0650gd) {
        super(xMPushService, c0650gd);
        this.f1091a = null;
        this.f1095c = null;
        this.f1097e = 0L;
        this.f1098f = 0L;
        this.f1099g = 0L;
        this.f1093b = xMPushService;
    }

    /* renamed from: a */
    private void m1429a(C0650gd c0650gd) {
        m1430a(c0650gd.m1420c(), c0650gd.m1414a());
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x03dd, code lost:            if (android.text.TextUtils.equals(r19, com.xiaomi.push.C0503as.m475a((android.content.Context) r8.f1093b)) == false) goto L76;     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x03ef A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x034e  */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 3 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m1430a(java.lang.String r9, int r10) {
        /*
            Method dump skipped, instructions count: 1024
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.AbstractC0656gj.m1430a(java.lang.String, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C0563cy m1431a(String str) {
        C0563cy m880a = C0568dc.m871a().m880a(str, false);
        if (!m880a.mo849b()) {
            C0679hf.m1534a(new RunnableC0659gm(this, str));
        }
        return m880a;
    }

    @Override // com.xiaomi.push.AbstractC0649gc
    /* renamed from: a */
    public String mo1389a() {
        return this.f1096d;
    }

    /* renamed from: a */
    public Socket m1432a() {
        return new Socket();
    }

    /* renamed from: a */
    protected void mo1372a() {
        synchronized (this) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1373a(int i, Exception exc) {
        synchronized (this) {
            if (m1395b() == 2) {
                return;
            }
            m1390a(2, i, exc);
            this.f1062a = "";
            try {
                this.f1092a.close();
            } catch (Throwable th) {
            }
            this.f1097e = 0L;
            this.f1098f = 0L;
        }
    }

    /* renamed from: a */
    protected void m1433a(Exception exc) {
        if (SystemClock.elapsedRealtime() - this.f1099g < 300000) {
            if (!C0503as.m484b(this.f1093b)) {
                return;
            }
            this.f1094c++;
            if (this.f1094c < 2) {
                return;
            }
            String mo1389a = mo1389a();
            AbstractC0407b.m70a("max short conn time reached, sink down current host:" + mo1389a);
            m1434a(mo1389a, 0L, exc);
        }
        this.f1094c = 0;
    }

    /* renamed from: a */
    protected void m1434a(String str, long j, Exception exc) {
        C0563cy m880a = C0568dc.m871a().m880a(C0650gd.m1411a(), false);
        if (m880a != null) {
            m880a.m848b(str, j, 0L, exc);
            C0568dc.m871a().m893c();
        }
    }

    /* renamed from: a */
    protected abstract void mo1378a(boolean z);

    @Override // com.xiaomi.push.AbstractC0649gc
    /* renamed from: a */
    public void mo1379a(C0641fv[] c0641fvArr) {
        throw new C0660gn("Don't support send Blob");
    }

    @Override // com.xiaomi.push.AbstractC0649gc
    /* renamed from: b */
    public void mo1398b(int i, Exception exc) {
        mo1373a(i, exc);
        if ((exc != null || i == 18) && this.f1099g != 0) {
            m1433a(exc);
        }
    }

    @Override // com.xiaomi.push.AbstractC0649gc
    /* renamed from: b */
    public void mo1401b(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        mo1378a(z);
        if (z) {
            return;
        }
        this.f1093b.m2469a(new C0657gk(this, 13, currentTimeMillis), 10000L);
    }

    /* renamed from: c */
    public String m1435c() {
        return this.f1062a;
    }

    /* renamed from: c */
    public void m1436c(int i, Exception exc) {
        this.f1093b.m2468a(new C0658gl(this, 2, i, exc));
    }

    /* renamed from: f */
    public void m1437f() {
        synchronized (this) {
            try {
                if (!m1435c() && !m1395b()) {
                    m1390a(0, 0, (Exception) null);
                    m1429a(this.f1059a);
                    return;
                }
                AbstractC0407b.m70a("WARNING: current xmpp has connected");
            } catch (IOException e) {
                throw new C0660gn(e);
            }
        }
    }

    /* renamed from: g */
    public void m1438g() {
        this.f1097e = SystemClock.elapsedRealtime();
    }

    /* renamed from: h */
    public void m1439h() {
        this.f1098f = SystemClock.elapsedRealtime();
    }
}
