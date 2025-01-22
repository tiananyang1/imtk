package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.ib */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ib.class */
public class C0702ib implements InterfaceC0744jq<C0702ib, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f1507a;

    /* renamed from: a */
    public String f1508a;

    /* renamed from: a */
    private BitSet f1509a = new BitSet(3);

    /* renamed from: a */
    public Map<String, String> f1510a;

    /* renamed from: a */
    public boolean f1511a;

    /* renamed from: b */
    public long f1512b;

    /* renamed from: b */
    public String f1513b;

    /* renamed from: c */
    public String f1514c;

    /* renamed from: d */
    public String f1515d;

    /* renamed from: e */
    public String f1516e;

    /* renamed from: f */
    public String f1517f;

    /* renamed from: g */
    public String f1518g;

    /* renamed from: a */
    private static final C0761kg f1496a = new C0761kg("ClientUploadDataItem");

    /* renamed from: a */
    private static final C0752jy f1495a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f1497b = new C0752jy("", (byte) 11, 2);

    /* renamed from: c */
    private static final C0752jy f1498c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f1499d = new C0752jy("", (byte) 10, 4);

    /* renamed from: e */
    private static final C0752jy f1500e = new C0752jy("", (byte) 10, 5);

    /* renamed from: f */
    private static final C0752jy f1501f = new C0752jy("", (byte) 2, 6);

    /* renamed from: g */
    private static final C0752jy f1502g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f1503h = new C0752jy("", (byte) 11, 8);

    /* renamed from: i */
    private static final C0752jy f1504i = new C0752jy("", (byte) 11, 9);

    /* renamed from: j */
    private static final C0752jy f1505j = new C0752jy("", (byte) 13, 10);

    /* renamed from: k */
    private static final C0752jy f1506k = new C0752jy("", (byte) 11, 11);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0702ib c0702ib) {
        int m2320a;
        int m2323a;
        int m2320a2;
        int m2320a3;
        int m2320a4;
        int m2326a;
        int m2318a;
        int m2318a2;
        int m2320a5;
        int m2320a6;
        int m2320a7;
        if (!getClass().equals(c0702ib.getClass())) {
            return getClass().getName().compareTo(c0702ib.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1644a()).compareTo(Boolean.valueOf(c0702ib.m1644a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1644a() && (m2320a7 = C0745jr.m2320a(this.f1508a, c0702ib.f1508a)) != 0) {
            return m2320a7;
        }
        int compareTo2 = Boolean.valueOf(m1650b()).compareTo(Boolean.valueOf(c0702ib.m1650b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1650b() && (m2320a6 = C0745jr.m2320a(this.f1513b, c0702ib.f1513b)) != 0) {
            return m2320a6;
        }
        int compareTo3 = Boolean.valueOf(m1654c()).compareTo(Boolean.valueOf(c0702ib.m1654c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1654c() && (m2320a5 = C0745jr.m2320a(this.f1514c, c0702ib.f1514c)) != 0) {
            return m2320a5;
        }
        int compareTo4 = Boolean.valueOf(m1657d()).compareTo(Boolean.valueOf(c0702ib.m1657d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1657d() && (m2318a2 = C0745jr.m2318a(this.f1507a, c0702ib.f1507a)) != 0) {
            return m2318a2;
        }
        int compareTo5 = Boolean.valueOf(m1660e()).compareTo(Boolean.valueOf(c0702ib.m1660e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1660e() && (m2318a = C0745jr.m2318a(this.f1512b, c0702ib.f1512b)) != 0) {
            return m2318a;
        }
        int compareTo6 = Boolean.valueOf(m1662f()).compareTo(Boolean.valueOf(c0702ib.m1662f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1662f() && (m2326a = C0745jr.m2326a(this.f1511a, c0702ib.f1511a)) != 0) {
            return m2326a;
        }
        int compareTo7 = Boolean.valueOf(m1664g()).compareTo(Boolean.valueOf(c0702ib.m1664g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m1664g() && (m2320a4 = C0745jr.m2320a(this.f1515d, c0702ib.f1515d)) != 0) {
            return m2320a4;
        }
        int compareTo8 = Boolean.valueOf(m1665h()).compareTo(Boolean.valueOf(c0702ib.m1665h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m1665h() && (m2320a3 = C0745jr.m2320a(this.f1516e, c0702ib.f1516e)) != 0) {
            return m2320a3;
        }
        int compareTo9 = Boolean.valueOf(m1666i()).compareTo(Boolean.valueOf(c0702ib.m1666i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m1666i() && (m2320a2 = C0745jr.m2320a(this.f1517f, c0702ib.f1517f)) != 0) {
            return m2320a2;
        }
        int compareTo10 = Boolean.valueOf(m1667j()).compareTo(Boolean.valueOf(c0702ib.m1667j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (m1667j() && (m2323a = C0745jr.m2323a(this.f1510a, c0702ib.f1510a)) != 0) {
            return m2323a;
        }
        int compareTo11 = Boolean.valueOf(m1668k()).compareTo(Boolean.valueOf(c0702ib.m1668k()));
        if (compareTo11 != 0) {
            return compareTo11;
        }
        if (!m1668k() || (m2320a = C0745jr.m2320a(this.f1518g, c0702ib.f1518g)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public long m1637a() {
        return this.f1512b;
    }

    /* renamed from: a */
    public C0702ib m1638a(long j) {
        this.f1507a = j;
        m1643a(true);
        return this;
    }

    /* renamed from: a */
    public C0702ib m1639a(String str) {
        this.f1508a = str;
        return this;
    }

    /* renamed from: a */
    public C0702ib m1640a(boolean z) {
        this.f1511a = z;
        m1653c(true);
        return this;
    }

    /* renamed from: a */
    public String m1641a() {
        return this.f1508a;
    }

    /* renamed from: a */
    public void m1642a() {
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1642a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f1508a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 11) {
                        this.f1513b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f1514c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 10) {
                        this.f1507a = abstractC0756kb.mo2341a();
                        m1643a(true);
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 10) {
                        this.f1512b = abstractC0756kb.mo2341a();
                        m1649b(true);
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 2) {
                        this.f1511a = abstractC0756kb.mo2365a();
                        m1653c(true);
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f1515d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f1516e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f1517f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 13) {
                        C0755ka mo2344a = abstractC0756kb.mo2344a();
                        this.f1510a = new HashMap(mo2344a.f2335a * 2);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2344a.f2335a) {
                                abstractC0756kb.mo2375i();
                                break;
                            } else {
                                this.f1510a.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
                                i = i2 + 1;
                            }
                        }
                    }
                    break;
                case 11:
                    if (mo2342a.f2324a == 11) {
                        this.f1518g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m1643a(boolean z) {
        this.f1509a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1644a() {
        return this.f1508a != null;
    }

    /* renamed from: a */
    public boolean m1645a(C0702ib c0702ib) {
        if (c0702ib == null) {
            return false;
        }
        boolean m1644a = m1644a();
        boolean m1644a2 = c0702ib.m1644a();
        if ((m1644a || m1644a2) && !(m1644a && m1644a2 && this.f1508a.equals(c0702ib.f1508a))) {
            return false;
        }
        boolean m1650b = m1650b();
        boolean m1650b2 = c0702ib.m1650b();
        if ((m1650b || m1650b2) && !(m1650b && m1650b2 && this.f1513b.equals(c0702ib.f1513b))) {
            return false;
        }
        boolean m1654c = m1654c();
        boolean m1654c2 = c0702ib.m1654c();
        if ((m1654c || m1654c2) && !(m1654c && m1654c2 && this.f1514c.equals(c0702ib.f1514c))) {
            return false;
        }
        boolean m1657d = m1657d();
        boolean m1657d2 = c0702ib.m1657d();
        if ((m1657d || m1657d2) && !(m1657d && m1657d2 && this.f1507a == c0702ib.f1507a)) {
            return false;
        }
        boolean m1660e = m1660e();
        boolean m1660e2 = c0702ib.m1660e();
        if ((m1660e || m1660e2) && !(m1660e && m1660e2 && this.f1512b == c0702ib.f1512b)) {
            return false;
        }
        boolean m1662f = m1662f();
        boolean m1662f2 = c0702ib.m1662f();
        if ((m1662f || m1662f2) && !(m1662f && m1662f2 && this.f1511a == c0702ib.f1511a)) {
            return false;
        }
        boolean m1664g = m1664g();
        boolean m1664g2 = c0702ib.m1664g();
        if ((m1664g || m1664g2) && !(m1664g && m1664g2 && this.f1515d.equals(c0702ib.f1515d))) {
            return false;
        }
        boolean m1665h = m1665h();
        boolean m1665h2 = c0702ib.m1665h();
        if ((m1665h || m1665h2) && !(m1665h && m1665h2 && this.f1516e.equals(c0702ib.f1516e))) {
            return false;
        }
        boolean m1666i = m1666i();
        boolean m1666i2 = c0702ib.m1666i();
        if ((m1666i || m1666i2) && !(m1666i && m1666i2 && this.f1517f.equals(c0702ib.f1517f))) {
            return false;
        }
        boolean m1667j = m1667j();
        boolean m1667j2 = c0702ib.m1667j();
        if ((m1667j || m1667j2) && !(m1667j && m1667j2 && this.f1510a.equals(c0702ib.f1510a))) {
            return false;
        }
        boolean m1668k = m1668k();
        boolean m1668k2 = c0702ib.m1668k();
        if (m1668k || m1668k2) {
            return m1668k && m1668k2 && this.f1518g.equals(c0702ib.f1518g);
        }
        return true;
    }

    /* renamed from: b */
    public C0702ib m1646b(long j) {
        this.f1512b = j;
        m1649b(true);
        return this;
    }

    /* renamed from: b */
    public C0702ib m1647b(String str) {
        this.f1513b = str;
        return this;
    }

    /* renamed from: b */
    public String m1648b() {
        return this.f1514c;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1642a();
        abstractC0756kb.mo2360a(f1496a);
        if (this.f1508a != null && m1644a()) {
            abstractC0756kb.mo2356a(f1495a);
            abstractC0756kb.mo2361a(this.f1508a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1513b != null && m1650b()) {
            abstractC0756kb.mo2356a(f1497b);
            abstractC0756kb.mo2361a(this.f1513b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1514c != null && m1654c()) {
            abstractC0756kb.mo2356a(f1498c);
            abstractC0756kb.mo2361a(this.f1514c);
            abstractC0756kb.mo2366b();
        }
        if (m1657d()) {
            abstractC0756kb.mo2356a(f1499d);
            abstractC0756kb.mo2355a(this.f1507a);
            abstractC0756kb.mo2366b();
        }
        if (m1660e()) {
            abstractC0756kb.mo2356a(f1500e);
            abstractC0756kb.mo2355a(this.f1512b);
            abstractC0756kb.mo2366b();
        }
        if (m1662f()) {
            abstractC0756kb.mo2356a(f1501f);
            abstractC0756kb.mo2364a(this.f1511a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1515d != null && m1664g()) {
            abstractC0756kb.mo2356a(f1502g);
            abstractC0756kb.mo2361a(this.f1515d);
            abstractC0756kb.mo2366b();
        }
        if (this.f1516e != null && m1665h()) {
            abstractC0756kb.mo2356a(f1503h);
            abstractC0756kb.mo2361a(this.f1516e);
            abstractC0756kb.mo2366b();
        }
        if (this.f1517f != null && m1666i()) {
            abstractC0756kb.mo2356a(f1504i);
            abstractC0756kb.mo2361a(this.f1517f);
            abstractC0756kb.mo2366b();
        }
        if (this.f1510a != null && m1667j()) {
            abstractC0756kb.mo2356a(f1505j);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f1510a.size()));
            for (Map.Entry<String, String> entry : this.f1510a.entrySet()) {
                abstractC0756kb.mo2361a(entry.getKey());
                abstractC0756kb.mo2361a(entry.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        if (this.f1518g != null && m1668k()) {
            abstractC0756kb.mo2356a(f1506k);
            abstractC0756kb.mo2361a(this.f1518g);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1649b(boolean z) {
        this.f1509a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1650b() {
        return this.f1513b != null;
    }

    /* renamed from: c */
    public C0702ib m1651c(String str) {
        this.f1514c = str;
        return this;
    }

    /* renamed from: c */
    public String m1652c() {
        return this.f1516e;
    }

    /* renamed from: c */
    public void m1653c(boolean z) {
        this.f1509a.set(2, z);
    }

    /* renamed from: c */
    public boolean m1654c() {
        return this.f1514c != null;
    }

    /* renamed from: d */
    public C0702ib m1655d(String str) {
        this.f1515d = str;
        return this;
    }

    /* renamed from: d */
    public String m1656d() {
        return this.f1517f;
    }

    /* renamed from: d */
    public boolean m1657d() {
        return this.f1509a.get(0);
    }

    /* renamed from: e */
    public C0702ib m1658e(String str) {
        this.f1516e = str;
        return this;
    }

    /* renamed from: e */
    public String m1659e() {
        return this.f1518g;
    }

    /* renamed from: e */
    public boolean m1660e() {
        return this.f1509a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0702ib)) {
            return m1645a((C0702ib) obj);
        }
        return false;
    }

    /* renamed from: f */
    public C0702ib m1661f(String str) {
        this.f1517f = str;
        return this;
    }

    /* renamed from: f */
    public boolean m1662f() {
        return this.f1509a.get(2);
    }

    /* renamed from: g */
    public C0702ib m1663g(String str) {
        this.f1518g = str;
        return this;
    }

    /* renamed from: g */
    public boolean m1664g() {
        return this.f1515d != null;
    }

    /* renamed from: h */
    public boolean m1665h() {
        return this.f1516e != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m1666i() {
        return this.f1517f != null;
    }

    /* renamed from: j */
    public boolean m1667j() {
        return this.f1510a != null;
    }

    /* renamed from: k */
    public boolean m1668k() {
        return this.f1518g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ClientUploadDataItem(");
        if (m1644a()) {
            sb.append("channel:");
            String str = this.f1508a;
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
        if (m1650b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("data:");
            String str2 = this.f1513b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
            z2 = false;
        }
        boolean z3 = z2;
        if (m1654c()) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append("name:");
            String str3 = this.f1514c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
            z3 = false;
        }
        boolean z4 = z3;
        if (m1657d()) {
            if (!z3) {
                sb.append(", ");
            }
            sb.append("counter:");
            sb.append(this.f1507a);
            z4 = false;
        }
        boolean z5 = z4;
        if (m1660e()) {
            if (!z4) {
                sb.append(", ");
            }
            sb.append("timestamp:");
            sb.append(this.f1512b);
            z5 = false;
        }
        boolean z6 = z5;
        if (m1662f()) {
            if (!z5) {
                sb.append(", ");
            }
            sb.append("fromSdk:");
            sb.append(this.f1511a);
            z6 = false;
        }
        boolean z7 = z6;
        if (m1664g()) {
            if (!z6) {
                sb.append(", ");
            }
            sb.append("category:");
            String str4 = this.f1515d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
            z7 = false;
        }
        boolean z8 = z7;
        if (m1665h()) {
            if (!z7) {
                sb.append(", ");
            }
            sb.append("sourcePackage:");
            String str5 = this.f1516e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
            z8 = false;
        }
        boolean z9 = z8;
        if (m1666i()) {
            if (!z8) {
                sb.append(", ");
            }
            sb.append("id:");
            String str6 = this.f1517f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
            z9 = false;
        }
        boolean z10 = z9;
        if (m1667j()) {
            if (!z9) {
                sb.append(", ");
            }
            sb.append("extra:");
            Map<String, String> map = this.f1510a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
            z10 = false;
        }
        if (m1668k()) {
            if (!z10) {
                sb.append(", ");
            }
            sb.append("pkgName:");
            String str7 = this.f1518g;
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
