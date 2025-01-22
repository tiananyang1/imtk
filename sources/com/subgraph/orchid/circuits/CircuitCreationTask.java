package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.CircuitBuildHandler;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.Connection;
import com.subgraph.orchid.ConnectionCache;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.ExitCircuit;
import com.subgraph.orchid.InternalCircuit;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Threading;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.circuits.CircuitManagerImpl;
import com.subgraph.orchid.circuits.path.CircuitPathChooser;
import com.subgraph.orchid.data.exitpolicy.ExitTarget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitCreationTask.class */
public class CircuitCreationTask implements Runnable {
    private static final int MAX_CIRCUIT_DIRTINESS = 300;
    private static final int MAX_PENDING_CIRCUITS = 4;
    private static final Logger logger = Logger.getLogger(CircuitCreationTask.class.getName());
    private final CircuitManagerImpl circuitManager;
    private final TorConfig config;
    private final ConnectionCache connectionCache;
    private final Directory directory;
    private final TorInitializationTracker initializationTracker;
    private final CircuitPathChooser pathChooser;
    private int notEnoughDirectoryInformationWarningCounter = 0;
    private final Executor executor = Threading.newPool("CircuitCreationTask worker");
    private final CircuitBuildHandler buildHandler = createCircuitBuildHandler();
    private final CircuitBuildHandler internalBuildHandler = createInternalCircuitBuildHandler();
    private final CircuitPredictor predictor = new CircuitPredictor();
    private final AtomicLong lastNewCircuit = new AtomicLong();

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitCreationTask(TorConfig torConfig, Directory directory, ConnectionCache connectionCache, CircuitPathChooser circuitPathChooser, CircuitManagerImpl circuitManagerImpl, TorInitializationTracker torInitializationTracker) {
        this.config = torConfig;
        this.directory = directory;
        this.connectionCache = connectionCache;
        this.circuitManager = circuitManagerImpl;
        this.initializationTracker = torInitializationTracker;
        this.pathChooser = circuitPathChooser;
    }

    private void assignPendingStreamsToActiveCircuits() {
        List<StreamExitRequest> pendingExitStreams = this.circuitManager.getPendingExitStreams();
        if (pendingExitStreams.isEmpty()) {
            return;
        }
        for (ExitCircuit exitCircuit : this.circuitManager.getRandomlyOrderedListOfExitCircuits()) {
            Iterator<StreamExitRequest> it = pendingExitStreams.iterator();
            while (it.hasNext()) {
                if (attemptHandleStreamRequest(exitCircuit, it.next())) {
                    it.remove();
                }
            }
        }
    }

