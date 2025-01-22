package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.ix */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ix.class */
public class C0724ix implements InterfaceC0744jq<C0724ix, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f1932a;

    /* renamed from: a */
    private BitSet f1933a = new BitSet(2);

    /* renamed from: b */
    public int f1934b;

    /* renamed from: a */
    private static final C0761kg f1930a = new C0761kg("XmPushActionCheckClientInfo");

    /* renamed from: a */
    private static final C0752jy f1929a = new C0752jy("", (byte) 8, 1);

    /* renamed from: b */
    private static final C0752jy f1931b = new C0752jy("", (byte) 8, 2);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0724ix c0724ix) {
        int m2317a;
        int m2317a2;
        if (!getClass().equals(c0724ix.getClass())) {
            return getClass().getName().compareTo(c0724ix.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1945a()).compareTo(Boolean.valueOf(c0724ix.m1945a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1945a() && (m2317a2 = C0745jr.m2317a(this.f1932a, c0724ix.f1932a)) != 0) {
            return m2317a2;
        }
        int compareTo2 = Boolean.valueOf(m1949b()).compareTo(Boolean.valueOf(c0724ix.m1949b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (!m1949b() || (m2317a = C0745jr.m2317a(this.f1934b, c0724ix.f1934b)) == 0) {
            return 0;
        }
        return m2317a;
    }

    /* renamed from: a */
    public C0724ix m1942a(int i) {
        this.f1932a = i;
        m1944a(true);
        return this;
    }

    /* renamed from: a */
    public void m1943a() {
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
                    this.f1934b = abstractC0756kb.mo2340a();
                    m1948b(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 8) {
                    this.f1932a = abstractC0756kb.mo2340a();
                    m1944a(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
        abstractC0756kb.mo2373g();
        if (!m1945a()) {
            throw new C0757kc("Required field 'miscConfigVersion' was not found in serialized data! Struct: " + toString());
        }
        if (m1949b()) {
            m1943a();
            return;
        }
        throw new C0757kc("Required field 'pluginConfigVersion' was not found in serialized data! Struct: " + toString());
    }

    /* renamed from: a */
    public void m1944a(boolean z) {
        this.f1933a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1945a() {
        return this.f1933a.get(0);
    }

    /* renamed from: a */
    public boolean m1946a(C0724ix c0724ix) {
        return c0724ix != null && this.f1932a == c0724ix.f1932a && this.f1934b == c0724ix.f1934b;
    }

    /* renamed from: b */
    public C0724ix m1947b(int i) {
        this.f1934b = i;
        m1948b(true);
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1943a();
        abstractC0756kb.mo2360a(f1930a);
        abstractC0756kb.mo2356a(f1929a);
        abstractC0756kb.mo2354a(this.f1932a);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2356a(f1931b);
        abstractC0756kb.mo2354a(this.f1934b);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1948b(boolean z) {
        this.f1933a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1949b() {
        return this.f1933a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0724ix)) {
            return m1946a((C0724ix) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "XmPushActionCheckClientInfo(miscConfigVersion:" + this.f1932a + ", pluginConfigVersion:" + this.f1934b + ")";
    }
}
