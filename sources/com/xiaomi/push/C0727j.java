package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: com.xiaomi.push.j */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/j.class */
public class C0727j {

    /* renamed from: a */
    private static String f1960a;

    /* renamed from: a */
    private static volatile boolean f1961a = false;

    /* renamed from: b */
    private static String f1962b = "";

    /* renamed from: c */
    private static String f1963c;

    /* renamed from: d */
    private static String f1964d;

    /* renamed from: e */
    private static String f1965e;

    @TargetApi(17)
    /* renamed from: a */
    public static int m1979a() {
        Object m497a;
        if (Build.VERSION.SDK_INT >= 17 && (m497a = C0504at.m497a("android.os.UserHandle", "myUserId", new Object[0])) != null) {
            return ((Integer) Integer.class.cast(m497a)).intValue();
        }
        return -1;
    }

    /* renamed from: a */
    public static String m1980a() {
        String str = null;
        if (Build.VERSION.SDK_INT > 8 && Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            str = (String) C0504at.m497a("android.os.Build", "getSerial", (Object[]) null);
        }
        return str;
    }

    /* renamed from: a */
    public static String m1981a(Context context) {
        return "a-" + C0509ay.m530b(((String) null) + m1992e(context) + ((String) null));
    }

    /* renamed from: a */
    public static String m1982a(Context context, boolean z) {
        if (f1963c == null) {
            String m1993f = !C0770n.m2408d() ? z ? m1993f(context) : m2001n(context) : "";
            String m1992e = m1992e(context);
            String m1980a = m1980a();
            StringBuilder sb = new StringBuilder();
            sb.append("a-");
            sb.append(C0509ay.m530b(m1993f + m1992e + m1980a));
            f1963c = sb.toString();
        }
        return f1963c;
    }

