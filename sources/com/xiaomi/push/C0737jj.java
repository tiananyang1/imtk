package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.jj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jj.class */
public class C0737jj implements InterfaceC0744jq<C0737jj, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C0720it f2191a;

    /* renamed from: a */
    public String f2192a;

    /* renamed from: a */
    public List<String> f2193a;

    /* renamed from: b */
    public String f2194b;

    /* renamed from: c */
    public String f2195c;

    /* renamed from: d */
    public String f2196d;

    /* renamed from: e */
    public String f2197e;

    /* renamed from: f */
    public String f2198f;

    /* renamed from: a */
    private static final C0761kg f2183a = new C0761kg("XmPushActionSubscription");

    /* renamed from: a */
    private static final C0752jy f2182a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2184b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2185c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2186d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2187e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f2188f = new C0752jy("", (byte) 11, 6);

    /* renamed from: g */
    private static final C0752jy f2189g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f2190h = new C0752jy("", (byte) 15, 8);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0737jj c0737jj) {
        int m2322a;
        int m2320a;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2320a5;
        int m2319a;
        int m2320a6;
        if (!getClass().equals(c0737jj.getClass())) {
            return getClass().getName().compareTo(c0737jj.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2214a()).compareTo(Boolean.valueOf(c0737jj.m2214a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2214a() && (m2320a6 = C0745jr.m2320a(this.f2192a, c0737jj.f2192a)) != 0) {
            return m2320a6;
        }
        int compareTo2 = Boolean.valueOf(m2217b()).compareTo(Boolean.valueOf(c0737jj.m2217b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2217b() && (m2319a = C0745jr.m2319a(this.f2191a, c0737jj.f2191a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m2219c()).compareTo(Boolean.valueOf(c0737jj.m2219c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2219c() && (m2320a5 = C0745jr.m2320a(this.f2194b, c0737jj.f2194b)) != 0) {
            return m2320a5;
        }
        int compareTo4 = Boolean.valueOf(m2221d()).compareTo(Boolean.valueOf(c0737jj.m2221d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2221d() && (m2320a4 = C0745jr.m2320a(this.f2195c, c0737jj.f2195c)) != 0) {
            return m2320a4;
        }
        int compareTo5 = Boolean.valueOf(m2223e()).compareTo(Boolean.valueOf(c0737jj.m2223e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2223e() && (m2320a3 = C0745jr.m2320a(this.f2196d, c0737jj.f2196d)) != 0) {
            return m2320a3;
        }
        int compareTo6 = Boolean.valueOf(m2224f()).compareTo(Boolean.valueOf(c0737jj.m2224f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2224f() && (m2320a2 = C0745jr.m2320a(this.f2197e, c0737jj.f2197e)) != 0) {
            return m2320a2;
        }
        int compareTo7 = Boolean.valueOf(m2225g()).compareTo(Boolean.valueOf(c0737jj.m2225g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2225g() && (m2320a = C0745jr.m2320a(this.f2198f, c0737jj.f2198f)) != 0) {
            return m2320a;
        }
        int compareTo8 = Boolean.valueOf(m2226h()).compareTo(Boolean.valueOf(c0737jj.m2226h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (!m2226h() || (m2322a = C0745jr.m2322a(this.f2193a, c0737jj.f2193a)) == 0) {
            return 0;
        }
        return m2322a;
    }

    /* renamed from: a */
    public C0737jj m2212a(String str) {
        this.f2194b = str;
        return this;
    }

    /* renamed from: a */
    public void m2213a() {
        if (this.f2194b == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f2195c == null) {
            throw new C0757kc("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f2196d != null) {
            return;
        }
        throw new C0757kc("Required field 'topic' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m2213a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2192a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2191a = new C0720it();
                        this.f2191a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2194b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2195c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f2196d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 11) {
                        this.f2197e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2198f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 15) {
                        C0753jz mo2343a = abstractC0756kb.mo2343a();
                        this.f2193a = new ArrayList(mo2343a.f2328a);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2343a.f2328a) {
                                abstractC0756kb.mo2376j();
                                break;
                            } else {
                                this.f2193a.add(abstractC0756kb.mo2347a());
                                i = i2 + 1;
                            }
                        }
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public boolean m2214a() {
        return this.f2192a != null;
    }

    /* renamed from: a */
    public boolean m2215a(C0737jj c0737jj) {
        if (c0737jj == null) {
            return false;
        }
        boolean m2214a = m2214a();
        boolean m2214a2 = c0737jj.m2214a();
        if ((m2214a || m2214a2) && !(m2214a && m2214a2 && this.f2192a.equals(c0737jj.f2192a))) {
            return false;
        }
        boolean m2217b = m2217b();
        boolean m2217b2 = c0737jj.m2217b();
        if ((m2217b || m2217b2) && !(m2217b && m2217b2 && this.f2191a.m1873a(c0737jj.f2191a))) {
            return false;
        }
        boolean m2219c = m2219c();
        boolean m2219c2 = c0737jj.m2219c();
        if ((m2219c || m2219c2) && !(m2219c && m2219c2 && this.f2194b.equals(c0737jj.f2194b))) {
            return false;
        }
        boolean m2221d = m2221d();
        boolean m2221d2 = c0737jj.m2221d();
        if ((m2221d || m2221d2) && !(m2221d && m2221d2 && this.f2195c.equals(c0737jj.f2195c))) {
            return false;
        }
        boolean m2223e = m2223e();
        boolean m2223e2 = c0737jj.m2223e();
        if ((m2223e || m2223e2) && !(m2223e && m2223e2 && this.f2196d.equals(c0737jj.f2196d))) {
            return false;
        }
        boolean m2224f = m2224f();
        boolean m2224f2 = c0737jj.m2224f();
        if ((m2224f || m2224f2) && !(m2224f && m2224f2 && this.f2197e.equals(c0737jj.f2197e))) {
            return false;
        }
        boolean m2225g = m2225g();
        boolean m2225g2 = c0737jj.m2225g();
        if ((m2225g || m2225g2) && !(m2225g && m2225g2 && this.f2198f.equals(c0737jj.f2198f))) {
            return false;
        }
        boolean m2226h = m2226h();
        boolean m2226h2 = c0737jj.m2226h();
        if (m2226h || m2226h2) {
            return m2226h && m2226h2 && this.f2193a.equals(c0737jj.f2193a);
        }
        return true;
    }

    /* renamed from: b */
    public C0737jj m2216b(String str) {
        this.f2195c = str;
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2213a();
        abstractC0756kb.mo2360a(f2183a);
        if (this.f2192a != null && m2214a()) {
            abstractC0756kb.mo2356a(f2182a);
            abstractC0756kb.mo2361a(this.f2192a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2191a != null && m2217b()) {
            abstractC0756kb.mo2356a(f2184b);
            this.f2191a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2194b != null) {
            abstractC0756kb.mo2356a(f2185c);
            abstractC0756kb.mo2361a(this.f2194b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2195c != null) {
            abstractC0756kb.mo2356a(f2186d);
            abstractC0756kb.mo2361a(this.f2195c);
            abstractC0756kb.mo2366b();
        }
        if (this.f2196d != null) {
            abstractC0756kb.mo2356a(f2187e);
            abstractC0756kb.mo2361a(this.f2196d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2197e != null && m2224f()) {
            abstractC0756kb.mo2356a(f2188f);
            abstractC0756kb.mo2361a(this.f2197e);
            abstractC0756kb.mo2366b();
        }
        if (this.f2198f != null && m2225g()) {
            abstractC0756kb.mo2356a(f2189g);
            abstractC0756kb.mo2361a(this.f2198f);
            abstractC0756kb.mo2366b();
        }
        if (this.f2193a != null && m2226h()) {
            abstractC0756kb.mo2356a(f2190h);
            abstractC0756kb.mo2357a(new C0753jz((byte) 11, this.f2193a.size()));
            Iterator<String> it = this.f2193a.iterator();
            while (it.hasNext()) {
                abstractC0756kb.mo2361a(it.next());
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m2217b() {
        return this.f2191a != null;
    }

    /* renamed from: c */
    public C0737jj m2218c(String str) {
        this.f2196d = str;
        return this;
    }

    /* renamed from: c */
    public boolean m2219c() {
        return this.f2194b != null;
    }

    /* renamed from: d */
    public C0737jj m2220d(String str) {
        this.f2197e = str;
        return this;
    }

    /* renamed from: d */
    public boolean m2221d() {
        return this.f2195c != null;
    }

    /* renamed from: e */
    public C0737jj m2222e(String str) {
        this.f2198f = str;
        return this;
    }

    /* renamed from: e */
    public boolean m2223e() {
        return this.f2196d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0737jj)) {
            return m2215a((C0737jj) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2224f() {
        return this.f2197e != null;
    }

    /* renamed from: g */
    public boolean m2225g() {
        return this.f2198f != null;
    }

    /* renamed from: h */
    public boolean m2226h() {
        return this.f2193a != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSubscription(");
        if (m2214a()) {
            sb.append("debug:");
            String str = this.f2192a;
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
        if (m2217b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2191a;
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
        String str2 = this.f2194b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f2195c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("topic:");
        String str4 = this.f2196d;
        if (str4 == null) {
            sb.append("null");
        } else {
            sb.append(str4);
        }
        if (m2224f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f2197e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2225g()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.f2198f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m2226h()) {
            sb.append(", ");
            sb.append("aliases:");
            List<String> list = this.f2193a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
