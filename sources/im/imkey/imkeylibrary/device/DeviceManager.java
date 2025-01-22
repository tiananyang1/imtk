package im.imkey.imkeylibrary.device;

import android.util.Base64;
import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.core.Apdu;
import im.imkey.imkeylibrary.core.foundation.crypto.AES;
import im.imkey.imkeylibrary.core.foundation.crypto.Hash;
import im.imkey.imkeylibrary.core.foundation.crypto.RsaCrypto;
import im.imkey.imkeylibrary.device.key.KeyManager;
import im.imkey.imkeylibrary.device.model.AppDeleteRequest;
import im.imkey.imkeylibrary.device.model.AppDownloadRequest;
import im.imkey.imkeylibrary.device.model.AppUpdateRequest;
import im.imkey.imkeylibrary.device.model.AuthCodeStorageRequest;
import im.imkey.imkeylibrary.device.model.DeviceCertCheckRequest;
import im.imkey.imkeylibrary.device.model.DeviceCertCheckResponse;
import im.imkey.imkeylibrary.device.model.ImKeyDevice;
import im.imkey.imkeylibrary.device.model.SdkInfo;
import im.imkey.imkeylibrary.device.model.SeActivateRequest;
import im.imkey.imkeylibrary.device.model.SeInfoQueryRequest;
import im.imkey.imkeylibrary.device.model.SeInfoQueryResponse;
import im.imkey.imkeylibrary.device.model.SeSecureCheckRequest;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.utils.ByteUtil;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.ArrayList;
import java.util.regex.Pattern;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/DeviceManager.class */
public class DeviceManager {
    private void authCodeStorage(String str) {
        String seId = getSeId();
        AuthCodeStorageRequest authCodeStorageRequest = new AuthCodeStorageRequest();
        authCodeStorageRequest.setStepKey("01");
        authCodeStorageRequest.setSeid(seId);
        authCodeStorageRequest.setAuthCode(str);
        authCodeStorageRequest.setCardRetDataList(new ArrayList());
        String str2 = new AuthCodeStorage().authCodeStorage(authCodeStorageRequest).get_ReturnCode();
        if (Constants.TSM_RETURNCODE_SUCCESS.equals(str2)) {
            return;
        }
        throw new ImkeyException("imkey_tsm_server_error_" + str2);
    }