    /* renamed from: a */
    public static ArrayList<String> m1983a(Context context) {
        m1994g(context);
        m1996i(context);
        if (TextUtils.isEmpty(f1960a)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(f1960a);
        if (TextUtils.isEmpty(f1962b)) {
            return arrayList;
        }
        String[] split = f1962b.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return arrayList;
            }
            arrayList.add(split[i2]);
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static void m1984a(Context context, String str) {
        AbstractC0407b.m74c("update vdevid = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f1965e = str;
        C0881w c0881w = null;
        C0881w c0881w2 = null;
        C0881w c0881w3 = null;
        try {
            try {
                if (m1985a(context)) {
                    File file = new File(Environment.getExternalStorageDirectory(), "/Xiaomi/");
                    if (file.exists() && file.isFile()) {
                        file.delete();
                    }
                    File file2 = new File(file, ".vdevid");
                    c0881w = C0881w.m2854a(context, file2);
                    C0883y.m2859a(file2);
                    C0883y.m2861a(file2, f1965e);
                }
                c0881w2 = c0881w;
                c0881w3 = c0881w;
                C0883y.m2861a(new File(context.getFilesDir(), ".vdevid"), f1965e);
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                C0881w c0881w4 = c0881w3;
                sb.append("update vdevid failure :");
                C0881w c0881w5 = c0881w3;
                sb.append(e.getMessage());
                C0881w c0881w6 = c0881w3;
                AbstractC0407b.m70a(sb.toString());
                if (c0881w3 == null) {
                    return;
                }
            }
            if (c0881w != null) {
                c0881w3 = c0881w;
                c0881w3.m2855a();
            }
        } catch (Throwable th) {
            if (c0881w2 != null) {
                c0881w2.m2855a();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static boolean m1985a(Context context) {
        boolean z = false;
        if (!C0771o.m2409a(context, "android.permission.WRITE_EXTERNAL_STORAGE") || C0770n.m2403a()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            z = true;
        }
        return !z ? C0880v.m2852a(context) : z;
    }

    /* renamed from: a */
    private static boolean m1986a(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= 15 && str.length() >= 14 && C0509ay.m531b(str) && !C0509ay.m533c(str);
    }

    /* renamed from: b */
    public static String m1987b(Context context) {
        try {
            return C0754k.m2379a(context).m2380a();
        } catch (Exception e) {
            AbstractC0407b.m70a("failure to get gaid:" + e.getMessage());
            return null;
        }
    }

    /* renamed from: b */
    private static boolean m1988b(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0091 A[RETURN] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m1989b(java.lang.String r4) {
        /*
            r0 = r4
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L9
            r0 = 0
            return r0
        L9:
            r0 = r4
            int r0 = r0.length()
            r1 = 17
            if (r0 == r1) goto L14
            r0 = 0
            return r0
        L14:
            java.lang.String r0 = "^([A-Fa-f0-9]{2}[-,:]){5}[A-Fa-f0-9]{2}$"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            r1 = r4
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r0 = r0.matches()
            if (r0 != 0) goto L25
            r0 = 0
            return r0
        L25:
            r0 = r4
            r1 = 0
            char r0 = r0.charAt(r1)
            r8 = r0
            r0 = r8
            r1 = 48
            if (r0 == r1) goto L41
            r0 = r8
            r1 = 102(0x66, float:1.43E-43)
            if (r0 == r1) goto L41
            r0 = r8
            r1 = 70
            if (r0 != r1) goto L89
        L41:
            r0 = 1
            r5 = r0
        L43:
            r0 = r5
            r1 = r4
            int r1 = r1.length()
            if (r0 >= r1) goto L89
            r0 = r4
            r1 = r5
            char r0 = r0.charAt(r1)
            r1 = r8
            if (r0 == r1) goto L5a
            r0 = 0
            r5 = r0
            goto L8b
        L5a:
            r0 = r5
            r6 = r0
            r0 = r5
            r1 = r4
            int r1 = r1.length()
            r2 = 1
            int r1 = r1 - r2
            if (r0 >= r1) goto L82
            r0 = r5
            r1 = 1
            int r0 = r0 + r1
            r7 = r0
            r0 = r4
            r1 = r7
            char r0 = r0.charAt(r1)
            r1 = 45
            if (r0 == r1) goto L80
            r0 = r5
            r6 = r0
            r0 = r4
            r1 = r7
            char r0 = r0.charAt(r1)
            r1 = 58
            if (r0 != r1) goto L82
        L80:
            r0 = r7
            r6 = r0
        L82:
            r0 = r6
            r1 = 1
            int r0 = r0 + r1
            r5 = r0
            goto L43
        L89:
            r0 = 1
            r5 = r0
        L8b:
            r0 = r5
            if (r0 == 0) goto L91
            r0 = 0
            return r0
        L91:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0727j.m1989b(java.lang.String):boolean");
    }

    /* renamed from: c */
    public static String m1990c(Context context) {
        if (!m1985a(context)) {
            return null;
        }
        if (!TextUtils.isEmpty(f1965e)) {
            return f1965e;
        }
        f1965e = C0883y.m2857a(new File(context.getFilesDir(), ".vdevid"));
        if (!TextUtils.isEmpty(f1965e)) {
            return f1965e;
        }
        C0881w c0881w = null;
        C0881w c0881w2 = null;
        try {
            try {
                File file = new File(new File(Environment.getExternalStorageDirectory(), "/Xiaomi/"), ".vdevid");
                C0881w m2854a = C0881w.m2854a(context, file);
                f1965e = "";
                String m2857a = C0883y.m2857a(file);
                if (m2857a != null) {
                    f1965e = m2857a;
                }
                c0881w = m2854a;
                c0881w2 = m2854a;
                String str = f1965e;
                if (m2854a != null) {
                    m2854a.m2855a();
                }
                return str;
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                C0881w c0881w3 = c0881w2;
                sb.append("getVDevID failure :");
                C0881w c0881w4 = c0881w2;
                sb.append(e.getMessage());
                c0881w = c0881w2;
                AbstractC0407b.m70a(sb.toString());
                if (c0881w2 != null) {
                    c0881w2.m2855a();
                }
                return f1965e;
            }
        } catch (Throwable th) {
            if (c0881w != null) {
                c0881w.m2855a();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012d  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m1991d(android.content.Context r7) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0727j.m1991d(android.content.Context):java.lang.String");
    }

    /* renamed from: e */
    public static String m1992e(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            AbstractC0407b.m72a(th);
            return null;
        }
    }

    /* renamed from: f */
    public static String m1993f(Context context) {
        String m1994g = m1994g(context);
        int i = 10;
        while (true) {
            int i2 = i;
            if (m1994g != null || i2 <= 0) {
                break;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
            }
            m1994g = m1994g(context);
            i = i2 - 1;
        }
        return m1994g;
    }

    /* renamed from: g */
    public static String m1994g(Context context) {
        Object m495a;
        Object m497a;
        Object m495a2;
        if (C0770n.m2408d()) {
            return "";
        }
        String str = f1960a;
        if (str != null) {
            return str;
        }
        try {
            String str2 = (!C0770n.m2403a() || (m497a = C0504at.m497a("miui.telephony.TelephonyManager", "getDefault", new Object[0])) == null || (m495a2 = C0504at.m495a(m497a, "getMiuiDeviceId", new Object[0])) == null || !(m495a2 instanceof String)) ? null : (String) String.class.cast(m495a2);
            String str3 = str2;
            if (str2 == null) {
                str3 = str2;
                if (m1988b(context)) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (Build.VERSION.SDK_INT < 26) {
                        str3 = telephonyManager.getDeviceId();
                    } else {
                        if (1 == telephonyManager.getPhoneType()) {
                            m495a = C0504at.m495a(telephonyManager, "getImei", (Object[]) null);
                        } else {
                            str3 = str2;
                            if (2 == telephonyManager.getPhoneType()) {
                                m495a = C0504at.m495a(telephonyManager, "getMeid", (Object[]) null);
                            }
                        }
                        str3 = (String) m495a;
                    }
                }
            }
            if (!m1986a(str3)) {
                return "";
            }
            f1960a = str3;
            return str3;
        } catch (Throwable th) {
            AbstractC0407b.m72a(th);
            return null;
        }
    }

    /* renamed from: h */
    public static String m1995h(Context context) {
        String m1997j = m1997j(context);
        int i = 10;
        while (true) {
            int i2 = i;
            if (m1997j != null || i2 <= 0) {
                break;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
            }
            m1997j = m1997j(context);
            i = i2 - 1;
        }
        return m1997j;
    }

    /* renamed from: i */
    public static String m1996i(Context context) {
        Object m495a;
        if (C0770n.m2408d() || Build.VERSION.SDK_INT < 22) {
            return "";
        }
        if (!TextUtils.isEmpty(f1962b)) {
            return f1962b;
        }
        if (!m1988b(context)) {
            return "";
        }
        m1994g(context);
        if (TextUtils.isEmpty(f1960a)) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Integer num = (Integer) C0504at.m495a(telephonyManager, "getPhoneCount", new Object[0]);
            if (num == null || num.intValue() <= 1) {
                return "";
            }
            String str = null;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= num.intValue()) {
                    break;
                }
                if (Build.VERSION.SDK_INT < 26) {
                    m495a = C0504at.m495a(telephonyManager, "getDeviceId", Integer.valueOf(i2));
                } else if (1 == telephonyManager.getPhoneType()) {
                    m495a = C0504at.m495a(telephonyManager, "getImei", Integer.valueOf(i2));
                } else {
                    if (2 == telephonyManager.getPhoneType()) {
                        m495a = C0504at.m495a(telephonyManager, "getMeid", Integer.valueOf(i2));
                    }
                    if (!TextUtils.isEmpty(str) && !TextUtils.equals(f1960a, str) && m1986a(str)) {
                        f1962b += str + Constants.ACCEPT_TIME_SEPARATOR_SP;
                    }
                    i = i2 + 1;
                }
                str = (String) m495a;
                if (!TextUtils.isEmpty(str)) {
                    f1962b += str + Constants.ACCEPT_TIME_SEPARATOR_SP;
                }
                i = i2 + 1;
            }
            int length = f1962b.length();
            if (length > 0) {
                f1962b = f1962b.substring(0, length - 1);
            }
            return f1962b;
        } catch (Exception e) {
            AbstractC0407b.m75d(e.toString());
            return "";
        }
    }

    /* renamed from: j */
    public static String m1997j(Context context) {
        m1996i(context);
        if (TextUtils.isEmpty(f1962b)) {
            return "";
        }
        String[] split = f1962b.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length = split.length;
        String str = "";
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                break;
            }
            String str2 = split[i2];
            if (m1986a(str2)) {
                str = str + C0509ay.m521a(str2) + Constants.ACCEPT_TIME_SEPARATOR_SP;
            }
            i = i2 + 1;
        }
        int length2 = str.length();
        String str3 = str;
        if (length2 > 0) {
            str3 = str.substring(0, length2 - 1);
        }
        return str3;
    }

    /* renamed from: k */
    public static String m1998k(Context context) {
        synchronized (C0727j.class) {
            try {
                if (f1964d != null) {
                    return f1964d;
                }
                f1964d = C0509ay.m530b(m1992e(context) + m1980a());
                return f1964d;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: l */
    public static String m1999l(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    /* renamed from: m */
    public static String m2000m(Context context) {
        if (C0770n.m2408d()) {
            return "";
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return m1989b(macAddress) ? C0509ay.m532c(macAddress) : "";
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return null;
        }
    }

    /* renamed from: n */
    private static String m2001n(Context context) {
        String m1994g = m1994g(context);
        int i = 10;
        while (true) {
            int i2 = i;
            if (!TextUtils.isEmpty(m1994g) || i2 <= 0) {
                break;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
            }
            m1994g = m1994g(context);
            i = i2 - 1;
        }
        return m1994g;
    }
}
