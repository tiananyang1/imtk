package com.subgraph.orchid;

import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/DirectoryStore.class */
public interface DirectoryStore {

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/DirectoryStore$CacheFile.class */
    public enum CacheFile {
        CERTIFICATES("certificates"),
        CONSENSUS("consensus"),
        CONSENSUS_MICRODESC("consensus-microdesc"),
        MICRODESCRIPTOR_CACHE("cached-microdescs"),
        MICRODESCRIPTOR_JOURNAL("cached-microdescs.new"),
        DESCRIPTOR_CACHE("cached-descriptors"),
        DESCRIPTOR_JOURNAL("cached-descriptors.new"),
        STATE("state");

        private final String filename;

        CacheFile(String str) {
            this.filename = str;
        }

        public String getFilename() {
            return this.filename;
        }
    }

    void appendDocumentList(CacheFile cacheFile, List<? extends Document> list);

    ByteBuffer loadCacheFile(CacheFile cacheFile);

    void removeAllCacheFiles();

    void removeCacheFile(CacheFile cacheFile);

    void writeData(CacheFile cacheFile, ByteBuffer byteBuffer);

    void writeDocument(CacheFile cacheFile, Document document);

    void writeDocumentList(CacheFile cacheFile, List<? extends Document> list);
}
