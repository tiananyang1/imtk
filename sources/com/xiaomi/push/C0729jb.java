package com.xiaomi.push;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.jb */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jb.class */
public class C0729jb implements InterfaceC0744jq<C0729jb, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public EnumC0696hw f1997a;

    /* renamed from: a */
    public C0717iq f1998a;

    /* renamed from: a */
    public C0720it f1999a;

    /* renamed from: a */
    public String f2000a;

    /* renamed from: a */
    public ByteBuffer f2001a;

    /* renamed from: b */
    public String f2004b;

    /* renamed from: a */
    private static final C0761kg f1989a = new C0761kg("XmPushActionContainer");

    /* renamed from: a */
    private static final C0752jy f1988a = new C0752jy("", (byte) 8, 1);

    /* renamed from: b */
    private static final C0752jy f1990b = new C0752jy("", (byte) 2, 2);

    /* renamed from: c */
    private static final C0752jy f1991c = new C0752jy("", (byte) 2, 3);

    /* renamed from: d */
    private static final C0752jy f1992d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f1993e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f1994f = new C0752jy("", (byte) 11, 6);

    /* renamed from: g */
    private static final C0752jy f1995g = new C0752jy("", (byte) 12, 7);

    /* renamed from: h */
    private static final C0752jy f1996h = new C0752jy("", (byte) 12, 8);

    /* renamed from: a */
    private BitSet f2002a = new BitSet(2);

    /* renamed from: a */
    public boolean f2003a = true;

    /* renamed from: b */
    public boolean f2005b = true;

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0729jb c0729jb) {
        int m2319a;
        int m2319a2;
        int m2320a;
        int m2320a2;
        int m2319a3;
        int m2326a;
        int m2326a2;
        int m2319a4;
        if (!getClass().equals(c0729jb.getClass())) {
            return getClass().getName().compareTo(c0729jb.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2032a()).compareTo(Boolean.valueOf(c0729jb.m2032a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m2032a() && (m2319a4 = C0745jr.m2319a(this.f1997a, c0729jb.f1997a)) != 0) {
            return m2319a4;
        }
        int compareTo2 = Boolean.valueOf(m2040c()).compareTo(Boolean.valueOf(c0729jb.m2040c()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m2040c() && (m2326a2 = C0745jr.m2326a(this.f2003a, c0729jb.f2003a)) != 0) {
            return m2326a2;
        }
        int compareTo3 = Boolean.valueOf(m2041d()).compareTo(Boolean.valueOf(c0729jb.m2041d()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m2041d() && (m2326a = C0745jr.m2326a(this.f2005b, c0729jb.f2005b)) != 0) {
            return m2326a;
        }
        int compareTo4 = Boolean.valueOf(m2042e()).compareTo(Boolean.valueOf(c0729jb.m2042e()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m2042e() && (m2319a3 = C0745jr.m2319a(this.f2001a, c0729jb.f2001a)) != 0) {
            return m2319a3;
        }
        int compareTo5 = Boolean.valueOf(m2043f()).compareTo(Boolean.valueOf(c0729jb.m2043f()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m2043f() && (m2320a2 = C0745jr.m2320a(this.f2000a, c0729jb.f2000a)) != 0) {
            return m2320a2;
        }
        int compareTo6 = Boolean.valueOf(m2044g()).compareTo(Boolean.valueOf(c0729jb.m2044g()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m2044g() && (m2320a = C0745jr.m2320a(this.f2004b, c0729jb.f2004b)) != 0) {
            return m2320a;
        }
        int compareTo7 = Boolean.valueOf(m2045h()).compareTo(Boolean.valueOf(c0729jb.m2045h()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m2045h() && (m2319a2 = C0745jr.m2319a(this.f1999a, c0729jb.f1999a)) != 0) {
            return m2319a2;
        }
        int compareTo8 = Boolean.valueOf(m2046i()).compareTo(Boolean.valueOf(c0729jb.m2046i()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (!m2046i() || (m2319a = C0745jr.m2319a(this.f1998a, c0729jb.f1998a)) == 0) {
            return 0;
        }
        return m2319a;
    }

    /* renamed from: a */
    public EnumC0696hw m2021a() {
        return this.f1997a;
    }

    /* renamed from: a */
    public C0717iq m2022a() {
        return this.f1998a;
    }

    /* renamed from: a */
    public C0729jb m2023a(EnumC0696hw enumC0696hw) {
        this.f1997a = enumC0696hw;
        return this;
    }

    /* renamed from: a */
    public C0729jb m2024a(C0717iq c0717iq) {
        this.f1998a = c0717iq;
        return this;
    }

    /* renamed from: a */
    public C0729jb m2025a(C0720it c0720it) {
        this.f1999a = c0720it;
        return this;
    }

    /* renamed from: a */
    public C0729jb m2026a(String str) {
        this.f2000a = str;
        return this;
    }

    /* renamed from: a */
    public C0729jb m2027a(ByteBuffer byteBuffer) {
        this.f2001a = byteBuffer;
        return this;
    }

    /* renamed from: a */
    public C0729jb m2028a(boolean z) {
        this.f2003a = z;
        m2031a(true);
        return this;
    }

    /* renamed from: a */
    public String m2029a() {
        return this.f2000a;
    }

    /* renamed from: a */
    public void m2030a() {
        if (this.f1997a == null) {
            throw new C0757kc("Required field 'action' was not present! Struct: " + toString());
        }
        if (this.f2001a == null) {
            throw new C0757kc("Required field 'pushAction' was not present! Struct: " + toString());
        }
        if (this.f1999a != null) {
            return;
        }
        throw new C0757kc("Required field 'target' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                if (!m2040c()) {
                    throw new C0757kc("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
                }
                if (m2041d()) {
                    m2030a();
                    return;
                }
                throw new C0757kc("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 8) {
                        this.f1997a = EnumC0696hw.m1613a(abstractC0756kb.mo2340a());
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 2) {
                        this.f2003a = abstractC0756kb.mo2365a();
                        m2031a(true);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 2) {
                        this.f2005b = abstractC0756kb.mo2365a();
                        m2038b(true);
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f2001a = abstractC0756kb.mo2349a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f2000a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 11) {
                        this.f2004b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 12) {
                        this.f1999a = new C0720it();
                        this.f1999a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 12) {
                        this.f1998a = new C0717iq();
                        this.f1998a.mo1287a(abstractC0756kb);
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m2031a(boolean z) {
        this.f2002a.set(0, z);
    }

    /* renamed from: a */
    public boolean m2032a() {
        return this.f1997a != null;
    }

    /* renamed from: a */
    public boolean m2033a(C0729jb c0729jb) {
        if (c0729jb == null) {
            return false;
        }
        boolean m2032a = m2032a();
        boolean m2032a2 = c0729jb.m2032a();
        if (((m2032a || m2032a2) && (!m2032a || !m2032a2 || !this.f1997a.equals(c0729jb.f1997a))) || this.f2003a != c0729jb.f2003a || this.f2005b != c0729jb.f2005b) {
            return false;
        }
        boolean m2042e = m2042e();
        boolean m2042e2 = c0729jb.m2042e();
        if ((m2042e || m2042e2) && !(m2042e && m2042e2 && this.f2001a.equals(c0729jb.f2001a))) {
            return false;
        }
        boolean m2043f = m2043f();
        boolean m2043f2 = c0729jb.m2043f();
        if ((m2043f || m2043f2) && !(m2043f && m2043f2 && this.f2000a.equals(c0729jb.f2000a))) {
            return false;
        }
        boolean m2044g = m2044g();
        boolean m2044g2 = c0729jb.m2044g();
        if ((m2044g || m2044g2) && !(m2044g && m2044g2 && this.f2004b.equals(c0729jb.f2004b))) {
            return false;
        }
        boolean m2045h = m2045h();
        boolean m2045h2 = c0729jb.m2045h();
        if ((m2045h || m2045h2) && !(m2045h && m2045h2 && this.f1999a.m1873a(c0729jb.f1999a))) {
            return false;
        }
        boolean m2046i = m2046i();
        boolean m2046i2 = c0729jb.m2046i();
        if (m2046i || m2046i2) {
            return m2046i && m2046i2 && this.f1998a.m1831a(c0729jb.f1998a);
        }
        return true;
    }

    /* renamed from: a */
    public byte[] m2034a() {
        m2027a(C0745jr.m2329a(this.f2001a));
        return this.f2001a.array();
    }

    /* renamed from: b */
    public C0729jb m2035b(String str) {
        this.f2004b = str;
        return this;
    }

    /* renamed from: b */
    public C0729jb m2036b(boolean z) {
        this.f2005b = z;
        m2038b(true);
        return this;
    }

    /* renamed from: b */
    public String m2037b() {
        return this.f2004b;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2030a();
        abstractC0756kb.mo2360a(f1989a);
        if (this.f1997a != null) {
            abstractC0756kb.mo2356a(f1988a);
            abstractC0756kb.mo2354a(this.f1997a.m1614a());
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2356a(f1990b);
        abstractC0756kb.mo2364a(this.f2003a);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2356a(f1991c);
        abstractC0756kb.mo2364a(this.f2005b);
        abstractC0756kb.mo2366b();
        if (this.f2001a != null) {
            abstractC0756kb.mo2356a(f1992d);
            abstractC0756kb.mo2362a(this.f2001a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2000a != null && m2043f()) {
            abstractC0756kb.mo2356a(f1993e);
            abstractC0756kb.mo2361a(this.f2000a);
            abstractC0756kb.mo2366b();
        }
        if (this.f2004b != null && m2044g()) {
            abstractC0756kb.mo2356a(f1994f);
            abstractC0756kb.mo2361a(this.f2004b);
            abstractC0756kb.mo2366b();
        }
        if (this.f1999a != null) {
            abstractC0756kb.mo2356a(f1995g);
            this.f1999a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        if (this.f1998a != null && m2046i()) {
            abstractC0756kb.mo2356a(f1996h);
            this.f1998a.mo1293b(abstractC0756kb);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m2038b(boolean z) {
        this.f2002a.set(1, z);
    }

    /* renamed from: b */
    public boolean m2039b() {
        return this.f2003a;
    }

    /* renamed from: c */
    public boolean m2040c() {
        return this.f2002a.get(0);
    }

    /* renamed from: d */
    public boolean m2041d() {
        return this.f2002a.get(1);
    }

    /* renamed from: e */
    public boolean m2042e() {
        return this.f2001a != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0729jb)) {
            return m2033a((C0729jb) obj);
        }
        return false;
    }

    /* renamed from: f */
    public boolean m2043f() {
        return this.f2000a != null;
    }

    /* renamed from: g */
    public boolean m2044g() {
        return this.f2004b != null;
    }

    /* renamed from: h */
    public boolean m2045h() {
        return this.f1999a != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m2046i() {
        return this.f1998a != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionContainer(");
        sb.append("action:");
        EnumC0696hw enumC0696hw = this.f1997a;
        if (enumC0696hw == null) {
            sb.append("null");
        } else {
            sb.append(enumC0696hw);
        }
        sb.append(", ");
        sb.append("encryptAction:");
        sb.append(this.f2003a);
        sb.append(", ");
        sb.append("isRequest:");
        sb.append(this.f2005b);
        sb.append(", ");
        sb.append("pushAction:");
        ByteBuffer byteBuffer = this.f2001a;
        if (byteBuffer == null) {
            sb.append("null");
        } else {
            C0745jr.m2330a(byteBuffer, sb);
        }
        if (m2043f()) {
            sb.append(", ");
            sb.append("appid:");
            String str = this.f2000a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        if (m2044g()) {
            sb.append(", ");
            sb.append("packageName:");
            String str2 = this.f2004b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("target:");
        C0720it c0720it = this.f1999a;
        if (c0720it == null) {
            sb.append("null");
        } else {
            sb.append(c0720it);
        }
        if (m2046i()) {
            sb.append(", ");
            sb.append("metaInfo:");
            C0717iq c0717iq = this.f1998a;
            if (c0717iq == null) {
                sb.append("null");
            } else {
                sb.append(c0717iq);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
