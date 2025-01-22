package com.xiaomi.push;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.push.jx */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jx.class */
public class C0751jx extends AbstractC0756kb {

    /* renamed from: a */
    private static final C0761kg f2308a = new C0761kg();

    /* renamed from: a */
    protected int f2309a;

    /* renamed from: a */
    protected boolean f2310a;

    /* renamed from: a */
    private byte[] f2311a;

    /* renamed from: b */
    protected boolean f2312b;

    /* renamed from: b */
    private byte[] f2313b;

    /* renamed from: c */
    protected boolean f2314c;

    /* renamed from: c */
    private byte[] f2315c;

    /* renamed from: d */
    private byte[] f2316d;

    /* renamed from: e */
    private byte[] f2317e;

    /* renamed from: f */
    private byte[] f2318f;

    /* renamed from: g */
    private byte[] f2319g;

    /* renamed from: h */
    private byte[] f2320h;

    /* renamed from: com.xiaomi.push.jx$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/jx$a.class */
    public static class a implements InterfaceC0758kd {

        /* renamed from: a */
        protected int f2321a;

        /* renamed from: a */
        protected boolean f2322a;

        /* renamed from: b */
        protected boolean f2323b;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.f2322a = false;
            this.f2323b = true;
            this.f2322a = z;
            this.f2323b = z2;
            this.f2321a = i;
        }

