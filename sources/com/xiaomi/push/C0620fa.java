package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import java.util.HashMap;

/* renamed from: com.xiaomi.push.fa */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fa.class */
public final class C0620fa {

    /* renamed from: a */
    private static volatile C0620fa f891a;

    /* renamed from: a */
    private int f892a;

    /* renamed from: a */
    private Context f893a;

    /* renamed from: a */
    private InterfaceC0624fe f894a;

    /* renamed from: a */
    private String f895a;

    /* renamed from: a */
    private HashMap<EnumC0622fc, InterfaceC0623fd> f896a = new HashMap<>();

    /* renamed from: b */
    private String f897b;

    private C0620fa(Context context) {
        this.f893a = context;
        this.f896a.put(EnumC0622fc.SERVICE_ACTION, new C0626fg());
        this.f896a.put(EnumC0622fc.SERVICE_COMPONENT, new C0627fh());
        this.f896a.put(EnumC0622fc.ACTIVITY, new C0617ey());
        this.f896a.put(EnumC0622fc.PROVIDER, new C0625ff());
    }

    /* renamed from: a */
    public static C0620fa m1220a(Context context) {
        if (f891a == null) {
            synchronized (C0620fa.class) {
                try {
                    if (f891a == null) {
                        f891a = new C0620fa(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f891a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1222a(EnumC0622fc enumC0622fc, Context context, C0618ez c0618ez) {
        this.f896a.get(enumC0622fc).mo1208a(context, c0618ez);
    }

    /* renamed from: a */
    public int m1223a() {
        return this.f892a;
    }

    /* renamed from: a */
    public InterfaceC0624fe m1224a() {
        return this.f894a;
    }

    /* renamed from: a */
    public String m1225a() {
        return this.f895a;
    }

    /* renamed from: a */
    public void m1226a(int i) {
        this.f892a = i;
    }

    /* renamed from: a */
    public void m1227a(Context context, String str, int i, String str2, String str3) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            m1226a(i);
            C0493ai.m439a(this.f893a).m443a(new RunnableC0621fb(this, str, context, str2, str3));
        } else {
            C0614ev.m1195a(context, "" + str, 1008, "A receive a incorrect message");
        }
    }

    /* renamed from: a */
    public void m1228a(EnumC0622fc enumC0622fc, Context context, Intent intent, String str) {
        if (enumC0622fc != null) {
            this.f896a.get(enumC0622fc).mo1207a(context, intent, str);
        } else {
            C0614ev.m1195a(context, "null", 1008, "A receive a incorrect message with empty type");
        }
    }

    /* renamed from: a */
    public void m1229a(InterfaceC0624fe interfaceC0624fe) {
        this.f894a = interfaceC0624fe;
    }

    /* renamed from: a */
    public void m1230a(String str) {
        this.f895a = str;
    }

    /* renamed from: a */
    public void m1231a(String str, String str2, int i, InterfaceC0624fe interfaceC0624fe) {
        m1230a(str);
        m1233b(str2);
        m1226a(i);
        m1229a(interfaceC0624fe);
    }

    /* renamed from: b */
    public String m1232b() {
        return this.f897b;
    }

    /* renamed from: b */
    public void m1233b(String str) {
        this.f897b = str;
    }
}
