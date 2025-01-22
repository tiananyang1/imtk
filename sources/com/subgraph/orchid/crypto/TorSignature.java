package com.subgraph.orchid.crypto;

import com.subgraph.orchid.TorException;
import com.subgraph.orchid.TorParsingException;
import com.subgraph.orchid.encoders.Base64;
import com.subgraph.orchid.encoders.Hex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorSignature.class */
public class TorSignature {
    private static final String ID_SIGNATURE_BEGIN = "-----BEGIN ID SIGNATURE-----";
    private static final String ID_SIGNATURE_END = "-----END ID SIGNATURE-----";
    private static final String SIGNATURE_BEGIN = "-----BEGIN SIGNATURE-----";
    private static final String SIGNATURE_END = "-----END SIGNATURE-----";
    private final DigestAlgorithm digestAlgorithm;
    private final byte[] signatureBytes;

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/TorSignature$DigestAlgorithm.class */
    public enum DigestAlgorithm {
        DIGEST_SHA1,
        DIGEST_SHA256
    }

    private TorSignature(byte[] bArr, DigestAlgorithm digestAlgorithm) {
        this.signatureBytes = bArr;
        this.digestAlgorithm = digestAlgorithm;
    }

    public static TorSignature createFromPEMBuffer(String str) {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
        String nextLine = nextLine(bufferedReader);
        if (SIGNATURE_BEGIN.equals(nextLine) || ID_SIGNATURE_BEGIN.equals(nextLine)) {
            return new TorSignature(Base64.decode(parseBase64Data(bufferedReader)), DigestAlgorithm.DIGEST_SHA1);
        }
        throw new TorParsingException("Did not find expected signature BEGIN header");
    }

    static String nextLine(BufferedReader bufferedReader) {
        try {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                return readLine;
            }
            throw new TorParsingException("Did not find expected signature END header");
        } catch (IOException e) {
            throw new TorException(e);
        }
    }

    private static String parseBase64Data(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String nextLine = nextLine(bufferedReader);
            if (SIGNATURE_END.equals(nextLine) || ID_SIGNATURE_END.equals(nextLine)) {
                break;
            }
            sb.append(nextLine);
        }
        return sb.toString();
    }

    public DigestAlgorithm getDigestAlgorithm() {
        return this.digestAlgorithm;
    }

    public byte[] getSignatureBytes() {
        byte[] bArr = this.signatureBytes;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String toString() {
        return "TorSignature: (" + this.signatureBytes.length + " bytes) " + new String(Hex.encode(this.signatureBytes));
    }

    public boolean verify(TorPublicKey torPublicKey, TorMessageDigest torMessageDigest) {
        return torPublicKey.verifySignature(this, torMessageDigest);
    }
}
