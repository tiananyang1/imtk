package com.subgraph.orchid.directory;

import com.subgraph.orchid.ConsensusDocument;
import com.subgraph.orchid.Descriptor;
import com.subgraph.orchid.Directory;
import com.subgraph.orchid.DirectoryServer;
import com.subgraph.orchid.DirectoryStore;
import com.subgraph.orchid.GuardEntry;
import com.subgraph.orchid.KeyCertificate;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.RouterDescriptor;
import com.subgraph.orchid.RouterMicrodescriptor;
import com.subgraph.orchid.RouterStatus;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.crypto.TorRandom;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.RandomSet;
import com.subgraph.orchid.directory.parsing.DocumentParser;
import com.subgraph.orchid.directory.parsing.DocumentParserFactory;
import com.subgraph.orchid.directory.parsing.DocumentParsingResult;
import com.subgraph.orchid.events.Event;
import com.subgraph.orchid.events.EventHandler;
import com.subgraph.orchid.events.EventManager;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DirectoryImpl.class */
public class DirectoryImpl implements Directory {
    private static final Logger logger = Logger.getLogger(DirectoryImpl.class.getName());
    private static final DocumentParserFactory parserFactory = new DocumentParserFactoryImpl();
    private final DescriptorCache<RouterDescriptor> basicDescriptorCache;
    private final TorConfig config;
    private final EventManager consensusChangedManager;
    private ConsensusDocument consensusWaitingForCertificates;
    private ConsensusDocument currentConsensus;
    private final RandomSet<RouterImpl> directoryCaches;
    private boolean haveMinimumRouterInfo;
    private final DescriptorCache<RouterMicrodescriptor> microdescriptorCache;
    private boolean needRecalculateMinimumRouterInfo;
    private final TorRandom random;
    private final Set<ConsensusDocument.RequiredCertificate> requiredCertificates;
    private final Map<HexDigest, RouterImpl> routersByIdentity;
    private final Map<String, RouterImpl> routersByNickname;
    private final StateFile stateFile;
    private final DirectoryStore store;
    private final Object loadLock = new Object();
    private boolean isLoaded = false;
    private long last = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.directory.DirectoryImpl$4 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DirectoryImpl$4.class */
    public static /* synthetic */ class C03454 {
        static final /* synthetic */ int[] $SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus = new int[ConsensusDocument.SignatureStatus.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.ConsensusDocument$SignatureStatus[] r0 = com.subgraph.orchid.ConsensusDocument.SignatureStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.directory.DirectoryImpl.C03454.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus = r0
                int[] r0 = com.subgraph.orchid.directory.DirectoryImpl.C03454.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus     // Catch: java.lang.NoSuchFieldError -> L2b
                com.subgraph.orchid.ConsensusDocument$SignatureStatus r1 = com.subgraph.orchid.ConsensusDocument.SignatureStatus.STATUS_FAILED     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.subgraph.orchid.directory.DirectoryImpl.C03454.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.subgraph.orchid.ConsensusDocument$SignatureStatus r1 = com.subgraph.orchid.ConsensusDocument.SignatureStatus.STATUS_VERIFIED     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.subgraph.orchid.directory.DirectoryImpl.C03454.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.subgraph.orchid.ConsensusDocument$SignatureStatus r1 = com.subgraph.orchid.ConsensusDocument.SignatureStatus.STATUS_NEED_CERTS     // Catch: java.lang.NoSuchFieldError -> L33
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L33
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L33
                return
            L2b:
                r4 = move-exception
                goto L14
            L2f:
                r4 = move-exception
                goto L1f
            L33:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.directory.DirectoryImpl.C03454.m3854clinit():void");
        }
    }

    public DirectoryImpl(TorConfig torConfig, DirectoryStore directoryStore) {
        this.store = directoryStore == null ? new DirectoryStoreImpl(torConfig) : directoryStore;
        this.config = torConfig;
        this.stateFile = new StateFile(this.store, this);
        this.microdescriptorCache = createMicrodescriptorCache(this.store);
        this.basicDescriptorCache = createBasicDescriptorCache(this.store);
        this.routersByIdentity = new HashMap();
        this.routersByNickname = new HashMap();
        this.directoryCaches = new RandomSet<>();
        this.requiredCertificates = new HashSet();
        this.consensusChangedManager = new EventManager();
        this.random = new TorRandom();
    }

    private void addRouter(RouterImpl routerImpl) {
        this.routersByIdentity.put(routerImpl.getIdentityHash(), routerImpl);
        addRouterByNickname(routerImpl);
    }

