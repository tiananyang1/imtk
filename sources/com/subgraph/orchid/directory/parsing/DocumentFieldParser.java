package com.subgraph.orchid.directory.parsing;

import com.subgraph.orchid.crypto.TorMessageDigest;
import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.crypto.TorSignature;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import com.subgraph.orchid.data.Timestamp;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/parsing/DocumentFieldParser.class */
public interface DocumentFieldParser {
    int argumentsRemaining();

    void endSignedEntity();

    String getCurrentKeyword();

    String getRawDocument();

    TorMessageDigest getSignatureMessageDigest();

    TorMessageDigest getSignatureMessageDigest256();

    void logDebug(String str);

    void logError(String str);

    void logWarn(String str);

    IPv4Address parseAddress();

    HexDigest parseBase32Digest();

    byte[] parseBase64Data();

    boolean parseBoolean();

    String parseConcatenatedString();

    HexDigest parseFingerprint();

    HexDigest parseHexDigest();

    int parseInteger();

    int parseInteger(String str);

    int[] parseIntegerList();

    String parseNickname();

    byte[] parseNtorPublicKey();

    DocumentObject parseObject();

    NameIntegerParameter parseParameter();

    int parsePort();

    int parsePort(String str);

    TorPublicKey parsePublicKey();

    TorSignature parseSignature();

    String parseString();

    Timestamp parseTimestamp();

    void processDocument();

    void resetRawDocument();

    void resetRawDocument(String str);

    void setDelimiter(String str);

    void setHandler(DocumentParsingHandler documentParsingHandler);

    void setRecognizeOpt();

    void setSignatureIgnoreToken(String str);

    void startSignedEntity();

    void verifyExpectedArgumentCount(String str, int i);

    boolean verifySignedEntity(TorPublicKey torPublicKey, TorSignature torSignature);
}
