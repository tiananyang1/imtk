package com.sensorsdata.analytics.android.sdk.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/util/SensorsDataTimer.class */
public class SensorsDataTimer {
    private static SensorsDataTimer instance;
    private ScheduledExecutorService mScheduledExecutorService;

    private SensorsDataTimer() {
    }

    public static SensorsDataTimer getInstance() {
        if (instance == null) {
            instance = new SensorsDataTimer();
        }
        return instance;
    }

    private boolean isShutdown() {
        ScheduledExecutorService scheduledExecutorService = this.mScheduledExecutorService;
        return scheduledExecutorService == null || scheduledExecutorService.isShutdown();
    }

    public void shutdownTimerTask() {
        ScheduledExecutorService scheduledExecutorService = this.mScheduledExecutorService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }

    public void timer(Runnable runnable, long j, long j2) {
        if (isShutdown()) {
            this.mScheduledExecutorService = Executors.newScheduledThreadPool(1);
            this.mScheduledExecutorService.scheduleAtFixedRate(runnable, j, j2, TimeUnit.MILLISECONDS);
        }
    }
}
