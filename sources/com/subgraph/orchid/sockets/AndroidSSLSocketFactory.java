package com.subgraph.orchid.sockets;

import com.subgraph.orchid.sockets.sslengine.SSLEngineSSLSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/AndroidSSLSocketFactory.class */
public class AndroidSSLSocketFactory extends SSLSocketFactory {
    private final SSLContext sslContext;

    public AndroidSSLSocketFactory() throws NoSuchAlgorithmException {
        this(SSLContext.getDefault());
    }

    public AndroidSSLSocketFactory(SSLContext sSLContext) {
        this.sslContext = sSLContext;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        throw new UnsupportedOperationException();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        throw new UnsupportedOperationException();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return new SSLEngineSSLSocket(socket, this.sslContext);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.sslContext.getDefaultSSLParameters().getCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.sslContext.getSupportedSSLParameters().getCipherSuites();
    }
}
