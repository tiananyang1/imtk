package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.cy */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cy.class */
public class C0563cy {

    /* renamed from: a */
    private long f633a;

    /* renamed from: a */
    public String f634a;

    /* renamed from: b */
    public String f637b;

    /* renamed from: c */
    public String f638c;

    /* renamed from: d */
    public String f639d;

    /* renamed from: e */
    public String f640e;

    /* renamed from: f */
    public String f641f;

    /* renamed from: g */
    public String f642g;

    /* renamed from: h */
    protected String f643h;

    /* renamed from: i */
    private String f644i;

    /* renamed from: a */
    private ArrayList<C0573dh> f635a = new ArrayList<>();

    /* renamed from: a */
    private double f632a = 0.1d;

    /* renamed from: j */
    private String f645j = "s.mi1.cc";

    /* renamed from: b */
    private long f636b = 86400000;

    public C0563cy(String str) {
        this.f634a = "";
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.f633a = System.currentTimeMillis();
        this.f635a.add(new C0573dh(str, -1));
        this.f634a = C0568dc.m872a();
        this.f637b = str;
    }

    /* renamed from: c */
    private void m828c(String str) {
        synchronized (this) {
            Iterator<C0573dh> it = this.f635a.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(it.next().f672a, str)) {
                    it.remove();
                }
            }
        }
    }

    /* renamed from: a */
    public C0563cy m829a(JSONObject jSONObject) {
        synchronized (this) {
            this.f634a = jSONObject.optString("net");
            this.f636b = jSONObject.getLong("ttl");
            this.f632a = jSONObject.getDouble("pct");
            this.f633a = jSONObject.getLong("ts");
            this.f639d = jSONObject.optString("city");
            this.f638c = jSONObject.optString("prv");
            this.f642g = jSONObject.optString("cty");
            this.f640e = jSONObject.optString("isp");
            this.f641f = jSONObject.optString("ip");
            this.f637b = jSONObject.optString("host");
            this.f643h = jSONObject.optString("xf");
            JSONArray jSONArray = jSONObject.getJSONArray("fbs");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < jSONArray.length()) {
                    m837a(new C0573dh().m911a(jSONArray.getJSONObject(i2)));
                    i = i2 + 1;
                }
            }
        }
        return this;
    }

    /* renamed from: a */
    public String m830a() {
        synchronized (this) {
            if (!TextUtils.isEmpty(this.f644i)) {
                return this.f644i;
            }
            if (TextUtils.isEmpty(this.f640e)) {
                return "hardcode_isp";
            }
            this.f644i = C0509ay.m526a(new String[]{this.f640e, this.f638c, this.f639d, this.f642g, this.f641f}, EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            return this.f644i;
        }
    }

    /* renamed from: a */
    public ArrayList<String> m831a() {
        ArrayList<String> mo833a;
        synchronized (this) {
            mo833a = mo833a(false);
        }
        return mo833a;
    }

    /* renamed from: a */
    public ArrayList<String> m832a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty.");
        }
        URL url = new URL(str);
        if (!TextUtils.equals(url.getHost(), this.f637b)) {
            throw new IllegalArgumentException("the url is not supported by the fallback");
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = mo833a(true).iterator();
        while (it.hasNext()) {
            C0566da m866a = C0566da.m866a(it.next(), url.getPort());
            arrayList.add(new URL(url.getProtocol(), m866a.m869a(), m866a.m868a(), url.getFile()).toString());
        }
        return arrayList;
    }

    /* renamed from: a */
    public ArrayList<String> mo833a(boolean z) {
        ArrayList<String> arrayList;
        String substring;
        synchronized (this) {
            C0573dh[] c0573dhArr = new C0573dh[this.f635a.size()];
            this.f635a.toArray(c0573dhArr);
            Arrays.sort(c0573dhArr);
            arrayList = new ArrayList<>();
            int length = c0573dhArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < length) {
                    C0573dh c0573dh = c0573dhArr[i2];
                    if (z) {
                        substring = c0573dh.f672a;
                    } else {
                        int indexOf = c0573dh.f672a.indexOf(Constants.COLON_SEPARATOR);
                        substring = indexOf != -1 ? c0573dh.f672a.substring(0, indexOf) : c0573dh.f672a;
                    }
                    arrayList.add(substring);
                    i = i2 + 1;
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public JSONObject m834a() {
        JSONObject jSONObject;
        synchronized (this) {
            jSONObject = new JSONObject();
            jSONObject.put("net", this.f634a);
            jSONObject.put("ttl", this.f636b);
            jSONObject.put("pct", this.f632a);
            jSONObject.put("ts", this.f633a);
            jSONObject.put("city", this.f639d);
            jSONObject.put("prv", this.f638c);
            jSONObject.put("cty", this.f642g);
            jSONObject.put("isp", this.f640e);
            jSONObject.put("ip", this.f641f);
            jSONObject.put("host", this.f637b);
            jSONObject.put("xf", this.f643h);
            JSONArray jSONArray = new JSONArray();
            Iterator<C0573dh> it = this.f635a.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().m912a());
            }
            jSONObject.put("fbs", jSONArray);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public void m835a(double d) {
        this.f632a = d;
    }

    /* renamed from: a */
    public void m836a(long j) {
        if (j > 0) {
            this.f636b = j;
            return;
        }
        throw new IllegalArgumentException("the duration is invalid " + j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m837a(C0573dh c0573dh) {
        synchronized (this) {
            m828c(c0573dh.f672a);
            this.f635a.add(c0573dh);
        }
    }

    /* renamed from: a */
    public void m838a(String str) {
        synchronized (this) {
            m837a(new C0573dh(str));
        }
    }

    /* renamed from: a */
    public void m839a(String str, int i, long j, long j2, Exception exc) {
        mo842a(str, new C0562cx(i, j, j2, exc));
    }

    /* renamed from: a */
    public void m840a(String str, long j, long j2) {
        try {
            m847b(new URL(str).getHost(), j, j2);
        } catch (MalformedURLException e) {
        }
    }

    /* renamed from: a */
    public void m841a(String str, long j, long j2, Exception exc) {
        try {
            m848b(new URL(str).getHost(), j, j2, exc);
        } catch (MalformedURLException e) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:            r0.m913a(r5);     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo842a(java.lang.String r4, com.xiaomi.push.C0562cx r5) {
        /*
            r3 = this;
            r0 = r3
            monitor-enter(r0)
            r0 = r3
            java.util.ArrayList<com.xiaomi.push.dh> r0 = r0.f635a     // Catch: java.lang.Throwable -> L33
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L33
            r6 = r0
        La:
            r0 = r6
            boolean r0 = r0.hasNext()     // Catch: java.lang.Throwable -> L33
            if (r0 == 0) goto L30
            r0 = r6
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L33
            com.xiaomi.push.dh r0 = (com.xiaomi.push.C0573dh) r0     // Catch: java.lang.Throwable -> L33
            r7 = r0
            r0 = r4
            r1 = r7
            java.lang.String r1 = r1.f672a     // Catch: java.lang.Throwable -> L33
            boolean r0 = android.text.TextUtils.equals(r0, r1)     // Catch: java.lang.Throwable -> L33
            if (r0 == 0) goto La
            r0 = r7
            r1 = r5
            r0.m913a(r1)     // Catch: java.lang.Throwable -> L33
        L30:
            r0 = r3
            monitor-exit(r0)
            return
        L33:
            r4 = move-exception
            r0 = r3
            monitor-exit(r0)
            r0 = r4
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0563cy.mo842a(java.lang.String, com.xiaomi.push.cx):void");
    }

    /* renamed from: a */
    public void m843a(String[] strArr) {
        int i;
        synchronized (this) {
            int size = this.f635a.size();
            while (true) {
                int i2 = size - 1;
                int i3 = 0;
                if (i2 < 0) {
                    break;
                }
                int length = strArr.length;
                while (true) {
                    if (i3 < length) {
                        if (TextUtils.equals(this.f635a.get(i2).f672a, strArr[i3])) {
                            this.f635a.remove(i2);
                            break;
                        }
                        i3++;
                    }
                }
                size = i2;
            }
            Iterator<C0573dh> it = this.f635a.iterator();
            int i4 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C0573dh next = it.next();
                if (next.f670a > i4) {
                    i4 = next.f670a;
                }
            }
            for (i = 0; i < strArr.length; i++) {
                m837a(new C0573dh(strArr[i], (strArr.length + i4) - i));
            }
        }
    }

    /* renamed from: a */
    public boolean m844a() {
        return TextUtils.equals(this.f634a, C0568dc.m872a());
    }

    /* renamed from: a */
    public boolean m845a(C0563cy c0563cy) {
        return TextUtils.equals(this.f634a, c0563cy.f634a);
    }

    /* renamed from: b */
    public void m846b(String str) {
        this.f645j = str;
    }

    /* renamed from: b */
    public void m847b(String str, long j, long j2) {
        m839a(str, 0, j, j2, null);
    }

    /* renamed from: b */
    public void m848b(String str, long j, long j2, Exception exc) {
        m839a(str, -1, j, j2, exc);
    }

    /* renamed from: b */
    public boolean mo849b() {
        return System.currentTimeMillis() - this.f633a < this.f636b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean m850c() {
        long j = this.f636b;
        if (864000000 >= j) {
            j = 864000000;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.f633a;
        if (currentTimeMillis - j2 <= j) {
            return currentTimeMillis - j2 > this.f636b && this.f634a.startsWith("WIFI-");
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f634a);
        sb.append("\n");
        sb.append(m830a());
        Iterator<C0573dh> it = this.f635a.iterator();
        while (it.hasNext()) {
            C0573dh next = it.next();
            sb.append("\n");
            sb.append(next.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
