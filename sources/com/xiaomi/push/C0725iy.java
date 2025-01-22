package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.iy */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/iy.class */
public class C0725iy implements InterfaceC0744jq<C0725iy, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public List<C0707ig> f1937a;

    /* renamed from: a */
    private static final C0761kg f1936a = new C0761kg("XmPushActionCollectData");

    /* renamed from: a */
    private static final C0752jy f1935a = new C0752jy("", (byte) 15, 1);

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0725iy c0725iy) {
        int m2322a;
        if (!getClass().equals(c0725iy.getClass())) {
            return getClass().getName().compareTo(c0725iy.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1953a()).compareTo(Boolean.valueOf(c0725iy.m1953a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m1953a() || (m2322a = C0745jr.m2322a(this.f1937a, c0725iy.f1937a)) == 0) {
            return 0;
        }
        return m2322a;
    }

    /* renamed from: a */
    public C0725iy m1951a(List<C0707ig> list) {
        this.f1937a = list;
        return this;
    }

    /* renamed from: a */
    public void m1952a() {
        if (this.f1937a != null) {
            return;
        }
        throw new C0757kc("Required field 'dataCollectionItems' was not present! Struct: " + toString());
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1952a();
                return;
            }
            if (mo2342a.f2326a == 1 && mo2342a.f2324a == 15) {
                C0753jz mo2343a = abstractC0756kb.mo2343a();
                this.f1937a = new ArrayList(mo2343a.f2328a);
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= mo2343a.f2328a) {
                        break;
                    }
                    C0707ig c0707ig = new C0707ig();
                    c0707ig.mo1287a(abstractC0756kb);
                    this.f1937a.add(c0707ig);
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
    public boolean m1953a() {
        return this.f1937a != null;
    }

    /* renamed from: a */
    public boolean m1954a(C0725iy c0725iy) {
        if (c0725iy == null) {
            return false;
        }
        boolean m1953a = m1953a();
        boolean m1953a2 = c0725iy.m1953a();
        if (m1953a || m1953a2) {
            return m1953a && m1953a2 && this.f1937a.equals(c0725iy.f1937a);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1952a();
        abstractC0756kb.mo2360a(f1936a);
        if (this.f1937a != null) {
            abstractC0756kb.mo2356a(f1935a);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f1937a.size()));
            Iterator<C0707ig> it = this.f1937a.iterator();
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
        if (obj != null && (obj instanceof C0725iy)) {
            return m1954a((C0725iy) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("XmPushActionCollectData(");
        sb.append("dataCollectionItems:");
        List<C0707ig> list = this.f1937a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
