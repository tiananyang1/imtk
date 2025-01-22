package com.subgraph.orchid;

import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Descriptor.class */
public interface Descriptor extends Document {

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Descriptor$CacheLocation.class */
    public enum CacheLocation {
        NOT_CACHED,
        CACHED_CACHEFILE,
        CACHED_JOURNAL
    }

    boolean exitPolicyAccepts(int i);

    boolean exitPolicyAccepts(IPv4Address iPv4Address, int i);

    IPv4Address getAddress();

    int getBodyLength();

    CacheLocation getCacheLocation();

    HexDigest getDescriptorDigest();

    Set<String> getFamilyMembers();

    long getLastListed();

    byte[] getNTorOnionKey();

    TorPublicKey getOnionKey();

    int getRouterPort();

    void setCacheLocation(CacheLocation cacheLocation);

    void setLastListed(long j);
}
