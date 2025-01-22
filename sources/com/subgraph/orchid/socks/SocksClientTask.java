package com.subgraph.orchid.socks;

import com.subgraph.orchid.CircuitManager;
import com.subgraph.orchid.OpenFailedException;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.TorException;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/socks/SocksClientTask.class */
public class SocksClientTask implements Runnable {
    private static final Logger logger = Logger.getLogger(SocksClientTask.class.getName());
    private final CircuitManager circuitManager;
    private final TorConfig config;
    private final Socket socket;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SocksClientTask(TorConfig torConfig, Socket socket, CircuitManager circuitManager) {
        this.config = torConfig;
        this.socket = socket;
        this.circuitManager = circuitManager;
    }

    private void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            logger.warning("Error closing SOCKS socket: " + e.getMessage());
        }
    }

    private void dispatchRequest(int i) {
        if (i == 4) {
            processRequest(new Socks4Request(this.config, this.socket));
            return;
        }
        if (i == 5) {
            processRequest(new Socks5Request(this.config, this.socket));
        } else if (i == 71 || i == 72 || i == 80) {
            sendHttpPage();
        }
    }

    private Stream openConnectStream(SocksRequest socksRequest) throws InterruptedException, TimeoutException, OpenFailedException {
        if (socksRequest.hasHostname()) {
            logger.fine("SOCKS CONNECT request to " + socksRequest.getHostname() + Constants.COLON_SEPARATOR + socksRequest.getPort());
            return this.circuitManager.openExitStreamTo(socksRequest.getHostname(), socksRequest.getPort());
        }
        logger.fine("SOCKS CONNECT request to " + socksRequest.getAddress() + Constants.COLON_SEPARATOR + socksRequest.getPort());
        return this.circuitManager.openExitStreamTo(socksRequest.getAddress(), socksRequest.getPort());
    }

    private void processRequest(SocksRequest socksRequest) {
        try {
            socksRequest.readRequest();
            if (!socksRequest.isConnectRequest()) {
                logger.warning("Non connect command (" + socksRequest.getCommandCode() + ")");
                socksRequest.sendError(true);
                return;
            }
            try {
                try {
                    try {
                        Stream openConnectStream = openConnectStream(socksRequest);
                        logger.fine("SOCKS CONNECT to " + socksRequest.getTarget() + " completed");
                        socksRequest.sendSuccess();
                        runOpenConnection(openConnectStream);
                    } catch (OpenFailedException e) {
                        logger.info("SOCKS CONNECT to " + socksRequest.getTarget() + " failed: " + e.getMessage());
                        socksRequest.sendConnectionRefused();
                    }
                } catch (InterruptedException e2) {
                    logger.info("SOCKS CONNECT to " + socksRequest.getTarget() + " was thread interrupted");
                    Thread.currentThread().interrupt();
                    socksRequest.sendError(false);
                }
            } catch (TimeoutException e3) {
                logger.info("SOCKS CONNECT to " + socksRequest.getTarget() + " timed out");
                socksRequest.sendError(false);
            }
        } catch (SocksRequestException e4) {
            logger.log(Level.WARNING, "Failure reading SOCKS request: " + e4.getMessage());
            try {
                socksRequest.sendError(false);
                this.socket.close();
            } catch (Exception e5) {
            }
        }
    }

    private int readByte() {
        try {
            return this.socket.getInputStream().read();
        } catch (IOException e) {
            logger.warning("IO error reading version byte: " + e.getMessage());
            return -1;
        }
    }

    private void runOpenConnection(Stream stream) {
        SocksStreamConnection.runConnection(this.socket, stream);
    }

    private void sendHttpPage() {
        throw new TorException("Returning HTTP page not implemented");
    }

    @Override // java.lang.Runnable
    public void run() {
        dispatchRequest(readByte());
        closeSocket();
    }
}
