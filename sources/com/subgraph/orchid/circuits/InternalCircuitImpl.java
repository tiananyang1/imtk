package com.subgraph.orchid.circuits;

import com.subgraph.orchid.Circuit;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.DirectoryCircuit;
import com.subgraph.orchid.HiddenServiceCircuit;
import com.subgraph.orchid.InternalCircuit;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.StreamConnectFailedException;
import com.subgraph.orchid.circuits.path.CircuitPathChooser;
import com.subgraph.orchid.circuits.path.PathSelectionFailedException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/InternalCircuitImpl.class */
public class InternalCircuitImpl extends CircuitImpl implements InternalCircuit, DirectoryCircuit, HiddenServiceCircuit {
    private boolean ntorEnabled;
    private InternalType type;

    /* renamed from: com.subgraph.orchid.circuits.InternalCircuitImpl$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/InternalCircuitImpl$1.class */
    static /* synthetic */ class C03181 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$circuits$InternalCircuitImpl$InternalType */
        static final /* synthetic */ int[] f186xf3f8465b = new int[InternalType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:22:0x003e
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.circuits.InternalCircuitImpl$InternalType[] r0 = com.subgraph.orchid.circuits.InternalCircuitImpl.InternalType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.circuits.InternalCircuitImpl.C03181.f186xf3f8465b = r0
                int[] r0 = com.subgraph.orchid.circuits.InternalCircuitImpl.C03181.f186xf3f8465b     // Catch: java.lang.NoSuchFieldError -> L36
                com.subgraph.orchid.circuits.InternalCircuitImpl$InternalType r1 = com.subgraph.orchid.circuits.InternalCircuitImpl.InternalType.HS_CIRCUIT     // Catch: java.lang.NoSuchFieldError -> L36
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L36
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L36
            L14:
                int[] r0 = com.subgraph.orchid.circuits.InternalCircuitImpl.C03181.f186xf3f8465b     // Catch: java.lang.NoSuchFieldError -> L36 java.lang.NoSuchFieldError -> L3a
                com.subgraph.orchid.circuits.InternalCircuitImpl$InternalType r1 = com.subgraph.orchid.circuits.InternalCircuitImpl.InternalType.HS_DIRECTORY     // Catch: java.lang.NoSuchFieldError -> L3a
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L3a
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L3a
            L1f:
                int[] r0 = com.subgraph.orchid.circuits.InternalCircuitImpl.C03181.f186xf3f8465b     // Catch: java.lang.NoSuchFieldError -> L3a java.lang.NoSuchFieldError -> L3e
                com.subgraph.orchid.circuits.InternalCircuitImpl$InternalType r1 = com.subgraph.orchid.circuits.InternalCircuitImpl.InternalType.HS_INTRODUCTION     // Catch: java.lang.NoSuchFieldError -> L3e
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L3e
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L3e
            L2a:
                int[] r0 = com.subgraph.orchid.circuits.InternalCircuitImpl.C03181.f186xf3f8465b     // Catch: java.lang.NoSuchFieldError -> L3e java.lang.NoSuchFieldError -> L42
                com.subgraph.orchid.circuits.InternalCircuitImpl$InternalType r1 = com.subgraph.orchid.circuits.InternalCircuitImpl.InternalType.UNUSED     // Catch: java.lang.NoSuchFieldError -> L42
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L42
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L42
                return
            L36:
                r4 = move-exception
                goto L14
            L3a:
                r4 = move-exception
                goto L1f
            L3e:
                r4 = move-exception
                goto L2a
            L42:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.InternalCircuitImpl.C03181.m3807clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/InternalCircuitImpl$InternalType.class */
    public enum InternalType {
        UNUSED,
        HS_INTRODUCTION,
        HS_DIRECTORY,
        HS_CIRCUIT
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InternalCircuitImpl(CircuitManagerImpl circuitManagerImpl) {
        this(circuitManagerImpl, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalCircuitImpl(CircuitManagerImpl circuitManagerImpl, List<Router> list) {
        super(circuitManagerImpl, list);
        this.type = InternalType.UNUSED;
        this.ntorEnabled = circuitManagerImpl.isNtorEnabled();
    }

    private void cannibalizeTo(Router router) {
        if (this.type == InternalType.UNUSED) {
            new CircuitExtender(this, this.ntorEnabled).extendTo(router);
            return;
        }
        throw new IllegalStateException("Cannot cannibalize internal circuit with type " + this.type);
    }

    @Override // com.subgraph.orchid.InternalCircuit
    public DirectoryCircuit cannibalizeToDirectory(Router router) {
        cannibalizeTo(router);
        this.type = InternalType.HS_DIRECTORY;
        return this;
    }

    @Override // com.subgraph.orchid.InternalCircuit
    public Circuit cannibalizeToIntroductionPoint(Router router) {
        cannibalizeTo(router);
        this.type = InternalType.HS_INTRODUCTION;
        return this;
    }

    @Override // com.subgraph.orchid.circuits.CircuitImpl
    protected List<Router> choosePathForCircuit(CircuitPathChooser circuitPathChooser) throws InterruptedException, PathSelectionFailedException {
        return circuitPathChooser.chooseInternalPath();
    }

    @Override // com.subgraph.orchid.InternalCircuit
    public HiddenServiceCircuit connectHiddenService(CircuitNode circuitNode) {
        if (this.type == InternalType.UNUSED) {
            appendNode(circuitNode);
            this.type = InternalType.HS_CIRCUIT;
            return this;
        }
        throw new IllegalStateException("Cannot connect hidden service from internal circuit type " + this.type);
    }

    @Override // com.subgraph.orchid.circuits.CircuitImpl
    protected String getCircuitTypeLabel() {
        int i = C03181.f186xf3f8465b[this.type.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "(null)" : "Internal" : "HS Introduction" : "HS Directory" : "Hidden Service";
    }

    @Override // com.subgraph.orchid.DirectoryCircuit
    public Stream openDirectoryStream(long j, boolean z) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        if (this.type != InternalType.HS_DIRECTORY) {
            throw new IllegalStateException("Cannot open directory stream on internal circuit with type " + this.type);
        }
        StreamImpl createNewStream = createNewStream();
        try {
            createNewStream.openDirectory(j);
            return createNewStream;
        } catch (Exception e) {
            removeStream(createNewStream);
            return processStreamOpenException(e);
        }
    }

    @Override // com.subgraph.orchid.HiddenServiceCircuit
    public Stream openStream(int i, long j) throws InterruptedException, TimeoutException, StreamConnectFailedException {
        if (this.type != InternalType.HS_CIRCUIT) {
            throw new IllegalStateException("Cannot open stream to hidden service from internal circuit type " + this.type);
        }
        StreamImpl createNewStream = createNewStream();
        try {
            createNewStream.openExit("", i, j);
            return createNewStream;
        } catch (Exception e) {
            removeStream(createNewStream);
            return processStreamOpenException(e);
        }
    }
}
