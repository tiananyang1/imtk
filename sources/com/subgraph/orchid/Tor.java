package com.subgraph.orchid;

import com.subgraph.orchid.circuits.CircuitManagerImpl;
import com.subgraph.orchid.circuits.TorInitializationTracker;
import com.subgraph.orchid.config.TorConfigProxy;
import com.subgraph.orchid.connections.ConnectionCacheImpl;
import com.subgraph.orchid.directory.DirectoryImpl;
import com.subgraph.orchid.directory.downloader.DirectoryDownloaderImpl;
import com.subgraph.orchid.socks.SocksPortListenerImpl;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Tor.class */
public class Tor {
    public static final int BOOTSTRAP_STATUS_CIRCUIT_CREATE = 90;
    public static final int BOOTSTRAP_STATUS_CONN_DIR = 5;
    public static final int BOOTSTRAP_STATUS_CONN_OR = 80;
    public static final int BOOTSTRAP_STATUS_DONE = 100;
    public static final int BOOTSTRAP_STATUS_HANDSHAKE_DIR = 10;
    public static final int BOOTSTRAP_STATUS_HANDSHAKE_OR = 85;
    public static final int BOOTSTRAP_STATUS_LOADING_DESCRIPTORS = 50;
    public static final int BOOTSTRAP_STATUS_LOADING_KEYS = 40;
    public static final int BOOTSTRAP_STATUS_LOADING_STATUS = 25;
    public static final int BOOTSTRAP_STATUS_ONEHOP_CREATE = 15;
    public static final int BOOTSTRAP_STATUS_REQUESTING_DESCRIPTORS = 45;
    public static final int BOOTSTRAP_STATUS_REQUESTING_KEYS = 35;
    public static final int BOOTSTRAP_STATUS_REQUESTING_STATUS = 20;
    public static final int BOOTSTRAP_STATUS_STARTING = 0;
    private static final String implementation = "Orchid";
    private static final String version = "1.0.0";
    private static final Logger logger = Logger.getLogger(Tor.class.getName());
    private static final Charset defaultCharset = createDefaultCharset();

    public static CircuitManager createCircuitManager(TorConfig torConfig, DirectoryDownloaderImpl directoryDownloaderImpl, Directory directory, ConnectionCache connectionCache, TorInitializationTracker torInitializationTracker) {
        return new CircuitManagerImpl(torConfig, directoryDownloaderImpl, directory, connectionCache, torInitializationTracker);
    }

    public static TorConfig createConfig() {
        TorConfig torConfig = (TorConfig) Proxy.newProxyInstance(TorConfigProxy.class.getClassLoader(), new Class[]{TorConfig.class}, new TorConfigProxy());
        if (isAndroidRuntime()) {
            logger.warning("Android Runtime detected, disabling V2 Link protocol");
            torConfig.setHandshakeV2Enabled(false);
        }
        return torConfig;
    }

    public static ConnectionCache createConnectionCache(TorConfig torConfig, TorInitializationTracker torInitializationTracker) {
        return new ConnectionCacheImpl(torConfig, torInitializationTracker);
    }

    private static Charset createDefaultCharset() {
        return Charset.forName("ISO-8859-1");
    }

    public static Directory createDirectory(TorConfig torConfig, DirectoryStore directoryStore) {
        return new DirectoryImpl(torConfig, directoryStore);
    }

    public static DirectoryDownloaderImpl createDirectoryDownloader(TorConfig torConfig, TorInitializationTracker torInitializationTracker) {
        return new DirectoryDownloaderImpl(torConfig, torInitializationTracker);
    }

    public static TorInitializationTracker createInitalizationTracker() {
        return new TorInitializationTracker();
    }

    public static SocksPortListener createSocksPortListener(TorConfig torConfig, CircuitManager circuitManager) {
        return new SocksPortListenerImpl(torConfig, circuitManager);
    }

    public static String getBuildRevision() {
        return Revision.getBuildRevision();
    }

    public static Charset getDefaultCharset() {
        return defaultCharset;
    }

    public static String getFullVersion() {
        String buildRevision = getBuildRevision();
        if (buildRevision == null || buildRevision.isEmpty()) {
            return getVersion();
        }
        return getVersion() + "." + buildRevision;
    }

    public static String getImplementation() {
        return implementation;
    }

    public static String getVersion() {
        return version;
    }

    public static boolean isAndroidRuntime() {
        String property = System.getProperty("java.runtime.name");
        return property != null && property.equals("Android Runtime");
    }
}
