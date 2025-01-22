package com.subgraph.orchid;

import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/DirectoryCircuit.class */
public interface DirectoryCircuit extends Circuit {
    Stream openDirectoryStream(long j, boolean z) throws InterruptedException, TimeoutException, StreamConnectFailedException;
}
