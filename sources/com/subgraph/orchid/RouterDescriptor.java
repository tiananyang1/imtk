package com.subgraph.orchid;

import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.Timestamp;
import com.subgraph.orchid.data.exitpolicy.ExitPolicy;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/RouterDescriptor.class */
public interface RouterDescriptor extends Descriptor {
    boolean allowsSingleHopExits();

    boolean cachesExtraInfo();

    int getAverageBandwidth();

    int getBurstBandwidth();

    String getContact();

    int getDirectoryPort();

    ExitPolicy getExitPolicy();

    HexDigest getExtraInfoDigest();

    HexDigest getFingerprint();

    TorPublicKey getIdentityKey();

    String getNickname();

    int getObservedBandwidth();

    String getPlatform();

    Timestamp getPublishedTime();

    int getUptime();

    boolean isHibernating();

    boolean isHiddenServiceDirectory();

    boolean isNewerThan(RouterDescriptor routerDescriptor);

    boolean supportsEventDNS();
}
