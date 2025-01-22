package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.stub.StubApp;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/CommonUtils.class */
public class CommonUtils {
    static final int BYTES_IN_A_GIGABYTE = 1073741824;
    static final int BYTES_IN_A_KILOBYTE = 1024;
    static final int BYTES_IN_A_MEGABYTE = 1048576;
    private static final String CLS_SHARED_PREFERENCES_NAME = "com.crashlytics.prefs";
    static final boolean CLS_TRACE_DEFAULT = false;
    static final String CLS_TRACE_PREFERENCE_NAME = "com.crashlytics.Trace";
    static final String CRASHLYTICS_BUILD_ID = "com.crashlytics.android.build_id";
    public static final int DEVICE_STATE_BETAOS = 8;
    public static final int DEVICE_STATE_COMPROMISEDLIBRARIES = 32;
    public static final int DEVICE_STATE_DEBUGGERATTACHED = 4;
    public static final int DEVICE_STATE_ISSIMULATOR = 1;
    public static final int DEVICE_STATE_JAILBROKEN = 2;
    public static final int DEVICE_STATE_VENDORINTERNAL = 16;
    static final String FABRIC_BUILD_ID = "io.fabric.android.build_id";
    public static final String GOOGLE_SDK = "google_sdk";
    private static final String LOG_PRIORITY_NAME_ASSERT = "A";
    private static final String LOG_PRIORITY_NAME_DEBUG = "D";
    private static final String LOG_PRIORITY_NAME_ERROR = "E";
    private static final String LOG_PRIORITY_NAME_INFO = "I";
    private static final String LOG_PRIORITY_NAME_UNKNOWN = "?";
    private static final String LOG_PRIORITY_NAME_VERBOSE = "V";
    private static final String LOG_PRIORITY_NAME_WARN = "W";
    public static final String SDK = "sdk";
    public static final String SHA1_INSTANCE = "SHA-1";
    public static final String SHA256_INSTANCE = "SHA-256";
    private static final long UNCALCULATED_TOTAL_RAM = -1;
    private static Boolean clsTrace;
    private static final char[] HEX_VALUES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static long totalRamInBytes = -1;
    public static final Comparator<File> FILE_MODIFIED_COMPARATOR = new Comparator<File>() { // from class: io.fabric.sdk.android.services.common.CommonUtils.1
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    };

    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/CommonUtils$Architecture.class */
    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;

        private static final Map<String, Architecture> matcher = new HashMap(4);

        static {
            matcher.put("armeabi-v7a", ARMV7);
            matcher.put("armeabi", ARMV6);
            matcher.put("arm64-v8a", ARM64);
            matcher.put("x86", X86_32);
        }

