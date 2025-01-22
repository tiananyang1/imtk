package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.C0486ab;
import com.xiaomi.push.C0650gd;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.cd */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/cd.class */
public class C0852cd extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2636a;

    /* renamed from: a */
    final /* synthetic */ String f2637a;

    /* renamed from: a */
    final /* synthetic */ byte[] f2638a;

    /* renamed from: b */
    final /* synthetic */ int f2639b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0852cd(XMPushService xMPushService, int i, int i2, byte[] bArr, String str) {
        super(i);
        this.f2636a = xMPushService;
        this.f2639b = i2;
        this.f2638a = bArr;
        this.f2637a = str;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "clear account cache.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        C0650gd c0650gd;
        C0871t.m2799a((Context) this.f2636a);
        C0814at.m2578a().m2590a("5");
        C0486ab.m425a(this.f2639b);
        c0650gd = this.f2636a.f2394a;
        c0650gd.m1421c(C0650gd.m1411a());
        this.f2636a.m2475a(this.f2638a, this.f2637a);
    }
}
