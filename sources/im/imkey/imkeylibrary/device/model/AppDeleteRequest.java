package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/AppDeleteRequest.class */
public class AppDeleteRequest extends CommonRequest {
    public String deviceCert;
    public String instanceAid;
    public String seid;

    @Override // im.imkey.imkeylibrary.device.model.CommonRequest
    public String getDeviceCert() {
        return this.deviceCert;
    }

    public String getInstanceAid() {
        return this.instanceAid;
    }

    public String getSeid() {
        return this.seid;
    }

    @Override // im.imkey.imkeylibrary.device.model.CommonRequest
    public void setDeviceCert(String str) {
        this.deviceCert = str;
    }

    public void setInstanceAid(String str) {
        this.instanceAid = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }
}
