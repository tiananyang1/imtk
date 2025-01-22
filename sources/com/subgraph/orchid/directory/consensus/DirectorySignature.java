package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.crypto.TorSignature;
import com.subgraph.orchid.data.HexDigest;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/DirectorySignature.class */
public class DirectorySignature {
    private final HexDigest identityDigest;
    private final TorSignature signature;
    private final HexDigest signingKeyDigest;
    private final boolean useSha256;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectorySignature(HexDigest hexDigest, HexDigest hexDigest2, TorSignature torSignature, boolean z) {
        this.identityDigest = hexDigest;
        this.signingKeyDigest = hexDigest2;
        this.signature = torSignature;
        this.useSha256 = z;
    }

    public HexDigest getIdentityDigest() {
        return this.identityDigest;
    }

    public TorSignature getSignature() {
        return this.signature;
    }

    public HexDigest getSigningKeyDigest() {
        return this.signingKeyDigest;
    }

    public boolean useSha256() {
        return this.useSha256;
    }
}
