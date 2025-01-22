package com.subgraph.orchid;

import com.subgraph.orchid.circuits.TorInitializationTracker;
import com.subgraph.orchid.crypto.PRNGFixes;
import com.subgraph.orchid.dashboard.Dashboard;
import com.subgraph.orchid.directory.downloader.DirectoryDownloaderImpl;
import com.subgraph.orchid.sockets.OrchidSocketFactory;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.net.SocketFactory;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/TorClient.class */
public class TorClient {
    private static final Logger logger = Logger.getLogger(TorClient.class.getName());
    private final CircuitManager circuitManager;
    private final TorConfig config;
    private final ConnectionCache connectionCache;
    private final Dashboard dashboard;
    private final Directory directory;
    private final DirectoryDownloaderImpl directoryDownloader;
    private final TorInitializationTracker initializationTracker;
    private boolean isStarted;
    private boolean isStopped;
    private final CountDownLatch readyLatch;
    private final SocksPortListener socksListener;

    public TorClient() {
        this(null);
    }

    public TorClient(DirectoryStore directoryStore) {
        this.isStarted = false;
        this.isStopped = false;
        if (Tor.isAndroidRuntime()) {
            PRNGFixes.apply();
        }
        this.config = Tor.createConfig();
        this.directory = Tor.createDirectory(this.config, directoryStore);
        this.initializationTracker = Tor.createInitalizationTracker();
        this.initializationTracker.addListener(createReadyFlagInitializationListener());
        this.connectionCache = Tor.createConnectionCache(this.config, this.initializationTracker);
        this.directoryDownloader = Tor.createDirectoryDownloader(this.config, this.initializationTracker);
        this.circuitManager = Tor.createCircuitManager(this.config, this.directoryDownloader, this.directory, this.connectionCache, this.initializationTracker);
        this.socksListener = Tor.createSocksPortListener(this.config, this.circuitManager);
        this.readyLatch = new CountDownLatch(1);
        this.dashboard = new Dashboard();
        this.dashboard.addRenderables(this.circuitManager, this.directoryDownloader, this.socksListener);
    }

    private static TorInitializationListener createInitalizationListner() {
        return new TorInitializationListener() { // from class: com.subgraph.orchid.TorClient.2
            @Override // com.subgraph.orchid.TorInitializationListener
            public void initializationCompleted() {
                System.out.println("Tor is ready to go!");
            }

            @Override // com.subgraph.orchid.TorInitializationListener
            public void initializationProgress(String str, int i) {
                System.out.println(">>> [ " + i + "% ]: " + str);
            }
        };
    }

    private TorInitializationListener createReadyFlagInitializationListener() {
        return new TorInitializationListener() { // from class: com.subgraph.orchid.TorClient.1
            @Override // com.subgraph.orchid.TorInitializationListener
            public void initializationCompleted() {
                TorClient.this.readyLatch.countDown();
            }

            @Override // com.subgraph.orchid.TorInitializationListener
            public void initializationProgress(String str, int i) {
            }
        };
    }

    private void ensureStarted() {
        synchronized (this) {
            if (!this.isStarted) {
                throw new IllegalStateException("Must call start() first");
            }
        }
    }

    public static void main(String[] strArr) {
        TorClient torClient = new TorClient();
        torClient.addInitializationListener(createInitalizationListner());
        torClient.start();
        torClient.enableSocksListener();
    }

    private void verifyUnlimitedStrengthPolicyInstalled() {
        try {
            try {
                if (Cipher.getMaxAllowedKeyLength("AES") >= 256) {
                    return;
                }
                logger.severe("Unlimited Strength Jurisdiction Policy Files are required but not installed.");
                throw new TorException("Unlimited Strength Jurisdiction Policy Files are required but not installed.");
            } catch (NoSuchMethodError e) {
                logger.info("Skipped check for Unlimited Strength Jurisdiction Policy Files");
            }
        } catch (NoSuchAlgorithmException e2) {
            logger.log(Level.SEVERE, "No AES provider found");
            throw new TorException(e2);
        }
    }

    public void addInitializationListener(TorInitializationListener torInitializationListener) {
        this.initializationTracker.addListener(torInitializationListener);
    }

    public void disableDashboard() {
        if (this.dashboard.isListening()) {
            this.dashboard.stopListening();
        }
    }

    public void enableDashboard() {
        if (this.dashboard.isListening()) {
            return;
        }
        this.dashboard.startListening();
    }

    public void enableDashboard(int i) {
        this.dashboard.setListeningPort(i);
        enableDashboard();
    }

    public void enableSocksListener() {
        enableSocksListener(9150);
    }

    public void enableSocksListener(int i) {
        this.socksListener.addListeningPort(i);
    }

    public CircuitManager getCircuitManager() {
        return this.circuitManager;
    }

    public TorConfig getConfig() {
        return this.config;
    }

    public ConnectionCache getConnectionCache() {
        return this.connectionCache;
    }

    public Directory getDirectory() {
        return this.directory;
    }

    public SocketFactory getSocketFactory() {
        return new OrchidSocketFactory(this);
    }

    public Stream openExitStreamTo(String str, int i) throws InterruptedException, TimeoutException, OpenFailedException {
        ensureStarted();
        return this.circuitManager.openExitStreamTo(str, i);
    }

    public void removeInitializationListener(TorInitializationListener torInitializationListener) {
        this.initializationTracker.removeListener(torInitializationListener);
    }

    public void start() {
        synchronized (this) {
            if (this.isStarted) {
                return;
            }
            if (this.isStopped) {
                throw new IllegalStateException("Cannot restart a TorClient instance.  Create a new instance instead.");
            }
            logger.info("Starting Orchid (version: " + Tor.getFullVersion() + ")");
            verifyUnlimitedStrengthPolicyInstalled();
            this.directoryDownloader.start(this.directory);
            this.circuitManager.startBuildingCircuits();
            if (this.dashboard.isEnabledByProperty()) {
                this.dashboard.startListening();
            }
            this.isStarted = true;
        }
    }

    public void stop() {
        synchronized (this) {
            if (!this.isStarted || this.isStopped) {
                return;
            }
            try {
                try {
                    this.socksListener.stop();
                    if (this.dashboard.isListening()) {
                        this.dashboard.stopListening();
                    }
                    this.directoryDownloader.stop();
                    this.circuitManager.stopBuildingCircuits(true);
                    this.directory.close();
                    this.connectionCache.close();
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Unexpected exception while shutting down TorClient instance: " + e, (Throwable) e);
                }
                this.isStopped = true;
            } catch (Throwable th) {
                this.isStopped = true;
                throw th;
            }
        }
    }

    public void waitUntilReady() throws InterruptedException {
        this.readyLatch.await();
    }

    public void waitUntilReady(long j) throws InterruptedException, TimeoutException {
        if (!this.readyLatch.await(j, TimeUnit.MILLISECONDS)) {
            throw new TimeoutException();
        }
    }
}
