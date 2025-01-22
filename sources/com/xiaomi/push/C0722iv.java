package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.iv */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/iv.class */
public class C0722iv implements InterfaceC0744jq<C0722iv, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f1886a;

    /* renamed from: a */
    public long f1887a;

    /* renamed from: a */
    public C0720it f1888a;

    /* renamed from: a */
    public C0736ji f1889a;

    /* renamed from: a */
    public String f1890a;

    /* renamed from: a */
    public Map<String, String> f1892a;

    /* renamed from: a */
    public short f1893a;

    /* renamed from: b */
    public String f1895b;

    /* renamed from: b */
    public short f1896b;

    /* renamed from: c */
    public String f1897c;

    /* renamed from: d */
    public String f1898d;

    /* renamed from: e */
    public String f1899e;

    /* renamed from: f */
    public String f1900f;

    /* renamed from: g */
    public String f1901g;

    /* renamed from: h */
    public String f1902h;

    /* renamed from: i */
    public String f1903i;

    /* renamed from: j */
    public String f1904j;

    /* renamed from: k */
    public String f1905k;

    /* renamed from: l */
    public String f1906l;

    /* renamed from: a */
    private static final C0761kg f1866a = new C0761kg("XmPushActionAckMessage");

    /* renamed from: a */
    private static final C0752jy f1865a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f1867b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f1868c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f1869d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f1870e = new C0752jy("", (byte) 10, 5);

    /* renamed from: f */
    private static final C0752jy f1871f = new C0752jy("", (byte) 11, 6);

    /* renamed from: g */
    private static final C0752jy f1872g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f1873h = new C0752jy("", (byte) 12, 8);

    /* renamed from: i */
    private static final C0752jy f1874i = new C0752jy("", (byte) 11, 9);

    /* renamed from: j */
    private static final C0752jy f1875j = new C0752jy("", (byte) 11, 10);

    /* renamed from: k */
    private static final C0752jy f1876k = new C0752jy("", (byte) 2, 11);

    /* renamed from: l */
    private static final C0752jy f1877l = new C0752jy("", (byte) 11, 12);

    /* renamed from: m */
    private static final C0752jy f1878m = new C0752jy("", (byte) 11, 13);

    /* renamed from: n */
    private static final C0752jy f1879n = new C0752jy("", (byte) 11, 14);

    /* renamed from: o */
    private static final C0752jy f1880o = new C0752jy("", (byte) 6, 15);

    /* renamed from: p */
    private static final C0752jy f1881p = new C0752jy("", (byte) 6, 16);

    /* renamed from: q */
    private static final C0752jy f1882q = new C0752jy("", (byte) 11, 20);

    /* renamed from: r */
    private static final C0752jy f1883r = new C0752jy("", (byte) 11, 21);

    /* renamed from: s */
    private static final C0752jy f1884s = new C0752jy("", (byte) 8, 22);

    /* renamed from: t */
    private static final C0752jy f1885t = new C0752jy("", (byte) 13, 23);

    /* renamed from: a */
    private BitSet f1891a = new BitSet(5);

    /* renamed from: a */
    public boolean f1894a = false;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0722iv c0722iv) {
        int m2323a;
        int m2317a;
        int m2320a;
        int m2320a2;
        int m2325a;
        int m2325a2;
        int m2320a3;
        int m2320a4;
        int m2320a5;
        int m2326a;
        int m2320a6;
        int m2320a7;
        int m2319a;
        int m2320a8;
        int m2320a9;
        int m2318a;
        int m2320a10;
        int m2320a11;
        int m2319a2;
        int m2320a12;
        if (!getClass().equals(c0722iv.getClass())) {
            return getClass().getName().compareTo(c0722iv.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1896a()).compareTo(Boolean.valueOf(c0722iv.m1896a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1896a() && (m2320a12 = C0745jr.m2320a(this.f1890a, c0722iv.f1890a)) != 0) {
            return m2320a12;
        }
        int compareTo2 = Boolean.valueOf(m1901b()).compareTo(Boolean.valueOf(c0722iv.m1901b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1901b() && (m2319a2 = C0745jr.m2319a(this.f1888a, c0722iv.f1888a)) != 0) {
            return m2319a2;
        }
        int compareTo3 = Boolean.valueOf(m1904c()).compareTo(Boolean.valueOf(c0722iv.m1904c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1904c() && (m2320a11 = C0745jr.m2320a(this.f1895b, c0722iv.f1895b)) != 0) {
            return m2320a11;
        }
        int compareTo4 = Boolean.valueOf(m1907d()).compareTo(Boolean.valueOf(c0722iv.m1907d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1907d() && (m2320a10 = C0745jr.m2320a(this.f1897c, c0722iv.f1897c)) != 0) {
            return m2320a10;
        }
        int compareTo5 = Boolean.valueOf(m1909e()).compareTo(Boolean.valueOf(c0722iv.m1909e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1909e() && (m2318a = C0745jr.m2318a(this.f1887a, c0722iv.f1887a)) != 0) {
            return m2318a;
        }
        int compareTo6 = Boolean.valueOf(m1910f()).compareTo(Boolean.valueOf(c0722iv.m1910f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1910f() && (m2320a9 = C0745jr.m2320a(this.f1898d, c0722iv.f1898d)) != 0) {
            return m2320a9;
        }
        int compareTo7 = Boolean.valueOf(m1911g()).compareTo(Boolean.valueOf(c0722iv.m1911g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m1911g() && (m2320a8 = C0745jr.m2320a(this.f1899e, c0722iv.f1899e)) != 0) {
            return m2320a8;
        }
        int compareTo8 = Boolean.valueOf(m1912h()).compareTo(Boolean.valueOf(c0722iv.m1912h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m1912h() && (m2319a = C0745jr.m2319a(this.f1889a, c0722iv.f1889a)) != 0) {
            return m2319a;
        }
        int compareTo9 = Boolean.valueOf(m1913i()).compareTo(Boolean.valueOf(c0722iv.m1913i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m1913i() && (m2320a7 = C0745jr.m2320a(this.f1900f, c0722iv.f1900f)) != 0) {
            return m2320a7;
        }
        int compareTo10 = Boolean.valueOf(m1914j()).compareTo(Boolean.valueOf(c0722iv.m1914j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (m1914j() && (m2320a6 = C0745jr.m2320a(this.f1901g, c0722iv.f1901g)) != 0) {
            return m2320a6;
        }
        int compareTo11 = Boolean.valueOf(m1915k()).compareTo(Boolean.valueOf(c0722iv.m1915k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (m1915k() && (m2326a = C0745jr.m2326a(this.f1894a, c0722iv.f1894a)) != 0) {
            return m2326a;
        }
        int compareTo12 = Boolean.valueOf(m1916l()).compareTo(Boolean.valueOf(c0722iv.m1916l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (m1916l() && (m2320a5 = C0745jr.m2320a(this.f1902h, c0722iv.f1902h)) != 0) {
            return m2320a5;
        }
        int compareTo13 = Boolean.valueOf(m1917m()).compareTo(Boolean.valueOf(c0722iv.m1917m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m1917m() && (m2320a4 = C0745jr.m2320a(this.f1903i, c0722iv.f1903i)) != 0) {
            return m2320a4;
        }
        int compareTo14 = Boolean.valueOf(m1918n()).compareTo(Boolean.valueOf(c0722iv.m1918n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (m1918n() && (m2320a3 = C0745jr.m2320a(this.f1904j, c0722iv.f1904j)) != 0) {
            return m2320a3;
        }
        int compareTo15 = Boolean.valueOf(m1919o()).compareTo(Boolean.valueOf(c0722iv.m1919o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (m1919o() && (m2325a2 = C0745jr.m2325a(this.f1893a, c0722iv.f1893a)) != 0) {
            return m2325a2;
        }
        int compareTo16 = Boolean.valueOf(m1920p()).compareTo(Boolean.valueOf(c0722iv.m1920p()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (m1920p() && (m2325a = C0745jr.m2325a(this.f1896b, c0722iv.f1896b)) != 0) {
            return m2325a;
        }
        int compareTo17 = Boolean.valueOf(m1921q()).compareTo(Boolean.valueOf(c0722iv.m1921q()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (m1921q() && (m2320a2 = C0745jr.m2320a(this.f1905k, c0722iv.f1905k)) != 0) {
            return m2320a2;
        }
        int compareTo18 = Boolean.valueOf(m1922r()).compareTo(Boolean.valueOf(c0722iv.m1922r()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (m1922r() && (m2320a = C0745jr.m2320a(this.f1906l, c0722iv.f1906l)) != 0) {
            return m2320a;
        }
        int compareTo19 = Boolean.valueOf(m1923s()).compareTo(Boolean.valueOf(c0722iv.m1923s()));
        if (compareTo19 != 0) {
            return compareTo19;
        }
        if (m1923s() && (m2317a = C0745jr.m2317a(this.f1886a, c0722iv.f1886a)) != 0) {
            return m2317a;
        }
        int compareTo20 = Boolean.valueOf(m1924t()).compareTo(Boolean.valueOf(c0722iv.m1924t()));
        if (compareTo20 != 0) {
            return compareTo20;
        }
        if (!m1924t() || (m2323a = C0745jr.m2323a(this.f1892a, c0722iv.f1892a)) == 0) {
            return 0;
        }
        return m2323a;
    }

    /* renamed from: a */
    public C0722iv m1891a(long j) {
        this.f1887a = j;
        m1895a(true);
        return this;
    }

    /* renamed from: a */
    public C0722iv m1892a(String str) {
        this.f1895b = str;
        return this;
    }

    /* renamed from: a */
    public C0722iv m1893a(short s) {
        this.f1893a = s;
        m1903c(true);
        return this;
    }

    /* renamed from: a */
    public void m1894a() {
        if (this.f1895b == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f1897c != null) {
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
                if (m1909e()) {
                    m1894a();
                    return;
                }
                throw new C0757kc("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f1890a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f1888a = new C0720it();
                        this.f1888a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f1895b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f1897c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 10) {
                        this.f1887a = abstractC0756kb.mo2341a();
                        m1895a(true);
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 11) {
                        this.f1898d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f1899e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 12) {
                        this.f1889a = new C0736ji();
                        this.f1889a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f1900f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 11) {
                        this.f1901g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 11:
                    if (mo2342a.f2324a == 2) {
                        this.f1894a = abstractC0756kb.mo2365a();
                        m1900b(true);
                        break;
                    }
                    break;
                case 12:
                    if (mo2342a.f2324a == 11) {
                        this.f1902h = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 13:
                    if (mo2342a.f2324a == 11) {
                        this.f1903i = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 14:
                    if (mo2342a.f2324a == 11) {
                        this.f1904j = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 15:
                    if (mo2342a.f2324a == 6) {
                        this.f1893a = abstractC0756kb.mo2350a();
                        m1903c(true);
                        break;
                    }
                    break;
                case 16:
                    if (mo2342a.f2324a == 6) {
                        this.f1896b = abstractC0756kb.mo2350a();
                        m1906d(true);
                        break;
                    }
                    break;
                case 20:
                    if (mo2342a.f2324a == 11) {
                        this.f1905k = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 21:
                    if (mo2342a.f2324a == 11) {
                        this.f1906l = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 22:
                    if (mo2342a.f2324a == 8) {
                        this.f1886a = abstractC0756kb.mo2340a();
                        m1908e(true);
                        break;
                    }
                    break;
                case 23:
                    if (mo2342a.f2324a == 13) {
                        C0755ka mo2344a = abstractC0756kb.mo2344a();
                        this.f1892a = new HashMap(mo2344a.f2335a * 2);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2344a.f2335a) {
                                abstractC0756kb.mo2375i();
                                break;
                            } else {
                                this.f1892a.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
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
    public void m1895a(boolean z) {
        this.f1891a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1896a() {
        return this.f1890a != null;
    }

    /* renamed from: a */
    public boolean m1897a(C0722iv c0722iv) {
        if (c0722iv == null) {
            return false;
        }
        boolean m1896a = m1896a();
        boolean m1896a2 = c0722iv.m1896a();
        if ((m1896a || m1896a2) && !(m1896a && m1896a2 && this.f1890a.equals(c0722iv.f1890a))) {
            return false;
        }
        boolean m1901b = m1901b();
        boolean m1901b2 = c0722iv.m1901b();
        if ((m1901b || m1901b2) && !(m1901b && m1901b2 && this.f1888a.m1873a(c0722iv.f1888a))) {
            return false;
        }
        boolean m1904c = m1904c();
        boolean m1904c2 = c0722iv.m1904c();
        if ((m1904c || m1904c2) && !(m1904c && m1904c2 && this.f1895b.equals(c0722iv.f1895b))) {
            return false;
        }
        boolean m1907d = m1907d();
        boolean m1907d2 = c0722iv.m1907d();
        if (((m1907d || m1907d2) && !(m1907d && m1907d2 && this.f1897c.equals(c0722iv.f1897c))) || this.f1887a != c0722iv.f1887a) {
            return false;
        }
        boolean m1910f = m1910f();
        boolean m1910f2 = c0722iv.m1910f();
        if ((m1910f || m1910f2) && !(m1910f && m1910f2 && this.f1898d.equals(c0722iv.f1898d))) {
            return false;
        }
        boolean m1911g = m1911g();
        boolean m1911g2 = c0722iv.m1911g();
        if ((m1911g || m1911g2) && !(m1911g && m1911g2 && this.f1899e.equals(c0722iv.f1899e))) {
            return false;
        }
        boolean m1912h = m1912h();
        boolean m1912h2 = c0722iv.m1912h();
        if ((m1912h || m1912h2) && !(m1912h && m1912h2 && this.f1889a.m2194a(c0722iv.f1889a))) {
            return false;
        }
        boolean m1913i = m1913i();
        boolean m1913i2 = c0722iv.m1913i();
        if ((m1913i || m1913i2) && !(m1913i && m1913i2 && this.f1900f.equals(c0722iv.f1900f))) {
            return false;
        }
        boolean m1914j = m1914j();
        boolean m1914j2 = c0722iv.m1914j();
        if ((m1914j || m1914j2) && !(m1914j && m1914j2 && this.f1901g.equals(c0722iv.f1901g))) {
            return false;
        }
        boolean m1915k = m1915k();
        boolean m1915k2 = c0722iv.m1915k();
        if ((m1915k || m1915k2) && !(m1915k && m1915k2 && this.f1894a == c0722iv.f1894a)) {
            return false;
        }
        boolean m1916l = m1916l();
        boolean m1916l2 = c0722iv.m1916l();
        if ((m1916l || m1916l2) && !(m1916l && m1916l2 && this.f1902h.equals(c0722iv.f1902h))) {
            return false;
        }
        boolean m1917m = m1917m();
        boolean m1917m2 = c0722iv.m1917m();
        if ((m1917m || m1917m2) && !(m1917m && m1917m2 && this.f1903i.equals(c0722iv.f1903i))) {
            return false;
        }
        boolean m1918n = m1918n();
        boolean m1918n2 = c0722iv.m1918n();
        if ((m1918n || m1918n2) && !(m1918n && m1918n2 && this.f1904j.equals(c0722iv.f1904j))) {
            return false;
        }
        boolean m1919o = m1919o();
        boolean m1919o2 = c0722iv.m1919o();
        if ((m1919o || m1919o2) && !(m1919o && m1919o2 && this.f1893a == c0722iv.f1893a)) {
            return false;
        }
        boolean m1920p = m1920p();
        boolean m1920p2 = c0722iv.m1920p();
        if ((m1920p || m1920p2) && !(m1920p && m1920p2 && this.f1896b == c0722iv.f1896b)) {
            return false;
        }
        boolean m1921q = m1921q();
        boolean m1921q2 = c0722iv.m1921q();
        if ((m1921q || m1921q2) && !(m1921q && m1921q2 && this.f1905k.equals(c0722iv.f1905k))) {
            return false;
        }
        boolean m1922r = m1922r();
        boolean m1922r2 = c0722iv.m1922r();
        if ((m1922r || m1922r2) && !(m1922r && m1922r2 && this.f1906l.equals(c0722iv.f1906l))) {
            return false;
        }
        boolean m1923s = m1923s();
        boolean m1923s2 = c0722iv.m1923s();
        if ((m1923s || m1923s2) && !(m1923s && m1923s2 && this.f1886a == c0722iv.f1886a)) {
            return false;
        }
        boolean m1924t = m1924t();
        boolean m1924t2 = c0722iv.m1924t();
        if (m1924t || m1924t2) {
            return m1924t && m1924t2 && this.f1892a.equals(c0722iv.f1892a);
        }
        return true;
    }

    /* renamed from: b */
    public C0722iv m1898b(String str) {
        this.f1897c = str;
        return this;
    }

    /* renamed from: b */
    public C0722iv m1899b(short s) {
        this.f1896b = s;
        m1906d(true);
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1894a();
        abstractC0756kb.mo2360a(f1866a);
        if (this.f1890a != null && m1896a()) {
            abstractC0756kb.mo2356a(f1865a);
            abstractC0756kb.mo2361a(this.f1890a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1888a != null && m1901b()) {
            abstractC0756kb.mo2356a(f1867b);
            this.f1888a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1895b != null) {
            abstractC0756kb.mo2356a(f1868c);
            abstractC0756kb.mo2361a(this.f1895b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1897c != null) {
            abstractC0756kb.mo2356a(f1869d);
            abstractC0756kb.mo2361a(this.f1897c);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f1870e);
        abstractC0756kb.mo2355a(this.f1887a);
        abstractC0756kb.mo2366b();
        if (this.f1898d != null && m1910f()) {
            abstractC0756kb.mo2356a(f1871f);
            abstractC0756kb.mo2361a(this.f1898d);
            abstractC0756kb.mo2366b();
        }
        if (this.f1899e != null && m1911g()) {
            abstractC0756kb.mo2356a(f1872g);
            abstractC0756kb.mo2361a(this.f1899e);
            abstractC0756kb.mo2366b();
        }
        if (this.f1889a != null && m1912h()) {
            abstractC0756kb.mo2356a(f1873h);
            this.f1889a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1900f != null && m1913i()) {
            abstractC0756kb.mo2356a(f1874i);
            abstractC0756kb.mo2361a(this.f1900f);
            abstractC0756kb.mo2366b();
        }
        if (this.f1901g != null && m1914j()) {
            abstractC0756kb.mo2356a(f1875j);
            abstractC0756kb.mo2361a(this.f1901g);
            abstractC0756kb.mo2366b();
        }
        if (m1915k()) {
            abstractC0756kb.mo2356a(f1876k);
            abstractC0756kb.mo2364a(this.f1894a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1902h != null && m1916l()) {
            abstractC0756kb.mo2356a(f1877l);
            abstractC0756kb.mo2361a(this.f1902h);
            abstractC0756kb.mo2366b();
        }
        if (this.f1903i != null && m1917m()) {
            abstractC0756kb.mo2356a(f1878m);
            abstractC0756kb.mo2361a(this.f1903i);
            abstractC0756kb.mo2366b();
        }
        if (this.f1904j != null && m1918n()) {
            abstractC0756kb.mo2356a(f1879n);
            abstractC0756kb.mo2361a(this.f1904j);
            abstractC0756kb.mo2366b();
        }
        if (m1919o()) {
            abstractC0756kb.mo2356a(f1880o);
            abstractC0756kb.mo2363a(this.f1893a);
            abstractC0756kb.mo2366b();
        }
        if (m1920p()) {
            abstractC0756kb.mo2356a(f1881p);
            abstractC0756kb.mo2363a(this.f1896b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1905k != null && m1921q()) {
            abstractC0756kb.mo2356a(f1882q);
            abstractC0756kb.mo2361a(this.f1905k);
            abstractC0756kb.mo2366b();
        }
        if (this.f1906l != null && m1922r()) {
            abstractC0756kb.mo2356a(f1883r);
            abstractC0756kb.mo2361a(this.f1906l);
            abstractC0756kb.mo2366b();
        }
        if (m1923s()) {
            abstractC0756kb.mo2356a(f1884s);
            abstractC0756kb.mo2354a(this.f1886a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1892a != null && m1924t()) {
            abstractC0756kb.mo2356a(f1885t);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f1892a.size()));
            for (Map.Entry<String, String> entry : this.f1892a.entrySet()) {
                abstractC0756kb.mo2361a(entry.getKey());
                abstractC0756kb.mo2361a(entry.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1900b(boolean z) {
        this.f1891a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1901b() {
        return this.f1888a != null;
    }

    /* renamed from: c */
    public C0722iv m1902c(String str) {
        this.f1898d = str;
        return this;
    }

    /* renamed from: c */
    public void m1903c(boolean z) {
        this.f1891a.set(2, z);
    }

    /* renamed from: c */
    public boolean m1904c() {
        return this.f1895b != null;
    }

    /* renamed from: d */
    public C0722iv m1905d(String str) {
        this.f1899e = str;
        return this;
    }

    /* renamed from: d */
    public void m1906d(boolean z) {
        this.f1891a.set(3, z);
    }

    /* renamed from: d */
    public boolean m1907d() {
        return this.f1897c != null;
    }

    /* renamed from: e */
    public void m1908e(boolean z) {
        this.f1891a.set(4, z);
    }

    /* renamed from: e */
    public boolean m1909e() {
        return this.f1891a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0722iv)) {
            return m1897a((C0722iv) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m1910f() {
        return this.f1898d != null;
    }

    /* renamed from: g */
    public boolean m1911g() {
        return this.f1899e != null;
    }

    /* renamed from: h */
    public boolean m1912h() {
        return this.f1889a != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m1913i() {
        return this.f1900f != null;
    }

    /* renamed from: j */
    public boolean m1914j() {
        return this.f1901g != null;
    }

    /* renamed from: k */
    public boolean m1915k() {
        return this.f1891a.get(1);
    }

    /* renamed from: l */
    public boolean m1916l() {
        return this.f1902h != null;
    }

    /* renamed from: m */
    public boolean m1917m() {
        return this.f1903i != null;
    }

    /* renamed from: n */
    public boolean m1918n() {
        return this.f1904j != null;
    }

    /* renamed from: o */
    public boolean m1919o() {
        return this.f1891a.get(2);
    }

    /* renamed from: p */
    public boolean m1920p() {
        return this.f1891a.get(3);
    }

    /* renamed from: q */
    public boolean m1921q() {
        return this.f1905k != null;
    }

    /* renamed from: r */
    public boolean m1922r() {
        return this.f1906l != null;
    }

    /* renamed from: s */
    public boolean m1923s() {
        return this.f1891a.get(4);
    }

    /* renamed from: t */
    public boolean m1924t() {
        return this.f1892a != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckMessage(");
        if (m1896a()) {
            sb.append("debug:");
            String str = this.f1890a;
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
        if (m1901b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f1888a;
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
        String str2 = this.f1895b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        String str3 = this.f1897c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f1887a);
        if (m1910f()) {
            sb.append(", ");
            sb.append("topic:");
            String str4 = this.f1898d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m1911g()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str5 = this.f1899e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m1912h()) {
            sb.append(", ");
            sb.append("request:");
            C0736ji c0736ji = this.f1889a;
            if (c0736ji == null) {
                sb.append("null");
            } else {
                sb.append(c0736ji);
            }
        }
        if (m1913i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f1900f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m1914j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f1901g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m1915k()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f1894a);
        }
        if (m1916l()) {
            sb.append(", ");
            sb.append("regId:");
            String str8 = this.f1902h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m1917m()) {
            sb.append(", ");
            sb.append("callbackUrl:");
            String str9 = this.f1903i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m1918n()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str10 = this.f1904j;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (m1919o()) {
            sb.append(", ");
            sb.append("deviceStatus:");
            sb.append((int) this.f1893a);
        }
        if (m1920p()) {
            sb.append(", ");
            sb.append("geoMsgStatus:");
            sb.append((int) this.f1896b);
        }
        if (m1921q()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str11 = this.f1905k;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (m1922r()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str12 = this.f1906l;
            if (str12 == null) {
                sb.append("null");
            } else {
                sb.append(str12);
            }
        }
        if (m1923s()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.f1886a);
        }
        if (m1924t()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f1892a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
