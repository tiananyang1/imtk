package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.directory.DocumentParserFactoryImpl;
import com.subgraph.orchid.directory.parsing.BasicDocumentParsingResult;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParserFactory;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/DocumentFetcher.class */
public abstract class DocumentFetcher<T> {
    protected static final DocumentParserFactory PARSER_FACTORY = new DocumentParserFactoryImpl();

    private ByteBuffer makeRequest(HttpConnection httpConnection) throws IOException, DirectoryRequestFailedException {
        httpConnection.sendGetRequest(getRequestPath());
        httpConnection.readResponse();
        if (httpConnection.getStatusCode() == 200) {
            return httpConnection.getMessageBody();
        }
        throw new DirectoryRequestFailedException("Request " + getRequestPath() + " to directory " + httpConnection.getHost() + " returned error code: " + httpConnection.getStatusCode() + " " + httpConnection.getStatusMessage());
    }

    private List<T> processResponse(ByteBuffer byteBuffer) throws DirectoryRequestFailedException {
        DocumentParser<T> createParser = createParser(byteBuffer);
        BasicDocumentParsingResult basicDocumentParsingResult = new BasicDocumentParsingResult();
        if (createParser.parse(basicDocumentParsingResult)) {
            return basicDocumentParsingResult.getParsedDocuments();
        }
        throw new DirectoryRequestFailedException("Failed to parse response from directory: " + basicDocumentParsingResult.getMessage());
    }

    abstract DocumentParser<T> createParser(ByteBuffer byteBuffer);

    abstract String getRequestPath();

    public List<T> requestDocuments(HttpConnection httpConnection) throws IOException, DirectoryRequestFailedException {
        ByteBuffer makeRequest = makeRequest(httpConnection);
        return makeRequest.hasRemaining() ? processResponse(makeRequest) : Collections.emptyList();
    }
}
