package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.jf */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jf.class */
public class C0733jf implements InterfaceC0744jq<C0733jf, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f2073a;

    /* renamed from: a */
    public long f2074a;

    /* renamed from: a */
    public EnumC0719is f2075a;

    /* renamed from: a */
    public C0720it f2076a;

    /* renamed from: a */
    public String f2077a;

    /* renamed from: a */
    public Map<String, String> f2079a;

    /* renamed from: b */
    public int f2081b;

    /* renamed from: b */
    public long f2082b;

    /* renamed from: b */
    public String f2083b;

    /* renamed from: c */
    public int f2085c;

    /* renamed from: c */
    public String f2086c;

    /* renamed from: d */
    public String f2087d;

    /* renamed from: e */
    public String f2088e;

    /* renamed from: f */
    public String f2089f;

    /* renamed from: g */
    public String f2090g;

    /* renamed from: h */
    public String f2091h;

    /* renamed from: i */
    public String f2092i;

    /* renamed from: j */
    public String f2093j;

    /* renamed from: k */
    public String f2094k;

    /* renamed from: l */
    public String f2095l;

    /* renamed from: m */
    public String f2096m;

    /* renamed from: n */
    public String f2097n;

    /* renamed from: o */
    public String f2098o;

    /* renamed from: p */
    public String f2099p;

    /* renamed from: q */
    public String f2100q;

    /* renamed from: r */
    public String f2101r;

    /* renamed from: a */
    private static final C0761kg f2047a = new C0761kg("XmPushActionRegistration");

    /* renamed from: a */
    private static final C0752jy f2046a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f2048b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f2049c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f2050d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f2051e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f2052f = new C0752jy("", (byte) 11, 6);

    /* renamed from: g */
    private static final C0752jy f2053g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f2054h = new C0752jy("", (byte) 11, 8);

    /* renamed from: i */
    private static final C0752jy f2055i = new C0752jy("", (byte) 11, 9);

    /* renamed from: j */
    private static final C0752jy f2056j = new C0752jy("", (byte) 11, 10);

    /* renamed from: k */
    private static final C0752jy f2057k = new C0752jy("", (byte) 11, 11);

    /* renamed from: l */
    private static final C0752jy f2058l = new C0752jy("", (byte) 11, 12);

    /* renamed from: m */
    private static final C0752jy f2059m = new C0752jy("", (byte) 8, 13);

    /* renamed from: n */
    private static final C0752jy f2060n = new C0752jy("", (byte) 8, 14);

    /* renamed from: o */
    private static final C0752jy f2061o = new C0752jy("", (byte) 11, 15);

    /* renamed from: p */
    private static final C0752jy f2062p = new C0752jy("", (byte) 11, 16);

    /* renamed from: q */
    private static final C0752jy f2063q = new C0752jy("", (byte) 11, 17);

    /* renamed from: r */
    private static final C0752jy f2064r = new C0752jy("", (byte) 11, 18);

    /* renamed from: s */
    private static final C0752jy f2065s = new C0752jy("", (byte) 8, 19);

    /* renamed from: t */
    private static final C0752jy f2066t = new C0752jy("", (byte) 8, 20);

    /* renamed from: u */
    private static final C0752jy f2067u = new C0752jy("", (byte) 2, 21);

    /* renamed from: v */
    private static final C0752jy f2068v = new C0752jy("", (byte) 10, 22);

    /* renamed from: w */
    private static final C0752jy f2069w = new C0752jy("", (byte) 10, 23);

    /* renamed from: x */
    private static final C0752jy f2070x = new C0752jy("", (byte) 11, 24);

    /* renamed from: y */
    private static final C0752jy f2071y = new C0752jy("", (byte) 11, 25);

    /* renamed from: z */
    private static final C0752jy f2072z = new C0752jy("", (byte) 13, 100);

    /* renamed from: A */
    private static final C0752jy f2044A = new C0752jy("", (byte) 2, 101);

    /* renamed from: B */
    private static final C0752jy f2045B = new C0752jy("", (byte) 11, 102);

    /* renamed from: a */
    private BitSet f2078a = new BitSet(7);

    /* renamed from: a */
    public boolean f2080a = true;

    /* renamed from: b */
    public boolean f2084b = false;

    /* renamed from: A */
    public boolean m2092A() {
        return this.f2078a.get(6);
    }

    /* renamed from: B */
    public boolean m2093B() {
        return this.f2101r != null;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0733jf c0733jf) {
        int m2320a;
        int m2326a;
        int m2323a;
        int m2320a2;
        int m2320a3;
        int m2318a;
        int m2318a2;
        int m2326a2;
        int m2319a;
        int m2317a;
        int m2320a4;
        int m2320a5;
        int m2320a6;
        int m2320a7;
        int m2317a2;
        int m2317a3;
        int m2320a8;
        int m2320a9;
        int m2320a10;
        int m2320a11;
        int m2320a12;
        int m2320a13;
        int m2320a14;
        int m2320a15;
        int m2320a16;
        int m2320a17;
        int m2319a2;
        int m2320a18;
        if (!getClass().equals(c0733jf.getClass())) {
            return getClass().getName().compareTo(c0733jf.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2101a()).compareTo(Boolean.valueOf(c0733jf.m2101a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2101a() && (m2320a18 = C0745jr.m2320a(this.f2077a, c0733jf.f2077a)) != 0) {
            return m2320a18;
        }
        int compareTo2 = Boolean.valueOf(m2107b()).compareTo(Boolean.valueOf(c0733jf.m2107b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2107b() && (m2319a2 = C0745jr.m2319a(this.f2076a, c0733jf.f2076a)) != 0) {
            return m2319a2;
        }
        int compareTo3 = Boolean.valueOf(m2112c()).compareTo(Boolean.valueOf(c0733jf.m2112c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2112c() && (m2320a17 = C0745jr.m2320a(this.f2083b, c0733jf.f2083b)) != 0) {
            return m2320a17;
        }
        int compareTo4 = Boolean.valueOf(m2115d()).compareTo(Boolean.valueOf(c0733jf.m2115d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2115d() && (m2320a16 = C0745jr.m2320a(this.f2086c, c0733jf.f2086c)) != 0) {
            return m2320a16;
        }
        int compareTo5 = Boolean.valueOf(m2118e()).compareTo(Boolean.valueOf(c0733jf.m2118e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2118e() && (m2320a15 = C0745jr.m2320a(this.f2087d, c0733jf.f2087d)) != 0) {
            return m2320a15;
        }
        int compareTo6 = Boolean.valueOf(m2121f()).compareTo(Boolean.valueOf(c0733jf.m2121f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2121f() && (m2320a14 = C0745jr.m2320a(this.f2088e, c0733jf.f2088e)) != 0) {
            return m2320a14;
        }
        int compareTo7 = Boolean.valueOf(m2124g()).compareTo(Boolean.valueOf(c0733jf.m2124g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2124g() && (m2320a13 = C0745jr.m2320a(this.f2089f, c0733jf.f2089f)) != 0) {
            return m2320a13;
        }
        int compareTo8 = Boolean.valueOf(m2126h()).compareTo(Boolean.valueOf(c0733jf.m2126h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m2126h() && (m2320a12 = C0745jr.m2320a(this.f2090g, c0733jf.f2090g)) != 0) {
            return m2320a12;
        }
        int compareTo9 = Boolean.valueOf(m2128i()).compareTo(Boolean.valueOf(c0733jf.m2128i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m2128i() && (m2320a11 = C0745jr.m2320a(this.f2091h, c0733jf.f2091h)) != 0) {
            return m2320a11;
        }
        int compareTo10 = Boolean.valueOf(m2130j()).compareTo(Boolean.valueOf(c0733jf.m2130j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (m2130j() && (m2320a10 = C0745jr.m2320a(this.f2092i, c0733jf.f2092i)) != 0) {
            return m2320a10;
        }
        int compareTo11 = Boolean.valueOf(m2132k()).compareTo(Boolean.valueOf(c0733jf.m2132k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (m2132k() && (m2320a9 = C0745jr.m2320a(this.f2093j, c0733jf.f2093j)) != 0) {
            return m2320a9;
        }
        int compareTo12 = Boolean.valueOf(m2133l()).compareTo(Boolean.valueOf(c0733jf.m2133l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (m2133l() && (m2320a8 = C0745jr.m2320a(this.f2094k, c0733jf.f2094k)) != 0) {
            return m2320a8;
        }
        int compareTo13 = Boolean.valueOf(m2134m()).compareTo(Boolean.valueOf(c0733jf.m2134m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m2134m() && (m2317a3 = C0745jr.m2317a(this.f2073a, c0733jf.f2073a)) != 0) {
            return m2317a3;
        }
        int compareTo14 = Boolean.valueOf(m2135n()).compareTo(Boolean.valueOf(c0733jf.m2135n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (m2135n() && (m2317a2 = C0745jr.m2317a(this.f2081b, c0733jf.f2081b)) != 0) {
            return m2317a2;
        }
        int compareTo15 = Boolean.valueOf(m2136o()).compareTo(Boolean.valueOf(c0733jf.m2136o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (m2136o() && (m2320a7 = C0745jr.m2320a(this.f2095l, c0733jf.f2095l)) != 0) {
            return m2320a7;
        }
        int compareTo16 = Boolean.valueOf(m2137p()).compareTo(Boolean.valueOf(c0733jf.m2137p()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (m2137p() && (m2320a6 = C0745jr.m2320a(this.f2096m, c0733jf.f2096m)) != 0) {
            return m2320a6;
        }
        int compareTo17 = Boolean.valueOf(m2138q()).compareTo(Boolean.valueOf(c0733jf.m2138q()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (m2138q() && (m2320a5 = C0745jr.m2320a(this.f2097n, c0733jf.f2097n)) != 0) {
            return m2320a5;
        }
        int compareTo18 = Boolean.valueOf(m2139r()).compareTo(Boolean.valueOf(c0733jf.m2139r()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (m2139r() && (m2320a4 = C0745jr.m2320a(this.f2098o, c0733jf.f2098o)) != 0) {
            return m2320a4;
        }
        int compareTo19 = Boolean.valueOf(m2140s()).compareTo(Boolean.valueOf(c0733jf.m2140s()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (m2140s() && (m2317a = C0745jr.m2317a(this.f2085c, c0733jf.f2085c)) != 0) {
            return m2317a;
        }
        int compareTo20 = Boolean.valueOf(m2141t()).compareTo(Boolean.valueOf(c0733jf.m2141t()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (m2141t() && (m2319a = C0745jr.m2319a(this.f2075a, c0733jf.f2075a)) != 0) {
            return m2319a;
        }
        int compareTo21 = Boolean.valueOf(m2142u()).compareTo(Boolean.valueOf(c0733jf.m2142u()));
        if (compareTo21 != 0) {
            return compareTo21;
        }
        if (m2142u() && (m2326a2 = C0745jr.m2326a(this.f2080a, c0733jf.f2080a)) != 0) {
            return m2326a2;
        }
        int compareTo22 = Boolean.valueOf(m2143v()).compareTo(Boolean.valueOf(c0733jf.m2143v()));
        if (compareTo22 != 0) {
            return compareTo22;
        }
        if (m2143v() && (m2318a2 = C0745jr.m2318a(this.f2074a, c0733jf.f2074a)) != 0) {
            return m2318a2;
        }
        int compareTo23 = Boolean.valueOf(m2144w()).compareTo(Boolean.valueOf(c0733jf.m2144w()));
        if (compareTo23 != 0) {
            return compareTo23;
        }
        if (m2144w() && (m2318a = C0745jr.m2318a(this.f2082b, c0733jf.f2082b)) != 0) {
            return m2318a;
        }
        int compareTo24 = Boolean.valueOf(m2145x()).compareTo(Boolean.valueOf(c0733jf.m2145x()));
        if (compareTo24 != 0) {
            return compareTo24;
        }
        if (m2145x() && (m2320a3 = C0745jr.m2320a(this.f2099p, c0733jf.f2099p)) != 0) {
            return m2320a3;
        }
        int compareTo25 = Boolean.valueOf(m2146y()).compareTo(Boolean.valueOf(c0733jf.m2146y()));
        if (compareTo25 != 0) {
            return compareTo25;
        }
        if (m2146y() && (m2320a2 = C0745jr.m2320a(this.f2100q, c0733jf.f2100q)) != 0) {
            return m2320a2;
        }
        int compareTo26 = Boolean.valueOf(m2147z()).compareTo(Boolean.valueOf(c0733jf.m2147z()));
        if (compareTo26 != 0) {
            return compareTo26;
        }
        if (m2147z() && (m2323a = C0745jr.m2323a(this.f2079a, c0733jf.f2079a)) != 0) {
            return m2323a;
        }
        int compareTo27 = Boolean.valueOf(m2092A()).compareTo(Boolean.valueOf(c0733jf.m2092A()));
        if (compareTo27 != 0) {
            return compareTo27;
        }
        if (m2092A() && (m2326a = C0745jr.m2326a(this.f2084b, c0733jf.f2084b)) != 0) {
            return m2326a;
        }
        int compareTo28 = Boolean.valueOf(m2093B()).compareTo(Boolean.valueOf(c0733jf.m2093B()));
        if (compareTo28 != 0) {
            return compareTo28;
        }
        if (!m2093B() || (m2320a = C0745jr.m2320a(this.f2101r, c0733jf.f2101r)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public C0733jf m2095a(int i) {
        this.f2073a = i;
        m2100a(true);
        return this;
    }

    /* renamed from: a */
    public C0733jf m2096a(EnumC0719is enumC0719is) {
        this.f2075a = enumC0719is;
        return this;
    }

    /* renamed from: a */
    public C0733jf m2097a(String str) {
        this.f2083b = str;
        return this;
    }

    /* renamed from: a */
    public String m2098a() {
        return this.f2083b;
    }

    /* renamed from: a */
    public void m2099a() {
        if (this.f2083b == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f2086c == null) {
            throw new C0757kc("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f2089f != null) {
            return;
        }
        throw new C0757kc("Required field 'token' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m2099a();
                return;
            }
            short s = mo2342a.f2326a;
            switch (s) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f2077a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f2076a = new C0720it();
                        this.f2076a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f2083b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2086c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f2087d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 11) {
                        this.f2088e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f2089f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f2090g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f2091h = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 11) {
                        this.f2092i = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 11:
                    if (mo2342a.f2324a == 11) {
                        this.f2093j = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 12:
                    if (mo2342a.f2324a == 11) {
                        this.f2094k = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 13:
                    if (mo2342a.f2324a == 8) {
                        this.f2073a = abstractC0756kb.mo2340a();
                        m2100a(true);
                        break;
                    }
                    break;
                case 14:
                    if (mo2342a.f2324a == 8) {
                        this.f2081b = abstractC0756kb.mo2340a();
                        m2106b(true);
                        break;
                    }
                    break;
                case 15:
                    if (mo2342a.f2324a == 11) {
                        this.f2095l = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 16:
                    if (mo2342a.f2324a == 11) {
                        this.f2096m = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 17:
                    if (mo2342a.f2324a == 11) {
                        this.f2097n = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 18:
                    if (mo2342a.f2324a == 11) {
                        this.f2098o = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 19:
                    if (mo2342a.f2324a == 8) {
                        this.f2085c = abstractC0756kb.mo2340a();
                        m2111c(true);
                        break;
                    }
                    break;
                case 20:
                    if (mo2342a.f2324a == 8) {
                        this.f2075a = EnumC0719is.m1867a(abstractC0756kb.mo2340a());
                        break;
                    }
                    break;
                case 21:
                    if (mo2342a.f2324a == 2) {
                        this.f2080a = abstractC0756kb.mo2365a();
                        m2114d(true);
                        break;
                    }
                    break;
                case 22:
                    if (mo2342a.f2324a == 10) {
                        this.f2074a = abstractC0756kb.mo2341a();
                        m2117e(true);
                        break;
                    }
                    break;
                case 23:
                    if (mo2342a.f2324a == 10) {
                        this.f2082b = abstractC0756kb.mo2341a();
                        m2120f(true);
                        break;
                    }
                    break;
                case 24:
                    if (mo2342a.f2324a == 11) {
                        this.f2099p = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 25:
                    if (mo2342a.f2324a == 11) {
                        this.f2100q = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                default:
                    switch (s) {
                        case 100:
                            if (mo2342a.f2324a == 13) {
                                C0755ka mo2344a = abstractC0756kb.mo2344a();
                                this.f2079a = new HashMap(mo2344a.f2335a * 2);
                                int i = 0;
                                while (true) {
                                    int i2 = i;
                                    if (i2 >= mo2344a.f2335a) {
                                        abstractC0756kb.mo2375i();
                                        break;
                                    } else {
                                        this.f2079a.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
                                        i = i2 + 1;
                                    }
                                }
                            }
                            break;
                        case 101:
                            if (mo2342a.f2324a == 2) {
                                this.f2084b = abstractC0756kb.mo2365a();
                                m2123g(true);
                                break;
                            }
                            break;
                        case 102:
                            if (mo2342a.f2324a == 11) {
                                this.f2101r = abstractC0756kb.mo2347a();
                                break;
                            }
                            break;
                    }
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2100a(boolean z) {
        this.f2078a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2101a() {
        return this.f2077a != null;
    }

    /* renamed from: a */
    public boolean m2102a(C0733jf c0733jf) {
        if (c0733jf == null) {
            return false;
        }
        boolean m2101a = m2101a();
        boolean m2101a2 = c0733jf.m2101a();
        if ((m2101a || m2101a2) && !(m2101a && m2101a2 && this.f2077a.equals(c0733jf.f2077a))) {
            return false;
        }
        boolean m2107b = m2107b();
        boolean m2107b2 = c0733jf.m2107b();
        if ((m2107b || m2107b2) && !(m2107b && m2107b2 && this.f2076a.m1873a(c0733jf.f2076a))) {
            return false;
        }
        boolean m2112c = m2112c();
        boolean m2112c2 = c0733jf.m2112c();
        if ((m2112c || m2112c2) && !(m2112c && m2112c2 && this.f2083b.equals(c0733jf.f2083b))) {
            return false;
        }
        boolean m2115d = m2115d();
        boolean m2115d2 = c0733jf.m2115d();
        if ((m2115d || m2115d2) && !(m2115d && m2115d2 && this.f2086c.equals(c0733jf.f2086c))) {
            return false;
        }
        boolean m2118e = m2118e();
        boolean m2118e2 = c0733jf.m2118e();
        if ((m2118e || m2118e2) && !(m2118e && m2118e2 && this.f2087d.equals(c0733jf.f2087d))) {
            return false;
        }
        boolean m2121f = m2121f();
        boolean m2121f2 = c0733jf.m2121f();
        if ((m2121f || m2121f2) && !(m2121f && m2121f2 && this.f2088e.equals(c0733jf.f2088e))) {
            return false;
        }
        boolean m2124g = m2124g();
        boolean m2124g2 = c0733jf.m2124g();
        if ((m2124g || m2124g2) && !(m2124g && m2124g2 && this.f2089f.equals(c0733jf.f2089f))) {
            return false;
        }
        boolean m2126h = m2126h();
        boolean m2126h2 = c0733jf.m2126h();
        if ((m2126h || m2126h2) && !(m2126h && m2126h2 && this.f2090g.equals(c0733jf.f2090g))) {
            return false;
        }
        boolean m2128i = m2128i();
        boolean m2128i2 = c0733jf.m2128i();
        if ((m2128i || m2128i2) && !(m2128i && m2128i2 && this.f2091h.equals(c0733jf.f2091h))) {
            return false;
        }
        boolean m2130j = m2130j();
        boolean m2130j2 = c0733jf.m2130j();
        if ((m2130j || m2130j2) && !(m2130j && m2130j2 && this.f2092i.equals(c0733jf.f2092i))) {
            return false;
        }
        boolean m2132k = m2132k();
        boolean m2132k2 = c0733jf.m2132k();
        if ((m2132k || m2132k2) && !(m2132k && m2132k2 && this.f2093j.equals(c0733jf.f2093j))) {
            return false;
        }
        boolean m2133l = m2133l();
        boolean m2133l2 = c0733jf.m2133l();
        if ((m2133l || m2133l2) && !(m2133l && m2133l2 && this.f2094k.equals(c0733jf.f2094k))) {
            return false;
        }
        boolean m2134m = m2134m();
        boolean m2134m2 = c0733jf.m2134m();
        if ((m2134m || m2134m2) && !(m2134m && m2134m2 && this.f2073a == c0733jf.f2073a)) {
            return false;
        }
        boolean m2135n = m2135n();
        boolean m2135n2 = c0733jf.m2135n();
        if ((m2135n || m2135n2) && !(m2135n && m2135n2 && this.f2081b == c0733jf.f2081b)) {
            return false;
        }
        boolean m2136o = m2136o();
        boolean m2136o2 = c0733jf.m2136o();
        if ((m2136o || m2136o2) && !(m2136o && m2136o2 && this.f2095l.equals(c0733jf.f2095l))) {
            return false;
        }
        boolean m2137p = m2137p();
        boolean m2137p2 = c0733jf.m2137p();
        if ((m2137p || m2137p2) && !(m2137p && m2137p2 && this.f2096m.equals(c0733jf.f2096m))) {
            return false;
        }
        boolean m2138q = m2138q();
        boolean m2138q2 = c0733jf.m2138q();
        if ((m2138q || m2138q2) && !(m2138q && m2138q2 && this.f2097n.equals(c0733jf.f2097n))) {
            return false;
        }
        boolean m2139r = m2139r();
        boolean m2139r2 = c0733jf.m2139r();
        if ((m2139r || m2139r2) && !(m2139r && m2139r2 && this.f2098o.equals(c0733jf.f2098o))) {
            return false;
        }
        boolean m2140s = m2140s();
        boolean m2140s2 = c0733jf.m2140s();
        if ((m2140s || m2140s2) && !(m2140s && m2140s2 && this.f2085c == c0733jf.f2085c)) {
            return false;
        }
        boolean m2141t = m2141t();
        boolean m2141t2 = c0733jf.m2141t();
        if ((m2141t || m2141t2) && !(m2141t && m2141t2 && this.f2075a.equals(c0733jf.f2075a))) {
            return false;
        }
        boolean m2142u = m2142u();
        boolean m2142u2 = c0733jf.m2142u();
        if ((m2142u || m2142u2) && !(m2142u && m2142u2 && this.f2080a == c0733jf.f2080a)) {
            return false;
        }
        boolean m2143v = m2143v();
        boolean m2143v2 = c0733jf.m2143v();
        if ((m2143v || m2143v2) && !(m2143v && m2143v2 && this.f2074a == c0733jf.f2074a)) {
            return false;
        }
        boolean m2144w = m2144w();
        boolean m2144w2 = c0733jf.m2144w();
        if ((m2144w || m2144w2) && !(m2144w && m2144w2 && this.f2082b == c0733jf.f2082b)) {
            return false;
        }
        boolean m2145x = m2145x();
        boolean m2145x2 = c0733jf.m2145x();
        if ((m2145x || m2145x2) && !(m2145x && m2145x2 && this.f2099p.equals(c0733jf.f2099p))) {
            return false;
        }
        boolean m2146y = m2146y();
        boolean m2146y2 = c0733jf.m2146y();
        if ((m2146y || m2146y2) && !(m2146y && m2146y2 && this.f2100q.equals(c0733jf.f2100q))) {
            return false;
        }
        boolean m2147z = m2147z();
        boolean m2147z2 = c0733jf.m2147z();
        if ((m2147z || m2147z2) && !(m2147z && m2147z2 && this.f2079a.equals(c0733jf.f2079a))) {
            return false;
        }
        boolean m2092A = m2092A();
        boolean m2092A2 = c0733jf.m2092A();
        if ((m2092A || m2092A2) && !(m2092A && m2092A2 && this.f2084b == c0733jf.f2084b)) {
            return false;
        }
        boolean m2093B = m2093B();
        boolean m2093B2 = c0733jf.m2093B();
        if (m2093B || m2093B2) {
            return m2093B && m2093B2 && this.f2101r.equals(c0733jf.f2101r);
        }
        return true;
    }

    /* renamed from: b */
    public C0733jf m2103b(int i) {
        this.f2081b = i;
        m2106b(true);
        return this;
    }

    /* renamed from: b */
    public C0733jf m2104b(String str) {
        this.f2086c = str;
        return this;
    }

    /* renamed from: b */
    public String m2105b() {
        return this.f2086c;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2099a();
        abstractC0756kb.mo2360a(f2047a);
        if (this.f2077a != null && m2101a()) {
            abstractC0756kb.mo2356a(f2046a);
            abstractC0756kb.mo2361a(this.f2077a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2076a != null && m2107b()) {
            abstractC0756kb.mo2356a(f2048b);
            this.f2076a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f2083b != null) {
            abstractC0756kb.mo2356a(f2049c);
            abstractC0756kb.mo2361a(this.f2083b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2086c != null) {
            abstractC0756kb.mo2356a(f2050d);
            abstractC0756kb.mo2361a(this.f2086c);
            abstractC0756kb.mo2366b();
        }
        if (this.f2087d != null && m2118e()) {
            abstractC0756kb.mo2356a(f2051e);
            abstractC0756kb.mo2361a(this.f2087d);
            abstractC0756kb.mo2366b();
        }
        if (this.f2088e != null && m2121f()) {
            abstractC0756kb.mo2356a(f2052f);
            abstractC0756kb.mo2361a(this.f2088e);
            abstractC0756kb.mo2366b();
        }
        if (this.f2089f != null) {
            abstractC0756kb.mo2356a(f2053g);
            abstractC0756kb.mo2361a(this.f2089f);
            abstractC0756kb.mo2366b();
        }
        if (this.f2090g != null && m2126h()) {
            abstractC0756kb.mo2356a(f2054h);
            abstractC0756kb.mo2361a(this.f2090g);
            abstractC0756kb.mo2366b();
        }
        if (this.f2091h != null && m2128i()) {
            abstractC0756kb.mo2356a(f2055i);
            abstractC0756kb.mo2361a(this.f2091h);
            abstractC0756kb.mo2366b();
        }
        if (this.f2092i != null && m2130j()) {
            abstractC0756kb.mo2356a(f2056j);
            abstractC0756kb.mo2361a(this.f2092i);
            abstractC0756kb.mo2366b();
        }
        if (this.f2093j != null && m2132k()) {
            abstractC0756kb.mo2356a(f2057k);
            abstractC0756kb.mo2361a(this.f2093j);
            abstractC0756kb.mo2366b();
        }
        if (this.f2094k != null && m2133l()) {
            abstractC0756kb.mo2356a(f2058l);
            abstractC0756kb.mo2361a(this.f2094k);
            abstractC0756kb.mo2366b();
        }
        if (m2134m()) {
            abstractC0756kb.mo2356a(f2059m);
            abstractC0756kb.mo2354a(this.f2073a);
            abstractC0756kb.mo2366b();
        }
        if (m2135n()) {
            abstractC0756kb.mo2356a(f2060n);
            abstractC0756kb.mo2354a(this.f2081b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2095l != null && m2136o()) {
            abstractC0756kb.mo2356a(f2061o);
            abstractC0756kb.mo2361a(this.f2095l);
            abstractC0756kb.mo2366b();
        }
        if (this.f2096m != null && m2137p()) {
            abstractC0756kb.mo2356a(f2062p);
            abstractC0756kb.mo2361a(this.f2096m);
            abstractC0756kb.mo2366b();
        }
        if (this.f2097n != null && m2138q()) {
            abstractC0756kb.mo2356a(f2063q);
            abstractC0756kb.mo2361a(this.f2097n);
            abstractC0756kb.mo2366b();
        }
        if (this.f2098o != null && m2139r()) {
            abstractC0756kb.mo2356a(f2064r);
            abstractC0756kb.mo2361a(this.f2098o);
            abstractC0756kb.mo2366b();
        }
        if (m2140s()) {
            abstractC0756kb.mo2356a(f2065s);
            abstractC0756kb.mo2354a(this.f2085c);
            abstractC0756kb.mo2366b();
        }
        if (this.f2075a != null && m2141t()) {
            abstractC0756kb.mo2356a(f2066t);
            abstractC0756kb.mo2354a(this.f2075a.m1868a());
            abstractC0756kb.mo2366b();
        }
        if (m2142u()) {
            abstractC0756kb.mo2356a(f2067u);
            abstractC0756kb.mo2364a(this.f2080a);
            abstractC0756kb.mo2366b();
        }
        if (m2143v()) {
            abstractC0756kb.mo2356a(f2068v);
            abstractC0756kb.mo2355a(this.f2074a);
            abstractC0756kb.mo2366b();
        }
        if (m2144w()) {
            abstractC0756kb.mo2356a(f2069w);
            abstractC0756kb.mo2355a(this.f2082b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2099p != null && m2145x()) {
            abstractC0756kb.mo2356a(f2070x);
            abstractC0756kb.mo2361a(this.f2099p);
            abstractC0756kb.mo2366b();
        }
        if (this.f2100q != null && m2146y()) {
            abstractC0756kb.mo2356a(f2071y);
            abstractC0756kb.mo2361a(this.f2100q);
            abstractC0756kb.mo2366b();
        }
        if (this.f2079a != null && m2147z()) {
            abstractC0756kb.mo2356a(f2072z);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f2079a.size()));
            for (Map.Entry<String, String> entry : this.f2079a.entrySet()) {
                abstractC0756kb.mo2361a(entry.getKey());
                abstractC0756kb.mo2361a(entry.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        if (m2092A()) {
            abstractC0756kb.mo2356a(f2044A);
            abstractC0756kb.mo2364a(this.f2084b);
            abstractC0756kb.mo2366b();
        }
        if (this.f2101r != null && m2093B()) {
            abstractC0756kb.mo2356a(f2045B);
            abstractC0756kb.mo2361a(this.f2101r);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m2106b(boolean z) {
        this.f2078a.set(1, z);
    }

    /* renamed from: b */
    public boolean m2107b() {
        return this.f2076a != null;
    }

    /* renamed from: c */
    public C0733jf m2108c(int i) {
        this.f2085c = i;
        m2111c(true);
        return this;
    }

    /* renamed from: c */
    public C0733jf m2109c(String str) {
        this.f2087d = str;
        return this;
    }

    /* renamed from: c */
    public String m2110c() {
        return this.f2089f;
    }

    /* renamed from: c */
    public void m2111c(boolean z) {
        this.f2078a.set(2, z);
    }

    /* renamed from: c */
    public boolean m2112c() {
        return this.f2083b != null;
    }

    /* renamed from: d */
    public C0733jf m2113d(String str) {
        this.f2088e = str;
        return this;
    }

    /* renamed from: d */
    public void m2114d(boolean z) {
        this.f2078a.set(3, z);
    }

    /* renamed from: d */
    public boolean m2115d() {
        return this.f2086c != null;
    }

    /* renamed from: e */
    public C0733jf m2116e(String str) {
        this.f2089f = str;
        return this;
    }

    /* renamed from: e */
    public void m2117e(boolean z) {
        this.f2078a.set(4, z);
    }

    /* renamed from: e */
    public boolean m2118e() {
        return this.f2087d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0733jf)) {
            return m2102a((C0733jf) obj);
        }
        return false;
    }

    /* renamed from: f */
    public C0733jf m2119f(String str) {
        this.f2090g = str;
        return this;
    }

    /* renamed from: f */
    public void m2120f(boolean z) {
        this.f2078a.set(5, z);
    }

    /* renamed from: f */
    public boolean m2121f() {
        return this.f2088e != null;
    }

    /* renamed from: g */
    public C0733jf m2122g(String str) {
        this.f2094k = str;
        return this;
    }

    /* renamed from: g */
    public void m2123g(boolean z) {
        this.f2078a.set(6, z);
    }

    /* renamed from: g */
    public boolean m2124g() {
        return this.f2089f != null;
    }

    /* renamed from: h */
    public C0733jf m2125h(String str) {
        this.f2095l = str;
        return this;
    }

    /* renamed from: h */
    public boolean m2126h() {
        return this.f2090g != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public C0733jf m2127i(String str) {
        this.f2096m = str;
        return this;
    }

    /* renamed from: i */
    public boolean m2128i() {
        return this.f2091h != null;
    }

    /* renamed from: j */
    public C0733jf m2129j(String str) {
        this.f2097n = str;
        return this;
    }

    /* renamed from: j */
    public boolean m2130j() {
        return this.f2092i != null;
    }

    /* renamed from: k */
    public C0733jf m2131k(String str) {
        this.f2098o = str;
        return this;
    }

    /* renamed from: k */
    public boolean m2132k() {
        return this.f2093j != null;
    }

    /* renamed from: l */
    public boolean m2133l() {
        return this.f2094k != null;
    }

    /* renamed from: m */
    public boolean m2134m() {
        return this.f2078a.get(0);
    }

    /* renamed from: n */
    public boolean m2135n() {
        return this.f2078a.get(1);
    }

    /* renamed from: o */
    public boolean m2136o() {
        return this.f2095l != null;
    }

    /* renamed from: p */
    public boolean m2137p() {
        return this.f2096m != null;
    }

    /* renamed from: q */
    public boolean m2138q() {
        return this.f2097n != null;
    }

    /* renamed from: r */
    public boolean m2139r() {
        return this.f2098o != null;
    }

    /* renamed from: s */
    public boolean m2140s() {
        return this.f2078a.get(2);
    }

    /* renamed from: t */
    public boolean m2141t() {
        return this.f2075a != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionRegistration(");
        if (m2101a()) {
            sb.append("debug:");
            String str = this.f2077a;
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
        if (m2107b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f2076a;
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
        String str2 = this.f2083b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f2086c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m2118e()) {
            sb.append(", ");
            sb.append("appVersion:");
            String str4 = this.f2087d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m2121f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f2088e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        sb.append(", ");
        sb.append("token:");
        String str6 = this.f2089f;
        if (str6 == null) {
            sb.append("null");
        } else {
            sb.append(str6);
        }
        if (m2126h()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str7 = this.f2090g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m2128i()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str8 = this.f2091h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m2130j()) {
            sb.append(", ");
            sb.append("sdkVersion:");
            String str9 = this.f2092i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m2132k()) {
            sb.append(", ");
            sb.append("regId:");
            String str10 = this.f2093j;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (m2133l()) {
            sb.append(", ");
            sb.append("pushSdkVersionName:");
            String str11 = this.f2094k;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (m2134m()) {
            sb.append(", ");
            sb.append("pushSdkVersionCode:");
            sb.append(this.f2073a);
        }
        if (m2135n()) {
            sb.append(", ");
            sb.append("appVersionCode:");
            sb.append(this.f2081b);
        }
        if (m2136o()) {
            sb.append(", ");
            sb.append("androidId:");
            String str12 = this.f2095l;
            if (str12 == null) {
                sb.append("null");
            } else {
                sb.append(str12);
            }
        }
        if (m2137p()) {
            sb.append(", ");
            sb.append("imei:");
            String str13 = this.f2096m;
            if (str13 == null) {
                sb.append("null");
            } else {
                sb.append(str13);
            }
        }
        if (m2138q()) {
            sb.append(", ");
            sb.append("serial:");
            String str14 = this.f2097n;
            if (str14 == null) {
                sb.append("null");
            } else {
                sb.append(str14);
            }
        }
        if (m2139r()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str15 = this.f2098o;
            if (str15 == null) {
                sb.append("null");
            } else {
                sb.append(str15);
            }
        }
        if (m2140s()) {
            sb.append(", ");
            sb.append("spaceId:");
            sb.append(this.f2085c);
        }
        if (m2141t()) {
            sb.append(", ");
            sb.append("reason:");
            EnumC0719is enumC0719is = this.f2075a;
            if (enumC0719is == null) {
                sb.append("null");
            } else {
                sb.append(enumC0719is);
            }
        }
        if (m2142u()) {
            sb.append(", ");
            sb.append("validateToken:");
            sb.append(this.f2080a);
        }
        if (m2143v()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.f2074a);
        }
        if (m2144w()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f2082b);
        }
        if (m2145x()) {
            sb.append(", ");
            sb.append("subImei:");
            String str16 = this.f2099p;
            if (str16 == null) {
                sb.append("null");
            } else {
                sb.append(str16);
            }
        }
        if (m2146y()) {
            sb.append(", ");
            sb.append("subImeiMd5:");
            String str17 = this.f2100q;
            if (str17 == null) {
                sb.append("null");
            } else {
                sb.append(str17);
            }
        }
        if (m2147z()) {
            sb.append(", ");
            sb.append("connectionAttrs:");
            Map<String, String> map = this.f2079a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m2092A()) {
            sb.append(", ");
            sb.append("cleanOldRegInfo:");
            sb.append(this.f2084b);
        }
        if (m2093B()) {
            sb.append(", ");
            sb.append("oldRegId:");
            String str18 = this.f2101r;
            if (str18 == null) {
                sb.append("null");
            } else {
                sb.append(str18);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: u */
    public boolean m2142u() {
        return this.f2078a.get(3);
    }

    /* renamed from: v */
    public boolean m2143v() {
        return this.f2078a.get(4);
    }

    /* renamed from: w */
    public boolean m2144w() {
        return this.f2078a.get(5);
    }

    /* renamed from: x */
    public boolean m2145x() {
        return this.f2099p != null;
    }

    /* renamed from: y */
    public boolean m2146y() {
        return this.f2100q != null;
    }

    /* renamed from: z */
    public boolean m2147z() {
        return this.f2079a != null;
    }
}