        static Architecture getValue() {
            String str = Build.CPU_ABI;
            if (TextUtils.isEmpty(str)) {
                Fabric.getLogger().mo2870d(Fabric.TAG, "Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture architecture = matcher.get(str.toLowerCase(Locale.US));
            Architecture architecture2 = architecture;
            if (architecture == null) {
                architecture2 = UNKNOWN;
            }
            return architecture2;
        }
    }

    public static long calculateFreeRamInBytes(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long calculateUsedDiskSpaceInBytes(String str) {
        long blockSize = new StatFs(str).getBlockSize();
        return (r0.getBlockCount() * blockSize) - (blockSize * r0.getAvailableBlocks());
    }

    @SuppressLint({"MissingPermission"})
    public static boolean canTryConnection(Context context) {
        boolean z = true;
        if (checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                return true;
            }
            z = false;
        }
        return z;
    }

    public static boolean checkPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static void closeOrLog(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Fabric.getLogger().mo2873e(Fabric.TAG, str, e);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    static long convertMemInfoToBytes(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * i;
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, read);
            }
        }
    }

    @Deprecated
    public static Cipher createCipher(int i, String str) throws InvalidKeyException {
        throw new InvalidKeyException("This method is deprecated");
    }

    public static String createInstanceIdFrom(String... strArr) {
        String str = null;
        if (strArr != null) {
            if (strArr.length == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = strArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                String str2 = strArr[i2];
                if (str2 != null) {
                    arrayList.add(str2.replace(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "").toLowerCase(Locale.US));
                }
                i = i2 + 1;
            }
            Collections.sort(arrayList);
            StringBuilder sb = new StringBuilder();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
            }
            String sb2 = sb.toString();
            str = null;
            if (sb2.length() > 0) {
                str = sha1(sb2);
            }
        }
        return str;
    }

    public static byte[] dehexify(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return bArr;
            }
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
            i = i2 + 2;
        }
    }

    public static String extractFieldFromSystemFile(File file, String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        String str2;
        try {
            if (!file.exists()) {
                return null;
            }
            try {
                BufferedReader bufferedReader3 = new BufferedReader(new FileReader(file), BYTES_IN_A_KILOBYTE);
                while (true) {
                    try {
                        String readLine = bufferedReader3.readLine();
                        bufferedReader = bufferedReader3;
                        str2 = null;
                        if (readLine == null) {
                            break;
                        }
                        String[] split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            str2 = split[1];
                            bufferedReader = bufferedReader3;
                            break;
                        }
                    } catch (Exception e) {
                        bufferedReader2 = bufferedReader3;
                        e = e;
                        Logger logger = Fabric.getLogger();
                        BufferedReader bufferedReader4 = bufferedReader2;
                        StringBuilder sb = new StringBuilder();
                        BufferedReader bufferedReader5 = bufferedReader2;
                        sb.append("Error parsing ");
                        BufferedReader bufferedReader6 = bufferedReader2;
                        sb.append(file);
                        BufferedReader bufferedReader7 = bufferedReader2;
                        logger.mo2873e(Fabric.TAG, sb.toString(), e);
                        bufferedReader = bufferedReader2;
                        str2 = null;
                        closeOrLog(bufferedReader, "Failed to close system file reader.");
                        return str2;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                bufferedReader2 = null;
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
                closeOrLog(bufferedReader, "Failed to close system file reader.");
                throw th;
            }
            closeOrLog(bufferedReader, "Failed to close system file reader.");
            return str2;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @TargetApi(16)
    public static void finishAffinity(Activity activity, int i) {
        if (activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.setResult(i);
            activity.finish();
        }
    }

    @TargetApi(16)
    public static void finishAffinity(Context context, int i) {
        if (context instanceof Activity) {
            finishAffinity((Activity) context, i);
        }
    }

    public static void flushOrLog(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e) {
                Fabric.getLogger().mo2873e(Fabric.TAG, str, e);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Closeable] */
    public static String getAppIconHashOrNull(Context context) {
        Context context2;
        InputStream inputStream;
        try {
            try {
                inputStream = context.getResources().openRawResource(getAppIconResourceId(context));
            } catch (Exception e) {
                e = e;
                inputStream = null;
            } catch (Throwable th) {
                th = th;
                context2 = null;
                closeOrLog(context2, "Failed to close icon input stream.");
                throw th;
            }
            try {
                String sha1 = sha1(inputStream);
                String str = isNullOrEmpty(sha1) ? null : sha1;
                closeOrLog(inputStream, "Failed to close icon input stream.");
                return str;
            } catch (Exception e2) {
                e = e2;
                Fabric.getLogger().mo2873e(Fabric.TAG, "Could not calculate hash for app icon.", e);
                closeOrLog(inputStream, "Failed to close icon input stream.");
                return null;
            }
        } catch (Throwable th2) {
            context2 = context;
            th = th2;
            closeOrLog(context2, "Failed to close icon input stream.");
            throw th;
        }
    }

    public static int getAppIconResourceId(Context context) {
        return StubApp.getOrigApplicationContext(context.getApplicationContext()).getApplicationInfo().icon;
    }

    public static ActivityManager.RunningAppProcessInfo getAppProcessInfo(String str, Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(str)) {
                return runningAppProcessInfo;
            }
        }
        return null;
    }

    public static Float getBatteryLevel(Context context) {
        if (context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")) == null) {
            return null;
        }
        return Float.valueOf(r0.getIntExtra("level", -1) / r0.getIntExtra("scale", -1));
    }

    public static int getBatteryVelocity(Context context, boolean z) {
        Float batteryLevel = getBatteryLevel(context);
        if (!z || batteryLevel == null) {
            return 1;
        }
        if (batteryLevel.floatValue() >= 99.0d) {
            return 3;
        }
        return ((double) batteryLevel.floatValue()) < 99.0d ? 2 : 0;
    }

    public static boolean getBooleanResourceValue(Context context, String str, boolean z) {
        Resources resources;
        if (context != null && (resources = context.getResources()) != null) {
            int resourcesIdentifier = getResourcesIdentifier(context, str, "bool");
            if (resourcesIdentifier > 0) {
                return resources.getBoolean(resourcesIdentifier);
            }
            int resourcesIdentifier2 = getResourcesIdentifier(context, str, "string");
            if (resourcesIdentifier2 > 0) {
                return Boolean.parseBoolean(context.getString(resourcesIdentifier2));
            }
        }
        return z;
    }

    public static int getCpuArchitectureInt() {
        return Architecture.getValue().ordinal();
    }

    public static int getDeviceState(Context context) {
        int i = isEmulator(context) ? 1 : 0;
        int i2 = i;
        if (isRooted(context)) {
            i2 = i | 2;
        }
        int i3 = i2;
        if (isDebuggerAttached()) {
            i3 = i2 | 4;
        }
        return i3;
    }

    public static boolean getProximitySensorEnabled(Context context) {
        boolean z = false;
        if (isEmulator(context)) {
            return false;
        }
        if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null) {
            z = true;
        }
        return z;
    }

    public static String getResourcePackageName(Context context) {
        int i = StubApp.getOrigApplicationContext(context.getApplicationContext()).getApplicationInfo().icon;
        return i > 0 ? context.getResources().getResourcePackageName(i) : context.getPackageName();
    }

    public static int getResourcesIdentifier(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, getResourcePackageName(context));
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(CLS_SHARED_PREFERENCES_NAME, 0);
    }

    public static String getStringsFileValue(Context context, String str) {
        int resourcesIdentifier = getResourcesIdentifier(context, str, "string");
        return resourcesIdentifier > 0 ? context.getString(resourcesIdentifier) : "";
    }

    public static long getTotalRamInBytes() {
        long j;
        synchronized (CommonUtils.class) {
            try {
                if (totalRamInBytes == -1) {
                    String extractFieldFromSystemFile = extractFieldFromSystemFile(new File("/proc/meminfo"), "MemTotal");
                    long j2 = 0;
                    if (!TextUtils.isEmpty(extractFieldFromSystemFile)) {
                        String upperCase = extractFieldFromSystemFile.toUpperCase(Locale.US);
                        try {
                            if (upperCase.endsWith("KB")) {
                                j2 = convertMemInfoToBytes(upperCase, "KB", BYTES_IN_A_KILOBYTE);
                            } else if (upperCase.endsWith("MB")) {
                                j2 = convertMemInfoToBytes(upperCase, "MB", BYTES_IN_A_MEGABYTE);
                            } else if (upperCase.endsWith("GB")) {
                                j2 = convertMemInfoToBytes(upperCase, "GB", BYTES_IN_A_GIGABYTE);
                            } else {
                                Fabric.getLogger().mo2870d(Fabric.TAG, "Unexpected meminfo format while computing RAM: " + upperCase);
                                j2 = 0;
                            }
                        } catch (NumberFormatException e) {
                            Fabric.getLogger().mo2873e(Fabric.TAG, "Unexpected meminfo format while computing RAM: " + upperCase, e);
                            j2 = 0;
                        }
                    }
                    totalRamInBytes = j2;
                }
                j = totalRamInBytes;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    private static String hash(InputStream inputStream, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            byte[] bArr = new byte[BYTES_IN_A_KILOBYTE];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return hexify(messageDigest.digest());
                }
                messageDigest.update(bArr, 0, read);
            }
        } catch (Exception e) {
            Fabric.getLogger().mo2873e(Fabric.TAG, "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String hash(String str, String str2) {
        return hash(str.getBytes(), str2);
    }

    private static String hash(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return hexify(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            Fabric.getLogger().mo2873e(Fabric.TAG, "Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return "";
        }
    }

    public static String hexify(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bArr.length) {
                return new String(cArr);
            }
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            char[] cArr2 = HEX_VALUES;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[i3 & 15];
            i = i2 + 1;
        }
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static boolean isClsTrace(Context context) {
        if (clsTrace == null) {
            clsTrace = Boolean.valueOf(getBooleanResourceValue(context, CLS_TRACE_PREFERENCE_NAME, false));
        }
        return clsTrace.booleanValue();
    }

    public static boolean isDebuggerAttached() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static boolean isEmulator(Context context) {
        return SDK.equals(Build.PRODUCT) || GOOGLE_SDK.equals(Build.PRODUCT) || Settings.Secure.getString(context.getContentResolver(), "android_id") == null;
    }

    @Deprecated
    public static boolean isLoggingEnabled(Context context) {
        return false;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isRooted(Context context) {
        boolean isEmulator = isEmulator(context);
        String str = Build.TAGS;
        if ((isEmulator || str == null || !str.contains("test-keys")) && !new File("/system/app/Superuser.apk").exists()) {
            return !isEmulator && new File("/system/xbin/su").exists();
        }
        return true;
    }

    public static void logControlled(Context context, int i, String str, String str2) {
        if (isClsTrace(context)) {
            Fabric.getLogger().log(i, Fabric.TAG, str2);
        }
    }

    public static void logControlled(Context context, String str) {
        if (isClsTrace(context)) {
            Fabric.getLogger().mo2870d(Fabric.TAG, str);
        }
    }

    public static void logControlledError(Context context, String str, Throwable th) {
        if (isClsTrace(context)) {
            Fabric.getLogger().mo2872e(Fabric.TAG, str);
        }
    }

    public static void logOrThrowIllegalArgumentException(String str, String str2) {
        if (Fabric.isDebuggable()) {
            throw new IllegalArgumentException(str2);
        }
        Fabric.getLogger().mo2878w(str, str2);
    }

    public static void logOrThrowIllegalStateException(String str, String str2) {
        if (Fabric.isDebuggable()) {
            throw new IllegalStateException(str2);
        }
        Fabric.getLogger().mo2878w(str, str2);
    }

    public static String logPriorityToString(int i) {
        switch (i) {
            case 2:
                return LOG_PRIORITY_NAME_VERBOSE;
            case 3:
                return LOG_PRIORITY_NAME_DEBUG;
            case 4:
                return LOG_PRIORITY_NAME_INFO;
            case 5:
                return LOG_PRIORITY_NAME_WARN;
            case 6:
                return LOG_PRIORITY_NAME_ERROR;
            case 7:
                return LOG_PRIORITY_NAME_ASSERT;
            default:
                return LOG_PRIORITY_NAME_UNKNOWN;
        }
    }

    public static void openKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    public static String padWithZerosToMaxIntWidth(int i) {
        if (i >= 0) {
            return String.format(Locale.US, "%1$10s", Integer.valueOf(i)).replace(' ', '0');
        }
        throw new IllegalArgumentException("value must be zero or greater");
    }

    public static String resolveBuildId(Context context) {
        int resourcesIdentifier = getResourcesIdentifier(context, FABRIC_BUILD_ID, "string");
        int i = resourcesIdentifier;
        if (resourcesIdentifier == 0) {
            i = getResourcesIdentifier(context, CRASHLYTICS_BUILD_ID, "string");
        }
        if (i == 0) {
            return null;
        }
        String string = context.getResources().getString(i);
        Fabric.getLogger().mo2870d(Fabric.TAG, "Build ID is: " + string);
        return string;
    }

    public static String sha1(InputStream inputStream) {
        return hash(inputStream, SHA1_INSTANCE);
    }

    public static String sha1(String str) {
        return hash(str, SHA1_INSTANCE);
    }

    public static String sha256(String str) {
        return hash(str, SHA256_INSTANCE);
    }

    public static String streamToString(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static boolean stringsEqualIncludingNull(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str != null) {
            return str.equals(str2);
        }
        return false;
    }
}
