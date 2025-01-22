package com.sensorsdata.analytics.android.sdk;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.stub.StubApp;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/HeatMapService.class */
public class HeatMapService {
    private static HeatMapService instance;
    private static HeatMapViewCrawler mVTrack;

    private HeatMapService() {
    }

    public static HeatMapService getInstance() {
        if (instance == null) {
            instance = new HeatMapService();
        }
        return instance;
    }

    public void resume() {
        try {
            if (mVTrack != null) {
                mVTrack.startUpdates();
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public void start(Activity activity, String str, String str2) {
        try {
            Bundle bundle = StubApp.getOrigApplicationContext(activity.getApplicationContext()).getPackageManager().getApplicationInfo(StubApp.getOrigApplicationContext(activity.getApplicationContext()).getPackageName(), 128).metaData;
            Bundle bundle2 = bundle;
            if (bundle == null) {
                bundle2 = new Bundle();
            }
            if (Build.VERSION.SDK_INT >= 16) {
                String string = bundle2.getString("com.sensorsdata.analytics.android.ResourcePackageName");
                String str3 = string;
                if (string == null) {
                    str3 = activity.getPackageName();
                }
                mVTrack = new HeatMapViewCrawler(activity, str3, str, str2);
                mVTrack.startUpdates();
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    public void stop() {
        try {
            if (mVTrack != null) {
                mVTrack.stopUpdates(false);
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }
}
