package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/SeSecureCheckResponse.class */
public class SeSecureCheckResponse extends CommonResponse {
    private String seid;

    public String getSeid() {
        return this.seid;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    @Override // im.imkey.imkeylibrary.device.model.CommonResponse
    public String toString() {
        return "SeSecureCheckResponse{seid='" + this.seid + "'}";
    }
}
