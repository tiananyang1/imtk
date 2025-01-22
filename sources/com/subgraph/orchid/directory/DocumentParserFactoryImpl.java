package com.subgraph.orchid.directory;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.RouterMicrodescriptor;
import com.subgraph.orchid.directory.certificate.KeyCertificateParser;
import com.subgraph.orchid.directory.consensus.ConsensusDocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParserFactory;
import com.subgraph.orchid.directory.router.RouterDescriptorParser;
import com.subgraph.orchid.directory.router.RouterMicrodescriptorParser;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DocumentParserFactoryImpl.class */
public class DocumentParserFactoryImpl implements DocumentParserFactory {
    @Override // com.subgraph.orchid.directory.parsing.DocumentParserFactory
    public DocumentParser<ConsensusDocument> createConsensusDocumentParser(ByteBuffer byteBuffer) {
        return new ConsensusDocumentParser(new DocumentFieldParserImpl(byteBuffer));
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParserFactory
    public DocumentParser<KeyCertificate> createKeyCertificateParser(ByteBuffer byteBuffer) {
        return new KeyCertificateParser(new DocumentFieldParserImpl(byteBuffer));
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParserFactory
    public DocumentParser<RouterDescriptor> createRouterDescriptorParser(ByteBuffer byteBuffer, boolean z) {
        return new RouterDescriptorParser(new DocumentFieldParserImpl(byteBuffer), z);
    }

    @Override // com.subgraph.orchid.directory.parsing.DocumentParserFactory
    public DocumentParser<RouterMicrodescriptor> createRouterMicrodescriptorParser(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        return new RouterMicrodescriptorParser(new DocumentFieldParserImpl(byteBuffer));
    }
}
