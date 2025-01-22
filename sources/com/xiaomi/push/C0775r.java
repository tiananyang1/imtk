package com.xiaomi.push;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.r */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/r.class */
public class C0775r {

    /* renamed from: a */
    private static volatile C0775r f2373a;

    /* renamed from: a */
    private Context f2374a;

    /* renamed from: a */
    private Handler f2375a = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    private Map<String, Map<String, String>> f2376a = new HashMap();

    private C0775r(Context context) {
        this.f2374a = context;
    }

    /* renamed from: a */
    public static C0775r m2416a(Context context) {
        if (f2373a == null) {
            synchronized (C0775r.class) {
                try {
                    if (f2373a == null) {
                        f2373a = new C0775r(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f2373a;
    }

    /* renamed from: a */
    private String m2417a(String str, String str2) {
        synchronized (this) {
            if (this.f2376a == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return "";
            }
            try {
                Map<String, String> map = this.f2376a.get(str);
                if (map == null) {
                    return "";
                }
                return map.get(str2);
            } catch (Throwable th) {
                return "";
            }
        }
    }

    /* renamed from: b */
    private void m2418b(String str, String str2, String str3) {
        synchronized (this) {
            if (this.f2376a == null) {
                this.f2376a = new HashMap();
            }
            Map<String, String> map = this.f2376a.get(str);
            Map<String, String> map2 = map;
            if (map == null) {
                map2 = new HashMap();
            }
            map2.put(str2, str3);
            this.f2376a.put(str, map2);
        }
    }

    /* renamed from: a */
    public String m2419a(String str, String str2, String str3) {
        synchronized (this) {
            String m2417a = m2417a(str, str2);
            if (!TextUtils.isEmpty(m2417a)) {
                return m2417a;
            }
            return this.f2374a.getSharedPreferences(str, 4).getString(str2, str3);
        }
    }

    /* renamed from: a */
    public void m2420a(String str, String str2, String str3) {
        synchronized (this) {
            m2418b(str, str2, str3);
            this.f2375a.post(new RunnableC0776s(this, str, str2, str3));
        }
    }
}
