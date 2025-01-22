package com.sensorsdata.analytics.android.sdk;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/ScreenAutoTracker.class */
public interface ScreenAutoTracker {
    String getScreenUrl();

    JSONObject getTrackProperties() throws JSONException;
}
