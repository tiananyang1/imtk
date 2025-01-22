package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0631fl;
import com.xiaomi.push.EnumC0636fq;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MessageHandleService.class */
public class MessageHandleService extends BaseService {

    /* renamed from: a */
    private static ConcurrentLinkedQueue<C0420a> f248a = new ConcurrentLinkedQueue<>();

    /* renamed from: a */
    private static ExecutorService f249a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: com.xiaomi.mipush.sdk.MessageHandleService$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MessageHandleService$a.class */
    public static class C0420a {

        /* renamed from: a */
        private Intent f250a;

        /* renamed from: a */
        private PushMessageReceiver f251a;

        public C0420a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.f251a = pushMessageReceiver;
            this.f250a = intent;
        }

        /* renamed from: a */
        public Intent m128a() {
            return this.f250a;
        }

        /* renamed from: a */
        public PushMessageReceiver m129a() {
            return this.f251a;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m125a(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        m126b(context);
    }

    public static void addJob(Context context, C0420a c0420a) {
        if (c0420a != null) {
            f248a.add(c0420a);
            m126b(context);
            startService(context);
        }
    }

    /* renamed from: b */
    private static void m126b(Context context) {
        if (f249a.isShutdown()) {
            return;
        }
        f249a.execute(new RunnableC0429af(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m127c(Context context) {
        C0631fl m1256a;
        String packageName;
        int i;
        String str;
        String[] stringArrayExtra;
        try {
            C0420a poll = f248a.poll();
            if (poll == null) {
                return;
            }
            PushMessageReceiver m129a = poll.m129a();
            Intent m128a = poll.m128a();
            int intExtra = m128a.getIntExtra(PushMessageHelper.MESSAGE_TYPE, 1);
            if (intExtra == 1) {
                PushMessageHandler.InterfaceC0422a m216a = C0446aw.m203a(context).m216a(m128a);
                int intExtra2 = m128a.getIntExtra("eventMessageType", -1);
                if (m216a == null) {
                    return;
                }
                if (m216a instanceof MiPushMessage) {
                    MiPushMessage miPushMessage = (MiPushMessage) m216a;
                    if (!miPushMessage.isArrivedMessage()) {
                        m129a.onReceiveMessage(context, miPushMessage);
                    }
                    if (miPushMessage.getPassThrough() == 1) {
                        C0631fl.m1256a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m1259a(context.getPackageName(), m128a, 2004, "call passThrough callBack");
                        m129a.onReceivePassThroughMessage(context, miPushMessage);
                        return;
                    }
                    if (!miPushMessage.isNotified()) {
                        m129a.onNotificationMessageArrived(context, miPushMessage);
                        return;
                    }
                    if (intExtra2 == 1000) {
                        m1256a = C0631fl.m1256a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                        packageName = context.getPackageName();
                        i = 1007;
                        str = "call notification callBack";
                    } else {
                        m1256a = C0631fl.m1256a(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                        packageName = context.getPackageName();
                        i = 3007;
                        str = "call business callBack";
                    }
                    m1256a.m1259a(packageName, m128a, i, str);
                    AbstractC0407b.m70a("begin execute onNotificationMessageClicked from\u3000" + miPushMessage.getMessageId());
                    m129a.onNotificationMessageClicked(context, miPushMessage);
                    return;
                }
                if (!(m216a instanceof MiPushCommandMessage)) {
                    return;
                }
                MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) m216a;
                m129a.onCommandResult(context, miPushCommandMessage);
                if (!TextUtils.equals(miPushCommandMessage.getCommand(), EnumC0636fq.COMMAND_REGISTER.f934a)) {
                    return;
                }
                m129a.onReceiveRegisterResult(context, miPushCommandMessage);
                if (miPushCommandMessage.getResultCode() != 0) {
                    return;
                }
            } else {
                if (intExtra != 3) {
                    if (intExtra == 4 || intExtra != 5 || !PushMessageHelper.ERROR_TYPE_NEED_PERMISSION.equals(m128a.getStringExtra(PushMessageHelper.ERROR_TYPE)) || (stringArrayExtra = m128a.getStringArrayExtra(PushMessageHelper.ERROR_MESSAGE)) == null) {
                        return;
                    }
                    m129a.onRequirePermissions(context, stringArrayExtra);
                    return;
                }
                MiPushCommandMessage miPushCommandMessage2 = (MiPushCommandMessage) m128a.getSerializableExtra(PushMessageHelper.KEY_COMMAND);
                m129a.onCommandResult(context, miPushCommandMessage2);
                if (!TextUtils.equals(miPushCommandMessage2.getCommand(), EnumC0636fq.COMMAND_REGISTER.f934a)) {
                    return;
                }
                m129a.onReceiveRegisterResult(context, miPushCommandMessage2);
                if (miPushCommandMessage2.getResultCode() != 0) {
                    return;
                }
            }
            C0466i.m353b(context);
        } catch (RuntimeException e) {
            AbstractC0407b.m72a(e);
        }
    }

    public static void startService(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) MessageHandleService.class));
        C0493ai.m439a(context).m443a(new RunnableC0428ae(context, intent));
    }

    @Override // com.xiaomi.mipush.sdk.BaseService
    /* renamed from: a */
    protected boolean mo117a() {
        ConcurrentLinkedQueue<C0420a> concurrentLinkedQueue = f248a;
        return concurrentLinkedQueue != null && concurrentLinkedQueue.size() > 0;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }
}
