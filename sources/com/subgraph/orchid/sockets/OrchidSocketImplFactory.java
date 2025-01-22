package com.subgraph.orchid.sockets;

import com.subgraph.orchid.TorClient;
import java.net.SocketImpl;
import java.net.SocketImplFactory;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/OrchidSocketImplFactory.class */
public class OrchidSocketImplFactory implements SocketImplFactory {
    private final TorClient torClient;

    public OrchidSocketImplFactory(TorClient torClient) {
        this.torClient = torClient;
    }

    @Override // java.net.SocketImplFactory
    public SocketImpl createSocketImpl() {
        return new OrchidSocketImpl(this.torClient);
    }
}
