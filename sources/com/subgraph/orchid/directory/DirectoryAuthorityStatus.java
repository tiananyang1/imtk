package com.subgraph.orchid.directory;

import com.subgraph.orchid.RouterStatus;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.Timestamp;
import com.subgraph.orchid.data.exitpolicy.ExitPorts;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DirectoryAuthorityStatus.class */
public class DirectoryAuthorityStatus implements RouterStatus {
    private IPv4Address address;
    private int directoryPort;
    private Set<String> flags = new HashSet();
    private HexDigest identity;
    private String nickname;
    private int routerPort;
    private HexDigest v3Ident;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectoryAuthorityStatus() {
        addFlag("Authority");
        addFlag("V2Dir");
    }

    void addFlag(String str) {
        this.flags.add(str);
    }

    @Override // com.subgraph.orchid.RouterStatus
    public IPv4Address getAddress() {
        return this.address;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public HexDigest getDescriptorDigest() {
        return null;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public int getDirectoryPort() {
        return this.directoryPort;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public int getEstimatedBandwidth() {
        return 0;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public ExitPorts getExitPorts() {
        return null;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public HexDigest getIdentity() {
        return this.identity;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public int getMeasuredBandwidth() {
        return 0;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public HexDigest getMicrodescriptorDigest() {
        return null;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public String getNickname() {
        return this.nickname;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public Timestamp getPublicationTime() {
        return null;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public int getRouterPort() {
        return this.routerPort;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HexDigest getV3Ident() {
        return this.v3Ident;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public String getVersion() {
        return null;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public boolean hasBandwidth() {
        return false;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public boolean hasFlag(String str) {
        return this.flags.contains(str);
    }

    @Override // com.subgraph.orchid.RouterStatus
    public boolean isDirectory() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAddress(IPv4Address iPv4Address) {
        this.address = iPv4Address;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBridgeAuthority() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDirectoryPort(int i) {
        this.directoryPort = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHiddenServiceAuthority() {
        addFlag("HSDir");
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
        this.routerPort = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setV1Authority() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setV3Ident(HexDigest hexDigest) {
        this.v3Ident = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unsetHiddenServiceAuthority() {
        this.flags.remove("HSDir");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unsetV2Authority() {
        this.flags.remove("V2Dir");
    }
}