        @Override // com.xiaomi.push.InterfaceC0758kd
        /* renamed from: a */
        public AbstractC0756kb mo2378a(AbstractC0766kl abstractC0766kl) {
            C0751jx c0751jx = new C0751jx(abstractC0766kl, this.f2322a, this.f2323b);
            int i = this.f2321a;
            if (i != 0) {
                c0751jx.m2367b(i);
            }
            return c0751jx;
        }
    }

    public C0751jx(AbstractC0766kl abstractC0766kl, boolean z, boolean z2) {
        super(abstractC0766kl);
        this.f2310a = false;
        this.f2312b = true;
        this.f2314c = false;
        this.f2311a = new byte[1];
        this.f2313b = new byte[2];
        this.f2315c = new byte[4];
        this.f2316d = new byte[8];
        this.f2317e = new byte[1];
        this.f2318f = new byte[2];
        this.f2319g = new byte[4];
        this.f2320h = new byte[8];
        this.f2310a = z;
        this.f2312b = z2;
    }

    /* renamed from: a */
    private int m2337a(byte[] bArr, int i, int i2) {
        m2369c(i2);
        return this.f2337a.m2395b(bArr, i, i2);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public byte mo2338a() {
        if (this.f2337a.mo2393b() < 1) {
            m2337a(this.f2317e, 0, 1);
            return this.f2317e[0];
        }
        byte b = this.f2337a.mo2392a()[this.f2337a.mo2389a()];
        this.f2337a.mo2390a(1);
        return b;
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public double mo2339a() {
        return Double.longBitsToDouble(mo2341a());
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public int mo2340a() {
        byte[] bArr = this.f2319g;
        int i = 0;
        if (this.f2337a.mo2393b() >= 4) {
            bArr = this.f2337a.mo2392a();
            i = this.f2337a.mo2389a();
            this.f2337a.mo2390a(4);
        } else {
            m2337a(this.f2319g, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public long mo2341a() {
        byte[] bArr = this.f2320h;
        int i = 0;
        if (this.f2337a.mo2393b() >= 8) {
            bArr = this.f2337a.mo2392a();
            i = this.f2337a.mo2389a();
            this.f2337a.mo2390a(8);
        } else {
            m2337a(this.f2320h, 0, 8);
        }
        return (bArr[i + 7] & 255) | ((bArr[i] & 255) << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public C0752jy mo2342a() {
        byte mo2338a = mo2338a();
        return new C0752jy("", mo2338a, mo2338a == 0 ? (short) 0 : mo2350a());
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public C0753jz mo2343a() {
        return new C0753jz(mo2338a(), mo2340a());
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public C0755ka mo2344a() {
        return new C0755ka(mo2338a(), mo2338a(), mo2340a());
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public C0760kf mo2345a() {
        return new C0760kf(mo2338a(), mo2340a());
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public C0761kg mo2346a() {
        return f2308a;
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public String mo2347a() {
        int mo2340a = mo2340a();
        if (this.f2337a.mo2393b() < mo2340a) {
            return m2348a(mo2340a);
        }
        try {
            String str = new String(this.f2337a.mo2392a(), this.f2337a.mo2389a(), mo2340a, "UTF-8");
            this.f2337a.mo2390a(mo2340a);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new C0749jv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    /* renamed from: a */
    public String m2348a(int i) {
        try {
            m2369c(i);
            byte[] bArr = new byte[i];
            this.f2337a.m2395b(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new C0749jv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public ByteBuffer mo2349a() {
        int mo2340a = mo2340a();
        m2369c(mo2340a);
        if (this.f2337a.mo2393b() >= mo2340a) {
            ByteBuffer wrap = ByteBuffer.wrap(this.f2337a.mo2392a(), this.f2337a.mo2389a(), mo2340a);
            this.f2337a.mo2390a(mo2340a);
            return wrap;
        }
        byte[] bArr = new byte[mo2340a];
        this.f2337a.m2395b(bArr, 0, mo2340a);
        return ByteBuffer.wrap(bArr);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public short mo2350a() {
        byte[] bArr = this.f2318f;
        int i = 0;
        if (this.f2337a.mo2393b() >= 2) {
            bArr = this.f2337a.mo2392a();
            i = this.f2337a.mo2389a();
            this.f2337a.mo2390a(2);
        } else {
            m2337a(this.f2318f, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2351a() {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2352a(byte b) {
        this.f2311a[0] = b;
        this.f2337a.mo2387a(this.f2311a, 0, 1);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2353a(double d) {
        mo2355a(Double.doubleToLongBits(d));
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2354a(int i) {
        byte[] bArr = this.f2315c;
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
        this.f2337a.mo2387a(this.f2315c, 0, 4);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2355a(long j) {
        byte[] bArr = this.f2316d;
        bArr[0] = (byte) ((j >> 56) & 255);
        bArr[1] = (byte) ((j >> 48) & 255);
        bArr[2] = (byte) ((j >> 40) & 255);
        bArr[3] = (byte) ((j >> 32) & 255);
        bArr[4] = (byte) ((j >> 24) & 255);
        bArr[5] = (byte) ((j >> 16) & 255);
        bArr[6] = (byte) ((j >> 8) & 255);
        bArr[7] = (byte) (j & 255);
        this.f2337a.mo2387a(this.f2316d, 0, 8);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2356a(C0752jy c0752jy) {
        mo2352a(c0752jy.f2324a);
        mo2363a(c0752jy.f2326a);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2357a(C0753jz c0753jz) {
        mo2352a(c0753jz.f2327a);
        mo2354a(c0753jz.f2328a);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2358a(C0755ka c0755ka) {
        mo2352a(c0755ka.f2334a);
        mo2352a(c0755ka.f2336b);
        mo2354a(c0755ka.f2335a);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2359a(C0760kf c0760kf) {
        mo2352a(c0760kf.f2340a);
        mo2354a(c0760kf.f2341a);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2360a(C0761kg c0761kg) {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2361a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            mo2354a(bytes.length);
            this.f2337a.mo2387a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new C0749jv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2362a(ByteBuffer byteBuffer) {
        int limit = (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset();
        mo2354a(limit);
        this.f2337a.mo2387a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2363a(short s) {
        byte[] bArr = this.f2313b;
        bArr[0] = (byte) ((s >> 8) & 255);
        bArr[1] = (byte) (s & 255);
        this.f2337a.mo2387a(this.f2313b, 0, 2);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public void mo2364a(boolean z) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public boolean mo2365a() {
        return mo2338a() == 1;
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: b */
    public void mo2366b() {
    }

    /* renamed from: b */
    public void m2367b(int i) {
        this.f2309a = i;
        this.f2314c = true;
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: c */
    public void mo2368c() {
        mo2352a((byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m2369c(int i) {
        if (i < 0) {
            throw new C0749jv("Negative length: " + i);
        }
        if (this.f2314c) {
            this.f2309a -= i;
            if (this.f2309a >= 0) {
                return;
            }
            throw new C0749jv("Message length exceeded: " + i);
        }
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: d */
    public void mo2370d() {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: e */
    public void mo2371e() {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: f */
    public void mo2372f() {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: g */
    public void mo2373g() {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: h */
    public void mo2374h() {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: i */
    public void mo2375i() {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: j */
    public void mo2376j() {
    }

    @Override // com.xiaomi.push.AbstractC0756kb
    /* renamed from: k */
    public void mo2377k() {
    }
}
