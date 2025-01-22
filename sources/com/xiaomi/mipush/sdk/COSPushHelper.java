package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/COSPushHelper.class */
public class COSPushHelper {

    /* renamed from: a */
    private static long f241a;

    /* renamed from: a */
    private static volatile boolean f242a;

    public static void convertMessage(Intent intent) {
        C0466i.m347a(intent);
    }

    public static void doInNetworkChange(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (getNeedRegister()) {
            long j = f241a;
            if (j <= 0 || j + 300000 <= elapsedRealtime) {
                f241a = elapsedRealtime;
                registerCOSAssemblePush(context);
            }
        }
    }

    public static boolean getNeedRegister() {
        return f242a;
    }

    public static boolean hasNetwork(Context context) {
        return C0466i.m350a(context);
    }

    public static void onNotificationMessageCome(Context context, String str) {
    }

    public static void onPassThoughMessageCome(Context context, String str) {
    }

    public static void registerCOSAssemblePush(Context context) {
        AbstractPushManager m333a = C0464g.m331a(context).m333a(EnumC0463f.ASSEMBLE_PUSH_COS);
        if (m333a != null) {
            AbstractC0407b.m70a("ASSEMBLE_PUSH :  register cos when network change!");
            m333a.register();
        }
    }

    public static void setNeedRegister(boolean z) {
        synchronized (COSPushHelper.class) {
            try {
                f242a = z;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void uploadToken(Context context, String str) {
        C0466i.m346a(context, EnumC0463f.ASSEMBLE_PUSH_COS, str);
    }
}
