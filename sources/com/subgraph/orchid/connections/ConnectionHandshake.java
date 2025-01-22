package com.subgraph.orchid.connections;

import com.subgraph.orchid.BridgeRouter;
import com.subgraph.orchid.Cell;
import com.subgraph.orchid.ConnectionHandshakeException;
import com.subgraph.orchid.ConnectionIOException;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.circuits.cells.CellImpl;
import com.subgraph.orchid.crypto.TorPublicKey;
import com.subgraph.orchid.data.IPv4Address;
import java.io.IOException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionHandshake.class */
public abstract class ConnectionHandshake {
    private static final Logger logger = Logger.getLogger(ConnectionHandshake.class.getName());
    protected final ConnectionImpl connection;
    private IPv4Address myAddress;
    private int remoteTimestamp;
    protected final SSLSocket socket;
    protected final List<Integer> remoteVersions = new ArrayList();
    private final List<IPv4Address> remoteAddresses = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionHandshake(ConnectionImpl connectionImpl, SSLSocket sSLSocket) {
        this.connection = connectionImpl;
        this.socket = sSLSocket;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConnectionHandshake createHandshake(TorConfig torConfig, ConnectionImpl connectionImpl, SSLSocket sSLSocket) throws ConnectionHandshakeException {
        if (torConfig.getHandshakeV3Enabled() && ConnectionHandshakeV3.sessionSupportsHandshake(sSLSocket.getSession())) {
            return new ConnectionHandshakeV3(connectionImpl, sSLSocket);
        }
        if (torConfig.getHandshakeV2Enabled()) {
            return new ConnectionHandshakeV2(connectionImpl, sSLSocket);
        }
        throw new ConnectionHandshakeException("No valid handshake type available for this connection");
    }

    private void putIPv4Address(Cell cell, IPv4Address iPv4Address) {
        byte[] addressDataBytes = iPv4Address.getAddressDataBytes();
        cell.putByte(4);
        cell.putByte(addressDataBytes.length);
        cell.putByteArray(addressDataBytes);
    }

    private void putMyAddresses(Cell cell) {
        cell.putByte(1);
        putIPv4Address(cell, new IPv4Address(0));
    }

    private void putTimestamp(Cell cell) {
        cell.putInt((int) (new Date().getTime() / 1000));
    }

    private IPv4Address readAddress(Cell cell) {
        int i = cell.getByte();
        int i2 = cell.getByte();
        if (i == 4 && i2 == 4) {
            return new IPv4Address(cell.getInt());
        }
        cell.getByteArray(new byte[i2]);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0070, code lost:            throw new com.subgraph.orchid.ConnectionHandshakeException("Expecting Cell command " + java.util.Arrays.asList(r5) + " and got [ " + r0.getCommand() + " ] instead");     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.subgraph.orchid.Cell expectCell(java.lang.Integer... r5) throws com.subgraph.orchid.ConnectionHandshakeException {
        /*
            r4 = this;
            r0 = r4
            com.subgraph.orchid.connections.ConnectionImpl r0 = r0.connection     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            com.subgraph.orchid.Cell r0 = r0.readConnectionControlCell()     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r9 = r0
            r0 = r5
            int r0 = r0.length     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r7 = r0
            r0 = 0
            r6 = r0
        Le:
            r0 = r6
            r1 = r7
            if (r0 >= r1) goto L2a
            r0 = r5
            r1 = r6
            r0 = r0[r1]     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            int r0 = r0.intValue()     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r8 = r0
            r0 = r9
            int r0 = r0.getCommand()     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r1 = r8
            if (r0 != r1) goto L97
            r0 = r9
            return r0
        L2a:
            r0 = r5
            java.util.List r0 = java.util.Arrays.asList(r0)     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r5 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r1 = r0
            r1.<init>()     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r10 = r0
            r0 = r10
            java.lang.String r1 = "Expecting Cell command "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r0 = r10
            r1 = r5
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r0 = r10
            java.lang.String r1 = " and got [ "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r0 = r10
            r1 = r9
            int r1 = r1.getCommand()     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r0 = r10
            java.lang.String r1 = " ] instead"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            com.subgraph.orchid.ConnectionHandshakeException r0 = new com.subgraph.orchid.ConnectionHandshakeException     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r1 = r0
            r2 = r10
            java.lang.String r2 = r2.toString()     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            r1.<init>(r2)     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
            throw r0     // Catch: com.subgraph.orchid.ConnectionIOException -> L71
        L71:
            r5 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r9 = r0
            r0 = r9
            java.lang.String r1 = "Connection exception while performing handshake "
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r9
            r1 = r5
            java.lang.StringBuilder r0 = r0.append(r1)
            com.subgraph.orchid.ConnectionHandshakeException r0 = new com.subgraph.orchid.ConnectionHandshakeException
            r1 = r0
            r2 = r9
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L97:
            r0 = r6
            r1 = 1
            int r0 = r0 + r1
            r6 = r0
            goto Le
        */
        throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.connections.ConnectionHandshake.expectCell(java.lang.Integer[]):com.subgraph.orchid.Cell");
    }

    IPv4Address getMyAddress() {
        return this.myAddress;
    }

    int getRemoteTimestamp() {
        return this.remoteTimestamp;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void processNetInfo(Cell cell) {
        this.remoteTimestamp = cell.getInt();
        this.myAddress = readAddress(cell);
        int i = cell.getByte();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                return;
            }
            IPv4Address readAddress = readAddress(cell);
            if (readAddress != null) {
                this.remoteAddresses.add(readAddress);
            }
            i2 = i3 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void receiveVersions() throws ConnectionHandshakeException {
        Cell expectCell = expectCell(7);
        while (expectCell.cellBytesRemaining() >= 2) {
            this.remoteVersions.add(Integer.valueOf(expectCell.getShort()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recvNetinfo() throws ConnectionHandshakeException {
        processNetInfo(expectCell(8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void runHandshake() throws IOException, InterruptedException, ConnectionIOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendNetinfo() throws ConnectionIOException {
        CellImpl createCell = CellImpl.createCell(0, 8);
        putTimestamp(createCell);
        putIPv4Address(createCell, this.connection.getRouter().getAddress());
        putMyAddresses(createCell);
        this.connection.sendCell(createCell);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendVersions(int... iArr) throws ConnectionIOException {
        CellImpl createVarCell = CellImpl.createVarCell(0, 7, iArr.length * 2);
        for (int i : iArr) {
            createVarCell.putShort(i);
        }
        this.connection.sendCell(createVarCell);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void verifyIdentityKey(PublicKey publicKey) throws ConnectionHandshakeException {
        if (!(publicKey instanceof RSAPublicKey)) {
            throw new ConnectionHandshakeException("Identity certificate public key is not an RSA key as expected");
        }
        TorPublicKey torPublicKey = new TorPublicKey((RSAPublicKey) publicKey);
        Router router = this.connection.getRouter();
        if (!(router instanceof BridgeRouter) || router.getIdentityHash() != null) {
            if (!torPublicKey.getFingerprint().equals(router.getIdentityHash())) {
                throw new ConnectionHandshakeException("Router identity does not match certificate key");
            }
            return;
        }
        logger.info("Setting Bridge fingerprint from connection handshake for " + router);
        ((BridgeRouter) router).setIdentity(torPublicKey.getFingerprint());
    }
}
