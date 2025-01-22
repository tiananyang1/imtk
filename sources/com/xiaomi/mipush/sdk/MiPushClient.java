package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.text.TextUtils;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.stub.StubApp;
import com.sun.jna.Callback;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.manager.ClientReportClient;
import com.xiaomi.mipush.sdk.MiTinyDataClient;
import com.xiaomi.push.AbstractC0772p;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0585dt;
import com.xiaomi.push.C0628fi;
import com.xiaomi.push.C0629fj;
import com.xiaomi.push.C0630fk;
import com.xiaomi.push.C0646g;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0726iz;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0733jf;
import com.xiaomi.push.C0737jj;
import com.xiaomi.push.C0739jl;
import com.xiaomi.push.C0741jn;
import com.xiaomi.push.C0770n;
import com.xiaomi.push.C0878t;
import com.xiaomi.push.C0880v;
import com.xiaomi.push.EnumC0636fq;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.EnumC0719is;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.C0812ar;
import com.xiaomi.push.service.C0859j;
import com.xiaomi.push.service.C0865o;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MiPushClient.class */
public abstract class MiPushClient {
    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_SET_ACCEPT_TIME = "accept-time";
    public static final String COMMAND_SET_ACCOUNT = "set-account";
    public static final String COMMAND_SET_ALIAS = "set-alias";
    public static final String COMMAND_SUBSCRIBE_TOPIC = "subscribe-topic";
    public static final String COMMAND_UNREGISTER = "unregister";
    public static final String COMMAND_UNSET_ACCOUNT = "unset-account";
    public static final String COMMAND_UNSET_ALIAS = "unset-alias";
    public static final String COMMAND_UNSUBSCRIBE_TOPIC = "unsubscibe-topic";
    public static final String PREF_EXTRA = "mipush_extra";
    private static boolean isCrashHandlerSuggested;
    private static C0458bh mSyncMIIDHelper;
    private static Context sContext;
    private static long sCurMsgId = System.currentTimeMillis();

    @Deprecated
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MiPushClient$MiPushClientCallback.class */
    public static abstract class MiPushClientCallback {
        private String category;

        /* JADX INFO: Access modifiers changed from: protected */
        public String getCategory() {
            return this.category;
        }

        public void onCommandResult(String str, long j, String str2, List<String> list) {
        }

        public void onInitializeResult(long j, String str, String str2) {
        }

        public void onReceiveMessage(MiPushMessage miPushMessage) {
        }

        public void onReceiveMessage(String str, String str2, String str3, boolean z) {
        }

        public void onSubscribeResult(long j, String str, String str2) {
        }

        public void onUnsubscribeResult(long j, String str, String str2) {
        }

        protected void setCategory(String str) {
            this.category = str;
        }
    }

    private static boolean acceptTimeSet(Context context, String str, String str2) {
        return TextUtils.equals(getAcceptTime(context), str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2);
    }

