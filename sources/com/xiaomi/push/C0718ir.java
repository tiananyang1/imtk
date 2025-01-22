package com.xiaomi.push;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* renamed from: com.xiaomi.push.ir */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ir.class */
public class C0718ir implements InterfaceC0744jq<C0718ir, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public Set<C0709ii> f1837a;

    /* renamed from: a */
    private static final C0761kg f1836a = new C0761kg("RegisteredGeoFencing");

    /* renamed from: a */
    private static final C0752jy f1835a = new C0752jy("", (byte) 14, 1);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0718ir c0718ir) {
        int m2324a;
        if (!getClass().equals(c0718ir.getClass())) {
            return getClass().getName().compareTo(c0718ir.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1865a()).compareTo(Boolean.valueOf(c0718ir.m1865a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m1865a() || (m2324a = C0745jr.m2324a(this.f1837a, c0718ir.f1837a)) == 0) {
            return 0;
        }
        return m2324a;
    }

    /* renamed from: a */
    public C0718ir m1862a(Set<C0709ii> set) {
        this.f1837a = set;
        return this;
    }

    /* renamed from: a */
    public Set<C0709ii> m1863a() {
        return this.f1837a;
    }

    /* renamed from: a */
    public void m1864a() {
        if (this.f1837a != null) {
            return;
        }
        throw new C0757kc("Required field 'geoFencings' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1864a();
                return;
            }
            if (mo2342a.f2326a == 1 && mo2342a.f2324a == 14) {
                C0760kf mo2345a = abstractC0756kb.mo2345a();
                this.f1837a = new HashSet(mo2345a.f2341a * 2);
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= mo2345a.f2341a) {
                        break;
                    }
                    C0709ii c0709ii = new C0709ii();
                    c0709ii.mo1287a(abstractC0756kb);
                    this.f1837a.add(c0709ii);
                    i = i2 + 1;
                }
                abstractC0756kb.mo2377k();
            } else {
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            }
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public boolean m1865a() {
        return this.f1837a != null;
    }

    /* renamed from: a */
    public boolean m1866a(C0718ir c0718ir) {
        if (c0718ir == null) {
            return false;
        }
        boolean m1865a = m1865a();
        boolean m1865a2 = c0718ir.m1865a();
        if (m1865a || m1865a2) {
            return m1865a && m1865a2 && this.f1837a.equals(c0718ir.f1837a);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1864a();
        abstractC0756kb.mo2360a(f1836a);
        if (this.f1837a != null) {
            abstractC0756kb.mo2356a(f1835a);
            abstractC0756kb.mo2359a(new C0760kf((byte) 12, this.f1837a.size()));
            Iterator<C0709ii> it = this.f1837a.iterator();
            while (it.hasNext()) {
                it.next().mo1293b(abstractC0756kb);
            }
            abstractC0756kb.mo2372f();
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0718ir)) {
            return m1866a((C0718ir) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("RegisteredGeoFencing(");
        sb.append("geoFencings:");
        Set<C0709ii> set = this.f1837a;
        if (set == null) {
            sb.append("null");
        } else {
            sb.append(set);
        }
        sb.append(")");
        return sb.toString();
    }
}
