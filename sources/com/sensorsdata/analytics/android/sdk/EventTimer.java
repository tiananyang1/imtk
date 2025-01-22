package com.sensorsdata.analytics.android.sdk;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/EventTimer.class */
public class EventTimer {
    private long endTime;
    private long eventAccumulatedDuration;
    private boolean isPause;
    private long startTime;
    private final TimeUnit timeUnit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventTimer(TimeUnit timeUnit) {
        this.isPause = false;
        this.startTime = SystemClock.elapsedRealtime();
        this.timeUnit = timeUnit;
        this.eventAccumulatedDuration = 0L;
        this.endTime = -1L;
    }

    public EventTimer(TimeUnit timeUnit, long j, long j2) {
        this.isPause = false;
        this.timeUnit = timeUnit;
        this.startTime = j;
        this.endTime = j2;
        this.eventAccumulatedDuration = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x009f A[Catch: Exception -> 0x00bd, TRY_ENTER, TryCatch #0 {Exception -> 0x00bd, blocks: (B:9:0x004b, B:15:0x009f, B:18:0x00a4, B:18:0x00a4, B:19:0x00a7, B:21:0x0058, B:23:0x0062, B:24:0x006b, B:26:0x0075, B:28:0x007c, B:29:0x0083, B:31:0x008d), top: B:8:0x004b }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a4 A[Catch: Exception -> 0x00bd, Exception -> 0x00bd, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x00bd, blocks: (B:9:0x004b, B:15:0x009f, B:18:0x00a4, B:18:0x00a4, B:19:0x00a7, B:21:0x0058, B:23:0x0062, B:24:0x006b, B:26:0x0075, B:28:0x007c, B:29:0x0083, B:31:0x008d), top: B:8:0x004b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String duration() {
        /*
            Method dump skipped, instructions count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.EventTimer.duration():java.lang.String");
    }

    public long getEndTime() {
        return this.endTime;
    }

    public long getEventAccumulatedDuration() {
        return this.eventAccumulatedDuration;
    }

    public long getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPause() {
        return this.isPause;
    }

    public void setEventAccumulatedDuration(long j) {
        this.eventAccumulatedDuration = j;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTimerState(boolean z) {
        this.isPause = z;
        if (z) {
            this.eventAccumulatedDuration = (this.eventAccumulatedDuration + SystemClock.elapsedRealtime()) - this.startTime;
        }
        this.startTime = SystemClock.elapsedRealtime();
    }
}
