package com.subgraph.orchid.circuits;

import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.circuits.cells.RelayCellImpl;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/TorOutputStream.class */
public class TorOutputStream extends OutputStream {
    private long bytesSent = 0;
    private RelayCell currentOutputCell;
    private volatile boolean isClosed;
    private final StreamImpl stream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TorOutputStream(StreamImpl streamImpl) {
        this.stream = streamImpl;
    }

    private void checkOpen() throws IOException {
        if (this.isClosed) {
            throw new IOException("Output stream is closed");
        }
    }

    private void flushCurrentOutputCell() {
        RelayCell relayCell = this.currentOutputCell;
        if (relayCell != null && relayCell.cellBytesConsumed() > 14) {
            this.stream.waitForSendWindowAndDecrement();
            this.stream.getCircuit().sendRelayCell(this.currentOutputCell);
            this.bytesSent += this.currentOutputCell.cellBytesConsumed() - 14;
        }
        this.currentOutputCell = new RelayCellImpl(this.stream.getTargetNode(), this.stream.getCircuit().getCircuitId(), this.stream.getStreamId(), 2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (this.isClosed) {
                return;
            }
            flush();
            this.isClosed = true;
            this.currentOutputCell = null;
            this.stream.close();
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        synchronized (this) {
            if (this.isClosed) {
                return;
            }
            flushCurrentOutputCell();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getBytesSent() {
        return this.bytesSent;
    }

    public String toString() {
        return "TorOutputStream stream=" + this.stream.getStreamId() + " node=" + this.stream.getTargetNode();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        synchronized (this) {
            checkOpen();
            if (this.currentOutputCell == null || this.currentOutputCell.cellBytesRemaining() == 0) {
                flushCurrentOutputCell();
            }
            this.currentOutputCell.putByte(i);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001c, code lost:            if (r5.currentOutputCell.cellBytesRemaining() == 0) goto L10;     */
    @Override // java.io.OutputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(byte[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
            r0 = r5
            monitor-enter(r0)
            r0 = r5
            r0.checkOpen()     // Catch: java.lang.Throwable -> L7a
            r0 = r5
            com.subgraph.orchid.RelayCell r0 = r0.currentOutputCell     // Catch: java.lang.Throwable -> L7a
            if (r0 == 0) goto L1f
            r0 = r7
            r9 = r0
            r0 = r8
            r10 = r0
            r0 = r5
            com.subgraph.orchid.RelayCell r0 = r0.currentOutputCell     // Catch: java.lang.Throwable -> L7a
            int r0 = r0.cellBytesRemaining()     // Catch: java.lang.Throwable -> L7a
            if (r0 != 0) goto L29
        L1f:
            r0 = r5
            r0.flushCurrentOutputCell()     // Catch: java.lang.Throwable -> L7a
            r0 = r8
            r10 = r0
            r0 = r7
            r9 = r0
        L29:
            r0 = r10
            if (r0 <= 0) goto L77
            r0 = r10
            r1 = r5
            com.subgraph.orchid.RelayCell r1 = r1.currentOutputCell     // Catch: java.lang.Throwable -> L7a
            int r1 = r1.cellBytesRemaining()     // Catch: java.lang.Throwable -> L7a
            if (r0 >= r1) goto L4d
            r0 = r5
            com.subgraph.orchid.RelayCell r0 = r0.currentOutputCell     // Catch: java.lang.Throwable -> L7a
            r1 = r6
            r2 = r9
            r3 = r10
            r0.putByteArray(r1, r2, r3)     // Catch: java.lang.Throwable -> L7a
            r0 = r5
            monitor-exit(r0)
            return
        L4d:
            r0 = r5
            com.subgraph.orchid.RelayCell r0 = r0.currentOutputCell     // Catch: java.lang.Throwable -> L7a
            int r0 = r0.cellBytesRemaining()     // Catch: java.lang.Throwable -> L7a
            r7 = r0
            r0 = r5
            com.subgraph.orchid.RelayCell r0 = r0.currentOutputCell     // Catch: java.lang.Throwable -> L7a
            r1 = r6
            r2 = r9
            r3 = r7
            r0.putByteArray(r1, r2, r3)     // Catch: java.lang.Throwable -> L7a
            r0 = r5
            r0.flushCurrentOutputCell()     // Catch: java.lang.Throwable -> L7a
            r0 = r9
            r1 = r7
            int r0 = r0 + r1
            r9 = r0
            r0 = r10
            r1 = r7
            int r0 = r0 - r1
            r10 = r0
            goto L29
        L77:
            r0 = r5
            monitor-exit(r0)
            return
        L7a:
            r6 = move-exception
            r0 = r5
            monitor-exit(r0)
            r0 = r6
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.TorOutputStream.write(byte[], int, int):void");
    }
}
