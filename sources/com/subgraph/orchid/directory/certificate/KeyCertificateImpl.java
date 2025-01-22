package com.subgraph.orchid.directory.certificate;

import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.Tor;
import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.Timestamp;
import com.xiaomi.mipush.sdk.Constants;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/certificate/KeyCertificateImpl.class */
public class KeyCertificateImpl implements KeyCertificate {
    private IPv4Address directoryAddress;
    private int directoryPort;
    private HexDigest fingerprint;
    private boolean hasValidSignature = false;
    private TorPublicKey identityKey;
    private Timestamp keyExpires;
    private Timestamp keyPublished;
    private String rawDocumentData;
    private TorPublicKey signingKey;

    @Override // com.subgraph.orchid.KeyCertificate
    public HexDigest getAuthorityFingerprint() {
        return this.fingerprint;
    }

    @Override // com.subgraph.orchid.KeyCertificate
    public TorPublicKey getAuthorityIdentityKey() {
        return this.identityKey;
    }

    @Override // com.subgraph.orchid.KeyCertificate
    public TorPublicKey getAuthoritySigningKey() {
        return this.signingKey;
    }

    @Override // com.subgraph.orchid.KeyCertificate
    public IPv4Address getDirectoryAddress() {
        return this.directoryAddress;
    }

    @Override // com.subgraph.orchid.KeyCertificate
    public int getDirectoryPort() {
        return this.directoryPort;
    }

    @Override // com.subgraph.orchid.KeyCertificate
    public Timestamp getKeyExpiryTime() {
        return this.keyExpires;
    }

    @Override // com.subgraph.orchid.KeyCertificate
    public Timestamp getKeyPublishedTime() {
        return this.keyPublished;
    }

    @Override // com.subgraph.orchid.Document
    public ByteBuffer getRawDocumentBytes() {
        return getRawDocumentData() == null ? ByteBuffer.allocate(0) : ByteBuffer.wrap(getRawDocumentData().getBytes(Tor.getDefaultCharset()));
    }

    @Override // com.subgraph.orchid.Document
    public String getRawDocumentData() {
        return this.rawDocumentData;
    }

    @Override // com.subgraph.orchid.KeyCertificate
    public boolean isExpired() {
        Timestamp timestamp = this.keyExpires;
        if (timestamp != null) {
            return timestamp.hasPassed();
        }
        return false;
    }

    @Override // com.subgraph.orchid.Document
    public boolean isValidDocument() {
        return (!this.hasValidSignature || this.fingerprint == null || this.identityKey == null || this.keyPublished == null || this.keyExpires == null || this.signingKey == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAuthorityFingerprint(HexDigest hexDigest) {
        this.fingerprint = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAuthorityIdentityKey(TorPublicKey torPublicKey) {
        this.identityKey = torPublicKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAuthoritySigningKey(TorPublicKey torPublicKey) {
        this.signingKey = torPublicKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDirectoryAddress(IPv4Address iPv4Address) {
        this.directoryAddress = iPv4Address;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDirectoryPort(int i) {
        this.directoryPort = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setKeyExpiryTime(Timestamp timestamp) {
        this.keyExpires = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setKeyPublishedTime(Timestamp timestamp) {
        this.keyPublished = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRawDocumentData(String str) {
        this.rawDocumentData = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValidSignature() {
        this.hasValidSignature = true;
    }

    public String toString() {
        return "(Certificate: address=" + this.directoryAddress + Constants.COLON_SEPARATOR + this.directoryPort + " fingerprint=" + this.fingerprint + " published=" + this.keyPublished + " expires=" + this.keyExpires + ")\nident=" + this.identityKey + " sign=" + this.signingKey;
    }
}