    public static long accountSetTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("account_" + str, -1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addAcceptTime(Context context, String str, String str2) {
        synchronized (MiPushClient.class) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
                edit.putString(Constants.EXTRA_KEY_ACCEPT_TIME, str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2);
                C0878t.m2846a(edit);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            try {
                context.getSharedPreferences("mipush_extra", 0).edit().putLong("account_" + str, System.currentTimeMillis()).commit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            try {
                context.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + str, System.currentTimeMillis()).commit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static void addPullNotificationTime(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_pull_notification", System.currentTimeMillis());
        C0878t.m2846a(edit);
    }

    private static void addRegRequestTime(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_reg_request", System.currentTimeMillis());
        C0878t.m2846a(edit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            try {
                context.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + str, System.currentTimeMillis()).commit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static long aliasSetTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("alias_" + str, -1L);
    }

    public static void awakeApps(Context context, String[] strArr) {
        C0493ai.m439a(context).m443a(new RunnableC0433aj(strArr, context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void awakePushServiceByPackageInfo(Context context, PackageInfo packageInfo) {
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
                    Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                    Intent intent = new Intent();
                    intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                    intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                    intent.putExtra("waker_pkgname", context.getPackageName());
                    PushMessageHandler.m157a(context, intent);
                    return;
                } catch (Throwable th) {
                    return;
                }
            }
            i = i2 + 1;
        }
    }

    private static void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException("param " + str + " is not nullable");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean checkPermission(android.content.Context r4) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.MiPushClient.checkPermission(android.content.Context):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void clearExtras(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.clear();
        edit.commit();
    }

    public static void clearLocalNotificationType(Context context) {
        C0449az.m224a(context).m269e();
    }

    public static void clearNotification(Context context) {
        C0449az.m224a(context).m246a(-1);
    }

    public static void clearNotification(Context context, int i) {
        C0449az.m224a(context).m246a(i);
    }

    public static void clearNotification(Context context, String str, String str2) {
        C0449az.m224a(context).m259a(str, str2);
    }

    public static void disablePush(Context context) {
        C0449az.m224a(context).m260a(true);
    }

    private static void enableGeo(Context context, boolean z) {
        if (Math.abs(System.currentTimeMillis() - getGeoEnableTime(context, String.valueOf(z))) > 60000) {
            C0859j.m2716a(context, z);
            C0480w.m394a(context, z);
            setGeoEnableTime(context, String.valueOf(z));
        }
    }

    public static void enablePush(Context context) {
        C0449az.m224a(context).m260a(false);
    }

    private static void forceHandleCrash() {
        boolean m2563a = C0809ao.m2557a(sContext).m2563a(EnumC0703ic.ForceHandleCrashSwitch.m1669a(), false);
        if (isCrashHandlerSuggested || !m2563a) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new C0483z(sContext));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getAcceptTime(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getString(Constants.EXTRA_KEY_ACCEPT_TIME, "00:00-23:59");
    }

    public static List<String> getAllAlias(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring(6));
            }
        }
        return arrayList;
    }

    public static List<String> getAllTopic(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("topic_") && !str.contains("**ALL**")) {
                arrayList.add(str.substring(6));
            }
        }
        return arrayList;
    }

    public static List<String> getAllUserAccount(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("account_")) {
                arrayList.add(str.substring(8));
            }
        }
        return arrayList;
    }

    public static String getAppRegion(Context context) {
        if (C0461d.m289a(context).m309c()) {
            return C0461d.m289a(context).m314f();
        }
        return null;
    }

    private static boolean getDefaultSwitch() {
        return C0770n.m2406b();
    }

    private static long getGeoEnableTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("geo_" + str, -1L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getOpenFCMPush() {
        return C0464g.m331a(sContext).m338b(EnumC0463f.ASSEMBLE_PUSH_FCM);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getOpenHmsPush() {
        return C0464g.m331a(sContext).m338b(EnumC0463f.ASSEMBLE_PUSH_HUAWEI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getOpenOPPOPush() {
        return C0464g.m331a(sContext).m338b(EnumC0463f.ASSEMBLE_PUSH_COS);
    }

    public static String getRegId(Context context) {
        if (C0461d.m289a(context).m309c()) {
            return C0461d.m289a(context).m308c();
        }
        return null;
    }

    private static void initEventPerfLogic(Context context) {
        C0630fk.m1254a(new C0434ak());
        Config m1242a = C0630fk.m1242a(context);
        ClientReportClient.init(context, m1242a, new C0628fi(context), new C0629fj(context));
        C0423a.m170a(context);
        C0475r.m373a(context, m1242a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        if (miPushClientCallback != null) {
            try {
                PushMessageHandler.m163a(miPushClientCallback);
            } catch (Throwable th) {
                AbstractC0407b.m72a(th);
                return;
            }
        }
        if (C0880v.m2852a(sContext)) {
            C0425ab.m172a(sContext);
        }
        if (C0461d.m289a(sContext).m301a(str, str2) || checkPermission(sContext)) {
            boolean z = C0461d.m289a(sContext).m291a() != Constants.m119a();
            if (!z && !shouldSendRegRequest(sContext)) {
                C0449az.m224a(sContext).m245a();
                AbstractC0407b.m70a("Could not send  register message within 5s repeatly .");
                return;
            }
            if (z || !C0461d.m289a(sContext).m301a(str, str2) || C0461d.m289a(sContext).m313e()) {
                String m520a = C0509ay.m520a(6);
                C0461d.m289a(sContext).m294a();
                C0461d.m289a(sContext).m295a(Constants.m119a());
                C0461d.m289a(sContext).m298a(str, str2, m520a);
                MiTinyDataClient.C0421a.m137a().m146b(MiTinyDataClient.PENDING_REASON_APPID);
                clearExtras(sContext);
                C0733jf c0733jf = new C0733jf();
                c0733jf.m2097a(C0812ar.m2571a());
                c0733jf.m2104b(str);
                c0733jf.m2116e(str2);
                c0733jf.m2113d(sContext.getPackageName());
                c0733jf.m2119f(m520a);
                c0733jf.m2109c(C0646g.m1359a(sContext, sContext.getPackageName()));
                c0733jf.m2103b(C0646g.m1356a(sContext, sContext.getPackageName()));
                c0733jf.m2122g("3_6_12");
                c0733jf.m2095a(30612);
                c0733jf.m2125h(C0727j.m1992e(sContext));
                c0733jf.m2096a(EnumC0719is.Init);
                if (!C0770n.m2408d()) {
                    String m1994g = C0727j.m1994g(sContext);
                    String m1996i = C0727j.m1996i(sContext);
                    if (!TextUtils.isEmpty(m1994g)) {
                        String str3 = m1994g;
                        if (C0770n.m2406b()) {
                            str3 = m1994g;
                            if (!TextUtils.isEmpty(m1996i)) {
                                str3 = m1994g + Constants.ACCEPT_TIME_SEPARATOR_SP + m1996i;
                            }
                            c0733jf.m2127i(str3);
                        }
                        c0733jf.m2131k(C0509ay.m521a(str3) + Constants.ACCEPT_TIME_SEPARATOR_SP + C0727j.m1997j(sContext));
                    }
                }
                c0733jf.m2129j(C0727j.m1980a());
                int m1979a = C0727j.m1979a();
                if (m1979a >= 0) {
                    c0733jf.m2108c(m1979a);
                }
                C0449az.m224a(sContext).m250a(c0733jf, z);
                C0450b.m272a(sContext);
                sContext.getSharedPreferences("mipush_extra", 4).getBoolean("mipush_registed", true);
            } else {
                if (1 == PushMessageHelper.getPushMode(sContext)) {
                    checkNotNull(miPushClientCallback, Callback.METHOD_NAME);
                    miPushClientCallback.onInitializeResult(0L, null, C0461d.m289a(sContext).m308c());
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(C0461d.m289a(sContext).m308c());
                    PushMessageHelper.sendCommandMessageBroadcast(sContext, PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_REGISTER.f934a, arrayList, 0L, null, null));
                }
                C0449az.m224a(sContext).m245a();
                if (C0461d.m289a(sContext).m300a()) {
                    C0732je c0732je = new C0732je();
                    c0732je.m2071b(C0461d.m289a(sContext).m293a());
                    c0732je.m2075c("client_info_update");
                    c0732je.m2058a(C0812ar.m2571a());
                    c0732je.f2033a = new HashMap();
                    c0732je.f2033a.put(Constants.EXTRA_KEY_APP_VERSION, C0646g.m1359a(sContext, sContext.getPackageName()));
                    c0732je.f2033a.put(Constants.EXTRA_KEY_APP_VERSION_CODE, Integer.toString(C0646g.m1356a(sContext, sContext.getPackageName())));
                    c0732je.f2033a.put("push_sdk_vn", "3_6_12");
                    c0732je.f2033a.put("push_sdk_vc", Integer.toString(30612));
                    String m312e = C0461d.m289a(sContext).m312e();
                    if (!TextUtils.isEmpty(m312e)) {
                        c0732je.f2033a.put("deviceid", m312e);
                    }
                    C0449az.m224a(sContext).m254a((C0449az) c0732je, EnumC0696hw.Notification, false, (C0717iq) null);
                    C0450b.m272a(sContext);
                }
                if (!AbstractC0772p.m2413a(sContext, "update_devId", false)) {
                    updateIMEI();
                    AbstractC0772p.m2411a(sContext, "update_devId", true);
                }
                String m1991d = C0727j.m1991d(sContext);
                if (!TextUtils.isEmpty(m1991d)) {
                    C0726iz c0726iz = new C0726iz();
                    c0726iz.m1956a(C0812ar.m2571a());
                    c0726iz.m1964b(str);
                    c0726iz.m1967c(EnumC0636fq.COMMAND_CHK_VDEVID.f934a);
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(C0727j.m1990c(sContext));
                    arrayList2.add(m1991d);
                    arrayList2.add(Build.MODEL != null ? Build.MODEL : "");
                    arrayList2.add(Build.BOARD != null ? Build.BOARD : "");
                    c0726iz.m1957a(arrayList2);
                    C0449az.m224a(sContext).m254a((C0449az) c0726iz, EnumC0696hw.Command, false, (C0717iq) null);
                }
                if (shouldUseMIUIPush(sContext) && shouldPullNotification(sContext)) {
                    C0732je c0732je2 = new C0732je();
                    c0732je2.m2071b(C0461d.m289a(sContext).m293a());
                    c0732je2.m2075c(EnumC0714in.PullOfflineMessage.f1752a);
                    c0732je2.m2058a(C0812ar.m2571a());
                    c0732je2.m2061a(false);
                    C0449az.m224a(sContext).m255a((C0449az) c0732je2, EnumC0696hw.Notification, false, (C0717iq) null, false);
                    addPullNotificationTime(sContext);
                }
            }
            addRegRequestTime(sContext);
            scheduleOcVersionCheckJob();
            scheduleGeoFenceLocUploadJobs();
            scheduleDataCollectionJobs(sContext);
            initEventPerfLogic(sContext);
            C0456bf.m279a(sContext);
            forceHandleCrash();
            if (!sContext.getPackageName().equals("com.xiaomi.xmsf")) {
                Logger.setLogger(sContext, Logger.getUserLogger());
                AbstractC0407b.m64a(2);
            }
            try {
                if (mSyncMIIDHelper == null) {
                    mSyncMIIDHelper = new C0458bh(sContext);
                }
                mSyncMIIDHelper.m286a(sContext);
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
            }
            if ("syncing".equals(C0439ap.m186a(sContext).m188a(EnumC0455be.DISABLE_PUSH))) {
                disablePush(sContext);
            }
            if ("syncing".equals(C0439ap.m186a(sContext).m188a(EnumC0455be.ENABLE_PUSH))) {
                enablePush(sContext);
            }
            if ("syncing".equals(C0439ap.m186a(sContext).m188a(EnumC0455be.UPLOAD_HUAWEI_TOKEN))) {
                syncAssemblePushToken(sContext);
            }
            if ("syncing".equals(C0439ap.m186a(sContext).m188a(EnumC0455be.UPLOAD_FCM_TOKEN))) {
                syncAssembleFCMPushToken(sContext);
            }
            if ("syncing".equals(C0439ap.m186a(context).m188a(EnumC0455be.UPLOAD_COS_TOKEN))) {
                syncAssembleCOSPushToken(sContext);
            }
        }
    }

    public static void pausePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 0, 0, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reInitialize(Context context, EnumC0719is enumC0719is) {
        if (C0461d.m289a(context).m309c()) {
            String m520a = C0509ay.m520a(6);
            String m293a = C0461d.m289a(context).m293a();
            String m303b = C0461d.m289a(context).m303b();
            C0461d.m289a(context).m294a();
            C0461d.m289a(context).m295a(Constants.m119a());
            C0461d.m289a(context).m298a(m293a, m303b, m520a);
            C0733jf c0733jf = new C0733jf();
            c0733jf.m2097a(C0812ar.m2571a());
            c0733jf.m2104b(m293a);
            c0733jf.m2116e(m303b);
            c0733jf.m2119f(m520a);
            c0733jf.m2113d(context.getPackageName());
            c0733jf.m2109c(C0646g.m1359a(context, context.getPackageName()));
            c0733jf.m2096a(enumC0719is);
            C0449az.m224a(context).m250a(c0733jf, false);
        }
    }

    public static void registerCrashHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        Thread.setDefaultUncaughtExceptionHandler(new C0483z(sContext, uncaughtExceptionHandler));
        isCrashHandlerSuggested = true;
    }

    private static void registerNetworkReceiver(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            StubApp.getOrigApplicationContext(context.getApplicationContext()).registerReceiver(new NetworkStatusReceiver(null), intentFilter);
        } catch (Throwable th) {
            AbstractC0407b.m72a(th);
        }
    }

    public static void registerPush(Context context, String str, String str2) {
        registerPush(context, str, str2, new PushConfiguration());
    }

    public static void registerPush(Context context, String str, String str2, PushConfiguration pushConfiguration) {
        checkNotNull(context, "context");
        checkNotNull(str, "appID");
        checkNotNull(str2, "appToken");
        sContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (sContext == null) {
            sContext = context;
        }
        Context context2 = sContext;
        if (!NetworkStatusReceiver.m2789a()) {
            registerNetworkReceiver(sContext);
        }
        C0464g.m331a(sContext).m334a(pushConfiguration);
        enableGeo(sContext, pushConfiguration.getGeoEnable());
        C0450b.m271a();
        C0493ai.m439a(context2).m443a(new RunnableC0430ag(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeAcceptTime(Context context) {
        synchronized (MiPushClient.class) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
                edit.remove(Constants.EXTRA_KEY_ACCEPT_TIME);
                C0878t.m2846a(edit);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            try {
                context.getSharedPreferences("mipush_extra", 0).edit().remove("account_" + str).commit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            try {
                context.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + str).commit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeAllAccounts(Context context) {
        synchronized (MiPushClient.class) {
            try {
                Iterator<String> it = getAllUserAccount(context).iterator();
                while (it.hasNext()) {
                    removeAccount(context, it.next());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeAllAliases(Context context) {
        synchronized (MiPushClient.class) {
            try {
                Iterator<String> it = getAllAlias(context).iterator();
                while (it.hasNext()) {
                    removeAlias(context, it.next());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeAllTopics(Context context) {
        synchronized (MiPushClient.class) {
            try {
                Iterator<String> it = getAllTopic(context).iterator();
                while (it.hasNext()) {
                    removeTopic(context, it.next());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            try {
                context.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + str).commit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void reportAppRunInBackground(Context context, boolean z) {
        if (C0461d.m289a(context).m307b()) {
            EnumC0714in enumC0714in = z ? EnumC0714in.APP_SLEEP : EnumC0714in.APP_WAKEUP;
            C0732je c0732je = new C0732je();
            c0732je.m2071b(C0461d.m289a(context).m293a());
            c0732je.m2075c(enumC0714in.f1752a);
            c0732je.m2079d(context.getPackageName());
            c0732je.m2058a(C0812ar.m2571a());
            c0732je.m2061a(false);
            C0449az.m224a(context).m255a((C0449az) c0732je, EnumC0696hw.Notification, false, (C0717iq) null, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reportIgnoreRegMessageClicked(Context context, String str, C0717iq c0717iq, String str2, String str3) {
        C0732je c0732je = new C0732je();
        if (TextUtils.isEmpty(str3)) {
            AbstractC0407b.m75d("do not report clicked message");
            return;
        }
        c0732je.m2071b(str3);
        c0732je.m2075c("bar:click");
        c0732je.m2058a(str);
        c0732je.m2061a(false);
        C0449az.m224a(context).m257a(c0732je, EnumC0696hw.Notification, false, true, c0717iq, true, str2, str3);
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        C0717iq c0717iq = new C0717iq();
        c0717iq.m1823a(miPushMessage.getMessageId());
        c0717iq.m1834b(miPushMessage.getTopic());
        c0717iq.m1846d(miPushMessage.getDescription());
        c0717iq.m1842c(miPushMessage.getTitle());
        c0717iq.m1841c(miPushMessage.getNotifyId());
        c0717iq.m1822a(miPushMessage.getNotifyType());
        c0717iq.m1833b(miPushMessage.getPassThrough());
        c0717iq.m1824a(miPushMessage.getExtra());
        reportMessageClicked(context, miPushMessage.getMessageId(), c0717iq, null);
    }

    @Deprecated
    public static void reportMessageClicked(Context context, String str) {
        reportMessageClicked(context, str, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reportMessageClicked(Context context, String str, C0717iq c0717iq, String str2) {
        C0732je c0732je = new C0732je();
        String str3 = str2;
        if (TextUtils.isEmpty(str2)) {
            if (!C0461d.m289a(context).m307b()) {
                AbstractC0407b.m75d("do not report clicked message");
                return;
            }
            str3 = C0461d.m289a(context).m293a();
        }
        c0732je.m2071b(str3);
        c0732je.m2075c("bar:click");
        c0732je.m2058a(str);
        c0732je.m2061a(false);
        C0449az.m224a(context).m254a((C0449az) c0732je, EnumC0696hw.Notification, false, c0717iq);
    }

    public static void resumePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 23, 59, str);
    }

    private static void scheduleDataCollectionJobs(Context context) {
        if (C0809ao.m2557a(sContext).m2563a(EnumC0703ic.DataCollectionSwitch.m1669a(), getDefaultSwitch())) {
            C0585dt.m946a().m948a(new C0474q(context));
            C0493ai.m439a(sContext).m444a(new RunnableC0431ah(), 10);
        }
    }

    private static void scheduleGeoFenceLocUploadJobs() {
        if (C0859j.m2723e(sContext) && !TextUtils.equals("com.xiaomi.xmsf", sContext.getPackageName()) && C0809ao.m2557a(sContext).m2563a(EnumC0703ic.UploadGeoAppLocSwitch.m1669a(), true) && !C0880v.m2853b()) {
            C0478u.m385a(sContext, true);
            int max = Math.max(60, C0809ao.m2557a(sContext).m2561a(EnumC0703ic.UploadWIFIGeoLocFrequency.m1669a(), 900));
            C0493ai.m439a(sContext).m447a(new C0478u(sContext, max), max, max);
        }
    }

    private static void scheduleOcVersionCheckJob() {
        C0493ai.m439a(sContext).m447a(new C0438ao(sContext), C0809ao.m2557a(sContext).m2561a(EnumC0703ic.OcVersionCheckFrequency.m1669a(), 86400), 5);
    }

    public static void setAcceptTime(Context context, int i, int i2, int i3, int i4, String str) {
        if (i < 0 || i >= 24 || i3 < 0 || i3 >= 24 || i2 < 0 || i2 >= 60 || i4 < 0 || i4 >= 60) {
            throw new IllegalArgumentException("the input parameter is not valid.");
        }
        long rawOffset = ((TimeZone.getTimeZone("GMT+08").getRawOffset() - TimeZone.getDefault().getRawOffset()) / PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT) / 60;
        long j = ((((i * 60) + i2) + rawOffset) + 1440) % 1440;
        long j2 = ((((i3 * 60) + i4) + rawOffset) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(j / 60), Long.valueOf(j % 60)));
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(j2 / 60), Long.valueOf(j2 % 60)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(String.format("%1$02d:%2$02d", Integer.valueOf(i), Integer.valueOf(i2)));
        arrayList2.add(String.format("%1$02d:%2$02d", Integer.valueOf(i3), Integer.valueOf(i4)));
        if (!acceptTimeSet(context, (String) arrayList.get(0), (String) arrayList.get(1))) {
            setCommand(context, EnumC0636fq.COMMAND_SET_ACCEPT_TIME.f934a, (ArrayList<String>) arrayList, str);
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.m162a(context, str, EnumC0636fq.COMMAND_SET_ACCEPT_TIME.f934a, 0L, null, arrayList2);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_SET_ACCEPT_TIME.f934a, arrayList2, 0L, null, null));
        }
    }

    public static void setAlias(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setCommand(context, EnumC0636fq.COMMAND_SET_ALIAS.f934a, str, str2);
    }

    protected static void setCommand(Context context, String str, String str2, String str3) {
        StringBuilder sb;
        String str4;
        EnumC0636fq enumC0636fq;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (EnumC0636fq.COMMAND_SET_ALIAS.f934a.equalsIgnoreCase(str) && Math.abs(System.currentTimeMillis() - aliasSetTime(context, str2)) < 3600000) {
            if (1 != PushMessageHelper.getPushMode(context)) {
                enumC0636fq = EnumC0636fq.COMMAND_SET_ALIAS;
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(enumC0636fq.f934a, arrayList, 0L, null, str3));
                return;
            }
            PushMessageHandler.m162a(context, str3, str, 0L, null, arrayList);
            return;
        }
        if (EnumC0636fq.COMMAND_UNSET_ALIAS.f934a.equalsIgnoreCase(str) && aliasSetTime(context, str2) < 0) {
            sb = new StringBuilder();
            str4 = "Don't cancel alias for ";
        } else {
            if (EnumC0636fq.COMMAND_SET_ACCOUNT.f934a.equalsIgnoreCase(str) && Math.abs(System.currentTimeMillis() - accountSetTime(context, str2)) < 3600000) {
                if (1 != PushMessageHelper.getPushMode(context)) {
                    enumC0636fq = EnumC0636fq.COMMAND_SET_ACCOUNT;
                    PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(enumC0636fq.f934a, arrayList, 0L, null, str3));
                    return;
                }
                PushMessageHandler.m162a(context, str3, str, 0L, null, arrayList);
                return;
            }
            if (!EnumC0636fq.COMMAND_UNSET_ACCOUNT.f934a.equalsIgnoreCase(str) || accountSetTime(context, str2) >= 0) {
                setCommand(context, str, (ArrayList<String>) arrayList, str3);
                return;
            } else {
                sb = new StringBuilder();
                str4 = "Don't cancel account for ";
            }
        }
        sb.append(str4);
        sb.append(C0509ay.m522a(arrayList.toString(), 3));
        sb.append(" is unseted");
        AbstractC0407b.m70a(sb.toString());
    }

    protected static void setCommand(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (TextUtils.isEmpty(C0461d.m289a(context).m293a())) {
            return;
        }
        C0726iz c0726iz = new C0726iz();
        c0726iz.m1956a(C0812ar.m2571a());
        c0726iz.m1964b(C0461d.m289a(context).m293a());
        c0726iz.m1967c(str);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            c0726iz.m1960a(it.next());
        }
        c0726iz.m1972e(str2);
        c0726iz.m1970d(context.getPackageName());
        C0449az.m224a(context).m252a((C0449az) c0726iz, EnumC0696hw.Command, (C0717iq) null);
    }

    static void setGeoEnableTime(Context context, String str) {
        synchronized (MiPushClient.class) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
                edit.putLong("geo_" + str, System.currentTimeMillis());
                C0878t.m2846a(edit);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void setLocalNotificationType(Context context, int i) {
        C0449az.m224a(context).m265b(i & (-1));
    }

    public static void setUserAccount(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setCommand(context, EnumC0636fq.COMMAND_SET_ACCOUNT.f934a, str, str2);
    }

    private static boolean shouldPullNotification(Context context) {
        boolean z = false;
        if (Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification", -1L)) > 300000) {
            z = true;
        }
        return z;
    }

    private static boolean shouldSendRegRequest(Context context) {
        boolean z = false;
        if (Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_reg_request", -1L)) > 5000) {
            z = true;
        }
        return z;
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return C0449az.m224a(context).m262a();
    }

    public static void subscribe(Context context, String str, String str2) {
        if (TextUtils.isEmpty(C0461d.m289a(context).m293a()) || TextUtils.isEmpty(str)) {
            return;
        }
        if (Math.abs(System.currentTimeMillis() - topicSubscribedTime(context, str)) <= 86400000) {
            if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.m161a(context, str2, 0L, null, str);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(EnumC0636fq.COMMAND_SUBSCRIBE_TOPIC.f934a, arrayList, 0L, null, null));
            return;
        }
        C0737jj c0737jj = new C0737jj();
        c0737jj.m2212a(C0812ar.m2571a());
        c0737jj.m2216b(C0461d.m289a(context).m293a());
        c0737jj.m2218c(str);
        c0737jj.m2220d(context.getPackageName());
        c0737jj.m2222e(str2);
        C0449az.m224a(context).m252a((C0449az) c0737jj, EnumC0696hw.Subscription, (C0717iq) null);
    }

    public static void syncAssembleCOSPushToken(Context context) {
        C0449az.m224a(context).m258a((String) null, EnumC0455be.UPLOAD_COS_TOKEN, EnumC0463f.ASSEMBLE_PUSH_COS);
    }

    public static void syncAssembleFCMPushToken(Context context) {
        C0449az.m224a(context).m258a((String) null, EnumC0455be.UPLOAD_FCM_TOKEN, EnumC0463f.ASSEMBLE_PUSH_FCM);
    }

    public static void syncAssemblePushToken(Context context) {
        C0449az.m224a(context).m258a((String) null, EnumC0455be.UPLOAD_HUAWEI_TOKEN, EnumC0463f.ASSEMBLE_PUSH_HUAWEI);
    }

    public static long topicSubscribedTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("topic_" + str, -1L);
    }

    public static void unregisterPush(Context context) {
        C0466i.m355c(context);
        if (C0461d.m289a(context).m307b()) {
            C0739jl c0739jl = new C0739jl();
            c0739jl.m2243a(C0812ar.m2571a());
            c0739jl.m2248b(C0461d.m289a(context).m293a());
            c0739jl.m2251c(C0461d.m289a(context).m308c());
            c0739jl.m2255e(C0461d.m289a(context).m303b());
            c0739jl.m2253d(context.getPackageName());
            C0449az.m224a(context).m251a(c0739jl);
            PushMessageHandler.m154a();
            C0461d.m289a(context).m304b();
            clearLocalNotificationType(context);
            clearNotification(context);
            if (mSyncMIIDHelper != null) {
                C0865o.m2771a(context).m2781b(mSyncMIIDHelper);
            }
            clearExtras(context);
        }
    }

    public static void unsetAlias(Context context, String str, String str2) {
        setCommand(context, EnumC0636fq.COMMAND_UNSET_ALIAS.f934a, str, str2);
    }

    public static void unsetUserAccount(Context context, String str, String str2) {
        setCommand(context, EnumC0636fq.COMMAND_UNSET_ACCOUNT.f934a, str, str2);
    }

    public static void unsubscribe(Context context, String str, String str2) {
        if (C0461d.m289a(context).m307b()) {
            if (topicSubscribedTime(context, str) < 0) {
                AbstractC0407b.m70a("Don't cancel subscribe for " + str + " is unsubscribed");
                return;
            }
            C0741jn c0741jn = new C0741jn();
            c0741jn.m2281a(C0812ar.m2571a());
            c0741jn.m2285b(C0461d.m289a(context).m293a());
            c0741jn.m2287c(str);
            c0741jn.m2289d(context.getPackageName());
            c0741jn.m2291e(str2);
            C0449az.m224a(context).m252a((C0449az) c0741jn, EnumC0696hw.UnSubscription, (C0717iq) null);
        }
    }

    private static void updateIMEI() {
        new Thread(new RunnableC0432ai()).start();
    }
}
