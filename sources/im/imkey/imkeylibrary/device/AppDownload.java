package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.device.model.AppDownloadRequest;
import im.imkey.imkeylibrary.device.model.AppDownloadResponse;
import im.imkey.imkeylibrary.device.model.CommonResponse;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.net.Https;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/AppDownload.class */
public class AppDownload extends TsmRequest {
    private static final String ACTION = "appDownload";

    private AppDownloadResponse fromJson(String str) {
        AppDownloadResponse appDownloadResponse;
        AppDownloadResponse appDownloadResponse2 = new AppDownloadResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            appDownloadResponse2.set_ReturnCode(jSONObject.getString("_ReturnCode"));
            appDownloadResponse2.set_ReturnMsg(jSONObject.getString("_ReturnMsg"));
            appDownloadResponse = appDownloadResponse2;
            if (appDownloadResponse2.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("_ReturnData");
                appDownloadResponse2.getClass();
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
                appDownloadResponse2.set_ReturnData(returnDataBean);
                return appDownloadResponse2;
            }
        } catch (JSONException e) {
            LogUtil.m2866d(e.getMessage());
            appDownloadResponse = null;
        }
        return appDownloadResponse;
    }

    private String toJson(AppDownloadRequest appDownloadRequest) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seid", appDownloadRequest.getSeid());
            jSONObject.put("instanceAid", appDownloadRequest.getInstanceAid());
            jSONObject.put("stepKey", appDownloadRequest.getStepKey());
            jSONObject.put("statusWord", appDownloadRequest.getStatusWord());
            jSONObject.put("deviceCert", appDownloadRequest.getDeviceCert());
            jSONObject.put("commandID", appDownloadRequest.getCommandID());
            JSONArray jSONArray = new JSONArray();
            if (appDownloadRequest.getCardRetDataList() != null) {
                Iterator<String> it = appDownloadRequest.getCardRetDataList().iterator();
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

    public AppDownloadResponse download(AppDownloadRequest appDownloadRequest) {
        CommonResponse.ReturnDataBean returnDataBean;
        if (appDownloadRequest == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        appDownloadRequest.setCommandID(ACTION);
        String json = toJson(appDownloadRequest);
        if (json == null || json.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        AppDownloadResponse fromJson = fromJson(Https.post(ACTION, json));
        if (fromJson == null || fromJson.get_ReturnCode() == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_JSON_PARSE_ERROR);
        }
        if (!fromJson.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS) || (returnDataBean = fromJson.get_ReturnData()) == null) {
            return fromJson;
        }
        if ("end".equals(returnDataBean.getNextStepKey())) {
            return fromJson;
        }
        AppDownloadRequest appDownloadRequest2 = new AppDownloadRequest();
        appDownloadRequest2.setStepKey(returnDataBean.getNextStepKey());
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
                    appDownloadRequest2.setStatusWord(status);
                }
                i = i2 + 1;
            }
            appDownloadRequest2.setCardRetDataList(arrayList);
        }
        appDownloadRequest2.setSeid(appDownloadRequest.getSeid());
        appDownloadRequest2.setInstanceAid(appDownloadRequest.getInstanceAid());
        return download(appDownloadRequest2);
    }
}
