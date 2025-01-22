package com.xiaomi.push.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0679hf;

/* renamed from: com.xiaomi.push.service.cb */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/cb.class */
class C0850cb extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2631a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0850cb(XMPushService xMPushService) {
        this.f2631a = xMPushService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean m2442a;
        if (TextUtils.equals("com.xiaomi.metoknlp.geofencing.state_change_protected", intent.getAction())) {
            String stringExtra = intent.getStringExtra("Describe");
            String stringExtra2 = intent.getStringExtra("State");
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            String str = stringExtra2;
            m2442a = this.f2631a.m2442a(stringExtra2, stringExtra, context);
            if (!m2442a) {
                str = "Unknown";
                AbstractC0407b.m70a(" updated geofence statue about geo_id:" + stringExtra + " falied. current_statue:Unknown");
            }
            C0679hf.m1534a(new RunnableC0851cc(this, context, stringExtra, str));
            AbstractC0407b.m74c("ownresilt结果:state= " + str + "\n describe=" + stringExtra);
        }
    }
}