    private void addRouterByNickname(RouterImpl routerImpl) {
        String nickname = routerImpl.getNickname();
        if (nickname == null || nickname.equals("Unnamed") || this.routersByNickname.containsKey(routerImpl.getNickname())) {
            return;
        }
        this.routersByNickname.put(nickname, routerImpl);
    }

    private void checkMinimumRouterInfo() {
        synchronized (this) {
            if (this.currentConsensus != null && this.currentConsensus.isLive()) {
                int i = 0;
                int i2 = 0;
                for (RouterImpl routerImpl : this.routersByIdentity.values()) {
                    int i3 = i2 + 1;
                    i2 = i3;
                    if (!routerImpl.isDescriptorDownloadable()) {
                        i++;
                        i2 = i3;
                    }
                }
                this.needRecalculateMinimumRouterInfo = false;
                this.haveMinimumRouterInfo = i * 4 > i2;
                return;
            }
            this.needRecalculateMinimumRouterInfo = true;
            this.haveMinimumRouterInfo = false;
        }
    }

    private void classifyRouter(RouterImpl routerImpl) {
        if (isValidDirectoryCache(routerImpl)) {
            this.directoryCaches.add(routerImpl);
        } else {
            this.directoryCaches.remove(routerImpl);
        }
    }

    private void clearAll() {
        this.routersByIdentity.clear();
        this.routersByNickname.clear();
        this.directoryCaches.clear();
    }

    private static DescriptorCache<RouterDescriptor> createBasicDescriptorCache(DirectoryStore directoryStore) {
        return new DescriptorCache<RouterDescriptor>(directoryStore, DirectoryStore.CacheFile.DESCRIPTOR_CACHE, DirectoryStore.CacheFile.DESCRIPTOR_JOURNAL) { // from class: com.subgraph.orchid.directory.DirectoryImpl.2
            @Override // com.subgraph.orchid.directory.DescriptorCache
            protected DocumentParser<RouterDescriptor> createDocumentParser(ByteBuffer byteBuffer) {
                return DirectoryImpl.parserFactory.createRouterDescriptorParser(byteBuffer, false);
            }
        };
    }

    private static DescriptorCache<RouterMicrodescriptor> createMicrodescriptorCache(DirectoryStore directoryStore) {
        return new DescriptorCache<RouterMicrodescriptor>(directoryStore, DirectoryStore.CacheFile.MICRODESCRIPTOR_CACHE, DirectoryStore.CacheFile.MICRODESCRIPTOR_JOURNAL) { // from class: com.subgraph.orchid.directory.DirectoryImpl.1
            @Override // com.subgraph.orchid.directory.DescriptorCache
            protected DocumentParser<RouterMicrodescriptor> createDocumentParser(ByteBuffer byteBuffer) {
                return DirectoryImpl.parserFactory.createRouterMicrodescriptorParser(byteBuffer);
            }
        };
    }

    private Descriptor getDescriptorForRouterStatus(RouterStatus routerStatus, boolean z) {
        return z ? this.microdescriptorCache.getDescriptor(routerStatus.getMicrodescriptorDigest()) : this.basicDescriptorCache.getDescriptor(routerStatus.getDescriptorDigest());
    }

    private boolean isValidDirectoryCache(RouterImpl routerImpl) {
        if (routerImpl.getDirectoryPort() == 0 || routerImpl.hasFlag("BadDirectory")) {
            return false;
        }
        return routerImpl.hasFlag("V2Dir");
    }

    private void loadCertificates(ByteBuffer byteBuffer) {
        DocumentParsingResult<KeyCertificate> parse = parserFactory.createKeyCertificateParser(byteBuffer).parse();
        if (testResult(parse, "certificates")) {
            Iterator<KeyCertificate> it = parse.getParsedDocuments().iterator();
            while (it.hasNext()) {
                addCertificate(it.next());
            }
        }
    }

    private void loadConsensus(ByteBuffer byteBuffer) {
        DocumentParsingResult<ConsensusDocument> parse = parserFactory.createConsensusDocumentParser(byteBuffer).parse();
        if (testResult(parse, "consensus")) {
            addConsensusDocument(parse.getDocument(), true);
        }
    }

