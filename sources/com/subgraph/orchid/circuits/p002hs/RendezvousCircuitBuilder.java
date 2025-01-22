package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.HiddenServiceCircuit;
import com.subgraph.orchid.InternalCircuit;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.CircuitManagerImpl;
import com.subgraph.orchid.crypto.TorTapKeyAgreement;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/RendezvousCircuitBuilder.class */
public class RendezvousCircuitBuilder implements Callable<HiddenServiceCircuit> {
    private final CircuitManagerImpl circuitManager;
    private final Directory directory;
    private final HiddenService hiddenService;
    private final Logger logger = Logger.getLogger(RendezvousCircuitBuilder.class.getName());
    private final HSDescriptor serviceDescriptor;

    public RendezvousCircuitBuilder(Directory directory, CircuitManagerImpl circuitManagerImpl, HiddenService hiddenService, HSDescriptor hSDescriptor) {
        this.directory = directory;
        this.circuitManager = circuitManagerImpl;
        this.hiddenService = hiddenService;
        this.serviceDescriptor = hSDescriptor;
    }

    private Circuit attemptOpenIntroductionCircuit(IntroductionPoint introductionPoint) {
        Router routerByIdentity = this.directory.getRouterByIdentity(introductionPoint.getIdentity());
        if (routerByIdentity == null) {
            return null;
        }
        try {
            return this.circuitManager.getCleanInternalCircuit().cannibalizeToIntroductionPoint(routerByIdentity);
        } catch (TorException e) {
            this.logger.fine("cannibalizeTo() failed : " + e.getMessage());
            return null;
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    private String logServiceName() {
        return this.hiddenService.getOnionAddressForLogging();
    }

    private IntroductionProcessor openIntroduction() {
        for (IntroductionPoint introductionPoint : this.serviceDescriptor.getShuffledIntroductionPoints()) {
            Circuit attemptOpenIntroductionCircuit = attemptOpenIntroductionCircuit(introductionPoint);
            if (attemptOpenIntroductionCircuit != null) {
                return new IntroductionProcessor(this.hiddenService, attemptOpenIntroductionCircuit, introductionPoint);
            }
        }
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public HiddenServiceCircuit call() throws Exception {
        this.logger.fine("Opening rendezvous circuit for " + logServiceName());
        InternalCircuit cleanInternalCircuit = this.circuitManager.getCleanInternalCircuit();
        this.logger.fine("Establishing rendezvous for " + logServiceName());
        RendezvousProcessor rendezvousProcessor = new RendezvousProcessor(cleanInternalCircuit);
        if (!rendezvousProcessor.establishRendezvous()) {
            cleanInternalCircuit.markForClose();
            return null;
        }
        this.logger.fine("Opening introduction circuit for " + logServiceName());
        IntroductionProcessor openIntroduction = openIntroduction();
        if (openIntroduction == null) {
            this.logger.info("Failed to open connection to any introduction point");
            cleanInternalCircuit.markForClose();
            return null;
        }
        this.logger.fine("Sending introduce cell for " + logServiceName());
        TorTapKeyAgreement torTapKeyAgreement = new TorTapKeyAgreement();
        boolean sendIntroduce = openIntroduction.sendIntroduce(openIntroduction.getServiceKey(), torTapKeyAgreement.getPublicKeyBytes(), rendezvousProcessor.getCookie(), rendezvousProcessor.getRendezvousRouter());
        openIntroduction.markCircuitForClose();
        if (!sendIntroduce) {
            cleanInternalCircuit.markForClose();
            return null;
        }
        this.logger.fine("Processing RV2 for " + logServiceName());
        HiddenServiceCircuit processRendezvous2 = rendezvousProcessor.processRendezvous2(torTapKeyAgreement);
        if (processRendezvous2 == null) {
            cleanInternalCircuit.markForClose();
        }
        this.logger.fine("Rendezvous circuit opened for " + logServiceName());
        return processRendezvous2;
    }
}
