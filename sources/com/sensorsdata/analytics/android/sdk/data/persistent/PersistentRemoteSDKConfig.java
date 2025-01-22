package com.sensorsdata.analytics.android.sdk.data.persistent;

import android.content.SharedPreferences;
import com.sensorsdata.analytics.android.sdk.data.PersistentLoader;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity;
import java.util.concurrent.Future;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/persistent/PersistentRemoteSDKConfig.class */
public class PersistentRemoteSDKConfig extends PersistentIdentity<String> {
    public PersistentRemoteSDKConfig(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.REMOTE_CONFIG, new PersistentIdentity.PersistentSerializer<String>() { // from class: com.sensorsdata.analytics.android.sdk.data.persistent.PersistentRemoteSDKConfig.1
            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public String create() {
                return null;
            }

            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public String load(String str) {
                return str;
            }

            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public String save(String str) {
                return str;
            }
        });
    }
}
