package com.sensorsdata.analytics.android.sdk.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.C0198R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataSDKRemoteConfig;
import com.stub.StubApp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/util/SensorsDataUtils.class */
public final class SensorsDataUtils {
    private static final String SHARED_PREF_DEVICE_ID_KEY = "sensorsdata.device.id";
    private static final String SHARED_PREF_EDITS_FILE = "sensorsdata";
    private static final String SHARED_PREF_REQUEST_TIME = "sensorsdata.request.time";
    private static final String SHARED_PREF_REQUEST_TIME_RANDOM = "sensorsdata.request.time.random";
    private static final String SHARED_PREF_USER_AGENT_KEY = "sensorsdata.user.agent";
    private static final String TAG = "SA.SensorsDataUtils";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";
    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final Map<String, String> sCarrierMap = new HashMap<String, String>() { // from class: com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.1
        {
            put("46000", "中国移动");
            put("46002", "中国移动");
            put("46007", "中国移动");
            put("46008", "中国移动");
            put("46001", "中国联通");
            put("46006", "中国联通");
            put("46009", "中国联通");
            put("46003", "中国电信");
            put("46005", "中国电信");
            put("46011", "中国电信");
            put("46004", "中国卫通");
            put("46020", "中国铁通");
        }
    };
    private static final List<String> sManufacturer = new ArrayList<String>() { // from class: com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.2
        {
            add("HUAWEI");
            add("OPPO");
            add("vivo");
        }
    };
    private static final List<String> mInvalidAndroidId = new ArrayList<String>() { // from class: com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.3
        {
            add("9774d56d682e549c");
            add("0123456789abcdef");
        }
    };

    public static boolean checkHasPermission(Context context, String str) {
        Class<?> cls;
        try {
            cls = Class.forName("android.support.v4.content.ContextCompat");
        } catch (Exception e) {
            cls = null;
        }
        Class<?> cls2 = cls;
        if (cls == null) {
            try {
                cls2 = Class.forName("androidx.core.content.ContextCompat");
            } catch (Exception e2) {
                cls2 = cls;
            }
        }
        if (cls2 == null) {
            return true;
        }
        try {
            if (((Integer) cls2.getMethod("checkSelfPermission", Context.class, String.class).invoke(null, context, str)).intValue() == 0) {
                return true;
            }
            SALog.m55i(TAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"" + str + "\" />");
            return false;
        } catch (Exception e3) {
            SALog.m55i(TAG, e3.toString());
            return true;
        }
    }

