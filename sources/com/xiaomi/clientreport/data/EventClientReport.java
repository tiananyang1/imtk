package com.xiaomi.clientreport.data;

import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/data/EventClientReport.class */
public class EventClientReport extends C0408a {
    public String eventContent;
    public String eventId;
    public long eventTime;
    public int eventType;

    public static EventClientReport getBlankInstance() {
        return new EventClientReport();
    }

    @Override // com.xiaomi.clientreport.data.C0408a
    public JSONObject toJson() {
        try {
            JSONObject json = super.toJson();
            if (json == null) {
                return null;
            }
            json.put("eventId", this.eventId);
            json.put("eventType", this.eventType);
            json.put("eventTime", this.eventTime);
            json.put("eventContent", this.eventContent);
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
