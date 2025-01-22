package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CompressionAlgorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.util.DeflateUtils;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/DeflateHelper.class */
class DeflateHelper {
    DeflateHelper() {
    }

    public static byte[] applyCompression(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        CompressionAlgorithm compressionAlgorithm = jWEHeader.getCompressionAlgorithm();
        if (compressionAlgorithm == null) {
            return bArr;
        }
        if (!compressionAlgorithm.equals(CompressionAlgorithm.DEF)) {
            throw new JOSEException("Unsupported compression algorithm: " + compressionAlgorithm);
        }
        try {
            return DeflateUtils.compress(bArr);
        } catch (Exception e) {
            throw new JOSEException("Couldn't compress plain text: " + e.getMessage(), e);
        }
    }

    public static byte[] applyDecompression(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        CompressionAlgorithm compressionAlgorithm = jWEHeader.getCompressionAlgorithm();
        if (compressionAlgorithm == null) {
            return bArr;
        }
        if (!compressionAlgorithm.equals(CompressionAlgorithm.DEF)) {
            throw new JOSEException("Unsupported compression algorithm: " + compressionAlgorithm);
        }
        try {
            return DeflateUtils.decompress(bArr);
        } catch (Exception e) {
            throw new JOSEException("Couldn't decompress plain text: " + e.getMessage(), e);
        }
    }
}
