package com.intercom.input.gallery;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/PermissionHelper.class */
class PermissionHelper {
    private static final String ASKED_FOR_PERMISSION = "asked_for_permission";
    private static final String PREFS = "intercom_composer_permission_status_prefs";
    private final Activity activity;
    private final SharedPreferences sharedPreferences;

    PermissionHelper(Activity activity, SharedPreferences sharedPreferences) {
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PermissionHelper create(Activity activity) {
        return new PermissionHelper(activity, activity.getSharedPreferences(PREFS, 0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPermissionStatus(String str) {
        if (ContextCompat.checkSelfPermission(this.activity, str) == 0) {
            return 0;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.activity, str)) {
            return 1;
        }
        return this.sharedPreferences.getBoolean(ASKED_FOR_PERMISSION, false) ? 2 : 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAskedForPermissionPref(boolean z) {
        this.sharedPreferences.edit().putBoolean(ASKED_FOR_PERMISSION, z).apply();
    }
}
