package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import com.xiaomi.push.C0488ad;
import java.util.List;

/* renamed from: com.xiaomi.push.service.ao */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ao.class */
public class C0809ao {

    /* renamed from: a */
    private static volatile C0809ao f2480a;

    /* renamed from: a */
    protected SharedPreferences f2481a;

    private C0809ao(Context context) {
        this.f2481a = context.getSharedPreferences("mipush_oc", 0);
    }

    /* renamed from: a */
    public static C0809ao m2557a(Context context) {
        if (f2480a == null) {
            synchronized (C0809ao.class) {
                try {
                    if (f2480a == null) {
                        f2480a = new C0809ao(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f2480a;
    }

    /* renamed from: a */
    private String m2558a(int i) {
        return "normal_oc_" + i;
    }

    /* renamed from: a */
    private void m2559a(SharedPreferences.Editor editor, Pair<Integer, Object> pair, String str) {
        if (pair.second instanceof Integer) {
            editor.putInt(str, ((Integer) pair.second).intValue());
            return;
        }
        if (pair.second instanceof Long) {
            editor.putLong(str, ((Long) pair.second).longValue());
        } else if (pair.second instanceof String) {
            editor.putString(str, (String) pair.second);
        } else if (pair.second instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) pair.second).booleanValue());
        }
    }

    /* renamed from: b */
    private String m2560b(int i) {
        return "custom_oc_" + i;
    }

    /* renamed from: a */
    public int m2561a(int i, int i2) {
        String m2560b = m2560b(i);
        if (this.f2481a.contains(m2560b)) {
            return this.f2481a.getInt(m2560b, 0);
        }
        String m2558a = m2558a(i);
        return this.f2481a.contains(m2558a) ? this.f2481a.getInt(m2558a, 0) : i2;
    }

    /* renamed from: a */
    public void m2562a(List<Pair<Integer, Object>> list) {
        if (C0488ad.m430a(list)) {
            return;
        }
        SharedPreferences.Editor edit = this.f2481a.edit();
        for (Pair<Integer, Object> pair : list) {
            if (pair.first != null && pair.second != null) {
                m2559a(edit, pair, m2558a(((Integer) pair.first).intValue()));
            }
        }
        edit.commit();
    }

    /* renamed from: a */
    public boolean m2563a(int i, boolean z) {
        String m2560b = m2560b(i);
        if (this.f2481a.contains(m2560b)) {
            return this.f2481a.getBoolean(m2560b, false);
        }
        String m2558a = m2558a(i);
        return this.f2481a.contains(m2558a) ? this.f2481a.getBoolean(m2558a, false) : z;
    }

    /* renamed from: b */
    public void m2564b(List<Pair<Integer, Object>> list) {
        if (C0488ad.m430a(list)) {
            return;
        }
        SharedPreferences.Editor edit = this.f2481a.edit();
        for (Pair<Integer, Object> pair : list) {
            if (pair.first != null) {
                String m2560b = m2560b(((Integer) pair.first).intValue());
                if (pair.second == null) {
                    edit.remove(m2560b);
                } else {
                    m2559a(edit, pair, m2560b);
                }
            }
        }
        edit.commit();
    }
}
