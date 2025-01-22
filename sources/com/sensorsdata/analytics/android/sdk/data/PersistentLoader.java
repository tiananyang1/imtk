package com.sensorsdata.analytics.android.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentAppEndData;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentAppPaused;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentAppStartTime;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentDistinctId;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstDay;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstStart;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstTrackInstallation;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstTrackInstallationWithCallback;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentIdentity;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentLoginId;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentRemoteSDKConfig;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentSessionIntervalTime;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentSuperProperties;
import com.stub.StubApp;
import java.util.concurrent.Future;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/PersistentLoader.class */
public class PersistentLoader {
    private static Context context;
    private static volatile PersistentLoader instance;
    private static Future<SharedPreferences> storedPreferences;

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/PersistentLoader$PersistentName.class */
    public interface PersistentName {
        public static final String APP_END_DATA = "app_end_data";
        public static final String APP_PAUSED_TIME = "app_paused_time";
        public static final String APP_SESSION_TIME = "session_interval_time";
        public static final String APP_START_TIME = "app_start_time";
        public static final String DISTINCT_ID = "events_distinct_id";
        public static final String FIRST_DAY = "first_day";
        public static final String FIRST_INSTALL = "first_track_installation";
        public static final String FIRST_INSTALL_CALLBACK = "first_track_installation_with_callback";
        public static final String FIRST_START = "first_start";
        public static final String LOGIN_ID = "events_login_id";
        public static final String REMOTE_CONFIG = "sensorsdata_sdk_configuration";
        public static final String SUPER_PROPERTIES = "super_properties";
    }

    private PersistentLoader(Context context2) {
        context = StubApp.getOrigApplicationContext(context2.getApplicationContext());
        storedPreferences = new SharedPreferencesLoader().loadPreferences(context2, "com.sensorsdata.analytics.android.sdk.SensorsDataAPI");
    }

    public static PersistentLoader initLoader(Context context2) {
        if (instance == null) {
            instance = new PersistentLoader(context2);
        }
        return instance;
    }

    public static PersistentIdentity loadPersistent(String str) {
        if (instance == null) {
            throw new RuntimeException("you should call 'PersistentLoader.initLoader(Context)' first");
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        boolean z = -1;
        switch (str.hashCode()) {
            case -1436067305:
                if (str.equals(PersistentName.LOGIN_ID)) {
                    z = 9;
                    break;
                }
                break;
            case -1173524450:
                if (str.equals(PersistentName.APP_SESSION_TIME)) {
                    z = 2;
                    break;
                }
                break;
            case -1046106592:
                if (str.equals(PersistentName.APP_PAUSED_TIME)) {
                    z = true;
                    break;
                }
                break;
            case -951089033:
                if (str.equals(PersistentName.SUPER_PROPERTIES)) {
                    z = 11;
                    break;
                }
                break;
            case -854148740:
                if (str.equals(PersistentName.FIRST_INSTALL_CALLBACK)) {
                    z = 7;
                    break;
                }
                break;
            case -690407917:
                if (str.equals(PersistentName.FIRST_START)) {
                    z = 8;
                    break;
                }
                break;
            case 133344653:
                if (str.equals(PersistentName.FIRST_DAY)) {
                    z = 5;
                    break;
                }
                break;
            case 721318680:
                if (str.equals(PersistentName.DISTINCT_ID)) {
                    z = 4;
                    break;
                }
                break;
            case 791585128:
                if (str.equals(PersistentName.APP_START_TIME)) {
                    z = 3;
                    break;
                }
                break;
            case 947194773:
                if (str.equals(PersistentName.REMOTE_CONFIG)) {
                    z = 10;
                    break;
                }
                break;
            case 1214783133:
                if (str.equals(PersistentName.FIRST_INSTALL)) {
                    z = 6;
                    break;
                }
                break;
            case 1521941740:
                if (str.equals(PersistentName.APP_END_DATA)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return new PersistentAppEndData(storedPreferences);
            case true:
                return new PersistentAppPaused(storedPreferences);
            case true:
                return new PersistentSessionIntervalTime(storedPreferences);
            case true:
                return new PersistentAppStartTime(storedPreferences);
            case true:
                return new PersistentDistinctId(storedPreferences, context);
            case true:
                return new PersistentFirstDay(storedPreferences);
            case true:
                return new PersistentFirstTrackInstallation(storedPreferences);
            case true:
                return new PersistentFirstTrackInstallationWithCallback(storedPreferences);
            case true:
                return new PersistentFirstStart(storedPreferences);
            case true:
                return new PersistentLoginId(storedPreferences);
            case true:
                return new PersistentRemoteSDKConfig(storedPreferences);
            case true:
                return new PersistentSuperProperties(storedPreferences);
            default:
                return null;
        }
    }
}
