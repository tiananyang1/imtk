package com.subgraph.orchid;

import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/HiddenServiceCircuit.class */
public interface HiddenServiceCircuit extends Circuit {
    Stream openStream(int i, long j) throws InterruptedException, TimeoutException, StreamConnectFailedException;
}
