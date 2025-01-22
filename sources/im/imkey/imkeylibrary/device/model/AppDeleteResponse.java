package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/AppDeleteResponse.class */
public class AppDeleteResponse extends CommonResponse {
    public String instanceAid;
    public String paid;
    public String seid;

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
}
