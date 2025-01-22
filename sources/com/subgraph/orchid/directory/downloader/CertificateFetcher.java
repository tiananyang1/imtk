package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.xiaomi.mipush.sdk.Constants;
import java.nio.ByteBuffer;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/CertificateFetcher.class */
public class CertificateFetcher extends DocumentFetcher<KeyCertificate> {
    private final Set<ConsensusDocument.RequiredCertificate> requiredCertificates;

    public CertificateFetcher(Set<ConsensusDocument.RequiredCertificate> set) {
        this.requiredCertificates = set;
    }

    private String getRequiredCertificatesRequestString() {
        StringBuilder sb = new StringBuilder();
        for (ConsensusDocument.RequiredCertificate requiredCertificate : this.requiredCertificates) {
            if (sb.length() > 0) {
                sb.append("+");
            }
            sb.append(requiredCertificate.getAuthorityIdentity().toString());
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            sb.append(requiredCertificate.getSigningKey().toString());
        }
        return sb.toString();
    }

    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    DocumentParser<KeyCertificate> createParser(ByteBuffer byteBuffer) {
        return PARSER_FACTORY.createKeyCertificateParser(byteBuffer);
    }

    @Override // com.subgraph.orchid.directory.downloader.DocumentFetcher
    String getRequestPath() {
        return "/tor/keys/fp-sk/" + getRequiredCertificatesRequestString();
    }
}
