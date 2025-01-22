package com.xiaomi.push;

import com.xiaomi.push.C0751jx;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.push.kh */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/kh.class */
public class C0762kh extends C0751jx {

    /* renamed from: b */
    private static int f2343b = 10000;

    /* renamed from: c */
    private static int f2344c = 10000;

    /* renamed from: d */
    private static int f2345d = 10000;

    /* renamed from: e */
    private static int f2346e = 10485760;

    /* renamed from: f */
    private static int f2347f = 104857600;

    /* renamed from: com.xiaomi.push.kh$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/kh$a.class */
    public static class a extends C0751jx.a {
        public a() {
            super(false, true);
        }

        public a(boolean z, boolean z2, int i) {
            super(z, z2, i);
        }

        @Override // com.xiaomi.push.C0751jx.a, com.xiaomi.push.InterfaceC0758kd
        /* renamed from: a */
        public AbstractC0756kb mo2378a(AbstractC0766kl abstractC0766kl) {
            C0762kh c0762kh = new C0762kh(abstractC0766kl, this.f2322a, this.f2323b);
            if (this.f2321a != 0) {
                c0762kh.m2367b(this.f2321a);
            }
            return c0762kh;
        }
    }

    public C0762kh(AbstractC0766kl abstractC0766kl, boolean z, boolean z2) {
        super(abstractC0766kl, z, z2);
    }

    @Override // com.xiaomi.push.C0751jx, com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public C0753jz mo2343a() {
        byte a2 = mo2343a();
        int a3 = mo2343a();
        if (a3 <= f2344c) {
            return new C0753jz(a2, a3);
        }
        throw new C0757kc(3, "Thrift list size " + a3 + " out of range!");
    }

    @Override // com.xiaomi.push.C0751jx, com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public C0755ka mo2344a() {
        byte a2 = mo2343a();
        byte a3 = mo2343a();
        int a4 = mo2343a();
        if (a4 <= f2343b) {
            return new C0755ka(a2, a3, a4);
        }
        throw new C0757kc(3, "Thrift map size " + a4 + " out of range!");
    }

    @Override // com.xiaomi.push.C0751jx, com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public C0760kf mo2345a() {
        byte a2 = mo2343a();
        int a3 = mo2343a();
        if (a3 <= f2345d) {
            return new C0760kf(a2, a3);
        }
        throw new C0757kc(3, "Thrift set size " + a3 + " out of range!");
    }

    @Override // com.xiaomi.push.C0751jx, com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public String mo2347a() {
        int a2 = mo2343a();
        if (a2 > f2346e) {
            throw new C0757kc(3, "Thrift string size " + a2 + " out of range!");
        }
        if (this.f2337a.mo2393b() < a2) {
            return m2348a(a2);
        }
        try {
            String str = new String(this.f2337a.mo2392a(), this.f2337a.mo2389a(), a2, "UTF-8");
            this.f2337a.mo2390a(a2);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new C0749jv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.C0751jx, com.xiaomi.push.AbstractC0756kb
    /* renamed from: a */
    public ByteBuffer mo2349a() {
        int a2 = mo2343a();
        if (a2 > f2347f) {
            throw new C0757kc(3, "Thrift binary size " + a2 + " out of range!");
        }
        m2369c(a2);
        if (this.f2337a.mo2393b() >= a2) {
            ByteBuffer wrap = ByteBuffer.wrap(this.f2337a.mo2392a(), this.f2337a.mo2389a(), a2);
            this.f2337a.mo2390a(a2);
            return wrap;
        }
        byte[] bArr = new byte[a2];
        this.f2337a.m2395b(bArr, 0, a2);
        return ByteBuffer.wrap(bArr);
    }
}
