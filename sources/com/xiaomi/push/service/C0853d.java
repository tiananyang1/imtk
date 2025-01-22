package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.AbstractC0666gt;
import com.xiaomi.push.C0641fv;
import com.xiaomi.push.C0664gr;
import com.xiaomi.push.C0665gs;
import com.xiaomi.push.C0668gv;
import com.xiaomi.push.service.C0814at;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.d */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/d.class */
public class C0853d {

    /* renamed from: a */
    private C0875x f2640a = new C0875x();

    /* renamed from: a */
    public static String m2675a(C0814at.b bVar) {
        StringBuilder sb;
        String str;
        if ("9".equals(bVar.f2509g)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(bVar.f2499a);
            sb = sb2;
            str = ".permission.MIMC_RECEIVE";
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(bVar.f2499a);
            str = ".permission.MIPUSH_RECEIVE";
            sb = sb3;
        }
        sb.append(str);
        return sb.toString();
    }

    /* renamed from: a */
    private static void m2676a(Context context, Intent intent, C0814at.b bVar) {
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, m2675a(bVar));
        }
    }

    /* renamed from: a */
    C0814at.b m2677a(C0641fv c0641fv) {
        Collection<C0814at.b> m2583a = C0814at.m2578a().m2583a(Integer.toString(c0641fv.m1324a()));
        if (m2583a.isEmpty()) {
            return null;
        }
        Iterator<C0814at.b> it = m2583a.iterator();
        if (m2583a.size() == 1) {
            return it.next();
        }
        String m1345g = c0641fv.m1345g();
        while (it.hasNext()) {
            C0814at.b next = it.next();
            if (TextUtils.equals(m1345g, next.f2503b)) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: a */
    C0814at.b m2678a(AbstractC0666gt abstractC0666gt) {
        Collection<C0814at.b> m2583a = C0814at.m2578a().m2583a(abstractC0666gt.m1492k());
        if (m2583a.isEmpty()) {
            return null;
        }
        Iterator<C0814at.b> it = m2583a.iterator();
        if (m2583a.size() == 1) {
            return it.next();
        }
        String m1496m = abstractC0666gt.m1496m();
        String m1494l = abstractC0666gt.m1494l();
        while (it.hasNext()) {
            C0814at.b next = it.next();
            if (TextUtils.equals(m1496m, next.f2503b) || TextUtils.equals(m1494l, next.f2503b)) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m2679a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.service_started");
        context.sendBroadcast(intent);
    }

    /* renamed from: a */
    public void m2680a(Context context, C0814at.b bVar, int i) {
        if ("5".equalsIgnoreCase(bVar.f2509g)) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_closed");
        intent.setPackage(bVar.f2499a);
        intent.putExtra(AbstractC0818ax.f2554r, bVar.f2509g);
        intent.putExtra("ext_reason", i);
        intent.putExtra(AbstractC0818ax.f2552p, bVar.f2503b);
        intent.putExtra(AbstractC0818ax.f2529C, bVar.f2511i);
        if (bVar.f2493a == null || !"9".equals(bVar.f2509g)) {
            m2676a(context, intent, bVar);
            return;
        }
        try {
            bVar.f2493a.send(Message.obtain(null, 17, intent));
        } catch (RemoteException e) {
            bVar.f2493a = null;
            AbstractC0407b.m70a("peer may died: " + bVar.f2503b.substring(bVar.f2503b.lastIndexOf(64)));
        }
    }

    /* renamed from: a */
    public void m2681a(Context context, C0814at.b bVar, String str, String str2) {
        if ("5".equalsIgnoreCase(bVar.f2509g)) {
            AbstractC0407b.m75d("mipush kicked by server");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.kicked");
        intent.setPackage(bVar.f2499a);
        intent.putExtra("ext_kick_type", str);
        intent.putExtra("ext_kick_reason", str2);
        intent.putExtra("ext_chid", bVar.f2509g);
        intent.putExtra(AbstractC0818ax.f2552p, bVar.f2503b);
        intent.putExtra(AbstractC0818ax.f2529C, bVar.f2511i);
        m2676a(context, intent, bVar);
    }

    /* renamed from: a */
    public void m2682a(Context context, C0814at.b bVar, boolean z, int i, String str) {
        if ("5".equalsIgnoreCase(bVar.f2509g)) {
            this.f2640a.m2843a(context, bVar, z, i, str);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_opened");
        intent.setPackage(bVar.f2499a);
        intent.putExtra("ext_succeeded", z);
        if (!z) {
            intent.putExtra("ext_reason", i);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ext_reason_msg", str);
        }
        intent.putExtra("ext_chid", bVar.f2509g);
        intent.putExtra(AbstractC0818ax.f2552p, bVar.f2503b);
        intent.putExtra(AbstractC0818ax.f2529C, bVar.f2511i);
        m2676a(context, intent, bVar);
    }

    /* renamed from: a */
    public void m2683a(XMPushService xMPushService, String str, C0641fv c0641fv) {
        C0814at.b m2677a = m2677a(c0641fv);
        if (m2677a == null) {
            AbstractC0407b.m75d("error while notify channel closed! channel " + str + " not registered");
            return;
        }
        if ("5".equalsIgnoreCase(str)) {
            this.f2640a.m2844a(xMPushService, c0641fv, m2677a);
            return;
        }
        String str2 = m2677a.f2499a;
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.new_msg");
        intent.setPackage(str2);
        intent.putExtra("ext_chid", str);
        intent.putExtra("ext_raw_packet", c0641fv.m1336a(m2677a.f2510h));
        intent.putExtra(AbstractC0818ax.f2529C, m2677a.f2511i);
        intent.putExtra(AbstractC0818ax.f2558v, m2677a.f2510h);
        if (m2677a.f2493a != null) {
            try {
                m2677a.f2493a.send(Message.obtain(null, 17, intent));
                return;
            } catch (RemoteException e) {
                m2677a.f2493a = null;
                AbstractC0407b.m70a("peer may died: " + m2677a.f2503b.substring(m2677a.f2503b.lastIndexOf(64)));
            }
        }
        if ("com.xiaomi.xmsf".equals(str2)) {
            return;
        }
        m2676a(xMPushService, intent, m2677a);
    }

    /* renamed from: a */
    public void m2684a(XMPushService xMPushService, String str, AbstractC0666gt abstractC0666gt) {
        String str2;
        String str3;
        C0814at.b m2678a = m2678a(abstractC0666gt);
        if (m2678a != null) {
            if ("5".equalsIgnoreCase(str)) {
                this.f2640a.m2845a(xMPushService, abstractC0666gt, m2678a);
                return;
            }
            String str4 = m2678a.f2499a;
            if (abstractC0666gt instanceof C0665gs) {
                str3 = "com.xiaomi.push.new_msg";
            } else if (abstractC0666gt instanceof C0664gr) {
                str3 = "com.xiaomi.push.new_iq";
            } else if (abstractC0666gt instanceof C0668gv) {
                str3 = "com.xiaomi.push.new_pres";
            } else {
                str2 = "unknown packet type, drop it";
            }
            Intent intent = new Intent();
            intent.setAction(str3);
            intent.setPackage(str4);
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_packet", abstractC0666gt.mo1454a());
            intent.putExtra(AbstractC0818ax.f2529C, m2678a.f2511i);
            intent.putExtra(AbstractC0818ax.f2558v, m2678a.f2510h);
            m2676a(xMPushService, intent, m2678a);
            return;
        }
        str2 = "error while notify channel closed! channel " + str + " not registered";
        AbstractC0407b.m75d(str2);
    }
}
