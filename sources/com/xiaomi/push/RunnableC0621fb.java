package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.fb */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fb.class */
public class RunnableC0621fb implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f898a;

    /* renamed from: a */
    final /* synthetic */ C0620fa f899a;

    /* renamed from: a */
    final /* synthetic */ String f900a;

    /* renamed from: b */
    final /* synthetic */ String f901b;

    /* renamed from: c */
    final /* synthetic */ String f902c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0621fb(C0620fa c0620fa, String str, Context context, String str2, String str3) {
        this.f899a = c0620fa;
        this.f900a = str;
        this.f898a = context;
        this.f901b = str2;
        this.f902c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        String str;
        String str2;
        C0620fa c0620fa;
        EnumC0622fc enumC0622fc;
        Context context2;
        if (TextUtils.isEmpty(this.f900a)) {
            context = this.f898a;
            str = "null";
            str2 = "A receive a incorrect message with empty info";
        } else {
            try {
                C0614ev.m1195a(this.f898a, this.f900a, 1001, "get message");
                JSONObject jSONObject = new JSONObject(this.f900a);
                String optString = jSONObject.optString("action");
                String optString2 = jSONObject.optString("awakened_app_packagename");
                String optString3 = jSONObject.optString("awake_app_packagename");
                String optString4 = jSONObject.optString("awake_app");
                String optString5 = jSONObject.optString("awake_type");
                if (this.f901b.equals(optString3) && this.f902c.equals(optString4)) {
                    if (TextUtils.isEmpty(optString5) || TextUtils.isEmpty(optString3) || TextUtils.isEmpty(optString4) || TextUtils.isEmpty(optString2)) {
                        C0614ev.m1195a(this.f898a, this.f900a, 1008, "A receive a incorrect message with empty type");
                        return;
                    }
                    this.f899a.m1233b(optString3);
                    this.f899a.m1230a(optString4);
                    C0618ez c0618ez = new C0618ez();
                    c0618ez.m1212b(optString);
                    c0618ez.m1210a(optString2);
                    c0618ez.m1216d(this.f900a);
                    if ("service".equals(optString5)) {
                        if (TextUtils.isEmpty(optString)) {
                            c0618ez.m1214c("com.xiaomi.mipush.sdk.PushMessageHandler");
                            c0620fa = this.f899a;
                            enumC0622fc = EnumC0622fc.SERVICE_COMPONENT;
                            context2 = this.f898a;
                        } else {
                            c0620fa = this.f899a;
                            enumC0622fc = EnumC0622fc.SERVICE_ACTION;
                            context2 = this.f898a;
                        }
                    } else if (EnumC0622fc.ACTIVITY.f908a.equals(optString5)) {
                        c0620fa = this.f899a;
                        enumC0622fc = EnumC0622fc.ACTIVITY;
                        context2 = this.f898a;
                    } else {
                        if (!EnumC0622fc.PROVIDER.f908a.equals(optString5)) {
                            C0614ev.m1195a(this.f898a, this.f900a, 1008, "A receive a incorrect message with unknown type " + optString5);
                            return;
                        }
                        c0620fa = this.f899a;
                        enumC0622fc = EnumC0622fc.PROVIDER;
                        context2 = this.f898a;
                    }
                    c0620fa.m1222a(enumC0622fc, context2, c0618ez);
                    return;
                }
                C0614ev.m1195a(this.f898a, this.f900a, 1008, "A receive a incorrect message with incorrect package info" + optString3);
                return;
            } catch (JSONException e) {
                AbstractC0407b.m72a(e);
                context = this.f898a;
                str = this.f900a;
                str2 = "A meet a exception when receive the message";
            }
        }
        C0614ev.m1195a(context, str, 1008, str2);
    }
}
