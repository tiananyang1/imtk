package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.CircuitBuildHandler;
import com.subgraph.orchid.CircuitManager;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.Connection;
import com.subgraph.orchid.ConnectionCache;
import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.DirectoryCircuit;
import com.subgraph.orchid.ExitCircuit;
import com.subgraph.orchid.InternalCircuit;
import com.subgraph.orchid.OpenFailedException;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.Threading;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.circuits.guards.EntryGuards;
import com.subgraph.orchid.circuits.p002hs.HiddenServiceManager;
import com.subgraph.orchid.circuits.path.CircuitPathChooser;
import com.subgraph.orchid.crypto.TorRandom;
import com.subgraph.orchid.dashboard.DashboardRenderable;
import com.subgraph.orchid.dashboard.DashboardRenderer;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.directory.downloader.DirectoryDownloaderImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitManagerImpl.class */
public class CircuitManagerImpl implements CircuitManager, DashboardRenderable {
    private static final int OPEN_DIRECTORY_STREAM_RETRY_COUNT = 5;
    private static final int OPEN_DIRECTORY_STREAM_TIMEOUT = 10000;
    private final Set<CircuitImpl> activeCircuits;
    private final CircuitCreationTask circuitCreationTask;
    private final Queue<InternalCircuit> cleanInternalCircuits;
    private final TorConfig config;
    private final ConnectionCache connectionCache;
    private final Directory directory;
    private final HiddenServiceManager hiddenServiceManager;
    private final TorInitializationTracker initializationTracker;
    private final CircuitPathChooser pathChooser;
    private final PendingExitStreams pendingExitStreams;
    private final TorRandom random;
    private int requestedInternalCircuitCount = 0;
    private int pendingInternalCircuitCount = 0;
    private final ScheduledExecutorService scheduledExecutor = Threading.newSingleThreadScheduledPool("CircuitManager worker");
    private final ReentrantLock lock = Threading.lock("circuitManager");
    private boolean isBuilding = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.circuits.CircuitManagerImpl$3 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitManagerImpl$3.class */
    public static /* synthetic */ class C03173 {
        static final /* synthetic */ int[] $SwitchMap$com$subgraph$orchid$TorConfig$AutoBoolValue = new int[TorConfig.AutoBoolValue.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.TorConfig$AutoBoolValue[] r0 = com.subgraph.orchid.TorConfig.AutoBoolValue.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.circuits.CircuitManagerImpl.C03173.$SwitchMap$com$subgraph$orchid$TorConfig$AutoBoolValue = r0
                int[] r0 = com.subgraph.orchid.circuits.CircuitManagerImpl.C03173.$SwitchMap$com$subgraph$orchid$TorConfig$AutoBoolValue     // Catch: java.lang.NoSuchFieldError -> L2b
                com.subgraph.orchid.TorConfig$AutoBoolValue r1 = com.subgraph.orchid.TorConfig.AutoBoolValue.AUTO     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.subgraph.orchid.circuits.CircuitManagerImpl.C03173.$SwitchMap$com$subgraph$orchid$TorConfig$AutoBoolValue     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.subgraph.orchid.TorConfig$AutoBoolValue r1 = com.subgraph.orchid.TorConfig.AutoBoolValue.FALSE     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.subgraph.orchid.circuits.CircuitManagerImpl.C03173.$SwitchMap$com$subgraph$orchid$TorConfig$AutoBoolValue     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.subgraph.orchid.TorConfig$AutoBoolValue r1 = com.subgraph.orchid.TorConfig.AutoBoolValue.TRUE     // Catch: java.lang.NoSuchFieldError -> L33
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L33
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L33
                return
            L2b:
                r4 = move-exception
                goto L14
            L2f:
                r4 = move-exception
                goto L1f
            L33:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.CircuitManagerImpl.C03173.m3804clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitManagerImpl$CircuitFilter.class */
    public interface CircuitFilter {
        boolean filter(Circuit circuit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitManagerImpl$DirectoryCircuitResult.class */
    public static class DirectoryCircuitResult implements CircuitBuildHandler {
        private boolean isFailed;

        private DirectoryCircuitResult() {
        }

        @Override // com.subgraph.orchid.CircuitBuildHandler
        public void circuitBuildCompleted(Circuit circuit) {
        }

        @Override // com.subgraph.orchid.CircuitBuildHandler
        public void circuitBuildFailed(String str) {
            this.isFailed = true;
        }

        @Override // com.subgraph.orchid.CircuitBuildHandler
        public void connectionCompleted(Connection connection) {
        }

        @Override // com.subgraph.orchid.CircuitBuildHandler
        public void connectionFailed(String str) {
            this.isFailed = true;
        }

        boolean isSuccessful() {
            return !this.isFailed;
        }

        @Override // com.subgraph.orchid.CircuitBuildHandler
        public void nodeAdded(CircuitNode circuitNode) {
        }
    }

    public CircuitManagerImpl(TorConfig torConfig, DirectoryDownloaderImpl directoryDownloaderImpl, Directory directory, ConnectionCache connectionCache, TorInitializationTracker torInitializationTracker) {
        this.config = torConfig;
        this.directory = directory;
        this.connectionCache = connectionCache;
        this.pathChooser = CircuitPathChooser.create(torConfig, directory);
        if (torConfig.getUseEntryGuards() || torConfig.getUseBridges()) {
            this.pathChooser.enableEntryGuards(new EntryGuards(torConfig, connectionCache, directoryDownloaderImpl, directory));
        }
        this.pendingExitStreams = new PendingExitStreams(torConfig);
        this.circuitCreationTask = new CircuitCreationTask(torConfig, directory, connectionCache, this.pathChooser, this, torInitializationTracker);
        this.activeCircuits = new HashSet();
        this.cleanInternalCircuits = new LinkedList();
        this.random = new TorRandom();
        this.initializationTracker = torInitializationTracker;
        this.hiddenServiceManager = new HiddenServiceManager(torConfig, directory, this);
        directoryDownloaderImpl.setCircuitManager(this);
    }

    private void maybeRejectInternalAddress(IPv4Address iPv4Address) throws OpenFailedException {
        if (iPv4Address.toInetAddress().isSiteLocalAddress() && this.config.getClientRejectInternalAddress()) {
            throw new OpenFailedException("Rejecting stream target with internal address: " + iPv4Address);
        }
    }

    private void maybeRejectInternalAddress(String str) throws OpenFailedException {
        if (IPv4Address.isValidIPv4AddressString(str)) {
            maybeRejectInternalAddress(IPv4Address.createFromString(str));
        }
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

    private boolean tryOpenCircuit(Circuit circuit, boolean z, boolean z2) {
        DirectoryCircuitResult directoryCircuitResult = new DirectoryCircuitResult();
        CircuitCreationRequest circuitCreationRequest = new CircuitCreationRequest(this.pathChooser, circuit, directoryCircuitResult, z);
        ConnectionCache connectionCache = this.connectionCache;
        boolean isNtorEnabled = isNtorEnabled();
        TorInitializationTracker torInitializationTracker = null;
        if (z2) {
            torInitializationTracker = this.initializationTracker;
        }
        new CircuitBuildTask(circuitCreationRequest, connectionCache, isNtorEnabled, torInitializationTracker).run();
        return directoryCircuitResult.isSuccessful();
    }

    private void validateHostname(String str) throws OpenFailedException {
        maybeRejectInternalAddress(str);
        if (str.toLowerCase().endsWith(".onion")) {
            throw new OpenFailedException("Hidden services not supported");
        }
        if (str.toLowerCase().endsWith(".exit")) {
            throw new OpenFailedException(".exit addresses are not supported");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addActiveCircuit(CircuitImpl circuitImpl) {
        synchronized (this.activeCircuits) {
            this.activeCircuits.add(circuitImpl);
            this.activeCircuits.notifyAll();
        }
        this.lock.lock();
        try {
            boolean z = this.isBuilding;
            this.lock.unlock();
            if (!z) {
                circuitImpl.destroyCircuit();
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCleanInternalCircuit(InternalCircuit internalCircuit) {
        synchronized (this.cleanInternalCircuits) {
            this.pendingInternalCircuitCount--;
            this.cleanInternalCircuits.add(internalCircuit);
            this.cleanInternalCircuits.notifyAll();
        }
    }

    public ExitCircuit createNewExitCircuit(Router router) {
        return CircuitImpl.createExitCircuit(this, router);
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderable
    public void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) throws IOException {
        if ((i & 8) == 0) {
            return;
        }
        dashboardRenderer.renderComponent(printWriter, i, this.connectionCache);
        dashboardRenderer.renderComponent(printWriter, i, this.circuitCreationTask.getCircuitPredictor());
        printWriter.println("[Circuit Manager]");
        printWriter.println();
        Iterator<Circuit> it = getCircuitsByFilter(null).iterator();
        while (it.hasNext()) {
            dashboardRenderer.renderComponent(printWriter, i, it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void decrementPendingInternalCircuitCount() {
        synchronized (this.cleanInternalCircuits) {
            this.pendingInternalCircuitCount--;
        }
    }

    int getActiveCircuitCount() {
        int size;
        synchronized (this.activeCircuits) {
            size = this.activeCircuits.size();
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<Circuit> getCircuitsByFilter(CircuitFilter circuitFilter) {
        HashSet hashSet = new HashSet();
        HashSet<CircuitImpl> hashSet2 = new HashSet();
        synchronized (this.activeCircuits) {
            hashSet2.addAll(this.activeCircuits);
        }
        for (CircuitImpl circuitImpl : hashSet2) {
            if (circuitFilter == null || circuitFilter.filter(circuitImpl)) {
                hashSet.add(circuitImpl);
            }
        }
        return hashSet;
    }

    @Override // com.subgraph.orchid.CircuitManager
    public InternalCircuit getCleanInternalCircuit() throws InterruptedException {
        InternalCircuit remove;
        synchronized (this.cleanInternalCircuits) {
            try {
                this.requestedInternalCircuitCount++;
                while (this.cleanInternalCircuits.isEmpty()) {
                    this.cleanInternalCircuits.wait();
                }
                remove = this.cleanInternalCircuits.remove();
            } finally {
                this.requestedInternalCircuitCount--;
            }
        }
        return remove;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNeededCleanCircuitCount(boolean z) {
        synchronized (this.cleanInternalCircuits) {
            int max = Math.max(this.requestedInternalCircuitCount, z ? 2 : 0) - (this.pendingInternalCircuitCount + this.cleanInternalCircuits.size());
            if (max < 0) {
                return 0;
            }
            return max;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPendingCircuitCount() {
        this.lock.lock();
        try {
            return getPendingCircuits().size();
        } finally {
            this.lock.unlock();
        }
    }

    Set<Circuit> getPendingCircuits() {
        return getCircuitsByFilter(new CircuitFilter() { // from class: com.subgraph.orchid.circuits.CircuitManagerImpl.1
            @Override // com.subgraph.orchid.circuits.CircuitManagerImpl.CircuitFilter
            public boolean filter(Circuit circuit) {
                return circuit.isPending();
            }
        });
    }

    public List<StreamExitRequest> getPendingExitStreams() {
        return this.pendingExitStreams.getUnreservedPendingRequests();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<ExitCircuit> getRandomlyOrderedListOfExitCircuits() {
        Set<Circuit> circuitsByFilter = getCircuitsByFilter(new CircuitFilter() { // from class: com.subgraph.orchid.circuits.CircuitManagerImpl.2
            @Override // com.subgraph.orchid.circuits.CircuitManagerImpl.CircuitFilter
            public boolean filter(Circuit circuit) {
                return (circuit instanceof ExitCircuit) && !circuit.isMarkedForClose() && circuit.isConnected();
            }
        });
        ArrayList arrayList = new ArrayList();
        for (Circuit circuit : circuitsByFilter) {
            if (circuit instanceof ExitCircuit) {
                arrayList.add((ExitCircuit) circuit);
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return arrayList;
            }
            ExitCircuit exitCircuit = (ExitCircuit) arrayList.get(i2);
            int nextInt = this.random.nextInt(size);
            arrayList.set(i2, arrayList.get(nextInt));
            arrayList.set(nextInt, exitCircuit);
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void incrementPendingInternalCircuitCount() {
        synchronized (this.cleanInternalCircuits) {
            this.pendingInternalCircuitCount++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isNtorEnabled() {
        int i = C03173.$SwitchMap$com$subgraph$orchid$TorConfig$AutoBoolValue[this.config.getUseNTorHandshake().ordinal()];
        if (i == 1) {
            return isNtorEnabledInConsensus();
        }
        if (i == 2) {
            return false;
        }
        if (i == 3) {
            return true;
        }
        throw new IllegalArgumentException("getUseNTorHandshake() returned " + this.config.getUseNTorHandshake());
    }

    boolean isNtorEnabledInConsensus() {
        ConsensusDocument currentConsensusDocument = this.directory.getCurrentConsensusDocument();
        return currentConsensusDocument != null && currentConsensusDocument.getUseNTorHandshake();
    }

    @Override // com.subgraph.orchid.CircuitManager
    public DirectoryCircuit openDirectoryCircuit() throws OpenFailedException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 5) {
                throw new OpenFailedException("Could not create circuit for directory stream");
            }
            DirectoryCircuit createDirectoryCircuit = CircuitImpl.createDirectoryCircuit(this);
            if (tryOpenCircuit(createDirectoryCircuit, true, true)) {
                return createDirectoryCircuit;
            }
            i = i2 + 1;
        }
    }

    @Override // com.subgraph.orchid.CircuitManager
    public DirectoryCircuit openDirectoryCircuitTo(List<Router> list) throws OpenFailedException {
        DirectoryCircuit createDirectoryCircuitTo = CircuitImpl.createDirectoryCircuitTo(this, list);
        if (tryOpenCircuit(createDirectoryCircuitTo, true, false)) {
            return createDirectoryCircuitTo;
        }
        throw new OpenFailedException("Could not create directory circuit for path");
    }

    @Override // com.subgraph.orchid.CircuitManager
    public Stream openDirectoryStream() throws OpenFailedException, InterruptedException, TimeoutException {
        return openDirectoryStream(0);
    }

    @Override // com.subgraph.orchid.CircuitManager
    public Stream openDirectoryStream(int i) throws OpenFailedException, InterruptedException {
        int purposeToEventCode = purposeToEventCode(i, false);
        int purposeToEventCode2 = purposeToEventCode(i, true);
        int i2 = 0;
        while (i2 < 5) {
            DirectoryCircuit openDirectoryCircuit = openDirectoryCircuit();
            if (purposeToEventCode > 0) {
                this.initializationTracker.notifyEvent(purposeToEventCode);
            }
            try {
                Stream openDirectoryStream = openDirectoryCircuit.openDirectoryStream(10000L, true);
                if (purposeToEventCode2 > 0) {
                    this.initializationTracker.notifyEvent(purposeToEventCode2);
                }
                return openDirectoryStream;
            } catch (StreamConnectFailedException e) {
                openDirectoryCircuit.markForClose();
                i2++;
            } catch (TimeoutException e2) {
                openDirectoryCircuit.markForClose();
            }
        }
        throw new OpenFailedException("Retry count exceeded opening directory stream");
    }

    @Override // com.subgraph.orchid.CircuitManager
    public ExitCircuit openExitCircuitTo(List<Router> list) throws OpenFailedException {
        ExitCircuit createExitCircuitTo = CircuitImpl.createExitCircuitTo(this, list);
        if (tryOpenCircuit(createExitCircuitTo, false, false)) {
            return createExitCircuitTo;
        }
        throw new OpenFailedException("Could not create exit circuit for path");
    }

    @Override // com.subgraph.orchid.CircuitManager
    public Stream openExitStreamTo(IPv4Address iPv4Address, int i) throws InterruptedException, TimeoutException, OpenFailedException {
        maybeRejectInternalAddress(iPv4Address);
        this.circuitCreationTask.predictPort(i);
        return this.pendingExitStreams.openExitStream(iPv4Address, i);
    }

    @Override // com.subgraph.orchid.CircuitManager
    public Stream openExitStreamTo(String str, int i) throws InterruptedException, TimeoutException, OpenFailedException {
        if (str.endsWith(".onion")) {
            return this.hiddenServiceManager.getStreamTo(str, i);
        }
        validateHostname(str);
        this.circuitCreationTask.predictPort(i);
        return this.pendingExitStreams.openExitStream(str, i);
    }

    @Override // com.subgraph.orchid.CircuitManager
    public InternalCircuit openInternalCircuitTo(List<Router> list) throws OpenFailedException {
        InternalCircuit createInternalCircuitTo = CircuitImpl.createInternalCircuitTo(this, list);
        if (tryOpenCircuit(createInternalCircuitTo, false, false)) {
            return createInternalCircuitTo;
        }
        throw new OpenFailedException("Could not create internal circuit for path");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeActiveCircuit(CircuitImpl circuitImpl) {
        synchronized (this.activeCircuits) {
            this.activeCircuits.remove(circuitImpl);
        }
    }

    @Override // com.subgraph.orchid.CircuitManager
    public void startBuildingCircuits() {
        this.lock.lock();
        try {
            this.isBuilding = true;
            this.scheduledExecutor.scheduleAtFixedRate(this.circuitCreationTask, 0L, 1000L, TimeUnit.MILLISECONDS);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.subgraph.orchid.CircuitManager
    public void stopBuildingCircuits(boolean z) {
        ArrayList arrayList;
        this.lock.lock();
        try {
            this.isBuilding = false;
            this.scheduledExecutor.shutdownNow();
            if (z) {
                synchronized (this.activeCircuits) {
                    arrayList = new ArrayList(this.activeCircuits);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((CircuitImpl) it.next()).destroyCircuit();
                }
            }
        } finally {
            this.lock.unlock();
        }
    }
}
