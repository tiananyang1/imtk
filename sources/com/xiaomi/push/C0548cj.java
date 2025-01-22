package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* renamed from: com.xiaomi.push.cj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cj.class */
class C0548cj {
    /* renamed from: a */
    public static DhcpInfo m766a(Context context) {
        if (context == null) {
            return null;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        DhcpInfo dhcpInfo = null;
        if (wifiManager != null) {
            if (!wifiManager.isWifiEnabled()) {
                return null;
            }
            dhcpInfo = null;
            try {
                if (context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) == 0) {
                    dhcpInfo = wifiManager.getDhcpInfo();
                }
            } catch (Exception e) {
                return null;
            }
        }
        return dhcpInfo;
    }

    /* renamed from: a */
    public static String m767a(Context context) {
        try {
            DhcpInfo m766a = m766a(context);
            if (m766a == null) {
                return null;
            }
            return m771a(m766a.gateway).getHostAddress();
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m768a(android.content.Context r4, int r5) {
        /*
            r0 = r4
            java.lang.String r1 = "wifi"
            java.lang.Object r0 = r0.getSystemService(r1)     // Catch: java.lang.Exception -> L5d
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0     // Catch: java.lang.Exception -> L5d
            r7 = r0
            r0 = r7
            if (r0 == 0) goto L5b
            r0 = r7
            boolean r0 = r0.isWifiEnabled()     // Catch: java.lang.Exception -> L5d
            r6 = r0
            r0 = r6
            if (r0 != 0) goto L19
            r0 = 0
            return r0
        L19:
            r0 = r4
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch: java.lang.Exception -> L60
            java.lang.String r1 = "android.permission.ACCESS_WIFI_STATE"
            r2 = r4
            java.lang.String r2 = r2.getPackageName()     // Catch: java.lang.Exception -> L60
            int r0 = r0.checkPermission(r1, r2)     // Catch: java.lang.Exception -> L60
            if (r0 != 0) goto L31
            r0 = r7
            android.net.wifi.WifiInfo r0 = r0.getConnectionInfo()     // Catch: java.lang.Exception -> L60
            r4 = r0
            goto L33
        L31:
            r0 = 0
            r4 = r0
        L33:
            r0 = r4
            if (r0 != 0) goto L39
            r0 = 0
            return r0
        L39:
            r0 = r5
            if (r0 != 0) goto L42
            r0 = r4
            java.lang.String r0 = r0.getMacAddress()     // Catch: java.lang.Exception -> L5d
            return r0
        L42:
            r0 = r5
            r1 = 1
            if (r0 != r1) goto L4c
            r0 = r4
            java.lang.String r0 = r0.getBSSID()     // Catch: java.lang.Exception -> L5d
            return r0
        L4c:
            r0 = r5
            r1 = 2
            if (r0 != r1) goto L5b
            r0 = r4
            java.lang.String r0 = r0.getSSID()     // Catch: java.lang.Exception -> L5d
            java.lang.String r0 = m770a(r0)     // Catch: java.lang.Exception -> L5d
            r4 = r0
            r0 = r4
            return r0
        L5b:
            r0 = 0
            return r0
        L5d:
            r4 = move-exception
            r0 = 0
            return r0
        L60:
            r4 = move-exception
            goto L31
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0548cj.m768a(android.content.Context, int):java.lang.String");
    }

    /* renamed from: a */
    public static String m769a(Context context, String str, String str2) {
        return context.getSharedPreferences("devicediscover", 0).getString(str, str2);
    }

    /* renamed from: a */
    private static String m770a(String str) {
        String str2 = str;
        if (str.startsWith("\"")) {
            str2 = str;
            if (str.endsWith("\"")) {
                str2 = str.substring(1, str.length() - 1);
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static InetAddress m771a(int i) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
        } catch (UnknownHostException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public static void m772a(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("devicediscover", 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    /* renamed from: b */
    public static String m773b(Context context) {
        try {
            DhcpInfo m766a = m766a(context);
            if (m766a == null) {
                return null;
            }
            return m771a(m766a.serverAddress).getHostAddress();
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0078, code lost:            if (r9 == null) goto L33;     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0071, code lost:            r9.close();     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0076, code lost:            return r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006c, code lost:            if (r9 == null) goto L33;     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m774c(android.content.Context r9) {
        /*
            r0 = r9
            r1 = 0
            java.lang.String r0 = m768a(r0, r1)
            r11 = r0
            r0 = r11
            if (r0 == 0) goto L1a
            r0 = r11
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L1a
            java.lang.String r0 = "02:00:00:00:00:00"
            r1 = r11
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L7e
        L1a:
            r0 = 0
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = 0
            r10 = r0
            r0 = 1024(0x400, float:1.435E-42)
            char[] r0 = new char[r0]     // Catch: java.lang.Throwable -> L60 java.io.FileNotFoundException -> L80 java.lang.Exception -> L87
            r14 = r0
            java.io.FileReader r0 = new java.io.FileReader     // Catch: java.lang.Throwable -> L60 java.io.FileNotFoundException -> L80 java.lang.Exception -> L87
            r1 = r0
            java.lang.String r2 = "/sys/class/net/wlan0/address"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L60 java.io.FileNotFoundException -> L80 java.lang.Exception -> L87
            r9 = r0
            java.lang.String r0 = new java.lang.String     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L60 java.io.FileNotFoundException -> L80 java.lang.Exception -> L87 java.io.FileNotFoundException -> L8d java.lang.Exception -> L91
            r1 = r0
            r2 = r14
            r3 = 0
            r4 = r9
            r5 = r14
            r6 = 0
            r7 = 1024(0x400, float:1.435E-42)
            int r4 = r4.read(r5, r6, r7)     // Catch: java.lang.Throwable -> L52 java.io.FileNotFoundException -> L8d java.lang.Exception -> L91
            r1.<init>(r2, r3, r4)     // Catch: java.lang.Throwable -> L52 java.io.FileNotFoundException -> L8d java.lang.Exception -> L91
            java.lang.String r0 = r0.trim()     // Catch: java.lang.Throwable -> L52 java.io.FileNotFoundException -> L8d java.lang.Exception -> L91
            r12 = r0
            r0 = r12
            r10 = r0
            r0 = r9
            r0.close()     // Catch: java.lang.Exception -> L95
            r0 = r12
            return r0
        L52:
            r11 = move-exception
            r0 = r9
            r10 = r0
            r0 = r11
            r9 = r0
            goto L61
        L5a:
            goto L6b
        L5d:
            goto L77
        L60:
            r9 = move-exception
        L61:
            r0 = r10
            if (r0 == 0) goto L69
            r0 = r10
            r0.close()     // Catch: java.lang.Exception -> L98
        L69:
            r0 = r9
            throw r0
        L6b:
            r0 = r9
            if (r0 == 0) goto L7e
        L6f:
            r0 = r11
            r10 = r0
            r0 = r9
            r0.close()     // Catch: java.lang.Exception -> L95
            r0 = r11
            return r0
        L77:
            r0 = r9
            if (r0 == 0) goto L7e
            goto L6f
        L7e:
            r0 = r11
            return r0
        L80:
            r9 = move-exception
            r0 = r13
            r9 = r0
            goto L77
        L87:
            r9 = move-exception
            r0 = r12
            r9 = r0
            goto L6b
        L8d:
            r10 = move-exception
            goto L5d
        L91:
            r10 = move-exception
            goto L5a
        L95:
            r9 = move-exception
            r0 = r10
            return r0
        L98:
            r10 = move-exception
            goto L69
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0548cj.m774c(android.content.Context):java.lang.String");
    }
}
