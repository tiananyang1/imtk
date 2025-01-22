package com.subgraph.orchid.sockets;

import com.subgraph.orchid.OpenFailedException;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.Threading;
import com.subgraph.orchid.TorClient;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/OrchidSocketImpl.class */
public class OrchidSocketImpl extends SocketImpl {
    private Stream stream;
    private Lock streamLock = Threading.lock("stream");
    private final TorClient torClient;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OrchidSocketImpl(TorClient torClient) {
        this.torClient = torClient;
        this.fd = new FileDescriptor();
    }

    private String addressToName(InetSocketAddress inetSocketAddress) {
        return inetSocketAddress.getAddress() != null ? inetSocketAddress.getAddress().getHostAddress() : inetSocketAddress.getHostName();
    }

    private void doConnect(String str, int i) throws IOException {
        this.streamLock.lock();
        Stream stream = this.stream;
        this.streamLock.unlock();
        if (stream != null) {
            throw new SocketException("Already connected");
        }
        try {
            Stream openExitStreamTo = this.torClient.openExitStreamTo(str, i);
            this.streamLock.lock();
            if (this.stream != null) {
                this.streamLock.unlock();
                openExitStreamTo.close();
            } else {
                this.stream = openExitStreamTo;
                this.streamLock.unlock();
            }
        } catch (OpenFailedException e) {
            throw new ConnectException(e.getMessage());
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            throw new SocketException("connect() interrupted");
        } catch (TimeoutException e3) {
            throw new SocketTimeoutException();
        }
    }

    private Stream getStream() throws IOException {
        this.streamLock.lock();
        try {
            if (this.stream != null) {
                return this.stream;
            }
            throw new IOException("Not connected");
        } finally {
            this.streamLock.unlock();
        }
    }

    @Override // java.net.SocketImpl
    protected void accept(SocketImpl socketImpl) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.net.SocketImpl
    protected int available() throws IOException {
        return getStream().getInputStream().available();
    }

    @Override // java.net.SocketImpl
    protected void bind(InetAddress inetAddress, int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void close() throws IOException {
        this.streamLock.lock();
        Stream stream = this.stream;
        this.stream = null;
        this.streamLock.unlock();
        if (stream != null) {
            stream.close();
        }
    }

    @Override // java.net.SocketImpl
    protected void connect(String str, int i) throws IOException {
        connect(InetSocketAddress.createUnresolved(str, i), 0);
    }

    @Override // java.net.SocketImpl
    protected void connect(InetAddress inetAddress, int i) throws IOException {
        connect(InetSocketAddress.createUnresolved(inetAddress.getHostAddress(), i), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.net.SocketImpl
    public void connect(SocketAddress socketAddress, int i) throws IOException {
        if (!(socketAddress instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("Unsupported address type");
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        doConnect(addressToName(inetSocketAddress), inetSocketAddress.getPort());
    }

    @Override // java.net.SocketImpl
    protected void create(boolean z) throws IOException {
    }

    @Override // java.net.SocketImpl
    protected InputStream getInputStream() throws IOException {
        return getStream().getInputStream();
    }

    @Override // java.net.SocketOptions
    public Object getOption(int i) throws SocketException {
        if (i == 128) {
            return 0;
        }
        if (i == 1) {
            return Boolean.TRUE;
        }
        if (i == 4102) {
        }
        return 0;
    }

    @Override // java.net.SocketImpl
    protected OutputStream getOutputStream() throws IOException {
        return getStream().getOutputStream();
    }

    @Override // java.net.SocketImpl
    protected void listen(int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.net.SocketImpl
    protected void sendUrgentData(int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.net.SocketOptions
    public void setOption(int i, Object obj) throws SocketException {
    }

    @Override // java.net.SocketImpl
    protected void shutdownInput() throws IOException {
    }

    @Override // java.net.SocketImpl
    protected void shutdownOutput() throws IOException {
    }
}
