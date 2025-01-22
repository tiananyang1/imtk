package com.samsung.android.sdk.pass.support;

import android.util.Log;
import java.lang.reflect.Field;
import java.util.HashMap;

/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/support/SdkSupporter.class */
public class SdkSupporter {
    public static final String CLASSNAME_FINGERPRINT_CLIENT = "com.samsung.android.fingerprint.IFingerprintClient$Stub";
    public static final String CLASSNAME_FINGERPRINT_CLIENT_SPEC_BUILDER = "com.samsung.android.fingerprint.FingerprintManager$FingerprintClientSpecBuilder";
    public static final String CLASSNAME_FINGERPRINT_MANAGER = "com.samsung.android.fingerprint.FingerprintManager";

    public static boolean copyStaticFields(Object obj, Class cls, String str, String str2) {
        Field field;
        try {
            Field[] fields = Class.forName(str).getFields();
            HashMap hashMap = new HashMap();
            int length = fields.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                Field field2 = fields[i2];
                hashMap.put(field2.getName(), field2);
                i = i2 + 1;
            }
            Field[] fields2 = cls.getFields();
            int length2 = fields2.length;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= length2) {
                    return true;
                }
                Field field3 = fields2[i4];
                String name = field3.getName();
                if ((str2 == null || name.startsWith(str2)) && (field = (Field) hashMap.get(name)) != null && field.getType().equals(field3.getType())) {
                    field3.set(obj, field.get(null));
                }
                i3 = i4 + 1;
            }
        } catch (Exception e) {
            Log.w("SdkSupporter", "copyFields: failed - " + e);
            return true;
        }
    }
}
