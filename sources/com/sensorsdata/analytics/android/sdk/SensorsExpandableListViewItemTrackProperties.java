package com.sensorsdata.analytics.android.sdk;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsExpandableListViewItemTrackProperties.class */
public interface SensorsExpandableListViewItemTrackProperties {
    JSONObject getSensorsChildItemTrackProperties(int i, int i2) throws JSONException;

    JSONObject getSensorsGroupItemTrackProperties(int i) throws JSONException;
}
