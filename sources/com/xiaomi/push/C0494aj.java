package com.xiaomi.push;

import android.content.SharedPreferences;
import com.xiaomi.push.C0493ai;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.aj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/aj.class */
public class C0494aj extends C0493ai.b {

    /* renamed from: a */
    final /* synthetic */ C0493ai f424a;

    /* renamed from: a */
    final /* synthetic */ String f425a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0494aj(C0493ai c0493ai, C0493ai.a aVar, String str) {
        super(aVar);
        this.f424a = c0493ai;
        this.f425a = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaomi.push.C0493ai.b
    /* renamed from: a */
    public void mo449a() {
        super.mo449a();
    }

    @Override // com.xiaomi.push.C0493ai.b
    /* renamed from: b */
    void mo450b() {
        SharedPreferences sharedPreferences;
        sharedPreferences = this.f424a.f419a;
        sharedPreferences.edit().putLong(this.f425a, System.currentTimeMillis()).commit();
    }
}
