package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.hy */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hy.class */
public class C0698hy implements InterfaceC0744jq<C0698hy, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f1463a;

    /* renamed from: a */
    private BitSet f1464a = new BitSet(2);

    /* renamed from: b */
    public int f1465b;

    /* renamed from: a */
    private static final C0761kg f1461a = new C0761kg("Cellular");

    /* renamed from: a */
    private static final C0752jy f1460a = new C0752jy("", (byte) 8, 1);

    /* renamed from: b */
    private static final C0752jy f1462b = new C0752jy("", (byte) 8, 2);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0698hy c0698hy) {
        int m2317a;
        int m2317a2;
        if (!getClass().equals(c0698hy.getClass())) {
            return getClass().getName().compareTo(c0698hy.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1620a()).compareTo(Boolean.valueOf(c0698hy.m1620a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1620a() && (m2317a2 = C0745jr.m2317a(this.f1463a, c0698hy.f1463a)) != 0) {
            return m2317a2;
        }
        int compareTo2 = Boolean.valueOf(m1624b()).compareTo(Boolean.valueOf(c0698hy.m1624b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (!m1624b() || (m2317a = C0745jr.m2317a(this.f1465b, c0698hy.f1465b)) == 0) {
            return 0;
        }
        return m2317a;
    }

    /* renamed from: a */
    public C0698hy m1617a(int i) {
        this.f1463a = i;
        m1619a(true);
        return this;
    }

    /* renamed from: a */
    public void m1618a() {
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
            if (s != 1) {
                if (s == 2 && mo2342a.f2324a == 8) {
                    this.f1465b = abstractC0756kb.mo2340a();
                    m1623b(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 8) {
                    this.f1463a = abstractC0756kb.mo2340a();
                    m1619a(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
        abstractC0756kb.mo2373g();
        if (!m1620a()) {
            throw new C0757kc("Required field 'id' was not found in serialized data! Struct: " + toString());
        }
        if (m1624b()) {
            m1618a();
            return;
        }
        throw new C0757kc("Required field 'signalStrength' was not found in serialized data! Struct: " + toString());
    }

    /* renamed from: a */
    public void m1619a(boolean z) {
        this.f1464a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1620a() {
        return this.f1464a.get(0);
    }

    /* renamed from: a */
    public boolean m1621a(C0698hy c0698hy) {
        return c0698hy != null && this.f1463a == c0698hy.f1463a && this.f1465b == c0698hy.f1465b;
    }

    /* renamed from: b */
    public C0698hy m1622b(int i) {
        this.f1465b = i;
        m1623b(true);
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1618a();
        abstractC0756kb.mo2360a(f1461a);
        abstractC0756kb.mo2356a(f1460a);
        abstractC0756kb.mo2354a(this.f1463a);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2356a(f1462b);
        abstractC0756kb.mo2354a(this.f1465b);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1623b(boolean z) {
        this.f1464a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1624b() {
        return this.f1464a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0698hy)) {
            return m1621a((C0698hy) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "Cellular(id:" + this.f1463a + ", signalStrength:" + this.f1465b + ")";
    }
}
