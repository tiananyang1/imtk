package com.samsung.android.sdk;

import android.content.Context;

/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/SsdkInterface.class */
public interface SsdkInterface {
    int getVersionCode();

    String getVersionName();

    void initialize(Context context) throws SsdkUnsupportedException;

    boolean isFeatureEnabled(int i);
}
