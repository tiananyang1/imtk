package com.xiaomi.push;

import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.bw */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bw.class */
public class HandlerC0534bw extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0533bv f526a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0534bw(C0533bv c0533bv, Looper looper) {
        super(looper);
        this.f526a = c0533bv;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        List list;
        List list2;
        List list3;
        List list4;
        int i = message.what;
        if (i == 200) {
            list = this.f526a.f525a;
            synchronized (list) {
                list2 = this.f526a.f525a;
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    ((InterfaceC0531bt) it.next()).mo643a((NetworkInfo) message.obj);
                }
            }
            return;
        }
        if (i != 201) {
            return;
        }
        list3 = this.f526a.f525a;
        synchronized (list3) {
            list4 = this.f526a.f525a;
            Iterator it2 = list4.iterator();
            while (it2.hasNext()) {
                ((InterfaceC0531bt) it2.next()).mo644b((NetworkInfo) message.obj);
            }
        }
    }
}
