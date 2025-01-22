package com.xiaomi.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.fg */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fg.class */
public class C0626fg implements InterfaceC0623fd {
    /* renamed from: a */
    private void m1236a(Service service, Intent intent) {
        String stringExtra = intent.getStringExtra("awake_info");
        if (!TextUtils.isEmpty(stringExtra)) {
            String m1193b = C0613eu.m1193b(stringExtra);
            if (!TextUtils.isEmpty(m1193b)) {
                C0614ev.m1195a(StubApp.getOrigApplicationContext(service.getApplicationContext()), m1193b, 1007, "play with service successfully");
                return;
            }
        }
        C0614ev.m1195a(StubApp.getOrigApplicationContext(service.getApplicationContext()), "service", 1008, "B get a incorrect message");
    }

    /* renamed from: a */
    private void m1237a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            if (TextUtils.isEmpty(str3)) {
                C0614ev.m1195a(context, "service", 1008, "argument error");
                return;
            } else {
                C0614ev.m1195a(context, str3, 1008, "argument error");
                return;
            }
        }
        if (!C0616ex.m1202a(context, str, str2)) {
            C0614ev.m1195a(context, str3, 1003, "B is not ready");
            return;
        }
        C0614ev.m1195a(context, str3, 1002, "B is ready");
        C0614ev.m1195a(context, str3, 1004, "A is ready");
        try {
            Intent intent = new Intent();
            intent.setAction(str2);
            intent.setPackage(str);
            intent.putExtra("awake_info", C0613eu.m1191a(str3));
            if (context.startService(intent) == null) {
                C0614ev.m1195a(context, str3, 1008, "A is fail to help B's service");
            } else {
                C0614ev.m1195a(context, str3, 1005, "A is successful");
                C0614ev.m1195a(context, str3, 1006, "The job is finished");
            }
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            C0614ev.m1195a(context, str3, 1008, "A meet a exception when help B's service");
        }
    }

    @Override // com.xiaomi.push.InterfaceC0623fd
    /* renamed from: a */
    public void mo1207a(Context context, Intent intent, String str) {
        if (context == null || !(context instanceof Service)) {
            C0614ev.m1195a(context, "service", 1008, "A receive incorrect message");
        } else {
            m1236a((Service) context, intent);
        }
    }

    @Override // com.xiaomi.push.InterfaceC0623fd
    /* renamed from: a */
    public void mo1208a(Context context, C0618ez c0618ez) {
        if (c0618ez != null) {
            m1237a(context, c0618ez.m1209a(), c0618ez.m1211b(), c0618ez.m1215d());
        } else {
            C0614ev.m1195a(context, "service", 1008, "A receive incorrect message");
        }
    }
}
