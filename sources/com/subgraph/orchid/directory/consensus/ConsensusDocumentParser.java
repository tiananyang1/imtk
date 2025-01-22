package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.directory.parsing.BasicDocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParsingHandler;
import com.subgraph.orchid.directory.parsing.DocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/ConsensusDocumentParser.class */
public class ConsensusDocumentParser implements DocumentParser<ConsensusDocument> {
    private static final String ITEM_DELIMITER = " ";
    private final AuthoritySectionParser authorityParser;
    private DocumentSection currentSection = DocumentSection.PREAMBLE;
    private final ConsensusDocumentImpl document;
    private final DocumentFieldParser fieldParser;
    private final FooterSectionParser footerParser;
    private final PreambleSectionParser preambleParser;
    private DocumentParsingResultHandler<ConsensusDocument> resultHandler;
    private final RouterStatusSectionParser routerStatusParser;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.directory.consensus.ConsensusDocumentParser$2 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/ConsensusDocumentParser$2.class */
    public static /* synthetic */ class C03522 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$directory$consensus$ConsensusDocumentParser$DocumentSection */
        static final /* synthetic */ int[] f199x238ea185 = new int[DocumentSection.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:22:0x003e
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.directory.consensus.ConsensusDocumentParser$DocumentSection[] r0 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.DocumentSection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.C03522.f199x238ea185 = r0
                int[] r0 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.C03522.f199x238ea185     // Catch: java.lang.NoSuchFieldError -> L36
                com.subgraph.orchid.directory.consensus.ConsensusDocumentParser$DocumentSection r1 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.DocumentSection.PREAMBLE     // Catch: java.lang.NoSuchFieldError -> L36
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L36
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L36
            L14:
                int[] r0 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.C03522.f199x238ea185     // Catch: java.lang.NoSuchFieldError -> L36 java.lang.NoSuchFieldError -> L3a
                com.subgraph.orchid.directory.consensus.ConsensusDocumentParser$DocumentSection r1 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.DocumentSection.AUTHORITY     // Catch: java.lang.NoSuchFieldError -> L3a
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L3a
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L3a
            L1f:
                int[] r0 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.C03522.f199x238ea185     // Catch: java.lang.NoSuchFieldError -> L3a java.lang.NoSuchFieldError -> L3e
                com.subgraph.orchid.directory.consensus.ConsensusDocumentParser$DocumentSection r1 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.DocumentSection.ROUTER_STATUS     // Catch: java.lang.NoSuchFieldError -> L3e
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L3e
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L3e
            L2a:
                int[] r0 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.C03522.f199x238ea185     // Catch: java.lang.NoSuchFieldError -> L3e java.lang.NoSuchFieldError -> L42
                com.subgraph.orchid.directory.consensus.ConsensusDocumentParser$DocumentSection r1 = com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.DocumentSection.FOOTER     // Catch: java.lang.NoSuchFieldError -> L42
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L42
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L42
                return
            L36:
                r4 = move-exception
                goto L14
            L3a:
                r4 = move-exception
                goto L1f
            L3e:
                r4 = move-exception
                goto L2a
            L42:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.C03522.m3866clinit():void");
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/ConsensusDocumentParser$DocumentSection.class */
    public enum DocumentSection {
        NO_SECTION,
        PREAMBLE,
        AUTHORITY,
        ROUTER_STATUS,
        FOOTER
    }

    public ConsensusDocumentParser(DocumentFieldParser documentFieldParser) {
        this.fieldParser = documentFieldParser;
        initializeParser();
        this.document = new ConsensusDocumentImpl();
        this.preambleParser = new PreambleSectionParser(documentFieldParser, this.document);
        this.authorityParser = new AuthoritySectionParser(documentFieldParser, this.document);
        this.routerStatusParser = new RouterStatusSectionParser(documentFieldParser, this.document);
        this.footerParser = new FooterSectionParser(documentFieldParser, this.document);
    }

    private DocumentParsingHandler createParsingHandler() {
        return new DocumentParsingHandler() { // from class: com.subgraph.orchid.directory.consensus.ConsensusDocumentParser.1
            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void endOfDocument() {
                ConsensusDocumentParser.this.document.setRawDocumentData(ConsensusDocumentParser.this.fieldParser.getRawDocument());
                ConsensusDocumentParser.this.resultHandler.documentParsed(ConsensusDocumentParser.this.document);
                ConsensusDocumentParser.this.fieldParser.logDebug("Finished parsing status document.");
            }

            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void parseKeywordLine() {
                ConsensusDocumentParser.this.processKeywordLine();
            }
        };
    }

    private void initializeParser() {
        this.fieldParser.resetRawDocument();
        this.fieldParser.setHandler(createParsingHandler());
        this.fieldParser.setDelimiter(ITEM_DELIMITER);
        this.fieldParser.setSignatureIgnoreToken("directory-signature");
        this.fieldParser.startSignedEntity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processKeywordLine() {
        DocumentSection documentSection = null;
        while (this.currentSection != DocumentSection.NO_SECTION) {
            int i = C03522.f199x238ea185[this.currentSection.ordinal()];
            if (i == 1) {
                documentSection = this.preambleParser.parseKeywordLine();
            } else if (i == 2) {
                documentSection = this.authorityParser.parseKeywordLine();
            } else if (i == 3) {
                documentSection = this.routerStatusParser.parseKeywordLine();
            } else if (i == 4) {
                documentSection = this.footerParser.parseKeywordLine();
            }
            if (documentSection == this.currentSection) {
                return;
            } else {
                this.currentSection = documentSection;
            }
        }
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public DocumentParsingResult<ConsensusDocument> parse() {
        BasicDocumentParsingResult basicDocumentParsingResult = new BasicDocumentParsingResult();
        parse(basicDocumentParsingResult);
        return basicDocumentParsingResult;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public boolean parse(DocumentParsingResultHandler<ConsensusDocument> documentParsingResultHandler) {
        this.resultHandler = documentParsingResultHandler;
        try {
            this.fieldParser.processDocument();
            return true;
        } catch (TorParsingException e) {
            documentParsingResultHandler.parsingError(e.getMessage());
            return false;
        }
    }
}
