package com.xiaomi.clientreport.data;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0516be;
import com.xiaomi.push.C0770n;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.xiaomi.clientreport.data.a */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/data/a.class */
public class C0408a {
    public String clientInterfaceId;
    private String pkgName;
    public int production;
    public int reportType;

    /* renamed from: os */
    private String f224os = C0516be.m565a();
    private String miuiVersion = C0770n.m2400a();

    public String getPackageName() {
        return this.pkgName;
    }

    public void setAppPackageName(String str) {
        this.pkgName = str;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("production", this.production);
            jSONObject.put("reportType", this.reportType);
            jSONObject.put("clientInterfaceId", this.clientInterfaceId);
            jSONObject.put("os", this.f224os);
            jSONObject.put("miuiVersion", this.miuiVersion);
            jSONObject.put("pkgName", this.pkgName);
            return jSONObject;
        } catch (JSONException e) {
            AbstractC0407b.m72a(e);
            return null;
        }
    }

    public String toJsonString() {
        JSONObject json = toJson();
        return json == null ? "" : json.toString();
    }
}
