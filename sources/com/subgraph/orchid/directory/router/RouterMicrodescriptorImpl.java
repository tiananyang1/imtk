package com.subgraph.orchid.directory.router;

import com.subgraph.orchid.Descriptor;
import com.subgraph.orchid.RouterMicrodescriptor;
import com.subgraph.orchid.Tor;
import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.exitpolicy.ExitPorts;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/RouterMicrodescriptorImpl.class */
public class RouterMicrodescriptorImpl implements RouterMicrodescriptor {
    private ExitPorts acceptPorts;
    private IPv4Address address;
    private HexDigest descriptorDigest;
    private long lastListed;
    private byte[] ntorOnionKey;
    private TorPublicKey onionKey;
    private String rawDocumentData;
    private ExitPorts rejectPorts;
    private int routerPort;
    private Set<String> familyMembers = Collections.emptySet();
    private Descriptor.CacheLocation cacheLocation = Descriptor.CacheLocation.NOT_CACHED;

    public void addAcceptPorts(String str) {
        this.acceptPorts = ExitPorts.createAcceptExitPorts(str);
    }

    public void addFamilyMember(String str) {
        if (this.familyMembers.isEmpty()) {
            this.familyMembers = new HashSet();
        }
        this.familyMembers.add(str);
    }

    public void addRejectPorts(String str) {
        this.rejectPorts = ExitPorts.createRejectExitPorts(str);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RouterMicrodescriptorImpl)) {
            return false;
        }
        RouterMicrodescriptorImpl routerMicrodescriptorImpl = (RouterMicrodescriptorImpl) obj;
        if (routerMicrodescriptorImpl.getDescriptorDigest() == null || this.descriptorDigest == null) {
            return false;
        }
        return routerMicrodescriptorImpl.getDescriptorDigest().equals(this.descriptorDigest);
    }

    @Override // com.subgraph.orchid.Descriptor
    public boolean exitPolicyAccepts(int i) {
        if (this.acceptPorts == null) {
            return false;
        }
        ExitPorts exitPorts = this.rejectPorts;
        if (exitPorts == null || exitPorts.acceptsPort(i)) {
            return this.acceptPorts.acceptsPort(i);
        }
        return false;
    }

    @Override // com.subgraph.orchid.Descriptor
    public boolean exitPolicyAccepts(IPv4Address iPv4Address, int i) {
        return exitPolicyAccepts(i);
    }

    @Override // com.subgraph.orchid.Descriptor
    public IPv4Address getAddress() {
        return this.address;
    }

    @Override // com.subgraph.orchid.Descriptor
    public int getBodyLength() {
        return this.rawDocumentData.length();
    }

    @Override // com.subgraph.orchid.Descriptor
    public Descriptor.CacheLocation getCacheLocation() {
        return this.cacheLocation;
    }

    @Override // com.subgraph.orchid.Descriptor
    public HexDigest getDescriptorDigest() {
        return this.descriptorDigest;
    }

    @Override // com.subgraph.orchid.Descriptor
    public Set<String> getFamilyMembers() {
        return this.familyMembers;
    }

    @Override // com.subgraph.orchid.Descriptor
    public long getLastListed() {
        return this.lastListed;
    }

    @Override // com.subgraph.orchid.Descriptor
    public byte[] getNTorOnionKey() {
        return this.ntorOnionKey;
    }

    @Override // com.subgraph.orchid.Descriptor
    public TorPublicKey getOnionKey() {
        return this.onionKey;
    }

    @Override // com.subgraph.orchid.Document
    public ByteBuffer getRawDocumentBytes() {
        return getRawDocumentData() == null ? ByteBuffer.allocate(0) : ByteBuffer.wrap(getRawDocumentData().getBytes(Tor.getDefaultCharset()));
    }

    @Override // com.subgraph.orchid.Document
    public String getRawDocumentData() {
        return this.rawDocumentData;
    }

    @Override // com.subgraph.orchid.Descriptor
    public int getRouterPort() {
        return this.routerPort;
    }

    public int hashCode() {
        HexDigest hexDigest = this.descriptorDigest;
        if (hexDigest == null) {
            return 0;
        }
        return hexDigest.hashCode();
    }

    @Override // com.subgraph.orchid.Document
    public boolean isValidDocument() {
        return (this.descriptorDigest == null || this.onionKey == null) ? false : true;
    }

    public void setAddress(IPv4Address iPv4Address) {
        this.address = iPv4Address;
    }

    @Override // com.subgraph.orchid.Descriptor
    public void setCacheLocation(Descriptor.CacheLocation cacheLocation) {
        this.cacheLocation = cacheLocation;
    }

    public void setDescriptorDigest(HexDigest hexDigest) {
        this.descriptorDigest = hexDigest;
    }

    @Override // com.subgraph.orchid.Descriptor
    public void setLastListed(long j) {
        this.lastListed = j;
    }

    public void setNtorOnionKey(byte[] bArr) {
        this.ntorOnionKey = bArr;
    }

    public void setOnionKey(TorPublicKey torPublicKey) {
        this.onionKey = torPublicKey;
    }

    public void setRawDocumentData(String str) {
        this.rawDocumentData = str;
    }

    public void setRouterPort(int i) {
        this.routerPort = i;
    }
}
