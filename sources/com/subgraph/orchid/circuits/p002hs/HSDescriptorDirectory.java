package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.Router;
import com.subgraph.orchid.data.HexDigest;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorDirectory.class */
public class HSDescriptorDirectory {
    private final HexDigest descriptorId;
    private final Router directory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSDescriptorDirectory(HexDigest hexDigest, Router router) {
        this.descriptorId = hexDigest;
        this.directory = router;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HexDigest getDescriptorId() {
        return this.descriptorId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Router getDirectory() {
        return this.directory;
    }

    public String toString() {
        return this.descriptorId + " : " + this.directory;
    }
}
