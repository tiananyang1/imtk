package com.xiaomi.push;

/* renamed from: com.xiaomi.push.a */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/a.class */
public final class C0484a {

    /* renamed from: a */
    public static final C0484a f400a = new C0484a(new byte[0]);

    /* renamed from: a */
    private volatile int f401a = 0;

    /* renamed from: a */
    private final byte[] f402a;

    private C0484a(byte[] bArr) {
        this.f402a = bArr;
    }

    /* renamed from: a */
    public static C0484a m415a(byte[] bArr) {
        return m416a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static C0484a m416a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new C0484a(bArr2);
    }

    /* renamed from: a */
    public int m417a() {
        return this.f402a.length;
    }

    /* renamed from: a */
    public byte[] m418a() {
        byte[] bArr = this.f402a;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0484a)) {
            return false;
        }
        byte[] bArr = this.f402a;
        int length = bArr.length;
        byte[] bArr2 = ((C0484a) obj).f402a;
        if (length != bArr2.length) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i = i2 + 1;
        }
    }

    public int hashCode() {
        int i = this.f401a;
        int i2 = i;
        if (i == 0) {
            byte[] bArr = this.f402a;
            i2 = bArr.length;
            for (byte b : bArr) {
                i2 = (i2 * 31) + b;
            }
            if (i2 == 0) {
                i2 = 1;
            }
            this.f401a = i2;
        }
        return i2;
    }
}
