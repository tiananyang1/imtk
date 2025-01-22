package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.ii */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ii.class */
public class C0709ii implements InterfaceC0744jq<C0709ii, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public double f1662a;

    /* renamed from: a */
    public long f1663a;

    /* renamed from: a */
    public EnumC0706if f1664a;

    /* renamed from: a */
    public EnumC0710ij f1665a;

    /* renamed from: a */
    public C0711ik f1666a;

    /* renamed from: a */
    public String f1667a;

    /* renamed from: a */
    private BitSet f1668a = new BitSet(3);

    /* renamed from: a */
    public List<C0711ik> f1669a;

    /* renamed from: b */
    public long f1670b;

    /* renamed from: b */
    public String f1671b;

    /* renamed from: c */
    public String f1672c;

    /* renamed from: a */
    private static final C0761kg f1652a = new C0761kg("GeoFencing");

    /* renamed from: a */
    private static final C0752jy f1651a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f1653b = new C0752jy("", (byte) 11, 2);

    /* renamed from: c */
    private static final C0752jy f1654c = new C0752jy("", (byte) 10, 3);

    /* renamed from: d */
    private static final C0752jy f1655d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f1656e = new C0752jy("", (byte) 10, 5);

    /* renamed from: f */
    private static final C0752jy f1657f = new C0752jy("", (byte) 8, 6);

    /* renamed from: g */
    private static final C0752jy f1658g = new C0752jy("", (byte) 12, 7);

    /* renamed from: h */
    private static final C0752jy f1659h = new C0752jy("", (byte) 4, 9);

    /* renamed from: i */
    private static final C0752jy f1660i = new C0752jy("", (byte) 15, 10);

    /* renamed from: j */
    private static final C0752jy f1661j = new C0752jy("", (byte) 8, 11);

    /* renamed from: a */
    public double m1699a() {
        return this.f1662a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0709ii c0709ii) {
        int m2319a;
        int m2322a;
        int m2316a;
        int m2319a2;
        int m2319a3;
        int m2318a;
        int m2320a;
        int m2318a2;
        int m2320a2;
        int m2320a3;
        if (!getClass().equals(c0709ii.getClass())) {
            return getClass().getName().compareTo(c0709ii.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1716a()).compareTo(Boolean.valueOf(c0709ii.m1716a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1716a() && (m2320a3 = C0745jr.m2320a(this.f1667a, c0709ii.f1667a)) != 0) {
            return m2320a3;
        }
        int compareTo2 = Boolean.valueOf(m1723b()).compareTo(Boolean.valueOf(c0709ii.m1723b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1723b() && (m2320a2 = C0745jr.m2320a(this.f1671b, c0709ii.f1671b)) != 0) {
            return m2320a2;
        }
        int compareTo3 = Boolean.valueOf(m1727c()).compareTo(Boolean.valueOf(c0709ii.m1727c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1727c() && (m2318a2 = C0745jr.m2318a(this.f1663a, c0709ii.f1663a)) != 0) {
            return m2318a2;
        }
        int compareTo4 = Boolean.valueOf(m1728d()).compareTo(Boolean.valueOf(c0709ii.m1728d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1728d() && (m2320a = C0745jr.m2320a(this.f1672c, c0709ii.f1672c)) != 0) {
            return m2320a;
        }
        int compareTo5 = Boolean.valueOf(m1729e()).compareTo(Boolean.valueOf(c0709ii.m1729e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1729e() && (m2318a = C0745jr.m2318a(this.f1670b, c0709ii.f1670b)) != 0) {
            return m2318a;
        }
        int compareTo6 = Boolean.valueOf(m1730f()).compareTo(Boolean.valueOf(c0709ii.m1730f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1730f() && (m2319a3 = C0745jr.m2319a(this.f1665a, c0709ii.f1665a)) != 0) {
            return m2319a3;
        }
        int compareTo7 = Boolean.valueOf(m1731g()).compareTo(Boolean.valueOf(c0709ii.m1731g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m1731g() && (m2319a2 = C0745jr.m2319a(this.f1666a, c0709ii.f1666a)) != 0) {
            return m2319a2;
        }
        int compareTo8 = Boolean.valueOf(m1732h()).compareTo(Boolean.valueOf(c0709ii.m1732h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m1732h() && (m2316a = C0745jr.m2316a(this.f1662a, c0709ii.f1662a)) != 0) {
            return m2316a;
        }
        int compareTo9 = Boolean.valueOf(m1733i()).compareTo(Boolean.valueOf(c0709ii.m1733i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m1733i() && (m2322a = C0745jr.m2322a(this.f1669a, c0709ii.f1669a)) != 0) {
            return m2322a;
        }
        int compareTo10 = Boolean.valueOf(m1734j()).compareTo(Boolean.valueOf(c0709ii.m1734j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!m1734j() || (m2319a = C0745jr.m2319a(this.f1664a, c0709ii.f1664a)) == 0) {
            return 0;
        }
        return m2319a;
    }

    /* renamed from: a */
    public long m1701a() {
        return this.f1663a;
    }

    /* renamed from: a */
    public EnumC0706if m1702a() {
        return this.f1664a;
    }

    /* renamed from: a */
    public C0709ii m1703a(double d) {
        this.f1662a = d;
        m1726c(true);
        return this;
    }

    /* renamed from: a */
    public C0709ii m1704a(long j) {
        this.f1663a = j;
        m1715a(true);
        return this;
    }

    /* renamed from: a */
    public C0709ii m1705a(EnumC0706if enumC0706if) {
        this.f1664a = enumC0706if;
        return this;
    }

    /* renamed from: a */
    public C0709ii m1706a(EnumC0710ij enumC0710ij) {
        this.f1665a = enumC0710ij;
        return this;
    }

    /* renamed from: a */
    public C0709ii m1707a(C0711ik c0711ik) {
        this.f1666a = c0711ik;
        return this;
    }

    /* renamed from: a */
    public C0709ii m1708a(String str) {
        this.f1667a = str;
        return this;
    }

    /* renamed from: a */
    public C0709ii m1709a(List<C0711ik> list) {
        this.f1669a = list;
        return this;
    }

    /* renamed from: a */
    public EnumC0710ij m1710a() {
        return this.f1665a;
    }

    /* renamed from: a */
    public C0711ik m1711a() {
        return this.f1666a;
    }

    /* renamed from: a */
    public String m1712a() {
        return this.f1667a;
    }

    /* renamed from: a */
    public List<C0711ik> m1713a() {
        return this.f1669a;
    }

    /* renamed from: a */
    public void m1714a() {
        if (this.f1667a == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f1671b == null) {
            throw new C0757kc("Required field 'name' was not present! Struct: " + toString());
        }
        if (this.f1672c == null) {
            throw new C0757kc("Required field 'packageName' was not present! Struct: " + toString());
        }
        if (this.f1665a == null) {
            throw new C0757kc("Required field 'type' was not present! Struct: " + toString());
        }
        if (this.f1664a != null) {
            return;
        }
        throw new C0757kc("Required field 'coordinateProvider' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                if (!m1727c()) {
                    throw new C0757kc("Required field 'appId' was not found in serialized data! Struct: " + toString());
                }
                if (m1729e()) {
                    m1714a();
                    return;
                }
                throw new C0757kc("Required field 'createTime' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f1667a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 11) {
                        this.f1671b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 10) {
                        this.f1663a = abstractC0756kb.mo2341a();
                        m1715a(true);
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f1672c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 10) {
                        this.f1670b = abstractC0756kb.mo2341a();
                        m1722b(true);
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 8) {
                        this.f1665a = EnumC0710ij.m1735a(abstractC0756kb.mo2340a());
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 12) {
                        this.f1666a = new C0711ik();
                        this.f1666a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 4) {
                        this.f1662a = abstractC0756kb.mo2339a();
                        m1726c(true);
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 15) {
                        C0753jz mo2343a = abstractC0756kb.mo2343a();
                        this.f1669a = new ArrayList(mo2343a.f2328a);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2343a.f2328a) {
                                abstractC0756kb.mo2376j();
                                break;
                            } else {
                                C0711ik c0711ik = new C0711ik();
                                c0711ik.mo1287a(abstractC0756kb);
                                this.f1669a.add(c0711ik);
                                i = i2 + 1;
                            }
                        }
                    }
                    break;
                case 11:
                    if (mo2342a.f2324a == 8) {
                        this.f1664a = EnumC0706if.m1673a(abstractC0756kb.mo2340a());
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m1715a(boolean z) {
        this.f1668a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1716a() {
        return this.f1667a != null;
    }

    /* renamed from: a */
    public boolean m1717a(C0709ii c0709ii) {
        if (c0709ii == null) {
            return false;
        }
        boolean m1716a = m1716a();
        boolean m1716a2 = c0709ii.m1716a();
        if ((m1716a || m1716a2) && !(m1716a && m1716a2 && this.f1667a.equals(c0709ii.f1667a))) {
            return false;
        }
        boolean m1723b = m1723b();
        boolean m1723b2 = c0709ii.m1723b();
        if (((m1723b || m1723b2) && !(m1723b && m1723b2 && this.f1671b.equals(c0709ii.f1671b))) || this.f1663a != c0709ii.f1663a) {
            return false;
        }
        boolean m1728d = m1728d();
        boolean m1728d2 = c0709ii.m1728d();
        if (((m1728d || m1728d2) && !(m1728d && m1728d2 && this.f1672c.equals(c0709ii.f1672c))) || this.f1670b != c0709ii.f1670b) {
            return false;
        }
        boolean m1730f = m1730f();
        boolean m1730f2 = c0709ii.m1730f();
        if ((m1730f || m1730f2) && !(m1730f && m1730f2 && this.f1665a.equals(c0709ii.f1665a))) {
            return false;
        }
        boolean m1731g = m1731g();
        boolean m1731g2 = c0709ii.m1731g();
        if ((m1731g || m1731g2) && !(m1731g && m1731g2 && this.f1666a.m1743a(c0709ii.f1666a))) {
            return false;
        }
        boolean m1732h = m1732h();
        boolean m1732h2 = c0709ii.m1732h();
        if ((m1732h || m1732h2) && !(m1732h && m1732h2 && this.f1662a == c0709ii.f1662a)) {
            return false;
        }
        boolean m1733i = m1733i();
        boolean m1733i2 = c0709ii.m1733i();
        if ((m1733i || m1733i2) && !(m1733i && m1733i2 && this.f1669a.equals(c0709ii.f1669a))) {
            return false;
        }
        boolean m1734j = m1734j();
        boolean m1734j2 = c0709ii.m1734j();
        if (m1734j || m1734j2) {
            return m1734j && m1734j2 && this.f1664a.equals(c0709ii.f1664a);
        }
        return true;
    }

    /* renamed from: b */
    public long m1718b() {
        return this.f1670b;
    }

    /* renamed from: b */
    public C0709ii m1719b(long j) {
        this.f1670b = j;
        m1722b(true);
        return this;
    }

    /* renamed from: b */
    public C0709ii m1720b(String str) {
        this.f1671b = str;
        return this;
    }

    /* renamed from: b */
    public String m1721b() {
        return this.f1671b;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1714a();
        abstractC0756kb.mo2360a(f1652a);
        if (this.f1667a != null) {
            abstractC0756kb.mo2356a(f1651a);
            abstractC0756kb.mo2361a(this.f1667a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1671b != null) {
            abstractC0756kb.mo2356a(f1653b);
            abstractC0756kb.mo2361a(this.f1671b);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f1654c);
        abstractC0756kb.mo2355a(this.f1663a);
        abstractC0756kb.mo2366b();
        if (this.f1672c != null) {
            abstractC0756kb.mo2356a(f1655d);
            abstractC0756kb.mo2361a(this.f1672c);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f1656e);
        abstractC0756kb.mo2355a(this.f1670b);
        abstractC0756kb.mo2366b();
        if (this.f1665a != null) {
            abstractC0756kb.mo2356a(f1657f);
            abstractC0756kb.mo2354a(this.f1665a.m1736a());
            abstractC0756kb.mo2366b();
        }
        if (this.f1666a != null && m1731g()) {
            abstractC0756kb.mo2356a(f1658g);
            this.f1666a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (m1732h()) {
            abstractC0756kb.mo2356a(f1659h);
            abstractC0756kb.mo2353a(this.f1662a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1669a != null && m1733i()) {
            abstractC0756kb.mo2356a(f1660i);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f1669a.size()));
            Iterator<C0711ik> it = this.f1669a.iterator();
            while (it.hasNext()) {
                it.next().mo1293b(abstractC0756kb);
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        if (this.f1664a != null) {
            abstractC0756kb.mo2356a(f1661j);
            abstractC0756kb.mo2354a(this.f1664a.m1674a());
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1722b(boolean z) {
        this.f1668a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1723b() {
        return this.f1671b != null;
    }

    /* renamed from: c */
    public C0709ii m1724c(String str) {
        this.f1672c = str;
        return this;
    }

    /* renamed from: c */
    public String m1725c() {
        return this.f1672c;
    }

    /* renamed from: c */
    public void m1726c(boolean z) {
        this.f1668a.set(2, z);
    }

    /* renamed from: c */
    public boolean m1727c() {
        return this.f1668a.get(0);
    }

    /* renamed from: d */
    public boolean m1728d() {
        return this.f1672c != null;
    }

    /* renamed from: e */
    public boolean m1729e() {
        return this.f1668a.get(1);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0709ii)) {
            return m1717a((C0709ii) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m1730f() {
        return this.f1665a != null;
    }

    /* renamed from: g */
    public boolean m1731g() {
        return this.f1666a != null;
    }

    /* renamed from: h */
    public boolean m1732h() {
        return this.f1668a.get(2);
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m1733i() {
        return this.f1669a != null;
    }

    /* renamed from: j */
    public boolean m1734j() {
        return this.f1664a != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GeoFencing(");
        sb.append("id:");
        String str = this.f1667a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("name:");
        String str2 = this.f1671b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("appId:");
        sb.append(this.f1663a);
        sb.append(", ");
        sb.append("packageName:");
        String str3 = this.f1672c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("createTime:");
        sb.append(this.f1670b);
        sb.append(", ");
        sb.append("type:");
        EnumC0710ij enumC0710ij = this.f1665a;
        if (enumC0710ij == null) {
            sb.append("null");
        } else {
            sb.append(enumC0710ij);
        }
        if (m1731g()) {
            sb.append(", ");
            sb.append("circleCenter:");
            C0711ik c0711ik = this.f1666a;
            if (c0711ik == null) {
                sb.append("null");
            } else {
                sb.append(c0711ik);
            }
        }
        if (m1732h()) {
            sb.append(", ");
            sb.append("circleRadius:");
            sb.append(this.f1662a);
        }
        if (m1733i()) {
            sb.append(", ");
            sb.append("polygonPoints:");
            List<C0711ik> list = this.f1669a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        sb.append(", ");
        sb.append("coordinateProvider:");
        EnumC0706if enumC0706if = this.f1664a;
        if (enumC0706if == null) {
            sb.append("null");
        } else {
            sb.append(enumC0706if);
        }
        sb.append(")");
        return sb.toString();
    }
}
