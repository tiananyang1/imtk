package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.iu */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/iu.class */
public class C0721iu implements InterfaceC0744jq<C0721iu, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f1861a;

    /* renamed from: a */
    public String f1862a;

    /* renamed from: a */
    private BitSet f1863a = new BitSet(1);

    /* renamed from: b */
    public String f1864b;

    /* renamed from: a */
    private static final C0761kg f1858a = new C0761kg("Wifi");

    /* renamed from: a */
    private static final C0752jy f1857a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f1859b = new C0752jy("", (byte) 8, 2);

    /* renamed from: c */
    private static final C0752jy f1860c = new C0752jy("", (byte) 11, 3);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0721iu c0721iu) {
        int m2320a;
        int m2317a;
        int m2320a2;
        if (!getClass().equals(c0721iu.getClass())) {
            return getClass().getName().compareTo(c0721iu.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1885a()).compareTo(Boolean.valueOf(c0721iu.m1885a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1885a() && (m2320a2 = C0745jr.m2320a(this.f1862a, c0721iu.f1862a)) != 0) {
            return m2320a2;
        }
        int compareTo2 = Boolean.valueOf(m1888b()).compareTo(Boolean.valueOf(c0721iu.m1888b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1888b() && (m2317a = C0745jr.m2317a(this.f1861a, c0721iu.f1861a)) != 0) {
            return m2317a;
        }
        int compareTo3 = Boolean.valueOf(m1889c()).compareTo(Boolean.valueOf(c0721iu.m1889c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (!m1889c() || (m2320a = C0745jr.m2320a(this.f1864b, c0721iu.f1864b)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public C0721iu m1881a(int i) {
        this.f1861a = i;
        m1884a(true);
        return this;
    }

    /* renamed from: a */
    public C0721iu m1882a(String str) {
        this.f1862a = str;
        return this;
    }

    /* renamed from: a */
    public void m1883a() {
        if (this.f1862a != null) {
            return;
        }
        throw new C0757kc("Required field 'macAddress' was not present! Struct: " + toString());
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
                if (mo2342a.f2324a == 11) {
                    this.f1862a = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s != 2) {
                if (s == 3 && mo2342a.f2324a == 11) {
                    this.f1864b = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 8) {
                    this.f1861a = abstractC0756kb.mo2340a();
                    m1884a(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
        abstractC0756kb.mo2373g();
        if (m1888b()) {
            m1883a();
            return;
        }
        throw new C0757kc("Required field 'signalStrength' was not found in serialized data! Struct: " + toString());
    }

    /* renamed from: a */
    public void m1884a(boolean z) {
        this.f1863a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1885a() {
        return this.f1862a != null;
    }

    /* renamed from: a */
    public boolean m1886a(C0721iu c0721iu) {
        if (c0721iu == null) {
            return false;
        }
        boolean m1885a = m1885a();
        boolean m1885a2 = c0721iu.m1885a();
        if (((m1885a || m1885a2) && !(m1885a && m1885a2 && this.f1862a.equals(c0721iu.f1862a))) || this.f1861a != c0721iu.f1861a) {
            return false;
        }
        boolean m1889c = m1889c();
        boolean m1889c2 = c0721iu.m1889c();
        if (m1889c || m1889c2) {
            return m1889c && m1889c2 && this.f1864b.equals(c0721iu.f1864b);
        }
        return true;
    }

    /* renamed from: b */
    public C0721iu m1887b(String str) {
        this.f1864b = str;
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1883a();
        abstractC0756kb.mo2360a(f1858a);
        if (this.f1862a != null) {
            abstractC0756kb.mo2356a(f1857a);
            abstractC0756kb.mo2361a(this.f1862a);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f1859b);
        abstractC0756kb.mo2354a(this.f1861a);
        abstractC0756kb.mo2366b();
        if (this.f1864b != null && m1889c()) {
            abstractC0756kb.mo2356a(f1860c);
            abstractC0756kb.mo2361a(this.f1864b);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m1888b() {
        return this.f1863a.get(0);
    }

    /* renamed from: c */
    public boolean m1889c() {
        return this.f1864b != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0721iu)) {
            return m1886a((C0721iu) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Wifi(");
        sb.append("macAddress:");
        String str = this.f1862a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("signalStrength:");
        sb.append(this.f1861a);
        if (m1889c()) {
            sb.append(", ");
            sb.append("ssid:");
            String str2 = this.f1864b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
