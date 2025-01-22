package com.xiaomi.push;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* renamed from: com.xiaomi.push.m */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/m.class */
public class C0769m {
    /* renamed from: a */
    public static Account m2396a(Context context) {
        try {
            if (!m2397a(context)) {
                return null;
            }
            Account[] accountsByType = AccountManager.get(context).getAccountsByType("com.xiaomi");
            Account account = null;
            if (accountsByType != null) {
                account = null;
                if (accountsByType.length > 0) {
                    account = accountsByType[0];
                }
            }
            return account;
        } catch (Exception e) {
            AbstractC0407b.m75d(e.toString());
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001f, code lost:            if (r0.checkPermission("android.permission.GET_ACCOUNTS_PRIVILEGED", r0) == 0) goto L7;     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m2397a(android.content.Context r4) {
        /*
            r0 = 0
            r6 = r0
            r0 = r4
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch: java.lang.Throwable -> L26
            r7 = r0
            r0 = r4
            java.lang.String r0 = r0.getPackageName()     // Catch: java.lang.Throwable -> L26
            r4 = r0
            r0 = r7
            java.lang.String r1 = "android.permission.GET_ACCOUNTS"
            r2 = r4
            int r0 = r0.checkPermission(r1, r2)     // Catch: java.lang.Throwable -> L26
            if (r0 == 0) goto L22
            r0 = r7
            java.lang.String r1 = "android.permission.GET_ACCOUNTS_PRIVILEGED"
            r2 = r4
            int r0 = r0.checkPermission(r1, r2)     // Catch: java.lang.Throwable -> L26
            r5 = r0
            r0 = r5
            if (r0 != 0) goto L24
        L22:
            r0 = 1
            r6 = r0
        L24:
            r0 = r6
            return r0
        L26:
            r4 = move-exception
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0769m.m2397a(android.content.Context):boolean");
    }
}
