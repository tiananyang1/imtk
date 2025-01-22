package com.subgraph.orchid.circuits.guards;

import com.subgraph.orchid.BridgeRouter;
import com.subgraph.orchid.DirectoryDownloader;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.config.TorConfigBridgeLine;
import com.subgraph.orchid.crypto.TorRandom;
import com.subgraph.orchid.directory.downloader.DirectoryRequestFailedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/guards/Bridges.class */
public class Bridges {
    private static final Logger logger = Logger.getLogger(Bridges.class.getName());
    private boolean bridgesInitialized;
    private boolean bridgesInitializing;
    private final TorConfig config;
    private final DirectoryDownloader directoryDownloader;
    private final Set<BridgeRouterImpl> bridgeRouters = new HashSet();
    private final TorRandom random = new TorRandom();
    private final Object lock = new Object();
    private AtomicInteger outstandingDownloadTasks = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/guards/Bridges$DescriptorDownloader.class */
    public class DescriptorDownloader implements Runnable {
        private final BridgeRouterImpl target;

        DescriptorDownloader(BridgeRouterImpl bridgeRouterImpl) {
            this.target = bridgeRouterImpl;
        }

        private void decrementOutstandingTasks() {
            if (Bridges.this.outstandingDownloadTasks.decrementAndGet() == 0) {
                Bridges.logger.fine("Initial descriptor fetch complete");
                synchronized (Bridges.this.lock) {
                    Bridges.this.bridgesInitialized = true;
                    Bridges.this.lock.notifyAll();
                }
            }
        }

        private void downloadDescriptor() {
            Bridges.logger.fine("Downloading descriptor for bridge: " + this.target);
            try {
                RouterDescriptor downloadBridgeDescriptor = Bridges.this.directoryDownloader.downloadBridgeDescriptor(this.target);
                if (downloadBridgeDescriptor != null) {
                    Bridges.logger.fine("Descriptor received for bridge " + this.target + ". Adding to list of usable bridges");
                    this.target.setDescriptor(downloadBridgeDescriptor);
                    synchronized (Bridges.this.lock) {
                        Bridges.this.bridgeRouters.add(this.target);
                        Bridges.this.lock.notifyAll();
                    }
                }
            } catch (DirectoryRequestFailedException e) {
                Bridges.logger.warning("Failed to download descriptor for bridge: " + e.getMessage());
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                downloadDescriptor();
            } finally {
                decrementOutstandingTasks();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bridges(TorConfig torConfig, DirectoryDownloader directoryDownloader) {
        this.config = torConfig;
        this.directoryDownloader = directoryDownloader;
    }

    private BridgeRouterImpl createBridgeFromLine(TorConfigBridgeLine torConfigBridgeLine) {
        BridgeRouterImpl bridgeRouterImpl = new BridgeRouterImpl(torConfigBridgeLine.getAddress(), torConfigBridgeLine.getPort());
        if (torConfigBridgeLine.getFingerprint() != null) {
            bridgeRouterImpl.setIdentity(torConfigBridgeLine.getFingerprint());
        }
        return bridgeRouterImpl;
    }

    private List<Runnable> createDownloadTasks() {
        ArrayList arrayList = new ArrayList();
        Iterator<TorConfigBridgeLine> it = this.config.getBridges().iterator();
        while (it.hasNext()) {
            arrayList.add(new DescriptorDownloader(createBridgeFromLine(it.next())));
        }
        return arrayList;
    }

    private List<BridgeRouter> getCandidates(Set<Router> set) {
        if (this.bridgeRouters.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(this.bridgeRouters.size());
        for (BridgeRouterImpl bridgeRouterImpl : this.bridgeRouters) {
            if (!set.contains(bridgeRouterImpl)) {
                arrayList.add(bridgeRouterImpl);
            }
        }
        return arrayList;
    }

    private boolean hasCandidates(Set<Router> set) {
        return !getCandidates(set).isEmpty();
    }

    private void initializeBridges() {
        logger.fine("Initializing bridges...");
        synchronized (this.lock) {
            if (!this.bridgesInitializing && !this.bridgesInitialized) {
                if (this.directoryDownloader == null) {
                    throw new IllegalStateException("Cannot download bridge descriptors because DirectoryDownload instance not initialized");
                }
                this.bridgesInitializing = true;
                startAllDownloadTasks();
            }
        }
    }

    private void startAllDownloadTasks() {
        List<Runnable> createDownloadTasks = createDownloadTasks();
        this.outstandingDownloadTasks.set(createDownloadTasks.size());
        Iterator<Runnable> it = createDownloadTasks.iterator();
        while (it.hasNext()) {
            new Thread(it.next()).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BridgeRouter chooseRandomBridge(Set<Router> set) throws InterruptedException {
        synchronized (this.lock) {
            if (!this.bridgesInitialized && !this.bridgesInitializing) {
                initializeBridges();
            }
            while (!this.bridgesInitialized && !hasCandidates(set)) {
                this.lock.wait();
            }
            List<BridgeRouter> candidates = getCandidates(set);
            if (candidates.isEmpty()) {
                logger.warning("Bridges enabled but no usable bridges configured");
                return null;
            }
            return candidates.get(this.random.nextInt(candidates.size()));
        }
    }
}
