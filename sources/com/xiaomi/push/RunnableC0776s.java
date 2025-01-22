package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.s */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/s.class */
public class RunnableC0776s implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0775r f2377a;

    /* renamed from: a */
    final /* synthetic */ String f2378a;

    /* renamed from: b */
    final /* synthetic */ String f2379b;

    /* renamed from: c */
    final /* synthetic */ String f2380c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0776s(C0775r c0775r, String str, String str2, String str3) {
        this.f2377a = c0775r;
        this.f2378a = str;
        this.f2379b = str2;
        this.f2380c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        context = this.f2377a.f2374a;
        SharedPreferences.Editor edit = context.getSharedPreferences(this.f2378a, 4).edit();
        edit.putString(this.f2379b, this.f2380c);
        edit.commit();
    }
}
