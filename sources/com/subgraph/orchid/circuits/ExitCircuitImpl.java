package com.subgraph.orchid.circuits;

import com.subgraph.orchid.ExitCircuit;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.circuits.path.CircuitPathChooser;
import com.subgraph.orchid.circuits.path.PathSelectionFailedException;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.exitpolicy.ExitTarget;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/ExitCircuitImpl.class */
public class ExitCircuitImpl extends CircuitImpl implements ExitCircuit {
    private final Router exitRouter;
    private final Set<ExitTarget> failedExitRequests;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExitCircuitImpl(CircuitManagerImpl circuitManagerImpl, Router router) {
        super(circuitManagerImpl);
        this.exitRouter = router;
        this.failedExitRequests = new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExitCircuitImpl(CircuitManagerImpl circuitManagerImpl, List<Router> list) {
        super(circuitManagerImpl, list);
        this.exitRouter = list.get(list.size() - 1);
        this.failedExitRequests = new HashSet();
    }

    @Override // com.subgraph.orchid.ExitCircuit
    public boolean canHandleExitTo(ExitTarget exitTarget) {
        synchronized (this.failedExitRequests) {
            if (this.failedExitRequests.contains(exitTarget)) {
                return false;
            }
            if (isMarkedForClose()) {
                return false;
            }
            return exitTarget.isAddressTarget() ? this.exitRouter.exitPolicyAccepts(exitTarget.getAddress(), exitTarget.getPort()) : this.exitRouter.exitPolicyAccepts(exitTarget.getPort());
        }
    }

    @Override // com.subgraph.orchid.ExitCircuit
    public boolean canHandleExitToPort(int i) {
        return this.exitRouter.exitPolicyAccepts(i);
    }

    @Override // com.subgraph.orchid.circuits.CircuitImpl
    protected List<Router> choosePathForCircuit(CircuitPathChooser circuitPathChooser) throws InterruptedException, PathSelectionFailedException {
        return circuitPathChooser.choosePathWithExit(this.exitRouter);
    }

    @Override // com.subgraph.orchid.circuits.CircuitImpl
    protected String getCircuitTypeLabel() {
        return "Exit";
    }

    @Override // com.subgraph.orchid.ExitCircuit
    public Stream openExitStream(IPv4Address iPv4Address, int i, long j) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        return openExitStream(iPv4Address.toString(), i, j);
    }

    @Override // com.subgraph.orchid.ExitCircuit
    public Stream openExitStream(String str, int i, long j) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        StreamImpl createNewStream = createNewStream();
        try {
            createNewStream.openExit(str, i, j);
            return createNewStream;
        } catch (Exception e) {
            removeStream(createNewStream);
            return processStreamOpenException(e);
        }
    }

    @Override // com.subgraph.orchid.ExitCircuit
    public void recordFailedExitTarget(ExitTarget exitTarget) {
        synchronized (this.failedExitRequests) {
            this.failedExitRequests.add(exitTarget);
        }
    }
}
