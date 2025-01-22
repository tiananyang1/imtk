package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import com.stub.StubApp;
import com.xiaomi.push.C0486ab;

/* renamed from: com.xiaomi.push.service.ba */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ba.class */
public class C0822ba {

    /* renamed from: a */
    private static C0822ba f2568a;

    /* renamed from: a */
    private int f2569a = 0;

    /* renamed from: a */
    private Context f2570a;

    private C0822ba(Context context) {
        this.f2570a = StubApp.getOrigApplicationContext(context.getApplicationContext());
    }

    /* renamed from: a */
    public static C0822ba m2613a(Context context) {
        if (f2568a == null) {
            f2568a = new C0822ba(context);
        }
        return f2568a;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public int m2614a() {
        int i = this.f2569a;
        if (i != 0) {
            return i;
        }
        this.f2569a = Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(this.f2570a.getContentResolver(), "device_provisioned", 0) : Settings.Secure.getInt(this.f2570a.getContentResolver(), "device_provisioned", 0);
        return this.f2569a;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public Uri m2615a() {
        return Build.VERSION.SDK_INT >= 17 ? Settings.Global.getUriFor("device_provisioned") : Settings.Secure.getUriFor("device_provisioned");
    }

    /* renamed from: a */
    public boolean m2616a() {
        return C0486ab.f404a.contains("xmsf") || C0486ab.f404a.contains("xiaomi") || C0486ab.f404a.contains("miui");
    }
}
