package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.C0461d;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0646g;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0722iv;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0733jf;
import com.xiaomi.push.C0734jg;
import com.xiaomi.push.C0739jl;
import com.xiaomi.push.C0740jm;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0770n;
import com.xiaomi.push.EnumC0636fq;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.EnumC0719is;
import com.xiaomi.push.service.C0812ar;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MiPushClient4Hybrid.class */
public class MiPushClient4Hybrid {

    /* renamed from: a */
    private static HybridProvider f252a;

    /* renamed from: a */
    private static MiPushClientCallbackV2 f253a;

    /* renamed from: a */
    private static Map<String, C0461d.a> f254a = new HashMap();

    /* renamed from: b */
    private static Map<String, Long> f255b = new HashMap();

    /* renamed from: a */
    private static short m130a(MiPushMessage miPushMessage, String str) {
        String str2 = miPushMessage.getExtra() == null ? "" : miPushMessage.getExtra().get(Constants.EXTRA_KEY_HYBRID_DEVICE_STATUS);
        int i = 0;
        if (!TextUtils.isEmpty(str2)) {
            i = Integer.valueOf(str2).intValue();
        }
        HybridProvider hybridProvider = f252a;
        int i2 = i;
        if (hybridProvider != null) {
            i2 = i;
            if (!hybridProvider.isAllowNotification(str)) {
                i2 = (i & (-4)) + C0646g.a.NOT_ALLOWED.m1368a();
            }
        }
        return (short) i2;
    }

