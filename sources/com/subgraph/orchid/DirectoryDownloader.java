package com.subgraph.orchid;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.directory.downloader.DirectoryRequestFailedException;
import java.util.List;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/DirectoryDownloader.class */
public interface DirectoryDownloader {
    RouterDescriptor downloadBridgeDescriptor(Router router) throws DirectoryRequestFailedException;

    ConsensusDocument downloadCurrentConsensus(boolean z) throws DirectoryRequestFailedException;

    ConsensusDocument downloadCurrentConsensus(boolean z, DirectoryCircuit directoryCircuit) throws DirectoryRequestFailedException;

    List<KeyCertificate> downloadKeyCertificates(Set<ConsensusDocument.RequiredCertificate> set) throws DirectoryRequestFailedException;

    List<KeyCertificate> downloadKeyCertificates(Set<ConsensusDocument.RequiredCertificate> set, DirectoryCircuit directoryCircuit) throws DirectoryRequestFailedException;

    List<RouterDescriptor> downloadRouterDescriptors(Set<HexDigest> set) throws DirectoryRequestFailedException;

    List<RouterDescriptor> downloadRouterDescriptors(Set<HexDigest> set, DirectoryCircuit directoryCircuit) throws DirectoryRequestFailedException;

    List<RouterMicrodescriptor> downloadRouterMicrodescriptors(Set<HexDigest> set) throws DirectoryRequestFailedException;

    List<RouterMicrodescriptor> downloadRouterMicrodescriptors(Set<HexDigest> set, DirectoryCircuit directoryCircuit) throws DirectoryRequestFailedException;

    void start(Directory directory);

    void stop();
}
