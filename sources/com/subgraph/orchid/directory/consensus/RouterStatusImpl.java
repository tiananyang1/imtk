package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.RouterStatus;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.Timestamp;
import com.subgraph.orchid.data.exitpolicy.ExitPorts;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/RouterStatusImpl.class */
public class RouterStatusImpl implements RouterStatus {
    private IPv4Address address;
    private int bandwidthEstimate;
    private int bandwidthMeasured;
    private HexDigest digest;
    private int directoryPort;
    private ExitPorts exitPorts;
    private Set<String> flags = new HashSet();
    private boolean hasBandwidth;
    private HexDigest identity;
    private HexDigest microdescriptorDigest;
    private String nickname;
    private Timestamp publicationTime;
    private int routerPort;
    private String version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFlag(String str) {
        this.flags.add(str);
    }

    @Override // com.subgraph.orchid.RouterStatus
    public IPv4Address getAddress() {
        return this.address;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public HexDigest getDescriptorDigest() {
        return this.digest;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public int getDirectoryPort() {
        return this.directoryPort;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public int getEstimatedBandwidth() {
        return this.bandwidthEstimate;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public ExitPorts getExitPorts() {
        return this.exitPorts;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public HexDigest getIdentity() {
        return this.identity;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public int getMeasuredBandwidth() {
        return this.bandwidthMeasured;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public HexDigest getMicrodescriptorDigest() {
        return this.microdescriptorDigest;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public String getNickname() {
        return this.nickname;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public Timestamp getPublicationTime() {
        return this.publicationTime;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public int getRouterPort() {
        return this.routerPort;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public String getVersion() {
        return this.version;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public boolean hasBandwidth() {
        return this.hasBandwidth;
    }

    @Override // com.subgraph.orchid.RouterStatus
    public boolean hasFlag(String str) {
        return this.flags.contains(str);
    }

    @Override // com.subgraph.orchid.RouterStatus
    public boolean isDirectory() {
        return this.directoryPort != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAcceptedPorts(String str) {
        this.exitPorts = ExitPorts.createAcceptExitPorts(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAddress(IPv4Address iPv4Address) {
        this.address = iPv4Address;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDigest(HexDigest hexDigest) {
        this.digest = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDirectoryPort(int i) {
        this.directoryPort = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEstimatedBandwidth(int i) {
        this.bandwidthEstimate = i;
        this.hasBandwidth = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIdentity(HexDigest hexDigest) {
        this.identity = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMeasuredBandwidth(int i) {
        this.bandwidthMeasured = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMicrodescriptorDigest(HexDigest hexDigest) {
        this.microdescriptorDigest = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNickname(String str) {
        this.nickname = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPublicationTime(Timestamp timestamp) {
        this.publicationTime = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRejectedPorts(String str) {
        this.exitPorts = ExitPorts.createRejectExitPorts(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRouterPort(int i) {
        this.routerPort = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        return "Router: (" + this.nickname + " " + this.identity + " " + this.digest + " " + this.address + " " + this.routerPort + " " + this.directoryPort + " " + this.version + " " + this.exitPorts + ")";
    }
}
