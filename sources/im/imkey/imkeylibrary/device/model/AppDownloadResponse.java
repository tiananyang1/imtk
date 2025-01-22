package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/AppDownloadResponse.class */
public class AppDownloadResponse extends CommonResponse {
    private String instanceAid;
    private String seid;

    public String getInstanceAid() {
        return this.instanceAid;
    }

    public String getSeid() {
        return this.seid;
    }

    public void setInstanceAid(String str) {
        this.instanceAid = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    @Override // im.imkey.imkeylibrary.device.model.CommonResponse
    public String toString() {
        return "AppDownloadResponse{seid='" + this.seid + "', instanceAid='" + this.instanceAid + "'}";
    }
}
