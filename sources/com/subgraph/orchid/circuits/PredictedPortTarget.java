package com.subgraph.orchid.circuits;

import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.exitpolicy.ExitTarget;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/PredictedPortTarget.class */
public class PredictedPortTarget implements ExitTarget {
    final int port;

    public PredictedPortTarget(int i) {
        this.port = i;
    }

    @Override // com.subgraph.orchid.data.exitpolicy.ExitTarget
    public IPv4Address getAddress() {
        return new IPv4Address(0);
    }

    @Override // com.subgraph.orchid.data.exitpolicy.ExitTarget
    public String getHostname() {
        return "";
    }

    @Override // com.subgraph.orchid.data.exitpolicy.ExitTarget
    public int getPort() {
        return this.port;
    }

    @Override // com.subgraph.orchid.data.exitpolicy.ExitTarget
    public boolean isAddressTarget() {
        return false;
    }
}
