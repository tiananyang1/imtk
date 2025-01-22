package com.subgraph.orchid.circuits;

import com.subgraph.orchid.dashboard.DashboardRenderable;
import com.subgraph.orchid.dashboard.DashboardRenderer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitPredictor.class */
public class CircuitPredictor implements DashboardRenderable {
    private static final Integer INTERNAL_CIRCUIT_PORT_VALUE = 0;
    private static final long TIMEOUT_MS = 3600000;
    private final Map<Integer, Long> portsSeen = new HashMap();

    public CircuitPredictor() {
        addExitPortRequest(80);
        addInternalRequest();
    }

    private boolean isEntryExpired(Map.Entry<Integer, Long> entry, long j) {
        return j - entry.getValue().longValue() > TIMEOUT_MS;
    }

    private void removeExpiredPorts() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Long>> it = this.portsSeen.entrySet().iterator();
        while (it.hasNext()) {
            if (isEntryExpired(it.next(), currentTimeMillis)) {
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addExitPortRequest(int i) {
        synchronized (this.portsSeen) {
            this.portsSeen.put(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis()));
        }
    }

    void addInternalRequest() {
        addExitPortRequest(INTERNAL_CIRCUIT_PORT_VALUE.intValue());
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderable
    public void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) throws IOException {
        if ((i & 4) == 0) {
            return;
        }
        printWriter.println("[Predicted Ports] ");
        Iterator<Integer> it = this.portsSeen.keySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            printWriter.write(" " + intValue);
            Long l = this.portsSeen.get(Integer.valueOf(intValue));
            if (l != null) {
                printWriter.write(" (last seen " + TimeUnit.MINUTES.convert(System.currentTimeMillis() - l.longValue(), TimeUnit.MILLISECONDS) + " minutes ago)");
            }
            printWriter.println();
        }
        printWriter.println();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<PredictedPortTarget> getPredictedPortTargets() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = getPredictedPorts().iterator();
        while (it.hasNext()) {
            arrayList.add(new PredictedPortTarget(it.next().intValue()));
        }
        return arrayList;
    }

    Set<Integer> getPredictedPorts() {
        HashSet hashSet;
        synchronized (this.portsSeen) {
            removeExpiredPorts();
            hashSet = new HashSet(this.portsSeen.keySet());
            hashSet.remove(INTERNAL_CIRCUIT_PORT_VALUE);
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInternalPredicted() {
        boolean containsKey;
        synchronized (this.portsSeen) {
            removeExpiredPorts();
            containsKey = this.portsSeen.containsKey(INTERNAL_CIRCUIT_PORT_VALUE);
        }
        return containsKey;
    }
}
