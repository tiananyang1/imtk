package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.de */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/de.class */
public class C0570de extends C0563cy {

    /* renamed from: a */
    C0563cy f666a;

    /* renamed from: a */
    final /* synthetic */ C0568dc f667a;

    /* renamed from: b */
    final /* synthetic */ C0563cy f668b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0570de(C0568dc c0568dc, String str, C0563cy c0563cy) {
        super(str);
        this.f667a = c0568dc;
        this.f668b = c0563cy;
        this.f666a = this.f668b;
        this.f637b = this.f637b;
        C0563cy c0563cy2 = this.f668b;
        if (c0563cy2 != null) {
            this.f641f = c0563cy2.f641f;
        }
    }

    @Override // com.xiaomi.push.C0563cy
    /* renamed from: a */
    public ArrayList<String> mo833a(boolean z) {
        ArrayList<String> arrayList;
        synchronized (this) {
            arrayList = new ArrayList<>();
            if (this.f666a != null) {
                arrayList.addAll(this.f666a.mo833a(true));
            }
            synchronized (C0568dc.f654b) {
                C0563cy c0563cy = C0568dc.f654b.get(this.f637b);
                if (c0563cy != null) {
                    Iterator<String> it = c0563cy.mo833a(true).iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (arrayList.indexOf(next) == -1) {
                            arrayList.add(next);
                        }
                    }
                    arrayList.remove(this.f637b);
                    arrayList.add(this.f637b);
                }
            }
        }
        return arrayList;
    }

    @Override // com.xiaomi.push.C0563cy
    /* renamed from: a */
    public void mo842a(String str, C0562cx c0562cx) {
        synchronized (this) {
            if (this.f666a != null) {
                this.f666a.mo842a(str, c0562cx);
            }
        }
    }

    @Override // com.xiaomi.push.C0563cy
    /* renamed from: b */
    public boolean mo849b() {
        return false;
    }
}
