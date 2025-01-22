package com.subgraph.orchid.directory.router;

import com.subgraph.orchid.Descriptor;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.Tor;
import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.BandwidthHistory;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.Timestamp;
import com.subgraph.orchid.data.exitpolicy.ExitPolicy;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/RouterDescriptorImpl.class */
public class RouterDescriptorImpl implements RouterDescriptor {
    private IPv4Address address;
    private String contact;
    private HexDigest descriptorDigest;
    private int directoryPort;
    private HexDigest fingerprint;
    private boolean hibernating;
    private TorPublicKey identityKey;
    private long lastListed;
    private String nickname;
    private byte[] ntorOnionKey;
    private TorPublicKey onionKey;
    private String platform;
    private Timestamp published;
    private String rawDocumentData;
    private BandwidthHistory readHistory;
    private int routerPort;
    private int uptime;
    private BandwidthHistory writeHistory;
    private int averageBandwidth = -1;
    private int burstBandwidth = -1;
    private int observedBandwidth = -1;
    private ExitPolicy exitPolicy = new ExitPolicy();
    private Set<String> familyMembers = Collections.emptySet();
    private Set<Integer> linkProtocols = Collections.emptySet();
    private Set<Integer> circuitProtocols = Collections.emptySet();
    private boolean eventDNS = false;
    private boolean cachesExtraInfo = false;
    private boolean hiddenServiceDir = false;
    private HexDigest extraInfoDigest = null;
    private boolean allowSingleHopExits = false;
    private boolean hasValidSignature = false;
    private Descriptor.CacheLocation cacheLocation = Descriptor.CacheLocation.NOT_CACHED;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAcceptRule(String str) {
        this.exitPolicy.addAcceptRule(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addCircuitProtocolVersion(int i) {
        if (this.circuitProtocols.isEmpty()) {
            this.circuitProtocols = new HashSet();
        }
        this.circuitProtocols.add(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFamilyMember(String str) {
        if (this.familyMembers.isEmpty()) {
            this.familyMembers = new HashSet();
        }
        this.familyMembers.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addLinkProtocolVersion(int i) {
        if (this.linkProtocols.isEmpty()) {
            this.linkProtocols = new HashSet();
        }
        this.linkProtocols.add(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addRejectRule(String str) {
        this.exitPolicy.addRejectRule(str);
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public boolean allowsSingleHopExits() {
        return this.allowSingleHopExits;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public boolean cachesExtraInfo() {
        return this.cachesExtraInfo;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RouterDescriptorImpl)) {
            return false;
        }
        RouterDescriptorImpl routerDescriptorImpl = (RouterDescriptorImpl) obj;
        if (routerDescriptorImpl.getDescriptorDigest() == null || this.descriptorDigest == null) {
            return false;
        }
        return routerDescriptorImpl.getDescriptorDigest().equals(this.descriptorDigest);
    }

    @Override // com.subgraph.orchid.Descriptor
    public boolean exitPolicyAccepts(int i) {
        return this.exitPolicy.acceptsPort(i);
    }

    @Override // com.subgraph.orchid.Descriptor
    public boolean exitPolicyAccepts(IPv4Address iPv4Address, int i) {
        return this.exitPolicy.acceptsDestination(iPv4Address, i);
    }

    @Override // com.subgraph.orchid.Descriptor
    public IPv4Address getAddress() {
        return this.address;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public int getAverageBandwidth() {
        return this.averageBandwidth;
    }

    @Override // com.subgraph.orchid.Descriptor
    public int getBodyLength() {
        return this.rawDocumentData.length();
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public int getBurstBandwidth() {
        return this.burstBandwidth;
    }

    @Override // com.subgraph.orchid.Descriptor
    public Descriptor.CacheLocation getCacheLocation() {
        return this.cacheLocation;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public String getContact() {
        return this.contact;
    }

    @Override // com.subgraph.orchid.Descriptor
    public HexDigest getDescriptorDigest() {
        return this.descriptorDigest;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public int getDirectoryPort() {
        return this.directoryPort;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public ExitPolicy getExitPolicy() {
        return this.exitPolicy;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public HexDigest getExtraInfoDigest() {
        return this.extraInfoDigest;
    }

    @Override // com.subgraph.orchid.Descriptor
    public Set<String> getFamilyMembers() {
        return this.familyMembers;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public HexDigest getFingerprint() {
        return this.fingerprint;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public TorPublicKey getIdentityKey() {
        return this.identityKey;
    }

    @Override // com.subgraph.orchid.Descriptor
    public long getLastListed() {
        return this.lastListed;
    }

    @Override // com.subgraph.orchid.Descriptor
    public byte[] getNTorOnionKey() {
        return this.ntorOnionKey;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public String getNickname() {
        return this.nickname;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public int getObservedBandwidth() {
        return this.observedBandwidth;
    }

    @Override // com.subgraph.orchid.Descriptor
    public TorPublicKey getOnionKey() {
        return this.onionKey;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public String getPlatform() {
        return this.platform;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public Timestamp getPublishedTime() {
        return this.published;
    }

    @Override // com.subgraph.orchid.Document
    public ByteBuffer getRawDocumentBytes() {
        return getRawDocumentData() == null ? ByteBuffer.allocate(0) : ByteBuffer.wrap(getRawDocumentData().getBytes(Tor.getDefaultCharset()));
    }

    @Override // com.subgraph.orchid.Document
    public String getRawDocumentData() {
        return this.rawDocumentData;
    }

    public BandwidthHistory getReadHistory() {
        return this.readHistory;
    }

    @Override // com.subgraph.orchid.Descriptor
    public int getRouterPort() {
        return this.routerPort;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public int getUptime() {
        return this.uptime;
    }

    public BandwidthHistory getWriteHistory() {
        return this.writeHistory;
    }

    public int hashCode() {
        HexDigest hexDigest = this.descriptorDigest;
        if (hexDigest == null) {
            return 0;
        }
        return hexDigest.hashCode();
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public boolean isHibernating() {
        return this.hibernating;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public boolean isHiddenServiceDirectory() {
        return this.hiddenServiceDir;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public boolean isNewerThan(RouterDescriptor routerDescriptor) {
        return routerDescriptor.getPublishedTime().isBefore(this.published);
    }

    @Override // com.subgraph.orchid.Document
    public boolean isValidDocument() {
        if (!this.hasValidSignature || this.nickname == null || this.address == null || this.averageBandwidth == -1) {
            return false;
        }
        return ((this.routerPort == 0 && this.directoryPort == 0) || this.published == null || this.onionKey == null || this.identityKey == null || this.descriptorDigest == null) ? false : true;
    }

    public void print() {
        System.out.println("nickname: " + this.nickname + " IP: " + this.address + " port: " + this.routerPort);
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("directory port: ");
        sb.append(this.directoryPort);
        sb.append(" platform: ");
        sb.append(this.platform);
        printStream.println(sb.toString());
        System.out.println("Bandwidth(avg/burst/observed): " + this.averageBandwidth + "/" + this.burstBandwidth + "/" + this.observedBandwidth);
        PrintStream printStream2 = System.out;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Publication time: ");
        sb2.append(this.published);
        sb2.append(" Uptime: ");
        sb2.append(this.uptime);
        printStream2.println(sb2.toString());
        if (this.fingerprint != null) {
            System.out.println("Fingerprint: " + this.fingerprint);
        }
        if (this.contact != null) {
            System.out.println("Contact: " + this.contact);
        }
    }

    public void setAddress(IPv4Address iPv4Address) {
        this.address = iPv4Address;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAllowSingleHopExits() {
        this.allowSingleHopExits = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBandwidthValues(int i, int i2, int i3) {
        this.averageBandwidth = i;
        this.burstBandwidth = i2;
        this.observedBandwidth = i3;
    }

    @Override // com.subgraph.orchid.Descriptor
    public void setCacheLocation(Descriptor.CacheLocation cacheLocation) {
        this.cacheLocation = cacheLocation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCachesExtraInfo() {
        this.cachesExtraInfo = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContact(String str) {
        this.contact = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDescriptorHash(HexDigest hexDigest) {
        this.descriptorDigest = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDirectoryPort(int i) {
        this.directoryPort = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEventDNS() {
        this.eventDNS = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setExtraInfoDigest(HexDigest hexDigest) {
        this.extraInfoDigest = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFingerprint(HexDigest hexDigest) {
        this.fingerprint = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHibernating(boolean z) {
        this.hibernating = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHiddenServiceDir() {
        this.hiddenServiceDir = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIdentityKey(TorPublicKey torPublicKey) {
        this.identityKey = torPublicKey;
    }

    @Override // com.subgraph.orchid.Descriptor
    public void setLastListed(long j) {
        this.lastListed = j;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNtorOnionKey(byte[] bArr) {
        this.ntorOnionKey = bArr;
    }

    public void setOnionKey(TorPublicKey torPublicKey) {
        this.onionKey = torPublicKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPlatform(String str) {
        this.platform = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPublished(Timestamp timestamp) {
        this.published = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRawDocumentData(String str) {
        this.rawDocumentData = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setReadHistory(BandwidthHistory bandwidthHistory) {
        this.readHistory = bandwidthHistory;
    }

    public void setRouterPort(int i) {
        this.routerPort = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUptime(int i) {
        this.uptime = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValidSignature() {
        this.hasValidSignature = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWriteHistory(BandwidthHistory bandwidthHistory) {
        this.writeHistory = bandwidthHistory;
    }

    @Override // com.subgraph.orchid.RouterDescriptor
    public boolean supportsEventDNS() {
        return this.eventDNS;
    }

    public String toString() {
        return "Router Descriptor: (name: " + this.nickname + " orport=" + this.routerPort + " dirport=" + this.directoryPort + " address=" + this.address + " platform=" + this.platform + " published=" + this.published.getDate() + ")";
    }
}
