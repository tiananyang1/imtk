package com.xiaomi.push;

import java.util.Random;

/* renamed from: com.xiaomi.push.he */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/he.class */
public class C0678he {

    /* renamed from: a */
    private static final char[] f1354a = "&quot;".toCharArray();

    /* renamed from: b */
    private static final char[] f1355b = "&apos;".toCharArray();

    /* renamed from: c */
    private static final char[] f1356c = "&amp;".toCharArray();

    /* renamed from: d */
    private static final char[] f1357d = "&lt;".toCharArray();

    /* renamed from: e */
    private static final char[] f1358e = "&gt;".toCharArray();

    /* renamed from: a */
    private static Random f1353a = new Random();

    /* renamed from: f */
    private static char[] f1359f = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* renamed from: a */
    public static String m1527a(int i) {
        if (i < 1) {
            return null;
        }
        char[] cArr = new char[i];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= cArr.length) {
                return new String(cArr);
            }
            cArr[i3] = f1359f[f1353a.nextInt(71)];
            i2 = i3 + 1;
        }
    }

    /* renamed from: a */
    public static String m1528a(String str) {
        int i;
        int i2;
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        StringBuilder sb = new StringBuilder((int) (length * 1.3d));
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = i4;
            if (i3 >= length) {
                break;
            }
            char c = charArray[i3];
            if (c > '>') {
                i2 = i;
            } else if (c == '<') {
                if (i3 > i) {
                    sb.append(charArray, i, i3 - i);
                }
                i2 = i3 + 1;
                sb.append(f1357d);
            } else if (c == '>') {
                if (i3 > i) {
                    sb.append(charArray, i, i3 - i);
                }
                i2 = i3 + 1;
                sb.append(f1358e);
            } else if (c == '&') {
                if (i3 > i) {
                    sb.append(charArray, i, i3 - i);
                }
                int i5 = i3 + 5;
                if (length > i5 && charArray[i3 + 1] == '#' && Character.isDigit(charArray[i3 + 2]) && Character.isDigit(charArray[i3 + 3]) && Character.isDigit(charArray[i3 + 4])) {
                    i2 = i;
                    if (charArray[i5] == ';') {
                    }
                }
                i2 = i3 + 1;
                sb.append(f1356c);
            } else if (c == '\"') {
                if (i3 > i) {
                    sb.append(charArray, i, i3 - i);
                }
                i2 = i3 + 1;
                sb.append(f1354a);
            } else {
                i2 = i;
                if (c == '\'') {
                    if (i3 > i) {
                        sb.append(charArray, i, i3 - i);
                    }
                    i2 = i3 + 1;
                    sb.append(f1355b);
                }
            }
            i3++;
            i4 = i2;
        }
        if (i == 0) {
            return str;
        }
        if (i3 > i) {
            sb.append(charArray, i, i3 - i);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static final String m1529a(String str, String str2, String str3) {
        int i;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(str2, 0);
        String str4 = str;
        if (indexOf >= 0) {
            char[] charArray = str.toCharArray();
            char[] charArray2 = str3.toCharArray();
            int length = str2.length();
            StringBuilder sb = new StringBuilder(charArray.length);
            sb.append(charArray, 0, indexOf);
            sb.append(charArray2);
            int i2 = indexOf;
            while (true) {
                i = i2 + length;
                int indexOf2 = str.indexOf(str2, i);
                if (indexOf2 <= 0) {
                    break;
                }
                sb.append(charArray, i, indexOf2 - i);
                sb.append(charArray2);
                i2 = indexOf2;
            }
            sb.append(charArray, i, charArray.length - i);
            str4 = sb.toString();
        }
        return str4;
    }

    /* renamed from: a */
    public static String m1530a(byte[] bArr) {
        return String.valueOf(C0506av.m513a(bArr));
    }

    /* renamed from: b */
    public static final String m1531b(String str) {
        return m1529a(m1529a(m1529a(m1529a(m1529a(str, "&lt;", "<"), "&gt;", ">"), "&quot;", "\""), "&apos;", "'"), "&amp;", "&");
    }
}
