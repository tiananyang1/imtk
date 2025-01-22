package com.subgraph.orchid.directory;

import com.subgraph.orchid.Descriptor;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.RouterStatus;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.geoip.CountryCodeService;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Collections;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/RouterImpl.class */
public class RouterImpl implements Router {
    private volatile String cachedCountryCode;
    private Descriptor descriptor;
    private final Directory directory;
    private final HexDigest identityHash;
    protected RouterStatus status;

    /* JADX INFO: Access modifiers changed from: protected */
    public RouterImpl(Directory directory, RouterStatus routerStatus) {
        this.directory = directory;
        this.identityHash = routerStatus.getIdentity();
        this.status = routerStatus;
        refreshDescriptor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RouterImpl createFromRouterStatus(Directory directory, RouterStatus routerStatus) {
        return new RouterImpl(directory, routerStatus);
    }

    private RouterDescriptor downcastDescriptor() {
        refreshDescriptor();
        Descriptor descriptor = this.descriptor;
        if (descriptor instanceof RouterDescriptor) {
            return (RouterDescriptor) descriptor;
        }
        return null;
    }

    private void refreshDescriptor() {
        synchronized (this) {
            if (this.descriptor == null && this.directory != null) {
                if (this.status.getMicrodescriptorDigest() != null) {
                    this.descriptor = this.directory.getMicrodescriptorFromCache(this.status.getMicrodescriptorDigest());
                } else if (this.status.getDescriptorDigest() != null) {
                    this.descriptor = this.directory.getBasicDescriptorFromCache(this.status.getDescriptorDigest());
                }
            }
        }
    }

    @Override // com.subgraph.orchid.Router
    public boolean exitPolicyAccepts(int i) {
        return exitPolicyAccepts(null, i);
    }

    @Override // com.subgraph.orchid.Router
    public boolean exitPolicyAccepts(IPv4Address iPv4Address, int i) {
        refreshDescriptor();
        Descriptor descriptor = this.descriptor;
        if (descriptor == null) {
            return false;
        }
        return iPv4Address == null ? descriptor.exitPolicyAccepts(i) : descriptor.exitPolicyAccepts(iPv4Address, i);
    }

    @Override // com.subgraph.orchid.Router
    public IPv4Address getAddress() {
        return this.status.getAddress();
    }

    @Override // com.subgraph.orchid.Router
    public int getAverageBandwidth() {
        RouterDescriptor downcastDescriptor = downcastDescriptor();
        if (downcastDescriptor == null) {
            return 0;
        }
        return downcastDescriptor.getAverageBandwidth();
    }

    @Override // com.subgraph.orchid.Router
    public int getBurstBandwidth() {
        RouterDescriptor downcastDescriptor = downcastDescriptor();
        if (downcastDescriptor == null) {
            return 0;
        }
        return downcastDescriptor.getBurstBandwidth();
    }

    @Override // com.subgraph.orchid.Router
    public String getCountryCode() {
        String str = this.cachedCountryCode;
        String str2 = str;
        if (str == null) {
            str2 = CountryCodeService.getInstance().getCountryCodeForAddress(getAddress());
            this.cachedCountryCode = str2;
        }
        return str2;
    }

    @Override // com.subgraph.orchid.Router
    public Descriptor getCurrentDescriptor() {
        refreshDescriptor();
        return this.descriptor;
    }

    @Override // com.subgraph.orchid.Router
    public HexDigest getDescriptorDigest() {
        return this.status.getDescriptorDigest();
    }

    @Override // com.subgraph.orchid.Router
    public int getDirectoryPort() {
        return this.status.getDirectoryPort();
    }

    @Override // com.subgraph.orchid.Router
    public int getEstimatedBandwidth() {
        return this.status.getEstimatedBandwidth();
    }

    @Override // com.subgraph.orchid.Router
    public Set<String> getFamilyMembers() {
        refreshDescriptor();
        Descriptor descriptor = this.descriptor;
        return descriptor != null ? descriptor.getFamilyMembers() : Collections.emptySet();
    }

    @Override // com.subgraph.orchid.Router
    public HexDigest getIdentityHash() {
        return this.identityHash;
    }

    @Override // com.subgraph.orchid.Router
    public TorPublicKey getIdentityKey() {
        RouterDescriptor downcastDescriptor = downcastDescriptor();
        if (downcastDescriptor != null) {
            return downcastDescriptor.getIdentityKey();
        }
        return null;
    }

    @Override // com.subgraph.orchid.Router
    public int getMeasuredBandwidth() {
        return this.status.getMeasuredBandwidth();
    }

    @Override // com.subgraph.orchid.Router
    public HexDigest getMicrodescriptorDigest() {
        return this.status.getMicrodescriptorDigest();
    }

    @Override // com.subgraph.orchid.Router
    public byte[] getNTorOnionKey() {
        refreshDescriptor();
        Descriptor descriptor = this.descriptor;
        if (descriptor != null) {
            return descriptor.getNTorOnionKey();
        }
        return null;
    }

    @Override // com.subgraph.orchid.Router
    public String getNickname() {
        return this.status.getNickname();
    }

    @Override // com.subgraph.orchid.Router
    public int getObservedBandwidth() {
        RouterDescriptor downcastDescriptor = downcastDescriptor();
        if (downcastDescriptor == null) {
            return 0;
        }
        return downcastDescriptor.getObservedBandwidth();
    }

    @Override // com.subgraph.orchid.Router
    public TorPublicKey getOnionKey() {
        refreshDescriptor();
        Descriptor descriptor = this.descriptor;
        if (descriptor != null) {
            return descriptor.getOnionKey();
        }
        return null;
    }

    @Override // com.subgraph.orchid.Router
    public int getOnionPort() {
        return this.status.getRouterPort();
    }

    @Override // com.subgraph.orchid.Router
    public String getVersion() {
        return this.status.getVersion();
    }

    @Override // com.subgraph.orchid.Router
    public boolean hasBandwidth() {
        return this.status.hasBandwidth();
    }

    public boolean hasFlag(String str) {
        return this.status.hasFlag(str);
    }

    @Override // com.subgraph.orchid.Router
    public boolean isBadExit() {
        return hasFlag("BadExit");
    }

    @Override // com.subgraph.orchid.Router
    public boolean isDescriptorDownloadable() {
        refreshDescriptor();
        boolean z = false;
        if (this.descriptor != null) {
            return false;
        }
        if (System.currentTimeMillis() - this.status.getPublicationTime().getDate().getTime() > 600000) {
            z = true;
        }
        return z;
    }

    @Override // com.subgraph.orchid.Router
    public boolean isExit() {
        return hasFlag("Exit");
    }

    @Override // com.subgraph.orchid.Router
    public boolean isFast() {
        return hasFlag("Fast");
    }

    @Override // com.subgraph.orchid.Router
    public boolean isHSDirectory() {
        return hasFlag("HSDir");
    }

    @Override // com.subgraph.orchid.Router
    public boolean isHibernating() {
        RouterDescriptor downcastDescriptor = downcastDescriptor();
        if (downcastDescriptor == null) {
            return false;
        }
        return downcastDescriptor.isHibernating();
    }

    @Override // com.subgraph.orchid.Router
    public boolean isPossibleGuard() {
        return hasFlag("Guard");
    }

    @Override // com.subgraph.orchid.Router
    public boolean isRunning() {
        return hasFlag("Running");
    }

    @Override // com.subgraph.orchid.Router
    public boolean isStable() {
        return hasFlag("Stable");
    }

    @Override // com.subgraph.orchid.Router
    public boolean isValid() {
        return hasFlag("Valid");
    }

    public String toString() {
        return "Router[" + getNickname() + " (" + getAddress() + Constants.COLON_SEPARATOR + getOnionPort() + ")]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateStatus(RouterStatus routerStatus) {
        if (!this.identityHash.equals(routerStatus.getIdentity())) {
            throw new TorException("Identity hash does not match status update");
        }
        this.status = routerStatus;
        this.cachedCountryCode = null;
        this.descriptor = null;
        refreshDescriptor();
    }
}