    private void deviceCertCheck(String str, String str2, String str3) {
        DeviceCertCheckRequest deviceCertCheckRequest = new DeviceCertCheckRequest();
        deviceCertCheckRequest.setStepKey("01");
        deviceCertCheckRequest.setSeid(str);
        deviceCertCheckRequest.setSn(str2);
        deviceCertCheckRequest.setDeviceCert(str3);
        deviceCertCheckRequest.setCardRetDataList(new ArrayList());
        DeviceCertCheckResponse deviceCertCheck = new DeviceCertCheck().deviceCertCheck(deviceCertCheckRequest);
        String str4 = deviceCertCheck.get_ReturnCode();
        if (Constants.TSM_RETURNCODE_SUCCESS.equals(str4)) {
            if (!Boolean.valueOf(deviceCertCheck.getVerifyResult()).booleanValue()) {
                throw new ImkeyException(Messages.IMKEY_SE_CERT_INVALID);
            }
            return;
        }
        if (Constants.TSM_RETURNCODE_DEVICE_CHECK_FAIL.equals(str4)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_AUTHENTICITY_CHECK_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEV_INACTIVATED.equals(str4)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_NOT_ACTIVATED);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_ILLEGAL.equals(str4)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_ILLEGAL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_STOP_USING.equals(str4)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_STOP_USING);
        }
        throw new ImkeyException("imkey_tsm_server_error_" + str4);
    }

    private byte[] genIV(String str) {
        byte[] sha256 = Hash.sha256(str.getBytes());
        byte[] sha2562 = Hash.sha256("bindingCode".getBytes());
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= sha256.length) {
                byte[] bArr = new byte[16];
                System.arraycopy(sha256, 0, bArr, 0, 16);
                return bArr;
            }
            sha256[i2] = (byte) (sha256[i2] ^ sha2562[i2]);
            i = i2 + 1;
        }
    }

    private String getCert() {
        Ble.getInstance().sendApdu(Constants.APDU_SELECT_ISD);
        return Ble.getInstance().sendApdu(Constants.APDU_GET_CERT);
    }

    public void activeDevice() {
        String seId = getSeId();
        String sn = getSn();
        SeActivateRequest seActivateRequest = new SeActivateRequest();
        seActivateRequest.setStepKey("01");
        seActivateRequest.setSeid(seId);
        seActivateRequest.setSn(sn);
        seActivateRequest.setStatusWord("");
        seActivateRequest.setDeviceCert(getCert());
        seActivateRequest.setCardRetDataList(new ArrayList());
        String str = new SeActive().activeSe(seActivateRequest).get_ReturnCode();
        if (Constants.TSM_RETURNCODE_SUCCESS.equals(str)) {
            return;
        }
        if (Constants.TSM_RETURNCODE_DEVICE_ACTIVE_FAIL.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_ACTIVE_FAIL);
        }
        if (Constants.TSM_RETURNCODE_SEID_ILLEGAL.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_ILLEGAL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_STOP_USING.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_STOP_USING);
        }
        throw new ImkeyException("imkey_tsm_server_error_" + str);
    }

    public String bindAcquire(String str) {
        String upperCase = str.toUpperCase();
        if (!Pattern.matches("^[A-HJ-NP-Z2-9]{8}$", upperCase)) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        try {
            authCodeStorage(ByteUtil.byteArrayToHexString(RsaCrypto.encryptByPublicKeyWithPadding(upperCase.getBytes(), Base64.decode(Constants.AUTHCODE_ENC_PUB_KEY, 0))));
            selectApplet();
            byte[] bArr = new byte[138];
            System.arraycopy(upperCase.getBytes(), 0, bArr, 0, 8);
            System.arraycopy(KeyManager.getInstance().getPubKey(), 0, bArr, 8, 65);
            System.arraycopy(KeyManager.getInstance().getSePubKey(), 0, bArr, 73, 65);
            byte[] encryptByCBC = AES.encryptByCBC(Hash.sha256(bArr), KeyManager.getInstance().getSessionKey(), genIV(upperCase));
            byte[] bArr2 = new byte[KeyManager.getInstance().getPubKey().length + encryptByCBC.length];
            System.arraycopy(KeyManager.getInstance().getPubKey(), 0, bArr2, 0, KeyManager.getInstance().getPubKey().length);
            System.arraycopy(encryptByCBC, 0, bArr2, KeyManager.getInstance().getPubKey().length, encryptByCBC.length);
            String sendApdu = Ble.getInstance().sendApdu(Apdu.identityVerify(Byte.MIN_VALUE, bArr2));
            Apdu.checkResponse(sendApdu);
            return Constants.identityVerifyStatusMap.get(sendApdu.substring(0, 2));
        } catch (Exception e) {
            throw new ImkeyException(Messages.IMKEY_ENCRYPT_AUTHCODE_FAIL);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x003c, code lost:            if (r8.booleanValue() != false) goto L9;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String bindCheck() {
        /*
            Method dump skipped, instructions count: 355
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: im.imkey.imkeylibrary.device.DeviceManager.bindCheck():java.lang.String");
    }

    public void checkDevice() {
        String seId = getSeId();
        String sn = getSn();
        SeSecureCheckRequest seSecureCheckRequest = new SeSecureCheckRequest();
        seSecureCheckRequest.setStepKey("01");
        seSecureCheckRequest.setSeid(seId);
        seSecureCheckRequest.setSn(sn);
        seSecureCheckRequest.setDeviceCert(getCert());
        seSecureCheckRequest.setCardRetDataList(new ArrayList());
        String str = new SeCheck().checkSe(seSecureCheckRequest).get_ReturnCode();
        if (Constants.TSM_RETURNCODE_SUCCESS.equals(str)) {
            return;
        }
        if (Constants.TSM_RETURNCODE_DEVICE_CHECK_FAIL.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_AUTHENTICITY_CHECK_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEV_INACTIVATED.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_NOT_ACTIVATED);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_ILLEGAL.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_ILLEGAL);
        }
        if (Constants.TSM_RETURNCODE_OCE_CERT_CHECK_FAIL.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_OCE_CERT_CHECK_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_STOP_USING.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_STOP_USING);
        }
        if (Constants.TSM_RETURNCODE_RECEIPT_CHECK_FAIL.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_RECEIPT_CHECK_FAIL);
        }
        throw new ImkeyException("imkey_tsm_server_error_" + str);
    }

    public ImKeyDevice checkUpdate() {
        ImKeyDevice imKeyDevice = new ImKeyDevice();
        String seId = getSeId();
        String sn = getSn();
        imKeyDevice.setSeid(seId);
        imKeyDevice.setSn(sn);
        SeQuery seQuery = new SeQuery();
        SeInfoQueryRequest seInfoQueryRequest = new SeInfoQueryRequest();
        seInfoQueryRequest.setStepKey("01");
        seInfoQueryRequest.setSeid(seId);
        seInfoQueryRequest.setSn(sn);
        seInfoQueryRequest.setSdkVersion(Constants.version);
        SeInfoQueryResponse query = seQuery.query(seInfoQueryRequest);
        String str = query.get_ReturnCode();
        if (Constants.TSM_RETURNCODE_SUCCESS.equals(str)) {
            imKeyDevice.setStatus(Constants.IMKEY_DEV_STATUS_LATEST);
            imKeyDevice.setSdkMode(query.getSdkMode());
            imKeyDevice.setAvailableAppList(query.getAvailableAppList());
            return imKeyDevice;
        }
        if (Constants.TSM_RETURNCODE_DEV_INACTIVATED.equals(str)) {
            imKeyDevice.setStatus(Constants.IMKEY_DEV_STATUS_INACTIVATED);
            imKeyDevice.setSdkMode(query.getSdkMode());
            imKeyDevice.setAvailableAppList(query.getAvailableAppList());
            return imKeyDevice;
        }
        if (Constants.TSM_RETURNCODE_DEVICE_ILLEGAL.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_ILLEGAL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_STOP_USING.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_STOP_USING);
        }
        if (Constants.TSM_RETURNCODE_SE_QUERY_FAIL.equals(str)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_UPDATE_CHECK_FAIL);
        }
        throw new ImkeyException("imkey_tsm_server_error_" + str);
    }

    public void delete(String str) {
        String seId = getSeId();
        AppDeleteRequest appDeleteRequest = new AppDeleteRequest();
        appDeleteRequest.setStepKey("01");
        appDeleteRequest.setSeid(seId);
        appDeleteRequest.setInstanceAid(Applet.appletName2instanceAid(str));
        appDeleteRequest.setDeviceCert(getCert());
        appDeleteRequest.setStatusWord("");
        appDeleteRequest.setCardRetDataList(new ArrayList());
        String str2 = new AppDelete().delete(appDeleteRequest).get_ReturnCode();
        if (Constants.TSM_RETURNCODE_SUCCESS.equals(str2)) {
            return;
        }
        if (Constants.TSM_RETURNCODE_APP_DELETE_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_APP_DELETE_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_ILLEGAL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_ILLEGAL);
        }
        if (Constants.TSM_RETURNCODE_OCE_CERT_CHECK_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_OCE_CERT_CHECK_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_STOP_USING.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_STOP_USING);
        }
        if (Constants.TSM_RETURNCODE_RECEIPT_CHECK_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_RECEIPT_CHECK_FAIL);
        }
        throw new ImkeyException("imkey_tsm_server_error_" + str2);
    }

    public void displayBindingCode() {
        Apdu.checkResponse(Ble.getInstance().sendApdu(Apdu.generateAuthCode()));
    }

    public void download(String str) {
        String seId = getSeId();
        AppDownloadRequest appDownloadRequest = new AppDownloadRequest();
        appDownloadRequest.setStepKey("01");
        appDownloadRequest.setSeid(seId);
        appDownloadRequest.setInstanceAid(Applet.appletName2instanceAid(str));
        appDownloadRequest.setDeviceCert(getCert());
        appDownloadRequest.setStatusWord("");
        appDownloadRequest.setCardRetDataList(new ArrayList());
        String str2 = new AppDownload().download(appDownloadRequest).get_ReturnCode();
        if (Constants.TSM_RETURNCODE_SUCCESS.equals(str2)) {
            return;
        }
        if (Constants.TSM_RETURNCODE_APP_DOWNLOAD_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_APP_DOWNLOAD_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_ILLEGAL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_ILLEGAL);
        }
        if (Constants.TSM_RETURNCODE_OCE_CERT_CHECK_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_OCE_CERT_CHECK_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_STOP_USING.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_STOP_USING);
        }
        if (Constants.TSM_RETURNCODE_RECEIPT_CHECK_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_RECEIPT_CHECK_FAIL);
        }
        throw new ImkeyException("imkey_tsm_server_error_" + str2);
    }

    public String getBatteryPower() {
        Ble.getInstance().sendApdu(Constants.APDU_SELECT_ISD);
        String sendApdu = Ble.getInstance().sendApdu(Constants.APDU_GET_BATTERY_POWER);
        Apdu.checkResponse(sendApdu);
        String responseData = Apdu.getResponseData(sendApdu);
        String str = responseData;
        if (!responseData.equals(Constants.BATTERY_CHARGING_SIGN)) {
            str = String.valueOf(Integer.parseInt(responseData, 16));
        }
        return str;
    }

    public String getBleName() {
        return new String(ByteUtil.hexStringToByteArray(Ble.getInstance().sendApdu(Constants.APDU_GET_BLE_NAME)));
    }

    public String getBleVersion() {
        Ble.getInstance().sendApdu(Constants.APDU_SELECT_ISD);
        String sendApdu = Ble.getInstance().sendApdu(Constants.APDU_GET_BLE_VERSION);
        Apdu.checkResponse(sendApdu);
        String[] split = sendApdu.substring(0, 4).split("");
        return split[1] + "." + split[2] + "." + split[3] + split[4];
    }

    public String getFirmwareVersion() {
        Ble.getInstance().sendApdu(Constants.APDU_SELECT_ISD);
        String sendApdu = Ble.getInstance().sendApdu(Constants.APDU_GET_COS_VERSION);
        Apdu.checkResponse(sendApdu);
        String responseData = Apdu.getResponseData(sendApdu);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(responseData.substring(0, 1));
        stringBuffer.append('.');
        stringBuffer.append(responseData.substring(1, 2));
        stringBuffer.append('.');
        stringBuffer.append(responseData.substring(2));
        return stringBuffer.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public String getLifeTime() {
        boolean z;
        String sendApdu = Ble.getInstance().sendApdu(Constants.APDU_GET_LIFE_TIME);
        Apdu.checkResponse(sendApdu);
        String responseData = Apdu.getResponseData(sendApdu);
        switch (responseData.hashCode()) {
            case 1784:
                if (responseData.equals("80")) {
                    z = false;
                    break;
                }
                z = -1;
                break;
            case 1785:
                if (responseData.equals("81")) {
                    z = 2;
                    break;
                }
                z = -1;
                break;
            case 1786:
            case 1791:
            case 1792:
            default:
                z = -1;
                break;
            case 1787:
                if (responseData.equals("83")) {
                    z = 3;
                    break;
                }
                z = -1;
                break;
            case 1788:
                if (responseData.equals("84")) {
                    z = 4;
                    break;
                }
                z = -1;
                break;
            case 1789:
                if (responseData.equals("85")) {
                    z = 5;
                    break;
                }
                z = -1;
                break;
            case 1790:
                if (responseData.equals("86")) {
                    z = 6;
                    break;
                }
                z = -1;
                break;
            case 1793:
                if (responseData.equals("89")) {
                    z = true;
                    break;
                }
                z = -1;
                break;
        }
        switch (z) {
            case false:
                return Constants.LIFE_TIME_DEVICE_INITED;
            case true:
                return Constants.LIFE_TIME_DEVICE_ACTIVATED;
            case true:
                return Constants.LIFE_TIME_UNSET_PIN;
            case true:
                return Constants.LIFE_TIME_WALLET_UNREADY;
            case true:
                return Constants.LIFE_TIME_WALLET_CREATTING;
            case true:
                return Constants.LIFE_TIME_WALLET_RECOVERING;
            case true:
                return Constants.LIFE_TIME_WALLET_READY;
            default:
                return Constants.LIFE_TIME_UNKNOWN;
        }
    }

    public int getRamSize() {
        String sendApdu = Ble.getInstance().sendApdu(Constants.APDU_GET_RAM_SIZE);
        Apdu.checkResponse(sendApdu);
        return Integer.parseInt(sendApdu.substring(4, 8), 16);
    }

    public SdkInfo getSdkInfo() {
        SdkInfo sdkInfo = new SdkInfo();
        sdkInfo.setSdkVersion(Constants.version);
        return sdkInfo;
    }

    public String getSeId() {
        Ble.getInstance().sendApdu(Constants.APDU_SELECT_ISD);
        String sendApdu = Ble.getInstance().sendApdu(Constants.APDU_GET_SEID);
        Apdu.checkResponse(sendApdu);
        return Apdu.getResponseData(sendApdu);
    }

    public String getSn() {
        Ble.getInstance().sendApdu(Constants.APDU_SELECT_ISD);
        String sendApdu = Ble.getInstance().sendApdu(Constants.APDU_GET_SN);
        Apdu.checkResponse(sendApdu);
        String str = new String(ByteUtil.hexStringToByteArray(Apdu.getResponseData(sendApdu)));
        LogUtil.m2866d(str);
        return str;
    }

    protected void selectApplet() {
        Apdu.checkResponse(Ble.getInstance().sendApdu(Apdu.select(Applet.IMK_AID)));
    }

    public void setBleName(String str) {
        if (!Pattern.matches("^[a-zA-Z0-9\\-]{1,12}$", str)) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        Apdu.checkResponse(Ble.getInstance().sendApdu(Apdu.setBleName(str)));
    }

    public void update(String str) {
        String seId = getSeId();
        AppUpdateRequest appUpdateRequest = new AppUpdateRequest();
        appUpdateRequest.setStepKey("01");
        appUpdateRequest.setSeid(seId);
        appUpdateRequest.setInstanceAid(Applet.appletName2instanceAid(str));
        appUpdateRequest.setDeviceCert(getCert());
        appUpdateRequest.setStatusWord("");
        appUpdateRequest.setCardRetDataList(new ArrayList());
        String str2 = new AppUpdate().update(appUpdateRequest).get_ReturnCode();
        if (Constants.TSM_RETURNCODE_SUCCESS.equals(str2)) {
            return;
        }
        if (Constants.TSM_RETURNCODE_APP_UPDATE_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_APP_UPDATE_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_ILLEGAL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_ILLEGAL);
        }
        if (Constants.TSM_RETURNCODE_OCE_CERT_CHECK_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_OCE_CERT_CHECK_FAIL);
        }
        if (Constants.TSM_RETURNCODE_DEVICE_STOP_USING.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_DEVICE_STOP_USING);
        }
        if (Constants.TSM_RETURNCODE_RECEIPT_CHECK_FAIL.equals(str2)) {
            throw new ImkeyException(Messages.IMKEY_TSM_RECEIPT_CHECK_FAIL);
        }
        throw new ImkeyException("imkey_tsm_server_error_" + str2);
    }
}
