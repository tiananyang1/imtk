package com.xiaomi.push;

import android.util.SparseArray;
import com.xiaomi.push.C0493ai;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.ak */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ak.class */
public class C0495ak extends C0493ai.b {

    /* renamed from: a */
    final /* synthetic */ C0493ai f426a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0495ak(C0493ai c0493ai, C0493ai.a aVar) {
        super(aVar);
        this.f426a = c0493ai;
    }

    @Override // com.xiaomi.push.C0493ai.b
    /* renamed from: b */
    void mo450b() {
        Object obj;
        SparseArray sparseArray;
        obj = this.f426a.f421a;
        synchronized (obj) {
            sparseArray = this.f426a.f420a;
            sparseArray.remove(this.f423a.mo185a());
        }
    }
}
