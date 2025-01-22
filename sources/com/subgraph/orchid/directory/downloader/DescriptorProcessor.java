package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.data.HexDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/DescriptorProcessor.class */
public class DescriptorProcessor {
    private static final int MAX_CLIENT_INTERVAL_WITHOUT_REQUEST = 600000;
    private static final int MAX_DL_PER_REQUEST = 96;
    private static final int MAX_DL_TO_DELAY = 16;
    private static final int MIN_DL_REQUESTS = 3;
    private final TorConfig config;
    private final Directory directory;
    private Date lastDescriptorDownload;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DescriptorProcessor(TorConfig torConfig, Directory directory) {
        this.config = torConfig;
        this.directory = directory;
    }

    private boolean canDownloadDescriptors(int i) {
        if (i >= 16) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        return this.lastDescriptorDownload == null || new Date().getTime() - this.lastDescriptorDownload.getTime() > 600000;
    }

    private List<HexDigest> createPartitionList(List<Router> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = i;
        while (true) {
            int i4 = i3;
            if (i4 >= i + i2 || i4 >= list.size()) {
                break;
            }
            arrayList.add(getDescriptorDigestForRouter(list.get(i4)));
            i3 = i4 + 1;
        }
        return arrayList;
    }

    private HexDigest getDescriptorDigestForRouter(Router router) {
        return useMicrodescriptors() ? router.getMicrodescriptorDigest() : router.getDescriptorDigest();
    }

    private List<List<HexDigest>> partitionDescriptors(List<Router> list) {
        int i;
        int size = list.size();
        ArrayList arrayList = new ArrayList();
        if (size <= 10) {
            arrayList.add(createPartitionList(list, 0, size));
            return arrayList;
        }
        if (size > 288) {
            for (int i2 = 0; i2 < list.size(); i2 += 96) {
                arrayList.add(createPartitionList(list, i2, 96));
            }
            return arrayList;
        }
        int i3 = size / 3;
        int i4 = 0;
        int i5 = size % 3;
        for (int i6 = 0; i6 < 3; i6++) {
            if (i5 != 0) {
                i = i3 + 1;
                i5--;
            } else {
                i = i3;
            }
            arrayList.add(createPartitionList(list, i4, i));
            i4 += i;
        }
        return arrayList;
    }

    private boolean useMicrodescriptors() {
        return this.config.getUseMicrodescriptors() != TorConfig.AutoBoolValue.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<List<HexDigest>> getDescriptorDigestsToDownload() {
        ConsensusDocument currentConsensusDocument = this.directory.getCurrentConsensusDocument();
        if (currentConsensusDocument == null || !currentConsensusDocument.isLive()) {
            return Collections.emptyList();
        }
        List<Router> routersWithDownloadableDescriptors = this.directory.getRoutersWithDownloadableDescriptors();
        if (!canDownloadDescriptors(routersWithDownloadableDescriptors.size())) {
            return Collections.emptyList();
        }
        this.lastDescriptorDownload = new Date();
        return partitionDescriptors(routersWithDownloadableDescriptors);
    }
}
