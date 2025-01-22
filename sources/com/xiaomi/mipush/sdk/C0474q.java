package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.InterfaceC0584ds;

/* renamed from: com.xiaomi.mipush.sdk.q */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/q.class */
public class C0474q implements InterfaceC0584ds {

    /* renamed from: a */
    private Context f378a;

    public C0474q(Context context) {
        this.f378a = context;
    }

    @Override // com.xiaomi.push.InterfaceC0584ds
    /* renamed from: a */
    public String mo371a() {
        return C0461d.m289a(this.f378a).m310d();
    }

    @Override // com.xiaomi.push.InterfaceC0584ds
    /* renamed from: a */
    public void mo372a(C0732je c0732je, EnumC0696hw enumC0696hw, C0717iq c0717iq) {
        C0449az.m224a(this.f378a).m252a((C0449az) c0732je, enumC0696hw, c0717iq);
    }
}
