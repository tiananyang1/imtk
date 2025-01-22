package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.il */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/il.class */
public class C0712il implements InterfaceC0744jq<C0712il, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C0708ih f1687a;

    /* renamed from: a */
    public List<C0721iu> f1688a;

    /* renamed from: b */
    public List<C0698hy> f1689b;

    /* renamed from: a */
    private static final C0761kg f1684a = new C0761kg("LocationInfo");

    /* renamed from: a */
    private static final C0752jy f1683a = new C0752jy("", (byte) 15, 1);

    /* renamed from: b */
    private static final C0752jy f1685b = new C0752jy("", (byte) 15, 2);

    /* renamed from: c */
    private static final C0752jy f1686c = new C0752jy("", (byte) 12, 3);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0712il c0712il) {
        int m2319a;
        int m2322a;
        int m2322a2;
        if (!getClass().equals(c0712il.getClass())) {
            return getClass().getName().compareTo(c0712il.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1753a()).compareTo(Boolean.valueOf(c0712il.m1753a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1753a() && (m2322a2 = C0745jr.m2322a(this.f1688a, c0712il.f1688a)) != 0) {
            return m2322a2;
        }
        int compareTo2 = Boolean.valueOf(m1756b()).compareTo(Boolean.valueOf(c0712il.m1756b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1756b() && (m2322a = C0745jr.m2322a(this.f1689b, c0712il.f1689b)) != 0) {
            return m2322a;
        }
        int compareTo3 = Boolean.valueOf(m1757c()).compareTo(Boolean.valueOf(c0712il.m1757c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (!m1757c() || (m2319a = C0745jr.m2319a(this.f1687a, c0712il.f1687a)) == 0) {
            return 0;
        }
        return m2319a;
    }

    /* renamed from: a */
    public C0708ih m1749a() {
        return this.f1687a;
    }

    /* renamed from: a */
    public C0712il m1750a(C0708ih c0708ih) {
        this.f1687a = c0708ih;
        return this;
    }

    /* renamed from: a */
    public C0712il m1751a(List<C0721iu> list) {
        this.f1688a = list;
        return this;
    }

    /* renamed from: a */
    public void m1752a() {
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1752a();
                return;
            }
            short s = mo2342a.f2326a;
            if (s != 1) {
                if (s != 2) {
                    if (s == 3 && mo2342a.f2324a == 12) {
                        this.f1687a = new C0708ih();
                        this.f1687a.mo1287a(abstractC0756kb);
                    }
                } else if (mo2342a.f2324a == 15) {
                    C0753jz mo2343a = abstractC0756kb.mo2343a();
                    this.f1689b = new ArrayList(mo2343a.f2328a);
                    for (int i = 0; i < mo2343a.f2328a; i++) {
                        C0698hy c0698hy = new C0698hy();
                        c0698hy.mo1287a(abstractC0756kb);
                        this.f1689b.add(c0698hy);
                    }
                    abstractC0756kb.mo2376j();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            } else {
                if (mo2342a.f2324a == 15) {
                    C0753jz mo2343a2 = abstractC0756kb.mo2343a();
                    this.f1688a = new ArrayList(mo2343a2.f2328a);
                    int i2 = 0;
                    while (true) {
                        int i3 = i2;
                        if (i3 >= mo2343a2.f2328a) {
                            break;
                        }
                        C0721iu c0721iu = new C0721iu();
                        c0721iu.mo1287a(abstractC0756kb);
                        this.f1688a.add(c0721iu);
                        i2 = i3 + 1;
                    }
                    abstractC0756kb.mo2376j();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            }
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public boolean m1753a() {
        return this.f1688a != null;
    }

    /* renamed from: a */
    public boolean m1754a(C0712il c0712il) {
        if (c0712il == null) {
            return false;
        }
        boolean m1753a = m1753a();
        boolean m1753a2 = c0712il.m1753a();
        if ((m1753a || m1753a2) && !(m1753a && m1753a2 && this.f1688a.equals(c0712il.f1688a))) {
            return false;
        }
        boolean m1756b = m1756b();
        boolean m1756b2 = c0712il.m1756b();
        if ((m1756b || m1756b2) && !(m1756b && m1756b2 && this.f1689b.equals(c0712il.f1689b))) {
            return false;
        }
        boolean m1757c = m1757c();
        boolean m1757c2 = c0712il.m1757c();
        if (m1757c || m1757c2) {
            return m1757c && m1757c2 && this.f1687a.m1694a(c0712il.f1687a);
        }
        return true;
    }

    /* renamed from: b */
    public C0712il m1755b(List<C0698hy> list) {
        this.f1689b = list;
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1752a();
        abstractC0756kb.mo2360a(f1684a);
        if (this.f1688a != null && m1753a()) {
            abstractC0756kb.mo2356a(f1683a);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f1688a.size()));
            Iterator<C0721iu> it = this.f1688a.iterator();
            while (it.hasNext()) {
                it.next().mo1293b(abstractC0756kb);
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        if (this.f1689b != null && m1756b()) {
            abstractC0756kb.mo2356a(f1685b);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f1689b.size()));
            Iterator<C0698hy> it2 = this.f1689b.iterator();
            while (it2.hasNext()) {
                it2.next().mo1293b(abstractC0756kb);
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        if (this.f1687a != null && m1757c()) {
            abstractC0756kb.mo2356a(f1686c);
            this.f1687a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m1756b() {
        return this.f1689b != null;
    }

    /* renamed from: c */
    public boolean m1757c() {
        return this.f1687a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0712il)) {
            return m1754a((C0712il) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("LocationInfo(");
        if (m1753a()) {
            sb.append("wifiList:");
            List<C0721iu> list = this.f1688a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
            z = false;
        } else {
            z = true;
        }
        boolean z2 = z;
        if (m1756b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("cellList:");
            List<C0698hy> list2 = this.f1689b;
            if (list2 == null) {
                sb.append("null");
            } else {
                sb.append(list2);
            }
            z2 = false;
        }
        if (m1757c()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("gps:");
            C0708ih c0708ih = this.f1687a;
            if (c0708ih == null) {
                sb.append("null");
            } else {
                sb.append(c0708ih);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
