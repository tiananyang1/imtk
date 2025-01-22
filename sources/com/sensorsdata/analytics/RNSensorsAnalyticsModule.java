package com.sensorsdata.analytics;

import android.text.TextUtils;
import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/RNSensorsAnalyticsModule.class */
public class RNSensorsAnalyticsModule extends ReactContextBaseJavaModule {
    private static final String LOGTAG = "SA.RN";
    private static final String MODULE_NAME = "RNSensorsAnalyticsModule";
    private static final String MODULE_VERSION = "1.1.0";

    public RNSensorsAnalyticsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private JSONObject convertToJSONObject(ReadableMap readableMap) {
        ReadableNativeMap readableNativeMap;
        if (readableMap == null) {
            return null;
        }
        try {
            readableNativeMap = (ReadableNativeMap) readableMap;
        } catch (Exception e) {
            e = e;
            readableNativeMap = null;
        }
        try {
            return new JSONObject(readableMap.toString()).getJSONObject("NativeMap");
        } catch (Exception e2) {
            e = e2;
            Log.e(LOGTAG, "" + e.getMessage());
            try {
                return new JSONObject(readableMap.toString()).getJSONObject(readableNativeMap.getClass().getSuperclass().getSimpleName());
            } catch (Exception e3) {
                Log.e(LOGTAG, "" + e3.getMessage());
                return null;
            }
        }
    }

    @ReactMethod
    public void clearSuperProperties() {
        try {
            SensorsDataAPI.sharedInstance().clearSuperProperties();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void clearTrackTimer() {
        try {
            SensorsDataAPI.sharedInstance().clearTrackTimer();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void getAnonymousIdPromise(Promise promise) {
        try {
            promise.resolve(SensorsDataAPI.sharedInstance().getAnonymousId());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
            promise.reject("getDistinctId fail", e);
        }
    }

    @ReactMethod
    public void getDistinctId(Callback callback, Callback callback2) {
        try {
            String loginId = SensorsDataAPI.sharedInstance().getLoginId();
            if (TextUtils.isEmpty(loginId)) {
                callback.invoke(new Object[]{SensorsDataAPI.sharedInstance().getAnonymousId()});
            } else {
                callback.invoke(new Object[]{loginId});
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
            callback2.invoke(new Object[]{e.getMessage()});
        }
    }

    @ReactMethod
    public void getDistinctIdPromise(Promise promise) {
        try {
            String loginId = SensorsDataAPI.sharedInstance().getLoginId();
            if (TextUtils.isEmpty(loginId)) {
                promise.resolve(SensorsDataAPI.sharedInstance().getAnonymousId());
            } else {
                promise.resolve(loginId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
            promise.reject("getDistinctId fail", e);
        }
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void login(String str) {
        try {
            SensorsDataAPI.sharedInstance().login(str);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void logout() {
        try {
            SensorsDataAPI.sharedInstance().logout();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void profileAppend(String str, String str2) {
        try {
            SensorsDataAPI.sharedInstance().profileAppend(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void profileDelete() {
        try {
            SensorsDataAPI.sharedInstance().profileDelete();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void profileIncrement(String str, Double d) {
        try {
            SensorsDataAPI.sharedInstance().profileIncrement(str, d);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void profileSet(ReadableMap readableMap) {
        try {
            SensorsDataAPI.sharedInstance().profileSet(convertToJSONObject(readableMap));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void profileSetOnce(ReadableMap readableMap) {
        try {
            SensorsDataAPI.sharedInstance().profileSetOnce(convertToJSONObject(readableMap));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void profileUnset(String str) {
        try {
            SensorsDataAPI.sharedInstance().profileUnset(str);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void registerSuperProperties(ReadableMap readableMap) {
        try {
            SensorsDataAPI.sharedInstance().registerSuperProperties(convertToJSONObject(readableMap));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void track(String str, ReadableMap readableMap) {
        try {
            SensorsDataAPI.sharedInstance().track(str, convertToJSONObject(readableMap));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void trackInstallation(String str, ReadableMap readableMap) {
        try {
            SensorsDataAPI.sharedInstance().trackInstallation(str, convertToJSONObject(readableMap));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void trackTimerBegin(String str) {
        try {
            SensorsDataAPI.sharedInstance().trackTimerBegin(str);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void trackTimerEnd(String str, ReadableMap readableMap) {
        try {
            SensorsDataAPI.sharedInstance().trackTimerEnd(str, convertToJSONObject(readableMap));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void trackTimerStart(String str) {
        try {
            SensorsDataAPI.sharedInstance().trackTimerStart(str);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void trackViewScreen(String str, ReadableMap readableMap) {
        try {
            SensorsDataAPI.sharedInstance().trackViewScreen(str, convertToJSONObject(readableMap));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }

    @ReactMethod
    public void unregisterSuperProperty(String str) {
        try {
            SensorsDataAPI.sharedInstance().unregisterSuperProperty(str);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOGTAG, e.toString() + "");
        }
    }
}
