package com.subgraph.orchid.socks;

import com.subgraph.orchid.TorConfig;
import java.net.Socket;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/socks/Socks4Request.class */
public class Socks4Request extends SocksRequest {
    static final int SOCKS_COMMAND_CONNECT = 1;
    static final int SOCKS_COMMAND_RESOLV = 240;
    private static final int SOCKS_STATUS_FAILURE = 91;
    private static final int SOCKS_STATUS_SUCCESS = 90;
    private int command;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Socks4Request(TorConfig torConfig, Socket socket) {
        super(torConfig, socket);
    }

    private boolean isVersion4aHostname(byte[] bArr) {
        boolean z = false;
        if (bArr.length != 4) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 3) {
                if (bArr[3] != 0) {
                    z = true;
                }
                return z;
            }
            if (bArr[i2] != 0) {
                return false;
            }
            i = i2 + 1;
        }
    }

    private void sendResponse(int i) throws SocksRequestException {
        byte[] bArr = new byte[8];
        bArr[0] = 0;
        bArr[1] = (byte) i;
        socketWrite(bArr);
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public int getCommandCode() {
        return this.command;
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public boolean isConnectRequest() {
        return this.command == 1;
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public void readRequest() throws SocksRequestException {
        this.command = readByte();
        setPortData(readPortData());
        byte[] readIPv4AddressData = readIPv4AddressData();
        readNullTerminatedString();
        if (isVersion4aHostname(readIPv4AddressData)) {
            setHostname(readNullTerminatedString());
        } else {
            setIPv4AddressData(readIPv4AddressData);
        }
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public void sendConnectionRefused() throws SocksRequestException {
        sendError(false);
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public void sendError(boolean z) throws SocksRequestException {
        sendResponse(91);
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public void sendSuccess() throws SocksRequestException {
        sendResponse(90);
    }
}
