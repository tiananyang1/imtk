package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/AppUpdateRequest.class */
public class AppUpdateRequest extends CommonRequest {
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

    @Override // im.imkey.imkeylibrary.device.model.CommonRequest
    public String toString() {
        return "AppUpdateRequest{seid='" + this.seid + "', instanceAid='" + this.instanceAid + "'}";
    }
}
