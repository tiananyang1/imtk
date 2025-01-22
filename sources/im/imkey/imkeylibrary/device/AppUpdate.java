package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.device.model.AppUpdateRequest;
import im.imkey.imkeylibrary.device.model.AppUpdateResponse;
import im.imkey.imkeylibrary.device.model.CommonResponse;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.net.Https;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/AppUpdate.class */
public class AppUpdate extends TsmRequest {
    private static final String ACTION = "appUpdate";

    private AppUpdateResponse fromJson(String str) {
        AppUpdateResponse appUpdateResponse;
        AppUpdateResponse appUpdateResponse2 = new AppUpdateResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            appUpdateResponse2.set_ReturnCode(jSONObject.getString("_ReturnCode"));
            appUpdateResponse2.set_ReturnMsg(jSONObject.getString("_ReturnMsg"));
            appUpdateResponse = appUpdateResponse2;
            if (appUpdateResponse2.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("_ReturnData");
                appUpdateResponse2.getClass();
                CommonResponse.ReturnDataBean returnDataBean = new CommonResponse.ReturnDataBean();
                returnDataBean.setSeid(jSONObject2.getString("seid"));
                returnDataBean.setNextStepKey(jSONObject2.getString("nextStepKey"));
                if (!returnDataBean.getNextStepKey().equals("end")) {
                    JSONArray jSONArray = jSONObject2.getJSONArray("apduList");
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= jSONArray.length()) {
                            break;
                        }
                        arrayList.add(jSONArray.getString(i2));
                        i = i2 + 1;
                    }
                    returnDataBean.setApduList(arrayList);
                }
                appUpdateResponse2.set_ReturnData(returnDataBean);
                return appUpdateResponse2;
            }
        } catch (JSONException e) {
            LogUtil.m2866d(e.getMessage());
            appUpdateResponse = null;
        }
        return appUpdateResponse;
    }

    private String toJson(AppUpdateRequest appUpdateRequest) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seid", appUpdateRequest.getSeid());
            jSONObject.put("instanceAid", appUpdateRequest.getInstanceAid());
            jSONObject.put("stepKey", appUpdateRequest.getStepKey());
            jSONObject.put("statusWord", appUpdateRequest.getStatusWord());
            jSONObject.put("deviceCert", appUpdateRequest.getDeviceCert());
            jSONObject.put("commandID", appUpdateRequest.getCommandID());
            JSONArray jSONArray = new JSONArray();
            if (appUpdateRequest.getCardRetDataList() != null) {
                Iterator<String> it = appUpdateRequest.getCardRetDataList().iterator();
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

    public AppUpdateResponse update(AppUpdateRequest appUpdateRequest) {
        CommonResponse.ReturnDataBean returnDataBean;
        if (appUpdateRequest == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        appUpdateRequest.setCommandID(ACTION);
        String json = toJson(appUpdateRequest);
        if (json == null || json.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        AppUpdateResponse fromJson = fromJson(Https.post(ACTION, json));
        if (fromJson == null || fromJson.get_ReturnCode() == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_JSON_PARSE_ERROR);
        }
        if (!fromJson.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS) || (returnDataBean = fromJson.get_ReturnData()) == null) {
            return fromJson;
        }
        if ("end".equals(returnDataBean.getNextStepKey())) {
            return fromJson;
        }
        AppUpdateRequest appUpdateRequest2 = new AppUpdateRequest();
        appUpdateRequest2.setStepKey(returnDataBean.getNextStepKey());
        ArrayList arrayList = new ArrayList();
        if (returnDataBean.getApduList() != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= returnDataBean.getApduList().size()) {
                    break;
                }
                String sendApdu = Ble.getInstance().sendApdu(returnDataBean.getApduList().get(i2));
                arrayList.add(sendApdu.toUpperCase());
                String status = getStatus(sendApdu);
                if ("03".equals(returnDataBean.getNextStepKey()) && i2 > 0 && !status.equals(Constants.APDU_RSP_SUCCESS)) {
                    break;
                }
                if (i2 == returnDataBean.getApduList().size() - 1) {
                    appUpdateRequest2.setStatusWord(status);
                }
                i = i2 + 1;
            }
            appUpdateRequest2.setCardRetDataList(arrayList);
        }
        appUpdateRequest2.setSeid(appUpdateRequest.getSeid());
        appUpdateRequest2.setInstanceAid(appUpdateRequest.getInstanceAid());
        return update(appUpdateRequest2);
    }
}
