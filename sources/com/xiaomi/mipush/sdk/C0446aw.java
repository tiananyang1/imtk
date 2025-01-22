package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0574di;
import com.xiaomi.push.C0620fa;
import com.xiaomi.push.C0630fk;
import com.xiaomi.push.C0631fl;
import com.xiaomi.push.C0716ip;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0722iv;
import com.xiaomi.push.C0723iw;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0728ja;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.C0730jc;
import com.xiaomi.push.C0731jd;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0734jg;
import com.xiaomi.push.C0736ji;
import com.xiaomi.push.C0738jk;
import com.xiaomi.push.C0740jm;
import com.xiaomi.push.C0742jo;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.C0749jv;
import com.xiaomi.push.C0878t;
import com.xiaomi.push.EnumC0636fq;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.EnumC0719is;
import com.xiaomi.push.InterfaceC0744jq;
import com.xiaomi.push.service.AbstractC0818ax;
import com.xiaomi.push.service.C0803ai;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.C0810ap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;

/* renamed from: com.xiaomi.mipush.sdk.aw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/aw.class */
public class C0446aw {

    /* renamed from: a */
    private static C0446aw f301a;

    /* renamed from: a */
    private static Object f302a = new Object();

    /* renamed from: a */
    private static Queue<String> f303a;

    /* renamed from: a */
    private Context f304a;

