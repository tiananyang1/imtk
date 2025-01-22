package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.bl */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bl.class */
public class C0523bl {

    /* renamed from: a */
    private static int f500a;

    /* renamed from: a */
    private static C0523bl f501a;

    /* renamed from: a */
    private static boolean f502a;

    /* renamed from: a */
    private Context f503a;

    /* renamed from: a */
    private SharedPreferences f504a;

    /* renamed from: a */
    private List f505a = new ArrayList();

    private C0523bl(Context context) {
        this.f503a = context;
        this.f504a = this.f503a.getSharedPreferences("config", 0);
    }

    /* renamed from: a */
    public static C0523bl m603a() {
        return f501a;
    }

    /* renamed from: a */
    public static void m604a(Context context) {
        synchronized (C0523bl.class) {
            try {
                if (f501a == null) {
                    f501a = new C0523bl(context);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static boolean m605a() {
        return false;
    }

    /* renamed from: a */
    public long m606a() {
        return this.f504a.getLong("d_m_i", Long.MAX_VALUE);
    }

    /* renamed from: a */
    public String m607a() {
        return this.f504a.getString("m_s_u", "https://metok.sys.miui.com");
    }

    /* renamed from: a */
    public void m608a() {
        synchronized (this.f505a) {
            Iterator it = this.f505a.iterator();
            while (it.hasNext()) {
                ((InterfaceC0556cr) it.next()).mo801a();
            }
        }
    }

    /* renamed from: a */
    public void m609a(InterfaceC0556cr interfaceC0556cr) {
        if (interfaceC0556cr != null) {
            synchronized (this.f505a) {
                this.f505a.add(interfaceC0556cr);
            }
        }
    }

    /* renamed from: a */
    public void m610a(String str) {
        SharedPreferences.Editor edit = this.f504a.edit();
        edit.putString("config_update_time", str);
        edit.commit();
    }

    /* renamed from: b */
    public long m611b() {
        return this.f504a.getLong("d_s_t", Long.MAX_VALUE);
    }

    /* renamed from: b */
    public String m612b() {
        return this.f504a.getString("config_update_time", "0");
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x01ae, code lost:            if (r0.isEmpty() != false) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x004e, code lost:            if (r0.isEmpty() != false) goto L9;     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m613b() {
        /*
            Method dump skipped, instructions count: 648
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0523bl.m613b():void");
    }

    /* renamed from: b */
    public boolean m614b() {
        return this.f504a.getBoolean("f_d_d", true);
    }

    /* renamed from: c */
    public long m615c() {
        return this.f504a.getLong("d_s_c_t", Long.MAX_VALUE);
    }

    /* renamed from: c */
    public boolean m616c() {
        return this.f504a.getBoolean("d_n_s", f502a);
    }
}
