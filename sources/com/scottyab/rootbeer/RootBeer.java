package com.scottyab.rootbeer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.scottyab.rootbeer.util.QLog;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* loaded from: classes08-dex2jar.jar:com/scottyab/rootbeer/RootBeer.class */
public class RootBeer {
    private boolean loggingEnabled = true;
    private final Context mContext;

    public RootBeer(Context context) {
        this.mContext = context;
    }

    private boolean isAnyPackageFromListInstalled(List<String> list) {
        PackageManager packageManager = this.mContext.getPackageManager();
        boolean z = false;
        for (String str : list) {
            try {
                packageManager.getPackageInfo(str, 0);
                QLog.m47e(str + " ROOT management app detected!");
                z = true;
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return z;
    }

    private String[] mountReader() {
        InputStream inputStream;
        String str;
        try {
            inputStream = Runtime.getRuntime().exec("mount").getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            inputStream = null;
        }
        if (inputStream == null) {
            return null;
        }
        try {
            str = new Scanner(inputStream).useDelimiter("\\A").next();
        } catch (NoSuchElementException e2) {
            e2.printStackTrace();
            str = "";
        }
        return str.split("\n");
    }

    private String[] propsReader() {
        InputStream inputStream;
        String str;
        try {
            inputStream = Runtime.getRuntime().exec("getprop").getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            inputStream = null;
        }
        try {
            str = new Scanner(inputStream).useDelimiter("\\A").next();
        } catch (NoSuchElementException e2) {
            QLog.m48e("Error getprop, NoSuchElementException: " + e2.getMessage(), e2);
            str = "";
        }
        return str.split("\n");
    }

    public boolean canLoadNativeLibrary() {
        return new RootBeerNative().wasNativeLibraryLoaded();
    }

    public boolean checkForBinary(String str) {
        boolean z = false;
        for (String str2 : Const.suPaths) {
            String str3 = str2 + str;
            if (new File(str3).exists()) {
                QLog.m50v(str3 + " binary detected!");
                z = true;
            }
        }
        return z;
    }

    public boolean checkForBusyBoxBinary() {
        return checkForBinary("busybox");
    }

    public boolean checkForDangerousProps() {
        HashMap hashMap = new HashMap();
        hashMap.put("ro.debuggable", "1");
        hashMap.put("ro.secure", "0");
        boolean z = false;
        for (String str : propsReader()) {
            for (String str2 : hashMap.keySet()) {
                if (str.contains(str2)) {
                    String str3 = "[" + ((String) hashMap.get(str2)) + "]";
                    if (str.contains(str3)) {
                        QLog.m50v(str2 + " = " + str3 + " detected!");
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public boolean checkForRWPaths() {
        boolean z = false;
        for (String str : mountReader()) {
            String[] split = str.split(" ");
            if (split.length < 4) {
                QLog.m47e("Error formatting mount line: " + str);
            } else {
                String str2 = split[1];
                String str3 = split[3];
                String[] strArr = Const.pathsThatShouldNotBeWrtiable;
                int length = strArr.length;
                int i = 0;
                while (i < length) {
                    String str4 = strArr[i];
                    boolean z2 = z;
                    if (str2.equalsIgnoreCase(str4)) {
                        String[] split2 = str3.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                        int length2 = split2.length;
                        int i2 = 0;
                        while (true) {
                            int i3 = i2;
                            z2 = z;
                            if (i3 >= length2) {
                                break;
                            }
                            if (split2[i3].equalsIgnoreCase("rw")) {
                                QLog.m50v(str4 + " path is mounted with rw permissions! " + str);
                                z2 = true;
                                break;
                            }
                            i2 = i3 + 1;
                        }
                    }
                    i++;
                    z = z2;
                }
            }
        }
        return z;
    }

    public boolean checkForRootNative() {
        boolean z = false;
        if (!canLoadNativeLibrary()) {
            QLog.m47e("We could not load the native library to test for root");
            return false;
        }
        String[] strArr = new String[Const.suPaths.length];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= strArr.length) {
                break;
            }
            strArr[i2] = Const.suPaths[i2] + "su";
            i = i2 + 1;
        }
        RootBeerNative rootBeerNative = new RootBeerNative();
        rootBeerNative.setLogDebugMessages(this.loggingEnabled);
        if (rootBeerNative.checkForRoot(strArr) > 0) {
            z = true;
        }
        return z;
    }

    public boolean checkForSuBinary() {
        return checkForBinary("su");
    }

    public boolean checkSuExists() {
        boolean z = false;
        Process process = null;
        Process process2 = null;
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"which", "su"});
            process2 = exec;
            process = exec;
            if (new BufferedReader(new InputStreamReader(exec.getInputStream())).readLine() != null) {
                z = true;
            }
            if (exec != null) {
                exec.destroy();
            }
            return z;
        } catch (Throwable th) {
            if (process == null) {
                return false;
            }
            process.destroy();
            return false;
        }
    }

    public boolean detectPotentiallyDangerousApps() {
        return detectPotentiallyDangerousApps(null);
    }

    public boolean detectPotentiallyDangerousApps(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(Const.knownDangerousAppsPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectRootCloakingApps() {
        return detectRootCloakingApps(null);
    }

    public boolean detectRootCloakingApps(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(Const.knownRootCloakingPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectRootManagementApps() {
        return detectRootManagementApps(null);
    }

    public boolean detectRootManagementApps(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(Const.knownRootAppsPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectTestKeys() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public boolean isRooted() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary("su") || checkForBinary("busybox") || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative();
    }

    public boolean isRootedWithoutBusyBoxCheck() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary("su") || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative();
    }

    public void setLogging(boolean z) {
        this.loggingEnabled = z;
        QLog.LOGGING_LEVEL = z ? 5 : 0;
    }
}
