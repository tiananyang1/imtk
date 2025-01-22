package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0729jb;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.service.n */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/n.class */
class C0864n extends XMPushService.AbstractC0786i {

    /* renamed from: a */
    final /* synthetic */ C0862m f2683a;

    /* renamed from: a */
    final /* synthetic */ String f2684a;

    /* renamed from: a */
    final /* synthetic */ List f2685a;

    /* renamed from: b */
    final /* synthetic */ String f2686b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0864n(C0862m c0862m, int i, String str, List list, String str2) {
        super(i);
        this.f2683a = c0862m;
        this.f2684a = str;
        this.f2685a = list;
        this.f2686b = str2;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public String mo1440a() {
        return "Send tiny data.";
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
    /* renamed from: a */
    public void mo1441a() {
        String m2759a;
        XMPushService xMPushService;
        m2759a = this.f2683a.m2759a(this.f2684a);
        ArrayList<C0732je> m2662a = C0833bl.m2662a(this.f2685a, this.f2684a, m2759a, 32768);
        AbstractC0407b.m70a("TinyData LongConnUploader.upload pack notifications " + m2662a.toString() + "  ts:" + System.currentTimeMillis());
        if (m2662a == null) {
            AbstractC0407b.m75d("TinyData LongConnUploader.upload Get a null XmPushActionNotification list when TinyDataHelper.pack() in XMPushService.");
            return;
        }
        Iterator<C0732je> it = m2662a.iterator();
        while (it.hasNext()) {
            C0732je next = it.next();
            next.m2066a("uploadWay", "longXMPushService");
            C0729jb m2495a = C0800af.m2495a(this.f2684a, m2759a, next, EnumC0696hw.Notification);
            if (!TextUtils.isEmpty(this.f2686b) && !TextUtils.equals(this.f2684a, this.f2686b)) {
                if (m2495a.m2022a() == null) {
                    C0717iq c0717iq = new C0717iq();
                    c0717iq.m1823a("-1");
                    m2495a.m2024a(c0717iq);
                }
                m2495a.m2022a().m1837b("ext_traffic_source_pkg", this.f2686b);
            }
            byte[] m2314a = C0743jp.m2314a(m2495a);
            xMPushService = this.f2683a.f2677a;
            xMPushService.m2473a(this.f2684a, m2314a, true);
        }
        Iterator it2 = this.f2685a.iterator();
        while (it2.hasNext()) {
            AbstractC0407b.m70a("TinyData LongConnUploader.upload uploaded by com.xiaomi.push.service.TinyDataUploader.  item" + ((C0702ib) it2.next()).m1656d() + "  ts:" + System.currentTimeMillis());
        }
    }
}
