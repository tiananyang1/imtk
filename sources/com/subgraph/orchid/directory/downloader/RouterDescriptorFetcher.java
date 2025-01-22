package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/RouterDescriptorFetcher.class */
public class RouterDescriptorFetcher extends DocumentFetcher<RouterDescriptor> {
    private final List<HexDigest> fingerprints;

    public RouterDescriptorFetcher(Collection<HexDigest> collection) {
        this.fingerprints = new ArrayList(collection);
    }

    private void appendFingerprint(StringBuilder sb, HexDigest hexDigest) {
        if (sb.length() > 0) {
            sb.append("+");
        }
        sb.append(hexDigest.toString());
    }

    private String fingerprintsToRequestString() {
        StringBuilder sb = new StringBuilder();
        Iterator<HexDigest> it = this.fingerprints.iterator();
        while (it.hasNext()) {
            appendFingerprint(sb, it.next());
        }
        return sb.toString();
    }

    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    DocumentParser<RouterDescriptor> createParser(ByteBuffer byteBuffer) {
        return PARSER_FACTORY.createRouterDescriptorParser(byteBuffer, true);
    }

    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    String getRequestPath() {
        return "/tor/server/d/" + fingerprintsToRequestString();
    }
}
