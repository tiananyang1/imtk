package io.fabric.sdk.android.services.common;

import io.fabric.sdk.android.services.common.IdManager;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/DeviceIdentifierProvider.class */
public interface DeviceIdentifierProvider {
    Map<IdManager.DeviceIdentifierType, String> getDeviceIdentifiers();
}
