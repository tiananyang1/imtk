package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.manager.C0410a;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.xiaomi.push.be */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/be.class */
public class C0516be {
    /* renamed from: a */
    public static int m564a(Context context) {
        if (!C0410a.m77a(context).m84a().isPerfUploadSwitchOpen()) {
            return -1;
        }
        int perfUploadFrequency = (int) C0410a.m77a(context).m84a().getPerfUploadFrequency();
        int currentTimeMillis = (int) ((System.currentTimeMillis() - C0519bh.m575a(context).m576a("sp_client_report_status", "perf_last_upload_time", 0L)) / 1000);
        AbstractC0407b.m74c(context.getPackageName() + " start perf upload timeDiff " + currentTimeMillis);
        if (currentTimeMillis >= perfUploadFrequency - 5) {
            return 0;
        }
        return currentTimeMillis;
    }

    /* renamed from: a */
    public static String m565a() {
        return Build.VERSION.RELEASE + Constants.ACCEPT_TIME_SEPARATOR_SERVER + Build.VERSION.INCREMENTAL;
    }

    /* renamed from: a */
    public static String m566a(Context context) {
        String m577a = C0519bh.m575a(context).m577a("sp_client_report_status", "sp_client_report_key", "");
        String str = m577a;
        if (TextUtils.isEmpty(m577a)) {
            str = C0509ay.m520a(20);
            C0519bh.m575a(context).m579a("sp_client_report_status", "sp_client_report_key", str);
        }
        return str;
    }

    /* renamed from: a */
    public static void m567a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.xmsf.push.XMSF_UPLOAD_ACTIVE");
        intent.putExtra("pkgname", context.getPackageName());
        intent.putExtra("category", "category_client_report_data");
        intent.putExtra("name", "quality_support");
        intent.putExtra("data", str);
        context.sendBroadcast(intent, "com.xiaomi.xmsf.permission.USE_XMSF_UPLOAD");
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x023d, code lost:            if (r7 == null) goto L85;     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x027d  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m568a(android.content.Context r5, java.lang.String r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 718
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0516be.m568a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    public static void m569a(Context context, List<String> list) {
        if (list == null || list.size() <= 0 || !m570a(context)) {
            return;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                m567a(context, str);
            }
        }
    }

    /* renamed from: a */
    public static boolean m570a(Context context) {
        boolean z = false;
        try {
            if (StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager().getPackageInfo("com.xiaomi.xmsf", 0).versionCode >= 108) {
                z = true;
            }
            return z;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m571a(Context context, String str) {
        File file = new File(str);
        long maxFileLength = C0410a.m77a(context).m84a().getMaxFileLength();
        if (!file.exists()) {
            C0883y.m2863a(file);
            return true;
        }
        try {
            return file.length() <= maxFileLength;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return false;
        }
    }

    @TargetApi(9)
    /* renamed from: a */
    public static byte[] m572a(String str) {
        byte[] copyOf = Arrays.copyOf(C0506av.m510a(str), 16);
        copyOf[0] = 68;
        copyOf[15] = 84;
        return copyOf;
    }

    /* renamed from: a */
    public static File[] m573a(Context context, String str) {
        File externalFilesDir = context.getExternalFilesDir(str);
        if (externalFilesDir != null) {
            return externalFilesDir.listFiles(new C0518bg());
        }
        return null;
    }

    /* renamed from: b */
    public static int m574b(Context context) {
        if (!C0410a.m77a(context).m84a().isEventUploadSwitchOpen()) {
            return -1;
        }
        int eventUploadFrequency = (int) C0410a.m77a(context).m84a().getEventUploadFrequency();
        int currentTimeMillis = (int) ((System.currentTimeMillis() - C0519bh.m575a(context).m576a("sp_client_report_status", "event_last_upload_time", 0L)) / 1000);
        AbstractC0407b.m74c(context.getPackageName() + " start event upload timeDiff " + currentTimeMillis);
        if (currentTimeMillis >= eventUploadFrequency - 5) {
            return 0;
        }
        return currentTimeMillis;
    }
}
