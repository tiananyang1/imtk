package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.InterfaceC0695hv;
import java.util.List;

/* renamed from: com.xiaomi.push.service.m */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/m.class */
public class C0862m implements InterfaceC0695hv {

    /* renamed from: a */
    private final XMPushService f2677a;

    public C0862m(XMPushService xMPushService) {
        this.f2677a = xMPushService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m2759a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.f2677a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    @Override // com.xiaomi.push.InterfaceC0695hv
    /* renamed from: a */
    public void mo1612a(List<C0702ib> list, String str, String str2) {
        AbstractC0407b.m70a("TinyData LongConnUploader.upload items size:" + list.size() + "  ts:" + System.currentTimeMillis());
        this.f2677a.m2468a(new C0864n(this, 4, str, list, str2));
    }
}
