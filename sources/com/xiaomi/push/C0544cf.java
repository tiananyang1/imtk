package com.xiaomi.push;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.cf */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cf.class */
public final class C0544cf {

    /* renamed from: a */
    private final double f562a;

    /* renamed from: a */
    private final long f563a;

    /* renamed from: a */
    private final String f564a;

    /* renamed from: a */
    private final List f565a;

    /* renamed from: b */
    private final double f566b;

    /* renamed from: b */
    private final long f567b;

    /* renamed from: b */
    private final String f568b;

    /* renamed from: c */
    private final String f569c;

    /* renamed from: d */
    private final String f570d;

    /* renamed from: e */
    private final String f571e;

    /* renamed from: f */
    private final String f572f;

    /* renamed from: g */
    private final String f573g;

    /* renamed from: h */
    private final String f574h;

    private C0544cf(C0542cd c0542cd) {
        String str;
        String str2;
        String str3;
        String str4;
        double d;
        double d2;
        String str5;
        String str6;
        long j;
        long j2;
        String str7;
        String str8;
        List list;
        str = c0542cd.f549a;
        this.f564a = str;
        str2 = c0542cd.f553b;
        this.f568b = str2;
        str3 = c0542cd.f554c;
        this.f569c = str3;
        str4 = c0542cd.f555d;
        this.f570d = str4;
        d = c0542cd.f547a;
        this.f562a = d;
        d2 = c0542cd.f551b;
        this.f566b = d2;
        str5 = c0542cd.f556e;
        this.f571e = str5;
        str6 = c0542cd.f557f;
        this.f572f = str6;
        j = c0542cd.f548a;
        this.f563a = j;
        j2 = c0542cd.f552b;
        this.f567b = j2;
        str7 = c0542cd.f558g;
        this.f573g = str7;
        str8 = c0542cd.f559h;
        this.f574h = str8;
        list = c0542cd.f550a;
        this.f565a = list;
    }

    /* renamed from: a */
    private void m744a(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null || obj == null) {
            return;
        }
        if ((obj instanceof String) && ((String) obj).isEmpty()) {
            return;
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
        }
    }

    /* renamed from: a */
    public JSONObject m745a() {
        JSONObject jSONObject = new JSONObject();
        m744a(jSONObject, "m", this.f564a);
        m744a(jSONObject, "i", this.f568b);
        m744a(jSONObject, "a", this.f569c);
        m744a(jSONObject, "o", this.f570d);
        m744a(jSONObject, "lg", Double.valueOf(this.f562a));
        m744a(jSONObject, "lt", Double.valueOf(this.f566b));
        m744a(jSONObject, "am", this.f571e);
        m744a(jSONObject, "as", this.f572f);
        m744a(jSONObject, "ast", Long.valueOf(this.f563a));
        m744a(jSONObject, "ad", Long.valueOf(this.f567b));
        m744a(jSONObject, "ds", this.f573g);
        m744a(jSONObject, "dm", this.f574h);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.f565a.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        m744a(jSONObject, "devices", jSONArray);
        return jSONObject;
    }
}
