package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.C0469l;
import com.xiaomi.push.C0504at;

/* renamed from: com.xiaomi.mipush.sdk.au */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/au.class */
public class C0444au {
    /* renamed from: a */
    public static AbstractPushManager m198a(Context context, EnumC0463f enumC0463f) {
        return m199b(context, enumC0463f);
    }

    /* renamed from: b */
    private static AbstractPushManager m199b(Context context, EnumC0463f enumC0463f) {
        C0469l.a m359a = C0469l.m359a(enumC0463f);
        if (m359a == null || TextUtils.isEmpty(m359a.f372a) || TextUtils.isEmpty(m359a.f373b)) {
            return null;
        }
        return (AbstractPushManager) C0504at.m497a(m359a.f372a, m359a.f373b, context);
    }
}
