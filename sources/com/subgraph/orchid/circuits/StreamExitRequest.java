package com.subgraph.orchid.circuits;

import com.subgraph.orchid.OpenFailedException;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.exitpolicy.ExitTarget;
import com.subgraph.orchid.misc.GuardedBy;
import com.xiaomi.mipush.sdk.Constants;
import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/StreamExitRequest.class */
public class StreamExitRequest implements ExitTarget {
    private final IPv4Address address;

    @GuardedBy("requestCompletionLock")
    private CompletionStatus completionStatus;
    private final String hostname;
    private final boolean isAddress;

    @GuardedBy("this")
    private boolean isReserved;
    private final int port;
    private final Object requestCompletionLock;

    @GuardedBy("this")
    private int retryCount;

    @GuardedBy("this")
    private long specificTimeout;

    @GuardedBy("requestCompletionLock")
    private Stream stream;

    @GuardedBy("requestCompletionLock")
    private int streamOpenFailReason;

    /* renamed from: com.subgraph.orchid.circuits.StreamExitRequest$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/StreamExitRequest$1.class */
    static /* synthetic */ class C03191 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$circuits$StreamExitRequest$CompletionStatus */
        static final /* synthetic */ int[] f187xea4500d = new int[CompletionStatus.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:36:0x005d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.circuits.StreamExitRequest$CompletionStatus[] r0 = com.subgraph.orchid.circuits.StreamExitRequest.CompletionStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.circuits.StreamExitRequest.C03191.f187xea4500d = r0
                int[] r0 = com.subgraph.orchid.circuits.StreamExitRequest.C03191.f187xea4500d     // Catch: java.lang.NoSuchFieldError -> L4d
                com.subgraph.orchid.circuits.StreamExitRequest$CompletionStatus r1 = com.subgraph.orchid.circuits.StreamExitRequest.CompletionStatus.NOT_COMPLETED     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L14:
                int[] r0 = com.subgraph.orchid.circuits.StreamExitRequest.C03191.f187xea4500d     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.subgraph.orchid.circuits.StreamExitRequest$CompletionStatus r1 = com.subgraph.orchid.circuits.StreamExitRequest.CompletionStatus.EXIT_FAILURE     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
            L1f:
                int[] r0 = com.subgraph.orchid.circuits.StreamExitRequest.C03191.f187xea4500d     // Catch: java.lang.NoSuchFieldError -> L51 java.lang.NoSuchFieldError -> L55
                com.subgraph.orchid.circuits.StreamExitRequest$CompletionStatus r1 = com.subgraph.orchid.circuits.StreamExitRequest.CompletionStatus.TIMEOUT     // Catch: java.lang.NoSuchFieldError -> L55
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L55
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L55
            L2a:
                int[] r0 = com.subgraph.orchid.circuits.StreamExitRequest.C03191.f187xea4500d     // Catch: java.lang.NoSuchFieldError -> L55 java.lang.NoSuchFieldError -> L59
                com.subgraph.orchid.circuits.StreamExitRequest$CompletionStatus r1 = com.subgraph.orchid.circuits.StreamExitRequest.CompletionStatus.STREAM_OPEN_FAILURE     // Catch: java.lang.NoSuchFieldError -> L59
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L59
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L59
            L35:
                int[] r0 = com.subgraph.orchid.circuits.StreamExitRequest.C03191.f187xea4500d     // Catch: java.lang.NoSuchFieldError -> L59 java.lang.NoSuchFieldError -> L5d
                com.subgraph.orchid.circuits.StreamExitRequest$CompletionStatus r1 = com.subgraph.orchid.circuits.StreamExitRequest.CompletionStatus.INTERRUPTED     // Catch: java.lang.NoSuchFieldError -> L5d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L5d
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L5d
            L40:
                int[] r0 = com.subgraph.orchid.circuits.StreamExitRequest.C03191.f187xea4500d     // Catch: java.lang.NoSuchFieldError -> L5d java.lang.NoSuchFieldError -> L61
                com.subgraph.orchid.circuits.StreamExitRequest$CompletionStatus r1 = com.subgraph.orchid.circuits.StreamExitRequest.CompletionStatus.SUCCESS     // Catch: java.lang.NoSuchFieldError -> L61
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L61
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L61
                return
            L4d:
                r4 = move-exception
                goto L14
            L51:
                r4 = move-exception
                goto L1f
            L55:
                r4 = move-exception
                goto L2a
            L59:
                r4 = move-exception
                goto L35
            L5d:
                r4 = move-exception
                goto L40
            L61:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.StreamExitRequest.C03191.m3811clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/StreamExitRequest$CompletionStatus.class */
    public enum CompletionStatus {
        NOT_COMPLETED,
        SUCCESS,
        TIMEOUT,
        STREAM_OPEN_FAILURE,
        EXIT_FAILURE,
        INTERRUPTED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamExitRequest(Object obj, IPv4Address iPv4Address, int i) {
        this(obj, true, "", iPv4Address, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamExitRequest(Object obj, String str, int i) {
        this(obj, false, str, null, i);
    }

    private StreamExitRequest(Object obj, boolean z, String str, IPv4Address iPv4Address, int i) {
        this.requestCompletionLock = obj;
        this.isAddress = z;
        this.hostname = str;
        this.address = iPv4Address;
        this.port = i;
        this.completionStatus = CompletionStatus.NOT_COMPLETED;
    }

    private void newStatus(CompletionStatus completionStatus) {
        if (this.completionStatus == CompletionStatus.NOT_COMPLETED) {
            this.completionStatus = completionStatus;
            this.requestCompletionLock.notifyAll();
            return;
        }
        throw new IllegalStateException("Attempt to set completion state to " + completionStatus + " while status is " + this.completionStatus);
    }

    @Override // com.subgraph.orchid.data.exitpolicy.ExitTarget
    public IPv4Address getAddress() {
        return this.address;
    }

    @Override // com.subgraph.orchid.data.exitpolicy.ExitTarget
    public String getHostname() {
        return this.hostname;
    }

    @Override // com.subgraph.orchid.data.exitpolicy.ExitTarget
    public int getPort() {
        return this.port;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Stream getStream() throws OpenFailedException, TimeoutException, StreamConnectFailedException, InterruptedException {
        Stream stream;
        synchronized (this.requestCompletionLock) {
            switch (C03191.f187xea4500d[this.completionStatus.ordinal()]) {
                case 1:
                    throw new IllegalStateException("Request not completed");
                case 2:
                    throw new OpenFailedException("Failure at exit node");
                case 3:
                    throw new TimeoutException();
                case 4:
                    throw new StreamConnectFailedException(this.streamOpenFailReason);
                case 5:
                    throw new InterruptedException();
                case 6:
                    stream = this.stream;
                    break;
                default:
                    throw new IllegalStateException("Unknown completion status");
            }
        }
        return stream;
    }

    public long getStreamTimeout() {
        synchronized (this) {
            if (this.specificTimeout > 0) {
                return this.specificTimeout;
            }
            return this.retryCount < 2 ? 10000L : 15000L;
        }
    }

    @Override // com.subgraph.orchid.data.exitpolicy.ExitTarget
    public boolean isAddressTarget() {
        return this.isAddress;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCompleted() {
        boolean z;
        synchronized (this.requestCompletionLock) {
            z = this.completionStatus != CompletionStatus.NOT_COMPLETED;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isReserved() {
        boolean z;
        synchronized (this) {
            z = this.isReserved;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean reserveRequest() {
        synchronized (this) {
            if (this.isReserved) {
                return false;
            }
            this.isReserved = true;
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetForRetry() {
        synchronized (this) {
            synchronized (this.requestCompletionLock) {
                this.streamOpenFailReason = 0;
                this.completionStatus = CompletionStatus.NOT_COMPLETED;
            }
            this.retryCount++;
            this.isReserved = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCompletedSuccessfully(Stream stream) {
        synchronized (this.requestCompletionLock) {
            this.stream = stream;
            newStatus(CompletionStatus.SUCCESS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCompletedTimeout() {
        synchronized (this.requestCompletionLock) {
            newStatus(CompletionStatus.TIMEOUT);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setExitFailed() {
        synchronized (this.requestCompletionLock) {
            newStatus(CompletionStatus.EXIT_FAILURE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setInterrupted() {
        synchronized (this.requestCompletionLock) {
            newStatus(CompletionStatus.INTERRUPTED);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStreamOpenFailure(int i) {
        synchronized (this.requestCompletionLock) {
            this.streamOpenFailReason = i;
            newStatus(CompletionStatus.STREAM_OPEN_FAILURE);
        }
    }

    public void setStreamTimeout(long j) {
        synchronized (this) {
            this.specificTimeout = j;
        }
    }

    public String toString() {
        if (this.isAddress) {
            return this.address + Constants.COLON_SEPARATOR + this.port;
        }
        return this.hostname + Constants.COLON_SEPARATOR + this.port;
    }
}
