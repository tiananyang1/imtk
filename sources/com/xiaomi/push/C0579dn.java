package com.xiaomi.push;

import com.xiaomi.push.C0496al;
import com.xiaomi.push.C0577dl;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.dn */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dn.class */
public class C0579dn extends C0496al.b {

    /* renamed from: a */
    C0496al.b f704a;

    /* renamed from: a */
    final /* synthetic */ C0577dl f705a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0579dn(C0577dl c0577dl) {
        this.f705a = c0577dl;
    }

    @Override // com.xiaomi.push.C0496al.b
    /* renamed from: b */
    public void mo462b() {
        C0577dl.b bVar = (C0577dl.b) this.f705a.f685a.peek();
        if (bVar == null || !bVar.mo938a()) {
            return;
        }
        if (this.f705a.f685a.remove(bVar)) {
            this.f704a = bVar;
        }
        C0496al.b bVar2 = this.f704a;
        if (bVar2 != null) {
            bVar2.mo462b();
        }
    }

    @Override // com.xiaomi.push.C0496al.b
    /* renamed from: c */
    public void mo463c() {
        C0496al.b bVar = this.f704a;
        if (bVar != null) {
            bVar.mo463c();
        }
    }
}
