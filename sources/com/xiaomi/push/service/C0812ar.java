package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.push.C0509ay;

/* renamed from: com.xiaomi.push.service.ar */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ar.class */
public class C0812ar {

    /* renamed from: a */
    private static long f2484a = 0;

    /* renamed from: a */
    private static String f2485a = "";

    /* renamed from: a */
    public static String m2571a() {
        if (TextUtils.isEmpty(f2485a)) {
            f2485a = C0509ay.m520a(4);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f2485a);
        long j = f2484a;
        f2484a = 1 + j;
        sb.append(j);
        return sb.toString();
    }
}
