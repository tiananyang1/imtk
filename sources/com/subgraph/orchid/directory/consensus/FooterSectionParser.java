package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.crypto.TorMessageDigest;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.directory.consensus.ConsensusDocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;
import com.subgraph.orchid.directory.parsing.NameIntegerParameter;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/FooterSectionParser.class */
public class FooterSectionParser extends ConsensusDocumentSectionParser {
    private boolean seenFirstSignature;

    /* renamed from: com.subgraph.orchid.directory.consensus.FooterSectionParser$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/FooterSectionParser$1.class */
    static /* synthetic */ class C03531 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$directory$consensus$DocumentKeyword */
        static final /* synthetic */ int[] f206xac0fcbdc = new int[DocumentKeyword.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:8:0x0020
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.directory.consensus.DocumentKeyword[] r0 = com.subgraph.orchid.directory.consensus.DocumentKeyword.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.consensus.FooterSectionParser.C03531.f206xac0fcbdc = r0
                int[] r0 = com.subgraph.orchid.directory.consensus.FooterSectionParser.C03531.f206xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L20
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.BANDWIDTH_WEIGHTS     // Catch: java.lang.NoSuchFieldError -> L20
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L20
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L20
            L14:
                int[] r0 = com.subgraph.orchid.directory.consensus.FooterSectionParser.C03531.f206xac0fcbdc     // Catch: java.lang.NoSuchFieldError -> L20 java.lang.NoSuchFieldError -> L24
                com.subgraph.orchid.directory.consensus.DocumentKeyword r1 = com.subgraph.orchid.directory.consensus.DocumentKeyword.DIRECTORY_SIGNATURE     // Catch: java.lang.NoSuchFieldError -> L24
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L24
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L24
                return
            L20:
                r4 = move-exception
                goto L14
            L24:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.consensus.FooterSectionParser.C03531.m3869clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FooterSectionParser(DocumentFieldParser documentFieldParser, ConsensusDocumentImpl consensusDocumentImpl) {
        super(documentFieldParser, consensusDocumentImpl);
        this.seenFirstSignature = false;
    }

    private void doFirstSignature() {
        this.seenFirstSignature = true;
        this.fieldParser.endSignedEntity();
        TorMessageDigest signatureMessageDigest = this.fieldParser.getSignatureMessageDigest();
        signatureMessageDigest.update("directory-signature ");
        this.document.setSigningHash(signatureMessageDigest.getHexDigest());
        TorMessageDigest signatureMessageDigest256 = this.fieldParser.getSignatureMessageDigest256();
        signatureMessageDigest256.update("directory-signature ");
        this.document.setSigningHash256(signatureMessageDigest256.getHexDigest());
    }

    private void processBandwidthWeights() {
        int argumentsRemaining = this.fieldParser.argumentsRemaining();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= argumentsRemaining) {
                return;
            }
            NameIntegerParameter parseParameter = this.fieldParser.parseParameter();
            this.document.addBandwidthWeight(parseParameter.getName(), parseParameter.getValue());
            i = i2 + 1;
        }
    }

    private void processSignature() {
        HexDigest createFromString;
        if (!this.seenFirstSignature) {
            doFirstSignature();
        }
        String parseString = this.fieldParser.parseString();
        boolean z = false;
        if (parseString.length() < 20) {
            z = "sha256".equals(parseString);
            createFromString = this.fieldParser.parseHexDigest();
        } else {
            createFromString = HexDigest.createFromString(parseString);
        }
        this.document.addSignature(new DirectorySignature(createFromString, this.fieldParser.parseHexDigest(), this.fieldParser.parseSignature(), z));
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    String getNextStateKeyword() {
        return null;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    ConsensusDocumentParser.DocumentSection getSection() {
        return ConsensusDocumentParser.DocumentSection.FOOTER;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    ConsensusDocumentParser.DocumentSection nextSection() {
        return ConsensusDocumentParser.DocumentSection.NO_SECTION;
    }

    @Override // com.subgraph.orchid.directory.consensus.ConsensusDocumentSectionParser
    void parseLine(DocumentKeyword documentKeyword) {
        int i = C03531.f206xac0fcbdc[documentKeyword.ordinal()];
        if (i == 1) {
            processBandwidthWeights();
        } else {
            if (i != 2) {
                return;
            }
            processSignature();
        }
    }
}
