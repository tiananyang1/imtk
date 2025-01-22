package com.xiaomi.push;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.xiaomi.mipush.sdk.Constants;

/* renamed from: com.xiaomi.push.ee */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ee.class */
public class C0597ee extends AbstractC0601ei {
    public C0597ee(Context context, int i) {
        super(context, i);
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 4;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.AppInstallList;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        StringBuilder sb = new StringBuilder();
        try {
            PackageManager packageManager = this.f733a.getPackageManager();
            int i = 0;
            for (PackageInfo packageInfo : packageManager.getInstalledPackages(128)) {
                if ((packageInfo.applicationInfo.flags & 1) == 0) {
                    if (sb.length() > 0) {
                        sb.append(";");
                    }
                    String charSequence = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                    String m1364b = C0646g.m1364b(this.f733a, packageInfo.packageName);
                    sb.append(charSequence);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(packageInfo.packageName);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(packageInfo.versionName);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(packageInfo.versionCode);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(m1364b);
                    int i2 = i + 1;
                    i = i2;
                    if (i2 >= 200) {
                        break;
                    }
                }
            }
        } catch (Throwable th) {
        }
        return sb.toString();
    }
}
