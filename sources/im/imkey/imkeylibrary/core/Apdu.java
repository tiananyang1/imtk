package im.imkey.imkeylibrary.core;

import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/Apdu.class */
public class Apdu {
    public static final int Hash_ALL = 1;
    private static final int LC_MAX = 245;

    public static String bindCheck(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 6];
        bArr2[0] = Byte.MIN_VALUE;
        bArr2[1] = 113;
        bArr2[2] = 0;
        bArr2[3] = 0;
        bArr2[4] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        bArr2[bArr2.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr2);
    }

    public static String btcCoinReg(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 6];
        bArr2[0] = Byte.MIN_VALUE;
        bArr2[1] = 54;
        bArr2[2] = 0;
        bArr2[3] = 0;
        bArr2[4] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        bArr2[bArr2.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr2);
    }

    public static List<String> btcPrepare(byte b, byte b2, byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int length = ((bArr.length - 1) / LC_MAX) + 1;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return arrayList;
            }
            if (i2 == length - 1) {
                int length2 = bArr.length % LC_MAX == 0 ? LC_MAX : bArr.length % LC_MAX;
                byte[] bArr2 = new byte[length2 + 6];
                bArr2[0] = Byte.MIN_VALUE;
                bArr2[1] = b;
                bArr2[2] = b2;
                bArr2[3] = Byte.MIN_VALUE;
                bArr2[4] = (byte) length2;
                System.arraycopy(bArr, i2 * LC_MAX, bArr2, 5, length2);
                arrayList.add(ByteUtil.byteArrayToHexString(bArr2));
            } else {
                byte[] bArr3 = new byte[251];
                bArr3[0] = Byte.MIN_VALUE;
                bArr3[1] = b;
                bArr3[2] = b2;
                bArr3[3] = 0;
                bArr3[4] = -11;
                System.arraycopy(bArr, i2 * LC_MAX, bArr3, 5, LC_MAX);
                arrayList.add(ByteUtil.byteArrayToHexString(bArr3));
            }
            i = i2 + 1;
        }
    }

    public static List<String> btcPrepare(String str) {
        return prepare(65, str);
    }

    public static String btcPrepareInput(byte b, String str) {
        byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str);
        byte[] bArr = new byte[hexStringToByteArray.length + 6];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 65;
        bArr[2] = b;
        bArr[3] = 0;
        bArr[4] = (byte) hexStringToByteArray.length;
        System.arraycopy(hexStringToByteArray, 0, bArr, 5, hexStringToByteArray.length);
        return ByteUtil.byteArrayToHexString(bArr);
    }

    public static List<String> btcSegwitPrepare(byte b, byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int length = ((bArr.length - 1) / LC_MAX) + 1;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return arrayList;
            }
            if (i2 == length - 1) {
                int length2 = bArr.length % LC_MAX == 0 ? LC_MAX : bArr.length % LC_MAX;
                byte[] bArr2 = new byte[length2 + 6];
                bArr2[0] = Byte.MIN_VALUE;
                bArr2[1] = 49;
                bArr2[2] = b;
                bArr2[3] = Byte.MIN_VALUE;
                bArr2[4] = (byte) length2;
                System.arraycopy(bArr, i2 * LC_MAX, bArr2, 5, length2);
                arrayList.add(ByteUtil.byteArrayToHexString(bArr2));
            } else {
                byte[] bArr3 = new byte[251];
                bArr3[0] = Byte.MIN_VALUE;
                bArr3[1] = 49;
                bArr3[2] = b;
                bArr3[3] = 0;
                bArr3[4] = -11;
                System.arraycopy(bArr, i2 * LC_MAX, bArr3, 5, LC_MAX);
                arrayList.add(ByteUtil.byteArrayToHexString(bArr3));
            }
            i = i2 + 1;
        }
    }

    public static List<String> btcSegwitPrepare(String str) {
        return prepare(49, str);
    }

    public static String btcSegwitSign(Boolean bool, int i, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 6];
        byte b = Byte.MIN_VALUE;
        bArr2[0] = Byte.MIN_VALUE;
        bArr2[1] = 50;
        if (!bool.booleanValue()) {
            b = 0;
        }
        bArr2[2] = b;
        bArr2[3] = (byte) i;
        bArr2[4] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        bArr2[bArr2.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr2);
    }

    public static String btcSign(int i, int i2, String str) {
        return sign(66, i, i2, str);
    }

    public static String btcXpub(String str, boolean z) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 6];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 67;
        if (z) {
            bArr[2] = 1;
        } else {
            bArr[2] = 0;
        }
        bArr[3] = 0;
        bArr[4] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        bArr[bArr.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr);
    }

    public static void checkImKeyStatus(String str) {
        if (str.equals(Constants.APDU_RSP_WALLET_NOT_CREATED)) {
            throw new ImkeyException(Messages.IMKEY_WALLET_NOT_CREATED);
        }
        if (str.equals(Constants.APDU_RSP_IN_MENU_PAGE)) {
            throw new ImkeyException(Messages.IMKEY_IN_MENU_PAGE);
        }
        if (str.equals(Constants.APDU_RSP_PIN_NOT_VERIFIED)) {
            throw new ImkeyException(Messages.IMKEY_PIN_NOT_VERIFIED);
        }
    }

    public static void checkResponse(String str) {
        if (str.endsWith(Constants.APDU_RSP_SUCCESS)) {
            return;
        }
        if (str.equals(Constants.APDU_RSP_USER_NOT_CONFIRMED)) {
            throw new ImkeyException(Messages.IMKEY_USER_NOT_CONFIRMED);
        }
        if (str.equals(Constants.APDU_CONDITIONS_NOT_SATISFIED)) {
            throw new ImkeyException(Messages.IMKEY_CONDITIONS_NOT_SATISFIED);
        }
        if (str.equals(Constants.APDU_RSP_INCORRECT_P1P2)) {
            throw new ImkeyException(Messages.IMKEY_COMMAND_FORMAT_ERROR);
        }
        if (str.equals(Constants.APDU_RSP_CLA_NOT_SUPPORTED)) {
            throw new ImkeyException(Messages.IMKEY_COMMAND_FORMAT_ERROR);
        }
        if (str.equals(Constants.APDU_RSP_APPLET_WRONG_DATA)) {
            throw new ImkeyException(Messages.IMKEY_COMMAND_DATA_ERROR);
        }
        if (str.equals(Constants.APDU_RSP_APPLET_NOT_EXIST)) {
            throw new ImkeyException(Messages.IMKEY_APPLET_NOT_EXIST);
        }
        if (str.equals(Constants.APDU_RSP_WRONG_LENGTH)) {
            throw new ImkeyException(Messages.IMKEY_APDU_WRONG_LENGTH);
        }
        if (str.equals(Constants.APDU_RSP_SIGNATURE_VERIFY_FAILED)) {
            throw new ImkeyException(Messages.IMKEY_SIGNATURE_VERIFY_FAIL);
        }
        if (str.equals(Constants.APDU_BLUETOOTH_CHANNEL_ERROR)) {
            throw new ImkeyException(Messages.IMKEY_BLUETOOTH_CHANNEL_ERROR);
        }
        if (str.equals(Constants.APDU_RSP_FUNCTION_NOT_SUPPORTED)) {
            throw new ImkeyException(Messages.IMKEY_APPLET_FUNCTION_NOT_SUPPORTED);
        }
        if (str.equals(Constants.APDU_RSP_EXCEEDED_MAX_UTXO_NUMBER)) {
            throw new ImkeyException(Messages.IMKEY_EXCEEDED_MAX_UTXO_NUMBER);
        }
        throw new ImkeyException("imkey_command_execute_fail_" + str);
    }

    public static String cosmosCoinReg(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 6];
        bArr2[0] = Byte.MIN_VALUE;
        bArr2[1] = 118;
        bArr2[2] = 0;
        bArr2[3] = 0;
        bArr2[4] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        bArr2[bArr2.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr2);
    }

    public static List<String> cosmosPrepare(String str) {
        return prepare(113, str);
    }

    public static String cosmosPub(String str, boolean z) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 6];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 115;
        if (z) {
            bArr[2] = 1;
        } else {
            bArr[2] = 0;
        }
        bArr[3] = 0;
        bArr[4] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        bArr[bArr.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr);
    }

    public static String cosmosSign(String str) {
        return sign(114, 0, 0, str);
    }

    public static String eosCoinReg(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 6];
        bArr2[0] = Byte.MIN_VALUE;
        bArr2[1] = 102;
        bArr2[2] = 0;
        bArr2[3] = 0;
        bArr2[4] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        bArr2[bArr2.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr2);
    }

    public static List<String> eosMsgPrepare(String str) {
        return prepare(100, str);
    }

    public static String eosMsgSign(int i) {
        return ByteUtil.byteArrayToHexString(new byte[]{Byte.MIN_VALUE, 101, 0, 0, 2, (byte) ((65280 & i) >> 8), (byte) (i & 255), 0});
    }

    public static List<String> eosPrepare(String str) {
        return prepare(97, str);
    }

    public static String eosPub(String str, boolean z) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 6];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 99;
        if (z) {
            bArr[2] = 1;
        } else {
            bArr[2] = 0;
        }
        bArr[3] = 0;
        bArr[4] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        bArr[bArr.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr);
    }

    public static String eosTxSign(int i) {
        return ByteUtil.byteArrayToHexString(new byte[]{Byte.MIN_VALUE, 98, 0, 0, 2, (byte) ((65280 & i) >> 8), (byte) (i & 255), 0});
    }

    public static String ethCoinReg(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 6];
        bArr2[0] = Byte.MIN_VALUE;
        bArr2[1] = 86;
        bArr2[2] = 0;
        bArr2[3] = 0;
        bArr2[4] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        bArr2[bArr2.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr2);
    }

    public static List<String> ethMsgPrepare(String str) {
        return prepare(84, str);
    }

    public static String ethMsgSign(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 6];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 85;
        bArr[2] = 0;
        bArr[3] = 0;
        bArr[4] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        bArr[bArr.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr);
    }

    public static List<String> ethPrepare(String str) {
        return prepare(81, str);
    }

    public static String ethSign(String str) {
        return sign(82, 0, 0, str);
    }

    public static String ethXpub(String str, boolean z) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 6];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 83;
        if (z) {
            bArr[2] = 1;
        } else {
            bArr[2] = 0;
        }
        bArr[3] = 0;
        bArr[4] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        bArr[bArr.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr);
    }

    public static String generateAuthCode() {
        return ByteUtil.byteArrayToHexString(new byte[]{Byte.MIN_VALUE, 114, 0, 0, 0});
    }

    public static String getResponseData(String str) {
        return str.substring(0, str.length() - 4);
    }

    private static byte[] header(byte b, byte b2, byte b3, byte b4, byte b5) {
        return new byte[]{b, b2, b3, b4, b5};
    }

    public static String identityVerify(byte b, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 6];
        bArr2[0] = Byte.MIN_VALUE;
        bArr2[1] = 115;
        bArr2[2] = b;
        bArr2[3] = 0;
        bArr2[4] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
        bArr2[bArr2.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr2);
    }

    public static String omniPrepareData(byte b, String str) {
        byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str);
        byte[] bArr = new byte[hexStringToByteArray.length + 6];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 68;
        bArr[2] = b;
        bArr[3] = 0;
        bArr[4] = (byte) hexStringToByteArray.length;
        System.arraycopy(hexStringToByteArray, 0, bArr, 5, hexStringToByteArray.length);
        return ByteUtil.byteArrayToHexString(bArr);
    }

    public static List<String> omniSegwitPrepare(byte b, byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int length = ((bArr.length - 1) / LC_MAX) + 1;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return arrayList;
            }
            if (i2 == length - 1) {
                int length2 = bArr.length % LC_MAX == 0 ? LC_MAX : bArr.length % LC_MAX;
                byte[] bArr2 = new byte[length2 + 6];
                bArr2[0] = Byte.MIN_VALUE;
                bArr2[1] = 52;
                bArr2[2] = b;
                bArr2[3] = Byte.MIN_VALUE;
                bArr2[4] = (byte) length2;
                System.arraycopy(bArr, i2 * LC_MAX, bArr2, 5, length2);
                arrayList.add(ByteUtil.byteArrayToHexString(bArr2));
            } else {
                byte[] bArr3 = new byte[251];
                bArr3[0] = Byte.MIN_VALUE;
                bArr3[1] = 52;
                bArr3[2] = b;
                bArr3[3] = 0;
                bArr3[4] = -11;
                System.arraycopy(bArr, i2 * LC_MAX, bArr3, 5, LC_MAX);
                arrayList.add(ByteUtil.byteArrayToHexString(bArr3));
            }
            i = i2 + 1;
        }
    }

    private static List<String> prepare(int i, String str) {
        ArrayList arrayList = new ArrayList();
        byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str);
        int length = (hexStringToByteArray.length / LC_MAX) + (hexStringToByteArray.length % LC_MAX == 0 ? 0 : 1);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                return arrayList;
            }
            int i4 = length - 1;
            byte[] bArr = i3 == i4 ? new byte[(hexStringToByteArray.length - (i4 * LC_MAX)) + 6] : new byte[251];
            bArr[0] = Byte.MIN_VALUE;
            bArr[1] = (byte) i;
            if (i3 == 0) {
                bArr[2] = 0;
            } else {
                bArr[2] = Byte.MIN_VALUE;
            }
            if (i3 == i4) {
                bArr[3] = Byte.MIN_VALUE;
                bArr[4] = (byte) (hexStringToByteArray.length - (i4 * LC_MAX));
                int i5 = i3 * LC_MAX;
                System.arraycopy(hexStringToByteArray, i5, bArr, 5, hexStringToByteArray.length - i5);
            } else {
                bArr[3] = 0;
                bArr[4] = -11;
                System.arraycopy(hexStringToByteArray, i3 * LC_MAX, bArr, 5, LC_MAX);
            }
            bArr[bArr.length - 1] = 0;
            arrayList.add(ByteUtil.byteArrayToHexString(bArr));
            i2 = i3 + 1;
        }
    }

    public static String select(String str) {
        byte[] hexStringToByteArray = ByteUtil.hexStringToByteArray(str);
        byte[] header = header((byte) 0, (byte) -92, (byte) 4, (byte) 0, (byte) hexStringToByteArray.length);
        byte[] bArr = new byte[header.length + hexStringToByteArray.length];
        System.arraycopy(header, 0, bArr, 0, header.length);
        System.arraycopy(hexStringToByteArray, 0, bArr, header.length, hexStringToByteArray.length);
        return ByteUtil.byteArrayToHexString(bArr);
    }

    public static String setBleName(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 6];
        bArr[0] = -1;
        bArr[1] = -38;
        bArr[2] = 70;
        bArr[3] = 84;
        bArr[4] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        bArr[bArr.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr);
    }

    private static String sign(int i, int i2, int i3, String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 6];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) i;
        bArr[2] = (byte) i2;
        bArr[3] = (byte) i3;
        bArr[4] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 5, bytes.length);
        bArr[bArr.length - 1] = 0;
        return ByteUtil.byteArrayToHexString(bArr);
    }
}
