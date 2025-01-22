package com.sensorsdata.analytics.android.sdk;

import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataSDKRemoteConfig.class */
public class SensorsDataSDKRemoteConfig {
    static final int REMOTE_EVENT_TYPE_NO_USE = -1;
    private int mAutoTrackEventType;

    /* renamed from: v */
    private String f180v;
    private boolean disableDebugMode = false;
    private boolean disableSDK = false;
    private int autoTrackMode = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAutoTrackEventType() {
        return this.mAutoTrackEventType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAutoTrackMode() {
        return this.autoTrackMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getV() {
        return this.f180v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAutoTrackEventTypeIgnored(int i) {
        int i2 = this.autoTrackMode;
        boolean z = false;
        if (i2 == -1) {
            return false;
        }
        if (i2 == 0) {
            return true;
        }
        int i3 = this.mAutoTrackEventType;
        if ((i | i3) != i3) {
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDisableDebugMode() {
        return this.disableDebugMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDisableSDK() {
        return this.disableSDK;
    }

    public void setAutoTrackMode(int i) {
        this.autoTrackMode = i;
        int i2 = this.autoTrackMode;
        if (i2 == -1 || i2 == 0) {
            this.mAutoTrackEventType = 0;
            return;
        }
        if ((i2 & 1) == 1) {
            this.mAutoTrackEventType |= 1;
        }
        if ((this.autoTrackMode & 2) == 2) {
            this.mAutoTrackEventType |= 2;
        }
        if ((this.autoTrackMode & 4) == 4) {
            this.mAutoTrackEventType |= 4;
        }
        if ((this.autoTrackMode & 8) == 8) {
            this.mAutoTrackEventType |= 8;
        }
    }

    public void setDisableDebugMode(boolean z) {
        this.disableDebugMode = z;
    }

    public void setDisableSDK(boolean z) {
        this.disableSDK = z;
    }

    public void setV(String str) {
        this.f180v = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("v", this.f180v);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("disableDebugMode", this.disableDebugMode);
            jSONObject2.put("autoTrackMode", this.autoTrackMode);
            jSONObject2.put("disableSDK", this.disableSDK);
            jSONObject.put("configs", jSONObject2);
            return jSONObject;
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return jSONObject;
        }
    }

    public String toString() {
        return "{ v=" + this.f180v + ", disableDebugMode=" + this.disableDebugMode + ", disableSDK=" + this.disableSDK + ", autoTrackMode=" + this.autoTrackMode + "}";
    }
}
