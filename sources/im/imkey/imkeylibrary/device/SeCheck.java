package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.device.model.CommonResponse;
import im.imkey.imkeylibrary.device.model.SeSecureCheckRequest;
import im.imkey.imkeylibrary.device.model.SeSecureCheckResponse;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.net.Https;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/SeCheck.class */
public class SeCheck extends TsmRequest {
    private static final String ACTION = "seSecureCheck";

    private SeSecureCheckResponse fromJson(String str) {
        SeSecureCheckResponse seSecureCheckResponse = new SeSecureCheckResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            seSecureCheckResponse.set_ReturnCode(jSONObject.getString("_ReturnCode"));
            seSecureCheckResponse.set_ReturnMsg(jSONObject.getString("_ReturnMsg"));
            if (seSecureCheckResponse.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("_ReturnData");
                seSecureCheckResponse.getClass();
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
                seSecureCheckResponse.set_ReturnData(returnDataBean);
                return seSecureCheckResponse;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return seSecureCheckResponse;
    }

    private String toJson(SeSecureCheckRequest seSecureCheckRequest) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seid", seSecureCheckRequest.getSeid());
            jSONObject.put("sn", seSecureCheckRequest.getSn());
            jSONObject.put("stepKey", seSecureCheckRequest.getStepKey());
            jSONObject.put("statusWord", seSecureCheckRequest.getStatusWord());
            jSONObject.put("deviceCert", seSecureCheckRequest.getDeviceCert());
            jSONObject.put("commandID", seSecureCheckRequest.getCommandID());
            JSONArray jSONArray = new JSONArray();
            if (seSecureCheckRequest.getCardRetDataList() != null) {
                Iterator<String> it = seSecureCheckRequest.getCardRetDataList().iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
            }
            jSONObject.put("cardRetDataList", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public SeSecureCheckResponse checkSe(SeSecureCheckRequest seSecureCheckRequest) {
        CommonResponse.ReturnDataBean returnDataBean;
        if (seSecureCheckRequest == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        seSecureCheckRequest.setCommandID(ACTION);
        String json = toJson(seSecureCheckRequest);
        if (json == null || json.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        SeSecureCheckResponse fromJson = fromJson(Https.post(ACTION, json));
        if (fromJson == null || fromJson.get_ReturnCode() == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_JSON_PARSE_ERROR);
        }
        if (!fromJson.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS) || (returnDataBean = fromJson.get_ReturnData()) == null) {
            return fromJson;
        }
        if ("end".equals(returnDataBean.getNextStepKey())) {
            return fromJson;
        }
        SeSecureCheckRequest seSecureCheckRequest2 = new SeSecureCheckRequest();
        seSecureCheckRequest2.setStepKey(returnDataBean.getNextStepKey());
        ArrayList arrayList = new ArrayList();
        if (returnDataBean.getApduList() != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= returnDataBean.getApduList().size()) {
                    break;
                }
                String sendApdu = Ble.getInstance().sendApdu(returnDataBean.getApduList().get(i2));
                arrayList.add(sendApdu);
                if (i2 == returnDataBean.getApduList().size() - 1) {
                    seSecureCheckRequest2.setStatusWord(getStatus(sendApdu));
                }
                i = i2 + 1;
            }
            seSecureCheckRequest2.setCardRetDataList(arrayList);
        }
        seSecureCheckRequest2.setSeid(seSecureCheckRequest.getSeid());
        seSecureCheckRequest2.setSn(seSecureCheckRequest.getSn());
        return checkSe(seSecureCheckRequest2);
    }
}
