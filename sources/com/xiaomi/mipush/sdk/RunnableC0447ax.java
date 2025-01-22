package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.ax */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ax.class */
public class RunnableC0447ax implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f305a;

    /* renamed from: a */
    final /* synthetic */ C0446aw f306a;

    /* renamed from: a */
    final /* synthetic */ String[] f307a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0447ax(C0446aw c0446aw, String[] strArr, Context context) {
        this.f306a = c0446aw;
        this.f307a = strArr;
        this.f305a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = 0;
        while (true) {
            try {
                int i2 = i;
                if (i2 >= this.f307a.length) {
                    return;
                }
                if (!TextUtils.isEmpty(this.f307a[i2])) {
                    if (i2 > 0) {
                        Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                    }
                    PackageInfo packageInfo = this.f305a.getPackageManager().getPackageInfo(this.f307a[i2], 4);
                    if (packageInfo != null) {
                        this.f306a.m205a(this.f305a, packageInfo);
                    }
                }
                i = i2 + 1;
            } catch (Throwable th) {
                AbstractC0407b.m72a(th);
                return;
            }
        }
    }
}
