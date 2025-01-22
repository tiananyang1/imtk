package com.subgraph.orchid.sockets;

import com.subgraph.orchid.Tor;
import com.subgraph.orchid.TorClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.net.SocketFactory;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/OrchidSocketFactory.class */
public class OrchidSocketFactory extends SocketFactory {
    private final boolean exceptionOnLocalBind;
    private final TorClient torClient;

    public OrchidSocketFactory(TorClient torClient) {
        this(torClient, true);
    }

    public OrchidSocketFactory(TorClient torClient, boolean z) {
        this.torClient = torClient;
        this.exceptionOnLocalBind = z;
    }

    private Socket connectOrchidSocket(Socket socket, String str, int i) throws IOException {
        socket.connect(InetSocketAddress.createUnresolved(str, i));
        return socket;
    }

    private Socket createSocketInstance() throws SocketException {
        OrchidSocketImpl orchidSocketImpl = new OrchidSocketImpl(this.torClient);
        return Tor.isAndroidRuntime() ? new AndroidSocket(orchidSocketImpl) : new Socket(orchidSocketImpl) { // from class: com.subgraph.orchid.sockets.OrchidSocketFactory.1
        };
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return createSocketInstance();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        return connectOrchidSocket(createSocketInstance(), str, i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        if (this.exceptionOnLocalBind) {
            throw new UnsupportedOperationException("Cannot bind to local address");
        }
        return createSocket(str, i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return connectOrchidSocket(createSocketInstance(), inetAddress.getHostAddress(), i);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        if (this.exceptionOnLocalBind) {
            throw new UnsupportedOperationException("Cannot bind to local address");
        }
        return createSocket(inetAddress, i);
    }
}
