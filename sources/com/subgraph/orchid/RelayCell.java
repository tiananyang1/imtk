package com.subgraph.orchid;

import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/RelayCell.class */
public interface RelayCell extends Cell {
    public static final int DIGEST_OFFSET = 8;
    public static final int HEADER_SIZE = 14;
    public static final int LENGTH_OFFSET = 12;
    public static final int REASON_CONNECTREFUSED = 3;
    public static final int REASON_CONNRESET = 12;
    public static final int REASON_DESTROY = 5;
    public static final int REASON_DONE = 6;
    public static final int REASON_EXITPOLICY = 4;
    public static final int REASON_HIBERNATING = 9;
    public static final int REASON_INTERNAL = 10;
    public static final int REASON_MISC = 1;
    public static final int REASON_NOROUTE = 8;
    public static final int REASON_NOTDIRECTORY = 14;
    public static final int REASON_RESOLVEFAILED = 2;
    public static final int REASON_RESOURCELIMIT = 11;
    public static final int REASON_TIMEOUT = 7;
    public static final int REASON_TORPROTOCOL = 13;
    public static final int RECOGNIZED_OFFSET = 4;
    public static final int RELAY_BEGIN = 1;
    public static final int RELAY_BEGIN_DIR = 13;
    public static final int RELAY_COMMAND_ESTABLISH_INTRO = 32;
    public static final int RELAY_COMMAND_ESTABLISH_RENDEZVOUS = 33;
    public static final int RELAY_COMMAND_INTRODUCE1 = 34;
    public static final int RELAY_COMMAND_INTRODUCE2 = 35;
    public static final int RELAY_COMMAND_INTRODUCE_ACK = 40;
    public static final int RELAY_COMMAND_INTRO_ESTABLISHED = 38;
    public static final int RELAY_COMMAND_RENDEZVOUS1 = 36;
    public static final int RELAY_COMMAND_RENDEZVOUS2 = 37;
    public static final int RELAY_COMMAND_RENDEZVOUS_ESTABLISHED = 39;
    public static final int RELAY_CONNECTED = 4;
    public static final int RELAY_DATA = 2;
    public static final int RELAY_DROP = 10;
    public static final int RELAY_END = 3;
    public static final int RELAY_EXTEND = 6;
    public static final int RELAY_EXTEND2 = 14;
    public static final int RELAY_EXTENDED = 7;
    public static final int RELAY_EXTENDED2 = 15;
    public static final int RELAY_RESOLVE = 11;
    public static final int RELAY_RESOLVED = 12;
    public static final int RELAY_SENDME = 5;
    public static final int RELAY_TRUNCATE = 8;
    public static final int RELAY_TRUNCATED = 9;

    CircuitNode getCircuitNode();

    ByteBuffer getPayloadBuffer();

    int getRelayCommand();

    int getStreamId();

    void setDigest(byte[] bArr);

    void setLength();
}
