package im.imkey.imkeylibrary.device.model;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/device/model/AvailableAppInfo.class */
public class AvailableAppInfo {
    public String appLogo;
    public String appletName;
    public String installMode;
    public String installedVersion;
    public String lastUpated;
    public String latestVersion;

    public String getAppLogo() {
        return this.appLogo;
    }

    public String getAppletName() {
        return this.appletName;
    }

    public String getInstallMode() {
        return this.installMode;
    }

    public String getInstalledVersion() {
        return this.installedVersion;
    }

    public String getLastUpated() {
        return this.lastUpated;
    }

    public String getLatestVersion() {
        return this.latestVersion;
    }

    public void setAppLogo(String str) {
        this.appLogo = str;
    }

    public void setAppletName(String str) {
        this.appletName = str;
    }

    public void setInstallMode(String str) {
        this.installMode = str;
    }

    public void setInstalledVersion(String str) {
        this.installedVersion = str;
    }

    public void setLastUpated(String str) {
        this.lastUpated = str;
    }

    public void setLatestVersion(String str) {
        this.latestVersion = str;
    }

    public String toString() {
        return "AvailableAppInfo{appletName='" + this.appletName + "', appLogo='" + this.appLogo + "', lastUpated='" + this.lastUpated + "', installMode='" + this.installMode + "', latestVersion='" + this.latestVersion + "', installedVersion='" + this.installedVersion + "'}";
    }
}
