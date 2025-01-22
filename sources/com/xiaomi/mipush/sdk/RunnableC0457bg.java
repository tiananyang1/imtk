package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.AbstractC0772p;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0646g;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0770n;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.C0812ar;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.bg */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/bg.class */
public final class RunnableC0457bg implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f335a;

    /* renamed from: a */
    final /* synthetic */ boolean f336a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0457bg(Context context, boolean z) {
        this.f335a = context;
        this.f336a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        String m284d;
        String m284d2;
        Map<String, String> map;
        String m284d3;
        String str;
        String str2;
        String m283c;
        String m283c2;
        String m283c3;
        AbstractC0407b.m70a("do sync info");
        C0732je c0732je = new C0732je(C0812ar.m2571a(), false);
        C0461d m289a = C0461d.m289a(this.f335a);
        c0732je.m2075c(EnumC0714in.SyncInfo.f1752a);
        c0732je.m2071b(m289a.m293a());
        c0732je.m2079d(this.f335a.getPackageName());
        c0732je.f2033a = new HashMap();
        Map<String, String> map2 = c0732je.f2033a;
        Context context = this.f335a;
        AbstractC0772p.m2412a(map2, Constants.EXTRA_KEY_APP_VERSION, C0646g.m1359a(context, context.getPackageName()));
        Map<String, String> map3 = c0732je.f2033a;
        Context context2 = this.f335a;
        AbstractC0772p.m2412a(map3, Constants.EXTRA_KEY_APP_VERSION_CODE, Integer.toString(C0646g.m1356a(context2, context2.getPackageName())));
        AbstractC0772p.m2412a(c0732je.f2033a, "push_sdk_vn", "3_6_12");
        AbstractC0772p.m2412a(c0732je.f2033a, "push_sdk_vc", Integer.toString(30612));
        AbstractC0772p.m2412a(c0732je.f2033a, "token", m289a.m303b());
        if (!C0770n.m2408d()) {
            String m521a = C0509ay.m521a(C0727j.m1993f(this.f335a));
            String m1995h = C0727j.m1995h(this.f335a);
            String str3 = m521a;
            if (!TextUtils.isEmpty(m1995h)) {
                str3 = m521a + Constants.ACCEPT_TIME_SEPARATOR_SP + m1995h;
            }
            if (!TextUtils.isEmpty(str3)) {
                AbstractC0772p.m2412a(c0732je.f2033a, Constants.EXTRA_KEY_IMEI_MD5, str3);
            }
        }
        AbstractC0772p.m2412a(c0732je.f2033a, Constants.EXTRA_KEY_REG_ID, m289a.m308c());
        AbstractC0772p.m2412a(c0732je.f2033a, Constants.EXTRA_KEY_REG_SECRET, m289a.m310d());
        AbstractC0772p.m2412a(c0732je.f2033a, Constants.EXTRA_KEY_ACCEPT_TIME, MiPushClient.getAcceptTime(this.f335a).replace(Constants.ACCEPT_TIME_SEPARATOR_SP, Constants.ACCEPT_TIME_SEPARATOR_SERVER));
        if (this.f336a) {
            Map<String, String> map4 = c0732je.f2033a;
            m283c = C0456bf.m283c(MiPushClient.getAllAlias(this.f335a));
            AbstractC0772p.m2412a(map4, Constants.EXTRA_KEY_ALIASES_MD5, m283c);
            Map<String, String> map5 = c0732je.f2033a;
            m283c2 = C0456bf.m283c(MiPushClient.getAllTopic(this.f335a));
            AbstractC0772p.m2412a(map5, Constants.EXTRA_KEY_TOPICS_MD5, m283c2);
            map = c0732je.f2033a;
            m283c3 = C0456bf.m283c(MiPushClient.getAllUserAccount(this.f335a));
            str = m283c3;
            str2 = Constants.EXTRA_KEY_ACCOUNTS_MD5;
        } else {
            Map<String, String> map6 = c0732je.f2033a;
            m284d = C0456bf.m284d(MiPushClient.getAllAlias(this.f335a));
            AbstractC0772p.m2412a(map6, Constants.EXTRA_KEY_ALIASES, m284d);
            Map<String, String> map7 = c0732je.f2033a;
            m284d2 = C0456bf.m284d(MiPushClient.getAllTopic(this.f335a));
            AbstractC0772p.m2412a(map7, Constants.EXTRA_KEY_TOPICS, m284d2);
            map = c0732je.f2033a;
            m284d3 = C0456bf.m284d(MiPushClient.getAllUserAccount(this.f335a));
            str = m284d3;
            str2 = Constants.EXTRA_KEY_ACCOUNTS;
        }
        AbstractC0772p.m2412a(map, str2, str);
        C0449az.m224a(this.f335a).m254a((C0449az) c0732je, EnumC0696hw.Notification, false, (C0717iq) null);
    }
}
