package com.xiaomi.push;

/* renamed from: com.xiaomi.push.gm */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gm.class */
class RunnableC0659gm implements Runnable {

    /* renamed from: a */
    final /* synthetic */ AbstractC0656gj f1105a;

    /* renamed from: a */
    final /* synthetic */ String f1106a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0659gm(AbstractC0656gj abstractC0656gj, String str) {
        this.f1105a = abstractC0656gj;
        this.f1106a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0568dc.m871a().m880a(this.f1106a, true);
    }
}
