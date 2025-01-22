package im.imkey.imkeylibrary.device.model;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/SeInfoQueryResponse.class */
public class SeInfoQueryResponse extends CommonResponse {
    public List<AvailableAppInfo> availableAppList;
    public String sdkMode;
    public String seid;

    /* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/SeInfoQueryResponse$AppUpdateInfo.class */
    public static class AppUpdateInfo {
        private String appletName;
        private String appletVersion;
        private String updateType;

        public String getAppletName() {
            return this.appletName;
        }

        public String getAppletVersion() {
            return this.appletVersion;
        }

        public String getUpdateType() {
            return this.updateType;
        }

        public void setAppletName(String str) {
            this.appletName = str;
        }

        public void setAppletVersion(String str) {
            this.appletVersion = str;
        }

        public void setUpdateType(String str) {
            this.updateType = str;
        }
    }

    public List<AvailableAppInfo> getAvailableAppList() {
        return this.availableAppList;
    }

    public String getSdkMode() {
        return this.sdkMode;
    }

    public String getSeid() {
        return this.seid;
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
}
