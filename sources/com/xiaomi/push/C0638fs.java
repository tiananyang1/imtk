package com.xiaomi.push;

import java.io.Serializable;
import java.util.BitSet;

/* renamed from: com.xiaomi.push.fs */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fs.class */
public class C0638fs implements InterfaceC0744jq<C0638fs, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public byte f1003a;

    /* renamed from: a */
    public int f1004a;

    /* renamed from: a */
    public String f1005a;

    /* renamed from: a */
    private BitSet f1006a = new BitSet(6);

    /* renamed from: b */
    public int f1007b;

    /* renamed from: b */
    public String f1008b;

    /* renamed from: c */
    public int f1009c;

    /* renamed from: c */
    public String f1010c;

    /* renamed from: d */
    public int f1011d;

    /* renamed from: d */
    public String f1012d;

    /* renamed from: e */
    public int f1013e;

    /* renamed from: a */
    private static final C0761kg f993a = new C0761kg("StatsEvent");

    /* renamed from: a */
    private static final C0752jy f992a = new C0752jy("", (byte) 3, 1);

    /* renamed from: b */
    private static final C0752jy f994b = new C0752jy("", (byte) 8, 2);

    /* renamed from: c */
    private static final C0752jy f995c = new C0752jy("", (byte) 8, 3);

    /* renamed from: d */
    private static final C0752jy f996d = new C0752jy("", (byte) 11, 4);

    /* renamed from: e */
    private static final C0752jy f997e = new C0752jy("", (byte) 11, 5);

    /* renamed from: f */
    private static final C0752jy f998f = new C0752jy("", (byte) 8, 6);

    /* renamed from: g */
    private static final C0752jy f999g = new C0752jy("", (byte) 11, 7);

    /* renamed from: h */
    private static final C0752jy f1000h = new C0752jy("", (byte) 11, 8);

    /* renamed from: i */
    private static final C0752jy f1001i = new C0752jy("", (byte) 8, 9);

    /* renamed from: j */
    private static final C0752jy f1002j = new C0752jy("", (byte) 8, 10);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0638fs c0638fs) {
        int m2317a;
        int m2317a2;
        int m2320a;
        int m2320a2;
        int m2317a3;
        int m2320a3;
        int m2320a4;
        int m2317a4;
        int m2317a5;
        int m2315a;
        if (!getClass().equals(c0638fs.getClass())) {
            return getClass().getName().compareTo(c0638fs.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1289a()).compareTo(Boolean.valueOf(c0638fs.m1289a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (m1289a() && (m2315a = C0745jr.m2315a(this.f1003a, c0638fs.f1003a)) != 0) {
            return m2315a;
        }
        int compareTo2 = Boolean.valueOf(m1295b()).compareTo(Boolean.valueOf(c0638fs.m1295b()));
        if (compareTo2 != 0) {
            return compareTo2;
        }
        if (m1295b() && (m2317a5 = C0745jr.m2317a(this.f1004a, c0638fs.f1004a)) != 0) {
            return m2317a5;
        }
        int compareTo3 = Boolean.valueOf(m1299c()).compareTo(Boolean.valueOf(c0638fs.m1299c()));
        if (compareTo3 != 0) {
            return compareTo3;
        }
        if (m1299c() && (m2317a4 = C0745jr.m2317a(this.f1007b, c0638fs.f1007b)) != 0) {
            return m2317a4;
        }
        int compareTo4 = Boolean.valueOf(m1303d()).compareTo(Boolean.valueOf(c0638fs.m1303d()));
        if (compareTo4 != 0) {
            return compareTo4;
        }
        if (m1303d() && (m2320a4 = C0745jr.m2320a(this.f1005a, c0638fs.f1005a)) != 0) {
            return m2320a4;
        }
        int compareTo5 = Boolean.valueOf(m1305e()).compareTo(Boolean.valueOf(c0638fs.m1305e()));
        if (compareTo5 != 0) {
            return compareTo5;
        }
        if (m1305e() && (m2320a3 = C0745jr.m2320a(this.f1008b, c0638fs.f1008b)) != 0) {
            return m2320a3;
        }
        int compareTo6 = Boolean.valueOf(m1307f()).compareTo(Boolean.valueOf(c0638fs.m1307f()));
        if (compareTo6 != 0) {
            return compareTo6;
        }
        if (m1307f() && (m2317a3 = C0745jr.m2317a(this.f1009c, c0638fs.f1009c)) != 0) {
            return m2317a3;
        }
        int compareTo7 = Boolean.valueOf(m1308g()).compareTo(Boolean.valueOf(c0638fs.m1308g()));
        if (compareTo7 != 0) {
            return compareTo7;
        }
        if (m1308g() && (m2320a2 = C0745jr.m2320a(this.f1010c, c0638fs.f1010c)) != 0) {
            return m2320a2;
        }
        int compareTo8 = Boolean.valueOf(m1309h()).compareTo(Boolean.valueOf(c0638fs.m1309h()));
        if (compareTo8 != 0) {
            return compareTo8;
        }
        if (m1309h() && (m2320a = C0745jr.m2320a(this.f1012d, c0638fs.f1012d)) != 0) {
            return m2320a;
        }
        int compareTo9 = Boolean.valueOf(m1310i()).compareTo(Boolean.valueOf(c0638fs.m1310i()));
        if (compareTo9 != 0) {
            return compareTo9;
        }
        if (m1310i() && (m2317a2 = C0745jr.m2317a(this.f1011d, c0638fs.f1011d)) != 0) {
            return m2317a2;
        }
        int compareTo10 = Boolean.valueOf(m1311j()).compareTo(Boolean.valueOf(c0638fs.m1311j()));
        if (compareTo10 != 0) {
            return compareTo10;
        }
        if (!m1311j() || (m2317a = C0745jr.m2317a(this.f1013e, c0638fs.f1013e)) == 0) {
            return 0;
        }
        return m2317a;
    }

    /* renamed from: a */
    public C0638fs m1283a(byte b) {
        this.f1003a = b;
        m1288a(true);
        return this;
    }

    /* renamed from: a */
    public C0638fs m1284a(int i) {
        this.f1004a = i;
        m1294b(true);
        return this;
    }

    /* renamed from: a */
    public C0638fs m1285a(String str) {
        this.f1005a = str;
        return this;
    }

    /* renamed from: a */
    public void m1286a() {
        if (this.f1005a != null) {
            return;
        }
        throw new C0757kc("Required field 'connpt' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                if (!m1289a()) {
                    throw new C0757kc("Required field 'chid' was not found in serialized data! Struct: " + toString());
                }
                if (!m1295b()) {
                    throw new C0757kc("Required field 'type' was not found in serialized data! Struct: " + toString());
                }
                if (m1299c()) {
                    m1286a();
                    return;
                }
                throw new C0757kc("Required field 'value' was not found in serialized data! Struct: " + toString());
            }
            switch (mo2342a.f2326a) {
                case 1:
                    if (mo2342a.f2324a == 3) {
                        this.f1003a = abstractC0756kb.mo2338a();
                        m1288a(true);
                        break;
                    }
                    break;
                case 2:
                    if (mo2342a.f2324a == 8) {
                        this.f1004a = abstractC0756kb.mo2340a();
                        m1294b(true);
                        break;
                    }
                    break;
                case 3:
                    if (mo2342a.f2324a == 8) {
                        this.f1007b = abstractC0756kb.mo2340a();
                        m1298c(true);
                        break;
                    }
                    break;
                case 4:
                    if (mo2342a.f2324a == 11) {
                        this.f1005a = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 5:
                    if (mo2342a.f2324a == 11) {
                        this.f1008b = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 6:
                    if (mo2342a.f2324a == 8) {
                        this.f1009c = abstractC0756kb.mo2340a();
                        m1302d(true);
                        break;
                    }
                    break;
                case 7:
                    if (mo2342a.f2324a == 11) {
                        this.f1010c = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 8:
                    if (mo2342a.f2324a == 11) {
                        this.f1012d = abstractC0756kb.mo2347a();
                        break;
                    }
                    break;
                case 9:
                    if (mo2342a.f2324a == 8) {
                        this.f1011d = abstractC0756kb.mo2340a();
                        m1304e(true);
                        break;
                    }
                    break;
                case 10:
                    if (mo2342a.f2324a == 8) {
                        this.f1013e = abstractC0756kb.mo2340a();
                        m1306f(true);
                        break;
                    }
                    break;
            }
            C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public void m1288a(boolean z) {
        this.f1006a.set(0, z);
    }

    /* renamed from: a */
    public boolean m1289a() {
        return this.f1006a.get(0);
    }

    /* renamed from: a */
    public boolean m1290a(C0638fs c0638fs) {
        if (c0638fs == null || this.f1003a != c0638fs.f1003a || this.f1004a != c0638fs.f1004a || this.f1007b != c0638fs.f1007b) {
            return false;
        }
        boolean m1303d = m1303d();
        boolean m1303d2 = c0638fs.m1303d();
        if ((m1303d || m1303d2) && !(m1303d && m1303d2 && this.f1005a.equals(c0638fs.f1005a))) {
            return false;
        }
        boolean m1305e = m1305e();
        boolean m1305e2 = c0638fs.m1305e();
        if ((m1305e || m1305e2) && !(m1305e && m1305e2 && this.f1008b.equals(c0638fs.f1008b))) {
            return false;
        }
        boolean m1307f = m1307f();
        boolean m1307f2 = c0638fs.m1307f();
        if ((m1307f || m1307f2) && !(m1307f && m1307f2 && this.f1009c == c0638fs.f1009c)) {
            return false;
        }
        boolean m1308g = m1308g();
        boolean m1308g2 = c0638fs.m1308g();
        if ((m1308g || m1308g2) && !(m1308g && m1308g2 && this.f1010c.equals(c0638fs.f1010c))) {
            return false;
        }
        boolean m1309h = m1309h();
        boolean m1309h2 = c0638fs.m1309h();
        if ((m1309h || m1309h2) && !(m1309h && m1309h2 && this.f1012d.equals(c0638fs.f1012d))) {
            return false;
        }
        boolean m1310i = m1310i();
        boolean m1310i2 = c0638fs.m1310i();
        if ((m1310i || m1310i2) && !(m1310i && m1310i2 && this.f1011d == c0638fs.f1011d)) {
            return false;
        }
        boolean m1311j = m1311j();
        boolean m1311j2 = c0638fs.m1311j();
        if (m1311j || m1311j2) {
            return m1311j && m1311j2 && this.f1013e == c0638fs.f1013e;
        }
        return true;
    }

    /* renamed from: b */
    public C0638fs m1291b(int i) {
        this.f1007b = i;
        m1298c(true);
        return this;
    }

    /* renamed from: b */
    public C0638fs m1292b(String str) {
        this.f1008b = str;
        return this;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1286a();
        abstractC0756kb.mo2360a(f993a);
        abstractC0756kb.mo2356a(f992a);
        abstractC0756kb.mo2352a(this.f1003a);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2356a(f994b);
        abstractC0756kb.mo2354a(this.f1004a);
        abstractC0756kb.mo2366b();
        abstractC0756kb.mo2356a(f995c);
        abstractC0756kb.mo2354a(this.f1007b);
        abstractC0756kb.mo2366b();
        if (this.f1005a != null) {
            abstractC0756kb.mo2356a(f996d);
            abstractC0756kb.mo2361a(this.f1005a);
            abstractC0756kb.mo2366b();
        }
        if (this.f1008b != null && m1305e()) {
            abstractC0756kb.mo2356a(f997e);
            abstractC0756kb.mo2361a(this.f1008b);
            abstractC0756kb.mo2366b();
        }
        if (m1307f()) {
            abstractC0756kb.mo2356a(f998f);
            abstractC0756kb.mo2354a(this.f1009c);
            abstractC0756kb.mo2366b();
        }
        if (this.f1010c != null && m1308g()) {
            abstractC0756kb.mo2356a(f999g);
            abstractC0756kb.mo2361a(this.f1010c);
            abstractC0756kb.mo2366b();
        }
        if (this.f1012d != null && m1309h()) {
            abstractC0756kb.mo2356a(f1000h);
            abstractC0756kb.mo2361a(this.f1012d);
            abstractC0756kb.mo2366b();
        }
        if (m1310i()) {
            abstractC0756kb.mo2356a(f1001i);
            abstractC0756kb.mo2354a(this.f1011d);
            abstractC0756kb.mo2366b();
        }
        if (m1311j()) {
            abstractC0756kb.mo2356a(f1002j);
            abstractC0756kb.mo2354a(this.f1013e);
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    /* renamed from: b */
    public void m1294b(boolean z) {
        this.f1006a.set(1, z);
    }

    /* renamed from: b */
    public boolean m1295b() {
        return this.f1006a.get(1);
    }

    /* renamed from: c */
    public C0638fs m1296c(int i) {
        this.f1009c = i;
        m1302d(true);
        return this;
    }

    /* renamed from: c */
    public C0638fs m1297c(String str) {
        this.f1010c = str;
        return this;
    }

    /* renamed from: c */
    public void m1298c(boolean z) {
        this.f1006a.set(2, z);
    }

    /* renamed from: c */
    public boolean m1299c() {
        return this.f1006a.get(2);
    }

    /* renamed from: d */
    public C0638fs m1300d(int i) {
        this.f1011d = i;
        m1304e(true);
        return this;
    }

    /* renamed from: d */
    public C0638fs m1301d(String str) {
        this.f1012d = str;
        return this;
    }

    /* renamed from: d */
    public void m1302d(boolean z) {
        this.f1006a.set(3, z);
    }

    /* renamed from: d */
    public boolean m1303d() {
        return this.f1005a != null;
    }

    /* renamed from: e */
    public void m1304e(boolean z) {
        this.f1006a.set(4, z);
    }

    /* renamed from: e */
    public boolean m1305e() {
        return this.f1008b != null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0638fs)) {
            return m1290a((C0638fs) obj);
        }
        return false;
    }

    /* renamed from: f */
    public void m1306f(boolean z) {
        this.f1006a.set(5, z);
    }

    /* renamed from: f */
    public boolean m1307f() {
        return this.f1006a.get(3);
    }

    /* renamed from: g */
    public boolean m1308g() {
        return this.f1010c != null;
    }

    /* renamed from: h */
    public boolean m1309h() {
        return this.f1012d != null;
    }

    public int hashCode() {
        return 0;
    }

    /* renamed from: i */
    public boolean m1310i() {
        return this.f1006a.get(4);
    }

    /* renamed from: j */
    public boolean m1311j() {
        return this.f1006a.get(5);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("StatsEvent(");
        sb.append("chid:");
        sb.append((int) this.f1003a);
        sb.append(", ");
        sb.append("type:");
        sb.append(this.f1004a);
        sb.append(", ");
        sb.append("value:");
        sb.append(this.f1007b);
        sb.append(", ");
        sb.append("connpt:");
        String str = this.f1005a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (m1305e()) {
            sb.append(", ");
            sb.append("host:");
            String str2 = this.f1008b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        if (m1307f()) {
            sb.append(", ");
            sb.append("subvalue:");
            sb.append(this.f1009c);
        }
        if (m1308g()) {
            sb.append(", ");
            sb.append("annotation:");
            String str3 = this.f1010c;
            if (str3 == null) {
                sb.append("null");
            } else {
                sb.append(str3);
            }
        }
        if (m1309h()) {
            sb.append(", ");
            sb.append("user:");
            String str4 = this.f1012d;
            if (str4 == null) {
                sb.append("null");
            } else {
                sb.append(str4);
            }
        }
        if (m1310i()) {
            sb.append(", ");
            sb.append("time:");
            sb.append(this.f1011d);
        }
        if (m1311j()) {
            sb.append(", ");
            sb.append("clientIp:");
            sb.append(this.f1013e);
        }
        sb.append(")");
        return sb.toString();
    }
}
