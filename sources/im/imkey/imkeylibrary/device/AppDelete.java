package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.device.model.AppDeleteRequest;
import im.imkey.imkeylibrary.device.model.AppDeleteResponse;
import im.imkey.imkeylibrary.device.model.CommonResponse;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.net.Https;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/AppDelete.class */
public class AppDelete extends TsmRequest {
    private static final String ACTION = "appDelete";

    private AppDeleteResponse fromJson(String str) {
        AppDeleteResponse appDeleteResponse;
        AppDeleteResponse appDeleteResponse2 = new AppDeleteResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            appDeleteResponse2.set_ReturnCode(jSONObject.getString("_ReturnCode"));
            appDeleteResponse2.set_ReturnMsg(jSONObject.getString("_ReturnMsg"));
            appDeleteResponse = appDeleteResponse2;
            if (appDeleteResponse2.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("_ReturnData");
                appDeleteResponse2.getClass();
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
                appDeleteResponse2.set_ReturnData(returnDataBean);
                return appDeleteResponse2;
            }
        } catch (JSONException e) {
            LogUtil.m2866d(e.getMessage());
            appDeleteResponse = null;
        }
        return appDeleteResponse;
    }

    private String toJson(AppDeleteRequest appDeleteRequest) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seid", appDeleteRequest.getSeid());
            jSONObject.put("instanceAid", appDeleteRequest.getInstanceAid());
            jSONObject.put("stepKey", appDeleteRequest.getStepKey());
            jSONObject.put("statusWord", appDeleteRequest.getStatusWord());
            jSONObject.put("deviceCert", appDeleteRequest.getDeviceCert());
            jSONObject.put("commandID", appDeleteRequest.getCommandID());
            JSONArray jSONArray = new JSONArray();
            if (appDeleteRequest.getCardRetDataList() != null) {
                Iterator<String> it = appDeleteRequest.getCardRetDataList().iterator();
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

    public AppDeleteResponse delete(AppDeleteRequest appDeleteRequest) {
        CommonResponse.ReturnDataBean returnDataBean;
        if (appDeleteRequest == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        appDeleteRequest.setCommandID(ACTION);
        String json = toJson(appDeleteRequest);
        if (json == null || json.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        AppDeleteResponse fromJson = fromJson(Https.post(ACTION, json));
        if (fromJson == null || fromJson.get_ReturnCode() == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_JSON_PARSE_ERROR);
        }
        if (!fromJson.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS) || (returnDataBean = fromJson.get_ReturnData()) == null) {
            return fromJson;
        }
        if ("end".equals(returnDataBean.getNextStepKey())) {
            return fromJson;
        }
        AppDeleteRequest appDeleteRequest2 = new AppDeleteRequest();
        appDeleteRequest2.setStepKey(returnDataBean.getNextStepKey());
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
                if (i2 == returnDataBean.getApduList().size() - 1) {
                    appDeleteRequest2.setStatusWord(getStatus(sendApdu));
                }
                i = i2 + 1;
            }
            appDeleteRequest2.setCardRetDataList(arrayList);
        }
        appDeleteRequest2.setSeid(appDeleteRequest.getSeid());
        appDeleteRequest2.setInstanceAid(appDeleteRequest.getInstanceAid());
        return delete(appDeleteRequest2);
    }
}
