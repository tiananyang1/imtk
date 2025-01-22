package com.sensorsdata.analytics.android.sdk;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/AbstractSAConfigOptions.class */
abstract class AbstractSAConfigOptions {
    String mAnonymousId;
    int mAutoTrackEventType;
    boolean mDisableRandomTimeRequestRemoteConfig;
    boolean mEnableTrackAppCrash;
    int mFlushBulkSize;
    int mFlushInterval;
    boolean mHeatMapConfirmDialogEnabled;
    boolean mHeatMapEnabled;
    boolean mHeatMapSSLChecked;
    boolean mLogEnabled;
    long mMaxCacheSize;
    boolean mRNAutoTrackEnabled;
    String mRemoteConfigUrl;
    String mServerUrl;
    boolean mTrackScreenOrientationEnabled;
    boolean mVisualizedConfirmDialogEnabled;
    boolean mVisualizedEnabled;
    boolean mVisualizedSSLChecked;
    int mMinRequestInterval = 24;
    int mMaxRequestInterval = 48;
    int mNetworkTypePolicy = 30;
    boolean enableMultiProcess = true;
}
