package com.xiaomi.push;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.je */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/je.class */
public class C0732je implements InterfaceC0744jq<C0732je, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f2028a;

    /* renamed from: a */
    public C0720it f2029a;

    /* renamed from: a */
    public String f2030a;

    /* renamed from: a */
    public ByteBuffer f2031a;

    /* renamed from: a */
    private BitSet f2032a;

    /* renamed from: a */
    public Map<String, String> f2033a;

    /* renamed from: a */
    public boolean f2034a;

    /* renamed from: b */
    public String f2035b;

    /* renamed from: b */
    public boolean f2036b;

    /* renamed from: c */
    public String f2037c;

    /* renamed from: d */
    public String f2038d;

    /* renamed from: e */
    public String f2039e;

    /* renamed from: f */
    public String f2040f;

    /* renamed from: g */
    public String f2041g;

    /* renamed from: h */
    public String f2042h;

    /* renamed from: i */
    public String f2043i;

    /* renamed from: a */
    private static final C0761kg f2013a = new C0761kg("XmPushActionNotification");

    /* renamed from: a */
    private static final C0752jy f2012a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2014b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2015c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2016d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2017e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f2018f = new C0752jy("", (byte) 2, 6);

    /* renamed from: g */
    private static final C0752jy f2019g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f2020h = new C0752jy("", (byte) 13, 8);

    /* renamed from: i */
    private static final C0752jy f2021i = new C0752jy("", (byte) 11, 9);

    /* renamed from: j */
    private static final C0752jy f2022j = new C0752jy("", (byte) 11, 10);

    /* renamed from: k */
    private static final C0752jy f2023k = new C0752jy("", (byte) 11, 12);

    /* renamed from: l */
    private static final C0752jy f2024l = new C0752jy("", (byte) 11, 13);

    /* renamed from: m */
    private static final C0752jy f2025m = new C0752jy("", (byte) 11, 14);

    /* renamed from: n */
    private static final C0752jy f2026n = new C0752jy("", (byte) 10, 15);

    /* renamed from: o */
    private static final C0752jy f2027o = new C0752jy("", (byte) 2, 20);

    public C0732je() {
        this.f2032a = new BitSet(3);
        this.f2034a = true;
        this.f2036b = false;
    }

    public C0732je(String str, boolean z) {
        this();
        this.f2035b = str;
        this.f2034a = z;
        m2067a(true);
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0732je c0732je) {
        int m2326a;
        int m2318a;
        int m2319a;
        int m2320a;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2323a;
        int m2320a5;
        int m2326a2;
        int m2320a6;
        int m2320a7;
        int m2320a8;
        int m2319a2;
        int m2320a9;
        if (!getClass().equals(c0732je.getClass())) {
            return getClass().getName().compareTo(c0732je.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2068a()).compareTo(Boolean.valueOf(c0732je.m2068a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2068a() && (m2320a9 = C0745jr.m2320a(this.f2030a, c0732je.f2030a)) != 0) {
            return m2320a9;
        }
        int compareTo2 = Boolean.valueOf(m2074b()).compareTo(Boolean.valueOf(c0732je.m2074b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2074b() && (m2319a2 = C0745jr.m2319a(this.f2029a, c0732je.f2029a)) != 0) {
            return m2319a2;
        }
        int compareTo3 = Boolean.valueOf(m2078c()).compareTo(Boolean.valueOf(c0732je.m2078c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2078c() && (m2320a8 = C0745jr.m2320a(this.f2035b, c0732je.f2035b)) != 0) {
            return m2320a8;
        }
        int compareTo4 = Boolean.valueOf(m2080d()).compareTo(Boolean.valueOf(c0732je.m2080d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2080d() && (m2320a7 = C0745jr.m2320a(this.f2037c, c0732je.f2037c)) != 0) {
            return m2320a7;
        }
        int compareTo5 = Boolean.valueOf(m2081e()).compareTo(Boolean.valueOf(c0732je.m2081e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2081e() && (m2320a6 = C0745jr.m2320a(this.f2038d, c0732je.f2038d)) != 0) {
            return m2320a6;
        }
        int compareTo6 = Boolean.valueOf(m2082f()).compareTo(Boolean.valueOf(c0732je.m2082f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2082f() && (m2326a2 = C0745jr.m2326a(this.f2034a, c0732je.f2034a)) != 0) {
            return m2326a2;
        }
        int compareTo7 = Boolean.valueOf(m2083g()).compareTo(Boolean.valueOf(c0732je.m2083g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2083g() && (m2320a5 = C0745jr.m2320a(this.f2039e, c0732je.f2039e)) != 0) {
            return m2320a5;
        }
        int compareTo8 = Boolean.valueOf(m2084h()).compareTo(Boolean.valueOf(c0732je.m2084h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m2084h() && (m2323a = C0745jr.m2323a(this.f2033a, c0732je.f2033a)) != 0) {
            return m2323a;
        }
        int compareTo9 = Boolean.valueOf(m2085i()).compareTo(Boolean.valueOf(c0732je.m2085i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m2085i() && (m2320a4 = C0745jr.m2320a(this.f2040f, c0732je.f2040f)) != 0) {
            return m2320a4;
        }
        int compareTo10 = Boolean.valueOf(m2086j()).compareTo(Boolean.valueOf(c0732je.m2086j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (m2086j() && (m2320a3 = C0745jr.m2320a(this.f2041g, c0732je.f2041g)) != 0) {
            return m2320a3;
        }
        int compareTo11 = Boolean.valueOf(m2087k()).compareTo(Boolean.valueOf(c0732je.m2087k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (m2087k() && (m2320a2 = C0745jr.m2320a(this.f2042h, c0732je.f2042h)) != 0) {
            return m2320a2;
        }
        int compareTo12 = Boolean.valueOf(m2088l()).compareTo(Boolean.valueOf(c0732je.m2088l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (m2088l() && (m2320a = C0745jr.m2320a(this.f2043i, c0732je.f2043i)) != 0) {
            return m2320a;
        }
        int compareTo13 = Boolean.valueOf(m2089m()).compareTo(Boolean.valueOf(c0732je.m2089m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m2089m() && (m2319a = C0745jr.m2319a(this.f2031a, c0732je.f2031a)) != 0) {
            return m2319a;
        }
        int compareTo14 = Boolean.valueOf(m2090n()).compareTo(Boolean.valueOf(c0732je.m2090n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (m2090n() && (m2318a = C0745jr.m2318a(this.f2028a, c0732je.f2028a)) != 0) {
            return m2318a;
        }
        int compareTo15 = Boolean.valueOf(m2091o()).compareTo(Boolean.valueOf(c0732je.m2091o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (!m2091o() || (m2326a = C0745jr.m2326a(this.f2036b, c0732je.f2036b)) == 0) {
            return 0;
        }
        return m2326a;
    }

    /* renamed from: a */
    public C0732je m2058a(String str) {
        this.f2035b = str;
        return this;
    }

    /* renamed from: a */
    public C0732je m2059a(ByteBuffer byteBuffer) {
        this.f2031a = byteBuffer;
        return this;
    }

    /* renamed from: a */
    public C0732je m2060a(Map<String, String> map) {
        this.f2033a = map;
        return this;
    }

    /* renamed from: a */
    public C0732je m2061a(boolean z) {
        this.f2034a = z;
        m2067a(true);
        return this;
    }

    /* renamed from: a */
    public C0732je m2062a(byte[] bArr) {
        m2059a(ByteBuffer.wrap(bArr));
        return this;
    }

    /* renamed from: a */
    public String m2063a() {
        return this.f2035b;
    }

    /* renamed from: a */
    public Map<String, String> m2064a() {
        return this.f2033a;
    }

    /* renamed from: a */
    public void m2065a() {
        if (this.f2035b != null) {
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
                if (m2082f()) {
                    m2065a();
                    return;
                }
                throw new C0757kc("Required field 'requireAck' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2030a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2029a = new C0720it();
                        this.f2029a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2035b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2037c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f2038d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 2) {
                        this.f2034a = abstractC0756kb.mo2365a();
                        m2067a(true);
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2039e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 13) {
                        C0755ka mo2344a = abstractC0756kb.mo2344a();
                        this.f2033a = new HashMap(mo2344a.f2335a * 2);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2344a.f2335a) {
                                abstractC0756kb.mo2375i();
                                break;
                            } else {
                                this.f2033a.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
                                i = i2 + 1;
                            }
                        }
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f2040f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 11) {
                        this.f2041g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 12:
                    if (mo2342a.f2324a == 11) {
                        this.f2042h = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 13:
                    if (mo2342a.f2324a == 11) {
                        this.f2043i = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 14:
                    if (mo2342a.f2324a == 11) {
                        this.f2031a = abstractC0756kb.mo2349a();
                        break;
                    }
                    break;
                case 15:
                    if (mo2342a.f2324a == 10) {
                        this.f2028a = abstractC0756kb.mo2341a();
                        m2073b(true);
                        break;
                    }
                    break;
                case 20:
                    if (mo2342a.f2324a == 2) {
                        this.f2036b = abstractC0756kb.mo2365a();
                        m2077c(true);
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2066a(String str, String str2) {
        if (this.f2033a == null) {
            this.f2033a = new HashMap();
        }
        this.f2033a.put(str, str2);
    }

    /* renamed from: a */
    public void m2067a(boolean z) {
        this.f2032a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2068a() {
        return this.f2030a != null;
    }

    /* renamed from: a */
    public boolean m2069a(C0732je c0732je) {
        if (c0732je == null) {
            return false;
        }
        boolean m2068a = m2068a();
        boolean m2068a2 = c0732je.m2068a();
        if ((m2068a || m2068a2) && !(m2068a && m2068a2 && this.f2030a.equals(c0732je.f2030a))) {
            return false;
        }
        boolean m2074b = m2074b();
        boolean m2074b2 = c0732je.m2074b();
        if ((m2074b || m2074b2) && !(m2074b && m2074b2 && this.f2029a.m1873a(c0732je.f2029a))) {
            return false;
        }
        boolean m2078c = m2078c();
        boolean m2078c2 = c0732je.m2078c();
        if ((m2078c || m2078c2) && !(m2078c && m2078c2 && this.f2035b.equals(c0732je.f2035b))) {
            return false;
        }
        boolean m2080d = m2080d();
        boolean m2080d2 = c0732je.m2080d();
        if ((m2080d || m2080d2) && !(m2080d && m2080d2 && this.f2037c.equals(c0732je.f2037c))) {
            return false;
        }
        boolean m2081e = m2081e();
        boolean m2081e2 = c0732je.m2081e();
        if (((m2081e || m2081e2) && !(m2081e && m2081e2 && this.f2038d.equals(c0732je.f2038d))) || this.f2034a != c0732je.f2034a) {
            return false;
        }
        boolean m2083g = m2083g();
        boolean m2083g2 = c0732je.m2083g();
        if ((m2083g || m2083g2) && !(m2083g && m2083g2 && this.f2039e.equals(c0732je.f2039e))) {
            return false;
        }
        boolean m2084h = m2084h();
        boolean m2084h2 = c0732je.m2084h();
        if ((m2084h || m2084h2) && !(m2084h && m2084h2 && this.f2033a.equals(c0732je.f2033a))) {
            return false;
        }
        boolean m2085i = m2085i();
        boolean m2085i2 = c0732je.m2085i();
        if ((m2085i || m2085i2) && !(m2085i && m2085i2 && this.f2040f.equals(c0732je.f2040f))) {
            return false;
        }
        boolean m2086j = m2086j();
        boolean m2086j2 = c0732je.m2086j();
        if ((m2086j || m2086j2) && !(m2086j && m2086j2 && this.f2041g.equals(c0732je.f2041g))) {
            return false;
        }
        boolean m2087k = m2087k();
        boolean m2087k2 = c0732je.m2087k();
        if ((m2087k || m2087k2) && !(m2087k && m2087k2 && this.f2042h.equals(c0732je.f2042h))) {
            return false;
        }
        boolean m2088l = m2088l();
        boolean m2088l2 = c0732je.m2088l();
        if ((m2088l || m2088l2) && !(m2088l && m2088l2 && this.f2043i.equals(c0732je.f2043i))) {
            return false;
        }
        boolean m2089m = m2089m();
        boolean m2089m2 = c0732je.m2089m();
        if ((m2089m || m2089m2) && !(m2089m && m2089m2 && this.f2031a.equals(c0732je.f2031a))) {
            return false;
        }
        boolean m2090n = m2090n();
        boolean m2090n2 = c0732je.m2090n();
        if ((m2090n || m2090n2) && !(m2090n && m2090n2 && this.f2028a == c0732je.f2028a)) {
            return false;
        }
        boolean m2091o = m2091o();
        boolean m2091o2 = c0732je.m2091o();
        if (m2091o || m2091o2) {
            return m2091o && m2091o2 && this.f2036b == c0732je.f2036b;
        }
        return true;
    }

    /* renamed from: a */
    public byte[] m2070a() {
        m2059a(C0745jr.m2329a(this.f2031a));
        return this.f2031a.array();
    }

    /* renamed from: b */
    public C0732je m2071b(String str) {
        this.f2037c = str;
        return this;
    }

    /* renamed from: b */
    public String m2072b() {
        return this.f2037c;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2065a();
        abstractC0756kb.mo2360a(f2013a);
        if (this.f2030a != null && m2068a()) {
            abstractC0756kb.mo2356a(f2012a);
            abstractC0756kb.mo2361a(this.f2030a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2029a != null && m2074b()) {
            abstractC0756kb.mo2356a(f2014b);
            this.f2029a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2035b != null) {
            abstractC0756kb.mo2356a(f2015c);
            abstractC0756kb.mo2361a(this.f2035b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2037c != null && m2080d()) {
            abstractC0756kb.mo2356a(f2016d);
            abstractC0756kb.mo2361a(this.f2037c);
            abstractC0756kb.mo2366b();
        }
        if (this.f2038d != null && m2081e()) {
            abstractC0756kb.mo2356a(f2017e);
            abstractC0756kb.mo2361a(this.f2038d);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f2018f);
        abstractC0756kb.mo2364a(this.f2034a);
        abstractC0756kb.mo2366b();
        if (this.f2039e != null && m2083g()) {
            abstractC0756kb.mo2356a(f2019g);
            abstractC0756kb.mo2361a(this.f2039e);
            abstractC0756kb.mo2366b();
        }
        if (this.f2033a != null && m2084h()) {
            abstractC0756kb.mo2356a(f2020h);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f2033a.size()));
            for (Map.Entry<String, String> entry : this.f2033a.entrySet()) {
                abstractC0756kb.mo2361a(entry.getKey());
                abstractC0756kb.mo2361a(entry.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        if (this.f2040f != null && m2085i()) {
            abstractC0756kb.mo2356a(f2021i);
            abstractC0756kb.mo2361a(this.f2040f);
            abstractC0756kb.mo2366b();
        }
        if (this.f2041g != null && m2086j()) {
            abstractC0756kb.mo2356a(f2022j);
            abstractC0756kb.mo2361a(this.f2041g);
            abstractC0756kb.mo2366b();
        }
        if (this.f2042h != null && m2087k()) {
            abstractC0756kb.mo2356a(f2023k);
            abstractC0756kb.mo2361a(this.f2042h);
            abstractC0756kb.mo2366b();
        }
        if (this.f2043i != null && m2088l()) {
            abstractC0756kb.mo2356a(f2024l);
            abstractC0756kb.mo2361a(this.f2043i);
            abstractC0756kb.mo2366b();
        }
        if (this.f2031a != null && m2089m()) {
            abstractC0756kb.mo2356a(f2025m);
            abstractC0756kb.mo2362a(this.f2031a);
            abstractC0756kb.mo2366b();
        }
        if (m2090n()) {
            abstractC0756kb.mo2356a(f2026n);
            abstractC0756kb.mo2355a(this.f2028a);
            abstractC0756kb.mo2366b();
        }
        if (m2091o()) {
            abstractC0756kb.mo2356a(f2027o);
            abstractC0756kb.mo2364a(this.f2036b);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m2073b(boolean z) {
        this.f2032a.set(1, z);
    }

    /* renamed from: b */
    public boolean m2074b() {
        return this.f2029a != null;
    }

    /* renamed from: c */
    public C0732je m2075c(String str) {
        this.f2038d = str;
        return this;
    }

    /* renamed from: c */
    public String m2076c() {
        return this.f2040f;
    }

    /* renamed from: c */
    public void m2077c(boolean z) {
        this.f2032a.set(2, z);
    }

    /* renamed from: c */
    public boolean m2078c() {
        return this.f2035b != null;
    }

    /* renamed from: d */
    public C0732je m2079d(String str) {
        this.f2040f = str;
        return this;
    }

    /* renamed from: d */
    public boolean m2080d() {
        return this.f2037c != null;
    }

    /* renamed from: e */
    public boolean m2081e() {
        return this.f2038d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0732je)) {
            return m2069a((C0732je) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2082f() {
        return this.f2032a.get(0);
    }

    /* renamed from: g */
    public boolean m2083g() {
        return this.f2039e != null;
    }

    /* renamed from: h */
    public boolean m2084h() {
        return this.f2033a != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m2085i() {
        return this.f2040f != null;
    }

    /* renamed from: j */
    public boolean m2086j() {
        return this.f2041g != null;
    }

    /* renamed from: k */
    public boolean m2087k() {
        return this.f2042h != null;
    }

    /* renamed from: l */
    public boolean m2088l() {
        return this.f2043i != null;
    }

    /* renamed from: m */
    public boolean m2089m() {
        return this.f2031a != null;
    }

    /* renamed from: n */
    public boolean m2090n() {
        return this.f2032a.get(1);
    }

    /* renamed from: o */
    public boolean m2091o() {
        return this.f2032a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionNotification(");
        if (m2068a()) {
            sb.append("debug:");
            String str = this.f2030a;
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
        if (m2074b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2029a;
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
        String str2 = this.f2035b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (m2080d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f2037c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m2081e()) {
            sb.append(", ");
            sb.append("type:");
            String str4 = this.f2038d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        sb.append(", ");
        sb.append("requireAck:");
        sb.append(this.f2034a);
        if (m2083g()) {
            sb.append(", ");
            sb.append("payload:");
            String str5 = this.f2039e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2084h()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f2033a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m2085i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f2040f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m2086j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f2041g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m2087k()) {
            sb.append(", ");
            sb.append("regId:");
            String str8 = this.f2042h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m2088l()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.f2043i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m2089m()) {
            sb.append(", ");
            sb.append("binaryExtra:");
            ByteBuffer byteBuffer = this.f2031a;
            if (byteBuffer == null) {
                sb.append("null");
            } else {
                C0745jr.m2330a(byteBuffer, sb);
            }
        }
        if (m2090n()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f2028a);
        }
        if (m2091o()) {
            sb.append(", ");
            sb.append("alreadyLogClickInXmq:");
            sb.append(this.f2036b);
        }
        sb.append(")");
        return sb.toString();
    }
}
