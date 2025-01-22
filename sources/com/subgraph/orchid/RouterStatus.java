package com.subgraph.orchid;

import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.Timestamp;
import com.subgraph.orchid.data.exitpolicy.ExitPorts;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/RouterStatus.class */
public interface RouterStatus {
    IPv4Address getAddress();

    HexDigest getDescriptorDigest();

    int getDirectoryPort();

    int getEstimatedBandwidth();

    ExitPorts getExitPorts();

    HexDigest getIdentity();

    int getMeasuredBandwidth();

    HexDigest getMicrodescriptorDigest();

    String getNickname();

    Timestamp getPublicationTime();

    int getRouterPort();

    String getVersion();

    boolean hasBandwidth();

    boolean hasFlag(String str);

    boolean isDirectory();
}
