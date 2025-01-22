package com.subgraph.orchid.directory.parsing;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/parsing/DocumentParser.class */
public interface DocumentParser<T> {
    DocumentParsingResult<T> parse();

    boolean parse(DocumentParsingResultHandler<T> documentParsingResultHandler);
}
