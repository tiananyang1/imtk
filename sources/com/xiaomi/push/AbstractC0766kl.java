package com.xiaomi.push;

/* renamed from: com.xiaomi.push.kl */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/kl.class */
public abstract class AbstractC0766kl {
    /* renamed from: a */
    public int mo2389a() {
        return 0;
    }

    /* renamed from: a */
    public abstract int mo2386a(byte[] bArr, int i, int i2);

    /* renamed from: a */
    public void mo2390a(int i) {
    }

    /* renamed from: a */
    public abstract void mo2387a(byte[] bArr, int i, int i2);

    /* renamed from: a */
    public byte[] mo2392a() {
        return null;
    }

    /* renamed from: b */
    public int mo2393b() {
        return -1;
    }

    /* renamed from: b */
    public int m2395b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                return i4;
            }
            int mo2386a = mo2386a(bArr, i + i4, i2 - i4);
            if (mo2386a <= 0) {
                throw new C0767km("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i4 + " bytes.");
            }
            i3 = i4 + mo2386a;
        }
    }
}
