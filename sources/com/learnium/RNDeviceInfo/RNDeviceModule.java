package com.learnium.RNDeviceInfo;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.UiModeManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebSettings;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.iid.InstanceID;
import com.stub.StubApp;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.math.BigInteger;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/learnium/RNDeviceInfo/RNDeviceModule.class */
public class RNDeviceModule extends ReactContextBaseJavaModule {
    Map<String, Object> constants;
    DeviceType deviceType;
    AsyncTask<Void, Void, Map<String, Object>> futureConstants;
    ReactApplicationContext reactContext;
    WifiInfo wifiInfo;

    /* JADX WARN: Type inference failed for: r1v6, types: [com.learnium.RNDeviceInfo.RNDeviceModule$1] */
    public RNDeviceModule(ReactApplicationContext reactApplicationContext, boolean z) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        this.deviceType = getDeviceType(reactApplicationContext);
        if (z) {
            this.futureConstants = new AsyncTask<Void, Void, Map<String, Object>>() { // from class: com.learnium.RNDeviceInfo.RNDeviceModule.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Map<String, Object> doInBackground(Void... voidArr) {
                    return RNDeviceModule.this.generateConstants();
                }
            }.execute(new Void[0]);
        } else {
            this.constants = generateConstants();
        }
    }

    private float fontScale() {
        return getReactApplicationContext().getResources().getConfiguration().fontScale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Object> generateConstants() {
        HashMap hashMap = new HashMap();
        PackageManager packageManager = this.reactContext.getPackageManager();
        String packageName = this.reactContext.getPackageName();
        hashMap.put("appVersion", "not available");
        hashMap.put("appName", "not available");
        hashMap.put("buildVersion", "not available");
        hashMap.put("buildNumber", 0);
        try {
            packageManager.getPackageInfo(packageName, 0);
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String charSequence = this.reactContext.getApplicationInfo().loadLabel(this.reactContext.getPackageManager()).toString();
            hashMap.put("appVersion", packageInfo.versionName);
            hashMap.put("buildNumber", Integer.valueOf(packageInfo.versionCode));
            hashMap.put("firstInstallTime", Long.valueOf(packageInfo.firstInstallTime));
            hashMap.put("lastUpdateTime", Long.valueOf(packageInfo.lastUpdateTime));
            hashMap.put("appName", charSequence);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String str = "Unknown";
        if (this.reactContext.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                str = "Unknown";
                if (defaultAdapter != null) {
                    str = defaultAdapter.getName();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                str = "Unknown";
            }
        }
        try {
            if (Class.forName("com.google.android.gms.iid.InstanceID") != null) {
                hashMap.put("instanceId", InstanceID.getInstance(this.reactContext).getId());
            }
        } catch (ClassNotFoundException e3) {
            hashMap.put("instanceId", "N/A: Add com.google.android.gms:play-services-gcm to your project.");
        }
        hashMap.put("serialNumber", Build.SERIAL);
        hashMap.put("deviceName", str);
        hashMap.put("systemName", "Android");
        hashMap.put("systemVersion", Build.VERSION.RELEASE);
        hashMap.put("model", Build.MODEL);
        hashMap.put(Constants.PHONE_BRAND, Build.BRAND);
        hashMap.put("buildId", Build.ID);
        hashMap.put("deviceId", Build.BOARD);
        hashMap.put("apiLevel", Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("deviceLocale", getCurrentLanguage());
        hashMap.put("preferredLocales", getPreferredLocales());
        hashMap.put("deviceCountry", getCurrentCountry());
        hashMap.put("uniqueId", Settings.Secure.getString(this.reactContext.getContentResolver(), "android_id"));
        hashMap.put("systemManufacturer", Build.MANUFACTURER);
        hashMap.put("bundleId", packageName);
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                hashMap.put("userAgent", WebSettings.getDefaultUserAgent(this.reactContext));
            } else {
                hashMap.put("userAgent", System.getProperty("http.agent"));
            }
        } catch (RuntimeException e4) {
            hashMap.put("userAgent", System.getProperty("http.agent"));
        }
        hashMap.put("timezone", TimeZone.getDefault().getID());
        hashMap.put("isEmulator", isEmulator());
        hashMap.put("isTablet", isTablet());
        hashMap.put("fontScale", Float.valueOf(fontScale()));
        hashMap.put("is24Hour", is24Hour());
        hashMap.put("carrier", getCarrier());
        hashMap.put("totalDiskCapacity", getTotalDiskCapacity());
        hashMap.put("freeDiskStorage", getFreeDiskStorage());
        hashMap.put("installReferrer", getInstallReferrer());
        ReactApplicationContext reactApplicationContext = this.reactContext;
        if (reactApplicationContext == null || (reactApplicationContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0 && ((Build.VERSION.SDK_INT < 23 || this.reactContext.checkCallingOrSelfPermission("android.permission.READ_SMS") != 0) && (Build.VERSION.SDK_INT < 26 || this.reactContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_NUMBERS") != 0)))) {
            hashMap.put("phoneNumber", null);
        } else {
            hashMap.put("phoneNumber", ((TelephonyManager) StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).getSystemService("phone")).getLine1Number());
        }
        hashMap.put("maxMemory", Long.valueOf(Runtime.getRuntime().maxMemory()));
        ActivityManager activityManager = (ActivityManager) this.reactContext.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        hashMap.put("totalMemory", Long.valueOf(memoryInfo.totalMem));
        hashMap.put("deviceType", this.deviceType.getValue());
        if (Build.VERSION.SDK_INT >= 21) {
            hashMap.put("supportedABIs", Build.SUPPORTED_ABIS);
            return hashMap;
        }
        hashMap.put("supportedABIs", new String[]{Build.CPU_ABI});
        return hashMap;
    }

    private String getCurrentCountry() {
        return (Build.VERSION.SDK_INT >= 24 ? getReactApplicationContext().getResources().getConfiguration().getLocales().get(0) : getReactApplicationContext().getResources().getConfiguration().locale).getCountry();
    }

    private String getCurrentLanguage() {
        Locale locale = Build.VERSION.SDK_INT >= 24 ? getReactApplicationContext().getResources().getConfiguration().getLocales().get(0) : getReactApplicationContext().getResources().getConfiguration().locale;
        if (Build.VERSION.SDK_INT >= 21) {
            return locale.toLanguageTag();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage());
        if (locale.getCountry() != null) {
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            sb.append(locale.getCountry());
        }
        return sb.toString();
    }

    private static DeviceType getDeviceType(ReactApplicationContext reactApplicationContext) {
        if (StubApp.getOrigApplicationContext(reactApplicationContext.getApplicationContext()).getPackageManager().hasSystemFeature("amazon.hardware.fire_tv")) {
            return DeviceType.TV;
        }
        UiModeManager uiModeManager = (UiModeManager) reactApplicationContext.getSystemService("uimode");
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
            return DeviceType.TV;
        }
        WindowManager windowManager = (WindowManager) reactApplicationContext.getSystemService("window");
        if (windowManager == null) {
            return DeviceType.UNKNOWN;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        double sqrt = Math.sqrt(Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d) + Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d));
        return (sqrt < 3.0d || sqrt > 6.9d) ? (sqrt <= 6.9d || sqrt > 18.0d) ? DeviceType.UNKNOWN : DeviceType.TABLET : DeviceType.HANDSET;
    }

    private ArrayList<String> getPreferredLocales() {
        Configuration configuration = getReactApplicationContext().getResources().getConfiguration();
        ArrayList<String> arrayList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 24) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= configuration.getLocales().size()) {
                    break;
                }
                arrayList.add(configuration.getLocales().get(i2).getLanguage());
                i = i2 + 1;
            }
        } else {
            arrayList.add(configuration.locale.getLanguage());
        }
        return arrayList;
    }

    private WifiInfo getWifiInfo() {
        if (this.wifiInfo == null) {
            this.wifiInfo = ((WifiManager) StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).getSystemService("wifi")).getConnectionInfo();
        }
        return this.wifiInfo;
    }

    private Boolean is24Hour() {
        return Boolean.valueOf(DateFormat.is24HourFormat(StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext())));
    }

    private Boolean isEmulator() {
        return Boolean.valueOf(Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains(CommonUtils.GOOGLE_SDK) || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || CommonUtils.GOOGLE_SDK.equals(Build.PRODUCT));
    }

    private Boolean isTablet() {
        return Boolean.valueOf(this.deviceType == DeviceType.TABLET);
    }

    @ReactMethod
    public void getAvailableLocationProviders(Promise promise) {
        LocationManager locationManager = (LocationManager) StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).getSystemService("location");
        List<String> providers = locationManager.getProviders(false);
        WritableMap createMap = Arguments.createMap();
        for (String str : providers) {
            createMap.putBoolean(str, locationManager.isProviderEnabled(str));
        }
        promise.resolve(createMap);
    }

    @ReactMethod
    public void getBatteryLevel(Promise promise) {
        Intent registerReceiver = StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        promise.resolve(Float.valueOf(registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1)));
    }

    @ReactMethod
    public void getCameraPresence(Promise promise) {
        boolean z = true;
        if (Build.VERSION.SDK_INT < 21) {
            promise.resolve(Boolean.valueOf(Camera.getNumberOfCameras() > 0));
            return;
        }
        try {
            if (((CameraManager) getReactApplicationContext().getSystemService("camera")).getCameraIdList().length <= 0) {
                z = false;
            }
            promise.resolve(Boolean.valueOf(z));
        } catch (CameraAccessException e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public String getCarrier() {
        return ((TelephonyManager) this.reactContext.getSystemService("phone")).getNetworkOperatorName();
    }

    @Nullable
    public Map<String, Object> getConstants() {
        AsyncTask<Void, Void, Map<String, Object>> asyncTask;
        if (this.constants == null && (asyncTask = this.futureConstants) != null) {
            try {
                this.constants = asyncTask.get();
            } catch (InterruptedException e) {
                return null;
            } catch (ExecutionException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        return this.constants;
    }

    @ReactMethod
    public BigInteger getFreeDiskStorage() {
        long availableBlocksLong;
        long blockSizeLong;
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (Build.VERSION.SDK_INT < 18) {
                availableBlocksLong = statFs.getAvailableBlocks();
                blockSizeLong = statFs.getBlockSize();
            } else {
                availableBlocksLong = statFs.getAvailableBlocksLong();
                blockSizeLong = statFs.getBlockSizeLong();
            }
            return BigInteger.valueOf(availableBlocksLong).multiply(BigInteger.valueOf(blockSizeLong));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getInstallReferrer() {
        return getReactApplicationContext().getSharedPreferences("react-native-device-info", 0).getString("installReferrer", null);
    }

    @ReactMethod
    public void getIpAddress(Promise promise) {
        promise.resolve(Formatter.formatIpAddress(getWifiInfo().getIpAddress()));
    }

    @ReactMethod
    public void getMacAddress(Promise promise) {
        String macAddress = getWifiInfo().getMacAddress();
        String str = macAddress;
        if (this.reactContext.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
            String str2 = macAddress;
            try {
                Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                while (true) {
                    str = macAddress;
                    str2 = macAddress;
                    if (!it.hasNext()) {
                        break;
                    }
                    String str3 = macAddress;
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    String str4 = macAddress;
                    if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            macAddress = "";
                        } else {
                            StringBuilder sb = new StringBuilder();
                            String str5 = macAddress;
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
                                String str6 = macAddress;
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            String str7 = macAddress;
                            macAddress = sb.toString();
                        }
                    }
                }
            } catch (Exception e) {
                str = str2;
            }
        }
        promise.resolve(str);
    }

    public String getName() {
        return "RNDeviceInfo";
    }

    @ReactMethod
    public void getSystemAvailableFeatures(Promise promise) {
        FeatureInfo[] systemAvailableFeatures = StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).getPackageManager().getSystemAvailableFeatures();
        WritableArray createArray = Arguments.createArray();
        int length = systemAvailableFeatures.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                promise.resolve(createArray);
                return;
            }
            FeatureInfo featureInfo = systemAvailableFeatures[i2];
            if (featureInfo.name != null) {
                createArray.pushString(featureInfo.name);
            }
            i = i2 + 1;
        }
    }

    @ReactMethod
    public BigInteger getTotalDiskCapacity() {
        try {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            return BigInteger.valueOf(statFs.getBlockCount()).multiply(BigInteger.valueOf(statFs.getBlockSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @ReactMethod
    public void hasSystemFeature(String str, Promise promise) {
        if (str == null || str == "") {
            promise.resolve(false);
        } else {
            promise.resolve(Boolean.valueOf(StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).getPackageManager().hasSystemFeature(str)));
        }
    }

    @ReactMethod
    public void isAirPlaneMode(Promise promise) {
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(this.reactContext.getContentResolver(), "airplane_mode_on", 0) == 0 : Settings.System.getInt(this.reactContext.getContentResolver(), "airplane_mode_on", 0) == 0) {
            z = false;
        }
        promise.resolve(Boolean.valueOf(z));
    }

    @ReactMethod
    public void isAutoDateAndTime(Promise promise) {
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(this.reactContext.getContentResolver(), "auto_time", 0) == 0 : Settings.System.getInt(this.reactContext.getContentResolver(), "auto_time", 0) == 0) {
            z = false;
        }
        promise.resolve(Boolean.valueOf(z));
    }

    @ReactMethod
    public void isAutoTimeZone(Promise promise) {
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(this.reactContext.getContentResolver(), "auto_time_zone", 0) == 0 : Settings.System.getInt(this.reactContext.getContentResolver(), "auto_time_zone", 0) == 0) {
            z = false;
        }
        promise.resolve(Boolean.valueOf(z));
    }

    @ReactMethod
    public void isBatteryCharging(Promise promise) {
        promise.resolve(Boolean.valueOf(StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra(SettingsJsonConstants.APP_STATUS_KEY, -1) == 2));
    }

    @ReactMethod
    public void isLocationEnabled(Promise promise) {
        promise.resolve(Boolean.valueOf(Build.VERSION.SDK_INT >= 28 ? ((LocationManager) StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).getSystemService("location")).isLocationEnabled() : Build.VERSION.SDK_INT >= 19 ? Settings.Secure.getInt(this.reactContext.getContentResolver(), "location_mode", 0) != 0 : !TextUtils.isEmpty(Settings.Secure.getString(this.reactContext.getContentResolver(), "location_providers_allowed"))));
    }

    @ReactMethod
    public void isPinOrFingerprintSet(Callback callback) {
        callback.invoke(new Object[]{Boolean.valueOf(((KeyguardManager) StubApp.getOrigApplicationContext(this.reactContext.getApplicationContext()).getSystemService("keyguard")).isKeyguardSecure())});
    }
}
