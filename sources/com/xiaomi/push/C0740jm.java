package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.jm */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jm.class */
public class C0740jm implements InterfaceC0744jq<C0740jm, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f2255a;

    /* renamed from: a */
    public C0720it f2256a;

    /* renamed from: a */
    public String f2257a;

    /* renamed from: a */
    private BitSet f2258a = new BitSet(3);

    /* renamed from: b */
    public long f2259b;

    /* renamed from: b */
    public String f2260b;

    /* renamed from: c */
    public long f2261c;

    /* renamed from: c */
    public String f2262c;

    /* renamed from: d */
    public String f2263d;

    /* renamed from: e */
    public String f2264e;

    /* renamed from: a */
    private static final C0761kg f2246a = new C0761kg("XmPushActionUnRegistrationResult");

    /* renamed from: a */
    private static final C0752jy f2245a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2247b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2248c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2249d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2250e = new C0752jy("", (byte) 10, 6);

    /* renamed from: f */
    private static final C0752jy f2251f = new C0752jy("", (byte) 11, 7);

    /* renamed from: g */
    private static final C0752jy f2252g = new C0752jy("", (byte) 11, 8);

    /* renamed from: h */
    private static final C0752jy f2253h = new C0752jy("", (byte) 10, 9);

    /* renamed from: i */
    private static final C0752jy f2254i = new C0752jy("", (byte) 10, 10);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0740jm c0740jm) {
        int m2318a;
        int m2318a2;
        int m2320a;
        int m2320a2;
        int m2318a3;
        int m2320a3;
        int m2320a4;
        int m2319a;
        int m2320a5;
        if (!getClass().equals(c0740jm.getClass())) {
            return getClass().getName().compareTo(c0740jm.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2268a()).compareTo(Boolean.valueOf(c0740jm.m2268a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2268a() && (m2320a5 = C0745jr.m2320a(this.f2257a, c0740jm.f2257a)) != 0) {
            return m2320a5;
        }
        int compareTo2 = Boolean.valueOf(m2271b()).compareTo(Boolean.valueOf(c0740jm.m2271b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2271b() && (m2319a = C0745jr.m2319a(this.f2256a, c0740jm.f2256a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m2273c()).compareTo(Boolean.valueOf(c0740jm.m2273c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2273c() && (m2320a4 = C0745jr.m2320a(this.f2260b, c0740jm.f2260b)) != 0) {
            return m2320a4;
        }
        int compareTo4 = Boolean.valueOf(m2274d()).compareTo(Boolean.valueOf(c0740jm.m2274d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2274d() && (m2320a3 = C0745jr.m2320a(this.f2262c, c0740jm.f2262c)) != 0) {
            return m2320a3;
        }
        int compareTo5 = Boolean.valueOf(m2275e()).compareTo(Boolean.valueOf(c0740jm.m2275e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2275e() && (m2318a3 = C0745jr.m2318a(this.f2255a, c0740jm.f2255a)) != 0) {
            return m2318a3;
        }
        int compareTo6 = Boolean.valueOf(m2276f()).compareTo(Boolean.valueOf(c0740jm.m2276f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2276f() && (m2320a2 = C0745jr.m2320a(this.f2263d, c0740jm.f2263d)) != 0) {
            return m2320a2;
        }
        int compareTo7 = Boolean.valueOf(m2277g()).compareTo(Boolean.valueOf(c0740jm.m2277g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2277g() && (m2320a = C0745jr.m2320a(this.f2264e, c0740jm.f2264e)) != 0) {
            return m2320a;
        }
        int compareTo8 = Boolean.valueOf(m2278h()).compareTo(Boolean.valueOf(c0740jm.m2278h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m2278h() && (m2318a2 = C0745jr.m2318a(this.f2259b, c0740jm.f2259b)) != 0) {
            return m2318a2;
        }
        int compareTo9 = Boolean.valueOf(m2279i()).compareTo(Boolean.valueOf(c0740jm.m2279i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (!m2279i() || (m2318a = C0745jr.m2318a(this.f2261c, c0740jm.f2261c)) == 0) {
            return 0;
        }
        return m2318a;
    }

    /* renamed from: a */
    public String m2265a() {
        return this.f2264e;
    }

    /* renamed from: a */
    public void m2266a() {
        if (this.f2260b == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f2262c != null) {
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
                if (m2275e()) {
                    m2266a();
                    return;
                }
                throw new C0757kc("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2257a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2256a = new C0720it();
                        this.f2256a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2260b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2262c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 10) {
                        this.f2255a = abstractC0756kb.mo2341a();
                        m2267a(true);
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2263d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f2264e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 10) {
                        this.f2259b = abstractC0756kb.mo2341a();
                        m2270b(true);
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 10) {
                        this.f2261c = abstractC0756kb.mo2341a();
                        m2272c(true);
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2267a(boolean z) {
        this.f2258a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2268a() {
        return this.f2257a != null;
    }

    /* renamed from: a */
    public boolean m2269a(C0740jm c0740jm) {
        if (c0740jm == null) {
            return false;
        }
        boolean m2268a = m2268a();
        boolean m2268a2 = c0740jm.m2268a();
        if ((m2268a || m2268a2) && !(m2268a && m2268a2 && this.f2257a.equals(c0740jm.f2257a))) {
            return false;
        }
        boolean m2271b = m2271b();
        boolean m2271b2 = c0740jm.m2271b();
        if ((m2271b || m2271b2) && !(m2271b && m2271b2 && this.f2256a.m1873a(c0740jm.f2256a))) {
            return false;
        }
        boolean m2273c = m2273c();
        boolean m2273c2 = c0740jm.m2273c();
        if ((m2273c || m2273c2) && !(m2273c && m2273c2 && this.f2260b.equals(c0740jm.f2260b))) {
            return false;
        }
        boolean m2274d = m2274d();
        boolean m2274d2 = c0740jm.m2274d();
        if (((m2274d || m2274d2) && !(m2274d && m2274d2 && this.f2262c.equals(c0740jm.f2262c))) || this.f2255a != c0740jm.f2255a) {
            return false;
        }
        boolean m2276f = m2276f();
        boolean m2276f2 = c0740jm.m2276f();
        if ((m2276f || m2276f2) && !(m2276f && m2276f2 && this.f2263d.equals(c0740jm.f2263d))) {
            return false;
        }
        boolean m2277g = m2277g();
        boolean m2277g2 = c0740jm.m2277g();
        if ((m2277g || m2277g2) && !(m2277g && m2277g2 && this.f2264e.equals(c0740jm.f2264e))) {
            return false;
        }
        boolean m2278h = m2278h();
        boolean m2278h2 = c0740jm.m2278h();
        if ((m2278h || m2278h2) && !(m2278h && m2278h2 && this.f2259b == c0740jm.f2259b)) {
            return false;
        }
        boolean m2279i = m2279i();
        boolean m2279i2 = c0740jm.m2279i();
        if (m2279i || m2279i2) {
            return m2279i && m2279i2 && this.f2261c == c0740jm.f2261c;
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2266a();
        abstractC0756kb.mo2360a(f2246a);
        if (this.f2257a != null && m2268a()) {
            abstractC0756kb.mo2356a(f2245a);
            abstractC0756kb.mo2361a(this.f2257a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2256a != null && m2271b()) {
            abstractC0756kb.mo2356a(f2247b);
            this.f2256a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2260b != null) {
            abstractC0756kb.mo2356a(f2248c);
            abstractC0756kb.mo2361a(this.f2260b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2262c != null) {
            abstractC0756kb.mo2356a(f2249d);
            abstractC0756kb.mo2361a(this.f2262c);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f2250e);
        abstractC0756kb.mo2355a(this.f2255a);
        abstractC0756kb.mo2366b();
        if (this.f2263d != null && m2276f()) {
            abstractC0756kb.mo2356a(f2251f);
            abstractC0756kb.mo2361a(this.f2263d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2264e != null && m2277g()) {
            abstractC0756kb.mo2356a(f2252g);
            abstractC0756kb.mo2361a(this.f2264e);
            abstractC0756kb.mo2366b();
        }
        if (m2278h()) {
            abstractC0756kb.mo2356a(f2253h);
            abstractC0756kb.mo2355a(this.f2259b);
            abstractC0756kb.mo2366b();
        }
        if (m2279i()) {
            abstractC0756kb.mo2356a(f2254i);
            abstractC0756kb.mo2355a(this.f2261c);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m2270b(boolean z) {
        this.f2258a.set(1, z);
    }

    /* renamed from: b */
    public boolean m2271b() {
        return this.f2256a != null;
    }

    /* renamed from: c */
    public void m2272c(boolean z) {
        this.f2258a.set(2, z);
    }

    /* renamed from: c */
    public boolean m2273c() {
        return this.f2260b != null;
    }

    /* renamed from: d */
    public boolean m2274d() {
        return this.f2262c != null;
    }

    /* renamed from: e */
    public boolean m2275e() {
        return this.f2258a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0740jm)) {
            return m2269a((C0740jm) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2276f() {
        return this.f2263d != null;
    }

    /* renamed from: g */
    public boolean m2277g() {
        return this.f2264e != null;
    }

    /* renamed from: h */
    public boolean m2278h() {
        return this.f2258a.get(1);
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m2279i() {
        return this.f2258a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnRegistrationResult(");
        if (m2268a()) {
            sb.append("debug:");
            String str = this.f2257a;
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
        if (m2271b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2256a;
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
        String str2 = this.f2260b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f2262c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f2255a);
        if (m2276f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f2263d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m2277g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f2264e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2278h()) {
            sb.append(", ");
            sb.append("unRegisteredAt:");
            sb.append(this.f2259b);
        }
        if (m2279i()) {
            sb.append(", ");
            sb.append("costTime:");
            sb.append(this.f2261c);
        }
        sb.append(")");
        return sb.toString();
    }
}
