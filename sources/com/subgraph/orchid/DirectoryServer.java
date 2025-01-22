package com.subgraph.orchid;

import com.subgraph.orchid.data.HexDigest;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/DirectoryServer.class */
public interface DirectoryServer extends Router {
    void addCertificate(KeyCertificate keyCertificate);

    KeyCertificate getCertificateByFingerprint(HexDigest hexDigest);

    List<KeyCertificate> getCertificates();

    @Override // com.subgraph.orchid.Router
    int getDirectoryPort();

    HexDigest getV3Identity();

    boolean isBridgeAuthority();

    boolean isExtraInfoCache();

    boolean isHiddenServiceAuthority();

    boolean isV2Authority();

    boolean isV3Authority();
}
