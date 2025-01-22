package com.subgraph.orchid.circuits.guards;

import com.subgraph.orchid.ConnectionCache;
import com.subgraph.orchid.ConnectionIOException;
import com.subgraph.orchid.GuardEntry;
import com.subgraph.orchid.Router;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/guards/GuardProbeTask.class */
public class GuardProbeTask implements Runnable {
    private static final Logger logger = Logger.getLogger(GuardProbeTask.class.getName());
    private final ConnectionCache connectionCache;
    private final GuardEntry entry;
    private final EntryGuards entryGuards;

    public GuardProbeTask(ConnectionCache connectionCache, EntryGuards entryGuards, GuardEntry guardEntry) {
        this.connectionCache = connectionCache;
        this.entryGuards = entryGuards;
        this.entry = guardEntry;
    }

    @Override // java.lang.Runnable
    public void run() {
        Router routerForEntry = this.entry.getRouterForEntry();
        if (routerForEntry == null) {
            this.entryGuards.probeConnectionFailed(this.entry);
            return;
        }
        try {
            this.connectionCache.getConnectionTo(routerForEntry, false);
            this.entryGuards.probeConnectionSucceeded(this.entry);
        } catch (ConnectionIOException e) {
            logger.fine("IO exception probing entry guard " + routerForEntry + " : " + e);
            this.entryGuards.probeConnectionFailed(this.entry);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            this.entryGuards.probeConnectionFailed(this.entry);
        } catch (Exception e3) {
            logger.log(Level.WARNING, "Unexpected exception probing entry guard: " + e3, (Throwable) e3);
            this.entryGuards.probeConnectionFailed(this.entry);
        }
    }
}
