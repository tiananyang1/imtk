package com.subgraph.orchid;

import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Stream.class */
public interface Stream {
    void close();

    Circuit getCircuit();

    InputStream getInputStream();

    OutputStream getOutputStream();

    int getStreamId();

    CircuitNode getTargetNode();

    void waitForSendWindow();
}
