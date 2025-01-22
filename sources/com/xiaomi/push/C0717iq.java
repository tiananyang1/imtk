package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.iq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/iq.class */
public class C0717iq implements InterfaceC0744jq<C0717iq, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f1821a;

    /* renamed from: a */
    public long f1822a;

    /* renamed from: a */
    public String f1823a;

    /* renamed from: a */
    private BitSet f1824a;

    /* renamed from: a */
    public Map<String, String> f1825a;

    /* renamed from: a */
    public boolean f1826a;

    /* renamed from: b */
    public int f1827b;

    /* renamed from: b */
    public String f1828b;

    /* renamed from: b */
    public Map<String, String> f1829b;

    /* renamed from: c */
    public int f1830c;

    /* renamed from: c */
    public String f1831c;

    /* renamed from: c */
    public Map<String, String> f1832c;

    /* renamed from: d */
    public String f1833d;

    /* renamed from: e */
    public String f1834e;

    /* renamed from: a */
    private static final C0761kg f1808a = new C0761kg("PushMetaInfo");

    /* renamed from: a */
    private static final C0752jy f1807a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f1809b = new C0752jy("", (byte) 10, 2);

    /* renamed from: c */
    private static final C0752jy f1810c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f1811d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f1812e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f1813f = new C0752jy("", (byte) 8, 6);

    /* renamed from: g */
    private static final C0752jy f1814g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f1815h = new C0752jy("", (byte) 8, 8);

    /* renamed from: i */
    private static final C0752jy f1816i = new C0752jy("", (byte) 8, 9);

    /* renamed from: j */
    private static final C0752jy f1817j = new C0752jy("", (byte) 13, 10);

    /* renamed from: k */
    private static final C0752jy f1818k = new C0752jy("", (byte) 13, 11);

    /* renamed from: l */
    private static final C0752jy f1819l = new C0752jy("", (byte) 2, 12);

    /* renamed from: m */
    private static final C0752jy f1820m = new C0752jy("", (byte) 13, 13);

    public C0717iq() {
        this.f1824a = new BitSet(5);
        this.f1826a = false;
    }

    public C0717iq(C0717iq c0717iq) {
        this.f1824a = new BitSet(5);
        this.f1824a.clear();
        this.f1824a.or(c0717iq.f1824a);
        if (c0717iq.m1830a()) {
            this.f1823a = c0717iq.f1823a;
        }
        this.f1822a = c0717iq.f1822a;
        if (c0717iq.m1845c()) {
            this.f1828b = c0717iq.f1828b;
        }
        if (c0717iq.m1849d()) {
            this.f1831c = c0717iq.f1831c;
        }
        if (c0717iq.m1851e()) {
            this.f1833d = c0717iq.f1833d;
        }
        this.f1821a = c0717iq.f1821a;
        if (c0717iq.m1853g()) {
            this.f1834e = c0717iq.f1834e;
        }
        this.f1827b = c0717iq.f1827b;
        this.f1830c = c0717iq.f1830c;
        if (c0717iq.m1856j()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, String> entry : c0717iq.f1825a.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            this.f1825a = hashMap;
        }
        if (c0717iq.m1857k()) {
            HashMap hashMap2 = new HashMap();
            for (Map.Entry<String, String> entry2 : c0717iq.f1829b.entrySet()) {
                hashMap2.put(entry2.getKey(), entry2.getValue());
            }
            this.f1829b = hashMap2;
        }
        this.f1826a = c0717iq.f1826a;
        if (c0717iq.m1860n()) {
            HashMap hashMap3 = new HashMap();
            for (Map.Entry<String, String> entry3 : c0717iq.f1832c.entrySet()) {
                hashMap3.put(entry3.getKey(), entry3.getValue());
            }
            this.f1832c = hashMap3;
        }
    }

    /* renamed from: a */
    public int m1818a() {
        return this.f1821a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0717iq c0717iq) {
        int m2323a;
        int m2326a;
        int m2323a2;
        int m2323a3;
        int m2317a;
        int m2317a2;
        int m2320a;
        int m2317a3;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2318a;
        int m2320a5;
        if (!getClass().equals(c0717iq.getClass())) {
            return getClass().getName().compareTo(c0717iq.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1830a()).compareTo(Boolean.valueOf(c0717iq.m1830a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1830a() && (m2320a5 = C0745jr.m2320a(this.f1823a, c0717iq.f1823a)) != 0) {
            return m2320a5;
        }
        int compareTo2 = Boolean.valueOf(m1839b()).compareTo(Boolean.valueOf(c0717iq.m1839b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1839b() && (m2318a = C0745jr.m2318a(this.f1822a, c0717iq.f1822a)) != 0) {
            return m2318a;
        }
        int compareTo3 = Boolean.valueOf(m1845c()).compareTo(Boolean.valueOf(c0717iq.m1845c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1845c() && (m2320a4 = C0745jr.m2320a(this.f1828b, c0717iq.f1828b)) != 0) {
            return m2320a4;
        }
        int compareTo4 = Boolean.valueOf(m1849d()).compareTo(Boolean.valueOf(c0717iq.m1849d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1849d() && (m2320a3 = C0745jr.m2320a(this.f1831c, c0717iq.f1831c)) != 0) {
            return m2320a3;
        }
        int compareTo5 = Boolean.valueOf(m1851e()).compareTo(Boolean.valueOf(c0717iq.m1851e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1851e() && (m2320a2 = C0745jr.m2320a(this.f1833d, c0717iq.f1833d)) != 0) {
            return m2320a2;
        }
        int compareTo6 = Boolean.valueOf(m1852f()).compareTo(Boolean.valueOf(c0717iq.m1852f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1852f() && (m2317a3 = C0745jr.m2317a(this.f1821a, c0717iq.f1821a)) != 0) {
            return m2317a3;
        }
        int compareTo7 = Boolean.valueOf(m1853g()).compareTo(Boolean.valueOf(c0717iq.m1853g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m1853g() && (m2320a = C0745jr.m2320a(this.f1834e, c0717iq.f1834e)) != 0) {
            return m2320a;
        }
        int compareTo8 = Boolean.valueOf(m1854h()).compareTo(Boolean.valueOf(c0717iq.m1854h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m1854h() && (m2317a2 = C0745jr.m2317a(this.f1827b, c0717iq.f1827b)) != 0) {
            return m2317a2;
        }
        int compareTo9 = Boolean.valueOf(m1855i()).compareTo(Boolean.valueOf(c0717iq.m1855i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m1855i() && (m2317a = C0745jr.m2317a(this.f1830c, c0717iq.f1830c)) != 0) {
            return m2317a;
        }
        int compareTo10 = Boolean.valueOf(m1856j()).compareTo(Boolean.valueOf(c0717iq.m1856j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (m1856j() && (m2323a3 = C0745jr.m2323a(this.f1825a, c0717iq.f1825a)) != 0) {
            return m2323a3;
        }
        int compareTo11 = Boolean.valueOf(m1857k()).compareTo(Boolean.valueOf(c0717iq.m1857k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (m1857k() && (m2323a2 = C0745jr.m2323a(this.f1829b, c0717iq.f1829b)) != 0) {
            return m2323a2;
        }
        int compareTo12 = Boolean.valueOf(m1859m()).compareTo(Boolean.valueOf(c0717iq.m1859m()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (m1859m() && (m2326a = C0745jr.m2326a(this.f1826a, c0717iq.f1826a)) != 0) {
            return m2326a;
        }
        int compareTo13 = Boolean.valueOf(m1860n()).compareTo(Boolean.valueOf(c0717iq.m1860n()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (!m1860n() || (m2323a = C0745jr.m2323a(this.f1832c, c0717iq.f1832c)) == 0) {
            return 0;
        }
        return m2323a;
    }

    /* renamed from: a */
    public long m1820a() {
        return this.f1822a;
    }

    /* renamed from: a */
    public C0717iq m1821a() {
        return new C0717iq(this);
    }

    /* renamed from: a */
    public C0717iq m1822a(int i) {
        this.f1821a = i;
        m1838b(true);
        return this;
    }

    /* renamed from: a */
    public C0717iq m1823a(String str) {
        this.f1823a = str;
        return this;
    }

    /* renamed from: a */
    public C0717iq m1824a(Map<String, String> map) {
        this.f1825a = map;
        return this;
    }

    /* renamed from: a */
    public String m1825a() {
        return this.f1823a;
    }

    /* renamed from: a */
    public Map<String, String> m1826a() {
        return this.f1825a;
    }

    /* renamed from: a */
    public void m1827a() {
        if (this.f1823a != null) {
            return;
        }
        throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0058. Please report as an issue. */
    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                if (m1839b()) {
                    m1827a();
                    return;
                }
                throw new C0757kc("Required field 'messageTs' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f1823a = abstractC0756kb.mo2347a();
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 2:
                    if (mo2342a.f2324a == 10) {
                        this.f1822a = abstractC0756kb.mo2341a();
                        m1829a(true);
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f1828b = abstractC0756kb.mo2347a();
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f1831c = abstractC0756kb.mo2347a();
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f1833d = abstractC0756kb.mo2347a();
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 6:
                    if (mo2342a.f2324a == 8) {
                        this.f1821a = abstractC0756kb.mo2340a();
                        m1838b(true);
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f1834e = abstractC0756kb.mo2347a();
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 8:
                    if (mo2342a.f2324a == 8) {
                        this.f1827b = abstractC0756kb.mo2340a();
                        m1844c(true);
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 9:
                    if (mo2342a.f2324a == 8) {
                        this.f1830c = abstractC0756kb.mo2340a();
                        m1848d(true);
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 10:
                    if (mo2342a.f2324a == 13) {
                        C0755ka mo2344a = abstractC0756kb.mo2344a();
                        this.f1825a = new HashMap(mo2344a.f2335a * 2);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2344a.f2335a) {
                                abstractC0756kb.mo2375i();
                                break;
                            } else {
                                this.f1825a.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
                                i = i2 + 1;
                            }
                        }
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 11:
                    if (mo2342a.f2324a == 13) {
                        C0755ka mo2344a2 = abstractC0756kb.mo2344a();
                        this.f1829b = new HashMap(mo2344a2.f2335a * 2);
                        int i3 = 0;
                        while (true) {
                            int i4 = i3;
                            if (i4 >= mo2344a2.f2335a) {
                                abstractC0756kb.mo2375i();
                                break;
                            } else {
                                this.f1829b.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
                                i3 = i4 + 1;
                            }
                        }
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 12:
                    if (mo2342a.f2324a == 2) {
                        this.f1826a = abstractC0756kb.mo2365a();
                        m1850e(true);
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                case 13:
                    if (mo2342a.f2324a == 13) {
                        C0755ka mo2344a3 = abstractC0756kb.mo2344a();
                        this.f1832c = new HashMap(mo2344a3.f2335a * 2);
                        for (int i5 = 0; i5 < mo2344a3.f2335a; i5++) {
                            this.f1832c.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
                        }
                        abstractC0756kb.mo2375i();
                        break;
                    }
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
                default:
                    C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                    break;
            }
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m1828a(String str, String str2) {
        if (this.f1825a == null) {
            this.f1825a = new HashMap();
        }
        this.f1825a.put(str, str2);
    }

    /* renamed from: a */
    public void m1829a(boolean z) {
        this.f1824a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1830a() {
        return this.f1823a != null;
    }

    /* renamed from: a */
    public boolean m1831a(C0717iq c0717iq) {
        if (c0717iq == null) {
            return false;
        }
        boolean m1830a = m1830a();
        boolean m1830a2 = c0717iq.m1830a();
        if (((m1830a || m1830a2) && !(m1830a && m1830a2 && this.f1823a.equals(c0717iq.f1823a))) || this.f1822a != c0717iq.f1822a) {
            return false;
        }
        boolean m1845c = m1845c();
        boolean m1845c2 = c0717iq.m1845c();
        if ((m1845c || m1845c2) && !(m1845c && m1845c2 && this.f1828b.equals(c0717iq.f1828b))) {
            return false;
        }
        boolean m1849d = m1849d();
        boolean m1849d2 = c0717iq.m1849d();
        if ((m1849d || m1849d2) && !(m1849d && m1849d2 && this.f1831c.equals(c0717iq.f1831c))) {
            return false;
        }
        boolean m1851e = m1851e();
        boolean m1851e2 = c0717iq.m1851e();
        if ((m1851e || m1851e2) && !(m1851e && m1851e2 && this.f1833d.equals(c0717iq.f1833d))) {
            return false;
        }
        boolean m1852f = m1852f();
        boolean m1852f2 = c0717iq.m1852f();
        if ((m1852f || m1852f2) && !(m1852f && m1852f2 && this.f1821a == c0717iq.f1821a)) {
            return false;
        }
        boolean m1853g = m1853g();
        boolean m1853g2 = c0717iq.m1853g();
        if ((m1853g || m1853g2) && !(m1853g && m1853g2 && this.f1834e.equals(c0717iq.f1834e))) {
            return false;
        }
        boolean m1854h = m1854h();
        boolean m1854h2 = c0717iq.m1854h();
        if ((m1854h || m1854h2) && !(m1854h && m1854h2 && this.f1827b == c0717iq.f1827b)) {
            return false;
        }
        boolean m1855i = m1855i();
        boolean m1855i2 = c0717iq.m1855i();
        if ((m1855i || m1855i2) && !(m1855i && m1855i2 && this.f1830c == c0717iq.f1830c)) {
            return false;
        }
        boolean m1856j = m1856j();
        boolean m1856j2 = c0717iq.m1856j();
        if ((m1856j || m1856j2) && !(m1856j && m1856j2 && this.f1825a.equals(c0717iq.f1825a))) {
            return false;
        }
        boolean m1857k = m1857k();
        boolean m1857k2 = c0717iq.m1857k();
        if ((m1857k || m1857k2) && !(m1857k && m1857k2 && this.f1829b.equals(c0717iq.f1829b))) {
            return false;
        }
        boolean m1859m = m1859m();
        boolean m1859m2 = c0717iq.m1859m();
        if ((m1859m || m1859m2) && !(m1859m && m1859m2 && this.f1826a == c0717iq.f1826a)) {
            return false;
        }
        boolean m1860n = m1860n();
        boolean m1860n2 = c0717iq.m1860n();
        if (m1860n || m1860n2) {
            return m1860n && m1860n2 && this.f1832c.equals(c0717iq.f1832c);
        }
        return true;
    }

    /* renamed from: b */
    public int m1832b() {
        return this.f1827b;
    }

    /* renamed from: b */
    public C0717iq m1833b(int i) {
        this.f1827b = i;
        m1844c(true);
        return this;
    }

    /* renamed from: b */
    public C0717iq m1834b(String str) {
        this.f1828b = str;
        return this;
    }

    /* renamed from: b */
    public String m1835b() {
        return this.f1828b;
    }

    /* renamed from: b */
    public Map<String, String> m1836b() {
        return this.f1829b;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1827a();
        abstractC0756kb.mo2360a(f1808a);
        if (this.f1823a != null) {
            abstractC0756kb.mo2356a(f1807a);
            abstractC0756kb.mo2361a(this.f1823a);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f1809b);
        abstractC0756kb.mo2355a(this.f1822a);
        abstractC0756kb.mo2366b();
        if (this.f1828b != null && m1845c()) {
            abstractC0756kb.mo2356a(f1810c);
            abstractC0756kb.mo2361a(this.f1828b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1831c != null && m1849d()) {
            abstractC0756kb.mo2356a(f1811d);
            abstractC0756kb.mo2361a(this.f1831c);
            abstractC0756kb.mo2366b();
        }
        if (this.f1833d != null && m1851e()) {
            abstractC0756kb.mo2356a(f1812e);
            abstractC0756kb.mo2361a(this.f1833d);
            abstractC0756kb.mo2366b();
        }
        if (m1852f()) {
            abstractC0756kb.mo2356a(f1813f);
            abstractC0756kb.mo2354a(this.f1821a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1834e != null && m1853g()) {
            abstractC0756kb.mo2356a(f1814g);
            abstractC0756kb.mo2361a(this.f1834e);
            abstractC0756kb.mo2366b();
        }
        if (m1854h()) {
            abstractC0756kb.mo2356a(f1815h);
            abstractC0756kb.mo2354a(this.f1827b);
            abstractC0756kb.mo2366b();
        }
        if (m1855i()) {
            abstractC0756kb.mo2356a(f1816i);
            abstractC0756kb.mo2354a(this.f1830c);
            abstractC0756kb.mo2366b();
        }
        if (this.f1825a != null && m1856j()) {
            abstractC0756kb.mo2356a(f1817j);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f1825a.size()));
            for (Map.Entry<String, String> entry : this.f1825a.entrySet()) {
                abstractC0756kb.mo2361a(entry.getKey());
                abstractC0756kb.mo2361a(entry.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        if (this.f1829b != null && m1857k()) {
            abstractC0756kb.mo2356a(f1818k);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f1829b.size()));
            for (Map.Entry<String, String> entry2 : this.f1829b.entrySet()) {
                abstractC0756kb.mo2361a(entry2.getKey());
                abstractC0756kb.mo2361a(entry2.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        if (m1859m()) {
            abstractC0756kb.mo2356a(f1819l);
            abstractC0756kb.mo2364a(this.f1826a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1832c != null && m1860n()) {
            abstractC0756kb.mo2356a(f1820m);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f1832c.size()));
            for (Map.Entry<String, String> entry3 : this.f1832c.entrySet()) {
                abstractC0756kb.mo2361a(entry3.getKey());
                abstractC0756kb.mo2361a(entry3.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1837b(String str, String str2) {
        if (this.f1829b == null) {
            this.f1829b = new HashMap();
        }
        this.f1829b.put(str, str2);
    }

    /* renamed from: b */
    public void m1838b(boolean z) {
        this.f1824a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1839b() {
        return this.f1824a.get(0);
    }

    /* renamed from: c */
    public int m1840c() {
        return this.f1830c;
    }

    /* renamed from: c */
    public C0717iq m1841c(int i) {
        this.f1830c = i;
        m1848d(true);
        return this;
    }

    /* renamed from: c */
    public C0717iq m1842c(String str) {
        this.f1831c = str;
        return this;
    }

    /* renamed from: c */
    public String m1843c() {
        return this.f1831c;
    }

    /* renamed from: c */
    public void m1844c(boolean z) {
        this.f1824a.set(2, z);
    }

    /* renamed from: c */
    public boolean m1845c() {
        return this.f1828b != null;
    }

    /* renamed from: d */
    public C0717iq m1846d(String str) {
        this.f1833d = str;
        return this;
    }

    /* renamed from: d */
    public String m1847d() {
        return this.f1833d;
    }

    /* renamed from: d */
    public void m1848d(boolean z) {
        this.f1824a.set(3, z);
    }

    /* renamed from: d */
    public boolean m1849d() {
        return this.f1831c != null;
    }

    /* renamed from: e */
    public void m1850e(boolean z) {
        this.f1824a.set(4, z);
    }

    /* renamed from: e */
    public boolean m1851e() {
        return this.f1833d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0717iq)) {
            return m1831a((C0717iq) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m1852f() {
        return this.f1824a.get(1);
    }

    /* renamed from: g */
    public boolean m1853g() {
        return this.f1834e != null;
    }

    /* renamed from: h */
    public boolean m1854h() {
        return this.f1824a.get(2);
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m1855i() {
        return this.f1824a.get(3);
    }

    /* renamed from: j */
    public boolean m1856j() {
        return this.f1825a != null;
    }

    /* renamed from: k */
    public boolean m1857k() {
        return this.f1829b != null;
    }

    /* renamed from: l */
    public boolean m1858l() {
        return this.f1826a;
    }

    /* renamed from: m */
    public boolean m1859m() {
        return this.f1824a.get(4);
    }

    /* renamed from: n */
    public boolean m1860n() {
        return this.f1832c != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PushMetaInfo(");
        sb.append("id:");
        String str = this.f1823a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("messageTs:");
        sb.append(this.f1822a);
        if (m1845c()) {
            sb.append(", ");
            sb.append("topic:");
            String str2 = this.f1828b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        if (m1849d()) {
            sb.append(", ");
            sb.append("title:");
            String str3 = this.f1831c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m1851e()) {
            sb.append(", ");
            sb.append("description:");
            String str4 = this.f1833d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m1852f()) {
            sb.append(", ");
            sb.append("notifyType:");
            sb.append(this.f1821a);
        }
        if (m1853g()) {
            sb.append(", ");
            sb.append("url:");
            String str5 = this.f1834e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m1854h()) {
            sb.append(", ");
            sb.append("passThrough:");
            sb.append(this.f1827b);
        }
        if (m1855i()) {
            sb.append(", ");
            sb.append("notifyId:");
            sb.append(this.f1830c);
        }
        if (m1856j()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f1825a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m1857k()) {
            sb.append(", ");
            sb.append("internal:");
            Map<String, String> map2 = this.f1829b;
            if (map2 == null) {
                sb.append("null");
            } else {
                sb.append(map2);
            }
        }
        if (m1859m()) {
            sb.append(", ");
            sb.append("ignoreRegInfo:");
            sb.append(this.f1826a);
        }
        if (m1860n()) {
            sb.append(", ");
            sb.append("apsProperFields:");
            Map<String, String> map3 = this.f1832c;
            if (map3 == null) {
                sb.append("null");
            } else {
                sb.append(map3);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
