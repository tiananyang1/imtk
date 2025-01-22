package com.xiaomi.push.service;

import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.AbstractC0572dg;
import com.xiaomi.push.C0496al;
import com.xiaomi.push.C0611es;
import com.xiaomi.push.C0880v;
import com.xiaomi.push.InterfaceC0502ar;
import com.xiaomi.push.service.C0830bi;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.bj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/bj.class */
public class C0831bj extends C0496al.b {

    /* renamed from: a */
    final /* synthetic */ C0830bi f2600a;

    /* renamed from: a */
    boolean f2601a = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0831bj(C0830bi c0830bi) {
        this.f2600a = c0830bi;
    }

    @Override // com.xiaomi.push.C0496al.b
    /* renamed from: b */
    public void mo462b() {
        try {
            C0611es.a m1008a = C0611es.a.m1008a(Base64.decode(AbstractC0572dg.m908a(C0880v.m2849a(), "http://resolver.msg.xiaomi.net/psc/?t=a", (List<InterfaceC0502ar>) null), 10));
            if (m1008a != null) {
                this.f2600a.f2598a = m1008a;
                this.f2601a = true;
                this.f2600a.m2649e();
            }
        } catch (Exception e) {
            AbstractC0407b.m70a("fetch config failure: " + e.getMessage());
        }
    }

    @Override // com.xiaomi.push.C0496al.b
    /* renamed from: c */
    public void mo463c() {
        List list;
        List list2;
        C0830bi.a[] aVarArr;
        C0611es.a aVar;
        this.f2600a.f2597a = null;
        if (!this.f2601a) {
            return;
        }
        synchronized (this.f2600a) {
            list = this.f2600a.f2599a;
            list2 = this.f2600a.f2599a;
            aVarArr = (C0830bi.a[]) list.toArray(new C0830bi.a[list2.size()]);
        }
        int length = aVarArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            C0830bi.a aVar2 = aVarArr[i2];
            aVar = this.f2600a.f2598a;
            aVar2.mo2612a(aVar);
            i = i2 + 1;
        }
    }
}
