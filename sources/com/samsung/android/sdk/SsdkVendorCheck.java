package com.samsung.android.sdk;

import android.os.Build;

/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/SsdkVendorCheck.class */
public class SsdkVendorCheck {
    private static String strBrand = Build.BRAND;
    private static String strManufacturer = Build.MANUFACTURER;

    private SsdkVendorCheck() {
    }

    public static boolean isSamsungDevice() {
        String str = strBrand;
        if (str == null || strManufacturer == null) {
            return false;
        }
        return str.compareToIgnoreCase("Samsung") == 0 || strManufacturer.compareToIgnoreCase("Samsung") == 0;
    }
}
