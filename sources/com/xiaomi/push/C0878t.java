package com.xiaomi.push;

import android.content.SharedPreferences;
import android.os.Build;

/* renamed from: com.xiaomi.push.t */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/t.class */
public final class C0878t {
    /* renamed from: a */
    public static void m2846a(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }
}
