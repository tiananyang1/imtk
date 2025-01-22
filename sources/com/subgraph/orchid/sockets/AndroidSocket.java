package com.subgraph.orchid.sockets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/AndroidSocket.class */
public class AndroidSocket extends Socket {
    private static final Logger logger = Logger.getLogger(AndroidSocket.class.getName());
    private final OrchidSocketImpl impl;
    private final Field isConnectedField;
    private boolean isSocketConnected;
    private final Object lock;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AndroidSocket(OrchidSocketImpl orchidSocketImpl) throws SocketException {
        super(orchidSocketImpl);
        this.lock = new Object();
        this.impl = orchidSocketImpl;
        this.isConnectedField = getField("isConnected");
    }

    private Field getField(String str) {
        try {
            Field declaredField = Socket.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (NoSuchFieldException e) {
            logger.warning("Could not locate field '" + str + "' in Socket class, disabling Android reflection");
            return null;
        } catch (SecurityException e2) {
            logger.warning("Reflection access to field '" + str + "' in Socket class not permitted." + e2.getMessage());
            return null;
        }
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress) throws IOException {
        connect(socketAddress, 0);
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i) throws IOException {
        synchronized (this.lock) {
            if (this.isSocketConnected) {
                throw new SocketException("Already connected");
            }
            try {
                this.impl.connect(socketAddress, i);
                setIsConnected();
            } catch (IOException e) {
                this.impl.close();
                throw e;
            }
        }
    }

    protected void setIsConnected() {
        this.isSocketConnected = true;
        try {
            if (this.isConnectedField != null) {
                this.isConnectedField.setBoolean(this, true);
            }
        } catch (IllegalAccessException e) {
            logger.warning("Illegal access trying to reflect value into isConnected field of Socket : " + e.getMessage());
        } catch (IllegalArgumentException e2) {
            logger.warning("Illegal argument trying to reflect value into isConnected field of Socket : " + e2.getMessage());
        }
    }
}
