package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.ih */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ih.class */
public class C0708ih implements InterfaceC0744jq<C0708ih, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public double f1646a;

    /* renamed from: a */
    public long f1647a;

    /* renamed from: a */
    public C0711ik f1648a;

    /* renamed from: a */
    public String f1649a;

    /* renamed from: a */
    private BitSet f1650a = new BitSet(2);

    /* renamed from: a */
    private static final C0761kg f1642a = new C0761kg("GPS");

    /* renamed from: a */
    private static final C0752jy f1641a = new C0752jy("", (byte) 12, 1);

    /* renamed from: b */
    private static final C0752jy f1643b = new C0752jy("", (byte) 11, 2);

    /* renamed from: c */
    private static final C0752jy f1644c = new C0752jy("", (byte) 10, 3);

    /* renamed from: d */
    private static final C0752jy f1645d = new C0752jy("", (byte) 4, 4);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0708ih c0708ih) {
        int m2316a;
        int m2318a;
        int m2320a;
        int m2319a;
        if (!getClass().equals(c0708ih.getClass())) {
            return getClass().getName().compareTo(c0708ih.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1693a()).compareTo(Boolean.valueOf(c0708ih.m1693a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1693a() && (m2319a = C0745jr.m2319a(this.f1648a, c0708ih.f1648a)) != 0) {
            return m2319a;
        }
        int compareTo2 = Boolean.valueOf(m1696b()).compareTo(Boolean.valueOf(c0708ih.m1696b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1696b() && (m2320a = C0745jr.m2320a(this.f1649a, c0708ih.f1649a)) != 0) {
            return m2320a;
        }
        int compareTo3 = Boolean.valueOf(m1697c()).compareTo(Boolean.valueOf(c0708ih.m1697c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1697c() && (m2318a = C0745jr.m2318a(this.f1647a, c0708ih.f1647a)) != 0) {
            return m2318a;
        }
        int compareTo4 = Boolean.valueOf(m1698d()).compareTo(Boolean.valueOf(c0708ih.m1698d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (!m1698d() || (m2316a = C0745jr.m2316a(this.f1646a, c0708ih.f1646a)) == 0) {
            return 0;
        }
        return m2316a;
    }

    /* renamed from: a */
    public C0708ih m1687a(double d) {
        this.f1646a = d;
        m1695b(true);
        return this;
    }

    /* renamed from: a */
    public C0708ih m1688a(long j) {
        this.f1647a = j;
        m1692a(true);
        return this;
    }

    /* renamed from: a */
    public C0708ih m1689a(C0711ik c0711ik) {
        this.f1648a = c0711ik;
        return this;
    }

    /* renamed from: a */
    public C0708ih m1690a(String str) {
        this.f1649a = str;
        return this;
    }

    /* renamed from: a */
    public void m1691a() {
        if (this.f1648a != null) {
            return;
        }
        throw new C0757kc("Required field 'location' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1691a();
                return;
            }
            short s = mo2342a.f2326a;
            if (s == 1) {
                if (mo2342a.f2324a == 12) {
                    this.f1648a = new C0711ik();
                    this.f1648a.mo1287a(abstractC0756kb);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s == 2) {
                if (mo2342a.f2324a == 11) {
                    this.f1649a = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s != 3) {
                if (s == 4 && mo2342a.f2324a == 4) {
                    this.f1646a = abstractC0756kb.mo2339a();
                    m1695b(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 10) {
                    this.f1647a = abstractC0756kb.mo2341a();
                    m1692a(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
    }

    /* renamed from: a */
    public void m1692a(boolean z) {
        this.f1650a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1693a() {
        return this.f1648a != null;
    }

    /* renamed from: a */
    public boolean m1694a(C0708ih c0708ih) {
        if (c0708ih == null) {
            return false;
        }
        boolean m1693a = m1693a();
        boolean m1693a2 = c0708ih.m1693a();
        if ((m1693a || m1693a2) && !(m1693a && m1693a2 && this.f1648a.m1743a(c0708ih.f1648a))) {
            return false;
        }
        boolean m1696b = m1696b();
        boolean m1696b2 = c0708ih.m1696b();
        if ((m1696b || m1696b2) && !(m1696b && m1696b2 && this.f1649a.equals(c0708ih.f1649a))) {
            return false;
        }
        boolean m1697c = m1697c();
        boolean m1697c2 = c0708ih.m1697c();
        if ((m1697c || m1697c2) && !(m1697c && m1697c2 && this.f1647a == c0708ih.f1647a)) {
            return false;
        }
        boolean m1698d = m1698d();
        boolean m1698d2 = c0708ih.m1698d();
        if (m1698d || m1698d2) {
            return m1698d && m1698d2 && this.f1646a == c0708ih.f1646a;
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1691a();
        abstractC0756kb.mo2360a(f1642a);
        if (this.f1648a != null) {
            abstractC0756kb.mo2356a(f1641a);
            this.f1648a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1649a != null && m1696b()) {
            abstractC0756kb.mo2356a(f1643b);
            abstractC0756kb.mo2361a(this.f1649a);
            abstractC0756kb.mo2366b();
        }
        if (m1697c()) {
            abstractC0756kb.mo2356a(f1644c);
            abstractC0756kb.mo2355a(this.f1647a);
            abstractC0756kb.mo2366b();
        }
        if (m1698d()) {
            abstractC0756kb.mo2356a(f1645d);
            abstractC0756kb.mo2353a(this.f1646a);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1695b(boolean z) {
        this.f1650a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1696b() {
        return this.f1649a != null;
    }

    /* renamed from: c */
    public boolean m1697c() {
        return this.f1650a.get(0);
    }

    /* renamed from: d */
    public boolean m1698d() {
        return this.f1650a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0708ih)) {
            return m1694a((C0708ih) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GPS(");
        sb.append("location:");
        C0711ik c0711ik = this.f1648a;
        if (c0711ik == null) {
            sb.append("null");
        } else {
            sb.append(c0711ik);
        }
        if (m1696b()) {
            sb.append(", ");
            sb.append("provider:");
            String str = this.f1649a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        if (m1697c()) {
            sb.append(", ");
            sb.append("period:");
            sb.append(this.f1647a);
        }
        if (m1698d()) {
            sb.append(", ");
            sb.append("accuracy:");
            sb.append(this.f1646a);
        }
        sb.append(")");
        return sb.toString();
    }
}
