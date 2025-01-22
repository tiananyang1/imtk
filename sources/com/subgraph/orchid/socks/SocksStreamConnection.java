package com.subgraph.orchid.socks;

import com.subgraph.orchid.Stream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/socks/SocksStreamConnection.class */
public class SocksStreamConnection {
    private static final int TRANSFER_BUFFER_SIZE = 4096;
    private static final Logger logger = Logger.getLogger(SocksStreamConnection.class.getName());
    private volatile boolean incomingClosed;
    private volatile boolean outgoingClosed;
    private final Socket socket;
    private final Stream stream;
    private final InputStream torInputStream;
    private final OutputStream torOutputStream;
    private final Object lock = new Object();
    private final Thread incomingThread = createIncomingThread();
    private final Thread outgoingThread = createOutgoingThread();

    private SocksStreamConnection(Socket socket, Stream stream) {
        this.socket = socket;
        this.stream = stream;
        this.torInputStream = stream.getInputStream();
        this.torOutputStream = stream.getOutputStream();
    }

    private void closeStream(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            logger.warning("Close failed on " + closeable + " : " + e.getMessage());
        }
    }

    private Thread createIncomingThread() {
        return new Thread(new Runnable() { // from class: com.subgraph.orchid.socks.SocksStreamConnection.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        SocksStreamConnection.this.incomingTransferLoop();
                        synchronized (SocksStreamConnection.this.lock) {
                            SocksStreamConnection.this.incomingClosed = true;
                            SocksStreamConnection.this.lock.notifyAll();
                        }
                    } catch (IOException e) {
                        SocksStreamConnection.logger.fine("System error on incoming stream IO  " + SocksStreamConnection.this.stream + " : " + e.getMessage());
                        synchronized (SocksStreamConnection.this.lock) {
                            SocksStreamConnection.this.incomingClosed = true;
                            SocksStreamConnection.this.lock.notifyAll();
                        }
                    }
                } catch (Throwable th) {
                    synchronized (SocksStreamConnection.this.lock) {
                        SocksStreamConnection.this.incomingClosed = true;
                        SocksStreamConnection.this.lock.notifyAll();
                        throw th;
                    }
                }
            }
        });
    }

    private Thread createOutgoingThread() {
        return new Thread(new Runnable() { // from class: com.subgraph.orchid.socks.SocksStreamConnection.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        SocksStreamConnection.this.outgoingTransferLoop();
                        synchronized (SocksStreamConnection.this.lock) {
                            SocksStreamConnection.this.outgoingClosed = true;
                            SocksStreamConnection.this.lock.notifyAll();
                        }
                    } catch (IOException e) {
                        SocksStreamConnection.logger.fine("System error on outgoing stream IO " + SocksStreamConnection.this.stream + " : " + e.getMessage());
                        synchronized (SocksStreamConnection.this.lock) {
                            SocksStreamConnection.this.outgoingClosed = true;
                            SocksStreamConnection.this.lock.notifyAll();
                        }
                    }
                } catch (Throwable th) {
                    synchronized (SocksStreamConnection.this.lock) {
                        SocksStreamConnection.this.outgoingClosed = true;
                        SocksStreamConnection.this.lock.notifyAll();
                        throw th;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void incomingTransferLoop() throws IOException {
        byte[] bArr = new byte[TRANSFER_BUFFER_SIZE];
        while (true) {
            int read = this.torInputStream.read(bArr);
            if (read == -1) {
                logger.fine("EOF on TOR input stream " + this.stream);
                this.socket.shutdownOutput();
                return;
            }
            if (read > 0) {
                logger.fine("Transferring " + read + " bytes from " + this.stream + " to SOCKS socket");
                if (this.socket.isOutputShutdown()) {
                    closeStream(this.torInputStream);
                    return;
                } else {
                    this.socket.getOutputStream().write(bArr, 0, read);
                    this.socket.getOutputStream().flush();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void outgoingTransferLoop() throws IOException {
        byte[] bArr = new byte[TRANSFER_BUFFER_SIZE];
        while (true) {
            this.stream.waitForSendWindow();
            int read = this.socket.getInputStream().read(bArr);
            if (read == -1) {
                this.torOutputStream.close();
                logger.fine("EOF on SOCKS socket connected to " + this.stream);
                return;
            }
            if (read > 0) {
                logger.fine("Transferring " + read + " bytes from SOCKS socket to " + this.stream);
                this.torOutputStream.write(bArr, 0, read);
                this.torOutputStream.flush();
            }
        }
    }

    private void run() {
        this.incomingThread.start();
        this.outgoingThread.start();
        synchronized (this.lock) {
            while (true) {
                if (this.outgoingClosed && this.incomingClosed) {
                    try {
                        break;
                    } catch (IOException e) {
                        logger.warning("IOException on SOCKS socket close(): " + e.getMessage());
                    }
                } else {
                    try {
                        this.lock.wait();
                    } catch (InterruptedException e2) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            this.socket.close();
            closeStream(this.torInputStream);
            closeStream(this.torOutputStream);
        }
    }

    public static void runConnection(Socket socket, Stream stream) {
        new SocksStreamConnection(socket, stream).run();
    }
}
