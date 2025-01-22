package com.subgraph.orchid.directory.parsing;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/parsing/DocumentParsingResultHandler.class */
public interface DocumentParsingResultHandler<T> {
    void documentInvalid(T t, String str);

    void documentParsed(T t);

    void parsingError(String str);
}
