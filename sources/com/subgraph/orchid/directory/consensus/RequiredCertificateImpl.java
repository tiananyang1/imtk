package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.data.HexDigest;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/RequiredCertificateImpl.class */
public class RequiredCertificateImpl implements ConsensusDocument.RequiredCertificate {
    private int downloadFailureCount;
    private final HexDigest identity;
    private final HexDigest signingKey;

    public RequiredCertificateImpl(HexDigest hexDigest, HexDigest hexDigest2) {
        this.identity = hexDigest;
        this.signingKey = hexDigest2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RequiredCertificateImpl requiredCertificateImpl = (RequiredCertificateImpl) obj;
        HexDigest hexDigest = this.identity;
        if (hexDigest == null) {
            if (requiredCertificateImpl.identity != null) {
                return false;
            }
        } else if (!hexDigest.equals(requiredCertificateImpl.identity)) {
            return false;
        }
        HexDigest hexDigest2 = this.signingKey;
        return hexDigest2 == null ? requiredCertificateImpl.signingKey == null : hexDigest2.equals(requiredCertificateImpl.signingKey);
    }

    @Override // com.subgraph.orchid.ConsensusDocument.RequiredCertificate
    public HexDigest getAuthorityIdentity() {
        return this.identity;
    }

    @Override // com.subgraph.orchid.ConsensusDocument.RequiredCertificate
    public int getDownloadFailureCount() {
        return this.downloadFailureCount;
    }

    @Override // com.subgraph.orchid.ConsensusDocument.RequiredCertificate
    public HexDigest getSigningKey() {
        return this.signingKey;
    }

    public int hashCode() {
        HexDigest hexDigest = this.identity;
        int i = 0;
        int hashCode = hexDigest == null ? 0 : hexDigest.hashCode();
        HexDigest hexDigest2 = this.signingKey;
        if (hexDigest2 != null) {
            i = hexDigest2.hashCode();
        }
        return ((hashCode + 31) * 31) + i;
    }

    @Override // com.subgraph.orchid.ConsensusDocument.RequiredCertificate
    public void incrementDownloadFailureCount() {
        this.downloadFailureCount++;
    }
}
