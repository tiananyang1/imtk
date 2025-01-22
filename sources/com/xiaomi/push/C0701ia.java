package com.xiaomi.push;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.ia */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ia.class */
public class C0701ia implements InterfaceC0744jq<C0701ia, Object>, Serializable, Cloneable {

    /* renamed from: a */
    public List<C0702ib> f1494a;

    /* renamed from: a */
    private static final C0761kg f1493a = new C0761kg("ClientUploadData");

    /* renamed from: a */
    private static final C0752jy f1492a = new C0752jy("", (byte) 15, 1);

    /* renamed from: a */
    public int m1630a() {
        List<C0702ib> list = this.f1494a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0701ia c0701ia) {
        int m2322a;
        if (!getClass().equals(c0701ia.getClass())) {
            return getClass().getName().compareTo(c0701ia.getClass().getName());
        }
        int compareTo = Boolean.valueOf(m1634a()).compareTo(Boolean.valueOf(c0701ia.m1634a()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (!m1634a() || (m2322a = C0745jr.m2322a(this.f1494a, c0701ia.f1494a)) == 0) {
            return 0;
        }
        return m2322a;
    }

    /* renamed from: a */
    public void m1632a() {
        if (this.f1494a != null) {
            return;
        }
        throw new C0757kc("Required field 'uploadDataItems' was not present! Struct: " + toString());
    }

    /* renamed from: a */
    public void m1633a(C0702ib c0702ib) {
        if (this.f1494a == null) {
            this.f1494a = new ArrayList();
        }
        this.f1494a.add(c0702ib);
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: a */
    public void mo1287a(AbstractC0756kb abstractC0756kb) {
        abstractC0756kb.mo2346a();
        while (true) {
            C0752jy mo2342a = abstractC0756kb.mo2342a();
            if (mo2342a.f2324a == 0) {
                abstractC0756kb.mo2373g();
                m1632a();
                return;
            }
            if (mo2342a.f2326a == 1 && mo2342a.f2324a == 15) {
                C0753jz mo2343a = abstractC0756kb.mo2343a();
                this.f1494a = new ArrayList(mo2343a.f2328a);
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= mo2343a.f2328a) {
                        break;
                    }
                    C0702ib c0702ib = new C0702ib();
                    c0702ib.mo1287a(abstractC0756kb);
                    this.f1494a.add(c0702ib);
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
    public boolean m1634a() {
        return this.f1494a != null;
    }

    /* renamed from: a */
    public boolean m1635a(C0701ia c0701ia) {
        if (c0701ia == null) {
            return false;
        }
        boolean m1634a = m1634a();
        boolean m1634a2 = c0701ia.m1634a();
        if (m1634a || m1634a2) {
            return m1634a && m1634a2 && this.f1494a.equals(c0701ia.f1494a);
        }
        return true;
    }

    @Override // com.xiaomi.push.InterfaceC0744jq
    /* renamed from: b */
    public void mo1293b(AbstractC0756kb abstractC0756kb) {
        m1632a();
        abstractC0756kb.mo2360a(f1493a);
        if (this.f1494a != null) {
            abstractC0756kb.mo2356a(f1492a);
            abstractC0756kb.mo2357a(new C0753jz((byte) 12, this.f1494a.size()));
            Iterator<C0702ib> it = this.f1494a.iterator();
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
        if (obj != null && (obj instanceof C0701ia)) {
            return m1635a((C0701ia) obj);
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ClientUploadData(");
        sb.append("uploadDataItems:");
        List<C0702ib> list = this.f1494a;
        if (list == null) {
            sb.append("null");
        } else {
            sb.append(list);
        }
        sb.append(")");
        return sb.toString();
    }
}
