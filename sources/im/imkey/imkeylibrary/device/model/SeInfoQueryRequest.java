package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/SeInfoQueryRequest.class */
public class SeInfoQueryRequest extends CommonRequest {
    public String sdkVersion;
    public String seid;

    /* renamed from: sn */
    public String f2755sn;

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public String getSeid() {
        return this.seid;
    }

    public String getSn() {
        return this.f2755sn;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    public void setSn(String str) {
        this.f2755sn = str;
    }
}
