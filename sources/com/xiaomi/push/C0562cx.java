package com.xiaomi.push;

import org.json.JSONObject;

/* renamed from: com.xiaomi.push.cx */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cx.class */
public class C0562cx {

    /* renamed from: a */
    private int f627a;

    /* renamed from: a */
    private long f628a;

    /* renamed from: a */
    private String f629a;

    /* renamed from: b */
    private long f630b;

    /* renamed from: c */
    private long f631c;

    public C0562cx() {
        this(0, 0L, 0L, null);
    }

    public C0562cx(int i, long j, long j2, Exception exc) {
        this.f627a = i;
        this.f628a = j;
        this.f631c = j2;
        this.f630b = System.currentTimeMillis();
        if (exc != null) {
            this.f629a = exc.getClass().getSimpleName();
        }
    }

    /* renamed from: a */
    public int m825a() {
        return this.f627a;
    }

    /* renamed from: a */
    public C0562cx m826a(JSONObject jSONObject) {
        this.f628a = jSONObject.getLong("cost");
        this.f631c = jSONObject.getLong("size");
        this.f630b = jSONObject.getLong("ts");
        this.f627a = jSONObject.getInt("wt");
        this.f629a = jSONObject.optString("expt");
        return this;
    }

    /* renamed from: a */
    public JSONObject m827a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.f628a);
        jSONObject.put("size", this.f631c);
        jSONObject.put("ts", this.f630b);
        jSONObject.put("wt", this.f627a);
        jSONObject.put("expt", this.f629a);
        return jSONObject;
    }
}
