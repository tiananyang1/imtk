package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.stub.StubApp;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/AdvertisingInfoProvider.class */
public class AdvertisingInfoProvider {
    private static final String ADVERTISING_INFO_PREFERENCES = "TwitterAdvertisingInfoPreferences";
    private static final String PREFKEY_ADVERTISING_ID = "advertising_id";
    private static final String PREFKEY_LIMIT_AD_TRACKING = "limit_ad_tracking_enabled";
    private final Context context;
    private final PreferenceStore preferenceStore;

    public AdvertisingInfoProvider(Context context) {
        this.context = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.preferenceStore = new PreferenceStoreImpl(context, ADVERTISING_INFO_PREFERENCES);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AdvertisingInfo getAdvertisingInfoFromStrategies() {
        AdvertisingInfo advertisingInfo = getReflectionStrategy().getAdvertisingInfo();
        if (isInfoValid(advertisingInfo)) {
            Fabric.getLogger().mo2870d(Fabric.TAG, "Using AdvertisingInfo from Reflection Provider");
            return advertisingInfo;
        }
        AdvertisingInfo advertisingInfo2 = getServiceStrategy().getAdvertisingInfo();
        if (isInfoValid(advertisingInfo2)) {
            Fabric.getLogger().mo2870d(Fabric.TAG, "Using AdvertisingInfo from Service Provider");
            return advertisingInfo2;
        }
        Fabric.getLogger().mo2870d(Fabric.TAG, "AdvertisingInfo not present");
        return advertisingInfo2;
    }

    private boolean isInfoValid(AdvertisingInfo advertisingInfo) {
        return (advertisingInfo == null || TextUtils.isEmpty(advertisingInfo.advertisingId)) ? false : true;
    }

    private void refreshInfoIfNeededAsync(final AdvertisingInfo advertisingInfo) {
        new Thread(new BackgroundPriorityRunnable() { // from class: io.fabric.sdk.android.services.common.AdvertisingInfoProvider.1
            @Override // io.fabric.sdk.android.services.common.BackgroundPriorityRunnable
            public void onRun() {
                AdvertisingInfo advertisingInfoFromStrategies = AdvertisingInfoProvider.this.getAdvertisingInfoFromStrategies();
                if (advertisingInfo.equals(advertisingInfoFromStrategies)) {
                    return;
                }
                Fabric.getLogger().mo2870d(Fabric.TAG, "Asychronously getting Advertising Info and storing it to preferences");
                AdvertisingInfoProvider.this.storeInfoToPreferences(advertisingInfoFromStrategies);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CommitPrefEdits"})
    public void storeInfoToPreferences(AdvertisingInfo advertisingInfo) {
        if (isInfoValid(advertisingInfo)) {
            PreferenceStore preferenceStore = this.preferenceStore;
            preferenceStore.save(preferenceStore.edit().putString(PREFKEY_ADVERTISING_ID, advertisingInfo.advertisingId).putBoolean(PREFKEY_LIMIT_AD_TRACKING, advertisingInfo.limitAdTrackingEnabled));
        } else {
            PreferenceStore preferenceStore2 = this.preferenceStore;
            preferenceStore2.save(preferenceStore2.edit().remove(PREFKEY_ADVERTISING_ID).remove(PREFKEY_LIMIT_AD_TRACKING));
        }
    }

    public AdvertisingInfo getAdvertisingInfo() {
        AdvertisingInfo infoFromPreferences = getInfoFromPreferences();
        if (isInfoValid(infoFromPreferences)) {
            Fabric.getLogger().mo2870d(Fabric.TAG, "Using AdvertisingInfo from Preference Store");
            refreshInfoIfNeededAsync(infoFromPreferences);
            return infoFromPreferences;
        }
        AdvertisingInfo advertisingInfoFromStrategies = getAdvertisingInfoFromStrategies();
        storeInfoToPreferences(advertisingInfoFromStrategies);
        return advertisingInfoFromStrategies;
    }

    protected AdvertisingInfo getInfoFromPreferences() {
        return new AdvertisingInfo(this.preferenceStore.get().getString(PREFKEY_ADVERTISING_ID, ""), this.preferenceStore.get().getBoolean(PREFKEY_LIMIT_AD_TRACKING, false));
    }

    public AdvertisingInfoStrategy getReflectionStrategy() {
        return new AdvertisingInfoReflectionStrategy(this.context);
    }

    public AdvertisingInfoStrategy getServiceStrategy() {
        return new AdvertisingInfoServiceStrategy(this.context);
    }
}
