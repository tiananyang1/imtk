package com.xiaomi.push;

import java.io.InputStream;

/* renamed from: com.xiaomi.push.b */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/b.class */
public final class C0511b {

    /* renamed from: a */
    private int f463a;

    /* renamed from: a */
    private final InputStream f464a;

    /* renamed from: a */
    private final byte[] f465a;

    /* renamed from: b */
    private int f466b;

    /* renamed from: c */
    private int f467c;

    /* renamed from: d */
    private int f468d;

    /* renamed from: e */
    private int f469e;

    /* renamed from: f */
    private int f470f;

    /* renamed from: g */
    private int f471g;

    /* renamed from: h */
    private int f472h;

    /* renamed from: i */
    private int f473i;

    private C0511b(InputStream inputStream) {
        this.f470f = Integer.MAX_VALUE;
        this.f472h = 64;
        this.f473i = 67108864;
        this.f465a = new byte[4096];
        this.f463a = 0;
        this.f467c = 0;
        this.f464a = inputStream;
    }

    private C0511b(byte[] bArr, int i, int i2) {
        this.f470f = Integer.MAX_VALUE;
        this.f472h = 64;
        this.f473i = 67108864;
        this.f465a = bArr;
        this.f463a = i2 + i;
        this.f467c = i;
        this.f464a = null;
    }

    /* renamed from: a */
    public static C0511b m534a(InputStream inputStream) {
        return new C0511b(inputStream);
    }

    /* renamed from: a */
    public static C0511b m535a(byte[] bArr, int i, int i2) {
        return new C0511b(bArr, i, i2);
    }

