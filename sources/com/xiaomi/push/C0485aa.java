package com.xiaomi.push;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.io.File;

/* renamed from: com.xiaomi.push.aa */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/aa.class */
public class C0485aa {
    /* renamed from: a */
    public static long m419a() {
        File externalStorageDirectory;
        if (m421b() || (externalStorageDirectory = Environment.getExternalStorageDirectory()) == null || TextUtils.isEmpty(externalStorageDirectory.getPath())) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(externalStorageDirectory.getPath());
            return statFs.getBlockSize() * (statFs.getAvailableBlocks() - 4);
        } catch (Throwable th) {
            return 0L;
        }
    }

    /* renamed from: a */
    public static boolean m420a() {
        try {
            return Environment.getExternalStorageState().equals("removed");
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return true;
        }
    }

    /* renamed from: b */
    public static boolean m421b() {
        try {
            return true ^ Environment.getExternalStorageState().equals("mounted");
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return true;
        }
    }

    /* renamed from: c */
    public static boolean m422c() {
        return m419a() <= 102400;
    }

    /* renamed from: d */
    public static boolean m423d() {
        return (m421b() || m422c() || m420a()) ? false : true;
    }
}
