package com.xiaomi.mipush.sdk;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0620fa;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0770n;
import com.xiaomi.push.EnumC0622fc;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.InterfaceC0744jq;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.C0812ar;
import java.util.HashMap;

/* renamed from: com.xiaomi.mipush.sdk.o */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/o.class */
public class C0472o {
    /* renamed from: a */
    public static void m366a(Context context, Intent intent, Uri uri) {
        C0620fa m1220a;
        EnumC0622fc enumC0622fc;
        if (context == null) {
            return;
        }
        C0449az.m224a(context).m245a();
        if (C0620fa.m1220a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m1224a() == null) {
            C0620fa.m1220a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m1231a(C0461d.m289a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m293a(), context.getPackageName(), C0809ao.m2557a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m2561a(EnumC0703ic.AwakeInfoUploadWaySwitch.m1669a(), 0), new C0462e());
        }
        if ((context instanceof Activity) && intent != null) {
            m1220a = C0620fa.m1220a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
            enumC0622fc = EnumC0622fc.ACTIVITY;
        } else {
            if (!(context instanceof Service) || intent == null) {
                if (uri == null || TextUtils.isEmpty(uri.toString())) {
                    return;
                }
                C0620fa.m1220a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m1228a(EnumC0622fc.PROVIDER, context, (Intent) null, uri.toString());
                return;
            }
            if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                m1220a = C0620fa.m1220a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                enumC0622fc = EnumC0622fc.SERVICE_COMPONENT;
            } else {
                m1220a = C0620fa.m1220a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                enumC0622fc = EnumC0622fc.SERVICE_ACTION;
            }
        }
        m1220a.m1228a(enumC0622fc, context, intent, (String) null);
    }

    /* renamed from: a */
    private static void m367a(Context context, C0732je c0732je) {
        boolean m2563a = C0809ao.m2557a(context).m2563a(EnumC0703ic.AwakeAppPingSwitch.m1669a(), false);
        int m2561a = C0809ao.m2557a(context).m2561a(EnumC0703ic.AwakeAppPingFrequency.m1669a(), 0);
        int i = m2561a;
        if (m2561a >= 0) {
            i = m2561a;
            if (m2561a < 30) {
                AbstractC0407b.m74c("aw_ping: frquency need > 30s.");
                i = 30;
            }
        }
        if (i < 0) {
            m2563a = false;
        }
        if (!C0770n.m2403a()) {
            m368a(context, c0732je, m2563a, i);
        } else if (m2563a) {
            C0493ai.m439a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m446a((C0493ai.a) new C0473p(c0732je, context), i);
        }
    }

    /* renamed from: a */
    public static final <T extends InterfaceC0744jq<T, ?>> void m368a(Context context, T t, boolean z, int i) {
        byte[] m2314a = C0743jp.m2314a(t);
        if (m2314a == null) {
            AbstractC0407b.m70a("send message fail, because msgBytes is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_help_ping");
        intent.putExtra("extra_help_ping_switch", z);
        intent.putExtra("extra_help_ping_frequency", i);
        intent.putExtra("mipush_payload", m2314a);
        intent.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
        C0449az.m224a(context).m248a(intent);
    }

    /* renamed from: a */
    public static void m369a(Context context, String str) {
        AbstractC0407b.m70a("aw_ping : send aw_ping cmd and content to push service from 3rd app");
        HashMap hashMap = new HashMap();
        hashMap.put("awake_info", str);
        hashMap.put("event_type", String.valueOf(9999));
        hashMap.put("description", "ping message");
        C0732je c0732je = new C0732je();
        c0732je.m2071b(C0461d.m289a(context).m293a());
        c0732je.m2079d(context.getPackageName());
        c0732je.m2075c(EnumC0714in.AwakeAppResponse.f1752a);
        c0732je.m2058a(C0812ar.m2571a());
        c0732je.f2033a = hashMap;
        m367a(context, c0732je);
    }

    /* renamed from: a */
    public static void m370a(Context context, String str, int i, String str2) {
        C0732je c0732je = new C0732je();
        c0732je.m2071b(str);
        c0732je.m2060a(new HashMap());
        c0732je.m2064a().put("extra_aw_app_online_cmd", String.valueOf(i));
        c0732je.m2064a().put("extra_help_aw_info", str2);
        c0732je.m2058a(C0812ar.m2571a());
        byte[] m2314a = C0743jp.m2314a(c0732je);
        if (m2314a == null) {
            AbstractC0407b.m70a("send message fail, because msgBytes is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_aw_app_logic");
        intent.putExtra("mipush_payload", m2314a);
        C0449az.m224a(context).m248a(intent);
    }
}
