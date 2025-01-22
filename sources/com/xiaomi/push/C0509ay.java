package com.xiaomi.push;

import android.text.TextUtils;
import com.sun.jna.Function;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Pattern;

/* renamed from: com.xiaomi.push.ay */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ay.class */
public class C0509ay {
    /* renamed from: a */
    public static String m520a(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                return stringBuffer.toString();
            }
            stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(62)));
            i2 = i3 + 1;
        }
    }

    /* renamed from: a */
    public static String m521a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(m529a(str));
            return String.format("%1$032X", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0022, code lost:            if (r0 < r5) goto L10;     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m522a(java.lang.String r4, int r5) {
        /*
            r0 = r4
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto La
            java.lang.String r0 = ""
            return r0
        La:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r9 = r0
            r0 = r4
            int r0 = r0.length()
            r8 = r0
            r0 = r5
            if (r0 <= 0) goto L25
            r0 = r5
            r6 = r0
            r0 = r8
            r1 = r5
            if (r0 >= r1) goto L3d
        L25:
            r0 = r8
            r1 = 3
            int r0 = r0 / r1
            r5 = r0
            r0 = r5
            r1 = 1
            if (r0 <= r1) goto L32
            goto L34
        L32:
            r0 = 1
            r5 = r0
        L34:
            r0 = r5
            r6 = r0
            r0 = r5
            r1 = 3
            if (r0 <= r1) goto L3d
            r0 = 3
            r6 = r0
        L3d:
            r0 = 0
            r5 = r0
        L3f:
            r0 = r5
            r1 = r8
            if (r0 >= r1) goto L6a
            r0 = r5
            r1 = 1
            int r0 = r0 + r1
            r7 = r0
            r0 = r7
            r1 = r6
            int r0 = r0 % r1
            if (r0 != 0) goto L5a
            r0 = r9
            java.lang.String r1 = "*"
            java.lang.StringBuilder r0 = r0.append(r1)
            goto L65
        L5a:
            r0 = r9
            r1 = r4
            r2 = r5
            char r1 = r1.charAt(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
        L65:
            r0 = r7
            r5 = r0
            goto L3f
        L6a:
            r0 = r9
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0509ay.m522a(java.lang.String, int):java.lang.String");
    }

    /* renamed from: a */
    public static String m523a(Collection<?> collection, String str) {
        if (collection == null) {
            return null;
        }
        return m524a(collection.iterator(), str);
    }

    /* renamed from: a */
    public static String m524a(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next.toString();
        }
        StringBuffer stringBuffer = new StringBuffer(Function.MAX_NARGS);
        if (next != null) {
            stringBuffer.append(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                stringBuffer.append(str);
            }
            Object next2 = it.next();
            if (next2 != null) {
                stringBuffer.append(next2);
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m525a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(bArr);
        }
    }

    /* renamed from: a */
    public static String m526a(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return m527a(objArr, str, 0, objArr.length);
    }

    /* renamed from: a */
    public static String m527a(Object[] objArr, String str, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        String str2 = str;
        if (str == null) {
            str2 = "";
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(i3 * ((objArr[i] == null ? 16 : objArr[i].toString().length()) + str2.length()));
        int i4 = i;
        while (true) {
            int i5 = i4;
            if (i5 >= i2) {
                return stringBuffer.toString();
            }
            if (i5 > i) {
                stringBuffer.append(str2);
            }
            if (objArr[i5] != null) {
                stringBuffer.append(objArr[i5]);
            }
            i4 = i5 + 1;
        }
    }

    /* renamed from: a */
    public static boolean m528a(String str) {
        if (str == null) {
            return true;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= str.length()) {
                return true;
            }
            char charAt = str.charAt(i2);
            if (charAt < 0 || charAt > 127) {
                return false;
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static byte[] m529a(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    /* renamed from: b */
    public static String m530b(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(m529a(str));
            return String.format("%1$032X", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            return str;
        }
    }

    /* renamed from: b */
    public static boolean m531b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[A-Za-z0-9]+$").matcher(str).matches();
    }

    /* renamed from: c */
    public static String m532c(String str) {
        return TextUtils.isEmpty(str) ? "" : str.toUpperCase();
    }

    /* renamed from: c */
    public static boolean m533c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        char charAt = str.charAt(0);
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 >= str.length()) {
                return true;
            }
            if (str.charAt(i2) != charAt) {
                return false;
            }
            i = i2 + 1;
        }
    }
}
