package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.directory.consensus.ConsensusDocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/RouterStatusSectionParser.class */
public class RouterStatusSectionParser extends ConsensusDocumentSectionParser {
    private RouterStatusImpl currentEntry;

    /* renamed from: com.subgraph.orchid.directory.consensus.RouterStatusSectionParser$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/RouterStatusSectionParser$1.class */
    static /* synthetic */ class C03551 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$directory$consensus$DocumentKeyword */
        static final /* synthetic */ int[] f208xac0fcbdc = new int[DocumentKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:36:0x005d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.directory.consensus.DocumentKeyword[] r0 = com.subgraph.orchid.directory.consensus.DocumentKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.consensus.RouterStatusSectionParser.C03551.f208xac0fcbdc = r0
                int[] r0 = com.subgraph.orchid.directory.consensus.RouterStatusSectionParser.C03551.f208xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L4d
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.R     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L14:
                int[] r0 = com.subgraph.orchid.directory.consensus.RouterStatusSectionParser.C03551.f208xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.S     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
            L1f:
                int[] r0 = com.subgraph.orchid.directory.consensus.RouterStatusSectionParser.C03551.f208xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L51 java.lang.NoSuchFieldError -> L55
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.V     // Catch: java.lang.NoSuchFieldError -> L55
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L55
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L55
            L2a:
                int[] r0 = com.subgraph.orchid.directory.consensus.RouterStatusSectionParser.C03551.f208xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L55 java.lang.NoSuchFieldError -> L59
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.W     // Catch: java.lang.NoSuchFieldError -> L59
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L59
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L59
            L35:
                int[] r0 = com.subgraph.orchid.directory.consensus.RouterStatusSectionParser.C03551.f208xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L59 java.lang.NoSuchFieldError -> L5d
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.P     // Catch: java.lang.NoSuchFieldError -> L5d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L5d
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L5d
            L40:
                int[] r0 = com.subgraph.orchid.directory.consensus.RouterStatusSectionParser.C03551.f208xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L5d java.lang.NoSuchFieldError -> L61
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.M     // Catch: java.lang.NoSuchFieldError -> L61
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L61
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L61
                return
            L4d:
                r4 = move-exception
                goto L14
            L51:
                r4 = move-exception
                goto L1f
            L55:
                r4 = move-exception
                goto L2a
            L59:
                r4 = move-exception
                goto L35
            L5d:
                r4 = move-exception
                goto L40
            L61:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.consensus.RouterStatusSectionParser.C03551.m3871clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RouterStatusSectionParser(DocumentFieldParser documentFieldParser, ConsensusDocumentImpl consensusDocumentImpl) {
        super(documentFieldParser, consensusDocumentImpl);
        this.currentEntry = null;
    }

    private void addCurrentEntry() {
        assertCurrentEntry();
        this.document.addRouterStatusEntry(this.currentEntry);
        this.currentEntry = null;
    }

    private void assertCurrentEntry() {
        if (this.currentEntry == null) {
            throw new TorParsingException("Router status entry must begin with an 'r' line");
        }
    }

    private void parseBandwidth() {
        while (this.fieldParser.argumentsRemaining() > 0) {
            String[] split = this.fieldParser.parseString().split("=");
            if (split.length == 2) {
                parseBandwidthItem(split[0], this.fieldParser.parseInteger(split[1]));
            }
        }
        if (this.document.getFlavor() == ConsensusDocument.ConsensusFlavor.MICRODESC) {
            addCurrentEntry();
        }
    }

    private void parseBandwidthItem(String str, int i) {
        if (str.equals("Bandwidth")) {
            this.currentEntry.setEstimatedBandwidth(i);
        } else if (str.equals("Measured")) {
            this.currentEntry.setMeasuredBandwidth(i);
        }
    }

    private HexDigest parseBase64Digest() {
        return HexDigest.createFromDigestBytes(this.fieldParser.parseBase64Data());
    }

    private void parseFirstLine() {
        if (this.currentEntry != null) {
            throw new TorParsingException("Unterminated router status entry.");
        }
        this.currentEntry = new RouterStatusImpl();
        this.currentEntry.setNickname(this.fieldParser.parseNickname());
        this.currentEntry.setIdentity(parseBase64Digest());
        if (this.document.getFlavor() != ConsensusDocument.ConsensusFlavor.MICRODESC) {
            this.currentEntry.setDigest(parseBase64Digest());
        }
        this.currentEntry.setPublicationTime(this.fieldParser.parseTimestamp());
        this.currentEntry.setAddress(this.fieldParser.parseAddress());
        this.currentEntry.setRouterPort(this.fieldParser.parsePort());
        this.currentEntry.setDirectoryPort(this.fieldParser.parsePort());
    }

    private void parseFlags() {
        while (this.fieldParser.argumentsRemaining() > 0) {
            this.currentEntry.addFlag(this.fieldParser.parseString());
        }
    }

    private void parseMicrodescriptorHash() {
        if (this.document.getFlavor() != ConsensusDocument.ConsensusFlavor.MICRODESC) {
            throw new TorParsingException("'m' line is invalid unless consensus flavor is microdesc");
        }
        byte[] parseBase64Data = this.fieldParser.parseBase64Data();
        if (parseBase64Data.length == 32) {
            this.currentEntry.setMicrodescriptorDigest(HexDigest.createFromDigestBytes(parseBase64Data));
            return;
        }
        throw new TorParsingException("'m' line has incorrect digest size " + parseBase64Data.length + " != 32");
    }

    private void parsePortList() {
        if (this.document.getFlavor() == ConsensusDocument.ConsensusFlavor.MICRODESC) {
            throw new TorParsingException("'p' line does not appear in consensus flavor 'microdesc'");
        }
        String parseString = this.fieldParser.parseString();
        if (parseString.equals("accept")) {
            this.currentEntry.setAcceptedPorts(this.fieldParser.parseString());
        } else if (parseString.equals("reject")) {
            this.currentEntry.setRejectedPorts(this.fieldParser.parseString());
        }
        addCurrentEntry();
    }

    private void parseVersion() {
        this.currentEntry.setVersion(this.fieldParser.parseConcatenatedString());
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    String getNextStateKeyword() {
        return "directory-footer";
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    ConsensusDocumentParser.DocumentSection getSection() {
        return ConsensusDocumentParser.DocumentSection.ROUTER_STATUS;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    ConsensusDocumentParser.DocumentSection nextSection() {
        return ConsensusDocumentParser.DocumentSection.FOOTER;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    void parseLine(DocumentKeyword documentKeyword) {
        if (!documentKeyword.equals(DocumentKeyword.R)) {
            assertCurrentEntry();
        }
        switch (C03551.f208xac0fcbdc[documentKeyword.ordinal()]) {
            case 1:
                parseFirstLine();
                return;
            case 2:
                parseFlags();
                return;
            case 3:
                parseVersion();
                return;
            case 4:
                parseBandwidth();
                return;
            case 5:
                parsePortList();
                return;
            case 6:
                parseMicrodescriptorHash();
                return;
            default:
                return;
        }
    }
}
