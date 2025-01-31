package io.fabric.sdk.android.services.events;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/events/DisabledEventsStrategy.class */
public class DisabledEventsStrategy<T> implements EventsStrategy<T> {
    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public void cancelTimeBasedFileRollOver() {
    }

    @Override // io.fabric.sdk.android.services.events.EventsManager
    public void deleteAllEvents() {
    }

    @Override // io.fabric.sdk.android.services.events.EventsStrategy
    public FilesSender getFilesSender() {
        return null;
    }

    @Override // io.fabric.sdk.android.services.events.EventsManager
    public void recordEvent(T t) {
    }

    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public boolean rollFileOver() {
        return false;
    }

    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public void scheduleTimeBasedRollOverIfNeeded() {
    }

    @Override // io.fabric.sdk.android.services.events.EventsManager
    public void sendEvents() {
    }
}
