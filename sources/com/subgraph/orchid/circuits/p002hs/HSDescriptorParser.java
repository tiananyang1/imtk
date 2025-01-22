package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.directory.DocumentFieldParserImpl;
import com.subgraph.orchid.directory.parsing.BasicDocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.DocumentObject;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParsingHandler;
import com.subgraph.orchid.directory.parsing.DocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler;
import com.subgraph.orchid.encoders.Base64;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorParser.class */
public class HSDescriptorParser implements DocumentParser<HSDescriptor> {
    private static final Logger logger = Logger.getLogger(HSDescriptor.class.getName());
    private final HSAuthentication authentication;
    private final HSDescriptor descriptor;
    private final DocumentFieldParser fieldParser;
    private DocumentParsingResultHandler<HSDescriptor> resultHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.circuits.hs.HSDescriptorParser$3 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorParser$3.class */
    public static /* synthetic */ class C03243 {
        static final /* synthetic */ int[] $SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword = new int[HSDescriptorKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:57:0x008d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword = r0
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L71
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.RENDEZVOUS_SERVICE_DESCRIPTOR     // Catch: java.lang.NoSuchFieldError -> L71
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L71
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L71
            L14:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L71 java.lang.NoSuchFieldError -> L75
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.VERSION     // Catch: java.lang.NoSuchFieldError -> L75
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L75
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L75
            L1f:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L75 java.lang.NoSuchFieldError -> L79
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.PERMANENT_KEY     // Catch: java.lang.NoSuchFieldError -> L79
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L79
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L79
            L2a:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L79 java.lang.NoSuchFieldError -> L7d
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.SECRET_ID_PART     // Catch: java.lang.NoSuchFieldError -> L7d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L7d
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L7d
            L35:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L7d java.lang.NoSuchFieldError -> L81
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.PUBLICATION_TIME     // Catch: java.lang.NoSuchFieldError -> L81
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L81
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L81
            L40:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L81 java.lang.NoSuchFieldError -> L85
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.PROTOCOL_VERSIONS     // Catch: java.lang.NoSuchFieldError -> L85
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L85
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L85
            L4c:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L85 java.lang.NoSuchFieldError -> L89
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.INTRODUCTION_POINTS     // Catch: java.lang.NoSuchFieldError -> L89
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L89
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L89
            L58:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L89 java.lang.NoSuchFieldError -> L8d
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.SIGNATURE     // Catch: java.lang.NoSuchFieldError -> L8d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L8d
                r2 = 8
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L8d
            L64:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword     // Catch: java.lang.NoSuchFieldError -> L8d java.lang.NoSuchFieldError -> L91
                com.subgraph.orchid.circuits.hs.HSDescriptorKeyword r1 = com.subgraph.orchid.circuits.p002hs.HSDescriptorKeyword.UNKNOWN_KEYWORD     // Catch: java.lang.NoSuchFieldError -> L91
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L91
                r2 = 9
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L91
                return
            L71:
                r4 = move-exception
                goto L14
            L75:
                r4 = move-exception
                goto L1f
            L79:
                r4 = move-exception
                goto L2a
            L7d:
                r4 = move-exception
                goto L35
            L81:
                r4 = move-exception
                goto L40
            L85:
                r4 = move-exception
                goto L4c
            L89:
                r4 = move-exception
                goto L58
            L8d:
                r4 = move-exception
                goto L64
            L91:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.p002hs.HSDescriptorParser.C03243.m3825clinit():void");
        }
    }

    public HSDescriptorParser(HiddenService hiddenService, DocumentFieldParser documentFieldParser) {
        this(hiddenService, documentFieldParser, null);
    }

    public HSDescriptorParser(HiddenService hiddenService, DocumentFieldParser documentFieldParser, HSDescriptorCookie hSDescriptorCookie) {
        this.fieldParser = documentFieldParser;
        this.fieldParser.setHandler(createParsingHandler());
        this.descriptor = new HSDescriptor(hiddenService);
        this.authentication = new HSAuthentication(hSDescriptorCookie);
    }

