package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/FirebaseInfo.class */
public class FirebaseInfo {
    static final String FIREBASE_FEATURE_SWITCH = "com.crashlytics.useFirebaseAppId";
    static final String GOOGLE_APP_ID = "google_app_id";

    protected String createApiKeyFromFirebaseAppId(String str) {
        return CommonUtils.sha256(str).substring(0, 40);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getApiKeyFromFirebaseAppId(Context context) {
        int resourcesIdentifier = CommonUtils.getResourcesIdentifier(context, GOOGLE_APP_ID, "string");
        if (resourcesIdentifier == 0) {
            return null;
        }
        Fabric.getLogger().mo2870d(Fabric.TAG, "Generating Crashlytics ApiKey from google_app_id in Strings");
        return createApiKeyFromFirebaseAppId(context.getResources().getString(resourcesIdentifier));
    }

    public boolean isFirebaseCrashlyticsEnabled(Context context) {
        if (CommonUtils.getBooleanResourceValue(context, FIREBASE_FEATURE_SWITCH, false)) {
            return true;
        }
        boolean z = CommonUtils.getResourcesIdentifier(context, GOOGLE_APP_ID, "string") != 0;
        boolean z2 = (TextUtils.isEmpty(new ApiKey().getApiKeyFromManifest(context)) && TextUtils.isEmpty(new ApiKey().getApiKeyFromStrings(context))) ? false : true;
        boolean z3 = false;
        if (z) {
            z3 = false;
            if (!z2) {
                z3 = true;
            }
        }
        return z3;
    }
}
