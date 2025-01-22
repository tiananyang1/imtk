package com.sensorsdata.analytics.android.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.sensorsdata.analytics.android.sdk.SALog;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/util/NetworkUtils.class */
public class NetworkUtils {
    public static boolean isNetworkAvailable(Context context) {
        boolean z;
        if (!SensorsDataUtils.checkHasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            boolean z2 = false;
            if (connectivityManager != null) {
                if (Build.VERSION.SDK_INT >= 23) {
                    Network activeNetwork = connectivityManager.getActiveNetwork();
                    z2 = false;
                    if (activeNetwork != null) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                        z2 = false;
                        if (networkCapabilities != null) {
                            if (!networkCapabilities.hasTransport(1) && !networkCapabilities.hasTransport(0) && !networkCapabilities.hasTransport(3)) {
                                z = false;
                                if (networkCapabilities.hasTransport(4)) {
                                }
                                return z;
                            }
                            z = true;
                            return z;
                        }
                    }
                } else {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    z2 = false;
                    if (activeNetworkInfo != null) {
                        z2 = false;
                        if (activeNetworkInfo.isConnected()) {
                            z2 = true;
                        }
                    }
                }
            }
            return z2;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return false;
        }
    }

    public static boolean isShouldFlush(String str, int i) {
        return (toNetworkType(str) & i) != 0;
    }

    public static String networkType(Context context) {
        try {
            if (!SensorsDataUtils.checkHasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                return "NULL";
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                if (Build.VERSION.SDK_INT >= 23) {
                    Network activeNetwork = connectivityManager.getActiveNetwork();
                    if (activeNetwork == null) {
                        return "NULL";
                    }
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                    if (networkCapabilities != null) {
                        if (networkCapabilities.hasTransport(1)) {
                            return "WIFI";
                        }
                        if (!networkCapabilities.hasTransport(0) && !networkCapabilities.hasTransport(3) && !networkCapabilities.hasTransport(4)) {
                            return "NULL";
                        }
                    }
                } else {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                        return "NULL";
                    }
                    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                    if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                        return "WIFI";
                    }
                }
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "NULL";
            }
            int networkType = telephonyManager.getNetworkType();
            if (networkType == 20) {
                return "5G";
            }
            switch (networkType) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return "2G";
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return "3G";
                case 13:
                    return "4G";
                default:
                    return "NULL";
            }
        } catch (Exception e) {
            return "NULL";
        }
    }

    private static int toNetworkType(String str) {
        if ("NULL".equals(str)) {
            return 255;
        }
        if ("WIFI".equals(str)) {
            return 8;
        }
        if ("2G".equals(str)) {
            return 1;
        }
        if ("3G".equals(str)) {
            return 2;
        }
        if ("4G".equals(str)) {
            return 4;
        }
        return "5G".equals(str) ? 16 : 255;
    }
}
