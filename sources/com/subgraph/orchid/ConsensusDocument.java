package com.subgraph.orchid;

import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.Timestamp;
import java.util.List;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/ConsensusDocument.class */
public interface ConsensusDocument extends Document {

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/ConsensusDocument$ConsensusFlavor.class */
    public enum ConsensusFlavor {
        NS,
        MICRODESC
    }

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/ConsensusDocument$RequiredCertificate.class */
    public interface RequiredCertificate {
        HexDigest getAuthorityIdentity();

        int getDownloadFailureCount();

        HexDigest getSigningKey();

        void incrementDownloadFailureCount();
    }

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/ConsensusDocument$SignatureStatus.class */
    public enum SignatureStatus {
        STATUS_VERIFIED,
        STATUS_FAILED,
        STATUS_NEED_CERTS
    }

    int getBandwidthWeight(String str);

    int getCircWindowParameter();

    Set<String> getClientVersions();

    int getConsensusMethod();

    int getDistSeconds();

    ConsensusFlavor getFlavor();

    Timestamp getFreshUntilTime();

    Set<RequiredCertificate> getRequiredCertificates();

    List<RouterStatus> getRouterStatusEntries();

    Set<String> getServerVersions();

    HexDigest getSigningHash();

    HexDigest getSigningHash256();

    boolean getUseNTorHandshake();

    Timestamp getValidAfterTime();

    Timestamp getValidUntilTime();

    int getVoteSeconds();

    int getWeightScaleParameter();

    boolean isLive();

    SignatureStatus verifySignatures();
}
