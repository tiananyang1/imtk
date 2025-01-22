package com.xiaomi.push;

import android.content.Context;
import android.content.IntentFilter;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.mpcd.receivers.BroadcastActionsReceiver;

/* renamed from: com.xiaomi.push.du */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/du.class */
public class C0586du {
    /* renamed from: a */
    private static IntentFilter m949a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        return intentFilter;
    }

    /* renamed from: a */
    private static InterfaceC0591dz m950a() {
        return new C0587dv();
    }

    /* renamed from: a */
    public static void m951a(Context context) {
        C0593ea.m969a(context).m973a();
        try {
            context.registerReceiver(new BroadcastActionsReceiver(m950a()), m949a());
        } catch (Throwable th) {
            AbstractC0407b.m72a(th);
        }
    }
}
