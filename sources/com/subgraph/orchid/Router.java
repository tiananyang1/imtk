package com.subgraph.orchid;

import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Router.class */
public interface Router {
    boolean exitPolicyAccepts(int i);

    boolean exitPolicyAccepts(IPv4Address iPv4Address, int i);

    IPv4Address getAddress();

    int getAverageBandwidth();

    int getBurstBandwidth();

    String getCountryCode();

    Descriptor getCurrentDescriptor();

    HexDigest getDescriptorDigest();

    int getDirectoryPort();

    int getEstimatedBandwidth();

    Set<String> getFamilyMembers();

    HexDigest getIdentityHash();

    TorPublicKey getIdentityKey();

    int getMeasuredBandwidth();

    HexDigest getMicrodescriptorDigest();

    byte[] getNTorOnionKey();

    String getNickname();

    int getObservedBandwidth();

    TorPublicKey getOnionKey();

    int getOnionPort();

    String getVersion();

    boolean hasBandwidth();

    boolean isBadExit();

    boolean isDescriptorDownloadable();

    boolean isExit();

    boolean isFast();

    boolean isHSDirectory();

    boolean isHibernating();

    boolean isPossibleGuard();

    boolean isRunning();

    boolean isStable();

    boolean isValid();
}
