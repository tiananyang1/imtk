package com.xiaomi.push;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* renamed from: com.xiaomi.push.c */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/c.class */
public final class C0538c {

    /* renamed from: a */
    private final int f536a;

    /* renamed from: a */
    private final OutputStream f537a;

    /* renamed from: a */
    private final byte[] f538a;

    /* renamed from: b */
    private int f539b;

    /* renamed from: com.xiaomi.push.c$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/c$a.class */
    public static class a extends IOException {
        a() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private C0538c(OutputStream outputStream, byte[] bArr) {
        this.f537a = outputStream;
        this.f538a = bArr;
        this.f539b = 0;
        this.f536a = bArr.length;
    }

    private C0538c(byte[] bArr, int i, int i2) {
        this.f537a = null;
        this.f538a = bArr;
        this.f539b = i;
        this.f536a = i + i2;
    }

    /* renamed from: a */
    public static int m658a(int i) {
        if (i >= 0) {
            return m680d(i);
        }
        return 10;
    }

    /* renamed from: a */
    public static int m659a(int i, int i2) {
        return m677c(i) + m658a(i2);
    }

    /* renamed from: a */
    public static int m660a(int i, long j) {
        return m677c(i) + m665a(j);
    }

    /* renamed from: a */
    public static int m661a(int i, C0484a c0484a) {
        return m677c(i) + m666a(c0484a);
    }

    /* renamed from: a */
    public static int m662a(int i, AbstractC0592e abstractC0592e) {
        return m677c(i) + m667a(abstractC0592e);
    }

    /* renamed from: a */
    public static int m663a(int i, String str) {
        return m677c(i) + m668a(str);
    }

    /* renamed from: a */
    public static int m664a(int i, boolean z) {
        return m677c(i) + m669a(z);
    }

    /* renamed from: a */
    public static int m665a(long j) {
        return m678c(j);
    }

    /* renamed from: a */
    public static int m666a(C0484a c0484a) {
        return m680d(c0484a.m417a()) + c0484a.m417a();
    }

    /* renamed from: a */
    public static int m667a(AbstractC0592e abstractC0592e) {
        int mo967b = abstractC0592e.mo967b();
        return m680d(mo967b) + mo967b;
    }

