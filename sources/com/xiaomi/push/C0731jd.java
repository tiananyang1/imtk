package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.jd */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jd.class */
public class C0731jd implements InterfaceC0744jq<C0731jd, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public List<C0713im> f2011a;

    /* renamed from: a */
    private static final C0761kg f2010a = new C0761kg("XmPushActionNormalConfig");

    /* renamed from: a */
    private static final C0752jy f2009a = new C0752jy("", (byte) 15, 1);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0731jd c0731jd) {
        int m2322a;
        if (!getClass().equals(c0731jd.getClass())) {
            return getClass().getName().compareTo(c0731jd.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2055a()).compareTo(Boolean.valueOf(c0731jd.m2055a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m2055a() || (m2322a = C0745jr.m2322a(this.f2011a, c0731jd.f2011a)) == 0) {
            return 0;
        }
        return m2322a;
    }

    /* renamed from: a */
    public List<C0713im> m2053a() {
        return this.f2011a;
    }

    /* renamed from: a */
    public void m2054a() {
        if (this.f2011a != null) {
            return;
        }
        throw new C0757kc("Required field 'normalConfigs' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m2054a();
                return;
            }
            if (mo2342a.f2326a == 1 && mo2342a.f2324a == 15) {
                C0753jz mo2343a = abstractC0756kb.mo2343a();
                this.f2011a = new ArrayList(mo2343a.f2328a);
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= mo2343a.f2328a) {
                        break;
                    }
                    C0713im c0713im = new C0713im();
                    c0713im.mo1287a(abstractC0756kb);
                    this.f2011a.add(c0713im);
                    i = i2 + 1;
                }
                abstractC0756kb.mo2376j();
            } else {
                C0759ke.m2384a(abstractC0756kb, mo2342a.f2324a);
            }
            abstractC0756kb.mo2374h();
        }
    }

    /* renamed from: a */
    public boolean m2055a() {
        return this.f2011a != null;
    }

    /* renamed from: a */
    public boolean m2056a(C0731jd c0731jd) {
        if (c0731jd == null) {
            return false;
        }
        boolean m2055a = m2055a();
        boolean m2055a2 = c0731jd.m2055a();
        if (m2055a || m2055a2) {
            return m2055a && m2055a2 && this.f2011a.equals(c0731jd.f2011a);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2054a();
        abstractC0756kb.mo2360a(f2010a);
        if (this.f2011a != null) {
            abstractC0756kb.mo2356a(f2009a);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f2011a.size()));
            Iterator<C0713im> it = this.f2011a.iterator();
            while (it.hasNext()) {
                it.next().mo1293b(abstractC0756kb);
            }
            abstractC0756kb.mo2371e();
            abstractC0756kb.mo2366b();
        }
        abstractC0756kb.mo2368c();
        abstractC0756kb.mo2351a();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0731jd)) {
            return m2056a((C0731jd) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionNormalConfig(");
        sb.append("normalConfigs:");
        List<C0713im> list = this.f2011a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
