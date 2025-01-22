package com.subgraph.orchid.directory.router;

import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.crypto.TorSignature;
import com.subgraph.orchid.data.BandwidthHistory;
import com.subgraph.orchid.data.Timestamp;
import com.subgraph.orchid.directory.parsing.BasicDocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParsingHandler;
import com.subgraph.orchid.directory.parsing.DocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/RouterDescriptorParser.class */
public class RouterDescriptorParser implements DocumentParser<RouterDescriptor> {
    private RouterDescriptorImpl currentDescriptor;
    private final DocumentFieldParser fieldParser;
    private DocumentParsingResultHandler<RouterDescriptor> resultHandler;
    private final boolean verifySignatures;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.directory.router.RouterDescriptorParser$2 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/RouterDescriptorParser$2.class */
    public static /* synthetic */ class C03582 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$directory$router$RouterDescriptorKeyword */
        static final /* synthetic */ int[] f209x234965bb = new int[RouterDescriptorKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:155:0x016d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                Method dump skipped, instructions count: 371
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.router.RouterDescriptorParser.C03582.m3877clinit():void");
        }
    }

    public RouterDescriptorParser(DocumentFieldParser documentFieldParser, boolean z) {
        this.fieldParser = documentFieldParser;
        this.fieldParser.setHandler(createParsingHandler());
        this.fieldParser.setRecognizeOpt();
        this.verifySignatures = z;
    }

    private DocumentParsingHandler createParsingHandler() {
        return new DocumentParsingHandler() { // from class: com.subgraph.orchid.directory.router.RouterDescriptorParser.1
            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void endOfDocument() {
            }

            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void parseKeywordLine() {
                RouterDescriptorParser.this.processKeywordLine();
            }
        };
    }

    private BandwidthHistory parseHistory() {
        Timestamp parseTimestamp = this.fieldParser.parseTimestamp();
        String parseString = this.fieldParser.parseString();
        this.fieldParser.parseString();
        BandwidthHistory bandwidthHistory = new BandwidthHistory(parseTimestamp, this.fieldParser.parseInteger(parseString.substring(1)));
        if (this.fieldParser.argumentsRemaining() == 0) {
            return bandwidthHistory;
        }
        String[] split = this.fieldParser.parseString().split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return bandwidthHistory;
            }
            bandwidthHistory.addSample(this.fieldParser.parseInteger(split[i2]));
            i = i2 + 1;
        }
    }

    private void processBandwidth() {
        this.currentDescriptor.setBandwidthValues(this.fieldParser.parseInteger(), this.fieldParser.parseInteger(), this.fieldParser.parseInteger());
    }

    private void processKeyword(RouterDescriptorKeyword routerDescriptorKeyword) {
        this.fieldParser.verifyExpectedArgumentCount(routerDescriptorKeyword.getKeyword(), routerDescriptorKeyword.getArgumentCount());
        switch (C03582.f209x234965bb[routerDescriptorKeyword.ordinal()]) {
            case 1:
                processRouter();
                return;
            case 2:
                processBandwidth();
                return;
            case 3:
                this.currentDescriptor.setPlatform(this.fieldParser.parseConcatenatedString());
                return;
            case 4:
                this.currentDescriptor.setPublished(this.fieldParser.parseTimestamp());
                return;
            case 5:
                this.currentDescriptor.setFingerprint(this.fieldParser.parseFingerprint());
                return;
            case 6:
                this.currentDescriptor.setHibernating(this.fieldParser.parseBoolean());
                return;
            case 7:
                this.currentDescriptor.setUptime(this.fieldParser.parseInteger());
                return;
            case 8:
                this.currentDescriptor.setOnionKey(this.fieldParser.parsePublicKey());
                return;
            case 9:
                this.currentDescriptor.setNtorOnionKey(this.fieldParser.parseNtorPublicKey());
                return;
            case 10:
                this.currentDescriptor.setIdentityKey(this.fieldParser.parsePublicKey());
                return;
            case 11:
                processSignature();
                return;
            case 12:
                this.currentDescriptor.addAcceptRule(this.fieldParser.parseString());
                return;
            case 13:
                this.currentDescriptor.addRejectRule(this.fieldParser.parseString());
                return;
            case 14:
                this.currentDescriptor.setContact(this.fieldParser.parseConcatenatedString());
                return;
            case 15:
                break;
            case 16:
                if (this.fieldParser.parseBoolean()) {
                    this.currentDescriptor.setEventDNS();
                    return;
                }
                return;
            case 17:
                processProtocols();
                return;
            case 18:
                this.currentDescriptor.setCachesExtraInfo();
                return;
            case 19:
                this.currentDescriptor.setHiddenServiceDir();
                return;
            case 20:
                this.currentDescriptor.setAllowSingleHopExits();
                return;
            case 21:
                this.currentDescriptor.setExtraInfoDigest(this.fieldParser.parseHexDigest());
                return;
            case 22:
                this.currentDescriptor.setReadHistory(parseHistory());
                return;
            case 23:
                this.currentDescriptor.setWriteHistory(parseHistory());
                return;
            default:
                return;
        }
        while (this.fieldParser.argumentsRemaining() > 0) {
            this.currentDescriptor.addFamilyMember(this.fieldParser.parseString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processKeywordLine() {
        RouterDescriptorKeyword findKeyword = RouterDescriptorKeyword.findKeyword(this.fieldParser.getCurrentKeyword());
        if (findKeyword.equals(RouterDescriptorKeyword.UNKNOWN_KEYWORD)) {
            return;
        }
        processKeyword(findKeyword);
    }

    private void processProtocols() {
        String parseString = this.fieldParser.parseString();
        if (!parseString.equals("Link")) {
            throw new TorParsingException("Expected 'Link' token in protocol line got: " + parseString);
        }
        while (true) {
            String parseString2 = this.fieldParser.parseString();
            if (parseString2.equals("Circuit")) {
                break;
            } else {
                this.currentDescriptor.addLinkProtocolVersion(this.fieldParser.parseInteger(parseString2));
            }
        }
        while (this.fieldParser.argumentsRemaining() > 0) {
            this.currentDescriptor.addCircuitProtocolVersion(this.fieldParser.parseInteger());
        }
    }

    private void processRouter() {
        this.currentDescriptor.setNickname(this.fieldParser.parseNickname());
        this.currentDescriptor.setAddress(this.fieldParser.parseAddress());
        this.currentDescriptor.setRouterPort(this.fieldParser.parsePort());
        this.fieldParser.parsePort();
        this.currentDescriptor.setDirectoryPort(this.fieldParser.parsePort());
    }

    private void processSignature() {
        this.fieldParser.endSignedEntity();
        this.currentDescriptor.setDescriptorHash(this.fieldParser.getSignatureMessageDigest().getHexDigest());
        TorSignature parseSignature = this.fieldParser.parseSignature();
        this.currentDescriptor.setRawDocumentData(this.fieldParser.getRawDocument());
        if (verifyCurrentDescriptor(parseSignature)) {
            this.resultHandler.documentParsed(this.currentDescriptor);
        }
        startNewDescriptor();
    }

    private void startNewDescriptor() {
        this.fieldParser.resetRawDocument();
        this.fieldParser.startSignedEntity();
        this.currentDescriptor = new RouterDescriptorImpl();
    }

    private boolean verifyCurrentDescriptor(TorSignature torSignature) {
        if (this.verifySignatures && !this.fieldParser.verifySignedEntity(this.currentDescriptor.getIdentityKey(), torSignature)) {
            this.resultHandler.documentInvalid(this.currentDescriptor, "Signature failed.");
            this.fieldParser.logWarn("Signature failed for router: " + this.currentDescriptor.getNickname());
            return false;
        }
        this.currentDescriptor.setValidSignature();
        if (!this.currentDescriptor.isValidDocument()) {
            this.resultHandler.documentInvalid(this.currentDescriptor, "Router data invalid");
            this.fieldParser.logWarn("Router data invalid for router: " + this.currentDescriptor.getNickname());
        }
        return this.currentDescriptor.isValidDocument();
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public DocumentParsingResult<RouterDescriptor> parse() {
        BasicDocumentParsingResult basicDocumentParsingResult = new BasicDocumentParsingResult();
        parse(basicDocumentParsingResult);
        return basicDocumentParsingResult;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public boolean parse(DocumentParsingResultHandler<RouterDescriptor> documentParsingResultHandler) {
        this.resultHandler = documentParsingResultHandler;
        startNewDescriptor();
        try {
            this.fieldParser.processDocument();
            return true;
        } catch (TorParsingException e) {
            documentParsingResultHandler.parsingError(e.getMessage());
            return false;
        }
    }
}
