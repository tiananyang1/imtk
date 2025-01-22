package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.jc */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jc.class */
public class C0730jc implements InterfaceC0744jq<C0730jc, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public List<C0715io> f2008a;

    /* renamed from: a */
    private static final C0761kg f2007a = new C0761kg("XmPushActionCustomConfig");

    /* renamed from: a */
    private static final C0752jy f2006a = new C0752jy("", (byte) 15, 1);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0730jc c0730jc) {
        int m2322a;
        if (!getClass().equals(c0730jc.getClass())) {
            return getClass().getName().compareTo(c0730jc.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m2050a()).compareTo(Boolean.valueOf(c0730jc.m2050a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m2050a() || (m2322a = C0745jr.m2322a(this.f2008a, c0730jc.f2008a)) == 0) {
            return 0;
        }
        return m2322a;
    }

    /* renamed from: a */
    public List<C0715io> m2048a() {
        return this.f2008a;
    }

    /* renamed from: a */
    public void m2049a() {
        if (this.f2008a != null) {
            return;
        }
        throw new C0757kc("Required field 'customConfigs' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m2049a();
                return;
            }
            if (mo2342a.f2326a == 1 && mo2342a.f2324a == 15) {
                C0753jz mo2343a = abstractC0756kb.mo2343a();
                this.f2008a = new ArrayList(mo2343a.f2328a);
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= mo2343a.f2328a) {
                        break;
                    }
                    C0715io c0715io = new C0715io();
                    c0715io.mo1287a(abstractC0756kb);
                    this.f2008a.add(c0715io);
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
    public boolean m2050a() {
        return this.f2008a != null;
    }

    /* renamed from: a */
    public boolean m2051a(C0730jc c0730jc) {
        if (c0730jc == null) {
            return false;
        }
        boolean m2050a = m2050a();
        boolean m2050a2 = c0730jc.m2050a();
        if (m2050a || m2050a2) {
            return m2050a && m2050a2 && this.f2008a.equals(c0730jc.f2008a);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m2049a();
        abstractC0756kb.mo2360a(f2007a);
        if (this.f2008a != null) {
            abstractC0756kb.mo2356a(f2006a);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f2008a.size()));
            Iterator<C0715io> it = this.f2008a.iterator();
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
        if (obj != null && (obj instanceof C0730jc)) {
            return m2051a((C0730jc) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCustomConfig(");
        sb.append("customConfigs:");
        List<C0715io> list = this.f2008a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