    public static void cleanUserAgent(Context context) {
        try {
            SharedPreferences.Editor edit = getSharedPreferences(context).edit();
            edit.putString(SHARED_PREF_USER_AGENT_KEY, null);
            edit.apply();
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private static Class<?> compatActivity() {
        Class<?> cls;
        try {
            cls = Class.forName("android.support.v7.app.AppCompatActivity");
        } catch (Exception e) {
            cls = null;
        }
        Class<?> cls2 = cls;
        if (cls == null) {
            try {
                cls2 = Class.forName("androidx.appcompat.app.AppCompatActivity");
            } catch (Exception e2) {
                return cls;
            }
        }
        return cls2;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0024 A[Catch: Exception -> 0x0068, TryCatch #0 {Exception -> 0x0068, blocks: (B:6:0x0004, B:8:0x000c, B:12:0x001d, B:14:0x0024, B:17:0x0030, B:19:0x0037, B:22:0x0042, B:24:0x004e, B:26:0x0059), top: B:5:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0037 A[Catch: Exception -> 0x0068, TRY_LEAVE, TryCatch #0 {Exception -> 0x0068, blocks: (B:6:0x0004, B:8:0x000c, B:12:0x001d, B:14:0x0024, B:17:0x0030, B:19:0x0037, B:22:0x0042, B:24:0x004e, B:26:0x0059), top: B:5:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getActivityTitle(android.app.Activity r4) {
        /*
            r0 = r4
            if (r0 == 0) goto L66
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L68
            r1 = 11
            if (r0 < r1) goto L6b
            r0 = r4
            java.lang.String r0 = getToolbarTitle(r0)     // Catch: java.lang.Exception -> L68
            r6 = r0
            r0 = r6
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L68
            if (r0 != 0) goto L6b
            goto L1b
        L1b:
            r0 = r6
            r5 = r0
            r0 = r6
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L68
            if (r0 != 0) goto L2e
            r0 = r4
            java.lang.CharSequence r0 = r0.getTitle()     // Catch: java.lang.Exception -> L68
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L68
            r5 = r0
        L2e:
            r0 = r5
            r6 = r0
            r0 = r5
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L68
            if (r0 == 0) goto L64
            r0 = r4
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch: java.lang.Exception -> L68
            r7 = r0
            r0 = r5
            r6 = r0
            r0 = r7
            if (r0 == 0) goto L64
            r0 = r7
            r1 = r4
            android.content.ComponentName r1 = r1.getComponentName()     // Catch: java.lang.Exception -> L68
            r2 = 0
            android.content.pm.ActivityInfo r0 = r0.getActivityInfo(r1, r2)     // Catch: java.lang.Exception -> L68
            r4 = r0
            r0 = r5
            r6 = r0
            r0 = r4
            r1 = r7
            java.lang.CharSequence r0 = r0.loadLabel(r1)     // Catch: java.lang.Exception -> L68
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L68
            if (r0 != 0) goto L64
            r0 = r4
            r1 = r7
            java.lang.CharSequence r0 = r0.loadLabel(r1)     // Catch: java.lang.Exception -> L68
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L68
            r6 = r0
        L64:
            r0 = r6
            return r0
        L66:
            r0 = 0
            return r0
        L68:
            r4 = move-exception
            r0 = 0
            return r0
        L6b:
            r0 = 0
            r6 = r0
            goto L1b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.getActivityTitle(android.app.Activity):java.lang.String");
    }

    @SuppressLint({"HardwareIds"})
    public static String getAndroidID(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return "";
        }
    }

    public static CharSequence getAppName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager == null ? "" : packageManager.getApplicationInfo(context.getPackageName(), 128).loadLabel(packageManager);
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return "";
        }
    }

    public static String getApplicationMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageManager().getApplicationInfo(StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName(), 128);
            String string = applicationInfo.metaData.getString(str);
            int i = string == null ? applicationInfo.metaData.getInt(str, -1) : -1;
            if (i != -1) {
                string = String.valueOf(i);
            }
            return string;
        } catch (Exception e) {
            return "";
        }
    }

    public static ArrayList<String> getAutoTrackFragments(Context context) {
        BufferedReader bufferedReader;
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("sa_autotrack_fragment.config")));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                bufferedReader.close();
                                return arrayList;
                            }
                            if (!TextUtils.isEmpty(readLine) && !readLine.startsWith("#")) {
                                arrayList.add(readLine);
                            }
                        } catch (IOException e) {
                            e = e;
                            if (e.toString().contains("FileNotFoundException")) {
                                BufferedReader bufferedReader3 = bufferedReader;
                                SALog.m53d(TAG, "SensorsDataAutoTrackFragment file not exists.");
                            } else {
                                SALog.printStackTrace(e);
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                return arrayList;
                            }
                            return arrayList;
                        } catch (Throwable th) {
                            bufferedReader2 = bufferedReader;
                            th = th;
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e2) {
                                    SALog.printStackTrace(e2);
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e = e3;
                bufferedReader = null;
            }
        } catch (IOException e4) {
            SALog.printStackTrace(e4);
            return arrayList;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004c A[Catch: Exception -> 0x0070, TryCatch #0 {Exception -> 0x0070, blocks: (B:5:0x000c, B:7:0x001d, B:9:0x002c, B:11:0x0039, B:13:0x0045, B:15:0x004c, B:17:0x0055, B:19:0x005e, B:21:0x0066), top: B:4:0x000c, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0066 A[Catch: Exception -> 0x0070, TRY_LEAVE, TryCatch #0 {Exception -> 0x0070, blocks: (B:5:0x000c, B:7:0x001d, B:9:0x002c, B:11:0x0039, B:13:0x0045, B:15:0x004c, B:17:0x0055, B:19:0x005e, B:21:0x0066), top: B:4:0x000c, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getCarrier(android.content.Context r4) {
        /*
            r0 = r4
            java.lang.String r1 = "android.permission.READ_PHONE_STATE"
            boolean r0 = checkHasPermission(r0, r1)     // Catch: java.lang.Exception -> L77
            r5 = r0
            r0 = r5
            if (r0 == 0) goto L7c
            r0 = r4
            java.lang.String r1 = "phone"
            java.lang.Object r0 = r0.getSystemService(r1)     // Catch: java.lang.Exception -> L70
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: java.lang.Exception -> L70
            r9 = r0
            r0 = r9
            if (r0 == 0) goto L7c
            r0 = r9
            java.lang.String r0 = r0.getSimOperator()     // Catch: java.lang.Exception -> L70
            r8 = r0
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L70
            r1 = 28
            if (r0 < r1) goto L7e
            r0 = r9
            java.lang.CharSequence r0 = r0.getSimCarrierIdName()     // Catch: java.lang.Exception -> L70
            r6 = r0
            r0 = r6
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L70
            if (r0 != 0) goto L7e
            r0 = r6
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L70
            r6 = r0
            goto L43
        L43:
            r0 = r6
            r7 = r0
            r0 = r6
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L70
            if (r0 == 0) goto L5e
            r0 = r9
            int r0 = r0.getSimState()     // Catch: java.lang.Exception -> L70
            r1 = 5
            if (r0 != r1) goto L83
            r0 = r9
            java.lang.String r0 = r0.getSimOperatorName()     // Catch: java.lang.Exception -> L70
            r7 = r0
            goto L5e
        L5e:
            r0 = r8
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L70
            if (r0 != 0) goto L7c
            r0 = r4
            r1 = r8
            r2 = r7
            java.lang.String r0 = operatorToCarrier(r0, r1, r2)     // Catch: java.lang.Exception -> L70
            r4 = r0
            r0 = r4
            return r0
        L70:
            r4 = move-exception
            r0 = r4
            com.sensorsdata.analytics.android.sdk.SALog.printStackTrace(r0)     // Catch: java.lang.Exception -> L77
            r0 = 0
            return r0
        L77:
            r4 = move-exception
            r0 = r4
            com.sensorsdata.analytics.android.sdk.SALog.printStackTrace(r0)
        L7c:
            r0 = 0
            return r0
        L7e:
            r0 = 0
            r6 = r0
            goto L43
        L83:
            java.lang.String r0 = "未知"
            r7 = r0
            goto L5e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.getCarrier(android.content.Context):java.lang.String");
    }

    private static String getCarrierFromJsonObject(JSONObject jSONObject, String str) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return jSONObject.optString(str);
    }

    private static String getCurrentProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return null;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo != null && runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
            return null;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return null;
        }
    }

    public static String getDeviceID(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String string = sharedPreferences.getString(SHARED_PREF_DEVICE_ID_KEY, null);
        String str = string;
        if (string == null) {
            str = UUID.randomUUID().toString();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(SHARED_PREF_DEVICE_ID_KEY, str);
            edit.apply();
        }
        return str;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager;
        String imei;
        try {
            if (!checkHasPermission(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                return "";
            }
            if (Build.VERSION.SDK_INT <= 28) {
                imei = Build.VERSION.SDK_INT >= 26 ? telephonyManager.getImei() : telephonyManager.getDeviceId();
            } else {
                if (!telephonyManager.hasCarrierPrivileges()) {
                    SALog.m53d(TAG, "Can not get IMEI info.");
                    return "";
                }
                imei = telephonyManager.getImei();
            }
            return imei;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return "";
        }
    }

    public static String getIMEIOld(Context context) {
        TelephonyManager telephonyManager;
        try {
            return (checkHasPermission(context, "android.permission.READ_PHONE_STATE") && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) ? telephonyManager.getDeviceId() : "";
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return "";
        }
    }

    private static String getJsonFromAssets(String str, Context context) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                        } catch (IOException e) {
                            e = e;
                            bufferedReader2 = bufferedReader;
                            SALog.printStackTrace(e);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return sb.toString();
                        } catch (Throwable th) {
                            bufferedReader2 = bufferedReader;
                            th = th;
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e2) {
                                    SALog.printStackTrace(e2);
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader.close();
                } catch (IOException e3) {
                    e = e3;
                    bufferedReader = null;
                }
            } catch (IOException e4) {
                SALog.printStackTrace(e4);
            }
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @SuppressLint({"HardwareIds"})
    public static String getMacAddress(Context context) {
        WifiManager wifiManager;
        try {
            if (!checkHasPermission(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) StubApp.getOrigApplicationContext(context.getApplicationContext()).getSystemService("wifi")) == null) {
                return "";
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo == null || !marshmallowMacAddress.equals(connectionInfo.getMacAddress())) {
                return (connectionInfo == null || connectionInfo.getMacAddress() == null) ? "" : connectionInfo.getMacAddress();
            }
            try {
                String macAddressByInterface = getMacAddressByInterface();
                return macAddressByInterface != null ? macAddressByInterface : marshmallowMacAddress;
            } catch (Exception e) {
                return marshmallowMacAddress;
            }
        } catch (Exception e2) {
            return "";
        }
    }

    private static String getMacAddressByInterface() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= length) {
                            break;
                        }
                        sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i2])));
                        i = i2 + 1;
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMainProcessName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return StubApp.getOrigApplicationContext(context.getApplicationContext()).getApplicationInfo().processName;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return "";
        }
    }

    public static String getManufacturer() {
        String trim = Build.MANUFACTURER == null ? "UNKNOWN" : Build.MANUFACTURER.trim();
        try {
            if (!TextUtils.isEmpty(trim)) {
                for (String str : sManufacturer) {
                    if (str.equalsIgnoreCase(trim)) {
                        return str;
                    }
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        return trim;
    }

    public static int getNaturalHeight(int i, int i2, int i3) {
        if (i == 0 || i == 2) {
            i2 = i3;
        }
        return i2;
    }

    public static int getNaturalWidth(int i, int i2, int i3) {
        int i4 = i2;
        if (i != 0) {
            if (i == 2) {
                return i2;
            }
            i4 = i3;
        }
        return i4;
    }

    public static void getScreenNameAndTitleFromActivity(JSONObject jSONObject, Activity activity) {
        if (activity == null || jSONObject == null) {
            return;
        }
        try {
            jSONObject.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
            String str = null;
            if (!TextUtils.isEmpty(activity.getTitle())) {
                str = activity.getTitle().toString();
            }
            String str2 = str;
            if (Build.VERSION.SDK_INT >= 11) {
                String toolbarTitle = getToolbarTitle(activity);
                str2 = str;
                if (!TextUtils.isEmpty(toolbarTitle)) {
                    str2 = toolbarTitle;
                }
            }
            String str3 = str2;
            if (TextUtils.isEmpty(str2)) {
                PackageManager packageManager = activity.getPackageManager();
                str3 = str2;
                if (packageManager != null) {
                    str3 = packageManager.getActivityInfo(activity.getComponentName(), 0).loadLabel(packageManager).toString();
                }
            }
            if (TextUtils.isEmpty(str3)) {
                return;
            }
            jSONObject.put(AopConstants.TITLE, str3);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_EDITS_FILE, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(11)
    public static String getToolbarTitle(Activity activity) {
        Object invoke;
        CharSequence charSequence;
        try {
            if ("com.tencent.connect.common.AssistActivity".equals(activity.getClass().getCanonicalName())) {
                if (TextUtils.isEmpty(activity.getTitle())) {
                    return null;
                }
                return activity.getTitle().toString();
            }
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                if (TextUtils.isEmpty(actionBar.getTitle())) {
                    return null;
                }
                return actionBar.getTitle().toString();
            }
            try {
                Class<?> compatActivity = compatActivity();
                if (compatActivity == null || !compatActivity.isInstance(activity) || (invoke = activity.getClass().getMethod("getSupportActionBar", new Class[0]).invoke(activity, new Object[0])) == null || (charSequence = (CharSequence) invoke.getClass().getMethod("getTitle", new Class[0]).invoke(invoke, new Object[0])) == null) {
                    return null;
                }
                return charSequence.toString();
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
            return null;
        }
    }

    @Deprecated
    public static String getUserAgent(Context context) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(context);
            String string = sharedPreferences.getString(SHARED_PREF_USER_AGENT_KEY, null);
            String str = string;
            if (TextUtils.isEmpty(string)) {
                String str2 = string;
                if (Build.VERSION.SDK_INT >= 17) {
                    str2 = string;
                    try {
                        if (Class.forName("android.webkit.WebSettings").getMethod("getDefaultUserAgent", Context.class) != null) {
                            str2 = WebSettings.getDefaultUserAgent(context);
                        }
                    } catch (Exception e) {
                        SALog.m55i(TAG, "WebSettings NoSuchMethod: getDefaultUserAgent");
                        str2 = string;
                    }
                }
                String str3 = str2;
                if (TextUtils.isEmpty(str2)) {
                    str3 = System.getProperty("http.agent");
                }
                str = str3;
                if (!TextUtils.isEmpty(str3)) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(SHARED_PREF_USER_AGENT_KEY, str3);
                    edit.apply();
                    str = str3;
                }
            }
            return str;
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
            return null;
        }
    }

    public static Integer getZoneOffset() {
        try {
            return Integer.valueOf(Calendar.getInstance(Locale.getDefault()).get(15) / PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT);
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return null;
        }
    }

    public static boolean hasUtmProperties(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("$utm_source") || jSONObject.has("$utm_medium") || jSONObject.has("$utm_term") || jSONObject.has("$utm_content") || jSONObject.has("$utm_campaign")) {
            z = true;
        }
        return z;
    }

    public static boolean isDoubleClick(View view) {
        if (view == null) {
            return false;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String str = (String) view.getTag(C0198R.id.sensors_analytics_tag_view_onclick_timestamp);
            if (!TextUtils.isEmpty(str) && currentTimeMillis - Long.parseLong(str) < 500) {
                return true;
            }
            view.setTag(C0198R.id.sensors_analytics_tag_view_onclick_timestamp, String.valueOf(currentTimeMillis));
            return false;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return false;
        }
    }

    public static boolean isMainProcess(Context context, String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String currentProcessName = getCurrentProcessName(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        if (!TextUtils.isEmpty(currentProcessName)) {
            if (str.equals(currentProcessName)) {
                return true;
            }
            z = false;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x004d, code lost:            if ((r0 / 1000.0f) < (r0 * 3600)) goto L12;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isRequestValid(android.content.Context r6, int r7, int r8) {
        /*
            r0 = r6
            android.content.SharedPreferences r0 = getSharedPreferences(r0)
            r6 = r0
            r0 = r6
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r14 = r0
            r0 = r6
            java.lang.String r1 = "sensorsdata.request.time"
            r2 = 0
            long r0 = r0.getLong(r1, r2)
            r11 = r0
            r0 = 0
            r13 = r0
            r0 = r6
            java.lang.String r1 = "sensorsdata.request.time.random"
            r2 = 0
            int r0 = r0.getInt(r1, r2)
            r10 = r0
            r0 = r11
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L53
            r0 = r10
            if (r0 == 0) goto L53
            long r0 = android.os.SystemClock.elapsedRealtime()
            r1 = r11
            long r0 = r0 - r1
            float r0 = (float) r0
            r9 = r0
            r0 = r9
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L53
            r0 = r9
            r1 = 1148846080(0x447a0000, float:1000.0)
            float r0 = r0 / r1
            r1 = r10
            r2 = 3600(0xe10, float:5.045E-42)
            int r1 = r1 * r2
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L53
            goto L56
        L53:
            r0 = 1
            r13 = r0
        L56:
            r0 = r13
            if (r0 == 0) goto L8a
            r0 = r14
            java.lang.String r1 = "sensorsdata.request.time"
            long r2 = android.os.SystemClock.elapsedRealtime()
            android.content.SharedPreferences$Editor r0 = r0.putLong(r1, r2)
            r0 = r14
            java.lang.String r1 = "sensorsdata.request.time.random"
            java.util.Random r2 = new java.util.Random
            r3 = r2
            r3.<init>()
            r3 = r8
            r4 = r7
            int r3 = r3 - r4
            r4 = 1
            int r3 = r3 + r4
            int r2 = r2.nextInt(r3)
            r3 = r7
            int r2 = r2 + r3
            android.content.SharedPreferences$Editor r0 = r0.putInt(r1, r2)
            r0 = r14
            r0.apply()
        L8a:
            r0 = r13
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.isRequestValid(android.content.Context, int, int):boolean");
    }

    public static boolean isValidAndroidId(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !mInvalidAndroidId.contains(str.toLowerCase(Locale.getDefault()));
    }

    public static void mergeJSONObject(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (!(obj instanceof Date) || "$time".equals(next)) {
                    jSONObject2.put(next, obj);
                } else {
                    jSONObject2.put(next, DateFormatUtils.formatDate((Date) obj, Locale.CHINA));
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public static void mergeSuperJSONObject(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Iterator<String> keys2 = jSONObject2.keys();
                while (true) {
                    if (!keys2.hasNext()) {
                        break;
                    }
                    String next2 = keys2.next();
                    if (!TextUtils.isEmpty(next) && next.toLowerCase(Locale.getDefault()).equals(next2.toLowerCase(Locale.getDefault()))) {
                        jSONObject2.remove(next2);
                        break;
                    }
                }
                Object obj = jSONObject.get(next);
                if (!(obj instanceof Date) || "$time".equals(next)) {
                    jSONObject2.put(next, obj);
                } else {
                    jSONObject2.put(next, DateFormatUtils.formatDate((Date) obj, Locale.CHINA));
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private static String operatorToCarrier(Context context, String str, String str2) {
        try {
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (sCarrierMap.containsKey(str)) {
            return sCarrierMap.get(str);
        }
        String jsonFromAssets = getJsonFromAssets("sa_mcc_mnc_mini.json", context);
        if (TextUtils.isEmpty(jsonFromAssets)) {
            sCarrierMap.put(str, str2);
            return str2;
        }
        String carrierFromJsonObject = getCarrierFromJsonObject(new JSONObject(jsonFromAssets), str);
        if (!TextUtils.isEmpty(carrierFromJsonObject)) {
            sCarrierMap.put(str, carrierFromJsonObject);
            return carrierFromJsonObject;
        }
        return str2;
    }

    public static SensorsDataSDKRemoteConfig toSDKRemoteConfig(String str) {
        SensorsDataSDKRemoteConfig sensorsDataSDKRemoteConfig = new SensorsDataSDKRemoteConfig();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                sensorsDataSDKRemoteConfig.setV(jSONObject.optString("v"));
                if (TextUtils.isEmpty(jSONObject.optString("configs"))) {
                    sensorsDataSDKRemoteConfig.setDisableDebugMode(false);
                    sensorsDataSDKRemoteConfig.setDisableSDK(false);
                    sensorsDataSDKRemoteConfig.setAutoTrackMode(-1);
                    return sensorsDataSDKRemoteConfig;
                }
                JSONObject jSONObject2 = new JSONObject(jSONObject.optString("configs"));
                sensorsDataSDKRemoteConfig.setDisableDebugMode(jSONObject2.optBoolean("disableDebugMode", false));
                sensorsDataSDKRemoteConfig.setDisableSDK(jSONObject2.optBoolean("disableSDK", false));
                sensorsDataSDKRemoteConfig.setAutoTrackMode(jSONObject2.optInt("autoTrackMode", -1));
                return sensorsDataSDKRemoteConfig;
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        return sensorsDataSDKRemoteConfig;
    }
}
