package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/SeSecureCheckRequest.class */
public class SeSecureCheckRequest extends CommonRequest {
    private String seid;

    /* renamed from: sn */
    private String f2756sn;

    public String getSeid() {
        return this.seid;
    }

    public String getSn() {
        return this.f2756sn;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    public void setSn(String str) {
        this.f2756sn = str;
    }

    @Override // im.imkey.imkeylibrary.device.model.CommonRequest
    public String toString() {
        return "SeSecureCheckRequest{seid='" + this.seid + "', sn='" + this.f2756sn + "', stepKey='" + this.stepKey + "', statusWord='" + this.statusWord + "', cardRetDataList=" + this.cardRetDataList + '}';
    }
}
