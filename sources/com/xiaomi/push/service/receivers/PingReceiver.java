package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0632fm;
import com.xiaomi.push.service.AbstractC0818ax;
import com.xiaomi.push.service.C0827bf;
import com.xiaomi.push.service.XMPushService;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/receivers/PingReceiver.class */
public class PingReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        AbstractC0407b.m74c(intent.getPackage() + " is the package name");
        if (!AbstractC0818ax.f2551o.equals(intent.getAction())) {
            AbstractC0407b.m70a("cancel the old ping timer");
            C0632fm.m1268a();
        } else if (TextUtils.equals(context.getPackageName(), intent.getPackage())) {
            AbstractC0407b.m74c("Ping XMChannelService on timer");
            try {
                Intent intent2 = new Intent(context, (Class<?>) XMPushService.class);
                intent2.putExtra("time_stamp", System.currentTimeMillis());
                intent2.setAction("com.xiaomi.push.timer");
                C0827bf.m2633a(context).m2638a(intent2);
            } catch (Exception e) {
                AbstractC0407b.m72a(e);
            }
        }
    }
}
