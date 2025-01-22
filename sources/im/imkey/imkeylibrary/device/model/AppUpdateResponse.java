package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/AppUpdateResponse.class */
public class AppUpdateResponse extends CommonResponse {
    private String instanceAid;
    private String paid;
    private String seid;

    public String getInstanceAid() {
        return this.instanceAid;
    }

    public String getPaid() {
        return this.paid;
    }

    public String getSeid() {
        return this.seid;
    }

    public void setInstanceAid(String str) {
        this.instanceAid = str;
    }

    public void setPaid(String str) {
        this.paid = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    @Override // im.imkey.imkeylibrary.device.model.CommonResponse
    public String toString() {
        return "AppUpdateResponse{seid='" + this.seid + "', paid='" + this.paid + "', instanceAid='" + this.instanceAid + "'}";
    }
}
