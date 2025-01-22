package com.sensorsdata.analytics.android.sdk;

import android.util.Log;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SALog.class */
public class SALog {
    private static boolean debug;
    private static boolean enableLog;

    /* renamed from: d */
    public static void m53d(String str, String str2) {
        if (debug) {
            info(str, str2, null);
        }
    }

    /* renamed from: d */
    public static void m54d(String str, String str2, Throwable th) {
        if (debug) {
            info(str, str2, th);
        }
    }

    /* renamed from: i */
    public static void m55i(String str, String str2) {
        if (enableLog) {
            info(str, str2, null);
        }
    }

    /* renamed from: i */
    public static void m56i(String str, String str2, Throwable th) {
        if (enableLog) {
            info(str, str2, th);
        }
    }

    /* renamed from: i */
    public static void m57i(String str, Throwable th) {
        if (enableLog) {
            info(str, "", th);
        }
    }

    public static void info(String str, String str2, Throwable th) {
        try {
            Log.i(str, str2, th);
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public static void printStackTrace(Exception exc) {
        if (exc != null) {
            exc.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setDebug(boolean z) {
        debug = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setEnableLog(boolean z) {
        enableLog = z;
    }
}
