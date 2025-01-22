package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.io */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/io.class */
public class C0715io implements InterfaceC0744jq<C0715io, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public int f1761a;

    /* renamed from: a */
    public long f1762a;

    /* renamed from: a */
    public String f1763a;

    /* renamed from: a */
    private BitSet f1764a = new BitSet(6);

    /* renamed from: a */
    public boolean f1765a;

    /* renamed from: b */
    public int f1766b;

    /* renamed from: b */
    public boolean f1767b;

    /* renamed from: c */
    public int f1768c;

    /* renamed from: a */
    private static final C0761kg f1754a = new C0761kg("OnlineConfigItem");

    /* renamed from: a */
    private static final C0752jy f1753a = new C0752jy("", (byte) 8, 1);

    /* renamed from: b */
    private static final C0752jy f1755b = new C0752jy("", (byte) 8, 2);

    /* renamed from: c */
    private static final C0752jy f1756c = new C0752jy("", (byte) 2, 3);

    /* renamed from: d */
    private static final C0752jy f1757d = new C0752jy("", (byte) 8, 4);

    /* renamed from: e */
    private static final C0752jy f1758e = new C0752jy("", (byte) 10, 5);

    /* renamed from: f */
    private static final C0752jy f1759f = new C0752jy("", (byte) 11, 6);

    /* renamed from: g */
    private static final C0752jy f1760g = new C0752jy("", (byte) 2, 7);

    /* renamed from: a */
    public int m1767a() {
        return this.f1761a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0715io c0715io) {
        int m2326a;
        int m2320a;
        int m2318a;
        int m2317a;
        int m2326a2;
        int m2317a2;
        int m2317a3;
        if (!getClass().equals(c0715io.getClass())) {
            return getClass().getName().compareTo(c0715io.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1773a()).compareTo(Boolean.valueOf(c0715io.m1773a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1773a() && (m2317a3 = C0745jr.m2317a(this.f1761a, c0715io.f1761a)) != 0) {
            return m2317a3;
        }
        int compareTo2 = Boolean.valueOf(m1777b()).compareTo(Boolean.valueOf(c0715io.m1777b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1777b() && (m2317a2 = C0745jr.m2317a(this.f1766b, c0715io.f1766b)) != 0) {
            return m2317a2;
        }
        int compareTo3 = Boolean.valueOf(m1780c()).compareTo(Boolean.valueOf(c0715io.m1780c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1780c() && (m2326a2 = C0745jr.m2326a(this.f1765a, c0715io.f1765a)) != 0) {
            return m2326a2;
        }
        int compareTo4 = Boolean.valueOf(m1782d()).compareTo(Boolean.valueOf(c0715io.m1782d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1782d() && (m2317a = C0745jr.m2317a(this.f1768c, c0715io.f1768c)) != 0) {
            return m2317a;
        }
        int compareTo5 = Boolean.valueOf(m1784e()).compareTo(Boolean.valueOf(c0715io.m1784e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1784e() && (m2318a = C0745jr.m2318a(this.f1762a, c0715io.f1762a)) != 0) {
            return m2318a;
        }
        int compareTo6 = Boolean.valueOf(m1786f()).compareTo(Boolean.valueOf(c0715io.m1786f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1786f() && (m2320a = C0745jr.m2320a(this.f1763a, c0715io.f1763a)) != 0) {
            return m2320a;
        }
        int compareTo7 = Boolean.valueOf(m1788h()).compareTo(Boolean.valueOf(c0715io.m1788h()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (!m1788h() || (m2326a = C0745jr.m2326a(this.f1767b, c0715io.f1767b)) == 0) {
            return 0;
        }
        return m2326a;
    }

    /* renamed from: a */
    public long m1769a() {
        return this.f1762a;
    }

    /* renamed from: a */
    public String m1770a() {
        return this.f1763a;
    }

    /* renamed from: a */
    public void m1771a() {
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1771a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 8) {
                        this.f1761a = abstractC0756kb.mo2340a();
                        m1772a(true);
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 8) {
                        this.f1766b = abstractC0756kb.mo2340a();
                        m1776b(true);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 2) {
                        this.f1765a = abstractC0756kb.mo2365a();
                        m1779c(true);
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 8) {
                        this.f1768c = abstractC0756kb.mo2340a();
                        m1781d(true);
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 10) {
                        this.f1762a = abstractC0756kb.mo2341a();
                        m1783e(true);
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 11) {
                        this.f1763a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 2) {
                        this.f1767b = abstractC0756kb.mo2365a();
                        m1785f(true);
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m1772a(boolean z) {
        this.f1764a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1773a() {
        return this.f1764a.get(0);
    }

    /* renamed from: a */
    public boolean m1774a(C0715io c0715io) {
        if (c0715io == null) {
            return false;
        }
        boolean m1773a = m1773a();
        boolean m1773a2 = c0715io.m1773a();
        if ((m1773a || m1773a2) && !(m1773a && m1773a2 && this.f1761a == c0715io.f1761a)) {
            return false;
        }
        boolean m1777b = m1777b();
        boolean m1777b2 = c0715io.m1777b();
        if ((m1777b || m1777b2) && !(m1777b && m1777b2 && this.f1766b == c0715io.f1766b)) {
            return false;
        }
        boolean m1780c = m1780c();
        boolean m1780c2 = c0715io.m1780c();
        if ((m1780c || m1780c2) && !(m1780c && m1780c2 && this.f1765a == c0715io.f1765a)) {
            return false;
        }
        boolean m1782d = m1782d();
        boolean m1782d2 = c0715io.m1782d();
        if ((m1782d || m1782d2) && !(m1782d && m1782d2 && this.f1768c == c0715io.f1768c)) {
            return false;
        }
        boolean m1784e = m1784e();
        boolean m1784e2 = c0715io.m1784e();
        if ((m1784e || m1784e2) && !(m1784e && m1784e2 && this.f1762a == c0715io.f1762a)) {
            return false;
        }
        boolean m1786f = m1786f();
        boolean m1786f2 = c0715io.m1786f();
        if ((m1786f || m1786f2) && !(m1786f && m1786f2 && this.f1763a.equals(c0715io.f1763a))) {
            return false;
        }
        boolean m1788h = m1788h();
        boolean m1788h2 = c0715io.m1788h();
        if (m1788h || m1788h2) {
            return m1788h && m1788h2 && this.f1767b == c0715io.f1767b;
        }
        return true;
    }

    /* renamed from: b */
    public int m1775b() {
        return this.f1766b;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1771a();
        abstractC0756kb.mo2360a(f1754a);
        if (m1773a()) {
            abstractC0756kb.mo2356a(f1753a);
            abstractC0756kb.mo2354a(this.f1761a);
            abstractC0756kb.mo2366b();
        }
        if (m1777b()) {
            abstractC0756kb.mo2356a(f1755b);
            abstractC0756kb.mo2354a(this.f1766b);
            abstractC0756kb.mo2366b();
        }
        if (m1780c()) {
            abstractC0756kb.mo2356a(f1756c);
            abstractC0756kb.mo2364a(this.f1765a);
            abstractC0756kb.mo2366b();
        }
        if (m1782d()) {
            abstractC0756kb.mo2356a(f1757d);
            abstractC0756kb.mo2354a(this.f1768c);
            abstractC0756kb.mo2366b();
        }
        if (m1784e()) {
            abstractC0756kb.mo2356a(f1758e);
            abstractC0756kb.mo2355a(this.f1762a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1763a != null && m1786f()) {
            abstractC0756kb.mo2356a(f1759f);
            abstractC0756kb.mo2361a(this.f1763a);
            abstractC0756kb.mo2366b();
        }
        if (m1788h()) {
            abstractC0756kb.mo2356a(f1760g);
            abstractC0756kb.mo2364a(this.f1767b);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1776b(boolean z) {
        this.f1764a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1777b() {
        return this.f1764a.get(1);
    }

    /* renamed from: c */
    public int m1778c() {
        return this.f1768c;
    }

    /* renamed from: c */
    public void m1779c(boolean z) {
        this.f1764a.set(2, z);
    }

    /* renamed from: c */
    public boolean m1780c() {
        return this.f1764a.get(2);
    }

    /* renamed from: d */
    public void m1781d(boolean z) {
        this.f1764a.set(3, z);
    }

    /* renamed from: d */
    public boolean m1782d() {
        return this.f1764a.get(3);
    }

    /* renamed from: e */
    public void m1783e(boolean z) {
        this.f1764a.set(4, z);
    }

    /* renamed from: e */
    public boolean m1784e() {
        return this.f1764a.get(4);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0715io)) {
            return m1774a((C0715io) obj);
        }
        return false;
    }

    /* renamed from: f */
    public void m1785f(boolean z) {
        this.f1764a.set(5, z);
    }

    /* renamed from: f */
    public boolean m1786f() {
        return this.f1763a != null;
    }

    /* renamed from: g */
    public boolean m1787g() {
        return this.f1767b;
    }

    /* renamed from: h */
    public boolean m1788h() {
        return this.f1764a.get(5);
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("OnlineConfigItem(");
        if (m1773a()) {
            sb.append("key:");
            sb.append(this.f1761a);
            z = false;
        } else {
            z = true;
        }
        boolean z2 = z;
        if (m1777b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("type:");
            sb.append(this.f1766b);
            z2 = false;
        }
        boolean z3 = z2;
        if (m1780c()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("clear:");
            sb.append(this.f1765a);
            z3 = false;
        }
        boolean z4 = z3;
        if (m1782d()) {
            if (!z3) {
                sb.append(", ");
            }
            sb.append("intValue:");
            sb.append(this.f1768c);
            z4 = false;
        }
        boolean z5 = z4;
        if (m1784e()) {
            if (!z4) {
                sb.append(", ");
            }
            sb.append("longValue:");
            sb.append(this.f1762a);
            z5 = false;
        }
        boolean z6 = z5;
        if (m1786f()) {
            if (!z5) {
                sb.append(", ");
            }
            sb.append("stringValue:");
            String str = this.f1763a;
            String str2 = str;
            if (str == null) {
                str2 = "null";
            }
            sb.append(str2);
            z6 = false;
        }
        if (m1788h()) {
            if (!z6) {
                sb.append(", ");
            }
            sb.append("boolValue:");
            sb.append(this.f1767b);
        }
        sb.append(")");
        return sb.toString();
    }
}
