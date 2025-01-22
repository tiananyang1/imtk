package com.xiaomi.push;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.ik */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ik.class */
public class C0711ik implements InterfaceC0744jq<C0711ik, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public double f1680a;

    /* renamed from: a */
    private BitSet f1681a = new BitSet(2);

    /* renamed from: b */
    public double f1682b;

    /* renamed from: a */
    private static final C0761kg f1678a = new C0761kg(HttpRequest.HEADER_LOCATION);

    /* renamed from: a */
    private static final C0752jy f1677a = new C0752jy("", (byte) 4, 1);

    /* renamed from: b */
    private static final C0752jy f1679b = new C0752jy("", (byte) 4, 2);

    /* renamed from: a */
    public double m1737a() {
        return this.f1680a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0711ik c0711ik) {
        int m2316a;
        int m2316a2;
        if (!getClass().equals(c0711ik.getClass())) {
            return getClass().getName().compareTo(c0711ik.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1742a()).compareTo(Boolean.valueOf(c0711ik.m1742a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1742a() && (m2316a2 = C0745jr.m2316a(this.f1680a, c0711ik.f1680a)) != 0) {
            return m2316a2;
        }
        int compareTo2 = Boolean.valueOf(m1747b()).compareTo(Boolean.valueOf(c0711ik.m1747b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (!m1747b() || (m2316a = C0745jr.m2316a(this.f1682b, c0711ik.f1682b)) == 0) {
            return 0;
        }
        return m2316a;
    }

    /* renamed from: a */
    public C0711ik m1739a(double d) {
        this.f1680a = d;
        m1741a(true);
        return this;
    }

    /* renamed from: a */
    public void m1740a() {
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
                if (s == 2 && mo2342a.f2324a == 4) {
                    this.f1682b = abstractC0756kb.mo2339a();
                    m1746b(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 4) {
                    this.f1680a = abstractC0756kb.mo2339a();
                    m1741a(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
        abstractC0756kb.mo2373g();
        if (!m1742a()) {
            throw new C0757kc("Required field 'longitude' was not found in serialized data! Struct: " + toString());
        }
        if (m1747b()) {
            m1740a();
            return;
        }
        throw new C0757kc("Required field 'latitude' was not found in serialized data! Struct: " + toString());
    }

    /* renamed from: a */
    public void m1741a(boolean z) {
        this.f1681a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1742a() {
        return this.f1681a.get(0);
    }

    /* renamed from: a */
    public boolean m1743a(C0711ik c0711ik) {
        return c0711ik != null && this.f1680a == c0711ik.f1680a && this.f1682b == c0711ik.f1682b;
    }

    /* renamed from: b */
    public double m1744b() {
        return this.f1682b;
    }

    /* renamed from: b */
    public C0711ik m1745b(double d) {
        this.f1682b = d;
        m1746b(true);
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1740a();
        abstractC0756kb.mo2360a(f1678a);
        abstractC0756kb.mo2356a(f1677a);
        abstractC0756kb.mo2353a(this.f1680a);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2356a(f1679b);
        abstractC0756kb.mo2353a(this.f1682b);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1746b(boolean z) {
        this.f1681a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1747b() {
        return this.f1681a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0711ik)) {
            return m1743a((C0711ik) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "Location(longitude:" + this.f1680a + ", latitude:" + this.f1682b + ")";
    }
}
