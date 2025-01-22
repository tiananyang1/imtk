package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.jo */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jo.class */
public class C0742jo implements InterfaceC0744jq<C0742jo, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f2292a;

    /* renamed from: a */
    public C0720it f2293a;

    /* renamed from: a */
    public String f2294a;

    /* renamed from: a */
    private BitSet f2295a = new BitSet(1);

    /* renamed from: b */
    public String f2296b;

    /* renamed from: c */
    public String f2297c;

    /* renamed from: d */
    public String f2298d;

    /* renamed from: e */
    public String f2299e;

    /* renamed from: f */
    public String f2300f;

    /* renamed from: g */
    public String f2301g;

    /* renamed from: a */
    private static final C0761kg f2283a = new C0761kg("XmPushActionUnSubscriptionResult");

    /* renamed from: a */
    private static final C0752jy f2282a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2284b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2285c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2286d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2287e = new C0752jy("", (byte) 10, 6);

    /* renamed from: f */
    private static final C0752jy f2288f = new C0752jy("", (byte) 11, 7);

    /* renamed from: g */
    private static final C0752jy f2289g = new C0752jy("", (byte) 11, 8);

    /* renamed from: h */
    private static final C0752jy f2290h = new C0752jy("", (byte) 11, 9);

    /* renamed from: i */
    private static final C0752jy f2291i = new C0752jy("", (byte) 11, 10);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0742jo c0742jo) {
        int m2320a;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2318a;
        int m2320a5;
        int m2320a6;
        int m2319a;
        int m2320a7;
        if (!getClass().equals(c0742jo.getClass())) {
            return getClass().getName().compareTo(c0742jo.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2300a()).compareTo(Boolean.valueOf(c0742jo.m2300a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2300a() && (m2320a7 = C0745jr.m2320a(this.f2294a, c0742jo.f2294a)) != 0) {
            return m2320a7;
        }
        int compareTo2 = Boolean.valueOf(m2303b()).compareTo(Boolean.valueOf(c0742jo.m2303b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2303b() && (m2319a = C0745jr.m2319a(this.f2293a, c0742jo.f2293a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m2304c()).compareTo(Boolean.valueOf(c0742jo.m2304c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2304c() && (m2320a6 = C0745jr.m2320a(this.f2296b, c0742jo.f2296b)) != 0) {
            return m2320a6;
        }
        int compareTo4 = Boolean.valueOf(m2305d()).compareTo(Boolean.valueOf(c0742jo.m2305d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2305d() && (m2320a5 = C0745jr.m2320a(this.f2297c, c0742jo.f2297c)) != 0) {
            return m2320a5;
        }
        int compareTo5 = Boolean.valueOf(m2306e()).compareTo(Boolean.valueOf(c0742jo.m2306e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2306e() && (m2318a = C0745jr.m2318a(this.f2292a, c0742jo.f2292a)) != 0) {
            return m2318a;
        }
        int compareTo6 = Boolean.valueOf(m2307f()).compareTo(Boolean.valueOf(c0742jo.m2307f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2307f() && (m2320a4 = C0745jr.m2320a(this.f2298d, c0742jo.f2298d)) != 0) {
            return m2320a4;
        }
        int compareTo7 = Boolean.valueOf(m2308g()).compareTo(Boolean.valueOf(c0742jo.m2308g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2308g() && (m2320a3 = C0745jr.m2320a(this.f2299e, c0742jo.f2299e)) != 0) {
            return m2320a3;
        }
        int compareTo8 = Boolean.valueOf(m2309h()).compareTo(Boolean.valueOf(c0742jo.m2309h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m2309h() && (m2320a2 = C0745jr.m2320a(this.f2300f, c0742jo.f2300f)) != 0) {
            return m2320a2;
        }
        int compareTo9 = Boolean.valueOf(m2310i()).compareTo(Boolean.valueOf(c0742jo.m2310i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (!m2310i() || (m2320a = C0745jr.m2320a(this.f2301g, c0742jo.f2301g)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public String m2297a() {
        return this.f2299e;
    }

    /* renamed from: a */
    public void m2298a() {
        if (this.f2296b != null) {
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
                m2298a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2294a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2293a = new C0720it();
                        this.f2293a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2296b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2297c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 10) {
                        this.f2292a = abstractC0756kb.mo2341a();
                        m2299a(true);
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2298d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f2299e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f2300f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 11) {
                        this.f2301g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2299a(boolean z) {
        this.f2295a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2300a() {
        return this.f2294a != null;
    }

    /* renamed from: a */
    public boolean m2301a(C0742jo c0742jo) {
        if (c0742jo == null) {
            return false;
        }
        boolean m2300a = m2300a();
        boolean m2300a2 = c0742jo.m2300a();
        if ((m2300a || m2300a2) && !(m2300a && m2300a2 && this.f2294a.equals(c0742jo.f2294a))) {
            return false;
        }
        boolean m2303b = m2303b();
        boolean m2303b2 = c0742jo.m2303b();
        if ((m2303b || m2303b2) && !(m2303b && m2303b2 && this.f2293a.m1873a(c0742jo.f2293a))) {
            return false;
        }
        boolean m2304c = m2304c();
        boolean m2304c2 = c0742jo.m2304c();
        if ((m2304c || m2304c2) && !(m2304c && m2304c2 && this.f2296b.equals(c0742jo.f2296b))) {
            return false;
        }
        boolean m2305d = m2305d();
        boolean m2305d2 = c0742jo.m2305d();
        if ((m2305d || m2305d2) && !(m2305d && m2305d2 && this.f2297c.equals(c0742jo.f2297c))) {
            return false;
        }
        boolean m2306e = m2306e();
        boolean m2306e2 = c0742jo.m2306e();
        if ((m2306e || m2306e2) && !(m2306e && m2306e2 && this.f2292a == c0742jo.f2292a)) {
            return false;
        }
        boolean m2307f = m2307f();
        boolean m2307f2 = c0742jo.m2307f();
        if ((m2307f || m2307f2) && !(m2307f && m2307f2 && this.f2298d.equals(c0742jo.f2298d))) {
            return false;
        }
        boolean m2308g = m2308g();
        boolean m2308g2 = c0742jo.m2308g();
        if ((m2308g || m2308g2) && !(m2308g && m2308g2 && this.f2299e.equals(c0742jo.f2299e))) {
            return false;
        }
        boolean m2309h = m2309h();
        boolean m2309h2 = c0742jo.m2309h();
        if ((m2309h || m2309h2) && !(m2309h && m2309h2 && this.f2300f.equals(c0742jo.f2300f))) {
            return false;
        }
        boolean m2310i = m2310i();
        boolean m2310i2 = c0742jo.m2310i();
        if (m2310i || m2310i2) {
            return m2310i && m2310i2 && this.f2301g.equals(c0742jo.f2301g);
        }
        return true;
    }

    /* renamed from: b */
    public String m2302b() {
        return this.f2301g;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2298a();
        abstractC0756kb.mo2360a(f2283a);
        if (this.f2294a != null && m2300a()) {
            abstractC0756kb.mo2356a(f2282a);
            abstractC0756kb.mo2361a(this.f2294a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2293a != null && m2303b()) {
            abstractC0756kb.mo2356a(f2284b);
            this.f2293a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2296b != null) {
            abstractC0756kb.mo2356a(f2285c);
            abstractC0756kb.mo2361a(this.f2296b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2297c != null && m2305d()) {
            abstractC0756kb.mo2356a(f2286d);
            abstractC0756kb.mo2361a(this.f2297c);
            abstractC0756kb.mo2366b();
        }
        if (m2306e()) {
            abstractC0756kb.mo2356a(f2287e);
            abstractC0756kb.mo2355a(this.f2292a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2298d != null && m2307f()) {
            abstractC0756kb.mo2356a(f2288f);
            abstractC0756kb.mo2361a(this.f2298d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2299e != null && m2308g()) {
            abstractC0756kb.mo2356a(f2289g);
            abstractC0756kb.mo2361a(this.f2299e);
            abstractC0756kb.mo2366b();
        }
        if (this.f2300f != null && m2309h()) {
            abstractC0756kb.mo2356a(f2290h);
            abstractC0756kb.mo2361a(this.f2300f);
            abstractC0756kb.mo2366b();
        }
        if (this.f2301g != null && m2310i()) {
            abstractC0756kb.mo2356a(f2291i);
            abstractC0756kb.mo2361a(this.f2301g);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m2303b() {
        return this.f2293a != null;
    }

    /* renamed from: c */
    public boolean m2304c() {
        return this.f2296b != null;
    }

    /* renamed from: d */
    public boolean m2305d() {
        return this.f2297c != null;
    }

    /* renamed from: e */
    public boolean m2306e() {
        return this.f2295a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0742jo)) {
            return m2301a((C0742jo) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2307f() {
        return this.f2298d != null;
    }

    /* renamed from: g */
    public boolean m2308g() {
        return this.f2299e != null;
    }

    /* renamed from: h */
    public boolean m2309h() {
        return this.f2300f != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m2310i() {
        return this.f2301g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionUnSubscriptionResult(");
        if (m2300a()) {
            sb.append("debug:");
            String str = this.f2294a;
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
        if (m2303b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2293a;
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
        String str2 = this.f2296b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (m2305d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f2297c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m2306e()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f2292a);
        }
        if (m2307f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f2298d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m2308g()) {
            sb.append(", ");
            sb.append("topic:");
            String str5 = this.f2299e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2309h()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f2300f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m2310i()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f2301g;
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
