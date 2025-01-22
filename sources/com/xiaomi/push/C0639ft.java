package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.ft */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ft.class */
public class C0639ft implements InterfaceC0744jq<C0639ft, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public String f1018a;

    /* renamed from: a */
    public List<C0638fs> f1019a;

    /* renamed from: b */
    public String f1020b;

    /* renamed from: a */
    private static final C0761kg f1015a = new C0761kg("StatsEvents");

    /* renamed from: a */
    private static final C0752jy f1014a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f1016b = new C0752jy("", (byte) 11, 2);

    /* renamed from: c */
    private static final C0752jy f1017c = new C0752jy("", (byte) 15, 3);

    public C0639ft() {
    }

    public C0639ft(String str, List<C0638fs> list) {
        this();
        this.f1018a = str;
        this.f1019a = list;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0639ft c0639ft) {
        int m2322a;
        int m2320a;
        int m2320a2;
        if (!getClass().equals(c0639ft.getClass())) {
            return getClass().getName().compareTo(c0639ft.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1315a()).compareTo(Boolean.valueOf(c0639ft.m1315a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1315a() && (m2320a2 = C0745jr.m2320a(this.f1018a, c0639ft.f1018a)) != 0) {
            return m2320a2;
        }
        int compareTo2 = Boolean.valueOf(m1317b()).compareTo(Boolean.valueOf(c0639ft.m1317b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1317b() && (m2320a = C0745jr.m2320a(this.f1020b, c0639ft.f1020b)) != 0) {
            return m2320a;
        }
        int compareTo3 = Boolean.valueOf(m1318c()).compareTo(Boolean.valueOf(c0639ft.m1318c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (!m1318c() || (m2322a = C0745jr.m2322a(this.f1019a, c0639ft.f1019a)) == 0) {
            return 0;
        }
        return m2322a;
    }

    /* renamed from: a */
    public C0639ft m1313a(String str) {
        this.f1020b = str;
        return this;
    }

    /* renamed from: a */
    public void m1314a() {
        if (this.f1018a == null) {
            throw new C0757kc("Required field 'uuid' was not present! Struct: " + toString());
        }
        if (this.f1019a != null) {
            return;
        }
        throw new C0757kc("Required field 'events' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1314a();
                return;
            }
            short s = mo2342a.f2326a;
            if (s == 1) {
                if (mo2342a.f2324a == 11) {
                    this.f1018a = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s != 2) {
                if (s == 3 && mo2342a.f2324a == 15) {
                    C0753jz mo2343a = abstractC0756kb.mo2343a();
                    this.f1019a = new ArrayList(mo2343a.f2328a);
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= mo2343a.f2328a) {
                            break;
                        }
                        C0638fs c0638fs = new C0638fs();
                        c0638fs.mo1287a(abstractC0756kb);
                        this.f1019a.add(c0638fs);
                        i = i2 + 1;
                    }
                    abstractC0756kb.mo2376j();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 11) {
                    this.f1020b = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
    }

    /* renamed from: a */
    public boolean m1315a() {
        return this.f1018a != null;
    }

    /* renamed from: a */
    public boolean m1316a(C0639ft c0639ft) {
        if (c0639ft == null) {
            return false;
        }
        boolean m1315a = m1315a();
        boolean m1315a2 = c0639ft.m1315a();
        if ((m1315a || m1315a2) && !(m1315a && m1315a2 && this.f1018a.equals(c0639ft.f1018a))) {
            return false;
        }
        boolean m1317b = m1317b();
        boolean m1317b2 = c0639ft.m1317b();
        if ((m1317b || m1317b2) && !(m1317b && m1317b2 && this.f1020b.equals(c0639ft.f1020b))) {
            return false;
        }
        boolean m1318c = m1318c();
        boolean m1318c2 = c0639ft.m1318c();
        if (m1318c || m1318c2) {
            return m1318c && m1318c2 && this.f1019a.equals(c0639ft.f1019a);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1314a();
        abstractC0756kb.mo2360a(f1015a);
        if (this.f1018a != null) {
            abstractC0756kb.mo2356a(f1014a);
            abstractC0756kb.mo2361a(this.f1018a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1020b != null && m1317b()) {
            abstractC0756kb.mo2356a(f1016b);
            abstractC0756kb.mo2361a(this.f1020b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1019a != null) {
            abstractC0756kb.mo2356a(f1017c);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f1019a.size()));
            Iterator<C0638fs> it = this.f1019a.iterator();
            while (it.hasNext()) {
                it.next().mo1293b(abstractC0756kb);
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m1317b() {
        return this.f1020b != null;
    }

    /* renamed from: c */
    public boolean m1318c() {
        return this.f1019a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0639ft)) {
            return m1316a((C0639ft) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvents(");
        sb.append("uuid:");
        String str = this.f1018a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (m1317b()) {
            sb.append(", ");
            sb.append("operator:");
            String str2 = this.f1020b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("events:");
        List<C0638fs> list = this.f1019a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
