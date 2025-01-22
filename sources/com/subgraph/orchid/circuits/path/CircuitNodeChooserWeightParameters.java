package com.subgraph.orchid.circuits.path;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.circuits.path.CircuitNodeChooser;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/CircuitNodeChooserWeightParameters.class */
class CircuitNodeChooserWeightParameters {
    private static final String ONE = "one";
    private static final int VAR_COUNT = 8;
    private static final int VAR_WD = 3;
    private static final int VAR_WDB = 7;
    private static final int VAR_WE = 2;
    private static final int VAR_WEB = 6;
    private static final int VAR_WG = 0;
    private static final int VAR_WGB = 4;
    private static final int VAR_WM = 1;
    private static final int VAR_WMB = 5;
    private static final String ZERO = "zero";
    private final boolean isValid;
    private final double[] vars;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.circuits.path.CircuitNodeChooserWeightParameters$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/CircuitNodeChooserWeightParameters$1.class */
    public static /* synthetic */ class C03291 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$circuits$path$CircuitNodeChooser$WeightRule */
        static final /* synthetic */ int[] f192x6aade5d5 = new int[CircuitNodeChooser.WeightRule.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:29:0x004d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.circuits.path.CircuitNodeChooser$WeightRule[] r0 = com.subgraph.orchid.circuits.path.CircuitNodeChooser.WeightRule.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.circuits.path.CircuitNodeChooserWeightParameters.C03291.f192x6aade5d5 = r0
                int[] r0 = com.subgraph.orchid.circuits.path.CircuitNodeChooserWeightParameters.C03291.f192x6aade5d5     // Catch: java.lang.NoSuchFieldError -> L41
                com.subgraph.orchid.circuits.path.CircuitNodeChooser$WeightRule r1 = com.subgraph.orchid.circuits.path.CircuitNodeChooser.WeightRule.WEIGHT_FOR_GUARD     // Catch: java.lang.NoSuchFieldError -> L41
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L41
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L41
            L14:
                int[] r0 = com.subgraph.orchid.circuits.path.CircuitNodeChooserWeightParameters.C03291.f192x6aade5d5     // Catch: java.lang.NoSuchFieldError -> L41 java.lang.NoSuchFieldError -> L45
                com.subgraph.orchid.circuits.path.CircuitNodeChooser$WeightRule r1 = com.subgraph.orchid.circuits.path.CircuitNodeChooser.WeightRule.WEIGHT_FOR_MID     // Catch: java.lang.NoSuchFieldError -> L45
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L45
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L45
            L1f:
                int[] r0 = com.subgraph.orchid.circuits.path.CircuitNodeChooserWeightParameters.C03291.f192x6aade5d5     // Catch: java.lang.NoSuchFieldError -> L45 java.lang.NoSuchFieldError -> L49
                com.subgraph.orchid.circuits.path.CircuitNodeChooser$WeightRule r1 = com.subgraph.orchid.circuits.path.CircuitNodeChooser.WeightRule.WEIGHT_FOR_EXIT     // Catch: java.lang.NoSuchFieldError -> L49
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L49
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L49
            L2a:
                int[] r0 = com.subgraph.orchid.circuits.path.CircuitNodeChooserWeightParameters.C03291.f192x6aade5d5     // Catch: java.lang.NoSuchFieldError -> L49 java.lang.NoSuchFieldError -> L4d
                com.subgraph.orchid.circuits.path.CircuitNodeChooser$WeightRule r1 = com.subgraph.orchid.circuits.path.CircuitNodeChooser.WeightRule.WEIGHT_FOR_DIR     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L35:
                int[] r0 = com.subgraph.orchid.circuits.path.CircuitNodeChooserWeightParameters.C03291.f192x6aade5d5     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.subgraph.orchid.circuits.path.CircuitNodeChooser$WeightRule r1 = com.subgraph.orchid.circuits.path.CircuitNodeChooser.WeightRule.NO_WEIGHTING     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
                return
            L41:
                r4 = move-exception
                goto L14
            L45:
                r4 = move-exception
                goto L1f
            L49:
                r4 = move-exception
                goto L2a
            L4d:
                r4 = move-exception
                goto L35
            L51:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.path.CircuitNodeChooserWeightParameters.C03291.m3833clinit():void");
        }
    }

    private CircuitNodeChooserWeightParameters(double[] dArr, boolean z) {
        this.vars = dArr;
        this.isValid = z;
    }

    private double calculateWeight(boolean z, boolean z2, boolean z3) {
        return (z2 && z) ? z3 ? getWdb() * getWd() : getWd() : z2 ? z3 ? getWgb() * getWg() : getWg() : z ? z3 ? getWeb() * getWe() : getWe() : z3 ? getWmb() * getWm() : getWm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CircuitNodeChooserWeightParameters create(ConsensusDocument consensusDocument, CircuitNodeChooser.WeightRule weightRule) {
        double[] dArr = new double[8];
        return !populateVars(consensusDocument, (long) consensusDocument.getWeightScaleParameter(), getTagsForWeightRule(weightRule), dArr) ? new CircuitNodeChooserWeightParameters(new double[8], false) : new CircuitNodeChooserWeightParameters(dArr, true);
    }

    static String[] getTagsForWeightRule(CircuitNodeChooser.WeightRule weightRule) {
        int i = C03291.f192x6aade5d5[weightRule.ordinal()];
        if (i == 1) {
            return new String[]{"Wgg", "Wgm", ZERO, "Wgd", "Wgb", "Wmb", "Web", "Wdb"};
        }
        if (i == 2) {
            return new String[]{"Wmg", "Wmm", "Wme", "Wmd", "Wgb", "Wmb", "Web", "Wdb"};
        }
        if (i == 3) {
            return new String[]{"Wee", "Wem", "Wed", "Weg", "Wgb", "Wmb", "Web", "Wdb"};
        }
        if (i == 4) {
            return new String[]{"Wbe", "Wbm", "Wbd", "Wbg", ONE, ONE, ONE, ONE};
        }
        if (i == 5) {
            return new String[]{ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE};
        }
        throw new IllegalArgumentException("Unhandled WeightRule type: " + weightRule);
    }

    static boolean populateVars(ConsensusDocument consensusDocument, long j, String[] strArr, double[] dArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 8) {
                return true;
            }
            dArr[i2] = tagToVarValue(consensusDocument, j, strArr[i2]);
            if (dArr[i2] < 0.0d) {
                return false;
            }
            dArr[i2] = dArr[i2] / j;
            i = i2 + 1;
        }
    }

    static double tagToVarValue(ConsensusDocument consensusDocument, long j, String str) {
        if (str.equals(ZERO)) {
            return 0.0d;
        }
        if (str.equals(ONE)) {
            return 1.0d;
        }
        return consensusDocument.getBandwidthWeight(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double calculateWeightedBandwidth(Router router) {
        long kbToBytes = kbToBytes(router.getEstimatedBandwidth());
        boolean z = true;
        boolean z2 = router.isExit() && !router.isBadExit();
        boolean isPossibleGuard = router.isPossibleGuard();
        if (router.getDirectoryPort() == 0) {
            z = false;
        }
        return (calculateWeight(z2, isPossibleGuard, z) * kbToBytes) + 0.5d;
    }

    double getWd() {
        return this.vars[3];
    }

    double getWdb() {
        return this.vars[7];
    }

    double getWe() {
        return this.vars[2];
    }

    double getWeb() {
        return this.vars[6];
    }

    double getWg() {
        return this.vars[0];
    }

    double getWgb() {
        return this.vars[4];
    }

    double getWm() {
        return this.vars[1];
    }

    double getWmb() {
        return this.vars[5];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValid() {
        return this.isValid;
    }

    long kbToBytes(long j) {
        if (j > 9223372036854775L) {
            return Long.MAX_VALUE;
        }
        return j * 1000;
    }
}
