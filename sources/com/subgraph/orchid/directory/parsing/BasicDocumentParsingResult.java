package com.subgraph.orchid.directory.parsing;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/parsing/BasicDocumentParsingResult.class */
public class BasicDocumentParsingResult<T> implements DocumentParsingResultHandler<T>, DocumentParsingResult<T> {
    private T invalidDocument;
    private final List<T> documents = new ArrayList();
    private boolean isOkay = true;
    private boolean isInvalid = false;
    private boolean isError = false;
    private String message = "";

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
    public void documentInvalid(T t, String str) {
        this.isOkay = false;
        this.isInvalid = true;
        this.invalidDocument = t;
        this.message = str;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
    public void documentParsed(T t) {
        this.documents.add(t);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResult
    public T getDocument() {
        if (this.documents.size() == 1) {
            return this.documents.get(0);
        }
        throw new IllegalStateException();
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResult
    public T getInvalidDocument() {
        return this.invalidDocument;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResult
    public String getMessage() {
        return this.message;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResult
    public List<T> getParsedDocuments() {
        return new ArrayList(this.documents);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResult
    public boolean isError() {
        return this.isError;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResult
    public boolean isInvalid() {
        return this.isInvalid;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResult
    public boolean isOkay() {
        return this.isOkay;
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
    public void parsingError(String str) {
        this.isOkay = false;
        this.isError = true;
        this.message = str;
    }
}
