package com.subgraph.orchid;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/ConnectionCache.class */
public interface ConnectionCache {
    void close();

    Connection getConnectionTo(Router router, boolean z) throws InterruptedException, ConnectionTimeoutException, ConnectionFailedException, ConnectionHandshakeException;

    boolean isClosed();
}
