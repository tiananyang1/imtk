package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/ConsensusFetcher.class */
public class ConsensusFetcher extends DocumentFetcher<ConsensusDocument> {
    private static final String CONSENSUS_BASE_PATH = "/tor/status-vote/current/";
    private final boolean useMicrodescriptors;

    public ConsensusFetcher(boolean z) {
        this.useMicrodescriptors = z;
    }

    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    DocumentParser<ConsensusDocument> createParser(ByteBuffer byteBuffer) {
        return PARSER_FACTORY.createConsensusDocumentParser(byteBuffer);
    }

    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    String getRequestPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(CONSENSUS_BASE_PATH);
        sb.append(this.useMicrodescriptors ? "consensus-microdesc" : "consensus");
        return sb.toString();
    }
}
