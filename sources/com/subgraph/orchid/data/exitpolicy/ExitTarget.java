package com.subgraph.orchid.data.exitpolicy;

import com.subgraph.orchid.data.IPv4Address;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/exitpolicy/ExitTarget.class */
public interface ExitTarget {
    IPv4Address getAddress();

    String getHostname();

    int getPort();

    boolean isAddressTarget();
}
