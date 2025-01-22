package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.jk */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jk.class */
public class C0738jk implements InterfaceC0744jq<C0738jk, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f2209a;

    /* renamed from: a */
    public C0720it f2210a;

    /* renamed from: a */
    public String f2211a;

    /* renamed from: a */
    private BitSet f2212a = new BitSet(1);

    /* renamed from: b */
    public String f2213b;

    /* renamed from: c */
    public String f2214c;

    /* renamed from: d */
    public String f2215d;

    /* renamed from: e */
    public String f2216e;

    /* renamed from: f */
    public String f2217f;

    /* renamed from: g */
    public String f2218g;

    /* renamed from: a */
    private static final C0761kg f2200a = new C0761kg("XmPushActionSubscriptionResult");

    /* renamed from: a */
    private static final C0752jy f2199a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2201b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2202c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2203d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2204e = new C0752jy("", (byte) 10, 6);

    /* renamed from: f */
    private static final C0752jy f2205f = new C0752jy("", (byte) 11, 7);

    /* renamed from: g */
    private static final C0752jy f2206g = new C0752jy("", (byte) 11, 8);

    /* renamed from: h */
    private static final C0752jy f2207h = new C0752jy("", (byte) 11, 9);

    /* renamed from: i */
    private static final C0752jy f2208i = new C0752jy("", (byte) 11, 10);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0738jk c0738jk) {
        int m2320a;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2318a;
        int m2320a5;
        int m2320a6;
        int m2319a;
        int m2320a7;
        if (!getClass().equals(c0738jk.getClass())) {
            return getClass().getName().compareTo(c0738jk.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2231a()).compareTo(Boolean.valueOf(c0738jk.m2231a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2231a() && (m2320a7 = C0745jr.m2320a(this.f2211a, c0738jk.f2211a)) != 0) {
            return m2320a7;
        }
        int compareTo2 = Boolean.valueOf(m2234b()).compareTo(Boolean.valueOf(c0738jk.m2234b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2234b() && (m2319a = C0745jr.m2319a(this.f2210a, c0738jk.f2210a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m2235c()).compareTo(Boolean.valueOf(c0738jk.m2235c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2235c() && (m2320a6 = C0745jr.m2320a(this.f2213b, c0738jk.f2213b)) != 0) {
            return m2320a6;
        }
        int compareTo4 = Boolean.valueOf(m2236d()).compareTo(Boolean.valueOf(c0738jk.m2236d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2236d() && (m2320a5 = C0745jr.m2320a(this.f2214c, c0738jk.f2214c)) != 0) {
            return m2320a5;
        }
        int compareTo5 = Boolean.valueOf(m2237e()).compareTo(Boolean.valueOf(c0738jk.m2237e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2237e() && (m2318a = C0745jr.m2318a(this.f2209a, c0738jk.f2209a)) != 0) {
            return m2318a;
        }
        int compareTo6 = Boolean.valueOf(m2238f()).compareTo(Boolean.valueOf(c0738jk.m2238f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2238f() && (m2320a4 = C0745jr.m2320a(this.f2215d, c0738jk.f2215d)) != 0) {
            return m2320a4;
        }
        int compareTo7 = Boolean.valueOf(m2239g()).compareTo(Boolean.valueOf(c0738jk.m2239g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2239g() && (m2320a3 = C0745jr.m2320a(this.f2216e, c0738jk.f2216e)) != 0) {
            return m2320a3;
        }
        int compareTo8 = Boolean.valueOf(m2240h()).compareTo(Boolean.valueOf(c0738jk.m2240h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m2240h() && (m2320a2 = C0745jr.m2320a(this.f2217f, c0738jk.f2217f)) != 0) {
            return m2320a2;
        }
        int compareTo9 = Boolean.valueOf(m2241i()).compareTo(Boolean.valueOf(c0738jk.m2241i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (!m2241i() || (m2320a = C0745jr.m2320a(this.f2218g, c0738jk.f2218g)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public String m2228a() {
        return this.f2216e;
    }

    /* renamed from: a */
    public void m2229a() {
        if (this.f2213b != null) {
            return;
        }
        throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m2229a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2211a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2210a = new C0720it();
                        this.f2210a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2213b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2214c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 10) {
                        this.f2209a = abstractC0756kb.mo2341a();
                        m2230a(true);
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2215d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f2216e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f2217f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 11) {
                        this.f2218g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2230a(boolean z) {
        this.f2212a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2231a() {
        return this.f2211a != null;
    }

    /* renamed from: a */
    public boolean m2232a(C0738jk c0738jk) {
        if (c0738jk == null) {
            return false;
        }
        boolean m2231a = m2231a();
        boolean m2231a2 = c0738jk.m2231a();
        if ((m2231a || m2231a2) && !(m2231a && m2231a2 && this.f2211a.equals(c0738jk.f2211a))) {
            return false;
        }
        boolean m2234b = m2234b();
        boolean m2234b2 = c0738jk.m2234b();
        if ((m2234b || m2234b2) && !(m2234b && m2234b2 && this.f2210a.m1873a(c0738jk.f2210a))) {
            return false;
        }
        boolean m2235c = m2235c();
        boolean m2235c2 = c0738jk.m2235c();
        if ((m2235c || m2235c2) && !(m2235c && m2235c2 && this.f2213b.equals(c0738jk.f2213b))) {
            return false;
        }
        boolean m2236d = m2236d();
        boolean m2236d2 = c0738jk.m2236d();
        if ((m2236d || m2236d2) && !(m2236d && m2236d2 && this.f2214c.equals(c0738jk.f2214c))) {
            return false;
        }
        boolean m2237e = m2237e();
        boolean m2237e2 = c0738jk.m2237e();
        if ((m2237e || m2237e2) && !(m2237e && m2237e2 && this.f2209a == c0738jk.f2209a)) {
            return false;
        }
        boolean m2238f = m2238f();
        boolean m2238f2 = c0738jk.m2238f();
        if ((m2238f || m2238f2) && !(m2238f && m2238f2 && this.f2215d.equals(c0738jk.f2215d))) {
            return false;
        }
        boolean m2239g = m2239g();
        boolean m2239g2 = c0738jk.m2239g();
        if ((m2239g || m2239g2) && !(m2239g && m2239g2 && this.f2216e.equals(c0738jk.f2216e))) {
            return false;
        }
        boolean m2240h = m2240h();
        boolean m2240h2 = c0738jk.m2240h();
        if ((m2240h || m2240h2) && !(m2240h && m2240h2 && this.f2217f.equals(c0738jk.f2217f))) {
            return false;
        }
        boolean m2241i = m2241i();
        boolean m2241i2 = c0738jk.m2241i();
        if (m2241i || m2241i2) {
            return m2241i && m2241i2 && this.f2218g.equals(c0738jk.f2218g);
        }
        return true;
    }

    /* renamed from: b */
    public String m2233b() {
        return this.f2218g;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2229a();
        abstractC0756kb.mo2360a(f2200a);
        if (this.f2211a != null && m2231a()) {
            abstractC0756kb.mo2356a(f2199a);
            abstractC0756kb.mo2361a(this.f2211a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2210a != null && m2234b()) {
            abstractC0756kb.mo2356a(f2201b);
            this.f2210a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2213b != null) {
            abstractC0756kb.mo2356a(f2202c);
            abstractC0756kb.mo2361a(this.f2213b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2214c != null && m2236d()) {
            abstractC0756kb.mo2356a(f2203d);
            abstractC0756kb.mo2361a(this.f2214c);
            abstractC0756kb.mo2366b();
        }
        if (m2237e()) {
            abstractC0756kb.mo2356a(f2204e);
            abstractC0756kb.mo2355a(this.f2209a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2215d != null && m2238f()) {
            abstractC0756kb.mo2356a(f2205f);
            abstractC0756kb.mo2361a(this.f2215d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2216e != null && m2239g()) {
            abstractC0756kb.mo2356a(f2206g);
            abstractC0756kb.mo2361a(this.f2216e);
            abstractC0756kb.mo2366b();
        }
        if (this.f2217f != null && m2240h()) {
            abstractC0756kb.mo2356a(f2207h);
            abstractC0756kb.mo2361a(this.f2217f);
            abstractC0756kb.mo2366b();
        }
        if (this.f2218g != null && m2241i()) {
            abstractC0756kb.mo2356a(f2208i);
            abstractC0756kb.mo2361a(this.f2218g);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m2234b() {
        return this.f2210a != null;
    }

    /* renamed from: c */
    public boolean m2235c() {
        return this.f2213b != null;
    }

    /* renamed from: d */
    public boolean m2236d() {
        return this.f2214c != null;
    }

    /* renamed from: e */
    public boolean m2237e() {
        return this.f2212a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0738jk)) {
            return m2232a((C0738jk) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2238f() {
        return this.f2215d != null;
    }

    /* renamed from: g */
    public boolean m2239g() {
        return this.f2216e != null;
    }

    /* renamed from: h */
    public boolean m2240h() {
        return this.f2217f != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m2241i() {
        return this.f2218g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscriptionResult(");
        if (m2231a()) {
            sb.append("debug:");
            String str = this.f2211a;
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
        if (m2234b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2210a;
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
        String str2 = this.f2213b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (m2236d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f2214c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m2237e()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f2209a);
        }
        if (m2238f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f2215d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m2239g()) {
            sb.append(", ");
            sb.append("topic:");
            String str5 = this.f2216e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2240h()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f2217f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m2241i()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f2218g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