    private boolean attemptHandleStreamRequest(ExitCircuit exitCircuit, StreamExitRequest streamExitRequest) {
        if (!exitCircuit.canHandleExitTo(streamExitRequest)) {
            return false;
        }
        if (!streamExitRequest.reserveRequest()) {
            return true;
        }
        launchExitStreamTask(exitCircuit, streamExitRequest);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void buildCircuitIfNeeded() {
        if (this.connectionCache.isClosed()) {
            logger.warning("Not building circuits, because connection cache is closed");
            return;
        }
        List<StreamExitRequest> pendingExitStreams = this.circuitManager.getPendingExitStreams();
        List<PredictedPortTarget> predictedPortTargets = this.predictor.getPredictedPortTargets();
        List<ExitTarget> arrayList = new ArrayList<>();
        for (StreamExitRequest streamExitRequest : pendingExitStreams) {
            if (!streamExitRequest.isReserved() && countCircuitsSupportingTarget(streamExitRequest, false) == 0) {
                arrayList.add(streamExitRequest);
            }
        }
        for (ExitTarget exitTarget : predictedPortTargets) {
            if (countCircuitsSupportingTarget(exitTarget, true) < 2) {
                arrayList.add(exitTarget);
            }
        }
        buildCircuitToHandleExitTargets(arrayList);
    }

    private void buildCircuitToHandleExitTargets(List<ExitTarget> list) {
        if (!list.isEmpty() && this.directory.haveMinimumRouterInfo() && this.circuitManager.getPendingCircuitCount() < 4) {
            if (logger.isLoggable(Level.FINE)) {
                logger.fine("Building new circuit to handle " + list.size() + " pending streams and predicted ports");
            }
            launchBuildTaskForTargets(list);
        }
    }

    private void checkCircuitsForCreation() {
        if (!this.directory.haveMinimumRouterInfo()) {
            if (this.notEnoughDirectoryInformationWarningCounter % 20 == 0) {
                logger.info("Cannot build circuits because we don't have enough directory information");
            }
            this.notEnoughDirectoryInformationWarningCounter++;
        } else {
            if (this.lastNewCircuit.get() != 0) {
                System.currentTimeMillis();
                this.lastNewCircuit.get();
                this.config.getNewCircuitPeriod();
            }
            buildCircuitIfNeeded();
            maybeBuildInternalCircuit();
        }
    }

    private void checkExpiredPendingCircuits() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void circuitOpenedHandler(Circuit circuit) {
        if (circuit instanceof ExitCircuit) {
            ExitCircuit exitCircuit = (ExitCircuit) circuit;
            for (StreamExitRequest streamExitRequest : this.circuitManager.getPendingExitStreams()) {
                if (exitCircuit.canHandleExitTo(streamExitRequest) && streamExitRequest.reserveRequest()) {
                    launchExitStreamTask(exitCircuit, streamExitRequest);
                }
            }
        }
    }

    private int countCircuitsSupportingTarget(final ExitTarget exitTarget, final boolean z) {
        return this.circuitManager.getCircuitsByFilter(new CircuitManagerImpl.CircuitFilter() { // from class: com.subgraph.orchid.circuits.CircuitCreationTask.2
            @Override // com.subgraph.orchid.circuits.CircuitManagerImpl.CircuitFilter
            public boolean filter(Circuit circuit) {
                if (!(circuit instanceof ExitCircuit)) {
                    return false;
                }
                ExitCircuit exitCircuit = (ExitCircuit) circuit;
                boolean z2 = circuit.isPending() || circuit.isConnected();
                boolean z3 = !z || circuit.isClean();
                boolean z4 = false;
                if (z2) {
                    z4 = false;
                    if (z3) {
                        z4 = false;
                        if (exitCircuit.canHandleExitTo(exitTarget)) {
                            z4 = true;
                        }
                    }
                }
                return z4;
            }
        }).size();
    }

    private CircuitBuildHandler createCircuitBuildHandler() {
        return new CircuitBuildHandler() { // from class: com.subgraph.orchid.circuits.CircuitCreationTask.3
            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void circuitBuildCompleted(Circuit circuit) {
                CircuitCreationTask.logger.fine("Circuit completed to: " + circuit);
                CircuitCreationTask.this.circuitOpenedHandler(circuit);
                CircuitCreationTask.this.lastNewCircuit.set(System.currentTimeMillis());
            }

            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void circuitBuildFailed(String str) {
                CircuitCreationTask.logger.fine("Circuit build failed: " + str);
                CircuitCreationTask.this.buildCircuitIfNeeded();
            }

            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void connectionCompleted(Connection connection) {
                CircuitCreationTask.logger.finer("Circuit connection completed to " + connection);
            }

            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void connectionFailed(String str) {
                CircuitCreationTask.logger.fine("Circuit connection failed: " + str);
                CircuitCreationTask.this.buildCircuitIfNeeded();
            }

            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void nodeAdded(CircuitNode circuitNode) {
                CircuitCreationTask.logger.finer("Node added to circuit: " + circuitNode);
            }
        };
    }

    private CircuitBuildHandler createInternalCircuitBuildHandler() {
        return new CircuitBuildHandler() { // from class: com.subgraph.orchid.circuits.CircuitCreationTask.4
            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void circuitBuildCompleted(Circuit circuit) {
                CircuitCreationTask.logger.fine("Internal circuit build completed: " + circuit);
                CircuitCreationTask.this.lastNewCircuit.set(System.currentTimeMillis());
                CircuitCreationTask.this.circuitManager.addCleanInternalCircuit((InternalCircuit) circuit);
            }

            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void circuitBuildFailed(String str) {
                CircuitCreationTask.logger.fine("Circuit build failed: " + str);
                CircuitCreationTask.this.circuitManager.decrementPendingInternalCircuitCount();
            }

            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void connectionCompleted(Connection connection) {
                CircuitCreationTask.logger.finer("Circuit connection completed to " + connection);
            }

            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void connectionFailed(String str) {
                CircuitCreationTask.logger.fine("Circuit connection failed: " + str);
                CircuitCreationTask.this.circuitManager.decrementPendingInternalCircuitCount();
            }

            @Override // com.subgraph.orchid.CircuitBuildHandler
            public void nodeAdded(CircuitNode circuitNode) {
                CircuitCreationTask.logger.finer("Node added to internal circuit: " + circuitNode);
            }
        };
    }

    private void expireOldCircuits() {
        for (Circuit circuit : this.circuitManager.getCircuitsByFilter(new CircuitManagerImpl.CircuitFilter() { // from class: com.subgraph.orchid.circuits.CircuitCreationTask.1
            @Override // com.subgraph.orchid.circuits.CircuitManagerImpl.CircuitFilter
            public boolean filter(Circuit circuit2) {
                return !circuit2.isMarkedForClose() && circuit2.getSecondsDirty() > CircuitCreationTask.MAX_CIRCUIT_DIRTINESS;
            }
        })) {
            logger.fine("Closing idle dirty circuit: " + circuit);
            ((CircuitImpl) circuit).markForClose();
        }
    }

    private void launchBuildTaskForInternalCircuit() {
        logger.fine("Launching new internal circuit");
        this.executor.execute(new CircuitBuildTask(new CircuitCreationRequest(this.pathChooser, new InternalCircuitImpl(this.circuitManager), this.internalBuildHandler, false), this.connectionCache, this.circuitManager.isNtorEnabled()));
        this.circuitManager.incrementPendingInternalCircuitCount();
    }

    private void launchBuildTaskForTargets(List<ExitTarget> list) {
        Router chooseExitNodeForTargets = this.pathChooser.chooseExitNodeForTargets(list);
        if (chooseExitNodeForTargets == null) {
            logger.warning("Failed to select suitable exit node for targets");
            return;
        }
        this.executor.execute(new CircuitBuildTask(new CircuitCreationRequest(this.pathChooser, this.circuitManager.createNewExitCircuit(chooseExitNodeForTargets), this.buildHandler, false), this.connectionCache, this.circuitManager.isNtorEnabled(), this.initializationTracker));
    }

    private void launchExitStreamTask(ExitCircuit exitCircuit, StreamExitRequest streamExitRequest) {
        this.executor.execute(new OpenExitStreamTask(exitCircuit, streamExitRequest));
    }

    private void maybeBuildInternalCircuit() {
        if (this.circuitManager.getNeededCleanCircuitCount(this.predictor.isInternalPredicted()) > 0) {
            launchBuildTaskForInternalCircuit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitPredictor getCircuitPredictor() {
        return this.predictor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void predictPort(int i) {
        this.predictor.addExitPortRequest(i);
    }

    @Override // java.lang.Runnable
    public void run() {
        expireOldCircuits();
        assignPendingStreamsToActiveCircuits();
        checkExpiredPendingCircuits();
        checkCircuitsForCreation();
    }
}
