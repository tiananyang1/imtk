package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/SeActivateResponse.class */
public class SeActivateResponse extends CommonResponse {
    private String seid;

    public String getSeid() {
        return this.seid;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    @Override // im.imkey.imkeylibrary.device.model.CommonResponse
    public String toString() {
        return "SeActivateResponse{seid='" + this.seid + "'}";
    }
}
