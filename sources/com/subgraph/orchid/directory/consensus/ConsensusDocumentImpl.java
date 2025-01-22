package com.subgraph.orchid.directory.consensus;

import com.nimbusds.jose.crypto.PasswordBasedEncrypter;
import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.DirectoryServer;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.RouterStatus;
import com.subgraph.orchid.Tor;
import com.subgraph.orchid.VoteAuthorityEntry;
import com.subgraph.orchid.crypto.TorSignature;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.Timestamp;
import com.subgraph.orchid.directory.TrustedAuthorities;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/ConsensusDocumentImpl.class */
public class ConsensusDocumentImpl implements ConsensusDocument {
    private static final int BW_WEIGHT_SCALE_DEFAULT = 10000;
    private static final int BW_WEIGHT_SCALE_MAX = Integer.MAX_VALUE;
    private static final int BW_WEIGHT_SCALE_MIN = 1;
    private static final String BW_WEIGHT_SCALE_PARAM = "bwweightscale";
    private static final int CIRCWINDOW_DEFAULT = 1000;
    private static final int CIRCWINDOW_MAX = 1000;
    private static final int CIRCWINDOW_MIN = 100;
    private static final String CIRCWINDOW_PARAM = "circwindow";
    private static final String USE_NTOR_HANDSHAKE_PARAM = "UseNTorHandshake";
    private static final Logger logger = Logger.getLogger(ConsensusDocumentImpl.class.getName());
    private int consensusMethod;
    private int distDelaySeconds;
    private ConsensusDocument.ConsensusFlavor flavor;
    private Timestamp freshUntil;
    private String rawDocumentData;
    private int signatureCount;
    private HexDigest signingHash;
    private HexDigest signingHash256;
    private Timestamp validAfter;
    private Timestamp validUntil;
    private int voteDelaySeconds;
    private Set<ConsensusDocument.RequiredCertificate> requiredCertificates = new HashSet();
    private boolean isFirstCallToVerifySignatures = true;
    private Set<String> clientVersions = new HashSet();
    private Set<String> serverVersions = new HashSet();
    private Set<String> knownFlags = new HashSet();
    private Map<HexDigest, VoteAuthorityEntry> voteAuthorityEntries = new HashMap();
    private List<RouterStatus> routerStatusEntries = new ArrayList();
    private Map<String, Integer> bandwidthWeights = new HashMap();
    private Map<String, Integer> parameters = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.directory.consensus.ConsensusDocumentImpl$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/ConsensusDocumentImpl$1.class */
    public static /* synthetic */ class C03501 {
        static final /* synthetic */ int[] $SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus = new int[ConsensusDocument.SignatureStatus.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.ConsensusDocument$SignatureStatus[] r0 = com.subgraph.orchid.ConsensusDocument.SignatureStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.consensus.ConsensusDocumentImpl.C03501.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus = r0
                int[] r0 = com.subgraph.orchid.directory.consensus.ConsensusDocumentImpl.C03501.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus     // Catch: java.lang.NoSuchFieldError -> L2b
                com.subgraph.orchid.ConsensusDocument$SignatureStatus r1 = com.subgraph.orchid.ConsensusDocument.SignatureStatus.STATUS_FAILED     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.subgraph.orchid.directory.consensus.ConsensusDocumentImpl.C03501.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.subgraph.orchid.ConsensusDocument$SignatureStatus r1 = com.subgraph.orchid.ConsensusDocument.SignatureStatus.STATUS_NEED_CERTS     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.subgraph.orchid.directory.consensus.ConsensusDocumentImpl.C03501.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.subgraph.orchid.ConsensusDocument$SignatureStatus r1 = com.subgraph.orchid.ConsensusDocument.SignatureStatus.STATUS_VERIFIED     // Catch: java.lang.NoSuchFieldError -> L33
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L33
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L33
                return
            L2b:
                r4 = move-exception
                goto L14
            L2f:
                r4 = move-exception
                goto L1f
            L33:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.consensus.ConsensusDocumentImpl.C03501.m3864clinit():void");
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/ConsensusDocumentImpl$SignatureVerifyStatus.class */
    enum SignatureVerifyStatus {
        STATUS_UNVERIFIED,
        STATUS_NEED_CERTS,
        STATUS_VERIFIED
    }

    private void addRequiredCertificateForSignature(DirectorySignature directorySignature) {
        this.requiredCertificates.add(new RequiredCertificateImpl(directorySignature.getIdentityDigest(), directorySignature.getSigningKeyDigest()));
    }

    private boolean getBooleanParameterValue(String str, boolean z) {
        return !this.parameters.containsKey(str) ? z : this.parameters.get(str).intValue() != 0;
    }

    private int getParameterValue(String str, int i, int i2, int i3) {
        if (!this.parameters.containsKey(str)) {
            return i;
        }
        int intValue = this.parameters.get(str).intValue();
        return intValue < i2 ? i2 : intValue > i3 ? i3 : intValue;
    }

    private ConsensusDocument.SignatureStatus verifySignatureForTrustedAuthority(DirectoryServer directoryServer, DirectorySignature directorySignature) {
        KeyCertificate certificateByFingerprint = directoryServer.getCertificateByFingerprint(directorySignature.getSigningKeyDigest());
        if (certificateByFingerprint == null) {
            logger.fine("Missing certificate for signing key: " + directorySignature.getSigningKeyDigest());
            addRequiredCertificateForSignature(directorySignature);
            return ConsensusDocument.SignatureStatus.STATUS_NEED_CERTS;
        }
        if (certificateByFingerprint.isExpired()) {
            return ConsensusDocument.SignatureStatus.STATUS_FAILED;
        }
        if (certificateByFingerprint.getAuthoritySigningKey().verifySignature(directorySignature.getSignature(), directorySignature.useSha256() ? this.signingHash256 : this.signingHash)) {
            return ConsensusDocument.SignatureStatus.STATUS_VERIFIED;
        }
        logger.warning("Signature failed on consensus for signing key: " + directorySignature.getSigningKeyDigest());
        return ConsensusDocument.SignatureStatus.STATUS_FAILED;
    }

    private ConsensusDocument.SignatureStatus verifySingleAuthority(VoteAuthorityEntry voteAuthorityEntry) {
        boolean z = false;
        boolean z2 = false;
        for (DirectorySignature directorySignature : voteAuthorityEntry.getSignatures()) {
            DirectoryServer authorityServerByIdentity = TrustedAuthorities.getInstance().getAuthorityServerByIdentity(directorySignature.getIdentityDigest());
            if (authorityServerByIdentity == null) {
                logger.warning("Consensus signed by unrecognized directory authority: " + directorySignature.getIdentityDigest());
                return ConsensusDocument.SignatureStatus.STATUS_FAILED;
            }
            int i = C03501.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus[verifySignatureForTrustedAuthority(authorityServerByIdentity, directorySignature).ordinal()];
            if (i == 2) {
                z2 = true;
            } else if (i == 3) {
                z = true;
            }
        }
        return z ? ConsensusDocument.SignatureStatus.STATUS_VERIFIED : z2 ? ConsensusDocument.SignatureStatus.STATUS_NEED_CERTS : ConsensusDocument.SignatureStatus.STATUS_FAILED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addBandwidthWeight(String str, int i) {
        this.bandwidthWeights.put(str, Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addClientVersion(String str) {
        this.clientVersions.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addKnownFlag(String str) {
        this.knownFlags.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addParameter(String str, int i) {
        this.parameters.put(str, Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addRouterStatusEntry(RouterStatusImpl routerStatusImpl) {
        this.routerStatusEntries.add(routerStatusImpl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addServerVersion(String str) {
        this.serverVersions.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addSignature(DirectorySignature directorySignature) {
        VoteAuthorityEntry voteAuthorityEntry = this.voteAuthorityEntries.get(directorySignature.getIdentityDigest());
        if (voteAuthorityEntry == null) {
            logger.warning("Consensus contains signature for source not declared in authority section: " + directorySignature.getIdentityDigest());
            return;
        }
        List<DirectorySignature> signatures = voteAuthorityEntry.getSignatures();
        TorSignature.DigestAlgorithm digestAlgorithm = directorySignature.getSignature().getDigestAlgorithm();
        Iterator<DirectorySignature> it = signatures.iterator();
        while (it.hasNext()) {
            if (it.next().getSignature().getDigestAlgorithm().equals(digestAlgorithm)) {
                logger.warning("Consensus contains two or more signatures for same source with same algorithm");
                return;
            }
        }
        this.signatureCount++;
        signatures.add(directorySignature);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addVoteAuthorityEntry(VoteAuthorityEntry voteAuthorityEntry) {
        this.voteAuthorityEntries.put(voteAuthorityEntry.getIdentity(), voteAuthorityEntry);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ConsensusDocumentImpl) {
            return ((ConsensusDocumentImpl) obj).getSigningHash().equals(this.signingHash);
        }
        return false;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public int getBandwidthWeight(String str) {
        if (this.bandwidthWeights.containsKey(str)) {
            return this.bandwidthWeights.get(str).intValue();
        }
        return -1;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public int getCircWindowParameter() {
        return getParameterValue(CIRCWINDOW_PARAM, PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT, 100, PasswordBasedEncrypter.MIN_RECOMMENDED_ITERATION_COUNT);
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public Set<String> getClientVersions() {
        return this.clientVersions;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public int getConsensusMethod() {
        return this.consensusMethod;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public int getDistSeconds() {
        return this.distDelaySeconds;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public ConsensusDocument.ConsensusFlavor getFlavor() {
        return this.flavor;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public Timestamp getFreshUntilTime() {
        return this.freshUntil;
    }

    @Override // com.subgraph.orchid.Document
    public ByteBuffer getRawDocumentBytes() {
        return getRawDocumentData() == null ? ByteBuffer.allocate(0) : ByteBuffer.wrap(getRawDocumentData().getBytes(Tor.getDefaultCharset()));
    }

    @Override // com.subgraph.orchid.Document
    public String getRawDocumentData() {
        return this.rawDocumentData;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public Set<ConsensusDocument.RequiredCertificate> getRequiredCertificates() {
        return this.requiredCertificates;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public List<RouterStatus> getRouterStatusEntries() {
        return Collections.unmodifiableList(this.routerStatusEntries);
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public Set<String> getServerVersions() {
        return this.serverVersions;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public HexDigest getSigningHash() {
        return this.signingHash;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public HexDigest getSigningHash256() {
        return this.signingHash256;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public boolean getUseNTorHandshake() {
        return getBooleanParameterValue(USE_NTOR_HANDSHAKE_PARAM, false);
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public Timestamp getValidAfterTime() {
        return this.validAfter;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public Timestamp getValidUntilTime() {
        return this.validUntil;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public int getVoteSeconds() {
        return this.voteDelaySeconds;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public int getWeightScaleParameter() {
        return getParameterValue(BW_WEIGHT_SCALE_PARAM, 10000, 1, Integer.MAX_VALUE);
    }

    public int hashCode() {
        HexDigest hexDigest = this.signingHash;
        if (hexDigest == null) {
            return 0;
        }
        return hexDigest.hashCode();
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public boolean isLive() {
        if (this.validUntil == null) {
            return false;
        }
        return !r0.hasPassed();
    }

    @Override // com.subgraph.orchid.Document
    public boolean isValidDocument() {
        return (this.validAfter == null || this.freshUntil == null || this.validUntil == null || this.voteDelaySeconds <= 0 || this.distDelaySeconds <= 0 || this.signingHash == null || this.signatureCount <= 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConsensusFlavor(ConsensusDocument.ConsensusFlavor consensusFlavor) {
        this.flavor = consensusFlavor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConsensusMethod(int i) {
        this.consensusMethod = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDistDelaySeconds(int i) {
        this.distDelaySeconds = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFreshUntil(Timestamp timestamp) {
        this.freshUntil = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRawDocumentData(String str) {
        this.rawDocumentData = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSigningHash(HexDigest hexDigest) {
        this.signingHash = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSigningHash256(HexDigest hexDigest) {
        this.signingHash256 = hexDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValidAfter(Timestamp timestamp) {
        this.validAfter = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValidUntil(Timestamp timestamp) {
        this.validUntil = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setVoteDelaySeconds(int i) {
        this.voteDelaySeconds = i;
    }

    @Override // com.subgraph.orchid.ConsensusDocument
    public ConsensusDocument.SignatureStatus verifySignatures() {
        synchronized (this) {
            boolean z = this.isFirstCallToVerifySignatures;
            int i = 0;
            this.isFirstCallToVerifySignatures = false;
            this.requiredCertificates.clear();
            int v3AuthorityServerCount = (TrustedAuthorities.getInstance().getV3AuthorityServerCount() / 2) + 1;
            Iterator<VoteAuthorityEntry> it = this.voteAuthorityEntries.values().iterator();
            int i2 = 0;
            while (it.hasNext()) {
                int i3 = C03501.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus[verifySingleAuthority(it.next()).ordinal()];
                if (i3 != 1) {
                    if (i3 == 2) {
                        i2++;
                    } else if (i3 == 3) {
                        i++;
                    }
                }
            }
            if (i >= v3AuthorityServerCount) {
                return ConsensusDocument.SignatureStatus.STATUS_VERIFIED;
            }
            if (i + i2 < v3AuthorityServerCount) {
                return ConsensusDocument.SignatureStatus.STATUS_FAILED;
            }
            if (z) {
                logger.info("Certificates need to be retrieved to verify consensus");
            }
            return ConsensusDocument.SignatureStatus.STATUS_NEED_CERTS;
        }
    }
}
