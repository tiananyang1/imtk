package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/ApiKey.class */
public class ApiKey {
    static final String CRASHLYTICS_API_KEY = "com.crashlytics.ApiKey";
    static final String FABRIC_API_KEY = "io.fabric.ApiKey";
    static final String STRING_TWITTER_CONSUMER_SECRET = "@string/twitter_consumer_secret";

    @Deprecated
    public static String getApiKey(Context context) {
        Fabric.getLogger().mo2878w(Fabric.TAG, "getApiKey(context) is deprecated, please upgrade kit(s) to the latest version.");
        return new ApiKey().getValue(context);
    }

    @Deprecated
    public static String getApiKey(Context context, boolean z) {
        Fabric.getLogger().mo2878w(Fabric.TAG, "getApiKey(context, debug) is deprecated, please upgrade kit(s) to the latest version.");
        return new ApiKey().getValue(context);
    }

    protected String buildApiKeyInstructions() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }

    protected String getApiKeyFromFirebaseAppId(Context context) {
        return new FirebaseInfo().getApiKeyFromFirebaseAppId(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getApiKeyFromManifest(Context context) {
        String str = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            str = null;
            if (bundle != null) {
                String string = bundle.getString(FABRIC_API_KEY);
                try {
                    if (STRING_TWITTER_CONSUMER_SECRET.equals(string)) {
                        Fabric.getLogger().mo2870d(Fabric.TAG, "Ignoring bad default value for Fabric ApiKey set by FirebaseUI-Auth");
                        string = null;
                    }
                    str = string;
                    if (string == null) {
                        Fabric.getLogger().mo2870d(Fabric.TAG, "Falling back to Crashlytics key lookup from Manifest");
                        str = string;
                        return bundle.getString(CRASHLYTICS_API_KEY);
                    }
                } catch (Exception e) {
                    str = string;
                    e = e;
                    Fabric.getLogger().mo2870d(Fabric.TAG, "Caught non-fatal exception while retrieving apiKey: " + e);
                    return str;
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getApiKeyFromStrings(Context context) {
        int resourcesIdentifier = CommonUtils.getResourcesIdentifier(context, FABRIC_API_KEY, "string");
        int i = resourcesIdentifier;
        if (resourcesIdentifier == 0) {
            Fabric.getLogger().mo2870d(Fabric.TAG, "Falling back to Crashlytics key lookup from Strings");
            i = CommonUtils.getResourcesIdentifier(context, CRASHLYTICS_API_KEY, "string");
        }
        if (i != 0) {
            return context.getResources().getString(i);
        }
        return null;
    }

    public String getValue(Context context) {
        String apiKeyFromManifest = getApiKeyFromManifest(context);
        String str = apiKeyFromManifest;
        if (TextUtils.isEmpty(apiKeyFromManifest)) {
            str = getApiKeyFromStrings(context);
        }
        String str2 = str;
        if (TextUtils.isEmpty(str)) {
            str2 = getApiKeyFromFirebaseAppId(context);
        }
        if (TextUtils.isEmpty(str2)) {
            logErrorOrThrowException(context);
        }
        return str2;
    }

    protected void logErrorOrThrowException(Context context) {
        if (Fabric.isDebuggable() || CommonUtils.isAppDebuggable(context)) {
            throw new IllegalArgumentException(buildApiKeyInstructions());
        }
        Fabric.getLogger().mo2872e(Fabric.TAG, buildApiKeyInstructions());
    }
}
