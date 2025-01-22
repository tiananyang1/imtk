package com.sensorsdata.analytics.android.sdk;

import android.net.Uri;
import android.text.TextUtils;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/ServerUrl.class */
public class ServerUrl {
    private String host;
    private String project;
    private String token;
    private String url;

    private ServerUrl() {
    }

    public ServerUrl(String str) {
        this.url = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Uri parse = Uri.parse(str);
        try {
            try {
                this.host = parse.getHost();
                this.token = parse.getQueryParameter("token");
                this.project = parse.getQueryParameter("project");
                if (TextUtils.isEmpty(this.host)) {
                    this.host = "";
                }
                if (TextUtils.isEmpty(this.project)) {
                    this.project = "default";
                }
                if (!TextUtils.isEmpty(this.token)) {
                    return;
                }
            } catch (Exception e) {
                SALog.printStackTrace(e);
                if (TextUtils.isEmpty(this.host)) {
                    this.host = "";
                }
                if (TextUtils.isEmpty(this.project)) {
                    this.project = "default";
                }
                if (!TextUtils.isEmpty(this.token)) {
                    return;
                }
            }
            this.token = "";
        } catch (Throwable th) {
            if (TextUtils.isEmpty(this.host)) {
                this.host = "";
            }
            if (TextUtils.isEmpty(this.project)) {
                this.project = "default";
            }
            if (TextUtils.isEmpty(this.token)) {
                this.token = "";
            }
            throw th;
        }
    }

    public boolean check(ServerUrl serverUrl) {
        if (serverUrl == null) {
            return false;
        }
        try {
            if (getHost().equals(serverUrl.getHost())) {
                return getProject().equals(serverUrl.getProject());
            }
            return false;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return false;
        }
    }

    public String getHost() {
        return this.host;
    }

    public String getProject() {
        return this.project;
    }

    public String getToken() {
        return this.token;
    }

    public String getUrl() {
        return this.url;
    }

    public String toString() {
        return "url=" + this.url + ",host=" + this.host + ",project=" + this.project + ",token=" + this.token;
    }
}
