package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0612et;
import com.xiaomi.push.C0650gd;
import com.xiaomi.push.InterfaceC0653gg;
import java.util.Map;

/* renamed from: com.xiaomi.push.service.bw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bw.class */
class C0844bw extends C0650gd {

    /* renamed from: a */
    final /* synthetic */ XMPushService f2623a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0844bw(XMPushService xMPushService, Map map, int i, String str, InterfaceC0653gg interfaceC0653gg) {
        super(map, i, str, interfaceC0653gg);
        this.f2623a = xMPushService;
    }

    @Override // com.xiaomi.push.C0650gd
    /* renamed from: a */
    public byte[] mo1417a() {
        try {
            C0612et.b bVar = new C0612et.b();
            bVar.m1060a(C0830bi.m2642a().m2650a());
            return bVar.mo959a();
        } catch (Exception e) {
            AbstractC0407b.m70a("getOBBString err: " + e.toString());
            return null;
        }
    }
}
