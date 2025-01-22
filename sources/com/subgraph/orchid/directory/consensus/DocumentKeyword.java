package com.subgraph.orchid.directory.consensus;

import com.subgraph.orchid.directory.consensus.ConsensusDocumentParser;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/consensus/DocumentKeyword.class */
public enum DocumentKeyword {
    NETWORK_STATUS_VERSION("network-status-version", ConsensusDocumentParser.DocumentSection.PREAMBLE, 1),
    VOTE_STATUS("vote-status", ConsensusDocumentParser.DocumentSection.PREAMBLE, 1),
    CONSENSUS_METHODS("consensus-methods", ConsensusDocumentParser.DocumentSection.PREAMBLE, 1, true),
    CONSENSUS_METHOD("consensus-method", ConsensusDocumentParser.DocumentSection.PREAMBLE, 1, false, true),
    PUBLISHED("published", ConsensusDocumentParser.DocumentSection.PREAMBLE, 2, true),
    VALID_AFTER("valid-after", ConsensusDocumentParser.DocumentSection.PREAMBLE, 2),
    FRESH_UNTIL("fresh-until", ConsensusDocumentParser.DocumentSection.PREAMBLE, 2),
    VALID_UNTIL("valid-until", ConsensusDocumentParser.DocumentSection.PREAMBLE, 2),
    VOTING_DELAY("voting-delay", ConsensusDocumentParser.DocumentSection.PREAMBLE, 2),
    CLIENT_VERSIONS("client-versions", ConsensusDocumentParser.DocumentSection.PREAMBLE, 1),
    SERVER_VERSIONS("server-versions", ConsensusDocumentParser.DocumentSection.PREAMBLE, 1),
    KNOWN_FLAGS("known-flags", ConsensusDocumentParser.DocumentSection.PREAMBLE),
    PARAMS("params", ConsensusDocumentParser.DocumentSection.PREAMBLE),
    DIR_SOURCE("dir-source", ConsensusDocumentParser.DocumentSection.AUTHORITY, 6),
    CONTACT("contact", ConsensusDocumentParser.DocumentSection.AUTHORITY),
    VOTE_DIGEST("vote-digest", ConsensusDocumentParser.DocumentSection.AUTHORITY, 1, false, true),
    R("r", ConsensusDocumentParser.DocumentSection.ROUTER_STATUS, 8),
    S("s", ConsensusDocumentParser.DocumentSection.ROUTER_STATUS),
    V("v", ConsensusDocumentParser.DocumentSection.ROUTER_STATUS),
    W("w", ConsensusDocumentParser.DocumentSection.ROUTER_STATUS, 1),
    P("p", ConsensusDocumentParser.DocumentSection.ROUTER_STATUS, 2),
    M("m", ConsensusDocumentParser.DocumentSection.ROUTER_STATUS, 1),
    DIRECTORY_FOOTER("directory-footer", ConsensusDocumentParser.DocumentSection.FOOTER),
    BANDWIDTH_WEIGHTS("bandwidth-weights", ConsensusDocumentParser.DocumentSection.FOOTER, 19),
    DIRECTORY_SIGNATURE("directory-signature", ConsensusDocumentParser.DocumentSection.FOOTER, 2),
    UNKNOWN_KEYWORD("KEYWORD NOT FOUND");

    public static final int VARIABLE_ARGUMENT_COUNT = -1;
    private final int argumentCount;
    private final boolean consensusOnly;
    private final String keyword;
    private final ConsensusDocumentParser.DocumentSection section;
    private final boolean voteOnly;

    DocumentKeyword(String str) {
        this(str, ConsensusDocumentParser.DocumentSection.NO_SECTION);
    }

    DocumentKeyword(String str, ConsensusDocumentParser.DocumentSection documentSection) {
        this(str, documentSection, -1);
    }

    DocumentKeyword(String str, ConsensusDocumentParser.DocumentSection documentSection, int i) {
        this(str, documentSection, i, false);
    }

    DocumentKeyword(String str, ConsensusDocumentParser.DocumentSection documentSection, int i, boolean z) {
        this(str, documentSection, i, z, false);
    }

    DocumentKeyword(String str, ConsensusDocumentParser.DocumentSection documentSection, int i, boolean z, boolean z2) {
        this.keyword = str;
        this.section = documentSection;
        this.argumentCount = i;
        this.voteOnly = z;
        this.consensusOnly = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DocumentKeyword findKeyword(String str, ConsensusDocumentParser.DocumentSection documentSection) {
        DocumentKeyword[] values = values();
        int length = values.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return UNKNOWN_KEYWORD;
            }
            DocumentKeyword documentKeyword = values[i2];
            if (documentKeyword.getKeyword().equals(str) && documentKeyword.getSection().equals(documentSection)) {
                return documentKeyword;
            }
            i = i2 + 1;
        }
    }

    public int getArgumentCount() {
        return this.argumentCount;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public ConsensusDocumentParser.DocumentSection getSection() {
        return this.section;
    }

    public boolean isConsensusOnly() {
        return this.consensusOnly;
    }

    public boolean isVoteOnly() {
        return this.voteOnly;
    }
}
