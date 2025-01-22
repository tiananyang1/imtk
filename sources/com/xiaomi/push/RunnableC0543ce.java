package com.xiaomi.push;

import java.net.InetAddress;

/* renamed from: com.xiaomi.push.ce */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ce.class */
class RunnableC0543ce implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0551cm f560a;

    /* renamed from: a */
    private String f561a;

    public RunnableC0543ce(C0551cm c0551cm, String str) {
        this.f560a = c0551cm;
        this.f561a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            InetAddress.getByName(this.f561a).isReachable(500);
        } catch (Exception e) {
        }
    }
}
