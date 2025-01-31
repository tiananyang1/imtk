package im.imkey.imkeylibrary.common;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/common/Constants.class */
public class Constants {
    public static final String APDU_BLUETOOTH_CHANNEL_ERROR = "6F01";
    public static final String APDU_CONDITIONS_NOT_SATISFIED = "6985";
    public static final String APDU_GET_BATTERY_POWER = "00D6FEED01";
    public static final String APDU_GET_BLE_NAME = "FFDB465400";
    public static final String APDU_GET_BLE_VERSION = "80CB800005DFFF02810000";
    public static final String APDU_GET_CERT = "80CABF2106A6048302151800";
    public static final String APDU_GET_COS_VERSION = "80CB800005DFFF02800300";
    public static final String APDU_GET_LIFE_TIME = "FFDCFEED00";
    public static final String APDU_GET_RAM_SIZE = "80CB800005DFFF02814600";
    public static final String APDU_GET_SEID = "80CB800005DFFF028101";
    public static final String APDU_GET_SN = "80CA004400";
    public static final String APDU_RESET = "80CB800005DFFE02814700";
    public static final String APDU_RSP_APPLET_NOT_EXIST = "6A82";
    public static final String APDU_RSP_APPLET_WRONG_DATA = "6A80";
    public static final String APDU_RSP_CLA_NOT_SUPPORTED = "6E00";
    public static final String APDU_RSP_EXCEEDED_MAX_UTXO_NUMBER = "6941";
    public static final String APDU_RSP_FUNCTION_NOT_SUPPORTED = "6D00";
    public static final String APDU_RSP_INCORRECT_P1P2 = "6A86";
    public static final String APDU_RSP_IN_MENU_PAGE = "F080";
    public static final String APDU_RSP_PIN_NOT_VERIFIED = "F081";
    public static final String APDU_RSP_SIGNATURE_VERIFY_FAILED = "6942";
    public static final String APDU_RSP_SUCCESS = "9000";
    public static final String APDU_RSP_USER_NOT_CONFIRMED = "6940";
    public static final String APDU_RSP_WALLET_NOT_CREATED = "F000";
    public static final String APDU_RSP_WRONG_LENGTH = "6700";
    public static final String APDU_SELECT_ISD = "00A4040000";
    public static final String AUTHCODE_ENC_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxmJ6bwSFsz3cHKfgYsZO\niEETO5JGpB9A0HZ7rkTqsu9FPQCP+we42f380hiCSH7MTakzyX5JQkKto84CxaBR\niapJQQ53GmboEA5Dyxr2zGELWe5OuyNv84xirXsdEd+9TgVNGeM0k5GjH16JynIS\nkrc4ApV0XYlozFwtIjrGdQuwrKJ3c2h+nNdgZeR/QvSuAFRZvOV0a9dgZGpb0Rm6\nNGmpNfSOuJjLq3LLOUw/7J5BY16ulUEHoXrHuMYyHY8XVa05FanSOY2yaKP2Qs7p\ny+n4Ls1a1k6+3d5mYB3CuJHi/t33La9if6j6FvfGQNtmG+Fdy0J02VdtmNvrIMJT\nCQIDAQAB";
    public static final String BATTERY_CHARGING_SIGN = "FF";
    public static final String BIND_STATUS_BOUND_OTHER = "AA";
    public static final String BIND_STATUS_BOUND_THIS = "55";
    public static final String BIND_STATUS_UNBOUND = "00";
    public static final int EACH_ROUND_NUMBER = 5;
    public static final String HOST_HTTPS = "https://imkey.online:1000/imkey/";
    public static final int HTTP_TIMEOUT = 10000;
    public static final String IMKEY_DEV_STATUS_INACTIVATED = "inactivated";
    public static final String IMKEY_DEV_STATUS_LATEST = "latest";
    public static final String IMKEY_DEV_STATUS_OUTDATED = "outdated";
    public static final String LIFE_TIME_DEVICE_ACTIVATED = "life_time_device_activated";
    public static final String LIFE_TIME_DEVICE_INITED = "life_time_device_inited";
    public static final String LIFE_TIME_UNKNOWN = "life_time_unknown";
    public static final String LIFE_TIME_UNSET_PIN = "life_time_unset_pin";
    public static final String LIFE_TIME_WALLET_CREATTING = "life_time_wallet_creatting";
    public static final String LIFE_TIME_WALLET_READY = "life_time_wallet_ready";
    public static final String LIFE_TIME_WALLET_RECOVERING = "life_time_wallet_recovering";
    public static final String LIFE_TIME_WALLET_UNREADY = "life_time_wallet_unready";
    public static final String MAINNET = "MAINNET";
    public static final int MAX_OPRETURN_SIZE = 80;
    public static final int MAX_UTXO_NUMBER = 252;
    private static final String PRODUCT_HOST = "https://imkey.online:1000/imkey/";
    private static final String PRODUCT_SSL_CERT = "30820122300D06092A864886F70D01010105000382010F003082010A028201010088BFDFBE85067CD720583FA3F5659BBA629A2335A924F618001DF1B9B89DB769B1C75273493D51CDAD6588441E015226CAAB0D1319BFEAB9E257E6FE6C8227640DA2A5FCCC58963269C908EEEEEB0B7D14E312D15A104E81BC45D1112DCB978C3CA0D483FFB405D6CAC10909733B6B0A8D369B24611E4C284D05077901F36365B407DC3CB29C7B42664A8958063D93E87D405BEE692EDA4068A841D4EE12D7FC57494B24EE72537DAC29DCDCCD721D4AA8C1306D6613B8E04861844DB49DE10A140A7EB8C4D0351CAF5D76D44AADCC5C37E7504A24E31E92F6F3CBC133BF4EFFA889A14D6F1A684A9B471BC5B040F3C04D163158970EED5AE9A011F2A3DDB0810203010001";
    public static final int SEND_SIGN_PRE_APDU_TIMEOUT = 120;
    public static final int SENT_APDU_TIMEOUT = 20;
    public static final String SSL_CERT_PUBKEY = "30820122300D06092A864886F70D01010105000382010F003082010A028201010088BFDFBE85067CD720583FA3F5659BBA629A2335A924F618001DF1B9B89DB769B1C75273493D51CDAD6588441E015226CAAB0D1319BFEAB9E257E6FE6C8227640DA2A5FCCC58963269C908EEEEEB0B7D14E312D15A104E81BC45D1112DCB978C3CA0D483FFB405D6CAC10909733B6B0A8D369B24611E4C284D05077901F36365B407DC3CB29C7B42664A8958063D93E87D405BEE692EDA4068A841D4EE12D7FC57494B24EE72537DAC29DCDCCD721D4AA8C1306D6613B8E04861844DB49DE10A140A7EB8C4D0351CAF5D76D44AADCC5C37E7504A24E31E92F6F3CBC133BF4EFFA889A14D6F1A684A9B471BC5B040F3C04D163158970EED5AE9A011F2A3DDB0810203010001";
    public static final String TESTNET = "TESTNET";
    private static final String TEST_HOST = "https://imkey.online:1000/imkey/";
    private static final String TEST_SSL_CERT = "30820122300D06092A864886F70D01010105000382010F003082010A028201010088BFDFBE85067CD720583FA3F5659BBA629A2335A924F618001DF1B9B89DB769B1C75273493D51CDAD6588441E015226CAAB0D1319BFEAB9E257E6FE6C8227640DA2A5FCCC58963269C908EEEEEB0B7D14E312D15A104E81BC45D1112DCB978C3CA0D483FFB405D6CAC10909733B6B0A8D369B24611E4C284D05077901F36365B407DC3CB29C7B42664A8958063D93E87D405BEE692EDA4068A841D4EE12D7FC57494B24EE72537DAC29DCDCCD721D4AA8C1306D6613B8E04861844DB49DE10A140A7EB8C4D0351CAF5D76D44AADCC5C37E7504A24E31E92F6F3CBC133BF4EFFA889A14D6F1A684A9B471BC5B040F3C04D163158970EED5AE9A011F2A3DDB0810203010001";
    public static final String TSM_RETURNCODE_APP_DELETE_FAIL = "BAPP0011";
    public static final String TSM_RETURNCODE_APP_DOWNLOAD_FAIL = "BAPP0006";
    public static final String TSM_RETURNCODE_APP_UPDATE_FAIL = "BAPP0008";
    public static final String TSM_RETURNCODE_DEVICE_ACTIVE_FAIL = "BSE0015";
    public static final String TSM_RETURNCODE_DEVICE_CHECK_FAIL = "BSE0009";
    public static final String TSM_RETURNCODE_DEVICE_ILLEGAL = "BSE0017";
    public static final String TSM_RETURNCODE_DEVICE_STOP_USING = "BSE0019";
    public static final String TSM_RETURNCODE_DEV_INACTIVATED = "BSE0007";
    public static final String TSM_RETURNCODE_OCE_CERT_CHECK_FAIL = "BSE0010";
    public static final String TSM_RETURNCODE_RECEIPT_CHECK_FAIL = "BSE0012";
    public static final String TSM_RETURNCODE_SEID_ILLEGAL = "BSE0008";
    public static final String TSM_RETURNCODE_SE_QUERY_FAIL = "BSE0018";
    public static final String TSM_RETURNCODE_SUCCESS = "000000";
    public static Map<String, String> identityVerifyStatusMap;
    public static final String version = "1.2.10";
    public static final BigInteger HALF_CURVE_ORDER = new BigInteger("7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF5D576E7357A4501DDFE92F46681B20A0", 16);
    public static final BigInteger CURVE_N = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);
    public static Map<String, String> bindcheckStatusMap = new HashMap();

    static {
        bindcheckStatusMap.put(BIND_STATUS_UNBOUND, "unbound");
        bindcheckStatusMap.put(BIND_STATUS_BOUND_THIS, "bound_this");
        bindcheckStatusMap.put(BIND_STATUS_BOUND_OTHER, "bound_other");
        identityVerifyStatusMap = new HashMap();
        identityVerifyStatusMap.put("5A", "success");
        identityVerifyStatusMap.put("A5", "authcode_error");
    }
}
