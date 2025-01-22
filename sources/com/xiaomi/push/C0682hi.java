package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.C0496al;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.hi */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hi.class */
final class C0682hi extends C0496al.b {

    /* renamed from: a */
    final /* synthetic */ Context f1375a;

    C0682hi(Context context) {
        this.f1375a = context;
    }

    @Override // com.xiaomi.push.C0496al.b
    /* renamed from: b */
    public void mo462b() {
        Object obj;
        ArrayList arrayList;
        List list;
        List list2;
        obj = C0681hh.f1366a;
        synchronized (obj) {
            list = C0681hh.f1368a;
            arrayList = new ArrayList(list);
            list2 = C0681hh.f1368a;
            list2.clear();
        }
        C0681hh.m1549b(this.f1375a, arrayList);
    }
}
