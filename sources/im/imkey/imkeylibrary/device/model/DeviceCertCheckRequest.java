package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/DeviceCertCheckRequest.class */
public class DeviceCertCheckRequest extends CommonRequest {
    public String deviceCert;
    public String seid;

    /* renamed from: sn */
    public String f2752sn;

    @Override // im.imkey.imkeylibrary.device.model.CommonRequest
    public String getDeviceCert() {
        return this.deviceCert;
    }

    public String getSeid() {
        return this.seid;
    }

    public String getSn() {
        return this.f2752sn;
    }

    @Override // im.imkey.imkeylibrary.device.model.CommonRequest
    public void setDeviceCert(String str) {
        this.deviceCert = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    public void setSn(String str) {
        this.f2752sn = str;
    }
}
