package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/HWPushHelper.class */
public class HWPushHelper {

    /* renamed from: a */
    private static boolean f243a;

    public static void convertMessage(Intent intent) {
        C0466i.m347a(intent);
    }

    public static boolean hasNetwork(Context context) {
        return C0466i.m350a(context);
    }

    public static boolean isHmsTokenSynced(Context context) {
        String m342a = C0466i.m342a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI);
        if (TextUtils.isEmpty(m342a)) {
            return false;
        }
        String m341a = C0466i.m341a(context, m342a);
        String m188a = C0439ap.m186a(context).m188a(EnumC0455be.UPLOAD_HUAWEI_TOKEN);
        return (TextUtils.isEmpty(m341a) || TextUtils.isEmpty(m188a) || !"synced".equals(m188a)) ? false : true;
    }

    public static boolean isUserOpenHmsPush() {
        return MiPushClient.getOpenHmsPush();
    }

    public static boolean needConnect() {
        return f243a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:            r8 = r0.getString("pushMsg");     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void notifyHmsNotificationMessageClicked(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = r5
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r7 = r0
            java.lang.String r0 = ""
            r9 = r0
            r0 = r9
            r8 = r0
            r0 = r7
            if (r0 != 0) goto L63
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch: java.lang.Exception -> L57
            r1 = r0
            r2 = r5
            r1.<init>(r2)     // Catch: java.lang.Exception -> L57
            r5 = r0
            r0 = r9
            r8 = r0
            r0 = r5
            int r0 = r0.length()     // Catch: java.lang.Exception -> L57
            if (r0 <= 0) goto L63
            r0 = 0
            r6 = r0
        L27:
            r0 = r9
            r8 = r0
            r0 = r6
            r1 = r5
            int r1 = r1.length()     // Catch: java.lang.Exception -> L57
            if (r0 >= r1) goto L63
            r0 = r5
            r1 = r6
            org.json.JSONObject r0 = r0.getJSONObject(r1)     // Catch: java.lang.Exception -> L57
            r8 = r0
            r0 = r8
            java.lang.String r1 = "pushMsg"
            boolean r0 = r0.has(r1)     // Catch: java.lang.Exception -> L57
            if (r0 == 0) goto L50
            r0 = r8
            java.lang.String r1 = "pushMsg"
            java.lang.String r0 = r0.getString(r1)     // Catch: java.lang.Exception -> L57
            r8 = r0
            goto L63
        L50:
            r0 = r6
            r1 = 1
            int r0 = r0 + r1
            r6 = r0
            goto L27
        L57:
            r5 = move-exception
            r0 = r5
            java.lang.String r0 = r0.toString()
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m75d(r0)
            r0 = r9
            r8 = r0
        L63:
            r0 = r4
            com.xiaomi.mipush.sdk.PushMessageReceiver r0 = com.xiaomi.mipush.sdk.C0466i.m340a(r0)
            r5 = r0
            r0 = r5
            if (r0 == 0) goto L76
            r0 = r5
            r1 = r4
            r2 = r8
            com.xiaomi.mipush.sdk.MiPushMessage r2 = com.xiaomi.mipush.sdk.C0466i.m339a(r2)
            r0.onNotificationMessageClicked(r1, r2)
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.HWPushHelper.notifyHmsNotificationMessageClicked(android.content.Context, java.lang.String):void");
    }

    public static void notifyHmsPassThoughMessageArrived(Context context, String str) {
        String str2 = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                str2 = "";
                if (jSONObject.has("content")) {
                    str2 = jSONObject.getString("content");
                }
            }
        } catch (Exception e) {
            AbstractC0407b.m75d(e.toString());
            str2 = "";
        }
        PushMessageReceiver m340a = C0466i.m340a(context);
        if (m340a != null) {
            m340a.onReceivePassThroughMessage(context, C0466i.m339a(str2));
        }
    }

    public static void registerHuaWeiAssemblePush(Context context) {
        AbstractPushManager m333a = C0464g.m331a(context).m333a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI);
        if (m333a != null) {
            m333a.register();
        }
    }

    public static void reportError(String str, int i) {
        C0466i.m349a(str, i);
    }

    public static void setConnectTime(Context context) {
        synchronized (HWPushHelper.class) {
            try {
                context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_connect_time", System.currentTimeMillis()).commit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void setGetTokenTime(Context context) {
        synchronized (HWPushHelper.class) {
            try {
                context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_get_token_time", System.currentTimeMillis()).commit();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void setNeedConnect(boolean z) {
        f243a = z;
    }

    public static boolean shouldGetToken(Context context) {
        boolean z;
        synchronized (HWPushHelper.class) {
            z = false;
            try {
                if (Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_get_token_time", -1L)) > 172800000) {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public static boolean shouldTryConnect(Context context) {
        boolean z;
        synchronized (HWPushHelper.class) {
            z = false;
            try {
                if (Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_connect_time", -1L)) > 5000) {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public static void uploadToken(Context context, String str) {
        C0466i.m346a(context, EnumC0463f.ASSEMBLE_PUSH_HUAWEI, str);
    }
}
