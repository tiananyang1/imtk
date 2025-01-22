package io.fabric.sdk.android.services.settings;

import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/settings/CachedSettingsIo.class */
public interface CachedSettingsIo {
    JSONObject readCachedSettings();

    void writeCachedSettings(long j, JSONObject jSONObject);
}
