package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/AuthCodeStorageRequest.class */
public class AuthCodeStorageRequest extends CommonRequest {
    public String authCode;
    public String seid;

    public String getAuthCode() {
        return this.authCode;
    }

    public String getSeid() {
        return this.seid;
    }

    public void setAuthCode(String str) {
        this.authCode = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }
}
