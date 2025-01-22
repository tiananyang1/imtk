package com.sensorsdata.analytics.android.sdk;

import android.content.Context;
import android.view.OrientationEventListener;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataScreenOrientationDetector.class */
public class SensorsDataScreenOrientationDetector extends OrientationEventListener {
    private int mCurrentOrientation;

    public SensorsDataScreenOrientationDetector(Context context, int i) {
        super(context, i);
    }

    public String getOrientation() {
        int i = this.mCurrentOrientation;
        if (i == 0 || i == 180) {
            return "portrait";
        }
        if (i == 90 || i == 270) {
            return "landscape";
        }
        return null;
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i) {
        if (i == -1) {
            return;
        }
        if (i < 45 || i > 315) {
            this.mCurrentOrientation = 0;
            return;
        }
        if (i > 45 && i < 135) {
            this.mCurrentOrientation = 90;
            return;
        }
        if (i > 135 && i < 225) {
            this.mCurrentOrientation = 180;
        } else {
            if (i <= 225 || i >= 315) {
                return;
            }
            this.mCurrentOrientation = 270;
        }
    }
}
