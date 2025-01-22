package com.subgraph.orchid.circuits.guards;

import com.subgraph.orchid.ConnectionCache;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.DirectoryDownloader;
import com.subgraph.orchid.GuardEntry;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Threading;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.circuits.path.CircuitNodeChooser;
import com.subgraph.orchid.circuits.path.RouterFilter;
import com.subgraph.orchid.crypto.TorRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/guards/EntryGuards.class */
public class EntryGuards {
    private static final int MIN_USABLE_GUARDS = 2;
    private static final int NUM_ENTRY_GUARDS = 3;
    private final Bridges bridges;
    private final TorConfig config;
    private final ConnectionCache connectionCache;
    private final Directory directory;
    private final CircuitNodeChooser nodeChooser;
    private static final Logger logger = Logger.getLogger(EntryGuards.class.getName());
    private static final long ONE_HOUR = hoursToMs(1);
    private static final long FOUR_HOURS = hoursToMs(4);
    private static final long SIX_HOURS = hoursToMs(6);
    private static final long EIGHTEEN_HOURS = hoursToMs(18);
    private static final long THIRTYSIX_HOURS = hoursToMs(36);
    private static final long THREE_DAYS = daysToMs(3);
    private static final long SEVEN_DAYS = daysToMs(7);
    private static final long THIRTY_DAYS = daysToMs(30);
    private static final long SIXTY_DAYS = daysToMs(60);
    private final TorRandom random = new TorRandom();
    private final Set<GuardEntry> pendingProbes = new HashSet();
    private final Object lock = new Object();
    private final Executor executor = Threading.newPool("EntryGuards worker");

    public EntryGuards(TorConfig torConfig, ConnectionCache connectionCache, DirectoryDownloader directoryDownloader, Directory directory) {
        this.config = torConfig;
        this.nodeChooser = new CircuitNodeChooser(torConfig, directory);
        this.connectionCache = connectionCache;
        this.directory = directory;
        this.bridges = new Bridges(torConfig, directoryDownloader);
    }

    private void addPendingInitialConnections(Set<Router> set) {
        Router routerForEntry;
        for (GuardEntry guardEntry : this.pendingProbes) {
            if (!guardEntry.isAdded() && (routerForEntry = guardEntry.getRouterForEntry()) != null) {
                set.add(routerForEntry);
            }
        }
    }

    private void addRouterIfUsableAndNotExcluded(GuardEntry guardEntry, Set<Router> set, List<Router> list) {
        Router routerForEntry;
        if (!guardEntry.testCurrentlyUsable() || guardEntry.getDownSince() != null || (routerForEntry = guardEntry.getRouterForEntry()) == null || set.contains(routerForEntry)) {
            return;
        }
        list.add(routerForEntry);
    }

    private Router chooseNewGuard(final Set<Router> set) {
        return this.nodeChooser.chooseRandomNode(CircuitNodeChooser.WeightRule.WEIGHT_FOR_GUARD, new RouterFilter() { // from class: com.subgraph.orchid.circuits.guards.EntryGuards.1
            @Override // com.subgraph.orchid.circuits.path.RouterFilter
            public boolean filter(Router router) {
                return router.isValid() && router.isPossibleGuard() && router.isRunning() && !set.contains(router);
            }
        });
    }

