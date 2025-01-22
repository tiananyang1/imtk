package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.device.model.CommonResponse;
import im.imkey.imkeylibrary.device.model.DeviceCertCheckRequest;
import im.imkey.imkeylibrary.device.model.DeviceCertCheckResponse;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.net.Https;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/DeviceCertCheck.class */
public class DeviceCertCheck extends TsmRequest {
    private static final String ACTION = "deviceCertCheck";

    private DeviceCertCheckResponse fromJson(String str) {
        DeviceCertCheckResponse deviceCertCheckResponse;
        DeviceCertCheckResponse deviceCertCheckResponse2 = new DeviceCertCheckResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            deviceCertCheckResponse2.set_ReturnCode(jSONObject.getString("_ReturnCode"));
            deviceCertCheckResponse2.set_ReturnMsg(jSONObject.getString("_ReturnMsg"));
            deviceCertCheckResponse = deviceCertCheckResponse2;
            if (deviceCertCheckResponse2.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("_ReturnData");
                deviceCertCheckResponse2.getClass();
                CommonResponse.ReturnDataBean returnDataBean = new CommonResponse.ReturnDataBean();
                returnDataBean.setSeid(jSONObject2.getString("seid"));
                deviceCertCheckResponse2.setVerifyResult(Boolean.valueOf(jSONObject2.getString("verifyResult")).booleanValue());
                deviceCertCheckResponse2.set_ReturnData(returnDataBean);
                return deviceCertCheckResponse2;
            }
        } catch (JSONException e) {
            LogUtil.m2866d(e.getMessage());
            deviceCertCheckResponse = null;
        }
        return deviceCertCheckResponse;
    }

    private String toJson(DeviceCertCheckRequest deviceCertCheckRequest) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seid", deviceCertCheckRequest.getSeid());
            jSONObject.put("sn", deviceCertCheckRequest.getSn());
            jSONObject.put("stepKey", deviceCertCheckRequest.getStepKey());
            jSONObject.put("deviceCert", deviceCertCheckRequest.getDeviceCert());
            JSONArray jSONArray = new JSONArray();
            if (deviceCertCheckRequest.getCardRetDataList() != null) {
                Iterator<String> it = deviceCertCheckRequest.getCardRetDataList().iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
            }
            jSONObject.put("cardRetDataList", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            LogUtil.m2866d(e.getMessage());
            return "";
        }
    }

    public DeviceCertCheckResponse deviceCertCheck(DeviceCertCheckRequest deviceCertCheckRequest) {
        if (deviceCertCheckRequest == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        deviceCertCheckRequest.setCommandID(ACTION);
        String json = toJson(deviceCertCheckRequest);
        if (json == null || json.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        DeviceCertCheckResponse fromJson = fromJson(Https.post(ACTION, json));
        if (fromJson == null || fromJson.get_ReturnCode() == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_JSON_PARSE_ERROR);
        }
        return fromJson;
    }
}
