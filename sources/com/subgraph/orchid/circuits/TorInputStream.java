package com.subgraph.orchid.circuits;

import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.Stream;
import com.subgraph.orchid.circuits.cells.RelayCellImpl;
import com.subgraph.orchid.misc.GuardedBy;
import com.subgraph.orchid.misc.ThreadSafe;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/TorInputStream.class */
public class TorInputStream extends InputStream {
    private static final RelayCell CLOSE_SENTINEL = new RelayCellImpl(null, 0, 0, 0);
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);

    @GuardedBy("lock")
    private int availableBytes;

    @GuardedBy("lock")
    private long bytesReceived;

    @GuardedBy("lock")
    private boolean isClosed;

    @GuardedBy("lock")
    private boolean isEOF;
    private final Stream stream;
    private final Object lock = new Object();

    @GuardedBy("lock")
    private final Queue<RelayCell> incomingCells = new LinkedList();

    @GuardedBy("lock")
    private ByteBuffer currentBuffer = EMPTY_BUFFER;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TorInputStream(Stream stream) {
        this.stream = stream;
    }

    private void checkReadArguments(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i >= bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @GuardedBy("lock")
    private void fillBuffer() throws IOException {
        do {
            processIncomingCell(getNextCell());
            if (this.isEOF) {
                return;
            }
        } while (!this.currentBuffer.hasRemaining());
    }

    @GuardedBy("lock")
    private RelayCell getNextCell() throws IOException {
        while (this.incomingCells.isEmpty()) {
            try {
                this.lock.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Read interrupted");
            }
        }
        return this.incomingCells.remove();
    }

    @GuardedBy("lock")
    private void processIncomingCell(RelayCell relayCell) throws IOException {
        if (this.isClosed || relayCell == CLOSE_SENTINEL) {
            throw new IOException("Input stream closed");
        }
        int relayCommand = relayCell.getRelayCommand();
        if (relayCommand == 2) {
            this.currentBuffer = relayCell.getPayloadBuffer();
            return;
        }
        if (relayCommand == 3) {
            this.currentBuffer = EMPTY_BUFFER;
            this.isEOF = true;
        } else {
            throw new IOException("Unexpected RelayCell command type in TorInputStream queue: " + relayCell.getRelayCommand());
        }
    }

    @GuardedBy("lock")
    private int readFromCurrentBuffer(byte[] bArr, int i, int i2) {
        if (this.currentBuffer.remaining() < i2) {
            i2 = this.currentBuffer.remaining();
        }
        this.currentBuffer.get(bArr, i, i2);
        this.availableBytes -= i2;
        return i2;
    }

    @GuardedBy("lock")
    private void refillBufferIfNeeded() throws IOException {
        if (this.isEOF || this.currentBuffer.hasRemaining()) {
            return;
        }
        fillBuffer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addEndCell(RelayCell relayCell) {
        synchronized (this.lock) {
            if (this.isClosed) {
                return;
            }
            this.incomingCells.add(relayCell);
            this.lock.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addInputCell(RelayCell relayCell) {
        synchronized (this.lock) {
            if (this.isClosed) {
                return;
            }
            this.incomingCells.add(relayCell);
            this.bytesReceived += relayCell.cellBytesRemaining();
            this.availableBytes += relayCell.cellBytesRemaining();
            this.lock.notifyAll();
        }
    }

    @Override // java.io.InputStream
    public int available() {
        int i;
        synchronized (this.lock) {
            i = this.availableBytes;
        }
        return i;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.lock) {
            if (this.isClosed) {
                return;
            }
            this.isClosed = true;
            this.incomingCells.add(CLOSE_SENTINEL);
            this.lock.notifyAll();
            this.stream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getBytesReceived() {
        long j;
        synchronized (this.lock) {
            j = this.bytesReceived;
        }
        return j;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        synchronized (this.lock) {
            if (this.isClosed) {
                throw new IOException("Stream closed");
            }
            refillBufferIfNeeded();
            if (this.isEOF) {
                return -1;
            }
            this.availableBytes--;
            return this.currentBuffer.get() & 255;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this) {
            synchronized (this.lock) {
                if (this.isClosed) {
                    throw new IOException("Stream closed");
                }
                checkReadArguments(bArr, i, i2);
                if (i2 == 0) {
                    return 0;
                }
                refillBufferIfNeeded();
                if (this.isEOF) {
                    return -1;
                }
                int i3 = i2;
                int i4 = 0;
                while (i3 > 0 && !this.isEOF) {
                    refillBufferIfNeeded();
                    int readFromCurrentBuffer = i4 + readFromCurrentBuffer(bArr, i + i4, i2 - i4);
                    i3 = i2 - readFromCurrentBuffer;
                    i4 = readFromCurrentBuffer;
                    if (this.availableBytes == 0) {
                        return readFromCurrentBuffer;
                    }
                }
                return i4;
            }
        }
    }

    public String toString() {
        return "TorInputStream stream=" + this.stream.getStreamId() + " node=" + this.stream.getTargetNode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int unflushedCellCount() {
        int size;
        synchronized (this.lock) {
            size = this.incomingCells.size();
        }
        return size;
    }
}