    private void logElapsed() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.last;
        this.last = currentTimeMillis;
        logger.fine("Loaded in " + (currentTimeMillis - j) + " ms.");
    }

    private boolean removeRequiredCertificate(KeyCertificate keyCertificate) {
        Iterator<ConsensusDocument.RequiredCertificate> it = this.requiredCertificates.iterator();
        while (it.hasNext()) {
            if (it.next().getSigningKey().equals(keyCertificate.getAuthoritySigningKey().getFingerprint())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    private void storeCurrentConsensus() {
        ConsensusDocument consensusDocument = this.currentConsensus;
        if (consensusDocument != null) {
            if (consensusDocument.getFlavor() == ConsensusDocument.ConsensusFlavor.MICRODESC) {
                this.store.writeDocument(DirectoryStore.CacheFile.CONSENSUS_MICRODESC, this.currentConsensus);
            } else {
                this.store.writeDocument(DirectoryStore.CacheFile.CONSENSUS, this.currentConsensus);
            }
        }
    }

    private boolean testResult(DocumentParsingResult<?> documentParsingResult, String str) {
        if (documentParsingResult.isOkay()) {
            return true;
        }
        if (documentParsingResult.isError()) {
            logger.warning("Parsing error loading " + str + " : " + documentParsingResult.getMessage());
            return false;
        }
        if (!documentParsingResult.isInvalid()) {
            logger.warning("Unknown problem loading " + str);
            return false;
        }
        logger.warning("Problem loading " + str + " : " + documentParsingResult.getMessage());
        return false;
    }

    private RouterImpl updateOrCreateRouter(RouterStatus routerStatus, Map<HexDigest, RouterImpl> map) {
        RouterImpl routerImpl = map.get(routerStatus.getIdentity());
        if (routerImpl == null) {
            return RouterImpl.createFromRouterStatus(this, routerStatus);
        }
        routerImpl.updateStatus(routerStatus);
        return routerImpl;
    }

    @Override // com.subgraph.orchid.Directory
    public void addCertificate(KeyCertificate keyCertificate) {
        synchronized (TrustedAuthorities.getInstance()) {
            boolean removeRequiredCertificate = removeRequiredCertificate(keyCertificate);
            DirectoryServer authorityServerByIdentity = TrustedAuthorities.getInstance().getAuthorityServerByIdentity(keyCertificate.getAuthorityFingerprint());
            if (authorityServerByIdentity == null) {
                logger.warning("Certificate read for unknown directory authority with identity: " + keyCertificate.getAuthorityFingerprint());
                return;
            }
            authorityServerByIdentity.addCertificate(keyCertificate);
            if (this.consensusWaitingForCertificates != null && removeRequiredCertificate) {
                int i = C03454.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus[this.consensusWaitingForCertificates.verifySignatures().ordinal()];
                if (i == 1) {
                    this.consensusWaitingForCertificates = null;
                } else if (i == 2) {
                    addConsensusDocument(this.consensusWaitingForCertificates, false);
                    this.consensusWaitingForCertificates = null;
                } else if (i == 3) {
                    this.requiredCertificates.addAll(this.consensusWaitingForCertificates.getRequiredCertificates());
                }
            }
        }
    }

    @Override // com.subgraph.orchid.Directory
    public void addConsensusDocument(ConsensusDocument consensusDocument, boolean z) {
        synchronized (this) {
            if (consensusDocument.equals(this.currentConsensus)) {
                return;
            }
            if (this.currentConsensus != null && consensusDocument.getValidAfterTime().isBefore(this.currentConsensus.getValidAfterTime())) {
                logger.warning("New consensus document is older than current consensus document");
                return;
            }
            synchronized (TrustedAuthorities.getInstance()) {
                int i = C03454.$SwitchMap$com$subgraph$orchid$ConsensusDocument$SignatureStatus[consensusDocument.verifySignatures().ordinal()];
                if (i == 1) {
                    logger.warning("Unable to verify signatures on consensus document, discarding...");
                    return;
                }
                if (i == 3) {
                    this.consensusWaitingForCertificates = consensusDocument;
                    this.requiredCertificates.addAll(consensusDocument.getRequiredCertificates());
                    return;
                }
                this.requiredCertificates.addAll(consensusDocument.getRequiredCertificates());
                HashMap hashMap = new HashMap(this.routersByIdentity);
                clearAll();
                for (RouterStatus routerStatus : consensusDocument.getRouterStatusEntries()) {
                    if (routerStatus.hasFlag("Running") && routerStatus.hasFlag("Valid")) {
                        RouterImpl updateOrCreateRouter = updateOrCreateRouter(routerStatus, hashMap);
                        addRouter(updateOrCreateRouter);
                        classifyRouter(updateOrCreateRouter);
                    }
                    Descriptor descriptorForRouterStatus = getDescriptorForRouterStatus(routerStatus, consensusDocument.getFlavor() == ConsensusDocument.ConsensusFlavor.MICRODESC);
                    if (descriptorForRouterStatus != null) {
                        descriptorForRouterStatus.setLastListed(consensusDocument.getValidAfterTime().getTime());
                    }
                }
                logger.fine("Loaded " + this.routersByIdentity.size() + " routers from consensus document");
                this.currentConsensus = consensusDocument;
                if (!z) {
                    storeCurrentConsensus();
                }
                this.consensusChangedManager.fireEvent(new Event() { // from class: com.subgraph.orchid.directory.DirectoryImpl.3
                });
            }
        }
    }

    @Override // com.subgraph.orchid.Directory
    public void addGuardEntry(GuardEntry guardEntry) {
        waitUntilLoaded();
        this.stateFile.addGuardEntry(guardEntry);
    }

    @Override // com.subgraph.orchid.Directory
    public void addRouterDescriptors(List<RouterDescriptor> list) {
        this.basicDescriptorCache.addDescriptors(list);
        this.needRecalculateMinimumRouterInfo = true;
    }

    @Override // com.subgraph.orchid.Directory
    public void addRouterMicrodescriptors(List<RouterMicrodescriptor> list) {
        synchronized (this) {
            this.microdescriptorCache.addDescriptors(list);
            this.needRecalculateMinimumRouterInfo = true;
        }
    }

    @Override // com.subgraph.orchid.Directory
    public void close() {
        this.basicDescriptorCache.shutdown();
        this.microdescriptorCache.shutdown();
    }

    @Override // com.subgraph.orchid.Directory
    public GuardEntry createGuardEntryFor(Router router) {
        waitUntilLoaded();
        return this.stateFile.createGuardEntryFor(router);
    }

    @Override // com.subgraph.orchid.Directory
    public List<Router> getAllRouters() {
        ArrayList arrayList;
        waitUntilLoaded();
        synchronized (this.routersByIdentity) {
            arrayList = new ArrayList(this.routersByIdentity.values());
        }
        return arrayList;
    }

    @Override // com.subgraph.orchid.Directory
    public RouterDescriptor getBasicDescriptorFromCache(HexDigest hexDigest) {
        return this.basicDescriptorCache.getDescriptor(hexDigest);
    }

    @Override // com.subgraph.orchid.Directory
    public ConsensusDocument getCurrentConsensusDocument() {
        return this.currentConsensus;
    }

    @Override // com.subgraph.orchid.Directory
    public Collection<DirectoryServer> getDirectoryAuthorities() {
        return TrustedAuthorities.getInstance().getAuthorityServers();
    }

    @Override // com.subgraph.orchid.Directory
    public List<GuardEntry> getGuardEntries() {
        waitUntilLoaded();
        return this.stateFile.getGuardEntries();
    }

    @Override // com.subgraph.orchid.Directory
    public RouterMicrodescriptor getMicrodescriptorFromCache(HexDigest hexDigest) {
        return this.microdescriptorCache.getDescriptor(hexDigest);
    }

    @Override // com.subgraph.orchid.Directory
    public DirectoryServer getRandomDirectoryAuthority() {
        List<DirectoryServer> authorityServers = TrustedAuthorities.getInstance().getAuthorityServers();
        return authorityServers.get(this.random.nextInt(authorityServers.size()));
    }

    @Override // com.subgraph.orchid.Directory
    public Set<ConsensusDocument.RequiredCertificate> getRequiredCertificates() {
        return new HashSet(this.requiredCertificates);
    }

    @Override // com.subgraph.orchid.Directory
    public Router getRouterByIdentity(HexDigest hexDigest) {
        RouterImpl routerImpl;
        waitUntilLoaded();
        synchronized (this.routersByIdentity) {
            routerImpl = this.routersByIdentity.get(hexDigest);
        }
        return routerImpl;
    }

    @Override // com.subgraph.orchid.Directory
    public Router getRouterByName(String str) {
        if (str.equals("Unnamed")) {
            return null;
        }
        if (str.length() == 41 && str.charAt(0) == '$') {
            try {
                return getRouterByIdentity(HexDigest.createFromString(str.substring(1)));
            } catch (Exception e) {
                return null;
            }
        }
        waitUntilLoaded();
        return this.routersByNickname.get(str);
    }

    @Override // com.subgraph.orchid.Directory
    public List<Router> getRouterListByNames(List<String> list) {
        waitUntilLoaded();
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            Router routerByName = getRouterByName(str);
            if (routerByName == null) {
                throw new TorException("Could not find router named: " + str);
            }
            arrayList.add(routerByName);
        }
        return arrayList;
    }

    @Override // com.subgraph.orchid.Directory
    public List<Router> getRoutersWithDownloadableDescriptors() {
        ArrayList arrayList;
        synchronized (this) {
            waitUntilLoaded();
            arrayList = new ArrayList();
            for (RouterImpl routerImpl : this.routersByIdentity.values()) {
                if (routerImpl.isDescriptorDownloadable()) {
                    arrayList.add(routerImpl);
                }
            }
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < arrayList.size()) {
                    Router router = (Router) arrayList.get(i2);
                    int nextInt = this.random.nextInt(arrayList.size());
                    arrayList.set(i2, (Router) arrayList.get(nextInt));
                    arrayList.set(nextInt, router);
                    i = i2 + 1;
                }
            }
        }
        return arrayList;
    }

    @Override // com.subgraph.orchid.Directory
    public boolean hasPendingConsensus() {
        boolean z;
        synchronized (TrustedAuthorities.getInstance()) {
            z = this.consensusWaitingForCertificates != null;
        }
        return z;
    }

    @Override // com.subgraph.orchid.Directory
    public boolean haveMinimumRouterInfo() {
        boolean z;
        synchronized (this) {
            if (this.needRecalculateMinimumRouterInfo) {
                checkMinimumRouterInfo();
            }
            z = this.haveMinimumRouterInfo;
        }
        return z;
    }

    @Override // com.subgraph.orchid.Directory
    public void loadFromStore() {
        logger.info("Loading cached network information from disk");
        synchronized (this.loadLock) {
            if (this.isLoaded) {
                return;
            }
            boolean z = this.config.getUseMicrodescriptors() != TorConfig.AutoBoolValue.FALSE;
            this.last = System.currentTimeMillis();
            logger.info("Loading certificates");
            loadCertificates(this.store.loadCacheFile(DirectoryStore.CacheFile.CERTIFICATES));
            logElapsed();
            logger.info("Loading consensus");
            loadConsensus(this.store.loadCacheFile(z ? DirectoryStore.CacheFile.CONSENSUS_MICRODESC : DirectoryStore.CacheFile.CONSENSUS));
            logElapsed();
            if (z) {
                logger.info("Loading microdescriptor cache");
                this.microdescriptorCache.initialLoad();
            } else {
                logger.info("Loading descriptors");
                this.basicDescriptorCache.initialLoad();
            }
            this.needRecalculateMinimumRouterInfo = true;
            logElapsed();
            logger.info("loading state file");
            this.stateFile.parseBuffer(this.store.loadCacheFile(DirectoryStore.CacheFile.STATE));
            logElapsed();
            this.isLoaded = true;
            this.loadLock.notifyAll();
        }
    }

    @Override // com.subgraph.orchid.Directory
    public void registerConsensusChangedHandler(EventHandler eventHandler) {
        this.consensusChangedManager.addListener(eventHandler);
    }

    @Override // com.subgraph.orchid.Directory
    public void removeGuardEntry(GuardEntry guardEntry) {
        waitUntilLoaded();
        this.stateFile.removeGuardEntry(guardEntry);
    }

    @Override // com.subgraph.orchid.Directory
    public void storeCertificates() {
        synchronized (TrustedAuthorities.getInstance()) {
            ArrayList arrayList = new ArrayList();
            Iterator<DirectoryServer> it = TrustedAuthorities.getInstance().getAuthorityServers().iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().getCertificates());
            }
            this.store.writeDocumentList(DirectoryStore.CacheFile.CERTIFICATES, arrayList);
        }
    }

    @Override // com.subgraph.orchid.Directory
    public void unregisterConsensusChangedHandler(EventHandler eventHandler) {
        this.consensusChangedManager.removeListener(eventHandler);
    }

    @Override // com.subgraph.orchid.Directory
    public void waitUntilLoaded() {
        synchronized (this.loadLock) {
            while (!this.isLoaded) {
                try {
                    this.loadLock.wait();
                } catch (InterruptedException e) {
                    logger.warning("Thread interrupted while waiting for directory to load from disk");
                }
            }
        }
    }
}
