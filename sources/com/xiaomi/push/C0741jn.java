package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.jn */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jn.class */
public class C0741jn implements InterfaceC0744jq<C0741jn, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C0720it f2274a;

    /* renamed from: a */
    public String f2275a;

    /* renamed from: a */
    public List<String> f2276a;

    /* renamed from: b */
    public String f2277b;

    /* renamed from: c */
    public String f2278c;

    /* renamed from: d */
    public String f2279d;

    /* renamed from: e */
    public String f2280e;

    /* renamed from: f */
    public String f2281f;

    /* renamed from: a */
    private static final C0761kg f2266a = new C0761kg("XmPushActionUnSubscription");

    /* renamed from: a */
    private static final C0752jy f2265a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2267b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2268c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2269d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2270e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f2271f = new C0752jy("", (byte) 11, 6);

    /* renamed from: g */
    private static final C0752jy f2272g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f2273h = new C0752jy("", (byte) 15, 8);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0741jn c0741jn) {
        int m2322a;
        int m2320a;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2320a5;
        int m2319a;
        int m2320a6;
        if (!getClass().equals(c0741jn.getClass())) {
            return getClass().getName().compareTo(c0741jn.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2283a()).compareTo(Boolean.valueOf(c0741jn.m2283a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2283a() && (m2320a6 = C0745jr.m2320a(this.f2275a, c0741jn.f2275a)) != 0) {
            return m2320a6;
        }
        int compareTo2 = Boolean.valueOf(m2286b()).compareTo(Boolean.valueOf(c0741jn.m2286b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2286b() && (m2319a = C0745jr.m2319a(this.f2274a, c0741jn.f2274a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m2288c()).compareTo(Boolean.valueOf(c0741jn.m2288c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2288c() && (m2320a5 = C0745jr.m2320a(this.f2277b, c0741jn.f2277b)) != 0) {
            return m2320a5;
        }
        int compareTo4 = Boolean.valueOf(m2290d()).compareTo(Boolean.valueOf(c0741jn.m2290d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2290d() && (m2320a4 = C0745jr.m2320a(this.f2278c, c0741jn.f2278c)) != 0) {
            return m2320a4;
        }
        int compareTo5 = Boolean.valueOf(m2292e()).compareTo(Boolean.valueOf(c0741jn.m2292e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2292e() && (m2320a3 = C0745jr.m2320a(this.f2279d, c0741jn.f2279d)) != 0) {
            return m2320a3;
        }
        int compareTo6 = Boolean.valueOf(m2293f()).compareTo(Boolean.valueOf(c0741jn.m2293f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2293f() && (m2320a2 = C0745jr.m2320a(this.f2280e, c0741jn.f2280e)) != 0) {
            return m2320a2;
        }
        int compareTo7 = Boolean.valueOf(m2294g()).compareTo(Boolean.valueOf(c0741jn.m2294g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2294g() && (m2320a = C0745jr.m2320a(this.f2281f, c0741jn.f2281f)) != 0) {
            return m2320a;
        }
        int compareTo8 = Boolean.valueOf(m2295h()).compareTo(Boolean.valueOf(c0741jn.m2295h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (!m2295h() || (m2322a = C0745jr.m2322a(this.f2276a, c0741jn.f2276a)) == 0) {
            return 0;
        }
        return m2322a;
    }

    /* renamed from: a */
    public C0741jn m2281a(String str) {
        this.f2277b = str;
        return this;
    }

    /* renamed from: a */
    public void m2282a() {
        if (this.f2277b == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f2278c == null) {
            throw new C0757kc("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f2279d != null) {
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
                m2282a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2275a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2274a = new C0720it();
                        this.f2274a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2277b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2278c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f2279d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 11) {
                        this.f2280e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2281f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 15) {
                        C0753jz mo2343a = abstractC0756kb.mo2343a();
                        this.f2276a = new ArrayList(mo2343a.f2328a);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2343a.f2328a) {
                                abstractC0756kb.mo2376j();
                                break;
                            } else {
                                this.f2276a.add(abstractC0756kb.mo2347a());
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
    public boolean m2283a() {
        return this.f2275a != null;
    }

    /* renamed from: a */
    public boolean m2284a(C0741jn c0741jn) {
        if (c0741jn == null) {
            return false;
        }
        boolean m2283a = m2283a();
        boolean m2283a2 = c0741jn.m2283a();
        if ((m2283a || m2283a2) && !(m2283a && m2283a2 && this.f2275a.equals(c0741jn.f2275a))) {
            return false;
        }
        boolean m2286b = m2286b();
        boolean m2286b2 = c0741jn.m2286b();
        if ((m2286b || m2286b2) && !(m2286b && m2286b2 && this.f2274a.m1873a(c0741jn.f2274a))) {
            return false;
        }
        boolean m2288c = m2288c();
        boolean m2288c2 = c0741jn.m2288c();
        if ((m2288c || m2288c2) && !(m2288c && m2288c2 && this.f2277b.equals(c0741jn.f2277b))) {
            return false;
        }
        boolean m2290d = m2290d();
        boolean m2290d2 = c0741jn.m2290d();
        if ((m2290d || m2290d2) && !(m2290d && m2290d2 && this.f2278c.equals(c0741jn.f2278c))) {
            return false;
        }
        boolean m2292e = m2292e();
        boolean m2292e2 = c0741jn.m2292e();
        if ((m2292e || m2292e2) && !(m2292e && m2292e2 && this.f2279d.equals(c0741jn.f2279d))) {
            return false;
        }
        boolean m2293f = m2293f();
        boolean m2293f2 = c0741jn.m2293f();
        if ((m2293f || m2293f2) && !(m2293f && m2293f2 && this.f2280e.equals(c0741jn.f2280e))) {
            return false;
        }
        boolean m2294g = m2294g();
        boolean m2294g2 = c0741jn.m2294g();
        if ((m2294g || m2294g2) && !(m2294g && m2294g2 && this.f2281f.equals(c0741jn.f2281f))) {
            return false;
        }
        boolean m2295h = m2295h();
        boolean m2295h2 = c0741jn.m2295h();
        if (m2295h || m2295h2) {
            return m2295h && m2295h2 && this.f2276a.equals(c0741jn.f2276a);
        }
        return true;
    }

    /* renamed from: b */
    public C0741jn m2285b(String str) {
        this.f2278c = str;
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2282a();
        abstractC0756kb.mo2360a(f2266a);
        if (this.f2275a != null && m2283a()) {
            abstractC0756kb.mo2356a(f2265a);
            abstractC0756kb.mo2361a(this.f2275a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2274a != null && m2286b()) {
            abstractC0756kb.mo2356a(f2267b);
            this.f2274a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2277b != null) {
            abstractC0756kb.mo2356a(f2268c);
            abstractC0756kb.mo2361a(this.f2277b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2278c != null) {
            abstractC0756kb.mo2356a(f2269d);
            abstractC0756kb.mo2361a(this.f2278c);
            abstractC0756kb.mo2366b();
        }
        if (this.f2279d != null) {
            abstractC0756kb.mo2356a(f2270e);
            abstractC0756kb.mo2361a(this.f2279d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2280e != null && m2293f()) {
            abstractC0756kb.mo2356a(f2271f);
            abstractC0756kb.mo2361a(this.f2280e);
            abstractC0756kb.mo2366b();
        }
        if (this.f2281f != null && m2294g()) {
            abstractC0756kb.mo2356a(f2272g);
            abstractC0756kb.mo2361a(this.f2281f);
            abstractC0756kb.mo2366b();
        }
        if (this.f2276a != null && m2295h()) {
            abstractC0756kb.mo2356a(f2273h);
            abstractC0756kb.mo2357a(new C0753jz((byte) 11, this.f2276a.size()));
            Iterator<String> it = this.f2276a.iterator();
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
    public boolean m2286b() {
        return this.f2274a != null;
    }

    /* renamed from: c */
    public C0741jn m2287c(String str) {
        this.f2279d = str;
        return this;
    }

    /* renamed from: c */
    public boolean m2288c() {
        return this.f2277b != null;
    }

    /* renamed from: d */
    public C0741jn m2289d(String str) {
        this.f2280e = str;
        return this;
    }

    /* renamed from: d */
    public boolean m2290d() {
        return this.f2278c != null;
    }

    /* renamed from: e */
    public C0741jn m2291e(String str) {
        this.f2281f = str;
        return this;
    }

    /* renamed from: e */
    public boolean m2292e() {
        return this.f2279d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0741jn)) {
            return m2284a((C0741jn) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2293f() {
        return this.f2280e != null;
    }

    /* renamed from: g */
    public boolean m2294g() {
        return this.f2281f != null;
    }

    /* renamed from: h */
    public boolean m2295h() {
        return this.f2276a != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscription(");
        if (m2283a()) {
            sb.append("debug:");
            String str = this.f2275a;
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
        if (m2286b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2274a;
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
        String str2 = this.f2277b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f2278c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("topic:");
        String str4 = this.f2279d;
        if (str4 == null) {
            sb.append("null");
        } else {
            sb.append(str4);
        }
        if (m2293f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f2280e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2294g()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.f2281f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m2295h()) {
            sb.append(", ");
            sb.append("aliases:");
            List<String> list = this.f2276a;
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
