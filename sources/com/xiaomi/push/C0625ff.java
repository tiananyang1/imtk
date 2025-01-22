package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.ff */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ff.class */
public class C0625ff implements InterfaceC0623fd {
    /* renamed from: a */
    private void m1234a(Context context, String str) {
        try {
            if (!TextUtils.isEmpty(str) && context != null) {
                String[] split = str.split("/");
                if (split.length > 0 && !TextUtils.isEmpty(split[split.length - 1])) {
                    String str2 = split[split.length - 1];
                    if (TextUtils.isEmpty(str2)) {
                        C0614ev.m1195a(context, "provider", 1008, "B get a incorrect message");
                        return;
                    }
                    String decode = Uri.decode(str2);
                    if (TextUtils.isEmpty(decode)) {
                        C0614ev.m1195a(context, "provider", 1008, "B get a incorrect message");
                        return;
                    }
                    String m1193b = C0613eu.m1193b(decode);
                    if (!TextUtils.isEmpty(m1193b)) {
                        C0614ev.m1195a(context, m1193b, 1007, "play with provider successfully");
                        return;
                    }
                }
            }
            C0614ev.m1195a(context, "provider", 1008, "B get a incorrect message");
        } catch (Exception e) {
            C0614ev.m1195a(context, "provider", 1008, "B meet a exception" + e.getMessage());
        }
    }

    /* renamed from: a */
    private void m1235a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str2)) {
                C0614ev.m1195a(context, "provider", 1008, "argument error");
                return;
            } else {
                C0614ev.m1195a(context, str2, 1008, "argument error");
                return;
            }
        }
        if (!C0616ex.m1203b(context, str)) {
            C0614ev.m1195a(context, str2, 1003, "B is not ready");
            return;
        }
        C0614ev.m1195a(context, str2, 1002, "B is ready");
        C0614ev.m1195a(context, str2, 1004, "A is ready");
        String m1191a = C0613eu.m1191a(str2);
        try {
            if (TextUtils.isEmpty(m1191a)) {
                C0614ev.m1195a(context, str2, 1008, "info is empty");
                return;
            }
            String type = context.getContentResolver().getType(C0613eu.m1190a(str, m1191a));
            if (TextUtils.isEmpty(type) || !"success".equals(type)) {
                C0614ev.m1195a(context, str2, 1008, "A is fail to help B's provider");
            } else {
                C0614ev.m1195a(context, str2, 1005, "A is successful");
                C0614ev.m1195a(context, str2, 1006, "The job is finished");
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            C0614ev.m1195a(context, str2, 1008, "A meet a exception when help B's provider");
        }
    }

    @Override // com.xiaomi.push.InterfaceC0623fd
    /* renamed from: a */
    public void mo1207a(Context context, Intent intent, String str) {
        m1234a(context, str);
    }

    @Override // com.xiaomi.push.InterfaceC0623fd
    /* renamed from: a */
    public void mo1208a(Context context, C0618ez c0618ez) {
        if (c0618ez != null) {
            m1235a(context, c0618ez.m1211b(), c0618ez.m1215d());
        } else {
            C0614ev.m1195a(context, "provider", 1008, "A receive incorrect message");
        }
    }
}
