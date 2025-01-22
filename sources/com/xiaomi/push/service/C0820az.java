package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0613eu;
import com.xiaomi.push.C0620fa;
import com.xiaomi.push.C0694hu;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.C0743jp;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.InterfaceC0624fe;
import java.util.HashMap;

/* renamed from: com.xiaomi.push.service.az */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/az.class */
public class C0820az implements InterfaceC0624fe {
    @Override // com.xiaomi.push.InterfaceC0624fe
    /* renamed from: a */
    public void mo328a(Context context, HashMap<String, String> hashMap) {
        C0732je c0732je = new C0732je();
        c0732je.m2071b(C0620fa.m1220a(context).m1225a());
        c0732je.m2079d(C0620fa.m1220a(context).m1232b());
        c0732je.m2075c(EnumC0714in.AwakeAppResponse.f1752a);
        c0732je.m2058a(C0812ar.m2571a());
        c0732je.f2033a = hashMap;
        byte[] m2314a = C0743jp.m2314a(C0800af.m2495a(c0732je.m2076c(), c0732je.m2072b(), c0732je, EnumC0696hw.Notification));
        if (!(context instanceof XMPushService)) {
            AbstractC0407b.m70a("MoleInfo : context is not correct in pushLayer " + c0732je.m2063a());
            return;
        }
        AbstractC0407b.m70a("MoleInfo : send data directly in pushLayer " + c0732je.m2063a());
        ((XMPushService) context).m2473a(context.getPackageName(), m2314a, true);
    }

    @Override // com.xiaomi.push.InterfaceC0624fe
    /* renamed from: b */
    public void mo329b(Context context, HashMap<String, String> hashMap) {
        C0694hu m1605a = C0694hu.m1605a(context);
        if (m1605a != null) {
            m1605a.m1611a("category_awake_app", "wake_up_app", 1L, C0613eu.m1192a(hashMap));
        }
    }

    @Override // com.xiaomi.push.InterfaceC0624fe
    /* renamed from: c */
    public void mo330c(Context context, HashMap<String, String> hashMap) {
        AbstractC0407b.m70a("MoleInfo：\u3000" + C0613eu.m1194b(hashMap));
    }
}
