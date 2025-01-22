package com.xiaomi.push;

import android.util.Log;
import com.xiaomi.push.C0496al;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.dq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dq.class */
public class C0582dq extends C0496al.b {

    /* renamed from: a */
    final /* synthetic */ C0581dp f715a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0582dq(C0581dp c0581dp) {
        this.f715a = c0581dp;
    }

    @Override // com.xiaomi.push.C0496al.b
    /* renamed from: b */
    public void mo462b() {
        List list;
        String str;
        String str2;
        list = C0581dp.f711a;
        if (list.isEmpty()) {
            return;
        }
        try {
            if (C0485aa.m423d()) {
                this.f715a.m943a();
            } else {
                str2 = this.f715a.f713b;
                Log.w(str2, "SDCard is unavailable.");
            }
        } catch (Exception e) {
            str = this.f715a.f713b;
            Log.e(str, "", e);
        }
    }
}
