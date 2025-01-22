package com.subgraph.orchid;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/InternalCircuit.class */
public interface InternalCircuit extends Circuit {
    DirectoryCircuit cannibalizeToDirectory(Router router);

    Circuit cannibalizeToIntroductionPoint(Router router);

    HiddenServiceCircuit connectHiddenService(CircuitNode circuitNode);
}
