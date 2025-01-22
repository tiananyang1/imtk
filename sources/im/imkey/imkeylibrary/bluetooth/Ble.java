package im.imkey.imkeylibrary.bluetooth;

import android.content.Context;
import android.util.Log;
import com.ftsafe.bluetooth.key.FTBtKeyErrCode;
import com.ftsafe.bluetooth.key.jkey.FTBluetoothDevice;
import com.ftsafe.bluetooth.key.jkey.FTBluetoothJKey;
import com.ftsafe.bluetooth.key.jkey.IFTConnectEventCallback;
import com.ftsafe.bluetooth.key.jkey.IFTRecvDataEventCallback;
import com.ftsafe.bluetooth.key.jkey.IFTScanCallback;
import im.imkey.imkeylibrary.bluetooth.Callback.ConnectCallback;
import im.imkey.imkeylibrary.bluetooth.Callback.ScanCallback;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/bluetooth/Ble.class */
public class Ble {
    private static final String TAG = "Ble";
    private BleDevice connectedDevice;
    private Boolean initialized = false;
    private Context mContext;
    private ErrorCode mErrorCode;
    private HashMap<String, FTBluetoothDevice> mMapDevices;
    private String mResponse;
    private FTBluetoothJKey sFtBluetoothJKey;

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/bluetooth/Ble$Holder.class */
    private static class Holder {
        private static Ble sInstance = new Ble();

        private Holder() {
        }
    }

