package com.subgraph.orchid.circuits;

import com.subgraph.orchid.DirectoryCircuit;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.circuits.path.CircuitPathChooser;
import com.subgraph.orchid.circuits.path.PathSelectionFailedException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/DirectoryCircuitImpl.class */
public class DirectoryCircuitImpl extends CircuitImpl implements DirectoryCircuit {
    /* JADX INFO: Access modifiers changed from: protected */
    public DirectoryCircuitImpl(CircuitManagerImpl circuitManagerImpl, List<Router> list) {
        super(circuitManagerImpl, list);
    }

    @Override // com.subgraph.orchid.circuits.CircuitImpl
    protected List<Router> choosePathForCircuit(CircuitPathChooser circuitPathChooser) throws InterruptedException, PathSelectionFailedException {
        return this.prechosenPath != null ? this.prechosenPath : circuitPathChooser.chooseDirectoryPath();
    }

    @Override // com.subgraph.orchid.circuits.CircuitImpl
    protected String getCircuitTypeLabel() {
        return "Directory";
    }

    @Override // com.subgraph.orchid.DirectoryCircuit
    public Stream openDirectoryStream(long j, boolean z) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        StreamImpl createNewStream = createNewStream(z);
        try {
            createNewStream.openDirectory(j);
            return createNewStream;
        } catch (Exception e) {
            removeStream(createNewStream);
            return processStreamOpenException(e);
        }
    }
}
