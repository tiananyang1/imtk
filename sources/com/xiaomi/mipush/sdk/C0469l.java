package com.xiaomi.mipush.sdk;

import com.xiaomi.push.EnumC0703ic;
import java.util.HashMap;

/* renamed from: com.xiaomi.mipush.sdk.l */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/l.class */
public class C0469l {

    /* renamed from: a */
    private static HashMap<EnumC0463f, a> f371a = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.mipush.sdk.l$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/l$a.class */
    public static class a {

        /* renamed from: a */
        public String f372a;

        /* renamed from: b */
        public String f373b;

        public a(String str, String str2) {
            this.f372a = str;
            this.f373b = str2;
        }
    }

    static {
        m361a(EnumC0463f.ASSEMBLE_PUSH_HUAWEI, new a("com.xiaomi.assemble.control.HmsPushManager", "newInstance"));
        m361a(EnumC0463f.ASSEMBLE_PUSH_FCM, new a("com.xiaomi.assemble.control.FCMPushManager", "newInstance"));
        m361a(EnumC0463f.ASSEMBLE_PUSH_COS, new a("com.xiaomi.assemble.control.COSPushManager", "newInstance"));
    }

    /* renamed from: a */
    public static EnumC0455be m358a(EnumC0463f enumC0463f) {
        int i = C0470m.f374a[enumC0463f.ordinal()];
        if (i == 1) {
            return EnumC0455be.UPLOAD_HUAWEI_TOKEN;
        }
        if (i == 2) {
            return EnumC0455be.UPLOAD_FCM_TOKEN;
        }
        if (i != 3) {
            return null;
        }
        return EnumC0455be.UPLOAD_COS_TOKEN;
    }

    /* renamed from: a */
    public static a m359a(EnumC0463f enumC0463f) {
        return f371a.get(enumC0463f);
    }

    /* renamed from: a */
    public static EnumC0703ic m360a(EnumC0463f enumC0463f) {
        return EnumC0703ic.AggregatePushSwitch;
    }

    /* renamed from: a */
    private static void m361a(EnumC0463f enumC0463f, a aVar) {
        if (aVar != null) {
            f371a.put(enumC0463f, aVar);
        }
    }
}
