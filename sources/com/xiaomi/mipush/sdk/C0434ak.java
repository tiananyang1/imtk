package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.C0630fk;
import com.xiaomi.push.C0702ib;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.ak */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ak.class */
public final class C0434ak implements C0630fk.a {
    @Override // com.xiaomi.push.C0630fk.a
    /* renamed from: a */
    public void mo183a(Context context, C0702ib c0702ib) {
        MiTinyDataClient.upload(context, c0702ib);
    }
}
