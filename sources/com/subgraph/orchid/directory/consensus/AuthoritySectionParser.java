package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.directory.consensus.ConsensusDocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/AuthoritySectionParser.class */
public class AuthoritySectionParser extends ConsensusDocumentSectionParser {
    private VoteAuthorityEntryImpl currentEntry;

    /* renamed from: com.subgraph.orchid.directory.consensus.AuthoritySectionParser$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/AuthoritySectionParser$1.class */
    static /* synthetic */ class C03491 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$directory$consensus$DocumentKeyword */
        static final /* synthetic */ int[] f198xac0fcbdc = new int[DocumentKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.directory.consensus.DocumentKeyword[] r0 = com.subgraph.orchid.directory.consensus.DocumentKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.consensus.AuthoritySectionParser.C03491.f198xac0fcbdc = r0
                int[] r0 = com.subgraph.orchid.directory.consensus.AuthoritySectionParser.C03491.f198xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L2b
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.DIR_SOURCE     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.subgraph.orchid.directory.consensus.AuthoritySectionParser.C03491.f198xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.CONTACT     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.subgraph.orchid.directory.consensus.AuthoritySectionParser.C03491.f198xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.VOTE_DIGEST     // Catch: java.lang.NoSuchFieldError -> L33
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
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.consensus.AuthoritySectionParser.C03491.m3862clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthoritySectionParser(DocumentFieldParser documentFieldParser, ConsensusDocumentImpl consensusDocumentImpl) {
        super(documentFieldParser, consensusDocumentImpl);
        this.currentEntry = null;
        startEntry();
    }

    private void addCurrentEntry() {
        this.document.addVoteAuthorityEntry(this.currentEntry);
        startEntry();
    }

    private void parseDirSource() {
        this.currentEntry.setNickname(this.fieldParser.parseNickname());
        this.currentEntry.setIdentity(this.fieldParser.parseHexDigest());
        this.currentEntry.setHostname(this.fieldParser.parseString());
        this.currentEntry.setAddress(this.fieldParser.parseAddress());
        this.currentEntry.setDirectoryPort(this.fieldParser.parsePort());
        this.currentEntry.setRouterPort(this.fieldParser.parsePort());
    }

    private void startEntry() {
        this.currentEntry = new VoteAuthorityEntryImpl();
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    String getNextStateKeyword() {
        return "r";
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    ConsensusDocumentParser.DocumentSection getSection() {
        return ConsensusDocumentParser.DocumentSection.AUTHORITY;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    ConsensusDocumentParser.DocumentSection nextSection() {
        return ConsensusDocumentParser.DocumentSection.ROUTER_STATUS;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    void parseLine(DocumentKeyword documentKeyword) {
        int i = C03491.f198xac0fcbdc[documentKeyword.ordinal()];
        if (i == 1) {
            parseDirSource();
            return;
        }
        if (i == 2) {
            this.currentEntry.setContact(this.fieldParser.parseConcatenatedString());
        } else {
            if (i != 3) {
                return;
            }
            this.currentEntry.setVoteDigest(this.fieldParser.parseHexDigest());
            addCurrentEntry();
        }
    }
}
