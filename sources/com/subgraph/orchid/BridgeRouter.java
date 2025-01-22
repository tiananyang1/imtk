package com.subgraph.orchid;

import com.subgraph.orchid.data.HexDigest;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/BridgeRouter.class */
public interface BridgeRouter extends Router {
    void setDescriptor(RouterDescriptor routerDescriptor);

    void setIdentity(HexDigest hexDigest);
}