    public static Ble getInstance() {
        return Holder.sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keepConnect() {
        Apdu.checkResponse(sendApdu(Constants.APDU_GET_BATTERY_POWER));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BleDevice toDevice(FTBluetoothDevice fTBluetoothDevice) {
        if (fTBluetoothDevice == null) {
            return null;
        }
        return new BleDevice(fTBluetoothDevice.getBluetoothDevice(), fTBluetoothDevice.getDevType(), fTBluetoothDevice.getRadioDevName(), fTBluetoothDevice.getRadioUUID(), fTBluetoothDevice.getDevRssi(), fTBluetoothDevice.getRadioManufacturerData());
    }

    public void connect(BleDevice bleDevice, int i, final ConnectCallback connectCallback) {
        if (!this.initialized.booleanValue()) {
            throw new ImkeyException(Messages.IMKEY_SDK_BLE_NOT_INITIALIZE);
        }
        if (bleDevice == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        if (i <= 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        if (connectCallback == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        FTBtKeyErrCode ftBTKeyComm_Connect = this.sFtBluetoothJKey.ftBTKeyComm_Connect(new FTBluetoothDevice(bleDevice.getBluetoothDevice(), bleDevice.getDevType(), bleDevice.getRadioDevName(), bleDevice.getRadioUUID(), bleDevice.getDevRssi(), bleDevice.getRadioManufacturerData()), i, new IFTConnectEventCallback() { // from class: im.imkey.imkeylibrary.bluetooth.Ble.2
            public void onFTBtConnectFail(FTBtKeyErrCode fTBtKeyErrCode) {
                Ble.this.connectedDevice = null;
                connectCallback.onConnectFail(ErrorCode.toErrorCode(fTBtKeyErrCode));
            }

            public void onFTBtConnected(FTBluetoothDevice fTBluetoothDevice) {
                Ble ble = Ble.this;
                ble.connectedDevice = ble.toDevice(fTBluetoothDevice);
                Ble.this.keepConnect();
                connectCallback.onConnected(Ble.this.connectedDevice);
            }

            public void onFTBtConnecting(FTBluetoothDevice fTBluetoothDevice) {
                connectCallback.onConnecting(Ble.this.toDevice(fTBluetoothDevice));
            }

            public void onFTBtDisconnected(FTBluetoothDevice fTBluetoothDevice) {
                Ble.this.connectedDevice = null;
                connectCallback.onDisconnected(Ble.this.toDevice(fTBluetoothDevice));
            }
        });
        if (ftBTKeyComm_Connect != FTBtKeyErrCode.FT_BTKey_SUCCESS) {
            connectCallback.onConnectFail(ErrorCode.toErrorCode(ftBTKeyComm_Connect));
        }
    }

    public void connectDirectly(String str, int i, final ConnectCallback connectCallback) {
        if (!this.initialized.booleanValue()) {
            throw new ImkeyException(Messages.IMKEY_SDK_BLE_NOT_INITIALIZE);
        }
        if (str == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        if (i <= 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        if (connectCallback == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        FTBtKeyErrCode ftBTKeyComm_ConnectDirectly = this.sFtBluetoothJKey.ftBTKeyComm_ConnectDirectly(4, str, i, new IFTConnectEventCallback() { // from class: im.imkey.imkeylibrary.bluetooth.Ble.4
            public void onFTBtConnectFail(FTBtKeyErrCode fTBtKeyErrCode) {
                Ble.this.connectedDevice = null;
                connectCallback.onConnectFail(ErrorCode.toErrorCode(fTBtKeyErrCode));
            }

            public void onFTBtConnected(FTBluetoothDevice fTBluetoothDevice) {
                Ble ble = Ble.this;
                ble.connectedDevice = ble.toDevice(fTBluetoothDevice);
                Ble.this.keepConnect();
                connectCallback.onConnected(Ble.this.connectedDevice);
            }

            public void onFTBtConnecting(FTBluetoothDevice fTBluetoothDevice) {
                connectCallback.onConnecting(Ble.this.toDevice(fTBluetoothDevice));
            }

            public void onFTBtDisconnected(FTBluetoothDevice fTBluetoothDevice) {
                Ble.this.connectedDevice = null;
                connectCallback.onDisconnected(Ble.this.toDevice(fTBluetoothDevice));
            }
        });
        if (ftBTKeyComm_ConnectDirectly != FTBtKeyErrCode.FT_BTKey_SUCCESS) {
            connectCallback.onConnectFail(ErrorCode.toErrorCode(ftBTKeyComm_ConnectDirectly));
        }
    }

    public void disconnect(BleDevice bleDevice) {
        if (bleDevice == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        if (!this.initialized.booleanValue()) {
            throw new ImkeyException(Messages.IMKEY_SDK_BLE_NOT_INITIALIZE);
        }
        FTBtKeyErrCode ftBTKeyComm_Disconnect = this.sFtBluetoothJKey.ftBTKeyComm_Disconnect(new FTBluetoothDevice(bleDevice.getBluetoothDevice(), bleDevice.getDevType(), bleDevice.getRadioDevName(), bleDevice.getRadioUUID(), bleDevice.getDevRssi(), bleDevice.getRadioManufacturerData()));
        if (ftBTKeyComm_Disconnect != FTBtKeyErrCode.FT_BTKey_SUCCESS) {
            throw new ImkeyException(ErrorCode.toErrorCode(ftBTKeyComm_Disconnect).get_desc());
        }
        this.connectedDevice = null;
    }

    public void finalize() {
        this.initialized = false;
        this.sFtBluetoothJKey.ftBTKeyComm_Finalize();
    }

    public Context getContext() {
        return this.mContext;
    }

    public void initialize(Context context, Locale locale) {
        this.mContext = context;
        this.sFtBluetoothJKey = FTBluetoothJKey.getInstance(context);
        LogUtil.m2866d("ftversion：" + this.sFtBluetoothJKey.ftBTKeyComm_GetLibVersion());
        FTBtKeyErrCode ftBTKeyComm_Initialize = this.sFtBluetoothJKey.ftBTKeyComm_Initialize();
        this.sFtBluetoothJKey.ftBTKeyComm_SetLibLanguage(locale);
        if (ftBTKeyComm_Initialize != FTBtKeyErrCode.FT_BTKey_SUCCESS) {
            throw new ImkeyException(ErrorCode.toErrorCode(ftBTKeyComm_Initialize).get_desc());
        }
        this.initialized = true;
    }

    public String sendApdu(String str) {
        return sendApdu(str, 20);
    }

    public String sendApdu(String str, int i) {
        if (this.connectedDevice == null) {
            throw new ImkeyException(ErrorCode.toErrorCode(FTBtKeyErrCode.FT_BTkey_NOT_CONNECTED).get_desc());
        }
        if (str == null || str.length() % 2 != 0) {
            Log.e(TAG, "invalide apdu");
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        if (i <= 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        LogUtil.m2866d("ble  >>>>>> " + str);
        this.mResponse = "";
        this.mErrorCode = null;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        FTBluetoothDevice fTBluetoothDevice = new FTBluetoothDevice(this.connectedDevice.getBluetoothDevice(), this.connectedDevice.getDevType(), this.connectedDevice.getRadioDevName(), this.connectedDevice.getRadioUUID(), this.connectedDevice.getDevRssi(), this.connectedDevice.getRadioManufacturerData());
        byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str);
        ErrorCode errorCode = ErrorCode.toErrorCode(this.sFtBluetoothJKey.ftBTKeyComm_SendAndRecvAsync(fTBluetoothDevice, hexStringToByteArray, hexStringToByteArray.length, i, new IFTRecvDataEventCallback() { // from class: im.imkey.imkeylibrary.bluetooth.Ble.3
            public void onRecvData(FTBluetoothDevice fTBluetoothDevice2, byte[] bArr, int i2) {
                Ble.this.mResponse = ByteUtil.byteArrayToHexString(bArr);
                countDownLatch.countDown();
            }

            public void onRecvFail(FTBluetoothDevice fTBluetoothDevice2, FTBtKeyErrCode fTBtKeyErrCode) {
                Ble.this.mErrorCode = ErrorCode.toErrorCode(fTBtKeyErrCode);
                countDownLatch.countDown();
            }
        }));
        if (errorCode != ErrorCode.SUCCESS) {
            throw new ImkeyException(errorCode.get_desc());
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ErrorCode errorCode2 = this.mErrorCode;
        if (errorCode2 != null && errorCode2 != ErrorCode.SUCCESS) {
            throw new ImkeyException(this.mErrorCode.get_desc());
        }
        LogUtil.m2866d("ble  <<<<<< " + this.mResponse);
        Apdu.checkImKeyStatus(this.mResponse);
        return this.mResponse;
    }

    public void startScan(int i, final ScanCallback scanCallback) {
        if (!this.initialized.booleanValue()) {
            throw new ImkeyException(Messages.IMKEY_SDK_BLE_NOT_INITIALIZE);
        }
        if (i <= 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        if (scanCallback == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        this.mMapDevices = new HashMap<>();
        FTBtKeyErrCode ftBTKeyComm_StartScan = this.sFtBluetoothJKey.ftBTKeyComm_StartScan(4, i, new IFTScanCallback() { // from class: im.imkey.imkeylibrary.bluetooth.Ble.1
            public void onScanDevice(FTBluetoothDevice fTBluetoothDevice) {
                Ble.this.mMapDevices.put(fTBluetoothDevice.getBluetoothDevice().getAddress(), fTBluetoothDevice);
                scanCallback.onScanDevice(Ble.this.toDevice(fTBluetoothDevice));
            }

            public void onScanStarted() {
                scanCallback.onScanStarted();
            }

            public void onScanStopped() {
                scanCallback.onScanStopped();
            }
        });
        if (ftBTKeyComm_StartScan != FTBtKeyErrCode.FT_BTKey_SUCCESS) {
            scanCallback.onScanFail(ErrorCode.toErrorCode(ftBTKeyComm_StartScan));
        }
    }

    public void stopScan() {
        this.sFtBluetoothJKey.ftBTKeyComm_StopScan();
    }
}
