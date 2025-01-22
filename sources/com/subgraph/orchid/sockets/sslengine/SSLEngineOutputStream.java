package com.subgraph.orchid.sockets.sslengine;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/sslengine/SSLEngineOutputStream.class */
public class SSLEngineOutputStream extends OutputStream {
    private final SSLEngineManager manager;
    private final ByteBuffer outputBuffer;

    public SSLEngineOutputStream(SSLEngineManager sSLEngineManager) {
        this.manager = sSLEngineManager;
        this.outputBuffer = sSLEngineManager.getSendBuffer();
    }

    private int doWrite(byte[] bArr, int i, int i2) throws IOException {
        int min = Math.min(i2, this.outputBuffer.remaining());
        this.outputBuffer.put(bArr, i, min);
        this.manager.write();
        return min;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.manager.close();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.outputBuffer.put((byte) i);
        this.manager.write();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                return;
            } else {
                i3 = i4 + doWrite(bArr, i + i4, i2 - i4);
            }
        }
    }
}
