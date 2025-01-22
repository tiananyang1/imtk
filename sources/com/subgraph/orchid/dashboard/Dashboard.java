package com.subgraph.orchid.dashboard;

import com.subgraph.orchid.Threading;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.misc.GuardedBy;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/dashboard/Dashboard.class */
public class Dashboard implements DashboardRenderable, DashboardRenderer {
    private static final String DASHBOARD_PORT_PROPERTY = "com.subgraph.orchid.dashboard.port";
    private static final int DEFAULT_FLAGS = 24;
    private static final int DEFAULT_LISTENING_PORT = 12345;
    private final Executor executor;

    @GuardedBy("this")
    private boolean isListening;

    @GuardedBy("this")
    private int listeningPort;

    @GuardedBy("this")
    private ServerSocket listeningSocket;
    private static final Logger logger = Logger.getLogger(Dashboard.class.getName());
    private static final IPv4Address LOCALHOST = IPv4Address.createFromString("127.0.0.1");

    @GuardedBy("this")
    private int flags = 24;
    private final List<DashboardRenderable> renderables = new CopyOnWriteArrayList();

    public Dashboard() {
        this.renderables.add(this);
        this.executor = Threading.newPool("Dashboard worker");
        this.listeningPort = chooseListeningPort();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptConnections(ServerSocket serverSocket) {
        while (true) {
            try {
                this.executor.execute(new DashboardConnection(this, serverSocket.accept()));
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    logger.warning("IOException on dashboard server socket: " + e);
                }
                stopListening();
                return;
            }
        }
    }

    private static int chooseListeningPort() {
        String property = System.getProperty(DASHBOARD_PORT_PROPERTY);
        int parsePortProperty = parsePortProperty(property);
        if (parsePortProperty > 0 && parsePortProperty <= 65535) {
            return parsePortProperty;
        }
        if (property == null) {
            return DEFAULT_LISTENING_PORT;
        }
        logger.warning("com.subgraph.orchid.dashboard.port was not a valid port value: " + property);
        return DEFAULT_LISTENING_PORT;
    }

    private void closeQuietly(ServerSocket serverSocket) {
        try {
            serverSocket.close();
        } catch (IOException e) {
        }
    }

    private Runnable createAcceptLoopRunnable(final ServerSocket serverSocket) {
        return new Runnable() { // from class: com.subgraph.orchid.dashboard.Dashboard.1
            @Override // java.lang.Runnable
            public void run() {
                Dashboard.this.acceptConnections(serverSocket);
            }
        };
    }

    private static int parsePortProperty(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void addRenderable(DashboardRenderable dashboardRenderable) {
        this.renderables.add(dashboardRenderable);
    }

    public void addRenderables(Object... objArr) {
        int length = objArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            Object obj = objArr[i2];
            if (obj instanceof DashboardRenderable) {
                this.renderables.add((DashboardRenderable) obj);
            }
            i = i2 + 1;
        }
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderable
    public void dashboardRender(DashboardRenderer dashboardRenderer, PrintWriter printWriter, int i) {
        printWriter.println("[Dashboard]");
        printWriter.println();
    }

    public void disableFlag(int i) {
        synchronized (this) {
            this.flags = i & this.flags;
        }
    }

    public void enableFlag(int i) {
        synchronized (this) {
            this.flags = i | this.flags;
        }
    }

    public boolean isEnabled(int i) {
        boolean z;
        synchronized (this) {
            z = (i & this.flags) != 0;
        }
        return z;
    }

    public boolean isEnabledByProperty() {
        return System.getProperty(DASHBOARD_PORT_PROPERTY) != null;
    }

    public boolean isListening() {
        boolean z;
        synchronized (this) {
            z = this.isListening;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void renderAll(PrintWriter printWriter) throws IOException {
        int i;
        synchronized (this) {
            i = this.flags;
        }
        Iterator<DashboardRenderable> it = this.renderables.iterator();
        while (it.hasNext()) {
            it.next().dashboardRender(this, printWriter, i);
        }
    }

    @Override // com.subgraph.orchid.dashboard.DashboardRenderer
    public void renderComponent(PrintWriter printWriter, int i, Object obj) throws IOException {
        if (obj instanceof DashboardRenderable) {
            ((DashboardRenderable) obj).dashboardRender(this, printWriter, i);
        }
    }

    public void setListeningPort(int i) {
        synchronized (this) {
            if (i != this.listeningPort) {
                this.listeningPort = i;
                if (this.isListening) {
                    stopListening();
                    startListening();
                }
            }
        }
    }

    public void startListening() {
        synchronized (this) {
            if (this.isListening) {
                return;
            }
            try {
                this.listeningSocket = new ServerSocket(this.listeningPort, 50, LOCALHOST.toInetAddress());
                this.isListening = true;
                logger.info("Dashboard listening on " + LOCALHOST + Constants.COLON_SEPARATOR + this.listeningPort);
                this.executor.execute(createAcceptLoopRunnable(this.listeningSocket));
            } catch (IOException e) {
                logger.warning("Failed to create listening Dashboard socket on port " + this.listeningPort + ": " + e);
            }
        }
    }

    public void stopListening() {
        synchronized (this) {
            if (this.isListening) {
                if (this.listeningSocket != null) {
                    closeQuietly(this.listeningSocket);
                    this.listeningSocket = null;
                }
                this.isListening = false;
            }
        }
    }
}
