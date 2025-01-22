package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.device.model.CommonResponse;
import im.imkey.imkeylibrary.device.model.SeActivateRequest;
import im.imkey.imkeylibrary.device.model.SeActivateResponse;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.net.Https;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/SeActive.class */
public class SeActive extends TsmRequest {
    private static final String ACTION = "seActivate";

    private SeActivateResponse fromJson(String str) {
        SeActivateResponse seActivateResponse;
        SeActivateResponse seActivateResponse2 = new SeActivateResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            seActivateResponse2.set_ReturnCode(jSONObject.getString("_ReturnCode"));
            seActivateResponse2.set_ReturnMsg(jSONObject.getString("_ReturnMsg"));
            seActivateResponse = seActivateResponse2;
            if (seActivateResponse2.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("_ReturnData");
                seActivateResponse2.getClass();
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
                seActivateResponse2.set_ReturnData(returnDataBean);
                return seActivateResponse2;
            }
        } catch (JSONException e) {
            LogUtil.m2866d(e.getMessage());
            seActivateResponse = null;
        }
        return seActivateResponse;
    }

    private String toJson(SeActivateRequest seActivateRequest) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seid", seActivateRequest.getSeid());
            jSONObject.put("sn", seActivateRequest.getSn());
            jSONObject.put("stepKey", seActivateRequest.getStepKey());
            jSONObject.put("statusWord", seActivateRequest.getStatusWord());
            jSONObject.put("deviceCert", seActivateRequest.getDeviceCert());
            jSONObject.put("commandID", seActivateRequest.getCommandID());
            JSONArray jSONArray = new JSONArray();
            if (seActivateRequest.getCardRetDataList() != null) {
                Iterator<String> it = seActivateRequest.getCardRetDataList().iterator();
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

    public SeActivateResponse activeSe(SeActivateRequest seActivateRequest) {
        CommonResponse.ReturnDataBean returnDataBean;
        if (seActivateRequest == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        seActivateRequest.setCommandID(ACTION);
        String json = toJson(seActivateRequest);
        if (json == null || json.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        SeActivateResponse fromJson = fromJson(Https.post(ACTION, json));
        if (fromJson == null || fromJson.get_ReturnCode() == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_JSON_PARSE_ERROR);
        }
        if (!fromJson.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS) || (returnDataBean = fromJson.get_ReturnData()) == null) {
            return fromJson;
        }
        if ("end".equals(returnDataBean.getNextStepKey())) {
            return fromJson;
        }
        SeActivateRequest seActivateRequest2 = new SeActivateRequest();
        seActivateRequest2.setStepKey(returnDataBean.getNextStepKey());
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
                    seActivateRequest2.setStatusWord(getStatus(sendApdu));
                }
                i = i2 + 1;
            }
            seActivateRequest2.setCardRetDataList(arrayList);
        }
        seActivateRequest2.setSeid(seActivateRequest.getSeid());
        seActivateRequest2.setSn(seActivateRequest.getSn());
        return activeSe(seActivateRequest2);
    }
}
