package com.sensorsdata.analytics.android.sdk.data.persistent;

import android.content.SharedPreferences;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.data.PersistentLoader;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/persistent/PersistentSuperProperties.class */
public class PersistentSuperProperties extends PersistentIdentity<JSONObject> {
    public PersistentSuperProperties(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.SUPER_PROPERTIES, new PersistentIdentity.PersistentSerializer<JSONObject>() { // from class: com.sensorsdata.analytics.android.sdk.data.persistent.PersistentSuperProperties.1
            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public JSONObject create() {
                return new JSONObject();
            }

            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public JSONObject load(String str) {
                try {
                    return new JSONObject(str);
                } catch (JSONException e) {
                    SALog.m54d("Persistent", "failed to load SuperProperties from SharedPreferences.", e);
                    return new JSONObject();
                }
            }

            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public String save(JSONObject jSONObject) {
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject == null) {
                    jSONObject2 = create();
                }
                return jSONObject2.toString();
            }
        });
    }
}
