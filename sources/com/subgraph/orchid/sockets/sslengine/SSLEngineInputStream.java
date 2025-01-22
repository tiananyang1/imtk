package com.subgraph.orchid.sockets.sslengine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/sslengine/SSLEngineInputStream.class */
public class SSLEngineInputStream extends InputStream {
    private boolean isEOF;
    private final SSLEngineManager manager;
    private final ByteBuffer recvBuffer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLEngineInputStream(SSLEngineManager sSLEngineManager) {
        this.manager = sSLEngineManager;
        this.recvBuffer = sSLEngineManager.getRecvBuffer();
    }

    private boolean fillRecvBufferIfEmpty() throws IOException {
        if (this.isEOF) {
            return false;
        }
        if (this.recvBuffer.position() != 0 || this.manager.read() >= 0) {
            this.recvBuffer.flip();
            return this.recvBuffer.hasRemaining();
        }
        this.isEOF = true;
        return false;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.manager.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (!fillRecvBufferIfEmpty()) {
            return -1;
        }
        byte b = this.recvBuffer.get();
        this.recvBuffer.compact();
        return b & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!fillRecvBufferIfEmpty()) {
            return -1;
        }
        int min = Math.min(this.recvBuffer.remaining(), i2);
        this.recvBuffer.get(bArr, i, min);
        this.recvBuffer.compact();
        return min;
    }
}
