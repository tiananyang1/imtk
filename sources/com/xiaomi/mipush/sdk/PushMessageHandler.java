package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.push.C0631fl;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.EnumC0636fq;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/PushMessageHandler.class */
public class PushMessageHandler extends BaseService {

    /* renamed from: a */
    private static List<MiPushClient.MiPushClientCallback> f267a = new ArrayList();

    /* renamed from: a */
    private static ThreadPoolExecutor f268a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.mipush.sdk.PushMessageHandler$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/PushMessageHandler$a.class */
    public interface InterfaceC0422a extends Serializable {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m154a() {
        synchronized (f267a) {
            f267a.clear();
        }
    }

    /* renamed from: a */
    public static void m155a(long j, String str, String str2) {
        synchronized (f267a) {
            Iterator<MiPushClient.MiPushClientCallback> it = f267a.iterator();
            while (it.hasNext()) {
                it.next().onInitializeResult(j, str, str2);
            }
        }
    }

    /* renamed from: a */
    public static void m156a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) PushMessageHandler.class));
        try {
            context.startService(intent);
        } catch (Exception e) {
            AbstractC0407b.m70a(e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m157a(Context context, Intent intent) {
        AbstractC0407b.m74c("addjob PushMessageHandler " + intent);
        if (intent != null) {
            m168c(context, intent);
            m156a(context);
        }
    }

    /* renamed from: a */
    private static void m158a(Context context, Intent intent, ResolveInfo resolveInfo) {
        try {
            MessageHandleService.addJob(StubApp.getOrigApplicationContext(context.getApplicationContext()), new MessageHandleService.C0420a(intent, (PushMessageReceiver) Class.forName(resolveInfo.activityInfo.name).newInstance()));
            MessageHandleService.m125a(context, new Intent(StubApp.getOrigApplicationContext(context.getApplicationContext()), (Class<?>) MessageHandleService.class));
        } catch (Throwable th) {
            AbstractC0407b.m72a(th);
        }
    }

    /* renamed from: a */
    public static void m159a(Context context, MiPushMessage miPushMessage) {
        synchronized (f267a) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f267a) {
                if (m164a(miPushMessage.getCategory(), miPushClientCallback.getCategory())) {
                    miPushClientCallback.onReceiveMessage(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    miPushClientCallback.onReceiveMessage(miPushMessage);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m160a(Context context, InterfaceC0422a interfaceC0422a) {
        if (interfaceC0422a instanceof MiPushMessage) {
            m159a(context, (MiPushMessage) interfaceC0422a);
            return;
        }
        if (interfaceC0422a instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) interfaceC0422a;
            String command = miPushCommandMessage.getCommand();
            if (EnumC0636fq.COMMAND_REGISTER.f934a.equals(command)) {
                List<String> commandArguments = miPushCommandMessage.getCommandArguments();
                String str = null;
                if (commandArguments != null) {
                    str = null;
                    if (!commandArguments.isEmpty()) {
                        str = commandArguments.get(0);
                    }
                }
                m155a(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
                return;
            }
            if (EnumC0636fq.COMMAND_SET_ALIAS.f934a.equals(command) || EnumC0636fq.COMMAND_UNSET_ALIAS.f934a.equals(command) || EnumC0636fq.COMMAND_SET_ACCEPT_TIME.f934a.equals(command)) {
                m162a(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
                return;
            }
            if (EnumC0636fq.COMMAND_SUBSCRIBE_TOPIC.f934a.equals(command)) {
                List<String> commandArguments2 = miPushCommandMessage.getCommandArguments();
                String str2 = null;
                if (commandArguments2 != null) {
                    str2 = null;
                    if (!commandArguments2.isEmpty()) {
                        str2 = commandArguments2.get(0);
                    }
                }
                m161a(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str2);
                return;
            }
            if (EnumC0636fq.COMMAND_UNSUBSCRIBE_TOPIC.f934a.equals(command)) {
                List<String> commandArguments3 = miPushCommandMessage.getCommandArguments();
                String str3 = null;
                if (commandArguments3 != null) {
                    str3 = null;
                    if (!commandArguments3.isEmpty()) {
                        str3 = commandArguments3.get(0);
                    }
                }
                m166b(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m161a(Context context, String str, long j, String str2, String str3) {
        synchronized (f267a) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f267a) {
                if (m164a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onSubscribeResult(j, str2, str3);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m162a(Context context, String str, String str2, long j, String str3, List<String> list) {
        synchronized (f267a) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f267a) {
                if (m164a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onCommandResult(str2, j, str3, list);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m163a(MiPushClient.MiPushClientCallback miPushClientCallback) {
        synchronized (f267a) {
            if (!f267a.contains(miPushClientCallback)) {
                f267a.add(miPushClientCallback);
            }
        }
    }

    /* renamed from: a */
    protected static boolean m164a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static void m165b(Context context, Intent intent) {
        try {
            if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                C0472o.m366a(context, intent, null);
                return;
            }
            if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                C0702ib c0702ib = new C0702ib();
                C0743jp.m2313a(c0702ib, intent.getByteArrayExtra("mipush_payload"));
                AbstractC0407b.m74c("PushMessageHandler.onHandleIntent " + c0702ib.m1656d());
                MiTinyDataClient.upload(context, c0702ib);
                return;
            }
            if ("com.xiaomi.mipush.sdk.HYBRID_NOTIFICATION_CLICK".equals(intent.getAction())) {
                MiPushMessage miPushMessage = (MiPushMessage) intent.getSerializableExtra("mipush_payload");
                String stringExtra = intent.getStringExtra("mipush_hybrid_app_pkg");
                MiPushClient.reportMessageClicked(context, miPushMessage);
                MiPushClient4Hybrid.onNotificationMessageClicked(context, stringExtra, miPushMessage);
                return;
            }
            if (1 == PushMessageHelper.getPushMode(context)) {
                if (m167b()) {
                    AbstractC0407b.m75d("receive a message before application calling initialize");
                    return;
                }
                InterfaceC0422a m216a = C0446aw.m203a(context).m216a(intent);
                if (m216a != null) {
                    m160a(context, m216a);
                    return;
                }
                return;
            }
            if ("com.xiaomi.mipush.sdk.SYNC_LOG".equals(intent.getAction())) {
                Logger.uploadLogFile(context, false);
                return;
            }
            Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent2.setPackage(context.getPackageName());
            intent2.putExtras(intent);
            try {
                List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 32);
                ResolveInfo resolveInfo = null;
                if (queryBroadcastReceivers != null) {
                    Iterator<ResolveInfo> it = queryBroadcastReceivers.iterator();
                    while (true) {
                        resolveInfo = null;
                        if (!it.hasNext()) {
                            break;
                        }
                        resolveInfo = it.next();
                        if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(context.getPackageName()) && PushMessageReceiver.class.isAssignableFrom(Class.forName(resolveInfo.activityInfo.name))) {
                            break;
                        }
                    }
                }
                if (resolveInfo != null) {
                    m158a(context, intent2, resolveInfo);
                } else {
                    AbstractC0407b.m75d("cannot find the receiver to handler this message, check your manifest");
                    C0631fl.m1256a(context).m1260a(context.getPackageName(), intent, "cannot find the receiver to handler this message, check your manifest");
                }
            } catch (Exception e) {
                AbstractC0407b.m72a(e);
                C0631fl.m1256a(context).m1261a(context.getPackageName(), intent, e);
            }
        } catch (Throwable th) {
            AbstractC0407b.m72a(th);
            C0631fl.m1256a(context).m1261a(context.getPackageName(), intent, th);
        }
    }

    /* renamed from: b */
    protected static void m166b(Context context, String str, long j, String str2, String str3) {
        synchronized (f267a) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f267a) {
                if (m164a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onUnsubscribeResult(j, str2, str3);
                }
            }
        }
    }

    /* renamed from: b */
    public static boolean m167b() {
        return f267a.isEmpty();
    }

    /* renamed from: c */
    private static void m168c(Context context, Intent intent) {
        if (intent == null || f268a.isShutdown()) {
            return;
        }
        f268a.execute(new RunnableC0445av(context, intent));
    }

    @Override // com.xiaomi.mipush.sdk.BaseService
    /* renamed from: a */
    protected boolean mo117a() {
        ThreadPoolExecutor threadPoolExecutor = f268a;
        return (threadPoolExecutor == null || threadPoolExecutor.getQueue() == null || f268a.getQueue().size() <= 0) ? false : true;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        m168c(StubApp.getOrigApplicationContext(getApplicationContext()), intent);
    }
}
