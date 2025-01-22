package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.xiaomi.push.bh */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bh.class */
public class C0519bh {

    /* renamed from: a */
    private static volatile C0519bh f480a;

    /* renamed from: a */
    private Context f481a;

    private C0519bh(Context context) {
        this.f481a = context;
    }

    /* renamed from: a */
    public static C0519bh m575a(Context context) {
        if (f480a == null) {
            synchronized (C0519bh.class) {
                try {
                    if (f480a == null) {
                        f480a = new C0519bh(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f480a;
    }

    /* renamed from: a */
    public long m576a(String str, String str2, long j) {
        long j2;
        synchronized (this) {
            try {
                j2 = this.f481a.getSharedPreferences(str, 4).getLong(str2, j);
            } catch (Throwable th) {
                return j;
            }
        }
        return j2;
    }

    /* renamed from: a */
    public String m577a(String str, String str2, String str3) {
        String string;
        synchronized (this) {
            try {
                string = this.f481a.getSharedPreferences(str, 4).getString(str2, str3);
            } catch (Throwable th) {
                return str3;
            }
        }
        return string;
    }

    /* renamed from: a */
    public void m578a(String str, String str2, long j) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f481a.getSharedPreferences(str, 4).edit();
            edit.putLong(str2, j);
            edit.commit();
        }
    }

    /* renamed from: a */
    public void m579a(String str, String str2, String str3) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f481a.getSharedPreferences(str, 4).edit();
            edit.putString(str2, str3);
            edit.commit();
        }
    }
}
