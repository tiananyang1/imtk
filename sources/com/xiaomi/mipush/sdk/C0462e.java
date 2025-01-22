package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0613eu;
import com.xiaomi.push.C0620fa;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.InterfaceC0624fe;
import com.xiaomi.push.service.C0812ar;
import java.util.HashMap;

/* renamed from: com.xiaomi.mipush.sdk.e */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/e.class */
public class C0462e implements InterfaceC0624fe {
    @Override // com.xiaomi.push.InterfaceC0624fe
    /* renamed from: a */
    public void mo328a(Context context, HashMap<String, String> hashMap) {
        C0732je c0732je = new C0732je();
        c0732je.m2071b(C0620fa.m1220a(context).m1225a());
        c0732je.m2079d(C0620fa.m1220a(context).m1232b());
        c0732je.m2075c(EnumC0714in.AwakeAppResponse.f1752a);
        c0732je.m2058a(C0812ar.m2571a());
        c0732je.f2033a = hashMap;
        C0449az.m224a(context).m255a((C0449az) c0732je, EnumC0696hw.Notification, true, (C0717iq) null, true);
        AbstractC0407b.m70a("MoleInfo：\u3000send data in app layer");
    }

    @Override // com.xiaomi.push.InterfaceC0624fe
    /* renamed from: b */
    public void mo329b(Context context, HashMap<String, String> hashMap) {
        MiTinyDataClient.upload("category_awake_app", "wake_up_app", 1L, C0613eu.m1192a(hashMap));
        AbstractC0407b.m70a("MoleInfo：\u3000send data in app layer");
    }

    @Override // com.xiaomi.push.InterfaceC0624fe
    /* renamed from: c */
    public void mo330c(Context context, HashMap<String, String> hashMap) {
        AbstractC0407b.m70a("MoleInfo：\u3000" + C0613eu.m1194b(hashMap));
        String str = hashMap.get("event_type");
        String str2 = hashMap.get("awake_info");
        if (String.valueOf(1007).equals(str)) {
            C0472o.m369a(context, str2);
        }
    }
}
