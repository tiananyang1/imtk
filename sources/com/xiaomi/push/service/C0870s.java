package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0646g;
import com.xiaomi.push.service.C0814at;
import java.util.Locale;

/* renamed from: com.xiaomi.push.service.s */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/s.class */
public class C0870s {

    /* renamed from: a */
    public final int f2707a;

    /* renamed from: a */
    public final String f2708a;

    /* renamed from: b */
    public final String f2709b;

    /* renamed from: c */
    public final String f2710c;

    /* renamed from: d */
    public final String f2711d;

    /* renamed from: e */
    public final String f2712e;

    /* renamed from: f */
    public final String f2713f;

    public C0870s(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        this.f2708a = str;
        this.f2709b = str2;
        this.f2710c = str3;
        this.f2711d = str4;
        this.f2712e = str5;
        this.f2713f = str6;
        this.f2707a = i;
    }

    /* renamed from: a */
    public static boolean m2790a() {
        try {
            return Class.forName("miui.os.Build").getField("IS_ALPHA_BUILD").getBoolean(null);
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m2791a(Context context) {
        return "com.xiaomi.xmsf".equals(context.getPackageName()) && m2790a();
    }

    /* renamed from: b */
    private static boolean m2792b(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }

    /* renamed from: a */
    public C0814at.b m2793a(XMPushService xMPushService) {
        C0814at.b bVar = new C0814at.b(xMPushService);
        m2794a(bVar, xMPushService, xMPushService.m2479b(), "c");
        return bVar;
    }

    /* renamed from: a */
    public C0814at.b m2794a(C0814at.b bVar, Context context, C0853d c0853d, String str) {
        bVar.f2499a = context.getPackageName();
        bVar.f2503b = this.f2708a;
        bVar.f2510h = this.f2710c;
        bVar.f2505c = this.f2709b;
        bVar.f2509g = "5";
        bVar.f2506d = "XMPUSH-PASS";
        bVar.f2501a = false;
        bVar.f2507e = String.format("%1$s:%2$s,%3$s:%4$s,%5$s:%6$s:%7$s:%8$s", "sdk_ver", 38, "cpvn", "3_6_12", "cpvc", 30612, "aapn", m2792b(context) ? C0646g.m1363b(context) : "");
        bVar.f2508f = String.format("%1$s:%2$s,%3$s:%4$s,%5$s:%6$s,sync:1", "appid", m2792b(context) ? "1000271" : this.f2711d, "locale", Locale.getDefault().toString(), Constants.EXTRA_KEY_MIID, C0865o.m2771a(context).m2777a());
        if (m2791a(context)) {
            bVar.f2508f += String.format(",%1$s:%2$s", "ab", str);
        }
        bVar.f2498a = c0853d;
        return bVar;
    }
}
