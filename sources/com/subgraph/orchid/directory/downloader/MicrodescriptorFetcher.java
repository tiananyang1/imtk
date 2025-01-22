package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.RouterMicrodescriptor;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.xiaomi.mipush.sdk.Constants;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/MicrodescriptorFetcher.class */
public class MicrodescriptorFetcher extends DocumentFetcher<RouterMicrodescriptor> {
    private final List<HexDigest> fingerprints;

    public MicrodescriptorFetcher(Collection<HexDigest> collection) {
        this.fingerprints = new ArrayList(collection);
    }

    private void appendFingerprint(StringBuilder sb, HexDigest hexDigest) {
        if (sb.length() > 0) {
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
        }
        sb.append(hexDigest.toBase64(true));
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
    DocumentParser<RouterMicrodescriptor> createParser(ByteBuffer byteBuffer) {
        return PARSER_FACTORY.createRouterMicrodescriptorParser(byteBuffer);
    }

    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    String getRequestPath() {
        return "/tor/micro/d/" + fingerprintsToRequestString();
    }
}
