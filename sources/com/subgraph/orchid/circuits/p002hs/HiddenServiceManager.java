package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.Directory;
import com.subgraph.orchid.HiddenServiceCircuit;
import com.subgraph.orchid.OpenFailedException;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.CircuitManagerImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HiddenServiceManager.class */
public class HiddenServiceManager {
    private static final int HS_STREAM_TIMEOUT = 20000;
    private static final int RENDEZVOUS_RETRY_COUNT = 5;
    private static final Logger logger = Logger.getLogger(HiddenServiceManager.class.getName());
    private final CircuitManagerImpl circuitManager;
    private final TorConfig config;
    private final Directory directory;
    private final Map<String, HiddenService> hiddenServices = new HashMap();
    private final HSDirectories hsDirectories;

    public HiddenServiceManager(TorConfig torConfig, Directory directory, CircuitManagerImpl circuitManagerImpl) {
        this.config = torConfig;
        this.directory = directory;
        this.hsDirectories = new HSDirectories(directory);
        this.circuitManager = circuitManagerImpl;
    }

    private HiddenService createHiddenServiceFor(String str) throws OpenFailedException {
        String str2;
        try {
            return new HiddenService(this.config, HiddenService.decodeOnion(str));
        } catch (TorException e) {
            if (this.config.getSafeLogging()) {
                str2 = "[scrubbed]";
            } else {
                str2 = str + ".onion";
            }
            throw new OpenFailedException("Failed to decode onion address " + str2 + " : " + e.getMessage());
        }
    }

    private HSDescriptor downloadDescriptorFor(HiddenService hiddenService) {
        logger.fine("Downloading HS descriptor for " + hiddenService.getOnionAddressForLogging());
        return new HSDescriptorDownloader(hiddenService, this.circuitManager, this.hsDirectories.getDirectoriesForHiddenService(hiddenService)).downloadDescriptor();
    }

    private HiddenServiceCircuit getCircuitTo(HiddenService hiddenService) throws OpenFailedException {
        HiddenServiceCircuit circuit;
        synchronized (this) {
            if (hiddenService.getCircuit() == null) {
                HiddenServiceCircuit openCircuitTo = openCircuitTo(hiddenService);
                if (openCircuitTo == null) {
                    throw new OpenFailedException("Failed to open circuit to " + hiddenService.getOnionAddressForLogging());
                }
                hiddenService.setCircuit(openCircuitTo);
            }
            circuit = hiddenService.getCircuit();
        }
        return circuit;
    }

    private HiddenServiceCircuit openCircuitTo(HiddenService hiddenService) throws OpenFailedException {
        HSDescriptor descriptorFor = getDescriptorFor(hiddenService);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 5) {
                throw new OpenFailedException("Failed to open circuit to " + hiddenService.getOnionAddressForLogging());
            }
            HiddenServiceCircuit openRendezvousCircuit = openRendezvousCircuit(hiddenService, descriptorFor);
            if (openRendezvousCircuit != null) {
                return openRendezvousCircuit;
            }
            i = i2 + 1;
        }
    }

    private HiddenServiceCircuit openRendezvousCircuit(HiddenService hiddenService, HSDescriptor hSDescriptor) {
        try {
            return new RendezvousCircuitBuilder(this.directory, this.circuitManager, hiddenService, hSDescriptor).call();
        } catch (Exception e) {
            return null;
        }
    }

    HSDescriptor getDescriptorFor(HiddenService hiddenService) throws OpenFailedException {
        if (hiddenService.hasCurrentDescriptor()) {
            return hiddenService.getDescriptor();
        }
        HSDescriptor downloadDescriptorFor = downloadDescriptorFor(hiddenService);
        if (downloadDescriptorFor != null) {
            hiddenService.setDescriptor(downloadDescriptorFor);
            return downloadDescriptorFor;
        }
        String str = "Failed to download HS descriptor for " + hiddenService.getOnionAddressForLogging();
        logger.info(str);
        throw new OpenFailedException(str);
    }

    HiddenService getHiddenServiceForOnion(String str) throws OpenFailedException {
        HiddenService hiddenService;
        String str2 = str;
        if (str.endsWith(".onion")) {
            str2 = str.substring(0, str.length() - 6);
        }
        synchronized (this.hiddenServices) {
            if (!this.hiddenServices.containsKey(str2)) {
                this.hiddenServices.put(str2, createHiddenServiceFor(str2));
            }
            hiddenService = this.hiddenServices.get(str2);
        }
        return hiddenService;
    }

    public Stream getStreamTo(String str, int i) throws OpenFailedException, InterruptedException, TimeoutException {
        HiddenService hiddenServiceForOnion = getHiddenServiceForOnion(str);
        try {
            return getCircuitTo(hiddenServiceForOnion).openStream(i, 20000L);
        } catch (StreamConnectFailedException e) {
            throw new OpenFailedException("Failed to open stream to hidden service " + hiddenServiceForOnion.getOnionAddressForLogging() + " reason " + e.getReason());
        }
    }
}
