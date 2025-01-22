package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xiaomi.mipush.sdk.Constants;

/* renamed from: com.xiaomi.push.ek */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ek.class */
public class C0603ek extends AbstractC0601ei {

    /* renamed from: a */
    private boolean f738a;

    /* renamed from: b */
    private boolean f739b;

    /* renamed from: c */
    private boolean f740c;

    /* renamed from: d */
    private boolean f741d;

    /* renamed from: e */
    private boolean f742e;

    public C0603ek(Context context, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        super(context, i);
        this.f738a = z;
        this.f739b = z2;
        this.f740c = z3;
        this.f741d = z4;
        this.f742e = z5;
    }

    /* renamed from: b */
    private String m987b() {
        if (!this.f738a) {
            return "off";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.f733a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels + Constants.ACCEPT_TIME_SEPARATOR_SP + displayMetrics.widthPixels;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: c */
    private String m988c() {
        if (!this.f739b) {
            return "off";
        }
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: d */
    private String m989d() {
        if (!this.f740c) {
            return "off";
        }
        try {
            return String.valueOf(Build.VERSION.SDK_INT);
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: e */
    private String m990e() {
        if (!this.f741d) {
            return "off";
        }
        try {
            return Settings.Secure.getString(this.f733a.getContentResolver(), "android_id");
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: f */
    private String m991f() {
        if (!this.f742e) {
            return "off";
        }
        try {
            return ((TelephonyManager) this.f733a.getSystemService("phone")).getSimOperator();
        } catch (Throwable th) {
            return "";
        }
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 3;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.DeviceInfoV2;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        return m987b() + "|" + m988c() + "|" + m989d() + "|" + m990e() + "|" + m991f();
    }
}
