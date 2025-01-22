package com.xiaomi.push.service;

import android.accounts.Account;

/* renamed from: com.xiaomi.push.service.q */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/q.class */
class RunnableC0867q implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0866p f2694a;

    /* renamed from: a */
    final /* synthetic */ Account[] f2695a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0867q(C0866p c0866p, Account[] accountArr) {
        this.f2694a = c0866p;
        this.f2695a = accountArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f2694a.f2693a.m2774a(this.f2695a);
    }
}
