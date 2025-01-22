package io.fabric.sdk.android;

import android.util.Log;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/DefaultLogger.class */
public class DefaultLogger implements Logger {
    private int logLevel;

    public DefaultLogger() {
        this.logLevel = 4;
    }

    public DefaultLogger(int i) {
        this.logLevel = i;
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: d */
    public void mo2870d(String str, String str2) {
        mo2871d(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: d */
    public void mo2871d(String str, String str2, Throwable th) {
        if (isLoggable(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: e */
    public void mo2872e(String str, String str2) {
        mo2873e(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: e */
    public void mo2873e(String str, String str2, Throwable th) {
        if (isLoggable(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    public int getLogLevel() {
        return this.logLevel;
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: i */
    public void mo2874i(String str, String str2) {
        mo2875i(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: i */
    public void mo2875i(String str, String str2, Throwable th) {
        if (isLoggable(str, 4)) {
            Log.i(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    public boolean isLoggable(String str, int i) {
        return this.logLevel <= i;
    }

    @Override // io.fabric.sdk.android.Logger
    public void log(int i, String str, String str2) {
        log(i, str, str2, false);
    }

    @Override // io.fabric.sdk.android.Logger
    public void log(int i, String str, String str2, boolean z) {
        if (z || isLoggable(str, i)) {
            Log.println(i, str, str2);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: v */
    public void mo2876v(String str, String str2) {
        mo2877v(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: v */
    public void mo2877v(String str, String str2, Throwable th) {
        if (isLoggable(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: w */
    public void mo2878w(String str, String str2) {
        mo2879w(str, str2, null);
    }

    @Override // io.fabric.sdk.android.Logger
    /* renamed from: w */
    public void mo2879w(String str, String str2, Throwable th) {
        if (isLoggable(str, 5)) {
            Log.w(str, str2, th);
        }
    }
}
