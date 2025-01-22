package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/BridgeDescriptorFetcher.class */
public class BridgeDescriptorFetcher extends DocumentFetcher<RouterDescriptor> {
    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    DocumentParser<RouterDescriptor> createParser(ByteBuffer byteBuffer) {
        return PARSER_FACTORY.createRouterDescriptorParser(byteBuffer, true);
    }

    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    String getRequestPath() {
        return "/tor/server/authority";
    }
}
