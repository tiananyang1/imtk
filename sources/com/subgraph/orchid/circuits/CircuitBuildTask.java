package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Connection;
import com.subgraph.orchid.ConnectionCache;
import com.subgraph.orchid.ConnectionFailedException;
import com.subgraph.orchid.ConnectionHandshakeException;
import com.subgraph.orchid.ConnectionTimeoutException;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.path.PathSelectionFailedException;
import com.xiaomi.mipush.sdk.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitBuildTask.class */
public class CircuitBuildTask implements Runnable {
    private static final Logger logger = Logger.getLogger(CircuitBuildTask.class.getName());
    private final CircuitImpl circuit;
    private Connection connection;
    private final ConnectionCache connectionCache;
    private final CircuitCreationRequest creationRequest;
    private final CircuitExtender extender;
    private final TorInitializationTracker initializationTracker;

    public CircuitBuildTask(CircuitCreationRequest circuitCreationRequest, ConnectionCache connectionCache, boolean z) {
        this(circuitCreationRequest, connectionCache, z, null);
    }

    public CircuitBuildTask(CircuitCreationRequest circuitCreationRequest, ConnectionCache connectionCache, boolean z, TorInitializationTracker torInitializationTracker) {
        this.connection = null;
        this.creationRequest = circuitCreationRequest;
        this.connectionCache = connectionCache;
        this.initializationTracker = torInitializationTracker;
        this.circuit = circuitCreationRequest.getCircuit();
        this.extender = new CircuitExtender(circuitCreationRequest.getCircuit(), z);
    }

    private void buildCircuit(Router router) throws TorException {
        notifyInitialization();
        this.creationRequest.nodeAdded(this.extender.createFastTo(router));
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 >= this.creationRequest.getPathLength()) {
                this.creationRequest.circuitBuildCompleted(this.circuit);
                notifyDone();
                return;
            } else {
                this.creationRequest.nodeAdded(this.extender.extendTo(this.creationRequest.getPathElement(i2)));
                i = i2 + 1;
            }
        }
    }

    private void circuitBuildFailed(String str) {
        this.creationRequest.circuitBuildFailed(str);
        this.circuit.notifyCircuitBuildFailed();
        Connection connection = this.connection;
        if (connection != null) {
            connection.removeCircuit(this.circuit);
        }
    }

    private void connectionFailed(String str) {
        this.creationRequest.connectionFailed(str);
        this.circuit.notifyCircuitBuildFailed();
    }

    private void notifyDone() {
        if (this.initializationTracker == null || this.creationRequest.isDirectoryCircuit()) {
            return;
        }
        this.initializationTracker.notifyEvent(100);
    }

    private void notifyInitialization() {
        if (this.initializationTracker != null) {
            this.initializationTracker.notifyEvent(this.creationRequest.isDirectoryCircuit() ? 15 : 90);
        }
    }

    private void openEntryNodeConnection(Router router) throws ConnectionTimeoutException, ConnectionFailedException, ConnectionHandshakeException, InterruptedException {
        this.connection = this.connectionCache.getConnectionTo(router, this.creationRequest.isDirectoryCircuit());
        this.circuit.bindToConnection(this.connection);
        this.creationRequest.connectionCompleted(this.connection);
    }

    private String pathToString(CircuitCreationRequest circuitCreationRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Router router : circuitCreationRequest.getPath()) {
            if (sb.length() > 1) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            sb.append(router.getNickname());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.lang.Runnable
    public void run() {
        Router router = null;
        Router router2 = null;
        Router router3 = null;
        try {
            this.circuit.notifyCircuitBuildStart();
            this.creationRequest.choosePath();
            if (logger.isLoggable(Level.FINE)) {
                Logger logger2 = logger;
                StringBuilder sb = new StringBuilder();
                sb.append("Opening a new circuit to ");
                sb.append(pathToString(this.creationRequest));
                logger2.fine(sb.toString());
            }
            Router pathElement = this.creationRequest.getPathElement(0);
            openEntryNodeConnection(pathElement);
            buildCircuit(pathElement);
            router = pathElement;
            router2 = pathElement;
            router3 = pathElement;
            this.circuit.notifyCircuitBuildCompleted();
        } catch (ConnectionFailedException e) {
            connectionFailed("Connection failed to " + router2 + " : " + e.getMessage());
        } catch (ConnectionHandshakeException e2) {
            connectionFailed("Handshake error connecting to " + router + " : " + e2.getMessage());
        } catch (ConnectionTimeoutException e3) {
            connectionFailed("Timeout connecting to " + router3);
        } catch (TorException e4) {
            circuitBuildFailed(e4.getMessage());
        } catch (PathSelectionFailedException e5) {
            circuitBuildFailed(e5.getMessage());
        } catch (InterruptedException e6) {
            Thread.currentThread().interrupt();
            circuitBuildFailed("Circuit building thread interrupted");
        } catch (Exception e7) {
            circuitBuildFailed("Unexpected exception: " + e7);
            logger.log(Level.WARNING, "Unexpected exception while building circuit: " + e7, (Throwable) e7);
        }
    }
}
