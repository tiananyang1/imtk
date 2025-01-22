package com.subgraph.orchid.dashboard;

import java.io.IOException;
import java.io.PrintWriter;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/dashboard/DashboardRenderable.class */
public interface DashboardRenderable {
    public static final int DASHBOARD_CIRCUITS = 8;
    public static final int DASHBOARD_CONNECTIONS = 1;
    public static final int DASHBOARD_CONNECTIONS_VERBOSE = 2;
    public static final int DASHBOARD_PREDICTED_PORTS = 4;
    public static final int DASHBOARD_STREAMS = 16;

    void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) throws IOException;
}
