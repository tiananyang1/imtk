package im.imkey.imkeylibrary.bluetooth.Callback;

import im.imkey.imkeylibrary.bluetooth.BleDevice;
import im.imkey.imkeylibrary.bluetooth.ErrorCode;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/bluetooth/Callback/ConnectCallback.class */
public interface ConnectCallback {
    void onConnectFail(ErrorCode errorCode);

    void onConnected(BleDevice bleDevice);

    void onConnecting(BleDevice bleDevice);

    void onDisconnected(BleDevice bleDevice);
}
