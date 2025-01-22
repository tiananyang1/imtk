package com.subgraph.orchid.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/encoders/Encoder.class */
public interface Encoder {
    int decode(String str, OutputStream outputStream) throws IOException;

    int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException;

    int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException;
}
