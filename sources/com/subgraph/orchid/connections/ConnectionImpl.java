package com.subgraph.orchid.connections;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.Connection;
import com.subgraph.orchid.ConnectionFailedException;
import com.subgraph.orchid.ConnectionHandshakeException;
import com.subgraph.orchid.ConnectionIOException;
import com.subgraph.orchid.ConnectionTimeoutException;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Threading;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.TorInitializationTracker;
import com.subgraph.orchid.circuits.cells.CellImpl;
import com.subgraph.orchid.crypto.TorRandom;
import com.subgraph.orchid.dashboard.DashboardRenderable;
import com.subgraph.orchid.dashboard.DashboardRenderer;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionImpl.class */
public class ConnectionImpl implements Connection, DashboardRenderable {
    private static final int CONNECTION_IDLE_TIMEOUT = 300000;
    private static final int DEFAULT_CONNECT_TIMEOUT = 5000;
    private final TorConfig config;
    private final BlockingQueue<Cell> connectionControlCells;
    private final TorInitializationTracker initializationTracker;
    private InputStream input;
    private volatile boolean isClosed;
    private boolean isConnected;
    private final boolean isDirectoryConnection;
    private OutputStream output;
    private final Router router;
    private final SSLSocket socket;
    private static final Logger logger = Logger.getLogger(ConnectionImpl.class.getName());
    private static final Cell connectionClosedSentinel = CellImpl.createCell(0, 0);
    private int currentId = 1;
    private final ReentrantLock connectLock = Threading.lock("connect");
    private final ReentrantLock circuitsLock = Threading.lock("circuits");
    private final ReentrantLock outputLock = Threading.lock("output");
    private final AtomicLong lastActivity = new AtomicLong();
    private final Map<Integer, Circuit> circuitMap = new HashMap();
    private final Thread readCellsThread = new Thread(createReadCellsRunnable());

    public ConnectionImpl(TorConfig torConfig, SSLSocket sSLSocket, Router router, TorInitializationTracker torInitializationTracker, boolean z) {
        this.config = torConfig;
        this.socket = sSLSocket;
        this.router = router;
        this.readCellsThread.setDaemon(true);
        this.connectionControlCells = new LinkedBlockingQueue();
        this.initializationTracker = torInitializationTracker;
        this.isDirectoryConnection = z;
        initializeCurrentCircuitId();
    }

    private void connectSocket() throws IOException {
        TorInitializationTracker torInitializationTracker = this.initializationTracker;
        if (torInitializationTracker != null) {
            if (this.isDirectoryConnection) {
                torInitializationTracker.notifyEvent(5);
            } else {
                torInitializationTracker.notifyEvent(80);
            }
        }
        this.socket.connect(routerToSocketAddress(this.router), DEFAULT_CONNECT_TIMEOUT);
        TorInitializationTracker torInitializationTracker2 = this.initializationTracker;
        if (torInitializationTracker2 != null) {
            if (this.isDirectoryConnection) {
                torInitializationTracker2.notifyEvent(10);
            } else {
                torInitializationTracker2.notifyEvent(85);
            }
        }
    }

