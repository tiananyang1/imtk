package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.service.C0809ao;
import java.util.ArrayList;

/* renamed from: com.xiaomi.mipush.sdk.b */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/b.class */
public class C0450b {

    /* renamed from: a */
    public static StackTraceElement[] f324a;

    /* renamed from: a */
    public static void m271a() {
        try {
            f324a = Thread.getAllStackTraces().get(Thread.currentThread());
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m272a(Context context) {
        C0493ai.m439a(context).m444a(new RunnableC0460c(context), 20);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m274b(int i) {
        StackTraceElement[] stackTraceElementArr = f324a;
        if (stackTraceElementArr == null || stackTraceElementArr.length <= 4) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 4;
        while (true) {
            try {
                int i3 = i2;
                if (i3 >= f324a.length || i3 >= i + 4) {
                    break;
                }
                arrayList.add(f324a[i3].toString());
                i2 = i3 + 1;
            } catch (Exception e) {
                return "";
            }
        }
        return arrayList.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m276b(Context context) {
        C0809ao m2557a = C0809ao.m2557a(context);
        if (!m2557a.m2563a(EnumC0703ic.AggregationSdkMonitorSwitch.m1669a(), false)) {
            return false;
        }
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_upload_call_stack_timestamp", 0L)) >= ((long) Math.max(60, m2557a.m2561a(EnumC0703ic.AggregationSdkMonitorFrequency.m1669a(), 86400)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m277c(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_upload_call_stack_timestamp", System.currentTimeMillis());
        edit.commit();
    }
}
