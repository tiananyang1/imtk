package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.directory.consensus.ConsensusDocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.NameIntegerParameter;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/PreambleSectionParser.class */
public class PreambleSectionParser extends ConsensusDocumentSectionParser {
    private static final int CURRENT_DOCUMENT_VERSION = 3;
    private boolean isFirstLine;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.directory.consensus.PreambleSectionParser$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/PreambleSectionParser$1.class */
    public static /* synthetic */ class C03541 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$directory$consensus$DocumentKeyword */
        static final /* synthetic */ int[] f207xac0fcbdc = new int[DocumentKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:71:0x00ad
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.directory.consensus.DocumentKeyword[] r0 = com.subgraph.orchid.directory.consensus.DocumentKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc = r0
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L89
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.NETWORK_STATUS_VERSION     // Catch: java.lang.NoSuchFieldError -> L89
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L89
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L89
            L14:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L89 java.lang.NoSuchFieldError -> L8d
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.VOTE_STATUS     // Catch: java.lang.NoSuchFieldError -> L8d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L8d
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L8d
            L1f:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L8d java.lang.NoSuchFieldError -> L91
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.CONSENSUS_METHOD     // Catch: java.lang.NoSuchFieldError -> L91
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L91
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L91
            L2a:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L91 java.lang.NoSuchFieldError -> L95
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.VALID_AFTER     // Catch: java.lang.NoSuchFieldError -> L95
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L95
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L95
            L35:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L95 java.lang.NoSuchFieldError -> L99
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.FRESH_UNTIL     // Catch: java.lang.NoSuchFieldError -> L99
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L99
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L99
            L40:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L99 java.lang.NoSuchFieldError -> L9d
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.VALID_UNTIL     // Catch: java.lang.NoSuchFieldError -> L9d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L9d
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L9d
            L4c:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L9d java.lang.NoSuchFieldError -> La1
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.VOTING_DELAY     // Catch: java.lang.NoSuchFieldError -> La1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La1
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La1
            L58:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> La1 java.lang.NoSuchFieldError -> La5
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.CLIENT_VERSIONS     // Catch: java.lang.NoSuchFieldError -> La5
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La5
                r2 = 8
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La5
            L64:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> La5 java.lang.NoSuchFieldError -> La9
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.SERVER_VERSIONS     // Catch: java.lang.NoSuchFieldError -> La9
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La9
                r2 = 9
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La9
            L70:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> La9 java.lang.NoSuchFieldError -> Lad
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.KNOWN_FLAGS     // Catch: java.lang.NoSuchFieldError -> Lad
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lad
                r2 = 10
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lad
            L7c:
                int[] r0 = com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.f207xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> Lad java.lang.NoSuchFieldError -> Lb1
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.PARAMS     // Catch: java.lang.NoSuchFieldError -> Lb1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lb1
                r2 = 11
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lb1
                return
            L89:
                r4 = move-exception
                goto L14
            L8d:
                r4 = move-exception
                goto L1f
            L91:
                r4 = move-exception
                goto L2a
            L95:
                r4 = move-exception
                goto L35
            L99:
                r4 = move-exception
                goto L40
            L9d:
                r4 = move-exception
                goto L4c
            La1:
                r4 = move-exception
                goto L58
            La5:
                r4 = move-exception
                goto L64
            La9:
                r4 = move-exception
                goto L70
            Lad:
                r4 = move-exception
                goto L7c
            Lb1:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.consensus.PreambleSectionParser.C03541.m3870clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreambleSectionParser(DocumentFieldParser documentFieldParser, ConsensusDocumentImpl consensusDocumentImpl) {
        super(documentFieldParser, consensusDocumentImpl);
        this.isFirstLine = true;
    }

    private void parseConsensusFlavor() {
        String parseString = this.fieldParser.parseString();
        if ("ns".equals(parseString)) {
            this.document.setConsensusFlavor(ConsensusDocument.ConsensusFlavor.NS);
            return;
        }
        if ("microdesc".equals(parseString)) {
            this.document.setConsensusFlavor(ConsensusDocument.ConsensusFlavor.MICRODESC);
            return;
        }
        this.fieldParser.logWarn("Unknown consensus flavor: " + parseString);
    }

    private void parseFirstLine(DocumentKeyword documentKeyword) {
        if (documentKeyword != DocumentKeyword.NETWORK_STATUS_VERSION) {
            throw new TorParsingException("network-status-version not found at beginning of consensus document as expected.");
        }
        int parseInteger = this.fieldParser.parseInteger();
        if (parseInteger == 3) {
            if (this.fieldParser.argumentsRemaining() > 0) {
                parseConsensusFlavor();
            }
            this.isFirstLine = false;
        } else {
            throw new TorParsingException("Unexpected consensus document version number: " + parseInteger);
        }
    }

    private void parseParams() {
        int argumentsRemaining = this.fieldParser.argumentsRemaining();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= argumentsRemaining) {
                return;
            }
            NameIntegerParameter parseParameter = this.fieldParser.parseParameter();
            this.document.addParameter(parseParameter.getName(), parseParameter.getValue());
            i = i2 + 1;
        }
    }

    private List<String> parseVersions(String str) {
        return Arrays.asList(str.split(Constants.ACCEPT_TIME_SEPARATOR_SP));
    }

    private void processKeyword(DocumentKeyword documentKeyword) {
        switch (C03541.f207xac0fcbdc[documentKeyword.ordinal()]) {
            case 1:
                throw new TorParsingException("Network status version may only appear on the first line of status document");
            case 2:
                String parseString = this.fieldParser.parseString();
                if (parseString.equals("consensus")) {
                    return;
                }
                throw new TorParsingException("Unexpected vote-status type: " + parseString);
            case 3:
                this.document.setConsensusMethod(this.fieldParser.parseInteger());
                return;
            case 4:
                this.document.setValidAfter(this.fieldParser.parseTimestamp());
                return;
            case 5:
                this.document.setFreshUntil(this.fieldParser.parseTimestamp());
                return;
            case 6:
                this.document.setValidUntil(this.fieldParser.parseTimestamp());
                return;
            case 7:
                this.document.setVoteDelaySeconds(this.fieldParser.parseInteger());
                this.document.setDistDelaySeconds(this.fieldParser.parseInteger());
                return;
            case 8:
                Iterator<String> it = parseVersions(this.fieldParser.parseString()).iterator();
                while (it.hasNext()) {
                    this.document.addClientVersion(it.next());
                }
                return;
            case 9:
                Iterator<String> it2 = parseVersions(this.fieldParser.parseString()).iterator();
                while (it2.hasNext()) {
                    this.document.addServerVersion(it2.next());
                }
                return;
            case 10:
                break;
            case 11:
                parseParams();
                return;
            default:
                return;
        }
        while (this.fieldParser.argumentsRemaining() > 0) {
            this.document.addKnownFlag(this.fieldParser.parseString());
        }
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    String getNextStateKeyword() {
        return "dir-source";
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    ConsensusDocumentParser.DocumentSection getSection() {
        return ConsensusDocumentParser.DocumentSection.PREAMBLE;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    ConsensusDocumentParser.DocumentSection nextSection() {
        return ConsensusDocumentParser.DocumentSection.AUTHORITY;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    void parseLine(DocumentKeyword documentKeyword) {
        if (this.isFirstLine) {
            parseFirstLine(documentKeyword);
        } else {
            processKeyword(documentKeyword);
        }
    }
}
