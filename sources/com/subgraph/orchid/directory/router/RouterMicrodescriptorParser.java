package com.subgraph.orchid.directory.router;

import com.subgraph.orchid.RouterMicrodescriptor;
import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.crypto.TorMessageDigest;
import com.subgraph.orchid.directory.parsing.BasicDocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParsingHandler;
import com.subgraph.orchid.directory.parsing.DocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/RouterMicrodescriptorParser.class */
public class RouterMicrodescriptorParser implements DocumentParser<RouterMicrodescriptor> {
    private RouterMicrodescriptorImpl currentDescriptor;
    private final DocumentFieldParser fieldParser;
    private DocumentParsingResultHandler<RouterMicrodescriptor> resultHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.directory.router.RouterMicrodescriptorParser$2 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/RouterMicrodescriptorParser$2.class */
    public static /* synthetic */ class C03602 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$directory$router$RouterMicrodescriptorKeyword */
        static final /* synthetic */ int[] f212x2e89eb95 = new int[RouterMicrodescriptorKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:29:0x004d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword[] r0 = com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.router.RouterMicrodescriptorParser.C03602.f212x2e89eb95 = r0
                int[] r0 = com.subgraph.orchid.directory.router.RouterMicrodescriptorParser.C03602.f212x2e89eb95     // Catch: java.lang.NoSuchFieldError -> L41
                com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword r1 = com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword.ONION_KEY     // Catch: java.lang.NoSuchFieldError -> L41
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L41
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L41
            L14:
                int[] r0 = com.subgraph.orchid.directory.router.RouterMicrodescriptorParser.C03602.f212x2e89eb95     // Catch: java.lang.NoSuchFieldError -> L41 java.lang.NoSuchFieldError -> L45
                com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword r1 = com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword.NTOR_ONION_KEY     // Catch: java.lang.NoSuchFieldError -> L45
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L45
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L45
            L1f:
                int[] r0 = com.subgraph.orchid.directory.router.RouterMicrodescriptorParser.C03602.f212x2e89eb95     // Catch: java.lang.NoSuchFieldError -> L45 java.lang.NoSuchFieldError -> L49
                com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword r1 = com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword.FAMILY     // Catch: java.lang.NoSuchFieldError -> L49
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L49
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L49
            L2a:
                int[] r0 = com.subgraph.orchid.directory.router.RouterMicrodescriptorParser.C03602.f212x2e89eb95     // Catch: java.lang.NoSuchFieldError -> L49 java.lang.NoSuchFieldError -> L4d
                com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword r1 = com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword.P     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L35:
                int[] r0 = com.subgraph.orchid.directory.router.RouterMicrodescriptorParser.C03602.f212x2e89eb95     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword r1 = com.subgraph.orchid.directory.router.RouterMicrodescriptorKeyword.A     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
                return
            L41:
                r4 = move-exception
                goto L14
            L45:
                r4 = move-exception
                goto L1f
            L49:
                r4 = move-exception
                goto L2a
            L4d:
                r4 = move-exception
                goto L35
            L51:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.router.RouterMicrodescriptorParser.C03602.m3879clinit():void");
        }
    }

    public RouterMicrodescriptorParser(DocumentFieldParser documentFieldParser) {
        this.fieldParser = documentFieldParser;
        this.fieldParser.setHandler(createParsingHandler());
    }

    private DocumentParsingHandler createParsingHandler() {
        return new DocumentParsingHandler() { // from class: com.subgraph.orchid.directory.router.RouterMicrodescriptorParser.1
            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void endOfDocument() {
                if (RouterMicrodescriptorParser.this.currentDescriptor != null) {
                    RouterMicrodescriptorParser routerMicrodescriptorParser = RouterMicrodescriptorParser.this;
                    routerMicrodescriptorParser.finalizeDescriptor(routerMicrodescriptorParser.currentDescriptor);
                }
            }

            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void parseKeywordLine() {
                RouterMicrodescriptorParser.this.processKeywordLine();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finalizeDescriptor(RouterMicrodescriptorImpl routerMicrodescriptorImpl) {
        TorMessageDigest torMessageDigest = new TorMessageDigest(true);
        torMessageDigest.update(routerMicrodescriptorImpl.getRawDocumentData());
        routerMicrodescriptorImpl.setDescriptorDigest(torMessageDigest.getHexDigest());
        if (routerMicrodescriptorImpl.isValidDocument()) {
            this.resultHandler.documentParsed(routerMicrodescriptorImpl);
        } else {
            this.resultHandler.documentInvalid(routerMicrodescriptorImpl, "Microdescriptor data invalid");
        }
    }

    private void processKeyword(RouterMicrodescriptorKeyword routerMicrodescriptorKeyword) {
        RouterMicrodescriptorImpl routerMicrodescriptorImpl;
        this.fieldParser.verifyExpectedArgumentCount(routerMicrodescriptorKeyword.getKeyword(), routerMicrodescriptorKeyword.getArgumentCount());
        int i = C03602.f212x2e89eb95[routerMicrodescriptorKeyword.ordinal()];
        if (i == 1) {
            processOnionKeyLine();
            return;
        }
        if (i == 2) {
            RouterMicrodescriptorImpl routerMicrodescriptorImpl2 = this.currentDescriptor;
            if (routerMicrodescriptorImpl2 != null) {
                routerMicrodescriptorImpl2.setNtorOnionKey(this.fieldParser.parseNtorPublicKey());
                return;
            }
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            processP();
        } else {
            while (this.fieldParser.argumentsRemaining() > 0 && (routerMicrodescriptorImpl = this.currentDescriptor) != null) {
                routerMicrodescriptorImpl.addFamilyMember(this.fieldParser.parseString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processKeywordLine() {
        RouterMicrodescriptorKeyword findKeyword = RouterMicrodescriptorKeyword.findKeyword(this.fieldParser.getCurrentKeyword());
        if (!findKeyword.equals(RouterMicrodescriptorKeyword.UNKNOWN_KEYWORD)) {
            processKeyword(findKeyword);
        }
        RouterMicrodescriptorImpl routerMicrodescriptorImpl = this.currentDescriptor;
        if (routerMicrodescriptorImpl != null) {
            routerMicrodescriptorImpl.setRawDocumentData(this.fieldParser.getRawDocument());
        }
    }

    private void processOnionKeyLine() {
        RouterMicrodescriptorImpl routerMicrodescriptorImpl = this.currentDescriptor;
        if (routerMicrodescriptorImpl != null) {
            finalizeDescriptor(routerMicrodescriptorImpl);
        }
        this.currentDescriptor = new RouterMicrodescriptorImpl();
        this.fieldParser.resetRawDocument(RouterMicrodescriptorKeyword.ONION_KEY.getKeyword() + "\n");
        this.currentDescriptor.setOnionKey(this.fieldParser.parsePublicKey());
    }

    private void processP() {
        if (this.currentDescriptor == null) {
            return;
        }
        String parseString = this.fieldParser.parseString();
        if ("accept".equals(parseString)) {
            this.currentDescriptor.addAcceptPorts(this.fieldParser.parseString());
            return;
        }
        if ("reject".equals(parseString)) {
            this.currentDescriptor.addRejectPorts(this.fieldParser.parseString());
            return;
        }
        this.fieldParser.logWarn("Unexpected P field in microdescriptor: " + parseString);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public DocumentParsingResult<RouterMicrodescriptor> parse() {
        BasicDocumentParsingResult basicDocumentParsingResult = new BasicDocumentParsingResult();
        parse(basicDocumentParsingResult);
        return basicDocumentParsingResult;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public boolean parse(DocumentParsingResultHandler<RouterMicrodescriptor> documentParsingResultHandler) {
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
