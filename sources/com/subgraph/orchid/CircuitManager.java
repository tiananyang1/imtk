package com.subgraph.orchid;

import com.subgraph.orchid.data.IPv4Address;
import java.util.List;
import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/CircuitManager.class */
public interface CircuitManager {
    public static final int DIRECTORY_PURPOSE_CERTIFICATES = 2;
    public static final int DIRECTORY_PURPOSE_CONSENSUS = 1;
    public static final int DIRECTORY_PURPOSE_DESCRIPTORS = 3;

    Circuit getCleanInternalCircuit() throws InterruptedException;

    DirectoryCircuit openDirectoryCircuit() throws OpenFailedException;

    DirectoryCircuit openDirectoryCircuitTo(List<Router> list) throws OpenFailedException;

    Stream openDirectoryStream() throws InterruptedException, TimeoutException, OpenFailedException;

    Stream openDirectoryStream(int i) throws InterruptedException, TimeoutException, OpenFailedException;

    ExitCircuit openExitCircuitTo(List<Router> list) throws OpenFailedException;

    Stream openExitStreamTo(IPv4Address iPv4Address, int i) throws InterruptedException, TimeoutException, OpenFailedException;

    Stream openExitStreamTo(String str, int i) throws InterruptedException, TimeoutException, OpenFailedException;

    InternalCircuit openInternalCircuitTo(List<Router> list) throws OpenFailedException;

    void startBuildingCircuits();

    void stopBuildingCircuits(boolean z);
}
