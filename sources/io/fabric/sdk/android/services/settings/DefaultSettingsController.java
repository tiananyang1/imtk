package io.fabric.sdk.android.services.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/settings/DefaultSettingsController.class */
public class DefaultSettingsController implements SettingsController {
    private static final String LOAD_ERROR_MESSAGE = "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.";
    private static final String PREFS_BUILD_INSTANCE_IDENTIFIER = "existing_instance_identifier";
    private final CachedSettingsIo cachedSettingsIo;
    private final CurrentTimeProvider currentTimeProvider;
    private final Kit kit;
    private final PreferenceStore preferenceStore;
    private final SettingsJsonTransform settingsJsonTransform;
    private final SettingsRequest settingsRequest;
    private final SettingsSpiCall settingsSpiCall;

    public DefaultSettingsController(Kit kit, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonTransform settingsJsonTransform, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall) {
        this.kit = kit;
        this.settingsRequest = settingsRequest;
        this.currentTimeProvider = currentTimeProvider;
        this.settingsJsonTransform = settingsJsonTransform;
        this.cachedSettingsIo = cachedSettingsIo;
        this.settingsSpiCall = settingsSpiCall;
        this.preferenceStore = new PreferenceStoreImpl(this.kit);
    }

    private SettingsData getCachedSettingsData(SettingsCacheBehavior settingsCacheBehavior) {
        SettingsData settingsData;
        SettingsData settingsData2 = null;
        try {
            if (!SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                JSONObject readCachedSettings = this.cachedSettingsIo.readCachedSettings();
                if (readCachedSettings == null) {
                    Fabric.getLogger().mo2870d(Fabric.TAG, "No cached settings data found.");
                    return null;
                }
                SettingsData buildFromJson = this.settingsJsonTransform.buildFromJson(this.currentTimeProvider, readCachedSettings);
                if (buildFromJson == null) {
                    Fabric.getLogger().mo2873e(Fabric.TAG, "Failed to transform cached settings data.", null);
                    return null;
                }
                logSettings(readCachedSettings, "Loaded cached settings: ");
                long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
                if (!SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior) && buildFromJson.isExpired(currentTimeMillis)) {
                    Fabric.getLogger().mo2870d(Fabric.TAG, "Cached settings have expired.");
                    return null;
                }
                try {
                    Fabric.getLogger().mo2870d(Fabric.TAG, "Returning cached settings.");
                    return buildFromJson;
                } catch (Exception e) {
                    settingsData = buildFromJson;
                    e = e;
                    Fabric.getLogger().mo2873e(Fabric.TAG, "Failed to get cached settings", e);
                    settingsData2 = settingsData;
                    return settingsData2;
                }
            }
        } catch (Exception e2) {
            e = e2;
            settingsData = null;
        }
        return settingsData2;
    }

    private void logSettings(JSONObject jSONObject, String str) throws JSONException {
        Fabric.getLogger().mo2870d(Fabric.TAG, str + jSONObject.toString());
    }

    boolean buildInstanceIdentifierChanged() {
        return !getStoredBuildInstanceIdentifier().equals(getBuildInstanceIdentifierFromContext());
    }

    String getBuildInstanceIdentifierFromContext() {
        return CommonUtils.createInstanceIdFrom(CommonUtils.resolveBuildId(this.kit.getContext()));
    }

    String getStoredBuildInstanceIdentifier() {
        return this.preferenceStore.get().getString(PREFS_BUILD_INSTANCE_IDENTIFIER, "");
    }

    @Override // io.fabric.sdk.android.services.settings.SettingsController
    public SettingsData loadSettingsData() {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE);
    }

    @Override // io.fabric.sdk.android.services.settings.SettingsController
    public SettingsData loadSettingsData(SettingsCacheBehavior settingsCacheBehavior) {
        SettingsData settingsData;
        SettingsData settingsData2 = null;
        SettingsData settingsData3 = null;
        try {
            if (!Fabric.isDebuggable()) {
                settingsData2 = null;
                if (!buildInstanceIdentifierChanged()) {
                    settingsData2 = getCachedSettingsData(settingsCacheBehavior);
                }
            }
            settingsData3 = settingsData2;
            if (settingsData2 == null) {
                JSONObject invoke = this.settingsSpiCall.invoke(this.settingsRequest);
                settingsData3 = settingsData2;
                if (invoke != null) {
                    SettingsData buildFromJson = this.settingsJsonTransform.buildFromJson(this.currentTimeProvider, invoke);
                    this.cachedSettingsIo.writeCachedSettings(buildFromJson.expiresAtMillis, invoke);
                    logSettings(invoke, "Loaded settings: ");
                    setStoredBuildInstanceIdentifier(getBuildInstanceIdentifierFromContext());
                    settingsData3 = buildFromJson;
                }
            }
            settingsData = settingsData3;
            if (settingsData3 == null) {
                return getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
            }
        } catch (Exception e) {
            Fabric.getLogger().mo2873e(Fabric.TAG, LOAD_ERROR_MESSAGE, e);
            settingsData = settingsData3;
        }
        return settingsData;
    }

    @SuppressLint({"CommitPrefEdits"})
    boolean setStoredBuildInstanceIdentifier(String str) {
        SharedPreferences.Editor edit = this.preferenceStore.edit();
        edit.putString(PREFS_BUILD_INSTANCE_IDENTIFIER, str);
        return this.preferenceStore.save(edit);
    }
}
