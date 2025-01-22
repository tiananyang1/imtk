package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.jh */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jh.class */
public class C0735jh implements InterfaceC0744jq<C0735jh, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f2148a;

    /* renamed from: a */
    public C0720it f2149a;

    /* renamed from: a */
    public String f2150a;

    /* renamed from: a */
    private BitSet f2151a = new BitSet(1);

    /* renamed from: b */
    public String f2152b;

    /* renamed from: c */
    public String f2153c;

    /* renamed from: d */
    public String f2154d;

    /* renamed from: e */
    public String f2155e;

    /* renamed from: a */
    private static final C0761kg f2141a = new C0761kg("XmPushActionSendFeedbackResult");

    /* renamed from: a */
    private static final C0752jy f2140a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2142b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2143c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2144d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2145e = new C0752jy("", (byte) 10, 6);

    /* renamed from: f */
    private static final C0752jy f2146f = new C0752jy("", (byte) 11, 7);

    /* renamed from: g */
    private static final C0752jy f2147g = new C0752jy("", (byte) 11, 8);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0735jh c0735jh) {
        int m2320a;
        int m2320a2;
        int m2318a;
        int m2320a3;
        int m2320a4;
        int m2319a;
        int m2320a5;
        if (!getClass().equals(c0735jh.getClass())) {
            return getClass().getName().compareTo(c0735jh.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2180a()).compareTo(Boolean.valueOf(c0735jh.m2180a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2180a() && (m2320a5 = C0745jr.m2320a(this.f2150a, c0735jh.f2150a)) != 0) {
            return m2320a5;
        }
        int compareTo2 = Boolean.valueOf(m2182b()).compareTo(Boolean.valueOf(c0735jh.m2182b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2182b() && (m2319a = C0745jr.m2319a(this.f2149a, c0735jh.f2149a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m2183c()).compareTo(Boolean.valueOf(c0735jh.m2183c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2183c() && (m2320a4 = C0745jr.m2320a(this.f2152b, c0735jh.f2152b)) != 0) {
            return m2320a4;
        }
        int compareTo4 = Boolean.valueOf(m2184d()).compareTo(Boolean.valueOf(c0735jh.m2184d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2184d() && (m2320a3 = C0745jr.m2320a(this.f2153c, c0735jh.f2153c)) != 0) {
            return m2320a3;
        }
        int compareTo5 = Boolean.valueOf(m2185e()).compareTo(Boolean.valueOf(c0735jh.m2185e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2185e() && (m2318a = C0745jr.m2318a(this.f2148a, c0735jh.f2148a)) != 0) {
            return m2318a;
        }
        int compareTo6 = Boolean.valueOf(m2186f()).compareTo(Boolean.valueOf(c0735jh.m2186f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2186f() && (m2320a2 = C0745jr.m2320a(this.f2154d, c0735jh.f2154d)) != 0) {
            return m2320a2;
        }
        int compareTo7 = Boolean.valueOf(m2187g()).compareTo(Boolean.valueOf(c0735jh.m2187g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (!m2187g() || (m2320a = C0745jr.m2320a(this.f2155e, c0735jh.f2155e)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public void m2178a() {
        if (this.f2152b == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f2153c != null) {
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
                if (m2185e()) {
                    m2178a();
                    return;
                }
                throw new C0757kc("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2150a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2149a = new C0720it();
                        this.f2149a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2152b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2153c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 10) {
                        this.f2148a = abstractC0756kb.mo2341a();
                        m2179a(true);
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2154d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f2155e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2179a(boolean z) {
        this.f2151a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2180a() {
        return this.f2150a != null;
    }

    /* renamed from: a */
    public boolean m2181a(C0735jh c0735jh) {
        if (c0735jh == null) {
            return false;
        }
        boolean m2180a = m2180a();
        boolean m2180a2 = c0735jh.m2180a();
        if ((m2180a || m2180a2) && !(m2180a && m2180a2 && this.f2150a.equals(c0735jh.f2150a))) {
            return false;
        }
        boolean m2182b = m2182b();
        boolean m2182b2 = c0735jh.m2182b();
        if ((m2182b || m2182b2) && !(m2182b && m2182b2 && this.f2149a.m1873a(c0735jh.f2149a))) {
            return false;
        }
        boolean m2183c = m2183c();
        boolean m2183c2 = c0735jh.m2183c();
        if ((m2183c || m2183c2) && !(m2183c && m2183c2 && this.f2152b.equals(c0735jh.f2152b))) {
            return false;
        }
        boolean m2184d = m2184d();
        boolean m2184d2 = c0735jh.m2184d();
        if (((m2184d || m2184d2) && !(m2184d && m2184d2 && this.f2153c.equals(c0735jh.f2153c))) || this.f2148a != c0735jh.f2148a) {
            return false;
        }
        boolean m2186f = m2186f();
        boolean m2186f2 = c0735jh.m2186f();
        if ((m2186f || m2186f2) && !(m2186f && m2186f2 && this.f2154d.equals(c0735jh.f2154d))) {
            return false;
        }
        boolean m2187g = m2187g();
        boolean m2187g2 = c0735jh.m2187g();
        if (m2187g || m2187g2) {
            return m2187g && m2187g2 && this.f2155e.equals(c0735jh.f2155e);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2178a();
        abstractC0756kb.mo2360a(f2141a);
        if (this.f2150a != null && m2180a()) {
            abstractC0756kb.mo2356a(f2140a);
            abstractC0756kb.mo2361a(this.f2150a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2149a != null && m2182b()) {
            abstractC0756kb.mo2356a(f2142b);
            this.f2149a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2152b != null) {
            abstractC0756kb.mo2356a(f2143c);
            abstractC0756kb.mo2361a(this.f2152b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2153c != null) {
            abstractC0756kb.mo2356a(f2144d);
            abstractC0756kb.mo2361a(this.f2153c);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f2145e);
        abstractC0756kb.mo2355a(this.f2148a);
        abstractC0756kb.mo2366b();
        if (this.f2154d != null && m2186f()) {
            abstractC0756kb.mo2356a(f2146f);
            abstractC0756kb.mo2361a(this.f2154d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2155e != null && m2187g()) {
            abstractC0756kb.mo2356a(f2147g);
            abstractC0756kb.mo2361a(this.f2155e);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m2182b() {
        return this.f2149a != null;
    }

    /* renamed from: c */
    public boolean m2183c() {
        return this.f2152b != null;
    }

    /* renamed from: d */
    public boolean m2184d() {
        return this.f2153c != null;
    }

    /* renamed from: e */
    public boolean m2185e() {
        return this.f2151a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0735jh)) {
            return m2181a((C0735jh) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2186f() {
        return this.f2154d != null;
    }

    /* renamed from: g */
    public boolean m2187g() {
        return this.f2155e != null;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendFeedbackResult(");
        if (m2180a()) {
            sb.append("debug:");
            String str = this.f2150a;
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
        if (m2182b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2149a;
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
        String str2 = this.f2152b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f2153c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f2148a);
        if (m2186f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f2154d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m2187g()) {
            sb.append(", ");
            sb.append("category:");
            String str5 = this.f2155e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
