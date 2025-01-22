package com.xiaomi.push;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.g */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/g.class */
public class C0646g {

    /* renamed from: com.xiaomi.push.g$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/g$a.class */
    public enum a {
        UNKNOWN(0),
        ALLOWED(1),
        NOT_ALLOWED(2);


        /* renamed from: a */
        private final int f1049a;

        a(int i) {
            this.f1049a = i;
        }

        /* renamed from: a */
        public int m1368a() {
            return this.f1049a;
        }
    }

    /* renamed from: a */
    public static int m1356a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return 0;
    }

    @TargetApi(19)
    /* renamed from: a */
    public static a m1357a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || Build.VERSION.SDK_INT < 19) {
            return a.UNKNOWN;
        }
        try {
            Integer num = (Integer) C0504at.m492a((Class<? extends Object>) AppOpsManager.class, "OP_POST_NOTIFICATION");
            if (num == null) {
                return a.UNKNOWN;
            }
            Integer num2 = (Integer) C0504at.m495a((AppOpsManager) context.getSystemService("appops"), "checkOpNoThrow", num, Integer.valueOf(context.getPackageManager().getApplicationInfo(str, 0).uid), str);
            return (num2 == null || num2.intValue() != 0) ? a.NOT_ALLOWED : a.ALLOWED;
        } catch (Throwable th) {
            return a.UNKNOWN;
        }
    }

    /* renamed from: a */
    public static String m1358a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null || (runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) == null) {
            return null;
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m1359a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionName : "1.0";
    }

    /* renamed from: a */
    public static String m1360a(String[] strArr) {
        boolean z;
        EnumC0673h[] values = EnumC0673h.values();
        byte[] bArr = new byte[(int) Math.ceil(values.length / 8.0d)];
        if (strArr == null) {
            AbstractC0407b.m74c("AppInfoUtils.: no permissions");
            return "";
        }
        int length = strArr.length;
        int i = 0;
        int i2 = -1;
        while (true) {
            int i3 = i2;
            if (i >= length) {
                return new String(Base64.encode(bArr, 0));
            }
            String str = strArr[i];
            int i4 = i3;
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith("android.permission.")) {
                    int i5 = 0;
                    while (true) {
                        i4 = i5;
                        if (i4 >= values.length) {
                            i4 = i3;
                            z = false;
                            break;
                        }
                        if (TextUtils.equals("android.permission." + values[i4].name(), str)) {
                            z = true;
                            break;
                        }
                        i5 = i4 + 1;
                    }
                    if (z && i4 != -1) {
                        int i6 = i4 / 8;
                        bArr[i6] = (byte) (bArr[i6] | (1 << (7 - (i4 % 8))));
                    }
                } else {
                    i4 = i3;
                }
            }
            i++;
            i2 = i4;
        }
    }

    /* renamed from: a */
    public static boolean m1361a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() < 1) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid() && runningAppProcessInfo.processName.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m1362a(Context context, String str) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
        while (it.hasNext()) {
            if (Arrays.asList(it.next().pkgList).contains(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static String m1363b(Context context) {
        String str;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (it.hasNext()) {
                String[] strArr = it.next().pkgList;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (strArr != null && i2 < strArr.length) {
                        if (!arrayList.contains(strArr[i2])) {
                            arrayList.add(strArr[i2]);
                            if (arrayList.size() == 1) {
                                str = (String) arrayList.get(0);
                            } else {
                                sb.append("#");
                                str = strArr[i2];
                            }
                            sb.append(str.hashCode() % 100000);
                        }
                        i = i2 + 1;
                    }
                }
            }
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static String m1364b(Context context, String str) {
        try {
            return m1360a(context.getPackageManager().getPackageInfo(str, 4096).requestedPermissions);
        } catch (PackageManager.NameNotFoundException e) {
            AbstractC0407b.m75d(e.toString());
            return "";
        }
    }

    /* renamed from: b */
    public static boolean m1365b(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    /* renamed from: c */
    public static String m1366c(Context context, String str) {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo != null && (applicationInfo = packageInfo.applicationInfo) != null) {
                return packageManager.getApplicationLabel(applicationInfo).toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            AbstractC0407b.m72a(e);
        }
        return str;
    }

    /* renamed from: c */
    public static boolean m1367c(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
