package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* renamed from: com.xiaomi.mipush.sdk.aj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/aj.class */
final class RunnableC0433aj implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f283a;

    /* renamed from: a */
    final /* synthetic */ String[] f284a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0433aj(String[] strArr, Context context) {
        this.f284a = strArr;
        this.f283a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        PackageInfo packageInfo;
        try {
            String[] strArr = this.f284a;
            int length = strArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return;
                }
                String str = strArr[i2];
                if (!TextUtils.isEmpty(str) && (packageInfo = this.f283a.getPackageManager().getPackageInfo(str, 4)) != null) {
                    MiPushClient.awakePushServiceByPackageInfo(this.f283a, packageInfo);
                }
                i = i2 + 1;
            }
        } catch (Throwable th) {
            AbstractC0407b.m72a(th);
        }
    }
}
