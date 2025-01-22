package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.DirectoryCircuit;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.RouterMicrodescriptor;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.circuits.TorInitializationTracker;
import com.subgraph.orchid.data.HexDigest;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/DirectoryDocumentRequestor.class */
public class DirectoryDocumentRequestor {
    private static final int OPEN_DIRECTORY_STREAM_TIMEOUT = 10000;
    private final DirectoryCircuit circuit;
    private final TorInitializationTracker initializationTracker;

    public DirectoryDocumentRequestor(DirectoryCircuit directoryCircuit) {
        this(directoryCircuit, null);
    }

    public DirectoryDocumentRequestor(DirectoryCircuit directoryCircuit, TorInitializationTracker torInitializationTracker) {
        this.circuit = directoryCircuit;
        this.initializationTracker = torInitializationTracker;
    }

    private HttpConnection createHttpConnection(int i) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        return new HttpConnection(openDirectoryStream(i));
    }

    private <T> List<T> fetchDocuments(DocumentFetcher<T> documentFetcher, int i) throws DirectoryRequestFailedException {
        try {
            try {
                HttpConnection createHttpConnection = createHttpConnection(i);
                try {
                    return documentFetcher.requestDocuments(createHttpConnection);
                } finally {
                    createHttpConnection.close();
                }
            } catch (InterruptedException e) {
                throw new DirectoryRequestFailedException("Directory request interrupted");
            }
        } catch (StreamConnectFailedException e2) {
            throw new DirectoryRequestFailedException("Failed to open directory stream", e2);
        } catch (IOException e3) {
            throw new DirectoryRequestFailedException("I/O exception processing directory request", e3);
        } catch (TimeoutException e4) {
            throw new DirectoryRequestFailedException("Directory request timed out");
        }
    }

    private <T> T fetchSingleDocument(DocumentFetcher<T> documentFetcher) throws DirectoryRequestFailedException {
        return (T) fetchSingleDocument(documentFetcher, 0);
    }

    private <T> T fetchSingleDocument(DocumentFetcher<T> documentFetcher, int i) throws DirectoryRequestFailedException {
        List<T> fetchDocuments = fetchDocuments(documentFetcher, i);
        if (fetchDocuments.size() == 1) {
            return fetchDocuments.get(0);
        }
        return null;
    }

    private void notifyInitialization(int i) {
        TorInitializationTracker torInitializationTracker;
        if (i <= 0 || (torInitializationTracker = this.initializationTracker) == null) {
            return;
        }
        torInitializationTracker.notifyEvent(i);
    }

    private Stream openDirectoryStream(int i) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        int purposeToEventCode = purposeToEventCode(i, false);
        int purposeToEventCode2 = purposeToEventCode(i, true);
        notifyInitialization(purposeToEventCode);
        Stream openDirectoryStream = this.circuit.openDirectoryStream(10000L, true);
        notifyInitialization(purposeToEventCode2);
        return openDirectoryStream;
    }

    private int purposeToEventCode(int i, boolean z) {
        if (i == 1) {
            return z ? 25 : 20;
        }
        if (i == 2) {
            return z ? 40 : 35;
        }
        if (i != 3) {
            return 0;
        }
        return z ? 50 : 45;
    }

    public RouterDescriptor downloadBridgeDescriptor(Router router) throws DirectoryRequestFailedException {
        return (RouterDescriptor) fetchSingleDocument(new BridgeDescriptorFetcher());
    }

    public ConsensusDocument downloadCurrentConsensus(boolean z) throws DirectoryRequestFailedException {
        return (ConsensusDocument) fetchSingleDocument(new ConsensusFetcher(z), 1);
    }

    public List<KeyCertificate> downloadKeyCertificates(Set<ConsensusDocument.RequiredCertificate> set) throws DirectoryRequestFailedException {
        return fetchDocuments(new CertificateFetcher(set), 2);
    }

    public List<RouterDescriptor> downloadRouterDescriptors(Set<HexDigest> set) throws DirectoryRequestFailedException {
        return fetchDocuments(new RouterDescriptorFetcher(set), 3);
    }

    public List<RouterMicrodescriptor> downloadRouterMicrodescriptors(Set<HexDigest> set) throws DirectoryRequestFailedException {
        return fetchDocuments(new MicrodescriptorFetcher(set), 3);
    }
}
