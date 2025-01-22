package io.fabric.sdk.android.services.events;

import android.content.Context;
import com.stub.StubApp;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/events/EventsFilesManager.class */
public abstract class EventsFilesManager<T> {
    public static final int MAX_BYTE_SIZE_PER_FILE = 8000;
    public static final int MAX_FILES_IN_BATCH = 1;
    public static final int MAX_FILES_TO_KEEP = 100;
    public static final String ROLL_OVER_FILE_NAME_SEPARATOR = "_";
    protected final Context context;
    protected final CurrentTimeProvider currentTimeProvider;
    private final int defaultMaxFilesToKeep;
    protected final EventsStorage eventStorage;
    protected volatile long lastRollOverTime;
    protected final List<EventsStorageListener> rollOverListeners = new CopyOnWriteArrayList();
    protected final EventTransform<T> transform;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/events/EventsFilesManager$FileWithTimestamp.class */
    public static class FileWithTimestamp {
        final File file;
        final long timestamp;

        public FileWithTimestamp(File file, long j) {
            this.file = file;
            this.timestamp = j;
        }
    }

    public EventsFilesManager(Context context, EventTransform<T> eventTransform, CurrentTimeProvider currentTimeProvider, EventsStorage eventsStorage, int i) throws IOException {
        this.context = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.transform = eventTransform;
        this.eventStorage = eventsStorage;
        this.currentTimeProvider = currentTimeProvider;
        this.lastRollOverTime = this.currentTimeProvider.getCurrentTimeMillis();
        this.defaultMaxFilesToKeep = i;
    }

    private void rollFileOverIfNeeded(int i) throws IOException {
        if (this.eventStorage.canWorkingFileStore(i, getMaxByteSizePerFile())) {
            return;
        }
        CommonUtils.logControlled(this.context, 4, Fabric.TAG, String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", Integer.valueOf(this.eventStorage.getWorkingFileUsedSizeInBytes()), Integer.valueOf(i), Integer.valueOf(getMaxByteSizePerFile())));
        rollFileOver();
    }

    private void triggerRollOverOnListeners(String str) {
        Iterator<EventsStorageListener> it = this.rollOverListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onRollOver(str);
            } catch (Exception e) {
                CommonUtils.logControlledError(this.context, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    public void deleteAllEventsFiles() {
        EventsStorage eventsStorage = this.eventStorage;
        eventsStorage.deleteFilesInRollOverDirectory(eventsStorage.getAllFilesInRollOverDirectory());
        this.eventStorage.deleteWorkingFile();
    }

    public void deleteOldestInRollOverIfOverMax() {
        List<File> allFilesInRollOverDirectory = this.eventStorage.getAllFilesInRollOverDirectory();
        int maxFilesToKeep = getMaxFilesToKeep();
        if (allFilesInRollOverDirectory.size() <= maxFilesToKeep) {
            return;
        }
        int size = allFilesInRollOverDirectory.size() - maxFilesToKeep;
        CommonUtils.logControlled(this.context, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", Integer.valueOf(allFilesInRollOverDirectory.size()), Integer.valueOf(maxFilesToKeep), Integer.valueOf(size)));
        TreeSet treeSet = new TreeSet(new Comparator<FileWithTimestamp>() { // from class: io.fabric.sdk.android.services.events.EventsFilesManager.1
            @Override // java.util.Comparator
            public int compare(FileWithTimestamp fileWithTimestamp, FileWithTimestamp fileWithTimestamp2) {
                return (int) (fileWithTimestamp.timestamp - fileWithTimestamp2.timestamp);
            }
        });
        for (File file : allFilesInRollOverDirectory) {
            treeSet.add(new FileWithTimestamp(file, parseCreationTimestampFromFileName(file.getName())));
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            arrayList.add(((FileWithTimestamp) it.next()).file);
            if (arrayList.size() == size) {
                break;
            }
        }
        this.eventStorage.deleteFilesInRollOverDirectory(arrayList);
    }

    public void deleteSentFiles(List<File> list) {
        this.eventStorage.deleteFilesInRollOverDirectory(list);
    }

    protected abstract String generateUniqueRollOverFileName();

    public List<File> getBatchOfFilesToSend() {
        return this.eventStorage.getBatchOfFilesToSend(1);
    }

    public long getLastRollOverTime() {
        return this.lastRollOverTime;
    }

    protected int getMaxByteSizePerFile() {
        return 8000;
    }

    protected int getMaxFilesToKeep() {
        return this.defaultMaxFilesToKeep;
    }

    public long parseCreationTimestampFromFileName(String str) {
        String[] split = str.split(ROLL_OVER_FILE_NAME_SEPARATOR);
        if (split.length != 3) {
            return 0L;
        }
        try {
            return Long.valueOf(split[2]).longValue();
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    public void registerRollOverListener(EventsStorageListener eventsStorageListener) {
        if (eventsStorageListener != null) {
            this.rollOverListeners.add(eventsStorageListener);
        }
    }

    public boolean rollFileOver() throws IOException {
        String str;
        boolean z = true;
        if (this.eventStorage.isWorkingFileEmpty()) {
            str = null;
            z = false;
        } else {
            str = generateUniqueRollOverFileName();
            this.eventStorage.rollOver(str);
            CommonUtils.logControlled(this.context, 4, Fabric.TAG, String.format(Locale.US, "generated new file %s", str));
            this.lastRollOverTime = this.currentTimeProvider.getCurrentTimeMillis();
        }
        triggerRollOverOnListeners(str);
        return z;
    }

    public void writeEvent(T t) throws IOException {
        byte[] bytes = this.transform.toBytes(t);
        rollFileOverIfNeeded(bytes.length);
        this.eventStorage.add(bytes);
    }
}
