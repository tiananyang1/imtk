package com.sensorsdata.analytics.android.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;
import com.microsoft.codepush.react.CodePushConstants;
import com.sensorsdata.analytics.android.sdk.data.DbAdapter;
import com.sensorsdata.analytics.android.sdk.data.PersistentLoader;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentDistinctId;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstDay;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstStart;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstTrackInstallation;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstTrackInstallationWithCallback;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentRemoteSDKConfig;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentSuperProperties;
import com.sensorsdata.analytics.android.sdk.exceptions.InvalidDataException;
import com.sensorsdata.analytics.android.sdk.internal.FragmentAPI;
import com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import com.sensorsdata.analytics.android.sdk.util.DateFormatUtils;
import com.sensorsdata.analytics.android.sdk.util.JSONUtils;
import com.sensorsdata.analytics.android.sdk.util.NetworkUtils;
import com.sensorsdata.analytics.android.sdk.util.SADeviceUtils;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;
import com.stub.StubApp;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataAPI.class */
public class SensorsDataAPI implements ISensorsDataAPI {
    static final String MIN_PLUGIN_VERSION = "3.0.0";
    private static final String TAG = "SA.SensorsDataAPI";
    static final String VERSION = "3.2.7";
    static final int VTRACK_SUPPORTED_MIN_API = 16;
    private static SensorsDataGPSLocation mGPSLocation;
    private static SAConfigOptions mSAConfigOptions;
    private static SensorsDataSDKRemoteConfig mSDKRemoteConfig;
    private String mAndroidId;
    private boolean mAutoTrack;
    private int mAutoTrackEventType;
    private List<Integer> mAutoTrackIgnoredActivities;
    private boolean mClearReferrerWhenAppEnd;
    private final Context mContext;
    private String mCookie;
    private DebugMode mDebugMode;
    private final Map<String, Object> mDeviceInfo;
    private boolean mDisableDefaultRemoteConfig;
    private boolean mDisableTrackDeviceId;
    private final PersistentDistinctId mDistinctId;
    private SensorsDataDynamicSuperProperties mDynamicSuperProperties;
    private boolean mEnableNetworkRequest;
    private HashSet<String> mFilterEventProperties;
    private final PersistentFirstDay mFirstDay;
    private final PersistentFirstStart mFirstStart;
    private final PersistentFirstTrackInstallation mFirstTrackInstallation;
    private final PersistentFirstTrackInstallationWithCallback mFirstTrackInstallationWithCallback;
    private int mFlushBulkSize;
    private int mFlushInterval;
    int mFlushNetworkPolicy;
    private IFragmentAPI mFragmentAPI;
    private List<Integer> mHeatMapActivities;
    private boolean mHeatMapConfirmDialogEnabled;
    private boolean mHeatMapEnabled;
    private boolean mHeatMapSSLCheckEnabled;
    private List<Class> mIgnoredViewTypeList;
    private SimpleDateFormat mIsFirstDayDateFormat;
    private JSONObject mLastScreenTrackProperties;
    private String mLastScreenUrl;
    private final Object mLoginIdLock;
    private String mMainProcessName;
    private long mMaxCacheSize;
    private final AnalyticsMessages mMessages;
    private SensorsDataScreenOrientationDetector mOrientationDetector;
    private String mOriginServerUrl;
    private final PersistentRemoteSDKConfig mPersistentRemoteSDKConfig;
    private CountDownTimer mPullSDKConfigCountDownTimer;
    private boolean mRNAutoTrackEnabled;
    private boolean mSDKConfigInit;
    private SSLSocketFactory mSSLSocketFactory;
    private String mServerUrl;
    private final PersistentSuperProperties mSuperProperties;
    private SensorsDataTrackEventCallBack mTrackEventCallBack;
    private TrackTaskManager mTrackTaskManager;
    private TrackTaskManagerThread mTrackTaskManagerThread;
    private final Map<String, EventTimer> mTrackTimer;
    private List<Integer> mVisualizedAutoTrackActivities;
    private boolean mVisualizedConfirmDialogEnabled;
    private boolean mVisualizedEnabled;
    private boolean mVisualizedSSLCheckEnabled;
    private static final Pattern KEY_PATTERN = Pattern.compile("^((?!^distinct_id$|^original_id$|^device_id$|^time$|^properties$|^id$|^first_id$|^second_id$|^users$|^events$|^event$|^user_id$|^date$|^datetime$)[a-zA-Z_$][a-zA-Z\\d_$]{0,99})$", 2);
    private static final Map<Context, SensorsDataAPI> sInstanceMap = new HashMap();
    static boolean mIsMainProcess = false;
    static boolean sEnableLog = false;
    static boolean SHOW_DEBUG_INFO_VIEW = true;

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataAPI$AutoTrackEventType.class */
    public enum AutoTrackEventType {
        APP_START(1),
        APP_END(2),
        APP_CLICK(4),
        APP_VIEW_SCREEN(8);

        private final int eventValue;

