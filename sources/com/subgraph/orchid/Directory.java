package com.subgraph.orchid;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.events.EventHandler;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Directory.class */
public interface Directory {
    void addCertificate(KeyCertificate keyCertificate);

    void addConsensusDocument(ConsensusDocument consensusDocument, boolean z);

    void addGuardEntry(GuardEntry guardEntry);

    void addRouterDescriptors(List<RouterDescriptor> list);

    void addRouterMicrodescriptors(List<RouterMicrodescriptor> list);

    void close();

    GuardEntry createGuardEntryFor(Router router);

    List<Router> getAllRouters();

    RouterDescriptor getBasicDescriptorFromCache(HexDigest hexDigest);

    ConsensusDocument getCurrentConsensusDocument();

    Collection<DirectoryServer> getDirectoryAuthorities();

    List<GuardEntry> getGuardEntries();

    RouterMicrodescriptor getMicrodescriptorFromCache(HexDigest hexDigest);

    DirectoryServer getRandomDirectoryAuthority();

    Set<ConsensusDocument.RequiredCertificate> getRequiredCertificates();

    Router getRouterByIdentity(HexDigest hexDigest);

    Router getRouterByName(String str);

    List<Router> getRouterListByNames(List<String> list);

    List<Router> getRoutersWithDownloadableDescriptors();

    boolean hasPendingConsensus();

    boolean haveMinimumRouterInfo();

    void loadFromStore();

    void registerConsensusChangedHandler(EventHandler eventHandler);

    void removeGuardEntry(GuardEntry guardEntry);

    void storeCertificates();

    void unregisterConsensusChangedHandler(EventHandler eventHandler);

    void waitUntilLoaded();
}
