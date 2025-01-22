package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0509ay;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.service.u */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/u.class */
public class C0872u {

    /* renamed from: a */
    private static C0872u f2716a;

    /* renamed from: a */
    private Context f2717a;

    /* renamed from: a */
    private List<String> f2718a = new ArrayList();

    /* renamed from: b */
    private final List<String> f2719b = new ArrayList();

    /* renamed from: c */
    private final List<String> f2720c = new ArrayList();

    private C0872u(Context context) {
        this.f2717a = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (this.f2717a == null) {
            this.f2717a = context;
        }
        SharedPreferences sharedPreferences = this.f2717a.getSharedPreferences("mipush_app_info", 0);
        String[] split = sharedPreferences.getString("unregistered_pkg_names", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                break;
            }
            String str = split[i2];
            if (TextUtils.isEmpty(str)) {
                this.f2718a.add(str);
            }
            i = i2 + 1;
        }
        String[] split2 = sharedPreferences.getString("disable_push_pkg_names", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length2 = split2.length;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= length2) {
                break;
            }
            String str2 = split2[i4];
            if (!TextUtils.isEmpty(str2)) {
                this.f2719b.add(str2);
            }
            i3 = i4 + 1;
        }
        String[] split3 = sharedPreferences.getString("disable_push_pkg_names_cache", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length3 = split3.length;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= length3) {
                return;
            }
            String str3 = split3[i6];
            if (!TextUtils.isEmpty(str3)) {
                this.f2720c.add(str3);
            }
            i5 = i6 + 1;
        }
    }

    /* renamed from: a */
    public static C0872u m2803a(Context context) {
        if (f2716a == null) {
            f2716a = new C0872u(context);
        }
        return f2716a;
    }

    /* renamed from: a */
    public void m2804a(String str) {
        synchronized (this.f2718a) {
            if (!this.f2718a.contains(str)) {
                this.f2718a.add(str);
                this.f2717a.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", C0509ay.m523a(this.f2718a, Constants.ACCEPT_TIME_SEPARATOR_SP)).commit();
            }
        }
    }

    /* renamed from: a */
    public boolean m2805a(String str) {
        boolean contains;
        synchronized (this.f2718a) {
            contains = this.f2718a.contains(str);
        }
        return contains;
    }

    /* renamed from: b */
    public void m2806b(String str) {
        synchronized (this.f2719b) {
            if (!this.f2719b.contains(str)) {
                this.f2719b.add(str);
                this.f2717a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names", C0509ay.m523a(this.f2719b, Constants.ACCEPT_TIME_SEPARATOR_SP)).commit();
            }
        }
    }

    /* renamed from: b */
    public boolean m2807b(String str) {
        boolean contains;
        synchronized (this.f2719b) {
            contains = this.f2719b.contains(str);
        }
        return contains;
    }

    /* renamed from: c */
    public void m2808c(String str) {
        synchronized (this.f2720c) {
            if (!this.f2720c.contains(str)) {
                this.f2720c.add(str);
                this.f2717a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names_cache", C0509ay.m523a(this.f2720c, Constants.ACCEPT_TIME_SEPARATOR_SP)).commit();
            }
        }
    }

    /* renamed from: c */
    public boolean m2809c(String str) {
        boolean contains;
        synchronized (this.f2720c) {
            contains = this.f2720c.contains(str);
        }
        return contains;
    }

    /* renamed from: d */
    public void m2810d(String str) {
        synchronized (this.f2718a) {
            if (this.f2718a.contains(str)) {
                this.f2718a.remove(str);
                this.f2717a.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", C0509ay.m523a(this.f2718a, Constants.ACCEPT_TIME_SEPARATOR_SP)).commit();
            }
        }
    }

    /* renamed from: e */
    public void m2811e(String str) {
        synchronized (this.f2719b) {
            if (this.f2719b.contains(str)) {
                this.f2719b.remove(str);
                this.f2717a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names", C0509ay.m523a(this.f2719b, Constants.ACCEPT_TIME_SEPARATOR_SP)).commit();
            }
        }
    }

    /* renamed from: f */
    public void m2812f(String str) {
        synchronized (this.f2720c) {
            if (this.f2720c.contains(str)) {
                this.f2720c.remove(str);
                this.f2717a.getSharedPreferences("mipush_app_info", 0).edit().putString("disable_push_pkg_names_cache", C0509ay.m523a(this.f2720c, Constants.ACCEPT_TIME_SEPARATOR_SP)).commit();
            }
        }
    }
}
