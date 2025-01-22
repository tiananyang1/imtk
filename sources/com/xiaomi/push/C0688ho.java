package com.xiaomi.push;

import android.content.Context;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0505au;
import com.xiaomi.push.C0762kh;
import com.xiaomi.push.service.C0830bi;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/* renamed from: com.xiaomi.push.ho */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ho.class */
public class C0688ho {

    /* renamed from: a */
    private int f1397a;

    /* renamed from: a */
    private long f1398a;

    /* renamed from: a */
    private C0687hn f1400a;

    /* renamed from: a */
    private String f1401a;

    /* renamed from: a */
    private boolean f1402a = false;

    /* renamed from: a */
    private C0505au f1399a = C0505au.m504a();

    /* renamed from: com.xiaomi.push.ho$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ho$a.class */
    static class a {

        /* renamed from: a */
        static final C0688ho f1403a = new C0688ho();
    }

    /* renamed from: a */
    private C0638fs m1565a(C0505au.a aVar) {
        if (aVar.f454a == 0) {
            if (aVar.f455a instanceof C0638fs) {
                return (C0638fs) aVar.f455a;
            }
            return null;
        }
        C0638fs m1570a = m1570a();
        m1570a.m1284a(EnumC0637fr.CHANNEL_STATS_COUNTER.m1281a());
        m1570a.m1296c(aVar.f454a);
        m1570a.m1297c(aVar.f456a);
        return m1570a;
    }

    /* renamed from: a */
    private C0639ft m1566a(int i) {
        ArrayList arrayList = new ArrayList();
        C0639ft c0639ft = new C0639ft(this.f1401a, arrayList);
        if (!C0503as.m486d(this.f1400a.f1389a)) {
            c0639ft.m1313a(C0727j.m1999l(this.f1400a.f1389a));
        }
        C0764kj c0764kj = new C0764kj(i);
        AbstractC0756kb mo2378a = new C0762kh.a().mo2378a(c0764kj);
        try {
            c0639ft.mo1293b(mo2378a);
        } catch (C0749jv e) {
        }
        LinkedList<C0505au.a> m507a = this.f1399a.m507a();
        while (m507a.size() > 0) {
            try {
                C0638fs m1565a = m1565a(m507a.getLast());
                if (m1565a != null) {
                    m1565a.mo1293b(mo2378a);
                }
                if (c0764kj.m2388a_() > i) {
                    return c0639ft;
                }
                if (m1565a != null) {
                    arrayList.add(m1565a);
                }
                m507a.removeLast();
            } catch (C0749jv | NoSuchElementException e2) {
                return c0639ft;
            }
        }
        return c0639ft;
    }

    /* renamed from: a */
    public static C0687hn m1567a() {
        C0687hn c0687hn;
        synchronized (a.f1403a) {
            c0687hn = a.f1403a.f1400a;
        }
        return c0687hn;
    }

    /* renamed from: a */
    public static C0688ho m1568a() {
        return a.f1403a;
    }

    /* renamed from: a */
    private void m1569a() {
        if (!this.f1402a || System.currentTimeMillis() - this.f1398a <= this.f1397a) {
            return;
        }
        this.f1402a = false;
        this.f1398a = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C0638fs m1570a() {
        C0638fs c0638fs;
        synchronized (this) {
            c0638fs = new C0638fs();
            c0638fs.m1285a(C0503as.m475a((Context) this.f1400a.f1389a));
            c0638fs.f1003a = (byte) 0;
            c0638fs.f1007b = 1;
            c0638fs.m1300d((int) (System.currentTimeMillis() / 1000));
        }
        return c0638fs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C0639ft m1571a() {
        C0639ft c0639ft;
        synchronized (this) {
            c0639ft = null;
            if (m1576b()) {
                int i = 750;
                if (!C0503as.m486d(this.f1400a.f1389a)) {
                    i = 375;
                }
                c0639ft = m1566a(i);
            }
        }
        return c0639ft;
    }

    /* renamed from: a */
    public void m1572a(int i) {
        if (i > 0) {
            int i2 = i * PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT;
            int i3 = i2;
            if (i2 > 604800000) {
                i3 = 604800000;
            }
            if (this.f1397a == i3 && this.f1402a) {
                return;
            }
            this.f1402a = true;
            this.f1398a = System.currentTimeMillis();
            this.f1397a = i3;
            AbstractC0407b.m74c("enable dot duration = " + i3 + " start = " + this.f1398a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1573a(C0638fs c0638fs) {
        synchronized (this) {
            this.f1399a.m508a(c0638fs);
        }
    }

    /* renamed from: a */
    public void m1574a(XMPushService xMPushService) {
        synchronized (this) {
            this.f1400a = new C0687hn(xMPushService);
            this.f1401a = "";
            C0830bi.m2642a().m2654a(new C0689hp(this));
        }
    }

    /* renamed from: a */
    public boolean m1575a() {
        return this.f1402a;
    }

    /* renamed from: b */
    boolean m1576b() {
        m1569a();
        return this.f1402a && this.f1399a.m506a() > 0;
    }
}
