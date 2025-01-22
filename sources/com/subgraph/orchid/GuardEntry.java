package com.subgraph.orchid;

import java.util.Date;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/GuardEntry.class */
public interface GuardEntry {
    void clearDownSince();

    Date getCreatedTime();

    Date getDownSince();

    String getIdentity();

    Date getLastConnectAttempt();

    String getNickname();

    Router getRouterForEntry();

    Date getUnlistedSince();

    String getVersion();

    boolean isAdded();

    void markAsDown();

    boolean testCurrentlyUsable();
}
