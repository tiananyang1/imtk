package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.er */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/er.class */
public class C0610er implements InterfaceC0539ca {

    /* renamed from: a */
    final /* synthetic */ C0609eq f753a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0610er(C0609eq c0609eq) {
        this.f753a = c0609eq;
    }

    @Override // com.xiaomi.push.InterfaceC0539ca
    /* renamed from: a */
    public void mo707a(String str) {
        String m1006b;
        Object obj;
        Object obj2;
        C0609eq c0609eq = this.f753a;
        m1006b = C0609eq.m1006b(str);
        c0609eq.f752a = m1006b;
        obj = this.f753a.f751a;
        synchronized (obj) {
            try {
                obj2 = this.f753a.f751a;
                obj2.notify();
            } catch (Exception e) {
                AbstractC0407b.m72a(e);
            }
        }
    }
}
