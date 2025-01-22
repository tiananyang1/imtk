package com.xiaomi.push;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.cz */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cz.class */
public class C0564cz {

    /* renamed from: a */
    private String f646a;

    /* renamed from: a */
    private final ArrayList<C0563cy> f647a = new ArrayList<>();

    public C0564cz() {
    }

    public C0564cz(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.f646a = str;
    }

    /* renamed from: a */
    public C0563cy m851a() {
        synchronized (this) {
            int size = this.f647a.size();
            while (true) {
                int i = size - 1;
                if (i < 0) {
                    return null;
                }
                C0563cy c0563cy = this.f647a.get(i);
                if (c0563cy.m844a()) {
                    C0568dc.m871a().m884a(c0563cy.m830a());
                    return c0563cy;
                }
                size = i;
            }
        }
    }

    /* renamed from: a */
    public C0564cz m852a(JSONObject jSONObject) {
        synchronized (this) {
            this.f646a = jSONObject.getString("host");
            JSONArray jSONArray = jSONObject.getJSONArray("fbs");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < jSONArray.length()) {
                    this.f647a.add(new C0563cy(this.f646a).m829a(jSONArray.getJSONObject(i2)));
                    i = i2 + 1;
                }
            }
        }
        return this;
    }

    /* renamed from: a */
    public String m853a() {
        return this.f646a;
    }

    /* renamed from: a */
    public ArrayList<C0563cy> m854a() {
        return this.f647a;
    }

    /* renamed from: a */
    public JSONObject m855a() {
        JSONObject jSONObject;
        synchronized (this) {
            jSONObject = new JSONObject();
            jSONObject.put("host", this.f646a);
            JSONArray jSONArray = new JSONArray();
            Iterator<C0563cy> it = this.f647a.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().m834a());
            }
            jSONObject.put("fbs", jSONArray);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public void m856a(C0563cy c0563cy) {
        int i;
        synchronized (this) {
            int i2 = 0;
            while (true) {
                i = i2;
                if (i >= this.f647a.size()) {
                    break;
                }
                if (this.f647a.get(i).m845a(c0563cy)) {
                    this.f647a.set(i, c0563cy);
                    break;
                }
                i2 = i + 1;
            }
            if (i >= this.f647a.size()) {
                this.f647a.add(c0563cy);
            }
        }
    }

    /* renamed from: a */
    public void m857a(boolean z) {
        ArrayList<C0563cy> arrayList;
        synchronized (this) {
            int size = this.f647a.size();
            while (true) {
                int i = size - 1;
                if (i >= 0) {
                    C0563cy c0563cy = this.f647a.get(i);
                    if (z) {
                        if (c0563cy.m850c()) {
                            arrayList = this.f647a;
                            arrayList.remove(i);
                            size = i;
                        } else {
                            size = i;
                        }
                    } else if (c0563cy.mo849b()) {
                        size = i;
                    } else {
                        arrayList = this.f647a;
                        arrayList.remove(i);
                        size = i;
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f646a);
        sb.append("\n");
        Iterator<C0563cy> it = this.f647a.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }
}
