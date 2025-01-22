package com.subgraph.orchid.circuits.guards;

import com.subgraph.orchid.BridgeRouter;
import com.subgraph.orchid.Descriptor;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.geoip.CountryCodeService;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/guards/BridgeRouterImpl.class */
public class BridgeRouterImpl implements BridgeRouter {
    private final IPv4Address address;
    private volatile String cachedCountryCode;
    private Descriptor descriptor;
    private HexDigest identity;
    private final int port;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BridgeRouterImpl(IPv4Address iPv4Address, int i) {
        this.address = iPv4Address;
        this.port = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BridgeRouterImpl bridgeRouterImpl = (BridgeRouterImpl) obj;
        IPv4Address iPv4Address = this.address;
        if (iPv4Address == null) {
            if (bridgeRouterImpl.address != null) {
                return false;
            }
        } else if (!iPv4Address.equals(bridgeRouterImpl.address)) {
            return false;
        }
        return this.port == bridgeRouterImpl.port;
    }

    @Override // com.subgraph.orchid.Router
    public boolean exitPolicyAccepts(int i) {
        return false;
    }

    @Override // com.subgraph.orchid.Router
    public boolean exitPolicyAccepts(IPv4Address iPv4Address, int i) {
        return false;
    }

    @Override // com.subgraph.orchid.Router
    public IPv4Address getAddress() {
        return this.address;
    }

    @Override // com.subgraph.orchid.Router
    public int getAverageBandwidth() {
        return 0;
    }

    @Override // com.subgraph.orchid.Router
    public int getBurstBandwidth() {
        return 0;
    }

    @Override // com.subgraph.orchid.Router
    public String getCountryCode() {
        String str = this.cachedCountryCode;
        String str2 = str;
        if (str == null) {
            str2 = CountryCodeService.getInstance().getCountryCodeForAddress(getAddress());
            this.cachedCountryCode = str2;
        }
        return str2;
    }

    @Override // com.subgraph.orchid.Router
    public Descriptor getCurrentDescriptor() {
        return this.descriptor;
    }

    @Override // com.subgraph.orchid.Router
    public HexDigest getDescriptorDigest() {
        return null;
    }

    @Override // com.subgraph.orchid.Router
    public int getDirectoryPort() {
        return 0;
    }

    @Override // com.subgraph.orchid.Router
    public int getEstimatedBandwidth() {
        return 0;
    }

    @Override // com.subgraph.orchid.Router
    public Set<String> getFamilyMembers() {
        Descriptor descriptor = this.descriptor;
        return descriptor != null ? descriptor.getFamilyMembers() : Collections.emptySet();
    }

    public HexDigest getIdentity() {
        return this.identity;
    }

    @Override // com.subgraph.orchid.Router
    public HexDigest getIdentityHash() {
        return this.identity;
    }

    @Override // com.subgraph.orchid.Router
    public TorPublicKey getIdentityKey() {
        return null;
    }

    @Override // com.subgraph.orchid.Router
    public int getMeasuredBandwidth() {
        return 0;
    }

    @Override // com.subgraph.orchid.Router
    public HexDigest getMicrodescriptorDigest() {
        return null;
    }

    @Override // com.subgraph.orchid.Router
    public byte[] getNTorOnionKey() {
        Descriptor descriptor = this.descriptor;
        if (descriptor != null) {
            return descriptor.getNTorOnionKey();
        }
        return null;
    }

    @Override // com.subgraph.orchid.Router
    public String getNickname() {
        return toString();
    }

    @Override // com.subgraph.orchid.Router
    public int getObservedBandwidth() {
        return 0;
    }

    @Override // com.subgraph.orchid.Router
    public TorPublicKey getOnionKey() {
        Descriptor descriptor = this.descriptor;
        if (descriptor != null) {
            return descriptor.getOnionKey();
        }
        return null;
    }

    @Override // com.subgraph.orchid.Router
    public int getOnionPort() {
        return this.port;
    }

    @Override // com.subgraph.orchid.Router
    public String getVersion() {
        return "";
    }

    @Override // com.subgraph.orchid.Router
    public boolean hasBandwidth() {
        return false;
    }

    public int hashCode() {
        IPv4Address iPv4Address = this.address;
        return (((iPv4Address == null ? 0 : iPv4Address.hashCode()) + 31) * 31) + this.port;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isBadExit() {
        return false;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isDescriptorDownloadable() {
        return false;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isExit() {
        return false;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isFast() {
        return true;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isHSDirectory() {
        return false;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isHibernating() {
        Descriptor descriptor = this.descriptor;
        if (descriptor instanceof RouterDescriptor) {
            return ((RouterDescriptor) descriptor).isHibernating();
        }
        return false;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isPossibleGuard() {
        return true;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isRunning() {
        return true;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isStable() {
        return true;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isValid() {
        return true;
    }

    @Override // com.subgraph.orchid.BridgeRouter
    public void setDescriptor(RouterDescriptor routerDescriptor) {
        this.descriptor = routerDescriptor;
    }

    @Override // com.subgraph.orchid.BridgeRouter
    public void setIdentity(HexDigest hexDigest) {
        this.identity = hexDigest;
    }

    public String toString() {
        return "[Bridge " + this.address + Constants.COLON_SEPARATOR + this.port + "]";
    }
}
