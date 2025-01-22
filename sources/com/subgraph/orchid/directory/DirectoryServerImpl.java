package com.subgraph.orchid.directory;

import com.subgraph.orchid.DirectoryServer;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.RouterStatus;
import com.subgraph.orchid.data.HexDigest;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DirectoryServerImpl.class */
public class DirectoryServerImpl extends RouterImpl implements DirectoryServer {
    private List<KeyCertificate> certificates;
    private boolean isBridgeAuthority;
    private boolean isExtraInfoCache;
    private boolean isHiddenServiceAuthority;
    private int port;
    private HexDigest v3Ident;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectoryServerImpl(RouterStatus routerStatus) {
        super(null, routerStatus);
        this.certificates = new ArrayList();
        this.isHiddenServiceAuthority = false;
        this.isBridgeAuthority = false;
        this.isExtraInfoCache = false;
    }

    private KeyCertificate getNewestCertificate() {
        KeyCertificate keyCertificate = null;
        for (KeyCertificate keyCertificate2 : this.certificates) {
            if (keyCertificate == null || getPublishedMilliseconds(keyCertificate) > getPublishedMilliseconds(keyCertificate2)) {
                keyCertificate = keyCertificate2;
            }
        }
        return keyCertificate;
    }

    private long getPublishedMilliseconds(KeyCertificate keyCertificate) {
        return keyCertificate.getKeyPublishedTime().getDate().getTime();
    }

    private boolean isMoreThan48HoursOlder(KeyCertificate keyCertificate, KeyCertificate keyCertificate2) {
        return getPublishedMilliseconds(keyCertificate) - getPublishedMilliseconds(keyCertificate2) > 172800000;
    }

    private void purgeExpiredCertificates() {
        Iterator<KeyCertificate> it = this.certificates.iterator();
        while (it.hasNext()) {
            if (it.next().isExpired()) {
                it.remove();
            }
        }
    }

    private void purgeOldCertificates() {
        if (this.certificates.size() < 2) {
            return;
        }
        KeyCertificate newestCertificate = getNewestCertificate();
        Iterator<KeyCertificate> it = this.certificates.iterator();
        while (it.hasNext()) {
            KeyCertificate next = it.next();
            if (next != newestCertificate && isMoreThan48HoursOlder(newestCertificate, next)) {
                it.remove();
            }
        }
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public void addCertificate(KeyCertificate keyCertificate) {
        if (!keyCertificate.getAuthorityFingerprint().equals(this.v3Ident)) {
            throw new IllegalArgumentException("This certificate does not appear to belong to this directory authority");
        }
        synchronized (this.certificates) {
            this.certificates.add(keyCertificate);
        }
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public KeyCertificate getCertificateByFingerprint(HexDigest hexDigest) {
        for (KeyCertificate keyCertificate : getCertificates()) {
            if (keyCertificate.getAuthoritySigningKey().getFingerprint().equals(hexDigest)) {
                return keyCertificate;
            }
        }
        return null;
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public List<KeyCertificate> getCertificates() {
        ArrayList arrayList;
        synchronized (this.certificates) {
            purgeExpiredCertificates();
            purgeOldCertificates();
            arrayList = new ArrayList(this.certificates);
        }
        return arrayList;
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public HexDigest getV3Identity() {
        return this.v3Ident;
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public boolean isBridgeAuthority() {
        return this.isBridgeAuthority;
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public boolean isExtraInfoCache() {
        return this.isExtraInfoCache;
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public boolean isHiddenServiceAuthority() {
        return this.isHiddenServiceAuthority;
    }

    public boolean isTrustedAuthority() {
        return true;
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public boolean isV2Authority() {
        return hasFlag("Authority") && hasFlag("V2Dir");
    }

    @Override // com.subgraph.orchid.DirectoryServer
    public boolean isV3Authority() {
        return hasFlag("Authority") && this.v3Ident != null;
    }

    @Override // com.subgraph.orchid.directory.RouterImpl, com.subgraph.orchid.Router
    public boolean isValid() {
        return true;
    }

    void setBridgeAuthority() {
        this.isBridgeAuthority = true;
    }

    void setExtraInfoCache() {
        this.isExtraInfoCache = true;
    }

    void setHiddenServiceAuthority() {
        this.isHiddenServiceAuthority = true;
    }

    void setPort(int i) {
        this.port = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setV3Ident(HexDigest hexDigest) {
        this.v3Ident = hexDigest;
    }

    @Override // com.subgraph.orchid.directory.RouterImpl
    public String toString() {
        if (this.v3Ident == null) {
            return "(Directory: " + getNickname() + " " + getAddress() + Constants.COLON_SEPARATOR + this.port + " fingerprint=" + getIdentityHash() + ")";
        }
        return "(Directory: " + getNickname() + " " + getAddress() + Constants.COLON_SEPARATOR + this.port + " fingerprint=" + getIdentityHash() + " v3ident=" + this.v3Ident + ")";
    }

    void unsetHiddenServiceAuthority() {
        this.isHiddenServiceAuthority = false;
    }
}
