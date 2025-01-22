package im.imkey.imkeylibrary.bluetooth;

import com.ftsafe.bluetooth.key.FTBtKeyErrCode;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/bluetooth/ErrorCode.class */
public enum ErrorCode {
    SUCCESS(FTBtKeyErrCode.FT_BTKey_SUCCESS, "imkey_ble_success"),
    OTHER_ERROR(FTBtKeyErrCode.FT_BTkey_OTHER_ERROR, "imkey_ble_other_error"),
    BT_NOT_SUPPORT(FTBtKeyErrCode.FT_BTkey_BT_NOT_SUPPORT, "imkey_ble_not_support"),
    BLE_NOT_SUPPORT(FTBtKeyErrCode.FT_BTkey_BLE_NOT_SUPPORT, "imkey_ble_not_support"),
    LOCATION_UNAUTHORIZE(FTBtKeyErrCode.FT_BTkey_LOCATION_UNAUTHORIZE, "imkey_ble_location_unauthorize"),
    BT_NOT_ENABLED(FTBtKeyErrCode.FT_BTkey_BT_NOT_ENABLED, "imkey_ble_not_enabled"),
    ENABLE_BT_FAIL(FTBtKeyErrCode.FT_BTKey_ENABLE_BT_FAIL, "imkey_ble_enable_bt_fail"),
    FIND_DEVICE_FAILED(FTBtKeyErrCode.FT_BTkey_FIND_DEVICE_FAILED, "imkey_ble_find_device_failed"),
    BOND_FAILED(FTBtKeyErrCode.FT_BTkey_BOND_FAILED, "imkey_ble_bond_failed"),
    CONNECT_FAILED(FTBtKeyErrCode.FT_BTkey_CONNECT_FAILED, "imkey_ble_connect_failed"),
    CONNECT_TIMEOUT(FTBtKeyErrCode.FT_BTkey_CONNECT_TIMEOUT, "imkey_ble_connect_timeout"),
    ALREADY_CONNECTED(FTBtKeyErrCode.FT_BTKey_ALREADY_CONNECTED, "imkey_ble_already_connected"),
    CONNECTION_BROKEN(FTBtKeyErrCode.FT_BTkey_CONNECTION_BROKEN, "imkey_ble_connection_broken"),
    NOT_CONNECTED(FTBtKeyErrCode.FT_BTkey_NOT_CONNECTED, "imkey_ble_not_connected"),
    PARA_ERR(FTBtKeyErrCode.FT_BTkey_PARA_ERR, "imkey_ble_illegal_argument"),
    SEND_DATA_FAILED(FTBtKeyErrCode.FT_BTkey_SEND_DATA_FAILED, "imkey_ble_send_data_failed"),
    RECV_BUF_SMALL(FTBtKeyErrCode.FT_BTKey_RECV_BUF_SMALL, "imkey_ble_recv_buf_small"),
    RECV_DATA_ERR(FTBtKeyErrCode.FT_BTkey_RECV_DATA_ERR, "imkey_ble_recv_data_err"),
    RECV_DATA_TIMEOUT(FTBtKeyErrCode.FT_BTkey_RECV_DATA_TIMEOUT, "imkey_ble_recv_data_timeout"),
    CONCURRENT_EXCEPTION(FTBtKeyErrCode.FT_BTkey_CONCURRENT_EXCEPTION, "imkey_ble_concurrent_exception"),
    MATCH_UUID_FAIL(FTBtKeyErrCode.FT_BTkey_MATCH_UUID_FAIL, "imkey_ble_match_uuid_fail");

    String _desc;
    FTBtKeyErrCode _ftBtKeyErrCode;

    ErrorCode(FTBtKeyErrCode fTBtKeyErrCode, String str) {
        this._ftBtKeyErrCode = fTBtKeyErrCode;
        this._desc = str;
    }

    public static ErrorCode toErrorCode(FTBtKeyErrCode fTBtKeyErrCode) {
        ErrorCode[] values = values();
        int length = values.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return OTHER_ERROR;
            }
            ErrorCode errorCode = values[i2];
            if (fTBtKeyErrCode == errorCode._ftBtKeyErrCode) {
                return errorCode;
            }
            i = i2 + 1;
        }
    }

    public String get_desc() {
        return this._desc;
    }
}
