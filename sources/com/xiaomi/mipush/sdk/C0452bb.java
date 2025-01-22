package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.push.C0503as;
import com.xiaomi.push.service.C0822ba;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.mipush.sdk.bb */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/bb.class */
public class C0452bb extends ContentObserver {

    /* renamed from: a */
    final /* synthetic */ C0449az f326a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0452bb(C0449az c0449az, Handler handler) {
        super(handler);
        this.f326a = c0449az;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        Context context;
        Integer num;
        Context context2;
        Context context3;
        C0449az c0449az = this.f326a;
        context = c0449az.f312a;
        c0449az.f316a = Integer.valueOf(C0822ba.m2613a(context).m2614a());
        num = this.f326a.f316a;
        if (num.intValue() != 0) {
            context2 = this.f326a.f312a;
            context2.getContentResolver().unregisterContentObserver(this);
            context3 = this.f326a.f312a;
            if (C0503as.m484b(context3)) {
                this.f326a.m267c();
            }
        }
    }
}
