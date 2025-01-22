package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/IntroductionPoint.class */
public class IntroductionPoint {
    private IPv4Address address;
    private HexDigest identity;
    private TorPublicKey onionKey;
    private int onionPort;
    private TorPublicKey serviceKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IntroductionPoint(HexDigest hexDigest) {
        this.identity = hexDigest;
    }

    public IPv4Address getAddress() {
        return this.address;
    }

    public HexDigest getIdentity() {
        return this.identity;
    }

    public TorPublicKey getOnionKey() {
        return this.onionKey;
    }

    public int getPort() {
        return this.onionPort;
    }

    public TorPublicKey getServiceKey() {
        return this.serviceKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValidDocument() {
        return (this.identity == null || this.address == null || this.onionPort == 0 || this.onionKey == null || this.serviceKey == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAddress(IPv4Address iPv4Address) {
        this.address = iPv4Address;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnionKey(TorPublicKey torPublicKey) {
        this.onionKey = torPublicKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnionPort(int i) {
        this.onionPort = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setServiceKey(TorPublicKey torPublicKey) {
        this.serviceKey = torPublicKey;
    }
}
