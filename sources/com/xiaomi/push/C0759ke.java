package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ke */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ke.class */
public class C0759ke {

    /* renamed from: a */
    private static int f2339a = Integer.MAX_VALUE;

    /* renamed from: a */
    public static void m2384a(AbstractC0756kb abstractC0756kb, byte b) {
        m2385a(abstractC0756kb, b, f2339a);
    }

    /* renamed from: a */
    public static void m2385a(AbstractC0756kb abstractC0756kb, byte b, int i) {
        if (i <= 0) {
            throw new C0749jv("Maximum skip depth exceeded");
        }
        switch (b) {
            case 2:
                abstractC0756kb.mo2365a();
                return;
            case 3:
                abstractC0756kb.mo2338a();
                return;
            case 4:
                abstractC0756kb.mo2339a();
                return;
            case 5:
            case 7:
            case 9:
            default:
                return;
            case 6:
                abstractC0756kb.mo2350a();
                return;
            case 8:
                abstractC0756kb.mo2340a();
                return;
            case 10:
                abstractC0756kb.mo2341a();
                return;
            case 11:
                abstractC0756kb.mo2349a();
                return;
            case 12:
                abstractC0756kb.mo2346a();
                while (true) {
                    C0752jy mo2342a = abstractC0756kb.mo2342a();
                    if (mo2342a.f2324a == 0) {
                        abstractC0756kb.mo2373g();
                        return;
                    } else {
                        m2385a(abstractC0756kb, mo2342a.f2324a, i - 1);
                        abstractC0756kb.mo2374h();
                    }
                }
            case 13:
                C0755ka mo2344a = abstractC0756kb.mo2344a();
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= mo2344a.f2335a) {
                        abstractC0756kb.mo2375i();
                        return;
                    }
                    byte b2 = mo2344a.f2334a;
                    int i4 = i - 1;
                    m2385a(abstractC0756kb, b2, i4);
                    m2385a(abstractC0756kb, mo2344a.f2336b, i4);
                    i2 = i3 + 1;
                }
            case 14:
                C0760kf mo2345a = abstractC0756kb.mo2345a();
                int i5 = 0;
                while (true) {
                    int i6 = i5;
                    if (i6 >= mo2345a.f2341a) {
                        abstractC0756kb.mo2377k();
                        return;
                    } else {
                        m2385a(abstractC0756kb, mo2345a.f2340a, i - 1);
                        i5 = i6 + 1;
                    }
                }
            case 15:
                C0753jz mo2343a = abstractC0756kb.mo2343a();
                int i7 = 0;
                while (true) {
                    int i8 = i7;
                    if (i8 >= mo2343a.f2328a) {
                        abstractC0756kb.mo2376j();
                        return;
                    } else {
                        m2385a(abstractC0756kb, mo2343a.f2327a, i - 1);
                        i7 = i8 + 1;
                    }
                }
        }
    }
}
