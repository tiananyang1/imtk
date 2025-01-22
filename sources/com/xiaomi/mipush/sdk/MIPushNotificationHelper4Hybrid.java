package com.xiaomi.mipush.sdk;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0504at;
import com.xiaomi.push.C0770n;
import com.xiaomi.push.service.C0803ai;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MIPushNotificationHelper4Hybrid.class */
public class MIPushNotificationHelper4Hybrid {
    public static final String KEY_CATEGORY = "miui.category";
    public static final String KEY_MESSAGE_ID = "message_id";
    public static final String KEY_SCORE_INFO = "score_info";
    public static final String KEY_SUBST_NAME = "miui.substName";

    /* renamed from: a */
    private static final LinkedList<C0419a> f244a = new LinkedList<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.xiaomi.mipush.sdk.MIPushNotificationHelper4Hybrid$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MIPushNotificationHelper4Hybrid$a.class */
    public static class C0419a {

        /* renamed from: a */
        int f245a;

        /* renamed from: a */
        MiPushMessage f246a;

        /* renamed from: a */
        String f247a;

        public C0419a(int i, String str, MiPushMessage miPushMessage) {
            this.f245a = i;
            this.f247a = str;
            this.f246a = miPushMessage;
        }
    }

    /* renamed from: a */
    private static Notification m120a(Notification notification) {
        Object m494a = C0504at.m494a(notification, "extraNotification");
        if (m494a != null) {
            C0504at.m495a(m494a, "setCustomizedIcon", true);
        }
        return notification;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private static Notification m121a(Context context, MiPushMessage miPushMessage, PendingIntent pendingIntent, Bitmap bitmap) {
        boolean z;
        Map<String, String> extra = miPushMessage.getExtra();
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(miPushMessage.getTitle());
        builder.setContentText(miPushMessage.getDescription());
        if (Build.VERSION.SDK_INT >= 16) {
            builder.setStyle(new Notification.BigTextStyle().bigText(miPushMessage.getDescription()));
        }
        builder.setWhen(System.currentTimeMillis());
        String str = extra == null ? null : extra.get("notification_show_when");
        if (!TextUtils.isEmpty(str)) {
            builder.setShowWhen(Boolean.parseBoolean(str));
        } else if (Build.VERSION.SDK_INT >= 24) {
            builder.setShowWhen(true);
        }
        builder.setContentIntent(pendingIntent);
        int i = context.getApplicationInfo().icon;
        int i2 = i;
        if (i == 0) {
            i2 = i;
            if (Build.VERSION.SDK_INT >= 9) {
                i2 = context.getApplicationInfo().logo;
            }
        }
        builder.setSmallIcon(i2);
        if (bitmap != null) {
            builder.setLargeIcon(bitmap);
            z = true;
        } else {
            z = false;
        }
        if (extra != null && Build.VERSION.SDK_INT >= 24) {
            String str2 = extra.get("notification_group");
            boolean parseBoolean = Boolean.parseBoolean(extra.get("notification_is_summary"));
            C0504at.m495a(builder, "setGroup", str2);
            C0504at.m495a(builder, "setGroupSummary", Boolean.valueOf(parseBoolean));
        }
        builder.setAutoCancel(true);
        long currentTimeMillis = System.currentTimeMillis();
        if (extra != null && extra.containsKey("ticker")) {
            builder.setTicker(extra.get("ticker"));
        }
        if (currentTimeMillis - C0803ai.f2456a > 10000) {
            C0803ai.f2456a = currentTimeMillis;
            builder.setDefaults(miPushMessage.getNotifyType());
        }
        Notification notification = builder.getNotification();
        if (z && C0770n.m2403a()) {
            m120a(notification);
        }
        return notification;
    }

    /* renamed from: a */
    private static PendingIntent m122a(Context context, String str, MiPushMessage miPushMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setComponent(new ComponentName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushMessageHandler"));
        intent.setAction("com.xiaomi.mipush.sdk.HYBRID_NOTIFICATION_CLICK");
        intent.putExtra("mipush_payload", miPushMessage);
        intent.putExtra("mipush_hybrid_app_pkg", str);
        intent.putExtra("mipush_notified", true);
        intent.addCategory(String.valueOf(miPushMessage.getNotifyId()));
        return PendingIntent.getService(context, 0, intent, 134217728);
    }

    /* renamed from: a */
    private static void m123a(Notification notification, String str) {
        try {
            Object m494a = C0504at.m494a(notification, "extraNotification");
            if (m494a != null) {
                C0504at.m503b(m494a, "setMessageClassName", str);
            } else {
                AbstractC0407b.m75d("Get null extraNotification, setShortcutId failed.");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void clearNotification(Context context, String str) {
        clearNotification(context, str, -1);
    }

    public static void clearNotification(Context context, String str, int i) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int hashCode = ((str.hashCode() / 10) * 10) + i;
        LinkedList linkedList = new LinkedList();
        if (i >= 0) {
            notificationManager.cancel(hashCode);
        }
        synchronized (f244a) {
            Iterator<C0419a> it = f244a.iterator();
            while (it.hasNext()) {
                C0419a next = it.next();
                if (i >= 0) {
                    if (hashCode == next.f245a && TextUtils.equals(str, next.f247a)) {
                        linkedList.add(next);
                    }
                } else if (i == -1 && TextUtils.equals(str, next.f247a)) {
                    notificationManager.cancel(next.f245a);
                    linkedList.add(next);
                }
            }
            if (f244a != null) {
                f244a.removeAll(linkedList);
                C0803ai.m2526a(context, (LinkedList<? extends Object>) linkedList);
            }
        }
    }

    public static void notifyPushMessage(Context context, MiPushMessage miPushMessage, Bitmap bitmap, String str, String str2, String str3) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        PendingIntent m122a = m122a(context, str, miPushMessage);
        if (m122a == null) {
            AbstractC0407b.m70a("The click PendingIntent is null. ");
            return;
        }
        Notification m121a = m121a(context, miPushMessage, m122a, bitmap);
        if (C0770n.m2403a()) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (!TextUtils.isEmpty(miPushMessage.getMessageId())) {
                    m121a.extras.putString(KEY_MESSAGE_ID, miPushMessage.getMessageId());
                }
                String str4 = miPushMessage.getExtra() == null ? null : miPushMessage.getExtra().get(KEY_SCORE_INFO);
                if (!TextUtils.isEmpty(str4)) {
                    m121a.extras.putString(KEY_SCORE_INFO, str4);
                }
                if (!TextUtils.isEmpty(str)) {
                    m121a.extras.putString(KEY_CATEGORY, str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    m121a.extras.putString(KEY_SUBST_NAME, str2);
                }
            }
            if (!TextUtils.isEmpty(str3)) {
                m123a(m121a, str3);
            }
        }
        int notifyId = miPushMessage.getNotifyId();
        int i = notifyId;
        if (str != null) {
            i = notifyId + ((str.hashCode() / 10) * 10);
        }
        notificationManager.notify(i, m121a);
        C0419a c0419a = new C0419a(i, str, miPushMessage);
        synchronized (f244a) {
            f244a.add(c0419a);
            if (f244a.size() > 100) {
                f244a.remove();
            }
        }
    }
}
