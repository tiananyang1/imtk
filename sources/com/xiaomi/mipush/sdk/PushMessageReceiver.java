package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.push.C0631fl;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/PushMessageReceiver.class */
public abstract class PushMessageReceiver extends BroadcastReceiver {
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
    }

    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
    }

    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        MessageHandleService.addJob(StubApp.getOrigApplicationContext(context.getApplicationContext()), new MessageHandleService.C0420a(intent, this));
        try {
            if (intent.getIntExtra("eventMessageType", -1) == 2000) {
                C0631fl.m1256a(StubApp.getOrigApplicationContext(context.getApplicationContext())).m1259a(context.getPackageName(), intent, 2003, "receive passThough message broadcast");
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }

    @Deprecated
    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
    }

    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
    }

    public void onRequirePermissions(Context context, String[] strArr) {
    }
}
