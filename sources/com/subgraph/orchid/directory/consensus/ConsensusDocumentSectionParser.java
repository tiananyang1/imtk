package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.directory.consensus.ConsensusDocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentFieldParser;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/ConsensusDocumentSectionParser.class */
public abstract class ConsensusDocumentSectionParser {
    protected final ConsensusDocumentImpl document;
    protected final DocumentFieldParser fieldParser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConsensusDocumentSectionParser(DocumentFieldParser documentFieldParser, ConsensusDocumentImpl consensusDocumentImpl) {
        this.fieldParser = documentFieldParser;
        this.document = consensusDocumentImpl;
    }

    abstract String getNextStateKeyword();

    abstract ConsensusDocumentParser.DocumentSection getSection();

    abstract ConsensusDocumentParser.DocumentSection nextSection();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConsensusDocumentParser.DocumentSection parseKeywordLine() {
        String currentKeyword = this.fieldParser.getCurrentKeyword();
        if (getNextStateKeyword() != null && getNextStateKeyword().equals(currentKeyword)) {
            return nextSection();
        }
        DocumentKeyword findKeyword = DocumentKeyword.findKeyword(currentKeyword, getSection());
        if (!findKeyword.equals(DocumentKeyword.UNKNOWN_KEYWORD)) {
            parseLine(findKeyword);
        }
        return getSection();
    }

    abstract void parseLine(DocumentKeyword documentKeyword);
}
