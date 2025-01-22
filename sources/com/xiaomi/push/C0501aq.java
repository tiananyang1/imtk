package com.xiaomi.push;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.aq */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/aq.class */
public class C0501aq {

    /* renamed from: a */
    public int f440a;

    /* renamed from: a */
    public String f441a;

    /* renamed from: a */
    public Map<String, String> f442a = new HashMap();

    /* renamed from: a */
    public String m468a() {
        return this.f441a;
    }

    public String toString() {
        return String.format("resCode = %1$d, headers = %2$s, response = %3$s", Integer.valueOf(this.f440a), this.f442a.toString(), this.f441a);
    }
}
