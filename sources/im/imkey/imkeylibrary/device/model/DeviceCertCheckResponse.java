package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/DeviceCertCheckResponse.class */
public class DeviceCertCheckResponse extends CommonResponse {
    public String seid;
    public boolean verifyResult;

    public String getSeid() {
        return this.seid;
    }

    public boolean getVerifyResult() {
        return this.verifyResult;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    public void setVerifyResult(boolean z) {
        this.verifyResult = z;
    }
}
