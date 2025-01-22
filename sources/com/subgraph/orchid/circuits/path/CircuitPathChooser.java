package com.subgraph.orchid.circuits.path;

import com.subgraph.orchid.Directory;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.circuits.guards.EntryGuards;
import com.subgraph.orchid.circuits.path.CircuitNodeChooser;
import com.subgraph.orchid.data.exitpolicy.ExitTarget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/CircuitPathChooser.class */
public class CircuitPathChooser {
    private final Directory directory;
    private final CircuitNodeChooser nodeChooser;
    private EntryGuards entryGuards = null;
    private boolean useEntryGuards = false;

    CircuitPathChooser(TorConfig torConfig, Directory directory, CircuitNodeChooser circuitNodeChooser) {
        this.directory = directory;
        this.nodeChooser = circuitNodeChooser;
    }

    private boolean areInSameSlash16(Router router, Router router2) {
        return (router.getAddress().getAddressData() & (-65536)) == (router2.getAddress().getAddressData() & (-65536));
    }

    private int countTargetSupport(Router router, List<ExitTarget> list) {
        Iterator<ExitTarget> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (routerSupportsTarget(router, it.next())) {
                i++;
            }
        }
        return i;
    }

    public static CircuitPathChooser create(TorConfig torConfig, Directory directory) {
        return new CircuitPathChooser(torConfig, directory, new CircuitNodeChooser(torConfig, directory));
    }

    private void excludeChosenRouterAndRelated(Router router, Set<Router> set) {
        set.add(router);
        for (Router router2 : this.directory.getAllRouters()) {
            if (areInSameSlash16(router, router2)) {
                set.add(router2);
            }
        }
        Iterator<String> it = router.getFamilyMembers().iterator();
        while (it.hasNext()) {
            Router routerByName = this.directory.getRouterByName(it.next());
            if (routerByName != null && isFamilyMember(routerByName.getFamilyMembers(), router)) {
                set.add(routerByName);
            }
        }
    }

    private List<Router> filterForExitTargets(List<Router> list, List<ExitTarget> list2) {
        int i;
        if (list2.isEmpty()) {
            return list;
        }
        int[] iArr = new int[list.size()];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = i3;
            if (i2 >= list.size()) {
                break;
            }
            iArr[i2] = countTargetSupport(list.get(i2), list2);
            int i4 = i;
            if (iArr[i2] > i) {
                i4 = iArr[i2];
            }
            i2++;
            i3 = i4;
        }
        if (i == 0) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= list.size()) {
                return arrayList;
            }
            if (iArr[i6] == i) {
                arrayList.add(list.get(i6));
            }
            i5 = i6 + 1;
        }
    }

    private List<Router> getUsableExitRouters() {
        ArrayList arrayList = new ArrayList();
        for (Router router : this.nodeChooser.getUsableRouters(true)) {
            if (router.isExit() && !router.isBadExit()) {
                arrayList.add(router);
            }
        }
        return arrayList;
    }

    private boolean isFamilyMember(Collection<String> collection, Router router) {
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            Router routerByName = this.directory.getRouterByName(it.next());
            if (routerByName != null && routerByName.equals(router)) {
                return true;
            }
        }
        return false;
    }

    private boolean routerSupportsTarget(Router router, ExitTarget exitTarget) {
        return exitTarget.isAddressTarget() ? router.exitPolicyAccepts(exitTarget.getAddress(), exitTarget.getPort()) : router.exitPolicyAccepts(exitTarget.getPort());
    }

    public List<Router> chooseDirectoryPath() throws InterruptedException {
        if (!this.useEntryGuards || !this.entryGuards.isUsingBridges()) {
            return Arrays.asList(this.nodeChooser.chooseDirectory());
        }
        Router chooseRandomGuard = this.entryGuards.chooseRandomGuard(Collections.emptySet());
        if (chooseRandomGuard != null) {
            return Arrays.asList(chooseRandomGuard);
        }
        throw new IllegalStateException("Failed to choose bridge for directory request");
    }

    public Router chooseEntryNode(final Set<Router> set) throws InterruptedException {
        return this.useEntryGuards ? this.entryGuards.chooseRandomGuard(set) : this.nodeChooser.chooseRandomNode(CircuitNodeChooser.WeightRule.WEIGHT_FOR_GUARD, new RouterFilter() { // from class: com.subgraph.orchid.circuits.path.CircuitPathChooser.1
            @Override // com.subgraph.orchid.circuits.path.RouterFilter
            public boolean filter(Router router) {
                return router.isPossibleGuard() && !set.contains(router);
            }
        });
    }

    public Router chooseExitNodeForTargets(List<ExitTarget> list) {
        return this.nodeChooser.chooseExitNode(filterForExitTargets(getUsableExitRouters(), list));
    }

    public List<Router> chooseInternalPath() throws InterruptedException, PathSelectionFailedException {
        return choosePathWithFinal(chooseMiddleNode(Collections.emptySet()));
    }

    Router chooseMiddleNode(final Set<Router> set) {
        return this.nodeChooser.chooseRandomNode(CircuitNodeChooser.WeightRule.WEIGHT_FOR_MID, new RouterFilter() { // from class: com.subgraph.orchid.circuits.path.CircuitPathChooser.2
            @Override // com.subgraph.orchid.circuits.path.RouterFilter
            public boolean filter(Router router) {
                return router.isFast() && !set.contains(router);
            }
        });
    }

    public List<Router> choosePathWithExit(Router router) throws InterruptedException, PathSelectionFailedException {
        return choosePathWithFinal(router);
    }

    public List<Router> choosePathWithFinal(Router router) throws InterruptedException, PathSelectionFailedException {
        HashSet hashSet = new HashSet();
        excludeChosenRouterAndRelated(router, hashSet);
        Router chooseMiddleNode = chooseMiddleNode(hashSet);
        if (chooseMiddleNode == null) {
            throw new PathSelectionFailedException("Failed to select suitable middle node");
        }
        excludeChosenRouterAndRelated(chooseMiddleNode, hashSet);
        Router chooseEntryNode = chooseEntryNode(hashSet);
        if (chooseEntryNode != null) {
            return Arrays.asList(chooseEntryNode, chooseMiddleNode, router);
        }
        throw new PathSelectionFailedException("Failed to select suitable entry node");
    }

    public void enableEntryGuards(EntryGuards entryGuards) {
        this.entryGuards = entryGuards;
        this.useEntryGuards = true;
    }
}
