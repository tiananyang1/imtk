package com.subgraph.orchid.sockets.sslengine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/sslengine/SSLEngineSSLSocket.class */
public class SSLEngineSSLSocket extends SSLSocket implements HandshakeCallbackHandler {
    private final SSLEngine engine;
    private InputStream inputStream;
    private final List<HandshakeCompletedListener> listenerList = new CopyOnWriteArrayList();
    private final SSLEngineManager manager;
    private OutputStream outputStream;
    private Socket socket;

    public SSLEngineSSLSocket(Socket socket, SSLContext sSLContext) throws IOException {
        this.engine = createSSLEngine(sSLContext);
        this.socket = socket;
        this.manager = new SSLEngineManager(this.engine, this, socket.getInputStream(), socket.getOutputStream());
    }

    private static SSLEngine createSSLEngine(SSLContext sSLContext) {
        SSLEngine createSSLEngine = sSLContext.createSSLEngine();
        createSSLEngine.setUseClientMode(true);
        return createSSLEngine;
    }

    @Override // javax.net.ssl.SSLSocket
    public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.listenerList.add(handshakeCompletedListener);
    }

    @Override // java.net.Socket
    public void bind(SocketAddress socketAddress) throws IOException {
        throw new IOException("Socket is already connected");
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress) throws IOException {
        throw new IOException("Socket is already connected");
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i) throws IOException {
        throw new IOException("Socket is already connected");
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getEnableSessionCreation() {
        return this.engine.getEnableSessionCreation();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getEnabledCipherSuites() {
        return this.engine.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getEnabledProtocols() {
        return this.engine.getEnabledProtocols();
    }

    @Override // java.net.Socket
    public InetAddress getInetAddress() {
        return this.socket.getInetAddress();
    }

    @Override // java.net.Socket
    public InputStream getInputStream() throws IOException {
        InputStream inputStream;
        synchronized (this) {
            if (this.inputStream == null) {
                this.inputStream = new SSLEngineInputStream(this.manager);
            }
            inputStream = this.inputStream;
        }
        return inputStream;
    }

    @Override // java.net.Socket
    public boolean getKeepAlive() throws SocketException {
        return this.socket.getKeepAlive();
    }

    @Override // java.net.Socket
    public InetAddress getLocalAddress() {
        return this.socket.getLocalAddress();
    }

    @Override // java.net.Socket
    public int getLocalPort() {
        return this.socket.getLocalPort();
    }

    @Override // java.net.Socket
    public SocketAddress getLocalSocketAddress() {
        return this.socket.getLocalSocketAddress();
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getNeedClientAuth() {
        return this.engine.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public boolean getOOBInline() throws SocketException {
        return this.socket.getOOBInline();
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() throws IOException {
        if (this.outputStream == null) {
            this.outputStream = new SSLEngineOutputStream(this.manager);
        }
        return this.outputStream;
    }

    @Override // java.net.Socket
    public int getPort() {
        return this.socket.getPort();
    }

    @Override // java.net.Socket
    public int getReceiveBufferSize() throws SocketException {
        int receiveBufferSize;
        synchronized (this) {
            receiveBufferSize = this.socket.getReceiveBufferSize();
        }
        return receiveBufferSize;
    }

    @Override // java.net.Socket
    public SocketAddress getRemoteSocketAddress() {
        return this.socket.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public boolean getReuseAddress() throws SocketException {
        return this.socket.getReuseAddress();
    }

    @Override // java.net.Socket
    public int getSendBufferSize() throws SocketException {
        int sendBufferSize;
        synchronized (this) {
            sendBufferSize = this.socket.getSendBufferSize();
        }
        return sendBufferSize;
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLSession getSession() {
        return this.engine.getSession();
    }

    @Override // java.net.Socket
    public int getSoLinger() throws SocketException {
        return this.socket.getSoLinger();
    }

    @Override // java.net.Socket
    public int getSoTimeout() throws SocketException {
        int soTimeout;
        synchronized (this) {
            soTimeout = this.socket.getSoTimeout();
        }
        return soTimeout;
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getSupportedCipherSuites() {
        return this.engine.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getSupportedProtocols() {
        return this.engine.getSupportedProtocols();
    }

    @Override // java.net.Socket
    public boolean getTcpNoDelay() throws SocketException {
        return this.socket.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public int getTrafficClass() throws SocketException {
        return this.socket.getTrafficClass();
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getUseClientMode() {
        return this.engine.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getWantClientAuth() {
        return this.engine.getWantClientAuth();
    }

    @Override // com.subgraph.orchid.sockets.sslengine.HandshakeCallbackHandler
    public void handshakeCompleted() {
        if (this.listenerList.isEmpty()) {
            return;
        }
        HandshakeCompletedEvent handshakeCompletedEvent = new HandshakeCompletedEvent(this, this.engine.getSession());
        Iterator<HandshakeCompletedListener> it = this.listenerList.iterator();
        while (it.hasNext()) {
            it.next().handshakeCompleted(handshakeCompletedEvent);
        }
    }

    @Override // java.net.Socket
    public boolean isInputShutdown() {
        return this.socket.isInputShutdown();
    }

    @Override // java.net.Socket
    public boolean isOutputShutdown() {
        return this.socket.isOutputShutdown();
    }

    @Override // javax.net.ssl.SSLSocket
    public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.listenerList.remove(handshakeCompletedListener);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnableSessionCreation(boolean z) {
        this.engine.setEnableSessionCreation(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnabledCipherSuites(String[] strArr) {
        this.engine.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnabledProtocols(String[] strArr) {
        this.engine.setEnabledProtocols(strArr);
    }

    @Override // java.net.Socket
    public void setKeepAlive(boolean z) throws SocketException {
        this.socket.setKeepAlive(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setNeedClientAuth(boolean z) {
        this.engine.setNeedClientAuth(z);
    }

    @Override // java.net.Socket
    public void setOOBInline(boolean z) throws SocketException {
        this.socket.setOOBInline(z);
    }

    @Override // java.net.Socket
    public void setReceiveBufferSize(int i) throws SocketException {
        synchronized (this) {
            this.socket.setReceiveBufferSize(i);
        }
    }

    @Override // java.net.Socket
    public void setReuseAddress(boolean z) throws SocketException {
        this.socket.setReuseAddress(z);
    }

    @Override // java.net.Socket
    public void setSendBufferSize(int i) throws SocketException {
        synchronized (this) {
            this.socket.setSendBufferSize(i);
        }
    }

    @Override // java.net.Socket
    public void setSoLinger(boolean z, int i) throws SocketException {
        this.socket.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public void setSoTimeout(int i) throws SocketException {
        synchronized (this) {
            this.socket.setSoTimeout(i);
        }
    }

    @Override // java.net.Socket
    public void setTcpNoDelay(boolean z) throws SocketException {
        this.socket.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public void setTrafficClass(int i) throws SocketException {
        this.socket.setTrafficClass(i);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setUseClientMode(boolean z) {
        this.engine.setUseClientMode(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setWantClientAuth(boolean z) {
        this.engine.setWantClientAuth(z);
    }

    @Override // java.net.Socket
    public void shutdownInput() throws IOException {
        throw new UnsupportedOperationException("shutdownInput() not supported on SSL Sockets");
    }

    @Override // java.net.Socket
    public void shutdownOutput() throws IOException {
        throw new UnsupportedOperationException("shutdownOutput() not supported on SSL Sockets");
    }

    @Override // javax.net.ssl.SSLSocket
    public void startHandshake() throws IOException {
        this.manager.startHandshake();
    }
}
