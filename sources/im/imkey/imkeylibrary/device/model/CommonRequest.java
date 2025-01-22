package im.imkey.imkeylibrary.device.model;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/CommonRequest.class */
public class CommonRequest {
    List<String> cardRetDataList;
    String commandID;
    String deviceCert;
    String statusWord;
    String stepKey;

    public List<String> getCardRetDataList() {
        return this.cardRetDataList;
    }

    public String getCommandID() {
        return this.commandID;
    }

    public String getDeviceCert() {
        return this.deviceCert;
    }

    public String getStatusWord() {
        return this.statusWord;
    }

    public String getStepKey() {
        return this.stepKey;
    }

    public void setCardRetDataList(List<String> list) {
        this.cardRetDataList = list;
    }

    public void setCommandID(String str) {
        this.commandID = str;
    }

    public void setDeviceCert(String str) {
        this.deviceCert = str;
    }

    public void setStatusWord(String str) {
        this.statusWord = str;
    }

    public void setStepKey(String str) {
        this.stepKey = str;
    }

    public String toString() {
        return "CommonRequest{stepKey='" + this.stepKey + "', statusWord='" + this.statusWord + "', cardRetDataList=" + this.cardRetDataList + ", deviceCert='" + this.deviceCert + "'}";
    }
}