        AutoTrackEventType(int i) {
            this.eventValue = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        static AutoTrackEventType autoTrackEventTypeFromEventName(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            boolean z = -1;
            switch (str.hashCode()) {
                case -618659154:
                    if (str.equals("$AppViewScreen")) {
                        z = 3;
                        break;
                    }
                    break;
                case -441870274:
                    if (str.equals("$AppEnd")) {
                        z = true;
                        break;
                    }
                    break;
                case 562530347:
                    if (str.equals(AopConstants.APP_CLICK_EVENT_NAME)) {
                        z = 2;
                        break;
                    }
                    break;
                case 577537797:
                    if (str.equals("$AppStart")) {
                        z = false;
                        break;
                    }
                    break;
            }
            if (!z) {
                return APP_START;
            }
            if (z) {
                return APP_END;
            }
            if (z == 2) {
                return APP_CLICK;
            }
            if (z != 3) {
                return null;
            }
            return APP_VIEW_SCREEN;
        }

        /* JADX WARN: Multi-variable type inference failed */
        static boolean isAutoTrackType(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            boolean z = -1;
            switch (str.hashCode()) {
                case -618659154:
                    if (str.equals("$AppViewScreen")) {
                        z = 3;
                        break;
                    }
                    break;
                case -441870274:
                    if (str.equals("$AppEnd")) {
                        z = true;
                        break;
                    }
                    break;
                case 562530347:
                    if (str.equals(AopConstants.APP_CLICK_EVENT_NAME)) {
                        z = 2;
                        break;
                    }
                    break;
                case 577537797:
                    if (str.equals("$AppStart")) {
                        z = false;
                        break;
                    }
                    break;
            }
            return !z || z || z == 2 || z == 3;
        }

        int getEventValue() {
            return this.eventValue;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataAPI$DebugMode.class */
    public enum DebugMode {
        DEBUG_OFF(false, false),
        DEBUG_ONLY(true, false),
        DEBUG_AND_TRACK(true, true);

        private final boolean debugMode;
        private final boolean debugWriteData;

        DebugMode(boolean z, boolean z2) {
            this.debugMode = z;
            this.debugWriteData = z2;
        }

        boolean isDebugMode() {
            return this.debugMode;
        }

        boolean isDebugWriteData() {
            return this.debugWriteData;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataAPI$InstanceProcessor.class */
    public interface InstanceProcessor {
        void process(SensorsDataAPI sensorsDataAPI);
    }

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataAPI$NetworkType.class */
    public final class NetworkType {
        public static final int TYPE_2G = 1;
        public static final int TYPE_3G = 2;
        public static final int TYPE_4G = 4;
        public static final int TYPE_5G = 16;
        public static final int TYPE_ALL = 255;
        public static final int TYPE_NONE = 0;
        public static final int TYPE_WIFI = 8;

        public NetworkType() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SensorsDataAPI() {
        this.mLoginIdLock = new Object();
        this.mFlushNetworkPolicy = 30;
        this.mIgnoredViewTypeList = new ArrayList();
        this.mAndroidId = null;
        this.mDebugMode = DebugMode.DEBUG_OFF;
        this.mHeatMapConfirmDialogEnabled = true;
        this.mHeatMapSSLCheckEnabled = true;
        this.mVisualizedConfirmDialogEnabled = true;
        this.mVisualizedSSLCheckEnabled = true;
        this.mEnableNetworkRequest = true;
        this.mClearReferrerWhenAppEnd = false;
        this.mDisableDefaultRemoteConfig = false;
        this.mDisableTrackDeviceId = false;
        this.mMaxCacheSize = 33554432L;
        this.mFilterEventProperties = null;
        this.mContext = null;
        this.mMessages = null;
        this.mDistinctId = null;
        this.mSuperProperties = null;
        this.mFirstStart = null;
        this.mFirstDay = null;
        this.mFirstTrackInstallation = null;
        this.mFirstTrackInstallationWithCallback = null;
        this.mPersistentRemoteSDKConfig = null;
        this.mDeviceInfo = null;
        this.mTrackTimer = null;
        this.mMainProcessName = null;
    }

    SensorsDataAPI(Context context, String str, DebugMode debugMode) {
        this.mLoginIdLock = new Object();
        this.mFlushNetworkPolicy = 30;
        this.mIgnoredViewTypeList = new ArrayList();
        this.mAndroidId = null;
        this.mDebugMode = DebugMode.DEBUG_OFF;
        this.mHeatMapConfirmDialogEnabled = true;
        this.mHeatMapSSLCheckEnabled = true;
        this.mVisualizedConfirmDialogEnabled = true;
        this.mVisualizedSSLCheckEnabled = true;
        this.mEnableNetworkRequest = true;
        this.mClearReferrerWhenAppEnd = false;
        this.mDisableDefaultRemoteConfig = false;
        this.mDisableTrackDeviceId = false;
        this.mMaxCacheSize = 33554432L;
        this.mFilterEventProperties = null;
        this.mContext = context;
        setDebugMode(debugMode);
        String packageName = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName();
        this.mAutoTrackIgnoredActivities = new ArrayList();
        this.mHeatMapActivities = new ArrayList();
        this.mVisualizedAutoTrackActivities = new ArrayList();
        PersistentLoader.initLoader(context);
        this.mDistinctId = (PersistentDistinctId) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.DISTINCT_ID);
        this.mSuperProperties = (PersistentSuperProperties) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.SUPER_PROPERTIES);
        this.mFirstStart = (PersistentFirstStart) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.FIRST_START);
        this.mFirstTrackInstallation = (PersistentFirstTrackInstallation) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.FIRST_INSTALL);
        this.mFirstTrackInstallationWithCallback = (PersistentFirstTrackInstallationWithCallback) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.FIRST_INSTALL_CALLBACK);
        this.mPersistentRemoteSDKConfig = (PersistentRemoteSDKConfig) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.REMOTE_CONFIG);
        this.mFirstDay = (PersistentFirstDay) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.FIRST_DAY);
        this.mTrackTaskManager = TrackTaskManager.getInstance();
        this.mTrackTaskManagerThread = new TrackTaskManagerThread();
        new Thread(this.mTrackTaskManagerThread).start();
        SensorsDataExceptionHandler.init();
        initSAConfig(str, packageName);
        DbAdapter.getInstance(context, packageName);
        this.mMessages = AnalyticsMessages.getInstance(this.mContext);
        this.mAndroidId = SensorsDataUtils.getAndroidID(this.mContext);
        applySDKConfigFromCache();
        if (this.mDebugMode != DebugMode.DEBUG_OFF && mIsMainProcess && SHOW_DEBUG_INFO_VIEW && !isSDKDisabled()) {
            showDebugModeWarning();
        }
        if (Build.VERSION.SDK_INT >= 14) {
            ((Application) StubApp.getOrigApplicationContext(context.getApplicationContext())).registerActivityLifecycleCallbacks(new SensorsDataActivityLifecycleCallbacks(this, this.mFirstStart, this.mFirstDay, context));
        }
        SALog.m55i(TAG, String.format(Locale.CHINA, "Initialized the instance of Sensors Analytics SDK with server url '%s', flush interval %d ms, debugMode: %s", this.mServerUrl, Integer.valueOf(this.mFlushInterval), debugMode));
        this.mDeviceInfo = setupDeviceInfo();
        this.mTrackTimer = new HashMap();
        this.mFragmentAPI = new FragmentAPI(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void allInstances(InstanceProcessor instanceProcessor) {
        synchronized (sInstanceMap) {
            Iterator<SensorsDataAPI> it = sInstanceMap.values().iterator();
            while (it.hasNext()) {
                instanceProcessor.process(it.next());
            }
        }
    }

    private void applySAConfigOptions() {
        if (mSAConfigOptions.mEnableTrackAppCrash) {
            SensorsDataExceptionHandler.enableAppCrash();
        }
        if (mSAConfigOptions.mFlushInterval != 0) {
            this.mFlushInterval = mSAConfigOptions.mFlushInterval;
        }
        if (mSAConfigOptions.mFlushBulkSize != 0) {
            this.mFlushBulkSize = mSAConfigOptions.mFlushBulkSize;
        }
        if (mSAConfigOptions.mMaxCacheSize != 0) {
            this.mMaxCacheSize = mSAConfigOptions.mMaxCacheSize;
        }
        if (mSAConfigOptions.mAutoTrackEventType != 0) {
            this.mAutoTrackEventType = mSAConfigOptions.mAutoTrackEventType;
            this.mAutoTrack = true;
        }
        if (mSAConfigOptions.mInvokeLog) {
            enableLog(mSAConfigOptions.mLogEnabled);
        }
        if (mSAConfigOptions.mInvokeHeatMapEnabled) {
            this.mHeatMapEnabled = mSAConfigOptions.mHeatMapEnabled;
        }
        if (mSAConfigOptions.mInvokeHeatMapConfirmDialog) {
            this.mHeatMapConfirmDialogEnabled = mSAConfigOptions.mHeatMapConfirmDialogEnabled;
        }
        if (mSAConfigOptions.mInvokeHeatMapSSLCheck) {
            this.mHeatMapSSLCheckEnabled = mSAConfigOptions.mHeatMapSSLChecked;
        }
        if (mSAConfigOptions.mInvokeVisualizedEnabled) {
            this.mVisualizedEnabled = mSAConfigOptions.mVisualizedEnabled;
        }
        if (mSAConfigOptions.mInvokeVisualizedConfirmDialog) {
            this.mVisualizedConfirmDialogEnabled = mSAConfigOptions.mVisualizedConfirmDialogEnabled;
        }
        if (mSAConfigOptions.mInvokeVisualizedSSLCheck) {
            this.mVisualizedSSLCheckEnabled = mSAConfigOptions.mVisualizedSSLChecked;
        }
        this.mRNAutoTrackEnabled = mSAConfigOptions.mRNAutoTrackEnabled;
        this.mFlushNetworkPolicy = mSAConfigOptions.mNetworkTypePolicy;
        enableTrackScreenOrientation(mSAConfigOptions.mTrackScreenOrientationEnabled);
        if (TextUtils.isEmpty(mSAConfigOptions.mAnonymousId)) {
            return;
        }
        identify(mSAConfigOptions.mAnonymousId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assertKey(String str) throws InvalidDataException {
        if (str == null || str.length() < 1) {
            throw new InvalidDataException("The key is empty.");
        }
        if (KEY_PATTERN.matcher(str).matches()) {
            return;
        }
        throw new InvalidDataException("The key '" + str + "' is invalid.");
    }

    private void assertPropertyTypes(JSONObject jSONObject) throws InvalidDataException {
        if (jSONObject == null) {
            return;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            assertKey(next);
            try {
                Object obj = jSONObject.get(next);
                if (!(obj instanceof CharSequence) && !(obj instanceof Number) && !(obj instanceof JSONArray) && !(obj instanceof Boolean) && !(obj instanceof Date)) {
                    throw new InvalidDataException("The property value must be an instance of CharSequence/Number/Boolean/JSONArray. [key='" + next + "', value='" + obj.toString() + "']");
                }
                if ("app_crashed_reason".equals(next)) {
                    if ((obj instanceof String) && ((String) obj).length() > 16382) {
                        jSONObject.put(next, ((String) obj).substring(0, 16382) + "$");
                        SALog.m53d(TAG, "The property value is too long. [key='" + next + "', value='" + obj.toString() + "']");
                    }
                } else if ((obj instanceof String) && ((String) obj).length() > 8191) {
                    jSONObject.put(next, ((String) obj).substring(0, 8191) + "$");
                    SALog.m53d(TAG, "The property value is too long. [key='" + next + "', value='" + obj.toString() + "']");
                }
            } catch (JSONException e) {
                throw new InvalidDataException("Unexpected property key. [key='" + next + "']");
            }
        }
    }

    private void assertValue(String str) throws InvalidDataException {
        if (TextUtils.isEmpty(str)) {
            throw new InvalidDataException("The value is empty.");
        }
        if (str.length() <= 255) {
            return;
        }
        throw new InvalidDataException("The " + str + " is too long, max length is 255.");
    }

    private void enableAutoTrack(int i) {
        if (i <= 0 || i > 15) {
            return;
        }
        try {
            this.mAutoTrack = true;
            this.mAutoTrackEventType = i | this.mAutoTrackEventType;
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private static SensorsDataAPI getInstance(Context context, String str, DebugMode debugMode) {
        SensorsDataAPI sensorsDataAPI;
        if (context == null) {
            return new SensorsDataAPIEmptyImplementation();
        }
        synchronized (sInstanceMap) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            SensorsDataAPI sensorsDataAPI2 = sInstanceMap.get(origApplicationContext);
            sensorsDataAPI = sensorsDataAPI2;
            if (sensorsDataAPI2 == null) {
                sensorsDataAPI = new SensorsDataAPI(origApplicationContext, str, debugMode);
                sInstanceMap.put(origApplicationContext, sensorsDataAPI);
            }
        }
        return sensorsDataAPI;
    }

    private void initSAConfig(String str, String str2) {
        Bundle bundle;
        try {
            bundle = StubApp.getOrigApplicationContext(this.mContext.getApplicationContext()).getPackageManager().getApplicationInfo(str2, 128).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            SALog.printStackTrace(e);
            bundle = null;
        }
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        if (mSAConfigOptions == null) {
            this.mSDKConfigInit = false;
            mSAConfigOptions = new SAConfigOptions(str);
        } else {
            this.mSDKConfigInit = true;
        }
        if (mSAConfigOptions.mInvokeLog) {
            enableLog(mSAConfigOptions.mLogEnabled);
        } else {
            enableLog(bundle2.getBoolean("com.sensorsdata.analytics.android.EnableLogging", this.mDebugMode != DebugMode.DEBUG_OFF));
        }
        setServerUrl(str);
        if (mSAConfigOptions.mEnableTrackAppCrash) {
            SensorsDataExceptionHandler.enableAppCrash();
        }
        int i = mSAConfigOptions.mFlushInterval;
        this.mFlushInterval = i;
        if (i == 0) {
            this.mFlushInterval = bundle2.getInt("com.sensorsdata.analytics.android.FlushInterval", 15000);
        }
        int i2 = mSAConfigOptions.mFlushBulkSize;
        this.mFlushBulkSize = i2;
        if (i2 == 0) {
            this.mFlushBulkSize = bundle2.getInt("com.sensorsdata.analytics.android.FlushBulkSize", 100);
        }
        long j = mSAConfigOptions.mMaxCacheSize;
        this.mMaxCacheSize = j;
        if (j == 0) {
            this.mMaxCacheSize = 33554432L;
        }
        this.mAutoTrack = bundle2.getBoolean("com.sensorsdata.analytics.android.AutoTrack", false);
        if (mSAConfigOptions.mAutoTrackEventType != 0) {
            enableAutoTrack(mSAConfigOptions.mAutoTrackEventType);
            this.mAutoTrack = true;
        }
        if (mSAConfigOptions.mInvokeHeatMapEnabled) {
            this.mHeatMapEnabled = mSAConfigOptions.mHeatMapEnabled;
        } else {
            this.mHeatMapEnabled = bundle2.getBoolean("com.sensorsdata.analytics.android.HeatMap", false);
        }
        if (mSAConfigOptions.mInvokeHeatMapConfirmDialog) {
            this.mHeatMapConfirmDialogEnabled = mSAConfigOptions.mHeatMapConfirmDialogEnabled;
        } else {
            this.mHeatMapConfirmDialogEnabled = bundle2.getBoolean("com.sensorsdata.analytics.android.EnableHeatMapConfirmDialog", true);
        }
        if (mSAConfigOptions.mInvokeHeatMapSSLCheck) {
            this.mHeatMapSSLCheckEnabled = mSAConfigOptions.mHeatMapSSLChecked;
        } else {
            this.mHeatMapSSLCheckEnabled = bundle2.getBoolean("com.sensorsdata.analytics.android.HeatMapSSLCertificateCheck", true);
        }
        if (mSAConfigOptions.mInvokeVisualizedEnabled) {
            this.mVisualizedEnabled = mSAConfigOptions.mVisualizedEnabled;
        } else {
            this.mVisualizedEnabled = bundle2.getBoolean("com.sensorsdata.analytics.android.VisualizedAutoTrack", false);
        }
        if (mSAConfigOptions.mInvokeVisualizedConfirmDialog) {
            this.mVisualizedConfirmDialogEnabled = mSAConfigOptions.mVisualizedConfirmDialogEnabled;
        } else {
            this.mVisualizedConfirmDialogEnabled = bundle2.getBoolean("com.sensorsdata.analytics.android.EnableVisualizedAutoTrackConfirmDialog", true);
        }
        if (mSAConfigOptions.mInvokeVisualizedSSLCheck) {
            this.mVisualizedSSLCheckEnabled = mSAConfigOptions.mVisualizedSSLChecked;
        } else {
            this.mVisualizedSSLCheckEnabled = bundle2.getBoolean("com.sensorsdata.analytics.android.VisualizedAutoTrackSSLCertificateCheck", true);
        }
        this.mRNAutoTrackEnabled = mSAConfigOptions.mRNAutoTrackEnabled;
        this.mFlushNetworkPolicy = mSAConfigOptions.mNetworkTypePolicy;
        enableTrackScreenOrientation(mSAConfigOptions.mTrackScreenOrientationEnabled);
        if (!TextUtils.isEmpty(mSAConfigOptions.mAnonymousId)) {
            identify(mSAConfigOptions.mAnonymousId);
        }
        SHOW_DEBUG_INFO_VIEW = bundle2.getBoolean("com.sensorsdata.analytics.android.ShowDebugInfoView", true);
        this.mDisableDefaultRemoteConfig = bundle2.getBoolean("com.sensorsdata.analytics.android.DisableDefaultRemoteConfig", false);
        this.mMainProcessName = SensorsDataUtils.getMainProcessName(this.mContext);
        if (TextUtils.isEmpty(this.mMainProcessName)) {
            this.mMainProcessName = bundle2.getString("com.sensorsdata.analytics.android.MainProcessName");
        }
        mIsMainProcess = SensorsDataUtils.isMainProcess(this.mContext, this.mMainProcessName);
        this.mDisableTrackDeviceId = bundle2.getBoolean("com.sensorsdata.analytics.android.DisableTrackDeviceId", false);
    }

    private boolean isEnterDb(String str, JSONObject jSONObject) {
        Object obj;
        boolean z = true;
        if (this.mTrackEventCallBack != null) {
            SALog.m53d(TAG, "SDK have set trackEvent callBack");
            try {
                JSONObject jSONObject2 = new JSONObject();
                Iterator<String> keys = jSONObject.keys();
                ArrayList arrayList = new ArrayList();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!next.startsWith("$") || this.mFilterEventProperties.contains(next)) {
                        jSONObject2.put(next, jSONObject.opt(next));
                        arrayList.add(next);
                    }
                }
                z = this.mTrackEventCallBack.onTrackEvent(str, jSONObject2);
                if (z) {
                    try {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            jSONObject.remove((String) it.next());
                        }
                        Iterator<String> keys2 = jSONObject2.keys();
                        while (keys2.hasNext()) {
                            String next2 = keys2.next();
                            try {
                                assertKey(next2);
                                Object opt = jSONObject2.opt(next2);
                                if (!(opt instanceof CharSequence) && !(opt instanceof Number) && !(opt instanceof JSONArray) && !(opt instanceof Boolean) && !(opt instanceof Date)) {
                                    SALog.m53d(TAG, String.format("The property value must be an instance of CharSequence/Number/Boolean/JSONArray. [key='%s', value='%s']", next2, opt == null ? "" : opt.toString()));
                                    return false;
                                }
                                if ("app_crashed_reason".equals(next2)) {
                                    obj = opt;
                                    if (opt instanceof String) {
                                        obj = opt;
                                        if (((String) opt).length() > 16382) {
                                            SALog.m53d(TAG, "The property value is too long. [key='" + next2 + "', value='" + opt.toString() + "']");
                                            StringBuilder sb = new StringBuilder();
                                            sb.append(((String) opt).substring(0, 16382));
                                            sb.append("$");
                                            obj = sb.toString();
                                        }
                                    }
                                } else {
                                    obj = opt;
                                    if (opt instanceof String) {
                                        obj = opt;
                                        if (((String) opt).length() > 8191) {
                                            SALog.m53d(TAG, "The property value is too long. [key='" + next2 + "', value='" + opt.toString() + "']");
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append(((String) opt).substring(0, 8191));
                                            sb2.append("$");
                                            obj = sb2.toString();
                                        }
                                    }
                                }
                                jSONObject.put(next2, obj);
                            } catch (Exception e) {
                                SALog.printStackTrace(e);
                                return false;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        SALog.printStackTrace(e);
                        return z;
                    }
                }
                return z;
            } catch (Exception e3) {
                e = e3;
                z = true;
            }
        }
        return z;
    }

    private boolean isFirstDay(long j) {
        String str = this.mFirstDay.get();
        if (str == null) {
            return true;
        }
        try {
            if (this.mIsFirstDayDateFormat == null) {
                this.mIsFirstDayDateFormat = new SimpleDateFormat(DateFormatUtils.YYYY_MM_DD, Locale.getDefault());
            }
            return str.equals(this.mIsFirstDayDateFormat.format(Long.valueOf(j)));
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return true;
        }
    }

    public static boolean isSDKDisabled() {
        SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = mSDKRemoteConfig;
        if (sensorsDataSDKRemoteConfig == null) {
            return false;
        }
        return sensorsDataSDKRemoteConfig.isDisableSDK();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSDKRemoteConfig(SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig, boolean z) {
        try {
            if (sensorsDataSDKRemoteConfig.isDisableSDK() && !SensorsDataUtils.toSDKRemoteConfig(this.mPersistentRemoteSDKConfig.get()).isDisableSDK()) {
                track("DisableSensorsDataSDK");
            }
            this.mPersistentRemoteSDKConfig.commit(sensorsDataSDKRemoteConfig.toJson().toString());
            if (z) {
                mSDKRemoteConfig = sensorsDataSDKRemoteConfig;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private Map<String, Object> setupDeviceInfo() {
        HashMap hashMap = new HashMap();
        hashMap.put("$lib", "Android");
        hashMap.put("$lib_version", "3.2.7");
        hashMap.put("$os", "Android");
        hashMap.put("$os_version", Build.VERSION.RELEASE == null ? "UNKNOWN" : Build.VERSION.RELEASE);
        hashMap.put("$manufacturer", SensorsDataUtils.getManufacturer());
        if (TextUtils.isEmpty(Build.MODEL)) {
            hashMap.put("$model", "UNKNOWN");
        } else {
            hashMap.put("$model", Build.MODEL.trim());
        }
        try {
            hashMap.put("$app_version", this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName);
        } catch (Exception e) {
            SALog.m56i(TAG, "Exception getting app version name", e);
        }
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        int i3 = i2;
        int i4 = i;
        try {
            Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
            int rotation = defaultDisplay.getRotation();
            int i5 = i2;
            int i6 = i;
            if (Build.VERSION.SDK_INT >= 17) {
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                i6 = point.x;
                i5 = point.y;
            }
            int i7 = i5;
            hashMap.put("$screen_width", Integer.valueOf(SensorsDataUtils.getNaturalWidth(rotation, i6, i5)));
            i3 = i5;
            i4 = i6;
            hashMap.put("$screen_height", Integer.valueOf(SensorsDataUtils.getNaturalHeight(rotation, i6, i5)));
        } catch (Exception e2) {
            hashMap.put("$screen_width", Integer.valueOf(i4));
            hashMap.put("$screen_height", Integer.valueOf(i3));
        }
        String carrier = SensorsDataUtils.getCarrier(this.mContext);
        if (!TextUtils.isEmpty(carrier)) {
            hashMap.put("$carrier", carrier);
        }
        if (!this.mDisableTrackDeviceId && !TextUtils.isEmpty(this.mAndroidId)) {
            hashMap.put("$device_id", this.mAndroidId);
        }
        SensorsDataUtils.getZoneOffset();
        return Collections.unmodifiableMap(hashMap);
    }

    public static SensorsDataAPI sharedInstance() {
        if (isSDKDisabled()) {
            return new SensorsDataAPIEmptyImplementation();
        }
        synchronized (sInstanceMap) {
            if (sInstanceMap.size() > 0) {
                Iterator<SensorsDataAPI> it = sInstanceMap.values().iterator();
                if (it.hasNext()) {
                    return it.next();
                }
            }
            return new SensorsDataAPIEmptyImplementation();
        }
    }

    public static SensorsDataAPI sharedInstance(Context context) {
        if (!isSDKDisabled() && context != null) {
            synchronized (sInstanceMap) {
                SensorsDataAPI sensorsDataAPI = sInstanceMap.get(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                if (sensorsDataAPI != null) {
                    return sensorsDataAPI;
                }
                SALog.m55i(TAG, "The static method sharedInstance(context, serverURL, debugMode) should be called before calling sharedInstance()");
                return new SensorsDataAPIEmptyImplementation();
            }
        }
        return new SensorsDataAPIEmptyImplementation();
    }

    @Deprecated
    public static SensorsDataAPI sharedInstance(Context context, SAConfigOptions sAConfigOptions) {
        mSAConfigOptions = sAConfigOptions;
        SensorsDataAPI sensorsDataAPI = getInstance(context, sAConfigOptions.mServerUrl, DebugMode.DEBUG_OFF);
        if (!sensorsDataAPI.mSDKConfigInit) {
            sensorsDataAPI.applySAConfigOptions();
        }
        return sensorsDataAPI;
    }

    @Deprecated
    public static SensorsDataAPI sharedInstance(Context context, String str) {
        return getInstance(context, str, DebugMode.DEBUG_OFF);
    }

    @Deprecated
    public static SensorsDataAPI sharedInstance(Context context, String str, DebugMode debugMode) {
        return getInstance(context, str, debugMode);
    }

    private void showDebugModeWarning() {
        try {
            if (this.mDebugMode == DebugMode.DEBUG_OFF || TextUtils.isEmpty(getServerUrl())) {
                return;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.31
                @Override // java.lang.Runnable
                public void run() {
                    String str = SensorsDataAPI.this.mDebugMode == DebugMode.DEBUG_ONLY ? "现在您打开了 SensorsData SDK 的 'DEBUG_ONLY' 模式，此模式下只校验数据但不导入数据，数据出错时会以 Toast 的方式提示开发者，请上线前一定使用 DEBUG_OFF 模式。" : SensorsDataAPI.this.mDebugMode == DebugMode.DEBUG_AND_TRACK ? "现在您打开了神策 SensorsData SDK 的 'DEBUG_AND_TRACK' 模式，此模式下校验数据并且导入数据，数据出错时会以 Toast 的方式提示开发者，请上线前一定使用 DEBUG_OFF 模式。" : null;
                    CharSequence appName = SensorsDataUtils.getAppName(SensorsDataAPI.this.mContext);
                    String str2 = str;
                    if (!TextUtils.isEmpty(appName)) {
                        str2 = String.format(Locale.CHINA, "%s：%s", appName, str);
                    }
                    Toast.makeText(SensorsDataAPI.this.mContext, str2, 1).show();
                }
            });
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static void startWithConfigOptions(Context context, SAConfigOptions sAConfigOptions) {
        if (context == null || sAConfigOptions == null) {
            throw new NullPointerException("Context、SAConfigOptions 不可以为 null");
        }
        mSAConfigOptions = sAConfigOptions;
        SensorsDataAPI sensorsDataAPI = getInstance(context, sAConfigOptions.mServerUrl, DebugMode.DEBUG_OFF);
        if (sensorsDataAPI.mSDKConfigInit) {
            return;
        }
        sensorsDataAPI.applySAConfigOptions();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackEvent(EventType eventType, String str, JSONObject jSONObject, String str2) {
        EventTimer eventTimer;
        JSONObject jSONObject2;
        JSONObject dynamicSuperProperties;
        String str3 = null;
        if (str != null) {
            synchronized (this.mTrackTimer) {
                eventTimer = this.mTrackTimer.get(str);
                this.mTrackTimer.remove(str);
            }
        } else {
            eventTimer = null;
        }
        try {
            if (eventType.isTrack()) {
                assertKey(str);
            }
            assertPropertyTypes(jSONObject);
            try {
                if (eventType.isTrack()) {
                    JSONObject jSONObject3 = new JSONObject(this.mDeviceInfo);
                    try {
                        if (TextUtils.isEmpty(jSONObject3.optString("$carrier"))) {
                            String carrier = SensorsDataUtils.getCarrier(this.mContext);
                            if (!TextUtils.isEmpty(carrier)) {
                                jSONObject3.put("$carrier", carrier);
                            }
                        }
                    } catch (Exception e) {
                        SALog.printStackTrace(e);
                    }
                    synchronized (this.mSuperProperties) {
                        SensorsDataUtils.mergeJSONObject(this.mSuperProperties.get(), jSONObject3);
                    }
                    try {
                        if (this.mDynamicSuperProperties != null && (dynamicSuperProperties = this.mDynamicSuperProperties.getDynamicSuperProperties()) != null) {
                            SensorsDataUtils.mergeJSONObject(dynamicSuperProperties, jSONObject3);
                        }
                    } catch (Exception e2) {
                        SALog.printStackTrace(e2);
                    }
                    String networkType = NetworkUtils.networkType(this.mContext);
                    jSONObject3.put("$wifi", "WIFI".equals(networkType));
                    jSONObject3.put("$network_type", networkType);
                    try {
                        if (mGPSLocation != null) {
                            jSONObject3.put("$latitude", mGPSLocation.getLatitude());
                            jSONObject3.put("$longitude", mGPSLocation.getLongitude());
                        }
                    } catch (Exception e3) {
                        SALog.printStackTrace(e3);
                    }
                    try {
                        String screenOrientation = getScreenOrientation();
                        jSONObject2 = jSONObject3;
                        if (!TextUtils.isEmpty(screenOrientation)) {
                            jSONObject3.put("$screen_orientation", screenOrientation);
                            jSONObject2 = jSONObject3;
                        }
                    } catch (Exception e4) {
                        SALog.printStackTrace(e4);
                        jSONObject2 = jSONObject3;
                    }
                } else if (!eventType.isProfile()) {
                    return;
                } else {
                    jSONObject2 = new JSONObject();
                }
                String str4 = "3.2.7";
                String str5 = this.mDeviceInfo.containsKey("$app_version") ? (String) this.mDeviceInfo.get("$app_version") : "";
                long currentTimeMillis = System.currentTimeMillis();
                String str6 = str4;
                String str7 = str5;
                long j = currentTimeMillis;
                if (jSONObject != null) {
                    String str8 = null;
                    str3 = null;
                    try {
                        if (jSONObject.has("$lib_detail")) {
                            str3 = jSONObject.getString("$lib_detail");
                            str8 = str3;
                            jSONObject.remove("$lib_detail");
                        }
                    } catch (Exception e5) {
                        SALog.printStackTrace(e5);
                        str3 = str8;
                    }
                    String str9 = str4;
                    String str10 = str5;
                    long j2 = currentTimeMillis;
                    str6 = str4;
                    str7 = str5;
                    j = currentTimeMillis;
                    try {
                        if ("$AppEnd".equals(str)) {
                            long j3 = jSONObject.getLong("event_time");
                            if (j3 > 0) {
                                currentTimeMillis = j3;
                            }
                            String optString = jSONObject.optString("$lib_version");
                            String optString2 = jSONObject.optString("$app_version");
                            if (TextUtils.isEmpty(optString)) {
                                jSONObject.remove("$lib_version");
                            } else {
                                str4 = optString;
                            }
                            String str11 = str4;
                            if (TextUtils.isEmpty(optString2)) {
                                jSONObject.remove("$app_version");
                            } else {
                                str5 = optString2;
                            }
                            str9 = str4;
                            str10 = str5;
                            j2 = currentTimeMillis;
                            jSONObject.remove("event_time");
                            str6 = str4;
                            str7 = str5;
                            j = currentTimeMillis;
                        }
                    } catch (Exception e6) {
                        SALog.printStackTrace(e6);
                        j = j2;
                        str7 = str10;
                        str6 = str9;
                    }
                    SensorsDataUtils.mergeJSONObject(jSONObject, jSONObject2);
                }
                if (eventTimer != null) {
                    try {
                        Double valueOf = Double.valueOf(eventTimer.duration());
                        if (valueOf.doubleValue() > 0.0d) {
                            jSONObject2.put("event_duration", valueOf);
                        }
                    } catch (Exception e7) {
                        SALog.printStackTrace(e7);
                    }
                }
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("$lib", "Android");
                jSONObject4.put("$lib_version", str6);
                jSONObject4.put("$app_version", str7);
                JSONObject jSONObject5 = this.mSuperProperties.get();
                if (jSONObject5 != null && jSONObject5.has("$app_version")) {
                    jSONObject4.put("$app_version", jSONObject5.get("$app_version"));
                }
                JSONObject jSONObject6 = new JSONObject();
                try {
                    jSONObject6.put("_track_id", new SecureRandom().nextInt());
                } catch (Exception e8) {
                }
                jSONObject6.put(CodePushConstants.LATEST_ROLLBACK_TIME_KEY, j);
                jSONObject6.put("type", eventType.getEventType());
                try {
                    if (jSONObject2.has("$project")) {
                        jSONObject6.put("project", jSONObject2.optString("$project"));
                        jSONObject2.remove("$project");
                    }
                    if (jSONObject2.has("$token")) {
                        jSONObject6.put("token", jSONObject2.optString("$token"));
                        jSONObject2.remove("$token");
                    }
                    if (jSONObject2.has("$time")) {
                        try {
                            Object opt = jSONObject2.opt("$time");
                            if ((opt instanceof Date) && DateFormatUtils.isDateValid((Date) opt)) {
                                jSONObject6.put(CodePushConstants.LATEST_ROLLBACK_TIME_KEY, ((Date) opt).getTime());
                            }
                        } catch (Exception e9) {
                            SALog.printStackTrace(e9);
                        }
                        jSONObject2.remove("$time");
                    }
                } catch (Exception e10) {
                    SALog.printStackTrace(e10);
                }
                jSONObject6.put("distinct_id", getDistinctId());
                jSONObject6.put("lib", jSONObject4);
                if (eventType == EventType.TRACK) {
                    jSONObject6.put("event", str);
                    jSONObject2.put("$is_first_day", isFirstDay(j));
                } else if (eventType == EventType.TRACK_SIGNUP) {
                    jSONObject6.put("event", str);
                    jSONObject6.put("original_id", str2);
                }
                jSONObject4.put("$lib_method", "code");
                String str12 = str3;
                if (this.mAutoTrack) {
                    str12 = str3;
                    if (jSONObject != null) {
                        str12 = str3;
                        if (AutoTrackEventType.isAutoTrackType(str)) {
                            AutoTrackEventType autoTrackEventTypeFromEventName = AutoTrackEventType.autoTrackEventTypeFromEventName(str);
                            str12 = str3;
                            if (autoTrackEventTypeFromEventName != null) {
                                str12 = str3;
                                if (!isAutoTrackEventTypeIgnored(autoTrackEventTypeFromEventName)) {
                                    str12 = str3;
                                    if (jSONObject.has(AopConstants.SCREEN_NAME)) {
                                        String string = jSONObject.getString(AopConstants.SCREEN_NAME);
                                        str12 = str3;
                                        if (!TextUtils.isEmpty(string)) {
                                            String[] split = string.split("\\|");
                                            str12 = str3;
                                            if (split.length > 0) {
                                                str12 = String.format("%s##%s##%s##%s", split[0], "", "", "");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                String str13 = str12;
                if (TextUtils.isEmpty(str12)) {
                    StackTraceElement[] stackTrace = new Exception().getStackTrace();
                    str13 = str12;
                    if (stackTrace.length > 1) {
                        StackTraceElement stackTraceElement = stackTrace[0];
                        str13 = String.format("%s##%s##%s##%s", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()));
                    }
                }
                jSONObject4.put("$lib_detail", str13);
                if (jSONObject2.has("$device_id") && this.mDeviceInfo.containsKey("$device_id")) {
                    jSONObject2.put("$device_id", this.mDeviceInfo.get("$device_id"));
                }
                if (eventType.isTrack() && !isEnterDb(str, jSONObject2)) {
                    SALog.m53d(TAG, str + " event can not enter database");
                    return;
                }
                jSONObject6.put("properties", jSONObject2);
                this.mMessages.enqueueEventMessage(eventType.getEventType(), jSONObject6);
                if (sEnableLog) {
                    SALog.m55i(TAG, "track event:\n" + JSONUtils.formatJson(jSONObject6.toString()));
                }
            } catch (JSONException e11) {
                throw new InvalidDataException("Unexpected property");
            }
        } catch (Exception e12) {
            SALog.printStackTrace(e12);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackItemEvent(String str, String str2, String str3, JSONObject jSONObject) {
        try {
            assertKey(str);
            assertValue(str2);
            assertPropertyTypes(jSONObject);
            String str4 = null;
            if (jSONObject != null) {
                str4 = null;
                if (jSONObject.has("$project")) {
                    str4 = (String) jSONObject.get("$project");
                    jSONObject.remove("$project");
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("$lib", "Android");
            jSONObject2.put("$lib_version", "3.2.7");
            jSONObject2.put("$lib_method", "code");
            if (this.mDeviceInfo.containsKey("$app_version")) {
                jSONObject2.put("$app_version", this.mDeviceInfo.get("$app_version"));
            }
            JSONObject jSONObject3 = this.mSuperProperties.get();
            if (jSONObject3 != null && jSONObject3.has("$app_version")) {
                jSONObject2.put("$app_version", jSONObject3.get("$app_version"));
            }
            StackTraceElement[] stackTrace = new Exception().getStackTrace();
            if (stackTrace.length > 1) {
                StackTraceElement stackTraceElement = stackTrace[0];
                String format = String.format("%s##%s##%s##%s", stackTraceElement.getClassName(), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()));
                if (!TextUtils.isEmpty(format)) {
                    jSONObject2.put("$lib_detail", format);
                }
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("item_type", str);
            jSONObject4.put("item_id", str2);
            jSONObject4.put("type", str3);
            jSONObject4.put(CodePushConstants.LATEST_ROLLBACK_TIME_KEY, System.currentTimeMillis());
            jSONObject4.put("properties", DateFormatUtils.formatDate(jSONObject));
            jSONObject4.put("lib", jSONObject2);
            if (!TextUtils.isEmpty(str4)) {
                jSONObject4.put("project", str4);
            }
            this.mMessages.enqueueEventMessage(str3, jSONObject4);
            SALog.m55i(TAG, "track event:\n" + JSONUtils.formatJson(jSONObject4.toString()));
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private void trackTimerState(final String str, final boolean z) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        EventTimer eventTimer = (EventTimer) SensorsDataAPI.this.mTrackTimer.get(str);
                        if (eventTimer != null && eventTimer.isPause() != z) {
                            eventTimer.setTimerState(z);
                        }
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean _trackEventFromH5(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            String optString = new JSONObject(str).optString("server_url");
            if (TextUtils.isEmpty(optString) || !new ServerUrl(optString).check(new ServerUrl(this.mServerUrl))) {
                return false;
            }
            trackEventFromH5(str);
            return true;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return false;
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void addHeatMapActivities(List<Class<?>> list) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                for (Class<?> cls : list) {
                    if (cls != null) {
                        int hashCode = cls.hashCode();
                        if (!this.mHeatMapActivities.contains(Integer.valueOf(hashCode))) {
                            this.mHeatMapActivities.add(Integer.valueOf(hashCode));
                        }
                    }
                }
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void addHeatMapActivity(Class<?> cls) {
        if (cls == null) {
            return;
        }
        try {
            this.mHeatMapActivities.add(Integer.valueOf(cls.hashCode()));
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void addVisualizedAutoTrackActivities(List<Class<?>> list) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                for (Class<?> cls : list) {
                    if (cls != null) {
                        int hashCode = cls.hashCode();
                        if (!this.mVisualizedAutoTrackActivities.contains(Integer.valueOf(hashCode))) {
                            this.mVisualizedAutoTrackActivities.add(Integer.valueOf(hashCode));
                        }
                    }
                }
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void addVisualizedAutoTrackActivity(Class<?> cls) {
        if (cls == null) {
            return;
        }
        try {
            this.mVisualizedAutoTrackActivities.add(Integer.valueOf(cls.hashCode()));
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appBecomeActive() {
        EventTimer value;
        synchronized (this.mTrackTimer) {
            try {
                for (Map.Entry<String, EventTimer> entry : this.mTrackTimer.entrySet()) {
                    if (entry != null && (value = entry.getValue()) != null) {
                        value.setStartTime(SystemClock.elapsedRealtime());
                    }
                }
            } catch (Exception e) {
                SALog.m55i(TAG, "appBecomeActive error:" + e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appEnterBackground() {
        EventTimer value;
        synchronized (this.mTrackTimer) {
            try {
                for (Map.Entry<String, EventTimer> entry : this.mTrackTimer.entrySet()) {
                    if (entry != null && !"$AppEnd".equals(entry.getKey().toString()) && (value = entry.getValue()) != null) {
                        value.setEventAccumulatedDuration((value.getEventAccumulatedDuration() + SystemClock.elapsedRealtime()) - value.getStartTime());
                        value.setStartTime(SystemClock.elapsedRealtime());
                    }
                }
            } catch (Exception e) {
                SALog.m55i(TAG, "appEnterBackground error:" + e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void applySDKConfigFromCache() {
        try {
            SensorsDataSDKRemoteConfig sDKRemoteConfig = SensorsDataUtils.toSDKRemoteConfig(this.mPersistentRemoteSDKConfig.get());
            SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = sDKRemoteConfig;
            if (sDKRemoteConfig == null) {
                sensorsDataSDKRemoteConfig = new SensorsDataSDKRemoteConfig();
            }
            if (sensorsDataSDKRemoteConfig.isDisableDebugMode()) {
                setDebugMode(DebugMode.DEBUG_OFF);
            }
            if (sensorsDataSDKRemoteConfig.getAutoTrackEventType() != 0) {
                enableAutoTrack(sensorsDataSDKRemoteConfig.getAutoTrackEventType());
            }
            if (sensorsDataSDKRemoteConfig.isDisableSDK()) {
                try {
                    flush();
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
            mSDKRemoteConfig = sensorsDataSDKRemoteConfig;
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void clearGPSLocation() {
        mGPSLocation = null;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void clearLastScreenUrl() {
        if (this.mClearReferrerWhenAppEnd) {
            this.mLastScreenUrl = null;
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void clearReferrerWhenAppEnd() {
        this.mClearReferrerWhenAppEnd = true;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void clearSuperProperties() {
        synchronized (this.mSuperProperties) {
            this.mSuperProperties.commit(new JSONObject());
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void clearTrackTimer() {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.16
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        SensorsDataAPI.this.mTrackTimer.clear();
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void deleteAll() {
        this.mMessages.deleteAll();
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void disableAutoTrack(AutoTrackEventType autoTrackEventType) {
        ignoreAutoTrackEventType(autoTrackEventType);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void disableAutoTrack(List<AutoTrackEventType> list) {
        ignoreAutoTrackEventType(list);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableAppHeatMapConfirmDialog(boolean z) {
        this.mHeatMapConfirmDialogEnabled = z;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void enableAutoTrack() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(AutoTrackEventType.APP_START);
        arrayList.add(AutoTrackEventType.APP_END);
        arrayList.add(AutoTrackEventType.APP_VIEW_SCREEN);
        enableAutoTrack(arrayList);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableAutoTrack(List<AutoTrackEventType> list) {
        if (list != null) {
            try {
                if (list.isEmpty()) {
                    return;
                }
                this.mAutoTrack = true;
                for (AutoTrackEventType autoTrackEventType : list) {
                    this.mAutoTrackEventType = autoTrackEventType.eventValue | this.mAutoTrackEventType;
                }
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public void enableAutoTrackFragment(Class<?> cls) {
        this.mFragmentAPI.enableAutoTrackFragment(cls);
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public void enableAutoTrackFragments(List<Class<?>> list) {
        this.mFragmentAPI.enableAutoTrackFragments(list);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableHeatMap() {
        this.mHeatMapEnabled = true;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableLog(boolean z) {
        sEnableLog = z;
        SALog.setEnableLog(z);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableNetworkRequest(boolean z) {
        this.mEnableNetworkRequest = z;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableReactNativeAutoTrack() {
        this.mRNAutoTrackEnabled = true;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableTrackScreenOrientation(boolean z) {
        try {
            if (z) {
                if (this.mOrientationDetector == null) {
                    this.mOrientationDetector = new SensorsDataScreenOrientationDetector(this.mContext, 3);
                }
                this.mOrientationDetector.enable();
            } else if (this.mOrientationDetector != null) {
                this.mOrientationDetector.disable();
                this.mOrientationDetector = null;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableVisualizedAutoTrack() {
        this.mVisualizedEnabled = true;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void enableVisualizedAutoTrackConfirmDialog(boolean z) {
        this.mVisualizedConfirmDialogEnabled = z;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void flush() {
        this.mMessages.flush();
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void flushSync() {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.20
            @Override // java.lang.Runnable
            public void run() {
                SensorsDataAPI.this.mMessages.flush();
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public String getAnonymousId() {
        String str;
        synchronized (this.mDistinctId) {
            str = this.mDistinctId.get();
        }
        return str;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public String getCookie(boolean z) {
        try {
            return z ? URLDecoder.decode(this.mCookie, "UTF-8") : this.mCookie;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DebugMode getDebugMode() {
        return this.mDebugMode;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public String getDistinctId() {
        String loginId = getLoginId();
        return !TextUtils.isEmpty(loginId) ? loginId : getAnonymousId();
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public int getFlushBulkSize() {
        return this.mFlushBulkSize;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public int getFlushInterval() {
        return this.mFlushInterval;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public List<Class> getIgnoredViewTypeList() {
        if (this.mIgnoredViewTypeList == null) {
            this.mIgnoredViewTypeList = new ArrayList();
        }
        return this.mIgnoredViewTypeList;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public JSONObject getLastScreenTrackProperties() {
        return this.mLastScreenTrackProperties;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public String getLastScreenUrl() {
        return this.mLastScreenUrl;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public String getLoginId() {
        return DbAdapter.getInstance().getLoginId();
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public String getMainProcessName() {
        return this.mMainProcessName;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public long getMaxCacheSize() {
        return this.mMaxCacheSize;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public JSONObject getPresetProperties() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("$app_version", this.mDeviceInfo.get("$app_version"));
            jSONObject.put("$lib", "Android");
            jSONObject.put("$lib_version", "3.2.7");
            jSONObject.put("$manufacturer", this.mDeviceInfo.get("$manufacturer"));
            jSONObject.put("$model", this.mDeviceInfo.get("$model"));
            jSONObject.put("$os", "Android");
            jSONObject.put("$os_version", this.mDeviceInfo.get("$os_version"));
            jSONObject.put("$screen_height", this.mDeviceInfo.get("$screen_height"));
            jSONObject.put("$screen_width", this.mDeviceInfo.get("$screen_width"));
            String networkType = NetworkUtils.networkType(this.mContext);
            jSONObject.put("$wifi", "WIFI".equals(networkType));
            jSONObject.put("$network_type", networkType);
            jSONObject.put("$carrier", this.mDeviceInfo.get("$carrier"));
            jSONObject.put("$is_first_day", isFirstDay(System.currentTimeMillis()));
            if (this.mDeviceInfo.containsKey("$device_id")) {
                jSONObject.put("$device_id", this.mDeviceInfo.get("$device_id"));
                return jSONObject;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLSocketFactory getSSLSocketFactory() {
        return this.mSSLSocketFactory;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public String getScreenOrientation() {
        try {
            if (this.mOrientationDetector != null) {
                return this.mOrientationDetector.getOrientation();
            }
            return null;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getServerUrl() {
        return this.mServerUrl;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public int getSessionIntervalTime() {
        if (DbAdapter.getInstance() != null) {
            return DbAdapter.getInstance().getSessionIntervalTime();
        }
        SALog.m55i(TAG, "The static method sharedInstance(context, serverURL, debugMode) should be called before calling sharedInstance()");
        return 30000;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public JSONObject getSuperProperties() {
        JSONObject jSONObject;
        synchronized (this.mSuperProperties) {
            try {
                jSONObject = new JSONObject(this.mSuperProperties.get().toString());
            } catch (JSONException e) {
                SALog.printStackTrace(e);
                return new JSONObject();
            }
        }
        return jSONObject;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void identify(final String str) {
        try {
            assertValue(str);
            this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        synchronized (SensorsDataAPI.this.mDistinctId) {
                            SensorsDataAPI.this.mDistinctId.commit(str);
                        }
                    } catch (Exception e) {
                        SALog.printStackTrace(e);
                    }
                }
            });
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void ignoreAutoTrackActivities(List<Class<?>> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        if (this.mAutoTrackIgnoredActivities == null) {
            this.mAutoTrackIgnoredActivities = new ArrayList();
        }
        for (Class<?> cls : list) {
            if (cls != null) {
                int hashCode = cls.hashCode();
                if (!this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(hashCode))) {
                    this.mAutoTrackIgnoredActivities.add(Integer.valueOf(hashCode));
                }
            }
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void ignoreAutoTrackActivity(Class<?> cls) {
        if (cls == null) {
            return;
        }
        if (this.mAutoTrackIgnoredActivities == null) {
            this.mAutoTrackIgnoredActivities = new ArrayList();
        }
        try {
            int hashCode = cls.hashCode();
            if (this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(hashCode))) {
                return;
            }
            this.mAutoTrackIgnoredActivities.add(Integer.valueOf(hashCode));
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void ignoreAutoTrackEventType(AutoTrackEventType autoTrackEventType) {
        int i;
        if (autoTrackEventType == null || (i = this.mAutoTrackEventType) == 0) {
            return;
        }
        int i2 = i | autoTrackEventType.eventValue;
        if (i2 == autoTrackEventType.eventValue) {
            this.mAutoTrackEventType = 0;
        } else {
            this.mAutoTrackEventType = autoTrackEventType.eventValue ^ i2;
        }
        if (this.mAutoTrackEventType == 0) {
            this.mAutoTrack = false;
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void ignoreAutoTrackEventType(List<AutoTrackEventType> list) {
        if (list == null || this.mAutoTrackEventType == 0) {
            return;
        }
        for (AutoTrackEventType autoTrackEventType : list) {
            int i = this.mAutoTrackEventType;
            int i2 = autoTrackEventType.eventValue;
            int i3 = this.mAutoTrackEventType;
            if ((i | i2) == i3) {
                this.mAutoTrackEventType = autoTrackEventType.eventValue ^ i3;
            }
        }
        if (this.mAutoTrackEventType == 0) {
            this.mAutoTrack = false;
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public void ignoreAutoTrackFragment(Class<?> cls) {
        this.mFragmentAPI.ignoreAutoTrackFragment(cls);
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public void ignoreAutoTrackFragments(List<Class<?>> list) {
        this.mFragmentAPI.ignoreAutoTrackFragments(list);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void ignoreView(View view) {
        if (view != null) {
            view.setTag(C0198R.id.sensors_analytics_tag_view_ignored, "1");
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void ignoreView(View view, boolean z) {
        if (view != null) {
            view.setTag(C0198R.id.sensors_analytics_tag_view_ignored, z ? "1" : "0");
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void ignoreViewType(Class cls) {
        if (cls == null) {
            return;
        }
        if (this.mIgnoredViewTypeList == null) {
            this.mIgnoredViewTypeList = new ArrayList();
        }
        if (this.mIgnoredViewTypeList.contains(cls)) {
            return;
        }
        this.mIgnoredViewTypeList.add(cls);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isActivityAutoTrackAppClickIgnored(Class<?> cls) {
        boolean z = false;
        if (cls == null) {
            return false;
        }
        List<Integer> list = this.mAutoTrackIgnoredActivities;
        if ((list != null && list.contains(Integer.valueOf(cls.hashCode()))) || cls.getAnnotation(SensorsDataIgnoreTrackAppViewScreenAndAppClick.class) != null) {
            return true;
        }
        if (cls.getAnnotation(SensorsDataIgnoreTrackAppClick.class) != null) {
            z = true;
        }
        return z;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isActivityAutoTrackAppViewScreenIgnored(Class<?> cls) {
        boolean z = false;
        if (cls == null) {
            return false;
        }
        List<Integer> list = this.mAutoTrackIgnoredActivities;
        if ((list != null && list.contains(Integer.valueOf(cls.hashCode()))) || cls.getAnnotation(SensorsDataIgnoreTrackAppViewScreenAndAppClick.class) != null) {
            return true;
        }
        if (cls.getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class) != null) {
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAppHeatMapConfirmDialogEnabled() {
        return this.mHeatMapConfirmDialogEnabled;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isAutoTrackEnabled() {
        if (isSDKDisabled()) {
            return false;
        }
        SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = mSDKRemoteConfig;
        if (sensorsDataSDKRemoteConfig != null) {
            if (sensorsDataSDKRemoteConfig.getAutoTrackMode() == 0) {
                return false;
            }
            if (mSDKRemoteConfig.getAutoTrackMode() > 0) {
                return true;
            }
        }
        return this.mAutoTrack;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isAutoTrackEventTypeIgnored(int i) {
        SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = mSDKRemoteConfig;
        if (sensorsDataSDKRemoteConfig == null || sensorsDataSDKRemoteConfig.getAutoTrackMode() == -1) {
            int i2 = this.mAutoTrackEventType;
            return (i | i2) != i2;
        }
        if (mSDKRemoteConfig.getAutoTrackMode() == 0) {
            return true;
        }
        return mSDKRemoteConfig.isAutoTrackEventTypeIgnored(i);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isAutoTrackEventTypeIgnored(AutoTrackEventType autoTrackEventType) {
        if (autoTrackEventType == null) {
            return false;
        }
        return isAutoTrackEventTypeIgnored(autoTrackEventType.eventValue);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isDebugMode() {
        return this.mDebugMode.isDebugMode();
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public boolean isFragmentAutoTrackAppViewScreen(Class<?> cls) {
        return this.mFragmentAPI.isFragmentAutoTrackAppViewScreen(cls);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isHeatMapActivity(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        try {
            if (this.mHeatMapActivities.size() == 0) {
                return true;
            }
            return this.mHeatMapActivities.contains(Integer.valueOf(cls.hashCode()));
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return false;
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isHeatMapEnabled() {
        return this.mHeatMapEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHeatMapSSLCheckEnabled() {
        return this.mHeatMapSSLCheckEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isMultiProcess() {
        return mSAConfigOptions.enableMultiProcess;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isNetworkRequestEnable() {
        return this.mEnableNetworkRequest;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isReactNativeAutoTrackEnabled() {
        return this.mRNAutoTrackEnabled;
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public boolean isTrackFragmentAppViewScreenEnabled() {
        return this.mFragmentAPI.isTrackFragmentAppViewScreenEnabled();
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isVisualizedAutoTrackActivity(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        try {
            if (this.mVisualizedAutoTrackActivities.size() == 0) {
                return true;
            }
            return this.mVisualizedAutoTrackActivities.contains(Integer.valueOf(cls.hashCode()));
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isVisualizedAutoTrackConfirmDialogEnabled() {
        return this.mVisualizedConfirmDialogEnabled;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public boolean isVisualizedAutoTrackEnabled() {
        return this.mVisualizedEnabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isVisualizedSSLCheckEnabled() {
        return this.mVisualizedSSLCheckEnabled;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void itemDelete(final String str, final String str2) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.35
            @Override // java.lang.Runnable
            public void run() {
                SensorsDataAPI.this.trackItemEvent(str, str2, EventType.ITEM_DELETE.getEventType(), null);
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void itemSet(final String str, final String str2, final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.34
            @Override // java.lang.Runnable
            public void run() {
                SensorsDataAPI.this.trackItemEvent(str, str2, EventType.ITEM_SET.getEventType(), jSONObject);
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void login(String str) {
        login(str, null);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void login(final String str, final JSONObject jSONObject) {
        try {
            assertValue(str);
            this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        synchronized (SensorsDataAPI.this.mLoginIdLock) {
                            if (!str.equals(DbAdapter.getInstance().getLoginId())) {
                                DbAdapter.getInstance().commitLoginId(str);
                                if (!str.equals(SensorsDataAPI.this.getAnonymousId())) {
                                    SensorsDataAPI.this.trackEvent(EventType.TRACK_SIGNUP, "$SignUp", jSONObject, SensorsDataAPI.this.getAnonymousId());
                                }
                            }
                        }
                    } catch (Exception e) {
                        SALog.printStackTrace(e);
                    }
                }
            });
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void logout() {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (SensorsDataAPI.this.mLoginIdLock) {
                        DbAdapter.getInstance().commitLoginId(null);
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileAppend(final String str, final String str2) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.27
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(str2);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str, jSONArray);
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_APPEND, null, jSONObject, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileAppend(final String str, final Set<String> set) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.28
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONArray jSONArray = new JSONArray();
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        jSONArray.put((String) it.next());
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(str, jSONArray);
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_APPEND, null, jSONObject, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileDelete() {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.30
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_DELETE, null, null, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileIncrement(final String str, final Number number) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.26
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_INCREMENT, null, new JSONObject().put(str, number), null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileIncrement(final Map<String, ? extends Number> map) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.25
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_INCREMENT, null, new JSONObject(map), null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profilePushId(final String str, final String str2) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.32
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    if (TextUtils.isEmpty(str2)) {
                        SALog.m53d(SensorsDataAPI.TAG, "pushId is empty");
                        return;
                    }
                    String str3 = SensorsDataAPI.this.getDistinctId() + str2;
                    SharedPreferences sharedPreferences = SensorsDataUtils.getSharedPreferences(SensorsDataAPI.this.mContext);
                    if (sharedPreferences.getString("distinctId_" + str, "").equals(str3)) {
                        return;
                    }
                    SensorsDataAPI.this.profileSet(str, str2);
                    sharedPreferences.edit().putString("distinctId_" + str, str3).apply();
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileSet(final String str, final Object obj) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.22
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_SET, null, new JSONObject().put(str, obj), null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileSet(final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.21
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_SET, null, jSONObject, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileSetOnce(final String str, final Object obj) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.24
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_SET_ONCE, null, new JSONObject().put(str, obj), null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileSetOnce(final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.23
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_SET_ONCE, null, jSONObject, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileUnset(final String str) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.29
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.PROFILE_UNSET, null, new JSONObject().put(str, true), null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void profileUnsetPushId(final String str) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.33
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    String distinctId = SensorsDataAPI.this.getDistinctId();
                    SharedPreferences sharedPreferences = SensorsDataUtils.getSharedPreferences(SensorsDataAPI.this.mContext);
                    String str2 = "distinctId_" + str;
                    if (sharedPreferences.getString(str2, "").startsWith(distinctId)) {
                        SensorsDataAPI.this.profileUnset(str);
                        sharedPreferences.edit().remove(str2).apply();
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pullSDKConfigFromServer() {
        if (isNetworkRequestEnable()) {
            SAConfigOptions sAConfigOptions = mSAConfigOptions;
            if ((sAConfigOptions == null || SensorsDataUtils.isRequestValid(this.mContext, sAConfigOptions.mMinRequestInterval, mSAConfigOptions.mMaxRequestInterval)) && !this.mDisableDefaultRemoteConfig) {
                CountDownTimer countDownTimer = this.mPullSDKConfigCountDownTimer;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    this.mPullSDKConfigCountDownTimer = null;
                }
                this.mPullSDKConfigCountDownTimer = new CountDownTimer(120000L, 30000L) { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.1
                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                    }

                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        new Thread(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.1.1
                            /* JADX WARN: Removed duplicated region for block: B:87:0x02a3 A[Catch: Exception -> 0x02b0, TRY_ENTER, TryCatch #0 {Exception -> 0x02b0, blocks: (B:93:0x0299, B:87:0x02a3), top: B:92:0x0299 }] */
                            /* JADX WARN: Removed duplicated region for block: B:92:0x0299 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                            @Override // java.lang.Runnable
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct code enable 'Show inconsistent code' option in preferences
                            */
                            public void run() {
                                /*
                                    Method dump skipped, instructions count: 704
                                    To view this dump change 'Code comments level' option to 'DEBUG'
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.CountDownTimerC01991.AnonymousClass1.run():void");
                            }
                        }).start();
                    }
                };
                this.mPullSDKConfigCountDownTimer.start();
            }
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void registerDynamicSuperProperties(SensorsDataDynamicSuperProperties sensorsDataDynamicSuperProperties) {
        this.mDynamicSuperProperties = sensorsDataDynamicSuperProperties;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void registerSuperProperties(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            assertPropertyTypes(jSONObject);
            synchronized (this.mSuperProperties) {
                JSONObject jSONObject2 = this.mSuperProperties.get();
                SensorsDataUtils.mergeSuperJSONObject(jSONObject, jSONObject2);
                this.mSuperProperties.commit(jSONObject2);
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void removeTimer(final String str) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        SensorsDataAPI.this.mTrackTimer.remove(str);
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void resetAnonymousId() {
        synchronized (this.mDistinctId) {
            if (SensorsDataUtils.isValidAndroidId(this.mAndroidId)) {
                this.mDistinctId.commit(this.mAndroidId);
            } else {
                this.mDistinctId.commit(UUID.randomUUID().toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetPullSDKConfigTimer() {
        try {
            try {
                if (this.mPullSDKConfigCountDownTimer != null) {
                    this.mPullSDKConfigCountDownTimer.cancel();
                }
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        } finally {
            this.mPullSDKConfigCountDownTimer = null;
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void resumeAutoTrackActivities(List<Class<?>> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        if (this.mAutoTrackIgnoredActivities == null) {
            this.mAutoTrackIgnoredActivities = new ArrayList();
        }
        try {
            for (Class<?> cls : list) {
                if (cls != null) {
                    int hashCode = cls.hashCode();
                    if (this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(hashCode))) {
                        this.mAutoTrackIgnoredActivities.remove(Integer.valueOf(hashCode));
                    }
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void resumeAutoTrackActivity(Class<?> cls) {
        if (cls == null) {
            return;
        }
        if (this.mAutoTrackIgnoredActivities == null) {
            this.mAutoTrackIgnoredActivities = new ArrayList();
        }
        try {
            int hashCode = cls.hashCode();
            if (this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(hashCode))) {
                this.mAutoTrackIgnoredActivities.remove(Integer.valueOf(hashCode));
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public void resumeIgnoredAutoTrackFragment(Class<?> cls) {
        this.mFragmentAPI.resumeIgnoredAutoTrackFragment(cls);
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public void resumeIgnoredAutoTrackFragments(List<Class<?>> list) {
        this.mFragmentAPI.resumeIgnoredAutoTrackFragments(list);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void resumeTrackScreenOrientation() {
        try {
            if (this.mOrientationDetector != null) {
                this.mOrientationDetector.enable();
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    void resumeTrackTaskThread() {
        this.mTrackTaskManagerThread = new TrackTaskManagerThread();
        new Thread(this.mTrackTaskManagerThread).start();
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setCookie(String str, boolean z) {
        try {
            if (z) {
                this.mCookie = URLEncoder.encode(str, "UTF-8");
            } else {
                this.mCookie = str;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDebugMode(DebugMode debugMode) {
        this.mDebugMode = debugMode;
        if (debugMode == DebugMode.DEBUG_OFF) {
            enableLog(false);
            SALog.setDebug(false);
            this.mServerUrl = this.mOriginServerUrl;
        } else {
            enableLog(true);
            SALog.setDebug(true);
            setServerUrl(this.mOriginServerUrl);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setFlushBulkSize(int i) {
        this.mFlushBulkSize = Math.max(50, i);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setFlushInterval(int i) {
        this.mFlushInterval = Math.max(5000, i);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setFlushNetworkPolicy(int i) {
        this.mFlushNetworkPolicy = i;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setGPSLocation(double d, double d2) {
        try {
            if (mGPSLocation == null) {
                mGPSLocation = new SensorsDataGPSLocation();
            }
            mGPSLocation.setLatitude((long) (d * Math.pow(10.0d, 6.0d)));
            mGPSLocation.setLongitude((long) (d2 * Math.pow(10.0d, 6.0d)));
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setMaxCacheSize(long j) {
        if (j > 0) {
            this.mMaxCacheSize = Math.max(16777216L, j);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.mSSLSocketFactory = sSLSocketFactory;
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setServerUrl(String str) {
        try {
            this.mOriginServerUrl = str;
            if (TextUtils.isEmpty(str)) {
                this.mServerUrl = str;
                return;
            }
            Uri parse = Uri.parse(str);
            String host = parse.getHost();
            if (!TextUtils.isEmpty(host) && host.contains(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)) {
                SALog.m55i(TAG, "Server url " + str + " contains '_' is not recommend，see details: https://en.wikipedia.org/wiki/Hostname");
            }
            if (this.mDebugMode == DebugMode.DEBUG_OFF) {
                this.mServerUrl = str;
                return;
            }
            String path = parse.getPath();
            if (TextUtils.isEmpty(path)) {
                return;
            }
            int lastIndexOf = path.lastIndexOf(47);
            if (lastIndexOf != -1) {
                this.mServerUrl = parse.buildUpon().path(path.substring(0, lastIndexOf) + "/debug").build().toString();
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setSessionIntervalTime(int i) {
        if (DbAdapter.getInstance() == null) {
            SALog.m55i(TAG, "The static method sharedInstance(context, serverURL, debugMode) should be called before calling sharedInstance()");
            return;
        }
        if (i >= 10000 && i <= 300000) {
            DbAdapter.getInstance().commitSessionIntervalTime(i);
            return;
        }
        SALog.m55i(TAG, "SessionIntervalTime:" + i + " is invalid, session interval time is between 10s and 300s.");
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setTrackEventCallBack(SensorsDataTrackEventCallBack sensorsDataTrackEventCallBack) {
        this.mTrackEventCallBack = sensorsDataTrackEventCallBack;
        if (this.mFilterEventProperties == null) {
            this.mFilterEventProperties = new HashSet<>();
            this.mFilterEventProperties.add("$device_id");
            this.mFilterEventProperties.add(AopConstants.ELEMENT_ID);
            this.mFilterEventProperties.add(AopConstants.SCREEN_NAME);
            this.mFilterEventProperties.add(AopConstants.TITLE);
            this.mFilterEventProperties.add(AopConstants.ELEMENT_POSITION);
            this.mFilterEventProperties.add(AopConstants.ELEMENT_CONTENT);
            this.mFilterEventProperties.add(AopConstants.ELEMENT_TYPE);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setViewActivity(View view, Activity activity) {
        if (view == null || activity == null) {
            return;
        }
        try {
            view.setTag(C0198R.id.sensors_analytics_tag_view_activity, activity);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setViewFragmentName(View view, String str) {
        if (view != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                view.setTag(C0198R.id.sensors_analytics_tag_view_fragment_name2, str);
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setViewID(Dialog dialog, String str) {
        if (dialog != null) {
            try {
                if (TextUtils.isEmpty(str) || dialog.getWindow() == null) {
                    return;
                }
                dialog.getWindow().getDecorView().setTag(C0198R.id.sensors_analytics_tag_view_id, str);
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setViewID(View view, String str) {
        if (view == null || TextUtils.isEmpty(str)) {
            return;
        }
        view.setTag(C0198R.id.sensors_analytics_tag_view_id, str);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setViewID(Object obj, String str) {
        Class<?> cls;
        Method method;
        Window window;
        if (obj == null) {
            return;
        }
        Class<?> cls2 = null;
        try {
            cls = Class.forName("android.support.v7.app.AlertDialog");
        } catch (Exception e) {
            cls = null;
        }
        try {
            cls2 = Class.forName("androidx.appcompat.app.AlertDialog");
        } catch (Exception e2) {
        }
        if (cls != null) {
            cls2 = cls;
        }
        if (cls2 == null) {
            return;
        }
        try {
            if (!cls2.isInstance(obj) || TextUtils.isEmpty(str) || (method = obj.getClass().getMethod("getWindow", new Class[0])) == null || (window = (Window) method.invoke(obj, new Object[0])) == null) {
                return;
            }
            window.getDecorView().setTag(C0198R.id.sensors_analytics_tag_view_id, str);
        } catch (Exception e3) {
            SALog.printStackTrace(e3);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void setViewProperties(View view, JSONObject jSONObject) {
        if (view == null || jSONObject == null) {
            return;
        }
        view.setTag(C0198R.id.sensors_analytics_tag_view_properties, jSONObject);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, JSONObject jSONObject, boolean z, boolean z2) {
        if (Build.VERSION.SDK_INT < 17 && !z) {
            SALog.m53d(TAG, "For applications targeted to API level JELLY_BEAN or below, this feature NOT SUPPORTED");
        } else if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new AppWebViewInterface(this.mContext, jSONObject, z2), "SensorsData_APP_JS_Bridge");
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean z) {
        showUpWebView(webView, z, (JSONObject) null);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean z, JSONObject jSONObject) {
        showUpWebView(webView, jSONObject, z, false);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean z, boolean z2) {
        showUpWebView(webView, null, z, z2);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void showUpX5WebView(Object obj) {
        showUpX5WebView(obj, false);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void showUpX5WebView(Object obj, JSONObject jSONObject, boolean z, boolean z2) {
        Method method;
        try {
            if (Build.VERSION.SDK_INT < 17 && !z) {
                SALog.m53d(TAG, "For applications targeted to API level JELLY_BEAN or below, this feature NOT SUPPORTED");
            } else {
                if (obj == null || (method = obj.getClass().getMethod("addJavascriptInterface", Object.class, String.class)) == null) {
                    return;
                }
                method.invoke(obj, new AppWebViewInterface(this.mContext, jSONObject, z2), "SensorsData_APP_JS_Bridge");
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void showUpX5WebView(Object obj, boolean z) {
        if (obj == null) {
            return;
        }
        try {
            Method method = obj.getClass().getMethod("addJavascriptInterface", Object.class, String.class);
            if (method == null) {
                return;
            }
            method.invoke(obj, new AppWebViewInterface(this.mContext, null, z), "SensorsData_APP_JS_Bridge");
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void stopTrackScreenOrientation() {
        try {
            if (this.mOrientationDetector != null) {
                this.mOrientationDetector.disable();
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    void stopTrackTaskThread() {
        this.mTrackTaskManagerThread.setStop(true);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void track(final String str) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.9
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.TRACK, str, null, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void track(final String str, final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.TRACK, str, jSONObject, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackAppCrash() {
        SensorsDataExceptionHandler.enableAppCrash();
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackEventFromH5(String str) {
        JSONObject dynamicSuperProperties;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("_hybrid_h5", true);
            String string = jSONObject.getString("type");
            EventType valueOf = EventType.valueOf(string.toUpperCase(Locale.getDefault()));
            if (valueOf == EventType.TRACK_SIGNUP) {
                jSONObject.put("original_id", getAnonymousId());
            } else if (TextUtils.isEmpty(getLoginId())) {
                jSONObject.put("distinct_id", getAnonymousId());
            } else {
                jSONObject.put("distinct_id", getLoginId());
            }
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put(CodePushConstants.LATEST_ROLLBACK_TIME_KEY, currentTimeMillis);
            try {
                jSONObject.put("_track_id", new SecureRandom().nextInt());
            } catch (Exception e) {
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("properties");
            JSONObject jSONObject2 = optJSONObject;
            if (optJSONObject == null) {
                jSONObject2 = new JSONObject();
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("lib");
            if (optJSONObject2 != null) {
                if (this.mDeviceInfo.containsKey("$app_version")) {
                    optJSONObject2.put("$app_version", this.mDeviceInfo.get("$app_version"));
                }
                JSONObject jSONObject3 = this.mSuperProperties.get();
                if (jSONObject3 != null && jSONObject3.has("$app_version")) {
                    optJSONObject2.put("$app_version", jSONObject3.get("$app_version"));
                }
            }
            if (valueOf.isTrack()) {
                if (this.mDeviceInfo != null) {
                    for (Map.Entry<String, Object> entry : this.mDeviceInfo.entrySet()) {
                        String key = entry.getKey();
                        if (!TextUtils.isEmpty(key) && !"$lib".equals(key) && !"$lib_version".equals(key)) {
                            jSONObject2.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
                String networkType = NetworkUtils.networkType(this.mContext);
                jSONObject2.put("$wifi", "WIFI".equals(networkType));
                jSONObject2.put("$network_type", networkType);
                synchronized (this.mSuperProperties) {
                    SensorsDataUtils.mergeJSONObject(this.mSuperProperties.get(), jSONObject2);
                }
                try {
                    if (this.mDynamicSuperProperties != null && (dynamicSuperProperties = this.mDynamicSuperProperties.getDynamicSuperProperties()) != null) {
                        SensorsDataUtils.mergeJSONObject(dynamicSuperProperties, jSONObject2);
                    }
                } catch (Exception e2) {
                    SALog.printStackTrace(e2);
                }
                if (valueOf.isTrack()) {
                    jSONObject2.put("$is_first_day", isFirstDay(currentTimeMillis));
                }
            }
            if (jSONObject.has("_nocache")) {
                jSONObject.remove("_nocache");
            }
            if (jSONObject.has("server_url")) {
                jSONObject.remove("server_url");
            }
            if (jSONObject2.has("$project")) {
                jSONObject.put("project", jSONObject2.optString("$project"));
                jSONObject2.remove("$project");
            }
            if (jSONObject2.has("$token")) {
                jSONObject.put("token", jSONObject2.optString("$token"));
                jSONObject2.remove("$token");
            }
            if (jSONObject2.has("$time")) {
                try {
                    long j = jSONObject2.getLong("$time");
                    if (DateFormatUtils.isDateValid(j)) {
                        jSONObject.put(CodePushConstants.LATEST_ROLLBACK_TIME_KEY, j);
                    }
                } catch (Exception e3) {
                    SALog.printStackTrace(e3);
                }
                jSONObject2.remove("$time");
            }
            String optString = jSONObject.optString("event");
            if (valueOf.isTrack() && !isEnterDb(optString, jSONObject2)) {
                SALog.m53d(TAG, optString + " event can not enter database");
                return;
            }
            jSONObject.put("properties", jSONObject2);
            if (valueOf == EventType.TRACK_SIGNUP) {
                String string2 = jSONObject.getString("distinct_id");
                synchronized (this.mLoginIdLock) {
                    if (!string2.equals(DbAdapter.getInstance().getLoginId())) {
                        DbAdapter.getInstance().commitLoginId(string2);
                        if (!string2.equals(getAnonymousId())) {
                            this.mMessages.enqueueEventMessage(string, jSONObject);
                        }
                    }
                }
            } else {
                this.mMessages.enqueueEventMessage(string, jSONObject);
            }
            if (sEnableLog) {
                SALog.m55i(TAG, "track event:\n" + JSONUtils.formatJson(jSONObject.toString()));
            }
        } catch (Exception e4) {
            SALog.printStackTrace(e4);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackEventFromH5(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (z) {
                String optString = jSONObject.optString("server_url");
                if (TextUtils.isEmpty(optString) || !new ServerUrl(optString).check(new ServerUrl(this.mServerUrl))) {
                    return;
                }
            }
            trackEventFromH5(str);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.internal.IFragmentAPI
    public void trackFragmentAppViewScreen() {
        this.mFragmentAPI.trackFragmentAppViewScreen();
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackInstallation(String str) {
        trackInstallation(str, null, false);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackInstallation(String str, JSONObject jSONObject) {
        trackInstallation(str, jSONObject, false);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackInstallation(final String str, JSONObject jSONObject, final boolean z) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        final JSONObject jSONObject2 = jSONObject;
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!SensorsDataAPI.mIsMainProcess) {
                        return;
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
                try {
                    if (z ? SensorsDataAPI.this.mFirstTrackInstallationWithCallback.get().booleanValue() : SensorsDataAPI.this.mFirstTrackInstallation.get().booleanValue()) {
                        try {
                            if (!SensorsDataUtils.hasUtmProperties(jSONObject2)) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("SENSORS_ANALYTICS_UTM_SOURCE", "$utm_source");
                                hashMap.put("SENSORS_ANALYTICS_UTM_MEDIUM", "$utm_medium");
                                hashMap.put("SENSORS_ANALYTICS_UTM_TERM", "$utm_term");
                                hashMap.put("SENSORS_ANALYTICS_UTM_CONTENT", "$utm_content");
                                hashMap.put("SENSORS_ANALYTICS_UTM_CAMPAIGN", "$utm_campaign");
                                for (Map.Entry entry : hashMap.entrySet()) {
                                    if (entry != null) {
                                        String applicationMetaData = SensorsDataUtils.getApplicationMetaData(SensorsDataAPI.this.mContext, (String) entry.getKey());
                                        if (!TextUtils.isEmpty(applicationMetaData)) {
                                            jSONObject2.put((String) entry.getValue(), applicationMetaData);
                                        }
                                    }
                                }
                            }
                            if (!SensorsDataUtils.hasUtmProperties(jSONObject2)) {
                                String format = String.format("android_id=%s##imei=%s##imei_old=%s##mac=%s##oaid=%s", SensorsDataAPI.this.mAndroidId, SensorsDataUtils.getIMEI(SensorsDataAPI.this.mContext), SensorsDataUtils.getIMEIOld(SensorsDataAPI.this.mContext), SensorsDataUtils.getMacAddress(SensorsDataAPI.this.mContext), SADeviceUtils.getOAID(SensorsDataAPI.this.mContext));
                                String str2 = format;
                                if (jSONObject2.has("$gaid")) {
                                    str2 = String.format("%s##gaid=%s", format, jSONObject2.optString("$gaid"));
                                }
                                jSONObject2.put("$ios_install_source", str2);
                            }
                            if (jSONObject2.has("$gaid")) {
                                jSONObject2.remove("$gaid");
                            }
                            if (z) {
                                jSONObject2.put("$ios_install_disable_callback", z);
                            }
                        } catch (Exception e2) {
                            SALog.printStackTrace(e2);
                        }
                        SensorsDataAPI.this.trackEvent(EventType.TRACK, str, jSONObject2, null);
                        JSONObject jSONObject3 = new JSONObject();
                        SensorsDataUtils.mergeJSONObject(jSONObject2, jSONObject3);
                        jSONObject3.put("$first_visit_time", new Date());
                        SensorsDataAPI.this.trackEvent(EventType.PROFILE_SET_ONCE, null, jSONObject3, null);
                        if (z) {
                            SensorsDataAPI.this.mFirstTrackInstallationWithCallback.commit(false);
                        } else {
                            SensorsDataAPI.this.mFirstTrackInstallation.commit(false);
                        }
                    }
                    SensorsDataAPI.this.flush();
                } catch (Exception e3) {
                    SALog.printStackTrace(e3);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void trackSignUp(final String str) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String anonymousId = SensorsDataAPI.this.getAnonymousId();
                    synchronized (SensorsDataAPI.this.mDistinctId) {
                        SensorsDataAPI.this.mDistinctId.commit(str);
                    }
                    SensorsDataAPI.this.trackEvent(EventType.TRACK_SIGNUP, "$SignUp", null, anonymousId);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void trackSignUp(final String str, final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String anonymousId = SensorsDataAPI.this.getAnonymousId();
                    synchronized (SensorsDataAPI.this.mDistinctId) {
                        SensorsDataAPI.this.mDistinctId.commit(str);
                    }
                    SensorsDataAPI.this.trackEvent(EventType.TRACK_SIGNUP, "$SignUp", jSONObject, anonymousId);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void trackTimer(String str) {
        trackTimer(str, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void trackTimer(final String str, final EventTimer eventTimer) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        SensorsDataAPI.this.mTrackTimer.put(str, eventTimer);
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void trackTimer(final String str, final TimeUnit timeUnit) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.assertKey(str);
                    synchronized (SensorsDataAPI.this.mTrackTimer) {
                        SensorsDataAPI.this.mTrackTimer.put(str, new EventTimer(timeUnit));
                    }
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void trackTimerBegin(String str) {
        trackTimer(str);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    @Deprecated
    public void trackTimerBegin(String str, TimeUnit timeUnit) {
        trackTimer(str, timeUnit);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackTimerEnd(final String str) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.15
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.TRACK, str, null, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackTimerEnd(final String str, final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.14
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SensorsDataAPI.this.trackEvent(EventType.TRACK, str, jSONObject, null);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackTimerPause(String str) {
        trackTimerState(str, true);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackTimerResume(String str) {
        trackTimerState(str, false);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackTimerStart(String str) {
        trackTimerBegin(str, TimeUnit.SECONDS);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackViewAppClick(View view) {
        trackViewAppClick(view, null);
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackViewAppClick(View view, JSONObject jSONObject) {
        if (view == null) {
            return;
        }
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject == null) {
            jSONObject2 = new JSONObject();
        }
        if (AopUtil.injectClickInfo(view, jSONObject2, true)) {
            track(AopConstants.APP_CLICK_EVENT_NAME, jSONObject2);
        }
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackViewScreen(final Activity activity) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.18
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (activity == null) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                    SensorsDataUtils.getScreenNameAndTitleFromActivity(jSONObject, activity);
                    if (!(activity instanceof ScreenAutoTracker)) {
                        SensorsDataAPI.this.track("$AppViewScreen", jSONObject);
                        return;
                    }
                    ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) activity;
                    String screenUrl = screenAutoTracker.getScreenUrl();
                    JSONObject trackProperties = screenAutoTracker.getTrackProperties();
                    if (trackProperties != null) {
                        SensorsDataUtils.mergeJSONObject(trackProperties, jSONObject);
                    }
                    SensorsDataAPI.this.trackViewScreen(screenUrl, jSONObject);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackViewScreen(final Object obj) {
        Class<?> cls;
        Class<?> cls2;
        if (obj == null) {
            return;
        }
        Class<?> cls3 = null;
        try {
            cls = Class.forName("android.support.v4.app.Fragment");
        } catch (Exception e) {
            cls = null;
        }
        try {
            cls2 = Class.forName("android.app.Fragment");
        } catch (Exception e2) {
            cls2 = null;
        }
        try {
            cls3 = Class.forName("androidx.fragment.app.Fragment");
        } catch (Exception e3) {
        }
        if ((cls == null || !cls.isInstance(obj)) && ((cls2 == null || !cls2.isInstance(obj)) && (cls3 == null || !cls3.isInstance(obj)))) {
            return;
        }
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.19
            @Override // java.lang.Runnable
            public void run() {
                Activity activity;
                SensorsDataFragmentTitle sensorsDataFragmentTitle;
                try {
                    JSONObject jSONObject = new JSONObject();
                    String canonicalName = obj.getClass().getCanonicalName();
                    String title = (!obj.getClass().isAnnotationPresent(SensorsDataFragmentTitle.class) || (sensorsDataFragmentTitle = (SensorsDataFragmentTitle) obj.getClass().getAnnotation(SensorsDataFragmentTitle.class)) == null) ? null : sensorsDataFragmentTitle.title();
                    String str = canonicalName;
                    String str2 = title;
                    if (Build.VERSION.SDK_INT >= 11) {
                        try {
                            Method method = obj.getClass().getMethod("getActivity", new Class[0]);
                            activity = null;
                            if (method != null) {
                                activity = (Activity) method.invoke(obj, new Object[0]);
                            }
                        } catch (Exception e4) {
                            activity = null;
                        }
                        str = canonicalName;
                        str2 = title;
                        if (activity != null) {
                            str2 = title;
                            if (TextUtils.isEmpty(title)) {
                                str2 = SensorsDataUtils.getActivityTitle(activity);
                            }
                            str = String.format(Locale.CHINA, "%s|%s", activity.getClass().getCanonicalName(), canonicalName);
                        }
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put(AopConstants.TITLE, str2);
                    }
                    jSONObject.put(AopConstants.SCREEN_NAME, str);
                    if (!(obj instanceof ScreenAutoTracker)) {
                        SensorsDataAPI.this.track("$AppViewScreen", jSONObject);
                        return;
                    }
                    ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) obj;
                    String screenUrl = screenAutoTracker.getScreenUrl();
                    JSONObject trackProperties = screenAutoTracker.getTrackProperties();
                    if (trackProperties != null) {
                        SensorsDataUtils.mergeJSONObject(trackProperties, jSONObject);
                    }
                    SensorsDataAPI.this.trackViewScreen(screenUrl, jSONObject);
                } catch (Exception e5) {
                    SALog.printStackTrace(e5);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void trackViewScreen(final String str, final JSONObject jSONObject) {
        this.mTrackTaskManager.addTrackEventTask(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.17
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (TextUtils.isEmpty(str) && jSONObject == null) {
                        return;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    SensorsDataAPI.this.mLastScreenTrackProperties = jSONObject;
                    if (!TextUtils.isEmpty(SensorsDataAPI.this.mLastScreenUrl)) {
                        jSONObject2.put("$referrer", SensorsDataAPI.this.mLastScreenUrl);
                    }
                    jSONObject2.put("$url", str);
                    SensorsDataAPI.this.mLastScreenUrl = str;
                    if (jSONObject != null) {
                        SensorsDataUtils.mergeJSONObject(jSONObject, jSONObject2);
                    }
                    SensorsDataAPI.this.track("$AppViewScreen", jSONObject2);
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
        });
    }

    @Override // com.sensorsdata.analytics.android.sdk.ISensorsDataAPI
    public void unregisterSuperProperty(String str) {
        try {
            synchronized (this.mSuperProperties) {
                JSONObject jSONObject = this.mSuperProperties.get();
                jSONObject.remove(str);
                this.mSuperProperties.commit(jSONObject);
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }
}
