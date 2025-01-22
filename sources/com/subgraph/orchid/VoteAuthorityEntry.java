package com.subgraph.orchid;

import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.directory.consensus.DirectorySignature;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/VoteAuthorityEntry.class */
public interface VoteAuthorityEntry {
    IPv4Address getAddress();

    String getContact();

    int getDirectoryPort();

    String getHostname();

    HexDigest getIdentity();

    String getNickname();

    int getRouterPort();

    List<DirectorySignature> getSignatures();

    HexDigest getVoteDigest();
}
