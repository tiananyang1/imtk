package com.xiaomi.push;

import com.sensorsdata.analytics.android.sdk.util.DateFormatUtils;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.xiaomi.push.bq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bq.class */
public final class C0528bq {

    /* renamed from: a */
    private static String f517a;

    /* renamed from: b */
    private static String f518b;

    /* renamed from: c */
    private static String f519c;

    /* renamed from: a */
    public static int m628a() {
        String m625a = C0527bp.m625a();
        if (m625a == null) {
            return -1;
        }
        int length = m625a.length();
        if (m625a.isEmpty() || length <= 1) {
            return -1;
        }
        try {
            return Integer.parseInt(m625a.substring(0, 3));
        } catch (Exception e) {
            return -1;
        }
    }

    /* renamed from: a */
    public static String m629a() {
        String str = f517a;
        if (str != null) {
            return str;
        }
        String m634b = m634b(C0527bp.m627b());
        if (m634b == null) {
            return m634b(C0526bo.m624a("ro.ril.miui.imei", ""));
        }
        f517a = m634b;
        return f517a;
    }

    /* renamed from: a */
    public static String m630a(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m631a(int i) {
        return i == 1;
    }

    /* renamed from: b */
    public static int m632b() {
        String m625a = C0527bp.m625a();
        if (m625a == null) {
            return -1;
        }
        int length = m625a.length();
        if (m625a.isEmpty() || length <= 1) {
            return -1;
        }
        try {
            return Integer.parseInt(m625a.substring(3));
        } catch (Exception e) {
            return -1;
        }
    }

    /* renamed from: b */
    public static String m633b() {
        String str = f518b;
        if (str != null && !str.isEmpty()) {
            return f518b;
        }
        f518b = C0526bo.m624a("ro.product.model", "");
        f518b = f518b.replaceAll(" ", "");
        return f518b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:            if (r5.endsWith(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP) != false) goto L10;     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m634b(java.lang.String r5) {
        /*
            r0 = r5
            if (r0 == 0) goto L40
            r0 = r5
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L40
            r0 = r5
            java.lang.String r1 = ","
            boolean r0 = r0.startsWith(r1)
            if (r0 != 0) goto L1f
            r0 = r5
            r8 = r0
            r0 = r5
            java.lang.String r1 = ","
            boolean r0 = r0.endsWith(r1)
            if (r0 == 0) goto L28
        L1f:
            r0 = r5
            java.lang.String r1 = ","
            java.lang.String r2 = ""
            java.lang.String r0 = r0.replace(r1, r2)
            r8 = r0
        L28:
            r0 = r8
            java.lang.String r1 = "0"
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L3e
            r0 = r8
            long r0 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Exception -> L42
            r6 = r0
            r0 = r6
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L3e
            r0 = 0
            return r0
        L3e:
            r0 = r8
            return r0
        L40:
            r0 = 0
            return r0
        L42:
            r5 = move-exception
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0528bq.m634b(java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    public static String m635c() {
        String str = f519c;
        if (str != null && !str.isEmpty()) {
            return f519c;
        }
        f519c = C0526bo.m624a("ro.build.version.incremental", "");
        return f519c;
    }

    /* renamed from: d */
    public static String m636d() {
        return !C0524bm.m618a() ? C0524bm.m617a() : !C0526bo.m624a("ro.product.locale.region", "CN").equals("CN") ? "global" : C0524bm.m619b() ? "alpha" : C0524bm.m620c() ? "dev" : C0524bm.m621d() ? "stable" : "alpha";
    }

    /* renamed from: e */
    public static String m637e() {
        try {
            return C0522bk.m590a().getPackageManager().getPackageInfo(C0522bk.m590a().getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: f */
    public static String m638f() {
        return new SimpleDateFormat(DateFormatUtils.YYYY_MM_DD).format(new Date());
    }
}
