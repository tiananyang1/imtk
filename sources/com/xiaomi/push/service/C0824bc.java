package com.xiaomi.push.service;

import com.sun.jna.Function;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0506av;

/* renamed from: com.xiaomi.push.service.bc */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bc.class */
public class C0824bc {

    /* renamed from: a */
    private static int f2573a = 8;

    /* renamed from: d */
    private int f2577d = -666;

    /* renamed from: a */
    private byte[] f2574a = new byte[Function.MAX_NARGS];

    /* renamed from: c */
    private int f2576c = 0;

    /* renamed from: b */
    private int f2575b = 0;

    /* renamed from: a */
    public static int m2617a(byte b) {
        return b >= 0 ? b : b + Function.MAX_NARGS;
    }

    /* renamed from: a */
    private void m2618a() {
        this.f2576c = 0;
        this.f2575b = 0;
    }

    /* renamed from: a */
    private void m2619a(int i, byte[] bArr, boolean z) {
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 256) {
                break;
            }
            this.f2574a[i3] = (byte) i3;
            i2 = i3 + 1;
        }
        this.f2576c = 0;
        this.f2575b = 0;
        while (true) {
            int i4 = this.f2575b;
            if (i4 >= i) {
                break;
            }
            this.f2576c = ((this.f2576c + m2617a(this.f2574a[i4])) + m2617a(bArr[this.f2575b % length])) % Function.MAX_NARGS;
            m2621a(this.f2574a, this.f2575b, this.f2576c);
            this.f2575b++;
        }
        if (i != 256) {
            this.f2577d = ((this.f2576c + m2617a(this.f2574a[i])) + m2617a(bArr[i % length])) % Function.MAX_NARGS;
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("S_");
            int i5 = i - 1;
            sb.append(i5);
            sb.append(Constants.COLON_SEPARATOR);
            int i6 = 0;
            while (true) {
                int i7 = i6;
                if (i7 > i) {
                    break;
                }
                sb.append(" ");
                sb.append(m2617a(this.f2574a[i7]));
                i6 = i7 + 1;
            }
            sb.append("   j_");
            sb.append(i5);
            sb.append("=");
            sb.append(this.f2576c);
            sb.append("   j_");
            sb.append(i);
            sb.append("=");
            sb.append(this.f2577d);
            sb.append("   S_");
            sb.append(i5);
            sb.append("[j_");
            sb.append(i5);
            sb.append("]=");
            sb.append(m2617a(this.f2574a[this.f2576c]));
            sb.append("   S_");
            sb.append(i5);
            sb.append("[j_");
            sb.append(i);
            sb.append("]=");
            sb.append(m2617a(this.f2574a[this.f2577d]));
            if (this.f2574a[1] != 0) {
                sb.append("   S[1]!=0");
            }
            AbstractC0407b.m70a(sb.toString());
        }
    }

    /* renamed from: a */
    private void m2620a(byte[] bArr) {
        m2619a(Function.MAX_NARGS, bArr, false);
    }

    /* renamed from: a */
    private static void m2621a(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    /* renamed from: a */
    public static byte[] m2622a(String str, String str2) {
        byte[] m510a = C0506av.m510a(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[m510a.length + 1 + bytes.length];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= m510a.length) {
                break;
            }
            bArr[i2] = m510a[i2];
            i = i2 + 1;
        }
        bArr[m510a.length] = 95;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= bytes.length) {
                return bArr;
            }
            bArr[m510a.length + 1 + i4] = bytes[i4];
            i3 = i4 + 1;
        }
    }

    /* renamed from: a */
    public static byte[] m2623a(byte[] bArr, String str) {
        return m2624a(bArr, C0506av.m510a(str));
    }

    /* renamed from: a */
    public static byte[] m2624a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        C0824bc c0824bc = new C0824bc();
        c0824bc.m2620a(bArr);
        c0824bc.m2618a();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bArr2.length) {
                return bArr3;
            }
            bArr3[i2] = (byte) (bArr2[i2] ^ c0824bc.m2626a());
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public static byte[] m2625a(byte[] bArr, byte[] bArr2, boolean z, int i, int i2) {
        byte[] bArr3;
        int i3;
        if (i < 0 || i > bArr2.length || i + i2 > bArr2.length) {
            throw new IllegalArgumentException("start = " + i + " len = " + i2);
        }
        if (z) {
            bArr3 = bArr2;
            i3 = i;
        } else {
            bArr3 = new byte[i2];
            i3 = 0;
        }
        C0824bc c0824bc = new C0824bc();
        c0824bc.m2620a(bArr);
        c0824bc.m2618a();
        for (int i4 = 0; i4 < i2; i4++) {
            bArr3[i3 + i4] = (byte) (bArr2[i + i4] ^ c0824bc.m2626a());
        }
        return bArr3;
    }

    /* renamed from: a */
    byte m2626a() {
        this.f2575b = (this.f2575b + 1) % Function.MAX_NARGS;
        this.f2576c = (this.f2576c + m2617a(this.f2574a[this.f2575b])) % Function.MAX_NARGS;
        m2621a(this.f2574a, this.f2575b, this.f2576c);
        byte[] bArr = this.f2574a;
        return bArr[(m2617a(bArr[this.f2575b]) + m2617a(this.f2574a[this.f2576c])) % Function.MAX_NARGS];
    }
}