    private ByteBuffer createIntroductionPointBuffer(DocumentObject documentObject) {
        byte[] decode = Base64.decode(documentObject.getContent(false));
        if (decode[0] == 105) {
            return ByteBuffer.wrap(decode);
        }
        try {
            return ByteBuffer.wrap(this.authentication.decryptIntroductionPoints(decode));
        } catch (HSAuthenticationException e) {
            throw new TorParsingException("Failed to decrypt introduction points: " + e.getMessage());
        }
    }

    private DocumentParsingHandler createParsingHandler() {
        return new DocumentParsingHandler() { // from class: com.subgraph.orchid.circuits.hs.HSDescriptorParser.1
            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void endOfDocument() {
            }

            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void parseKeywordLine() {
                HSDescriptorParser.this.processKeywordLine();
            }
        };
    }

    private void processIntroductionPoints() {
        new IntroductionPointParser(new DocumentFieldParserImpl(createIntroductionPointBuffer(this.fieldParser.parseObject()))).parse(new DocumentParsingResultHandler<IntroductionPoint>() { // from class: com.subgraph.orchid.circuits.hs.HSDescriptorParser.2
            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
            public void documentInvalid(IntroductionPoint introductionPoint, String str) {
                HSDescriptorParser.logger.info("Invalid introduction point received");
            }

            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
            public void documentParsed(IntroductionPoint introductionPoint) {
                HSDescriptorParser.logger.fine("adding intro point " + introductionPoint.getIdentity());
                HSDescriptorParser.this.descriptor.addIntroductionPoint(introductionPoint);
            }

            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
            public void parsingError(String str) {
                HSDescriptorParser.logger.info("Error parsing introduction points: " + str);
            }
        });
    }

    private void processKeyword(HSDescriptorKeyword hSDescriptorKeyword) {
        switch (C03243.$SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorKeyword[hSDescriptorKeyword.ordinal()]) {
            case 1:
                this.descriptor.setDescriptorId(this.fieldParser.parseBase32Digest());
                return;
            case 2:
                if (this.fieldParser.parseInteger() != 2) {
                    throw new TorParsingException("Unexpected Descriptor version");
                }
                return;
            case 3:
                this.descriptor.setPermanentKey(this.fieldParser.parsePublicKey());
                return;
            case 4:
                this.descriptor.setSecretIdPart(this.fieldParser.parseBase32Digest());
                return;
            case 5:
                this.descriptor.setPublicationTime(this.fieldParser.parseTimestamp());
                return;
            case 6:
                this.descriptor.setProtocolVersions(this.fieldParser.parseIntegerList());
                return;
            case 7:
                processIntroductionPoints();
                return;
            case 8:
                processSignature();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processKeywordLine() {
        HSDescriptorKeyword findKeyword = HSDescriptorKeyword.findKeyword(this.fieldParser.getCurrentKeyword());
        if (findKeyword.equals(HSDescriptorKeyword.UNKNOWN_KEYWORD)) {
            return;
        }
        processKeyword(findKeyword);
    }

    private void processSignature() {
        this.fieldParser.endSignedEntity();
        if (this.fieldParser.verifySignedEntity(this.descriptor.getPermanentKey(), this.fieldParser.parseSignature())) {
            this.resultHandler.documentParsed(this.descriptor);
            return;
        }
        this.resultHandler.documentInvalid(this.descriptor, "Signature verification failed");
        this.fieldParser.logWarn("Signature failed for descriptor: " + this.descriptor.getDescriptorId().toBase32());
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public DocumentParsingResult<HSDescriptor> parse() {
        BasicDocumentParsingResult basicDocumentParsingResult = new BasicDocumentParsingResult();
        parse(basicDocumentParsingResult);
        return basicDocumentParsingResult;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public boolean parse(DocumentParsingResultHandler<HSDescriptor> documentParsingResultHandler) {
        this.resultHandler = documentParsingResultHandler;
        this.fieldParser.startSignedEntity();
        try {
            this.fieldParser.processDocument();
            return true;
        } catch (TorParsingException e) {
            documentParsingResultHandler.parsingError(e.getMessage());
            return false;
        }
    }
}
