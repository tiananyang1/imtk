package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.clientreport.data.C0408a;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.manager.ClientReportClient;

/* renamed from: com.xiaomi.push.fl */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fl.class */
public class C0631fl {

    /* renamed from: a */
    private static volatile C0631fl f911a;

    /* renamed from: a */
    private Context f912a;

    private C0631fl(Context context) {
        this.f912a = context;
    }

    /* renamed from: a */
    public static C0631fl m1256a(Context context) {
        if (f911a == null) {
            synchronized (C0631fl.class) {
                try {
                    if (f911a == null) {
                        f911a = new C0631fl(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f911a;
    }

    /* renamed from: a */
    private void m1257a(C0408a c0408a) {
        if (c0408a instanceof PerfClientReport) {
            ClientReportClient.reportPerf(this.f912a, (PerfClientReport) c0408a);
        } else if (c0408a instanceof EventClientReport) {
            ClientReportClient.reportEvent(this.f912a, (EventClientReport) c0408a);
        }
    }

    /* renamed from: a */
    public void m1258a(String str, int i, long j, long j2) {
        if (i < 0 || j2 < 0 || j <= 0) {
            return;
        }
        PerfClientReport m1246a = C0630fk.m1246a(this.f912a, i, j, j2);
        m1246a.setAppPackageName(str);
        m1257a(m1246a);
    }

    /* renamed from: a */
    public void m1259a(String str, Intent intent, int i, String str2) {
        if (intent == null) {
            return;
        }
        m1262a(str, C0630fk.m1249a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), i, System.currentTimeMillis(), str2);
    }

    /* renamed from: a */
    public void m1260a(String str, Intent intent, String str2) {
        if (intent == null) {
            return;
        }
        m1262a(str, C0630fk.m1249a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), 5001, System.currentTimeMillis(), str2);
    }

    /* renamed from: a */
    public void m1261a(String str, Intent intent, Throwable th) {
        if (intent == null) {
            return;
        }
        m1262a(str, C0630fk.m1249a(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), 5001, System.currentTimeMillis(), th.getMessage());
    }

    /* renamed from: a */
    public void m1262a(String str, String str2, String str3, int i, long j, String str4) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        EventClientReport m1243a = C0630fk.m1243a(this.f912a, str2, str3, i, j, str4);
        m1243a.setAppPackageName(str);
        m1257a(m1243a);
    }

    /* renamed from: a */
    public void m1263a(String str, String str2, String str3, int i, String str4) {
        m1262a(str, str2, str3, i, System.currentTimeMillis(), str4);
    }

    /* renamed from: a */
    public void m1264a(String str, String str2, String str3, String str4) {
        m1262a(str, str2, str3, 5002, System.currentTimeMillis(), str4);
    }

    /* renamed from: a */
    public void m1265a(String str, String str2, String str3, Throwable th) {
        m1262a(str, str2, str3, 5001, System.currentTimeMillis(), th.getMessage());
    }

    /* renamed from: b */
    public void m1266b(String str, String str2, String str3, String str4) {
        m1262a(str, str2, str3, 5001, System.currentTimeMillis(), str4);
    }

    /* renamed from: c */
    public void m1267c(String str, String str2, String str3, String str4) {
        m1262a(str, str2, str3, 4002, System.currentTimeMillis(), str4);
    }
}
