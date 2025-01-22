package im.imkey.imkeylibrary.device.model;

import java.sql.Timestamp;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/SeAppList.class */
public class SeAppList {
    public String appVersion;
    public String appletName;
    public Timestamp createtime;
    public String instanceAid;
    public String paid;
    public String sdAid;
    public String seid;
    public String status;
    public Timestamp updatetime;

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getAppletName() {
        return this.appletName;
    }

    public Timestamp getCreatetime() {
        return this.createtime;
    }

    public String getInstanceAid() {
        return this.instanceAid;
    }

    public String getPaid() {
        return this.paid;
    }

    public String getSdAid() {
        return this.sdAid;
    }

    public String getSeid() {
        return this.seid;
    }

    public String getStatus() {
        return this.status;
    }

    public Timestamp getUpdatetime() {
        return this.updatetime;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setAppletName(String str) {
        this.appletName = str;
    }

    public void setCreatetime(Timestamp timestamp) {
        this.createtime = timestamp;
    }

    public void setInstanceAid(String str) {
        this.instanceAid = str;
    }

    public void setPaid(String str) {
        this.paid = str;
    }

    public void setSdAid(String str) {
        this.sdAid = str;
    }

    public void setSeid(String str) {
        this.seid = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setUpdatetime(Timestamp timestamp) {
        this.updatetime = timestamp;
    }
}
