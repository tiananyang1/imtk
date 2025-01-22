package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0878t;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.service.C0809ao;
import java.lang.Thread;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.z */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/z.class */
public class C0483z implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private static final Object f395a = new Object();

    /* renamed from: a */
    private static final String[] f396a = {"com.xiaomi.channel.commonutils", "com.xiaomi.common.logger", "com.xiaomi.measite.smack", "com.xiaomi.metoknlp", "com.xiaomi.mipush.sdk", "com.xiaomi.network", "com.xiaomi.push", "com.xiaomi.slim", "com.xiaomi.smack", "com.xiaomi.stats", "com.xiaomi.tinyData", "com.xiaomi.xmpush.thrift", "com.xiaomi.clientreport"};

    /* renamed from: a */
    private Context f397a;

    /* renamed from: a */
    private SharedPreferences f398a;

    /* renamed from: a */
    private Thread.UncaughtExceptionHandler f399a;

    public C0483z(Context context) {
        this(context, Thread.getDefaultUncaughtExceptionHandler());
    }

    public C0483z(Context context, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f397a = context;
        this.f399a = uncaughtExceptionHandler;
    }

    /* renamed from: a */
    private String m407a(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= Math.min(3, stackTrace.length)) {
                break;
            }
            sb.append(stackTrace[i2].toString() + "\r\n");
            i = i2 + 1;
        }
        String sb2 = sb.toString();
        return TextUtils.isEmpty(sb2) ? "" : C0509ay.m521a(sb2);
    }

    /* renamed from: a */
    private void m408a() {
        C0493ai.m439a(this.f397a).m443a(new RunnableC0424aa(this));
    }

    /* renamed from: a */
    private void m410a(Throwable th) {
        String m413b = m413b(th);
        if (TextUtils.isEmpty(m413b)) {
            return;
        }
        String m407a = m407a(th);
        if (TextUtils.isEmpty(m407a)) {
            return;
        }
        C0476s.m374a(this.f397a).m378a(m413b, m407a);
        if (m411a()) {
            m408a();
        }
    }

    /* renamed from: a */
    private boolean m411a() {
        this.f398a = this.f397a.getSharedPreferences("mipush_extra", 4);
        boolean z = false;
        if (C0503as.m487e(this.f397a)) {
            if (!C0809ao.m2557a(this.f397a).m2563a(EnumC0703ic.Crash4GUploadSwitch.m1669a(), true)) {
                return false;
            }
            if (((float) Math.abs((System.currentTimeMillis() / 1000) - this.f398a.getLong("last_crash_upload_time_stamp", 0L))) >= Math.max(3600, C0809ao.m2557a(this.f397a).m2561a(EnumC0703ic.Crash4GUploadFrequency.m1669a(), 3600)) * 0.9f) {
                z = true;
            }
            return z;
        }
        if (!C0503as.m486d(this.f397a)) {
            return true;
        }
        boolean z2 = false;
        if (Math.abs((System.currentTimeMillis() / 1000) - this.f398a.getLong("last_crash_upload_time_stamp", 0L)) >= Math.max(60, C0809ao.m2557a(this.f397a).m2561a(EnumC0703ic.CrashWIFIUploadFrequency.m1669a(), 1800))) {
            z2 = true;
        }
        return z2;
    }

    /* renamed from: a */
    private boolean m412a(boolean z, String str) {
        String[] strArr = f396a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return z;
            }
            if (str.contains(strArr[i2])) {
                return true;
            }
            i = i2 + 1;
        }
    }

    /* renamed from: b */
    private String m413b(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder(th.toString());
        sb.append("\r\n");
        boolean z = false;
        for (StackTraceElement stackTraceElement : stackTrace) {
            String stackTraceElement2 = stackTraceElement.toString();
            z = m412a(z, stackTraceElement2);
            sb.append(stackTraceElement2);
            sb.append("\r\n");
        }
        return z ? sb.toString() : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m414b() {
        this.f398a = this.f397a.getSharedPreferences("mipush_extra", 4);
        SharedPreferences.Editor edit = this.f398a.edit();
        edit.putLong("last_crash_upload_time_stamp", System.currentTimeMillis() / 1000);
        C0878t.m2846a(edit);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        m410a(th);
        synchronized (f395a) {
            try {
                f395a.wait(3000L);
            } catch (InterruptedException e) {
                AbstractC0407b.m72a(e);
            }
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f399a;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }
}
