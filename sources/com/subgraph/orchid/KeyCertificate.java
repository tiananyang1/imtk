package com.subgraph.orchid;

import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.Timestamp;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/KeyCertificate.class */
public interface KeyCertificate extends Document {
    HexDigest getAuthorityFingerprint();

    TorPublicKey getAuthorityIdentityKey();

    TorPublicKey getAuthoritySigningKey();

    IPv4Address getDirectoryAddress();

    int getDirectoryPort();

    Timestamp getKeyExpiryTime();

    Timestamp getKeyPublishedTime();

    boolean isExpired();
}
