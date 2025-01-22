package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.C0439ap;
import com.xiaomi.mipush.sdk.C0449az;
import com.xiaomi.mipush.sdk.C0461d;
import com.xiaomi.mipush.sdk.COSPushHelper;
import com.xiaomi.mipush.sdk.EnumC0455be;
import com.xiaomi.mipush.sdk.HWPushHelper;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.service.C0827bf;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/receivers/NetworkStatusReceiver.class */
public class NetworkStatusReceiver extends BroadcastReceiver {

    /* renamed from: b */
    private boolean f2704b;

    /* renamed from: a */
    private static BlockingQueue<Runnable> f2699a = new LinkedBlockingQueue();

    /* renamed from: a */
    private static int f2698a = 1;

    /* renamed from: b */
    private static int f2702b = 1;

    /* renamed from: c */
    private static int f2703c = 2;

    /* renamed from: a */
    private static ThreadPoolExecutor f2700a = new ThreadPoolExecutor(f2698a, f2702b, f2703c, TimeUnit.SECONDS, f2699a);

    /* renamed from: a */
    private static boolean f2701a = false;

    public NetworkStatusReceiver() {
        this.f2704b = false;
        this.f2704b = true;
    }

    public NetworkStatusReceiver(Object obj) {
        this.f2704b = false;
        f2701a = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2787a(Context context) {
        if (!C0449az.m224a(context).m262a() && C0461d.m289a(context).m309c() && !C0461d.m289a(context).m313e()) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(context, "com.xiaomi.push.service.XMPushService"));
                intent.setAction("com.xiaomi.push.network_status_changed");
                C0827bf.m2633a(context).m2638a(intent);
            } catch (Exception e) {
                AbstractC0407b.m72a(e);
            }
        }
        if (C0503as.m484b(context) && C0449az.m224a(context).m266b()) {
            C0449az.m224a(context).m267c();
        }
        if (C0503as.m484b(context)) {
            if ("syncing".equals(C0439ap.m186a(context).m188a(EnumC0455be.DISABLE_PUSH))) {
                MiPushClient.disablePush(context);
            }
            if ("syncing".equals(C0439ap.m186a(context).m188a(EnumC0455be.ENABLE_PUSH))) {
                MiPushClient.enablePush(context);
            }
            if ("syncing".equals(C0439ap.m186a(context).m188a(EnumC0455be.UPLOAD_HUAWEI_TOKEN))) {
                MiPushClient.syncAssemblePushToken(context);
            }
            if ("syncing".equals(C0439ap.m186a(context).m188a(EnumC0455be.UPLOAD_FCM_TOKEN))) {
                MiPushClient.syncAssembleFCMPushToken(context);
            }
            if ("syncing".equals(C0439ap.m186a(context).m188a(EnumC0455be.UPLOAD_COS_TOKEN))) {
                MiPushClient.syncAssembleCOSPushToken(context);
            }
            if (HWPushHelper.needConnect() && HWPushHelper.shouldTryConnect(context)) {
                HWPushHelper.setConnectTime(context);
                HWPushHelper.registerHuaWeiAssemblePush(context);
            }
            COSPushHelper.doInNetworkChange(context);
        }
    }

    /* renamed from: a */
    public static boolean m2789a() {
        return f2701a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.f2704b) {
            return;
        }
        f2700a.execute(new RunnableC0869a(this, context));
    }
}