    /* renamed from: a */
    private boolean m536a(boolean z) {
        int i = this.f467c;
        int i2 = this.f463a;
        if (i < i2) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        }
        int i3 = this.f469e;
        if (i3 + i2 == this.f470f) {
            if (z) {
                throw C0565d.m858a();
            }
            return false;
        }
        this.f469e = i3 + i2;
        this.f467c = 0;
        InputStream inputStream = this.f464a;
        this.f463a = inputStream == null ? -1 : inputStream.read(this.f465a);
        int i4 = this.f463a;
        if (i4 == 0 || i4 < -1) {
            throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.f463a + "\nThe InputStream implementation is buggy.");
        }
        if (i4 == -1) {
            this.f463a = 0;
            if (z) {
                throw C0565d.m858a();
            }
            return false;
        }
        m537b();
        int i5 = this.f469e + this.f463a + this.f466b;
        if (i5 > this.f473i || i5 < 0) {
            throw C0565d.m865h();
        }
        return true;
    }

    /* renamed from: b */
    private void m537b() {
        this.f463a += this.f466b;
        int i = this.f469e;
        int i2 = this.f463a;
        int i3 = i + i2;
        int i4 = this.f470f;
        if (i3 <= i4) {
            this.f466b = 0;
        } else {
            this.f466b = i3 - i4;
            this.f463a = i2 - this.f466b;
        }
    }

    /* renamed from: a */
    public byte m538a() {
        if (this.f467c == this.f463a) {
            m536a(true);
        }
        byte[] bArr = this.f465a;
        int i = this.f467c;
        this.f467c = i + 1;
        return bArr[i];
    }

    /* renamed from: a */
    public int m539a() {
        if (m553b()) {
            this.f468d = 0;
            return 0;
        }
        this.f468d = m557d();
        int i = this.f468d;
        if (i != 0) {
            return i;
        }
        throw C0565d.m861d();
    }

    /* renamed from: a */
    public int m540a(int i) {
        if (i < 0) {
            throw C0565d.m859b();
        }
        int i2 = i + this.f469e + this.f467c;
        int i3 = this.f470f;
        if (i2 > i3) {
            throw C0565d.m858a();
        }
        this.f470f = i2;
        m537b();
        return i3;
    }

    /* renamed from: a */
    public long m541a() {
        return m555c();
    }

    /* renamed from: a */
    public C0484a m542a() {
        int m557d = m557d();
        int i = this.f463a;
        int i2 = this.f467c;
        if (m557d > i - i2 || m557d <= 0) {
            return C0484a.m415a(m549a(m557d));
        }
        C0484a m416a = C0484a.m416a(this.f465a, i2, m557d);
        this.f467c += m557d;
        return m416a;
    }

    /* renamed from: a */
    public String m543a() {
        int m557d = m557d();
        int i = this.f463a;
        int i2 = this.f467c;
        if (m557d > i - i2 || m557d <= 0) {
            return new String(m549a(m557d), "UTF-8");
        }
        String str = new String(this.f465a, i2, m557d, "UTF-8");
        this.f467c += m557d;
        return str;
    }

    /* renamed from: a */
    public void m544a() {
        int m539a;
        do {
            m539a = m539a();
            if (m539a == 0) {
                return;
            }
        } while (m548a(m539a));
    }

    /* renamed from: a */
    public void m545a(int i) {
        if (this.f468d != i) {
            throw C0565d.m862e();
        }
    }

    /* renamed from: a */
    public void m546a(AbstractC0592e abstractC0592e) {
        int m557d = m557d();
        if (this.f471g >= this.f472h) {
            throw C0565d.m864g();
        }
        int m540a = m540a(m557d);
        this.f471g++;
        abstractC0592e.mo960a(this);
        m545a(0);
        this.f471g--;
        m552b(m540a);
    }

    /* renamed from: a */
    public boolean m547a() {
        return m557d() != 0;
    }

    /* renamed from: a */
    public boolean m548a(int i) {
        int m1217a = C0619f.m1217a(i);
        if (m1217a == 0) {
            m550b();
            return true;
        }
        if (m1217a == 1) {
            m558d();
            return true;
        }
        if (m1217a == 2) {
            m556c(m557d());
            return true;
        }
        if (m1217a == 3) {
            m544a();
            m545a(C0619f.m1218a(C0619f.m1219b(i), 4));
            return true;
        }
        if (m1217a == 4) {
            return false;
        }
        if (m1217a != 5) {
            throw C0565d.m863f();
        }
        m559e();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0132, code lost:            r8 = r8 - r0.length;        r0.addElement(r0);     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] m549a(int r7) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0511b.m549a(int):byte[]");
    }

    /* renamed from: b */
    public int m550b() {
        return m557d();
    }

    /* renamed from: b */
    public long m551b() {
        return m555c();
    }

    /* renamed from: b */
    public void m552b(int i) {
        this.f470f = i;
        m537b();
    }

    /* renamed from: b */
    public boolean m553b() {
        boolean z = false;
        if (this.f467c == this.f463a) {
            z = false;
            if (!m536a(false)) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: c */
    public int m554c() {
        return m557d();
    }

    /* renamed from: c */
    public long m555c() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            j |= (r0 & Byte.MAX_VALUE) << i;
            if ((m538a() & 128) == 0) {
                return j;
            }
        }
        throw C0565d.m860c();
    }

    /* renamed from: c */
    public void m556c(int i) {
        if (i < 0) {
            throw C0565d.m859b();
        }
        int i2 = this.f469e;
        int i3 = this.f467c;
        int i4 = this.f470f;
        if (i2 + i3 + i > i4) {
            m556c((i4 - i2) - i3);
            throw C0565d.m858a();
        }
        int i5 = this.f463a;
        if (i <= i5 - i3) {
            this.f467c = i3 + i;
            return;
        }
        int i6 = i5 - i3;
        this.f469e = i2 + i5;
        this.f467c = 0;
        this.f463a = 0;
        while (i6 < i) {
            InputStream inputStream = this.f464a;
            int skip = inputStream == null ? -1 : (int) inputStream.skip(i - i6);
            if (skip <= 0) {
                throw C0565d.m858a();
            }
            i6 += skip;
            this.f469e += skip;
        }
    }

    /* renamed from: d */
    public int m557d() {
        int i;
        byte m538a = m538a();
        if (m538a >= 0) {
            return m538a;
        }
        int i2 = m538a & Byte.MAX_VALUE;
        byte m538a2 = m538a();
        if (m538a2 >= 0) {
            i = m538a2 << 7;
        } else {
            i2 |= (m538a2 & Byte.MAX_VALUE) << 7;
            byte m538a3 = m538a();
            if (m538a3 >= 0) {
                i = m538a3 << 14;
            } else {
                i2 |= (m538a3 & Byte.MAX_VALUE) << 14;
                byte m538a4 = m538a();
                if (m538a4 >= 0) {
                    i = m538a4 << 21;
                } else {
                    byte m538a5 = m538a();
                    int i3 = i2 | ((m538a4 & Byte.MAX_VALUE) << 21) | (m538a5 << 28);
                    if (m538a5 >= 0) {
                        return i3;
                    }
                    int i4 = 0;
                    while (true) {
                        int i5 = i4;
                        if (i5 >= 5) {
                            throw C0565d.m860c();
                        }
                        if (m538a() >= 0) {
                            return i3;
                        }
                        i4 = i5 + 1;
                    }
                }
            }
        }
        return i2 | i;
    }

    /* renamed from: d */
    public long m558d() {
        return ((m538a() & 255) << 8) | (m538a() & 255) | ((m538a() & 255) << 16) | ((m538a() & 255) << 24) | ((m538a() & 255) << 32) | ((m538a() & 255) << 40) | ((m538a() & 255) << 48) | ((m538a() & 255) << 56);
    }

    /* renamed from: e */
    public int m559e() {
        return (m538a() & 255) | ((m538a() & 255) << 8) | ((m538a() & 255) << 16) | ((m538a() & 255) << 24);
    }
}
