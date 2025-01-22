package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.ef */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ef.class */
public class C0598ef extends AbstractC0601ei {
    public C0598ef(Context context, int i) {
        super(context, i);
    }

    /* renamed from: b */
    private String m977b() {
        Bundle extras;
        StringBuilder sb = new StringBuilder();
        try {
            Intent registerReceiver = this.f733a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null && (extras = registerReceiver.getExtras()) != null) {
                Set<String> keySet = extras.keySet();
                JSONObject jSONObject = new JSONObject();
                if (keySet != null && keySet.size() > 0) {
                    for (String str : keySet) {
                        if (!TextUtils.isEmpty(str)) {
                            try {
                                jSONObject.put(str, String.valueOf(extras.get(str)));
                            } catch (Exception e) {
                            }
                        }
                    }
                    sb.append(jSONObject);
                }
            }
        } catch (Exception e2) {
        }
        return sb.toString();
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 20;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public EnumC0699hz mo974a() {
        return EnumC0699hz.Battery;
    }

    @Override // com.xiaomi.push.AbstractC0601ei
    /* renamed from: a */
    public String mo975a() {
        return m977b();
    }
}
