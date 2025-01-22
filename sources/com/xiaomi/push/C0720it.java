package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.it */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/it.class */
public class C0720it implements InterfaceC0744jq<C0720it, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public String f1851a;

    /* renamed from: d */
    public String f1856d;

    /* renamed from: a */
    private static final C0761kg f1844a = new C0761kg("Target");

    /* renamed from: a */
    private static final C0752jy f1843a = new C0752jy("", (byte) 10, 1);

    /* renamed from: b */
    private static final C0752jy f1845b = new C0752jy("", (byte) 11, 2);

    /* renamed from: c */
    private static final C0752jy f1846c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f1847d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f1848e = new C0752jy("", (byte) 2, 5);

    /* renamed from: f */
    private static final C0752jy f1849f = new C0752jy("", (byte) 11, 7);

    /* renamed from: a */
    private BitSet f1852a = new BitSet(2);

    /* renamed from: a */
    public long f1850a = 5;

    /* renamed from: b */
    public String f1854b = "xiaomi.com";

    /* renamed from: c */
    public String f1855c = "";

    /* renamed from: a */
    public boolean f1853a = false;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0720it c0720it) {
        int m2320a;
        int m2326a;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2318a;
        if (!getClass().equals(c0720it.getClass())) {
            return getClass().getName().compareTo(c0720it.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1872a()).compareTo(Boolean.valueOf(c0720it.m1872a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1872a() && (m2318a = C0745jr.m2318a(this.f1850a, c0720it.f1850a)) != 0) {
            return m2318a;
        }
        int compareTo2 = Boolean.valueOf(m1875b()).compareTo(Boolean.valueOf(c0720it.m1875b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1875b() && (m2320a4 = C0745jr.m2320a(this.f1851a, c0720it.f1851a)) != 0) {
            return m2320a4;
        }
        int compareTo3 = Boolean.valueOf(m1876c()).compareTo(Boolean.valueOf(c0720it.m1876c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1876c() && (m2320a3 = C0745jr.m2320a(this.f1854b, c0720it.f1854b)) != 0) {
            return m2320a3;
        }
        int compareTo4 = Boolean.valueOf(m1877d()).compareTo(Boolean.valueOf(c0720it.m1877d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1877d() && (m2320a2 = C0745jr.m2320a(this.f1855c, c0720it.f1855c)) != 0) {
            return m2320a2;
        }
        int compareTo5 = Boolean.valueOf(m1878e()).compareTo(Boolean.valueOf(c0720it.m1878e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1878e() && (m2326a = C0745jr.m2326a(this.f1853a, c0720it.f1853a)) != 0) {
            return m2326a;
        }
        int compareTo6 = Boolean.valueOf(m1879f()).compareTo(Boolean.valueOf(c0720it.m1879f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (!m1879f() || (m2320a = C0745jr.m2320a(this.f1856d, c0720it.f1856d)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public void m1870a() {
        if (this.f1851a != null) {
            return;
        }
        throw new C0757kc("Required field 'userId' was not present! Struct: " + toString());
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
                    this.f1850a = abstractC0756kb.mo2341a();
                    m1871a(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s == 2) {
                if (mo2342a.f2324a == 11) {
                    this.f1851a = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s == 3) {
                if (mo2342a.f2324a == 11) {
                    this.f1854b = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s == 4) {
                if (mo2342a.f2324a == 11) {
                    this.f1855c = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s != 5) {
                if (s == 7 && mo2342a.f2324a == 11) {
                    this.f1856d = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 2) {
                    this.f1853a = abstractC0756kb.mo2365a();
                    m1874b(true);
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
        abstractC0756kb.mo2373g();
        if (m1872a()) {
            m1870a();
            return;
        }
        throw new C0757kc("Required field 'channelId' was not found in serialized data! Struct: " + toString());
    }

    /* renamed from: a */
    public void m1871a(boolean z) {
        this.f1852a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1872a() {
        return this.f1852a.get(0);
    }

    /* renamed from: a */
    public boolean m1873a(C0720it c0720it) {
        if (c0720it == null || this.f1850a != c0720it.f1850a) {
            return false;
        }
        boolean m1875b = m1875b();
        boolean m1875b2 = c0720it.m1875b();
        if ((m1875b || m1875b2) && !(m1875b && m1875b2 && this.f1851a.equals(c0720it.f1851a))) {
            return false;
        }
        boolean m1876c = m1876c();
        boolean m1876c2 = c0720it.m1876c();
        if ((m1876c || m1876c2) && !(m1876c && m1876c2 && this.f1854b.equals(c0720it.f1854b))) {
            return false;
        }
        boolean m1877d = m1877d();
        boolean m1877d2 = c0720it.m1877d();
        if ((m1877d || m1877d2) && !(m1877d && m1877d2 && this.f1855c.equals(c0720it.f1855c))) {
            return false;
        }
        boolean m1878e = m1878e();
        boolean m1878e2 = c0720it.m1878e();
        if ((m1878e || m1878e2) && !(m1878e && m1878e2 && this.f1853a == c0720it.f1853a)) {
            return false;
        }
        boolean m1879f = m1879f();
        boolean m1879f2 = c0720it.m1879f();
        if (m1879f || m1879f2) {
            return m1879f && m1879f2 && this.f1856d.equals(c0720it.f1856d);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1870a();
        abstractC0756kb.mo2360a(f1844a);
        abstractC0756kb.mo2356a(f1843a);
        abstractC0756kb.mo2355a(this.f1850a);
        abstractC0756kb.mo2366b();
        if (this.f1851a != null) {
            abstractC0756kb.mo2356a(f1845b);
            abstractC0756kb.mo2361a(this.f1851a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1854b != null && m1876c()) {
            abstractC0756kb.mo2356a(f1846c);
            abstractC0756kb.mo2361a(this.f1854b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1855c != null && m1877d()) {
            abstractC0756kb.mo2356a(f1847d);
            abstractC0756kb.mo2361a(this.f1855c);
            abstractC0756kb.mo2366b();
        }
        if (m1878e()) {
            abstractC0756kb.mo2356a(f1848e);
            abstractC0756kb.mo2364a(this.f1853a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1856d != null && m1879f()) {
            abstractC0756kb.mo2356a(f1849f);
            abstractC0756kb.mo2361a(this.f1856d);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1874b(boolean z) {
        this.f1852a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1875b() {
        return this.f1851a != null;
    }

    /* renamed from: c */
    public boolean m1876c() {
        return this.f1854b != null;
    }

    /* renamed from: d */
    public boolean m1877d() {
        return this.f1855c != null;
    }

    /* renamed from: e */
    public boolean m1878e() {
        return this.f1852a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0720it)) {
            return m1873a((C0720it) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m1879f() {
        return this.f1856d != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Target(");
        sb.append("channelId:");
        sb.append(this.f1850a);
        sb.append(", ");
        sb.append("userId:");
        String str = this.f1851a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (m1876c()) {
            sb.append(", ");
            sb.append("server:");
            String str2 = this.f1854b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        if (m1877d()) {
            sb.append(", ");
            sb.append("resource:");
            String str3 = this.f1855c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m1878e()) {
            sb.append(", ");
            sb.append("isPreview:");
            sb.append(this.f1853a);
        }
        if (m1879f()) {
            sb.append(", ");
            sb.append("token:");
            String str4 = this.f1856d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
