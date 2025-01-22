package com.subgraph.orchid;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Cell.class */
public interface Cell {
    public static final int ADDRESS_TYPE_HOSTNAME = 0;
    public static final int ADDRESS_TYPE_IPV4 = 4;
    public static final int ADRESS_TYPE_IPV6 = 6;
    public static final int AUTHENTICATE = 131;
    public static final int AUTHORIZE = 132;
    public static final int AUTH_CHALLENGE = 130;
    public static final int CELL_HEADER_LEN = 3;
    public static final int CELL_LEN = 512;
    public static final int CELL_PAYLOAD_LEN = 509;
    public static final int CELL_VAR_HEADER_LEN = 5;
    public static final int CERTS = 129;
    public static final int CREATE = 1;
    public static final int CREATED = 2;
    public static final int CREATED_FAST = 6;
    public static final int CREATE_FAST = 5;
    public static final int DESTROY = 4;
    public static final int ERROR_CONNECTFAILED = 6;
    public static final int ERROR_DESTROYED = 11;
    public static final int ERROR_FINISHED = 9;
    public static final int ERROR_HIBERNATING = 4;
    public static final int ERROR_INTERNAL = 2;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NOSUCHSERVICE = 12;
    public static final int ERROR_OR_CONN_CLOSED = 8;
    public static final int ERROR_OR_IDENTITY = 7;
    public static final int ERROR_PROTOCOL = 1;
    public static final int ERROR_REQUESTED = 3;
    public static final int ERROR_RESOURCELIMIT = 5;
    public static final int ERROR_TIMEOUT = 10;
    public static final int NETINFO = 8;
    public static final int PADDING = 0;
    public static final int RELAY = 3;
    public static final int RELAY_EARLY = 9;
    public static final int VERSIONS = 7;
    public static final int VPADDING = 128;

    int cellBytesConsumed();

    int cellBytesRemaining();

    int getByte();

    void getByteArray(byte[] bArr);

    int getByteAt(int i);

    byte[] getCellBytes();

    int getCircuitId();

    int getCommand();

    int getInt();

    int getShort();

    int getShortAt(int i);

    void putByte(int i);

    void putByteArray(byte[] bArr);

    void putByteArray(byte[] bArr, int i, int i2);

    void putByteAt(int i, int i2);

    void putInt(int i);

    void putShort(int i);

    void putShortAt(int i, int i2);

    void putString(String str);

    void resetToPayload();
}
