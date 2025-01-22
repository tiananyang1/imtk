package io.fabric.sdk.android.services.persistence;

import java.io.File;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/persistence/FileStore.class */
public interface FileStore {
    File getCacheDir();

    File getExternalCacheDir();

    File getExternalFilesDir();

    File getFilesDir();
}
