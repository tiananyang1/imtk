package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.clientreport.data.C0408a;
import com.xiaomi.clientreport.processor.InterfaceC0416d;

/* renamed from: com.xiaomi.push.az */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/az.class */
public class RunnableC0510az implements Runnable {

    /* renamed from: a */
    private Context f460a;

    /* renamed from: a */
    private C0408a f461a;

    /* renamed from: a */
    private InterfaceC0416d f462a;

    public RunnableC0510az(Context context, C0408a c0408a, InterfaceC0416d interfaceC0416d) {
        this.f460a = context;
        this.f461a = c0408a;
        this.f462a = interfaceC0416d;
    }

    @Override // java.lang.Runnable
    public void run() {
        InterfaceC0416d interfaceC0416d = this.f462a;
        if (interfaceC0416d != null) {
            interfaceC0416d.mo98a(this.f461a);
        }
    }
}
