package im.imkey.imkeylibrary.device.model;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/ImKeyDevice.class */
public class ImKeyDevice {
    public List<AvailableAppInfo> availableAppList;
    private String sdkMode;
    private String seid;

    /* renamed from: sn */
    private String f2753sn;
    private String status;

    public List<AvailableAppInfo> getAvailableAppList() {
        return this.availableAppList;
    }

    public String getSdkMode() {
        return this.sdkMode;
    }

    public String getSeid() {
        return this.seid;
    }

    public String getSn() {
        return this.f2753sn;
    }

    public String getStatus() {
        return this.status;
    }

    public void setAvailableAppList(List<AvailableAppInfo> list) {
        this.availableAppList = list;
    }

    public void setSdkMode(String str) {
        this.sdkMode = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    public void setSn(String str) {
        this.f2753sn = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        return "ImKeyDevice{seid='" + this.seid + "', sn='" + this.f2753sn + "', status='" + this.status + "', sdkMode='" + this.sdkMode + "', availableAppList=" + this.availableAppList + '}';
    }
}
