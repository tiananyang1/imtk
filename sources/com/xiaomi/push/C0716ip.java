package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.ip */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ip.class */
public class C0716ip implements InterfaceC0744jq<C0716ip, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f1788a;

    /* renamed from: a */
    public C0717iq f1789a;

    /* renamed from: a */
    public C0720it f1790a;

    /* renamed from: a */
    public String f1791a;

    /* renamed from: a */
    private BitSet f1792a = new BitSet(4);

    /* renamed from: a */
    public boolean f1793a = false;

    /* renamed from: b */
    public long f1794b;

    /* renamed from: b */
    public String f1795b;

    /* renamed from: c */
    public long f1796c;

    /* renamed from: c */
    public String f1797c;

    /* renamed from: d */
    public String f1798d;

    /* renamed from: e */
    public String f1799e;

    /* renamed from: f */
    public String f1800f;

    /* renamed from: g */
    public String f1801g;

    /* renamed from: h */
    public String f1802h;

    /* renamed from: i */
    public String f1803i;

    /* renamed from: j */
    public String f1804j;

    /* renamed from: k */
    public String f1805k;

    /* renamed from: l */
    public String f1806l;

    /* renamed from: a */
    private static final C0761kg f1770a = new C0761kg("PushMessage");

    /* renamed from: a */
    private static final C0752jy f1769a = new C0752jy("", (byte) 12, 1);

    /* renamed from: b */
    private static final C0752jy f1771b = new C0752jy("", (byte) 11, 2);

    /* renamed from: c */
    private static final C0752jy f1772c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f1773d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f1774e = new C0752jy("", (byte) 10, 5);

    /* renamed from: f */
    private static final C0752jy f1775f = new C0752jy("", (byte) 10, 6);

    /* renamed from: g */
    private static final C0752jy f1776g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f1777h = new C0752jy("", (byte) 11, 8);

    /* renamed from: i */
    private static final C0752jy f1778i = new C0752jy("", (byte) 11, 9);

    /* renamed from: j */
    private static final C0752jy f1779j = new C0752jy("", (byte) 11, 10);

    /* renamed from: k */
    private static final C0752jy f1780k = new C0752jy("", (byte) 11, 11);

    /* renamed from: l */
    private static final C0752jy f1781l = new C0752jy("", (byte) 12, 12);

    /* renamed from: m */
    private static final C0752jy f1782m = new C0752jy("", (byte) 11, 13);

    /* renamed from: n */
    private static final C0752jy f1783n = new C0752jy("", (byte) 2, 14);

    /* renamed from: o */
    private static final C0752jy f1784o = new C0752jy("", (byte) 11, 15);

    /* renamed from: p */
    private static final C0752jy f1785p = new C0752jy("", (byte) 10, 16);

    /* renamed from: q */
    private static final C0752jy f1786q = new C0752jy("", (byte) 11, 20);

    /* renamed from: r */
    private static final C0752jy f1787r = new C0752jy("", (byte) 11, 21);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0716ip c0716ip) {
        int m2320a;
        int m2320a2;
        int m2318a;
        int m2320a3;
        int m2326a;
        int m2320a4;
        int m2319a;
        int m2320a5;
        int m2320a6;
        int m2320a7;
        int m2320a8;
        int m2320a9;
        int m2318a2;
        int m2318a3;
        int m2320a10;
        int m2320a11;
        int m2320a12;
        int m2319a2;
        if (!getClass().equals(c0716ip.getClass())) {
            return getClass().getName().compareTo(c0716ip.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1794a()).compareTo(Boolean.valueOf(c0716ip.m1794a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1794a() && (m2319a2 = C0745jr.m2319a(this.f1790a, c0716ip.f1790a)) != 0) {
            return m2319a2;
        }
        int compareTo2 = Boolean.valueOf(m1798b()).compareTo(Boolean.valueOf(c0716ip.m1798b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1798b() && (m2320a12 = C0745jr.m2320a(this.f1791a, c0716ip.f1791a)) != 0) {
            return m2320a12;
        }
        int compareTo3 = Boolean.valueOf(m1801c()).compareTo(Boolean.valueOf(c0716ip.m1801c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1801c() && (m2320a11 = C0745jr.m2320a(this.f1795b, c0716ip.f1795b)) != 0) {
            return m2320a11;
        }
        int compareTo4 = Boolean.valueOf(m1803d()).compareTo(Boolean.valueOf(c0716ip.m1803d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1803d() && (m2320a10 = C0745jr.m2320a(this.f1797c, c0716ip.f1797c)) != 0) {
            return m2320a10;
        }
        int compareTo5 = Boolean.valueOf(m1804e()).compareTo(Boolean.valueOf(c0716ip.m1804e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1804e() && (m2318a3 = C0745jr.m2318a(this.f1788a, c0716ip.f1788a)) != 0) {
            return m2318a3;
        }
        int compareTo6 = Boolean.valueOf(m1805f()).compareTo(Boolean.valueOf(c0716ip.m1805f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1805f() && (m2318a2 = C0745jr.m2318a(this.f1794b, c0716ip.f1794b)) != 0) {
            return m2318a2;
        }
        int compareTo7 = Boolean.valueOf(m1806g()).compareTo(Boolean.valueOf(c0716ip.m1806g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m1806g() && (m2320a9 = C0745jr.m2320a(this.f1798d, c0716ip.f1798d)) != 0) {
            return m2320a9;
        }
        int compareTo8 = Boolean.valueOf(m1807h()).compareTo(Boolean.valueOf(c0716ip.m1807h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m1807h() && (m2320a8 = C0745jr.m2320a(this.f1799e, c0716ip.f1799e)) != 0) {
            return m2320a8;
        }
        int compareTo9 = Boolean.valueOf(m1808i()).compareTo(Boolean.valueOf(c0716ip.m1808i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m1808i() && (m2320a7 = C0745jr.m2320a(this.f1800f, c0716ip.f1800f)) != 0) {
            return m2320a7;
        }
        int compareTo10 = Boolean.valueOf(m1809j()).compareTo(Boolean.valueOf(c0716ip.m1809j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (m1809j() && (m2320a6 = C0745jr.m2320a(this.f1801g, c0716ip.f1801g)) != 0) {
            return m2320a6;
        }
        int compareTo11 = Boolean.valueOf(m1810k()).compareTo(Boolean.valueOf(c0716ip.m1810k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (m1810k() && (m2320a5 = C0745jr.m2320a(this.f1802h, c0716ip.f1802h)) != 0) {
            return m2320a5;
        }
        int compareTo12 = Boolean.valueOf(m1811l()).compareTo(Boolean.valueOf(c0716ip.m1811l()));
        if (compareTo12 != 0) {
            return compareTo12;
        }
        if (m1811l() && (m2319a = C0745jr.m2319a(this.f1789a, c0716ip.f1789a)) != 0) {
            return m2319a;
        }
        int compareTo13 = Boolean.valueOf(m1812m()).compareTo(Boolean.valueOf(c0716ip.m1812m()));
        if (compareTo13 != 0) {
            return compareTo13;
        }
        if (m1812m() && (m2320a4 = C0745jr.m2320a(this.f1803i, c0716ip.f1803i)) != 0) {
            return m2320a4;
        }
        int compareTo14 = Boolean.valueOf(m1813n()).compareTo(Boolean.valueOf(c0716ip.m1813n()));
        if (compareTo14 != 0) {
            return compareTo14;
        }
        if (m1813n() && (m2326a = C0745jr.m2326a(this.f1793a, c0716ip.f1793a)) != 0) {
            return m2326a;
        }
        int compareTo15 = Boolean.valueOf(m1814o()).compareTo(Boolean.valueOf(c0716ip.m1814o()));
        if (compareTo15 != 0) {
            return compareTo15;
        }
        if (m1814o() && (m2320a3 = C0745jr.m2320a(this.f1804j, c0716ip.f1804j)) != 0) {
            return m2320a3;
        }
        int compareTo16 = Boolean.valueOf(m1815p()).compareTo(Boolean.valueOf(c0716ip.m1815p()));
        if (compareTo16 != 0) {
            return compareTo16;
        }
        if (m1815p() && (m2318a = C0745jr.m2318a(this.f1796c, c0716ip.f1796c)) != 0) {
            return m2318a;
        }
        int compareTo17 = Boolean.valueOf(m1816q()).compareTo(Boolean.valueOf(c0716ip.m1816q()));
        if (compareTo17 != 0) {
            return compareTo17;
        }
        if (m1816q() && (m2320a2 = C0745jr.m2320a(this.f1805k, c0716ip.f1805k)) != 0) {
            return m2320a2;
        }
        int compareTo18 = Boolean.valueOf(m1817r()).compareTo(Boolean.valueOf(c0716ip.m1817r()));
        if (compareTo18 != 0) {
            return compareTo18;
        }
        if (!m1817r() || (m2320a = C0745jr.m2320a(this.f1806l, c0716ip.f1806l)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public long m1790a() {
        return this.f1788a;
    }

    /* renamed from: a */
    public String m1791a() {
        return this.f1791a;
    }

    /* renamed from: a */
    public void m1792a() {
        if (this.f1791a == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f1795b == null) {
            throw new C0757kc("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f1797c != null) {
            return;
        }
        throw new C0757kc("Required field 'payload' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1792a();
                return;
            }
            short s = mo2342a.f2326a;
            if (s == 20) {
                if (mo2342a.f2324a == 11) {
                    this.f1805k = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else if (s != 21) {
                switch (s) {
                    case 1:
                        if (mo2342a.f2324a == 12) {
                            this.f1790a = new C0720it();
                            this.f1790a.mo1287a(abstractC0756kb);
                            break;
                        }
                        break;
                    case 2:
                        if (mo2342a.f2324a == 11) {
                            this.f1791a = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 3:
                        if (mo2342a.f2324a == 11) {
                            this.f1795b = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 4:
                        if (mo2342a.f2324a == 11) {
                            this.f1797c = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 5:
                        if (mo2342a.f2324a == 10) {
                            this.f1788a = abstractC0756kb.mo2341a();
                            m1793a(true);
                            break;
                        }
                        break;
                    case 6:
                        if (mo2342a.f2324a == 10) {
                            this.f1794b = abstractC0756kb.mo2341a();
                            m1797b(true);
                            break;
                        }
                        break;
                    case 7:
                        if (mo2342a.f2324a == 11) {
                            this.f1798d = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 8:
                        if (mo2342a.f2324a == 11) {
                            this.f1799e = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 9:
                        if (mo2342a.f2324a == 11) {
                            this.f1800f = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 10:
                        if (mo2342a.f2324a == 11) {
                            this.f1801g = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 11:
                        if (mo2342a.f2324a == 11) {
                            this.f1802h = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 12:
                        if (mo2342a.f2324a == 12) {
                            this.f1789a = new C0717iq();
                            this.f1789a.mo1287a(abstractC0756kb);
                            break;
                        }
                        break;
                    case 13:
                        if (mo2342a.f2324a == 11) {
                            this.f1803i = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 14:
                        if (mo2342a.f2324a == 2) {
                            this.f1793a = abstractC0756kb.mo2365a();
                            m1800c(true);
                            break;
                        }
                        break;
                    case 15:
                        if (mo2342a.f2324a == 11) {
                            this.f1804j = abstractC0756kb.mo2347a();
                            break;
                        }
                        break;
                    case 16:
                        if (mo2342a.f2324a == 10) {
                            this.f1796c = abstractC0756kb.mo2341a();
                            m1802d(true);
                            break;
                        }
                        break;
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            } else {
                if (mo2342a.f2324a == 11) {
                    this.f1806l = abstractC0756kb.mo2347a();
                    abstractC0756kb.mo2374h();
                }
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
                abstractC0756kb.mo2374h();
            }
        }
    }

    /* renamed from: a */
    public void m1793a(boolean z) {
        this.f1792a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1794a() {
        return this.f1790a != null;
    }

    /* renamed from: a */
    public boolean m1795a(C0716ip c0716ip) {
        if (c0716ip == null) {
            return false;
        }
        boolean m1794a = m1794a();
        boolean m1794a2 = c0716ip.m1794a();
        if ((m1794a || m1794a2) && !(m1794a && m1794a2 && this.f1790a.m1873a(c0716ip.f1790a))) {
            return false;
        }
        boolean m1798b = m1798b();
        boolean m1798b2 = c0716ip.m1798b();
        if ((m1798b || m1798b2) && !(m1798b && m1798b2 && this.f1791a.equals(c0716ip.f1791a))) {
            return false;
        }
        boolean m1801c = m1801c();
        boolean m1801c2 = c0716ip.m1801c();
        if ((m1801c || m1801c2) && !(m1801c && m1801c2 && this.f1795b.equals(c0716ip.f1795b))) {
            return false;
        }
        boolean m1803d = m1803d();
        boolean m1803d2 = c0716ip.m1803d();
        if ((m1803d || m1803d2) && !(m1803d && m1803d2 && this.f1797c.equals(c0716ip.f1797c))) {
            return false;
        }
        boolean m1804e = m1804e();
        boolean m1804e2 = c0716ip.m1804e();
        if ((m1804e || m1804e2) && !(m1804e && m1804e2 && this.f1788a == c0716ip.f1788a)) {
            return false;
        }
        boolean m1805f = m1805f();
        boolean m1805f2 = c0716ip.m1805f();
        if ((m1805f || m1805f2) && !(m1805f && m1805f2 && this.f1794b == c0716ip.f1794b)) {
            return false;
        }
        boolean m1806g = m1806g();
        boolean m1806g2 = c0716ip.m1806g();
        if ((m1806g || m1806g2) && !(m1806g && m1806g2 && this.f1798d.equals(c0716ip.f1798d))) {
            return false;
        }
        boolean m1807h = m1807h();
        boolean m1807h2 = c0716ip.m1807h();
        if ((m1807h || m1807h2) && !(m1807h && m1807h2 && this.f1799e.equals(c0716ip.f1799e))) {
            return false;
        }
        boolean m1808i = m1808i();
        boolean m1808i2 = c0716ip.m1808i();
        if ((m1808i || m1808i2) && !(m1808i && m1808i2 && this.f1800f.equals(c0716ip.f1800f))) {
            return false;
        }
        boolean m1809j = m1809j();
        boolean m1809j2 = c0716ip.m1809j();
        if ((m1809j || m1809j2) && !(m1809j && m1809j2 && this.f1801g.equals(c0716ip.f1801g))) {
            return false;
        }
        boolean m1810k = m1810k();
        boolean m1810k2 = c0716ip.m1810k();
        if ((m1810k || m1810k2) && !(m1810k && m1810k2 && this.f1802h.equals(c0716ip.f1802h))) {
            return false;
        }
        boolean m1811l = m1811l();
        boolean m1811l2 = c0716ip.m1811l();
        if ((m1811l || m1811l2) && !(m1811l && m1811l2 && this.f1789a.m1831a(c0716ip.f1789a))) {
            return false;
        }
        boolean m1812m = m1812m();
        boolean m1812m2 = c0716ip.m1812m();
        if ((m1812m || m1812m2) && !(m1812m && m1812m2 && this.f1803i.equals(c0716ip.f1803i))) {
            return false;
        }
        boolean m1813n = m1813n();
        boolean m1813n2 = c0716ip.m1813n();
        if ((m1813n || m1813n2) && !(m1813n && m1813n2 && this.f1793a == c0716ip.f1793a)) {
            return false;
        }
        boolean m1814o = m1814o();
        boolean m1814o2 = c0716ip.m1814o();
        if ((m1814o || m1814o2) && !(m1814o && m1814o2 && this.f1804j.equals(c0716ip.f1804j))) {
            return false;
        }
        boolean m1815p = m1815p();
        boolean m1815p2 = c0716ip.m1815p();
        if ((m1815p || m1815p2) && !(m1815p && m1815p2 && this.f1796c == c0716ip.f1796c)) {
            return false;
        }
        boolean m1816q = m1816q();
        boolean m1816q2 = c0716ip.m1816q();
        if ((m1816q || m1816q2) && !(m1816q && m1816q2 && this.f1805k.equals(c0716ip.f1805k))) {
            return false;
        }
        boolean m1817r = m1817r();
        boolean m1817r2 = c0716ip.m1817r();
        if (m1817r || m1817r2) {
            return m1817r && m1817r2 && this.f1806l.equals(c0716ip.f1806l);
        }
        return true;
    }

    /* renamed from: b */
    public String m1796b() {
        return this.f1795b;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1792a();
        abstractC0756kb.mo2360a(f1770a);
        if (this.f1790a != null && m1794a()) {
            abstractC0756kb.mo2356a(f1769a);
            this.f1790a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1791a != null) {
            abstractC0756kb.mo2356a(f1771b);
            abstractC0756kb.mo2361a(this.f1791a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1795b != null) {
            abstractC0756kb.mo2356a(f1772c);
            abstractC0756kb.mo2361a(this.f1795b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1797c != null) {
            abstractC0756kb.mo2356a(f1773d);
            abstractC0756kb.mo2361a(this.f1797c);
            abstractC0756kb.mo2366b();
        }
        if (m1804e()) {
            abstractC0756kb.mo2356a(f1774e);
            abstractC0756kb.mo2355a(this.f1788a);
            abstractC0756kb.mo2366b();
        }
        if (m1805f()) {
            abstractC0756kb.mo2356a(f1775f);
            abstractC0756kb.mo2355a(this.f1794b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1798d != null && m1806g()) {
            abstractC0756kb.mo2356a(f1776g);
            abstractC0756kb.mo2361a(this.f1798d);
            abstractC0756kb.mo2366b();
        }
        if (this.f1799e != null && m1807h()) {
            abstractC0756kb.mo2356a(f1777h);
            abstractC0756kb.mo2361a(this.f1799e);
            abstractC0756kb.mo2366b();
        }
        if (this.f1800f != null && m1808i()) {
            abstractC0756kb.mo2356a(f1778i);
            abstractC0756kb.mo2361a(this.f1800f);
            abstractC0756kb.mo2366b();
        }
        if (this.f1801g != null && m1809j()) {
            abstractC0756kb.mo2356a(f1779j);
            abstractC0756kb.mo2361a(this.f1801g);
            abstractC0756kb.mo2366b();
        }
        if (this.f1802h != null && m1810k()) {
            abstractC0756kb.mo2356a(f1780k);
            abstractC0756kb.mo2361a(this.f1802h);
            abstractC0756kb.mo2366b();
        }
        if (this.f1789a != null && m1811l()) {
            abstractC0756kb.mo2356a(f1781l);
            this.f1789a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1803i != null && m1812m()) {
            abstractC0756kb.mo2356a(f1782m);
            abstractC0756kb.mo2361a(this.f1803i);
            abstractC0756kb.mo2366b();
        }
        if (m1813n()) {
            abstractC0756kb.mo2356a(f1783n);
            abstractC0756kb.mo2364a(this.f1793a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1804j != null && m1814o()) {
            abstractC0756kb.mo2356a(f1784o);
            abstractC0756kb.mo2361a(this.f1804j);
            abstractC0756kb.mo2366b();
        }
        if (m1815p()) {
            abstractC0756kb.mo2356a(f1785p);
            abstractC0756kb.mo2355a(this.f1796c);
            abstractC0756kb.mo2366b();
        }
        if (this.f1805k != null && m1816q()) {
            abstractC0756kb.mo2356a(f1786q);
            abstractC0756kb.mo2361a(this.f1805k);
            abstractC0756kb.mo2366b();
        }
        if (this.f1806l != null && m1817r()) {
            abstractC0756kb.mo2356a(f1787r);
            abstractC0756kb.mo2361a(this.f1806l);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1797b(boolean z) {
        this.f1792a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1798b() {
        return this.f1791a != null;
    }

    /* renamed from: c */
    public String m1799c() {
        return this.f1797c;
    }

    /* renamed from: c */
    public void m1800c(boolean z) {
        this.f1792a.set(2, z);
    }

    /* renamed from: c */
    public boolean m1801c() {
        return this.f1795b != null;
    }

    /* renamed from: d */
    public void m1802d(boolean z) {
        this.f1792a.set(3, z);
    }

    /* renamed from: d */
    public boolean m1803d() {
        return this.f1797c != null;
    }

    /* renamed from: e */
    public boolean m1804e() {
        return this.f1792a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0716ip)) {
            return m1795a((C0716ip) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m1805f() {
        return this.f1792a.get(1);
    }

    /* renamed from: g */
    public boolean m1806g() {
        return this.f1798d != null;
    }

    /* renamed from: h */
    public boolean m1807h() {
        return this.f1799e != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m1808i() {
        return this.f1800f != null;
    }

    /* renamed from: j */
    public boolean m1809j() {
        return this.f1801g != null;
    }

    /* renamed from: k */
    public boolean m1810k() {
        return this.f1802h != null;
    }

    /* renamed from: l */
    public boolean m1811l() {
        return this.f1789a != null;
    }

    /* renamed from: m */
    public boolean m1812m() {
        return this.f1803i != null;
    }

    /* renamed from: n */
    public boolean m1813n() {
        return this.f1792a.get(2);
    }

    /* renamed from: o */
    public boolean m1814o() {
        return this.f1804j != null;
    }

    /* renamed from: p */
    public boolean m1815p() {
        return this.f1792a.get(3);
    }

    /* renamed from: q */
    public boolean m1816q() {
        return this.f1805k != null;
    }

    /* renamed from: r */
    public boolean m1817r() {
        return this.f1806l != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("PushMessage(");
        if (m1794a()) {
            sb.append("to:");
            C0720it c0720it = this.f1790a;
            if (c0720it == null) {
                sb.append("null");
            } else {
                sb.append(c0720it);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("id:");
        String str = this.f1791a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("appId:");
        String str2 = this.f1795b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("payload:");
        String str3 = this.f1797c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m1804e()) {
            sb.append(", ");
            sb.append("createAt:");
            sb.append(this.f1788a);
        }
        if (m1805f()) {
            sb.append(", ");
            sb.append("ttl:");
            sb.append(this.f1794b);
        }
        if (m1806g()) {
            sb.append(", ");
            sb.append("collapseKey:");
            String str4 = this.f1798d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m1807h()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f1799e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m1808i()) {
            sb.append(", ");
            sb.append("regId:");
            String str6 = this.f1800f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m1809j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f1801g;
            if (str7 == null) {
                sb.append("null");
            } else {
                sb.append(str7);
            }
        }
        if (m1810k()) {
            sb.append(", ");
            sb.append("topic:");
            String str8 = this.f1802h;
            if (str8 == null) {
                sb.append("null");
            } else {
                sb.append(str8);
            }
        }
        if (m1811l()) {
            sb.append(", ");
            sb.append("metaInfo:");
            C0717iq c0717iq = this.f1789a;
            if (c0717iq == null) {
                sb.append("null");
            } else {
                sb.append(c0717iq);
            }
        }
        if (m1812m()) {
            sb.append(", ");
            sb.append("aliasName:");
            String str9 = this.f1803i;
            if (str9 == null) {
                sb.append("null");
            } else {
                sb.append(str9);
            }
        }
        if (m1813n()) {
            sb.append(", ");
            sb.append("isOnline:");
            sb.append(this.f1793a);
        }
        if (m1814o()) {
            sb.append(", ");
            sb.append("userAccount:");
            String str10 = this.f1804j;
            if (str10 == null) {
                sb.append("null");
            } else {
                sb.append(str10);
            }
        }
        if (m1815p()) {
            sb.append(", ");
            sb.append("miid:");
            sb.append(this.f1796c);
        }
        if (m1816q()) {
            sb.append(", ");
            sb.append("imeiMd5:");
            String str11 = this.f1805k;
            if (str11 == null) {
                sb.append("null");
            } else {
                sb.append(str11);
            }
        }
        if (m1817r()) {
            sb.append(", ");
            sb.append("deviceId:");
            String str12 = this.f1806l;
            if (str12 == null) {
                sb.append("null");
            } else {
                sb.append(str12);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
