package com.xiaomi.push;

import android.os.Looper;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* renamed from: com.xiaomi.push.ao */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ao.class */
public class C0499ao {
    /* renamed from: a */
    public static void m464a() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new RuntimeException("can't do this on ui thread");
        }
    }

    /* renamed from: a */
    public static void m465a(boolean z) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread() && z) {
            throw new RuntimeException("can't do this on ui thread when debug_switch:" + z);
        }
        if (Looper.getMainLooper().getThread() != Thread.currentThread() || z) {
            return;
        }
        AbstractC0407b.m70a("can't do this on ui thread when debug_switch:" + z);
    }
}
