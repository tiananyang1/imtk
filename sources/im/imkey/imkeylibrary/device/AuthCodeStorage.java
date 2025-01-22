package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.device.model.AuthCodeStorageRequest;
import im.imkey.imkeylibrary.device.model.AuthCodeStorageResponse;
import im.imkey.imkeylibrary.device.model.CommonResponse;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.net.Https;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/AuthCodeStorage.class */
public class AuthCodeStorage extends TsmRequest {
    private static final String ACTION = "authCodeStorage";

    private AuthCodeStorageResponse fromJson(String str) {
        AuthCodeStorageResponse authCodeStorageResponse;
        AuthCodeStorageResponse authCodeStorageResponse2 = new AuthCodeStorageResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            authCodeStorageResponse2.set_ReturnCode(jSONObject.getString("_ReturnCode"));
            authCodeStorageResponse2.set_ReturnMsg(jSONObject.getString("_ReturnMsg"));
            authCodeStorageResponse = authCodeStorageResponse2;
            if (authCodeStorageResponse2.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("_ReturnData");
                authCodeStorageResponse2.getClass();
                CommonResponse.ReturnDataBean returnDataBean = new CommonResponse.ReturnDataBean();
                returnDataBean.setSeid(jSONObject2.getString("seid"));
                authCodeStorageResponse2.set_ReturnData(returnDataBean);
                return authCodeStorageResponse2;
            }
        } catch (JSONException e) {
            LogUtil.m2866d(e.getMessage());
            authCodeStorageResponse = null;
        }
        return authCodeStorageResponse;
    }

    private String toJson(AuthCodeStorageRequest authCodeStorageRequest) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seid", authCodeStorageRequest.getSeid());
            jSONObject.put("authCode", authCodeStorageRequest.getAuthCode());
            jSONObject.put("stepKey", authCodeStorageRequest.getStepKey());
            jSONObject.put("statusWord", authCodeStorageRequest.getStatusWord());
            jSONObject.put("commandID", authCodeStorageRequest.getCommandID());
            JSONArray jSONArray = new JSONArray();
            if (authCodeStorageRequest.getCardRetDataList() != null) {
                Iterator<String> it = authCodeStorageRequest.getCardRetDataList().iterator();
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

    public AuthCodeStorageResponse authCodeStorage(AuthCodeStorageRequest authCodeStorageRequest) {
        if (authCodeStorageRequest == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        authCodeStorageRequest.setCommandID(ACTION);
        String json = toJson(authCodeStorageRequest);
        if (json == null || json.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        AuthCodeStorageResponse fromJson = fromJson(Https.post(ACTION, json));
        if (fromJson == null || fromJson.get_ReturnCode() == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_JSON_PARSE_ERROR);
        }
        return fromJson;
    }
}
