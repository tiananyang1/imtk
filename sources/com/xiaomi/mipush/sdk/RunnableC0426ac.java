package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.ac */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ac.class */
public final class RunnableC0426ac implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f275a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0426ac(Context context) {
        this.f275a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            PackageInfo packageInfo = this.f275a.getPackageManager().getPackageInfo(this.f275a.getPackageName(), 4612);
            C0425ab.m180c(this.f275a);
            C0425ab.m182d(this.f275a, packageInfo);
            C0425ab.m181c(this.f275a, packageInfo);
        } catch (Throwable th) {
            Log.e("ManifestChecker", "", th);
        }
    }
}
