package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.service.C0814at;
import java.util.HashMap;

/* renamed from: com.xiaomi.push.fu */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fu.class */
class C0640fu {
    /* renamed from: a */
    public static void m1319a(C0814at.b bVar, String str, AbstractC0649gc abstractC0649gc) {
        String m516a;
        C0612et.c cVar = new C0612et.c();
        if (!TextUtils.isEmpty(bVar.f2505c)) {
            cVar.m1072a(bVar.f2505c);
        }
        if (!TextUtils.isEmpty(bVar.f2507e)) {
            cVar.m1081d(bVar.f2507e);
        }
        if (!TextUtils.isEmpty(bVar.f2508f)) {
            cVar.m1084e(bVar.f2508f);
        }
        cVar.m1075b(bVar.f2501a ? "1" : "0");
        if (TextUtils.isEmpty(bVar.f2506d)) {
            cVar.m1078c("XIAOMI-SASL");
        } else {
            cVar.m1078c(bVar.f2506d);
        }
        C0641fv c0641fv = new C0641fv();
        c0641fv.m1342c(bVar.f2503b);
        c0641fv.m1328a(Integer.parseInt(bVar.f2509g));
        c0641fv.m1339b(bVar.f2499a);
        c0641fv.m1331a("BIND", (String) null);
        c0641fv.m1330a(c0641fv.m1343e());
        AbstractC0407b.m70a("[Slim]: bind id=" + c0641fv.m1343e());
        HashMap hashMap = new HashMap();
        hashMap.put("challenge", str);
        hashMap.put("token", bVar.f2505c);
        hashMap.put("chid", bVar.f2509g);
        hashMap.put("from", bVar.f2503b);
        hashMap.put("id", c0641fv.m1343e());
        hashMap.put("to", "xiaomi.com");
        if (bVar.f2501a) {
            hashMap.put("kick", "1");
        } else {
            hashMap.put("kick", "0");
        }
        if (TextUtils.isEmpty(bVar.f2507e)) {
            hashMap.put("client_attrs", "");
        } else {
            hashMap.put("client_attrs", bVar.f2507e);
        }
        if (TextUtils.isEmpty(bVar.f2508f)) {
            hashMap.put("cloud_attrs", "");
        } else {
            hashMap.put("cloud_attrs", bVar.f2508f);
        }
        if (bVar.f2506d.equals("XIAOMI-PASS") || bVar.f2506d.equals("XMPUSH-PASS")) {
            m516a = C0507aw.m516a(bVar.f2506d, null, hashMap, bVar.f2510h);
        } else {
            bVar.f2506d.equals("XIAOMI-SASL");
            m516a = null;
        }
        cVar.m1087f(m516a);
        c0641fv.m1333a(cVar.mo959a(), (String) null);
        abstractC0649gc.mo1382b(c0641fv);
    }

    /* renamed from: a */
    public static void m1320a(String str, String str2, AbstractC0649gc abstractC0649gc) {
        C0641fv c0641fv = new C0641fv();
        c0641fv.m1342c(str2);
        c0641fv.m1328a(Integer.parseInt(str));
        c0641fv.m1331a("UBND", (String) null);
        abstractC0649gc.mo1382b(c0641fv);
    }
}
