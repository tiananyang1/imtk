package com.subgraph.orchid.data;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/data/BandwidthHistory.class */
public class BandwidthHistory {
    private final int reportingInterval;
    private final Timestamp reportingTime;
    private final List<Integer> samples = new ArrayList();

    public BandwidthHistory(Timestamp timestamp, int i) {
        this.reportingTime = timestamp;
        this.reportingInterval = i;
    }

    public void addSample(int i) {
        this.samples.add(Integer.valueOf(i));
    }

    public int getReportingInterval() {
        return this.reportingInterval;
    }

    public Timestamp getReportingTime() {
        return this.reportingTime;
    }
}
