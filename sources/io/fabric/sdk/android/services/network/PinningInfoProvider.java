package io.fabric.sdk.android.services.network;

import java.io.InputStream;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/network/PinningInfoProvider.class */
public interface PinningInfoProvider {
    public static final long PIN_CREATION_TIME_UNDEFINED = -1;

    String getKeyStorePassword();

    InputStream getKeyStoreStream();

    long getPinCreationTimeInMillis();

    String[] getPins();
}
