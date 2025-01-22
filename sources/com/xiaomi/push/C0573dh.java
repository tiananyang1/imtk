package com.xiaomi.push;

import com.xiaomi.mipush.sdk.Constants;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.dh */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dh.class */
class C0573dh implements Comparable<C0573dh> {

    /* renamed from: a */
    protected int f670a;

    /* renamed from: a */
    private long f671a;

    /* renamed from: a */
    String f672a;

    /* renamed from: a */
    private final LinkedList<C0562cx> f673a;

    public C0573dh() {
        this(null, 0);
    }

    public C0573dh(String str) {
        this(str, 0);
    }

    public C0573dh(String str, int i) {
        this.f673a = new LinkedList<>();
        this.f671a = 0L;
        this.f672a = str;
        this.f670a = i;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C0573dh c0573dh) {
        if (c0573dh == null) {
            return 1;
        }
        return c0573dh.f670a - this.f670a;
    }

    /* renamed from: a */
    public C0573dh m911a(JSONObject jSONObject) {
        synchronized (this) {
            this.f671a = jSONObject.getLong("tt");
            this.f670a = jSONObject.getInt("wt");
            this.f672a = jSONObject.getString("host");
            JSONArray jSONArray = jSONObject.getJSONArray("ah");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < jSONArray.length()) {
                    this.f673a.add(new C0562cx().m826a(jSONArray.getJSONObject(i2)));
                    i = i2 + 1;
                }
            }
        }
        return this;
    }

    /* renamed from: a */
    public JSONObject m912a() {
        JSONObject jSONObject;
        synchronized (this) {
            jSONObject = new JSONObject();
            jSONObject.put("tt", this.f671a);
            jSONObject.put("wt", this.f670a);
            jSONObject.put("host", this.f672a);
            JSONArray jSONArray = new JSONArray();
            Iterator<C0562cx> it = this.f673a.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().m827a());
            }
            jSONObject.put("ah", jSONArray);
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m913a(C0562cx c0562cx) {
        synchronized (this) {
            if (c0562cx != null) {
                this.f673a.add(c0562cx);
                int m825a = c0562cx.m825a();
                if (m825a > 0) {
                    this.f670a += c0562cx.m825a();
                } else {
                    int i = 0;
                    int size = this.f673a.size();
                    while (true) {
                        int i2 = size - 1;
                        if (i2 < 0 || this.f673a.get(i2).m825a() >= 0) {
                            break;
                        }
                        i++;
                        size = i2;
                    }
                    this.f670a += m825a * i;
                }
                if (this.f673a.size() > 30) {
                    this.f670a -= this.f673a.remove().m825a();
                }
            }
        }
    }

    public String toString() {
        return this.f672a + Constants.COLON_SEPARATOR + this.f670a;
    }
}
