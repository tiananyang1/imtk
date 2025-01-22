package com.xiaomi.clientreport.data;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/data/PerfClientReport.class */
public class PerfClientReport extends C0408a {
    private static final long DEFAULT_VALUE = -1;
    public int code;
    public long perfCounts = -1;
    public long perfLatencies = -1;

    public static PerfClientReport getBlankInstance() {
        return new PerfClientReport();
    }

    @Override // com.xiaomi.clientreport.data.C0408a
    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            if (json == null) {
                return null;
            }
            json.put("code", this.code);
            json.put("perfCounts", this.perfCounts);
            json.put("perfLatencies", this.perfLatencies);
            return json;
        } catch (JSONException e) {
            AbstractC0407b.m72a(e);
            return null;
        }
    }

    @Override // com.xiaomi.clientreport.data.C0408a
    public String toJsonString() {
        return super.toJsonString();
    }
}
