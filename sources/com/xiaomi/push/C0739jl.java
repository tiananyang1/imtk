package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.jl */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jl.class */
public class C0739jl implements InterfaceC0744jq<C0739jl, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f2232a;

    /* renamed from: a */
    public C0720it f2233a;

    /* renamed from: a */
    public String f2234a;

    /* renamed from: a */
    private BitSet f2235a = new BitSet(2);

    /* renamed from: a */
    public boolean f2236a = true;

    /* renamed from: b */
    public String f2237b;

    /* renamed from: c */
    public String f2238c;

    /* renamed from: d */
    public String f2239d;

    /* renamed from: e */
    public String f2240e;

    /* renamed from: f */
    public String f2241f;

    /* renamed from: g */
    public String f2242g;

    /* renamed from: h */
    public String f2243h;

    /* renamed from: i */
    public String f2244i;

    /* renamed from: a */
    private static final C0761kg f2220a = new C0761kg("XmPushActionUnRegistration");

    /* renamed from: a */
    private static final C0752jy f2219a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2221b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2222c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2223d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2224e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f2225f = new C0752jy("", (byte) 11, 6);

    /* renamed from: g */
    private static final C0752jy f2226g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f2227h = new C0752jy("", (byte) 11, 8);

    /* renamed from: i */
    private static final C0752jy f2228i = new C0752jy("", (byte) 11, 9);

    /* renamed from: j */
    private static final C0752jy f2229j = new C0752jy("", (byte) 11, 10);

    /* renamed from: k */
    private static final C0752jy f2230k = new C0752jy("", (byte) 2, 11);

    /* renamed from: l */
    private static final C0752jy f2231l = new C0752jy("", (byte) 10, 12);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0739jl c0739jl) {
        int m2318a;
        int m2326a;
        int m2320a;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2320a5;
        int m2320a6;
        int m2320a7;
        int m2320a8;
        int m2319a;
        int m2320a9;
        if (!getClass().equals(c0739jl.getClass())) {
            return getClass().getName().compareTo(c0739jl.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2246a()).compareTo(Boolean.valueOf(c0739jl.m2246a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2246a() && (m2320a9 = C0745jr.m2320a(this.f2234a, c0739jl.f2234a)) != 0) {
            return m2320a9;
        }
        int compareTo2 = Boolean.valueOf(m2250b()).compareTo(Boolean.valueOf(c0739jl.m2250b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2250b() && (m2319a = C0745jr.m2319a(this.f2233a, c0739jl.f2233a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m2252c()).compareTo(Boolean.valueOf(c0739jl.m2252c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2252c() && (m2320a8 = C0745jr.m2320a(this.f2237b, c0739jl.f2237b)) != 0) {
            return m2320a8;
        }
        int compareTo4 = Boolean.valueOf(m2254d()).compareTo(Boolean.valueOf(c0739jl.m2254d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2254d() && (m2320a7 = C0745jr.m2320a(this.f2238c, c0739jl.f2238c)) != 0) {
            return m2320a7;
        }
        int compareTo5 = Boolean.valueOf(m2256e()).compareTo(Boolean.valueOf(c0739jl.m2256e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2256e() && (m2320a6 = C0745jr.m2320a(this.f2239d, c0739jl.f2239d)) != 0) {
            return m2320a6;
        }
        int compareTo6 = Boolean.valueOf(m2257f()).compareTo(Boolean.valueOf(c0739jl.m2257f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2257f() && (m2320a5 = C0745jr.m2320a(this.f2240e, c0739jl.f2240e)) != 0) {
            return m2320a5;
        }
        int compareTo7 = Boolean.valueOf(m2258g()).compareTo(Boolean.valueOf(c0739jl.m2258g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2258g() && (m2320a4 = C0745jr.m2320a(this.f2241f, c0739jl.f2241f)) != 0) {
            return m2320a4;
        }
        int compareTo8 = Boolean.valueOf(m2259h()).compareTo(Boolean.valueOf(c0739jl.m2259h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m2259h() && (m2320a3 = C0745jr.m2320a(this.f2242g, c0739jl.f2242g)) != 0) {
            return m2320a3;
        }
        int compareTo9 = Boolean.valueOf(m2260i()).compareTo(Boolean.valueOf(c0739jl.m2260i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m2260i() && (m2320a2 = C0745jr.m2320a(this.f2243h, c0739jl.f2243h)) != 0) {
            return m2320a2;
        }
        int compareTo10 = Boolean.valueOf(m2261j()).compareTo(Boolean.valueOf(c0739jl.m2261j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (m2261j() && (m2320a = C0745jr.m2320a(this.f2244i, c0739jl.f2244i)) != 0) {
            return m2320a;
        }
        int compareTo11 = Boolean.valueOf(m2262k()).compareTo(Boolean.valueOf(c0739jl.m2262k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (m2262k() && (m2326a = C0745jr.m2326a(this.f2236a, c0739jl.f2236a)) != 0) {
            return m2326a;
        }
        int compareTo12 = Boolean.valueOf(m2263l()).compareTo(Boolean.valueOf(c0739jl.m2263l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (!m2263l() || (m2318a = C0745jr.m2318a(this.f2232a, c0739jl.f2232a)) == 0) {
            return 0;
        }
        return m2318a;
    }

    /* renamed from: a */
    public C0739jl m2243a(String str) {
        this.f2237b = str;
        return this;
    }

    /* renamed from: a */
    public void m2244a() {
        if (this.f2237b == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f2238c != null) {
            return;
        }
        throw new C0757kc("Required field 'appId' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m2244a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2234a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2233a = new C0720it();
                        this.f2233a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2237b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2238c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f2239d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 11) {
                        this.f2240e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2241f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f2242g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f2243h = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 11) {
                        this.f2244i = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 11:
                    if (mo2342a.f2324a == 2) {
                        this.f2236a = abstractC0756kb.mo2365a();
                        m2245a(true);
                        break;
                    }
                    break;
                case 12:
                    if (mo2342a.f2324a == 10) {
                        this.f2232a = abstractC0756kb.mo2341a();
                        m2249b(true);
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2245a(boolean z) {
        this.f2235a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2246a() {
        return this.f2234a != null;
    }

    /* renamed from: a */
    public boolean m2247a(C0739jl c0739jl) {
        if (c0739jl == null) {
            return false;
        }
        boolean m2246a = m2246a();
        boolean m2246a2 = c0739jl.m2246a();
        if ((m2246a || m2246a2) && !(m2246a && m2246a2 && this.f2234a.equals(c0739jl.f2234a))) {
            return false;
        }
        boolean m2250b = m2250b();
        boolean m2250b2 = c0739jl.m2250b();
        if ((m2250b || m2250b2) && !(m2250b && m2250b2 && this.f2233a.m1873a(c0739jl.f2233a))) {
            return false;
        }
        boolean m2252c = m2252c();
        boolean m2252c2 = c0739jl.m2252c();
        if ((m2252c || m2252c2) && !(m2252c && m2252c2 && this.f2237b.equals(c0739jl.f2237b))) {
            return false;
        }
        boolean m2254d = m2254d();
        boolean m2254d2 = c0739jl.m2254d();
        if ((m2254d || m2254d2) && !(m2254d && m2254d2 && this.f2238c.equals(c0739jl.f2238c))) {
            return false;
        }
        boolean m2256e = m2256e();
        boolean m2256e2 = c0739jl.m2256e();
        if ((m2256e || m2256e2) && !(m2256e && m2256e2 && this.f2239d.equals(c0739jl.f2239d))) {
            return false;
        }
        boolean m2257f = m2257f();
        boolean m2257f2 = c0739jl.m2257f();
        if ((m2257f || m2257f2) && !(m2257f && m2257f2 && this.f2240e.equals(c0739jl.f2240e))) {
            return false;
        }
        boolean m2258g = m2258g();
        boolean m2258g2 = c0739jl.m2258g();
        if ((m2258g || m2258g2) && !(m2258g && m2258g2 && this.f2241f.equals(c0739jl.f2241f))) {
            return false;
        }
        boolean m2259h = m2259h();
        boolean m2259h2 = c0739jl.m2259h();
        if ((m2259h || m2259h2) && !(m2259h && m2259h2 && this.f2242g.equals(c0739jl.f2242g))) {
            return false;
        }
        boolean m2260i = m2260i();
        boolean m2260i2 = c0739jl.m2260i();
        if ((m2260i || m2260i2) && !(m2260i && m2260i2 && this.f2243h.equals(c0739jl.f2243h))) {
            return false;
        }
        boolean m2261j = m2261j();
        boolean m2261j2 = c0739jl.m2261j();
        if ((m2261j || m2261j2) && !(m2261j && m2261j2 && this.f2244i.equals(c0739jl.f2244i))) {
            return false;
        }
        boolean m2262k = m2262k();
        boolean m2262k2 = c0739jl.m2262k();
        if ((m2262k || m2262k2) && !(m2262k && m2262k2 && this.f2236a == c0739jl.f2236a)) {
            return false;
        }
        boolean m2263l = m2263l();
        boolean m2263l2 = c0739jl.m2263l();
        if (m2263l || m2263l2) {
            return m2263l && m2263l2 && this.f2232a == c0739jl.f2232a;
        }
        return true;
    }

    /* renamed from: b */
    public C0739jl m2248b(String str) {
        this.f2238c = str;
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2244a();
        abstractC0756kb.mo2360a(f2220a);
        if (this.f2234a != null && m2246a()) {
            abstractC0756kb.mo2356a(f2219a);
            abstractC0756kb.mo2361a(this.f2234a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2233a != null && m2250b()) {
            abstractC0756kb.mo2356a(f2221b);
            this.f2233a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2237b != null) {
            abstractC0756kb.mo2356a(f2222c);
            abstractC0756kb.mo2361a(this.f2237b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2238c != null) {
            abstractC0756kb.mo2356a(f2223d);
            abstractC0756kb.mo2361a(this.f2238c);
            abstractC0756kb.mo2366b();
        }
        if (this.f2239d != null && m2256e()) {
            abstractC0756kb.mo2356a(f2224e);
            abstractC0756kb.mo2361a(this.f2239d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2240e != null && m2257f()) {
            abstractC0756kb.mo2356a(f2225f);
            abstractC0756kb.mo2361a(this.f2240e);
            abstractC0756kb.mo2366b();
        }
        if (this.f2241f != null && m2258g()) {
            abstractC0756kb.mo2356a(f2226g);
            abstractC0756kb.mo2361a(this.f2241f);
            abstractC0756kb.mo2366b();
        }
        if (this.f2242g != null && m2259h()) {
            abstractC0756kb.mo2356a(f2227h);
            abstractC0756kb.mo2361a(this.f2242g);
            abstractC0756kb.mo2366b();
        }
        if (this.f2243h != null && m2260i()) {
            abstractC0756kb.mo2356a(f2228i);
            abstractC0756kb.mo2361a(this.f2243h);
            abstractC0756kb.mo2366b();
        }
        if (this.f2244i != null && m2261j()) {
            abstractC0756kb.mo2356a(f2229j);
            abstractC0756kb.mo2361a(this.f2244i);
            abstractC0756kb.mo2366b();
        }
        if (m2262k()) {
            abstractC0756kb.mo2356a(f2230k);
            abstractC0756kb.mo2364a(this.f2236a);
            abstractC0756kb.mo2366b();
        }
        if (m2263l()) {
            abstractC0756kb.mo2356a(f2231l);
            abstractC0756kb.mo2355a(this.f2232a);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m2249b(boolean z) {
        this.f2235a.set(1, z);
    }

    /* renamed from: b */
    public boolean m2250b() {
        return this.f2233a != null;
    }

    /* renamed from: c */
    public C0739jl m2251c(String str) {
        this.f2239d = str;
        return this;
    }

    /* renamed from: c */
    public boolean m2252c() {
        return this.f2237b != null;
    }

    /* renamed from: d */
    public C0739jl m2253d(String str) {
        this.f2241f = str;
        return this;
    }

    /* renamed from: d */
    public boolean m2254d() {
        return this.f2238c != null;
    }

    /* renamed from: e */
    public C0739jl m2255e(String str) {
        this.f2242g = str;
        return this;
    }

    /* renamed from: e */
    public boolean m2256e() {
        return this.f2239d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0739jl)) {
            return m2247a((C0739jl) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2257f() {
        return this.f2240e != null;
    }

    /* renamed from: g */
    public boolean m2258g() {
        return this.f2241f != null;
    }

    /* renamed from: h */
    public boolean m2259h() {
        return this.f2242g != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m2260i() {
        return this.f2243h != null;
    }

    /* renamed from: j */
    public boolean m2261j() {
        return this.f2244i != null;
    }

    /* renamed from: k */
    public boolean m2262k() {
        return this.f2235a.get(0);
    }

    /* renamed from: l */
    public boolean m2263l() {
        return this.f2235a.get(1);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistration(");
        if (m2246a()) {
            sb.append("debug:");
            String str = this.f2234a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        boolean z2 = z;
        if (m2250b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2233a;
            if (c0720it == null) {
                sb.append("null");
            } else {
                sb.append(c0720it);
            }
            z2 = false;
        }
        if (!z2) {
            sb.append(", ");
        }
        sb.append("id:");
        String str2 = this.f2237b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f2238c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m2256e()) {
            sb.append(", ");
            sb.append("regId:");
            String str4 = this.f2239d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m2257f()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str5 = this.f2240e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2258g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f2241f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m2259h()) {
            sb.append(", ");
            sb.append("token:");
            String str7 = this.f2242g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m2260i()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str8 = this.f2243h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m2261j()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.f2244i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m2262k()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f2236a);
        }
        if (m2263l()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f2232a);
        }
        sb.append(")");
        return sb.toString();
    }
}
