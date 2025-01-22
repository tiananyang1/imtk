package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.DirectoryDownloader;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.Threading;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.crypto.TorRandom;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/DirectoryDownloadTask.class */
public class DirectoryDownloadTask implements Runnable {
    private static final Logger logger = Logger.getLogger(DirectoryDownloadTask.class.getName());
    private final TorConfig config;
    private Date consensusDownloadTime;
    private ConsensusDocument currentConsensus;
    private final DescriptorProcessor descriptorProcessor;
    private final Directory directory;
    private final DirectoryDownloader downloader;
    private volatile boolean isDownloadingCertificates;
    private volatile boolean isDownloadingConsensus;
    private volatile boolean isStopped;
    private final ExecutorService executor = Threading.newPool("DirectoryDownloadTask worker");
    private final TorRandom random = new TorRandom();
    private final AtomicInteger outstandingDescriptorTasks = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/DirectoryDownloadTask$DownloadCertificatesTask.class */
    public class DownloadCertificatesTask implements Runnable {
        private DownloadCertificatesTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    Iterator<KeyCertificate> it = DirectoryDownloadTask.this.downloader.downloadKeyCertificates(DirectoryDownloadTask.this.directory.getRequiredCertificates()).iterator();
                    while (it.hasNext()) {
                        DirectoryDownloadTask.this.directory.addCertificate(it.next());
                    }
                    DirectoryDownloadTask.this.directory.storeCertificates();
                } catch (DirectoryRequestFailedException e) {
                    DirectoryDownloadTask.logger.warning("Failed to download key certificates: " + e.getMessage());
                }
            } finally {
                DirectoryDownloadTask.this.isDownloadingCertificates = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/DirectoryDownloadTask$DownloadConsensusTask.class */
    public class DownloadConsensusTask implements Runnable {
        private DownloadConsensusTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    ConsensusDocument downloadCurrentConsensus = DirectoryDownloadTask.this.downloader.downloadCurrentConsensus(DirectoryDownloadTask.this.useMicrodescriptors());
                    DirectoryDownloadTask.this.setCurrentConsensus(downloadCurrentConsensus);
                    DirectoryDownloadTask.this.directory.addConsensusDocument(downloadCurrentConsensus, false);
                } catch (DirectoryRequestFailedException e) {
                    DirectoryDownloadTask.logger.warning("Failed to download current consensus document: " + e.getMessage());
                }
            } finally {
                DirectoryDownloadTask.this.isDownloadingConsensus = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/DirectoryDownloadTask$DownloadRouterDescriptorsTask.class */
    public class DownloadRouterDescriptorsTask implements Runnable {
        private final Set<HexDigest> fingerprints;
        private final boolean useMicrodescriptors;

        public DownloadRouterDescriptorsTask(Collection<HexDigest> collection, boolean z) {
            this.fingerprints = new HashSet(collection);
            this.useMicrodescriptors = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    if (this.useMicrodescriptors) {
                        DirectoryDownloadTask.this.directory.addRouterMicrodescriptors(DirectoryDownloadTask.this.downloader.downloadRouterMicrodescriptors(this.fingerprints));
                    } else {
                        DirectoryDownloadTask.this.directory.addRouterDescriptors(DirectoryDownloadTask.this.downloader.downloadRouterDescriptors(this.fingerprints));
                    }
                } catch (DirectoryRequestFailedException e) {
                    DirectoryDownloadTask.logger.warning("Failed to download router descriptors: " + e.getMessage());
                }
            } finally {
                DirectoryDownloadTask.this.outstandingDescriptorTasks.decrementAndGet();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectoryDownloadTask(TorConfig torConfig, Directory directory, DirectoryDownloader directoryDownloader) {
        this.config = torConfig;
        this.directory = directory;
        this.downloader = directoryDownloader;
        this.descriptorProcessor = new DescriptorProcessor(torConfig, directory);
    }

    private void checkCertificates() {
        if (this.isDownloadingCertificates || this.directory.getRequiredCertificates().isEmpty()) {
            return;
        }
        this.isDownloadingCertificates = true;
        this.executor.execute(new DownloadCertificatesTask());
    }

    private void checkConsensus() {
        if (this.isDownloadingConsensus || !needConsensusDownload()) {
            return;
        }
        this.isDownloadingConsensus = true;
        this.executor.execute(new DownloadConsensusTask());
    }

    private void checkDescriptors() {
        if (this.outstandingDescriptorTasks.get() > 0) {
            return;
        }
        List<List<HexDigest>> descriptorDigestsToDownload = this.descriptorProcessor.getDescriptorDigestsToDownload();
        if (descriptorDigestsToDownload.isEmpty()) {
            return;
        }
        for (List<HexDigest> list : descriptorDigestsToDownload) {
            this.outstandingDescriptorTasks.incrementAndGet();
            this.executor.execute(new DownloadRouterDescriptorsTask(list, useMicrodescriptors()));
        }
    }

    private Date chooseDownloadTimeForConsensus(ConsensusDocument consensusDocument) {
        long milliseconds = getMilliseconds(consensusDocument.getValidAfterTime());
        long milliseconds2 = getMilliseconds(consensusDocument.getFreshUntilTime());
        long milliseconds3 = getMilliseconds(consensusDocument.getValidUntilTime());
        long j = milliseconds2 + (((milliseconds2 - milliseconds) * 3) / 4);
        return new Date(j + this.random.nextLong(((milliseconds3 - j) * 7) / 8));
    }

    private long getMilliseconds(Timestamp timestamp) {
        return timestamp.getDate().getTime();
    }

    private boolean needConsensusDownload() {
        if (this.directory.hasPendingConsensus()) {
            return false;
        }
        ConsensusDocument consensusDocument = this.currentConsensus;
        if (consensusDocument != null && consensusDocument.isLive()) {
            return this.consensusDownloadTime.before(new Date());
        }
        if (this.currentConsensus == null) {
            logger.info("Downloading consensus because we have no consensus document");
            return true;
        }
        logger.info("Downloading consensus because the document we have is not live");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean useMicrodescriptors() {
        return this.config.getUseMicrodescriptors() != TorConfig.AutoBoolValue.FALSE;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.directory.loadFromStore();
        this.directory.waitUntilLoaded();
        setCurrentConsensus(this.directory.getCurrentConsensusDocument());
        while (!this.isStopped) {
            checkCertificates();
            checkConsensus();
            checkDescriptors();
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    void setCurrentConsensus(ConsensusDocument consensusDocument) {
        if (consensusDocument != null) {
            this.currentConsensus = consensusDocument;
            this.consensusDownloadTime = chooseDownloadTimeForConsensus(consensusDocument);
        } else {
            this.currentConsensus = null;
            this.consensusDownloadTime = null;
        }
    }

    public void stop() {
        synchronized (this) {
            if (this.isStopped) {
                return;
            }
            this.executor.shutdownNow();
            this.isStopped = true;
        }
    }
}
