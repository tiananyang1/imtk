package com.nimbusds.jose.util;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/BoundedInputStream.class */
public class BoundedInputStream extends InputStream {

    /* renamed from: in */
    private final InputStream f122in;
    private long mark;
    private final long max;
    private long pos;
    private boolean propagateClose;

    public BoundedInputStream(InputStream inputStream) {
        this(inputStream, -1L);
    }

    public BoundedInputStream(InputStream inputStream, long j) {
        this.pos = 0L;
        this.mark = -1L;
        this.propagateClose = true;
        this.max = j;
        this.f122in = inputStream;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        long j = this.max;
        if (j < 0 || this.pos < j) {
            return this.f122in.available();
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.propagateClose) {
            this.f122in.close();
        }
    }

    public long getLimitBytes() {
        return this.max;
    }

    public boolean isPropagateClose() {
        return this.propagateClose;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        synchronized (this) {
            this.f122in.mark(i);
            this.mark = this.pos;
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f122in.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        long j = this.max;
        if (j < 0 || this.pos < j) {
            int read = this.f122in.read();
            this.pos++;
            return read;
        }
        throw new IOException("Exceeded configured input limit of " + this.max + " bytes");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.max;
        if (j >= 0 && this.pos >= j) {
            throw new IOException("Exceeded configured input limit of " + this.max + " bytes");
        }
        int read = this.f122in.read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        this.pos += read;
        long j2 = this.max;
        if (j2 >= 0 && this.pos >= j2) {
            throw new IOException("Exceeded configured input limit of " + this.max + " bytes");
        }
        return read;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        synchronized (this) {
            this.f122in.reset();
            this.pos = this.mark;
        }
    }

    public void setPropagateClose(boolean z) {
        this.propagateClose = z;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = this.max;
        long j3 = j;
        if (j2 >= 0) {
            j3 = Math.min(j, j2 - this.pos);
        }
        long skip = this.f122in.skip(j3);
        this.pos += skip;
        return skip;
    }

    public String toString() {
        return this.f122in.toString();
    }
}
