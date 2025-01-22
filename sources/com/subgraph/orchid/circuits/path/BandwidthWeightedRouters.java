package com.subgraph.orchid.circuits.path;

import com.subgraph.orchid.Router;
import com.subgraph.orchid.crypto.TorRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/BandwidthWeightedRouters.class */
public class BandwidthWeightedRouters {
    private static final double EPSILON = 0.1d;
    private static final long MAX_SCALE = 2305843009213693951L;
    private boolean isScaled;
    private double totalExitBw;
    private double totalGuardBw;
    private double totalNonExitBw;
    private int unknownCount;
    private final List<WeightedRouter> weightedRouters = new ArrayList();
    private final TorRandom random = new TorRandom();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/BandwidthWeightedRouters$WeightedRouter.class */
    public static class WeightedRouter {
        private boolean isUnknown;
        private final Router router;
        private long scaledBandwidth;
        private double weightedBandwidth;

        WeightedRouter(Router router, double d) {
            this.router = router;
            this.weightedBandwidth = d;
        }

        void scaleBandwidth(double d) {
            this.scaledBandwidth = Math.round(this.weightedBandwidth * d);
        }
    }

    private void adjustTotals(Router router, double d) {
        if (router.isExit()) {
            this.totalExitBw += d;
        } else {
            this.totalNonExitBw += d;
        }
        if (router.isPossibleGuard()) {
            this.totalGuardBw += d;
        }
    }

    private Router chooseFirstElementAboveRandom(long j) {
        Router router = null;
        long j2 = j;
        long j3 = 0;
        for (WeightedRouter weightedRouter : this.weightedRouters) {
            long j4 = j3 + weightedRouter.scaledBandwidth;
            j3 = j4;
            if (j4 > j2) {
                router = weightedRouter.router;
                j2 = Long.MAX_VALUE;
                j3 = j4;
            }
        }
        if (router != null) {
            return router;
        }
        List<WeightedRouter> list = this.weightedRouters;
        return list.get(list.size() - 1).router;
    }

    private void fixUnknownValues(long j, long j2) {
        for (WeightedRouter weightedRouter : this.weightedRouters) {
            if (weightedRouter.isUnknown) {
                double d = weightedRouter.router.isFast() ? j : j2;
                weightedRouter.weightedBandwidth = d;
                weightedRouter.isUnknown = false;
                adjustTotals(weightedRouter.router, d);
            }
        }
        this.unknownCount = 0;
        this.isScaled = false;
    }

    private long getScaledTotal() {
        if (!this.isScaled) {
            scaleRouterWeights();
        }
        long j = 0;
        Iterator<WeightedRouter> it = this.weightedRouters.iterator();
        while (it.hasNext()) {
            j += it.next().scaledBandwidth;
        }
        return j;
    }

    private double getWeightedTotal() {
        Iterator<WeightedRouter> it = this.weightedRouters.iterator();
        double d = 0.0d;
        while (true) {
            double d2 = d;
            if (!it.hasNext()) {
                return d2;
            }
            d = d2 + it.next().weightedBandwidth;
        }
    }

    private void scaleRouterWeights() {
        double weightedTotal = 2.305843009213694E18d / getWeightedTotal();
        Iterator<WeightedRouter> it = this.weightedRouters.iterator();
        while (it.hasNext()) {
            it.next().scaleBandwidth(weightedTotal);
        }
        this.isScaled = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addRouter(Router router, double d) {
        this.weightedRouters.add(new WeightedRouter(router, d));
        adjustTotals(router, d);
        this.isScaled = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addRouterUnknown(Router router) {
        WeightedRouter weightedRouter = new WeightedRouter(router, 0.0d);
        weightedRouter.isUnknown = true;
        this.weightedRouters.add(weightedRouter);
        this.unknownCount++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void adjustWeights(double d, double d2) {
        for (WeightedRouter weightedRouter : this.weightedRouters) {
            Router router = weightedRouter.router;
            if (router.isExit() && router.isPossibleGuard()) {
                weightedRouter.weightedBandwidth *= d * d2;
            } else if (router.isPossibleGuard()) {
                weightedRouter.weightedBandwidth *= d2;
            } else if (router.isExit()) {
                weightedRouter.weightedBandwidth *= d;
            }
        }
        scaleRouterWeights();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Router chooseRandomRouterByWeight() {
        long scaledTotal = getScaledTotal();
        if (scaledTotal != 0) {
            return chooseFirstElementAboveRandom(this.random.nextLong(scaledTotal));
        }
        if (this.weightedRouters.size() == 0) {
            return null;
        }
        return this.weightedRouters.get(this.random.nextInt(this.weightedRouters.size())).router;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fixUnknownValues() {
        if (this.unknownCount == 0) {
            return;
        }
        if (isTotalBandwidthZero()) {
            fixUnknownValues(40000L, 20000L);
            return;
        }
        long totalBandwidth = (long) (getTotalBandwidth() / (this.weightedRouters.size() - this.unknownCount));
        fixUnknownValues(totalBandwidth, totalBandwidth);
    }

    int getRouterCount() {
        return this.weightedRouters.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getTotalBandwidth() {
        return this.totalExitBw + this.totalNonExitBw;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getTotalExitBandwidth() {
        return this.totalExitBw;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getTotalGuardBandwidth() {
        return this.totalGuardBw;
    }

    int getUnknownCount() {
        return this.unknownCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTotalBandwidthZero() {
        return getTotalBandwidth() < EPSILON;
    }
}
