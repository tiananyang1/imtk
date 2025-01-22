package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.directory.parsing.BasicDocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParsingHandler;
import com.subgraph.orchid.directory.parsing.DocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/IntroductionPointParser.class */
public class IntroductionPointParser implements DocumentParser<IntroductionPoint> {
    private IntroductionPoint currentIntroductionPoint;
    private final DocumentFieldParser fieldParser;
    private DocumentParsingResultHandler<IntroductionPoint> resultHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.circuits.hs.IntroductionPointParser$2 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/IntroductionPointParser$2.class */
    public static /* synthetic */ class C03272 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$circuits$hs$IntroductionPointKeyword */
        static final /* synthetic */ int[] f191x4e374e00 = new int[IntroductionPointKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:50:0x007d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00 = r0
                int[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00     // Catch: java.lang.NoSuchFieldError -> L65
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword r1 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.INTRO_AUTHENTICATION     // Catch: java.lang.NoSuchFieldError -> L65
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L65
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L65
            L14:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00     // Catch: java.lang.NoSuchFieldError -> L65 java.lang.NoSuchFieldError -> L69
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword r1 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.INTRODUCTION_POINT     // Catch: java.lang.NoSuchFieldError -> L69
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L69
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L69
            L1f:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00     // Catch: java.lang.NoSuchFieldError -> L69 java.lang.NoSuchFieldError -> L6d
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword r1 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.IP_ADDRESS     // Catch: java.lang.NoSuchFieldError -> L6d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L6d
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L6d
            L2a:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00     // Catch: java.lang.NoSuchFieldError -> L6d java.lang.NoSuchFieldError -> L71
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword r1 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.ONION_KEY     // Catch: java.lang.NoSuchFieldError -> L71
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L71
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L71
            L35:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00     // Catch: java.lang.NoSuchFieldError -> L71 java.lang.NoSuchFieldError -> L75
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword r1 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.ONION_PORT     // Catch: java.lang.NoSuchFieldError -> L75
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L75
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L75
            L40:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00     // Catch: java.lang.NoSuchFieldError -> L75 java.lang.NoSuchFieldError -> L79
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword r1 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.SERVICE_KEY     // Catch: java.lang.NoSuchFieldError -> L79
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L79
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L79
            L4c:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00     // Catch: java.lang.NoSuchFieldError -> L79 java.lang.NoSuchFieldError -> L7d
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword r1 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.SERVICE_AUTHENTICATION     // Catch: java.lang.NoSuchFieldError -> L7d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L7d
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L7d
            L58:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.f191x4e374e00     // Catch: java.lang.NoSuchFieldError -> L7d java.lang.NoSuchFieldError -> L81
                com.subgraph.orchid.circuits.hs.IntroductionPointKeyword r1 = com.subgraph.orchid.circuits.p002hs.IntroductionPointKeyword.UNKNOWN_KEYWORD     // Catch: java.lang.NoSuchFieldError -> L81
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L81
                r2 = 8
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L81
                return
            L65:
                r4 = move-exception
                goto L14
            L69:
                r4 = move-exception
                goto L1f
            L6d:
                r4 = move-exception
                goto L2a
            L71:
                r4 = move-exception
                goto L35
            L75:
                r4 = move-exception
                goto L40
            L79:
                r4 = move-exception
                goto L4c
            L7d:
                r4 = move-exception
                goto L58
            L81:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.p002hs.IntroductionPointParser.C03272.m3828clinit():void");
        }
    }

    public IntroductionPointParser(DocumentFieldParser documentFieldParser) {
        this.fieldParser = documentFieldParser;
        this.fieldParser.setHandler(createParsingHandler());
    }

    private DocumentParsingHandler createParsingHandler() {
        return new DocumentParsingHandler() { // from class: com.subgraph.orchid.circuits.hs.IntroductionPointParser.1
            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void endOfDocument() {
                IntroductionPointParser introductionPointParser = IntroductionPointParser.this;
                introductionPointParser.validateAndReportIntroductionPoint(introductionPointParser.currentIntroductionPoint);
            }

            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void parseKeywordLine() {
                IntroductionPointParser.this.processKeywordLine();
            }
        };
    }

    private void processKeyword(IntroductionPointKeyword introductionPointKeyword) {
        switch (C03272.f191x4e374e00[introductionPointKeyword.ordinal()]) {
            case 1:
            case 7:
                return;
            case 2:
                resetIntroductionPoint(this.fieldParser.parseBase32Digest());
                return;
            case 3:
                IntroductionPoint introductionPoint = this.currentIntroductionPoint;
                if (introductionPoint != null) {
                    introductionPoint.setAddress(this.fieldParser.parseAddress());
                    return;
                }
                return;
            case 4:
                IntroductionPoint introductionPoint2 = this.currentIntroductionPoint;
                if (introductionPoint2 != null) {
                    introductionPoint2.setOnionKey(this.fieldParser.parsePublicKey());
                    return;
                }
                return;
            case 5:
                IntroductionPoint introductionPoint3 = this.currentIntroductionPoint;
                if (introductionPoint3 != null) {
                    introductionPoint3.setOnionPort(this.fieldParser.parsePort());
                    return;
                }
                return;
            case 6:
                IntroductionPoint introductionPoint4 = this.currentIntroductionPoint;
                if (introductionPoint4 != null) {
                    introductionPoint4.setServiceKey(this.fieldParser.parsePublicKey());
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processKeywordLine() {
        IntroductionPointKeyword findKeyword = IntroductionPointKeyword.findKeyword(this.fieldParser.getCurrentKeyword());
        if (findKeyword.equals(IntroductionPointKeyword.UNKNOWN_KEYWORD)) {
            return;
        }
        processKeyword(findKeyword);
    }

    private void resetIntroductionPoint(HexDigest hexDigest) {
        validateAndReportIntroductionPoint(this.currentIntroductionPoint);
        this.currentIntroductionPoint = new IntroductionPoint(hexDigest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateAndReportIntroductionPoint(IntroductionPoint introductionPoint) {
        if (introductionPoint == null) {
            return;
        }
        if (introductionPoint.isValidDocument()) {
            this.resultHandler.documentParsed(introductionPoint);
        } else {
            this.resultHandler.documentInvalid(introductionPoint, "Invalid introduction point");
        }
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public DocumentParsingResult<IntroductionPoint> parse() {
        BasicDocumentParsingResult basicDocumentParsingResult = new BasicDocumentParsingResult();
        parse(basicDocumentParsingResult);
        return basicDocumentParsingResult;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public boolean parse(DocumentParsingResultHandler<IntroductionPoint> documentParsingResultHandler) {
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
