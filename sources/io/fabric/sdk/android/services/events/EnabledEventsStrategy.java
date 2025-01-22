package io.fabric.sdk.android.services.events;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/events/EnabledEventsStrategy.class */
public abstract class EnabledEventsStrategy<T> implements EventsStrategy<T> {
    static final int UNDEFINED_ROLLOVER_INTERVAL_SECONDS = -1;
    protected final Context context;
    final ScheduledExecutorService executorService;
    protected final EventsFilesManager<T> filesManager;
    volatile int rolloverIntervalSeconds = -1;
    final AtomicReference<ScheduledFuture<?>> scheduledRolloverFutureRef = new AtomicReference<>();

    public EnabledEventsStrategy(Context context, ScheduledExecutorService scheduledExecutorService, EventsFilesManager<T> eventsFilesManager) {
        this.context = context;
        this.executorService = scheduledExecutorService;
        this.filesManager = eventsFilesManager;
    }

    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public void cancelTimeBasedFileRollOver() {
        if (this.scheduledRolloverFutureRef.get() != null) {
            CommonUtils.logControlled(this.context, "Cancelling time-based rollover because no events are currently being generated.");
            this.scheduledRolloverFutureRef.get().cancel(false);
            this.scheduledRolloverFutureRef.set(null);
        }
    }

    protected void configureRollover(int i) {
        this.rolloverIntervalSeconds = i;
        scheduleTimeBasedFileRollOver(0L, this.rolloverIntervalSeconds);
    }

    @Override // io.fabric.sdk.android.services.events.EventsManager
    public void deleteAllEvents() {
        this.filesManager.deleteAllEventsFiles();
    }

    public int getRollover() {
        return this.rolloverIntervalSeconds;
    }

    @Override // io.fabric.sdk.android.services.events.EventsManager
    public void recordEvent(T t) {
        CommonUtils.logControlled(this.context, t.toString());
        try {
            this.filesManager.writeEvent(t);
        } catch (IOException e) {
            CommonUtils.logControlledError(this.context, "Failed to write event.", e);
        }
        scheduleTimeBasedRollOverIfNeeded();
    }

    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public boolean rollFileOver() {
        try {
            return this.filesManager.rollFileOver();
        } catch (IOException e) {
            CommonUtils.logControlledError(this.context, "Failed to roll file over.", e);
            return false;
        }
    }

    void scheduleTimeBasedFileRollOver(long j, long j2) {
        if (this.scheduledRolloverFutureRef.get() == null) {
            TimeBasedFileRollOverRunnable timeBasedFileRollOverRunnable = new TimeBasedFileRollOverRunnable(this.context, this);
            CommonUtils.logControlled(this.context, "Scheduling time based file roll over every " + j2 + " seconds");
            try {
                this.scheduledRolloverFutureRef.set(this.executorService.scheduleAtFixedRate(timeBasedFileRollOverRunnable, j, j2, TimeUnit.SECONDS));
            } catch (RejectedExecutionException e) {
                CommonUtils.logControlledError(this.context, "Failed to schedule time based file roll over", e);
            }
        }
    }

    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public void scheduleTimeBasedRollOverIfNeeded() {
        if (this.rolloverIntervalSeconds != -1) {
            scheduleTimeBasedFileRollOver(this.rolloverIntervalSeconds, this.rolloverIntervalSeconds);
        }
    }

    void sendAndCleanUpIfSuccess() {
        int i;
        FilesSender filesSender = getFilesSender();
        if (filesSender == null) {
            CommonUtils.logControlled(this.context, "skipping files send because we don't yet know the target endpoint");
            return;
        }
        CommonUtils.logControlled(this.context, "Sending all files");
        List<File> batchOfFilesToSend = this.filesManager.getBatchOfFilesToSend();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            i = i3;
            try {
                if (batchOfFilesToSend.size() <= 0) {
                    break;
                }
                CommonUtils.logControlled(this.context, String.format(Locale.US, "attempt to send batch of %d files", Integer.valueOf(batchOfFilesToSend.size())));
                boolean send = filesSender.send(batchOfFilesToSend);
                i = i3;
                if (send) {
                    i = i3 + batchOfFilesToSend.size();
                    this.filesManager.deleteSentFiles(batchOfFilesToSend);
                }
                if (!send) {
                    break;
                }
                batchOfFilesToSend = this.filesManager.getBatchOfFilesToSend();
                i2 = i;
            } catch (Exception e) {
                CommonUtils.logControlledError(this.context, "Failed to send batch of analytics files to server: " + e.getMessage(), e);
                i = i3;
            }
        }
        if (i == 0) {
            this.filesManager.deleteOldestInRollOverIfOverMax();
        }
    }

    @Override // io.fabric.sdk.android.services.events.EventsManager
    public void sendEvents() {
        sendAndCleanUpIfSuccess();
    }
}