    /* renamed from: a */
    private static void m131a(Context context, MiPushMessage miPushMessage) {
        Intent parseUri;
        String str = miPushMessage.getExtra().get("web_uri");
        String str2 = miPushMessage.getExtra().get("intent_uri");
        if (TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                try {
                    parseUri = Intent.parseUri(str2, 0);
                } catch (URISyntaxException e) {
                    AbstractC0407b.m71a("intent uri parse failed", e);
                }
            }
            parseUri = null;
        } else {
            parseUri = new Intent("android.intent.action.VIEW");
            parseUri.setData(Uri.parse(str));
        }
        if (parseUri == null) {
            AbstractC0407b.m70a("web uri and intent uri all are empty");
            return;
        }
        parseUri.addFlags(268435456);
        try {
            context.startActivity(parseUri);
        } catch (Throwable th) {
            AbstractC0407b.m71a("start activity failed from web uri or intent uri", th);
        }
    }

    /* renamed from: a */
    private static void m132a(Context context, MiPushMessage miPushMessage, String str, short s) {
        if (miPushMessage == null || miPushMessage.getExtra() == null) {
            AbstractC0407b.m70a("do not ack message, message is null");
            return;
        }
        try {
            try {
                C0722iv c0722iv = new C0722iv();
                c0722iv.m1898b(C0461d.m289a(context).m293a());
                c0722iv.m1892a(miPushMessage.getMessageId());
                c0722iv.m1891a(Long.valueOf(miPushMessage.getExtra().get(Constants.EXTRA_KEY_HYBRID_MESSAGE_TS)).longValue());
                c0722iv.m1893a(s);
                if (!TextUtils.isEmpty(miPushMessage.getTopic())) {
                    c0722iv.m1902c(miPushMessage.getTopic());
                }
                C0449az.m224a(context).m254a((C0449az) c0722iv, EnumC0696hw.AckMessage, false, PushMessageHelper.generateMessage(miPushMessage));
                AbstractC0407b.m73b("MiPushClient4Hybrid ack mina message, app is :" + str + ", messageId is " + miPushMessage.getMessageId());
            } catch (Throwable th) {
                AbstractC0407b.m72a(th);
            }
        } finally {
            miPushMessage.getExtra().remove(Constants.EXTRA_KEY_HYBRID_MESSAGE_TS);
            miPushMessage.getExtra().remove(Constants.EXTRA_KEY_HYBRID_DEVICE_STATUS);
        }
    }

    /* renamed from: a */
    private static void m133a(Context context, String str) {
        context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_pull_notification_" + str, System.currentTimeMillis()).commit();
    }

    /* renamed from: a */
    private static boolean m134a(Context context, String str) {
        boolean z = false;
        if (Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification_" + str, -1L)) > 300000) {
            z = true;
        }
        return z;
    }

    /* renamed from: a */
    private static boolean m135a(MiPushMessage miPushMessage) {
        return TextUtils.equals(miPushMessage.getExtra() == null ? "" : miPushMessage.getExtra().get(Constants.EXTRA_KEY_PUSH_SERVER_ACTION), Constants.EXTRA_VALUE_PLATFORM_MESSAGE);
    }

    public static void ackMessage(Context context, MiPushMessage miPushMessage) {
        if (miPushMessage == null || miPushMessage.getExtra() == null) {
            AbstractC0407b.m70a("do not ack message, message is null");
        } else {
            String str = miPushMessage.getExtra().get(Constants.EXTRA_KEY_HYBRID_PKGNAME);
            m132a(context, miPushMessage, str, m130a(miPushMessage, str));
        }
    }

    public static boolean isRegistered(Context context, String str) {
        return C0461d.m289a(context).m292a(str) != null;
    }

    public static void onNotificationMessageArrived(Context context, String str, MiPushMessage miPushMessage) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ackMessage(context, miPushMessage);
        MiPushClientCallbackV2 miPushClientCallbackV2 = f253a;
        if (miPushClientCallbackV2 != null) {
            miPushClientCallbackV2.onNotificationMessageArrived(str, miPushMessage);
        }
    }

    public static void onNotificationMessageClicked(Context context, String str, MiPushMessage miPushMessage) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (m135a(miPushMessage)) {
            m131a(context, miPushMessage);
            return;
        }
        MiPushClientCallbackV2 miPushClientCallbackV2 = f253a;
        if (miPushClientCallbackV2 != null) {
            miPushClientCallbackV2.onNotificationMessageClicked(str, miPushMessage);
        }
    }

    public static void onPlatformNotificationMessageArrived(Context context, MiPushMessage miPushMessage, boolean z) {
        int intValue = Integer.valueOf(miPushMessage.getExtra().get(Constants.EXTRA_KEY_HYBRID_DEVICE_STATUS)).intValue();
        int i = intValue;
        if (!z) {
            i = C0646g.a.NOT_ALLOWED.m1368a() + (intValue & (-4));
        }
        m132a(context, miPushMessage, context.getPackageName(), (short) i);
    }

    public static void onReceivePassThroughMessage(Context context, String str, MiPushMessage miPushMessage) {
        MiPushClientCallbackV2 miPushClientCallbackV2;
        if (TextUtils.isEmpty(str) || (miPushClientCallbackV2 = f253a) == null) {
            return;
        }
        miPushClientCallbackV2.onReceivePassThroughMessage(str, miPushMessage);
    }

    public static void onReceiveRegisterResult(Context context, C0734jg c0734jg) {
        C0461d.a aVar;
        String m2155b = c0734jg.m2155b();
        if (c0734jg.m2149a() == 0 && (aVar = f254a.get(m2155b)) != null) {
            aVar.m320a(c0734jg.f2132e, c0734jg.f2133f);
            C0461d.m289a(context).m297a(m2155b, aVar);
        }
        ArrayList arrayList = null;
        if (!TextUtils.isEmpty(c0734jg.f2132e)) {
            arrayList = new ArrayList();
            arrayList.add(c0734jg.f2132e);
        }
        MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_REGISTER.f934a, arrayList, c0734jg.f2122a, c0734jg.f2131d, null);
        MiPushClientCallbackV2 miPushClientCallbackV2 = f253a;
        if (miPushClientCallbackV2 != null) {
            miPushClientCallbackV2.onReceiveRegisterResult(m2155b, generateCommandMessage);
        }
    }

    public static void onReceiveUnregisterResult(Context context, C0740jm c0740jm) {
        MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_UNREGISTER.f934a, null, c0740jm.f2255a, c0740jm.f2263d, null);
        String m2265a = c0740jm.m2265a();
        MiPushClientCallbackV2 miPushClientCallbackV2 = f253a;
        if (miPushClientCallbackV2 != null) {
            miPushClientCallbackV2.onReceiveUnregisterResult(m2265a, generateCommandMessage);
        }
    }

    public static void registerPush(Context context, String str, String str2, String str3) {
        if (C0461d.m289a(context).m302a(str2, str3, str)) {
            ArrayList arrayList = new ArrayList();
            C0461d.a m292a = C0461d.m289a(context).m292a(str);
            if (m292a != null) {
                arrayList.add(m292a.f350c);
                MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_REGISTER.f934a, arrayList, 0L, null, null);
                MiPushClientCallbackV2 miPushClientCallbackV2 = f253a;
                if (miPushClientCallbackV2 != null) {
                    miPushClientCallbackV2.onReceiveRegisterResult(str, generateCommandMessage);
                }
            }
            if (m134a(context, str)) {
                C0732je c0732je = new C0732je();
                c0732je.m2071b(str2);
                c0732je.m2075c(EnumC0714in.PullOfflineMessage.f1752a);
                c0732je.m2058a(C0812ar.m2571a());
                c0732je.m2061a(false);
                C0449az.m224a(context).m257a(c0732je, EnumC0696hw.Notification, false, true, null, false, str, str2);
                AbstractC0407b.m73b("MiPushClient4Hybrid pull offline pass through message");
                m133a(context, str);
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - (f255b.get(str) != null ? f255b.get(str).longValue() : 0L)) < 5000) {
            AbstractC0407b.m70a("MiPushClient4Hybrid  Could not send register message within 5s repeatedly.");
            return;
        }
        f255b.put(str, Long.valueOf(currentTimeMillis));
        String m520a = C0509ay.m520a(6);
        C0461d.a aVar = new C0461d.a(context);
        aVar.m327c(str2, str3, m520a);
        f254a.put(str, aVar);
        C0733jf c0733jf = new C0733jf();
        c0733jf.m2097a(C0812ar.m2571a());
        c0733jf.m2104b(str2);
        c0733jf.m2116e(str3);
        c0733jf.m2113d(str);
        c0733jf.m2119f(m520a);
        c0733jf.m2109c(C0646g.m1359a(context, context.getPackageName()));
        c0733jf.m2103b(C0646g.m1356a(context, context.getPackageName()));
        c0733jf.m2122g("3_6_12");
        c0733jf.m2095a(30612);
        c0733jf.m2125h(C0727j.m1992e(context));
        c0733jf.m2096a(EnumC0719is.Init);
        if (!C0770n.m2408d()) {
            String m1994g = C0727j.m1994g(context);
            if (!TextUtils.isEmpty(m1994g)) {
                if (C0770n.m2406b()) {
                    c0733jf.m2127i(m1994g);
                }
                c0733jf.m2131k(C0509ay.m521a(m1994g));
            }
        }
        c0733jf.m2129j(C0727j.m1980a());
        int m1979a = C0727j.m1979a();
        if (m1979a >= 0) {
            c0733jf.m2108c(m1979a);
        }
        C0732je c0732je2 = new C0732je();
        c0732je2.m2075c(EnumC0714in.HybridRegister.f1752a);
        c0732je2.m2071b(C0461d.m289a(context).m293a());
        c0732je2.m2079d(context.getPackageName());
        c0732je2.m2062a(C0743jp.m2314a(c0733jf));
        c0732je2.m2058a(C0812ar.m2571a());
        C0449az.m224a(context).m252a((C0449az) c0732je2, EnumC0696hw.Notification, (C0717iq) null);
    }

    public static void removeDuplicateCache(Context context, MiPushMessage miPushMessage) {
        String str = miPushMessage.getExtra() != null ? miPushMessage.getExtra().get("jobkey") : null;
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            str2 = miPushMessage.getMessageId();
        }
        C0446aw.m206a(context, str2);
    }

    public static void setCallback(MiPushClientCallbackV2 miPushClientCallbackV2) {
        f253a = miPushClientCallbackV2;
    }

    public static void setProvider(HybridProvider hybridProvider) {
        f252a = hybridProvider;
    }

    public static void unregisterPush(Context context, String str) {
        f255b.remove(str);
        C0461d.a m292a = C0461d.m289a(context).m292a(str);
        if (m292a == null) {
            return;
        }
        C0739jl c0739jl = new C0739jl();
        c0739jl.m2243a(C0812ar.m2571a());
        c0739jl.m2253d(str);
        c0739jl.m2248b(m292a.f346a);
        c0739jl.m2251c(m292a.f350c);
        c0739jl.m2255e(m292a.f348b);
        C0732je c0732je = new C0732je();
        c0732je.m2075c(EnumC0714in.HybridUnregister.f1752a);
        c0732je.m2071b(C0461d.m289a(context).m293a());
        c0732je.m2079d(context.getPackageName());
        c0732je.m2062a(C0743jp.m2314a(c0739jl));
        c0732je.m2058a(C0812ar.m2571a());
        C0449az.m224a(context).m252a((C0449az) c0732je, EnumC0696hw.Notification, (C0717iq) null);
        C0461d.m289a(context).m305b(str);
        MIPushNotificationHelper4Hybrid.clearNotification(context, str);
    }
}
