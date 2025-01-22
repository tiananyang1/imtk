package com.xiaomi.push.service;

import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0688ho;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.bd */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bd.class */
public class C0825bd {

    /* renamed from: c */
    private static int f2578c = 300000;

    /* renamed from: a */
    private XMPushService f2581a;

    /* renamed from: b */
    private int f2582b = 0;

    /* renamed from: a */
    private int f2579a = 500;

    /* renamed from: a */
    private long f2580a = 0;

    public C0825bd(XMPushService xMPushService) {
        this.f2581a = xMPushService;
    }

    /* renamed from: a */
    private int m2627a() {
        if (this.f2582b > 8) {
            return 300000;
        }
        double random = (Math.random() * 2.0d) + 1.0d;
        int i = this.f2582b;
        if (i > 4) {
            return (int) (random * 60000.0d);
        }
        if (i > 1) {
            return (int) (random * 10000.0d);
        }
        if (this.f2580a == 0) {
            return 0;
        }
        if (System.currentTimeMillis() - this.f2580a >= 300000) {
            this.f2579a = PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT;
            return 0;
        }
        int i2 = this.f2579a;
        if (i2 >= f2578c) {
            return i2;
        }
        this.f2579a = (int) (i2 * 1.5d);
        return i2;
    }

    /* renamed from: a */
    public void m2628a() {
        this.f2580a = System.currentTimeMillis();
        this.f2581a.m2465a(1);
        this.f2582b = 0;
    }

    /* renamed from: a */
    public void m2629a(boolean z) {
        if (!this.f2581a.m2477a()) {
            AbstractC0407b.m74c("should not reconnect as no client or network.");
            return;
        }
        if (z) {
            if (!this.f2581a.m2478a(1)) {
                this.f2582b++;
            }
            this.f2581a.m2465a(1);
            XMPushService xMPushService = this.f2581a;
            xMPushService.getClass();
            xMPushService.m2468a(new XMPushService.C0781d());
            return;
        }
        if (this.f2581a.m2478a(1)) {
            return;
        }
        int m2627a = m2627a();
        if (!this.f2581a.m2478a(1)) {
            this.f2582b++;
        }
        AbstractC0407b.m70a("schedule reconnect in " + m2627a + "ms");
        XMPushService xMPushService2 = this.f2581a;
        xMPushService2.getClass();
        xMPushService2.m2469a(new XMPushService.C0781d(), (long) m2627a);
        if (this.f2582b == 2 && C0688ho.m1568a().m1575a()) {
            C0806al.m2548b();
        }
        if (this.f2582b == 3) {
            C0806al.m2545a();
        }
    }
}
