package com.squareup.okhttp;

import java.net.Socket;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/Connection.class */
public interface Connection {
    Handshake getHandshake();

    Protocol getProtocol();

    Route getRoute();

    Socket getSocket();
}