    private int countPendingInitialProbes() {
        Iterator<GuardEntry> it = this.pendingProbes.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!it.next().isAdded()) {
                i++;
            }
        }
        return i;
    }

    private static long daysToMs(long j) {
        return TimeUnit.MILLISECONDS.convert(j, TimeUnit.DAYS);
    }

    private Set<Router> getExcludedForChooseNew(Set<Router> set, List<Router> list) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(set);
        hashSet.addAll(list);
        addPendingInitialConnections(hashSet);
        return hashSet;
    }

    private List<Router> getMinimumUsableGuards(Set<Router> set, int i) throws InterruptedException {
        List<Router> usableGuardRouters;
        synchronized (this.lock) {
            testStatusOfAllGuards();
            while (true) {
                usableGuardRouters = getUsableGuardRouters(set);
                if (usableGuardRouters.size() < i) {
                    maybeChooseNew(usableGuardRouters.size(), i, getExcludedForChooseNew(set, usableGuardRouters));
                    this.lock.wait(5000L);
                }
            }
        }
        return usableGuardRouters;
    }

    private long getRetestInterval(long j) {
        return j < SIX_HOURS ? ONE_HOUR : j < THREE_DAYS ? FOUR_HOURS : j < SEVEN_DAYS ? EIGHTEEN_HOURS : THIRTYSIX_HOURS;
    }

    private List<Router> getUsableGuardRouters(Set<Router> set) {
        ArrayList arrayList = new ArrayList();
        Iterator<GuardEntry> it = this.directory.getGuardEntries().iterator();
        while (it.hasNext()) {
            addRouterIfUsableAndNotExcluded(it.next(), set, arrayList);
        }
        return arrayList;
    }

    private static long hoursToMs(long j) {
        return TimeUnit.MILLISECONDS.convert(j, TimeUnit.HOURS);
    }

    private void initialProbeSucceeded(GuardEntry guardEntry) {
        logger.fine("Probe connection to " + guardEntry.getRouterForEntry() + " succeeded.  Adding it as a new entry guard.");
        this.directory.addGuardEntry(guardEntry);
        retestAllUnreachable();
    }

    private boolean isExpired(GuardEntry guardEntry) {
        return new Date().getTime() - guardEntry.getCreatedTime().getTime() > SIXTY_DAYS;
    }

    private boolean isPermanentlyUnlisted(GuardEntry guardEntry) {
        Date unlistedSince = guardEntry.getUnlistedSince();
        boolean z = false;
        if (unlistedSince != null) {
            if (this.pendingProbes.contains(guardEntry)) {
                return false;
            }
            z = false;
            if (new Date().getTime() - unlistedSince.getTime() > THIRTY_DAYS) {
                z = true;
            }
        }
        return z;
    }

    private void launchEntryProbe(GuardEntry guardEntry) {
        if (!guardEntry.testCurrentlyUsable() || this.pendingProbes.contains(guardEntry)) {
            return;
        }
        this.pendingProbes.add(guardEntry);
        this.executor.execute(new GuardProbeTask(this.connectionCache, this, guardEntry));
    }

    private void maybeChooseNew(int i, int i2, Set<Router> set) {
        int countPendingInitialProbes = countPendingInitialProbes();
        int i3 = i;
        while (true) {
            int i4 = countPendingInitialProbes + i3;
            if (i4 >= i2) {
                return;
            }
            Router chooseNewGuard = chooseNewGuard(set);
            if (chooseNewGuard == null) {
                logger.warning("Need to add entry guards but no suitable guard routers are available");
                return;
            }
            logger.fine("Testing " + chooseNewGuard + " as a new guard since we only have " + i + " usable guards");
            launchEntryProbe(this.directory.createGuardEntryFor(chooseNewGuard));
            countPendingInitialProbes = i4;
            i3 = 1;
        }
    }

    private boolean needsUnreachableTest(GuardEntry guardEntry) {
        Date downSince = guardEntry.getDownSince();
        boolean z = false;
        if (downSince != null) {
            if (!guardEntry.testCurrentlyUsable()) {
                return false;
            }
            Date date = new Date();
            Date lastConnectAttempt = guardEntry.getLastConnectAttempt();
            long time = date.getTime() - downSince.getTime();
            z = false;
            if ((lastConnectAttempt == null ? time : date.getTime() - lastConnectAttempt.getTime()) > getRetestInterval(time)) {
                z = true;
            }
        }
        return z;
    }

    private void retestAllUnreachable() {
        for (GuardEntry guardEntry : this.directory.getGuardEntries()) {
            if (guardEntry.getDownSince() != null) {
                launchEntryProbe(guardEntry);
            }
        }
    }

    private void retestProbeFailed(GuardEntry guardEntry) {
        guardEntry.markAsDown();
    }

    private void retestProbeSucceeded(GuardEntry guardEntry) {
        guardEntry.clearDownSince();
    }

    private void testStatusOfAllGuards() {
        for (GuardEntry guardEntry : this.directory.getGuardEntries()) {
            if (isPermanentlyUnlisted(guardEntry) || isExpired(guardEntry)) {
                this.directory.removeGuardEntry(guardEntry);
            } else if (needsUnreachableTest(guardEntry)) {
                launchEntryProbe(guardEntry);
            }
        }
    }

    public Router chooseRandomGuard(Set<Router> set) throws InterruptedException {
        if (this.config.getUseBridges()) {
            return this.bridges.chooseRandomBridge(set);
        }
        List<Router> minimumUsableGuards = getMinimumUsableGuards(set, 2);
        return minimumUsableGuards.get(this.random.nextInt(Math.min(minimumUsableGuards.size(), 3)));
    }

    public boolean isUsingBridges() {
        return this.config.getUseBridges();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void probeConnectionFailed(GuardEntry guardEntry) {
        synchronized (this.lock) {
            this.pendingProbes.remove(guardEntry);
            if (guardEntry.isAdded()) {
                retestProbeFailed(guardEntry);
            }
            this.lock.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void probeConnectionSucceeded(GuardEntry guardEntry) {
        synchronized (this.lock) {
            this.pendingProbes.remove(guardEntry);
            if (guardEntry.isAdded()) {
                retestProbeSucceeded(guardEntry);
            } else {
                initialProbeSucceeded(guardEntry);
            }
        }
    }
}
