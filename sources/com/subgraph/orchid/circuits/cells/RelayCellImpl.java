package com.subgraph.orchid.circuits.cells;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.CircuitNode;
import com.subgraph.orchid.RelayCell;
import com.subgraph.orchid.TorException;
import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/cells/RelayCellImpl.class */
public class RelayCellImpl extends CellImpl implements RelayCell {
    private final CircuitNode circuitNode;
    private final boolean isOutgoing;
    private final int relayCommand;
    private final int streamId;

    public RelayCellImpl(CircuitNode circuitNode, int i, int i2, int i3) {
        this(circuitNode, i, i2, i3, false);
    }

    public RelayCellImpl(CircuitNode circuitNode, int i, int i2, int i3, boolean z) {
        super(i, z ? 9 : 3);
        this.circuitNode = circuitNode;
        this.relayCommand = i3;
        this.streamId = i2;
        this.isOutgoing = true;
        putByte(i3);
        putShort(0);
        putShort(i2);
        putInt(0);
        putShort(0);
    }

    private RelayCellImpl(CircuitNode circuitNode, byte[] bArr) {
        super(bArr);
        this.circuitNode = circuitNode;
        this.relayCommand = getByte();
        getShort();
        this.streamId = getShort();
        this.isOutgoing = false;
        getInt();
        int i = getShort();
        this.cellBuffer.mark();
        int i2 = i + 14;
        if (i2 > bArr.length) {
            throw new TorException("Header length field exceeds total size of cell");
        }
        this.cellBuffer.limit(i2);
    }

    public static String commandToDescription(int i) {
        switch (i) {
            case 1:
                return "RELAY_BEGIN";
            case 2:
                return "RELAY_DATA";
            case 3:
                return "RELAY_END";
            case 4:
                return "RELAY_CONNECTED";
            case 5:
                return "RELAY_SENDME";
            case 6:
                return "RELAY_EXTEND";
            case 7:
                return "RELAY_EXTENDED";
            case 8:
                return "RELAY_TRUNCATE";
            case 9:
                return "RELAY_TRUNCATED";
            case 10:
                return "RELAY_DROP";
            case 11:
                return "RELAY_RESOLVE";
            case 12:
                return "RELAY_RESOLVED";
            case 13:
                return "RELAY_BEGIN_DIR";
            case 14:
                return "RELAY_EXTEND2";
            case 15:
                return "RELAY_EXTENDED2";
            default:
                return "Relay command = " + i;
        }
    }

    public static RelayCell createFromCell(CircuitNode circuitNode, Cell cell) {
        if (cell.getCommand() == 3) {
            return new RelayCellImpl(circuitNode, cell.getCellBytes());
        }
        throw new TorException("Attempted to create RelayCell from Cell type: " + cell.getCommand());
    }

    public static String reasonToDescription(int i) {
        switch (i) {
            case 1:
                return "Unlisted reason";
            case 2:
                return "Couldn't look up hostname";
            case 3:
                return "Remote host refused connection";
            case 4:
                return "OR refuses to connect to host or port";
            case 5:
                return "Circuit is being destroyed";
            case 6:
                return "Anonymized TCP connection was closed";
            case 7:
                return "Connection timed out, or OR timed out while connecting";
            case 8:
            default:
                return "Reason code " + i;
            case 9:
                return "OR is temporarily hibernating";
            case 10:
                return "Internal error at the OR";
            case 11:
                return "OR has no resources to fulfill request";
            case 12:
                return "Connection was unexpectedly reset";
            case 13:
                return "Tor protocol violation";
            case 14:
                return "Client sent RELAY_BEGIN_DIR to a non-directory server.";
        }
    }

    public String commandToString() {
        int i = this.relayCommand;
        if (i == 9) {
            return commandToDescription(this.relayCommand) + " (" + CellImpl.errorToDescription(getByteAt(14)) + ")";
        }
        if (i != 3) {
            return commandToDescription(i);
        }
        return commandToDescription(this.relayCommand) + " (" + reasonToDescription(getByteAt(14)) + ")";
    }

    @Override // com.subgraph.orchid.RelayCell
    public CircuitNode getCircuitNode() {
        return this.circuitNode;
    }

    @Override // com.subgraph.orchid.RelayCell
    public ByteBuffer getPayloadBuffer() {
        ByteBuffer duplicate = this.cellBuffer.duplicate();
        duplicate.reset();
        return duplicate.slice();
    }

    @Override // com.subgraph.orchid.RelayCell
    public int getRelayCommand() {
        return this.relayCommand;
    }

    @Override // com.subgraph.orchid.RelayCell
    public int getStreamId() {
        return this.streamId;
    }

    @Override // com.subgraph.orchid.RelayCell
    public void setDigest(byte[] bArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 4) {
                return;
            }
            putByteAt(i2 + 8, bArr[i2]);
            i = i2 + 1;
        }
    }

    @Override // com.subgraph.orchid.RelayCell
    public void setLength() {
        putShortAt(12, (short) (cellBytesConsumed() - 14));
    }

    @Override // com.subgraph.orchid.circuits.cells.CellImpl
    public String toString() {
        if (this.isOutgoing) {
            return "[" + commandToDescription(this.relayCommand) + " stream=" + this.streamId + " payload_len=" + (cellBytesConsumed() - 14) + " dest=" + this.circuitNode + "]";
        }
        return "[" + commandToString() + " stream=" + this.streamId + " payload_len=" + this.cellBuffer.remaining() + " source=" + this.circuitNode + "]";
    }
}
