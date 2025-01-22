package im.imkey.imkeylibrary.device;

import im.imkey.imkeylibrary.bluetooth.Ble;
import im.imkey.imkeylibrary.common.Constants;
import im.imkey.imkeylibrary.common.Messages;
import im.imkey.imkeylibrary.device.model.CommonResponse;
import im.imkey.imkeylibrary.device.model.SeInfoQueryRequest;
import im.imkey.imkeylibrary.device.model.SeInfoQueryResponse;
import im.imkey.imkeylibrary.exception.ImkeyException;
import im.imkey.imkeylibrary.net.Https;
import im.imkey.imkeylibrary.utils.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/SeQuery.class */
public class SeQuery extends TsmRequest {
    private static final String ACTION = "seInfoQuery";

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0045, code lost:            if (r0.get_ReturnCode().equals(im.imkey.imkeylibrary.common.Constants.TSM_RETURNCODE_DEV_INACTIVATED) != false) goto L9;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private im.imkey.imkeylibrary.device.model.SeInfoQueryResponse fromJson(java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 381
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: im.imkey.imkeylibrary.device.SeQuery.fromJson(java.lang.String):im.imkey.imkeylibrary.device.model.SeInfoQueryResponse");
    }

    private String toJson(SeInfoQueryRequest seInfoQueryRequest) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seid", seInfoQueryRequest.getSeid());
            jSONObject.put("sn", seInfoQueryRequest.getSn());
            jSONObject.put("sdkVersion", seInfoQueryRequest.getSdkVersion());
            jSONObject.put("stepKey", seInfoQueryRequest.getStepKey());
            jSONObject.put("statusWord", seInfoQueryRequest.getStatusWord());
            jSONObject.put("deviceCert", seInfoQueryRequest.getDeviceCert());
            jSONObject.put("commandID", seInfoQueryRequest.getCommandID());
            JSONArray jSONArray = new JSONArray();
            if (seInfoQueryRequest.getCardRetDataList() != null) {
                Iterator<String> it = seInfoQueryRequest.getCardRetDataList().iterator();
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

    public SeInfoQueryResponse query(SeInfoQueryRequest seInfoQueryRequest) {
        CommonResponse.ReturnDataBean returnDataBean;
        if (seInfoQueryRequest == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        seInfoQueryRequest.setCommandID(ACTION);
        String json = toJson(seInfoQueryRequest);
        if (json == null || json.length() == 0) {
            throw new ImkeyException(Messages.IMKEY_SDK_ILLEGAL_ARGUMENT);
        }
        SeInfoQueryResponse fromJson = fromJson(Https.post(ACTION, json));
        if (fromJson == null || fromJson.get_ReturnCode() == null) {
            throw new ImkeyException(Messages.IMKEY_SDK_JSON_PARSE_ERROR);
        }
        if (!fromJson.get_ReturnCode().equals(Constants.TSM_RETURNCODE_SUCCESS) || (returnDataBean = fromJson.get_ReturnData()) == null) {
            return fromJson;
        }
        if ("end".equals(returnDataBean.getNextStepKey())) {
            return fromJson;
        }
        SeInfoQueryRequest seInfoQueryRequest2 = new SeInfoQueryRequest();
        seInfoQueryRequest2.setStepKey(returnDataBean.getNextStepKey());
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
                    seInfoQueryRequest2.setStatusWord(getStatus(sendApdu));
                }
                i = i2 + 1;
            }
            seInfoQueryRequest2.setCardRetDataList(arrayList);
        }
        seInfoQueryRequest2.setSeid(seInfoQueryRequest.getSeid());
        seInfoQueryRequest2.setSn(seInfoQueryRequest.getSn());
        return query(seInfoQueryRequest2);
    }
}
