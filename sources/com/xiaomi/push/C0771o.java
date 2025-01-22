package com.xiaomi.push;

import android.content.Context;

/* renamed from: com.xiaomi.push.o */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/o.class */
public class C0771o {
    /* renamed from: a */
    public static boolean m2409a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
