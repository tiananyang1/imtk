package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.ja */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ja.class */
public class C0728ja implements InterfaceC0744jq<C0728ja, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f1977a;

    /* renamed from: a */
    public C0720it f1978a;

    /* renamed from: a */
    public String f1979a;

    /* renamed from: a */
    public List<String> f1981a;

    /* renamed from: b */
    public String f1983b;

    /* renamed from: c */
    public String f1984c;

    /* renamed from: d */
    public String f1985d;

    /* renamed from: e */
    public String f1986e;

    /* renamed from: f */
    public String f1987f;

    /* renamed from: a */
    private static final C0761kg f1967a = new C0761kg("XmPushActionCommandResult");

    /* renamed from: a */
    private static final C0752jy f1966a = new C0752jy("", (byte) 12, 2);

    /* renamed from: b */
    private static final C0752jy f1968b = new C0752jy("", (byte) 11, 3);

    /* renamed from: c */
    private static final C0752jy f1969c = new C0752jy("", (byte) 11, 4);

    /* renamed from: d */
    private static final C0752jy f1970d = new C0752jy("", (byte) 11, 5);

    /* renamed from: e */
    private static final C0752jy f1971e = new C0752jy("", (byte) 10, 7);

    /* renamed from: f */
    private static final C0752jy f1972f = new C0752jy("", (byte) 11, 8);

    /* renamed from: g */
    private static final C0752jy f1973g = new C0752jy("", (byte) 11, 9);

    /* renamed from: h */
    private static final C0752jy f1974h = new C0752jy("", (byte) 15, 10);

    /* renamed from: i */
    private static final C0752jy f1975i = new C0752jy("", (byte) 11, 12);

    /* renamed from: j */
    private static final C0752jy f1976j = new C0752jy("", (byte) 2, 13);

    /* renamed from: a */
    private BitSet f1980a = new BitSet(2);

    /* renamed from: a */
    public boolean f1982a = true;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0728ja c0728ja) {
        int m2326a;
        int m2320a;
        int m2322a;
        int m2320a2;
        int m2320a3;
        int m2318a;
        int m2320a4;
        int m2320a5;
        int m2320a6;
        int m2319a;
        if (!getClass().equals(c0728ja.getClass())) {
            return getClass().getName().compareTo(c0728ja.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2007a()).compareTo(Boolean.valueOf(c0728ja.m2007a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2007a() && (m2319a = C0745jr.m2319a(this.f1978a, c0728ja.f1978a)) != 0) {
            return m2319a;
        }
        int compareTo2 = Boolean.valueOf(m2011b()).compareTo(Boolean.valueOf(c0728ja.m2011b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2011b() && (m2320a6 = C0745jr.m2320a(this.f1979a, c0728ja.f1979a)) != 0) {
            return m2320a6;
        }
        int compareTo3 = Boolean.valueOf(m2012c()).compareTo(Boolean.valueOf(c0728ja.m2012c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2012c() && (m2320a5 = C0745jr.m2320a(this.f1983b, c0728ja.f1983b)) != 0) {
            return m2320a5;
        }
        int compareTo4 = Boolean.valueOf(m2013d()).compareTo(Boolean.valueOf(c0728ja.m2013d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2013d() && (m2320a4 = C0745jr.m2320a(this.f1984c, c0728ja.f1984c)) != 0) {
            return m2320a4;
        }
        int compareTo5 = Boolean.valueOf(m2014e()).compareTo(Boolean.valueOf(c0728ja.m2014e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2014e() && (m2318a = C0745jr.m2318a(this.f1977a, c0728ja.f1977a)) != 0) {
            return m2318a;
        }
        int compareTo6 = Boolean.valueOf(m2015f()).compareTo(Boolean.valueOf(c0728ja.m2015f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2015f() && (m2320a3 = C0745jr.m2320a(this.f1985d, c0728ja.f1985d)) != 0) {
            return m2320a3;
        }
        int compareTo7 = Boolean.valueOf(m2016g()).compareTo(Boolean.valueOf(c0728ja.m2016g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2016g() && (m2320a2 = C0745jr.m2320a(this.f1986e, c0728ja.f1986e)) != 0) {
            return m2320a2;
        }
        int compareTo8 = Boolean.valueOf(m2017h()).compareTo(Boolean.valueOf(c0728ja.m2017h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m2017h() && (m2322a = C0745jr.m2322a(this.f1981a, c0728ja.f1981a)) != 0) {
            return m2322a;
        }
        int compareTo9 = Boolean.valueOf(m2018i()).compareTo(Boolean.valueOf(c0728ja.m2018i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m2018i() && (m2320a = C0745jr.m2320a(this.f1987f, c0728ja.f1987f)) != 0) {
            return m2320a;
        }
        int compareTo10 = Boolean.valueOf(m2019j()).compareTo(Boolean.valueOf(c0728ja.m2019j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!m2019j() || (m2326a = C0745jr.m2326a(this.f1982a, c0728ja.f1982a)) == 0) {
            return 0;
        }
        return m2326a;
    }

    /* renamed from: a */
    public String m2003a() {
        return this.f1984c;
    }

    /* renamed from: a */
    public List<String> m2004a() {
        return this.f1981a;
    }

    /* renamed from: a */
    public void m2005a() {
        if (this.f1979a == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f1983b == null) {
            throw new C0757kc("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f1984c != null) {
            return;
        }
        throw new C0757kc("Required field 'cmdName' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                if (m2014e()) {
                    m2005a();
                    return;
                }
                throw new C0757kc("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f1978a = new C0720it();
                        this.f1978a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f1979a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f1983b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f1984c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 10) {
                        this.f1977a = abstractC0756kb.mo2341a();
                        m2006a(true);
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f1985d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f1986e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 15) {
                        C0753jz mo2343a = abstractC0756kb.mo2343a();
                        this.f1981a = new ArrayList(mo2343a.f2328a);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2343a.f2328a) {
                                abstractC0756kb.mo2376j();
                                break;
                            } else {
                                this.f1981a.add(abstractC0756kb.mo2347a());
                                i = i2 + 1;
                            }
                        }
                    }
                    break;
                case 12:
                    if (mo2342a.f2324a == 11) {
                        this.f1987f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 13:
                    if (mo2342a.f2324a == 2) {
                        this.f1982a = abstractC0756kb.mo2365a();
                        m2010b(true);
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2006a(boolean z) {
        this.f1980a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2007a() {
        return this.f1978a != null;
    }

    /* renamed from: a */
    public boolean m2008a(C0728ja c0728ja) {
        if (c0728ja == null) {
            return false;
        }
        boolean m2007a = m2007a();
        boolean m2007a2 = c0728ja.m2007a();
        if ((m2007a || m2007a2) && !(m2007a && m2007a2 && this.f1978a.m1873a(c0728ja.f1978a))) {
            return false;
        }
        boolean m2011b = m2011b();
        boolean m2011b2 = c0728ja.m2011b();
        if ((m2011b || m2011b2) && !(m2011b && m2011b2 && this.f1979a.equals(c0728ja.f1979a))) {
            return false;
        }
        boolean m2012c = m2012c();
        boolean m2012c2 = c0728ja.m2012c();
        if ((m2012c || m2012c2) && !(m2012c && m2012c2 && this.f1983b.equals(c0728ja.f1983b))) {
            return false;
        }
        boolean m2013d = m2013d();
        boolean m2013d2 = c0728ja.m2013d();
        if (((m2013d || m2013d2) && !(m2013d && m2013d2 && this.f1984c.equals(c0728ja.f1984c))) || this.f1977a != c0728ja.f1977a) {
            return false;
        }
        boolean m2015f = m2015f();
        boolean m2015f2 = c0728ja.m2015f();
        if ((m2015f || m2015f2) && !(m2015f && m2015f2 && this.f1985d.equals(c0728ja.f1985d))) {
            return false;
        }
        boolean m2016g = m2016g();
        boolean m2016g2 = c0728ja.m2016g();
        if ((m2016g || m2016g2) && !(m2016g && m2016g2 && this.f1986e.equals(c0728ja.f1986e))) {
            return false;
        }
        boolean m2017h = m2017h();
        boolean m2017h2 = c0728ja.m2017h();
        if ((m2017h || m2017h2) && !(m2017h && m2017h2 && this.f1981a.equals(c0728ja.f1981a))) {
            return false;
        }
        boolean m2018i = m2018i();
        boolean m2018i2 = c0728ja.m2018i();
        if ((m2018i || m2018i2) && !(m2018i && m2018i2 && this.f1987f.equals(c0728ja.f1987f))) {
            return false;
        }
        boolean m2019j = m2019j();
        boolean m2019j2 = c0728ja.m2019j();
        if (m2019j || m2019j2) {
            return m2019j && m2019j2 && this.f1982a == c0728ja.f1982a;
        }
        return true;
    }

    /* renamed from: b */
    public String m2009b() {
        return this.f1987f;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2005a();
        abstractC0756kb.mo2360a(f1967a);
        if (this.f1978a != null && m2007a()) {
            abstractC0756kb.mo2356a(f1966a);
            this.f1978a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1979a != null) {
            abstractC0756kb.mo2356a(f1968b);
            abstractC0756kb.mo2361a(this.f1979a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1983b != null) {
            abstractC0756kb.mo2356a(f1969c);
            abstractC0756kb.mo2361a(this.f1983b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1984c != null) {
            abstractC0756kb.mo2356a(f1970d);
            abstractC0756kb.mo2361a(this.f1984c);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f1971e);
        abstractC0756kb.mo2355a(this.f1977a);
        abstractC0756kb.mo2366b();
        if (this.f1985d != null && m2015f()) {
            abstractC0756kb.mo2356a(f1972f);
            abstractC0756kb.mo2361a(this.f1985d);
            abstractC0756kb.mo2366b();
        }
        if (this.f1986e != null && m2016g()) {
            abstractC0756kb.mo2356a(f1973g);
            abstractC0756kb.mo2361a(this.f1986e);
            abstractC0756kb.mo2366b();
        }
        if (this.f1981a != null && m2017h()) {
            abstractC0756kb.mo2356a(f1974h);
            abstractC0756kb.mo2357a(new C0753jz((byte) 11, this.f1981a.size()));
            Iterator<String> it = this.f1981a.iterator();
            while (it.hasNext()) {
                abstractC0756kb.mo2361a(it.next());
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        if (this.f1987f != null && m2018i()) {
            abstractC0756kb.mo2356a(f1975i);
            abstractC0756kb.mo2361a(this.f1987f);
            abstractC0756kb.mo2366b();
        }
        if (m2019j()) {
            abstractC0756kb.mo2356a(f1976j);
            abstractC0756kb.mo2364a(this.f1982a);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m2010b(boolean z) {
        this.f1980a.set(1, z);
    }

    /* renamed from: b */
    public boolean m2011b() {
        return this.f1979a != null;
    }

    /* renamed from: c */
    public boolean m2012c() {
        return this.f1983b != null;
    }

    /* renamed from: d */
    public boolean m2013d() {
        return this.f1984c != null;
    }

    /* renamed from: e */
    public boolean m2014e() {
        return this.f1980a.get(0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0728ja)) {
            return m2008a((C0728ja) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2015f() {
        return this.f1985d != null;
    }

    /* renamed from: g */
    public boolean m2016g() {
        return this.f1986e != null;
    }

    /* renamed from: h */
    public boolean m2017h() {
        return this.f1981a != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m2018i() {
        return this.f1987f != null;
    }

    /* renamed from: j */
    public boolean m2019j() {
        return this.f1980a.get(1);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommandResult(");
        if (m2007a()) {
            sb.append("target:");
            C0720it c0720it = this.f1978a;
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
        String str = this.f1979a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("appId:");
        String str2 = this.f1983b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("cmdName:");
        String str3 = this.f1984c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("errorCode:");
        sb.append(this.f1977a);
        if (m2015f()) {
            sb.append(", ");
            sb.append("reason:");
            String str4 = this.f1985d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m2016g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str5 = this.f1986e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m2017h()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            List<String> list = this.f1981a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        if (m2018i()) {
            sb.append(", ");
            sb.append("category:");
            String str6 = this.f1987f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m2019j()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f1982a);
        }
        sb.append(")");
        return sb.toString();
    }
}
