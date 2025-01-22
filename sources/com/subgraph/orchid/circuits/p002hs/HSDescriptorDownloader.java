package com.subgraph.orchid.circuits.p002hs;

import com.subgraph.orchid.InternalCircuit;
import com.subgraph.orchid.OpenFailedException;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.TorException;
import com.subgraph.orchid.circuits.CircuitManagerImpl;
import com.subgraph.orchid.directory.DocumentFieldParserImpl;
import com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorDownloader.class */
public class HSDescriptorDownloader {
    private static final Logger logger = Logger.getLogger(HSDescriptorDirectory.class.getName());
    private final CircuitManagerImpl circuitManager;
    private final List<HSDescriptorDirectory> directories;
    private final HiddenService hiddenService;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorDownloader$DescriptorParseResult.class */
    public static class DescriptorParseResult implements DocumentParsingResultHandler<HSDescriptor> {

        /* renamed from: dd */
        HSDescriptorDirectory f190dd;
        HSDescriptor descriptor;

        public DescriptorParseResult(HSDescriptorDirectory hSDescriptorDirectory) {
            this.f190dd = hSDescriptorDirectory;
        }

        @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
        public void documentInvalid(HSDescriptor hSDescriptor, String str) {
            HSDescriptorDownloader.logger.info("Invalid HS descriptor document received from " + this.f190dd.getDirectory() + " for descriptor " + this.f190dd.getDescriptorId());
        }

        @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
        public void documentParsed(HSDescriptor hSDescriptor) {
            this.descriptor = hSDescriptor;
        }

        HSDescriptor getDescriptor() {
            return this.descriptor;
        }

        @Override // com.subgraph.orchid.directory.parsing.DocumentParsingResultHandler
        public void parsingError(String str) {
            HSDescriptorDownloader.logger.info("Failed to parse HS descriptor document received from " + this.f190dd.getDirectory() + " for descriptor " + this.f190dd.getDescriptorId() + " : " + str);
        }
    }

    public HSDescriptorDownloader(HiddenService hiddenService, CircuitManagerImpl circuitManagerImpl, List<HSDescriptorDirectory> list) {
        this.hiddenService = hiddenService;
        this.circuitManager = circuitManagerImpl;
        this.directories = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02cd  */
    /* JADX WARN: Type inference failed for: r0v15, types: [com.subgraph.orchid.Stream] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.subgraph.orchid.Stream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.subgraph.orchid.circuits.p002hs.HSDescriptor downloadDescriptorFrom(com.subgraph.orchid.circuits.p002hs.HSDescriptorDirectory r5) {
        /*
            Method dump skipped, instructions count: 744
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.p002hs.HSDescriptorDownloader.downloadDescriptorFrom(com.subgraph.orchid.circuits.hs.HSDescriptorDirectory):com.subgraph.orchid.circuits.hs.HSDescriptor");
    }

    private Stream openHSDirectoryStream(Router router) throws TimeoutException, InterruptedException, OpenFailedException {
        InternalCircuit cleanInternalCircuit = this.circuitManager.getCleanInternalCircuit();
        try {
            return cleanInternalCircuit.cannibalizeToDirectory(router).openDirectoryStream(10000L, true);
        } catch (StreamConnectFailedException e) {
            cleanInternalCircuit.markForClose();
            throw new OpenFailedException("Failed to open directory stream");
        } catch (TorException e2) {
            cleanInternalCircuit.markForClose();
            throw new OpenFailedException("Failed to extend circuit to HS directory: " + e2.getMessage());
        }
    }

    private HSDescriptor readDocument(HSDescriptorDirectory hSDescriptorDirectory, ByteBuffer byteBuffer) {
        DocumentFieldParserImpl documentFieldParserImpl = new DocumentFieldParserImpl(byteBuffer);
        HiddenService hiddenService = this.hiddenService;
        HSDescriptorParser hSDescriptorParser = new HSDescriptorParser(hiddenService, documentFieldParserImpl, hiddenService.getAuthenticationCookie());
        DescriptorParseResult descriptorParseResult = new DescriptorParseResult(hSDescriptorDirectory);
        hSDescriptorParser.parse(descriptorParseResult);
        return descriptorParseResult.getDescriptor();
    }

    public HSDescriptor downloadDescriptor() {
        Iterator<HSDescriptorDirectory> it = this.directories.iterator();
        while (it.hasNext()) {
            HSDescriptor downloadDescriptorFrom = downloadDescriptorFrom(it.next());
            if (downloadDescriptorFrom != null) {
                return downloadDescriptorFrom;
            }
        }
        return null;
    }
}
