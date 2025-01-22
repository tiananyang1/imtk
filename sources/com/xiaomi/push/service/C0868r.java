package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;

/* renamed from: com.xiaomi.push.service.r */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/r.class */
public class C0868r {

    /* renamed from: a */
    private static volatile C0868r f2696a;

    /* renamed from: a */
    private SharedPreferences f2697a;

    private C0868r(Context context) {
        this.f2697a = context.getSharedPreferences("mipush", 0);
    }

    /* renamed from: a */
    public static C0868r m2782a(Context context) {
        if (f2696a == null) {
            synchronized (C0868r.class) {
                try {
                    if (f2696a == null) {
                        f2696a = new C0868r(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f2696a;
    }

    /* renamed from: a */
    public String m2783a() {
        String string;
        synchronized (this) {
            string = this.f2697a.getString(Constants.EXTRA_KEY_MIID, "0");
        }
        return string;
    }

    /* renamed from: a */
    public void m2784a() {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f2697a.edit();
            edit.remove(Constants.EXTRA_KEY_MIID);
            edit.commit();
        }
    }

    /* renamed from: a */
    public void m2785a(String str) {
        synchronized (this) {
            String str2 = str;
            if (TextUtils.isEmpty(str)) {
                str2 = "0";
            }
            SharedPreferences.Editor edit = this.f2697a.edit();
            edit.putString(Constants.EXTRA_KEY_MIID, str2);
            edit.commit();
        }
    }

    /* renamed from: a */
    public boolean m2786a() {
        boolean equals;
        synchronized (this) {
            equals = TextUtils.equals("0", m2783a());
        }
        return !equals;
    }
}
