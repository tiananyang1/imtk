package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.CircuitManager;
import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.Descriptor;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.DirectoryCircuit;
import com.subgraph.orchid.DirectoryDownloader;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.OpenFailedException;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.RouterMicrodescriptor;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.circuits.TorInitializationTracker;
import com.subgraph.orchid.data.HexDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/DirectoryDownloaderImpl.class */
public class DirectoryDownloaderImpl implements DirectoryDownloader {
    private static final Logger logger = Logger.getLogger(DirectoryDownloaderImpl.class.getName());
    private CircuitManager circuitManager;
    private final TorConfig config;
    private DirectoryDownloadTask downloadTask;
    private Thread downloadTaskThread;
    private final TorInitializationTracker initializationTracker;
    private boolean isStarted;
    private boolean isStopped;

    public DirectoryDownloaderImpl(TorConfig torConfig, TorInitializationTracker torInitializationTracker) {
        this.config = torConfig;
        this.initializationTracker = torInitializationTracker;
    }

    private DirectoryCircuit openBridgeCircuit(Router router) throws DirectoryRequestFailedException {
        try {
            return this.circuitManager.openDirectoryCircuitTo(Arrays.asList(router));
        } catch (OpenFailedException e) {
            throw new DirectoryRequestFailedException("Failed to open directory circuit to bridge " + router, e);
        }
    }

    private DirectoryCircuit openCircuit() throws DirectoryRequestFailedException {
        try {
            return this.circuitManager.openDirectoryCircuit();
        } catch (OpenFailedException e) {
            throw new DirectoryRequestFailedException("Failed to open directory circuit", e);
        }
    }

    private <T extends Descriptor> List<T> removeUnrequestedDescriptors(Set<HexDigest> set, List<T> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (T t : list) {
            if (set.contains(t.getDescriptorDigest())) {
                arrayList.add(t);
            } else {
                i++;
            }
        }
        if (i > 0) {
            logger.warning("Discarding " + i + " received descriptor(s) with fingerprints that did not match requested descriptors");
        }
        return arrayList;
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public RouterDescriptor downloadBridgeDescriptor(Router router) throws DirectoryRequestFailedException {
        return new DirectoryDocumentRequestor(openBridgeCircuit(router)).downloadBridgeDescriptor(router);
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public ConsensusDocument downloadCurrentConsensus(boolean z) throws DirectoryRequestFailedException {
        return downloadCurrentConsensus(z, openCircuit());
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public ConsensusDocument downloadCurrentConsensus(boolean z, DirectoryCircuit directoryCircuit) throws DirectoryRequestFailedException {
        return new DirectoryDocumentRequestor(directoryCircuit, this.initializationTracker).downloadCurrentConsensus(z);
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public List<KeyCertificate> downloadKeyCertificates(Set<ConsensusDocument.RequiredCertificate> set) throws DirectoryRequestFailedException {
        return downloadKeyCertificates(set, openCircuit());
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public List<KeyCertificate> downloadKeyCertificates(Set<ConsensusDocument.RequiredCertificate> set, DirectoryCircuit directoryCircuit) throws DirectoryRequestFailedException {
        return new DirectoryDocumentRequestor(directoryCircuit, this.initializationTracker).downloadKeyCertificates(set);
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public List<RouterDescriptor> downloadRouterDescriptors(Set<HexDigest> set) throws DirectoryRequestFailedException {
        return downloadRouterDescriptors(set, openCircuit());
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public List<RouterDescriptor> downloadRouterDescriptors(Set<HexDigest> set, DirectoryCircuit directoryCircuit) throws DirectoryRequestFailedException {
        return removeUnrequestedDescriptors(set, new DirectoryDocumentRequestor(directoryCircuit, this.initializationTracker).downloadRouterDescriptors(set));
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public List<RouterMicrodescriptor> downloadRouterMicrodescriptors(Set<HexDigest> set) throws DirectoryRequestFailedException {
        return downloadRouterMicrodescriptors(set, openCircuit());
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public List<RouterMicrodescriptor> downloadRouterMicrodescriptors(Set<HexDigest> set, DirectoryCircuit directoryCircuit) throws DirectoryRequestFailedException {
        return removeUnrequestedDescriptors(set, new DirectoryDocumentRequestor(directoryCircuit, this.initializationTracker).downloadRouterMicrodescriptors(set));
    }

    public void setCircuitManager(CircuitManager circuitManager) {
        this.circuitManager = circuitManager;
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public void start(Directory directory) {
        synchronized (this) {
            if (this.isStarted) {
                logger.warning("Directory downloader already running");
            } else {
                if (this.circuitManager == null) {
                    throw new IllegalStateException("Must set CircuitManager instance with setCircuitManager() before starting.");
                }
                this.downloadTask = new DirectoryDownloadTask(this.config, directory, this);
                this.downloadTaskThread = new Thread(this.downloadTask);
                this.downloadTaskThread.start();
                this.isStarted = true;
            }
        }
    }

    @Override // com.subgraph.orchid.DirectoryDownloader
    public void stop() {
        synchronized (this) {
            if (this.isStarted && !this.isStopped) {
                this.downloadTask.stop();
                this.downloadTaskThread.interrupt();
            }
        }
    }
}
