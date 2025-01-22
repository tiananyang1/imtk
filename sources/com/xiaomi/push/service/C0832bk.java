package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import com.stub.StubApp;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0490af;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.C0878t;

/* renamed from: com.xiaomi.push.service.bk */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bk.class */
public final class C0832bk {

    /* renamed from: a */
    private static volatile C0832bk f2602a;

    /* renamed from: a */
    Context f2603a;

    /* renamed from: a */
    private SharedPreferences f2604a;

    /* renamed from: com.xiaomi.push.service.bk$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bk$a.class */
    public static abstract class a implements Runnable {

        /* renamed from: a */
        long f2605a;

        /* renamed from: a */
        String f2606a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(String str, long j) {
            this.f2606a = str;
            this.f2605a = j;
        }

        /* renamed from: a */
        abstract void mo2502a(C0832bk c0832bk);

        @Override // java.lang.Runnable
        public void run() {
            if (C0832bk.f2602a != null) {
                Context context = C0832bk.f2602a.f2603a;
                if (C0503as.m485c(context)) {
                    if (System.currentTimeMillis() - C0832bk.f2602a.f2604a.getLong(":ts-" + this.f2606a, 0L) > this.f2605a || C0490af.m432a(context)) {
                        C0878t.m2846a(C0832bk.f2602a.f2604a.edit().putLong(":ts-" + this.f2606a, System.currentTimeMillis()));
                        mo2502a(C0832bk.f2602a);
                    }
                }
            }
        }
    }

    private C0832bk(Context context) {
        this.f2603a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.f2604a = context.getSharedPreferences("sync", 0);
    }

    /* renamed from: a */
    public static C0832bk m2657a(Context context) {
        if (f2602a == null) {
            synchronized (C0832bk.class) {
                try {
                    if (f2602a == null) {
                        f2602a = new C0832bk(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f2602a;
    }

    /* renamed from: a */
    public String m2658a(String str, String str2) {
        return this.f2604a.getString(str + Constants.COLON_SEPARATOR + str2, "");
    }

    /* renamed from: a */
    public void m2659a(a aVar) {
        C0493ai.m439a(this.f2603a).m444a(aVar, ((int) (Math.random() * 30.0d)) + 10);
    }

    /* renamed from: a */
    public void m2660a(String str, String str2, String str3) {
        C0878t.m2846a(f2602a.f2604a.edit().putString(str + Constants.COLON_SEPARATOR + str2, str3));
    }
}
