package com.subgraph.orchid.crypto;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorKeyAgreement.class */
public interface TorKeyAgreement {
    byte[] createOnionSkin();

    boolean deriveKeysFromHandshakeResponse(byte[] bArr, byte[] bArr2, byte[] bArr3);
}
