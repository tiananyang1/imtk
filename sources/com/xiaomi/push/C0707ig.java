package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.ig */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ig.class */
public class C0707ig implements InterfaceC0744jq<C0707ig, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f1637a;

    /* renamed from: a */
    public EnumC0699hz f1638a;

    /* renamed from: a */
    public String f1639a;

    /* renamed from: a */
    private BitSet f1640a = new BitSet(1);

    /* renamed from: a */
    private static final C0761kg f1634a = new C0761kg("DataCollectionItem");

    /* renamed from: a */
    private static final C0752jy f1633a = new C0752jy("", (byte) 10, 1);

    /* renamed from: b */
    private static final C0752jy f1635b = new C0752jy("", (byte) 8, 2);

    /* renamed from: c */
    private static final C0752jy f1636c = new C0752jy("", (byte) 11, 3);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0707ig c0707ig) {
        int m2320a;
        int m2319a;
        int m2318a;
        if (!getClass().equals(c0707ig.getClass())) {
            return getClass().getName().compareTo(c0707ig.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1682a()).compareTo(Boolean.valueOf(c0707ig.m1682a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1682a() && (m2318a = C0745jr.m2318a(this.f1637a, c0707ig.f1637a)) != 0) {
            return m2318a;
        }
        int compareTo2 = Boolean.valueOf(m1684b()).compareTo(Boolean.valueOf(c0707ig.m1684b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1684b() && (m2319a = C0745jr.m2319a(this.f1638a, c0707ig.f1638a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m1685c()).compareTo(Boolean.valueOf(c0707ig.m1685c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (!m1685c() || (m2320a = C0745jr.m2320a(this.f1639a, c0707ig.f1639a)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public C0707ig m1676a(long j) {
        this.f1637a = j;
        m1681a(true);
        return this;
    }

    /* renamed from: a */
    public C0707ig m1677a(EnumC0699hz enumC0699hz) {
        this.f1638a = enumC0699hz;
        return this;
    }

    /* renamed from: a */
    public C0707ig m1678a(String str) {
        this.f1639a = str;
        return this;
    }

    /* renamed from: a */
    public String m1679a() {
        return this.f1639a;
    }

    /* renamed from: a */
    public void m1680a() {
        if (this.f1638a == null) {
            throw new C0757kc("Required field 'collectionType' was not present! Struct: " + toString());
        }
        if (this.f1639a != null) {
            return;
        }
        throw new C0757kc("Required field 'content' was not present! Struct: " + toString());
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
                if (mo2342a.f2324a == 10) {
                    this.f1637a = abstractC0756kb.mo2341a();
                    m1681a(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s != 2) {
                if (s == 3 && mo2342a.f2324a == 11) {
                    this.f1639a = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 8) {
                    this.f1638a = EnumC0699hz.m1625a(abstractC0756kb.mo2340a());
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
        abstractC0756kb.mo2373g();
        if (m1682a()) {
            m1680a();
            return;
        }
        throw new C0757kc("Required field 'collectedAt' was not found in serialized data! Struct: " + toString());
    }

    /* renamed from: a */
    public void m1681a(boolean z) {
        this.f1640a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1682a() {
        return this.f1640a.get(0);
    }

    /* renamed from: a */
    public boolean m1683a(C0707ig c0707ig) {
        if (c0707ig == null || this.f1637a != c0707ig.f1637a) {
            return false;
        }
        boolean m1684b = m1684b();
        boolean m1684b2 = c0707ig.m1684b();
        if ((m1684b || m1684b2) && !(m1684b && m1684b2 && this.f1638a.equals(c0707ig.f1638a))) {
            return false;
        }
        boolean m1685c = m1685c();
        boolean m1685c2 = c0707ig.m1685c();
        if (m1685c || m1685c2) {
            return m1685c && m1685c2 && this.f1639a.equals(c0707ig.f1639a);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1680a();
        abstractC0756kb.mo2360a(f1634a);
        abstractC0756kb.mo2356a(f1633a);
        abstractC0756kb.mo2355a(this.f1637a);
        abstractC0756kb.mo2366b();
        if (this.f1638a != null) {
            abstractC0756kb.mo2356a(f1635b);
            abstractC0756kb.mo2354a(this.f1638a.m1626a());
            abstractC0756kb.mo2366b();
        }
        if (this.f1639a != null) {
            abstractC0756kb.mo2356a(f1636c);
            abstractC0756kb.mo2361a(this.f1639a);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m1684b() {
        return this.f1638a != null;
    }

    /* renamed from: c */
    public boolean m1685c() {
        return this.f1639a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0707ig)) {
            return m1683a((C0707ig) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataCollectionItem(");
        sb.append("collectedAt:");
        sb.append(this.f1637a);
        sb.append(", ");
        sb.append("collectionType:");
        EnumC0699hz enumC0699hz = this.f1638a;
        if (enumC0699hz == null) {
            sb.append("null");
        } else {
            sb.append(enumC0699hz);
        }
        sb.append(", ");
        sb.append("content:");
        String str = this.f1639a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }
}
