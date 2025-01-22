package com.subgraph.orchid.circuits.cells;

import com.subgraph.orchid.Cell;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/cells/CellImpl.class */
public class CellImpl implements Cell {
    protected final ByteBuffer cellBuffer;
    private final int circuitId;
    private final int command;

    /* JADX INFO: Access modifiers changed from: protected */
    public CellImpl(int i, int i2) {
        this.circuitId = i;
        this.command = i2;
        this.cellBuffer = ByteBuffer.wrap(new byte[Cell.CELL_LEN]);
        this.cellBuffer.putShort((short) i);
        this.cellBuffer.put((byte) i2);
        this.cellBuffer.mark();
    }

    private CellImpl(int i, int i2, int i3) {
        this.circuitId = i;
        this.command = i2;
        this.cellBuffer = ByteBuffer.wrap(new byte[i3 + 5]);
        this.cellBuffer.putShort((short) i);
        this.cellBuffer.put((byte) i2);
        this.cellBuffer.putShort((short) i3);
        this.cellBuffer.mark();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CellImpl(byte[] bArr) {
        this.cellBuffer = ByteBuffer.wrap(bArr);
        this.circuitId = this.cellBuffer.getShort() & 65535;
        this.command = this.cellBuffer.get() & 255;
        this.cellBuffer.mark();
    }

    public static CellImpl createCell(int i, int i2) {
        return new CellImpl(i, i2);
    }

    public static CellImpl createVarCell(int i, int i2, int i3) {
        return new CellImpl(i, i2, i3);
    }

    public static String errorToDescription(int i) {
        switch (i) {
            case 0:
                return "No error reason given";
            case 1:
                return "Tor protocol violation";
            case 2:
                return "Internal error";
            case 3:
                return "Response to a TRUNCATE command sent from client";
            case 4:
                return "Not currently operating; trying to save bandwidth.";
            case 5:
                return "Out of memory, sockets, or circuit IDs.";
            case 6:
                return "Unable to reach server.";
            case 7:
                return "Connected to server, but its OR identity was not as expected.";
            case 8:
                return "The OR connection that was carrying this circuit died.";
            case 9:
                return "The circuit has expired for being dirty or old.";
            case 10:
                return "Circuit construction took too long.";
            case 11:
                return "The circuit was destroyed without client TRUNCATE";
            case 12:
                return "Request for unknown hidden service";
            default:
                return "Error code " + i;
        }
    }

    private static void readAll(InputStream inputStream, byte[] bArr) throws IOException {
        readAll(inputStream, bArr, 0, bArr.length);
    }

    private static void readAll(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                return;
            }
            int read = inputStream.read(bArr, i + i4, i2 - i4);
            if (read == -1) {
                throw new EOFException();
            }
            i3 = i4 + read;
        }
    }

    public static CellImpl readFromInputStream(InputStream inputStream) throws IOException {
        ByteBuffer readHeaderFromInputStream = readHeaderFromInputStream(inputStream);
        int i = readHeaderFromInputStream.getShort() & 65535;
        int i2 = readHeaderFromInputStream.get() & 255;
        if (i2 == 7 || i2 > 127) {
            return readVarCell(i, i2, inputStream);
        }
        CellImpl cellImpl = new CellImpl(i, i2);
        readAll(inputStream, cellImpl.getCellBytes(), 3, Cell.CELL_PAYLOAD_LEN);
        return cellImpl;
    }

    private static ByteBuffer readHeaderFromInputStream(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[3];
        readAll(inputStream, bArr);
        return ByteBuffer.wrap(bArr);
    }

    private static CellImpl readVarCell(int i, int i2, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[2];
        readAll(inputStream, bArr);
        int i3 = (bArr[1] & 255) | ((bArr[0] & 255) << 8);
        CellImpl cellImpl = new CellImpl(i, i2, i3);
        readAll(inputStream, cellImpl.getCellBytes(), 5, i3);
        return cellImpl;
    }

    @Override // com.subgraph.orchid.Cell
    public int cellBytesConsumed() {
        return this.cellBuffer.position();
    }

    @Override // com.subgraph.orchid.Cell
    public int cellBytesRemaining() {
        return this.cellBuffer.remaining();
    }

    @Override // com.subgraph.orchid.Cell
    public int getByte() {
        return this.cellBuffer.get() & 255;
    }

    @Override // com.subgraph.orchid.Cell
    public void getByteArray(byte[] bArr) {
        this.cellBuffer.get(bArr);
    }

    @Override // com.subgraph.orchid.Cell
    public int getByteAt(int i) {
        return this.cellBuffer.get(i) & 255;
    }

    @Override // com.subgraph.orchid.Cell
    public byte[] getCellBytes() {
        return this.cellBuffer.array();
    }

    @Override // com.subgraph.orchid.Cell
    public int getCircuitId() {
        return this.circuitId;
    }

    @Override // com.subgraph.orchid.Cell
    public int getCommand() {
        return this.command;
    }

    @Override // com.subgraph.orchid.Cell
    public int getInt() {
        return this.cellBuffer.getInt();
    }

    @Override // com.subgraph.orchid.Cell
    public int getShort() {
        return this.cellBuffer.getShort() & 65535;
    }

    @Override // com.subgraph.orchid.Cell
    public int getShortAt(int i) {
        return this.cellBuffer.getShort(i) & 65535;
    }

    @Override // com.subgraph.orchid.Cell
    public void putByte(int i) {
        this.cellBuffer.put((byte) i);
    }

    @Override // com.subgraph.orchid.Cell
    public void putByteArray(byte[] bArr) {
        this.cellBuffer.put(bArr);
    }

    @Override // com.subgraph.orchid.Cell
    public void putByteArray(byte[] bArr, int i, int i2) {
        this.cellBuffer.put(bArr, i, i2);
    }

    @Override // com.subgraph.orchid.Cell
    public void putByteAt(int i, int i2) {
        this.cellBuffer.put(i, (byte) i2);
    }

    @Override // com.subgraph.orchid.Cell
    public void putInt(int i) {
        this.cellBuffer.putInt(i);
    }

    @Override // com.subgraph.orchid.Cell
    public void putShort(int i) {
        this.cellBuffer.putShort((short) i);
    }

    @Override // com.subgraph.orchid.Cell
    public void putShortAt(int i, int i2) {
        this.cellBuffer.putShort(i, (short) i2);
    }

    @Override // com.subgraph.orchid.Cell
    public void putString(String str) {
        byte[] bArr = new byte[str.length() + 1];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= str.length()) {
                putByteArray(bArr);
                return;
            } else {
                bArr[i2] = (byte) str.charAt(i2);
                i = i2 + 1;
            }
        }
    }

    @Override // com.subgraph.orchid.Cell
    public void resetToPayload() {
        this.cellBuffer.reset();
    }

    public String toString() {
        return "Cell: circuit_id=" + this.circuitId + " command=" + this.command + " payload_len=" + this.cellBuffer.position();
    }
}
