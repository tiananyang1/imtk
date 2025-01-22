package com.sensorsdata.analytics.android.sdk.data.persistent;

import android.content.SharedPreferences;
import com.sensorsdata.analytics.android.sdk.data.PersistentLoader;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity;
import java.util.concurrent.Future;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/persistent/PersistentFirstTrackInstallationWithCallback.class */
public class PersistentFirstTrackInstallationWithCallback extends PersistentIdentity<Boolean> {
    public PersistentFirstTrackInstallationWithCallback(Future<SharedPreferences> future) {
        super(future, PersistentLoader.PersistentName.FIRST_INSTALL_CALLBACK, new PersistentIdentity.PersistentSerializer<Boolean>() { // from class: com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstTrackInstallationWithCallback.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public Boolean create() {
                return true;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public Boolean load(String str) {
                return false;
            }

            @Override // com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity.PersistentSerializer
            public String save(Boolean bool) {
                return bool == null ? create().toString() : String.valueOf(true);
            }
        });
    }
}
