package com.xiaomi.push;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.gb */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gb.class */
public class C0648gb extends Thread {

    /* renamed from: a */
    final /* synthetic */ C0647ga f1054a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0648gb(C0647ga c0647ga, String str) {
        super(str);
        this.f1054a = c0647ga;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        C0642fw c0642fw;
        try {
            c0642fw = this.f1054a.f1050a;
            c0642fw.m1350a();
        } catch (Exception e) {
            this.f1054a.m1436c(9, e);
        }
    }
}
