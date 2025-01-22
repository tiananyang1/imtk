package com.subgraph.orchid.directory.parsing;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/parsing/DocumentParsingResult.class */
public interface DocumentParsingResult<T> {
    T getDocument();

    T getInvalidDocument();

    String getMessage();

    List<T> getParsedDocuments();

    boolean isError();

    boolean isInvalid();

    boolean isOkay();
}
