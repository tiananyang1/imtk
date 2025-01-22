package com.xiaomi.push;

/* renamed from: com.xiaomi.push.kj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/kj.class */
public class C0764kj extends AbstractC0766kl {

    /* renamed from: a */
    private int f2350a;

    /* renamed from: a */
    private C0747jt f2351a;

    public C0764kj(int i) {
        this.f2351a = new C0747jt(i);
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public int mo2386a(byte[] bArr, int i, int i2) {
        byte[] m2334a = this.f2351a.m2334a();
        int i3 = i2;
        if (i2 > this.f2351a.m2333a() - this.f2350a) {
            i3 = this.f2351a.m2333a() - this.f2350a;
        }
        if (i3 > 0) {
            System.arraycopy(m2334a, this.f2350a, bArr, i, i3);
            this.f2350a += i3;
        }
        return i3;
    }

    @Override // com.xiaomi.push.AbstractC0766kl
    /* renamed from: a */
    public void mo2387a(byte[] bArr, int i, int i2) {
        this.f2351a.write(bArr, i, i2);
    }

    /* renamed from: a_ */
    public int m2388a_() {
        return this.f2351a.size();
    }
}
