package com.subgraph.orchid.circuits;

import com.subgraph.orchid.OpenFailedException;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.data.IPv4Address;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/PendingExitStreams.class */
public class PendingExitStreams {
    private final TorConfig config;
    private final Object lock = new Object();
    private final Set<StreamExitRequest> pendingRequests = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public PendingExitStreams(TorConfig torConfig) {
        this.config = torConfig;
    }

    private Stream handleRequest(StreamExitRequest streamExitRequest) throws InterruptedException, OpenFailedException {
        while (true) {
            if (streamExitRequest.isCompleted()) {
                try {
                    return streamExitRequest.getStream();
                } catch (StreamConnectFailedException e) {
                    streamExitRequest.resetForRetry();
                } catch (TimeoutException e2) {
                    streamExitRequest.resetForRetry();
                }
            } else {
                this.lock.wait();
            }
        }
    }

    private Stream openExitStreamByRequest(StreamExitRequest streamExitRequest) throws InterruptedException, OpenFailedException {
        Stream handleRequest;
        if (this.config.getCircuitStreamTimeout() != 0) {
            streamExitRequest.setStreamTimeout(this.config.getCircuitStreamTimeout());
        }
        synchronized (this.lock) {
            this.pendingRequests.add(streamExitRequest);
            try {
                handleRequest = handleRequest(streamExitRequest);
            } finally {
                this.pendingRequests.remove(streamExitRequest);
            }
        }
        return handleRequest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<StreamExitRequest> getUnreservedPendingRequests() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.lock) {
            for (StreamExitRequest streamExitRequest : this.pendingRequests) {
                if (!streamExitRequest.isReserved()) {
                    arrayList.add(streamExitRequest);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Stream openExitStream(IPv4Address iPv4Address, int i) throws InterruptedException, OpenFailedException {
        return openExitStreamByRequest(new StreamExitRequest(this.lock, iPv4Address, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Stream openExitStream(String str, int i) throws InterruptedException, OpenFailedException {
        return openExitStreamByRequest(new StreamExitRequest(this.lock, str, i));
    }
}
