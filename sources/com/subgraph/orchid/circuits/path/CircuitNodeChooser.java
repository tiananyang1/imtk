package com.subgraph.orchid.circuits.path;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.crypto.TorRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/CircuitNodeChooser.class */
public class CircuitNodeChooser {
    private static final double EPSILON = 0.1d;
    private static final Logger logger = Logger.getLogger(CircuitNodeChooser.class.getName());
    private final TorConfigNodeFilter configNodeFilter;
    private final Directory directory;
    private final TorRandom random = new TorRandom();

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/CircuitNodeChooser$WeightRule.class */
    public enum WeightRule {
        WEIGHT_FOR_DIR,
        WEIGHT_FOR_EXIT,
        WEIGHT_FOR_MID,
        WEIGHT_FOR_GUARD,
        NO_WEIGHTING
    }

    public CircuitNodeChooser(TorConfig torConfig, Directory directory) {
        this.directory = directory;
        this.configNodeFilter = new TorConfigNodeFilter(torConfig);
    }

    private double calculateWeight(boolean z, double d, double d2) {
        double d3 = 1.0d;
        if (!z) {
            if (d < EPSILON) {
                return 1.0d;
            }
            double d4 = 1.0d - (d2 / (d * 3.0d));
            d3 = d4;
            if (d4 <= 0.0d) {
                return 0.0d;
            }
        }
        return d3;
    }

    private Router chooseByBandwidth(List<Router> list, WeightRule weightRule) {
        Router chooseNodeByBandwidthWeights = chooseNodeByBandwidthWeights(list, weightRule);
        return chooseNodeByBandwidthWeights != null ? chooseNodeByBandwidthWeights : chooseNodeByBandwidth(list, weightRule);
    }

    private Router chooseNodeByBandwidth(List<Router> list, WeightRule weightRule) {
        BandwidthWeightedRouters bandwidthWeightedRouters = new BandwidthWeightedRouters();
        for (Router router : list) {
            long routerBandwidthBytes = getRouterBandwidthBytes(router);
            if (routerBandwidthBytes == -1) {
                bandwidthWeightedRouters.addRouterUnknown(router);
            } else {
                bandwidthWeightedRouters.addRouter(router, routerBandwidthBytes);
            }
        }
        bandwidthWeightedRouters.fixUnknownValues();
        if (!bandwidthWeightedRouters.isTotalBandwidthZero()) {
            computeFinalWeights(bandwidthWeightedRouters, weightRule);
            return bandwidthWeightedRouters.chooseRandomRouterByWeight();
        }
        if (list.size() == 0) {
            return null;
        }
        return list.get(this.random.nextInt(list.size()));
    }

    private Router chooseNodeByBandwidthWeights(List<Router> list, WeightRule weightRule) {
        ConsensusDocument currentConsensusDocument = this.directory.getCurrentConsensusDocument();
        if (currentConsensusDocument == null) {
            return null;
        }
        return computeWeightedBandwidths(list, currentConsensusDocument, weightRule).chooseRandomRouterByWeight();
    }

    private void computeFinalWeights(BandwidthWeightedRouters bandwidthWeightedRouters, WeightRule weightRule) {
        bandwidthWeightedRouters.adjustWeights(calculateWeight(weightRule == WeightRule.WEIGHT_FOR_EXIT, bandwidthWeightedRouters.getTotalExitBandwidth(), bandwidthWeightedRouters.getTotalBandwidth()), calculateWeight(weightRule == WeightRule.WEIGHT_FOR_GUARD, bandwidthWeightedRouters.getTotalGuardBandwidth(), bandwidthWeightedRouters.getTotalBandwidth()));
    }

    private BandwidthWeightedRouters computeWeightedBandwidths(List<Router> list, ConsensusDocument consensusDocument, WeightRule weightRule) {
        CircuitNodeChooserWeightParameters create = CircuitNodeChooserWeightParameters.create(consensusDocument, weightRule);
        if (!create.isValid()) {
            logger.warning("Got invalid bandwidth weights. Falling back to old selection method");
            return null;
        }
        BandwidthWeightedRouters bandwidthWeightedRouters = new BandwidthWeightedRouters();
        for (Router router : list) {
            bandwidthWeightedRouters.addRouter(router, create.calculateWeightedBandwidth(router));
        }
        return bandwidthWeightedRouters;
    }

    private List<Router> getFilteredRouters(RouterFilter routerFilter, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (Router router : getUsableRouters(z)) {
            if (routerFilter.filter(router)) {
                arrayList.add(router);
            }
        }
        return arrayList;
    }

    private long getRouterBandwidthBytes(Router router) {
        if (router.hasBandwidth()) {
            return kbToBytes(router.getEstimatedBandwidth());
        }
        return -1L;
    }

    private long kbToBytes(long j) {
        if (j > 9223372036854775L) {
            return Long.MAX_VALUE;
        }
        return j * 1000;
    }

    public Router chooseDirectory() {
        Router chooseByBandwidth = chooseByBandwidth(getFilteredRouters(new RouterFilter() { // from class: com.subgraph.orchid.circuits.path.CircuitNodeChooser.1
            @Override // com.subgraph.orchid.circuits.path.RouterFilter
            public boolean filter(Router router) {
                return router.getDirectoryPort() != 0;
            }
        }, false), WeightRule.WEIGHT_FOR_DIR);
        Router router = chooseByBandwidth;
        if (chooseByBandwidth == null) {
            router = this.directory.getRandomDirectoryAuthority();
        }
        return router;
    }

    public Router chooseExitNode(List<Router> list) {
        return chooseByBandwidth(this.configNodeFilter.filterExitCandidates(list), WeightRule.WEIGHT_FOR_EXIT);
    }

    public Router chooseRandomNode(WeightRule weightRule, RouterFilter routerFilter) {
        Router chooseByBandwidth = chooseByBandwidth(getFilteredRouters(routerFilter, true), weightRule);
        Router router = chooseByBandwidth;
        if (chooseByBandwidth == null) {
            router = null;
        }
        return router;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Router> getUsableRouters(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (Router router : this.directory.getAllRouters()) {
            if (router.isRunning() && router.isValid() && !router.isHibernating() && (!z || router.getCurrentDescriptor() != null)) {
                arrayList.add(router);
            }
        }
        return arrayList;
    }
}
