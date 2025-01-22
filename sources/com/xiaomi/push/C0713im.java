package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.im */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/im.class */
public class C0713im implements InterfaceC0744jq<C0713im, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f1694a;

    /* renamed from: a */
    public EnumC0704id f1695a;

    /* renamed from: a */
    private BitSet f1696a = new BitSet(1);

    /* renamed from: a */
    public List<C0715io> f1697a;

    /* renamed from: a */
    private static final C0761kg f1691a = new C0761kg("NormalConfig");

    /* renamed from: a */
    private static final C0752jy f1690a = new C0752jy("", (byte) 8, 1);

    /* renamed from: b */
    private static final C0752jy f1692b = new C0752jy("", (byte) 15, 2);

    /* renamed from: c */
    private static final C0752jy f1693c = new C0752jy("", (byte) 8, 3);

    /* renamed from: a */
    public int m1758a() {
        return this.f1694a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0713im c0713im) {
        int m2319a;
        int m2322a;
        int m2317a;
        if (!getClass().equals(c0713im.getClass())) {
            return getClass().getName().compareTo(c0713im.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1763a()).compareTo(Boolean.valueOf(c0713im.m1763a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1763a() && (m2317a = C0745jr.m2317a(this.f1694a, c0713im.f1694a)) != 0) {
            return m2317a;
        }
        int compareTo2 = Boolean.valueOf(m1765b()).compareTo(Boolean.valueOf(c0713im.m1765b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1765b() && (m2322a = C0745jr.m2322a(this.f1697a, c0713im.f1697a)) != 0) {
            return m2322a;
        }
        int compareTo3 = Boolean.valueOf(m1766c()).compareTo(Boolean.valueOf(c0713im.m1766c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (!m1766c() || (m2319a = C0745jr.m2319a(this.f1695a, c0713im.f1695a)) == 0) {
            return 0;
        }
        return m2319a;
    }

    /* renamed from: a */
    public EnumC0704id m1760a() {
        return this.f1695a;
    }

    /* renamed from: a */
    public void m1761a() {
        if (this.f1697a != null) {
            return;
        }
        throw new C0757kc("Required field 'configItems' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                break;
            }
            short s = mo2342a.f2326a;
            if (s == 1) {
                if (mo2342a.f2324a == 8) {
                    this.f1694a = abstractC0756kb.mo2340a();
                    m1762a(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s != 2) {
                if (s == 3 && mo2342a.f2324a == 8) {
                    this.f1695a = EnumC0704id.m1670a(abstractC0756kb.mo2340a());
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 15) {
                    C0753jz mo2343a = abstractC0756kb.mo2343a();
                    this.f1697a = new ArrayList(mo2343a.f2328a);
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= mo2343a.f2328a) {
                            break;
                        }
                        C0715io c0715io = new C0715io();
                        c0715io.mo1287a(abstractC0756kb);
                        this.f1697a.add(c0715io);
                        i = i2 + 1;
                    }
                    abstractC0756kb.mo2376j();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
        abstractC0756kb.mo2373g();
        if (m1763a()) {
            m1761a();
            return;
        }
        throw new C0757kc("Required field 'version' was not found in serialized data! Struct: " + toString());
    }

    /* renamed from: a */
    public void m1762a(boolean z) {
        this.f1696a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1763a() {
        return this.f1696a.get(0);
    }

    /* renamed from: a */
    public boolean m1764a(C0713im c0713im) {
        if (c0713im == null || this.f1694a != c0713im.f1694a) {
            return false;
        }
        boolean m1765b = m1765b();
        boolean m1765b2 = c0713im.m1765b();
        if ((m1765b || m1765b2) && !(m1765b && m1765b2 && this.f1697a.equals(c0713im.f1697a))) {
            return false;
        }
        boolean m1766c = m1766c();
        boolean m1766c2 = c0713im.m1766c();
        if (m1766c || m1766c2) {
            return m1766c && m1766c2 && this.f1695a.equals(c0713im.f1695a);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1761a();
        abstractC0756kb.mo2360a(f1691a);
        abstractC0756kb.mo2356a(f1690a);
        abstractC0756kb.mo2354a(this.f1694a);
        abstractC0756kb.mo2366b();
        if (this.f1697a != null) {
            abstractC0756kb.mo2356a(f1692b);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f1697a.size()));
            Iterator<C0715io> it = this.f1697a.iterator();
            while (it.hasNext()) {
                it.next().mo1293b(abstractC0756kb);
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        if (this.f1695a != null && m1766c()) {
            abstractC0756kb.mo2356a(f1693c);
            abstractC0756kb.mo2354a(this.f1695a.m1671a());
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m1765b() {
        return this.f1697a != null;
    }

    /* renamed from: c */
    public boolean m1766c() {
        return this.f1695a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0713im)) {
            return m1764a((C0713im) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NormalConfig(");
        sb.append("version:");
        sb.append(this.f1694a);
        sb.append(", ");
        sb.append("configItems:");
        List<C0715io> list = this.f1697a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        if (m1766c()) {
            sb.append(", ");
            sb.append("type:");
            EnumC0704id enumC0704id = this.f1695a;
            if (enumC0704id == null) {
                sb.append("null");
            } else {
                sb.append(enumC0704id);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
