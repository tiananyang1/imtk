package com.subgraph.orchid.connections;

import com.subgraph.orchid.Connection;
import com.subgraph.orchid.ConnectionCache;
import com.subgraph.orchid.ConnectionFailedException;
import com.subgraph.orchid.ConnectionHandshakeException;
import com.subgraph.orchid.ConnectionTimeoutException;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.circuits.TorInitializationTracker;
import com.subgraph.orchid.dashboard.DashboardRenderable;
import com.subgraph.orchid.dashboard.DashboardRenderer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionCacheImpl.class */
public class ConnectionCacheImpl implements ConnectionCache, DashboardRenderable {
    private static final Logger logger = Logger.getLogger(ConnectionCacheImpl.class.getName());
    private final TorConfig config;
    private final TorInitializationTracker initializationTracker;
    private volatile boolean isClosed;
    private final ConcurrentMap<Router, Future<ConnectionImpl>> activeConnections = new ConcurrentHashMap();
    private final ConnectionSocketFactory factory = new ConnectionSocketFactory();
    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionCacheImpl$CloseIdleConnectionCheckTask.class */
    private class CloseIdleConnectionCheckTask implements Runnable {
        private CloseIdleConnectionCheckTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (Future future : ConnectionCacheImpl.this.activeConnections.values()) {
                if (future.isDone()) {
                    try {
                        ((ConnectionImpl) future.get()).idleCloseCheck();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionCacheImpl$ConnectionTask.class */
    public class ConnectionTask implements Callable<ConnectionImpl> {
        private final boolean isDirectoryConnection;
        private final Router router;

        ConnectionTask(Router router, boolean z) {
            this.router = router;
            this.isDirectoryConnection = z;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public ConnectionImpl call() throws Exception {
            ConnectionImpl connectionImpl = new ConnectionImpl(ConnectionCacheImpl.this.config, ConnectionCacheImpl.this.factory.createSocket(), this.router, ConnectionCacheImpl.this.initializationTracker, this.isDirectoryConnection);
            connectionImpl.connect();
            return connectionImpl;
        }
    }

    public ConnectionCacheImpl(TorConfig torConfig, TorInitializationTracker torInitializationTracker) {
        this.config = torConfig;
        this.initializationTracker = torInitializationTracker;
        this.scheduledExecutor.scheduleAtFixedRate(new CloseIdleConnectionCheckTask(), 5000L, 5000L, TimeUnit.MILLISECONDS);
    }

    private void addConnectionFromFuture(Future<ConnectionImpl> future, List<Connection> list) {
        try {
            if (!future.isDone() || future.isCancelled()) {
                return;
            }
            list.add(future.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e2) {
        }
    }

    private Future<ConnectionImpl> createFutureForIfAbsent(Router router, boolean z) {
        FutureTask futureTask = new FutureTask(new ConnectionTask(router, z));
        Future<ConnectionImpl> putIfAbsent = this.activeConnections.putIfAbsent(router, futureTask);
        if (putIfAbsent != null) {
            return putIfAbsent;
        }
        futureTask.run();
        return futureTask;
    }

    private Future<ConnectionImpl> getFutureFor(Router router, boolean z) {
        Future<ConnectionImpl> future = this.activeConnections.get(router);
        return future != null ? future : createFutureForIfAbsent(router, z);
    }

    private void printDashboardBanner(PrintWriter printWriter, int i) {
        if ((i & 2) != 0) {
            printWriter.println("[Connection Cache (verbose)]");
        } else {
            printWriter.println("[Connection Cache]");
        }
        printWriter.println();
    }

    @Override // com.subgraph.orchid.ConnectionCache
    public void close() {
        if (this.isClosed) {
            return;
        }
        this.isClosed = true;
        for (Future<ConnectionImpl> future : this.activeConnections.values()) {
            if (future.isDone()) {
                try {
                    future.get().closeSocket();
                } catch (InterruptedException e) {
                    logger.warning("Unexpected interruption while closing connection");
                } catch (ExecutionException e2) {
                    logger.warning("Exception closing connection: " + e2.getCause());
                }
            } else {
                future.cancel(true);
            }
        }
        this.activeConnections.clear();
        this.scheduledExecutor.shutdownNow();
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderable
    public void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) throws IOException {
        if ((i & 1) == 0) {
            return;
        }
        printDashboardBanner(printWriter, i);
        for (Connection connection : getActiveConnections()) {
            if (!connection.isClosed()) {
                dashboardRenderer.renderComponent(printWriter, i, connection);
            }
        }
        printWriter.println();
    }

    List<Connection> getActiveConnections() {
        ArrayList arrayList = new ArrayList();
        Iterator<Future<ConnectionImpl>> it = this.activeConnections.values().iterator();
        while (it.hasNext()) {
            addConnectionFromFuture(it.next(), arrayList);
        }
        return arrayList;
    }

    @Override // com.subgraph.orchid.ConnectionCache
    public Connection getConnectionTo(Router router, boolean z) throws InterruptedException, ConnectionTimeoutException, ConnectionFailedException, ConnectionHandshakeException {
        ConnectionImpl connectionImpl;
        if (this.isClosed) {
            throw new IllegalStateException("ConnectionCache has been closed");
        }
        logger.fine("Get connection to " + router.getAddress() + " " + router.getOnionPort() + " " + router.getNickname());
        while (true) {
            Future<ConnectionImpl> futureFor = getFutureFor(router, z);
            try {
                connectionImpl = futureFor.get();
            } catch (CancellationException e) {
                this.activeConnections.remove(router, futureFor);
            } catch (ExecutionException e2) {
                this.activeConnections.remove(router, futureFor);
                Throwable cause = e2.getCause();
                if (cause instanceof ConnectionTimeoutException) {
                    throw ((ConnectionTimeoutException) cause);
                }
                if (cause instanceof ConnectionFailedException) {
                    throw ((ConnectionFailedException) cause);
                }
                if (cause instanceof ConnectionHandshakeException) {
                    throw ((ConnectionHandshakeException) cause);
                }
                throw new RuntimeException("Unexpected exception: " + e2, e2);
            }
            if (!connectionImpl.isClosed()) {
                return connectionImpl;
            }
            this.activeConnections.remove(router, futureFor);
        }
    }

    @Override // com.subgraph.orchid.ConnectionCache
    public boolean isClosed() {
        return this.isClosed;
    }
}
