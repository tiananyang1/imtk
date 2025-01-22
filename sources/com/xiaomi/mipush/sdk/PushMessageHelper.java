package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0736ji;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/PushMessageHelper.class */
public class PushMessageHelper {
    public static final String ERROR_MESSAGE = "error_message";
    public static final String ERROR_TYPE = "error_type";
    public static final String ERROR_TYPE_NEED_PERMISSION = "error_lack_of_permission";
    public static final String KEY_COMMAND = "key_command";
    public static final String KEY_MESSAGE = "key_message";
    public static final int MESSAGE_COMMAND = 3;
    public static final int MESSAGE_ERROR = 5;
    public static final int MESSAGE_QUIT = 4;
    public static final int MESSAGE_RAW = 1;
    public static final int MESSAGE_SENDMESSAGE = 2;
    public static final String MESSAGE_TYPE = "message_type";
    public static final int PUSH_MODE_BROADCAST = 2;
    public static final int PUSH_MODE_CALLBACK = 1;
    private static int pushMode;

    public static MiPushCommandMessage generateCommandMessage(String str, List<String> list, long j, String str2, String str3) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(str);
        miPushCommandMessage.setCommandArguments(list);
        miPushCommandMessage.setResultCode(j);
        miPushCommandMessage.setReason(str2);
        miPushCommandMessage.setCategory(str3);
        return miPushCommandMessage;
    }

    public static MiPushMessage generateMessage(C0736ji c0736ji, C0717iq c0717iq, boolean z) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(c0736ji.m2190a());
        if (!TextUtils.isEmpty(c0736ji.m2199d())) {
            miPushMessage.setMessageType(1);
            miPushMessage.setAlias(c0736ji.m2199d());
        } else if (!TextUtils.isEmpty(c0736ji.m2197c())) {
            miPushMessage.setMessageType(2);
            miPushMessage.setTopic(c0736ji.m2197c());
        } else if (TextUtils.isEmpty(c0736ji.m2203f())) {
            miPushMessage.setMessageType(0);
        } else {
            miPushMessage.setMessageType(3);
            miPushMessage.setUserAccount(c0736ji.m2203f());
        }
        miPushMessage.setCategory(c0736ji.m2201e());
        if (c0736ji.m2189a() != null) {
            miPushMessage.setContent(c0736ji.m2189a().m1799c());
        }
        if (c0717iq != null) {
            if (TextUtils.isEmpty(miPushMessage.getMessageId())) {
                miPushMessage.setMessageId(c0717iq.m1825a());
            }
            if (TextUtils.isEmpty(miPushMessage.getTopic())) {
                miPushMessage.setTopic(c0717iq.m1835b());
            }
            miPushMessage.setDescription(c0717iq.m1847d());
            miPushMessage.setTitle(c0717iq.m1843c());
            miPushMessage.setNotifyType(c0717iq.m1818a());
            miPushMessage.setNotifyId(c0717iq.m1840c());
            miPushMessage.setPassThrough(c0717iq.m1832b());
            miPushMessage.setExtra(c0717iq.m1826a());
        }
        miPushMessage.setNotified(z);
        return miPushMessage;
    }

    public static C0717iq generateMessage(MiPushMessage miPushMessage) {
        C0717iq c0717iq = new C0717iq();
        c0717iq.m1823a(miPushMessage.getMessageId());
        c0717iq.m1834b(miPushMessage.getTopic());
        c0717iq.m1846d(miPushMessage.getDescription());
        c0717iq.m1842c(miPushMessage.getTitle());
        c0717iq.m1841c(miPushMessage.getNotifyId());
        c0717iq.m1822a(miPushMessage.getNotifyType());
        c0717iq.m1833b(miPushMessage.getPassThrough());
        c0717iq.m1824a(miPushMessage.getExtra());
        return c0717iq;
    }

    public static int getPushMode(Context context) {
        if (pushMode == 0) {
            setPushMode(isUseCallbackPushMode(context) ? 1 : 2);
        }
        return pushMode;
    }

    private static boolean isIntentAvailable(Context context, Intent intent) {
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

    public static boolean isUseCallbackPushMode(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return isIntentAvailable(context, intent);
    }

    public static void sendCommandMessageBroadcast(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, 3);
        intent.putExtra(KEY_COMMAND, miPushCommandMessage);
        new PushServiceReceiver().onReceive(context, intent);
    }

    public static void sendQuitMessageBroadcast(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, 4);
        new PushServiceReceiver().onReceive(context, intent);
    }

    private static void setPushMode(int i) {
        pushMode = i;
    }
}
