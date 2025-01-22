package io.fabric.sdk.android;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/Logger.class */
public interface Logger {
    /* renamed from: d */
    void mo2870d(String str, String str2);

    /* renamed from: d */
    void mo2871d(String str, String str2, Throwable th);

    /* renamed from: e */
    void mo2872e(String str, String str2);

    /* renamed from: e */
    void mo2873e(String str, String str2, Throwable th);

    int getLogLevel();

    /* renamed from: i */
    void mo2874i(String str, String str2);

    /* renamed from: i */
    void mo2875i(String str, String str2, Throwable th);

    boolean isLoggable(String str, int i);

    void log(int i, String str, String str2);

    void log(int i, String str, String str2, boolean z);

    void setLogLevel(int i);

    /* renamed from: v */
    void mo2876v(String str, String str2);

    /* renamed from: v */
    void mo2877v(String str, String str2, Throwable th);

    /* renamed from: w */
    void mo2878w(String str, String str2);

    /* renamed from: w */
    void mo2879w(String str, String str2, Throwable th);
}
