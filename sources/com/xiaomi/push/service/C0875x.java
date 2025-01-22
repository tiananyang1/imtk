package com.xiaomi.push.service;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.mipush.sdk.MIPushNotificationHelper4Hybrid;
import com.xiaomi.push.AbstractC0666gt;
import com.xiaomi.push.C0574di;
import com.xiaomi.push.C0631fl;
import com.xiaomi.push.C0641fv;
import com.xiaomi.push.C0646g;
import com.xiaomi.push.C0660gn;
import com.xiaomi.push.C0663gq;
import com.xiaomi.push.C0665gs;
import com.xiaomi.push.C0681hh;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0722iv;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0769m;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.C0803ai;
import com.xiaomi.push.service.C0814at;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* renamed from: com.xiaomi.push.service.x */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/x.class */
public class C0875x {
    /* renamed from: a */
    public static Intent m2819a(byte[] bArr, long j) {
        C0729jb m2822a = m2822a(bArr);
        if (m2822a == null) {
            return null;
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mrt", Long.toString(j));
        intent.setPackage(m2822a.f2004b);
        return intent;
    }

    /* renamed from: a */
    public static C0729jb m2820a(Context context, C0729jb c0729jb) {
        return m2821a(context, c0729jb, false, false, false);
    }

    /* renamed from: a */
    public static C0729jb m2821a(Context context, C0729jb c0729jb, boolean z, boolean z2, boolean z3) {
        C0722iv c0722iv = new C0722iv();
        c0722iv.m1898b(c0729jb.m2029a());
        C0717iq m2022a = c0729jb.m2022a();
        if (m2022a != null) {
            c0722iv.m1892a(m2022a.m1825a());
            c0722iv.m1891a(m2022a.m1820a());
            if (!TextUtils.isEmpty(m2022a.m1835b())) {
                c0722iv.m1902c(m2022a.m1835b());
            }
        }
        c0722iv.m1893a(C0743jp.m2311a(context, c0729jb));
        c0722iv.m1899b(C0743jp.m2312a(z, z2, z3));
        C0729jb m2495a = C0800af.m2495a(c0729jb.m2037b(), c0729jb.m2029a(), c0722iv, EnumC0696hw.AckMessage);
        C0717iq m1821a = c0729jb.m2022a().m1821a();
        m1821a.m1828a("mat", Long.toString(System.currentTimeMillis()));
        m2495a.m2024a(m1821a);
        return m2495a;
    }

    /* renamed from: a */
    public static C0729jb m2822a(byte[] bArr) {
        C0729jb c0729jb = new C0729jb();
        try {
            C0743jp.m2313a(c0729jb, bArr);
            return c0729jb;
        } catch (Throwable th) {
            AbstractC0407b.m72a(th);
            return null;
        }
    }

    /* renamed from: a */
    private static void m2823a(XMPushService xMPushService, C0729jb c0729jb) {
        xMPushService.m2468a(new C0876y(4, xMPushService, c0729jb));
    }

    /* renamed from: a */
    private static void m2824a(XMPushService xMPushService, C0729jb c0729jb, String str) {
        xMPushService.m2468a(new C0797ac(4, xMPushService, c0729jb, str));
    }

    /* renamed from: a */
    private static void m2825a(XMPushService xMPushService, C0729jb c0729jb, String str, String str2) {
        xMPushService.m2468a(new C0798ad(4, xMPushService, c0729jb, str, str2));
    }

    /* renamed from: a */
    public static void m2826a(XMPushService xMPushService, C0729jb c0729jb, boolean z, boolean z2, boolean z3) {
        m2827a(xMPushService, c0729jb, z, z2, z3, false);
    }

    /* renamed from: a */
    public static void m2827a(XMPushService xMPushService, C0729jb c0729jb, boolean z, boolean z2, boolean z3, boolean z4) {
        xMPushService.m2468a(new C0799ae(4, xMPushService, c0729jb, z, z2, z3, z4));
    }

    /* renamed from: a */
    public static void m2828a(XMPushService xMPushService, String str, byte[] bArr, Intent intent, boolean z) {
        C0631fl m1256a;
        String m2037b;
        String m2535b;
        String m1825a;
        String str2;
        boolean z2;
        String str3;
        C0729jb m2822a = m2822a(bArr);
        C0717iq m2022a = m2822a.m2022a();
        if (bArr != null) {
            C0574di.m919a(m2822a.m2037b(), StubApp.getOrigApplicationContext(xMPushService.getApplicationContext()), null, m2822a.m2021a(), bArr.length);
        }
        if (m2841c(m2822a) && m2831a(xMPushService, str)) {
            if (C0803ai.m2541d(m2822a)) {
                C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1264a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), "old message received by new SDK.");
            }
            m2840c(xMPushService, m2822a);
            return;
        }
        if (m2833a(m2822a) && !m2831a(xMPushService, str) && !m2839b(m2822a)) {
            if (C0803ai.m2541d(m2822a)) {
                C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1264a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), "new message received by old SDK.");
            }
            m2842d(xMPushService, m2822a);
            return;
        }
        if ((!C0803ai.m2530a(m2822a) || !C0646g.m1365b((Context) xMPushService, m2822a.f2004b)) && !m2830a(xMPushService, intent)) {
            if (!C0646g.m1365b((Context) xMPushService, m2822a.f2004b)) {
                if (C0803ai.m2541d(m2822a)) {
                    C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1266b(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), "receive a message, but the package is removed.");
                }
                m2823a(xMPushService, m2822a);
                return;
            } else {
                AbstractC0407b.m70a("receive a mipush message, we can see the app, but we can't see the receiver.");
                if (C0803ai.m2541d(m2822a)) {
                    C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1266b(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), "receive a mipush message, we can see the app, but we can't see the receiver.");
                    return;
                }
                return;
            }
        }
        if (EnumC0696hw.Registration == m2822a.m2021a()) {
            String m2037b2 = m2822a.m2037b();
            SharedPreferences.Editor edit = xMPushService.getSharedPreferences("pref_registered_pkg_names", 0).edit();
            edit.putString(m2037b2, m2822a.f2000a);
            edit.commit();
        }
        if (C0803ai.m2540c(m2822a)) {
            C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1262a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), 1001, System.currentTimeMillis(), "receive notification message ");
            if (!TextUtils.isEmpty(m2022a.m1825a())) {
                intent.putExtra("messageId", m2022a.m1825a());
                intent.putExtra("eventMessageType", PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT);
            }
        }
        if (C0803ai.m2539b(m2822a)) {
            C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1262a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), 2001, System.currentTimeMillis(), "receive passThrough message");
            if (!TextUtils.isEmpty(m2022a.m1825a())) {
                intent.putExtra("messageId", m2022a.m1825a());
                intent.putExtra("eventMessageType", 2000);
            }
        }
        if (C0803ai.m2530a(m2822a)) {
            C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1262a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), 3001, System.currentTimeMillis(), "receive business message");
            if (!TextUtils.isEmpty(m2022a.m1825a())) {
                intent.putExtra("messageId", m2022a.m1825a());
                intent.putExtra("eventMessageType", 3000);
            }
        }
        if (m2022a != null && !TextUtils.isEmpty(m2022a.m1843c()) && !TextUtils.isEmpty(m2022a.m1847d()) && m2022a.f1827b != 1 && (C0803ai.m2532a(m2022a.m1826a()) || !C0803ai.m2528a((Context) xMPushService, m2822a.f2004b))) {
            if (m2022a != null) {
                String str4 = null;
                if (m2022a.f1825a != null) {
                    str4 = m2022a.f1825a.get("jobkey");
                }
                str3 = str4;
                if (TextUtils.isEmpty(str4)) {
                    str3 = m2022a.m1825a();
                }
                z2 = C0805ak.m2543a(xMPushService, m2822a.f2004b, str3);
            } else {
                z2 = false;
                str3 = null;
            }
            if (z2) {
                C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1267c(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), "drop a duplicate message");
                AbstractC0407b.m70a("drop a duplicate message, key=" + str3);
            } else {
                C0803ai.c m2519a = C0803ai.m2519a((Context) xMPushService, m2822a, bArr);
                if (m2519a.f2464a > 0 && !TextUtils.isEmpty(m2519a.f2465a)) {
                    C0681hh.m1544a(xMPushService, m2519a.f2465a, m2519a.f2464a, true, false, System.currentTimeMillis());
                }
                if (!C0803ai.m2530a(m2822a)) {
                    Intent intent2 = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
                    intent2.putExtra("mipush_payload", bArr);
                    intent2.setPackage(m2822a.f2004b);
                    try {
                        List<ResolveInfo> queryBroadcastReceivers = xMPushService.getPackageManager().queryBroadcastReceivers(intent2, 0);
                        if (queryBroadcastReceivers != null && !queryBroadcastReceivers.isEmpty()) {
                            xMPushService.sendBroadcast(intent2, C0800af.m2497a(m2822a.f2004b));
                        }
                    } catch (Exception e) {
                        xMPushService.sendBroadcast(intent2, C0800af.m2497a(m2822a.f2004b));
                        C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1266b(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), e.getMessage());
                    }
                }
            }
            if (z) {
                m2826a(xMPushService, m2822a, false, true, false);
            } else {
                m2838b(xMPushService, m2822a);
            }
        } else if ("com.xiaomi.xmsf".contains(m2822a.f2004b) && !m2822a.m2039b() && m2022a != null && m2022a.m1826a() != null && m2022a.m1826a().containsKey("ab")) {
            m2838b(xMPushService, m2822a);
            AbstractC0407b.m74c("receive abtest message. ack it." + m2022a.m1825a());
        } else if (m2836a(xMPushService, str, m2822a, m2022a)) {
            if (m2022a != null && !TextUtils.isEmpty(m2022a.m1825a())) {
                if (C0803ai.m2539b(m2822a)) {
                    C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1263a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), 2002, "try send passThrough message Broadcast");
                } else {
                    if (C0803ai.m2530a(m2822a)) {
                        m1256a = C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext()));
                        m2037b = m2822a.m2037b();
                        m2535b = C0803ai.m2535b(m2822a);
                        m1825a = m2022a.m1825a();
                        str2 = "try show awake message , but it don't show in foreground";
                    } else if (C0803ai.m2540c(m2822a)) {
                        m1256a = C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext()));
                        m2037b = m2822a.m2037b();
                        m2535b = C0803ai.m2535b(m2822a);
                        m1825a = m2022a.m1825a();
                        str2 = "try show notification message , but it don't show in foreground";
                    }
                    m1256a.m1264a(m2037b, m2535b, m1825a, str2);
                }
            }
            xMPushService.sendBroadcast(intent, C0800af.m2497a(m2822a.f2004b));
        } else {
            C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1264a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), "passThough message: not permit to send broadcast ");
        }
        if (m2822a.m2021a() != EnumC0696hw.UnRegistration || "com.xiaomi.xmsf".equals(xMPushService.getPackageName())) {
            return;
        }
        xMPushService.stopSelf();
    }

    /* renamed from: a */
    private static void m2829a(XMPushService xMPushService, byte[] bArr, long j) {
        boolean m2834a;
        Map<String, String> m1826a;
        C0729jb m2822a = m2822a(bArr);
        if (m2822a == null) {
            return;
        }
        if (TextUtils.isEmpty(m2822a.f2004b)) {
            AbstractC0407b.m70a("receive a mipush message without package name");
            return;
        }
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        Intent m2819a = m2819a(bArr, valueOf.longValue());
        String m2521a = C0803ai.m2521a(m2822a);
        C0681hh.m1544a(xMPushService, m2521a, j, true, true, System.currentTimeMillis());
        C0717iq m2022a = m2822a.m2022a();
        if (m2022a != null) {
            m2022a.m1828a("mrt", Long.toString(valueOf.longValue()));
        }
        String str = "";
        if (EnumC0696hw.SendMessage == m2822a.m2021a() && C0872u.m2803a(xMPushService).m2805a(m2822a.f2004b) && !C0803ai.m2530a(m2822a)) {
            if (m2022a != null) {
                String m1825a = m2022a.m1825a();
                str = m1825a;
                if (C0803ai.m2541d(m2822a)) {
                    C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1264a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m1825a, "Drop a message for unregistered");
                    str = m1825a;
                }
            }
            AbstractC0407b.m70a("Drop a message for unregistered, msgid=" + str);
            m2824a(xMPushService, m2822a, m2822a.f2004b);
            return;
        }
        if (EnumC0696hw.SendMessage == m2822a.m2021a() && C0872u.m2803a(xMPushService).m2809c(m2822a.f2004b) && !C0803ai.m2530a(m2822a)) {
            if (m2022a != null) {
                String m1825a2 = m2022a.m1825a();
                str = m1825a2;
                if (C0803ai.m2541d(m2822a)) {
                    C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1264a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m1825a2, "Drop a message for push closed");
                    str = m1825a2;
                }
            }
            AbstractC0407b.m70a("Drop a message for push closed, msgid=" + str);
            m2824a(xMPushService, m2822a, m2822a.f2004b);
            return;
        }
        if (EnumC0696hw.SendMessage == m2822a.m2021a() && !TextUtils.equals(xMPushService.getPackageName(), "com.xiaomi.xmsf") && !TextUtils.equals(xMPushService.getPackageName(), m2822a.f2004b)) {
            AbstractC0407b.m70a("Receive a message with wrong package name, expect " + xMPushService.getPackageName() + ", received " + m2822a.f2004b);
            m2825a(xMPushService, m2822a, "unmatched_package", "package should be " + xMPushService.getPackageName() + ", but got " + m2822a.f2004b);
            if (m2022a == null || !C0803ai.m2541d(m2822a)) {
                return;
            }
            C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1264a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), "Receive a message with wrong package name");
            return;
        }
        if (m2022a != null && m2022a.m1825a() != null) {
            AbstractC0407b.m70a(String.format("receive a message, appid=%1$s, msgid= %2$s", m2822a.m2029a(), m2022a.m1825a()));
        }
        if (m2022a != null && (m1826a = m2022a.m1826a()) != null && m1826a.containsKey("hide") && "true".equalsIgnoreCase(m1826a.get("hide"))) {
            m2838b(xMPushService, m2822a);
            return;
        }
        if (m2022a != null && m2022a.m1826a() != null && m2022a.m1826a().containsKey("__miid")) {
            String str2 = m2022a.m1826a().get("__miid");
            Account m2396a = C0769m.m2396a((Context) xMPushService);
            if (m2396a == null || !TextUtils.equals(str2, m2396a.name)) {
                if (C0803ai.m2541d(m2822a)) {
                    C0631fl.m1256a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext())).m1264a(m2822a.m2037b(), C0803ai.m2535b(m2822a), m2022a.m1825a(), "miid already logout or anther already login");
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(" should be login, but got ");
                sb.append(m2396a);
                AbstractC0407b.m70a(sb.toString() == null ? "nothing" : m2396a.name);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(" should be login, but got ");
                sb2.append(m2396a);
                m2825a(xMPushService, m2822a, "miid already logout or anther already login", sb2.toString() == null ? "nothing" : m2396a.name);
                return;
            }
        }
        boolean z = m2022a != null && m2837a(m2022a.m1826a());
        if (z) {
            if (!C0859j.m2723e(xMPushService)) {
                m2827a(xMPushService, m2822a, false, false, false, true);
                return;
            }
            if (!(m2832a(m2022a) && C0859j.m2722d(xMPushService))) {
                m2834a = true;
            } else if (!m2835a(xMPushService, m2822a)) {
                return;
            } else {
                m2834a = m2834a(xMPushService, m2022a, bArr);
            }
            m2826a(xMPushService, m2822a, true, false, false);
            if (!m2834a) {
                return;
            }
        }
        m2828a(xMPushService, m2521a, bArr, m2819a, z);
    }

    /* renamed from: a */
    private static boolean m2830a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (queryBroadcastReceivers != null) {
                return !queryBroadcastReceivers.isEmpty();
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0050, code lost:            if (r0.isEmpty() == false) goto L7;     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m2831a(android.content.Context r4, java.lang.String r5) {
        /*
            android.content.Intent r0 = new android.content.Intent
            r1 = r0
            java.lang.String r2 = "com.xiaomi.mipush.miui.CLICK_MESSAGE"
            r1.<init>(r2)
            r8 = r0
            r0 = r8
            r1 = r5
            android.content.Intent r0 = r0.setPackage(r1)
            android.content.Intent r0 = new android.content.Intent
            r1 = r0
            java.lang.String r2 = "com.xiaomi.mipush.miui.RECEIVE_MESSAGE"
            r1.<init>(r2)
            r9 = r0
            r0 = r9
            r1 = r5
            android.content.Intent r0 = r0.setPackage(r1)
            r0 = r4
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            r4 = r0
            r0 = 0
            r6 = r0
            r0 = r4
            r1 = r9
            r2 = 32
            java.util.List r0 = r0.queryBroadcastReceivers(r1, r2)     // Catch: java.lang.Exception -> L57
            r5 = r0
            r0 = r4
            r1 = r8
            r2 = 32
            java.util.List r0 = r0.queryIntentServices(r1, r2)     // Catch: java.lang.Exception -> L57
            r4 = r0
            r0 = r5
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Exception -> L57
            if (r0 == 0) goto L53
            r0 = r4
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Exception -> L57
            r7 = r0
            r0 = r7
            if (r0 != 0) goto L55
        L53:
            r0 = 1
            r6 = r0
        L55:
            r0 = r6
            return r0
        L57:
            r4 = move-exception
            r0 = r4
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m72a(r0)
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0875x.m2831a(android.content.Context, java.lang.String):boolean");
    }

    /* renamed from: a */
    public static boolean m2832a(C0717iq c0717iq) {
        Map<String, String> m1826a;
        if (c0717iq == null || (m1826a = c0717iq.m1826a()) == null) {
            return false;
        }
        return TextUtils.equals("1", m1826a.get("__geo_local_check"));
    }

    /* renamed from: a */
    private static boolean m2833a(C0729jb c0729jb) {
        return "com.xiaomi.xmsf".equals(c0729jb.f2004b) && c0729jb.m2022a() != null && c0729jb.m2022a().m1826a() != null && c0729jb.m2022a().m1826a().containsKey("miui_package_name");
    }

    /* renamed from: a */
    private static boolean m2834a(XMPushService xMPushService, C0717iq c0717iq, byte[] bArr) {
        Map<String, String> m1826a = c0717iq.m1826a();
        String[] split = m1826a.get("__geo_ids").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        ArrayList<ContentValues> arrayList = new ArrayList<>();
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                if (C0858i.m2709a(xMPushService).m2713a(arrayList)) {
                    return false;
                }
                AbstractC0407b.m74c("geofence added some new geofence message failed messagi_id:" + c0717iq.m1825a());
                return false;
            }
            String str = split[i2];
            if (C0856g.m2692a(xMPushService).m2698a(str) != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("geo_id", str);
                contentValues.put(MIPushNotificationHelper4Hybrid.KEY_MESSAGE_ID, c0717iq.m1825a());
                int parseInt = Integer.parseInt(m1826a.get("__geo_action"));
                contentValues.put("action", Integer.valueOf(parseInt));
                contentValues.put("content", bArr);
                contentValues.put("deadline", Long.valueOf(Long.parseLong(m1826a.get("__geo_deadline"))));
                if (TextUtils.equals(C0856g.m2692a(xMPushService).m2699a(str), "Enter") && parseInt == 1) {
                    return true;
                }
                arrayList.add(contentValues);
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private static boolean m2835a(XMPushService xMPushService, C0729jb c0729jb) {
        if (!C0859j.m2718a(xMPushService) || !C0859j.m2721c(xMPushService)) {
            return false;
        }
        if (C0646g.m1365b((Context) xMPushService, c0729jb.f2004b)) {
            Map<String, String> m1826a = c0729jb.m2022a().m1826a();
            return (m1826a == null || !"12".contains(m1826a.get("__geo_action")) || TextUtils.isEmpty(m1826a.get("__geo_ids"))) ? false : true;
        }
        m2823a(xMPushService, c0729jb);
        return false;
    }

    /* renamed from: a */
    private static boolean m2836a(XMPushService xMPushService, String str, C0729jb c0729jb, C0717iq c0717iq) {
        boolean z = true;
        if (c0717iq != null) {
            z = true;
            if (c0717iq.m1826a() != null) {
                z = true;
                if (c0717iq.m1826a().containsKey("__check_alive")) {
                    z = true;
                    if (c0717iq.m1826a().containsKey("__awake")) {
                        C0732je c0732je = new C0732je();
                        c0732je.m2071b(c0729jb.m2029a());
                        c0732je.m2079d(str);
                        c0732je.m2075c(EnumC0714in.AwakeSystemApp.f1752a);
                        c0732je.m2058a(c0717iq.m1825a());
                        c0732je.f2033a = new HashMap();
                        boolean m1362a = C0646g.m1362a(StubApp.getOrigApplicationContext(xMPushService.getApplicationContext()), str);
                        c0732je.f2033a.put("app_running", Boolean.toString(m1362a));
                        z = true;
                        if (!m1362a) {
                            boolean parseBoolean = Boolean.parseBoolean(c0717iq.m1826a().get("__awake"));
                            c0732je.f2033a.put("awaked", Boolean.toString(parseBoolean));
                            z = true;
                            if (!parseBoolean) {
                                z = false;
                            }
                        }
                        try {
                            C0800af.m2499a(xMPushService, C0800af.m2495a(c0729jb.m2037b(), c0729jb.m2029a(), c0732je, EnumC0696hw.Notification));
                            return z;
                        } catch (C0660gn e) {
                            AbstractC0407b.m72a(e);
                        }
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    private static boolean m2837a(Map<String, String> map) {
        return map != null && map.containsKey("__geo_ids");
    }

    /* renamed from: b */
    private static void m2838b(XMPushService xMPushService, C0729jb c0729jb) {
        xMPushService.m2468a(new C0877z(4, xMPushService, c0729jb));
    }

    /* renamed from: b */
    private static boolean m2839b(C0729jb c0729jb) {
        Map<String, String> m1826a = c0729jb.m2022a().m1826a();
        return m1826a != null && m1826a.containsKey("notify_effect");
    }

    /* renamed from: c */
    private static void m2840c(XMPushService xMPushService, C0729jb c0729jb) {
        xMPushService.m2468a(new C0795aa(4, xMPushService, c0729jb));
    }

    /* renamed from: c */
    private static boolean m2841c(C0729jb c0729jb) {
        if (c0729jb.m2022a() == null || c0729jb.m2022a().m1826a() == null) {
            return false;
        }
        return "1".equals(c0729jb.m2022a().m1826a().get("obslete_ads_message"));
    }

    /* renamed from: d */
    private static void m2842d(XMPushService xMPushService, C0729jb c0729jb) {
        xMPushService.m2468a(new C0796ab(4, xMPushService, c0729jb));
    }

    /* renamed from: a */
    public void m2843a(Context context, C0814at.b bVar, boolean z, int i, String str) {
        C0870s m2795a;
        if (z || (m2795a = C0871t.m2795a(context)) == null || !"token-expired".equals(str)) {
            return;
        }
        try {
            C0871t.m2796a(context, m2795a.f2713f, m2795a.f2711d, m2795a.f2712e);
        } catch (IOException | JSONException e) {
            AbstractC0407b.m72a(e);
        }
    }

    /* renamed from: a */
    public void m2844a(XMPushService xMPushService, C0641fv c0641fv, C0814at.b bVar) {
        try {
            m2829a(xMPushService, c0641fv.m1336a(bVar.f2510h), c0641fv.mo1340c());
        } catch (IllegalArgumentException e) {
            AbstractC0407b.m72a(e);
        }
    }

    /* renamed from: a */
    public void m2845a(XMPushService xMPushService, AbstractC0666gt abstractC0666gt, C0814at.b bVar) {
        if (!(abstractC0666gt instanceof C0665gs)) {
            AbstractC0407b.m70a("not a mipush message");
            return;
        }
        C0665gs c0665gs = (C0665gs) abstractC0666gt;
        C0663gq a = c0665gs.m1461a("s");
        if (a != null) {
            try {
                m2829a(xMPushService, C0824bc.m2623a(C0824bc.m2622a(bVar.f2510h, c0665gs.m1491j()), a.m1452c()), C0681hh.m1536a(abstractC0666gt.mo1456a()));
            } catch (IllegalArgumentException e) {
                AbstractC0407b.m72a(e);
            }
        }
    }
}
