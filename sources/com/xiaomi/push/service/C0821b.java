package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.EnumC0696hw;
import java.lang.ref.WeakReference;

/* renamed from: com.xiaomi.push.service.b */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/b.class */
public class C0821b extends C0493ai.a {

    /* renamed from: a */
    private C0732je f2565a;

    /* renamed from: a */
    private WeakReference<XMPushService> f2566a;

    /* renamed from: a */
    private boolean f2567a;

    public C0821b(C0732je c0732je, WeakReference<XMPushService> weakReference, boolean z) {
        this.f2567a = false;
        this.f2565a = c0732je;
        this.f2566a = weakReference;
        this.f2567a = z;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 22;
    }

    @Override // java.lang.Runnable
    public void run() {
        XMPushService xMPushService;
        WeakReference<XMPushService> weakReference = this.f2566a;
        if (weakReference == null || this.f2565a == null || (xMPushService = weakReference.get()) == null) {
            return;
        }
        this.f2565a.m2058a(C0812ar.m2571a());
        this.f2565a.m2061a(false);
        AbstractC0407b.m74c("MoleInfo aw_ping : send aw_Ping msg " + this.f2565a.m2063a());
        try {
            String m2076c = this.f2565a.m2076c();
            xMPushService.m2473a(m2076c, C0743jp.m2314a(C0800af.m2495a(m2076c, this.f2565a.m2072b(), this.f2565a, EnumC0696hw.Notification)), this.f2567a);
        } catch (Exception e) {
            AbstractC0407b.m75d("MoleInfo aw_ping : send help app ping error" + e.toString());
        }
    }
}
