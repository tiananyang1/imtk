package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.iw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/iw.class */
public class C0723iw implements InterfaceC0744jq<C0723iw, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public C0720it f1919a;

    /* renamed from: a */
    public String f1920a;

    /* renamed from: a */
    public Map<String, String> f1922a;

    /* renamed from: b */
    public String f1923b;

    /* renamed from: c */
    public String f1924c;

    /* renamed from: d */
    public String f1925d;

    /* renamed from: e */
    public String f1926e;

    /* renamed from: f */
    public String f1927f;

    /* renamed from: g */
    public String f1928g;

    /* renamed from: a */
    private static final C0761kg f1908a = new C0761kg("XmPushActionAckNotification");

    /* renamed from: a */
    private static final C0752jy f1907a = new C0752jy("", (byte) 11, 1);

    /* renamed from: b */
    private static final C0752jy f1909b = new C0752jy("", (byte) 12, 2);

    /* renamed from: c */
    private static final C0752jy f1910c = new C0752jy("", (byte) 11, 3);

    /* renamed from: d */
    private static final C0752jy f1911d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f1912e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f1913f = new C0752jy("", (byte) 10, 7);

    /* renamed from: g */
    private static final C0752jy f1914g = new C0752jy("", (byte) 11, 8);

    /* renamed from: h */
    private static final C0752jy f1915h = new C0752jy("", (byte) 13, 9);

    /* renamed from: i */
    private static final C0752jy f1916i = new C0752jy("", (byte) 11, 10);

    /* renamed from: j */
    private static final C0752jy f1917j = new C0752jy("", (byte) 11, 11);

    /* renamed from: a */
    private BitSet f1921a = new BitSet(1);

    /* renamed from: a */
    public long f1918a = 0;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0723iw c0723iw) {
        int m2320a;
        int m2320a2;
        int m2323a;
        int m2320a3;
        int m2318a;
        int m2320a4;
        int m2320a5;
        int m2320a6;
        int m2319a;
        int m2320a7;
        if (!getClass().equals(c0723iw.getClass())) {
            return getClass().getName().compareTo(c0723iw.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1930a()).compareTo(Boolean.valueOf(c0723iw.m1930a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1930a() && (m2320a7 = C0745jr.m2320a(this.f1920a, c0723iw.f1920a)) != 0) {
            return m2320a7;
        }
        int compareTo2 = Boolean.valueOf(m1932b()).compareTo(Boolean.valueOf(c0723iw.m1932b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1932b() && (m2319a = C0745jr.m2319a(this.f1919a, c0723iw.f1919a)) != 0) {
            return m2319a;
        }
        int compareTo3 = Boolean.valueOf(m1933c()).compareTo(Boolean.valueOf(c0723iw.m1933c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1933c() && (m2320a6 = C0745jr.m2320a(this.f1923b, c0723iw.f1923b)) != 0) {
            return m2320a6;
        }
        int compareTo4 = Boolean.valueOf(m1934d()).compareTo(Boolean.valueOf(c0723iw.m1934d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1934d() && (m2320a5 = C0745jr.m2320a(this.f1924c, c0723iw.f1924c)) != 0) {
            return m2320a5;
        }
        int compareTo5 = Boolean.valueOf(m1935e()).compareTo(Boolean.valueOf(c0723iw.m1935e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1935e() && (m2320a4 = C0745jr.m2320a(this.f1925d, c0723iw.f1925d)) != 0) {
            return m2320a4;
        }
        int compareTo6 = Boolean.valueOf(m1936f()).compareTo(Boolean.valueOf(c0723iw.m1936f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1936f() && (m2318a = C0745jr.m2318a(this.f1918a, c0723iw.f1918a)) != 0) {
            return m2318a;
        }
        int compareTo7 = Boolean.valueOf(m1937g()).compareTo(Boolean.valueOf(c0723iw.m1937g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m1937g() && (m2320a3 = C0745jr.m2320a(this.f1926e, c0723iw.f1926e)) != 0) {
            return m2320a3;
        }
        int compareTo8 = Boolean.valueOf(m1938h()).compareTo(Boolean.valueOf(c0723iw.m1938h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m1938h() && (m2323a = C0745jr.m2323a(this.f1922a, c0723iw.f1922a)) != 0) {
            return m2323a;
        }
        int compareTo9 = Boolean.valueOf(m1939i()).compareTo(Boolean.valueOf(c0723iw.m1939i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m1939i() && (m2320a2 = C0745jr.m2320a(this.f1927f, c0723iw.f1927f)) != 0) {
            return m2320a2;
        }
        int compareTo10 = Boolean.valueOf(m1940j()).compareTo(Boolean.valueOf(c0723iw.m1940j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!m1940j() || (m2320a = C0745jr.m2320a(this.f1928g, c0723iw.f1928g)) == 0) {
            return 0;
        }
        return m2320a;
    }

    /* renamed from: a */
    public String m1926a() {
        return this.f1923b;
    }

    /* renamed from: a */
    public Map<String, String> m1927a() {
        return this.f1922a;
    }

    /* renamed from: a */
    public void m1928a() {
        if (this.f1923b != null) {
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
                m1928a();
                return;
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 11) {
                        this.f1920a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 12) {
                        this.f1919a = new C0720it();
                        this.f1919a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 11) {
                        this.f1923b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f1924c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f1925d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 10) {
                        this.f1918a = abstractC0756kb.mo2341a();
                        m1929a(true);
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f1926e = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 13) {
                        C0755ka mo2344a = abstractC0756kb.mo2344a();
                        this.f1922a = new HashMap(mo2344a.f2335a * 2);
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= mo2344a.f2335a) {
                                abstractC0756kb.mo2375i();
                                break;
                            } else {
                                this.f1922a.put(abstractC0756kb.mo2347a(), abstractC0756kb.mo2347a());
                                i = i2 + 1;
                            }
                        }
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 11) {
                        this.f1927f = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 11:
                    if (mo2342a.f2324a == 11) {
                        this.f1928g = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m1929a(boolean z) {
        this.f1921a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1930a() {
        return this.f1920a != null;
    }

    /* renamed from: a */
    public boolean m1931a(C0723iw c0723iw) {
        if (c0723iw == null) {
            return false;
        }
        boolean m1930a = m1930a();
        boolean m1930a2 = c0723iw.m1930a();
        if ((m1930a || m1930a2) && !(m1930a && m1930a2 && this.f1920a.equals(c0723iw.f1920a))) {
            return false;
        }
        boolean m1932b = m1932b();
        boolean m1932b2 = c0723iw.m1932b();
        if ((m1932b || m1932b2) && !(m1932b && m1932b2 && this.f1919a.m1873a(c0723iw.f1919a))) {
            return false;
        }
        boolean m1933c = m1933c();
        boolean m1933c2 = c0723iw.m1933c();
        if ((m1933c || m1933c2) && !(m1933c && m1933c2 && this.f1923b.equals(c0723iw.f1923b))) {
            return false;
        }
        boolean m1934d = m1934d();
        boolean m1934d2 = c0723iw.m1934d();
        if ((m1934d || m1934d2) && !(m1934d && m1934d2 && this.f1924c.equals(c0723iw.f1924c))) {
            return false;
        }
        boolean m1935e = m1935e();
        boolean m1935e2 = c0723iw.m1935e();
        if ((m1935e || m1935e2) && !(m1935e && m1935e2 && this.f1925d.equals(c0723iw.f1925d))) {
            return false;
        }
        boolean m1936f = m1936f();
        boolean m1936f2 = c0723iw.m1936f();
        if ((m1936f || m1936f2) && !(m1936f && m1936f2 && this.f1918a == c0723iw.f1918a)) {
            return false;
        }
        boolean m1937g = m1937g();
        boolean m1937g2 = c0723iw.m1937g();
        if ((m1937g || m1937g2) && !(m1937g && m1937g2 && this.f1926e.equals(c0723iw.f1926e))) {
            return false;
        }
        boolean m1938h = m1938h();
        boolean m1938h2 = c0723iw.m1938h();
        if ((m1938h || m1938h2) && !(m1938h && m1938h2 && this.f1922a.equals(c0723iw.f1922a))) {
            return false;
        }
        boolean m1939i = m1939i();
        boolean m1939i2 = c0723iw.m1939i();
        if ((m1939i || m1939i2) && !(m1939i && m1939i2 && this.f1927f.equals(c0723iw.f1927f))) {
            return false;
        }
        boolean m1940j = m1940j();
        boolean m1940j2 = c0723iw.m1940j();
        if (m1940j || m1940j2) {
            return m1940j && m1940j2 && this.f1928g.equals(c0723iw.f1928g);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1928a();
        abstractC0756kb.mo2360a(f1908a);
        if (this.f1920a != null && m1930a()) {
            abstractC0756kb.mo2356a(f1907a);
            abstractC0756kb.mo2361a(this.f1920a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1919a != null && m1932b()) {
            abstractC0756kb.mo2356a(f1909b);
            this.f1919a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1923b != null) {
            abstractC0756kb.mo2356a(f1910c);
            abstractC0756kb.mo2361a(this.f1923b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1924c != null && m1934d()) {
            abstractC0756kb.mo2356a(f1911d);
            abstractC0756kb.mo2361a(this.f1924c);
            abstractC0756kb.mo2366b();
        }
        if (this.f1925d != null && m1935e()) {
            abstractC0756kb.mo2356a(f1912e);
            abstractC0756kb.mo2361a(this.f1925d);
            abstractC0756kb.mo2366b();
        }
        if (m1936f()) {
            abstractC0756kb.mo2356a(f1913f);
            abstractC0756kb.mo2355a(this.f1918a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1926e != null && m1937g()) {
            abstractC0756kb.mo2356a(f1914g);
            abstractC0756kb.mo2361a(this.f1926e);
            abstractC0756kb.mo2366b();
        }
        if (this.f1922a != null && m1938h()) {
            abstractC0756kb.mo2356a(f1915h);
            abstractC0756kb.mo2358a(new C0755ka((byte) 11, (byte) 11, this.f1922a.size()));
            for (Map.Entry<String, String> entry : this.f1922a.entrySet()) {
                abstractC0756kb.mo2361a(entry.getKey());
                abstractC0756kb.mo2361a(entry.getValue());
            }
            abstractC0756kb.mo2370d();
            abstractC0756kb.mo2366b();
        }
        if (this.f1927f != null && m1939i()) {
            abstractC0756kb.mo2356a(f1916i);
            abstractC0756kb.mo2361a(this.f1927f);
            abstractC0756kb.mo2366b();
        }
        if (this.f1928g != null && m1940j()) {
            abstractC0756kb.mo2356a(f1917j);
            abstractC0756kb.mo2361a(this.f1928g);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public boolean m1932b() {
        return this.f1919a != null;
    }

    /* renamed from: c */
    public boolean m1933c() {
        return this.f1923b != null;
    }

    /* renamed from: d */
    public boolean m1934d() {
        return this.f1924c != null;
    }

    /* renamed from: e */
    public boolean m1935e() {
        return this.f1925d != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0723iw)) {
            return m1931a((C0723iw) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m1936f() {
        return this.f1921a.get(0);
    }

    /* renamed from: g */
    public boolean m1937g() {
        return this.f1926e != null;
    }

    /* renamed from: h */
    public boolean m1938h() {
        return this.f1922a != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m1939i() {
        return this.f1927f != null;
    }

    /* renamed from: j */
    public boolean m1940j() {
        return this.f1928g != null;
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("XmPushActionAckNotification(");
        if (m1930a()) {
            sb.append("debug:");
            String str = this.f1920a;
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
        if (m1932b()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append("target:");
            C0720it c0720it = this.f1919a;
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
        String str2 = this.f1923b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        if (m1934d()) {
            sb.append(", ");
            sb.append("appId:");
            String str3 = this.f1924c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m1935e()) {
            sb.append(", ");
            sb.append("type:");
            String str4 = this.f1925d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m1936f()) {
            sb.append(", ");
            sb.append("errorCode:");
            sb.append(this.f1918a);
        }
        if (m1937g()) {
            sb.append(", ");
            sb.append("reason:");
            String str5 = this.f1926e;
            if (str5 == null) {
                sb.append("null");
            } else {
                sb.append(str5);
            }
        }
        if (m1938h()) {
            sb.append(", ");
            sb.append("extra:");
            Map<String, String> map = this.f1922a;
            if (map == null) {
                sb.append("null");
            } else {
                sb.append(map);
            }
        }
        if (m1939i()) {
            sb.append(", ");
            sb.append("packageName:");
            String str6 = this.f1927f;
            if (str6 == null) {
                sb.append("null");
            } else {
                sb.append(str6);
            }
        }
        if (m1940j()) {
            sb.append(", ");
            sb.append("category:");
            String str7 = this.f1928g;
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
