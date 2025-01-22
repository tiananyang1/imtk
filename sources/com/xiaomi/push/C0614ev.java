package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import java.util.HashMap;

/* renamed from: com.xiaomi.push.ev */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ev.class */
public class C0614ev {
    /* renamed from: a */
    public static void m1195a(Context context, String str, int i, String str2) {
        C0493ai.m439a(context).m443a(new RunnableC0615ew(context, str, i, str2));
    }

    /* renamed from: a */
    private static void m1196a(Context context, HashMap<String, String> hashMap) {
        InterfaceC0624fe m1224a = C0620fa.m1220a(context).m1224a();
        if (m1224a != null) {
            m1224a.mo328a(context, hashMap);
        }
    }

    /* renamed from: b */
    private static void m1198b(Context context, HashMap<String, String> hashMap) {
        InterfaceC0624fe m1224a = C0620fa.m1220a(context).m1224a();
        if (m1224a != null) {
            m1224a.mo330c(context, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m1199c(Context context, String str, int i, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("awake_info", str);
            hashMap.put("event_type", String.valueOf(i));
            hashMap.put("description", str2);
            int m1223a = C0620fa.m1220a(context).m1223a();
            if (m1223a != 1) {
                if (m1223a != 2) {
                    if (m1223a == 3) {
                        m1196a(context, hashMap);
                    }
                }
                m1200c(context, hashMap);
            } else {
                m1196a(context, hashMap);
            }
            m1198b(context, hashMap);
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
        }
    }

    /* renamed from: c */
    private static void m1200c(Context context, HashMap<String, String> hashMap) {
        InterfaceC0624fe m1224a = C0620fa.m1220a(context).m1224a();
        if (m1224a != null) {
            m1224a.mo329b(context, hashMap);
        }
    }
}