    private Runnable createReadCellsRunnable() {
        return new Runnable() { // from class: com.subgraph.orchid.connections.ConnectionImpl.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ConnectionImpl.this.readCellsLoop();
                } catch (Exception e) {
                    ConnectionImpl.logger.log(Level.WARNING, "Unhandled exception processing incoming cells on connection " + e, (Throwable) e);
                }
            }
        };
    }

    private void doConnect() throws IOException, InterruptedException, ConnectionIOException {
        connectSocket();
        ConnectionHandshake createHandshake = ConnectionHandshake.createHandshake(this.config, this, this.socket);
        this.input = this.socket.getInputStream();
        this.output = this.socket.getOutputStream();
        this.readCellsThread.start();
        createHandshake.runHandshake();
        updateLastActivity();
    }

    private long getIdleMilliseconds() {
        if (this.lastActivity.get() == 0) {
            return 0L;
        }
        return System.currentTimeMillis() - this.lastActivity.get();
    }

    private void incrementNextId() {
        this.currentId++;
        if (this.currentId > 65535) {
            this.currentId = 1;
        }
    }

    private void initializeCurrentCircuitId() {
        this.currentId = new TorRandom().nextInt(65535) + 1;
    }

    private void notifyCircuitsLinkClosed() {
    }

    private void processCell(Cell cell) {
        updateLastActivity();
        int command = cell.getCommand();
        if (command == 3) {
            processRelayCell(cell);
            return;
        }
        if (command == 2 || command == 4 || command == 6) {
            processControlCell(cell);
        } else if (command == 7 || command == 8 || command == 129 || command == 130) {
            this.connectionControlCells.add(cell);
        }
    }

    private void processControlCell(Cell cell) {
        this.circuitsLock.lock();
        try {
            Circuit circuit = this.circuitMap.get(Integer.valueOf(cell.getCircuitId()));
            if (circuit != null) {
                circuit.deliverControlCell(cell);
            }
        } finally {
            this.circuitsLock.unlock();
        }
    }

    private void processRelayCell(Cell cell) {
        this.circuitsLock.lock();
        try {
            Circuit circuit = this.circuitMap.get(Integer.valueOf(cell.getCircuitId()));
            if (circuit != null) {
                this.circuitsLock.unlock();
                circuit.deliverRelayCell(cell);
                return;
            }
            logger.warning("Could not deliver relay cell for circuit id = " + cell.getCircuitId() + " on connection " + this + ". Circuit not found");
        } finally {
            this.circuitsLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readCellsLoop() {
        while (!Thread.interrupted()) {
            try {
                processCell(recvCell());
            } catch (ConnectionIOException e) {
                this.connectionControlCells.add(connectionClosedSentinel);
                notifyCircuitsLinkClosed();
                return;
            } catch (TorException e2) {
                logger.log(Level.WARNING, "Unhandled Tor exception reading and processing cells: " + e2.getMessage(), (Throwable) e2);
            }
        }
    }

    private Cell recvCell() throws ConnectionIOException {
        try {
            return CellImpl.readFromInputStream(this.input);
        } catch (EOFException e) {
            closeSocket();
            throw new ConnectionIOException();
        } catch (IOException e2) {
            if (!this.isClosed) {
                logger.fine("IOException reading cell from connection " + this + " : " + e2.getMessage());
                closeSocket();
            }
            throw new ConnectionIOException(e2.getClass().getName() + " " + e2.getMessage());
        }
    }

    private SocketAddress routerToSocketAddress(Router router) {
        return new InetSocketAddress(router.getAddress().toInetAddress(), router.getOnionPort());
    }

    private void updateLastActivity() {
        this.lastActivity.set(System.currentTimeMillis());
    }

    @Override // com.subgraph.orchid.Connection
    public int bindCircuit(Circuit circuit) {
        this.circuitsLock.lock();
        while (this.circuitMap.containsKey(Integer.valueOf(this.currentId))) {
            try {
                incrementNextId();
            } finally {
                this.circuitsLock.unlock();
            }
        }
        int i = this.currentId;
        incrementNextId();
        this.circuitMap.put(Integer.valueOf(i), circuit);
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeSocket() {
        try {
            logger.fine("Closing connection to " + this);
            this.isClosed = true;
            this.socket.close();
            this.isConnected = false;
        } catch (IOException e) {
            logger.warning("Error closing socket: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void connect() throws ConnectionFailedException, ConnectionTimeoutException, ConnectionHandshakeException {
        this.connectLock.lock();
        try {
            if (this.isConnected) {
                return;
            }
            try {
                try {
                    doConnect();
                    this.isConnected = true;
                } catch (ConnectionHandshakeException e) {
                    throw e;
                } catch (SocketTimeoutException e2) {
                    throw new ConnectionTimeoutException();
                } catch (IOException e3) {
                    throw new ConnectionFailedException(e3.getClass().getName() + " : " + e3.getMessage());
                }
            } catch (ConnectionIOException e4) {
                throw new ConnectionFailedException(e4.getMessage());
            } catch (InterruptedException e5) {
                Thread.currentThread().interrupt();
                throw new ConnectionHandshakeException("Handshake interrupted");
            }
        } finally {
            this.connectLock.unlock();
        }
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderable
    public void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) throws IOException {
        this.circuitsLock.lock();
        try {
            int size = this.circuitMap.size();
            if (size == 0 && (i & 2) == 0) {
                return;
            }
            printWriter.print("  [Connection router=" + this.router.getNickname());
            printWriter.print(" circuits=" + size);
            printWriter.print(" idle=" + (getIdleMilliseconds() / 1000) + "s");
            printWriter.println("]");
        } finally {
            this.circuitsLock.unlock();
        }
    }

    @Override // com.subgraph.orchid.Connection
    public Router getRouter() {
        return this.router;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void idleCloseCheck() {
        this.circuitsLock.lock();
        try {
            if (!this.isClosed && this.circuitMap.isEmpty() && getIdleMilliseconds() > 300000) {
                logger.fine("Closing connection to " + this + " on idle timeout");
                closeSocket();
            }
        } finally {
            this.circuitsLock.unlock();
        }
    }

    @Override // com.subgraph.orchid.Connection
    public boolean isClosed() {
        return this.isClosed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cell readConnectionControlCell() throws ConnectionIOException {
        try {
            return this.connectionControlCells.take();
        } catch (InterruptedException e) {
            closeSocket();
            throw new ConnectionIOException();
        }
    }

    @Override // com.subgraph.orchid.Connection
    public void removeCircuit(Circuit circuit) {
        this.circuitsLock.lock();
        try {
            this.circuitMap.remove(Integer.valueOf(circuit.getCircuitId()));
        } finally {
            this.circuitsLock.unlock();
        }
    }

    @Override // com.subgraph.orchid.Connection
    public void sendCell(Cell cell) throws ConnectionIOException {
        if (!this.socket.isConnected()) {
            throw new ConnectionIOException("Cannot send cell because connection is not connected");
        }
        updateLastActivity();
        this.outputLock.lock();
        try {
            try {
                this.output.write(cell.getCellBytes());
                this.outputLock.unlock();
            } catch (IOException e) {
                logger.fine("IOException writing cell to connection " + e.getMessage());
                closeSocket();
                throw new ConnectionIOException(e.getClass().getName() + " : " + e.getMessage());
            }
        } catch (Throwable th) {
            this.outputLock.unlock();
            throw th;
        }
    }

    public String toString() {
        return "!" + this.router.getNickname() + "!";
    }
}
