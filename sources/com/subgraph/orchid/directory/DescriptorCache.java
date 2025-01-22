package com.subgraph.orchid.directory;

import com.subgraph.orchid.Descriptor;
import com.subgraph.orchid.DirectoryStore;
import com.subgraph.orchid.Threading;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParsingResult;
import com.subgraph.orchid.misc.GuardedBy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DescriptorCache.class */
public abstract class DescriptorCache<T extends Descriptor> {
    private static final Logger logger = Logger.getLogger(DescriptorCache.class.getName());
    private final DirectoryStore.CacheFile cacheFile;

    @GuardedBy("this")
    private int cacheLength;

    @GuardedBy("this")
    private int droppedBytes;

    @GuardedBy("this")
    private boolean initiallyLoaded;
    private final DirectoryStore.CacheFile journalFile;

    @GuardedBy("this")
    private int journalLength;
    private final DirectoryStore store;
    private final ScheduledExecutorService rebuildExecutor = Threading.newScheduledPool("DescriptorCache rebuild worker");
    private final DescriptorCacheData<T> data = new DescriptorCacheData<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DescriptorCache(DirectoryStore directoryStore, DirectoryStore.CacheFile cacheFile, DirectoryStore.CacheFile cacheFile2) {
        this.store = directoryStore;
        this.cacheFile = cacheFile;
        this.journalFile = cacheFile2;
        startRebuildTask();
    }

    private void clearMemoryCache() {
        synchronized (this) {
            this.data.clear();
            this.journalLength = 0;
            this.cacheLength = 0;
            this.droppedBytes = 0;
        }
    }

    private ByteBuffer[] loadCacheBuffers() {
        ByteBuffer loadCacheFile;
        ByteBuffer loadCacheFile2;
        synchronized (this.store) {
            loadCacheFile = this.store.loadCacheFile(this.cacheFile);
            loadCacheFile2 = this.store.loadCacheFile(this.journalFile);
        }
        return new ByteBuffer[]{loadCacheFile, loadCacheFile2};
    }

    private void loadCacheFileBuffer(ByteBuffer byteBuffer) {
        this.cacheLength = byteBuffer.limit();
        if (this.cacheLength == 0) {
            return;
        }
        DocumentParsingResult<T> parse = createDocumentParser(byteBuffer).parse();
        if (parse.isOkay()) {
            for (T t : parse.getParsedDocuments()) {
                t.setCacheLocation(Descriptor.CacheLocation.CACHED_CACHEFILE);
                this.data.addDescriptor(t);
            }
        }
    }

    private void loadJournalFileBuffer(ByteBuffer byteBuffer) {
        this.journalLength = byteBuffer.limit();
        if (this.journalLength == 0) {
            return;
        }
        DocumentParsingResult<T> parse = createDocumentParser(byteBuffer).parse();
        if (!parse.isOkay()) {
            if (parse.isInvalid()) {
                logger.warning("Invalid descriptor data parsing from journal file : " + parse.getMessage());
                return;
            }
            if (parse.isError()) {
                logger.warning("Error parsing descriptors from journal file : " + parse.getMessage());
                return;
            }
            return;
        }
        int i = 0;
        logger.fine("Loaded " + parse.getParsedDocuments().size() + " descriptors from journal");
        for (T t : parse.getParsedDocuments()) {
            t.setCacheLocation(Descriptor.CacheLocation.CACHED_JOURNAL);
            if (!this.data.addDescriptor(t)) {
                i++;
            }
        }
        if (i > 0) {
            logger.info("Found " + i + " duplicate descriptors in journal file");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeRebuildCache() {
        synchronized (this) {
            if (this.initiallyLoaded) {
                this.droppedBytes += this.data.cleanExpired();
                if (shouldRebuildCache()) {
                    rebuildCache();
                }
            }
        }
    }

    private void rebuildCache() {
        synchronized (this.store) {
            this.store.writeDocumentList(this.cacheFile, this.data.getAllDescriptors());
            this.store.removeCacheFile(this.journalFile);
        }
        reloadCache();
    }

    private void reloadCache() {
        synchronized (this) {
            clearMemoryCache();
            ByteBuffer[] loadCacheBuffers = loadCacheBuffers();
            loadCacheFileBuffer(loadCacheBuffers[0]);
            loadJournalFileBuffer(loadCacheBuffers[1]);
            if (!this.initiallyLoaded) {
                this.initiallyLoaded = true;
            }
        }
    }

    private boolean shouldRebuildCache() {
        int i = this.journalLength;
        if (i < 16384) {
            return false;
        }
        int i2 = this.droppedBytes;
        int i3 = this.cacheLength;
        return i2 > (i + i3) / 3 || i > i3 / 2;
    }

    private ScheduledFuture<?> startRebuildTask() {
        return this.rebuildExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.subgraph.orchid.directory.DescriptorCache.1
            @Override // java.lang.Runnable
            public void run() {
                DescriptorCache.this.maybeRebuildCache();
            }
        }, 5L, 30L, TimeUnit.MINUTES);
    }

    public void addDescriptor(T t) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(t);
        addDescriptors(arrayList);
    }

    public void addDescriptors(List<T> list) {
        synchronized (this) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (T t : list) {
                if (!this.data.addDescriptor(t)) {
                    i++;
                } else if (t.getCacheLocation() == Descriptor.CacheLocation.NOT_CACHED) {
                    this.journalLength += t.getBodyLength();
                    arrayList.add(t);
                }
            }
            if (!arrayList.isEmpty()) {
                this.store.appendDocumentList(this.journalFile, arrayList);
            }
            if (i > 0) {
                logger.info("Duplicate descriptors added to journal, count = " + i);
            }
        }
    }

    protected abstract DocumentParser<T> createDocumentParser(ByteBuffer byteBuffer);

    public T getDescriptor(HexDigest hexDigest) {
        return this.data.findByDigest(hexDigest);
    }

    public void initialLoad() {
        synchronized (this) {
            if (this.initiallyLoaded) {
                return;
            }
            reloadCache();
        }
    }

    public void shutdown() {
        this.rebuildExecutor.shutdownNow();
    }
}
