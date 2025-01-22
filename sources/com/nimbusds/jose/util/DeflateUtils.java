package com.nimbusds.jose.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/DeflateUtils.class */
public class DeflateUtils {
    private static final boolean NOWRAP = true;

    private DeflateUtils() {
    }

    public static byte[] compress(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater(8, true));
        deflaterOutputStream.write(bArr);
        deflaterOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompress(byte[] bArr) throws IOException {
        InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(bArr), new Inflater(true));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[1024];
        while (true) {
            int read = inflaterInputStream.read(bArr2);
            if (read <= 0) {
                inflaterInputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr2, 0, read);
        }
    }
}
