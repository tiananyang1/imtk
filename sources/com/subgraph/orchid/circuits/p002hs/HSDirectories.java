package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.crypto.TorRandom;
import com.subgraph.orchid.data.HexDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDirectories.class */
public class HSDirectories {
    private static final int DIR_CLUSTER_SZ = 3;
    private ConsensusDocument currentConsensus;
    private final Directory directory;
    private List<Router> hsDirectories = new ArrayList();
    private final TorRandom random = new TorRandom();

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSDirectories(Directory directory) {
        this.directory = directory;
    }

    private List<Router> getDirectoriesForDescriptorId(HexDigest hexDigest) {
        String hexDigest2 = hexDigest.toString();
        refreshFromDirectory();
        return selectDirectoriesAtIndex(getIndexForDescriptorId(hexDigest2));
    }

    private String getHexIdForIndex(int i) {
        return this.hsDirectories.get(i).getIdentityHash().toString();
    }

    private int getIndexForDescriptorId(String str) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.hsDirectories.size()) {
                return 0;
            }
            if (getHexIdForIndex(i2).compareTo(str) > 0) {
                return i2;
            }
            i = i2 + 1;
        }
    }

    private void randomShuffle(List<Router> list) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return;
            }
            swap(list, i2, this.random.nextInt(list.size()));
            i = i2 + 1;
        }
    }

    private void refreshFromDirectory() {
        ConsensusDocument currentConsensusDocument = this.directory.getCurrentConsensusDocument();
        if (this.currentConsensus == currentConsensusDocument) {
            return;
        }
        this.currentConsensus = currentConsensusDocument;
        this.hsDirectories.clear();
        for (Router router : this.directory.getAllRouters()) {
            if (router.isHSDirectory()) {
                this.hsDirectories.add(router);
            }
        }
        Collections.sort(this.hsDirectories, new Comparator<Router>() { // from class: com.subgraph.orchid.circuits.hs.HSDirectories.1
            @Override // java.util.Comparator
            public int compare(Router router2, Router router3) {
                return router2.getIdentityHash().toString().compareTo(router3.getIdentityHash().toString());
            }
        });
    }

    private List<Router> selectDirectoriesAtIndex(int i) {
        if (i < 0 || i >= this.hsDirectories.size()) {
            throw new IllegalArgumentException("idx = " + i);
        }
        if (this.hsDirectories.size() < 3) {
            throw new IllegalStateException();
        }
        List<Router> arrayList = new ArrayList<>(3);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 3) {
                randomShuffle(arrayList);
                return arrayList;
            }
            arrayList.add(this.hsDirectories.get(i));
            int i4 = i + 1;
            i = i4;
            if (i4 == this.hsDirectories.size()) {
                i = 0;
            }
            i2 = i3 + 1;
        }
    }

    private void swap(List<Router> list, int i, int i2) {
        if (i != i2) {
            Router router = list.get(i);
            list.set(i, list.get(i2));
            list.set(i2, router);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<HSDescriptorDirectory> getDirectoriesForHiddenService(HiddenService hiddenService) {
        ArrayList arrayList = new ArrayList(6);
        for (HexDigest hexDigest : hiddenService.getAllCurrentDescriptorIds()) {
            Iterator<Router> it = getDirectoriesForDescriptorId(hexDigest).iterator();
            while (it.hasNext()) {
                arrayList.add(new HSDescriptorDirectory(hexDigest, it.next()));
            }
        }
        return arrayList;
    }
}
