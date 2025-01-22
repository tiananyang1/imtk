package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.ji */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ji.class */
public class C0736ji implements InterfaceC0744jq<C0736ji, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C0716ip f2169a;

    /* renamed from: a */
    public C0720it f2170a;

    /* renamed from: a */
    public String f2171a;

    /* renamed from: a */
    public Map<String, String> f2173a;

    /* renamed from: b */
    public String f2175b;

    /* renamed from: c */
    public String f2176c;

    /* renamed from: d */
    public String f2177d;

    /* renamed from: e */
    public String f2178e;

    /* renamed from: f */
    public String f2179f;

    /* renamed from: g */
    public String f2180g;

    /* renamed from: h */
    public String f2181h;

    /* renamed from: a */
    private static final C0761kg f2157a = new C0761kg("XmPushActionSendMessage");

    /* renamed from: a */
    private static final C0752jy f2156a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2158b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2159c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2160d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2161e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f2162f = new C0752jy("", (byte) 11, 6);

    /* renamed from: g */
    private static final C0752jy f2163g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f2164h = new C0752jy("", (byte) 12, 8);

    /* renamed from: i */
    private static final C0752jy f2165i = new C0752jy("", (byte) 2, 9);

    /* renamed from: j */
    private static final C0752jy f2166j = new C0752jy("", (byte) 13, 10);

    /* renamed from: k */
    private static final C0752jy f2167k = new C0752jy("", (byte) 11, 11);

    /* renamed from: l */
    private static final C0752jy f2168l = new C0752jy("", (byte) 11, 12);

    /* renamed from: a */
    private BitSet f2172a = new BitSet(1);

    /* renamed from: a */
    public boolean f2174a = true;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0736ji c0736ji) {
        int m2320a;
        int m2320a2;
        int m2323a;
        int m2326a;
        int m2319a;
        int m2320a3;
        int m2320a4;
        int m2320a5;
        int m2320a6;
        int m2320a7;
        int m2319a2;
        int m2320a8;
        if (!getClass().equals(c0736ji.getClass())) {
            return getClass().getName().compareTo(c0736ji.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2193a()).compareTo(Boolean.valueOf(c0736ji.m2193a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2193a() && (m2320a8 = C0745jr.m2320a(this.f2171a, c0736ji.f2171a)) != 0) {
            return m2320a8;
        }
        int compareTo2 = Boolean.valueOf(m2196b()).compareTo(Boolean.valueOf(c0736ji.m2196b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2196b() && (m2319a2 = C0745jr.m2319a(this.f2170a, c0736ji.f2170a)) != 0) {
            return m2319a2;
        }
        int compareTo3 = Boolean.valueOf(m2198c()).compareTo(Boolean.valueOf(c0736ji.m2198c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2198c() && (m2320a7 = C0745jr.m2320a(this.f2175b, c0736ji.f2175b)) != 0) {
            return m2320a7;
        }
        int compareTo4 = Boolean.valueOf(m2200d()).compareTo(Boolean.valueOf(c0736ji.m2200d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2200d() && (m2320a6 = C0745jr.m2320a(this.f2176c, c0736ji.f2176c)) != 0) {
            return m2320a6;
        }
        int compareTo5 = Boolean.valueOf(m2202e()).compareTo(Boolean.valueOf(c0736ji.m2202e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2202e() && (m2320a5 = C0745jr.m2320a(this.f2177d, c0736ji.f2177d)) != 0) {
            return m2320a5;
        }
        int compareTo6 = Boolean.valueOf(m2204f()).compareTo(Boolean.valueOf(c0736ji.m2204f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2204f() && (m2320a4 = C0745jr.m2320a(this.f2178e, c0736ji.f2178e)) != 0) {
            return m2320a4;
        }
        int compareTo7 = Boolean.valueOf(m2205g()).compareTo(Boolean.valueOf(c0736ji.m2205g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2205g() && (m2320a3 = C0745jr.m2320a(this.f2179f, c0736ji.f2179f)) != 0) {
            return m2320a3;
        }
        int compareTo8 = Boolean.valueOf(m2206h()).compareTo(Boolean.valueOf(c0736ji.m2206h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m2206h() && (m2319a = C0745jr.m2319a(this.f2169a, c0736ji.f2169a)) != 0) {
            return m2319a;
        }
        int compareTo9 = Boolean.valueOf(m2207i()).compareTo(Boolean.valueOf(c0736ji.m2207i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m2207i() && (m2326a = C0745jr.m2326a(this.f2174a, c0736ji.f2174a)) != 0) {
            return m2326a;
        }
        int compareTo10 = Boolean.valueOf(m2208j()).compareTo(Boolean.valueOf(c0736ji.m2208j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (m2208j() && (m2323a = C0745jr.m2323a(this.f2173a, c0736ji.f2173a)) != 0) {
            return m2323a;
        }
        int compareTo11 = Boolean.valueOf(m2209k()).compareTo(Boolean.valueOf(c0736ji.m2209k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (m2209k() && (m2320a2 = C0745jr.m2320a(this.f2180g, c0736ji.f2180g)) != 0) {
            return m2320a2;
        }
        int compareTo12 = Boolean.valueOf(m2210l()).compareTo(Boolean.valueOf(c0736ji.m2210l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (!m2210l() || (m2320a = C0745jr.m2320a(this.f2181h, c0736ji.f2181h)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public C0716ip m2189a() {
        return this.f2169a;
    }

    /* renamed from: a */
    public String m2190a() {
        return this.f2175b;
    }

    /* renamed from: a */
    public void m2191a() {
        if (this.f2175b == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f2176c != null) {
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
                m2191a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2171a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2170a = new C0720it();
                        this.f2170a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2175b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2176c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f2177d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 11) {
                        this.f2178e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2179f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 12) {
                        this.f2169a = new C0716ip();
                        this.f2169a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 2) {
                        this.f2174a = abstractC0756kb.mo2365a();
                        m2192a(true);
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 13) {
                        C0755ka mo2344a = abstractC0756kb.mo2344a();
                        this.f2173a = new HashMap(mo2344a.f2335a * 2);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2344a.f2335a) {
                                abstractC0756kb.mo2375i();
                                break;
                            } else {
                                this.f2173a.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
                                i = i2 + 1;
                            }
                        }
                    }
                    break;
                case 11:
                    if (mo2342a.f2324a == 11) {
                        this.f2180g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 12:
                    if (mo2342a.f2324a == 11) {
                        this.f2181h = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2192a(boolean z) {
        this.f2172a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2193a() {
        return this.f2171a != null;
    }

    /* renamed from: a */
    public boolean m2194a(C0736ji c0736ji) {
        if (c0736ji == null) {
            return false;
        }
        boolean m2193a = m2193a();
        boolean m2193a2 = c0736ji.m2193a();
        if ((m2193a || m2193a2) && !(m2193a && m2193a2 && this.f2171a.equals(c0736ji.f2171a))) {
            return false;
        }
        boolean m2196b = m2196b();
        boolean m2196b2 = c0736ji.m2196b();
        if ((m2196b || m2196b2) && !(m2196b && m2196b2 && this.f2170a.m1873a(c0736ji.f2170a))) {
            return false;
        }
        boolean m2198c = m2198c();
        boolean m2198c2 = c0736ji.m2198c();
        if ((m2198c || m2198c2) && !(m2198c && m2198c2 && this.f2175b.equals(c0736ji.f2175b))) {
            return false;
        }
        boolean m2200d = m2200d();
        boolean m2200d2 = c0736ji.m2200d();
        if ((m2200d || m2200d2) && !(m2200d && m2200d2 && this.f2176c.equals(c0736ji.f2176c))) {
            return false;
        }
        boolean m2202e = m2202e();
        boolean m2202e2 = c0736ji.m2202e();
        if ((m2202e || m2202e2) && !(m2202e && m2202e2 && this.f2177d.equals(c0736ji.f2177d))) {
            return false;
        }
        boolean m2204f = m2204f();
        boolean m2204f2 = c0736ji.m2204f();
        if ((m2204f || m2204f2) && !(m2204f && m2204f2 && this.f2178e.equals(c0736ji.f2178e))) {
            return false;
        }
        boolean m2205g = m2205g();
        boolean m2205g2 = c0736ji.m2205g();
        if ((m2205g || m2205g2) && !(m2205g && m2205g2 && this.f2179f.equals(c0736ji.f2179f))) {
            return false;
        }
        boolean m2206h = m2206h();
        boolean m2206h2 = c0736ji.m2206h();
        if ((m2206h || m2206h2) && !(m2206h && m2206h2 && this.f2169a.m1795a(c0736ji.f2169a))) {
            return false;
        }
        boolean m2207i = m2207i();
        boolean m2207i2 = c0736ji.m2207i();
        if ((m2207i || m2207i2) && !(m2207i && m2207i2 && this.f2174a == c0736ji.f2174a)) {
            return false;
        }
        boolean m2208j = m2208j();
        boolean m2208j2 = c0736ji.m2208j();
        if ((m2208j || m2208j2) && !(m2208j && m2208j2 && this.f2173a.equals(c0736ji.f2173a))) {
            return false;
        }
        boolean m2209k = m2209k();
        boolean m2209k2 = c0736ji.m2209k();
        if ((m2209k || m2209k2) && !(m2209k && m2209k2 && this.f2180g.equals(c0736ji.f2180g))) {
            return false;
        }
        boolean m2210l = m2210l();
        boolean m2210l2 = c0736ji.m2210l();
        if (m2210l || m2210l2) {
            return m2210l && m2210l2 && this.f2181h.equals(c0736ji.f2181h);
        }
        return true;
    }

    /* renamed from: b */
    public String m2195b() {
        return this.f2176c;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2191a();
        abstractC0756kb.mo2360a(f2157a);
        if (this.f2171a != null && m2193a()) {
            abstractC0756kb.mo2356a(f2156a);
            abstractC0756kb.mo2361a(this.f2171a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2170a != null && m2196b()) {
            abstractC0756kb.mo2356a(f2158b);
            this.f2170a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2175b != null) {
            abstractC0756kb.mo2356a(f2159c);
            abstractC0756kb.mo2361a(this.f2175b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2176c != null) {
            abstractC0756kb.mo2356a(f2160d);
            abstractC0756kb.mo2361a(this.f2176c);
            abstractC0756kb.mo2366b();
        }
        if (this.f2177d != null && m2202e()) {
            abstractC0756kb.mo2356a(f2161e);
            abstractC0756kb.mo2361a(this.f2177d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2178e != null && m2204f()) {
            abstractC0756kb.mo2356a(f2162f);
            abstractC0756kb.mo2361a(this.f2178e);
            abstractC0756kb.mo2366b();
        }
        if (this.f2179f != null && m2205g()) {
            abstractC0756kb.mo2356a(f2163g);
            abstractC0756kb.mo2361a(this.f2179f);
            abstractC0756kb.mo2366b();
        }
        if (this.f2169a != null && m2206h()) {
            abstractC0756kb.mo2356a(f2164h);
            this.f2169a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (m2207i()) {
            abstractC0756kb.mo2356a(f2165i);
            abstractC0756kb.mo2364a(this.f2174a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2173a != null && m2208j()) {
            abstractC0756kb.mo2356a(f2166j);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f2173a.size()));
            for (Map.Entry<String, String> entry : this.f2173a.entrySet()) {
                abstractC0756kb.mo2361a(entry.getKey());
                abstractC0756kb.mo2361a(entry.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        if (this.f2180g != null && m2209k()) {
            abstractC0756kb.mo2356a(f2167k);
            abstractC0756kb.mo2361a(this.f2180g);
            abstractC0756kb.mo2366b();
        }
        if (this.f2181h != null && m2210l()) {
            abstractC0756kb.mo2356a(f2168l);
            abstractC0756kb.mo2361a(this.f2181h);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m2196b() {
        return this.f2170a != null;
    }

    /* renamed from: c */
    public String m2197c() {
        return this.f2178e;
    }

    /* renamed from: c */
    public boolean m2198c() {
        return this.f2175b != null;
    }

    /* renamed from: d */
    public String m2199d() {
        return this.f2179f;
    }

    /* renamed from: d */
    public boolean m2200d() {
        return this.f2176c != null;
    }

    /* renamed from: e */
    public String m2201e() {
        return this.f2180g;
    }

    /* renamed from: e */
    public boolean m2202e() {
        return this.f2177d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0736ji)) {
            return m2194a((C0736ji) obj);
        }
        return false;
    }

    /* renamed from: f */
    public String m2203f() {
        return this.f2181h;
    }

    /* renamed from: f */
    public boolean m2204f() {
        return this.f2178e != null;
    }

    /* renamed from: g */
    public boolean m2205g() {
        return this.f2179f != null;
    }

    /* renamed from: h */
    public boolean m2206h() {
        return this.f2169a != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m2207i() {
        return this.f2172a.get(0);
    }

    /* renamed from: j */
    public boolean m2208j() {
        return this.f2173a != null;
    }

    /* renamed from: k */
    public boolean m2209k() {
        return this.f2180g != null;
    }

    /* renamed from: l */
    public boolean m2210l() {
        return this.f2181h != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionSendMessage(");
        if (m2193a()) {
            sb.append("debug:");
            String str = this.f2171a;
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
        if (m2196b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2170a;
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
        String str2 = this.f2175b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f2176c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m2202e()) {
            sb.append(", ");
            sb.append("packageName:");
            String str4 = this.f2177d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m2204f()) {
            sb.append(", ");
            sb.append("topic:");
            String str5 = this.f2178e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2205g()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str6 = this.f2179f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m2206h()) {
            sb.append(", ");
            sb.append("message:");
            C0716ip c0716ip = this.f2169a;
            if (c0716ip == null) {
                sb.append("null");
            } else {
                sb.append(c0716ip);
            }
        }
        if (m2207i()) {
            sb.append(", ");
            sb.append("needAck:");
            sb.append(this.f2174a);
        }
        if (m2208j()) {
            sb.append(", ");
            sb.append("params:");
            Map<String, String> map = this.f2173a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m2209k()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f2180g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m2210l()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str8 = this.f2181h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
