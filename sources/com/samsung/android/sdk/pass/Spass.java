package com.samsung.android.sdk.pass;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/Spass.class */
public class Spass implements SsdkInterface {
    public static final int DEVICE_FINGERPRINT = 0;
    public static final int DEVICE_FINGERPRINT_AVAILABLE_PASSWORD = 4;
    public static final int DEVICE_FINGERPRINT_CUSTOMIZED_DIALOG = 2;
    public static final int DEVICE_FINGERPRINT_FINGER_INDEX = 1;
    public static final int DEVICE_FINGERPRINT_UNIQUE_ID = 3;

    /* renamed from: a */
    private SpassFingerprint f134a;

    /* renamed from: a */
    private static boolean m13a() {
        try {
            Class<?> cls = Class.forName("com.samsung.android.feature.FloatingFeature");
            boolean booleanValue = ((Boolean) cls.getMethod("getEnableStatus", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE")).booleanValue();
            Log.d("SecFloating", "floating feature : " + booleanValue);
            return booleanValue;
        } catch (Exception e) {
            Log.d("SecFloating", "Floating feature is not supported (non-samsung device)");
            try {
                Class<?> cls2 = Class.forName("com.samsung.android.feature.semFloatingFeature");
                boolean booleanValue2 = ((Boolean) cls2.getMethod("getBoolean", String.class).invoke(cls2.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE")).booleanValue();
                Log.d("SecFloating", "floating feature : " + booleanValue2);
                return booleanValue2;
            } catch (Exception e2) {
                Log.d("SecFloating", "Floating feature is not supported this device (non-samsung device)");
                return false;
            }
        }
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public int getVersionCode() {
        return 9;
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public String getVersionName() {
        return String.format("%d.%d.%d", 1, 2, 2);
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public void initialize(Context context) throws SsdkUnsupportedException {
        if (this.f134a != null) {
            return;
        }
        if (context == null) {
            throw new IllegalArgumentException("context passed is null.");
        }
        try {
            if (m13a()) {
                if (context.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
                    Log.d("SM_SDK", " com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY is not allowed ");
                } else {
                    ContentValues contentValues = new ContentValues();
                    String name = getClass().getPackage().getName();
                    String str = String.valueOf(context.getPackageName()) + "#" + getVersionCode();
                    contentValues.put(Constants.APP_ID, name);
                    contentValues.put("feature", str);
                    Intent intent = new Intent();
                    intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
                    intent.putExtra("data", contentValues);
                    intent.setPackage("com.samsung.android.providers.context");
                    context.sendBroadcast(intent);
                }
            }
            if (!SsdkVendorCheck.isSamsungDevice()) {
                throw new SsdkUnsupportedException("This is not Samsung device.", 0);
            }
            this.f134a = new SpassFingerprint(context);
            if (!this.f134a.m33a()) {
                throw new SsdkUnsupportedException("This device does not provide FingerprintService.", 1);
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("context is not valid.");
        } catch (SecurityException e2) {
            throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required.");
        }
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public boolean isFeatureEnabled(int i) {
        SpassFingerprint spassFingerprint = this.f134a;
        if (spassFingerprint == null) {
            throw new IllegalStateException("initialize() is not Called first.");
        }
        if (i == 0) {
            return spassFingerprint.m33a();
        }
        if (i == 1 || i == 2) {
            return this.f134a.m34b();
        }
        if (i == 3) {
            return spassFingerprint.m35c();
        }
        if (i == 4) {
            return spassFingerprint.m36d();
        }
        throw new IllegalArgumentException("type passed is not valid");
    }
}
