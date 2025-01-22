package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.VoteAuthorityEntry;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/VoteAuthorityEntryImpl.class */
public class VoteAuthorityEntryImpl implements VoteAuthorityEntry {
    private IPv4Address address;
    private String contact;
    private String hostname;
    private HexDigest identity;
    private String nickname;
    private HexDigest voteDigest;
    private int dirport = -1;
    private int orport = -1;
    private final List<DirectorySignature> signatures = new ArrayList();

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public IPv4Address getAddress() {
        return this.address;
    }

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public String getContact() {
        return this.contact;
    }

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public int getDirectoryPort() {
        return this.dirport;
    }

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public String getHostname() {
        return this.hostname;
    }

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public HexDigest getIdentity() {
        return this.identity;
    }

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public String getNickname() {
        return this.nickname;
    }

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public int getRouterPort() {
        return this.orport;
    }

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public List<DirectorySignature> getSignatures() {
        return this.signatures;
    }

    @Override // com.subgraph.orchid.VoteAuthorityEntry
    public HexDigest getVoteDigest() {
        return this.voteDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAddress(IPv4Address iPv4Address) {
        this.address = iPv4Address;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContact(String str) {
        this.contact = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDirectoryPort(int i) {
        this.dirport = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHostname(String str) {
        this.hostname = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIdentity(HexDigest hexDigest) {
        this.identity = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNickname(String str) {
        this.nickname = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRouterPort(int i) {
        this.orport = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setVoteDigest(HexDigest hexDigest) {
        this.voteDigest = hexDigest;
    }
}
