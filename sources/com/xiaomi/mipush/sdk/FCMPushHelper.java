package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/FCMPushHelper.class */
public class FCMPushHelper {
    public static void clearToken(Context context) {
        C0466i.m345a(context, EnumC0463f.ASSEMBLE_PUSH_FCM);
    }

    public static void convertMessage(Intent intent) {
        C0466i.m347a(intent);
    }

    public static boolean isFCMSwitchOpen(Context context) {
        return C0466i.m351a(context, EnumC0463f.ASSEMBLE_PUSH_FCM) && MiPushClient.getOpenFCMPush();
    }

    public static void notifyFCMNotificationCome(Context context, Map<String, String> map) {
        PushMessageReceiver m340a;
        String str = map.get("pushMsg");
        if (TextUtils.isEmpty(str) || (m340a = C0466i.m340a(context)) == null) {
            return;
        }
        m340a.onNotificationMessageArrived(context, C0466i.m339a(str));
    }

    public static void notifyFCMPassThoughMessageCome(Context context, Map<String, String> map) {
        PushMessageReceiver m340a;
        String str = map.get("pushMsg");
        if (TextUtils.isEmpty(str) || (m340a = C0466i.m340a(context)) == null) {
            return;
        }
        m340a.onReceivePassThroughMessage(context, C0466i.m339a(str));
    }

    public static void reportFCMMessageDelete() {
        MiTinyDataClient.upload(C0466i.m352b(EnumC0463f.ASSEMBLE_PUSH_FCM), "fcm", 1L, "some fcm messages was deleted ");
    }

    public static void uploadToken(Context context, String str) {
        C0466i.m346a(context, EnumC0463f.ASSEMBLE_PUSH_FCM, str);
    }
}
