package com.subgraph.orchid.circuits;

import com.subgraph.orchid.ExitCircuit;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/OpenExitStreamTask.class */
public class OpenExitStreamTask implements Runnable {
    private static final Logger logger = Logger.getLogger(OpenExitStreamTask.class.getName());
    private final ExitCircuit circuit;
    private final StreamExitRequest exitRequest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenExitStreamTask(ExitCircuit exitCircuit, StreamExitRequest streamExitRequest) {
        this.circuit = exitCircuit;
        this.exitRequest = streamExitRequest;
    }

    private Stream tryOpenExitStream() throws InterruptedException, TimeoutException, StreamConnectFailedException {
        return this.exitRequest.isAddressTarget() ? this.circuit.openExitStream(this.exitRequest.getAddress(), this.exitRequest.getPort(), this.exitRequest.getStreamTimeout()) : this.circuit.openExitStream(this.exitRequest.getHostname(), this.exitRequest.getPort(), this.exitRequest.getStreamTimeout());
    }

    @Override // java.lang.Runnable
    public void run() {
        logger.fine("Attempting to open stream to " + this.exitRequest);
        try {
            this.exitRequest.setCompletedSuccessfully(tryOpenExitStream());
        } catch (StreamConnectFailedException e) {
            if (e.isReasonRetryable()) {
                this.circuit.markForClose();
                this.exitRequest.setStreamOpenFailure(e.getReason());
            } else {
                this.exitRequest.setExitFailed();
                this.circuit.recordFailedExitTarget(this.exitRequest);
            }
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            this.exitRequest.setInterrupted();
        } catch (TimeoutException e3) {
            this.circuit.markForClose();
            this.exitRequest.setCompletedTimeout();
        }
    }
}