    /* renamed from: a */
    public static int m668a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return m680d(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    /* renamed from: a */
    public static int m669a(boolean z) {
        return 1;
    }

    /* renamed from: a */
    public static C0538c m670a(OutputStream outputStream) {
        return m671a(outputStream, 4096);
    }

    /* renamed from: a */
    public static C0538c m671a(OutputStream outputStream, int i) {
        return new C0538c(outputStream, new byte[i]);
    }

    /* renamed from: a */
    public static C0538c m672a(byte[] bArr, int i, int i2) {
        return new C0538c(bArr, i, i2);
    }

    /* renamed from: b */
    public static int m673b(int i) {
        return m680d(i);
    }

    /* renamed from: b */
    public static int m674b(int i, int i2) {
        return m677c(i) + m673b(i2);
    }

    /* renamed from: b */
    public static int m675b(int i, long j) {
        return m677c(i) + m676b(j);
    }

    /* renamed from: b */
    public static int m676b(long j) {
        return m678c(j);
    }

    /* renamed from: c */
    public static int m677c(int i) {
        return m680d(C0619f.m1218a(i, 0));
    }

    /* renamed from: c */
    public static int m678c(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    /* renamed from: c */
    private void m679c() {
        OutputStream outputStream = this.f537a;
        if (outputStream == null) {
            throw new a();
        }
        outputStream.write(this.f538a, 0, this.f539b);
        this.f539b = 0;
    }

    /* renamed from: d */
    public static int m680d(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    /* renamed from: a */
    public int m681a() {
        if (this.f537a == null) {
            return this.f536a - this.f539b;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    /* renamed from: a */
    public void m682a() {
        if (this.f537a != null) {
            m679c();
        }
    }

    /* renamed from: a */
    public void m683a(byte b) {
        if (this.f539b == this.f536a) {
            m679c();
        }
        byte[] bArr = this.f538a;
        int i = this.f539b;
        this.f539b = i + 1;
        bArr[i] = b;
    }

    /* renamed from: a */
    public void m684a(int i) {
        if (i >= 0) {
            m706d(i);
        } else {
            m705c(i);
        }
    }

    /* renamed from: a */
    public void m685a(int i, int i2) {
        m704c(i, 0);
        m684a(i2);
    }

    /* renamed from: a */
    public void m686a(int i, long j) {
        m704c(i, 0);
        m691a(j);
    }

    /* renamed from: a */
    public void m687a(int i, C0484a c0484a) {
        m704c(i, 2);
        m692a(c0484a);
    }

    /* renamed from: a */
    public void m688a(int i, AbstractC0592e abstractC0592e) {
        m704c(i, 2);
        m693a(abstractC0592e);
    }

    /* renamed from: a */
    public void m689a(int i, String str) {
        m704c(i, 2);
        m694a(str);
    }

    /* renamed from: a */
    public void m690a(int i, boolean z) {
        m704c(i, 0);
        m695a(z);
    }

    /* renamed from: a */
    public void m691a(long j) {
        m705c(j);
    }

    /* renamed from: a */
    public void m692a(C0484a c0484a) {
        byte[] m418a = c0484a.m418a();
        m706d(m418a.length);
        m696a(m418a);
    }

    /* renamed from: a */
    public void m693a(AbstractC0592e abstractC0592e) {
        m706d(abstractC0592e.mo959a());
        abstractC0592e.mo963a(this);
    }

    /* renamed from: a */
    public void m694a(String str) {
        byte[] bytes = str.getBytes("UTF-8");
        m706d(bytes.length);
        m696a(bytes);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    /* renamed from: a */
    public void m695a(boolean z) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    /* renamed from: a */
    public void m696a(byte[] bArr) {
        m697a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public void m697a(byte[] bArr, int i, int i2) {
        int i3 = this.f536a;
        int i4 = this.f539b;
        if (i3 - i4 >= i2) {
            System.arraycopy(bArr, i, this.f538a, i4, i2);
            this.f539b += i2;
            return;
        }
        int i5 = i3 - i4;
        System.arraycopy(bArr, i, this.f538a, i4, i5);
        int i6 = i + i5;
        int i7 = i2 - i5;
        this.f539b = this.f536a;
        m679c();
        if (i7 > this.f536a) {
            this.f537a.write(bArr, i6, i7);
        } else {
            System.arraycopy(bArr, i6, this.f538a, 0, i7);
            this.f539b = i7;
        }
    }

    /* renamed from: b */
    public void m698b() {
        if (m681a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* renamed from: b */
    public void m699b(int i) {
        m706d(i);
    }

    /* renamed from: b */
    public void m700b(int i, int i2) {
        m704c(i, 0);
        m699b(i2);
    }

    /* renamed from: b */
    public void m701b(int i, long j) {
        m704c(i, 0);
        m702b(j);
    }

    /* renamed from: b */
    public void m702b(long j) {
        m705c(j);
    }

    /* renamed from: c */
    public void m703c(int i) {
        m683a((byte) i);
    }

    /* renamed from: c */
    public void m704c(int i, int i2) {
        m706d(C0619f.m1218a(i, i2));
    }

    /* renamed from: c */
    public void m705c(long j) {
        while (((-128) & j) != 0) {
            m703c((((int) j) & 127) | 128);
            j >>>= 7;
        }
        m703c((int) j);
    }

    /* renamed from: d */
    public void m706d(int i) {
        while ((i & (-128)) != 0) {
            m703c((i & 127) | 128);
            i >>>= 7;
        }
        m703c(i);
    }
}
