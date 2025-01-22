package io.fabric.sdk.android.services.persistence;

import android.content.SharedPreferences;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/persistence/PreferenceStore.class */
public interface PreferenceStore {
    SharedPreferences.Editor edit();

    SharedPreferences get();

    boolean save(SharedPreferences.Editor editor);
}
