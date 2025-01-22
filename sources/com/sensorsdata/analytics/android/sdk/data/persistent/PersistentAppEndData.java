package com.sensorsdata.analytics.android.sdk.data.persistent;

import android.content.SharedPreferences;
import com.sensorsdata.analytics.android.sdk.data.PersistentLoader;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity;
import java.util.concurrent.Future;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/persistent/PersistentAppEndData.class */
public class PersistentAppEndData extends PersistentIdentity<String> {
    public PersistentAppEndData(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.APP_END_DATA, new PersistentIdentity.PersistentSerializer<String>() { // from class: com.sensorsdata.analytics.android.sdk.data.persistent.PersistentAppEndData.1
            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public String create() {
                return "";
            }

            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public String load(String str) {
                return str;
            }

            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public String save(String str) {
                String str2 = str;
                if (str == null) {
                    str2 = create();
                }
                return str2;
            }
        });
    }
}
