package com.xiaomi.push.service;

import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import android.content.Context;
import com.xiaomi.push.C0493ai;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.p */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/p.class */
public class C0866p implements OnAccountsUpdateListener {

    /* renamed from: a */
    final /* synthetic */ C0865o f2693a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0866p(C0865o c0865o) {
        this.f2693a = c0865o;
    }

    @Override // android.accounts.OnAccountsUpdateListener
    public void onAccountsUpdated(Account[] accountArr) {
        Context context;
        context = this.f2693a.f2690a;
        C0493ai.m439a(context).m443a(new RunnableC0867q(this, accountArr));
    }
}
