package com.subgraph.orchid;

import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.exitpolicy.ExitTarget;
import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/ExitCircuit.class */
public interface ExitCircuit extends Circuit {
    boolean canHandleExitTo(ExitTarget exitTarget);

    boolean canHandleExitToPort(int i);

    Stream openExitStream(IPv4Address iPv4Address, int i, long j) throws InterruptedException, TimeoutException, StreamConnectFailedException;

    Stream openExitStream(String str, int i, long j) throws InterruptedException, TimeoutException, StreamConnectFailedException;

    void recordFailedExitTarget(ExitTarget exitTarget);
}
