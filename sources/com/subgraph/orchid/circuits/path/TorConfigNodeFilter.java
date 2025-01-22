package com.subgraph.orchid.circuits.path;

import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/path/TorConfigNodeFilter.class */
public class TorConfigNodeFilter {
    private static final String ENTRY_NODES_FILTER = "EntryNodes";
    private static final String EXCLUDE_EXIT_NODES_FILTER = "ExcludeExitNodes";
    private static final String EXCLUDE_NODES_FILTER = "ExcludeNodes";
    private static final String EXIT_NODES_FILTER = "ExitNodes";
    private final Map<String, ConfigNodeFilter> filters = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public TorConfigNodeFilter(TorConfig torConfig) {
        addFilter(this.filters, EXCLUDE_NODES_FILTER, torConfig.getExcludeNodes());
        addFilter(this.filters, EXCLUDE_EXIT_NODES_FILTER, torConfig.getExcludeExitNodes());
        addFilter(this.filters, ENTRY_NODES_FILTER, torConfig.getEntryNodes());
        addFilter(this.filters, EXIT_NODES_FILTER, torConfig.getExitNodes());
    }

    private static void addFilter(Map<String, ConfigNodeFilter> map, String str, List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        map.put(str, ConfigNodeFilter.createFromStrings(list));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Router> filterExitCandidates(List<Router> list) {
        ArrayList arrayList = new ArrayList();
        for (Router router : list) {
            if (isExitNodeIncluded(router)) {
                arrayList.add(router);
            }
        }
        return arrayList;
    }

    boolean isExcludedByFilter(Router router, String str) {
        ConfigNodeFilter configNodeFilter = this.filters.get(str);
        if (configNodeFilter == null || configNodeFilter.isEmpty()) {
            return false;
        }
        return configNodeFilter.filter(router);
    }

    boolean isExitNodeIncluded(Router router) {
        return (!isIncludedByFilter(router, EXIT_NODES_FILTER) || isExcludedByFilter(router, EXCLUDE_EXIT_NODES_FILTER) || isExcludedByFilter(router, EXCLUDE_NODES_FILTER)) ? false : true;
    }

    boolean isIncludedByFilter(Router router, String str) {
        ConfigNodeFilter configNodeFilter = this.filters.get(str);
        if (configNodeFilter == null || configNodeFilter.isEmpty()) {
            return true;
        }
        return configNodeFilter.filter(router);
    }
}
