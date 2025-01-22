package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0646g;
import com.xiaomi.push.C0709ii;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0718ir;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0749jv;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.AbstractC0823bb;
import com.xiaomi.push.service.C0812ar;
import com.xiaomi.push.service.C0856g;
import com.xiaomi.push.service.C0858i;
import com.xiaomi.push.service.C0859j;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/* renamed from: com.xiaomi.mipush.sdk.w */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/w.class */
public class C0480w {

    /* renamed from: a */
    private static volatile C0480w f386a;

    /* renamed from: a */
    private Context f387a;

    /* renamed from: a */
    private final String f388a = "GeoFenceRegMessageProcessor.";

    private C0480w(Context context) {
        this.f387a = context;
    }

    /* renamed from: a */
    public static C0480w m391a(Context context) {
        if (f386a == null) {
            synchronized (C0480w.class) {
                try {
                    if (f386a == null) {
                        f386a = new C0480w(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f386a;
    }

    /* renamed from: a */
    private C0709ii m392a(C0732je c0732je, boolean z) {
        if (z && !C0859j.m2718a(this.f387a)) {
            return null;
        }
        if (z && !C0859j.m2721c(this.f387a)) {
            return null;
        }
        try {
            C0709ii c0709ii = new C0709ii();
            C0743jp.m2313a(c0709ii, c0732je.m2070a());
            return c0709ii;
        } catch (C0749jv e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private C0718ir m393a(boolean z) {
        C0718ir c0718ir = new C0718ir();
        TreeSet treeSet = new TreeSet();
        if (z) {
            Iterator<C0709ii> it = C0856g.m2692a(this.f387a).m2700a().iterator();
            while (it.hasNext()) {
                treeSet.add(it.next());
            }
        }
        c0718ir.m1862a(treeSet);
        return c0718ir;
    }

    /* renamed from: a */
    public static void m394a(Context context, boolean z) {
        C0732je c0732je = new C0732je(C0812ar.m2571a(), false);
        c0732je.m2071b(C0461d.m289a(context).m293a());
        c0732je.m2075c(EnumC0714in.GeoAuthorized.f1752a);
        c0732je.f2033a = new HashMap();
        c0732je.f2033a.put("permission_to_location", String.valueOf(z));
        C0449az.m224a(context).m254a((C0449az) c0732je, EnumC0696hw.Notification, false, (C0717iq) null);
    }

    /* renamed from: a */
    private void m395a(C0709ii c0709ii) {
        byte[] m2314a = C0743jp.m2314a(c0709ii);
        C0732je c0732je = new C0732je(C0812ar.m2571a(), false);
        c0732je.m2075c(EnumC0714in.GeoPackageUninstalled.f1752a);
        c0732je.m2062a(m2314a);
        C0449az.m224a(this.f387a).m254a((C0449az) c0732je, EnumC0696hw.Notification, true, (C0717iq) null);
        AbstractC0407b.m74c("GeoFenceRegMessageProcessor. report package not exist geo_fencing id:" + c0709ii.m1712a());
    }

    /* renamed from: a */
    private void m396a(C0709ii c0709ii, boolean z, boolean z2) {
        byte[] m2314a = C0743jp.m2314a(c0709ii);
        C0732je c0732je = new C0732je(C0812ar.m2571a(), false);
        c0732je.m2075c((z ? EnumC0714in.GeoRegsiterResult : EnumC0714in.GeoUnregsiterResult).f1752a);
        c0732je.m2062a(m2314a);
        if (z2) {
            c0732je.m2066a("permission_to_location", AbstractC0823bb.f2572b);
        }
        C0449az.m224a(this.f387a).m254a((C0449az) c0732je, EnumC0696hw.Notification, true, (C0717iq) null);
        StringBuilder sb = new StringBuilder();
        sb.append("GeoFenceRegMessageProcessor. report geo_fencing id:");
        sb.append(c0709ii.m1712a());
        sb.append(" ");
        sb.append(z ? "geo_reg" : "geo_unreg");
        sb.append("  isUnauthorized:");
        sb.append(z2);
        AbstractC0407b.m74c(sb.toString());
    }

    /* renamed from: a */
    private boolean m397a(C0732je c0732je) {
        return m398a(c0732je.m2064a()) && C0859j.m2722d(this.f387a);
    }

    /* renamed from: a */
    public static boolean m398a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        return TextUtils.equals("1", map.get("__geo_local_cache"));
    }

    /* renamed from: a */
    public void m399a(C0732je c0732je) {
        String str;
        boolean m397a = m397a(c0732je);
        C0709ii m392a = m392a(c0732je, m397a);
        if (m392a == null) {
            str = "GeoFenceRegMessageProcessor. registration convert geofence object failed notification_id:" + c0732je.m2063a();
        } else {
            if (!C0859j.m2723e(this.f387a)) {
                m396a(m392a, true, true);
                return;
            }
            if (!C0646g.m1365b(this.f387a, m392a.m1725c())) {
                if (m397a) {
                    m395a(m392a);
                    return;
                }
                return;
            } else {
                if (!m397a) {
                    m396a(m392a, true, false);
                    return;
                }
                if (C0856g.m2692a(this.f387a).m2697a(m392a) == -1) {
                    AbstractC0407b.m70a("GeoFenceRegMessageProcessor. insert a new geofence failed about geo_id:" + m392a.m1712a());
                }
                new C0481x(this.f387a).m404a(m392a);
                m396a(m392a, true, false);
                str = "GeoFenceRegMessageProcessor. receive geo reg notification";
            }
        }
        AbstractC0407b.m74c(str);
    }

    /* renamed from: b */
    public void m400b(C0732je c0732je) {
        boolean m397a = m397a(c0732je);
        C0709ii m392a = m392a(c0732je, m397a);
        if (m392a == null) {
            AbstractC0407b.m74c("GeoFenceRegMessageProcessor. unregistration convert geofence object failed notification_id:" + c0732je.m2063a());
            return;
        }
        if (!C0859j.m2723e(this.f387a)) {
            m396a(m392a, false, true);
            return;
        }
        if (!C0646g.m1365b(this.f387a, m392a.m1725c())) {
            if (m397a) {
                m395a(m392a);
                return;
            }
            return;
        }
        if (!m397a) {
            m396a(m392a, false, false);
            return;
        }
        if (C0856g.m2692a(this.f387a).m2695a(m392a.m1712a()) == 0) {
            AbstractC0407b.m70a("GeoFenceRegMessageProcessor. delete a geofence about geo_id:" + m392a.m1712a() + " falied");
        }
        if (C0858i.m2709a(this.f387a).m2714b(m392a.m1712a()) == 0) {
            AbstractC0407b.m70a("GeoFenceRegMessageProcessor. delete all geofence messages about geo_id:" + m392a.m1712a() + " failed");
        }
        new C0481x(this.f387a).m403a(m392a.m1712a());
        m396a(m392a, false, false);
        AbstractC0407b.m74c("GeoFenceRegMessageProcessor. receive geo unreg notification");
    }

    /* renamed from: c */
    public void m401c(C0732je c0732je) {
        if (C0859j.m2723e(this.f387a)) {
            boolean m397a = m397a(c0732je);
            if (!m397a || C0859j.m2718a(this.f387a)) {
                if ((!m397a || C0859j.m2721c(this.f387a)) && C0646g.m1365b(this.f387a, c0732je.f2040f)) {
                    C0718ir m393a = m393a(m397a);
                    byte[] m2314a = C0743jp.m2314a(m393a);
                    C0732je c0732je2 = new C0732je("-1", false);
                    c0732je2.m2075c(EnumC0714in.GeoUpload.f1752a);
                    c0732je2.m2062a(m2314a);
                    C0449az.m224a(this.f387a).m254a((C0449az) c0732je2, EnumC0696hw.Notification, true, (C0717iq) null);
                    AbstractC0407b.m74c("GeoFenceRegMessageProcessor. sync_geo_data. geos size:" + m393a.m1863a().size());
                }
            }
        }
    }
}
