package com.subgraph.orchid.config;

import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/config/TorConfigBridgeLine.class */
public class TorConfigBridgeLine {
    private final IPv4Address address;
    private final HexDigest fingerprint;
    private final int port;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TorConfigBridgeLine(IPv4Address iPv4Address, int i, HexDigest hexDigest) {
        this.address = iPv4Address;
        this.port = i;
        this.fingerprint = hexDigest;
    }

    public IPv4Address getAddress() {
        return this.address;
    }

    public HexDigest getFingerprint() {
        return this.fingerprint;
    }

    public int getPort() {
        return this.port;
    }
}
