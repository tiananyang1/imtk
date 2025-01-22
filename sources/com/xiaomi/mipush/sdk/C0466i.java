package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.C0878t;
import com.xiaomi.push.service.C0809ao;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.xiaomi.mipush.sdk.i */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/i.class */
public class C0466i {

    /* renamed from: a */
    private static HashMap<String, String> f366a = new HashMap<>();

    /* renamed from: a */
    public static MiPushMessage m339a(String str) {
        MiPushMessage miPushMessage = new MiPushMessage();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("messageId")) {
                    miPushMessage.setMessageId(jSONObject.getString("messageId"));
                }
                if (jSONObject.has("description")) {
                    miPushMessage.setDescription(jSONObject.getString("description"));
                }
                if (jSONObject.has(SettingsJsonConstants.PROMPT_TITLE_KEY)) {
                    miPushMessage.setTitle(jSONObject.getString(SettingsJsonConstants.PROMPT_TITLE_KEY));
                }
                if (jSONObject.has("content")) {
                    miPushMessage.setContent(jSONObject.getString("content"));
                }
                if (jSONObject.has("passThrough")) {
                    miPushMessage.setPassThrough(jSONObject.getInt("passThrough"));
                }
                if (jSONObject.has("notifyType")) {
                    miPushMessage.setNotifyType(jSONObject.getInt("notifyType"));
                }
                if (jSONObject.has("messageType")) {
                    miPushMessage.setMessageType(jSONObject.getInt("messageType"));
                }
                if (jSONObject.has("alias")) {
                    miPushMessage.setAlias(jSONObject.getString("alias"));
                }
                if (jSONObject.has("topic")) {
                    miPushMessage.setTopic(jSONObject.getString("topic"));
                }
                if (jSONObject.has("user_account")) {
                    miPushMessage.setUserAccount(jSONObject.getString("user_account"));
                }
                if (jSONObject.has("notifyId")) {
                    miPushMessage.setNotifyId(jSONObject.getInt("notifyId"));
                }
                if (jSONObject.has("category")) {
                    miPushMessage.setCategory(jSONObject.getString("category"));
                }
                if (jSONObject.has("isNotified")) {
                    miPushMessage.setNotified(jSONObject.getBoolean("isNotified"));
                }
                if (jSONObject.has("extra")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("extra");
                    Iterator<String> keys = jSONObject2.keys();
                    HashMap hashMap = new HashMap();
                    while (keys != null && keys.hasNext()) {
                        String next = keys.next();
                        hashMap.put(next, jSONObject2.getString(next));
                    }
                    if (hashMap.size() > 0) {
                        miPushMessage.setExtra(hashMap);
                        return miPushMessage;
                    }
                }
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
            }
        }
        return miPushMessage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static PushMessageReceiver m340a(Context context) {
        ResolveInfo resolveInfo;
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (queryBroadcastReceivers != null) {
                for (ResolveInfo resolveInfo2 : queryBroadcastReceivers) {
                    if (resolveInfo2.activityInfo != null && resolveInfo2.activityInfo.packageName.equals(context.getPackageName())) {
                        resolveInfo = resolveInfo2;
                        break;
                    }
                }
            }
            resolveInfo = null;
            if (resolveInfo != null) {
                return (PushMessageReceiver) Class.forName(resolveInfo.activityInfo.name).newInstance();
            }
            return null;
        } catch (Exception e) {
            AbstractC0407b.m75d(e.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static String m341a(Context context, String str) {
        String str2;
        synchronized (C0466i.class) {
            try {
                String str3 = f366a.get(str);
                str2 = str3;
                if (TextUtils.isEmpty(str3)) {
                    str2 = "";
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static String m342a(EnumC0463f enumC0463f) {
        int i = C0468k.f370a[enumC0463f.ordinal()];
        if (i == 1) {
            return "hms_push_token";
        }
        if (i == 2) {
            return "fcm_push_token";
        }
        if (i != 3) {
            return null;
        }
        return "cos_push_token";
    }

    /* renamed from: a */
    public static HashMap<String, String> m343a(Context context, EnumC0463f enumC0463f) {
        String str;
        StringBuilder sb;
        EnumC0440aq enumC0440aq;
        HashMap<String, String> hashMap = new HashMap<>();
        String m342a = m342a(enumC0463f);
        if (TextUtils.isEmpty(m342a)) {
            return hashMap;
        }
        int i = C0468k.f370a[enumC0463f.ordinal()];
        ApplicationInfo applicationInfo = null;
        if (i != 1) {
            if (i == 2) {
                sb = new StringBuilder();
                sb.append("brand:");
                enumC0440aq = EnumC0440aq.FCM;
            } else if (i != 3) {
                str = null;
            } else {
                sb = new StringBuilder();
                sb.append("brand:");
                enumC0440aq = EnumC0440aq.OPPO;
            }
            sb.append(enumC0440aq.name());
            sb.append(Constants.WAVE_SEPARATOR);
            sb.append("token");
            sb.append(Constants.COLON_SEPARATOR);
            sb.append(m341a(context, m342a));
            sb.append(Constants.WAVE_SEPARATOR);
            sb.append(Constants.PACKAGE_NAME);
            sb.append(Constants.COLON_SEPARATOR);
            sb.append(context.getPackageName());
            str = sb.toString();
        } else {
            try {
                applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
            }
            int i2 = -1;
            if (applicationInfo != null) {
                i2 = applicationInfo.metaData.getInt(Constants.HUAWEI_HMS_CLIENT_APPID);
            }
            str = "brand:" + C0471n.m362a(context).name() + Constants.WAVE_SEPARATOR + "token" + Constants.COLON_SEPARATOR + m341a(context, m342a) + Constants.WAVE_SEPARATOR + Constants.PACKAGE_NAME + Constants.COLON_SEPARATOR + context.getPackageName() + Constants.WAVE_SEPARATOR + Constants.APP_ID + Constants.COLON_SEPARATOR + i2;
        }
        hashMap.put(Constants.ASSEMBLE_PUSH_REG_INFO, str);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m344a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String m342a = m342a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI);
        String m342a2 = m342a(EnumC0463f.ASSEMBLE_PUSH_FCM);
        boolean z = false;
        if (!TextUtils.isEmpty(sharedPreferences.getString(m342a, ""))) {
            z = false;
            if (TextUtils.isEmpty(sharedPreferences.getString(m342a2, ""))) {
                z = true;
            }
        }
        if (z) {
            C0449az.m224a(context).m247a(2, m342a);
        }
    }

    /* renamed from: a */
    public static void m345a(Context context, EnumC0463f enumC0463f) {
        String m342a = m342a(enumC0463f);
        if (TextUtils.isEmpty(m342a)) {
            return;
        }
        C0878t.m2846a(context.getSharedPreferences("mipush_extra", 0).edit().putString(m342a, ""));
    }

    /* renamed from: a */
    public static void m346a(Context context, EnumC0463f enumC0463f, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String m342a = m342a(enumC0463f);
        if (TextUtils.isEmpty(m342a)) {
            AbstractC0407b.m70a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
            return;
        }
        String string = sharedPreferences.getString(m342a, "");
        if (!TextUtils.isEmpty(string) && str.equals(string)) {
            AbstractC0407b.m70a("ASSEMBLE_PUSH : do not need to send token");
            return;
        }
        AbstractC0407b.m70a("ASSEMBLE_PUSH : send token upload");
        m348a(enumC0463f, str);
        EnumC0455be m358a = C0469l.m358a(enumC0463f);
        if (m358a == null) {
            return;
        }
        C0449az.m224a(context).m258a((String) null, m358a, enumC0463f);
    }

    /* renamed from: a */
    public static void m347a(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null || !extras.containsKey("pushMsg")) {
            return;
        }
        intent.putExtra(PushMessageHelper.KEY_MESSAGE, m339a(extras.getString("pushMsg")));
    }

    /* renamed from: a */
    private static void m348a(EnumC0463f enumC0463f, String str) {
        synchronized (C0466i.class) {
            try {
                String m342a = m342a(enumC0463f);
                if (TextUtils.isEmpty(m342a)) {
                    AbstractC0407b.m70a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
                } else if (TextUtils.isEmpty(str)) {
                    AbstractC0407b.m70a("ASSEMBLE_PUSH : token is null");
                } else {
                    f366a.put(m342a, str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static void m349a(String str, int i) {
        MiTinyDataClient.upload("hms_push_error", str, 1L, "error code = " + i);
    }

    /* renamed from: a */
    public static boolean m350a(Context context) {
        if (context == null) {
            return false;
        }
        return C0503as.m484b(context);
    }

    /* renamed from: a */
    public static boolean m351a(Context context, EnumC0463f enumC0463f) {
        if (C0469l.m360a(enumC0463f) != null) {
            return C0809ao.m2557a(context).m2563a(C0469l.m360a(enumC0463f).m1669a(), true);
        }
        return false;
    }

    /* renamed from: b */
    public static String m352b(EnumC0463f enumC0463f) {
        int i = C0468k.f370a[enumC0463f.ordinal()];
        if (i == 1) {
            return "hms_push_error";
        }
        if (i == 2) {
            return "fcm_push_error";
        }
        if (i != 3) {
            return null;
        }
        return "cos_push_error";
    }

    /* renamed from: b */
    public static void m353b(Context context) {
        C0464g.m331a(context).register();
    }

    /* renamed from: b */
    public static void m354b(Context context, EnumC0463f enumC0463f, String str) {
        C0493ai.m439a(context).m443a(new RunnableC0467j(str, context, enumC0463f));
    }

    /* renamed from: c */
    public static void m355c(Context context) {
        C0464g.m331a(context).unregister();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m357d(Context context, EnumC0463f enumC0463f, String str) {
        synchronized (C0466i.class) {
            try {
                String m342a = m342a(enumC0463f);
                if (TextUtils.isEmpty(m342a)) {
                    AbstractC0407b.m70a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
                    return;
                }
                C0878t.m2846a(context.getSharedPreferences("mipush_extra", 0).edit().putString(m342a, str));
                AbstractC0407b.m70a("ASSEMBLE_PUSH : update sp file success!  " + str);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
