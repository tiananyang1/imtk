package io.fabric.sdk.android.services.events;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/events/EventsStrategy.class */
public interface EventsStrategy<T> extends FileRollOverManager, EventsManager<T> {
    FilesSender getFilesSender();
}
