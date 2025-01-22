package im.imkey.imkeylibrary.bluetooth.Callback;

import im.imkey.imkeylibrary.bluetooth.BleDevice;
import im.imkey.imkeylibrary.bluetooth.ErrorCode;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/bluetooth/Callback/ScanCallback.class */
public interface ScanCallback {
    void onScanDevice(BleDevice bleDevice);

    void onScanFail(ErrorCode errorCode);

    void onScanStarted();

    void onScanStopped();
}
