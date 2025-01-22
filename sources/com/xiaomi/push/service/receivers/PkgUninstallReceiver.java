package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.AbstractC0823bb;
import com.xiaomi.push.service.C0827bf;
import com.xiaomi.push.service.C0859j;
import com.xiaomi.push.service.XMPushService;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/receivers/PkgUninstallReceiver.class */
public class PkgUninstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            return;
        }
        boolean z = intent.getExtras().getBoolean("android.intent.extra.REPLACING");
        Uri data = intent.getData();
        if (data == null || z) {
            return;
        }
        try {
            Intent intent2 = new Intent(context, (Class<?>) XMPushService.class);
            intent2.setAction(AbstractC0823bb.f2571a);
            intent2.putExtra("uninstall_pkg_name", data.getEncodedSchemeSpecificPart());
            C0827bf.m2633a(context).m2638a(intent2);
            C0859j.m2715a(StubApp.getOrigApplicationContext(context.getApplicationContext()), data.getEncodedSchemeSpecificPart());
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }
}
