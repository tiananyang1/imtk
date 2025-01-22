package com.subgraph.orchid.directory.certificate;

import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.crypto.TorSignature;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.directory.parsing.BasicDocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParsingHandler;
import com.subgraph.orchid.directory.parsing.DocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/certificate/KeyCertificateParser.class */
public class KeyCertificateParser implements DocumentParser<KeyCertificate> {
    private static final int CURRENT_CERTIFICATE_VERSION = 3;
    private KeyCertificateImpl currentCertificate;
    private final DocumentFieldParser fieldParser;
    private DocumentParsingResultHandler<KeyCertificate> resultHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.directory.certificate.KeyCertificateParser$2 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/certificate/KeyCertificateParser$2.class */
    public static /* synthetic */ class C03482 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$directory$certificate$KeyCertificateKeyword */
        static final /* synthetic */ int[] f197x8d4d587f = new int[KeyCertificateKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:64:0x009d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f = r0
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L7d
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.DIR_KEY_CERTIFICATE_VERSION     // Catch: java.lang.NoSuchFieldError -> L7d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L7d
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L7d
            L14:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L7d java.lang.NoSuchFieldError -> L81
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.DIR_ADDRESS     // Catch: java.lang.NoSuchFieldError -> L81
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L81
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L81
            L1f:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L81 java.lang.NoSuchFieldError -> L85
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.FINGERPRINT     // Catch: java.lang.NoSuchFieldError -> L85
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L85
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L85
            L2a:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L85 java.lang.NoSuchFieldError -> L89
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.DIR_IDENTITY_KEY     // Catch: java.lang.NoSuchFieldError -> L89
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L89
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L89
            L35:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L89 java.lang.NoSuchFieldError -> L8d
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.DIR_SIGNING_KEY     // Catch: java.lang.NoSuchFieldError -> L8d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L8d
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L8d
            L40:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L8d java.lang.NoSuchFieldError -> L91
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.DIR_KEY_PUBLISHED     // Catch: java.lang.NoSuchFieldError -> L91
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L91
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L91
            L4c:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L91 java.lang.NoSuchFieldError -> L95
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.DIR_KEY_EXPIRES     // Catch: java.lang.NoSuchFieldError -> L95
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L95
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L95
            L58:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L95 java.lang.NoSuchFieldError -> L99
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.DIR_KEY_CROSSCERT     // Catch: java.lang.NoSuchFieldError -> L99
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L99
                r2 = 8
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L99
            L64:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L99 java.lang.NoSuchFieldError -> L9d
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.DIR_KEY_CERTIFICATION     // Catch: java.lang.NoSuchFieldError -> L9d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L9d
                r2 = 9
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L9d
            L70:
                int[] r0 = com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.f197x8d4d587f     // Catch: java.lang.NoSuchFieldError -> L9d java.lang.NoSuchFieldError -> La1
                com.subgraph.orchid.directory.certificate.KeyCertificateKeyword r1 = com.subgraph.orchid.directory.certificate.KeyCertificateKeyword.UNKNOWN_KEYWORD     // Catch: java.lang.NoSuchFieldError -> La1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La1
                r2 = 10
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La1
                return
            L7d:
                r4 = move-exception
                goto L14
            L81:
                r4 = move-exception
                goto L1f
            L85:
                r4 = move-exception
                goto L2a
            L89:
                r4 = move-exception
                goto L35
            L8d:
                r4 = move-exception
                goto L40
            L91:
                r4 = move-exception
                goto L4c
            L95:
                r4 = move-exception
                goto L58
            L99:
                r4 = move-exception
                goto L64
            L9d:
                r4 = move-exception
                goto L70
            La1:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.certificate.KeyCertificateParser.C03482.m3861clinit():void");
        }
    }

    public KeyCertificateParser(DocumentFieldParser documentFieldParser) {
        this.fieldParser = documentFieldParser;
        this.fieldParser.setHandler(createParsingHandler());
    }

    private DocumentParsingHandler createParsingHandler() {
        return new DocumentParsingHandler() { // from class: com.subgraph.orchid.directory.certificate.KeyCertificateParser.1
            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void endOfDocument() {
            }

            @Override // com.subgraph.orchid.directory.parsing.DocumentParsingHandler
            public void parseKeywordLine() {
                KeyCertificateParser.this.processKeywordLine();
            }
        };
    }

    private void processCertificateSignature() {
        this.fieldParser.endSignedEntity();
        if (verifyCurrentCertificate(this.fieldParser.parseSignature())) {
            this.currentCertificate.setRawDocumentData(this.fieldParser.getRawDocument());
            this.resultHandler.documentParsed(this.currentCertificate);
        }
        startNewCertificate();
    }

    private void processCertificateVersion() {
        int parseInteger = this.fieldParser.parseInteger();
        if (parseInteger == 3) {
            return;
        }
        throw new TorParsingException("Unexpected certificate version: " + parseInteger);
    }

    private void processDirectoryAddress() {
        String parseString = this.fieldParser.parseString();
        String[] split = parseString.split(Constants.COLON_SEPARATOR);
        if (split.length == 2) {
            this.currentCertificate.setDirectoryAddress(IPv4Address.createFromString(split[0]));
            this.currentCertificate.setDirectoryPort(this.fieldParser.parsePort(split[1]));
        } else {
            throw new TorParsingException("Address/Port string incorrectly formed: " + parseString);
        }
    }

    private void processKeyword(KeyCertificateKeyword keyCertificateKeyword) {
        switch (C03482.f197x8d4d587f[keyCertificateKeyword.ordinal()]) {
            case 1:
                processCertificateVersion();
                return;
            case 2:
                processDirectoryAddress();
                return;
            case 3:
                this.currentCertificate.setAuthorityFingerprint(this.fieldParser.parseHexDigest());
                return;
            case 4:
                this.currentCertificate.setAuthorityIdentityKey(this.fieldParser.parsePublicKey());
                return;
            case 5:
                this.currentCertificate.setAuthoritySigningKey(this.fieldParser.parsePublicKey());
                return;
            case 6:
                this.currentCertificate.setKeyPublishedTime(this.fieldParser.parseTimestamp());
                return;
            case 7:
                this.currentCertificate.setKeyExpiryTime(this.fieldParser.parseTimestamp());
                return;
            case 8:
                verifyCrossSignature(this.fieldParser.parseSignature());
                return;
            case 9:
                processCertificateSignature();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processKeywordLine() {
        KeyCertificateKeyword findKeyword = KeyCertificateKeyword.findKeyword(this.fieldParser.getCurrentKeyword());
        if (findKeyword.equals(KeyCertificateKeyword.UNKNOWN_KEYWORD)) {
            return;
        }
        processKeyword(findKeyword);
    }

    private void startNewCertificate() {
        this.fieldParser.resetRawDocument();
        this.fieldParser.startSignedEntity();
        this.currentCertificate = new KeyCertificateImpl();
    }

    private void verifyCrossSignature(TorSignature torSignature) {
        if (!this.currentCertificate.getAuthoritySigningKey().verifySignature(torSignature, this.currentCertificate.getAuthorityIdentityKey().getFingerprint())) {
            throw new TorParsingException("Cross signature on certificate failed.");
        }
    }

    private boolean verifyCurrentCertificate(TorSignature torSignature) {
        if (!this.fieldParser.verifySignedEntity(this.currentCertificate.getAuthorityIdentityKey(), torSignature)) {
            this.resultHandler.documentInvalid(this.currentCertificate, "Signature failed");
            this.fieldParser.logWarn("Signature failed for certificate with fingerprint: " + this.currentCertificate.getAuthorityFingerprint());
            return false;
        }
        this.currentCertificate.setValidSignature();
        boolean isValidDocument = this.currentCertificate.isValidDocument();
        if (!isValidDocument) {
            this.resultHandler.documentInvalid(this.currentCertificate, "Certificate data is invalid");
            this.fieldParser.logWarn("Certificate data is invalid for certificate with fingerprint: " + this.currentCertificate.getAuthorityFingerprint());
        }
        return isValidDocument;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public DocumentParsingResult<KeyCertificate> parse() {
        BasicDocumentParsingResult basicDocumentParsingResult = new BasicDocumentParsingResult();
        parse(basicDocumentParsingResult);
        return basicDocumentParsingResult;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParser
    public boolean parse(DocumentParsingResultHandler<KeyCertificate> documentParsingResultHandler) {
        this.resultHandler = documentParsingResultHandler;
        startNewCertificate();
        try {
            this.fieldParser.processDocument();
            return true;
        } catch (TorParsingException e) {
            documentParsingResultHandler.parsingError(e.getMessage());
            return false;
        }
    }
}
