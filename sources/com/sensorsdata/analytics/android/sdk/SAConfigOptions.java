package com.sensorsdata.analytics.android.sdk;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SAConfigOptions.class */
public final class SAConfigOptions extends AbstractSAConfigOptions {
    boolean mInvokeHeatMapConfirmDialog;
    boolean mInvokeHeatMapEnabled;
    boolean mInvokeHeatMapSSLCheck;
    boolean mInvokeLog;
    boolean mInvokeVisualizedConfirmDialog;
    boolean mInvokeVisualizedEnabled;
    boolean mInvokeVisualizedSSLCheck;

    private SAConfigOptions() {
    }

    public SAConfigOptions(String str) {
        this.mServerUrl = str;
    }

    public SAConfigOptions disableRandomTimeRequestRemoteConfig() {
        this.mDisableRandomTimeRequestRemoteConfig = true;
        return this;
    }

    public SAConfigOptions enableHeatMap(boolean z) {
        this.mHeatMapEnabled = z;
        this.mInvokeHeatMapEnabled = true;
        return this;
    }

    public SAConfigOptions enableHeatMapConfirmDialog(boolean z) {
        this.mHeatMapConfirmDialogEnabled = z;
        this.mInvokeHeatMapConfirmDialog = true;
        return this;
    }

    public SAConfigOptions enableHeatMapSSLCheck(boolean z) {
        this.mHeatMapSSLChecked = z;
        this.mInvokeHeatMapSSLCheck = true;
        return this;
    }

    public SAConfigOptions enableLog(boolean z) {
        this.mLogEnabled = z;
        this.mInvokeLog = true;
        return this;
    }

    public SAConfigOptions enableMultiProcess(boolean z) {
        this.enableMultiProcess = z;
        return this;
    }

    public SAConfigOptions enableReactNativeAutoTrack(boolean z) {
        this.mRNAutoTrackEnabled = z;
        return this;
    }

    public SAConfigOptions enableTrackAppCrash() {
        this.mEnableTrackAppCrash = true;
        return this;
    }

    public SAConfigOptions enableTrackScreenOrientation(boolean z) {
        this.mTrackScreenOrientationEnabled = z;
        return this;
    }

    public SAConfigOptions enableVisualizedAutoTrack(boolean z) {
        this.mVisualizedEnabled = z;
        this.mInvokeVisualizedEnabled = true;
        return this;
    }

    public SAConfigOptions enableVisualizedAutoTrackConfirmDialog(boolean z) {
        this.mVisualizedConfirmDialogEnabled = z;
        this.mInvokeVisualizedConfirmDialog = true;
        return this;
    }

    public SAConfigOptions enableVisualizedAutoTrackSSLCheck(boolean z) {
        this.mVisualizedSSLChecked = z;
        this.mInvokeVisualizedSSLCheck = true;
        return this;
    }

    public SAConfigOptions setAnonymousId(String str) {
        this.mAnonymousId = str;
        return this;
    }

    public SAConfigOptions setAutoTrackEventType(int i) {
        this.mAutoTrackEventType = i;
        return this;
    }

    public SAConfigOptions setFlushBulkSize(int i) {
        this.mFlushBulkSize = Math.max(50, i);
        return this;
    }

    public SAConfigOptions setFlushInterval(int i) {
        this.mFlushInterval = Math.max(5000, i);
        return this;
    }

    public SAConfigOptions setMaxCacheSize(long j) {
        this.mMaxCacheSize = Math.max(16777216L, j);
        return this;
    }

    public SAConfigOptions setMaxRequestInterval(int i) {
        this.mMaxRequestInterval = i;
        return this;
    }

    public SAConfigOptions setMinRequestInterval(int i) {
        this.mMinRequestInterval = i;
        return this;
    }

    public SAConfigOptions setNetworkTypePolicy(int i) {
        this.mNetworkTypePolicy = i;
        return this;
    }

    public SAConfigOptions setRemoteConfigUrl(String str) {
        this.mRemoteConfigUrl = str;
        return this;
    }

    public SAConfigOptions setServerUrl(String str) {
        this.mServerUrl = str;
        return this;
    }
}
