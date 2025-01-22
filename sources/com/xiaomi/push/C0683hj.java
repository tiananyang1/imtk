package com.xiaomi.push;

import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.hj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hj.class */
class C0683hj implements C0814at.b.a {

    /* renamed from: a */
    private int f1376a;

    /* renamed from: a */
    private AbstractC0649gc f1377a;

    /* renamed from: a */
    private XMPushService f1378a;

    /* renamed from: a */
    private C0814at.b f1379a;

    /* renamed from: a */
    private boolean f1381a = false;

    /* renamed from: a */
    private C0814at.c f1380a = C0814at.c.binding;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0683hj(XMPushService xMPushService, C0814at.b bVar) {
        this.f1378a = xMPushService;
        this.f1379a = bVar;
    }

    /* renamed from: b */
    private void m1552b() {
        this.f1379a.m2608b(this);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:25:0x00d1
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m1553c() {
        /*
            Method dump skipped, instructions count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0683hj.m1553c():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m1554a() {
        this.f1379a.m2606a(this);
        this.f1377a = this.f1378a.m2462a();
    }

    @Override // com.xiaomi.push.service.C0814at.b.a
    /* renamed from: a */
    public void mo1555a(C0814at.c cVar, C0814at.c cVar2, int i) {
        if (!this.f1381a && cVar == C0814at.c.binding) {
            this.f1380a = cVar2;
            this.f1376a = i;
            this.f1381a = true;
        }
        this.f1378a.m2468a(new C0684hk(this, 4));
    }
}
