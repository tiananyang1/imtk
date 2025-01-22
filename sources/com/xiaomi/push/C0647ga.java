package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.AbstractC0649gc;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.C0824bc;
import com.xiaomi.push.service.C0830bi;
import com.xiaomi.push.service.XMPushService;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.ga */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ga.class */
public class C0647ga extends AbstractC0656gj {

    /* renamed from: a */
    private C0642fw f1050a;

    /* renamed from: a */
    private C0643fx f1051a;

    /* renamed from: a */
    private Thread f1052a;

    /* renamed from: a */
    private byte[] f1053a;

    public C0647ga(XMPushService xMPushService, C0650gd c0650gd) {
        super(xMPushService, c0650gd);
    }

    /* renamed from: a */
    private C0641fv m1369a(boolean z) {
        C0645fz c0645fz = new C0645fz();
        if (z) {
            c0645fz.m1330a("1");
        }
        byte[] m1586a = C0690hq.m1586a();
        if (m1586a != null) {
            C0612et.j jVar = new C0612et.j();
            jVar.m1167a(C0484a.m415a(m1586a));
            c0645fz.m1333a(jVar.mo959a(), (String) null);
        }
        return c0645fz;
    }

    /* renamed from: i */
    private void m1371i() {
        try {
            this.f1050a = new C0642fw(this.f1092a.getInputStream(), this);
            this.f1051a = new C0643fx(this.f1092a.getOutputStream(), this);
            this.f1052a = new C0648gb(this, "Blob Reader (" + this.f1066b + ")");
            this.f1052a.start();
        } catch (Exception e) {
            throw new C0660gn("Error to init reader and writer", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaomi.push.AbstractC0656gj
    /* renamed from: a */
    public void mo1372a() {
        synchronized (this) {
            m1371i();
            this.f1051a.m1353a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaomi.push.AbstractC0656gj
    /* renamed from: a */
    public void mo1373a(int i, Exception exc) {
        synchronized (this) {
            if (this.f1050a != null) {
                this.f1050a.m1351b();
                this.f1050a = null;
            }
            if (this.f1051a != null) {
                try {
                    this.f1051a.m1354b();
                } catch (Exception e) {
                    AbstractC0407b.m72a(e);
                }
                this.f1051a = null;
            }
            this.f1053a = null;
            super.mo1373a(i, exc);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1374a(C0641fv c0641fv) {
        if (c0641fv == null) {
            return;
        }
        if (c0641fv.m1334a()) {
            AbstractC0407b.m70a("[Slim] RCV blob chid=" + c0641fv.m1324a() + "; id=" + c0641fv.m1343e() + "; errCode=" + c0641fv.m1337b() + "; err=" + c0641fv.m1341c());
        }
        if (c0641fv.m1324a() == 0) {
            if ("PING".equals(c0641fv.m1325a())) {
                AbstractC0407b.m70a("[Slim] RCV ping id=" + c0641fv.m1343e());
                m1439h();
            } else if ("CLOSE".equals(c0641fv.m1325a())) {
                m1436c(13, null);
            }
        }
        Iterator<AbstractC0649gc.a> it = this.f1065a.values().iterator();
        while (it.hasNext()) {
            it.next().m1409a(c0641fv);
        }
    }

    @Override // com.xiaomi.push.AbstractC0649gc
    @Deprecated
    /* renamed from: a */
    public void mo1375a(AbstractC0666gt abstractC0666gt) {
        mo1382b(C0641fv.m1321a(abstractC0666gt, (String) null));
    }

    @Override // com.xiaomi.push.AbstractC0649gc
    /* renamed from: a */
    public void mo1376a(C0814at.b bVar) {
        synchronized (this) {
            C0640fu.m1319a(bVar, m1435c(), this);
        }
    }

    @Override // com.xiaomi.push.AbstractC0649gc
    /* renamed from: a */
    public void mo1377a(String str, String str2) {
        synchronized (this) {
            C0640fu.m1320a(str, str2, this);
        }
    }

    @Override // com.xiaomi.push.AbstractC0656gj
    /* renamed from: a */
    protected void mo1378a(boolean z) {
        if (this.f1051a == null) {
            throw new C0660gn("The BlobWriter is null.");
        }
        C0641fv m1369a = m1369a(z);
        AbstractC0407b.m70a("[Slim] SND ping id=" + m1369a.m1343e());
        mo1382b(m1369a);
        m1438g();
    }

    @Override // com.xiaomi.push.AbstractC0656gj, com.xiaomi.push.AbstractC0649gc
    /* renamed from: a */
    public void mo1379a(C0641fv[] c0641fvArr) {
        int length = c0641fvArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            mo1382b(c0641fvArr[i2]);
            i = i2 + 1;
        }
    }

    @Override // com.xiaomi.push.AbstractC0649gc
    /* renamed from: a */
    public boolean mo1380a() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public byte[] m1381a() {
        byte[] bArr;
        synchronized (this) {
            if (this.f1053a == null && !TextUtils.isEmpty(this.f1062a)) {
                String m2643a = C0830bi.m2643a();
                this.f1053a = C0824bc.m2624a(this.f1062a.getBytes(), (this.f1062a.substring(this.f1062a.length() / 2) + m2643a.substring(m2643a.length() / 2)).getBytes());
            }
            bArr = this.f1053a;
        }
        return bArr;
    }

    @Override // com.xiaomi.push.AbstractC0649gc
    /* renamed from: b */
    public void mo1382b(C0641fv c0641fv) {
        C0643fx c0643fx = this.f1051a;
        if (c0643fx == null) {
            throw new C0660gn("the writer is null.");
        }
        try {
            int m1352a = c0643fx.m1352a(c0641fv);
            this.f1072d = System.currentTimeMillis();
            String m1344f = c0641fv.m1344f();
            if (!TextUtils.isEmpty(m1344f)) {
                C0681hh.m1544a(this.f1061a, m1344f, m1352a, false, true, System.currentTimeMillis());
            }
            Iterator<AbstractC0649gc.a> it = this.f1069b.values().iterator();
            while (it.hasNext()) {
                it.next().m1409a(c0641fv);
            }
        } catch (Exception e) {
            throw new C0660gn(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m1383b(AbstractC0666gt abstractC0666gt) {
        if (abstractC0666gt == null) {
            return;
        }
        Iterator<AbstractC0649gc.a> it = this.f1065a.values().iterator();
        while (it.hasNext()) {
            it.next().m1410a(abstractC0666gt);
        }
    }
}
