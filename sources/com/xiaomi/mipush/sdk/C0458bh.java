package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.push.AbstractC0772p;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0727j;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.EnumC0703ic;
import com.xiaomi.push.EnumC0714in;
import com.xiaomi.push.service.C0809ao;
import com.xiaomi.push.service.C0812ar;
import com.xiaomi.push.service.C0865o;
import java.util.HashMap;

/* renamed from: com.xiaomi.mipush.sdk.bh */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/bh.class */
public class C0458bh implements C0865o.a {
    public C0458bh(Context context) {
        C0865o.m2771a(context).m2779a(this);
    }

    /* renamed from: b */
    private void m285b(String str, Context context) {
        C0732je c0732je = new C0732je();
        c0732je.m2075c(EnumC0714in.ClientMIIDUpdate.f1752a);
        c0732je.m2071b(C0461d.m289a(context).m293a());
        c0732je.m2058a(C0812ar.m2571a());
        HashMap hashMap = new HashMap();
        AbstractC0772p.m2412a(hashMap, Constants.EXTRA_KEY_MIID, str);
        c0732je.m2060a(hashMap);
        int m1979a = C0727j.m1979a();
        if (m1979a >= 0) {
            c0732je.m2064a().put("space_id", Integer.toString(m1979a));
        }
        C0449az.m224a(context).m254a((C0449az) c0732je, EnumC0696hw.Notification, true, (C0717iq) null);
    }

    /* renamed from: a */
    public void m286a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("last_sync_miid_time", -1L);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        int m2561a = C0809ao.m2557a(context).m2561a(EnumC0703ic.SyncMIIDFrequency.m1669a(), 21600);
        if (j != -1) {
            if (Math.abs(currentTimeMillis - j) <= m2561a) {
                return;
            } else {
                C0493ai.m439a(context).m446a((C0493ai.a) new C0459bi(context), m2561a);
            }
        }
        sharedPreferences.edit().putLong("last_sync_miid_time", currentTimeMillis).commit();
    }

    @Override // com.xiaomi.push.service.C0865o.a
    /* renamed from: a */
    public void mo287a(String str, Context context) {
        m285b(str, context);
    }
}
