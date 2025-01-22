package com.subgraph.orchid.directory;

import com.subgraph.orchid.DirectoryStore;
import com.subgraph.orchid.Document;
import com.subgraph.orchid.TorConfig;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DirectoryStoreImpl.class */
public class DirectoryStoreImpl implements DirectoryStore {
    private final TorConfig config;
    private Map<DirectoryStore.CacheFile, DirectoryStoreFile> fileMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectoryStoreImpl(TorConfig torConfig) {
        this.config = torConfig;
    }

    private DirectoryStoreFile getStoreFile(DirectoryStore.CacheFile cacheFile) {
        if (!this.fileMap.containsKey(cacheFile)) {
            this.fileMap.put(cacheFile, new DirectoryStoreFile(this.config, cacheFile.getFilename()));
        }
        return this.fileMap.get(cacheFile);
    }

    @Override // com.subgraph.orchid.DirectoryStore
    public void appendDocumentList(DirectoryStore.CacheFile cacheFile, List<? extends Document> list) {
        synchronized (this) {
            getStoreFile(cacheFile).appendDocuments(list);
        }
    }

    @Override // com.subgraph.orchid.DirectoryStore
    public ByteBuffer loadCacheFile(DirectoryStore.CacheFile cacheFile) {
        ByteBuffer loadContents;
        synchronized (this) {
            loadContents = getStoreFile(cacheFile).loadContents();
        }
        return loadContents;
    }

    @Override // com.subgraph.orchid.DirectoryStore
    public void removeAllCacheFiles() {
        synchronized (this) {
            DirectoryStore.CacheFile[] values = DirectoryStore.CacheFile.values();
            int length = values.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < length) {
                    getStoreFile(values[i2]).remove();
                    i = i2 + 1;
                }
            }
        }
    }

    @Override // com.subgraph.orchid.DirectoryStore
    public void removeCacheFile(DirectoryStore.CacheFile cacheFile) {
        synchronized (this) {
            getStoreFile(cacheFile).remove();
        }
    }

    @Override // com.subgraph.orchid.DirectoryStore
    public void writeData(DirectoryStore.CacheFile cacheFile, ByteBuffer byteBuffer) {
        synchronized (this) {
            getStoreFile(cacheFile).writeData(byteBuffer);
        }
    }

    @Override // com.subgraph.orchid.DirectoryStore
    public void writeDocument(DirectoryStore.CacheFile cacheFile, Document document) {
        synchronized (this) {
            writeDocumentList(cacheFile, Arrays.asList(document));
        }
    }

    @Override // com.subgraph.orchid.DirectoryStore
    public void writeDocumentList(DirectoryStore.CacheFile cacheFile, List<? extends Document> list) {
        synchronized (this) {
            getStoreFile(cacheFile).writeDocuments(list);
        }
    }
}
