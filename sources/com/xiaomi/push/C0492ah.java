package com.xiaomi.push;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

/* renamed from: com.xiaomi.push.ah */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ah.class */
public class C0492ah {
    /* renamed from: a */
    public static boolean m435a(Context context) {
        try {
            return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m436b(Context context) {
        Intent intent;
        try {
            intent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (Exception e) {
            intent = null;
        }
        boolean z = false;
        if (intent == null) {
            return false;
        }
        int intExtra = intent.getIntExtra(SettingsJsonConstants.APP_STATUS_KEY, -1);
        if (intExtra == 2 || intExtra == 5) {
            z = true;
        }
        return z;
    }
}
