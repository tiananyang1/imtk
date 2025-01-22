package com.subgraph.orchid.directory.parsing;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.RouterMicrodescriptor;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/parsing/DocumentParserFactory.class */
public interface DocumentParserFactory {
    DocumentParser<ConsensusDocument> createConsensusDocumentParser(ByteBuffer byteBuffer);

    DocumentParser<KeyCertificate> createKeyCertificateParser(ByteBuffer byteBuffer);

    DocumentParser<RouterDescriptor> createRouterDescriptorParser(ByteBuffer byteBuffer, boolean z);

    DocumentParser<RouterMicrodescriptor> createRouterMicrodescriptorParser(ByteBuffer byteBuffer);
}