    private C0446aw(Context context) {
        this.f304a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (this.f304a == null) {
            this.f304a = context;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent m200a(android.content.Context r6, java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            Method dump skipped, instructions count: 566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.C0446aw.m200a(android.content.Context, java.lang.String, java.util.Map):android.content.Intent");
    }

    /* renamed from: a */
    private PushMessageHandler.InterfaceC0422a m201a(C0729jb c0729jb, boolean z, byte[] bArr, String str, int i) {
        MiPushMessage miPushMessage;
        MiPushMessage miPushMessage2;
        try {
            InterfaceC0744jq m196a = C0442as.m196a(this.f304a, c0729jb);
            if (m196a == null) {
                AbstractC0407b.m75d("receiving an un-recognized message. " + c0729jb.f1997a);
                C0631fl.m1256a(this.f304a).m1266b(this.f304a.getPackageName(), C0630fk.m1249a(i), str, "receiving an un-recognized message.");
                return null;
            }
            EnumC0696hw m2021a = c0729jb.m2021a();
            AbstractC0407b.m70a("processing a message, action=" + m2021a);
            switch (C0448ay.f308a[m2021a.ordinal()]) {
                case 1:
                    if (C0461d.m289a(this.f304a).m311d() && !z) {
                        AbstractC0407b.m70a("receive a message in pause state. drop it");
                        C0631fl.m1256a(this.f304a).m1264a(this.f304a.getPackageName(), C0630fk.m1249a(i), str, "receive a message in pause state. drop it");
                        return null;
                    }
                    C0736ji c0736ji = (C0736ji) m196a;
                    C0716ip m2189a = c0736ji.m2189a();
                    if (m2189a == null) {
                        AbstractC0407b.m75d("receive an empty message without push content, drop it");
                        C0631fl.m1256a(this.f304a).m1266b(this.f304a.getPackageName(), C0630fk.m1249a(i), str, "receive an empty message without push content, drop it");
                        return null;
                    }
                    if (z) {
                        if (C0803ai.m2530a(c0729jb)) {
                            MiPushClient.reportIgnoreRegMessageClicked(this.f304a, m2189a.m1791a(), c0729jb.m2022a(), c0729jb.f2004b, m2189a.m1796b());
                        } else {
                            MiPushClient.reportMessageClicked(this.f304a, m2189a.m1791a(), c0729jb.m2022a(), m2189a.m1796b());
                        }
                    }
                    if (!z) {
                        if (!TextUtils.isEmpty(c0736ji.m2199d()) && MiPushClient.aliasSetTime(this.f304a, c0736ji.m2199d()) < 0) {
                            MiPushClient.addAlias(this.f304a, c0736ji.m2199d());
                        } else if (!TextUtils.isEmpty(c0736ji.m2197c()) && MiPushClient.topicSubscribedTime(this.f304a, c0736ji.m2197c()) < 0) {
                            MiPushClient.addTopic(this.f304a, c0736ji.m2197c());
                        }
                    }
                    String str2 = (c0729jb.f1998a == null || c0729jb.f1998a.m1826a() == null) ? null : c0729jb.f1998a.f1825a.get("jobkey");
                    String str3 = str2;
                    if (TextUtils.isEmpty(str2)) {
                        str3 = m2189a.m1791a();
                    }
                    if (z || !m213a(this.f304a, str3)) {
                        MiPushMessage generateMessage = PushMessageHelper.generateMessage(c0736ji, c0729jb.m2022a(), z);
                        if (generateMessage.getPassThrough() == 0 && !z && C0803ai.m2532a(generateMessage.getExtra())) {
                            C0803ai.m2519a(this.f304a, c0729jb, bArr);
                            return null;
                        }
                        AbstractC0407b.m70a("receive a message, msgid=" + m2189a.m1791a() + ", jobkey=" + str3);
                        if (z && generateMessage.getExtra() != null && generateMessage.getExtra().containsKey("notify_effect")) {
                            Map<String, String> extra = generateMessage.getExtra();
                            String str4 = extra.get("notify_effect");
                            if (C0803ai.m2530a(c0729jb)) {
                                Intent m200a = m200a(this.f304a, c0729jb.f2004b, extra);
                                m200a.putExtra("eventMessageType", i);
                                m200a.putExtra("messageId", str);
                                if (m200a == null) {
                                    AbstractC0407b.m70a("Getting Intent fail from ignore reg message. ");
                                    C0631fl.m1256a(this.f304a).m1266b(this.f304a.getPackageName(), C0630fk.m1249a(i), str, "Getting Intent fail from ignore reg message.");
                                    return null;
                                }
                                String m1799c = m2189a.m1799c();
                                if (!TextUtils.isEmpty(m1799c)) {
                                    m200a.putExtra("payload", m1799c);
                                }
                                this.f304a.startActivity(m200a);
                                C0631fl.m1256a(this.f304a).m1263a(this.f304a.getPackageName(), C0630fk.m1249a(i), str, 3006, "business message is clicked typeId " + str4);
                                return null;
                            }
                            Context context = this.f304a;
                            Intent m200a2 = m200a(context, context.getPackageName(), extra);
                            if (m200a2 == null) {
                                return null;
                            }
                            if (!str4.equals(AbstractC0818ax.f2539c)) {
                                m200a2.putExtra(PushMessageHelper.KEY_MESSAGE, generateMessage);
                                m200a2.putExtra("eventMessageType", i);
                                m200a2.putExtra("messageId", str);
                            }
                            this.f304a.startActivity(m200a2);
                            C0631fl.m1256a(this.f304a).m1263a(this.f304a.getPackageName(), C0630fk.m1249a(i), str, 1006, "notification message is clicked typeId " + str4);
                            if (!str4.equals(AbstractC0818ax.f2539c)) {
                                return null;
                            }
                            C0631fl.m1256a(this.f304a).m1264a(this.f304a.getPackageName(), C0630fk.m1249a(i), str, "try open web page typeId " + str4);
                            return null;
                        }
                        miPushMessage = generateMessage;
                    } else {
                        AbstractC0407b.m70a("drop a duplicate message, key=" + str3);
                        C0631fl.m1256a(this.f304a).m1267c(this.f304a.getPackageName(), C0630fk.m1249a(i), str, "drop a duplicate message, key=" + str3);
                        miPushMessage = null;
                    }
                    miPushMessage2 = miPushMessage;
                    if (c0729jb.m2022a() == null) {
                        miPushMessage2 = miPushMessage;
                        if (!z) {
                            m211a(c0736ji, c0729jb);
                            miPushMessage2 = miPushMessage;
                            break;
                        }
                    }
                    break;
                case 2:
                    C0734jg c0734jg = (C0734jg) m196a;
                    String str5 = C0461d.m289a(this.f304a).f342a;
                    if (TextUtils.isEmpty(str5) || !TextUtils.equals(str5, c0734jg.m2150a())) {
                        AbstractC0407b.m70a("bad Registration result:");
                        return null;
                    }
                    C0461d.m289a(this.f304a).f342a = null;
                    if (c0734jg.f2122a == 0) {
                        C0461d.m289a(this.f304a).m306b(c0734jg.f2132e, c0734jg.f2133f, c0734jg.f2139l);
                    }
                    ArrayList arrayList = null;
                    if (!TextUtils.isEmpty(c0734jg.f2132e)) {
                        arrayList = new ArrayList();
                        arrayList.add(c0734jg.f2132e);
                    }
                    MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_REGISTER.f934a, arrayList, c0734jg.f2122a, c0734jg.f2131d, null);
                    C0449az.m224a(this.f304a).m268d();
                    return generateCommandMessage;
                case 3:
                    if (((C0740jm) m196a).f2255a == 0) {
                        C0461d.m289a(this.f304a).m294a();
                        MiPushClient.clearExtras(this.f304a);
                    }
                    PushMessageHandler.m154a();
                    return null;
                case 4:
                    C0738jk c0738jk = (C0738jk) m196a;
                    if (c0738jk.f2209a == 0) {
                        MiPushClient.addTopic(this.f304a, c0738jk.m2228a());
                    }
                    ArrayList arrayList2 = null;
                    if (!TextUtils.isEmpty(c0738jk.m2228a())) {
                        arrayList2 = new ArrayList();
                        arrayList2.add(c0738jk.m2228a());
                    }
                    return PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_SUBSCRIBE_TOPIC.f934a, arrayList2, c0738jk.f2209a, c0738jk.f2215d, c0738jk.m2233b());
                case 5:
                    C0742jo c0742jo = (C0742jo) m196a;
                    if (c0742jo.f2292a == 0) {
                        MiPushClient.removeTopic(this.f304a, c0742jo.m2297a());
                    }
                    ArrayList arrayList3 = null;
                    if (!TextUtils.isEmpty(c0742jo.m2297a())) {
                        arrayList3 = new ArrayList();
                        arrayList3.add(c0742jo.m2297a());
                    }
                    return PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_UNSUBSCRIBE_TOPIC.f934a, arrayList3, c0742jo.f2292a, c0742jo.f2298d, c0742jo.m2302b());
                case 6:
                    C0574di.m919a(this.f304a.getPackageName(), this.f304a, m196a, EnumC0696hw.Command, bArr.length);
                    C0728ja c0728ja = (C0728ja) m196a;
                    String m2003a = c0728ja.m2003a();
                    List<String> m2004a = c0728ja.m2004a();
                    List<String> list = m2004a;
                    if (c0728ja.f1977a == 0) {
                        if (TextUtils.equals(m2003a, EnumC0636fq.COMMAND_SET_ACCEPT_TIME.f934a) && m2004a != null && m2004a.size() > 1) {
                            MiPushClient.addAcceptTime(this.f304a, m2004a.get(0), m2004a.get(1));
                            if ("00:00".equals(m2004a.get(0)) && "00:00".equals(m2004a.get(1))) {
                                C0461d.m289a(this.f304a).m299a(true);
                            } else {
                                C0461d.m289a(this.f304a).m299a(false);
                            }
                            list = m217a(TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault(), m2004a);
                        } else if (TextUtils.equals(m2003a, EnumC0636fq.COMMAND_SET_ALIAS.f934a) && m2004a != null && m2004a.size() > 0) {
                            MiPushClient.addAlias(this.f304a, m2004a.get(0));
                            list = m2004a;
                        } else if (TextUtils.equals(m2003a, EnumC0636fq.COMMAND_UNSET_ALIAS.f934a) && m2004a != null && m2004a.size() > 0) {
                            MiPushClient.removeAlias(this.f304a, m2004a.get(0));
                            list = m2004a;
                        } else if (TextUtils.equals(m2003a, EnumC0636fq.COMMAND_SET_ACCOUNT.f934a) && m2004a != null && m2004a.size() > 0) {
                            MiPushClient.addAccount(this.f304a, m2004a.get(0));
                            list = m2004a;
                        } else if (!TextUtils.equals(m2003a, EnumC0636fq.COMMAND_UNSET_ACCOUNT.f934a) || m2004a == null || m2004a.size() <= 0) {
                            list = m2004a;
                            if (TextUtils.equals(m2003a, EnumC0636fq.COMMAND_CHK_VDEVID.f934a)) {
                                if (m2004a == null || m2004a.size() <= 0) {
                                    return null;
                                }
                                C0727j.m1984a(this.f304a, m2004a.get(0));
                                return null;
                            }
                        } else {
                            MiPushClient.removeAccount(this.f304a, m2004a.get(0));
                            list = m2004a;
                        }
                    }
                    return PushMessageHelper.generateCommandMessage(m2003a, list, c0728ja.f1977a, c0728ja.f1985d, c0728ja.m2009b());
                case 7:
                    C0574di.m919a(this.f304a.getPackageName(), this.f304a, m196a, EnumC0696hw.Notification, bArr.length);
                    if (m196a instanceof C0723iw) {
                        C0723iw c0723iw = (C0723iw) m196a;
                        String m1926a = c0723iw.m1926a();
                        if (EnumC0714in.DisablePushMessage.f1752a.equalsIgnoreCase(c0723iw.f1925d)) {
                            if (c0723iw.f1918a == 0) {
                                synchronized (C0439ap.class) {
                                    try {
                                        if (C0439ap.m186a(this.f304a).m191a(m1926a)) {
                                            C0439ap.m186a(this.f304a).m193c(m1926a);
                                            if ("syncing".equals(C0439ap.m186a(this.f304a).m188a(EnumC0455be.DISABLE_PUSH))) {
                                                C0439ap.m186a(this.f304a).m189a(EnumC0455be.DISABLE_PUSH, "synced");
                                                MiPushClient.clearNotification(this.f304a);
                                                MiPushClient.clearLocalNotificationType(this.f304a);
                                                PushMessageHandler.m154a();
                                                C0449az.m224a(this.f304a).m264b();
                                            }
                                        }
                                    } finally {
                                    }
                                }
                                return null;
                            }
                            if ("syncing".equals(C0439ap.m186a(this.f304a).m188a(EnumC0455be.DISABLE_PUSH))) {
                                synchronized (C0439ap.class) {
                                    try {
                                        if (C0439ap.m186a(this.f304a).m191a(m1926a)) {
                                            if (C0439ap.m186a(this.f304a).m187a(m1926a) < 10) {
                                                C0439ap.m186a(this.f304a).m192b(m1926a);
                                                C0449az.m224a(this.f304a).m261a(true, m1926a);
                                            } else {
                                                C0439ap.m186a(this.f304a).m193c(m1926a);
                                            }
                                        }
                                    } finally {
                                    }
                                }
                                return null;
                            }
                        } else if (!EnumC0714in.EnablePushMessage.f1752a.equalsIgnoreCase(c0723iw.f1925d)) {
                            miPushMessage2 = null;
                            if (EnumC0714in.ThirdPartyRegUpdate.f1752a.equalsIgnoreCase(c0723iw.f1925d)) {
                                m209a(c0723iw);
                                return null;
                            }
                        } else {
                            if (c0723iw.f1918a == 0) {
                                synchronized (C0439ap.class) {
                                    try {
                                        if (C0439ap.m186a(this.f304a).m191a(m1926a)) {
                                            C0439ap.m186a(this.f304a).m193c(m1926a);
                                            if ("syncing".equals(C0439ap.m186a(this.f304a).m188a(EnumC0455be.ENABLE_PUSH))) {
                                                C0439ap.m186a(this.f304a).m189a(EnumC0455be.ENABLE_PUSH, "synced");
                                            }
                                        }
                                    } finally {
                                    }
                                }
                                return null;
                            }
                            if ("syncing".equals(C0439ap.m186a(this.f304a).m188a(EnumC0455be.ENABLE_PUSH))) {
                                synchronized (C0439ap.class) {
                                    try {
                                        if (C0439ap.m186a(this.f304a).m191a(m1926a)) {
                                            if (C0439ap.m186a(this.f304a).m187a(m1926a) < 10) {
                                                C0439ap.m186a(this.f304a).m192b(m1926a);
                                                C0449az.m224a(this.f304a).m261a(false, m1926a);
                                            } else {
                                                C0439ap.m186a(this.f304a).m193c(m1926a);
                                            }
                                        }
                                    } finally {
                                    }
                                }
                                return null;
                            }
                        }
                        C0439ap.m186a(this.f304a).m193c(m1926a);
                        return null;
                    }
                    miPushMessage2 = null;
                    if (m196a instanceof C0732je) {
                        C0732je c0732je = (C0732je) m196a;
                        if ("registration id expired".equalsIgnoreCase(c0732je.f2038d)) {
                            List<String> allAlias = MiPushClient.getAllAlias(this.f304a);
                            List<String> allTopic = MiPushClient.getAllTopic(this.f304a);
                            List<String> allUserAccount = MiPushClient.getAllUserAccount(this.f304a);
                            String acceptTime = MiPushClient.getAcceptTime(this.f304a);
                            MiPushClient.reInitialize(this.f304a, EnumC0719is.RegIdExpired);
                            for (String str6 : allAlias) {
                                MiPushClient.removeAlias(this.f304a, str6);
                                MiPushClient.setAlias(this.f304a, str6, null);
                            }
                            for (String str7 : allTopic) {
                                MiPushClient.removeTopic(this.f304a, str7);
                                MiPushClient.subscribe(this.f304a, str7, null);
                            }
                            for (String str8 : allUserAccount) {
                                MiPushClient.removeAccount(this.f304a, str8);
                                MiPushClient.setUserAccount(this.f304a, str8, null);
                            }
                            String[] split = acceptTime.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                            miPushMessage2 = null;
                            if (split.length == 2) {
                                MiPushClient.removeAcceptTime(this.f304a);
                                MiPushClient.addAcceptTime(this.f304a, split[0], split[1]);
                                return null;
                            }
                        } else if ("client_info_update_ok".equalsIgnoreCase(c0732je.f2038d)) {
                            miPushMessage2 = null;
                            if (c0732je.m2064a() != null) {
                                miPushMessage2 = null;
                                if (c0732je.m2064a().containsKey(Constants.EXTRA_KEY_APP_VERSION)) {
                                    C0461d.m289a(this.f304a).m296a(c0732je.m2064a().get(Constants.EXTRA_KEY_APP_VERSION));
                                    return null;
                                }
                            }
                        } else if (EnumC0714in.AwakeApp.f1752a.equalsIgnoreCase(c0732je.f2038d)) {
                            miPushMessage2 = null;
                            if (c0729jb.m2039b()) {
                                miPushMessage2 = null;
                                if (c0732je.m2064a() != null) {
                                    miPushMessage2 = null;
                                    if (c0732je.m2064a().containsKey("awake_info")) {
                                        String str9 = c0732je.m2064a().get("awake_info");
                                        Context context2 = this.f304a;
                                        C0472o.m370a(context2, C0461d.m289a(context2).m293a(), C0809ao.m2557a(this.f304a).m2561a(EnumC0703ic.AwakeInfoUploadWaySwitch.m1669a(), 0), str9);
                                        return null;
                                    }
                                }
                            }
                        } else {
                            try {
                                if (EnumC0714in.NormalClientConfigUpdate.f1752a.equalsIgnoreCase(c0732je.f2038d)) {
                                    C0731jd c0731jd = new C0731jd();
                                    C0743jp.m2313a(c0731jd, c0732je.m2070a());
                                    C0810ap.m2570a(C0809ao.m2557a(this.f304a), c0731jd);
                                    C0466i.m353b(this.f304a);
                                    C0620fa.m1220a(this.f304a).m1226a(C0809ao.m2557a(this.f304a).m2561a(EnumC0703ic.AwakeInfoUploadWaySwitch.m1669a(), 0));
                                    C0630fk.m1250a(this.f304a);
                                    return null;
                                }
                                if (EnumC0714in.CustomClientConfigUpdate.f1752a.equalsIgnoreCase(c0732je.f2038d)) {
                                    C0730jc c0730jc = new C0730jc();
                                    C0743jp.m2313a(c0730jc, c0732je.m2070a());
                                    C0810ap.m2569a(C0809ao.m2557a(this.f304a), c0730jc);
                                    C0466i.m353b(this.f304a);
                                    C0620fa.m1220a(this.f304a).m1226a(C0809ao.m2557a(this.f304a).m2561a(EnumC0703ic.AwakeInfoUploadWaySwitch.m1669a(), 0));
                                    C0630fk.m1250a(this.f304a);
                                    return null;
                                }
                                if (EnumC0714in.SyncInfoResult.f1752a.equalsIgnoreCase(c0732je.f2038d)) {
                                    C0456bf.m280a(this.f304a, c0732je);
                                    return null;
                                }
                                if (EnumC0714in.ForceSync.f1752a.equalsIgnoreCase(c0732je.f2038d)) {
                                    AbstractC0407b.m70a("receive force sync notification");
                                    C0456bf.m281a(this.f304a, false);
                                    return null;
                                }
                                if (EnumC0714in.GeoRegsiter.f1752a.equalsIgnoreCase(c0732je.f2038d)) {
                                    C0480w.m391a(this.f304a).m399a(c0732je);
                                    return null;
                                }
                                if (EnumC0714in.GeoUnregsiter.f1752a.equalsIgnoreCase(c0732je.f2038d)) {
                                    C0480w.m391a(this.f304a).m400b(c0732je);
                                    return null;
                                }
                                if (EnumC0714in.GeoSync.f1752a.equalsIgnoreCase(c0732je.f2038d)) {
                                    C0480w.m391a(this.f304a).m401c(c0732je);
                                    return null;
                                }
                                if (EnumC0714in.CancelPushMessage.f1752a.equals(c0732je.f2038d)) {
                                    miPushMessage2 = null;
                                    if (c0732je.m2064a() != null) {
                                        int i2 = -2;
                                        if (c0732je.m2064a().containsKey(AbstractC0818ax.f2536J)) {
                                            String str10 = c0732je.m2064a().get(AbstractC0818ax.f2536J);
                                            i2 = -2;
                                            if (!TextUtils.isEmpty(str10)) {
                                                try {
                                                    i2 = Integer.parseInt(str10);
                                                } catch (NumberFormatException e) {
                                                    e.printStackTrace();
                                                    i2 = -2;
                                                }
                                            }
                                        }
                                        if (i2 >= -1) {
                                            MiPushClient.clearNotification(this.f304a, i2);
                                            return null;
                                        }
                                        MiPushClient.clearNotification(this.f304a, c0732je.m2064a().containsKey(AbstractC0818ax.f2534H) ? c0732je.m2064a().get(AbstractC0818ax.f2534H) : "", c0732je.m2064a().containsKey(AbstractC0818ax.f2535I) ? c0732je.m2064a().get(AbstractC0818ax.f2535I) : "");
                                        return null;
                                    }
                                } else {
                                    if (EnumC0714in.HybridRegisterResult.f1752a.equals(c0732je.f2038d)) {
                                        C0734jg c0734jg2 = new C0734jg();
                                        C0743jp.m2313a(c0734jg2, c0732je.m2070a());
                                        MiPushClient4Hybrid.onReceiveRegisterResult(this.f304a, c0734jg2);
                                        return null;
                                    }
                                    if (EnumC0714in.HybridUnregisterResult.f1752a.equals(c0732je.f2038d)) {
                                        C0740jm c0740jm = new C0740jm();
                                        C0743jp.m2313a(c0740jm, c0732je.m2070a());
                                        MiPushClient4Hybrid.onReceiveUnregisterResult(this.f304a, c0740jm);
                                        return null;
                                    }
                                    miPushMessage2 = null;
                                    if (EnumC0714in.PushLogUpload.f1752a.equals(c0732je.f2038d)) {
                                        miPushMessage2 = null;
                                        if (c0732je.m2064a() != null) {
                                            miPushMessage2 = null;
                                            if (c0732je.m2064a().containsKey("packages")) {
                                                String[] split2 = c0732je.m2064a().get("packages").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                                                if (!TextUtils.equals(this.f304a.getPackageName(), "com.xiaomi.xmsf")) {
                                                    Logger.uploadLogFile(this.f304a, false);
                                                    return null;
                                                }
                                                Logger.uploadLogFile(this.f304a, true);
                                                m207a(this.f304a, split2);
                                                return null;
                                            }
                                        }
                                    }
                                }
                            } catch (C0749jv e2) {
                                AbstractC0407b.m72a(e2);
                                return null;
                            }
                        }
                    }
                    break;
                default:
                    return null;
            }
            return miPushMessage2;
        } catch (C0477t e3) {
            AbstractC0407b.m72a(e3);
            m210a(c0729jb);
            C0631fl.m1256a(this.f304a).m1265a(this.f304a.getPackageName(), C0630fk.m1249a(i), str, e3);
            return null;
        } catch (C0749jv e4) {
            AbstractC0407b.m72a(e4);
            AbstractC0407b.m75d("receive a message which action string is not valid. is the reg expired?");
            C0631fl.m1256a(this.f304a).m1265a(this.f304a.getPackageName(), C0630fk.m1249a(i), str, e4);
            return null;
        }
    }

    /* renamed from: a */
    private PushMessageHandler.InterfaceC0422a m202a(C0729jb c0729jb, byte[] bArr) {
        String str;
        InterfaceC0744jq m196a;
        try {
            m196a = C0442as.m196a(this.f304a, c0729jb);
        } catch (C0477t e) {
            AbstractC0407b.m72a(e);
            str = "message arrived: receive a message but decrypt failed. report when click.";
        } catch (C0749jv e2) {
            AbstractC0407b.m72a(e2);
            str = "message arrived: receive a message which action string is not valid. is the reg expired?";
        }
        if (m196a == null) {
            AbstractC0407b.m75d("message arrived: receiving an un-recognized message. " + c0729jb.f1997a);
            return null;
        }
        EnumC0696hw m2021a = c0729jb.m2021a();
        AbstractC0407b.m70a("message arrived: processing an arrived message, action=" + m2021a);
        if (C0448ay.f308a[m2021a.ordinal()] != 1) {
            return null;
        }
        C0736ji c0736ji = (C0736ji) m196a;
        C0716ip m2189a = c0736ji.m2189a();
        if (m2189a == null) {
            str = "message arrived: receive an empty message without push content, drop it";
            AbstractC0407b.m75d(str);
            return null;
        }
        String str2 = null;
        if (c0729jb.f1998a != null) {
            str2 = null;
            if (c0729jb.f1998a.m1826a() != null) {
                str2 = c0729jb.f1998a.f1825a.get("jobkey");
            }
        }
        MiPushMessage generateMessage = PushMessageHelper.generateMessage(c0736ji, c0729jb.m2022a(), false);
        generateMessage.setArrivedMessage(true);
        AbstractC0407b.m70a("message arrived: receive a message, msgid=" + m2189a.m1791a() + ", jobkey=" + str2);
        return generateMessage;
    }

    /* renamed from: a */
    public static C0446aw m203a(Context context) {
        if (f301a == null) {
            f301a = new C0446aw(context);
        }
        return f301a;
    }

    /* renamed from: a */
    private void m204a() {
        SharedPreferences sharedPreferences = this.f304a.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - sharedPreferences.getLong(Constants.SP_KEY_LAST_REINITIALIZE, 0L)) > 1800000) {
            MiPushClient.reInitialize(this.f304a, EnumC0719is.PackageUnregistered);
            sharedPreferences.edit().putLong(Constants.SP_KEY_LAST_REINITIALIZE, currentTimeMillis).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m205a(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr == null) {
            return;
        }
        int length = serviceInfoArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            ServiceInfo serviceInfo = serviceInfoArr[i2];
            if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                try {
                    Intent intent = new Intent();
                    intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                    intent.setAction("com.xiaomi.mipush.sdk.SYNC_LOG");
                    PushMessageHandler.m157a(context, intent);
                    return;
                } catch (Throwable th) {
                    return;
                }
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static void m206a(Context context, String str) {
        synchronized (f302a) {
            f303a.remove(str);
            C0461d.m289a(context);
            SharedPreferences m288a = C0461d.m288a(context);
            String m523a = C0509ay.m523a(f303a, Constants.ACCEPT_TIME_SEPARATOR_SP);
            SharedPreferences.Editor edit = m288a.edit();
            edit.putString("pref_msg_ids", m523a);
            C0878t.m2846a(edit);
        }
    }

    /* renamed from: a */
    private void m207a(Context context, String[] strArr) {
        C0493ai.m439a(context).m443a(new RunnableC0447ax(this, strArr, context));
    }

    /* renamed from: a */
    private void m209a(C0723iw c0723iw) {
        long j;
        EnumC0463f enumC0463f;
        AbstractC0407b.m74c("ASSEMBLE_PUSH : " + c0723iw.toString());
        String m1926a = c0723iw.m1926a();
        Map<String, String> m1927a = c0723iw.m1927a();
        if (m1927a != null) {
            String str = m1927a.get(Constants.ASSEMBLE_PUSH_REG_INFO);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (str.contains("brand:" + EnumC0440aq.FCM.name())) {
                AbstractC0407b.m70a("ASSEMBLE_PUSH : receive fcm token sync ack");
                C0466i.m354b(this.f304a, EnumC0463f.ASSEMBLE_PUSH_FCM, str);
                j = c0723iw.f1918a;
                enumC0463f = EnumC0463f.ASSEMBLE_PUSH_FCM;
            } else {
                if (str.contains("brand:" + EnumC0440aq.HUAWEI.name())) {
                    AbstractC0407b.m70a("ASSEMBLE_PUSH : receive hw token sync ack");
                    C0466i.m354b(this.f304a, EnumC0463f.ASSEMBLE_PUSH_HUAWEI, str);
                    j = c0723iw.f1918a;
                    enumC0463f = EnumC0463f.ASSEMBLE_PUSH_HUAWEI;
                } else {
                    if (!str.contains("brand:" + EnumC0440aq.OPPO.name())) {
                        return;
                    }
                    AbstractC0407b.m70a("ASSEMBLE_PUSH : receive COS token sync ack");
                    C0466i.m354b(this.f304a, EnumC0463f.ASSEMBLE_PUSH_COS, str);
                    j = c0723iw.f1918a;
                    enumC0463f = EnumC0463f.ASSEMBLE_PUSH_COS;
                }
            }
            m212a(m1926a, j, enumC0463f);
        }
    }

    /* renamed from: a */
    private void m210a(C0729jb c0729jb) {
        AbstractC0407b.m70a("receive a message but decrypt failed. report now.");
        C0732je c0732je = new C0732je(c0729jb.m2022a().f1823a, false);
        c0732je.m2075c(EnumC0714in.DecryptMessageFail.f1752a);
        c0732je.m2071b(c0729jb.m2029a());
        c0732je.m2079d(c0729jb.f2004b);
        c0732je.f2033a = new HashMap();
        c0732je.f2033a.put("regid", MiPushClient.getRegId(this.f304a));
        C0449az.m224a(this.f304a).m254a((C0449az) c0732je, EnumC0696hw.Notification, false, (C0717iq) null);
    }

    /* renamed from: a */
    private void m211a(C0736ji c0736ji, C0729jb c0729jb) {
        C0717iq m2022a = c0729jb.m2022a();
        C0722iv c0722iv = new C0722iv();
        c0722iv.m1898b(c0736ji.m2195b());
        c0722iv.m1892a(c0736ji.m2190a());
        c0722iv.m1891a(c0736ji.m2189a().m1790a());
        if (!TextUtils.isEmpty(c0736ji.m2197c())) {
            c0722iv.m1902c(c0736ji.m2197c());
        }
        if (!TextUtils.isEmpty(c0736ji.m2199d())) {
            c0722iv.m1905d(c0736ji.m2199d());
        }
        c0722iv.m1893a(C0743jp.m2311a(this.f304a, c0729jb));
        C0449az.m224a(this.f304a).m252a((C0449az) c0722iv, EnumC0696hw.AckMessage, m2022a);
    }

    /* renamed from: a */
    private void m212a(String str, long j, EnumC0463f enumC0463f) {
        EnumC0455be m358a = C0469l.m358a(enumC0463f);
        if (m358a == null) {
            return;
        }
        if (j == 0) {
            synchronized (C0439ap.class) {
                try {
                    if (C0439ap.m186a(this.f304a).m191a(str)) {
                        C0439ap.m186a(this.f304a).m193c(str);
                        if ("syncing".equals(C0439ap.m186a(this.f304a).m188a(m358a))) {
                            C0439ap.m186a(this.f304a).m189a(m358a, "synced");
                        }
                    }
                } finally {
                }
            }
            return;
        }
        if (!"syncing".equals(C0439ap.m186a(this.f304a).m188a(m358a))) {
            C0439ap.m186a(this.f304a).m193c(str);
            return;
        }
        synchronized (C0439ap.class) {
            try {
                if (C0439ap.m186a(this.f304a).m191a(str)) {
                    if (C0439ap.m186a(this.f304a).m187a(str) < 10) {
                        C0439ap.m186a(this.f304a).m192b(str);
                        C0449az.m224a(this.f304a).m258a(str, m358a, enumC0463f);
                    } else {
                        C0439ap.m186a(this.f304a).m193c(str);
                    }
                }
            } finally {
            }
        }
    }

    /* renamed from: a */
    private static boolean m213a(Context context, String str) {
        synchronized (f302a) {
            C0461d.m289a(context);
            SharedPreferences m288a = C0461d.m288a(context);
            if (f303a == null) {
                String[] split = m288a.getString("pref_msg_ids", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                f303a = new LinkedList();
                int length = split.length;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= length) {
                        break;
                    }
                    f303a.add(split[i2]);
                    i = i2 + 1;
                }
            }
            if (f303a.contains(str)) {
                return true;
            }
            f303a.add(str);
            if (f303a.size() > 25) {
                f303a.poll();
            }
            String m523a = C0509ay.m523a(f303a, Constants.ACCEPT_TIME_SEPARATOR_SP);
            SharedPreferences.Editor edit = m288a.edit();
            edit.putString("pref_msg_ids", m523a);
            C0878t.m2846a(edit);
            return false;
        }
    }

    /* renamed from: a */
    private boolean m214a(C0729jb c0729jb) {
        if (!TextUtils.equals(Constants.HYBRID_PACKAGE_NAME, c0729jb.m2037b()) && !TextUtils.equals(Constants.HYBRID_DEBUG_PACKAGE_NAME, c0729jb.m2037b())) {
            return false;
        }
        Map<String, String> m1826a = c0729jb.m2022a() == null ? null : c0729jb.m2022a().m1826a();
        if (m1826a == null) {
            return false;
        }
        String str = m1826a.get(Constants.EXTRA_KEY_PUSH_SERVER_ACTION);
        return TextUtils.equals(str, Constants.EXTRA_VALUE_HYBRID_MESSAGE) || TextUtils.equals(str, Constants.EXTRA_VALUE_PLATFORM_MESSAGE);
    }

    /* renamed from: b */
    private void m215b(C0729jb c0729jb) {
        C0717iq m2022a = c0729jb.m2022a();
        C0722iv c0722iv = new C0722iv();
        c0722iv.m1898b(c0729jb.m2029a());
        c0722iv.m1892a(m2022a.m1825a());
        c0722iv.m1891a(m2022a.m1820a());
        if (!TextUtils.isEmpty(m2022a.m1835b())) {
            c0722iv.m1902c(m2022a.m1835b());
        }
        c0722iv.m1893a(C0743jp.m2311a(this.f304a, c0729jb));
        C0449az.m224a(this.f304a).m254a((C0449az) c0722iv, EnumC0696hw.AckMessage, false, c0729jb.m2022a());
    }

    /* renamed from: a */
    public PushMessageHandler.InterfaceC0422a m216a(Intent intent) {
        String str;
        String action = intent.getAction();
        AbstractC0407b.m70a("receive an intent from server, action=" + action);
        String stringExtra = intent.getStringExtra("mrt");
        String str2 = stringExtra;
        if (stringExtra == null) {
            str2 = Long.toString(System.currentTimeMillis());
        }
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            String stringExtra2 = intent.getStringExtra("messageId");
            int intExtra = intent.getIntExtra("eventMessageType", -1);
            if (byteArrayExtra == null) {
                AbstractC0407b.m75d("receiving an empty message, drop");
                C0631fl.m1256a(this.f304a).m1260a(this.f304a.getPackageName(), intent, "receiving an empty message, drop");
                return null;
            }
            C0729jb c0729jb = new C0729jb();
            try {
                C0743jp.m2313a(c0729jb, byteArrayExtra);
                C0461d m289a = C0461d.m289a(this.f304a);
                C0717iq m2022a = c0729jb.m2022a();
                if (c0729jb.m2021a() == EnumC0696hw.SendMessage && m2022a != null && !m289a.m311d() && !booleanExtra) {
                    m2022a.m1828a("mrt", str2);
                    m2022a.m1828a("mat", Long.toString(System.currentTimeMillis()));
                    if (m214a(c0729jb)) {
                        AbstractC0407b.m73b("this is a mina's message, ack later");
                        m2022a.m1828a(Constants.EXTRA_KEY_HYBRID_MESSAGE_TS, String.valueOf(m2022a.m1820a()));
                        m2022a.m1828a(Constants.EXTRA_KEY_HYBRID_DEVICE_STATUS, String.valueOf((int) C0743jp.m2311a(this.f304a, c0729jb)));
                    } else {
                        m215b(c0729jb);
                    }
                }
                if (c0729jb.m2021a() == EnumC0696hw.SendMessage && !c0729jb.m2039b()) {
                    if (!C0803ai.m2530a(c0729jb)) {
                        AbstractC0407b.m70a(String.format("drop an un-encrypted messages. %1$s, %2$s", c0729jb.m2037b(), m2022a != null ? m2022a.m1825a() : ""));
                        C0631fl.m1256a(this.f304a).m1260a(this.f304a.getPackageName(), intent, String.format("drop an un-encrypted messages. %1$s, %2$s", c0729jb.m2037b(), m2022a != null ? m2022a.m1825a() : ""));
                        return null;
                    }
                    if (!booleanExtra || m2022a.m1826a() == null || !m2022a.m1826a().containsKey("notify_effect")) {
                        AbstractC0407b.m70a(String.format("drop an un-encrypted messages. %1$s, %2$s", c0729jb.m2037b(), m2022a.m1825a()));
                        C0631fl.m1256a(this.f304a).m1260a(this.f304a.getPackageName(), intent, String.format("drop an un-encrypted messages. %1$s, %2$s", c0729jb.m2037b(), m2022a != null ? m2022a.m1825a() : ""));
                        return null;
                    }
                }
                if (!m289a.m309c() && c0729jb.f1997a != EnumC0696hw.Registration) {
                    if (C0803ai.m2530a(c0729jb)) {
                        return m201a(c0729jb, booleanExtra, byteArrayExtra, stringExtra2, intExtra);
                    }
                    AbstractC0407b.m75d("receive message without registration. need re-register!");
                    C0631fl.m1256a(this.f304a).m1260a(this.f304a.getPackageName(), intent, "receive message without registration. need re-register!");
                    m204a();
                    return null;
                }
                if (!m289a.m309c() || !m289a.m313e()) {
                    return m201a(c0729jb, booleanExtra, byteArrayExtra, stringExtra2, intExtra);
                }
                if (c0729jb.f1997a != EnumC0696hw.UnRegistration) {
                    MiPushClient.unregisterPush(this.f304a);
                    return null;
                }
                m289a.m294a();
                MiPushClient.clearExtras(this.f304a);
                PushMessageHandler.m154a();
                return null;
            } catch (C0749jv | Exception e) {
                e = e;
                C0631fl.m1256a(this.f304a).m1261a(this.f304a.getPackageName(), intent, e);
            }
        } else {
            if ("com.xiaomi.mipush.ERROR".equals(action)) {
                MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
                C0729jb c0729jb2 = new C0729jb();
                try {
                    byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                    if (byteArrayExtra2 != null) {
                        C0743jp.m2313a(c0729jb2, byteArrayExtra2);
                    }
                } catch (C0749jv e2) {
                }
                miPushCommandMessage.setCommand(String.valueOf(c0729jb2.m2021a()));
                miPushCommandMessage.setResultCode(intent.getIntExtra("mipush_error_code", 0));
                miPushCommandMessage.setReason(intent.getStringExtra("mipush_error_msg"));
                AbstractC0407b.m75d("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
                return miPushCommandMessage;
            }
            if (!"com.xiaomi.mipush.MESSAGE_ARRIVED".equals(action)) {
                return null;
            }
            byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra3 == null) {
                AbstractC0407b.m75d("message arrived: receiving an empty message, drop");
                return null;
            }
            C0729jb c0729jb3 = new C0729jb();
            try {
                C0743jp.m2313a(c0729jb3, byteArrayExtra3);
                C0461d m289a2 = C0461d.m289a(this.f304a);
                if (C0803ai.m2530a(c0729jb3)) {
                    str = "message arrived: receive ignore reg message, ignore!";
                } else if (!m289a2.m309c()) {
                    str = "message arrived: receive message without registration. need unregister or re-register!";
                } else {
                    if (!m289a2.m309c() || !m289a2.m313e()) {
                        return m202a(c0729jb3, byteArrayExtra3);
                    }
                    str = "message arrived: app info is invalidated";
                }
                AbstractC0407b.m75d(str);
                return null;
            } catch (C0749jv e3) {
                e = e3;
            } catch (Exception e4) {
                e = e4;
            }
        }
        AbstractC0407b.m72a(e);
        return null;
    }

    /* renamed from: a */
    public List<String> m217a(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        if (timeZone.equals(timeZone2)) {
            return list;
        }
        long rawOffset = ((timeZone.getRawOffset() - timeZone2.getRawOffset()) / PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT) / 60;
        long parseLong = Long.parseLong(list.get(0).split(Constants.COLON_SEPARATOR)[0]);
        long parseLong2 = ((((parseLong * 60) + Long.parseLong(list.get(0).split(Constants.COLON_SEPARATOR)[1])) - rawOffset) + 1440) % 1440;
        long parseLong3 = ((((Long.parseLong(list.get(1).split(Constants.COLON_SEPARATOR)[0]) * 60) + Long.parseLong(list.get(1).split(Constants.COLON_SEPARATOR)[1])) - rawOffset) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(parseLong2 / 60), Long.valueOf(parseLong2 % 60)));
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(parseLong3 / 60), Long.valueOf(parseLong3 % 60)));
        return arrayList;
    }
}
