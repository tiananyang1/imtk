package com.sensorsdata.analytics.android.sdk.data;

import android.net.Uri;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/DbParams.class */
public class DbParams {
    static final String DATABASE_NAME = "sensorsdata";
    static final int DATABASE_VERSION = 4;
    public static final int DB_OUT_OF_MEMORY_ERROR = -2;
    static final int DB_UPDATE_ERROR = -1;
    static final String KEY_CREATED_AT = "created_at";
    static final String KEY_DATA = "data";
    static final String TABLE_ACTIVITY_START_COUNT = "activity_started_count";
    static final String TABLE_APP_END_DATA = "app_end_data";
    static final String TABLE_APP_PAUSED_TIME = "app_paused_time";
    static final String TABLE_APP_START_TIME = "app_start_time";
    public static final String TABLE_EVENTS = "events";
    static final String TABLE_LOGIN_ID = "events_login_id";
    static final String TABLE_SESSION_INTERVAL_TIME = "session_interval_time";
    private static DbParams instance;
    private final Uri mActivityStartCountUri;
    private final Uri mAppEndDataUri;
    private final Uri mAppPausedUri;
    private final Uri mAppStartTimeUri;
    private final Uri mLoginIdUri;
    private final Uri mSessionTimeUri;
    private final Uri mUri;

    private DbParams(String str) {
        this.mUri = Uri.parse("content://" + str + ".SensorsDataContentProvider/" + TABLE_EVENTS);
        this.mActivityStartCountUri = Uri.parse("content://" + str + ".SensorsDataContentProvider/" + TABLE_ACTIVITY_START_COUNT);
        this.mAppStartTimeUri = Uri.parse("content://" + str + ".SensorsDataContentProvider/app_start_time");
        this.mAppEndDataUri = Uri.parse("content://" + str + ".SensorsDataContentProvider/app_end_data");
        this.mAppPausedUri = Uri.parse("content://" + str + ".SensorsDataContentProvider/app_paused_time");
        this.mSessionTimeUri = Uri.parse("content://" + str + ".SensorsDataContentProvider/session_interval_time");
        this.mLoginIdUri = Uri.parse("content://" + str + ".SensorsDataContentProvider/events_login_id");
    }

    public static DbParams getInstance() {
        DbParams dbParams = instance;
        if (dbParams != null) {
            return dbParams;
        }
        throw new IllegalStateException("The static method getInstance(String packageName) should be called before calling getInstance()");
    }

    public static DbParams getInstance(String str) {
        if (instance == null) {
            instance = new DbParams(str);
        }
        return instance;
    }

    public Uri getActivityStartCountUri() {
        return this.mActivityStartCountUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Uri getAppEndDataUri() {
        return this.mAppEndDataUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Uri getAppPausedUri() {
        return this.mAppPausedUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Uri getAppStartTimeUri() {
        return this.mAppStartTimeUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Uri getEventUri() {
        return this.mUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Uri getLoginIdUri() {
        return this.mLoginIdUri;
    }

    public Uri getSessionTimeUri() {
        return this.mSessionTimeUri;
    }
}
