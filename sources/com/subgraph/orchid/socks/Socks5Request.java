package com.subgraph.orchid.socks;

import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.TorException;
import java.net.Socket;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/socks/Socks5Request.class */
public class Socks5Request extends SocksRequest {
    static final int SOCKS5_ADDRESS_HOSTNAME = 3;
    static final int SOCKS5_ADDRESS_IPV4 = 1;
    static final int SOCKS5_ADDRESS_IPV6 = 4;
    static final int SOCKS5_AUTH_NONE = 0;
    static final int SOCKS5_COMMAND_CONNECT = 1;
    static final int SOCKS5_COMMAND_RESOLV = 240;
    static final int SOCKS5_COMMAND_RESOLV_PTR = 241;
    static final int SOCKS5_STATUS_COMMAND_NOT_SUPPORTED = 7;
    static final int SOCKS5_STATUS_CONNECTION_REFUSED = 5;
    static final int SOCKS5_STATUS_FAILURE = 1;
    static final int SOCKS5_STATUS_SUCCESS = 0;
    static final int SOCKS5_VERSION = 5;
    private byte[] addressBytes;
    private int addressType;
    private int command;
    private byte[] portBytes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Socks5Request(TorConfig torConfig, Socket socket) {
        super(torConfig, socket);
        this.addressBytes = new byte[0];
        this.portBytes = new byte[0];
    }

    private String addressBytesToHostname() {
        if (this.addressType != 3) {
            throw new TorException("SOCKS 4 request is not a hostname request");
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (true) {
            int i2 = i;
            byte[] bArr = this.addressBytes;
            if (i2 >= bArr.length) {
                return sb.toString();
            }
            sb.append((char) (bArr[i2] & 255));
            i = i2 + 1;
        }
    }

    private boolean processAuthentication() throws SocksRequestException {
        int readByte = readByte();
        boolean z = false;
        for (int i = 0; i < readByte; i++) {
            if (readByte() == 0) {
                z = true;
            }
        }
        if (z) {
            sendAuthenticationResponse(0);
            return true;
        }
        sendAuthenticationResponse(255);
        return false;
    }

    private byte[] readAddressBytes() throws SocksRequestException {
        int i = this.addressType;
        if (i == 1) {
            return readIPv4AddressData();
        }
        if (i == 3) {
            return readHostnameData();
        }
        if (i == 4) {
            return readIPv6AddressData();
        }
        throw new SocksRequestException();
    }

    private byte[] readHostnameData() throws SocksRequestException {
        int readByte = readByte();
        byte[] bArr = new byte[readByte + 1];
        bArr[0] = (byte) readByte;
        readAll(bArr, 1, readByte);
        return bArr;
    }

    private void sendAuthenticationResponse(int i) throws SocksRequestException {
        socketWrite(new byte[]{5, (byte) i});
    }

    private void sendResponse(int i) throws SocksRequestException {
        byte[] bArr = this.addressBytes;
        byte[] bArr2 = new byte[bArr.length + 4 + this.portBytes.length];
        bArr2[0] = 5;
        bArr2[1] = (byte) i;
        bArr2[2] = 0;
        bArr2[3] = (byte) this.addressType;
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        byte[] bArr3 = this.portBytes;
        System.arraycopy(bArr3, 0, bArr2, this.addressBytes.length + 4, bArr3.length);
        socketWrite(bArr2);
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
        if (!processAuthentication()) {
            throw new SocksRequestException("Failed to negotiate authentication");
        }
        if (readByte() != 5) {
            throw new SocksRequestException();
        }
        this.command = readByte();
        readByte();
        this.addressType = readByte();
        this.addressBytes = readAddressBytes();
        this.portBytes = readPortData();
        int i = this.addressType;
        if (i == 1) {
            setIPv4AddressData(this.addressBytes);
        } else {
            if (i != 3) {
                throw new SocksRequestException();
            }
            setHostname(addressBytesToHostname());
        }
        setPortData(this.portBytes);
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public void sendConnectionRefused() throws SocksRequestException {
        sendResponse(5);
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public void sendError(boolean z) throws SocksRequestException {
        if (z) {
            sendResponse(7);
        } else {
            sendResponse(1);
        }
    }

    @Override // com.subgraph.orchid.socks.SocksRequest
    public void sendSuccess() throws SocksRequestException {
        sendResponse(0);
    }
}
