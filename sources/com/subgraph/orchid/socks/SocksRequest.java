package com.subgraph.orchid.socks;

import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.data.IPv4Address;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/socks/SocksRequest.class */
public abstract class SocksRequest {
    private static final Logger logger = Logger.getLogger(SocksRequest.class.getName());
    private IPv4Address address;
    private byte[] addressData;
    private final TorConfig config;
    private String hostname;
    private long lastWarningTimestamp = 0;
    private int port;
    private final Socket socket;

    /* JADX INFO: Access modifiers changed from: protected */
    public SocksRequest(TorConfig torConfig, Socket socket) {
        this.config = torConfig;
        this.socket = socket;
    }

    private void logUnsafeSOCKS() throws SocksRequestException {
        if ((this.config.getWarnUnsafeSocks() || this.config.getSafeSocks()) && testRateLimit()) {
            logger.warning("Your application is giving Orchid only an IP address.  Applications that do DNS resolves themselves may leak information. Consider using Socks4a (e.g. via privoxy or socat) instead.  For more information please see https://wiki.torproject.org/TheOnionRouter/TorFAQ#SOCKSAndDNS");
        }
        if (this.config.getSafeSocks()) {
            throw new SocksRequestException("Rejecting unsafe SOCKS request");
        }
    }

    private boolean testRateLimit() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.lastWarningTimestamp;
        this.lastWarningTimestamp = currentTimeMillis;
        return currentTimeMillis - j > 5000;
    }

    public IPv4Address getAddress() {
        return this.address;
    }

    public abstract int getCommandCode();

    public String getHostname() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    public String getTarget() {
        if (this.config.getSafeLogging()) {
            return "[scrubbed]:" + this.port;
        }
        if (this.hostname != null) {
            return this.hostname + Constants.COLON_SEPARATOR + this.port;
        }
        return this.address + Constants.COLON_SEPARATOR + this.port;
    }

    public boolean hasHostname() {
        return this.hostname != null;
    }

    public abstract boolean isConnectRequest();

    protected void readAll(byte[] bArr) throws SocksRequestException {
        readAll(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void readAll(byte[] bArr, int i, int i2) throws SocksRequestException {
        while (i2 > 0) {
            try {
                int read = this.socket.getInputStream().read(bArr, i, i2);
                if (read == -1) {
                    throw new SocksRequestException();
                }
                i += read;
                i2 -= read;
            } catch (IOException e) {
                throw new SocksRequestException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readByte() throws SocksRequestException {
        try {
            int read = this.socket.getInputStream().read();
            if (read != -1) {
                return read;
            }
            throw new SocksRequestException();
        } catch (IOException e) {
            throw new SocksRequestException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] readIPv4AddressData() throws SocksRequestException {
        byte[] bArr = new byte[4];
        readAll(bArr);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] readIPv6AddressData() throws SocksRequestException {
        byte[] bArr = new byte[16];
        readAll(bArr);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String readNullTerminatedString() throws SocksRequestException {
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = this.socket.getInputStream().read();
                if (read == -1) {
                    throw new SocksRequestException();
                }
                if (read == 0) {
                    return sb.toString();
                }
                sb.append((char) read);
            }
        } catch (IOException e) {
            throw new SocksRequestException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] readPortData() throws SocksRequestException {
        byte[] bArr = new byte[2];
        readAll(bArr, 0, 2);
        return bArr;
    }

    public abstract void readRequest() throws SocksRequestException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void sendConnectionRefused() throws SocksRequestException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void sendError(boolean z) throws SocksRequestException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void sendSuccess() throws SocksRequestException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void setHostname(String str) {
        this.hostname = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setIPv4AddressData(byte[] bArr) throws SocksRequestException {
        logUnsafeSOCKS();
        if (bArr.length != 4) {
            throw new SocksRequestException();
        }
        this.addressData = bArr;
        int i = 0;
        for (byte b : this.addressData) {
            i = (i << 8) | (b & 255);
        }
        this.address = new IPv4Address(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPortData(byte[] bArr) throws SocksRequestException {
        if (bArr.length != 2) {
            throw new SocksRequestException();
        }
        this.port = (bArr[1] & 255) | ((bArr[0] & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void socketWrite(byte[] bArr) throws SocksRequestException {
        try {
            this.socket.getOutputStream().write(bArr);
        } catch (IOException e) {
            throw new SocksRequestException(e);
        }
    }
}
