package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.iz */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/iz.class */
public class C0726iz implements InterfaceC0744jq<C0726iz, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public long f1949a;

    /* renamed from: a */
    public C0720it f1950a;

    /* renamed from: a */
    public String f1951a;

    /* renamed from: a */
    public List<String> f1953a;

    /* renamed from: b */
    public String f1955b;

    /* renamed from: c */
    public String f1957c;

    /* renamed from: d */
    public String f1958d;

    /* renamed from: e */
    public String f1959e;

    /* renamed from: a */
    private static final C0761kg f1939a = new C0761kg("XmPushActionCommand");

    /* renamed from: a */
    private static final C0752jy f1938a = new C0752jy("", (byte) 12, 2);

    /* renamed from: b */
    private static final C0752jy f1940b = new C0752jy("", (byte) 11, 3);

    /* renamed from: c */
    private static final C0752jy f1941c = new C0752jy("", (byte) 11, 4);

    /* renamed from: d */
    private static final C0752jy f1942d = new C0752jy("", (byte) 11, 5);

    /* renamed from: e */
    private static final C0752jy f1943e = new C0752jy("", (byte) 15, 6);

    /* renamed from: f */
    private static final C0752jy f1944f = new C0752jy("", (byte) 11, 7);

    /* renamed from: g */
    private static final C0752jy f1945g = new C0752jy("", (byte) 11, 9);

    /* renamed from: h */
    private static final C0752jy f1946h = new C0752jy("", (byte) 2, 10);

    /* renamed from: i */
    private static final C0752jy f1947i = new C0752jy("", (byte) 2, 11);

    /* renamed from: j */
    private static final C0752jy f1948j = new C0752jy("", (byte) 10, 12);

    /* renamed from: a */
    private BitSet f1952a = new BitSet(3);

    /* renamed from: a */
    public boolean f1954a = false;

    /* renamed from: b */
    public boolean f1956b = true;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0726iz c0726iz) {
        int m2318a;
        int m2326a;
        int m2326a2;
        int m2320a;
        int m2320a2;
        int m2322a;
        int m2320a3;
        int m2320a4;
        int m2320a5;
        int m2319a;
        if (!getClass().equals(c0726iz.getClass())) {
            return getClass().getName().compareTo(c0726iz.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1962a()).compareTo(Boolean.valueOf(c0726iz.m1962a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1962a() && (m2319a = C0745jr.m2319a(this.f1950a, c0726iz.f1950a)) != 0) {
            return m2319a;
        }
        int compareTo2 = Boolean.valueOf(m1966b()).compareTo(Boolean.valueOf(c0726iz.m1966b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1966b() && (m2320a5 = C0745jr.m2320a(this.f1951a, c0726iz.f1951a)) != 0) {
            return m2320a5;
        }
        int compareTo3 = Boolean.valueOf(m1969c()).compareTo(Boolean.valueOf(c0726iz.m1969c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1969c() && (m2320a4 = C0745jr.m2320a(this.f1955b, c0726iz.f1955b)) != 0) {
            return m2320a4;
        }
        int compareTo4 = Boolean.valueOf(m1971d()).compareTo(Boolean.valueOf(c0726iz.m1971d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1971d() && (m2320a3 = C0745jr.m2320a(this.f1957c, c0726iz.f1957c)) != 0) {
            return m2320a3;
        }
        int compareTo5 = Boolean.valueOf(m1973e()).compareTo(Boolean.valueOf(c0726iz.m1973e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1973e() && (m2322a = C0745jr.m2322a(this.f1953a, c0726iz.f1953a)) != 0) {
            return m2322a;
        }
        int compareTo6 = Boolean.valueOf(m1974f()).compareTo(Boolean.valueOf(c0726iz.m1974f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1974f() && (m2320a2 = C0745jr.m2320a(this.f1958d, c0726iz.f1958d)) != 0) {
            return m2320a2;
        }
        int compareTo7 = Boolean.valueOf(m1975g()).compareTo(Boolean.valueOf(c0726iz.m1975g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m1975g() && (m2320a = C0745jr.m2320a(this.f1959e, c0726iz.f1959e)) != 0) {
            return m2320a;
        }
        int compareTo8 = Boolean.valueOf(m1976h()).compareTo(Boolean.valueOf(c0726iz.m1976h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m1976h() && (m2326a2 = C0745jr.m2326a(this.f1954a, c0726iz.f1954a)) != 0) {
            return m2326a2;
        }
        int compareTo9 = Boolean.valueOf(m1977i()).compareTo(Boolean.valueOf(c0726iz.m1977i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m1977i() && (m2326a = C0745jr.m2326a(this.f1956b, c0726iz.f1956b)) != 0) {
            return m2326a;
        }
        int compareTo10 = Boolean.valueOf(m1978j()).compareTo(Boolean.valueOf(c0726iz.m1978j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!m1978j() || (m2318a = C0745jr.m2318a(this.f1949a, c0726iz.f1949a)) == 0) {
            return 0;
        }
        return m2318a;
    }

    /* renamed from: a */
    public C0726iz m1956a(String str) {
        this.f1951a = str;
        return this;
    }

    /* renamed from: a */
    public C0726iz m1957a(List<String> list) {
        this.f1953a = list;
        return this;
    }

    /* renamed from: a */
    public String m1958a() {
        return this.f1957c;
    }

    /* renamed from: a */
    public void m1959a() {
        if (this.f1951a == null) {
            throw new C0757kc("Required field 'id' was not present! Struct: " + toString());
        }
        if (this.f1955b == null) {
            throw new C0757kc("Required field 'appId' was not present! Struct: " + toString());
        }
        if (this.f1957c != null) {
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
                m1959a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f1950a = new C0720it();
                        this.f1950a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f1951a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f1955b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f1957c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 15) {
                        C0753jz mo2343a = abstractC0756kb.mo2343a();
                        this.f1953a = new ArrayList(mo2343a.f2328a);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2343a.f2328a) {
                                abstractC0756kb.mo2376j();
                                break;
                            } else {
                                this.f1953a.add(abstractC0756kb.mo2347a());
                                i = i2 + 1;
                            }
                        }
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f1958d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 11) {
                        this.f1959e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 2) {
                        this.f1954a = abstractC0756kb.mo2365a();
                        m1961a(true);
                        break;
                    }
                    break;
                case 11:
                    if (mo2342a.f2324a == 2) {
                        this.f1956b = abstractC0756kb.mo2365a();
                        m1965b(true);
                        break;
                    }
                    break;
                case 12:
                    if (mo2342a.f2324a == 10) {
                        this.f1949a = abstractC0756kb.mo2341a();
                        m1968c(true);
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m1960a(String str) {
        if (this.f1953a == null) {
            this.f1953a = new ArrayList();
        }
        this.f1953a.add(str);
    }

    /* renamed from: a */
    public void m1961a(boolean z) {
        this.f1952a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1962a() {
        return this.f1950a != null;
    }

    /* renamed from: a */
    public boolean m1963a(C0726iz c0726iz) {
        if (c0726iz == null) {
            return false;
        }
        boolean m1962a = m1962a();
        boolean m1962a2 = c0726iz.m1962a();
        if ((m1962a || m1962a2) && !(m1962a && m1962a2 && this.f1950a.m1873a(c0726iz.f1950a))) {
            return false;
        }
        boolean m1966b = m1966b();
        boolean m1966b2 = c0726iz.m1966b();
        if ((m1966b || m1966b2) && !(m1966b && m1966b2 && this.f1951a.equals(c0726iz.f1951a))) {
            return false;
        }
        boolean m1969c = m1969c();
        boolean m1969c2 = c0726iz.m1969c();
        if ((m1969c || m1969c2) && !(m1969c && m1969c2 && this.f1955b.equals(c0726iz.f1955b))) {
            return false;
        }
        boolean m1971d = m1971d();
        boolean m1971d2 = c0726iz.m1971d();
        if ((m1971d || m1971d2) && !(m1971d && m1971d2 && this.f1957c.equals(c0726iz.f1957c))) {
            return false;
        }
        boolean m1973e = m1973e();
        boolean m1973e2 = c0726iz.m1973e();
        if ((m1973e || m1973e2) && !(m1973e && m1973e2 && this.f1953a.equals(c0726iz.f1953a))) {
            return false;
        }
        boolean m1974f = m1974f();
        boolean m1974f2 = c0726iz.m1974f();
        if ((m1974f || m1974f2) && !(m1974f && m1974f2 && this.f1958d.equals(c0726iz.f1958d))) {
            return false;
        }
        boolean m1975g = m1975g();
        boolean m1975g2 = c0726iz.m1975g();
        if ((m1975g || m1975g2) && !(m1975g && m1975g2 && this.f1959e.equals(c0726iz.f1959e))) {
            return false;
        }
        boolean m1976h = m1976h();
        boolean m1976h2 = c0726iz.m1976h();
        if ((m1976h || m1976h2) && !(m1976h && m1976h2 && this.f1954a == c0726iz.f1954a)) {
            return false;
        }
        boolean m1977i = m1977i();
        boolean m1977i2 = c0726iz.m1977i();
        if ((m1977i || m1977i2) && !(m1977i && m1977i2 && this.f1956b == c0726iz.f1956b)) {
            return false;
        }
        boolean m1978j = m1978j();
        boolean m1978j2 = c0726iz.m1978j();
        if (m1978j || m1978j2) {
            return m1978j && m1978j2 && this.f1949a == c0726iz.f1949a;
        }
        return true;
    }

    /* renamed from: b */
    public C0726iz m1964b(String str) {
        this.f1955b = str;
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1959a();
        abstractC0756kb.mo2360a(f1939a);
        if (this.f1950a != null && m1962a()) {
            abstractC0756kb.mo2356a(f1938a);
            this.f1950a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1951a != null) {
            abstractC0756kb.mo2356a(f1940b);
            abstractC0756kb.mo2361a(this.f1951a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1955b != null) {
            abstractC0756kb.mo2356a(f1941c);
            abstractC0756kb.mo2361a(this.f1955b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1957c != null) {
            abstractC0756kb.mo2356a(f1942d);
            abstractC0756kb.mo2361a(this.f1957c);
            abstractC0756kb.mo2366b();
        }
        if (this.f1953a != null && m1973e()) {
            abstractC0756kb.mo2356a(f1943e);
            abstractC0756kb.mo2357a(new C0753jz((byte) 11, this.f1953a.size()));
            Iterator<String> it = this.f1953a.iterator();
            while (it.hasNext()) {
                abstractC0756kb.mo2361a(it.next());
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        if (this.f1958d != null && m1974f()) {
            abstractC0756kb.mo2356a(f1944f);
            abstractC0756kb.mo2361a(this.f1958d);
            abstractC0756kb.mo2366b();
        }
        if (this.f1959e != null && m1975g()) {
            abstractC0756kb.mo2356a(f1945g);
            abstractC0756kb.mo2361a(this.f1959e);
            abstractC0756kb.mo2366b();
        }
        if (m1976h()) {
            abstractC0756kb.mo2356a(f1946h);
            abstractC0756kb.mo2364a(this.f1954a);
            abstractC0756kb.mo2366b();
        }
        if (m1977i()) {
            abstractC0756kb.mo2356a(f1947i);
            abstractC0756kb.mo2364a(this.f1956b);
            abstractC0756kb.mo2366b();
        }
        if (m1978j()) {
            abstractC0756kb.mo2356a(f1948j);
            abstractC0756kb.mo2355a(this.f1949a);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1965b(boolean z) {
        this.f1952a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1966b() {
        return this.f1951a != null;
    }

    /* renamed from: c */
    public C0726iz m1967c(String str) {
        this.f1957c = str;
        return this;
    }

    /* renamed from: c */
    public void m1968c(boolean z) {
        this.f1952a.set(2, z);
    }

    /* renamed from: c */
    public boolean m1969c() {
        return this.f1955b != null;
    }

    /* renamed from: d */
    public C0726iz m1970d(String str) {
        this.f1958d = str;
        return this;
    }

    /* renamed from: d */
    public boolean m1971d() {
        return this.f1957c != null;
    }

    /* renamed from: e */
    public C0726iz m1972e(String str) {
        this.f1959e = str;
        return this;
    }

    /* renamed from: e */
    public boolean m1973e() {
        return this.f1953a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0726iz)) {
            return m1963a((C0726iz) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m1974f() {
        return this.f1958d != null;
    }

    /* renamed from: g */
    public boolean m1975g() {
        return this.f1959e != null;
    }

    /* renamed from: h */
    public boolean m1976h() {
        return this.f1952a.get(0);
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m1977i() {
        return this.f1952a.get(1);
    }

    /* renamed from: j */
    public boolean m1978j() {
        return this.f1952a.get(2);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionCommand(");
        if (m1962a()) {
            sb.append("target:");
            C0720it c0720it = this.f1950a;
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
        String str = this.f1951a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("appId:");
        String str2 = this.f1955b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("cmdName:");
        String str3 = this.f1957c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        if (m1973e()) {
            sb.append(", ");
            sb.append("cmdArgs:");
            List<String> list = this.f1953a;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        if (m1974f()) {
            sb.append(", ");
            sb.append("packageName:");
            String str4 = this.f1958d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m1975g()) {
            sb.append(", ");
            sb.append("category:");
            String str5 = this.f1959e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m1976h()) {
            sb.append(", ");
            sb.append("updateCache:");
            sb.append(this.f1954a);
        }
        if (m1977i()) {
            sb.append(", ");
            sb.append("response2Client:");
            sb.append(this.f1956b);
        }
        if (m1978j()) {
            sb.append(", ");
            sb.append("createdTs:");
            sb.append(this.f1949a);
        }
        sb.append(")");
        return sb.toString();
    }
}
