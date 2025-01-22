package com.subgraph.orchid.socks;

import com.subgraph.orchid.CircuitManager;
import com.subgraph.orchid.SocksPortListener;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.TorException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/socks/SocksPortListenerImpl.class */
public class SocksPortListenerImpl implements SocksPortListener {
    private static final Logger logger = Logger.getLogger(SocksPortListenerImpl.class.getName());
    private final CircuitManager circuitManager;
    private final TorConfig config;
    private boolean isStopped;
    private final Set<Integer> listeningPorts = new HashSet();
    private final Map<Integer, AcceptTask> acceptThreads = new HashMap();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/socks/SocksPortListenerImpl$AcceptTask.class */
    public class AcceptTask implements Runnable {
        private final int port;
        private final ServerSocket socket;
        private volatile boolean stopped;

        AcceptTask(int i) throws IOException {
            this.socket = new ServerSocket(i);
            this.port = i;
        }

        private void runAcceptLoop() throws IOException {
            while (!Thread.interrupted() && !this.stopped) {
                SocksPortListenerImpl.this.executor.execute(SocksPortListenerImpl.this.newClientSocket(this.socket.accept()));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    runAcceptLoop();
                    synchronized (SocksPortListenerImpl.this.listeningPorts) {
                        SocksPortListenerImpl.this.listeningPorts.remove(Integer.valueOf(this.port));
                        SocksPortListenerImpl.this.acceptThreads.remove(Integer.valueOf(this.port));
                    }
                } catch (IOException e) {
                    if (!this.stopped) {
                        SocksPortListenerImpl.logger.warning("System error accepting SOCKS socket connections: " + e.getMessage());
                    }
                    synchronized (SocksPortListenerImpl.this.listeningPorts) {
                        SocksPortListenerImpl.this.listeningPorts.remove(Integer.valueOf(this.port));
                        SocksPortListenerImpl.this.acceptThreads.remove(Integer.valueOf(this.port));
                    }
                }
            } catch (Throwable th) {
                synchronized (SocksPortListenerImpl.this.listeningPorts) {
                    SocksPortListenerImpl.this.listeningPorts.remove(Integer.valueOf(this.port));
                    SocksPortListenerImpl.this.acceptThreads.remove(Integer.valueOf(this.port));
                    throw th;
                }
            }
        }

        void stop() {
            this.stopped = true;
            try {
                this.socket.close();
            } catch (IOException e) {
            }
        }
    }

    public SocksPortListenerImpl(TorConfig torConfig, CircuitManager circuitManager) {
        this.config = torConfig;
        this.circuitManager = circuitManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Runnable newClientSocket(Socket socket) {
        return new SocksClientTask(this.config, socket, this.circuitManager);
    }

    private void startListening(int i) throws IOException {
        AcceptTask acceptTask = new AcceptTask(i);
        this.acceptThreads.put(Integer.valueOf(i), acceptTask);
        this.executor.execute(acceptTask);
    }

    @Override // com.subgraph.orchid.SocksPortListener
    public void addListeningPort(int i) {
        if (i <= 0 || i > 65535) {
            throw new TorException("Illegal listening port: " + i);
        }
        synchronized (this.listeningPorts) {
            if (this.isStopped) {
                throw new IllegalStateException("Cannot add listening port because Socks proxy has been stopped");
            }
            if (this.listeningPorts.contains(Integer.valueOf(i))) {
                return;
            }
            this.listeningPorts.add(Integer.valueOf(i));
            try {
                startListening(i);
                logger.fine("Listening for SOCKS connections on port " + i);
            } catch (IOException e) {
                this.listeningPorts.remove(Integer.valueOf(i));
                throw new TorException("Failed to listen on port " + i + " : " + e.getMessage());
            }
        }
    }

    @Override // com.subgraph.orchid.SocksPortListener
    public void stop() {
        synchronized (this.listeningPorts) {
            Iterator<AcceptTask> it = this.acceptThreads.values().iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
            this.executor.shutdownNow();
            this.isStopped = true;
        }
    }
}
