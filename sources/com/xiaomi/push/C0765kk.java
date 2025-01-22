package com.xiaomi.push;

/* renamed from: com.xiaomi.push.kk */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/kk.class */
public final class C0765kk extends AbstractC0766kl {

    /* renamed from: a */
    private int f2352a;

    /* renamed from: a */
    private byte[] f2353a;

    /* renamed from: b */
    private int f2354b;

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public int mo2389a() {
        return this.f2352a;
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public int mo2386a(byte[] bArr, int i, int i2) {
        int mo2393b = mo2393b();
        int i3 = i2;
        if (i2 > mo2393b) {
            i3 = mo2393b;
        }
        if (i3 > 0) {
            System.arraycopy(this.f2353a, this.f2352a, bArr, i, i3);
            mo2390a(i3);
        }
        return i3;
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public void mo2390a(int i) {
        this.f2352a += i;
    }

    /* renamed from: a */
    public void m2391a(byte[] bArr) {
        m2394b(bArr, 0, bArr.length);
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public void mo2387a(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public byte[] mo2392a() {
        return this.f2353a;
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: b */
    public int mo2393b() {
        return this.f2354b - this.f2352a;
    }

    /* renamed from: b */
    public void m2394b(byte[] bArr, int i, int i2) {
        this.f2353a = bArr;
        this.f2352a = i;
        this.f2354b = i + i2;
    }
}
