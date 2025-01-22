package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0580do;
import com.xiaomi.push.C0581dp;
import java.io.File;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/Logger.class */
public class Logger {
    private static boolean sDisablePushLog;
    private static LoggerInterface sUserLogger;

    public static void disablePushFileLog(Context context) {
        sDisablePushLog = true;
        setPushLog(context);
    }

    public static void enablePushFileLog(Context context) {
        sDisablePushLog = false;
        setPushLog(context);
    }

    public static File getLogFile(String str) {
        try {
            File file = new File(str);
            if (!file.exists() || !file.isDirectory()) {
                return null;
            }
            File[] listFiles = file.listFiles();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= listFiles.length) {
                    return null;
                }
                if (listFiles[i2].isFile() && !listFiles[i2].getName().contains("lock") && listFiles[i2].getName().contains("log")) {
                    return listFiles[i2];
                }
                i = i2 + 1;
            }
        } catch (NullPointerException e) {
            AbstractC0407b.m75d("null pointer exception while retrieve file.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static LoggerInterface getUserLogger() {
        return sUserLogger;
    }

    private static boolean hasWritePermission(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            int length = strArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return false;
                }
                if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(strArr[i2])) {
                    return true;
                }
                i = i2 + 1;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void setLogger(Context context, LoggerInterface loggerInterface) {
        sUserLogger = loggerInterface;
        setPushLog(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.xiaomi.channel.commonutils.logger.LoggerInterface] */
    public static void setPushLog(Context context) {
        C0580do c0580do;
        boolean z = sUserLogger != null;
        C0581dp c0581dp = new C0581dp(context);
        if (!sDisablePushLog && hasWritePermission(context) && z) {
            c0580do = new C0580do(sUserLogger, c0581dp);
        } else {
            if (!sDisablePushLog && hasWritePermission(context)) {
                AbstractC0407b.m68a(c0581dp);
                return;
            }
            c0580do = z ? sUserLogger : new C0580do(null, null);
        }
        AbstractC0407b.m68a(c0580do);
    }

    public static void uploadLogFile(Context context, boolean z) {
        C0493ai.m439a(context).m443a(new RunnableC0482y(context, z));
    }
}
